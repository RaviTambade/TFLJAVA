# Spring Boot Application

> "Learning Spring Boot is not about memorizing annotations.
> It is about understanding how a Java program becomes a real-world, multi-user web application."


## 1. From Standalone Apps to Web Apps

### Mentor Story

> "Till now, whatever applications you built in Java, C, or Python were mostly **standalone applications**. You wrote a `main()` method, ran the program on your computer, and the output appeared on your machine."

For example:

```java
public class Main {

    public static void main(String[] args) {

        System.out.println("Library Management System");

    }
}
```

Now ask the students:

> **"Who can use this application?"**

Usually the answer is:

> "The person who has the application installed."

Exactly.

That means our application is running locally.

Imagine we build:

```text
Library Management System
```

and install it on:

```text
Computer A
Computer B
Computer C
Computer D
```

We may end up with:

```text
Librarian A → Application A → Database A

Librarian B → Application B → Database B

Librarian C → Application C → Database C
```

Now ask:

> **"Are all librarians working with the same data?"**

Not necessarily. This is one of the limitations of traditional standalone applications.



## 2. Why Web Applications?

Now ask the students:

> **"What if we keep only ONE copy of the application?"**

Instead of installing the application on every computer, we put it on a server.

```text
                         SERVER
              ┌─────────────────────────┐
              │                         │
              │    Java Application     │
              │                         │
              │    Web Server           │
              │                         │
              │    Database             │
              │                         │
              └─────────────────────────┘
                    ▲      ▲      ▲
                    │      │      │
                   HTTP   HTTP   HTTP
                    │      │      │
                    ▼      ▼      ▼
                  User   User   User
```

Now multiple users can access the same application.

The application becomes:

> **A multi-user Web Application.**

The basic idea is:

```text
Client
   ↓
HTTP Request
   ↓
Web Application
   ↓
Business Logic
   ↓
Database
   ↓
HTTP Response
```

The source material describes this transition as centralizing the application on a server so multiple users can access shared application logic and data over HTTP. 



## 3. Where Does Spring Boot Fit?

Now I ask:

> **"If Java can create applications, why do we need Spring Boot?"**

Earlier, Java developers could create web applications using:

```text
Servlet
   ↓
doGet()
doPost()
```

It works.

But developers have to deal with a lot of infrastructure and configuration.

Modern applications need:

```text
Web Server
HTTP
REST APIs
Database
Security
Dependency Injection
Configuration
Testing
Logging
Monitoring
```

This is where **Spring Boot** comes in.

> **Spring Boot helps Java developers build production-ready applications quickly with minimal configuration.**

It gives us:

```text
Project Structure
       +
Starter Dependencies
       +
Auto Configuration
       +
Embedded Server
       +
REST API Support
       +
Database Integration
```

Instead of spending time configuring infrastructure, we focus on:

> **Business Logic**

The source positions Spring Boot as the layer that hides much of the servlet complexity and simplifies REST API development using annotations such as `@RestController`, `@GetMapping`, and `@PostMapping`. 


## 4. What Is Spring Boot?

### Mentor says:

> "Spring Boot is not a replacement for Java.
> It is a framework built on top of the Spring ecosystem that helps us build Java applications faster."

Think of the relationship like this:

```text
Java
  ↓
Programming Language
  ↓
Spring Framework
  ↓
Application Infrastructure
  ↓
Spring Boot
  ↓
Simplified Application Development
```

Spring Boot provides sensible defaults so that developers don't have to configure everything manually.


## 5. Spring Boot as a "Team of Helpers"

### Mentor Story

Imagine you are running a large company. You don't personally do every job. You have different departments:

```text
Receptionist
Database Team
Security Team
Operations Team
Testing Team
```

Spring Boot works in a similar way. Think of Spring Boot as:

> **A team of technical assistants.**

You say:

```text
"I need a Web API."
```

Spring Boot helps configure the web infrastructure. You say:

```text
"I need database access."
```

Spring Data JPA helps with data access. You say:

```text
"I need security."
```

Spring Security provides security infrastructure. You say:

```text
"I need testing."
```

Spring Boot provides testing support through its ecosystem. The source uses this “framework of helpers” idea to explain how Spring Boot removes much of the setup work. 



# 6. Why Spring Initializr?

Now ask:

> **"When starting a new Maven project, what do we normally have to create?"**

Students may answer:

```text
pom.xml
src
main
test
packages
dependencies
configuration
```

That setup takes time. So Spring provides:

# Spring Initializr

Think of it as:

> **A project generator.**

It creates the basic Spring Boot project for us.


## 6.1 Pizza Analogy

### Mentor says:

> "Imagine you want to make pizza."

Do you want to start by:

```text
Growing wheat
      ↓
Making flour
      ↓
Making dough
      ↓
Preparing the oven
```

No.

You want:

```text
Ready Pizza Base
       +
Your Toppings
       ↓
Pizza
```

Spring Initializr is the:

```text
Ready Project Base
```

Your application code becomes:

```text
Your Business Logic
       +
Spring Boot Project
       ↓
Spring Boot Application
```

The uploaded source uses the same “ready-made base” idea for Spring Initializr. 

 

# 6.2 Create a Spring Boot Project

Open:

`start.spring.io`

Select:

```text
Project       → Maven
Language      → Java
Packaging     → Jar
Java          → Your JDK version
```

Add dependencies:

```text
Spring Web
Spring Data JPA
MySQL Driver
```

Then:

```text
Generate
   ↓
Download ZIP
   ↓
Extract
   ↓
Open in IDE
```

The source recommends this setup for the initial REST API and database exercise. 

 

# 7. Import the Project into the IDE

Open the generated project in:

```text
IntelliJ IDEA
Eclipse
Visual Studio Code
```

You will find:

```text
pom.xml
src/
 ├── main/
 │    └── java/
 │
 └── test/
```

And an application class such as:

```java
@SpringBootApplication
public class DemoApplication {

    public static void main(String[] args) {

        SpringApplication.run(
            DemoApplication.class,
            args
        );
    }
}
```

Now ask:

> **"What does `main()` normally do?"**

Student:

> "It starts the Java application."

Correct.

But something interesting happens here.

```java
SpringApplication.run(
    DemoApplication.class,
    args
);
```

Spring Boot starts the application and its web infrastructure. The source specifically highlights this as the entry point that starts the application and embedded Tomcat server. 

 

# 8. Embedded Tomcat

Traditional thinking:

```text
Install Server
      ↓
Configure Server
      ↓
Deploy Application
      ↓
Start Server
```

Spring Boot simplifies this.

```text
Spring Boot Application
          ↓
Embedded Tomcat
          ↓
Application Running
```

So we can run our application directly. The developer doesn't have to separately install and configure an external web server for the basic application.


# 9. First REST API

Now tell the students:

> "Let's create our first API."

Create:

```java
@RestController
public class HelloController {

    @GetMapping("/hello")
    public String sayHello() {

        return "Hello, Spring Boot!";
    }
}
```

Start the application.

Open:

```text
http://localhost:8080/hello
```

The browser sends:

```http
GET /hello
```

Spring Boot finds:

```java
@GetMapping("/hello")
```

and executes:

```java
sayHello()
```

Response:

```text
Hello, Spring Boot!
```

The source demonstrates this exact first-API exercise. 


# 10. Mentor's Question

Now stop. Ask:  **"Where did we write `doGet()`?"**
Students: "We didn't."
Ask:**"Where did we configure Tomcat manually?"**
Students: "We didn't."
Ask: **"Where did we write servlet configuration?"**
Students: "We didn't."
Then say: **"That is the power of abstraction."**
Spring Boot is taking care of infrastructure while we focus on application behavior.


# 11. What Is a REST API?

A REST API allows different applications to communicate using HTTP.

For example:

```text
React
Angular
Mobile App
Postman
Another Backend
Third-party Application
```

can communicate with:

```text
Spring Boot API
```

using:

```http
GET
POST
PUT
DELETE
```

For example:

```http
GET    /api/products
GET    /api/products/101
POST   /api/products
PUT    /api/products/101
DELETE /api/products/101
```

The API becomes the communication bridge.

```text
React
   │
   │ HTTP
   ▼
Spring Boot API
   │
   ▼
Database
```

# 12. Spring Boot API — Online Shopping Analogy

### Mentor says: "Think about an online shopping system."

When you click:

```text
Buy Product
```

you don't directly access the warehouse. You don't directly access:

```text
Inventory Database
Payment Database
Order Database
```

Instead:

```text
Customer
   ↓
Client Application
   ↓
API
   ↓
Business Logic
   ↓
Database / Services
   ↓
Response
```

The same principle applies to Spring Boot.

```text
Client
   ↓
Controller
   ↓
Service
   ↓
Repository
   ↓
Database
```

Response:

```text
Database
   ↓
Repository
   ↓
Service
   ↓
Controller
   ↓
JSON
   ↓
Client
```

The source uses this online-shopping analogy to explain how the API hides internal processing from the client. 

# 13. Layers in a Spring Boot Application

Now we move from:

```text
Hello World
```

to:

```text
Real Application
```

Suppose we build:

# Insurance Management System

We have:

```text
Customer
Policy
Premium
Claim
Payment
```

We should not put everything inside the Controller.

Instead:

```text
                    Client
                      │
                      ▼
              ┌──────────────┐
              │  Controller  │
              └──────┬───────┘
                     │
                     ▼
              ┌──────────────┐
              │   Service    │
              └──────┬───────┘
                     │
                     ▼
              ┌──────────────┐
              │  Repository  │
              └──────┬───────┘
                     │
                     ▼
                  Database
```


# 14. Controller — "The Receptionist"

### Mentor Story

Imagine an insurance office. A customer enters: "I want to purchase a policy."The receptionist receives the request. The receptionist does not:

```text
Calculate premium
Query database
Process payment
Validate policy
```

The receptionist routes the request. That is the Controller.

```java
@RestController
@RequestMapping("/policies")
public class PolicyController {

}
```

### Mentor Rule

> **Controller receives the request.**


# 15. Service — "The Business Expert"

Now the request goes to the Service. Suppose:

```text
Purchase Policy
```

requires:

```text
Customer exists?
       ↓
Policy available?
       ↓
Customer eligible?
       ↓
Calculate premium
       ↓
Create policy
       ↓
Save policy
```

That is business logic.

So we write:

```java
@Service
public class PolicyService {

    public Policy purchasePolicy(...) {

        // Business rules

        return policy;
    }
}
```

### Mentor Rule

> **Service contains business logic.**



# 16. Repository — "The Database Specialist"

Now suppose the Service needs customer information. Traditional JDBC approach:

```text
Connection
      ↓
PreparedStatement
      ↓
SQL
      ↓
ResultSet
      ↓
Object
```

Spring Data JPA simplifies this.

We define:

```java
public interface ProductRepository
        extends JpaRepository<Product, Integer> {

}
```

Now Spring Data provides common operations such as:

```text
findAll()
findById()
save()
deleteById()
```

The source explicitly uses this repository approach to demonstrate how common CRUD operations can be obtained without manually writing the JDBC plumbing. 


# 17. Entity — "Java Representation of Data"

Suppose our database has:

```text
products
----------------
id
name
price
```

We create:

```java
@Entity
public class Product {

    @Id
    @GeneratedValue
    private int id;

    private String name;

    private double price;
}
```

Think:

```text
Database Table
      ↕
Java Entity
```

The Entity represents persistent application data. The source introduces this same `Product` entity example before moving to repository operations. 


# 18. Replace JDBC with Spring Data JPA

### Earlier:

```text
Connection
      ↓
PreparedStatement
      ↓
SQL
      ↓
ResultSet
      ↓
Mapping
      ↓
Close Connection
```

### Now:

```java
repository.findAll();
```

or:

```java
repository.findById(id);
```

or:

```java
repository.save(product);
```

or:

```java
repository.deleteById(id);
```

The important lesson is: **Spring Boot reduces boilerplate.** But don't misunderstand. Spring Boot did not remove the database. You still need to understand:

```text
SQL
Tables
Primary Keys
Foreign Keys
Relationships
Indexes
Transactions
Query Performance
```

A framework makes you productive. It does not make database knowledge unnecessary.


# 19. First CRUD API

Now let's build a Product API.

```java
@RestController
@RequestMapping("/products")
public class ProductController {

    @Autowired
    private ProductRepository repo;

    @GetMapping
    public List<Product> getAllProducts() {

        return repo.findAll();
    }

    @PostMapping
    public Product addProduct(
            @RequestBody Product product) {

        return repo.save(product);
    }
}
```

Now we have:

```http
GET  /products
POST /products
```

We can extend this to:

```http
GET    /products/{id}
PUT    /products/{id}
DELETE /products/{id}
```

The source demonstrates this same repository-backed CRUD pattern. 

# 20. Complete Request Flow

This is the picture I would draw on the classroom board:

```text
                    CLIENT
               React / Angular
                      │
                      │ HTTP
                      ▼
              ┌──────────────┐
              │  Controller  │
              └──────┬───────┘
                     │
                     ▼
              ┌──────────────┐
              │   Service    │
              │Business Logic│
              └──────┬───────┘
                     │
                     ▼
              ┌──────────────┐
              │  Repository  │
              │ Data Access  │
              └──────┬───────┘
                     │
                     ▼
              ┌──────────────┐
              │ JPA/Hibernate│
              └──────┬───────┘
                     │
                     ▼
                  MySQL
```

Response:

```text
MySQL
  ↓
JPA/Hibernate
  ↓
Repository
  ↓
Service
  ↓
Controller
  ↓
JSON
  ↓
React / Angular
```

The source summarizes the request path as client → Controller → Service → Repository → Database → JSON response. 


# 21. Spring Boot vs JDBC

Now ask students:

> **"What changed?"**

### JDBC Approach

```text
Java
 ↓
JDBC
 ↓
Connection
 ↓
PreparedStatement
 ↓
SQL
 ↓
ResultSet
 ↓
Mapping
```

### Spring Boot + JPA

```text
Java
 ↓
Spring Boot
 ↓
Repository
 ↓
JPA / Hibernate
 ↓
Database
```

The source makes this exact comparison: JDBC requires manual connection, SQL, and result-set handling, while Spring Data JPA allows developers to define repository interfaces. 


# 🔹 22. Mentor's Simple Analogy

### JDBC

> **"Writing every letter yourself with a pen."**

You control every small detail.

### Spring Boot

> **"Sending a WhatsApp voice message."**

You express what you want, and the infrastructure handles much of the mechanics.

But remember:

> **"Abstraction saves effort, but understanding the underlying technology makes you a stronger engineer."**


# 23. Hands-on Exercise

Now we stop talking. We build.

## Product Management API

Create:

```text
Product
```

with:

```text
id
name
price
quantity
category
```

 

### Step 1 — Create Spring Boot Project

Dependencies:

```text
Spring Web
Spring Data JPA
MySQL Driver
```

  

### Step 2 — Create Entity

```java
@Entity
public class Product {

    @Id
    @GeneratedValue
    private int id;

    private String name;

    private double price;

    private int quantity;

    private String category;
}
```


### Step 3 — Create Repository

```java
public interface ProductRepository
        extends JpaRepository<Product, Integer> {

}
```


### Step 4 — Create Controller

Implement:

```http
GET    /products
GET    /products/{id}
POST   /products
PUT    /products/{id}
DELETE /products/{id}
```


### Step 5 — Run the Application

Start Spring Boot.

```text
Application
    ↓
Embedded Tomcat
    ↓
Port 8080
```


### Step 6 — Test with Postman

Test:

```http
GET http://localhost:8080/products
```

Then:

```http
POST http://localhost:8080/products
```

with JSON:

```json
{
    "name": "Laptop",
    "price": 75000,
    "quantity": 10,
    "category": "Electronics"
}
```


### Step 7 — Compare with JDBC

Now ask students:

> **"How much code did we avoid writing?"**

Compare:

```text
JDBC
Connection
PreparedStatement
SQL
ResultSet
Mapping
Closing resources
```

with:

```text
Spring Data JPA

repository.save(product);
```

This comparison is specifically recommended in the source as a hands-on learning exercise. 


# 🔹 24. The Learning Journey

Now connect everything.

```text
                    CORE JAVA
                        │
                        ▼
                 Console Application
                        │
                        ▼
                  Web Application
                        │
                        ▼
                      HTTP
                        │
                        ▼
                  REST API
                        │
                        ▼
                  Spring Boot
                        │
            ┌───────────┼───────────┐
            ▼           ▼           ▼
       Controller     Service    Repository
            │           │           │
            └───────────┼───────────┘
                        │
                        ▼
                   JPA/Hibernate
                        │
                        ▼
                     MySQL
                        │
                        ▼
                Real-world System
```

Now the student can see the journey.

# 25. From Product API to Enterprise Application

Don't stop at:

```text
GET /products
```

Take the same architecture into a real business domain.

For example:

```text
Insurance Management System
```

Then build:

```text
Customer API
Policy API
Premium API
Claim API
Payment API
Authentication API
```

Architecture:

```text
                  React / Angular
                         │
                         ▼
                 Spring Boot API
                         │
          ┌──────────────┼──────────────┐
          ▼              ▼              ▼
      Customer         Policy         Claims
       Service         Service        Service
          │              │              │
          ▼              ▼              ▼
      Repository      Repository     Repository
          │              │              │
          └──────────────┼──────────────┘
                         ▼
                       MySQL
```

Now we are no longer learning annotations. We are building a **business application**.


# 26. Mentor's Questions

Before ending the session, ask the students:

### Question 1

> **What is Spring Boot?**

Expected understanding:

> A framework that simplifies development of production-ready Java applications.

### Question 2

> **Why Spring Initializr?**

Answer:

> To quickly generate a ready-to-use Spring Boot project.

### Question 3

> **What does `@RestController` do?**

Answer:

> Identifies a class that handles REST/web requests and produces responses.

### Question 4

> **What does `@GetMapping` do?**

Answer:

> Maps an HTTP GET request to a Java method.

### Question 5

> **Why Service Layer?**

Answer:

> To keep business logic separate from HTTP handling.

### Question 6

> **Why Repository?**

Answer:

> To isolate data-access operations.

### Question 7

> **Why JPA?**

Answer:

> To simplify object-relational database access.

### Question 8

> **What happens when React calls `/products`?**

Expected flow:

```text
React
 ↓
HTTP GET
 ↓
Controller
 ↓
Service
 ↓
Repository
 ↓
JPA/Hibernate
 ↓
MySQL
 ↓
JSON Response
 ↓
React
```

# 27. Mentor's Final Mantra

Write this on the classroom board:

```text
Java
  ↓
"I can write programs."

Spring
  ↓
"I can structure applications."

Spring Boot
  ↓
"I can quickly build Web APIs."

Spring Data JPA
  ↓
"I can work with databases."

Spring Security
  ↓
"I can secure applications."

Testing
  ↓
"I can verify applications."

Docker + Cloud
  ↓
"I can deploy applications."
```

And finally:

```text
              LEARN
                ↓
             BUILD
                ↓
              TEST
                ↓
             DEPLOY
                ↓
             IMPROVE
                ↓
          REAL ENGINEERING
```

> **Transflower Mentor Mantra:**
>
> **"Don't learn Spring Boot to memorize Spring Boot.**
>
> **Learn Spring Boot to solve business problems using Java."**

Start with:

```text
Hello API
   ↓
Product CRUD
   ↓
Database Integration
   ↓
Authentication
   ↓
Insurance Application
   ↓
Testing
   ↓
Docker
   ↓
Cloud Deployment
```

Every feature becomes a classroom.

Every bug becomes a lesson.

Every project becomes evidence of your engineering ability.

> **A Java programmer writes code.
> A Spring Boot developer builds services.
> A professional engineer builds, tests, deploys, and maintains business systems.**