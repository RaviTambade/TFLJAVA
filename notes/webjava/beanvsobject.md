
# Bean vs Object in Java

> *"Imagine you are running a software workshop. You have a blueprint for building a house. The blueprint tells you what the house should contain — rooms, doors, windows, and walls. But the blueprint itself is not a house."*
>
> *"In Java, a **class** is that blueprint. An **object** is the actual thing created from that blueprint. And when Spring takes responsibility for creating and managing that object, we call it a **Spring Bean**."*

Let me take you on a journey today — **from Class → Object → Bean → Spring Container** — so that you understand not only *what a Bean is*, but *why Spring needs Beans in the first place*.



## What Is a Class?

A **class** is a blueprint or template used to create objects. Think about an `Employee`.

```java
public class Employee {

    private int id;
    private String name;

    public void work() {
        System.out.println(name + " is working...");
    }
}
```

The class describes what an Employee should contain.

```text
             Employee Class
             -------------
             id
             name
             work()
```

But remember:

> *"A blueprint is not the building."*

Similarly:

> **A class is not an object.**

 
## Creating an Object

Now the developer wants to create an Employee.

```java
Employee emp = new Employee();
```

The `new` keyword creates an object in memory.

```text
             Employee Class
                    │
                    │ new Employee()
                    ▼
          ┌──────────────────┐
          │ Employee Object  │
          │                  │
          │ id               │
          │ name             │
          │ work()           │
          └──────────────────┘
                    ▲
                    │
                   emp
```

### Mentor Tip

> **Class = Blueprint**
> **Object = Actual instance created from the blueprint**


## So, What Is an Object?

An **object is a runtime instance of a class**. For example:

```java
Employee emp1 = new Employee();
Employee emp2 = new Employee();
Employee emp3 = new Employee();
```

We have:

```text
Employee Class
      │
      ├── emp1 → Object
      ├── emp2 → Object
      └── emp3 → Object
```

One class can create many objects.


# Now Enter Spring

So far, Java itself is creating objects.

```java
Employee emp = new Employee();
```

But enterprise applications can contain hundreds or thousands of objects. Imagine an Insurance application:

```text
Insurance Application
        │
        ├── CustomerService
        ├── PolicyService
        ├── PremiumService
        ├── ClaimService
        ├── PaymentService
        └── NotificationService
```

- Who should create all these objects?
- Who should connect them together?
- Who should manage their lifecycle?

This is where **Spring IoC Container** enters the story.

# Spring Container — The Object Factory

Think of Spring Container as a **smart software factory**. Instead of developers manually creating every object:

```java
new PolicyService();
new PremiumService();
new ClaimService();
new PaymentService();
```

we tell Spring:

> *"Spring, you take care of these objects."*

Spring creates and manages them.

```text
                 Spring Container
                       │
          ┌────────────┼────────────┐
          ▼            ▼            ▼
    PolicyService  ClaimService  PaymentService
       Object         Object         Object
          │             │             │
          ▼             ▼             ▼
        Bean           Bean           Bean
```

These managed objects are called:

# Spring Beans


## What Is a Spring Bean?

A **Spring Bean is an object whose creation and lifecycle are managed by the Spring IoC container.**

For example:

```java
@Component
public class PolicyService {

    public void purchasePolicy() {
        System.out.println("Policy purchased");
    }
}
```

The `@Component` annotation tells Spring:

> *"Please manage an object of this class."*

Spring creates the object.

```text
PolicyService.java
       │
       │ @Component
       ▼
Spring Container
       │
       │ creates
       ▼
PolicyService Object
       │
       ▼
Spring Bean
```


# Object vs Spring Bean

Let's compare them.

### Normal Java Object

```java
PolicyService service = new PolicyService();
```

Here:

```text
Developer
    │
    │ new
    ▼
PolicyService Object
```

The developer explicitly creates the object.

### Spring Bean

```java
@Component
public class PolicyService {
}
```

Spring creates and manages the object.

```text
Spring Container
       │
       │ creates
       ▼
PolicyService Object
       │
       ▼
Spring Bean
```

### Mentor Tip

> *"The important difference is not the object itself. The important difference is **who is responsible for managing that object**."*


# Is Every Object a Spring Bean?

**No.**

Consider:

```java
PolicyService service =
                    new PolicyService();
```

This is a normal Java object. Spring does not automatically manage every object created using `new`.

```text
new PolicyService()
       │
       ▼
Java Object
       │
       └── Not automatically a Spring Bean
```


# Is Every Spring Bean an Object?

**Yes.**

A Spring Bean is ultimately an object.The difference is:

```text
Object
   │
   │ managed by Spring
   ▼
Spring Bean
```

Therefore:

> **Every Spring Bean is an object, but every object is not necessarily a Spring Bean.**


# JavaBean vs Spring Bean

Now comes one of the most common interview questions. Many developers confuse:

```text
JavaBean
Spring Bean
```

They are not the same concept.


## JavaBean

A traditional **JavaBean** follows certain conventions. Typically:

* Private fields
* Public no-argument constructor
* Getter methods
* Setter methods

Example:

```java
public class Customer {

    private int id;
    private String name;

    public Customer() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
```

This follows JavaBean conventions.

  

# Spring Bean

A **Spring Bean** is about **Spring managing the object**. For example:

```java
@Service
public class CustomerService {

    public void register() {
        System.out.println("Customer registered");
    }
}
```

Spring detects the `@Service` component and creates a managed object.

```text
CustomerService Class
         │
         │ @Service
         ▼
 Spring IoC Container
         │
         │ creates
         ▼
 CustomerService Object
         │
         ▼
     Spring Bean
```


# Another Way to Create a Spring Bean

Spring also allows explicit Bean configuration.

```java
@Configuration
public class AppConfig {

    @Bean
    public CustomerService customerService() {
        return new CustomerService();
    }
}
```

Here:

```text
@Bean
  │
  ▼
Spring Container
  │
  │ creates
  ▼
CustomerService Object
  │
  ▼
Spring Bean
```

### Mentor Tip

> `@Component`, `@Service`, `@Repository`, and `@Controller` can allow Spring to discover components, while `@Bean` explicitly declares an object to be managed by Spring.

# The Real Power — Dependency Injection

Now imagine:

```text
PolicyService
      │
      │ needs
      ▼
PremiumService
```

Without Spring:

```java
PremiumService premiumService = new PremiumService();
PolicyService policyService = new PolicyService(premiumService);
```

The developer creates and connects objects manually. Spring changes the approach.

```java
@Service
public class PolicyService {

    private final PremiumService premiumService;

    public PolicyService(PremiumService premiumService) {
        this.premiumService = premiumService;
    }
}
```

Spring sees:

```text
PolicyService
      │
      │ depends on
      ▼
PremiumService
```

and supplies the required Bean.

```text
              Spring Container
                     │
          ┌──────────┴──────────┐
          ▼                     ▼
   PremiumService          PolicyService
       Bean                    Bean
          │                     │
          └──── dependency ─────┘
```

This is the foundation of:

#  IoC — Inversion of Control

Instead of saying:

> *"I will create all the objects."*

the developer says:

> **"Spring, you manage the objects. I will focus on business logic."**


#  Let's Build the Mental Model

Don't memorize definitions. Visualize this:

```text
              CLASS
          "Blueprint"
                │
                │ new
                ▼
             OBJECT
       "Runtime Instance"
                │
                │ Spring manages it
                ▼
          SPRING BEAN
                │
                │ injected into
                ▼
        OTHER SPRING BEANS
```

This is the story behind Spring's Dependency Injection.


# Mentor's Quick Comparison

| Concept                  | Meaning                                     |
| ------------------------ | ------------------------------------------- |
| **Class**                | Blueprint/template                          |
| **Object**               | Runtime instance of a class                 |
| **JavaBean**             | Object/class following JavaBean conventions |
| **Spring Bean**          | Object managed by Spring Container          |
| **IoC Container**        | Creates and manages Spring Beans            |
| **Dependency Injection** | Spring supplies required dependencies       |



# Interview Time

### Q1. What is an Object?

> An object is a runtime instance of a class.

### Q2. What is a Spring Bean?

> A Spring Bean is an object whose creation and lifecycle are managed by the Spring IoC container.

### Q3. Is every Java object a Spring Bean?

> No. Objects created directly using `new` are not automatically Spring Beans.

### Q4. Is every Spring Bean an object?

> Yes. A Spring Bean is an object managed by the Spring container.

### Q5. What is the difference between JavaBean and Spring Bean?

> A JavaBean follows standard JavaBean conventions such as private properties, a no-argument constructor, and getters/setters. A Spring Bean is an object managed by the Spring IoC container.

# Summary — The Transflower Way

> *"Students often start Spring by memorizing annotations. But a good developer starts by understanding objects."*

First understand:

```text
Class
  ↓
Object
```

Then understand:

```text
Object
  ↓
Spring manages it
  ↓
Spring Bean
```

Then understand:

```text
Spring Bean
  ↓
Dependency Injection
  ↓
IoC
  ↓
Loosely Coupled Application
```

### 🌱 Final Mentor Mantra

> **"Class defines the object.
> Object represents the runtime instance.
> Spring Bean is the object managed by Spring.
> IoC manages the Beans.
> Dependency Injection connects the Beans."**

Once this foundation is clear, concepts such as **`@Component`, `@Service`, `@Repository`, `@Controller`, `@Bean`, IoC, and Dependency Injection** become much easier to learn.

**Don't just learn Spring annotations. Understand the object story behind them.** 🌱

This follows the structure and teaching rhythm of your uploaded **Collection Framework** material, including analogy → concept → code → mentor tip → practical flow → interview questions → summary. 