
## 🧱 1️⃣ Class Purpose

```java
public class Employee {
```

The `Employee` class represents a **real-world employee** in a company — with data such as name, salary, tax, and **birthdate**.

In OOP, classes like `Employee` are **blueprints for objects** (individual employees).


## 🧩 2️⃣ Instance Variables (Attributes)

```java
private String name;
private int workingDays;
private double dailyWages;
private double basicSalary;
private double tax;
private Date birthdate;  // Employee has a Date
```

Each employee **object** stores its own data:

* `name` → employee’s name
* `workingDays` → how many days worked
* `dailyWages` → pay per day
* `basicSalary` → base salary
* `tax` → deducted amount
* `birthdate` → **object of another class (`Date`)**


## 🤝 3️⃣ HAS-A Relationship (Association / Containment)

```java
private Date birthdate;
```

✅ This line is very important — it means:

> **“Employee has a Date”**

That’s a **HAS-A relationship**, also called **Association** or **Containment**.

In simple words:

* `Date` is a **part of** `Employee`.
* An `Employee` *contains* a `Date` object.

So instead of creating a new type of Date logic inside `Employee`, we **reuse** the `Date` class.
This is how **composition** works in OOP — combining small, focused classes to build larger systems.

Example:

```java
Date d = new Date(12,6,2003);
Employee e = new Employee("Aarav", 20, 1000, 5000, 200);
```

Here, the Employee *has* a `Date` object inside it (his birthdate).


## 🏗️ 4️⃣ Constructors (Initialization Methods)

```java
public Employee(String name, int workingDays, double dailyWages, double basicSalary, double tax) {
    this.name = name;
    this.workingDays = workingDays;
    this.dailyWages = dailyWages;
    this.basicSalary = basicSalary;
    this.tax = tax;

    this.birthdate = new Date(12,6,2003);  // composition
    employeeCount++;
}
```

* Constructors are **special methods** that initialize the object.
* `this` refers to **the current object**.
* `this.birthdate = new Date(12,6,2003);`
  ➜ creates a `Date` object **inside** the `Employee` object (containment).

Also note:

```java
private static int employeeCount = 0;
```

`static` → shared among all Employee objects (not per instance).
So, every time a new employee is created:

```java
employeeCount++;
```

This increases the total number of employees tracked globally.


## 🔄 5️⃣ Method Overloading — computePay

```java
public double computePay() {
    return (workingDays * dailyWages) + basicSalary - tax;
}

public double computePay(double bonus) {
    return computePay() + bonus;
}
```

Here, both methods have the **same name** but **different parameters**.
This is called **Method Overloading** (a type of compile-time polymorphism).

* `computePay()` → basic salary computation.
* `computePay(bonus)` → adds bonus to the basic computation.


## 💰 6️⃣ Method Example — calculateTax

```java
public void calculateTax(double taxPercentage) {
    this.tax = computePay() * (taxPercentage / 100);
}
```

This method dynamically calculates tax as a percentage of total pay.


## 🧾 7️⃣ Display Information Method

```java
public void displayInfo() {
    System.out.println("Name: " + name);
    System.out.println("Working Days: " + workingDays);
    System.out.println("Daily Wages: " + dailyWages);
    System.out.println("Basic Salary: " + basicSalary);
    System.out.println("Tax: " + tax);
    System.out.println("Net Salary: " + computePay());
}
```

Shows employee data neatly formatted — typical for debugging or reporting.


## 🧮 8️⃣ Static Method Example

```java
public static int getEmployeeCount() {
    return employeeCount;
}
```

Static methods belong to the **class**, not the object.
You can call this as:

```java
System.out.println(Employee.getEmployeeCount());
```

This is a classic use of a **class-level property**.


## 🧠 9️⃣ Summary — Java Concepts Used Here

| Concept                         | Description                                      |
| ------------------------------- | ------------------------------------------------ |
| **Class**                       | Blueprint for objects                            |
| **Object**                      | Instance of a class                              |
| **Encapsulation**               | Data is private and accessed via getters/setters |
| **Constructor**                 | Initializes object attributes                    |
| **Static Variable/Method**      | Belongs to class, not to each object             |
| **Method Overloading**          | Same method name, different parameter list       |
| **Containment (Has-A)**         | Employee *has a* Date object                     |
| **this keyword**                | Refers to current object                         |
| **Polymorphism (compile-time)** | Through overloaded methods                       |


## 🧩 10️⃣ Example Main Program

```java
public class Main {
    public static void main(String[] args) {
        Employee e1 = new Employee("Aarav", 20, 1000, 5000, 200);
        e1.displayInfo();

        System.out.println("\nEmployee Count: " + Employee.getEmployeeCount());
    }
}
```

### Output:

```
Name: Aarav
Working Days: 20
Daily Wages: 1000.0
Basic Salary: 5000.0
Tax: 200.0
Net Salary: 24500.0

Employee Count: 1
```
 