
## **Building a Secure REST API with Spring Boot (JWT Based)**

> **Lab Goal**
> Build a **secure, production-ready REST API** using **Spring Boot best practices** —
> authentication, authorization, validation, secrets, logging, and security headers.

## 🧠 What You Will Learn

By the end of this lab, you will:

* Build a secure Spring Boot REST API
* Implement JWT authentication
* Apply role-based authorization
* Validate inputs safely
* Secure secrets & configuration
* Add logging & security headers
* Understand **why each security step exists**

## 🛠 Prerequisites

* Java 17+
* Maven
* VS Code
* Postman / curl
* Basic Java & HTTP knowledge

## 🧩 Lab Scenario

You are building a **Product Management API** for an internal system.

### Requirements

* Only authenticated users can access APIs
* Only **ADMIN** can add products
* Inputs must be validated
* Secrets must not be hardcoded
* All access must be logged

# 🔹 Step 1: Create Spring Boot Project (Maven)

### Option A: Spring Initializr (Recommended)

* Project: **Maven**
* Language: **Java**
* Spring Boot: latest
* Packaging: **Jar**
* Java: **17**

### Dependencies

✔ Spring Web
✔ Spring Security
✔ Validation
✔ Lombok (optional)

Download & unzip.


### Option B: Verify in VS Code

```bash
cd secure-product-api
code .
```

Run application:

```bash
./mvnw spring-boot:run
```

✅ **Checkpoint:** App runs on `https://localhost:8080`

# 🔹 Step 2: Understand Spring Security Pipeline

```
Request
  |
HTTPS
  |
Security Filter Chain
  |
Authentication Filter (JWT)
  |
Authorization Rules
  |
Validation
  |
Controller
  |
Service
  |
Logging
  |
Response
```

📌 **Security in Spring happens in filters, not controllers**


# 🔹 Step 3: Add JWT Dependencies

### Add to `pom.xml`

```xml
<dependency>
    <groupId>io.jsonwebtoken</groupId>
    <artifactId>jjwt-api</artifactId>
    <version>0.11.5</version>
</dependency>

<dependency>
    <groupId>io.jsonwebtoken</groupId>
    <artifactId>jjwt-impl</artifactId>
    <scope>runtime</scope>
</dependency>

<dependency>
    <groupId>io.jsonwebtoken</groupId>
    <artifactId>jjwt-jackson</artifactId>
    <scope>runtime</scope>
</dependency>
```

📌 Use **battle-tested JWT libraries**, never custom crypto.

# 🔹 Step 4: Secure Secrets (application.yml)

### `application.yml`

```yaml
jwt:
  secret: ${JWT_SECRET}
  issuer: secure-api
  expiration: 1800000
```

### Set environment variable

```bash
export JWT_SECRET=SUPER_SECRET_KEY_123
```

📌 Secrets **never live in source code**

# 🔹 Step 5: Create DTO & Validation

### Product DTO

```java
public class CreateProductDto {

    @NotBlank
    @Size(max = 100)
    private String name;

    @Min(1)
    @Max(100000)
    private double price;

    // getters & setters
}
```

📌 Validation occurs **before controller logic**

# 🔹 Step 6: JWT Utility Class

```java
@Component
public class JwtUtil {

    @Value("${jwt.secret}")
    private String secret;

    @Value("${jwt.issuer}")
    private String issuer;

    @Value("${jwt.expiration}")
    private long expiration;

    public String generateToken(String username, String role) {

        return Jwts.builder()
            .setSubject(username)
            .claim("role", role)
            .setIssuer(issuer)
            .setIssuedAt(new Date())
            .setExpiration(new Date(System.currentTimeMillis() + expiration))
            .signWith(Keys.hmacShaKeyFor(secret.getBytes()))
            .compact();
    }
}
```

📌 JWT = **signed proof**, not session storage

# 🔹 Step 7: Create Authentication Controller

```java
@RestController
@RequestMapping("/auth")
public class AuthController {

    private final JwtUtil jwtUtil;

    public AuthController(JwtUtil jwtUtil) {
        this.jwtUtil = jwtUtil;
    }

    @PostMapping("/login")
    public Map<String, String> login() {

        // Demo user
        String token = jwtUtil.generateToken("student", "ADMIN");

        return Map.of("token", token);
    }
}
```

📌 Real apps validate credentials via DB.

# 🔹 Step 8: JWT Authentication Filter

```java
@Component
public class JwtAuthFilter extends OncePerRequestFilter {

    private final JwtUtil jwtUtil;

    public JwtAuthFilter(JwtUtil jwtUtil) {
        this.jwtUtil = jwtUtil;
    }

    @Override
    protected void doFilterInternal(
        HttpServletRequest request,
        HttpServletResponse response,
        FilterChain filterChain)
        throws ServletException, IOException {

        String header = request.getHeader("Authorization");

        if (header != null && header.startsWith("Bearer ")) {
            // validate token (simplified)
        }

        filterChain.doFilter(request, response);
    }
}
```

📌 Filters execute **before controllers**

# 🔹 Step 9: Configure Security Filter Chain

```java
@Configuration
@EnableMethodSecurity
public class SecurityConfig {

    @Bean
    SecurityFilterChain filterChain(HttpSecurity http) throws Exception {

        http
            .csrf(csrf -> csrf.disable())
            .sessionManagement(sm ->
                sm.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
            .authorizeHttpRequests(auth -> auth
                .requestMatchers("/auth/**").permitAll()
                .anyRequest().authenticated())
            .addFilterBefore(jwtFilter(),
                UsernamePasswordAuthenticationFilter.class);

        return http.build();
    }
}
```

📌 Stateless = **cloud-ready**

---

# 🔹 Step 🔟 Secure Controller with Roles

```java
@RestController
@RequestMapping("/api/products")
public class ProductController {

    @GetMapping
    public String getProducts() {
        return "Secure product list";
    }

    @PostMapping
    @PreAuthorize("hasRole('ADMIN')")
    public String createProduct(
        @Valid @RequestBody CreateProductDto dto) {

        return "Product created";
    }
}
```

📌 UI hiding ≠ API security

# 🔹 Step 1️⃣1️⃣ Add Security Headers

```java
@Component
public class SecurityHeadersFilter extends OncePerRequestFilter {

    @Override
    protected void doFilterInternal(
        HttpServletRequest request,
        HttpServletResponse response,
        FilterChain filterChain)
        throws ServletException, IOException {

        response.setHeader("X-Frame-Options", "DENY");
        response.setHeader("X-Content-Type-Options", "nosniff");
        response.setHeader("Referrer-Policy", "no-referrer");

        filterChain.doFilter(request, response);
    }
}
```

📌 Browser-level protection = **zero performance cost**

# 🔹 Step 1️⃣2️⃣ Logging Access (SLF4J)

```java
@Slf4j
@RestController
public class AuditController {

    @GetMapping("/audit")
    public String audit(Authentication auth) {

        log.info("User {} accessed /audit",
            auth.getName());

        return "Logged";
    }
}
```

📌 Log **actions**, not secrets

# 🔹 Step 1️⃣3️⃣ Secure Error Handling

```java
@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(Exception.class)
    public ResponseEntity<?> handle() {
        return ResponseEntity
            .status(HttpStatus.INTERNAL_SERVER_ERROR)
            .body("Something went wrong");
    }
}
```

📌 Never expose stack traces in prod


# 🔹 Step 1️⃣4️⃣ Test with Postman

| Scenario      | Result |
| ------------- | ------ |
| No token      | 401    |
| Invalid token | 401    |
| User token    | 403    |
| Admin token   | 200    |

📌 **Security success = correct blocking**

## 🧠 Final Student Checklist

✔ HTTPS
✔ JWT authentication
✔ Role authorization
✔ Validation
✔ Secrets externalized
✔ Logging
✔ Security headers

## 🧑‍🏫 Mentor Insight (Spring + .NET Common Truth)

> Frameworks differ
> Security principles don’t.

If a student understands this lab:

* Spring Security becomes logical
* JWT stops being “magic”
* Architecture fear disappears

## 🚀 Optional Extensions

* Add JPA + parameterized queries
* Integrate Keycloak / Auth0
* Add rate limiting
* Run OWASP ZAP scan
* Convert to microservices

### 🏁 Final Thought

> **Security is not an add-on.
> It’s an architectural decision made on Day One.**
