

## ☕🛰️ Deploying a Servlet/JSP App on Apache Tomcat (Step-by-Step)



### 🧱 **1. Prerequisites**

✅ Java JDK installed
✅ Apache Tomcat installed (e.g., Tomcat 9 or 10)
✅ Maven installed
✅ A basic Servlet/JSP project (WAR-packaged)
✅ IDE (like IntelliJ IDEA, Eclipse, or VS Code)


### 🛠️ **2. Project Structure using Maven**

Create the Maven project like this:

```
my-webapp/
├── src/
│   └── main/
│       ├── java/        ← Servlet files
│       ├── resources/
│       └── webapp/      ← HTML, JSP files, WEB-INF
│           ├── index.jsp
│           └── WEB-INF/
│               └── web.xml
├── pom.xml
```

### ✍️ **3. Sample Servlet Code**

📄 `HelloServlet.java`

```java
package com.example;

import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;

public class HelloServlet extends HttpServlet {
    public void doGet(HttpServletRequest request, HttpServletResponse response)
      throws IOException, ServletException {
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        out.println("<h2>Hello from Servlet!</h2>");
    }
}
```

### 📄 **4. Add `web.xml` (Deployment Descriptor)**

📁 `src/main/webapp/WEB-INF/web.xml`

```xml
<web-app xmlns="http://java.sun.com/xml/ns/javaee" version="3.0">
  <servlet>
    <servlet-name>HelloServlet</servlet-name>
    <servlet-class>com.example.HelloServlet</servlet-class>
  </servlet>
  <servlet-mapping>
    <servlet-name>HelloServlet</servlet-name>
    <url-pattern>/hello</url-pattern>
  </servlet-mapping>
</web-app>
```

### ⚙️ **5. `pom.xml` Maven Configuration**

Add servlet API dependency:

```xml
<project xmlns="http://maven.apache.org/POM/4.0.0" ...>
  <modelVersion>4.0.0</modelVersion>
  <groupId>com.example</groupId>
  <artifactId>my-webapp</artifactId>
  <version>1.0</version>
  <packaging>war</packaging>

  <dependencies>
    <dependency>
      <groupId>javax.servlet</groupId>
      <artifactId>javax.servlet-api</artifactId>
      <version>4.0.1</version>
      <scope>provided</scope>
    </dependency>
  </dependencies>

  <build>
    <finalName>my-webapp</finalName>
  </build>
</project>
```

### 🔨 **6. Build WAR File Using Maven**

In the terminal, run:

```bash
mvn clean package
```

➡️ This creates a file `target/my-webapp.war`.


### 🐱 **7. Install & Start Apache Tomcat**

1. [Download Tomcat](https://tomcat.apache.org/download-90.cgi)
2. Extract ZIP
3. Navigate to `apache-tomcat-x.x.xx/bin`
4. Start Tomcat:

```bash
./startup.sh   # Linux/Mac
startup.bat    # Windows
```

Open browser:
[http://localhost:8080](http://localhost:8080)


### 📦 **8. Deploy the WAR File**

#### Option 1: Manual WAR Deployment

1. Copy `target/my-webapp.war`
2. Paste into Tomcat's `webapps/` directory
3. Restart Tomcat

   * Or wait — Tomcat auto-deploys WAR files in `webapps/`

App will be available at:
👉 `http://localhost:8080/my-webapp/hello`


#### Option 2: Use Tomcat Manager App (GUI)

1. Access:
   [http://localhost:8080/manager](http://localhost:8080/manager)

2. Login (edit `tomcat-users.xml` in `conf/`):

```xml
<tomcat-users>
  <role rolename="manager-gui"/>
  <user username="admin" password="admin" roles="manager-gui"/>
</tomcat-users>
```

3. Restart Tomcat, then log in
4. Use the web interface to upload your `.war` file and deploy



### 🧪 **9. Test It!**

Visit:
🔗 [http://localhost:8080/my-webapp/hello](http://localhost:8080/my-webapp/hello)

You should see:

> **Hello from Servlet!**



### ✅ Summary Checklist

| Step | Task                         |
| ---- | ---------------------------- |
| 1    | Install JDK, Maven, Tomcat   |
| 2    | Create Maven WAR project     |
| 3    | Write Servlet + `web.xml`    |
| 4    | Build WAR with `mvn package` |
| 5    | Deploy WAR to Tomcat         |
| 6    | Access via browser           |


