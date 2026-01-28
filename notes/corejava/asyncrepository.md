##  Async Repository for Product Management

### 👨‍🏫 Setting the Scene:

I was mentoring a student named **Sejal**, who was building a basic **eCommerce backend** using Java. She had one class called `Product` and wanted to perform **CRUD operations** — but using **modern asynchronous techniques**, like **`async/await`** in .NET.

In Java, we don't have `async/await` keywords, but we **simulate it using `CompletableFuture`** — which is part of Java’s powerful **concurrency utilities**.

So I showed her how to design an **Async Repository Pattern**.

## 🧱 Step-by-Step Breakdown


### ✅ 1. `Product` Class – POJO

```java
public class Product {
    private int id;
    private String name;
    private double price;

    // Constructors
    public Product(int id, String name, double price) {
        this.id = id;
        this.name = name;
        this.price = price;
    }

    // Getters and Setters
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public double getPrice() { return price; }
    public void setPrice(double price) { this.price = price; }

    @Override
    public String toString() {
        return "Product [id=" + id + ", name=" + name + ", price=" + price + "]";
    }
}
```

### ✅ 2. `IProductRepository` – Async Interface

```java
import java.util.List;
import java.util.Optional;
import java.util.concurrent.CompletableFuture;

public interface IProductRepository {
    CompletableFuture<Void> addProduct(Product product);
    CompletableFuture<Optional<Product>> getProductById(int id);
    CompletableFuture<List<Product>> getAllProducts();
    CompletableFuture<Void> updateProduct(Product product);
    CompletableFuture<Boolean> deleteProduct(int id);
}
```

### ✅ 3. `InMemoryProductRepository` – Async Implementation

```java
import java.util.*;
import java.util.concurrent.*;
import java.util.stream.Collectors;

public class InMemoryProductRepository implements IProductRepository {
    private final List<Product> products = Collections.synchronizedList(new ArrayList<>());

    @Override
    public CompletableFuture<Void> addProduct(Product product) {
        return CompletableFuture.runAsync(() -> {
            simulateDelay();
            products.add(product);
        });
    }

    @Override
    public CompletableFuture<Optional<Product>> getProductById(int id) {
        return CompletableFuture.supplyAsync(() -> {
            simulateDelay();
            return products.stream().filter(p -> p.getId() == id).findFirst();
        });
    }

    @Override
    public CompletableFuture<List<Product>> getAllProducts() {
        return CompletableFuture.supplyAsync(() -> {
            simulateDelay();
            return new ArrayList<>(products);
        });
    }

    @Override
    public CompletableFuture<Void> updateProduct(Product updatedProduct) {
        return CompletableFuture.runAsync(() -> {
            simulateDelay();
            for (int i = 0; i < products.size(); i++) {
                if (products.get(i).getId() == updatedProduct.getId()) {
                    products.set(i, updatedProduct);
                    return;
                }
            }
        });
    }

    @Override
    public CompletableFuture<Boolean> deleteProduct(int id) {
        return CompletableFuture.supplyAsync(() -> {
            simulateDelay();
            return products.removeIf(p -> p.getId() == id);
        });
    }

    private void simulateDelay() {
        try { Thread.sleep(500); } catch (InterruptedException e) { Thread.currentThread().interrupt(); }
    }
}
```

### ✅ 4. Demo – Using the Async Repository

```java
public class AsyncRepositoryDemo {
    public static void main(String[] args) throws Exception {
        IProductRepository repo = new InMemoryProductRepository();

        // Add Products
        repo.addProduct(new Product(1, "Laptop", 79999)).get();
        repo.addProduct(new Product(2, "Phone", 29999)).get();

        // Get All
        List<Product> all = repo.getAllProducts().get();
        System.out.println("All Products: " + all);

        // Update
        repo.updateProduct(new Product(2, "Smartphone", 24999)).get();

        // Get by ID
        Product p = repo.getProductById(2).get().orElse(null);
        System.out.println("Product with ID 2: " + p);

        // Delete
        repo.deleteProduct(1).get();

        // Final List
        System.out.println("Final Products: " + repo.getAllProducts().get());
    }
}
```

## 🔍 Mentor Notes: Why This Pattern?

* ✅ **Clean Separation** between interface and implementation.
* ✅ Simulates **non-blocking architecture** useful in web services and backend.
* ✅ Can later replace `InMemory` with a **real DB implementation** (like MongoDB, PostgreSQL, etc.) without changing business logic.


## 💬 Final Words from the Mentor

"A good programmer doesn’t just write code. They **design systems** that are scalable, testable, and future-proof. The async repository pattern is one such design."
