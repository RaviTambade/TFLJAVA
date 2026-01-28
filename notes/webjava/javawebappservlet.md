🌟 **Mentor Storytelling Recap: Java Web App with Servlet + Maven + Repository Pattern**

 Let’s simplify everything we just discussed — and thread it like a real-world **mentor session** to guide Om, Sanika, and Yash clearly through how a **Java Servlet web application** is created, structured, configured, and deployed using **Maven**, along with **best practices like Repository Pattern**.

 

## 🧑‍🏫 Mentor Begins…

> *"Team, earlier we used to build console applications using `System.out.println()` and command line input. Now it's time to move to the web — where users interact via browsers, data flows through HTTP requests, and responses are HTML or JSON."*

 

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
 

## 🙋 Final Mentor Note

> "Once you understand how to structure your Java web app properly — using Maven, Servlets, Repository pattern — you're no longer just a coder. You're becoming a software engineer who thinks in terms of architecture, maintainability, and scalability."