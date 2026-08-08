# Spring MVC Application Execution Flow


> “Students, don't start by memorizing `@Controller`, `@GetMapping`, `Model`, and `ViewResolver`.First understand one thing: **What happens when a user types a URL in the browser?** Once you understand that journey, Spring MVC becomes very easy.”



## 🎬 Let's Take a Real Example

Suppose we have a Product application. The user enters:

```text
http://localhost:8080/products
```

The browser sends:

```http
GET /products
```

Now the real journey begins.



# 🛣️ Complete Spring MVC Execution Flow

```text
                    USER
                     |
                     | 1. GET /products
                     v
              ┌──────────────┐
              │   Browser    │
              └──────┬───────┘
                     |
                     v
        ┌─────────────────────────┐
        │      Web Server         │
        │   Tomcat / Servlet      │
        │       Container         │
        └────────────┬────────────┘
                     |
                     v
        ┌─────────────────────────┐
        │    DispatcherServlet    │
        │    Front Controller     │
        └────────────┬────────────┘
                     |
                     | 2. Find Controller
                     v
        ┌─────────────────────────┐
        │   ProductController     │
        │                         │
        │ @GetMapping("/products")│
        └────────────┬────────────┘
                     |
                     | 3. Call business logic
                     v
        ┌─────────────────────────┐
        │    ProductService       │
        └────────────┬────────────┘
                     |
                     | 4. Data request
                     v
        ┌─────────────────────────┐
        │      ProductDAO         │
        │    / Repository         │
        └────────────┬────────────┘
                     |
                     | 5. JDBC / JPA
                     v
              ┌──────────────┐
              │   Database   │
              │    MySQL     │
              └──────┬───────┘
                     |
                     | 6. Product data
                     v
        ┌─────────────────────────┐
        │    ProductService       │
        └────────────┬────────────┘
                     |
                     v
        ┌─────────────────────────┐
        │   ProductController     │
        │                         │
        │ model.addAttribute(...) │
        └────────────┬────────────┘
                     |
                     | 7. Return View Name
                     |    "products"
                     v
        ┌─────────────────────────┐
        │      ViewResolver       │
        └────────────┬────────────┘
                     |
                     | 8. Locate JSP
                     v
        ┌─────────────────────────┐
        │      products.jsp       │
        └────────────┬────────────┘
                     |
                     | 9. Generate HTML
                     v
        ┌─────────────────────────┐
        │    DispatcherServlet    │
        └────────────┬────────────┘
                     |
                     | 10. HTTP Response
                     v
              ┌──────────────┐
              │   Browser    │
              └──────────────┘
```


# 🧩 Step 1 — User Sends a Request

The user enters:

```text
/products
```

The browser generates an HTTP request:

```http
GET /products HTTP/1.1
Host: localhost:8080
```

The request travels to the Java web application.



# Step 2 — Tomcat Receives the Request

Tomcat is the runtime environment for the traditional Spring MVC web application. Think of Tomcat as the **building** in which our web application lives.

```text
Internet
   |
   v
Tomcat
   |
   v
Spring MVC Application
```

Tomcat receives the HTTP request and passes it into the application's web layer.

# 🚦 Step 3 — DispatcherServlet Takes Charge

Now comes the most important character:

## `DispatcherServlet`

Think of it as the **receptionist** of your application. It receives incoming requests and asks:

> “Who should handle `/products`?”

It does not normally contain your product business logic. Its job is to **coordinate the request processing**.

```text
                 DispatcherServlet
                        |
          "Who handles /products?"
                        |
                        v
                 ProductController
```

# 🔍 Step 4 — Find the Correct Controller

Suppose we have:

```java
@Controller
public class ProductController {

    @GetMapping("/products")
    public String listProducts(Model model) {

        // ...

        return "products";
    }
}
```

Spring knows that:

```text
GET /products
```

should be handled by:

```text
ProductController.listProducts()
```

This mapping is one of the key things Spring MVC manages for us.


# ⚙️ Step 5 — Controller Calls the Service

The controller should not become a giant class containing all business logic.

Instead:

```text
Controller
     |
     v
Service
     |
     v
Repository / DAO
```

For example:

```java
@GetMapping("/products")
public String listProducts(Model model) {

    List<Product> products =
            productService.getAllProducts();

    model.addAttribute("products", products);

    return "products";
}
```

The controller's responsibility is primarily:

> **Receive → Delegate → Prepare Model → Select View**

# 🧠 Step 6 — Service Performs Business Logic

The service represents the business layer.

```java
public List<Product> getAllProducts() {

    // business rules

    return productRepository.findAll();
}
```

For example, business rules could include:

```text
Check product status
Calculate discount
Validate product
Apply business rules
```

The service then asks the repository/DAO for data.

# 🗄️ Step 7 — Repository/DAO Talks to Database

The repository or DAO handles data access.

Conceptually:

```text
ProductService
      |
      v
ProductRepository
      |
      v
JDBC / JPA
      |
      v
MySQL
```

The database might return:

```text
101   Laptop       75000
102   Keyboard      2500
103   Mouse          800
```

That data travels back upward.


# 🔄 Step 8 — Data Comes Back to Controller

The journey reverses:

```text
MySQL
  ↓
Repository
  ↓
Service
  ↓
Controller
```

The controller now has the products.

```java
List<Product> products =
        productService.getAllProducts();
```

Now the controller puts the data into the **Model**.

```java
model.addAttribute("products", products);
```

Think of `Model` as a **data bag** travelling from the controller to the view.

```text
Model
 └── products
      ├── Laptop
      ├── Keyboard
      └── Mouse
```

# 🎨 Step 9 — Controller Returns a View Name

The controller returns:

```java
return "products";
```

Notice something interesting.

It doesn't return:

```text
products.jsp
```

It returns a **logical view name**:

```text
products
```

Spring's view resolution mechanism determines which actual view should render that name.


# 🔎 Step 10 — ViewResolver Finds the JSP

For a JSP-based Spring MVC application, the view resolver can translate:

```text
products
```

into something such as:

```text
/WEB-INF/views/products.jsp
```

Conceptually:

```text
"products"
    |
    v
ViewResolver
    |
    v
/WEB-INF/views/products.jsp
```

# 🖥️ Step 11 — JSP Generates HTML

The JSP receives the model data.

For example:

```jsp
<c:forEach var="product" items="${products}">
    <tr>
        <td>${product.id}</td>
        <td>${product.name}</td>
        <td>${product.price}</td>
    </tr>
</c:forEach>
```

The JSP generates HTML.

The browser **does not receive JSP**.

It receives the generated HTML.

```text
JSP
 |
 | generates
 v
HTML
```

# 📤 Step 12 — HTML Goes Back to Browser

Finally:

```text
products.jsp
      |
      | HTML response
      v
DispatcherServlet
      |
      v
Tomcat
      |
      v
Browser
```

The user sees:

```text
--------------------------------
        Product List
--------------------------------
ID       Product       Price
101      Laptop        75000
102      Keyboard       2500
103      Mouse            800
--------------------------------
```

🎉 The request-response journey is complete.

# 🧠 The Golden Flow

Students, remember this one line:

```text
Browser
   ↓
Tomcat
   ↓
DispatcherServlet
   ↓
Controller
   ↓
Service
   ↓
Repository / DAO
   ↓
Database
   ↑
Repository
   ↑
Service
   ↑
Controller
   ↓
Model + View Name
   ↓
ViewResolver
   ↓
JSP
   ↓
HTML
   ↓
Browser
```

# 🍕 A Simple Pizza Analogy

Imagine ordering pizza.

```text
You
 |
 | "I want Pizza"
 v
Receptionist
 |
 | "Who handles this?"
 v
Kitchen Manager
 |
 v
Chef
 |
 v
Ingredients
```

In Spring MVC:

| Pizza World       | Spring MVC            |
| ----------------- | --------------------- |
| Customer          | Browser               |
| Order             | HTTP Request          |
| Receptionist      | DispatcherServlet     |
| Kitchen Manager   | Controller            |
| Chef              | Service               |
| Ingredients/Store | Repository + Database |
| Prepared Pizza    | Model/Data            |
| Presentation      | JSP                   |
| Customer          | Browser               |

So:

> **DispatcherServlet is like the receptionist.**

It doesn't cook the pizza. It makes sure the order reaches the correct person.

# 🎯 Why Is This Architecture Powerful?

Because responsibilities are separated.

```text
Controller  → Request handling
Service     → Business logic
Repository  → Data access
Database    → Data storage
JSP         → Presentation
```

If tomorrow we replace:

```text
JSP
```

with:

```text
Thymeleaf
```

or another presentation technology, the business logic doesn't necessarily need to change. Similarly, if JDBC is replaced with JPA, the higher-level business logic can remain largely independent of that change. That's the real value of MVC:

> **Separation of concerns.**


# Transflower Mentor's Final Lesson

> “Students, don't learn Spring MVC by memorizing 50 annotations. Learn it by following **one HTTP request**.”

When you can mentally trace:

```text
GET /products
      ↓
DispatcherServlet
      ↓
ProductController
      ↓
ProductService
      ↓
ProductRepository
      ↓
MySQL
      ↓
Product data
      ↓
Model
      ↓
ViewResolver
      ↓
products.jsp
      ↓
HTML
      ↓
Browser
```

you have understood the **heart of Spring MVC execution**.  And then Spring Boot becomes much less mysterious—because underneath the conveniences, you already understand the web application journey.