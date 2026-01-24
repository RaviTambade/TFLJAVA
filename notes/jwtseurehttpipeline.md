## 🌐 Spring Boot HTTP Request–Response Pipeline
### (Security • Filters • Routing • Controller)

```
┌────────────────────┐
│   Client / Browser │
│  (Postman / Front) │
└─────────┬──────────┘
          │  HTTP Request
          ▼
┌────────────────────┐
│ Embedded Web Server│
│ (Tomcat / Jetty)   │
│  - Accepts request │
│  - Creates servlet │
└─────────┬──────────┘
          │
          ▼
┌──────────────────────────────────────────┐
│        Servlet Filter Chain              │
│                                          │
│  ┌────────────────────────────────────┐  │
│  │ Spring Security Filters            │  │
│  │ - Authentication (JWT / Session)   │  │
│  │ - Authorization (Roles / Access)   │  │
│  │ - SecurityContext creation         │  │
│  └────────────────────────────────────┘  │
│                                          │
│  ┌────────────────────────────────────┐  │
│  │ Custom / System Filters            │  │
│  │ - CORS                             │  │
│  │ - Logging                          │  │
│  │ - Headers                          │  │
│  └────────────────────────────────────┘  │
└─────────┬────────────────────────────────┘
          │
          │  ❌ If auth fails → 401 / 403
          │
          ▼
┌──────────────────────────────────────────┐
│  DispatcherServlet (Front Controller)    |
│  - Central traffic controller            │
│  - Coordinates MVC flow                  │
└─────────┬────────────────────────────────┘
          │
          ▼
┌────────────────────┐
│  Handler Mapping   │
│  (URL + HTTP verb) │
│  /api/students/1   │
└─────────┬──────────┘
          │
          ▼
┌────────────────────┐
│ Handler Interceptor│
│ - preHandle()      │
│ - postHandle()     │
│ - afterCompletion()│
└─────────┬──────────┘
          │
          ▼
┌────────────────────┐
│   Controller       │
│ @RestController    │
│ @GetMapping        │
│ Thin request logic │
└─────────┬──────────┘
          │
          ▼
┌────────────────────┐
│   Service Layer    │
│ Business logic     │
└─────────┬──────────┘
          │
          ▼
┌────────────────────┐
│ Repository / DAO   │
│ Database access    │
└─────────┬──────────┘
          │
          ▼
┌────────────────────┐
│ HttpMessageConverter
│ Object → JSON/XML  │
└─────────┬──────────┘
          │
          ▼
┌──────────────────────────────────────────┐
│     Response through Filters (Reverse)   │
│  - Security headers                      │
│  - CORS headers                          │
│  - Logging                               │
└─────────┬────────────────────────────────┘
          │
          ▼
┌────────────────────┐
│   HTTP Response    │
│ 200 / 401 / 403    │
│ JSON to Client     │
└────────────────────┘
```
## 🧠 One-Line Mental Model (for students)

```
Request
 → Server
 → Security
 → Dispatcher
 → Router
 → Controller
 → Service
 → DB
 → JSON
 → Response
```

## 🌱 Transflower Teaching Insight

> **If security fails, business logic never runs.**
> **If routing fails, controllers are invisible.**
> **If controllers are fat, architecture is broken.**

# 🌐 Spring Boot HTTP Request–Response Pipeline Explaination

### (with Security, Filters, Routing & Controllers)

## 1️⃣ Client Sends HTTP Request

A request starts from:

* Browser
* Mobile app
* Postman
* Frontend (React / Angular)

Example:

```
GET /api/students/101
Authorization: Bearer <JWT>
```

⬇️
## 2️⃣ Embedded Web Server (Tomcat / Jetty / Netty)

Spring Boot ships with an **embedded server**.

**Responsibility:**

* Accept HTTP connection
* Convert raw HTTP → Servlet Request
* Hand over to Spring framework

> At this point:
> ❌ No security
> ❌ No routing
> ❌ No controller yet

⬇️
## 3️⃣ Servlet Filter Chain (VERY IMPORTANT)

Before Spring MVC touches your request, it passes through **Filters**.

### 🔹 What is a Filter?

A **cross-cutting interceptor** that can:

* Inspect request/response
* Modify headers
* Stop the request
* Pass it forward

### 🔐 Spring Security Filters (Executed FIRST)

If Spring Security is enabled, it **injects its own filter chain**:

Typical order (simplified):

1. **SecurityContextPersistenceFilter**
2. **JWT / UsernamePasswordAuthenticationFilter**
3. **AuthorizationFilter**
4. **ExceptionTranslationFilter**

### What happens here?

* Extract token (JWT / session)
* Validate credentials
* Create `Authentication` object
* Store it in `SecurityContext`

If authentication fails:

```
➡️ 401 Unauthorized
➡️ Request never reaches controller
```

📌 **Security happens BEFORE routing.**
⬇️

## 4️⃣ DispatcherServlet (Front Controller)

Now the request enters **Spring MVC world**.

> DispatcherServlet = **Traffic Police of Spring**

**Responsibilities:**

* Decide *which controller method* should handle the request
* Coordinate the entire MVC flow

⬇️

## 5️⃣ Handler Mapping (Routing)

Spring checks:

* URL
* HTTP method (GET, POST, PUT, DELETE)
* Annotations

Example:

```java
@GetMapping("/api/students/{id}")
```

Spring finds:

```
StudentController.getStudentById()
```

📌 This is **routing**, not security.

⬇️

## 6️⃣ Handler Interceptors (Optional)

Similar to filters but **Spring-aware**.

Used for:

* Logging
* Metrics
* Audit
* Role-based checks

Runs:

* `preHandle()`
* `postHandle()`
* `afterCompletion()`

⬇️

## 7️⃣ Controller Method Execution

Now your **Controller** finally runs.

```java
@RestController
@RequestMapping("/api/students")
public class StudentController {

    @GetMapping("/{id}")
    public Student getStudent(@PathVariable int id) {
        return service.findById(id);
    }
}
```

Controller:

* Reads request data
* Calls Service layer
* Returns object / response

📌 Controllers should be **thin**.

⬇️

## 8️⃣ HttpMessageConverter (Serialization)

Spring converts:

```
Java Object → JSON / XML
```

Using:

* Jackson
* Gson

Based on:

```
Accept: application/json
```

⬇️


## 9️⃣ Response Goes Back Through Filters

Before leaving:

* Security filters may add headers
* CORS headers applied
* Logging filters execute

⬇️


## 🔟 HTTP Response Sent to Client

Final response:

```
HTTP/1.1 200 OK
Content-Type: application/json
```

🎉 Done.


# 🔁 Full Pipeline Summary (One Line)

```
Client
 → Web Server
 → Security Filters
 → DispatcherServlet
 → Handler Mapping
 → Controller
 → Message Converter
 → Filters
 → Response
```

# 🧠 Transflower Analogy (Human Body Model)

| Spring Component  | Human Body      |
| ----------------- | --------------- |
| Client            | External World  |
| Web Server        | Skin            |
| Security Filter   | Immune System   |
| DispatcherServlet | Brain           |
| Router            | Decision Center |
| Controller        | Muscle          |
| Service           | Nervous System  |
| Repository        | Memory          |
| Response          | Action          |

> No immune clearance → brain never processes the request.


# 🔐 Where Security Configuration Fits

```java
@Bean
SecurityFilterChain filterChain(HttpSecurity http) {
    http
      .csrf().disable()
      .authorizeHttpRequests(auth -> auth
          .requestMatchers("/auth/**").permitAll()
          .anyRequest().authenticated()
      )
      .addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class);
    return http.build();
}
```

This **defines rules**, not execution.
Execution happens automatically in the filter chain.


## 🎯 Key Teaching Takeaways

* **Filters ≠ Controllers**
* **Security runs before routing**
* **DispatcherServlet is the core**
* **Controllers should never do authentication**
* **Every request is a pipeline, not a method call**

## 🔐 Spring Boot HTTP Pipeline
Below is a **JWT-specific ASCII pipeline**, layered **on top of the normal Spring Boot flow**, so the *security story becomes visual*.
### **JWT-Specific Security Filter Flow**

```
┌────────────────────┐
│   Client / Frontend│
│ (Browser / Mobile) │
└─────────┬──────────┘
          │
          │  HTTP Request
          │  Authorization: Bearer <JWT>
          ▼
┌────────────────────┐
│ Embedded Web Server│
│ (Tomcat / Jetty)   │
└─────────┬──────────┘
          │
          ▼
┌──────────────────────────────────────────────────┐
│              Servlet Filter Chain                │
│                                                  │
│  ┌────────────────────────────────────────────┐  │
│  │ SecurityContextPersistenceFilter           │  │
│  │ - Creates empty SecurityContext            │  │
│  │ - Attaches it to current request           │  │
│  └────────────────────────────────────────────┘  │
│                         │
│                         ▼
│  ┌────────────────────────────────────────────┐  │
│  │ JWTAuthenticationFilter (Custom)           │  │
│  │ ------------------------------------------ │  │
│  │ 1. Read Authorization header               │  │
│  │ 2. Check "Bearer <token>"                  │  │
│  │ 3. Validate JWT (signature + expiry)       │  │
│  │ 4. Extract username & roles                │  │
│  │ 5. Create Authentication object            │  │
│  │ 6. Store in SecurityContext                │  │
│  └────────────────────────────────────────────┘  │
│                         │
│              ❌ Invalid / Missing JWT
│                         │
│                         ▼
│              → 401 Unauthorized
│                                                  │
│  ┌────────────────────────────────────────────┐  │
│  │ AuthorizationFilter                        │  │
│  │ - Checks roles & permissions               │  │
│  │ - @PreAuthorize / requestMatchers          │  │
│  └────────────────────────────────────────────┘  │
│                         │
│              ❌ Role not allowed
│                         │
│                         ▼
│              → 403 Forbidden
│                                                  │
│  ┌────────────────────────────────────────────┐  │
│  │ ExceptionTranslationFilter                 │  │
│  │ - Converts security errors to HTTP response│  │
│  └────────────────────────────────────────────┘  │
└─────────┬────────────────────────────────────────┘
          │
          │  ✅ Authenticated & Authorized
          ▼
┌────────────────────────────────────────────┐
│ DispatcherServlet (Front Controller)       │
└─────────┬──────────────────────────────────┘
          │
          ▼
┌────────────────────┐
│ Handler Mapping    │
│ URL → Controller   │
└─────────┬──────────┘
          │
          ▼
┌────────────────────┐
│ Controller Method  │
│ @GetMapping        │
│ Business use-case  │
└─────────┬──────────┘
          │
          ▼
┌────────────────────┐
│ HttpMessageConverter
│ Object → JSON      │
└─────────┬──────────┘
          │
          ▼
┌────────────────────┐
│ HTTP Response      │
│ 200 OK             │
└────────────────────┘
```

## 🔁 JWT Login vs Normal API Call

### 🔓 Login API (`/auth/login`)

```
Client
 → No JWT
 → UsernamePasswordAuthenticationFilter
 → AuthManager validates credentials
 → JWT generated
 → JWT returned to client
```

### 🔐 Protected API (`/api/**`)

```
Client
 → Sends JWT
 → JWTAuthenticationFilter
 → SecurityContext populated
 → Controller allowed
```


## 🧠 Key Teaching Hooks (Very Important)

### 🔑 Authentication vs Authorization

| Concept        | Meaning                     |
| -------------- | --------------------------- |
| Authentication | Who are you?                |
| Authorization  | What are you allowed to do? |

JWT filter handles **authentication**
AuthorizationFilter handles **authorization**



## 🌱 Transflower Analogy (Security Layer)

| Component       | Human Body       |
| --------------- | ---------------- |
| JWT Token       | ID Card          |
| JWT Filter      | Security Guard   |
| SecurityContext | Temporary Memory |
| Authorization   | Access Badge     |
| Controller      | Workplace        |

> No ID → no entry
> Wrong badge → restricted access



## ⚠️ Common Student Mistakes

* Doing JWT validation in **controller** ❌
* Skipping role checks ❌
* Confusing 401 vs 403 ❌
* Generating JWT inside filter ❌



## 🧩 Security Configuration Placement

```java
http
 .addFilterBefore(jwtFilter,
     UsernamePasswordAuthenticationFilter.class);
```

📌 This ensures JWT is validated **before Spring authentication logic**.
