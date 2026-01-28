
## Multithreading & Asynchronous Programming in Java

### **1. Opening Story — Setting the Context**

*"Before we open our laptops, I want you to imagine this... You walk into a restaurant. There’s only one chef. He takes your order for pasta, then makes your coffee, then prepares your dessert — one by one. By the time you get your food, you’re already late for your next meeting."*

*"Now, imagine there are three chefs. One starts making pasta, another brews coffee, the third handles dessert — all at the same time. That’s **multithreading**. Multiple tasks, running in parallel, sharing the same kitchen (CPU)."*

*"But there’s another trick — you don’t always need multiple chefs. Sometimes, you just need to place an order and do something else until it’s ready. That’s **asynchronous programming**. You give the coffee order and go chat with your friend, and the waiter tells you when it’s ready."*

### **2. Real-world Problem to Solve**

**Mentor:**
*"We will simulate a real-world problem — we’ll download multiple images from the internet while keeping our application responsive. Later, we’ll process a large file without freezing the UI."*

### **3. Hands-on Part 1 — Multithreading in Java**

*(Mentor switches to IDE and types while explaining)*

#### **Code Example: Multiple Chefs**

```java
class DishMaker extends Thread {
    private String dish;

    public DishMaker(String dish) {
        this.dish = dish;
    }

    @Override
    public void run() {
        System.out.println(dish + " preparation started by " + Thread.currentThread().getName());
        try {
            Thread.sleep(2000); // Simulate cooking time
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(dish + " is ready!");
    }
}

public class MultiChefRestaurant {
    public static void main(String[] args) {
        Thread chef1 = new DishMaker("Pasta");
        Thread chef2 = new DishMaker("Coffee");
        Thread chef3 = new DishMaker("Dessert");

        chef1.start();
        chef2.start();
        chef3.start();
    }
}
```

**Mentor Commentary:**

* *"Each `Thread` here is a chef in the kitchen."*
* *"Notice how the output may appear in different orders — that’s because threads run independently."*
* *"We didn’t wait for one to finish before starting the next — they overlap."*

**Hands-on Task for Students:**

* Change `Thread` to `Runnable` and run using `ExecutorService`.
* Add more dishes and see how threads handle them.

### **4. Hands-on Part 2 — Asynchronous Programming**

*(Mentor shifts analogy to coffee order)*

#### **Code Example: Coffee Order**

```java
import java.util.concurrent.CompletableFuture;

public class AsyncCoffeeShop {
    public static void main(String[] args) {
        System.out.println("Customer: I’d like a coffee.");
        
        CompletableFuture<Void> coffeeOrder = CompletableFuture.runAsync(() -> {
            try {
                Thread.sleep(2000); // Simulate brewing
                System.out.println("Barista: Your coffee is ready!");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });

        System.out.println("Customer: Great, I’ll chat with my friend while waiting.");
        
        coffeeOrder.join();
        System.out.println("Customer: Thanks, I’ll take my coffee now.");
    }
}
```

**Mentor Commentary:**

* *"Here, `CompletableFuture` lets us continue doing other work while coffee is brewing."*
* *"No need to block the main thread — our program remains responsive."*

**Hands-on Task for Students:**

* Change `runAsync` to `supplyAsync` to return coffee type.
* Chain multiple tasks — order coffee, then dessert, and serve together.

### **5. Combined Example — Downloading Multiple Images**

*(Mentor ties both concepts together)*

**Activity:**

* Students will write code that downloads 5 images from URLs using `CompletableFuture.supplyAsync`.
* Save them locally while allowing the main program to display progress.


### **6. Wrap-up — Key Takeaways**

**Mentor:**

* *"Multithreading is like hiring more chefs — more workers to do tasks in parallel."*
* *"Asynchronous programming is like placing an order and using your time for something else while it’s prepared."*
* *"Both aim for efficiency, but their strategies differ."*
* *"In real applications — servers, file processing, network calls — we often use both together."*