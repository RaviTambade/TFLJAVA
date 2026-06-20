# ** Clean Architecture — “The Day Your Project Grew Up”**

While helping a student team build a **mini Amazon** for their semester project.
At first, the code is cute — small, innocent, and obedient. A few products, a form, a simple JDBC call.

Then one day…
Your project **hits puberty**.

Suddenly, it becomes messy. Emotional. Unpredictable.

You open your code file, and it looks like this:

```java
Connection conn = DriverManager.getConnection(...);
Statement stmt = conn.createStatement();
ResultSet rs = stmt.executeQuery("SELECT * FROM products");
```

And you whisper to yourself:

> “Beta, what have you become?” 🤦‍♂️

Your **UI code** is talking directly to the **database**.
Your **business logic** is mixed with **SQL statements**.
Your servlet is crying because it now knows too much.

# 💣 The Coming Explosion

Weeks pass.

Your faculty says:
“Can you fetch products by category? By price? Filter? Sort?”

Your friend says:
“Let’s move from MySQL to PostgreSQL — it's better.”

Your tester says:
“Write unit tests.”

Your project replies:

> “Touch me and I will break.” 😭

This is the moment real developers feel the pain of **tight coupling**.

Your logic, your UI, your SQL — *all tied together like tangled earphones*.

# 🎯 Mentor Insight:

## **“Real projects survive only when each part minds its own business.”**

And that's when I look at you, smile, and say:

## 🧭 **Enter the Repository Pattern — your code’s peacekeeper.**


# 🍱 A Simple Analogy

## **The Shop Counter Story**

When you walk into a shop and say:

> “Bhaiya, ek Product ID 101 dena.”

You don’t care:

* Where he stored it
* Whether it’s in a warehouse
* Whether a robot fetched it
* Whether it came from Amazon backend

You just interact with the **counter**.

That counter = **Repository Interface**

Where the items are stored = **Database (JDBC, Mongo, File, API, anything)**

Your business logic = **The customer**

# 🛠️ Now Let's Build It in Java Step-by-Step

## **Step 1: Create the Contract (Clean Code Rule: “Program to an Interface”)**

```java
public interface ProductRepository {
    Product findById(int id);
    List<Product> findAll();
    void save(Product product);
    void delete(int id);
}
```

Notice:
- ❌ No JDBC
- ❌ No SQL
- ❌ No MySQL

Just **pure rules**.
This is the "counter".
What happens behind it is NOT your headache.

## **Step 2: JDBC Implementation (Behind the Counter)**

```java
public class JdbcProductRepository implements ProductRepository {

    private Connection conn;

    public JdbcProductRepository(Connection conn) {
        this.conn = conn;
    }

    @Override
    public Product findById(int id) {
        // JDBC logic here
    }

    @Override
    public List<Product> findAll() {
        // JDBC logic here
    }

    @Override
    public void save(Product product) {
        // JDBC insert logic
    }

    @Override
    public void delete(int id) {
        // JDBC delete logic
    }
}
```

This is your **warehouse**.
Business logic should NEVER enter here.

## **Step 3: ProductService — Business Logic Becomes Clean & Lean**

```java
public class ProductService {
    private ProductRepository productRepo;

    public ProductService(ProductRepository repo) {
        this.productRepo = repo;
    }

    public void displayAllProducts() {
        List<Product> products = productRepo.findAll();
        products.forEach(System.out::println);
    }
}
```

Here comes the magic:

* You can pass any implementation.
* Your business logic doesn't know OR care about JDBC.
* Your system becomes flexible — like a pro.

# 🧪 Want to Test Without DB?

Create a mock repository:

```java
ProductRepository mock = new InMemoryProductRepository();
ProductService service = new ProductService(mock);

service.displayAllProducts();
```

No database.
No JDBC.
100% testable.
100% clean code.

# 🌐 Works Beautifully with Servlets Too

```java
@WebServlet("/products")
public class ProductServlet extends HttpServlet {

    private ProductRepository productRepo;

    @Override
    public void init() {
        Connection conn = DBUtil.getConnection();
        productRepo = new JdbcProductRepository(conn);
    }

    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        List<Product> products = productRepo.findAll();
        req.setAttribute("products", products);
        req.getRequestDispatcher("products.jsp").forward(req, resp);
    }
}
```

Your servlet now looks fresh, clean, and focused.

# 🧼 Clean Code Strategy Learned

| Principle                 | How Repository Pattern Helps                       |
| ------------------------- | -------------------------------------------------- |
| **Single Responsibility** | JDBC & Business Logic separated                    |
| **Open-Close Principle**  | Add new DB implementations without modifying logic |
| **Dependency Inversion**  | High-level code depends on interfaces              |
| **Testability**           | In-memory repo for fast tests                      |
| **Scalability**           | Plug & play data sources                           |


# 🧑‍🎓 Mentee’s Realization

After understanding this, your student looks up and says:

> “Sir, this is exactly how enterprise code stays clean even when the system grows huge!”

And I smile, because that’s exactly what you needed to learn today.

Absolutely, **let’s build the full practical version step-by-step** — just like how I would guide a mentee in a real classroom.

Today, we will not only *explain* the Repository Pattern…
We will **implement a complete mini-project** with:

# 🧱 **Mini Project Setup**

A clean, layered architecture:

```
com.shop
 ├── model
 │     └── Product.java
 ├── repository
 │     ├── ProductRepository.java
 │     ├── JdbcProductRepository.java
 │     └── InMemoryProductRepository.java
 ├── service
 │     └── ProductService.java
 ├── web
 │     └── ProductServlet.java
 └── util
       └── DBUtil.java
```

This is **enterprise-style structure** — but simplified for learning.

# 🌟 **STEP 1 — Product Model (POJO)**

*Your hero object*

```java
package com.shop.model;

public class Product {
    private int id;
    private String name;
    private double price;

    public Product() {}

    public Product(int id, String name, double price) {
        this.id = id;
        this.name = name;
        this.price = price;
    }

    // Getters + Setters
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public double getPrice() { return price; }
    public void setPrice(double price) { this.price = price; }

    @Override
    public String toString() {
        return id + " | " + name + " | ₹" + price;
    }
}
```

# 🌟 **STEP 2 — Repository Interface (Clean Contract)**

*Your “shop counter”*

```java
package com.shop.repository;

import com.shop.model.Product;
import java.util.List;

public interface ProductRepository {
    Product findById(int id);
    List<Product> findAll();
    void save(Product product);
    void delete(int id);
}
```

# 🌟 **STEP 3 — JDBC Implementation**

*Your “warehouse” — database-specific code*

```java
package com.shop.repository;

import com.shop.model.Product;
import java.sql.*;
import java.util.*;

public class JdbcProductRepository implements ProductRepository {

    private Connection conn;

    public JdbcProductRepository(Connection conn) {
        this.conn = conn;
    }

    @Override
    public Product findById(int id) {
        try {
            String sql = "SELECT * FROM products WHERE id = ?";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, id);

            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                return new Product(
                    rs.getInt("id"),
                    rs.getString("name"),
                    rs.getDouble("price")
                );
            }
        } catch (Exception e) { e.printStackTrace(); }

        return null;
    }

    @Override
    public List<Product> findAll() {
        List<Product> list = new ArrayList<>();

        try {
            String sql = "SELECT * FROM products";
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery(sql);

            while (rs.next()) {
                list.add(new Product(
                    rs.getInt("id"),
                    rs.getString("name"),
                    rs.getDouble("price")
                ));
            }
        } catch (Exception e) { e.printStackTrace(); }

        return list;
    }

    @Override
    public void save(Product product) {
        try {
            String sql = "INSERT INTO products(id, name, price) VALUES (?, ?, ?)";
            PreparedStatement ps = conn.prepareStatement(sql);

            ps.setInt(1, product.getId());
            ps.setString(2, product.getName());
            ps.setDouble(3, product.getPrice());

            ps.executeUpdate();
        } catch (Exception e) { e.printStackTrace(); }
    }

    @Override
    public void delete(int id) {
        try {
            String sql = "DELETE FROM products WHERE id = ?";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, id);

            ps.executeUpdate();
        } catch (Exception e) { e.printStackTrace(); }
    }
}
```

# 🌟 **STEP 4 — In-Memory Repository (Testing)**

*Zero database, perfect for unit tests*

```java
package com.shop.repository;

import com.shop.model.Product;
import java.util.*;

public class InMemoryProductRepository implements ProductRepository {

    private Map<Integer, Product> store = new HashMap<>();

    @Override
    public Product findById(int id) {
        return store.get(id);
    }

    @Override
    public List<Product> findAll() {
        return new ArrayList<>(store.values());
    }

    @Override
    public void save(Product product) {
        store.put(product.getId(), product);
    }

    @Override
    public void delete(int id) {
        store.remove(id);
    }
}
```

# 🌟 **STEP 5 — DBUtil for JDBC Connection**

```java
package com.shop.util;

import java.sql.*;

public class DBUtil {

    public static Connection getConnection() {

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");

            return DriverManager.getConnection(
                "jdbc:mysql://localhost:3306/shopdb",
                "root",
                "password"
            );

        } catch (Exception e) {
            throw new RuntimeException("DB Connection failed!", e);
        }
    }
}
```

# 🌟 **STEP 6 — ProductService (Business Logic)**

*The clean-hearted layer*

```java
package com.shop.service;

import com.shop.model.Product;
import com.shop.repository.ProductRepository;
import java.util.List;

public class ProductService {

    private ProductRepository repo;

    public ProductService(ProductRepository repo) {
        this.repo = repo;
    }

    public List<Product> getAllProducts() {
        return repo.findAll();
    }

    public void addProduct(Product p) {
        repo.save(p);
    }

    public void removeProduct(int id) {
        repo.delete(id);
    }
}
```

# 🌟 **STEP 7 — ProductServlet (Web Layer)**

*The presentation layer using clean architecture*

```java
package com.shop.web;

import com.shop.repository.*;
import com.shop.service.ProductService;
import com.shop.util.DBUtil;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import java.io.IOException;

@WebServlet("/products")
public class ProductServlet extends HttpServlet {

    private ProductService productService;

    @Override
    public void init() {
        ProductRepository repo = new JdbcProductRepository(DBUtil.getConnection());
        productService = new ProductService(repo);
    }

    @Override
    protected void doGet(
            HttpServletRequest req,
            HttpServletResponse resp) throws ServletException, IOException {

        req.setAttribute("products", productService.getAllProducts());
        req.getRequestDispatcher("products.jsp").forward(req, resp);
    }
}
```

# 🌟 **STEP 8 — JSP (View Layer)**

`products.jsp`

```jsp
<h2>Product List</h2>

<table border="1">
    <tr>
        <th>ID</th><th>Name</th><th>Price</th>
    </tr>

    <c:forEach var="p" items="${products}">
        <tr>
            <td>${p.id}</td>
            <td>${p.name}</td>
            <td>${p.price}</td>
        </tr>
    </c:forEach>
</table>
```

---

# 💥 FINAL RESULT

You now have a **full working project** using:

✔ Clean Architecture
✔ Separation of Concerns
✔ Repository Pattern
✔ Interface + Polymorphism
✔ Testable Business Logic
✔ Reusable Service Layer
✔ Decoupled Servlet

This is now **industry-grade** code — simplified for students, but architecturally strong.
