# Java  Server Pages (JSP)

Students, you already know **Core Java**, **OOP**, **JDBC**, and recently we introduced **Servlets**. Now imagine this situation: your Servlet has successfully fetched products from MySQL. Great! But how do we show those products nicely in a browser?”. “We need a **View**.”. “That is where **JSP — JavaServer Pages** enters our journey.

## 1️⃣ From JDBC → Servlet → JSP

Let's connect what you have already learned.Earlier:

```text
Java Application
      │
      │ JDBC
      ▼
   MySQL
```

Then we moved to web applications:

```text
Browser
   │
   │ HTTP Request
   ▼
Tomcat
   │
   ▼
Servlet
   │
   │ JDBC
   ▼
MySQL
```

Now we add JSP:

```text
                         Java Web Application
                                 │
             ┌───────────────────┴───────────────────┐
             │                                       │
          Request                                  Response
             │                                       ▲
             ▼                                       │
           Browser                                   │
             │                                       │
             ▼                                       │
          Tomcat                                    JSP
             │                                       ▲
             ▼                                       │
        Servlet ──────── Service ──────── DAO ──────┘
                                      │
                                      │ JDBC
                                      ▼
                                    MySQL

> “Servlet is primarily interested in **controlling the request**. JSP is primarily interested in **presenting the result**.”



##  2️⃣ What Exactly Is JSP?

**JSP = JavaServer Pages**. A JSP is a server-side page used to generate dynamic web content. A simple JSP might look like:

```jsp
<html>
<body>
    <h1>Welcome to Transflower!</h1>
</body>
</html>
```

It looks almost like HTML. But JSP can also access server-side data:

```jsp
<h1>Welcome, ${user.name}!</h1>
```

So think of JSP as:

```text
HTML
 +
Dynamic server-side data
 =
JSP
```



## 3️⃣ The Important Question: Where Does JSP Execute?

> “Sir, does JSP execute inside Chrome?”

> **Mentor:**
> “No!”

This is extremely important.

```text
             SERVER
┌──────────────────────────────┐
│                              │
│   Tomcat                     │
│      │                       │
│      ▼                       │
│   JSP Processing             │
│      │                       │
│      ▼                       │
│   Generated HTML             │
│                              │
└──────────────┬───────────────┘
               │
               │ HTTP Response
               ▼
          🌐 Browser
```

The browser receives **HTML**, not JSP source code.



## 4️⃣ JSP Is Server-Side Technology

Suppose we have:

```text
welcome.jsp
```

and the browser requests:

```text
http://localhost:8080/shop/welcome.jsp
```

The journey is:

```text
Browser
   │
   │ GET /welcome.jsp
   ▼
Tomcat
   │
   ▼
JSP Engine
   │
   ▼
HTML Response
   │
   ▼
Browser
```

The browser finally sees:

```html
<h1>Welcome Ravi!</h1>
```

It doesn't see the JSP processing code.


## 5️⃣ The JSP Lifecycle — A Very Important Concept

Students often think:

```text
Browser → JSP → Browser
```

But internally, the container performs more work.

Conceptually:

```text
              welcome.jsp
                   │
                   ▼
          JSP translated into
             Servlet source
                   │
                   ▼
              Java compile
                   │
                   ▼
              .class file
                   │
                   ▼
          Servlet execution
                   │
                   ▼
              HTML output
                   │
                   ▼
                Browser
```

> 👨‍🏫 **Mentor:**
> “This is one of the most beautiful things to understand about JSP.”

A JSP is processed by the web container and ultimately executed through the Servlet mechanism.


## 6️⃣ JSP + Servlet = MVC Foundation

Now our architecture becomes clearer.

```text
             🌐 Browser
                  │
                  │ Request
                  ▼
             ┌─────────┐
             │ Servlet │
             │   C     │
             └────┬────┘
                  │
                  ▼
             Business Logic
                  │
                  ▼
                JDBC
                  │
                  ▼
               Database
                  │
                  │ Data
                  ▼
             ┌─────────┐
             │  JSP    │
             │   V     │
             └────┬────┘
                  │
                  │ HTML
                  ▼
             🌐 Browser
```

Where:

```text
C = Controller
V = View
M = Model
```

This becomes the foundation for understanding **MVC**.


## 7️⃣ Your First JSP

Create:

```text
src/main/webapp/hello.jsp
```

```jsp
<%@ page contentType="text/html;charset=UTF-8" %>

<!DOCTYPE html>
<html>

<head>
    <title>Transflower JSP</title>
</head>

<body>
    <h1>Welcome to Transflower!</h1>
    <p>We are learning Java Web Development.</p>
</body>

</html>
```

Deploy the application to Tomcat and request:

```text
http://localhost:8080/mywebapp/hello.jsp
```

The server generates an HTTP response containing HTML.

## 8️⃣ JSP Can Display Dynamic Data

Suppose the Servlet puts this into the request:

```java
request.setAttribute("message",
                     "Hello from Servlet!");
```

Then JSP can display it.

```jsp
<h1>${message}</h1>
```

The execution becomes:

```text
Servlet
   │
   │ setAttribute()
   ▼
Request
   │
   ▼
JSP
   │
   │ ${message}
   ▼
HTML
   │
   ▼
Browser
```

This is much cleaner than putting database logic inside JSP.


## 9️⃣ JSP Expression Language — EL

You will frequently see:

```jsp
${message}
```

This is called:

> **Expression Language (EL)**

For example:

```jsp
${product.name}
```

```jsp
${product.price}
```

```jsp
${student.email}
```

Instead of writing Java code directly into the page, we can access data using EL.


## 🔟 JSP Scriptlets — The Old Style

You may encounter this in older applications:

```jsp
<%
    String name = "Ravi";
%>

<h1>
    Welcome <%= name %>
</h1>
```

There are three important JSP constructs:

###### Scriptlet

```jsp
<% ... %>
```

Used historically to embed Java statements.

### Expression

```jsp
<%= ... %>
```

Used historically to output a value.

### Directive

```jsp
<%@ ... %>
```

Used for JSP configuration.


## ⚠️ Mentor Advice: Don't Put Business Logic Here

You **can** write:

```jsp
<%
    // Java code
%>
```

But should you put:

```java
Connection conn = ...
ResultSet rs = ...
SELECT * FROM products
```

inside JSP?

> **Mentor:**
> “No. Please don't turn your JSP into a kitchen where everything is cooked together!”

Avoid:

```text
JSP
 ├── HTML
 ├── JDBC
 ├── SQL
 ├── Business Rules
 ├── Authentication
 └── Everything else 
```

Instead:

```text
JSP
 ↓
Presentation

Servlet
 ↓
Request Control

Service
 ↓
Business Logic

DAO
 ↓
Database Access

JDBC
 ↓
Database
```

## 1️⃣1️⃣ JSTL — Cleaner JSP

This is where **JSTL** becomes useful.JSTL stands for: **JSP Standard Tag Library**

Instead of Java loops:

```jsp
<%
for(Product p : products) {
%>
    <p><%= p.getName() %></p>
<%
}
%>
```

we can use a tag-based approach:

```jsp
<c:forEach var="product" items="${products}">
    <p>${product.name}</p>
</c:forEach>
```

Much easier to read.

## 1️⃣2️⃣ Common JSTL Tags

### `<c:forEach>`

For iteration:

```jsp
<c:forEach var="product"
           items="${products}">

    <p>${product.name}</p>

</c:forEach>
```

### `<c:if>`

For conditions:

```jsp
<c:if test="${product.price > 1000}">

    <p>Premium Product</p>

</c:if>
```


### `<c:choose>`

For multiple conditions:

```jsp
<c:choose>

    <c:when test="${user.role == 'ADMIN'}">
        <p>Administrator</p>
    </c:when>

    <c:otherwise>
        <p>Regular User</p>
    </c:otherwise>

</c:choose>
```

### `<c:set>`

Create/set a variable:

```jsp
<c:set var="total"
       value="${product.price * product.quantity}" />
```

### `<c:out>`

Safely output a value:

```jsp
<c:out value="${product.name}" />
```

## 1️⃣3️⃣ Product Example

Let's connect this to the **Product CRUD application** you just learned. Our Servlet might retrieve:

```java
List<Product> products =
        productDAO.findAll();

request.setAttribute(
        "products",
        products);

request.getRequestDispatcher(
        "/products.jsp")
        .forward(request, response);
```

Now JSP displays them:

```jsp
<c:forEach var="product"
           items="${products}">

    <tr>

        <td>${product.id}</td>

        <td>${product.name}</td>

        <td>${product.price}</td>

        <td>${product.quantity}</td>

    </tr>

</c:forEach>
```

The complete flow:

```text
🌐 Browser
     │
     │ GET /products
     ▼
🐱 Tomcat
     │
     ▼
ProductServlet
     │
     ▼
ProductDAO
     │
     │ JDBC
     ▼
🗄️ MySQL
     │
     │ Products
     ▼
ProductServlet
     │
     │ request.setAttribute()
     ▼
products.jsp
     │
     │ HTML
     ▼
🌐 Browser
```

## 1️⃣4️⃣ JSP Project Structure

A traditional Maven web application can look like:

```text
ProductWebApp/
│
├── pom.xml
│
└── src/
    └── main/
        │
        ├── java/
        │   └── com/transflower/
        │       ├── model/
        │       │   └── Product.java
        │       │
        │       ├── dao/
        │       │   └── ProductDAO.java
        │       │
        │       └── controller/
        │           └── ProductServlet.java
        │
        └── webapp/
            │
            ├── products.jsp
            ├── product-form.jsp
            │
            └── WEB-INF/
                └── web.xml
```

Notice the separation:

```text
Java
 ↓
Application Logic

JSP
 ↓
Presentation

WEB-INF
 ↓
Web Application Configuration
```

## 1️⃣5️⃣ JSP Include

Suppose every page needs the same header. Instead of copying it everywhere:

```jsp
<%@ include file="header.jsp" %>
```

Similarly:

```jsp
<%@ include file="footer.jsp" %>
```

Conceptually:

```text
products.jsp
     │
     ├── header.jsp
     │
     ├── product content
     │
     └── footer.jsp
```

This encourages reuse.

   

## 1️⃣6️⃣ JSP Implicit Objects

JSP provides several predefined objects.

Common ones include:

```text
request
response
session
application
out
pageContext
```

For example:

```jsp
${sessionScope.user.name}
```

or traditionally:

```jsp
<%= request.getParameter("name") %>
```

The important idea is:

> **JSP already has access to the current web request and application context.**


## 1️⃣7️⃣ JSP Request → Response

Let's finish with the simplest mental model. Suppose the browser requests:

```text
/products
```

Tomcat receives it.

```text
Browser
   │
   ▼
Tomcat
   │
   ▼
Servlet
   │
   ▼
Service
   │
   ▼
DAO
   │
   ▼
Database
   │
   ▼
DAO
   │
   ▼
Service
   │
   ▼
Servlet
   │
   │ forward()
   ▼
JSP
   │
   │ HTML
   ▼
Tomcat
   │
   │ HTTP Response
   ▼
Browser
```


Students, don't learn JSP merely as a collection of tags.Understand **why JSP exists**. You started with:

```text
Core Java
     ↓
OOP
     ↓
JDBC
     ↓
Database
```

Then:

```text
Servlet
     ↓
HTTP Request / Response
     ↓
Tomcat
```

Now:

```text
Servlet
     ↓
JSP
     ↓
HTML
     ↓
Browser
```

And this naturally leads to:

```text
Servlet + JSP
      ↓
     MVC
      ↓
 Spring MVC
      ↓
 Spring Boot
      ↓
 REST API
      ↓
 Spring Security
      ↓
 Microservices
```

### Remember this simple formula

```text
Servlet   = Controller
JSP   = View
Java Object = Model
JDBC / DAO   = Data Access
```

> **“JSP is not the destination. JSP is one of the bridges that helps you understand how a Java web application transforms an HTTP request into a meaningful HTML response.”**

That understanding will make **Spring MVC** feel much less magical when you reach it.