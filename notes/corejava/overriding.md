 
###  Mentor Walks into the Room with a File Full of Employee Records… 

> *"Students,"* the mentor begins, holding a file of printed employee records, *"Have you ever tried to compare two employees and ended up confused because they looked different on paper, even though they were the same person?"*

> *"Or tried printing employee info and saw some strange output like `Employee@7a46a697`?"*

> The class chuckles.

> *"Well, that’s why we override `toString()`, `equals()`, `hashCode()`, and even `finalize()`."*

 

## 🧑‍💼 Meet the Hero: The `Employee` Class

```java
public class Employee {
    private int id;
    private String name;
    private String designation;
    private double salary;
    private String location;

    // Constructor + Getters/Setters
}
```

Now, let’s tell the story behind **each method override** with real-world metaphors.
 

## 📌 1. **Why Override `toString()`?**

> *“You’re showing your ID badge to someone…”*

By default, if you do:

```java
System.out.println(employee);
```

It prints:

```
Employee@3ac3fd8b
```

That’s like showing your **database memory address** instead of your **name**!

### ✅ Mentor Fix:

```java
@Override
public String toString() {
    return "Employee: " + name + ", ID: " + id + ", Role: " + designation +
           ", Salary: ₹" + salary + ", Location: " + location;
}
```

> *“Now when I print the employee, I get human-readable details, not a robot code,”* the mentor says with a grin.

 

## 📌 2. **Why Override `equals()`?**

> *“You meet two people named Raj. Are they the same person just because their name is the same?”*

Java’s default `equals()` checks for **reference** equality — like checking if both variables point to **the same object** in memory.

But in real-world HR, you care if two employees have the **same ID**, not if they're stored in the same place.

### ✅ Mentor Fix:

```java
@Override
public boolean equals(Object obj) {
    if (this == obj) return true;
    if (obj == null || getClass() != obj.getClass()) return false;
    Employee other = (Employee) obj;
    return this.id == other.id; // assuming ID is unique
}
```

> *"Now, two Employee objects with the same ID will be treated as equal — just like the HR system would!"*

  

## 📌 3. **Why Override `hashCode()`?**

> *“Imagine placing employees into folders based on their ID. You need a consistent rule to decide which folder to place them in.”*

In Java, `hashCode()` determines **which bucket** in a hash-based collection (like `HashMap`, `HashSet`) your object goes into.

If you override `equals()`, you **must** override `hashCode()` — otherwise, weird bugs will happen in `HashSet`, `HashMap`.

### ✅ Mentor Fix:

```java
@Override
public int hashCode() {
    return Integer.hashCode(id);
}
```

> *"Hash code is like a filing system. It makes searching fast, and keeps objects organized in memory,"* says the mentor, pointing to a cabinet.

 

## 📌 4. **Why Override `finalize()`?** *(⚠️ Deprecated Use)*

> *“This is the goodbye note your object writes before being deleted…”*

Java calls `finalize()` **before garbage collection** — giving you a last chance to clean up (like closing a file or database connection).

But it’s **not reliable or recommended anymore**. In modern Java (from Java 9+), it’s deprecated. You should use `try-with-resources` or `Cleaner`.

### 🟡 Example:

```java
@Override
protected void finalize() throws Throwable {
    System.out.println("Cleaning up Employee: " + this.name);
}
```

> *“Treat `finalize()` like a retirement speech. Rarely needed, and nowadays, HR handles it for you.”*

  

## 🧠 Mentor Summary: "Know Your Employee's Identity"

| Method       | Why Override It?                                           | Real-Life Analogy                 |
| ------------ | ---------------------------------------------------------- | --------------------------------- |
| `toString()` | To display meaningful output instead of memory hash        | ID badge / bio-data               |
| `equals()`   | To compare business meaning (e.g., ID) not memory location | Comparing by employee ID          |
| `hashCode()` | To enable efficient storage & lookup in collections        | Filing system folder              |
| `finalize()` | Cleanup before deletion (not used today)                   | Farewell speech before retirement |
 

### ✅ Final Tip from Mentor:

> “Java gives you default behavior, but real-world objects like Employees have **identity**, **behavior**, and **meaning**. When you override these methods, you give your class a personality and a place in the system.”
 