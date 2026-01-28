Wonderful! Let's continue the **mentor storytelling journey** with the next level:
## Custom Serialization and JSON-based Serialization using Jackson/Gson

## From Magic Backpacks to Custom Chests – Beyond Default Serialization in Java

> *“The default magic works… until you want your own spells.”*
> — Mentor Javaanand

### 🧙 Scene 6: Custom Serialization – Writing Your Own Spellbook

Meera was back, but this time she had a special requirement.

> “Professor! I want to save the game state — but only the level. The score is secret! I don’t want it stored.”

Professor nodded,

> “Ah, default serialization won't help. Time to write your own spells.”

He handed her the ancient scroll:

```java
import java.io.*;

class GameState implements Serializable {
    private static final long serialVersionUID = 1L;
    int level;
    transient int score; // marked transient, but…

    public GameState(int level, int score) {
        this.level = level;
        this.score = score;
    }

    private void writeObject(ObjectOutputStream oos) throws IOException {
        oos.defaultWriteObject(); // default serialization for level
        // You can write extra info if needed
        System.out.println("Custom write logic");
    }

    private void readObject(ObjectInputStream ois) throws IOException, ClassNotFoundException {
        ois.defaultReadObject(); // restore level
        // You can restore custom fields separately if written
        System.out.println("Custom read logic");
    }

    public String toString() {
        return "Level: " + level + ", Score: " + score;
    }
}
```

> “So you're overriding the default behavior to decide exactly what goes into the backpack,” said Meera.

🧪 She tested it — and it worked. Only the parts she wanted were saved and restored.


### ⚙️ Use Case: When is Custom Serialization Useful?

| Scenario                       | Why Use Custom Serialization? |
| ------------------------------ | ----------------------------- |
| Fine control                   | Store only required fields    |
| Encrypt sensitive data         | Like passwords before storing |
| Handle non-serializable fields | Like streams, sockets, etc.   |
| Backward compatibility         | Add/remove fields gracefully  |


### 🌍 Scene 7: JSON-Based Serialization – Speaking the Universal Language

Soon, Meera wanted to **share game progress** with a friend on another system — but they spoke **Python**.

Professor smiled again:

> “Use JSON, the language everyone understands.”


### 📦 Step 1: Using Jackson for JSON Serialization

```xml
<!-- Add this to your Maven pom.xml -->
<dependency>
  <groupId>com.fasterxml.jackson.core</groupId>
  <artifactId>jackson-databind</artifactId>
  <version>2.15.2</version>
</dependency>
```

```java
import com.fasterxml.jackson.databind.ObjectMapper;

class GameState {
    public int level;
    public int score;

    public GameState() {} // default constructor needed

    public GameState(int level, int score) {
        this.level = level;
        this.score = score;
    }
}
```

```java
ObjectMapper mapper = new ObjectMapper();
GameState gameState = new GameState(10, 3000);

// Serialize to JSON string
String json = mapper.writeValueAsString(gameState);
System.out.println("JSON Output: " + json);

// Deserialize back to object
GameState restored = mapper.readValue(json, GameState.class);
System.out.println("Restored: " + restored.level + ", " + restored.score);
```

🧠 Now, Meera could send game progress over networks, store in NoSQL, or even expose APIs!


### 🆚 Gson Alternative:

```xml
<!-- For Maven -->
<dependency>
  <groupId>com.google.code.gson</groupId>
  <artifactId>gson</artifactId>
  <version>2.10.1</version>
</dependency>
```

```java
import com.google.gson.Gson;

Gson gson = new Gson();
String json = gson.toJson(gameState);
GameState fromJson = gson.fromJson(json, GameState.class);
```

### ✅ When to Use What?

| Technique                   | Use When...                                                       |
| --------------------------- | ----------------------------------------------------------------- |
| Java Serialization (`.ser`) | Internal Java-only storage, fast, binary format                   |
| Custom Serialization        | You need control or optimization                                  |
| JSON (Jackson/Gson)         | Interoperability with APIs, databases, and cross-language systems |


### 🔚 Final Words from the Mentor

> “Meera, now you hold not one but many keys:
> A binary lockbox for Java, a custom chest for secrets, and a universal messenger for the world.
> Use them wisely.”


Great! Let's build a hands-on mini project:
🎓 **Student Profile Saver**
A simple Java app to demonstrate:

* ✅ Binary Serialization (`.ser` file)
* ✅ JSON Serialization (using Jackson)
* ✅ Export to and import from files


## 👨‍🏫 **Project Story: “Preserving Student Profiles Across Time and Space”**

Imagine you're managing student profiles — name, age, marks, and course — and you want to:

1. Save profiles locally (binary file)
2. Share them over APIs (JSON format)
3. Load profiles back and display


## 🧱 Step-by-Step Plan:

### ✅ 1. Create the Student class

```java
import java.io.Serializable;

public class Student implements Serializable {
    private static final long serialVersionUID = 1L;

    public String name;
    public int age;
    public double marks;
    public String course;

    public Student() {} // Required for JSON

    public Student(String name, int age, double marks, String course) {
        this.name = name;
        this.age = age;
        this.marks = marks;
        this.course = course;
    }

    @Override
    public String toString() {
        return name + " | " + age + " | " + marks + " | " + course;
    }
}
```

### ✅ 2. Save as Binary (.ser)

```java
import java.io.*;

public class BinarySerializer {
    public static void saveStudent(Student s, String filename) throws IOException {
        FileOutputStream fos = new FileOutputStream(filename);
        ObjectOutputStream oos = new ObjectOutputStream(fos);
        oos.writeObject(s);
        oos.close();
        System.out.println("Student saved in binary format.");
    }

    public static Student loadStudent(String filename) throws IOException, ClassNotFoundException {
        FileInputStream fis = new FileInputStream(filename);
        ObjectInputStream ois = new ObjectInputStream(fis);
        Student s = (Student) ois.readObject();
        ois.close();
        return s;
    }
}
```

### ✅ 3. Save as JSON (using Jackson)

**Add to your `pom.xml`** (if using Maven):

```xml
<dependency>
  <groupId>com.fasterxml.jackson.core</groupId>
  <artifactId>jackson-databind</artifactId>
  <version>2.15.2</version>
</dependency>
```

```java
import com.fasterxml.jackson.databind.ObjectMapper;

public class JSONSerializer {
    private static ObjectMapper mapper = new ObjectMapper();

    public static void saveStudent(Student s, String filename) throws IOException {
        mapper.writeValue(new File(filename), s);
        System.out.println("Student saved in JSON format.");
    }

    public static Student loadStudent(String filename) throws IOException {
        return mapper.readValue(new File(filename), Student.class);
    }
}
```

### ✅ 4. Test It All in `Main.java`

```java
public class Main {
    public static void main(String[] args) {
        try {
            Student student = new Student("Aarya Patil", 21, 87.5, "Computer Science");

            // Binary Serialization
            BinarySerializer.saveStudent(student, "student.ser");
            Student loadedFromBinary = BinarySerializer.loadStudent("student.ser");
            System.out.println("Loaded from binary: " + loadedFromBinary);

            // JSON Serialization
            JSONSerializer.saveStudent(student, "student.json");
            Student loadedFromJson = JSONSerializer.loadStudent("student.json");
            System.out.println("Loaded from JSON: " + loadedFromJson);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
```

## ✅ Output Example:

```
Student saved in binary format.
Loaded from binary: Aarya Patil | 21 | 87.5 | Computer Science

Student saved in JSON format.
Loaded from JSON: Aarya Patil | 21 | 87.5 | Computer Science
```

## 🧠 Learning Recap:

| Format | Use Case                   | File Extension | Library Used |
| ------ | -------------------------- | -------------- | ------------ |
| Binary | Fast, local, Java-specific | `.ser`         | Built-in     |
| JSON   | API-ready, human-readable  | `.json`        | Jackson      |

## 🏁 Ready for the Next Step?

Would you like to:

1. Extend this to **save/load multiple students** (like a list)?
2. Add a **menu-based console app** for interactive input?
3. Try **Gson** as an alternative to Jackson?
4. Build a **Spring Boot REST API** around this?

Tell me where you'd like to go next, and I’ll guide you there.

Great! Let's **extend this project** into an interactive **console-based menu application** that can:

✅ Accept multiple student entries
✅ Save/load all students using both **Binary** and **JSON** serialization
✅ Display all loaded students

## 🎯 Project Title: **Student Profile Saver – Console App Edition**

> "In the hands of a student, even a console becomes a classroom."

### ✅ Step-by-Step Plan

We will:

1. Create a `List<Student>` to store multiple students
2. Add a simple console menu:

   * 1️⃣ Add student
   * 2️⃣ Save (Binary / JSON)
   * 3️⃣ Load (Binary / JSON)
   * 4️⃣ Display all students
   * 0️⃣ Exit
3. Support saving and loading the entire list of students

### 🔁 Update `Student` class (same as before – already serializable)

```java
import java.io.Serializable;

public class Student implements Serializable {
    private static final long serialVersionUID = 1L;
    public String name;
    public int age;
    public double marks;
    public String course;

    public Student() {}

    public Student(String name, int age, double marks, String course) {
        this.name = name;
        this.age = age;
        this.marks = marks;
        this.course = course;
    }

    @Override
    public String toString() {
        return name + " | " + age + " | " + marks + " | " + course;
    }
}
```

### 🧾 Update BinarySerializer for List

```java
import java.io.*;
import java.util.List;

public class BinarySerializer {
    public static void saveStudents(List<Student> students, String filename) throws IOException {
        ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(filename));
        oos.writeObject(students);
        oos.close();
        System.out.println("Students saved in binary format.");
    }

    public static List<Student> loadStudents(String filename) throws IOException, ClassNotFoundException {
        ObjectInputStream ois = new ObjectInputStream(new FileInputStream(filename));
        List<Student> students = (List<Student>) ois.readObject();
        ois.close();
        return students;
    }
}
```

### 🌐 Update JSONSerializer for List (using Jackson)

```java
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.core.type.TypeReference;
import java.io.*;
import java.util.List;

public class JSONSerializer {
    private static ObjectMapper mapper = new ObjectMapper();

    public static void saveStudents(List<Student> students, String filename) throws IOException {
        mapper.writeValue(new File(filename), students);
        System.out.println("Students saved in JSON format.");
    }

    public static List<Student> loadStudents(String filename) throws IOException {
        return mapper.readValue(new File(filename), new TypeReference<List<Student>>() {});
    }
}
```

### 🖥️ Main Menu Console App

```java
import java.util.*;
import java.io.*;

public class Main {
    static List<Student> students = new ArrayList<>();
    static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        while (true) {
            System.out.println("\n---- Student Profile Saver ----");
            System.out.println("1. Add Student");
            System.out.println("2. Save Students (Binary)");
            System.out.println("3. Load Students (Binary)");
            System.out.println("4. Save Students (JSON)");
            System.out.println("5. Load Students (JSON)");
            System.out.println("6. Display All Students");
            System.out.println("0. Exit");
            System.out.print("Choose an option: ");
            int choice = Integer.parseInt(scanner.nextLine());

            try {
                switch (choice) {
                    case 1 -> addStudent();
                    case 2 -> BinarySerializer.saveStudents(students, "students.ser");
                    case 3 -> students = BinarySerializer.loadStudents("students.ser");
                    case 4 -> JSONSerializer.saveStudents(students, "students.json");
                    case 5 -> students = JSONSerializer.loadStudents("students.json");
                    case 6 -> displayStudents();
                    case 0 -> System.exit(0);
                    default -> System.out.println("Invalid choice!");
                }
            } catch (Exception e) {
                System.out.println("Error: " + e.getMessage());
            }
        }
    }

    static void addStudent() {
        System.out.print("Name: ");
        String name = scanner.nextLine();
        System.out.print("Age: ");
        int age = Integer.parseInt(scanner.nextLine());
        System.out.print("Marks: ");
        double marks = Double.parseDouble(scanner.nextLine());
        System.out.print("Course: ");
        String course = scanner.nextLine();

        students.add(new Student(name, age, marks, course));
        System.out.println("Student added.");
    }

    static void displayStudents() {
        if (students.isEmpty()) {
            System.out.println("No students available.");
        } else {
            System.out.println("\n--- Student List ---");
            for (Student s : students) {
                System.out.println(s);
            }
        }
    }
}
```

## 🧪 Sample Interaction:

```
---- Student Profile Saver ----
1. Add Student
2. Save Students (Binary)
3. Load Students (Binary)
4. Save Students (JSON)
5. Load Students (JSON)
6. Display All Students
0. Exit
Choose an option: 1
Name: Tejas
Age: 22
Marks: 91.5
Course: AI

Student added.
```

## ✅ What You've Practiced:

| Skill                                      | Description           |
| ------------------------------------------ | --------------------- |
| `Serializable`                             | Saving Java objects   |
| `ObjectOutputStream` / `ObjectInputStream` | Binary format         |
| Jackson JSON                               | Platform-independent  |
| Collections serialization                  | Handling lists        |
| Console interaction                        | Real-world user input |

