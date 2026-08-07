# What Is Debugging?

**Debugging** is the process of **finding and fixing mistakes (bugs)** in your code so that it works the way you want.

### **What's a Bug?**
A **bug** is a **problem in your program** — it could be:
* Wrong output
* Error messages
* Program crash
* Or it just behaves weirdly

### **Goal of Debugging**
To **understand what went wrong**, and then **fix it**.

Imagine you're a **detective** investigating a crime scene:

* The program is your **crime scene**
* A bug is the **mystery**
* You are the **detective**
* Breakpoints are like **security cameras** stopping the action
* The debugger tools help you **interrogate variables**, **trace functions**, and **see what happened step by step**

That’s **debugging**!

### ⚙️ **How Debugging Works**

When you **run a program normally**, it runs from top to bottom, too fast to observe. But in **debug mode**:

1. You can **pause** the program at specific lines (called **breakpoints**)
2. You can **see values** of variables (e.g. what’s inside `a` and `b`)
3. You can **go line-by-line** and watch what happens next
4. You can **fix mistakes** once you see where things went wrong

### 👨‍💻 Example (Java)

```java
int a = 5;
int b = 0;
int result = a / b;  // 💥 bug: division by zero
```

When you debug:

* You pause at `int result = a / b`
* You see `b = 0`
* You realize the code will crash
* You fix it with:

```java
if (b != 0) {
    result = a / b;
} else {
    System.out.println("Cannot divide by zero");
}
```

### 🛠️ Tools That Help Debug

* **Breakpoints** – pause the program
* **Step Over / Step Into** – run line-by-line
* **Watch / Variables** – inspect variable values
* **Call Stack** – see which method called which


> “Debugging is not just fixing code. It’s understanding how your code thinks.”

The more you debug, the better programmer you become — not by memorizing, but by **observing and understanding**.

### 🔍 **What does "Debug" mean?**

**Debug** = **De** + **Bug**
👉 It literally means "**removing bugs**."

### 🐞 **What is a bug?**

A **bug** is a **mistake** or **error** in your code that causes the program to behave unexpectedly — like crashing, giving wrong output, or doing nothing at all.

### 🛠️ **So, what is debugging?**

**Debugging** is the process of:

1. **Finding** the bug
2. **Understanding** why it's happening
3. **Fixing** it

### 🤖 Example

You wrote this code:

```java
int a = 5;
int b = 0;
int result = a / b;  // This line causes a bug (division by zero)
```

When you run it — the program crashes.
🧠 You now **debug** to find where and why it crashed, then fix it:

```java
if (b != 0) {
    int result = a / b;
} else {
    System.out.println("Cannot divide by zero");
}
```

### 👨‍🏫 Simple Analogy

Imagine your program is a **car** 🚗 and it's **not starting**.
You open the **car bonnet** (like opening your code), check wires, engine, battery (like checking variables, logic), and **fix** the issue.
That’s **debugging**!

### 🧰 Prerequisites (Tools You Need First)

✅ Install the following:

1. **Java JDK** (version 11 or higher)
   👉 Verify: `java -version`
2. **Maven** (build tool for Java)
   👉 Verify: `mvn -version`
3. **Visual Studio Code**
4. In VS Code, install the **Java Extension Pack**, which includes:

   * Java Language Support
   * Debugger for Java
   * Maven for Java

### 🎯 Step-by-Step: Create a Maven Java Project

#### 🧱 1. Create the Project

Open terminal in VS Code and run:

```bash
mvn archetype:generate -DgroupId=com.example \
  -DartifactId=demo-debug \
  -DarchetypeArtifactId=maven-archetype-quickstart \
  -DinteractiveMode=false
```

This creates a project structure like:

```
demo-debug/
├── pom.xml
└── src/
    ├── main/java/com/example/App.java
    └── test/java/com/example/AppTest.java
```

#### 📂 2. Open Project in VS Code

Open `demo-debug` folder in VS Code.

> VS Code will prompt: *"Java project detected. Do you want to import?"*
> ✅ Click **Yes** (it will auto-detect Maven structure)

#### ✍️ 3. Add Simple Debuggable Code

Open `src/main/java/com/example/App.java` and replace it with:

```java
package com.example;

public class App {
    public static void main(String[] args) {
        int x = 10;
        int y = 5;
        int result = multiply(x, y);  // ← Set breakpoint here
        System.out.println("Result: " + result);
    }

    public static int multiply(int a, int b) {
        return a * b;
    }
}
```
#### 📌 4. Set a Breakpoint

Click to the **left of the line number** where it says:

```java
int result = multiply(x, y);
```

👉 A red dot will appear — this is your **breakpoint**.

#### ▶️ 5. Start Debugging

* Press `F5` OR go to **Run → Start Debugging**
* Choose **Java** if prompted
* The debugger will pause at the breakpoint
* You can now:

  * 🧐 See variable values (`x`, `y`, `result`)
  * 🔁 Step over, step into, continue
  * 🛑 Stop debugging when done

### 🧠 What Happens Behind the Scenes?

When you start debugging:

* VS Code compiles using Maven
* Launches the Java debugger
* Breaks execution at your breakpoint
* Opens **debug panel** with watch, variables, and call stack

### 🗂️ Optional: launch.json (custom config)

VS Code auto-generates this. But if needed:

```json
// .vscode/launch.json
{
  "version": "0.2.0",
  "configurations": [
    {
      "type": "java",
      "name": "Debug App",
      "request": "launch",
      "mainClass": "com.example.App",
      "projectName": "demo-debug"
    }
  ]
}
```

### ✅ You're Debug-Ready!