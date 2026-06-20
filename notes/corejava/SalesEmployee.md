
##  1️⃣ he Big Picture: Inheritance = “IS-A Relationship”

```java
public class SalesEmployee extends Employee {
```

This line means:

> “A SalesEmployee **is a type of** Employee.”

✅ **Inheritance** allows one class (child/subclass) to reuse and extend the behavior of another class (parent/superclass).

Here:

* **Parent class (superclass)** → `Employee`
* **Child class (subclass)** → `SalesEmployee`

So, `SalesEmployee` **inherits** all data and methods from `Employee` — like `name`, `basicSalary`, `computePay()`, etc.


## 🧩 2️⃣ New (Specialized) Attributes

```java
private double salesTarget;
private double incentives;
```

These are **extra fields** specific to a sales employee.
A general employee doesn’t need them — so they belong only in this subclass.

💡 This is the idea of **specialization**:

* `Employee` → general template (common features)
* `SalesEmployee` → specialized version (extra sales-related behavior)


## 🏗️ 3️⃣ Constructor and super()

```java
public SalesEmployee(double target, double incentives) {
    super("Rutuja", 35, 1000, 10000, 20000);
    this.salesTarget = target;
    this.incentives = incentives;
}
```

### Let’s break this down:

* `super(...)` calls the **constructor of the parent class (Employee)**.
* It passes initial values for:

  * name = `"Rutuja"`
  * workingDays = `35`
  * dailyWages = `1000`
  * basicSalary = `10000`
  * tax = `20000`
* After that, it sets the subclass’s own fields:

  * `this.salesTarget = target;`
  * `this.incentives = incentives;`

👉 The `super()` call **must be the first statement** in a subclass constructor.
Without it, Java tries to call the **default constructor** of `Employee`, which doesn’t exist here — and causes a compile-time error.

## ⚙️ 4️⃣ Method Overriding

```java
public double computePay() {
    return super.computePay() + incentives;
}
```

This is the key difference between `SalesEmployee` and `Employee`.

* In the parent class, `computePay()` calculated:

  ```java
  (workingDays * dailyWages) + basicSalary - tax
  ```

* In the child class, we **override** the method to add extra pay for sales incentives:

  ```java
  parent’s computePay() + incentives
  ```

This is **method overriding**, one form of **runtime polymorphism** in Java.

### 👉 `super.computePay()`

* Calls the parent’s version of the method.
* Then we extend the result with new behavior.

✅ Concept: **Reuse + Extend (not duplicate)**
This is what makes OOP powerful — we don’t rewrite logic, we **extend it**.


## 🧠 5️⃣ Summary of Java Concepts Used

| Concept                    | Description                                                       | Example in Code                                        |
| -------------------------- | ----------------------------------------------------------------- | ------------------------------------------------------ |
| **Inheritance (IS-A)**     | `SalesEmployee` is a subclass of `Employee`                       | `class SalesEmployee extends Employee`                 |
| **Constructor Chaining**   | Calling parent’s constructor                                      | `super(...)`                                           |
| **Method Overriding**      | Redefining a parent method in subclass                            | `public double computePay()`                           |
| **Polymorphism (Runtime)** | Deciding which `computePay()` to call based on object type        | `Employee e = new SalesEmployee(...); e.computePay();` |
| **Encapsulation**          | Keeping fields private and controlled via constructors or setters | `private double salesTarget`                           |
| **Code Reuse**             | Reusing logic from parent class                                   | `super.computePay()`                                   |


##  6️⃣ Single Responsibility Principle (SRP)

Let’s relate this to a **SOLID principle** — the **S** in SOLID.

> **Single Responsibility Principle (SRP)**
> “A class should have only one reason to change.”

✅ Applying SRP here:

* `Employee` → handles general employee data and salary computation.
* `SalesEmployee` → handles **sales-specific responsibilities** like incentives and targets.

Each class focuses on **one area of responsibility**:

* If salary rules change → modify `Employee`.
* If incentive policy changes → modify `SalesEmployee`.

Thus, code remains **modular, maintainable, and easy to extend**.


##  7️⃣ Example Main Program

```java
public class Main {
    public static void main(String[] args) {
        SalesEmployee s1 = new SalesEmployee(500000, 20000);
        double totalPay = s1.computePay();
        System.out.println("Sales Employee Total Pay: " + totalPay);
    }
}
```

### Output (example)

```
Sales Employee Total Pay: 255000.0
```


## 8️⃣ Summary Analogy (for easy memory)

| Concept                | Real-world analogy                                                  |
| ---------------------- | ------------------------------------------------------------------- |
| **Inheritance (IS-A)** | A “Car” **is a** type of “Vehicle”                                  |
| **super()**            | When a car starts, it still needs the vehicle engine behavior       |
| **Overriding**         | SportsCar can start faster (changes the “start” behavior)           |
| **SRP**                | Each class has one focus: Engine handles power, Car handles driving |


### ✅ Final Thought:

This `SalesEmployee` class is a textbook example of:

* **Inheritance (IS-A relationship)**
* **Method Overriding (runtime polymorphism)**
* **Constructor chaining (super keyword)**
* **SRP (Single Responsibility Principle)**
 