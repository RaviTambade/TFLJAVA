## Java Multithreading + Async

### **1. The Pain of Waiting**

> *"Have you ever stood in a bank queue with only one cashier? You wait… the person ahead of you has a thousand coins to count… and you’re stuck doing nothing. Now imagine the bank has 4 cashiers. Suddenly, people move faster, and everyone’s happy. That’s the magic of multithreading in software — multiple workers instead of one."*

```java
public class SingleThreadBank {
    public static void main(String[] args) {
        serveCustomer("Customer 1");
        serveCustomer("Customer 2");
        serveCustomer("Customer 3");
    }

    static void serveCustomer(String name) {
        System.out.println(name + " service started...");
        try { Thread.sleep(2000); } catch (InterruptedException e) {}
        System.out.println(name + " service finished!");
    }
}
```

💡 **tip:** Run this, and students will complain it’s slow. This creates *emotional buy-in* for why concurrency matters.

### **2. First Taste of Multithreading**


> *"Java can hire more 'cashiers' without you buying new furniture. Just threads. Let’s open more counters."*

```java
public class MultiThreadBank {
    public static void main(String[] args) {
        Thread t1 = new Thread(() -> serveCustomer("Customer 1"));
        Thread t2 = new Thread(() -> serveCustomer("Customer 2"));
        Thread t3 = new Thread(() -> serveCustomer("Customer 3"));

        t1.start();
        t2.start();
        t3.start();
    }

    static void serveCustomer(String name) {
        System.out.println(name + " service started by " + Thread.currentThread().getName());
        try { Thread.sleep(2000); } catch (InterruptedException e) {}
        System.out.println(name + " service finished by " + Thread.currentThread().getName());
    }
}
```

💡 **Key explanation:**

* `.start()` → runs in parallel.
* `.run()` → runs in the current thread (don’t confuse them).

Run it — students will see mixed start/finish order. This *surprise* cements the concept.


### **3. Thread Pools – Hiring Without Overstaffing**

*(You use a workplace analogy again.)*

> *"Hiring too many workers increases payroll and chaos. Thread pools are like a well-managed HR department — fixed staff, tasks in queue."*

```java
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ThreadPoolBank {
    public static void main(String[] args) {
        ExecutorService service = Executors.newFixedThreadPool(2);

        for (int i = 1; i <= 5; i++) {
            final String customer = "Customer " + i;
            service.submit(() -> serveCustomer(customer));
        }

        service.shutdown();
    }

    static void serveCustomer(String name) {
        System.out.println(name + " served by " + Thread.currentThread().getName());
        try { Thread.sleep(2000); } catch (InterruptedException e) {}
    }
}
```
### **4. Async Programming – The Coffee Shop Analogy**

*(Change tone to something casual.)*

> *"Imagine you order coffee, and instead of staring at the coffee machine, you keep chatting with your friend. When the coffee is ready, the barista calls your name. That’s asynchronous programming."*

**Java Example with `CompletableFuture`:**

```java
import java.util.concurrent.CompletableFuture;

public class AsyncCoffeeShop {
    public static void main(String[] args) {
        System.out.println("Order placed for coffee...");

        CompletableFuture<Void> coffeeReady = CompletableFuture.runAsync(() -> {
            try { Thread.sleep(2000); } catch (InterruptedException e) {}
            System.out.println("Coffee is ready!");
        });

        System.out.println("You keep chatting with your friend...");
        coffeeReady.join(); // Wait at the very end
        System.out.println("Enjoy your coffee!");
    }
}
```

💡 **Teaching point:**

* Async ≠ multithreading always, but often uses threads.
* Async is **non-blocking** — main thread keeps doing work.

### **5. Hands-on Challenge**


> **Challenge:** “Write a program that downloads 3 files in parallel using `CompletableFuture.supplyAsync()`, and after all are downloaded, print ‘All files ready’.”

💡 **Why?** This forces them to combine **parallel execution** + **async callbacks**.

### **6. Common Pitfalls (Story Form)**

* **Race Conditions:** Two tellers update the same account → wrong balance.
* **Deadlock:** Two tellers wait for each other’s pen → no one moves.
* **Starvation:** One teller hogs the counter, others never serve.

💡 Show a **counter without synchronization** in Java, then fix it with `synchronized` keyword.

### **7. Wrap-Up Inspiration**

> *"Threads are like workers — treat them well, give them the right tools, and don’t overwork them. Async is like smart scheduling — don’t keep them idle when they could be helping others. If you master both, your software will feel as fast as your best real-life team."*