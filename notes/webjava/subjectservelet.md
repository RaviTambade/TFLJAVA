#  **Simple Servlet-based Product CRUD application**

> **Mentor Ravi:**
> “Students, you already know Java classes and JDBC. Now we are going to connect the two worlds: **HTTP + Servlet + JDBC + MySQL**.”

The request journey will be:

```text
🌐 Browser
     │
     │ HTTP Request
     ▼
🐱 Tomcat
     │
     ▼
☕ ProductServlet
     │
     ▼
⚙️ ProductDAO
     │
     │ JDBC
     ▼
🗄️ MySQL
```

## 1. Database Table

Let's start with a simple `products` table.

```sql
CREATE DATABASE transflower_store;

USE transflower_store;

CREATE TABLE products (
    id INT PRIMARY KEY AUTO_INCREMENT,
    name VARCHAR(100) NOT NULL,
    price DECIMAL(10,2) NOT NULL,
    quantity INT NOT NULL
);
```

---

## 2. Product Model

Create:

```text
src/main/java/com/transflower/model/Product.java
```

```java
package com.transflower.model;

public class Product {

    private int id;
    private String name;
    private double price;
    private int quantity;

    public Product() {
    }

    public Product(int id, String name, double price, int quantity) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.quantity = quantity;
    }

    public Product(String name, double price, int quantity) {
        this.name = name;
        this.price = price;
        this.quantity = quantity;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}
```

---

## 3. ProductDAO

Now our Servlet should not directly contain SQL.

> 👨‍🏫 **Mentor:**
> “Remember our separation of responsibilities. Servlet handles the **web request**. DAO handles the **database**.”

Create:

```text
src/main/java/com/transflower/dao/ProductDAO.java
```

```java
package com.transflower.dao;

import com.transflower.model.Product;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ProductDAO {

    private static final String URL =
            "jdbc:mysql://localhost:3306/transflower_store";

    private static final String USER = "root";
    private static final String PASSWORD = "TFL@678";

    // CREATE
    public void insert(Product product) throws SQLException {

        String sql =
                "INSERT INTO products(name, price, quantity) VALUES (?, ?, ?)";

        try (Connection conn =
                     DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement stmt =
                     conn.prepareStatement(sql)) {

            stmt.setString(1, product.getName());
            stmt.setDouble(2, product.getPrice());
            stmt.setInt(3, product.getQuantity());

            stmt.executeUpdate();
        }
    }

    // READ ALL
    public List<Product> findAll() throws SQLException {

        List<Product> products = new ArrayList<>();

        String sql = "SELECT * FROM products";

        try (Connection conn =
                     DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement stmt =
                     conn.prepareStatement(sql);
             ResultSet rs =
                     stmt.executeQuery()) {

            while (rs.next()) {

                Product product = new Product();

                product.setId(rs.getInt("id"));
                product.setName(rs.getString("name"));
                product.setPrice(rs.getDouble("price"));
                product.setQuantity(rs.getInt("quantity"));

                products.add(product);
            }
        }

        return products;
    }

    // READ ONE
    public Product findById(int id) throws SQLException {

        String sql =
                "SELECT * FROM products WHERE id = ?";

        try (Connection conn =
                     DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement stmt =
                     conn.prepareStatement(sql)) {

            stmt.setInt(1, id);

            try (ResultSet rs = stmt.executeQuery()) {

                if (rs.next()) {

                    Product product = new Product();

                    product.setId(rs.getInt("id"));
                    product.setName(rs.getString("name"));
                    product.setPrice(rs.getDouble("price"));
                    product.setQuantity(rs.getInt("quantity"));

                    return product;
                }
            }
        }

        return null;
    }

    // UPDATE
    public void update(Product product) throws SQLException {

        String sql =
                """
                UPDATE products
                SET name = ?, price = ?, quantity = ?
                WHERE id = ?
                """;

        try (Connection conn =
                     DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement stmt =
                     conn.prepareStatement(sql)) {

            stmt.setString(1, product.getName());
            stmt.setDouble(2, product.getPrice());
            stmt.setInt(3, product.getQuantity());
            stmt.setInt(4, product.getId());

            stmt.executeUpdate();
        }
    }

    // DELETE
    public void delete(int id) throws SQLException {

        String sql =
                "DELETE FROM products WHERE id = ?";

        try (Connection conn =
                     DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement stmt =
                     conn.prepareStatement(sql)) {

            stmt.setInt(1, id);

            stmt.executeUpdate();
        }
    }
}
```

> 💡 **Important:** `PreparedStatement` is preferred over building SQL strings with user input. It helps prevent SQL injection and handles parameters correctly.

## 4. ProductServlet

Now comes our main character.

```text
ProductServlet.java
```

```java
package com.transflower.controller;

import com.transflower.dao.ProductDAO;
import com.transflower.model.Product;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

@WebServlet("/products")
public class ProductServlet extends HttpServlet {

    private ProductDAO productDAO;

    @Override
    public void init() {
        productDAO = new ProductDAO();
    }

    // =========================
    // READ
    // =========================

    @Override
    protected void doGet(
            HttpServletRequest request,
            HttpServletResponse response)
            throws ServletException, IOException {

        String action = request.getParameter("action");

        try {

            if ("edit".equals(action)) {

                int id = Integer.parseInt(
                        request.getParameter("id"));

                Product product =
                        productDAO.findById(id);

                request.setAttribute(
                        "product", product);

                request.getRequestDispatcher(
                        "/product-form.jsp")
                        .forward(request, response);

            } else if ("delete".equals(action)) {

                int id = Integer.parseInt(
                        request.getParameter("id"));

                productDAO.delete(id);

                response.sendRedirect(
                        request.getContextPath()
                        + "/products");

            } else {

                List<Product> products =
                        productDAO.findAll();

                request.setAttribute(
                        "products", products);

                request.getRequestDispatcher(
                        "/products.jsp")
                        .forward(request, response);
            }

        } catch (SQLException e) {

            throw new ServletException(
                    "Unable to process product request", e);
        }
    }

    // =========================
    // CREATE / UPDATE
    // =========================

    @Override
    protected void doPost(
            HttpServletRequest request,
            HttpServletResponse response)
            throws ServletException, IOException {

        String action = request.getParameter("action");

        try {

            String name =
                    request.getParameter("name");

            double price =
                    Double.parseDouble(
                            request.getParameter("price"));

            int quantity =
                    Integer.parseInt(
                            request.getParameter("quantity"));

            Product product =
                    new Product(name, price, quantity);

            if ("update".equals(action)) {

                int id =
                        Integer.parseInt(
                                request.getParameter("id"));

                product.setId(id);

                productDAO.update(product);

            } else {

                productDAO.insert(product);
            }

            response.sendRedirect(
                    request.getContextPath()
                    + "/products");

        } catch (SQLException e) {

            throw new ServletException(
                    "Unable to save product", e);
        }
    }
}
```


##  5. How CRUD Maps to HTTP

Now look carefully.

| Operation      | HTTP | Servlet    |
| -------------- | ---- | ---------- |
| List products  | GET  | `doGet()`  |
| Get product    | GET  | `doGet()`  |
| Delete product | GET* | `doGet()`  |
| Create product | POST | `doPost()` |
| Update product | POST | `doPost()` |

`GET` for delete is acceptable for a simple learning exercise, but in a production application you would normally use **POST/DELETE** for state-changing operations.

# 6. Product List JSP

Create:

```text
src/main/webapp/products.jsp
```

```jsp
<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c"
           uri="jakarta.tags.core" %>

<!DOCTYPE html>
<html>

<head>
    <title>Product List</title>
</head>

<body>

<h1>Product Management</h1>

<a href="product-form.jsp">
    Add Product
</a>

<br><br>

<table border="1" cellpadding="10">

    <tr>
        <th>ID</th>
        <th>Name</th>
        <th>Price</th>
        <th>Quantity</th>
        <th>Actions</th>
    </tr>

    <c:forEach var="product"
               items="${products}">

        <tr>

            <td>${product.id}</td>

            <td>${product.name}</td>

            <td>${product.price}</td>

            <td>${product.quantity}</td>

            <td>

                <a href="products?action=edit&id=${product.id}">
                    Edit
                </a>

                |

                <a href="products?action=delete&id=${product.id}"
                   onclick="return confirm('Delete this product?');">
                    Delete
                </a>

            </td>

        </tr>

    </c:forEach>

</table>

</body>
</html>
```

---

## 7. Product Form JSP

Create:

```text
src/main/webapp/product-form.jsp
```

```jsp
<%@ page contentType="text/html;charset=UTF-8" %>

<!DOCTYPE html>
<html>

<head>
    <title>Product Form</title>
</head>

<body>

<h1>
    ${empty product ? "Add Product" : "Edit Product"}
</h1>

<form method="post" action="products">

    <input type="hidden"
           name="action"
           value="${empty product ? 'insert' : 'update'}">

    <input type="hidden"
           name="id"
           value="${product.id}">

    <label>Product Name:</label>

    <input type="text"
           name="name"
           value="${product.name}"
           required>

    <br><br>

    <label>Price:</label>

    <input type="number"
           step="0.01"
           name="price"
           value="${product.price}"
           required>

    <br><br>

    <label>Quantity:</label>

    <input type="number"
           name="quantity"
           value="${product.quantity}"
           required>

    <br><br>

    <button type="submit">
        Save Product
    </button>

</form>

<br>

<a href="products">
    Back to Products
</a>

</body>
</html>
```

##  8. Complete Project Structure

Your project now looks like:

```text
ProductWebApp/
│
├── pom.xml
│
└── src/
    └── main/
        │
        ├── java/
        │   └── com/
        │       └── transflower/
        │
        │           ├── model/
        │           │   └── Product.java
        │           │
        │           ├── dao/
        │           │   └── ProductDAO.java
        │           │
        │           └── controller/
        │               └── ProductServlet.java
        │
        └── webapp/
            │
            ├── products.jsp
            └── product-form.jsp
```


## 9. Complete CRUD Execution Flow

### CREATE

User fills the form:

```text
Browser
   │
   │ POST /products
   ▼
ProductServlet
   │
   ▼
ProductDAO
   │
   │ INSERT
   ▼
MySQL
```

### READ

User opens:

```text
/products
```

Flow:

```text
Browser
   │
   │ GET /products
   ▼
ProductServlet
   │
   ▼
ProductDAO
   │
   │ SELECT
   ▼
MySQL
   │
   ▼
ProductDAO
   │
   ▼
ProductServlet
   │
   ▼
products.jsp
   │
   ▼
Browser
```

### UPDATE

```text
Browser
   │
   │ GET /products?action=edit&id=5
   ▼
ProductServlet
   │
   ▼
ProductDAO
   │
   ▼
product-form.jsp
```

User changes the data:

```text
Browser
   │
   │ POST /products
   ▼
ProductServlet
   │
   ▼
ProductDAO
   │
   │ UPDATE
   ▼
MySQL
```

### DELETE

```text
Browser
   │
   │ /products?action=delete&id=5
   ▼
ProductServlet
   │
   ▼
ProductDAO
   │
   │ DELETE
   ▼
MySQL
   │
   ▼
Redirect
   │
   ▼
Product List
```

> “Students, this small Product application is actually teaching you a very important architecture.”

```text
                 🌐 BROWSER
                     │
                     │ HTTP
                     ▼
              ┌──────────────┐
              │    TOMCAT    │
              │              │
              │  Servlet     │
              │  Container   │
              └──────┬───────┘
                     │
                     ▼
             ProductServlet
                Controller
                     │
                     ▼
              ProductDAO
               Data Access
                     │
                     │ JDBC
                     ▼
                🗄️ MySQL
```

And the responsibilities are separated:

```text
Product
   ↓
Data / Model

ProductServlet
   ↓
Web Request / Response

ProductDAO
   ↓
Database Access

MySQL
   ↓
Persistent Data

JSP
   ↓
Presentation
```

### The learning progression

```text
Core Java
    ↓
OOP
    ↓
JDBC
    ↓
Servlet
    ↓
JSP
    ↓
MVC
    ↓
CRUD Web Application
    ↓
Spring MVC
    ↓
Spring Boot
    ↓
REST API
    ↓
Spring Data JPA
```

> **“Don't memorize `ProductServlet`. Understand the journey of the request. Once you understand the journey, the framework becomes much easier to learn.”**