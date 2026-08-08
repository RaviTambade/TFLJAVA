
# Spring MVC

> “You already know JDBC. You learned how Java talks to a database.You learned Servlets. You learned how a browser request reaches Java code. You learned JSP. You learned how Java can generate dynamic HTML. Now comes the next question: **How do we organize all these pieces when the application becomes big?** And that is where **Spring MVC** enters the story."


## Scene 1 — Our Servlet Application Starts Growing

Imagine we are building a simple **Product Management System**. Initially, life is simple.

```text
Browser
   |
   | GET /products
   v
ProductServlet
   |
   | JDBC
   v
MySQL
```

The servlet receives the request, talks to JDBC, gets products, and forwards the data to JSP.

```text
Browser
   |
   v
ProductServlet
   |
   +---- JDBC ----> MySQL
   |
   v
products.jsp
   |
   v
HTML Response
```

Perfect! 👍 But then the business grows.

Suddenly we need:

* Product management
* Customer management
* Order management
* Payment
* Authentication
* Reports
* Admin dashboard
* Shopping cart

Now imagine putting everything inside one servlet.

```text
ProductServlet
 ├── Login
 ├── Product CRUD
 ├── Customer CRUD
 ├── Order processing
 ├── Payment
 ├── JDBC
 ├── Validation
 ├── HTML navigation
 └── Error handling
```


> “Students, the problem is not that Servlets are bad.
> The problem is that **we are putting too many responsibilities in one place**.”

This is where architecture becomes important.

# Scene 2 — From Chaos to a School

Think about a school. A school doesn't ask one person to do everything.

```text
                    SCHOOL
                       |
        +--------------+--------------+
        |              |              |
    Principal       Teachers       Office
        |              |              |
    Management      Teaching       Administration
```

Each person has a responsibility. Similarly, our application should have responsibilities separated. That idea leads us toward:

# MVC

**M → Model**

**V → View**

**C → Controller**


# Scene 3 — What is MVC?

Let's take our Product application.

```text
              ┌───────────────────┐
              │      Browser      │
              │   User Interface  │
              └─────────┬─────────┘
                        │
                     Request
                        │
                        ▼
              ┌───────────────────┐
              │    Controller     │
              │  ProductController│
              └─────────┬─────────┘
                        │
                        ▼
              ┌───────────────────┐
              │       Model       │
              │ Business + Data   │
              └─────────┬─────────┘
                        │
                       JDBC
                        │
                        ▼
                  ┌───────────┐
                  │  MySQL DB │
                  └───────────┘
                        │
                        ▼
              ┌───────────────────┐
              │       View        │
              │ products.jsp      │
              └─────────┬─────────┘
                        │
                        ▼
                     Browser
```

Now the responsibilities are clearer.

### Model

Represents:

```text
Product
Customer
Order
Payment
```

and the business/data operations around them.

### View

Represents what the user sees.

Examples:

```text
products.jsp
product-details.jsp
login.jsp
dashboard.jsp
```

### Controller

Receives the request and decides:

> “Which operation should happen?”

and

> “Which view should be displayed?”

# Scene 4 — So What Exactly Is Spring MVC?

Now we add the Spring Framework. Spring MVC is a web framework built around the **Model-View-Controller pattern**. Instead of manually managing everything with Servlets, request dispatching, object creation, and configuration, Spring MVC provides a structured framework for handling web requests. The most important character in this story is:

# DispatcherServlet

Think of `DispatcherServlet` as the **receptionist of your application**.

A customer walks into a company and says:

> “I want to see the products.”

The receptionist doesn't personally fetch products from the database. Instead, the receptionist asks:

> “Which department should handle this request?”

That's exactly what `DispatcherServlet` does.

  

# Scene 5 — DispatcherServlet

Suppose the browser sends:

```http
GET /products
```

The request first reaches:

```text
             Browser
                |
                | GET /products
                v
       ┌──────────────────┐
       │DispatcherServlet │
       └────────┬─────────┘
                |
                | Find appropriate controller
                v
       ┌──────────────────┐
       │ProductController │
       └────────┬─────────┘
                |
                v
          ProductService
                |
                v
           ProductDAO
                |
                v
              JDBC
                |
                v
             MySQL
```

The result comes back:

```text
MySQL
  |
  v
ProductDAO
  |
  v
ProductService
  |
  v
ProductController
  |
  v
products.jsp
  |
  v
Browser
```

This is the basic Spring MVC journey.

# Scene 6 — Meet the Controller

In traditional Servlet programming, we might write:

```java
@WebServlet("/products")
public class ProductServlet extends HttpServlet {
    
    protected void doGet(
        HttpServletRequest request,
        HttpServletResponse response) {

        // logic
    }
}
```

Spring MVC gives us a much cleaner programming model.

```java
@Controller
public class ProductController {

    @GetMapping("/products")
    public String getProducts(Model model) {

        // get products

        return "products";
    }
}
```

Look at the difference.

The annotation:

```java
@Controller
```

tells Spring:

> “This class is responsible for handling web requests.”

And:

```java
@GetMapping("/products")
```

means:

> “When somebody sends GET `/products`, invoke this method.”


# Scene 7 — The Controller Should Not Become a Superhero

Here is an important mentor lesson. Don't write this:

```text
Controller
   |
   +-- validation
   +-- business logic
   +-- SQL
   +-- database connection
   +-- calculations
   +-- HTML generation
```

That's the same old problem coming back. Instead:

```text
Controller
    |
    v
Service
    |
    v
Repository / DAO
    |
    v
Database
```

For example:

```java
@Controller
public class ProductController {

    private ProductService service;

    @GetMapping("/products")
    public String list(Model model) {

        List<Product> products =
                service.getAllProducts();

        model.addAttribute("products", products);

        return "products";
    }
}
```

Now the controller's job is simple:

> **Receive → Delegate → Prepare Model → Select View**


# Scene 8 — Model Meets JDBC

You already learned JDBC. So don't throw away what you learned. Spring MVC doesn't replace the knowledge of JDBC. Instead, we can organize JDBC behind another layer.

```text
ProductController
        |
        v
ProductService
        |
        v
ProductDAO
        |
        v
      JDBC
        |
        v
     MySQL
```

Your JDBC knowledge becomes the foundation underneath the Spring application. This is an important learning principle:

> **Don't forget your fundamentals when learning a framework.**

Frameworks are built **on top of fundamentals**.



# Scene 9 — JSP Becomes the View

You already know JSP. Spring MVC can use JSP as a view technology.

For example:

```text
src
 └── main
      ├── java
      │    └── com.transflower
      │         ├── controller
      │         ├── service
      │         ├── dao
      │         └── model
      │
      └── webapp
           └── WEB-INF
                └── views
                     ├── products.jsp
                     ├── product-details.jsp
                     └── product-form.jsp
```

Controller:

```java
@GetMapping("/products")
public String list(Model model) {

    List<Product> products =
            service.getAllProducts();

    model.addAttribute("products", products);

    return "products";
}
```

Spring MVC eventually resolves:

```text
"products"
     |
     v
products.jsp
```

The JSP generates HTML. The browser receives HTML—not JSP and not Java code.

 

# Complete Spring MVC Execution Flow

Now let's put everything together. Suppose the user enters:

```text
http://localhost:8080/products
```

The journey looks like this:

```text
                USER
                 |
                 | GET /products
                 v
        ┌─────────────────┐
        │     Browser     │
        └────────┬────────┘
                 |
                 v
        ┌─────────────────┐
        │DispatcherServlet│
        │Front Controller │
        └────────┬────────┘
                 |
                 v
        ┌─────────────────┐
        │ProductController│
        └────────┬────────┘
                 |
                 v
        ┌─────────────────┐
        │ ProductService  │
        │ Business Logic  │
        └────────┬────────┘
                 |
                 v
        ┌─────────────────┐
        │   ProductDAO    │
        │ Data Access     │
        └────────┬────────┘
                 |
                 v
        ┌─────────────────┐
        │      JDBC       │
        └────────┬────────┘
                 |
                 v
        ┌─────────────────┐
        │     MySQL       │
        └────────┬────────┘
                 |
                 | Product data
                 v
        ┌─────────────────┐
        │      Model      │
        └────────┬────────┘
                 |
                 v
        ┌─────────────────┐
        │   JSP View      │
        │  products.jsp   │
        └────────┬────────┘
                 |
                 | HTML
                 v
        ┌─────────────────┐
        │     Browser     │
        └─────────────────┘
```

# Servlet vs Spring MVC

Now students can see the evolution.

### Traditional Servlet Application

```text
Browser
   |
   v
Servlet
   |
   +---- Business Logic
   |
   +---- JDBC
   |
   +---- Request handling
   |
   v
JSP
```

### Spring MVC Application

```text
Browser
   |
   v
DispatcherServlet
   |
   v
Controller
   |
   v
Service
   |
   v
DAO / Repository
   |
   v
JDBC / JPA
   |
   v
Database
```

The second architecture gives us **clearer separation of responsibilities**.


# Typical Spring MVC Application

A simple project might look like:

```text
SpringMVCApp/
│
├── src/main/java/
│   │
│   └── com/transflower/
│       │
│       ├── controller/
│       │    └── ProductController.java
│       │
│       ├── service/
│       │    └── ProductService.java
│       │
│       ├── dao/
│       │    └── ProductDAO.java
│       │
│       └── model/
│            └── Product.java
│
├── src/main/webapp/
│   │
│   └── WEB-INF/
│       └── views/
│            ├── products.jsp
│            ├── details.jsp
│            └── form.jsp
│
└── pom.xml
```

Each folder has a responsibility.


# Mentor's Blackboard

I would summarize today's lesson on the board like this:

```text
Servlet
   ↓
Servlet + JSP
   ↓
MVC Pattern
   ↓
Spring MVC
   ↓
Controller
   ↓
Service
   ↓
DAO / Repository
   ↓
JDBC / JPA
   ↓
Database
```

And remember this:

> **Servlet taught you how a Java web request works.**
> **JSP taught you how the server can generate a dynamic view.**
> **JDBC taught you how Java communicates with a database.**
> **Spring MVC teaches you how to organize all these pieces into a maintainable web application.**

## The Transflower Learning Path

Don't jump directly into `@RestController` and Spring Boot just because everyone is talking about REST APIs.

Build the foundation first:

```text
Java
  ↓
OOP
  ↓
Collections
  ↓
JDBC
  ↓
Servlet
  ↓
JSP
  ↓
MVC
  ↓
Spring MVC
  ↓
Spring Core
  ↓
Spring Boot
  ↓
Spring Data JPA
  ↓
Spring Security
  ↓
REST APIs
  ↓
Microservices
```

👨‍🏫 **Final Mentor Message:**

> “Students, Spring MVC is not magic. If you understand HTTP, Servlet, JSP, JDBC, OOP, and MVC, Spring MVC becomes much easier. Don't memorize annotations. **Understand the journey of a request.**  
Once you can explain what happens from `Browser → DispatcherServlet → Controller → Service → DAO → Database → JSP → Browser`.  You have started thinking like a Java web developer—not just someone who knows Spring syntax.”