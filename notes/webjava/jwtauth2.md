
 

## 🛠️ Approaches to Implement Custom Middleware in Spring Boot


**Quick Answer:** In Spring Boot, the equivalent of ASP.NET Core middleware is implemented using **Filters** or **Interceptors**. Filters sit at the servlet level and intercept requests before they reach controllers, while Interceptors work at the Spring MVC level and can run before and after controller execution. Both allow you to add custom logic such as logging, authentication, or modifying requests/responses.


### 1. Using **Servlet Filters**
Filters are part of the Java Servlet specification and work at a lower level.

```java
import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class LoggingFilter implements Filter {
    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, jakarta.servlet.ServletException {
        
        HttpServletRequest req = (HttpServletRequest) request;
        System.out.println("➡️ Request URI: " + req.getRequestURI());
        
        chain.doFilter(request, response); // pass to next filter/controller
        
        System.out.println("⬅️ Response completed");
    }
}
```

- **Pros:** Works for all requests (REST, static resources, etc.).  
- **Cons:** Less fine-grained compared to Interceptors.


### 2. Using **Spring MVC Interceptors**
Interceptors are more Spring-specific and allow pre- and post-processing around controller methods.

```java
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class RequestInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
            throws Exception {
        System.out.println("➡️ PreHandle: " + request.getRequestURI());
        return true; // continue to controller
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response,
                                Object handler, Exception ex) throws Exception {
        System.out.println("⬅️ AfterCompletion: " + response.getStatus());
    }
}
```

Register the interceptor:

```java
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {
    @Autowired
    private RequestInterceptor requestInterceptor;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(requestInterceptor);
    }
}
```

- **Pros:** Integrated with Spring MVC, can target specific endpoints.  
- **Cons:** Only applies to controller requests (not static files).

### 3. Using **Aspect-Oriented Programming (AOP)**
For cross-cutting concerns (like logging or security), you can use AOP with `@Aspect`.

```java
@Aspect
@Component
public class LoggingAspect {
    @Before("execution(* com.example.controller.*.*(..))")
    public void logBefore(JoinPoint joinPoint) {
        System.out.println("📌 Before method: " + joinPoint.getSignature());
    }
}
```

## ⚖️ Comparison

| Approach       | Scope                        | Best Use Case                  |
|----------------|------------------------------|--------------------------------|
| **Filter**     | All requests (servlet level) | Security, request validation   |
| **Interceptor**| Controller requests only     | Logging, auth, role checks     |
| **AOP**        | Method-level cross-cutting   | Auditing, performance metrics  |


## 🚨 Risks & Considerations
- **Filters** run for everything, so be careful not to block static resources unintentionally.  
- **Interceptors** won’t catch non-controller requests (like static files).  
- **AOP** can be powerful but may add complexity if overused.  



👉 In practice, **Filters** are best for global concerns (security, compression), **Interceptors** for request/response handling in APIs, and **AOP** for cross-cutting concerns like logging or metrics.  



Great, Ravi! Let’s build a **real-world Spring Boot example** where a **custom interceptor** enforces **role-based access** — similar to what you did in ASP.NET Core.  

---

## 🛠️ Step 1: Create a Custom Interceptor

```java
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

@Component
public class RoleInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request,
                             HttpServletResponse response,
                             Object handler) throws Exception {

        // Example: read role from session
        String role = (String) request.getSession().getAttribute("UserRole");

        if (role == null) {
            role = "Guest"; // default if not logged in
        }

        System.out.println("👤 Current Role: " + role);

        // Block Guests from accessing /admin routes
        if ("Guest".equals(role) && request.getRequestURI().startsWith("/admin")) {
            response.setStatus(HttpServletResponse.SC_FORBIDDEN);
            response.getWriter().write("Access denied for Guests.");
            return false; // stop request here
        }

        return true; // allow request to continue
    }
}
```

---

## 🛠️ Step 2: Register the Interceptor

```java
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Autowired
    private RoleInterceptor roleInterceptor;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(roleInterceptor)
                .addPathPatterns("/**"); // apply to all routes
    }
}
```

---

## 🛠️ Step 3: Set Role in Session (e.g., after login)

```java
import org.springframework.web.bind.annotation.*;

import jakarta.servlet.http.HttpSession;

@RestController
@RequestMapping("/account")
public class AccountController {

    @PostMapping("/login")
    public String login(@RequestParam String username,
                        @RequestParam String password,
                        HttpSession session) {

        if ("ravi".equals(username) && "password123".equals(password)) {
            session.setAttribute("UserRole", "Admin");
            return "Logged in as Admin!";
        } else if ("guest".equals(username) && "guest123".equals(password)) {
            session.setAttribute("UserRole", "User");
            return "Logged in as User!";
        }

        return "Invalid credentials!";
    }

    @GetMapping("/logout")
    public String logout(HttpSession session) {
        session.invalidate();
        return "Logged out!";
    }
}
```

---

## 🧩 How It Works
- When a request comes in, the **RoleInterceptor** checks the user’s role from the session.  
- If the role is `"Guest"` and the request path starts with `/admin`, the interceptor blocks it with a **403 Forbidden**.  
- Otherwise, the request continues to the controller.  



Perfect, Ravi! Let’s now implement **role-based authorization using Spring Security** — this is the standard and most powerful way in Spring Boot.  

---

## 🛠️ Step 1: Add Spring Security Dependency
In your `pom.xml` (Maven):

```xml
<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-security</artifactId>
</dependency>
```

Or in `build.gradle` (Gradle):

```gradle
implementation 'org.springframework.boot:spring-boot-starter-security'
```

---

## 🛠️ Step 2: Configure Security

In modern Spring Boot (Spring Security 6+), you use a `SecurityFilterChain` bean instead of extending `WebSecurityConfigurerAdapter`.

```java
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
            .authorizeHttpRequests(auth -> auth
                .requestMatchers("/admin/**").hasRole("ADMIN")
                .requestMatchers("/user/**").hasRole("USER")
                .requestMatchers("/", "/public/**", "/account/**").permitAll()
                .anyRequest().authenticated()
            )
            .formLogin(login -> login
                .loginPage("/account/login") // custom login page
                .permitAll()
            )
            .logout(logout -> logout
                .logoutUrl("/account/logout")
                .permitAll()
            );

        return http.build();
    }
}
```

---

## 🛠️ Step 3: Define Users and Roles

For simplicity, you can use **in-memory authentication**:

```java
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

@Configuration
public class UserConfig {

    @Bean
    public UserDetailsService userDetailsService() {
        var admin = User.withUsername("ravi")
                .password("{noop}password123") // {noop} means no password encoding
                .roles("ADMIN")
                .build();

        var user = User.withUsername("guest")
                .password("{noop}guest123")
                .roles("USER")
                .build();

        return new InMemoryUserDetailsManager(admin, user);
    }
}
```

---

## 🛠️ Step 4: Secure Controllers

```java
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DemoController {

    @GetMapping("/admin/dashboard")
    public String adminDashboard() {
        return "Welcome Admin!";
    }

    @GetMapping("/user/profile")
    public String userProfile() {
        return "Welcome User!";
    }

    @GetMapping("/public/info")
    public String publicInfo() {
        return "This page is public.";
    }
}
```

---

## 🔑 How It Works
- `/admin/**` → Only accessible to users with role `ADMIN`.  
- `/user/**` → Only accessible to users with role `USER`.  
- `/public/**` → Accessible to everyone.  
- Login form is automatically provided by Spring Security (or you can customize it).  


Perfect, Ravi! Let’s now extend your Spring Boot API into a **JWT-based authentication and role-based authorization** setup. This makes your API **stateless** (no session storage needed) and ideal for mobile/web clients.  

---

## 🛠️ Step 1: Add Dependencies
In `pom.xml`:

```xml
<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-security</artifactId>
</dependency>

<dependency>
    <groupId>io.jsonwebtoken</groupId>
    <artifactId>jjwt-api</artifactId>
    <version>0.11.5</version>
</dependency>
<dependency>
    <groupId>io.jsonwebtoken</groupId>
    <artifactId>jjwt-impl</artifactId>
    <version>0.11.5</version>
    <scope>runtime</scope>
</dependency>
<dependency>
    <groupId>io.jsonwebtoken</groupId>
    <artifactId>jjwt-jackson</artifactId>
    <version>0.11.5</version>
    <scope>runtime</scope>
</dependency>
```

---

## 🛠️ Step 2: JWT Utility Class

```java
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;

import java.security.Key;
import java.util.Date;

public class JwtUtil {
    private static final Key key = Keys.secretKeyFor(SignatureAlgorithm.HS256);

    public static String generateToken(String username, String role) {
        return Jwts.builder()
                .setSubject(username)
                .claim("role", role)
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 60)) // 1 hour
                .signWith(key)
                .compact();
    }

    public static String getRole(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(key)
                .build()
                .parseClaimsJws(token)
                .getBody()
                .get("role", String.class);
    }

    public static String getUsername(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(key)
                .build()
                .parseClaimsJws(token)
                .getBody()
                .getSubject();
    }
}
```

---

## 🛠️ Step 3: JWT Filter

```java
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.List;

@Component
public class JwtFilter extends OncePerRequestFilter {

    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain chain) throws ServletException, IOException {

        String authHeader = request.getHeader("Authorization");

        if (authHeader != null && authHeader.startsWith("Bearer ")) {
            String token = authHeader.substring(7);

            try {
                String username = JwtUtil.getUsername(token);
                String role = JwtUtil.getRole(token);

                var auth = new UsernamePasswordAuthenticationToken(
                        username,
                        null,
                        List.of(() -> "ROLE_" + role) // Spring expects ROLE_ prefix
                );
                auth.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));

                SecurityContextHolder.getContext().setAuthentication(auth);
            } catch (Exception e) {
                response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
                return;
            }
        }

        chain.doFilter(request, response);
    }
}
```

---

## 🛠️ Step 4: Security Configuration

```java
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
public class SecurityConfig {

    private final JwtFilter jwtFilter;

    public SecurityConfig(JwtFilter jwtFilter) {
        this.jwtFilter = jwtFilter;
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.csrf(csrf -> csrf.disable())
            .authorizeHttpRequests(auth -> auth
                .requestMatchers("/auth/**").permitAll()
                .requestMatchers("/admin/**").hasRole("ADMIN")
                .requestMatchers("/user/**").hasRole("USER")
                .anyRequest().authenticated()
            )
            .addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class);

        return http.build();
    }
}
```

---

## 🛠️ Step 5: Auth Controller

```java
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @PostMapping("/login")
    public String login(@RequestParam String username, @RequestParam String password) {
        if ("ravi".equals(username) && "password123".equals(password)) {
            return JwtUtil.generateToken(username, "ADMIN");
        } else if ("guest".equals(username) && "guest123".equals(password)) {
            return JwtUtil.generateToken(username, "USER");
        }
        return "Invalid credentials!";
    }
}
```

---

## 🛠️ Step 6: Protected Endpoints

```java
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DemoController {

    @GetMapping("/admin/dashboard")
    public String adminDashboard() {
        return "Welcome Admin!";
    }

    @GetMapping("/user/profile")
    public String userProfile() {
        return "Welcome User!";
    }
}
```

---

## 🔑 How It Works
- Client logs in via `/auth/login` → receives a **JWT token**.  
- Client sends requests with `Authorization: Bearer <token>`.  
- The **JwtFilter** validates the token and sets the user’s role in the security context.  
- Spring Security enforces role-based access (`/admin/**` vs `/user/**`).  


Perfect, Ravi! Let’s extend our **Spring Boot JWT authentication** setup to include a **refresh token mechanism**. This ensures users don’t get logged out immediately when their access token expires — instead, they can request a new access token using a refresh token.

---

## 🛠️ Step 1: Update JWT Utility to Handle Refresh Tokens

We’ll generate **two tokens** at login:
- **Access Token** → short-lived (e.g., 15 minutes)
- **Refresh Token** → long-lived (e.g., 7 days)

```java
public class JwtUtil {
    private static final Key key = Keys.secretKeyFor(SignatureAlgorithm.HS256);

    public static String generateAccessToken(String username, String role) {
        return Jwts.builder()
                .setSubject(username)
                .claim("role", role)
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 15)) // 15 minutes
                .signWith(key)
                .compact();
    }

    public static String generateRefreshToken(String username) {
        return Jwts.builder()
                .setSubject(username)
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 60 * 24 * 7)) // 7 days
                .signWith(key)
                .compact();
    }

    public static String getUsername(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(key)
                .build()
                .parseClaimsJws(token)
                .getBody()
                .getSubject();
    }
}
```

---

## 🛠️ Step 2: Auth Controller with Refresh Endpoint

```java
@RestController
@RequestMapping("/auth")
public class AuthController {

    @PostMapping("/login")
    public Map<String, String> login(@RequestParam String username, @RequestParam String password) {
        Map<String, String> tokens = new HashMap<>();

        if ("ravi".equals(username) && "password123".equals(password)) {
            tokens.put("accessToken", JwtUtil.generateAccessToken(username, "ADMIN"));
            tokens.put("refreshToken", JwtUtil.generateRefreshToken(username));
        } else if ("guest".equals(username) && "guest123".equals(password)) {
            tokens.put("accessToken", JwtUtil.generateAccessToken(username, "USER"));
            tokens.put("refreshToken", JwtUtil.generateRefreshToken(username));
        } else {
            tokens.put("error", "Invalid credentials");
        }

        return tokens;
    }

    @PostMapping("/refresh")
    public Map<String, String> refresh(@RequestParam String refreshToken) {
        Map<String, String> tokens = new HashMap<>();
        try {
            String username = JwtUtil.getUsername(refreshToken);

            // In real-world apps, validate refresh token against DB/Redis
            tokens.put("accessToken", JwtUtil.generateAccessToken(username, "USER")); 
            tokens.put("refreshToken", refreshToken); // reuse existing refresh token
        } catch (Exception e) {
            tokens.put("error", "Invalid refresh token");
        }
        return tokens;
    }
}
```

---

## 🛠️ Step 3: Workflow

1. **Login** → Client receives both `accessToken` and `refreshToken`.  
2. **Use Access Token** → Client sends requests with `Authorization: Bearer <accessToken>`.  
3. **When Access Token Expires** → Client calls `/auth/refresh` with the `refreshToken`.  
4. **Server Issues New Access Token** → Client continues without re-login.  

---

## 🔑 Best Practices
- Store **refresh tokens securely** (e.g., HttpOnly cookies or secure storage).  
- Consider **revoking refresh tokens** on logout or password change.  
- Use **database/Redis** to track refresh tokens for extra security.  

 
👉 Now you have a **complete JWT authentication system with refresh tokens** in Spring Boot.  

