# Problem Statement


## Mentor Note for Students

Dear TAP Intern,

You have already learned JavaScript, Node.js, and Express.
Now it is time to strengthen your core software engineering foundation using Java.

This project is intentionally designed as a simple Console Menu Driven Application so that you can deeply understand how real backend systems work internally before using frameworks like Spring Boot.

In this project, you are not only writing Java code.
You are learning:

* Object Oriented Programming
* Collections Framework
* Session Management
* Authentication Flow
* JSON Serialization
* Layered Architecture
* Software Design Thinking

Your task is to build a small utility for:

* Students
* Subject Matter Experts (SME)

The application should allow:

* Login
* Session handling
* Subject management
* Assignment handling
* Answer submission
* Logout functionality

Data should be stored using JSON files instead of databases so that you can understand serialization and object persistence clearly.

While building this project, apply:

* Classes and Objects
* Encapsulation
* Inheritance
* Polymorphism
* Abstraction
* Interfaces
* Collections like ArrayList and HashMap
* Exception Handling

Do not focus only on completing menus.
Focus on understanding:

* Why classes are created
* Why services are separated
* Why collections are used
* How session state is maintained
* How objects are converted into JSON

This project is a bridge between:
“Learning Java Syntax”
and
“Thinking Like a Software Engineer.”

Once you complete this project properly, concepts of:

* Spring Boot
* REST APIs
* Authentication
* Microservices
* Backend Architecture

will become much easier to understand.

Build step by step.
Think practically.
Design carefully.
Write clean code.
And most importantly:
understand the purpose behind every class and every object.

– Mentor

## “TFL Learning Console Portal”

A Menu-Driven Java Application for Subject Matter Experts (SME) and Students

Your students already know JavaScript, Node.js, and Express.
Now the goal is to help them transition into:

* Core Java
* Object-Oriented Programming
* Collections Framework
* Session Management
* JSON Serialization
* Console-based Application Design
* Clean Architecture Thinking

This project should feel like a **mini backend system without UI**.


# Project Vision

Build a simple console-based learning portal where:

* Students can register and login
* SMEs can login and manage subjects
* SMEs can add assignments/questions
* Students can view subjects and submit answers
* Session remains active until logout
* Data is stored using JSON serialization

This project helps students understand how backend systems internally work before learning frameworks like Spring Boot.

# Real World Analogy

Before learning:

* REST API
* Spring Boot
* Hibernate
* JWT
* Database

Students must understand:

* Objects
* Memory
* State
* Collections
* Serialization
* Authentication flow
* Session lifecycle

This project acts like:

> “Building your own mini learning platform engine in Core Java”

# Functional Requirements

## Roles

### Student

* Register
* Login
* View subjects
* View assignments
* Submit answers
* Logout

### Subject Matter Expert (SME)

* Login
* Create subjects
* Add assignments/problems
* View student submissions
* Logout


# Major Learning Concepts

| Concept            | Applied In                       |
| ------------------ | -------------------------------- |
| Class & Object     | Student, SME, Subject            |
| Encapsulation      | Private fields + getters/setters |
| Constructor        | Creating domain objects          |
| Inheritance        | User → Student/SME               |
| Polymorphism       | User operations                  |
| Abstraction        | Service interfaces               |
| Collections        | ArrayList, HashMap               |
| Composition        | Subject contains Assignments     |
| Exception Handling | Invalid login/menu               |
| Session Management | Logged-in user                   |
| JSON Serialization | Save/load data                   |
| File Handling      | JSON files                       |
| OOP Design         | Service Layer                    |


# Suggested Project Structure

```text
src/
│
├── model/
│   ├── User.java
│   ├── Student.java
│   ├── SME.java
│   ├── Subject.java
│   ├── Assignment.java
│   ├── Submission.java
│
├── service/
│   ├── AuthService.java
│   ├── SubjectService.java
│   ├── AssignmentService.java
│   ├── SubmissionService.java
│
├── repository/
│   ├── UserRepository.java
│   ├── SubjectRepository.java
│
├── session/
│   └── SessionManager.java
│
├── util/
│   ├── JsonUtil.java
│   ├── FileUtil.java
│
└── app/
    └── Main.java
```


# Step-by-Step Learning Roadmap

# Phase 1 — Understanding Classes and Objects

## Step 1: Create Base User Class

```java
public class User {

    protected int id;
    protected String name;
    protected String email;
    protected String password;

    public User() {
    }

    public User(int id, String name, String email, String password) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.password = password;
    }
}
```

# OOP Concept Applied

## Encapsulation

Fields are grouped inside object.

## Constructor

Object initialization.



# Phase 2 — Inheritance

## Step 2: Create Student and SME

```java
public class Student extends User {

    private String collegeName;

    public Student(int id, String name, String email,
                   String password, String collegeName) {

        super(id, name, email, password);
        this.collegeName = collegeName;
    }
}
```

```java
public class SME extends User {

    private String expertise;

    public SME(int id, String name, String email,
               String password, String expertise) {

        super(id, name, email, password);
        this.expertise = expertise;
    }
}
```

# OOP Concept Applied

## Inheritance

```text
User
 ├── Student
 └── SME
```

Students understand:

* code reuse
* parent-child relation
* IS-A relationship

# Phase 3 — Composition

## Step 3: Create Subject Class

```java
import java.util.ArrayList;
import java.util.List;

public class Subject {

    private int id;
    private String title;

    private List<Assignment> assignments;

    public Subject() {
        assignments = new ArrayList<>();
    }
}
```

# OOP Concept Applied

## Composition

A Subject HAS assignments.

---

# Phase 4 — Collections Framework

## Step 4: Store Users in Collection

```java
List<User> users = new ArrayList<>();
```

## Store Subjects

```java
List<Subject> subjects = new ArrayList<>();
```

## Store Sessions

```java
Map<String, User> activeSessions = new HashMap<>();
```

# Collection Concepts Students Learn

| Collection     | Use                      |
| -------------- | ------------------------ |
| ArrayList      | Dynamic storage          |
| HashMap        | Session/token management |
| List Interface | Loose coupling           |

# Phase 5 — Authentication System

## Step 5: Login Logic

```java
public User login(String email, String password) {

    for(User user : users) {

        if(user.getEmail().equals(email)
                && user.getPassword().equals(password)) {

            return user;
        }
    }

    return null;
}
```

# OOP Concept Applied

## Abstraction through Service Layer

```text
Main Menu
   ↓
AuthService
   ↓
Repository
```

# Phase 6 — Session Management

## Step 6: Create SessionManager

```java
import java.util.UUID;

public class SessionManager {

    private static String currentSession;

    public static String createSession(User user) {

        currentSession = UUID.randomUUID().toString();

        return currentSession;
    }
}
```


# What Students Learn

| Topic          | Explanation          |
| -------------- | -------------------- |
| Session        | Logged-in user state |
| UUID           | Unique session token |
| Authentication | Verify identity      |
| Authorization  | Role based access    |


# Phase 7 — Menu Driven Application

## Step 7: Main Menu

```java
1. Register Student
2. Login
3. SME Login
4. Exit
```

# Student Menu

```text
1. View Subjects
2. View Assignments
3. Submit Answer
4. Logout
```

# SME Menu

```text
1. Add Subject
2. Add Assignment
3. View Submissions
4. Logout
```

# Phase 8 — JSON Serialization

## Add Gson Library

Students learn:

* converting objects to JSON
* saving data permanently


# Example JSON Save

```java
Gson gson = new Gson();

String json = gson.toJson(users);
```


# Save to File

```java
FileWriter writer = new FileWriter("users.json");

writer.write(json);

writer.close();
```


# Load JSON

```java
BufferedReader br =
        new BufferedReader(new FileReader("users.json"));

List<User> users =
        gson.fromJson(br,
                new TypeToken<List<User>>(){}.getType());
```



# What Students Learn

| Concept         | Real Industry Meaning   |
| --------------- | ----------------------- |
| Serialization   | Persisting object state |
| JSON            | API communication       |
| File Storage    | Lightweight persistence |
| Deserialization | Rebuilding objects      |

# Phase 9 — Exception Handling

## Example

```java
try {

    int choice = scanner.nextInt();

} catch(Exception ex) {

    System.out.println("Invalid Input");
}
```

# Concepts Students Learn

* Runtime exceptions
* Input validation
* Stability
* Defensive programming

# Phase 10 — Object Oriented Refinement

## Introduce Interfaces

```java
public interface AuthOperations {

    void login();

    void logout();
}
```

# Concepts Applied

| Concept        | Meaning                  |
| -------------- | ------------------------ |
| Interface      | Contract                 |
| Loose Coupling | Flexible architecture    |
| Polymorphism   | Multiple implementations |

# Suggested Collections Usage

| Requirement            | Collection                         |
| ---------------------- | ---------------------------------- |
| Store students         | ArrayList                          |
| Store subjects         | ArrayList                          |
| Session tokens         | HashMap                            |
| Assignment submissions | HashMap<Integer, List<Submission>> |



# JSON Files

```text
users.json
subjects.json
submissions.json
sessions.json
```


# Final Console Flow

```text
WELCOME TO TFL LEARNING PORTAL

1. Register Student
2. Student Login
3. SME Login
4. Exit
```


# Example Student Journey

```text
Student Login Successful

1. View Subjects
2. View Assignments
3. Submit Answer
4. Logout
```


# Example SME Journey

```text
SME Login Successful

1. Create Subject
2. Add Assignment
3. View Submissions
4. Logout
```


# Advanced Enhancements (Optional)

After completion students can add:

* Role based authorization
* Password encryption
* Search assignment
* Sort assignments
* Stream API
* Generic Repository
* Multi-session support
* Activity logs
* Date/time handling
* Leaderboard
* Assessment scoring

# Mentor Guidance Strategy

## What Students Should Realize

This project is NOT only:

* console programming

It is actually:

* backend architecture thinking

Students are unknowingly learning:

* authentication
* state management
* persistence
* domain modeling
* layered architecture
* software engineering basics


# Final Mentor Message to Students

When you build this project carefully, you are indirectly preparing for:

* Spring Boot
* REST APIs
* Hibernate
* JWT Authentication
* Microservices
* Real LMS systems
* Enterprise backend development

Because every modern backend system internally depends on:

* objects
* collections
* sessions
* serialization
* layered design
* authentication
* state management

This project is the bridge between:

> “Java syntax learning”
> and
> “Real software engineering thinking.”
