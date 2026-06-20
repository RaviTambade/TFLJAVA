# Understanding the Basics of Object-Oriented Programming (OOP)

## Learning Objective

Object-Oriented Programming (OOP) is a programming paradigm that models software using **objects**, which represent real-world entities. Instead of thinking only in terms of functions and data, OOP encourages developers to design software around **things**, their **properties**, and their **behaviors**.

# 1. What is OOP?

OOP is a way of organizing programs by combining **data** and the **operations performed on that data** into reusable units called **objects**.

### Real-World Examples

| Real World       | Software Object  |
| ---------------- | ---------------- |
| Student          | `Student` class  |
| Employee         | `Employee` class |
| Bank Account     | `Account` class  |
| Insurance Policy | `Policy` class   |
| Car              | `Car` class      |

### Mentor Insight

Think about the real-world problem before writing code. Identify the important entities and model them as classes.

# 2. Class – The Blueprint

A **class** defines the structure and behavior of similar objects.

```java
public class Student {
    private String name;
    private int age;
}
```

A class specifies:

* What data objects should store.
* What operations objects can perform.

### Analogy

A class is like the blueprint of a house. Many houses can be built from the same blueprint.


# 3. Object – The Real Instance

An **object** is an instance created from a class.

```java
Student s1 = new Student();
Student s2 = new Student();
```

Although both objects belong to the same class, they maintain independent data.

| Object | Name  | Age |
| ------ | ----- | --- |
| `s1`   | Alice | 20  |
| `s2`   | Bob   | 22  |

# 4. Attributes (Fields)

Attributes describe the **state** of an object.

```java
private String name;
private int age;
```

For an employee, attributes may include:

* Employee ID
* Name
* Salary
* Department

For a customer:

* Customer ID
* Email
* Phone Number
* Address

# 5. Methods (Behaviors)

Methods define what an object can do.

```java
public void display() {
    System.out.println(name);
}
```

Examples:

| Class    | Method            |
| -------- | ----------------- |
| Student  | `study()`         |
| Employee | `computeSalary()` |
| Account  | `deposit()`       |
| Policy   | `renewPolicy()`   |

# 6. Constructor

A constructor initializes an object when it is created.

```java
public Student(String name, int age) {
    this.name = name;
    this.age = age;
}
```

Creating an object:

```java
Student s = new Student("Alice", 20);
```

The constructor ensures that objects start with meaningful values.

# 7. The `this` Keyword

`this` refers to the current object.

```java
this.name = name;
```

It distinguishes the instance variable from the constructor parameter.


# 8. Encapsulation

Encapsulation hides internal data and provides controlled access.

```java
private double balance;

public void deposit(double amount) {
    balance += amount;
}
```

Benefits:

* Protects data integrity
* Prevents accidental modification
* Improves maintainability


# 9. Abstraction

Abstraction exposes only necessary functionality while hiding implementation details.

Example:

```text
ATM Machine
------------
Insert Card
Enter PIN
Withdraw Money
Check Balance
```

The customer uses simple operations without needing to understand the internal banking system.


# 10. Inheritance (IS-A Relationship)

Inheritance allows one class to reuse and extend another.

```text
            Employee
             /    \
            /      \
      Manager   Developer
```

Here:

* Manager **is an** Employee.
* Developer **is an** Employee.

Benefits:

* Code reuse
* Easier maintenance
* Extensibility


# 11. Composition (HAS-A Relationship)

Composition models one object containing another.

Examples:

```text
Employee HAS-A DateOfBirth
Customer HAS-A Address
Policy HAS-A Nominee
Order HAS-A Product
```

Example:

```java
class Employee {
    private Date birthDate;
}
```

Composition promotes modular and reusable designs.


# 12. Polymorphism

Polymorphism allows one interface to support multiple implementations.

### Method Overloading

```java
computePay()
computePay(double bonus)
```

Same method name, different parameter lists.

### Method Overriding

```text
Animal
   |
   +-- Dog
   +-- Cat
```

Each subclass provides its own implementation of a common method such as `makeSound()`.

# 13. Static Members

Static variables belong to the class rather than individual objects.

```java
private static int employeeCount;
```

Static methods can be invoked without creating an object.

```java
Employee.getEmployeeCount();
```

Common uses:

* Counters
* Constants
* Utility methods


# 14. Object Relationships

## IS-A Relationship

```text
Vehicle
   |
   +-- Car
```

A Car **is a** Vehicle.

## HAS-A Relationship

```text
Car
 |
 +-- Engine
```

A Car **has an** Engine.

Understanding these relationships helps build accurate object models.

# 15. Four Pillars of OOP

| Pillar            | Description                                                  | Example                            |
| ----------------- | ------------------------------------------------------------ | ---------------------------------- |
| **Encapsulation** | Protects data through controlled access                      | Private fields with public methods |
| **Abstraction**   | Hides complexity and exposes essential behavior              | ATM interface                      |
| **Inheritance**   | Reuses and extends existing classes                          | `Manager` extends `Employee`       |
| **Polymorphism**  | Supports multiple implementations through a common interface | Overloaded or overridden methods   |

# 16. Sample OOP Design for an Insurance System

```text
                Customer
                    |
        -------------------------
        |                       |
        v                       v
   InsurancePolicy -------> Nominee
          |
          |
          v
        Claim
          |
          v
       Payment
```

Possible responsibilities:

* **Customer** – Stores customer information.
* **InsurancePolicy** – Manages policy details and premiums.
* **Nominee** – Holds beneficiary information.
* **Claim** – Processes insurance claims.
* **Payment** – Records premium payments.



# 17. Why Learn OOP?

OOP enables developers to:

* Model real-world business problems naturally.
* Reuse code through inheritance and composition.
* Build modular and maintainable systems.
* Reduce duplication.
* Improve scalability and collaboration in large projects.


# Key Takeaways

* A **class** is a blueprint; an **object** is an instance of that blueprint.
* **Attributes** represent an object's state, while **methods** define its behavior.
* **Constructors** initialize objects, and the `this` keyword refers to the current instance.
* **Encapsulation** protects data, **abstraction** hides complexity, **inheritance** enables reuse, and **polymorphism** supports flexible behavior.
* **Composition (HAS-A)** and **inheritance (IS-A)** are fundamental ways to model relationships between classes.
* Mastering these basics provides the foundation for building enterprise applications such as banking, e-commerce, healthcare, and insurance management systems.