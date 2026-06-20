## 🧱 Maven Project Structure

```
task-manager/
├── pom.xml
├── src/
│   ├── main/
│   │   └── java/
│   │       └── com/
│   │           └── mentor/
│   │               └── taskmanager/
│   │                   ├── Task.java
│   │                   ├── TaskManager.java
│   │                   └── Main.java
│   └── test/
│       └── java/
│           └── com/
│               └── mentor/
│                   └── taskmanager/
│                       └── TaskManagerTest.java
```

## 📦 `pom.xml` – Maven Dependencies

```xml
<project xmlns="http://maven.apache.org/POM/4.0.0"
         xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
         xsi:schemaLocation="
           http://maven.apache.org/POM/4.0.0
           http://maven.apache.org/xsd/maven-4.0.0.xsd">
    <modelVersion>4.0.0</modelVersion>

    <groupId>com.mentor</groupId>
    <artifactId>task-manager</artifactId>
    <version>1.0-SNAPSHOT</version>

    <name>Task Manager</name>

    <properties>
        <maven.compiler.source>17</maven.compiler.source>
        <maven.compiler.target>17</maven.compiler.target>
    </properties>

    <dependencies>
        <!-- JUnit 5 for Testing -->
        <dependency>
            <groupId>org.junit.jupiter</groupId>
            <artifactId>junit-jupiter</artifactId>
            <version>5.10.0</version>
            <scope>test</scope>
        </dependency>
    </dependencies>

    <build>
        <plugins>
            <!-- Surefire Plugin to run tests -->
            <plugin>
                <groupId>org.apache.maven.plugins</groupId>
                <artifactId>maven-surefire-plugin</artifactId>
                <version>3.2.2</version>
            </plugin>
        </plugins>
    </build>
</project>
```



## ✅ `TaskManagerTest.java` – Unit Testing with JUnit

```java
package com.mentor.taskmanager;

import org.junit.jupiter.api.*;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class TaskManagerTest {
    private TaskManager manager;

    @BeforeEach
    void setUp() {
        manager = new TaskManager();
    }

    @Test
    void testAddTask() {
        manager.addTask("Learn Java", "OOP and Collections");
        manager.addTask("Build App", "Use ArrayList");

        List<Task> tasks = manager.getAllTasks();
        assertEquals(2, tasks.size());
        assertEquals("Learn Java", tasks.get(0).getTitle());
    }

    @Test
    void testDeleteTask() {
        manager.addTask("Task A", "Test delete");
        manager.addTask("Task B", "Keep");

        int idToDelete = manager.getAllTasks().get(0).getId();
        manager.deleteTask(idToDelete);

        List<Task> tasks = manager.getAllTasks();
        assertEquals(1, tasks.size());
        assertEquals("Task B", tasks.get(0).getTitle());
    }

    @Test
    void testMarkCompleted() {
        manager.addTask("Complete me", "Mark as done");

        int id = manager.getAllTasks().get(0).getId();
        manager.markTaskCompleted(id);

        assertTrue(manager.getAllTasks().get(0).isCompleted());
    }

    @Test
    void testSearchTask() {
        manager.addTask("Shopping", "Buy milk");
        manager.addTask("Cleaning", "Kitchen and hall");

        List<Task> results = manager.searchTaskAndReturn("clean");
        assertEquals(1, results.size());
        assertEquals("Cleaning", results.get(0).getTitle());
    }
}
```

> 🔧 Add the following helper method in `TaskManager.java` to support testing:

```java
public List<Task> getAllTasks() {
    return new ArrayList<>(taskList);
}

public List<Task> searchTaskAndReturn(String keyword) {
    List<Task> result = new ArrayList<>();
    for (Task task : taskList) {
        if (task.getTitle().toLowerCase().contains(keyword.toLowerCase())) {
            result.add(task);
        }
    }
    return result;
}
```

## 💻 Build & Run

### To Compile and Package:

```bash
mvn clean compile
```

### To Run Tests:

```bash
mvn test
```

### To Run App (if packaged):

```bash
mvn package
java -cp target/task-manager-1.0-SNAPSHOT.jar com.mentor.taskmanager.Main
```


## 🧠 Mentor Insight:

> “Maven taught Ravi **project discipline**, and JUnit taught him the **power of test-driven thinking**.
> Tools don't just support your code — they sharpen your mind.”
