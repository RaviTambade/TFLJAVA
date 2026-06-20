##  **The Tale of the Repository Pattern**

###  Scene: The Village of Java Developers

In the peaceful village of Java Developers, lived a group of students learning the sacred art of clean code and maintainable software.

One day, the Mentor calls the students together and says:

> “Let me tell you a story about **separation of concerns** and the secret vault called the **Repository Pattern**.”

### 🛒 The Marketplace and the `Product` Class

Imagine there's a **Marketplace** that sells various products. Naturally, we need a `Product` class.

```java
public class Product {
    private int id;
    private String name;
    private double price;

    // Constructors, getters, setters
}
```

Now, we want to:

* Add new products
* Find a product by ID
* Delete a product
* List all products

So what do most beginners do?

###  The Mistake: Mixing Everything in One Class

They put **business logic** and **data access logic** together in a `ProductService` class:

```java
public class ProductService {
    public void addProduct(Product p) {
        // JDBC code to insert into DB
    }

    public Product getProductById(int id) {
        // JDBC code to fetch product from DB
    }

    // and so on...
}
```

 Mentor frowns and says:

> “This is like mixing your kitchen and your bedroom in one room. It will work… but it’s a mess.”

### The Repository Pattern: Your **Data Access Layer**

So Mentor teaches the way of the Repository:

> "Separate **what** you do (business logic) from **how** you get your data (data access)."

He draws this on the board:

```
[ Controller / Service ]
         ↓
   [ Repository Interface ]
         ↓
[ Repository Implementation (JDBC, JPA, etc.) ]
         ↓
       [ Database ]
```

 

### Step-by-Step with the `ProductRepository`

#### Step 1: Define an Interface

```java
public interface ProductRepository {
    void save(Product product);
    Product findById(int id);
    List<Product> findAll();
    void deleteById(int id);
}
```

>  *“This is the contract. It says what needs to be done, not how.”*

 

#### Step 2: Implement the Interface

```java
public class ProductRepositoryImpl implements ProductRepository {

    @Override
    public void save(Product product) {
        // Use JDBC or JPA to insert/update
        System.out.println("Product saved to DB");
    }

    @Override
    public Product findById(int id) {
        // Retrieve from DB
        return new Product(id, "Sample", 100.0);
    }

    @Override
    public List<Product> findAll() {
        return List.of(
            new Product(1, "Pen", 10.0),
            new Product(2, "Notebook", 50.0)
        );
    }

    @Override
    public void deleteById(int id) {
        System.out.println("Product deleted from DB");
    }
}
```

 

#### Step 3: Use It in Service Layer

```java
public class ProductService {
    private ProductRepository repo = new ProductRepositoryImpl();

    public void registerProduct(Product p) {
        repo.save(p);
    }

    public void showAllProducts() {
        List<Product> products = repo.findAll();
        products.forEach(p -> System.out.println(p.getName()));
    }
}
```

 

### Why Use Repository Pattern?

| Without Repository | With Repository Pattern |
| ------------------ | ----------------------- |
| Tight coupling     | Loose coupling          |
| Code duplication   | Centralized data logic  |
| Hard to test       | Easy to mock/test       |
| Spaghetti code     | Clean architecture      |

 Mentor says:

> “Repository is your **bridge** between business and database. It lets each side focus on its role—like actors and scriptwriters.”

 

### Want to Change from JDBC to JPA?

No problem! Just create a new class:

```java
public class JpaProductRepository implements ProductRepository {
    // Use JPA EntityManager here
}
```

Now change the service:

```java
ProductRepository repo = new JpaProductRepository();
```

✨ *No need to touch business logic at all.*

 

### Final Wisdom

The mentor concludes:

> “Students, the world is full of change. Business rules change. Databases change. Tools change. But if you follow the Repository pattern, your code will **adapt** and **endure**.”
