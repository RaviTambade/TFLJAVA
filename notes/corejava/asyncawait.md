### Learning Async/Await in Java

Let me take you back to the time when I was mentoring three bright students: **Pranit**, **Sarthak**, and **Sahil**. They were working on a school management project where they had to **fetch student records**, **calculate average scores**, and **send an email report**.

But here's the twist — each task took time.
Fetching records from a remote server took 3 seconds.
Calculating average scores took 2 seconds.
Sending an email took another 4 seconds.

If done one after another, it would take 9 seconds. That’s slow!
So I asked them:

> ❓“What if we could start all these tasks and not wait for each to finish before moving to the next?”

That’s when we introduced the magical world of **asynchronous programming** in Java — powered by `CompletableFuture`.


### 🧠 The Analogy: The Chef and the Oven

Imagine a chef who needs to:

1. **Boil water** (3 minutes),
2. **Bake a cake** (30 minutes), and
3. **Prepare salad** (5 minutes).

If he waits for each task to finish before starting the next, he wastes a lot of time.

But if he **puts water to boil**, then **starts baking**, and while the cake is in the oven, he **prepares the salad** — all three get done faster.

This is **asynchronous thinking**.


### ☕ Java’s Async Ingredients: `CompletableFuture`

Java doesn't have direct `async` and `await` keywords like C#, but it **achieves the same result** using `CompletableFuture`.

Let me show how I explained this to Sarthak:


### 👨‍🏫 **Real Code Example**

```java
import java.util.concurrent.CompletableFuture;

public class AsyncDemo {
    public static void main(String[] args) throws Exception {
        System.out.println("Start: " + Thread.currentThread().getName());

        // Step 1: Fetch data
        CompletableFuture<String> fetchData = CompletableFuture.supplyAsync(() -> {
            simulateDelay(3000);
            return "Student Data";
        });

        // Step 2: Process data (when fetched)
        CompletableFuture<String> processedData = fetchData.thenApply(data -> {
            simulateDelay(2000);
            return data + " + Processed Marks";
        });

        // Step 3: Send email (after processing)
        CompletableFuture<Void> emailSent = processedData.thenAccept(data -> {
            simulateDelay(4000);
            System.out.println("Email sent with: " + data);
        });

        // Wait for all to finish
        emailSent.join();

        System.out.println("Done: " + Thread.currentThread().getName());
    }

    static void simulateDelay(int millis) {
        try { Thread.sleep(millis); } catch (InterruptedException e) { }
    }
}
```


### 🔍 What's Happening Behind the Scenes?

* `supplyAsync()` is like `async` — it **starts a task in the background**.
* `thenApply()` is like `await` + transform — it **waits and processes the result**.
* `thenAccept()` is like **final action** when all is done.
* `join()` waits for everything to complete.

### 💡 Mentor's Insight: Why Use Async?

I told Sahil:

> “In real-world applications, you don’t want your UI or API to freeze while waiting for slow operations like **network calls**, **database queries**, or **file uploads**.”

Using `CompletableFuture`, you can **run tasks in parallel**, reduce total waiting time, and keep your app responsive.


### 🧵 Advanced Tip: Run in Parallel

Want to **run multiple tasks at the same time**?

```java
CompletableFuture<String> task1 = CompletableFuture.supplyAsync(() -> {
    simulateDelay(2000);
    return "Task 1 done";
});

CompletableFuture<String> task2 = CompletableFuture.supplyAsync(() -> {
    simulateDelay(3000);
    return "Task 2 done";
});

CompletableFuture<Void> all = CompletableFuture.allOf(task1, task2);
all.join(); // Waits for both

System.out.println("All tasks completed!");
```


### 🎓 Final Words from the Mentor

"Don't get stuck waiting in line when you can cook multiple dishes at once. That’s what async is all about."
