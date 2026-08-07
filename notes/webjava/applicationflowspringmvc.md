 
# Spring MVC + JSP — Application Execution Flow

The execution flow as a **request journey**—from browser to Controller and back to the JSP response.
```text
                    SPRING MVC WEB APPLICATION
                    ===========================

        USER / BROWSER
              │
              │  HTTP Request
              │  GET /products
              ▼
     ┌──────────────────────┐
     │  FRONT CONTROLLER    │
     │ Finds matching       │
     │ Controller + Method  │
     └──────────┬───────────┘
                │
                │ 2. Invoke
                ▼
     ┌──────────────────────┐
     │      CONTROLLER      │
     │   ProductController  │
     │                      │
     │  /products           │
     └──────────┬───────────┘
                │
                │ 3. Call business logic
                ▼
     ┌──────────────────────┐
     │       SERVICE        │
     │    ProductService    │
     │                      │
     │  Business Logic      │
     └──────────┬───────────┘
                │
                │ 4. Access data
                ▼
     ┌──────────────────────┐
     │      REPOSITORY      │
     │    ProductRepository │
     └──────────┬───────────┘
                │
                │ 5. Query
                ▼
     ┌──────────────────────┐
     │       DATABASE       │
     │       MySQL          │
     └──────────┬───────────┘
                │
                │ 6. Data
                ▼
     ┌──────────────────────┐
     │      CONTROLLER      │
     │                      │
     │ Model / ModelAndView │
     │                      │
     │ view = "products"    │
     └──────────┬───────────┘
                │
                │ 7. Return View Name
                ▼
     ┌──────────────────────┐
     │    VIEW RESOLVER     │
     │                      │
     │ "products"           │
     │        ↓             │
     │ /WEB-INF/views/      │
     │ products.jsp         │
     └──────────┬───────────┘
                │
                │ 8. Render JSP
                ▼
     ┌──────────────────────┐
     │      products.jsp    │
     │                      │
     │ HTML + JSTL + EL     │
     └──────────┬───────────┘
                │
                │ 9. HTML Response
                ▼
        ┌─────────────────┐
        │     BROWSER     │
        │                 │
        │  Displays HTML  │
        └─────────────────┘
```

### The Complete Journey

```text
Browser
   │
   │ HTTP Request
   ▼
DispatcherServlet
   │
   ▼
HandlerMapping
   │
   ▼
Controller
   │
   ▼
Service
   │
   ▼
Repository
   │
   ▼
Database
   │
   │ Data
   ▼
Controller
   │
   │ View Name + Model
   ▼
ViewResolver
   │
   ▼
JSP
   │
   │ HTML
   ▼
Browser
```

### Mentor's Simple Story

> **"Think of DispatcherServlet as the receptionist of a company."**

The customer enters the company and says:

```text
"I want to see all products."
```

The receptionist doesn't handle the business operation herself. She finds the right person:

```text
Customer
   │
   ▼
Receptionist
   │
   ▼
ProductController
   │
   ▼
ProductService
   │
   ▼
ProductRepository
   │
   ▼
Database
```

The result comes back:

```text
Database
   │
   ▼
Repository
   │
   ▼
Service
   │
   ▼
Controller
   │
   ▼
"products"
   │
   ▼
ViewResolver
   │
   ▼
products.jsp
   │
   ▼
HTML
   │
   ▼
Browser
```

### Remember the Two Most Important Flows

**Request flow:**

```text
Browser
   ↓
DispatcherServlet
   ↓
Controller
   ↓
Service
   ↓
Repository
   ↓
Database
```

**Response flow:**

```text
Database
   ↓
Repository
   ↓
Service
   ↓
Controller
   ↓
ViewResolver
   ↓
JSP
   ↓
Browser
```

### Transflower Mentor Mantra

> **"DispatcherServlet receives the request, Controller decides what to do, Service performs business logic, Repository talks to the database, ViewResolver finds the JSP, and JSP generates the HTML that finally reaches the browser."**
