# Understanding the `Employee` Class in Java

## Learning Objective

The `Employee` class demonstrates how **Object-Oriented Programming (OOP)** models real-world business entities. It combines data, behavior, object relationships, constructors, static members, and polymorphism to represent an employee in a payroll or human resources system.


# 1. Class Purpose

```java
public class Employee {
```

## Explanation

A class is a blueprint used to create objects. The `Employee` class represents an employee working in an organization.

Each `Employee` object stores information such as:

* Employee name
* Number of working days
* Daily wages
* Basic salary
* Tax deductions
* Birth date

### Mentor Insight

Think of the class as a template. Every employee in the company is an object created from this template, each with its own data.

# 2. Instance Variables (Attributes)

```java
private String name;
private int workingDays;
private double dailyWages;
private double basicSalary;
private double tax;
private Date birthdate;
```

## Explanation

These fields define the state of an employee.

| Field         | Purpose                                                 |
| ------------- | ------------------------------------------------------- |
| `name`        | Employee's full name                                    |
| `workingDays` | Number of days worked                                   |
| `dailyWages`  | Earnings per day                                        |
| `basicSalary` | Fixed salary component                                  |
| `tax`         | Tax deduction                                           |
| `birthdate`   | Employee's date of birth represented by a `Date` object |

The `private` modifier supports **encapsulation** by restricting direct external access.

# 3. HAS-A Relationship (Association / Composition)

```java
private Date birthdate;
```

## Explanation

This declaration means:

> **An Employee has a Date.**

Rather than storing separate `day`, `month`, and `year` fields inside `Employee`, the design reuses the `Date` class.

This illustrates the **HAS-A relationship**, often implemented using composition.

### Example

```java
Date dob = new Date(12, 6, 2003);
Employee emp = new Employee("Aarav", 20, 1000, 5000, 200);
```

Here, the employee object contains a `Date` object representing the employee's birth date.

### Mentor Insight

Building larger systems by combining smaller, focused classes promotes modularity, reuse, and maintainability.


# 4. Constructors and Object Initialization

```java
public Employee(String name,
                int workingDays,
                double dailyWages,
                double basicSalary,
                double tax) {

    this.name = name;
    this.workingDays = workingDays;
    this.dailyWages = dailyWages;
    this.basicSalary = basicSalary;
    this.tax = tax;

    this.birthdate = new Date(12, 6, 2003);
    employeeCount++;
}
```

## Explanation

The constructor initializes the employee when it is created.

Responsibilities include:

* Assigning parameter values to instance variables
* Creating the embedded `Date` object
* Updating the shared employee counter

The `this` keyword refers to the current object being initialized.

### Mentor Insight

Constructors ensure that newly created objects begin with valid and consistent state.


# 5. Static Variables and Shared Data

```java
private static int employeeCount = 0;
```

## Explanation

A `static` field belongs to the class itself rather than to individual objects.

Whenever a new employee is created:

```java
employeeCount++;
```

the shared count increases.

### Example

```java
Employee e1 = new Employee(...);
Employee e2 = new Employee(...);

System.out.println(Employee.getEmployeeCount());
```

Output:

```
2
```

### Mentor Insight

Use `static` for information common to all objects, such as counters or configuration values.


# 6. Method Overloading (`computePay`)

```java
public double computePay() {
    return (workingDays * dailyWages) + basicSalary - tax;
}

public double computePay(double bonus) {
    return computePay() + bonus;
}
```

## Explanation

Both methods share the same name but differ in their parameter lists.

This is called **method overloading**, an example of **compile-time polymorphism**.

* `computePay()` calculates standard pay.
* `computePay(double bonus)` includes an additional bonus amount.

### Mentor Insight

Overloading provides multiple ways to perform related operations while keeping method names meaningful and consistent.


# 7. Tax Calculation Method

```java
public void calculateTax(double taxPercentage) {
    this.tax = computePay() * (taxPercentage / 100);
}
```

## Explanation

This method recalculates tax based on a supplied percentage of the employee's computed pay.

Example:

If computed pay is `50,000` and tax percentage is `10`, then:

```
Tax = 50,000 × 10 / 100 = 5,000
```

# 8. Displaying Employee Information

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

## Explanation

This method prints a formatted summary of the employee's information and calculated net salary.

Typical uses include:

* Debugging
* Console demonstrations
* Reports
* Educational examples

# 9. Static Method Example

```java
public static int getEmployeeCount() {
    return employeeCount;
}
```

## Explanation

Because the method is `static`, it is invoked using the class name rather than an object reference.

Example:

```java
System.out.println(Employee.getEmployeeCount());
```

This returns the total number of `Employee` objects created.


# 10. Key Object-Oriented Programming Concepts

| Concept                              | Explanation                                                        |
| ------------------------------------ | ------------------------------------------------------------------ |
| **Class**                            | Blueprint used to define employee objects.                         |
| **Object**                           | A runtime instance of the `Employee` class.                        |
| **Encapsulation**                    | Private fields protect internal state from direct external access. |
| **Constructor**                      | Initializes an employee when it is created.                        |
| **Static Variable**                  | Shared by all instances of the class.                              |
| **Static Method**                    | Invoked on the class rather than on an object.                     |
| **Method Overloading**               | Multiple methods with the same name but different parameter lists. |
| **HAS-A Relationship (Composition)** | `Employee` contains a `Date` object representing birth date.       |
| **`this` Keyword**                   | Refers to the current object.                                      |
| **Compile-Time Polymorphism**        | Achieved through overloaded methods such as `computePay()`.        |


# 11. Example Main Program

```java
public class Main {
    public static void main(String[] args) {
        Employee e1 = new Employee("Aarav", 20, 1000, 5000, 200);

        e1.displayInfo();

        System.out.println("\nEmployee Count: " +
                           Employee.getEmployeeCount());
    }
}
```

### Sample Output

```text
Name: Aarav
Working Days: 20
Daily Wages: 1000.0
Basic Salary: 5000.0
Tax: 200.0
Net Salary: 24800.0

Employee Count: 1
```

> **Note:** With the formula `(workingDays × dailyWages) + basicSalary − tax`, the values shown above evaluate to `20 × 1000 + 5000 − 200 = 24,800`. If a different total such as `24,500` is expected, review the salary calculation formula or input values.

# Mentor Recommendations

* Design each class around a single business concept or responsibility.
* Favor composition (HAS-A relationships) to build complex models from simpler reusable classes.
* Keep fields private and expose behavior through methods to preserve encapsulation.
* Use constructors to guarantee proper initialization of objects.
* Apply `static` members only for data and behavior shared across all instances.
* Demonstrate polymorphism through method overloading when multiple variants of the same operation are needed.
* As an extension exercise, enhance the `Employee` class with getters and setters, a `toString()` implementation, validation logic, and support for configurable birth dates passed into the constructor.