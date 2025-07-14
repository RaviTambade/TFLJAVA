##  Mini Project: Task Manager using Java Collections Framework 

### 👨‍🏫 Mentor Story Setup:

> One day, Ravi decided to apply what he had learned from Sir Narendra.
> “Let me build something useful,” he said, “like a simple **Task Manager**.”
> “Something that can **add**, **view**, **delete**, and **search** tasks!”

## 🧱 Project Structure

* `Task` – A class representing a task
* `TaskManager` – A class managing the tasks using a `List`
* `Main` – Console UI to interact with the user

## 📦 1. `Task.java`

```java
public class Task {
    private int id;
    private String title;
    private String description;
    private boolean isCompleted;

    public Task(int id, String title, String description) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.isCompleted = false;
    }

    public int getId() { return id; }
    public String getTitle() { return title; }
    public String getDescription() { return description; }
    public boolean isCompleted() { return isCompleted; }

    public void markCompleted() {
        this.isCompleted = true;
    }

    @Override
    public String toString() {
        return "[" + id + "] " + title + " - " + (isCompleted ? "✅ Done" : "❌ Pending");
    }
}
```

## 📚 2. `TaskManager.java`

```java
import java.util.*;

public class TaskManager {
    private List<Task> taskList = new ArrayList<>();
    private int nextId = 1;

    public void addTask(String title, String description) {
        Task task = new Task(nextId++, title, description);
        taskList.add(task);
        System.out.println("✅ Task added successfully!");
    }

    public void viewTasks() {
        if (taskList.isEmpty()) {
            System.out.println("⚠️ No tasks available.");
            return;
        }
        for (Task task : taskList) {
            System.out.println(task);
        }
    }

    public void deleteTask(int id) {
        Iterator<Task> iterator = taskList.iterator();
        boolean found = false;
        while (iterator.hasNext()) {
            if (iterator.next().getId() == id) {
                iterator.remove();
                found = true;
                System.out.println("🗑️ Task deleted.");
                break;
            }
        }
        if (!found) System.out.println("❌ Task not found.");
    }

    public void markTaskCompleted(int id) {
        for (Task task : taskList) {
            if (task.getId() == id) {
                task.markCompleted();
                System.out.println("✅ Task marked as completed!");
                return;
            }
        }
        System.out.println("❌ Task not found.");
    }

    public void searchTask(String keyword) {
        for (Task task : taskList) {
            if (task.getTitle().toLowerCase().contains(keyword.toLowerCase())) {
                System.out.println(task);
            }
        }
    }
}
```

## 🖥️ 3. `Main.java` (Console App)

```java
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        TaskManager manager = new TaskManager();
        Scanner sc = new Scanner(System.in);
        int choice;

        do {
            System.out.println("\n===== 📋 TASK MANAGER =====");
            System.out.println("1. Add Task");
            System.out.println("2. View All Tasks");
            System.out.println("3. Delete Task");
            System.out.println("4. Mark Task Completed");
            System.out.println("5. Search Task");
            System.out.println("0. Exit");
            System.out.print("Enter your choice: ");
            choice = sc.nextInt();
            sc.nextLine(); // consume newline

            switch (choice) {
                case 1:
                    System.out.print("Enter title: ");
                    String title = sc.nextLine();
                    System.out.print("Enter description: ");
                    String desc = sc.nextLine();
                    manager.addTask(title, desc);
                    break;
                case 2:
                    manager.viewTasks();
                    break;
                case 3:
                    System.out.print("Enter task ID to delete: ");
                    int idToDelete = sc.nextInt();
                    manager.deleteTask(idToDelete);
                    break;
                case 4:
                    System.out.print("Enter task ID to mark as completed: ");
                    int idToComplete = sc.nextInt();
                    manager.markTaskCompleted(idToComplete);
                    break;
                case 5:
                    System.out.print("Enter keyword to search: ");
                    String keyword = sc.nextLine();
                    manager.searchTask(keyword);
                    break;
                case 0:
                    System.out.println("👋 Exiting Task Manager. Goodbye!");
                    break;
                default:
                    System.out.println("❌ Invalid choice. Try again.");
            }
        } while (choice != 0);

        sc.close();
    }
}
```

## 🔍 Concepts Covered:

| Concept               | Where it's used                         |
| --------------------- | --------------------------------------- |
| `List` (ArrayList)    | To store and manage tasks               |
| `Iterator`            | To safely delete tasks during iteration |
| `OOP` (Encapsulation) | `Task` and `TaskManager` classes        |
| `Scanner`             | Console interaction                     |
| `toString()` override | For pretty task printing                |


## 💡 Bonus Ideas (Advanced Students)

* Sort tasks by name or status
* Save/load tasks from a file (`FileWriter`/`FileReader`)
* Use `Map<Integer, Task>` instead of `List` for quick lookup
* GUI version using JavaFX or Swing

## 🧠 Mentor Reflection:

> "Ravi’s task manager may be small — but it's powerful. Because he didn’t just learn syntax.
> He **understood design**, **practiced logic**, and **used collections with clarity**."

Would you like a **Maven project structure** or **unit testing** added for this mini project?
