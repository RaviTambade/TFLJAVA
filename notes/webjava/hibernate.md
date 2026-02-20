# Hibernate (ORM) Framework

When building Java applications, we often compare **JDBC vs Hibernate**.

Let’s understand clearly **why Hibernate is preferred over plain JDBC** in many enterprise applications.

## 1️⃣ What is JDBC?

Java Database Connectivity (JDBC) is a low-level API provided by Java to:

* Connect to a database
* Execute SQL queries
* Fetch and process results

You manually write:

* SQL queries
* Connection handling
* ResultSet mapping
* Transaction management

It gives full control — but requires more boilerplate code.

 

## 2️⃣ What is Hibernate?

Hibernate ORM is an **ORM (Object Relational Mapping) framework**.

It maps:

```
Java Class  ↔  Database Table
Java Object ↔  Row
Field       ↔  Column
```

You work with **objects**, not SQL.

 

# 🔥 Why Use Hibernate Over JDBC?

Let’s compare practically.

## ✅ 1. Less Boilerplate Code

### JDBC

You must write:

* Connection code
* PreparedStatement
* ResultSet loop
* Manual object mapping
* Close resources

### Hibernate

Just:

```java
session.save(employee);
```

Hibernate:

* Generates SQL
* Executes it
* Manages connection
* Maps object automatically

👉 Cleaner code. Faster development.

 

## ✅ 2. Automatic Object Mapping

In JDBC:

```java
while(rs.next()){
    emp.setId(rs.getInt("id"));
    emp.setName(rs.getString("name"));
}
```

In Hibernate:

```java
Employee emp = session.get(Employee.class, 1);
```

No manual mapping needed.

## ✅ 3. Database Independence

With JDBC:

* If you change DB (MySQL → PostgreSQL)
* You may need to rewrite queries.

With Hibernate:

* It uses dialects
* SQL is auto-generated
* Switching DB is easier
 

## ✅ 4. Caching Mechanism

Hibernate provides:

* First-level cache (Session)
* Second-level cache

JDBC:

* No built-in caching
* Every query hits database

👉 Hibernate improves performance.

## ✅ 5. Transaction Management

Hibernate integrates with:

* JTA
* Spring

JDBC:

* You manually manage commit/rollback

More risk of mistakes in JDBC.

## ✅ 6. Relationship Handling (Very Important)

Imagine:

* Employee
* Department
* One-to-Many
* Many-to-One

In JDBC:

* You manually write join queries
* Manually map objects

In Hibernate:

```java
@OneToMany
private List<Employee> employees;
```

Relationships are automatically managed.

👉 Huge advantage in enterprise apps.

 

## ✅ 7. HQL (Hibernate Query Language)

Hibernate provides:

* HQL
* Criteria API

Example:

```java
from Employee where salary > 50000
```

No need to write raw SQL always.

 

# ⚖ When Should You Use JDBC?

Hibernate is not always better.

Use JDBC when:

* Very small application
* Performance-critical bulk operations
* You need full SQL control
* Simple CRUD with no relationships

 

# 🧠 Real-World Analogy (Mentor Style)

JDBC is like:

> Driving a car manually — full control, but you handle everything.

Hibernate is like:

> Using a smart automatic car — less stress, more productivity.

 

# 📊 Quick Comparison Table

| Feature              | JDBC   | Hibernate |
| -------------------- | ------ | --------- |
| Code Length          | High   | Low       |
| Object Mapping       | Manual | Automatic |
| Caching              | No     | Yes       |
| Relationship Mapping | Manual | Automatic |
| DB Portability       | Low    | High      |
| Learning Curve       | Easy   | Moderate  |

 

# 🎯 Industry Reality

In enterprise Java:

* Most large projects use Hibernate (often with Spring Boot)
* JDBC is still used internally by Hibernate
* Developers rarely use pure JDBC for complex systems

Hibernate sits **on top of JDBC**.

 

# Understanding

👉 JDBC = Low-level database access
👉 Hibernate = High-level ORM abstraction

If you are teaching students preparing for industry:

* Teach JDBC first (to understand SQL + DB fundamentals)
* Then teach Hibernate (to build scalable enterprise apps)
 


Perfect 👍 Let’s draw a **clear architecture diagram** you can use while teaching students.

We’ll show:

1. JDBC Architecture
2. Hibernate Architecture
3. Internal Flow Comparison

 

# 🔹 1️⃣ JDBC Architecture (Low-Level Approach)

```
+----------------------+
|   Java Application   |
|  (Business Logic)    |
+----------+-----------+
           |
           v
+----------------------+
|     JDBC API         |
| (Connection,Stmt,RS) |
+----------+-----------+
           |
           v
+----------------------+
|   JDBC Driver        |
| (MySQL / Oracle etc) |
+----------+-----------+
           |
           v
+----------------------+
|      Database        |
|   (Tables & Rows)    |
+----------------------+
```

### Flow:

1. Developer writes SQL manually
2. JDBC sends SQL to database
3. Database returns ResultSet
4. Developer maps ResultSet → Object manually

 

# 🔹 2️⃣ Hibernate Architecture (ORM Approach)

Using Hibernate ORM

```
+----------------------------+
|     Java Application       |
|   (POJO Classes / Entity)  |
+-------------+--------------+
              |
              v
+----------------------------+
|     Hibernate Session      |
| (SessionFactory, Session)  |
+-------------+--------------+
              |
              v
+----------------------------+
|      ORM Engine            |
| (Mapping + Caching + HQL)  |
+-------------+--------------+
              |
              v
+----------------------------+
|          JDBC              |
+-------------+--------------+
              |
              v
+----------------------------+
|         Database           |
+----------------------------+
```

### Important Understanding:

👉 Hibernate does NOT replace JDBC
👉 Hibernate internally uses JDBC

It sits **above JDBC**.

 

# 🔥 3️⃣ Internal Request Flow Comparison

## JDBC Flow

```
App → Write SQL → JDBC → DB
App ← ResultSet ← JDBC ← DB
App → Manual Object Mapping
```

## Hibernate Flow

```
App → session.save(object)
        ↓
Hibernate converts Object → SQL
        ↓
Hibernate uses JDBC
        ↓
DB executes SQL
        ↓
Hibernate maps Result → Object
        ↓
Returns managed entity
```
 

# 🔹 4️⃣ Conceptual Comparison (Object vs Table Thinking)

## JDBC Mindset

```
Table-Centric Thinking
----------------------
Write SQL
Fetch rows
Convert rows to objects
```

## Hibernate Mindset

```
Object-Centric Thinking
-----------------------
Create Java Objects
Hibernate handles table mapping
Work with entities
```

 

# 🎯 Teaching Tip for Your Students

Explain like this:

JDBC = You speak directly to database in SQL language.

Hibernate = You speak Java language.
Hibernate translates to SQL.
Database responds.
Hibernate translates back to Objects.

---

# 🚀 Enterprise Architecture View

```
Controller Layer
        ↓
Service Layer
        ↓
Repository Layer
        ↓
Hibernate (ORM)
        ↓
JDBC
        ↓
Database
```

This is how most Spring Boot enterprise applications work today.


## Understanding Object Relational Mapping (ORM) through a Story

**“Sir, why do we even need this ORM? Can’t we just use SQL queries directly?”**

Ah, good question! Let me tell you a story…

### 📖 **Once Upon a Time in CodeLand…**

Imagine you’re a brilliant software developer who speaks fluent Java (or C# or Python). One day, your manager gives you a big task:

> *“Build a student management system that stores and retrieves student records from a database.”*

You roll up your sleeves and start writing Java code:

```java
Student student = new Student("Tejas", 22, "Computer Science");
```

It’s so easy to create and manage this student object in your code. But now comes the twist—this object has to be **stored in a relational database** like MySQL or Oracle.

Now Java speaks in **objects**, but the database only understands **tables, rows, and columns**.
It’s like trying to talk to someone who only speaks SQL while you’re speaking Java!

### 🤝 Enter ORM – The Translator!

Here comes **ORM**—our friendly **Object-Relational Mapper**.

Think of ORM as a **translator between Java and SQL**. You create and use Java objects, and ORM takes care of converting them into SQL statements behind the scenes. Magic, right?

### 🎭 Meet Hibernate – The Hero of Our Story

Hibernate is one of the most popular ORM frameworks in the Java world.

Let me explain Hibernate’s world with characters in this story:

#### 🧱 1. **Entity Mapping (The Blueprint Maker)**

You create a class like:

```java
@Entity
public class Student {
    @Id
    private int id;
    private String name;
    private int age;
}
```

Hibernate uses annotations to understand: “Ah, this `Student` class maps to a `student` table in the database.”

#### 🏭 2. **SessionFactory (The Factory Manager)**

You configure Hibernate using a file called `hibernate.cfg.xml`—this is like giving the address of your database and telling Hibernate what it needs to know about the environment.

Then Hibernate sets up a `SessionFactory`—like a central office where all database operations begin.

#### 🧑‍💼 3. **Session (The Worker Bee)**

A `Session` is like a hardworking employee who interacts with the database:

* You want to save a student? Session will do it.
* You want to fetch a list of students? Session will query it.
* You want to delete or update? Session is ready.

All of this happens inside something called a **transaction**.

#### 🔒 4. **Transactions (The Vault Keeper)**

A transaction ensures everything is done **safely**:

* Either **all changes happen**, or
* **None of them do** (in case something fails).

This is where Hibernate gives you those powerful **ACID** guarantees—just like in your Chemistry lab: Atomicity, Consistency, Isolation, and Durability.

#### 🔍 5. **Querying (Multiple Dialects)**

Hibernate speaks multiple query languages:

* **HQL (Hibernate Query Language)** – similar to SQL but object-based.
* **Criteria API** – for building queries dynamically in code.
* **Native SQL** – when you still want to use raw SQL.

Hibernate translates all of them into proper SQL for your specific database.


### 🔄 Bonus Power: **Caching**

Imagine you just asked the database for a student’s details. What if you need that data again? Should we hit the database again?

Hibernate says **“No need!”** and stores it in **cache**—faster access, better performance.


### 🧠 Student Reflection: Why Should I Use Hibernate?

Let’s ask Tejas, our student in the story.

> “Sir, I just want to write code in Java. I don’t want to think about opening database connections, writing insert/update SQL, or managing transactions manually. Hibernate makes my life simpler—and it handles a lot of complexity under the hood.”

Exactly! ORM (and Hibernate) lets you:

* Focus more on **business logic**.
* Write **less boilerplate code**.
* Enjoy the benefits of **object-oriented design** without worrying about SQL syntax all the time.


### 🧪 Mentor's Final Tip:

ORM is **not a replacement for understanding SQL**—you should still know what’s happening under the hood. But it's a **power tool** that makes working with databases cleaner, faster, and more maintainable—especially in enterprise-level apps.

## Advanatages and disadvantes of Hibernate
Hibernate offers several advantages and disadvantages, which are important to consider when deciding whether to use it in a project.

### Advantages of Hibernate:

1. **Simplified Database Interaction**: Hibernate simplifies database interaction by abstracting away SQL queries and providing an object-oriented approach to working with databases. Developers can use familiar programming paradigms and focus more on business logic rather than database details.

2. **Object-Relational Mapping (ORM)**: Hibernate provides a powerful ORM solution, allowing developers to map Java objects to database tables and vice versa. This eliminates the need to write boilerplate code for mapping objects to database tables and greatly reduces development time.

3. **Cross-Platform Compatibility**: Hibernate is platform-independent and works with various relational databases, including MySQL, PostgreSQL, Oracle, and SQL Server. This allows developers to write database-independent code that can be deployed on different database systems without modification.

4. **Automatic Database Schema Generation**: Hibernate can automatically generate database schemas based on entity mappings, reducing the need for manual database schema management. This makes it easier to maintain and evolve database schemas over time.

5. **Caching Mechanisms**: Hibernate supports caching mechanisms to improve application performance by caching frequently accessed data in memory. This can reduce the number of database queries and improve overall system performance.

6. **Transaction Management**: Hibernate provides built-in support for transactions, allowing developers to ensure data consistency and integrity by managing transactions programmatically.

### Disadvantages of Hibernate:

1. **Learning Curve**: Hibernate has a steep learning curve, especially for developers who are new to ORM concepts. Understanding Hibernate's architecture, configuration, and best practices may require significant time and effort.

2. **Performance Overhead**: Hibernate introduces some performance overhead compared to raw JDBC or native SQL queries. The overhead comes from the abstraction layer, object-relational mapping, and additional processing Hibernate performs behind the scenes.

3. **Complexity**: Hibernate can be complex to configure and maintain, especially for large-scale applications with complex domain models. Configuring mappings, caching strategies, and optimization techniques requires careful consideration and expertise.

4. **Potential for Lazy Loading Issues**: Hibernate's lazy loading mechanism can lead to performance issues and unexpected behavior if not used properly. Developers need to be aware of lazy loading implications and carefully manage fetching strategies to avoid performance bottlenecks.

5. **Database Vendor Limitations**: While Hibernate supports multiple relational databases, there may be limitations or inconsistencies across different database vendors. Certain database-specific features may not be fully supported or may require custom configurations.

6. **Debugging and Troubleshooting**: Debugging Hibernate-related issues can be challenging, especially when dealing with complex mappings, caching problems, or performance bottlenecks. Proper logging and monitoring are essential for diagnosing and resolving issues effectively.

Overall, while Hibernate offers many advantages in terms of productivity, portability, and maintainability, it's important to weigh these benefits against potential drawbacks and consider whether Hibernate is the right choice for a specific project and team skill set.


Combining Java Servlets with Hibernate is a common approach for building web applications in Java. Servlets provide the web framework, handling HTTP requests and responses, while Hibernate acts as an Object-Relational Mapping (ORM) tool, facilitating interaction with the database.

Here's a high-level overview of how you might set up a Java Servlet with Hibernate:

1. **Setup Hibernate Configuration:**
   - Define Hibernate configuration properties, such as database connection details, in a `hibernate.cfg.xml` file.
   - Configure Hibernate mapping files or annotations to map Java classes to database tables.

2. **Create Entity Classes:**
   - Define Java classes representing entities in your application (e.g., User, Product).
   - Annotate these classes with Hibernate annotations to specify the mapping between Java objects and database tables.

3. **Configure Data Access Layer:**
   - Create a Data Access Object (DAO) layer to encapsulate database operations.
   - Implement methods in DAO classes to perform CRUD (Create, Read, Update, Delete) operations using Hibernate APIs.

4. **Develop Servlets:**
   - Create Java Servlet classes to handle HTTP requests and responses.
   - Use DAO classes to interact with the database and retrieve or manipulate data.
   - Implement Servlet methods such as `doGet()` and `doPost()` to handle different types of requests.

5. **Configure Deployment Descriptor (web.xml):**
   - Define servlet mappings and other configurations in the `web.xml` file.
   - Map Servlet classes to specific URL patterns.

6. **Build the Web Application:**
   - Compile your Java classes and package them into a WAR (Web ARchive) file.
   - Include libraries for Hibernate and any other dependencies in the WAR file.

7. **Deploy and Run:**
   - Deploy the WAR file to a servlet container, such as Apache Tomcat.
   - Start the servlet container and access your application through the specified URL.

Here's a simplified example demonstrating a Java Servlet using Hibernate:

```java
@WebServlet("/users")
public class UserServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doGet(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        // Initialize Hibernate SessionFactory
        SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
        Session session = sessionFactory.openSession();

        // Retrieve users from the database using Hibernate
        List<User> users = session.createQuery("from User", User.class).getResultList();

        // Close Hibernate session
        session.close();

        // Forward users to the JSP view for rendering
        request.setAttribute("users", users);
        RequestDispatcher dispatcher = request.getRequestDispatcher("/user-list.jsp");
        dispatcher.forward(request, response);
    }
}
```

In this example, `UserServlet` handles HTTP GET requests for `/users` URL pattern. It uses Hibernate to retrieve a list of users from the database and forwards them to a JSP view (`user-list.jsp`) for rendering.

Please note that this is a simplified example, and in a real-world application, you would handle exceptions, perform validation, and implement additional features. Additionally, using JSPs for rendering views is just one approach; you could also use other templating engines or front-end frameworks.


## Step-by-Step guide to create a Java Maven project that uses Servlets with Hibernate for building a web application.

### Step 1: Set up a Maven Project

1. Open your preferred IDE (such as IntelliJ IDEA, Eclipse, or NetBeans).
2. Create a new Maven project.
3. Define the project's GroupId, ArtifactId, and other necessary information.
4. Ensure that Maven is properly configured to manage dependencies.

### Step 2: Add Dependencies

In your `pom.xml`, add the necessary dependencies for Servlets, Hibernate, and any other libraries you may need. Here's a basic example:

```xml
<dependencies>
    <!-- Servlet API -->
    <dependency>
        <groupId>javax.servlet</groupId>
        <artifactId>javax.servlet-api</artifactId>
        <version>4.0.1</version>
        <scope>provided</scope>
    </dependency>

    <!-- Hibernate -->
    <dependency>
        <groupId>org.hibernate</groupId>
        <artifactId>hibernate-core</artifactId>
        <version>5.6.1.Final</version>
    </dependency>

    <!-- MySQL Connector -->
    <dependency>
        <groupId>mysql</groupId>
        <artifactId>mysql-connector-java</artifactId>
        <version>8.0.28</version>
    </dependency>
</dependencies>
```

### Step 3: Configure Hibernate

Create a Hibernate configuration file (`hibernate.cfg.xml`) in the `src/main/resources` directory. Configure it with your database connection details.

```xml
<hibernate-configuration>
    <session-factory>
        <!-- Database connection settings -->
        <property name="hibernate.connection.driver_class">com.mysql.cj.jdbc.Driver</property>
        <property name="hibernate.connection.url">jdbc:mysql://localhost:3306/mydatabase</property>
        <property name="hibernate.connection.username">username</property>
        <property name="hibernate.connection.password">password</property>
        
        <!-- Hibernate dialect -->
        <property name="hibernate.dialect">org.hibernate.dialect.MySQLDialect</property>

        <!-- Enable Hibernate's automatic session context management -->
        <property name="hibernate.current_session_context_class">thread</property>
    </session-factory>
</hibernate-configuration>
```

### Step 4: Create Entity Classes

Create Java classes to represent your entities and map them to database tables using Hibernate annotations.

```java
@Entity
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name")
    private String name;

    // Getters and setters
}
```

### Step 5: Create Data Access Objects (DAOs)

Create Data Access Object (DAO) classes to interact with the database using Hibernate APIs.

```java
public class UserDao {
    private final SessionFactory sessionFactory;

    public UserDao(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    public List<User> getAllUsers() {
        try (Session session = sessionFactory.openSession()) {
            return session.createQuery("from User", User.class).list();
        }
    }
}
```

### Step 6: Develop Servlets

Create Servlet classes to handle HTTP requests and responses.

```java
@WebServlet("/users")
public class UserServlet extends HttpServlet {
    private final UserDao userDao;

    public UserServlet() {
        this.userDao = new UserDao(HibernateUtil.getSessionFactory());
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        List<User> users = userDao.getAllUsers();
        request.setAttribute("users", users);
        request.getRequestDispatcher("/user-list.jsp").forward(request, response);
    }
}
```

### Step 7: Configure web.xml

Configure servlet mappings and other configurations in the `web.xml` file.

```xml
<web-app xmlns="http://xmlns.jcp.org/xml/ns/javaee"
         xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
         xsi:schemaLocation="http://xmlns.jcp.org/xml/ns/javaee http://xmlns.jcp.org/xml/ns/javaee/web-app_4_0.xsd"
         version="4.0">
    <servlet>
        <servlet-name>UserServlet</servlet-name>
        <servlet-class>com.example.UserServlet</servlet-class>
    </servlet>
    <servlet-mapping>
        <servlet-name>UserServlet</servlet-name>
        <url-pattern>/users</url-pattern>
    </servlet-mapping>
</web-app>
```

### Step 8: Create JSP Views

Create JSP files for rendering views.

```jsp
<!-- user-list.jsp -->
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>User List</title>
</head>
<body>
    <h1>User List</h1>
    <table>
        <thead>
            <tr>
                <th>ID</th>
                <th>Name</th>
            </tr>
        </thead>
        <tbody>
            <c:forEach items="${users}" var="user">
                <tr>
                    <td>${user.id}</td>
                    <td>${user.name}</td>
                </tr>
            </c:forEach>
        </tbody>
    </table>
</body>
</html>
```

### Step 9: Run the Application

Deploy the application to a servlet container (e.g., Apache Tomcat) and access it through the specified URL (e.g., http://localhost:8080/myapp/users).

That's it! You've created a Maven project with Servlets and Hibernate for building a web application. Adjust the configurations and implementations as needed for your specific requirements.


In Hibernate, the `session.createQuery()` method is typically used to create queries using Hibernate's own query language called HQL (Hibernate Query Language) or JPQL (Java Persistence Query Language), which are similar to SQL but tailored for working with entities in the context of Hibernate.

However, if you need to execute a native SQL query (i.e., a query written in the native SQL dialect of your database), you can use `session.createNativeQuery()` method instead of `createQuery()`. This method allows you to pass a native SQL query directly.

Here's an example of how you can execute a native SQL query using Hibernate's `createNativeQuery()` method:

```java
public List<User> getUsersWithSalaryGreaterThan(int minSalary) {
    try (Session session = sessionFactory.openSession()) {
        String sqlQuery = "SELECT * FROM users WHERE salary > :minSalary";
        Query<User> query = session.createNativeQuery(sqlQuery, User.class);
        query.setParameter("minSalary", minSalary);
        return query.getResultList();
    }
}
```

In this example:

- We create a native SQL query string that selects users with a salary greater than a specified minimum salary.
- We use `session.createNativeQuery()` to create a native SQL query, specifying the SQL query string and the entity class (`User.class`) to which the result should be mapped.
- We set the parameter `minSalary` using `query.setParameter()` to avoid SQL injection and safely pass the value to the query.
- Finally, we execute the query using `query.getResultList()` to retrieve the list of users that match the criteria.

It's important to note that when using native SQL queries, you're working directly with database-specific SQL syntax, which may vary between different database vendors. Therefore, be cautious about using native SQL queries as they may reduce the portability of your application across different database systems. If possible, consider using HQL or JPQL queries for better portability and abstraction.

You can use Hibernate's `session.createNativeQuery()` method to perform CRUD (Create, Read, Update, Delete) operations using native SQL queries. Here's how you can do it for each operation:

### Create (INSERT) Operation:

```java
public void createUser(User user) {
    try (Session session = sessionFactory.openSession()) {
        Transaction transaction = session.beginTransaction();
        String sqlQuery = "INSERT INTO users (name, email) VALUES (:name, :email)";
        NativeQuery<?> query = session.createNativeQuery(sqlQuery);
        query.setParameter("name", user.getName());
        query.setParameter("email", user.getEmail());
        query.executeUpdate();
        transaction.commit();
    }
}
```

### Read (SELECT) Operation:

```java
public List<User> getAllUsers() {
    try (Session session = sessionFactory.openSession()) {
        String sqlQuery = "SELECT * FROM users";
        NativeQuery<User> query = session.createNativeQuery(sqlQuery, User.class);
        return query.getResultList();
    }
}
```

### Update (UPDATE) Operation:

```java
public void updateUser(User user) {
    try (Session session = sessionFactory.openSession()) {
        Transaction transaction = session.beginTransaction();
        String sqlQuery = "UPDATE users SET name = :name, email = :email WHERE id = :id";
        NativeQuery<?> query = session.createNativeQuery(sqlQuery);
        query.setParameter("name", user.getName());
        query.setParameter("email", user.getEmail());
        query.setParameter("id", user.getId());
        query.executeUpdate();
        transaction.commit();
    }
}
```

### Delete (DELETE) Operation:

```java
public void deleteUser(long userId) {
    try (Session session = sessionFactory.openSession()) {
        Transaction transaction = session.beginTransaction();
        String sqlQuery = "DELETE FROM users WHERE id = :id";
        NativeQuery<?> query = session.createNativeQuery(sqlQuery);
        query.setParameter("id", userId);
        query.executeUpdate();
        transaction.commit();
    }
}
```

In each operation:

- We obtain a Hibernate session using `sessionFactory.openSession()`.
- We start a transaction using `session.beginTransaction()` for operations that modify the database.
- We create a native SQL query using `session.createNativeQuery()` with the appropriate SQL query string and entity mapping (if needed).
- We set parameters using `query.setParameter()` to avoid SQL injection and safely pass values to the query.
- We execute the query using `query.executeUpdate()` for operations that modify the database.
- We commit the transaction using `transaction.commit()` for operations that modify the database.

These examples demonstrate how to perform CRUD operations using native SQL queries with Hibernate in Java. Ensure that your SQL queries are valid and properly handle exceptions and edge cases in your application logic.