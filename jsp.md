##  Java Server Pages (JSP)

JSP stands for JavaServer Pages. It is a technology used to create dynamic web pages in Java. JSP pages are a mix of HTML and Java code, allowing developers to embed Java code directly into HTML pages. When a JSP page is requested by a client (usually a web browser), the server processes it, executes any Java code, and generates an HTML page that is sent back to the client.

Here are some key features of JSP:

1. **Easy Integration**: JSP allows easy integration of Java code with HTML, making it straightforward to create dynamic content.

2. **Server-side Execution**: JSP pages are executed on the server, which means that Java code is processed on the server side before the resulting HTML is sent to the client.

3. **Tag-Based Syntax**: JSP uses custom tags, such as `<% %>` for Java code, `<%= %>` for outputting expressions, and `<%@ %>` for directives, to embed Java code within HTML.

4. **Reuse of Components**: JSP allows for the creation of reusable components using custom tags, known as JSP tag libraries, which encapsulate complex functionality.

5. **Separation of Concerns**: JSP encourages a separation of concerns by allowing developers to separate presentation (HTML) from logic (Java code), which promotes maintainability and readability.

6. **Support for Java EE Technologies**: JSP seamlessly integrates with other Java EE technologies such as Servlets, JavaBeans, and JDBC for database access, enabling the development of robust and scalable web applications.

Overall, JSP is a powerful technology for building dynamic web applications in Java, offering a balance between ease of development and performance. However, it's worth noting that modern web development trends tend to favor client-side rendering frameworks like React, Angular, or Vue.js for building highly interactive web applications, while JSP remains a viable option for server-side rendering and legacy applications.

## Simple JSP  step by step
Let us explore step-by-step, how to use JSP with a Servlet in a Maven project:

Step 1: Set Up Maven Project
Create a new Maven project using your preferred IDE or the Maven command line. You can use the following command to create a new Maven project from the command line:

```
mvn archetype:generate -DgroupId=com.example -DartifactId=mywebapp -DarchetypeArtifactId=maven-archetype-webapp -DinteractiveMode=false
```

This will generate a basic Maven web application structure.

Step 2: Add Dependencies
Add the dependencies for Servlet API and JSP support in the `pom.xml` file:

```xml
<dependencies>
    <!-- Servlet API -->
    <dependency>
        <groupId>javax.servlet</groupId>
        <artifactId>javax.servlet-api</artifactId>
        <version>4.0.1</version>
        <scope>provided</scope>
    </dependency>
    <!-- JSP API -->
    <dependency>
        <groupId>javax.servlet</groupId>
        <artifactId>jstl</artifactId>
        <version>1.2</version>
    </dependency>
</dependencies>
```

Step 3: Create Servlet
Create a Servlet class in the `src/main/java` directory:

```java
package com.example.mywebapp;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/test")
public class TestServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setAttribute("message", "Hello from Servlet!");
        request.getRequestDispatcher("/test.jsp").forward(request, response);
    }
}
```

Step 4: Create JSP
Create a JSP file in the `src/main/webapp` directory:

```jsp
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Hello JSP</title>
</head>
<body>
    <h1><%= request.getAttribute("message") %></h1>
</body>
</html>
```

Step 5: Configure Web Deployment Descriptor (web.xml)
Since we are not using Servlet 3.0 annotations for servlet mapping, we need to configure the servlet mapping in the `web.xml` file. Create `web.xml` in the `src/main/webapp/WEB-INF` directory:

```xml
<web-app xmlns="http://xmlns.jcp.org/xml/ns/javaee"
         xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
         xsi:schemaLocation="http://xmlns.jcp.org/xml/ns/javaee http://xmlns.jcp.org/xml/ns/javaee/web-app_4_0.xsd"
         version="4.0">
         
    <servlet>
        <servlet-name>TestServlet</servlet-name>
        <servlet-class>com.tfl.servlets.TestServlet</servlet-class>
    </servlet>
    <servlet-mapping>
        <servlet-name>TestServlet</servlet-name>
        <url-pattern>/test</url-pattern>
    </servlet-mapping>
</web-app>
```

Step 6: Build and Run
Build the Maven project using `mvn clean install` and deploy it to a servlet container like Apache Tomcat.

Access the application using the URL: `http://localhost:8080/mywebapp/hello`

You should see the message "Hello from Servlet!" displayed on the webpage.

That's it! You have successfully created a Maven project with JSP and Servlet.


## JSTL Tags

JSTL (JavaServer Pages Standard Tag Library) is a collection of custom tags used in JSP (JavaServer Pages) to simplify the development of dynamic web pages. JSP allows embedding Java code within HTML, but mixing presentation logic with business logic can lead to code that is difficult to maintain and understand. JSTL provides a set of predefined tags that encapsulate common tasks and promote better code organization and separation of concerns.

JSTL tags can be divided into several categories:

1. **Core Tags (`<c:...>`)**: These tags provide basic functionality such as iteration, conditional execution, variable manipulation, and URL handling.

2. **Formatting Tags (`<fmt:...>`)**: These tags facilitate the formatting and internationalization of text, numbers, dates, and times.

3. **XML Tags (`<x:...>`)**: These tags are used for XML processing tasks such as parsing and transforming XML documents.

4. **SQL Tags (`<sql:...>`)**: These tags enable interaction with databases using SQL queries directly within JSP pages (though this approach is generally discouraged in favor of using a separate data access layer).

Using JSTL with JSP allows developers to create more maintainable and modular web applications. Instead of embedding Java code directly into JSP files, developers can use JSTL tags to perform common tasks, resulting in cleaner and more readable code. Additionally, JSTL promotes the reuse of components and facilitates internationalization by providing tags for handling localized content.

Here's a simple example of using JSTL in a JSP page:

```jsp
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<html>
<head>
    <title>Using JSTL in JSP</title>
</head>
<body>
    <c:set var="user" value="John Doe" />
    <c:if test="${not empty user}">
        <p>Welcome, <c:out value="${user}" />!</p>
    </c:if>
    
    <fmt:formatDate var="currentDate" value="${now}" pattern="dd/MM/yyyy" />
    <p>Today's date: <fmt:parseDate value="${currentDate}" pattern="dd/MM/yyyy" /></p>
</body>
</html>
```

In this example, we're using `<c:set>` to assign a value to a variable, `<c:if>` to conditionally display content, and `<fmt:formatDate>` to format the current date. These tags make the JSP code more concise and easier to understand compared to embedding Java code directly.


## Frequently used JSTL tags
JSTL (JavaServer Pages Standard Tag Library) provides a set of tags that encapsulate core functionality common to many web applications. Here are some frequently used JSTL tags along with their purposes:

1. **`<c:forEach>`**: Used for iterating over collections like arrays, Lists, or Maps. It generates HTML output for each item in the collection.

    Example:
    ```jsp
    <c:forEach items="${userList}" var="user">
        <tr>
            <td>${user.id}</td>
            <td>${user.name}</td>
            <td>${user.email}</td>
        </tr>
    </c:forEach>
    ```

2. **`<c:if>`**: Conditionally includes or excludes content based on a boolean expression.

    Example:
    ```jsp
    <c:if test="${user.isAdmin}">
        <p>Welcome Admin!</p>
    </c:if>
    ```

3. **`<c:choose>`, `<c:when>`, `<c:otherwise>`**: Allows for conditional branching similar to a switch statement in Java.

    Example:
    ```jsp
    <c:choose>
        <c:when test="${user.role eq 'ADMIN'}">
            <p>Welcome Admin!</p>
        </c:when>
        <c:otherwise>
            <p>Welcome User!</p>
        </c:otherwise>
    </c:choose>
    ```

4. **`<c:set>`**: Assigns a value to a variable.

    Example:
    ```jsp
    <c:set var="totalPrice" value="${product.price * product.quantity}" />
    ```

5. **`<c:import>`**: Imports the content of another resource (such as a JSP page or a text file) into the current JSP page.

    Example:
    ```jsp
    <c:import url="/header.jsp" />
    ```

6. **`<c:url>`**: Constructs a URL with optional query parameters, session ID, and URL rewriting.

    Example:
    ```jsp
    <c:url value="/login.jsp" var="loginUrl">
        <c:param name="redirect" value="/dashboard.jsp" />
    </c:url>
    ```

7. **`<c:out>`**: Writes the value of an expression to the output stream, escaping HTML characters.

    Example:
    ```jsp
    <c:out value="${user.name}" />
    ```

8. **`<c:remove>`**: Removes a scoped variable.

    Example:
    ```jsp
    <c:remove var="user" scope="session" />
    ```

These are just some of the frequently used tags provided by JSTL. There are more tags available in JSTL for various purposes like formatting, internationalization, XML manipulation, etc. JSTL simplifies the development of JSP pages by providing reusable components for common tasks, reducing the need for embedded Java code in JSP files.