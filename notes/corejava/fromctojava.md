
## From C to Java 

###  **1: The Age of Native Code**

> "Let’s go back to where it all began — to the age of C and C++."

Back in our earlier sessions, we wrote C++ programs. We compiled them into **object files**, and linked them into executable code — we called this the **AXC**, the Application Executable Code. With the help of the **compiler** and the **linker**, we created code that spoke directly to the operating system.

This is what we call **native code**.
No interpreter, no runtime — just raw power.
Your application is talking to the operating system like a warrior with no shield.

That’s why we call these **system-level applications**.

###  **2: Enter Java — The Language of Enterprise**

> “Now we are moving from the wilderness to the city — from system code to enterprise code. From C++ to Java.”

Java is not just a programming language — it's a **language of business**, a **language of stability**, a **language of scaling**.

When you learn Java, you're not just learning to build an app.
You’re learning how to build **systems that power banks, governments, e-commerce giants, and even satellites.**

Let me take you to our repository: **TFL-Java**.
Yes, yesterday I updated it with fresh examples. This is our **launchpad** into the world of Java.

###  **3: Why Java Matters**

> “Behind every buzzword — AI, microservices, DevOps, Cloud — lies a quiet, steady powerhouse: Java.”

Java has survived not because it’s flashy, but because it’s **reliable**.
It is **platform-independent**, **robust**, **secure**, and **scalable**.
It’s trusted by Amazon, Netflix, Google — even Microsoft.

If you're serious about building **production-grade applications**, handling **real-world business logic**, and working with **enterprise data** — Java is your foundation.

###  **4: Let's Build Our First Java App**

So how do we begin?

 Step 1: **Install Java Runtime**
Use either **OpenJDK** or **Oracle Java**.
Check installation:

```bash
java -version
```

 Step 2: **Open VS Code** (with Java Extension installed)

 Step 3: **Write your first Java class**

```java
public class TFLClassroom {
    public static void main(String[] args) {
        System.out.println("Welcome to TFL Classroom!");
        System.out.println("This class demonstrates principles of Object-Oriented Programming.");
    }
}
```

This is your **starting point** — your `main()` function is the **entry gate**, just like `int main()` in C++.


###  **5: Building with Classes — Let’s Create a Student**

In Java, **everything is inside a class**. Java is a **pure object-oriented language**.

So let’s create a `Student.java` file:

```java
public class Student {
    private String name;

    public Student(String name) {
        this.name = name;
    }

    public String getName() { return name; }

    @Override
    public String toString() {
        return "Transflower Student: " + name;
    }
}
```

> “Notice the magic here: `@Override` is an annotation — metadata that tells Java, *Hey, I’m redefining a method from my parent class!*”

Every Java class silently extends from the **Object class** — the mother of all classes.

The `toString()` method comes from this Object class.
When you override it, you're customizing how your object prints itself.

### **6: The Memory Game — Stack vs Heap**

Now in our main program:

```java
Student s1 = new Student("Pankaj");
Student s2 = new Student("Nikhil");
Student s3 = new Student("Sejal");

System.out.println(s1);
System.out.println(s2);
System.out.println(s3);
```

* Objects are created in the **heap**
* References (`s1`, `s2`, `s3`) are stored on the **stack**
* `System.out.println(s1)` internally calls `s1.toString()` — your overridden method

This is how Java merges **abstraction with performance**.

### **7: Run, Debug, Evolve**

Open your `TFLClassroom.java` in VS Code → click "Run Java".

Observe the output:

```
Welcome to TFL Classroom!
This class demonstrates principles of Object-Oriented Programming.
Transflower Student: Pankaj
Transflower Student: Nikhil
Transflower Student: Sejal
```

> "We changed the formatting of `toString()`? Boom — the output adapts. That’s Java's beauty."

### **8: What Did You Just Learn?**

You didn’t just write Java code.

You just understood:

* The **transition from system programming to enterprise programming**
* Why Java still rules the backend of the world
* **Classes, Objects, Annotations, Memory**
* The elegant concept of **Object-Oriented Programming** in practice

### **9: Java vs C — Simplicity vs Power**

* **C** gives you raw power — like riding a motorcycle with no helmet
* **Java** gives you power **with safety** — it’s the bullet train of programming
* Coming up next? A language that **merges both** — the simplicity of Java and the performance of C.

> “Stay tuned — because what comes next is a story of speed, safety, and scalability.”