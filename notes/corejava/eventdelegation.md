 

# Banking Application Design

## Introduction

In this mentor session, Ravi Tambade explains the practical application of event handling, delegation, interfaces, object-oriented programming, and SOLID design principles through a real-world banking application example. The discussion demonstrates how Java uses interfaces and listeners to implement event-driven architectures, enabling loosely coupled and maintainable systems. Students are guided through concepts such as publishers and subscribers, account listeners, dependency injection, marker interfaces, and notification services, while reinforcing core OOP principles like encapsulation, inheritance, polymorphism, and abstraction. The session also emphasizes structured problem-solving, clean code practices, interview readiness, and industry-oriented software design, providing a strong foundation for building scalable applications in both Java and C#.

 


### 1. Learning Objectives

By the end of this session, students should understand:

* Event-driven programming concepts
* Delegation using interfaces in Java
* Publishers and Subscribers pattern
* Listener implementation
* Loose coupling in software design
* Practical application of SOLID principles
* Dependency Injection (DI)
* Core Object-Oriented Programming concepts
* Interview expectations for Java and .NET developers


### 2. Event Handling and Delegation

##### C# vs Java

| C#                         | Java                                                |
| -------------------------- | --------------------------------------------------- |
| Uses `delegate` keyword    | No delegate keyword                                 |
| Supports `event` keyword   | Uses interfaces and listeners                       |
| Delegate points to methods | Interface implementations provide callback behavior |

##### Java achieves delegation using interfaces.

```java
public interface AccountListener {
    void onUnderBalance(Account account);
    void onOverBalance(Account account);
}
```

Concrete classes implement the interface:

```java
public class AccountsDepartment implements AccountListener {

    @Override
    public void onUnderBalance(Account account) {
        System.out.println("Warning: Under Balance");
    }

    @Override
    public void onOverBalance(Account account) {
        System.out.println("High Balance Alert");
    }
}
```


###  3. Banking Application Architecture

```
                Deposit()
                     |
                     |
              +--------------+
              |   Account    |
              +--------------+
                     |
         -------------------------
         |                       |
 Under Balance Event      Over Balance Event
         |                       |
         |                       |
     Notify Listeners      Notify Listeners
         |                       |
    -------------------------------
    |              |             |
Accounts Dept   SMS Service   Email Service
```

The `Account` acts as the **publisher**, while departments and notification services act as **subscribers/listeners**.


### 4. Event Triggers

Typical triggers:

* `onUnderBalance()`
* `onOverBalance()`
* `onDeposit()`
* `onWithdraw()`
* Mouse Click
* Button Click
* Keyboard Input

Example:

```java
if(balance < 1000){
    listener.onUnderBalance(this);
}
```

The event is **raised (fired)** when the condition becomes true.

### 5. Publisher–Subscriber Model

## Publisher

* Generates events
* Does not know implementation details of listeners

Example:
`Account`

##### Subscriber

* Listens to events
* Executes business logic

Examples:

* Accounts Department
* Notification Service
* SMS Service
* Email Service


###  6. Interface Implementation Rules

A concrete class:

* Must implement every method declared in the interface.
* Must use `@Override` when implementing interface methods.

Example:

```java
interface DepositService{
    void deposit(double amount);
}

class SavingsAccount implements DepositService{

    @Override
    public void deposit(double amount){
        // implementation
    }
}
```

# 7. Business Scenario

```
Withdraw ₹5,000
        |
        |
 Balance becomes ₹500
        |
        |
Account detects under balance
        |
        |
Raises UnderBalance Event
        |
        |
Listener receives callback
        |
        |
Accounts Department sends warning
```

###  8. Seven-Step Problem Solving Process

1. Define the problem
2. Analyze the problem
3. Generate possible solutions
4. Evaluate alternatives
5. Select the best solution
6. Implement the solution
7. Test, review, and improve

This structured approach leads to maintainable software.

### 9. Loose Coupling

Bad Design:

```
Account
   |
   +----> SMSService
   |
   +----> EmailService
```

Good Design:

```
Account
    |
    |
NotificationService (Interface)
      /        \
     /          \
SMSService   EmailService
```

Advantages:

* Easier maintenance
* Easier testing
* Easier extension
* Better reuse

### 10. Multiple Listeners

An account can notify multiple listeners simultaneously.

```
Account
   |
   +------> Accounts Department
   |
   +------> SMS Notification
   |
   +------> Email Notification
   |
   +------> Audit Department
```

Each listener independently performs its responsibility.

### 11. Notification Listener Design

```
NotificationListener
        |
   -----------------
   |               |
SMSListener   EmailListener
```

Both classes implement the same abstraction but provide different behaviors.

### 12. Marker Interface

A **Marker Interface** contains no methods and is used to mark a class for special treatment.

Example:

```java
public interface Serializable {

}
```

Purpose:

* Metadata
* Framework identification
* Runtime checks

### 13. SOLID Principles

##### S — Single Responsibility Principle (SRP)

A class should have **only one reason to change**.

Good example:

```
Account
    -> balance operations

EmailService
    -> email sending

SMSService
    -> SMS sending
```

Each class has one responsibility.


## O — Open/Closed Principle (OCP)

> Open for Extension, Closed for Modification.

Instead of changing existing code:

```
NotificationService
      |
 ---------------------
 |                   |
SMSService     EmailService
                    |
             WhatsAppService
```

Extend by adding new classes without modifying existing ones.


##### L — Liskov Substitution Principle (LSP)

Child classes should replace parent abstractions without breaking functionality.

Example:

```java
NotificationService service;

service = new SMSService();

service = new EmailService();

service = new PushNotificationService();
```

The `Account` should work correctly regardless of which implementation is supplied.

## I — Interface Segregation Principle (ISP)

Prefer small, focused interfaces.

Bad:

```
BankService
    deposit()
    withdraw()
    issueCard()
    blockCard()
    transferFunds()
```

Good:

```
DepositService

WithdrawService

CardService

TransferService
```

Classes implement only what they need.

##### D — Dependency Inversion Principle (DIP)

Depend on abstractions, not concrete implementations.

Instead of:

```java
Account
   -> SMSService
```

Use:

```java
Account
   -> NotificationService
```

Then inject:

```java
NotificationService service =
        new SMSService();

Account account =
        new Account(service);
```

This is the basis of **Dependency Injection (DI)**.

##### 14. Constructor Injection Example

```java
class Account {

    private NotificationService notification;

    public Account(NotificationService notification){
        this.notification = notification;
    }

    public void deposit(double amount){
        notification.sendNotification("Deposit Successful");
    }
}
```

The `Account` does not know whether notifications are sent by SMS or email.

##### 15. Goals of Clean Code

A professional software solution should be:

* ✅ Testable
* ✅ Maintainable
* ✅ Reusable
* ✅ Extendable
* ✅ Loosely Coupled
* ✅ Easy to modify
* ✅ Easy to understand


### 16. Suggested Maven Project Structure

```
src/
 └── main/
      └── java/
            ├── model/
            │      Account.java
            │
            ├── operations/
            │      DepositService.java
            │      WithdrawService.java
            │
            ├── listeners/
            │      AccountListener.java
            │      NotificationListener.java
            │
            ├── publishers/
            │      AccountPublisher.java
            │
            ├── services/
            │      SMSService.java
            │      EmailService.java
            │
            └── app/
                   Main.java
```

### 17. Review of OOP Concepts

| Concept                  | Example                                           |
| ------------------------ | ------------------------------------------------- |
| Class                    | `Account`                                         |
| Object                   | `new Account()`                                   |
| Encapsulation            | `private double balance;`                         |
| Inheritance              | `SavingsAccount extends Account`                  |
| Interface Implementation | `SMSService implements NotificationService`       |
| Association              | `Account` references `NotificationService`        |
| Containment              | `Account` maintains a list of listeners           |
| Method Overloading       | Same method name, different parameters            |
| Method Overriding        | Implementing interface methods with `@Override`   |
| Polymorphism             | `NotificationService service = new SMSService();` |

### 18. Key Interview Questions

1. Why does Java use interfaces instead of delegates?
2. What is the Publisher–Subscriber pattern?
3. Explain event handling in Java.
4. What is a listener?
5. Differentiate inheritance and implementation.
6. Explain all five SOLID principles with examples.
7. What is Dependency Injection?
8. What is a Marker Interface?
9. Why use `@Override`?
10. What is loose coupling?
11. What is containment?
12. How does polymorphism help in notification services?
13. Why should interfaces be small?
14. What are the benefits of constructor injection?
15. How would you add a WhatsApp notification without changing existing code?


### 19. Mentor Takeaways

* Use **interfaces** to achieve delegation and callback mechanisms in Java.
* Design systems using **publishers and listeners** for extensibility.
* Prefer **abstractions over concrete classes** to reduce coupling.
* Apply **SOLID principles** to build maintainable and scalable applications.
* Strengthen core OOP concepts—encapsulation, inheritance, polymorphism, association, and overriding—as they are commonly assessed in technical interviews.
* Organize projects with clear responsibilities and use **dependency injection** to keep components flexible and easy to test.
