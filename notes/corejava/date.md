# Mentor Notes: Understanding the `Date` Class in Java

## Learning Objective

In object-oriented programming (OOP), a class represents a real-world concept by combining **data (state)** and **behavior (methods)**. The `Date` class is a simple yet effective example for learning how Java classes are designed and how objects are created and used.


## 1. Class Definition – The Blueprint

```java
public class Date {
```

### Explanation

* A **class** is a blueprint or template for creating objects.
* The `Date` class models a calendar date such as **12/10/2025**.
* The `public` access modifier makes the class accessible from anywhere in the application.

### Mentor Insight

Think of a class as the architectural plan for a house. The plan itself is not a house, but many houses (objects) can be built from it.


## 2. Instance Variables – Representing State

```java
private int day;
private int month;
private int year;
```

### Explanation

These variables store the internal state of each `Date` object.

* `day` → Stores the day of the month.
* `month` → Stores the month.
* `year` → Stores the year.

The `private` keyword enforces **encapsulation**, preventing direct access from outside the class.

### Mentor Insight

Each object maintains its own independent values.

For example:

| Object     | Day | Month | Year |
| ---------- | --: | ----: | ---: |
| `today`    |  12 |    10 | 2025 |
| `birthday` |   5 |     7 | 2003 |

## 3. Constructor – Initializing Objects

```java
public Date(int d, int m, int y){
    this.day = d;
    this.month = m;
    this.year = y;
}
```

### Explanation

A constructor is a special method that runs automatically when an object is created using the `new` keyword.

Example:

```java
Date today = new Date(12, 10, 2025);
```

Execution steps:

1. Memory is allocated for the object.
2. The constructor is invoked.
3. The values are assigned:

   * `day = 12`
   * `month = 10`
   * `year = 2025`

### Understanding `this`

The `this` keyword refers to the current object.

```java
this.day = d;
```

Here:

* `this.day` refers to the object's field.
* `d` refers to the constructor parameter.

### Mentor Insight

Constructors ensure that newly created objects start in a valid and initialized state.

## 4. Methods – Defining Behavior

```java
public void show(){
    System.out.println(this.day + "/" + this.month + "/" + this.year);
}
```

### Explanation

Methods define what an object can do.

The `show()` method prints the stored date in `day/month/year` format.

Example:

```java
today.show();
```

Output:

```
12/10/2025
```

### Understanding `void`

The return type `void` indicates that the method performs an action but does not return a value.

### Mentor Insight

An object is useful not only because it stores information but also because it exposes behaviors through methods.

## 5. Complete Working Example

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
        today.show();
    }
}
```

### Expected Output

```
12/10/2025
```

# Object Creation Flow

```text
Class Definition (Blueprint)
            │
            ▼
Date today = new Date(12, 10, 2025);
            │
            ▼
Constructor Executes
            │
            ▼
day = 12
month = 10
year = 2025
            │
            ▼
today.show()
            │
            ▼
Console Output:
12/10/2025
```

# Object-Oriented Programming Concepts Demonstrated

| Concept                                  | Description                                                            |
| ---------------------------------------- | ---------------------------------------------------------------------- |
| **Class**                                | A blueprint used to define objects and their behavior.                 |
| **Object**                               | A runtime instance created from a class.                               |
| **Instance Variables**                   | Fields that store the state of an object.                              |
| **Encapsulation**                        | Restricting direct access to data using `private` members.             |
| **Constructor**                          | A special method that initializes an object when it is created.        |
| **`this` Keyword**                       | Refers to the current object and distinguishes fields from parameters. |
| **Method**                               | Encapsulates behavior or operations that objects can perform.          |
| **Access Modifier (`public`/`private`)** | Controls the visibility and accessibility of classes and members.      |

# Mentor Recommendations

* Start with simple domain classes like `Date`, `Student`, `Customer`, or `Employee` to understand OOP fundamentals.
* Keep data members `private` and expose behavior through methods to promote encapsulation.
* Use constructors to ensure every object is initialized with meaningful values.
* Use `this` whenever constructor parameters or local variables have the same names as instance variables.
* After mastering this example, enhance the `Date` class by adding methods such as `isLeapYear()`, `nextDay()`, `compareTo(Date other)`, or `toString()` to gain deeper experience with object-oriented design.