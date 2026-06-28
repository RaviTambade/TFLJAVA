# Mock Java Full Stack Interview

## Interviewer: Ravi Tambade

**Good morning, Sahil. Please introduce yourself.**

---

## Candidate: Sahil Kamble

Good morning, Sir.

My name is Sahil Kamble. I have been working as a Java Full Stack Developer Intern at Transflower since June 2025.

During my internship, I have worked with Java, Spring Boot, REST APIs, MySQL, React, GitHub, and Maven. I have also worked on enterprise application development using layered architecture.

The major project I have contributed to is **TFLComentor**, a mentor-driven skill evaluation platform. Through this project I learned backend development using Spring Boot, frontend development using React, database design, REST API development, team collaboration, and Git version control.

I am looking for an opportunity where I can continue learning while contributing as a Java Full Stack Developer.

---

# Project Discussion

### Ravi

Can you explain your project?

### Sahil

Our project is called **TFLComentor**.

It is an AI-assisted mentor-driven learning platform that evaluates students based on their skill gaps and helps them follow personalized learning paths.

The application supports four users:

* Student
* Mentor
* Admin
* Employer

The application currently contains seven major modules.

* Skill Taxonomy
* Evaluation Content Management
* Evaluation Services
* Assessment Orchestrator
* Insight Core
* Growth Engine
* Membership & User Management

Each module has a separate responsibility.

---

### Ravi

Which modules did you work on?

### Sahil

I worked mainly on

* Skill Taxonomy
* Evaluation Content Management

I developed REST APIs, business logic, repository classes and database tables for managing runtimes, concepts, question banks and MCQ options.

I also developed around 15 React components for filtering questions based on question type, status and IDs.

---

### Ravi

Which technologies did you use?

### Sahil

Backend

* Java
* Spring Boot
* Spring Data JPA
* REST APIs

Frontend

* React
* JavaScript
* HTML
* CSS

Database

* MySQL

Tools

* GitHub
* Maven
* Postman

---

# Architecture Discussion

### Ravi

What architecture did you follow?

### Sahil

Initially we planned Microservices.

But since the project was in its initial stage, we adopted a **Modular Monolithic Architecture**.

Inside every module we followed Layered Architecture.

```
Controller

↓

Service

↓

Repository

↓

Database
```

Each layer has a specific responsibility which makes the application easy to maintain and test.

---

### Ravi

Why didn't you continue with Microservices?

### Sahil

Microservices introduced deployment complexity, API communication, service discovery and DevOps overhead.

Since our project was only around 20% complete, a Modular Monolith was easier to develop and maintain.

Later we can extract modules into independent microservices.

---

# Database

### Ravi

Tell me about your database.

### Sahil

Initially our ER diagram contained around 108 tables.

During database optimization we merged similar entities.

Currently we have around 45 production tables.

I mainly worked on

* Runtime
* Concept
* Question Bank
* MCQ Options

tables.

---

### Ravi

What challenge did you face?

### Sahil

Initially the Question Bank required joining many tables.

```
Question

↓

Concept

↓

Runtime

↓

Framework

↓

Technology
```

The joins became expensive.

To improve perfogit rmance, we denormalized some independent tables by storing required information directly in the Question Bank table.

This reduced query complexity and improved API response time.

---

# Spring Boot

### Ravi

What is Spring Boot?

### Sahil

Spring Boot is built on top of the Spring Framework.

It simplifies enterprise application development by providing

* Auto Configuration
* Embedded Tomcat
* Starter Dependencies
* Annotation-based configuration

so developers don't need XML configuration.

---

### Ravi

Which annotations have you used?

### Sahil

I frequently used

* @RestController
* @RequestMapping
* @GetMapping
* @PostMapping
* @Autowired
* @Service
* @Repository
* @Entity
* @Id
* @GeneratedValue

---

# REST APIs

### Ravi

Explain the flow of a REST request.

### Sahil

```
React UI

↓

HTTP Request

↓

Controller

↓

Service

↓

Repository

↓

MySQL

↓

JSON Response

↓

React UI
```

---

# React

### Ravi

What React work did you do?

### Sahil

I developed approximately 15 reusable components.

Some examples are

* Question List
* Question Filter
* Search Bar
* Runtime Dropdown
* Status Filter
* Question Details

The components communicate with Spring Boot REST APIs using Fetch API.

---

# Java

### Ravi

Which OOP concepts did you implement?

### Sahil

All four.

Encapsulation

Private fields with getters and setters.

Abstraction

Repository interfaces.

Inheritance

Common entity classes.

Polymorphism

Method overriding and interface implementation.

---

### Ravi

What replaced finalize()?

### Sahil

The finalize() method is deprecated.

Modern Java uses

* AutoCloseable
* try-with-resources

for automatic resource management.

---

# JDBC

### Ravi

Difference between PreparedStatement and CallableStatement?

### Sahil

PreparedStatement

* Executes parameterized SQL queries
* Prevents SQL Injection

CallableStatement

* Executes Stored Procedures inside the database.

---

# Java Concurrency

### Ravi

How does Java perform asynchronous programming?

### Sahil

Using

* CompletableFuture

Internally it uses

ForkJoinPool

which assigns tasks to worker threads without manually creating Thread objects.

---

# Git

### Ravi

How did your team use Git?

### Sahil

Our workflow was

```
Pull latest code

↓

Create feature

↓

Commit

↓

Push

↓

Create Pull Request

↓

Code Review

↓

Merge
```

Git helped us collaborate and resolve conflicts efficiently.

---

# CI/CD

### Ravi

What is CI/CD?

### Sahil

CI means Continuous Integration.

Whenever developers push code to GitHub, automated builds and tests are triggered.

CD means Continuous Deployment or Delivery.

Once all checks pass, the latest version is deployed automatically.

---

# HR Round

### Ravi

What was the biggest lesson you learned?

### Sahil

Working in a team.

I learned

* Code reviews
* Git collaboration
* Requirement discussions
* Meeting deadlines
* Debugging production issues

I also understood that software engineering is much more than writing code.

---

### Ravi

Why should we hire you?

### Sahil

I have practical experience building enterprise applications using Java, Spring Boot, React, REST APIs, MySQL and Git.

I enjoy learning new technologies, solving problems and working collaboratively. I believe I can contribute effectively while continuously improving my skills.

---

### Ravi

Do you have any questions for us?

### Sahil

Yes, Sir.

1. What technologies does your team currently use?
2. What kind of training is provided to freshers?
3. What projects would I initially work on?
4. What growth opportunities are available for Java Full Stack Developers?

---

## Interviewer's Assessment

**Strengths**

* Clear communication
* Good project understanding
* Strong Java and Spring Boot fundamentals
* Good knowledge of layered architecture
* Practical React experience
* Team collaboration using Git
* Sound database concepts
* Understanding of CI/CD and enterprise development

**Areas to Improve**

* Gain deeper knowledge of Spring Security and JWT
* Learn Microservices in depth
* Explore Docker and Kubernetes
* Improve knowledge of Design Patterns
* Strengthen Data Structures and Algorithms (DSA)
* Practice system design for scalable applications

This mock interview closely resembles a **45–60 minute Java Full Stack Developer interview** conducted by companies hiring freshers or interns, combining project discussion, technical concepts, coding knowledge, and behavioral questions.
