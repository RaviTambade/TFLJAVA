🌟 **Java Web App with Servlet + Maven + Repository Pattern**

## 🧑‍🏫 Mentor Begins…

> *"Team, earlier we used to build console applications using `System.out.println()` and command line input. Now it's time to move to the web — where users interact via browsers, data flows through HTTP requests, and responses are HTML or JSON."*


Here is your content structured using the **Transflower Learning Framework (TFL)** — in a **mentor-style, industry-oriented format** for teaching students.


## **Topic: Java Servlets – Foundation of Server-Side Web Development**


## 🔷 1. **Why Should You Learn Servlets? (Purpose Layer – *“Why”)**

Before Spring Boot, before Microservices, before REST APIs…

👉 **There were Servlets.**

Every serious Java backend framework is built **on top of Servlets**.

If you understand Servlets:

* You understand how web servers really work
* You understand how requests reach your code
* You understand how responses go back to users

📌 **Industry Truth:**
Good backend engineers don’t just use frameworks —
They understand what happens *inside* them.


## 🔷 2. **What Is a Servlet? (Concept Layer – *“What”)**

A **Servlet** is:

> A Java class that handles HTTP requests and generates HTTP responses.

In simple words:

🧠 **Servlet = Java Program that Talks to Browsers**

```
Browser  →  Request  →  Servlet  →  Response  →  Browser
```

It runs on a **server**, not on the client.


## 🔷 3. **Where Does a Servlet Run? (System Layer – *“Where”)**

Servlets never run alone.

They run inside:

### ✅ Servlet Container (Web Container)

Examples:

* Apache Tomcat
* Jetty
* GlassFish
* WildFly

### Container Responsibilities:

| Task           | Who Does It? |
| -------------- | ------------ |
| Load servlet   | Container    |
| Create object  | Container    |
| Call methods   | Container    |
| Manage memory  | Container    |
| Handle threads | Container    |

👉 Developer writes **logic**
👉 Container handles **infrastructure**

📌 This is the beginning of **Server Framework Thinking**.


## 🔷 4. **Servlet Architecture (Flow Layer – *“How It Works”)**

### Request–Response Pipeline

```
Client (Browser)
      ↓
Web Server (Tomcat)
      ↓
Servlet Container
      ↓
Your Servlet
      ↓
Response Generated
      ↓
Client
```

### Behind the Scene:

- 1️⃣ User clicks button
- 2️⃣ HTTP request sent
- 3️⃣ Container finds servlet
- 4️⃣ Calls service() method
- 5️⃣ Servlet processes
- 6️⃣ Response returned

This is the **heart of Web Programming**.

## 🔷 5. **Servlet Lifecycle (Control Layer – *“How It Lives”)**

Every servlet follows a fixed lifecycle:

### 📌 Three Phases

```
init()     →   service()   →   destroy()
```

| Phase     | Purpose         |
| --------- | --------------- |
| init()    | One-time setup  |
| service() | Handle requests |
| destroy() | Cleanup         |

### Lifecycle Diagram

```
Load → init() → Ready → service() → destroy() → Remove
```

👉 This teaches **Resource Management** (Memory, DB, Files)

---

## 🔷 6. **What Can Servlets Do? (Capability Layer – *“What Power”)**

Servlets can:

- ✅ Read Form Data
- ✅ Connect Database (JDBC)
- ✅ Apply Business Rules
- ✅ Generate HTML / JSON / XML
- ✅ Authenticate Users
- ✅ Handle Sessions
- ✅ Integrate Messaging

📌 A Servlet is a **Mini Backend Engine**.

## 🔷 7. **Servlet vs JSP vs JSF (Technology Layer – *“Positioning”)**

| Technology | Role            |
| ---------- | --------------- |
| Servlet    | Core Controller |
| JSP        | View (UI)       |
| JSF        | UI Framework    |

### Reality:

```
Spring MVC
   ↓
Servlet
```

Even Spring Boot ultimately runs on Servlets.

👉 Servlets are **root technology**.


## 🔷 8. **Performance & Platform Independence (Quality Layer – *“Why Industry Uses It”)**

### 🚀 Performance

* Compiled Java bytecode
* Multi-threaded execution
* JVM optimized

### 🌍 Platform Independent

```
Write Once → Run Anywhere
```

Windows / Linux / Cloud → Same code

This is why enterprises trust Java.

---

## 🔷 9. **Extensibility & Integration (Ecosystem Layer – *“Enterprise Usage”)**

Servlets integrate with:

| Tech          | Purpose             |
| ------------- | ------------------- |
| JDBC          | Database            |
| EJB           | Business Services   |
| JMS           | Messaging           |
| REST          | APIs                |
| Microservices | Distributed Systems |

👉 Servlets = Backbone of Java Enterprise Stack


## 🔷 10. **Industry Mapping (Career Layer – *“Why You Need This”)**

If you know Servlets well, you can become:

- ✅ Java Backend Developer
- ✅ Spring Engineer
- ✅ API Developer
- ✅ Cloud Engineer
- ✅ Microservices Architect

📌 Interviews always test:

> “Do you know how Servlet works internally?”



## 🔷 11. **TFL Learning Path for Servlets (Growth Layer – *“How to Master”)**

### Phase 1: Foundation

- ✔ HTTP Basics
- ✔ Request/Response
- ✔ GET/POST
- ✔ Sessions

### Phase 2: Implementation

- ✔ HttpServlet
- ✔ doGet / doPost
- ✔ JDBC Integration
- ✔ JSON Response

### Phase 3: Architecture

- ✔ MVC using Servlets
- ✔ Filters
- ✔ Listeners
- ✔ Security

### Phase 4: Framework Bridge

- ✔ Spring MVC
- ✔ REST APIs
- ✔ Microservices



## 🔷 12. **Mentor Insight (Wisdom Layer – *“Reality Check”)**

> ❝ Frameworks come and go.
> Servlets remain. ❞

Students who skip Servlets:
- ❌ Only know “how to use”
- ❌ Don’t know “why it works”

Students who master Servlets:
- ✅ Can debug production systems
- ✅ Can build frameworks
- ✅ Can scale systems


## 🌱 Final Transflower Summary

| Layer      | Learning Outcome   |
| ---------- | ------------------ |
| Concept    | What is Servlet    |
| System     | Container & Server |
| Flow       | Request Pipeline   |
| Control    | Lifecycle          |
| Capability | Features           |
| Quality    | Performance        |
| Ecosystem  | Integration        |
| Career     | Industry Value     |

👉 **Servlet = Foundation Stone of Java Web Engineering**

## 🌐 Step-by-Step: Building a Java Web Application with Servlet

### 📦 1. **Create Maven Project**

```bash
mvn archetype:generate -DgroupId=com.mywebapp \
  -DartifactId=myservletapp \
  -DarchetypeArtifactId=maven-archetype-webapp \
  -DinteractiveMode=false
```

🔹 This creates:

* `src/main/java/` → Java source
* `src/main/webapp/` → HTML, JSP, web.xml
* `pom.xml` → Dependencies, build config
* `target/` → Compiled WAR file

 

### 🏗️ 2. **Project Folder Structure**

```
myservletapp/
├── pom.xml
├── src/
│   ├── main/
│   │   ├── java/
│   │   │   └── com/mywebapp/servlets/SimpleServlet.java
│   │   ├── resources/
│   │   └── webapp/
│   │       ├── index.html
│   │       ├── WEB-INF/
│   │       │   └── web.xml
└── target/
    └── myservletapp.war  ← ready for Tomcat
```

 

### 🧾 3. **pom.xml Dependency**

```xml
<dependencies>
  <dependency>
    <groupId>javax.servlet</groupId>
    <artifactId>javax.servlet-api</artifactId>
    <version>4.0.1</version>
    <scope>provided</scope>
  </dependency>
</dependencies>
```

💡 **Scope: provided** → Because Tomcat already includes servlet libraries.

 

### 🔧 4. **Simple Servlet Code**

```java
@WebServlet("/hello")
public class SimpleServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        out.println("<h1>Hello from GET request!</h1>");
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        out.println("<h1>Hello from POST request!</h1>");
    }
}
```
 

### 📄 5. **web.xml (optional if using annotations)**

```xml
<web-app xmlns="http://xmlns.jcp.org/xml/ns/javaee"
         version="3.1">
  <servlet>
    <servlet-name>SimpleServlet</servlet-name>
    <servlet-class>com.mywebapp.servlets.SimpleServlet</servlet-class>
  </servlet>
  <servlet-mapping>
    <servlet-name>SimpleServlet</servlet-name>
    <url-pattern>/hello</url-pattern>
  </servlet-mapping>
</web-app>
```

 

### 🛠️ 6. **Build WAR File**

```bash
mvn clean package
```

WAR file generated at:

```
target/myservletapp.war
```

  

### 🌐 7. **Deploy to Tomcat**

Copy `myservletapp.war` into:

```
C:\Program Files\Apache Software Foundation\Tomcat 9.0\webapps\
```

Then visit in browser:

```
http://localhost:8080/myservletapp/hello
```

## 🧩 Advanced: Add Repository Pattern (Best Practice)

Let’s say we want to show a list of products.

---

### 📁 Create Interface (Repository)

```java
public interface ProductRepository {
    List<String> getAllProducts();
}
```

### 📁 Create Implementation

```java
public class DummyProductRepository implements ProductRepository {
    public List<String> getAllProducts() {
        return Arrays.asList("Laptop", "Mouse", "Keyboard", "Monitor");
    }
}
```

### 🧑‍🍳 Use It in Servlet

```java
@WebServlet("/products")
public class ProductServlet extends HttpServlet {

    private ProductRepository repo = new DummyProductRepository();

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        List<String> products = repo.getAllProducts();
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        out.println("<ul>");
        for (String product : products) {
            out.println("<li>" + product + "</li>");
        }
        out.println("</ul>");
    }
}
```

✅ **Now your servlet is decoupled from data logic!**

 

## 🎯 What You Learned

| Concept                | Purpose                                       |
| ---------------------- | --------------------------------------------- |
| `Maven`                | Project creation, build, dependency           |
| `Servlet`              | Java class handling HTTP requests             |
| `web.xml`              | Web app deployment descriptor                 |
| `@WebServlet`          | Servlet annotation mapping                    |
| `Repository Interface` | Abstract data logic                           |
| `Polymorphism`         | Switch repository logic without changing code |
| `WAR` file             | Web Archive for deployment on Tomcat          |
 


> "Once you understand how to structure your Java web app properly — using Maven, Servlets, Repository pattern — you're no longer just a coder. You're becoming a software engineer who thinks in terms of architecture, maintainability, and scalability."