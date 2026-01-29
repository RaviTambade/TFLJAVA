 
## 🚧 Precautions When Deploying a Servlet/JSP App on Apache Tomcat

### 🔐 1. **Do Not Include Sensitive Data in WAR**

* ❌ Never hardcode database passwords or API keys in `.java`, `.jsp`, or `web.xml`
* ✅ Use **environment variables**, `.properties` files, or **context parameters** in `context.xml`


### 🛡️ 2. **Disable Directory Listing**

* By default, Tomcat may allow users to browse folders like `/images/` or `/files/`
* 🔧 In `web.xml`, disable it:

```xml
<init-param>
  <param-name>listings</param-name>
  <param-value>false</param-value>
</init-param>
```

* Or, **remove the default servlet mapping** if not needed

  

### 🔒 3. **Restrict Tomcat Manager Access**

* The `/manager` app can deploy, undeploy, and stop apps
* 🚫 Don't expose this in production without:

  * IP whitelisting
  * Strong credentials (change `admin/admin`)
  * Remove or disable `/manager` in `webapps/`

 

### 📁 4. **Use Proper File Permissions**

* Set least-privilege permissions for:

  * `webapps/`
  * `conf/`
  * `logs/`
* Example:

  * Tomcat runs as user `tomcat`
  * Only `tomcat` user has write access to deployment folders

### 🧱 5. **Validate WAR Structure**

Before deployment:

* Check your WAR file for:

  * `WEB-INF/web.xml` present
  * Proper Servlet mappings
  * Dependencies included (unless marked as `provided`)

### ⚠️ 6. **Disable Unused Features**

* Disable **unnecessary Tomcat components**:

  * JSP if you only use Servlets
  * AJP connector if not used
  * Auto-deploy or auto-execution of scripts

In `conf/server.xml` or `conf/web.xml`, comment or remove unused tags.

### 🔥 7. **Watch for Memory Leaks**

* Improper use of JDBC connections, threads, or listeners may cause leaks
* Always:

  * Close DB connections in `finally`
  * Unregister listeners on `contextDestroyed()`

 

### 📦 8. **Don’t Deploy in ROOT Unless Necessary**

* If you deploy to `ROOT.war`, it becomes default app on `/`
* Prefer deploying under `/yourapp/` unless it's the only app on the server

### 🧪 9. **Test Locally First**

* Before putting on a production server:

  * Run on `localhost`
  * Test servlet responses, form submissions, sessions, error handling

### 🧰 10. **Logging and Error Handling**

* Configure `log4j` or `java.util.logging` for logs
* In `web.xml`, define custom error pages:

```xml
<error-page>
  <error-code>404</error-code>
  <location>/error404.jsp</location>
</error-page>
```

 

### 🌐 11. **Use HTTPS in Production**

* Never deploy a web app using HTTP in real production
* Set up SSL certificate (Let's Encrypt or other)
* Enable HTTPS connector in `server.xml`

 

### 🔐 12. **Secure Java Code**

* Avoid using `scriptlets (<% %>)` in JSP. Prefer JSTL, EL, MVC (like Spring)
* Use `request.getParameter()` safely – validate all inputs
* Protect against:

  * XSS (Cross-site scripting)
  * SQL Injection
  * CSRF (Cross-site request forgery)

 

### 🗄️ 13. **Backup Existing Deployments**

* Before deploying a new `.war`, take a backup of:

  * `webapps/yourapp/`
  * `logs/`
  * `conf/`
* Rollback is easier if something goes wrong

 

### 🕒 14. **Automate Build & Deployment**

* Use CI/CD tools (e.g., Jenkins, GitHub Actions) for consistent deployments
* Automate:

  * `mvn clean package`
  * `.war` deployment to Tomcat's `webapps/` via script

 

### 🧯 15. **Monitor Resource Usage**

* Regularly check:

  * Heap size
  * Thread usage
  * Request count
* Tools: JConsole, VisualVM, Prometheus + Grafana

 
## ✅ Summary: Deployment Safety Checklist

| ✅ Precaution          | 🔧 Action                   |
| --------------------- | --------------------------- |
| 🔒 Sensitive Data     | Externalize configs         |
| 🚫 Directory Browsing | Disable in `web.xml`        |
| 🔐 Tomcat Manager     | Restrict access             |
| 👤 File Permissions   | Least-privilege             |
| 🧪 WAR File           | Validate structure          |
| 🔧 Remove Unused      | Disable unneeded connectors |
| 🧼 Clean Code         | Manage threads, DBs         |
| 🌐 HTTPS              | Always in production        |
| 🧰 Logs               | Set up proper logging       |
| 🧯 Rollback           | Backup before deploying     |
| ⚙️ CI/CD              | Use Maven + automation      |

 