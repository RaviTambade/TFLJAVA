### 🧱 1️⃣ Class Definition

```java
public class Date {
```

* `class` → A **class** is like a blueprint or template.
* Here, `Date` is a class that represents a **date** (like 12/10/2025).
* `public` means this class can be accessed from **anywhere** in your program.



### 📦 2️⃣ Data Members (Instance Variables)

```java
private int day;
private int month;
private int year;
```

These are **instance variables** — data that belongs to each object of the `Date` class.

* `private` → means these variables **cannot be accessed directly** from outside the class.
  (It’s called **encapsulation**, one of the four pillars of OOP.)
* `int` → data type (integer numbers).
* So, each `Date` object will store its own `day`, `month`, and `year`.

---

### 🏗️ 3️⃣ Constructor

```java
public Date(int d, int m, int y){
    this.day = d;
    this.month = m;
    this.year = y;
}
```

* A **constructor** is a special method used to **initialize objects**.
* It runs **automatically** when you create an object using `new`.

Example:

```java
Date today = new Date(12, 10, 2025);
```

This will:

* Create a new `Date` object.
* Call the constructor.
* Assign values:

  * `this.day = 12;`
  * `this.month = 10;`
  * `this.year = 2025;`

The keyword `this` refers to **the current object** — it helps Java know you’re referring to the instance variable, not the parameter.

 

### 👁️ 4️⃣ Display Method

```java
public void show(){
    System.out.println(this.day + "/" + this.month + "/" + this.year);
}
```

* `void` → means this method **doesn’t return anything**.
* It just **prints** the date in `day/month/year` format.
* `System.out.println()` prints output on the console.

Example:

```java
today.show();
```

Output:

```
12/10/2025
```
 -

### 💡 5️⃣ Complete Example to Run

If you put everything together:

```java
public class Date {
    private int day;
    private int month;
    private int year;

    public Date(int d, int m, int y){
        this.day = d;
        this.month = m;
        this.year = y;
    }

    public void show(){
        System.out.println(this.day + "/" + this.month + "/" + this.year);
    }

    public static void main(String[] args) {
        Date today = new Date(12, 10, 2025);
        today.show(); // prints: 12/10/2025
    }
}
```
 

### 🧠 Key Concepts You Learned Here

| Concept               | Meaning                              |
| --------------------- | ------------------------------------ |
| **Class**             | Blueprint for creating objects       |
| **Object**            | Instance of a class                  |
| **Private variables** | Used for data hiding (encapsulation) |
| **Constructor**       | Initializes object data              |
| **this keyword**      | Refers to current object             |
| **Method**            | Defines behavior of a class          |
 