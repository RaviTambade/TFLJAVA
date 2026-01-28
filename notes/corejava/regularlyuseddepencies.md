## Maven dependency and plugins for for **Java + Spring Boot + Testing** development environments 👇


| **Dependency / Plugin**          | **Purpose / Usage**                                                                                                                                            |
| -------------------------------- | -------------------------------------------------------------------------------------------------------------------------------------------------------------- |
| **maven-compiler-plugin**        | Compiles Java source code using a specific Java version (e.g., Java 21). Ensures compatibility between source and target bytecode.                             |
| **mysql-connector-j**            | JDBC driver that enables Java or Spring Boot applications to connect and interact with a MySQL database for CRUD operations.                                   |
| **javax.servlet-api:4.0.1**      | Provides core classes and interfaces (like `HttpServlet`, `ServletRequest`, and `ServletResponse`) to handle HTTP requests and responses in web applications.  |
| **spring-boot-starter-web**      | Provides all dependencies required to build RESTful APIs — includes Spring MVC, Jackson for JSON, and an embedded Tomcat server.                               |
| **spring-boot-starter-tomcat**   | Adds Apache Tomcat support for deploying Spring Boot applications as **WAR** files to an external server.                                                      |
| **spring-boot-maven-plugin**     | Simplifies running, packaging, and deploying Spring Boot applications (`mvn spring-boot:run`). Also creates executable JAR/WAR files.                          |
| **selenium-java**                | Provides the Selenium WebDriver library for automating browsers — used for functional and UI testing of web applications.                                      |
| **webdrivermanager**             | Automatically downloads and configures browser drivers (like ChromeDriver, GeckoDriver) required by Selenium tests — no manual setup needed.                   |
| **junit:3.8.1**                  | A very early version of the JUnit testing framework used for writing unit tests with basic assertions (modern projects use JUnit 5).                           |
| **mockito-core**                 | A mocking framework used to create fake (mock) objects for unit testing — isolates business logic by simulating dependent classes or APIs.                     |
| **spring-boot-starter-test**     | Bundles popular testing tools (JUnit 5, Mockito, Hamcrest, AssertJ, Spring Test) — provides full support for unit and integration testing in Spring Boot apps. |
| **jasypt-spring-boot-starter**   | Adds encryption/decryption capability for sensitive configuration values (like DB passwords or API keys) in Spring Boot’s `application.properties`.            |
| **io.rest-assured:rest-assured** | Provides a fluent Java API for testing RESTful APIs — helps validate response status, headers, and JSON/XML body easily.                                       |
| **maven-surefire-plugin**        | Executes unit and integration tests during the Maven build lifecycle — supports frameworks like JUnit and TestNG.                                              |
| **testng**                       | A powerful testing framework that supports grouping, parameterized tests, and parallel execution — often used for automation and regression testing.           |
| **cucumber-java**                | Enables **Behavior-Driven Development (BDD)** by allowing tests to be written in plain English (Gherkin) and linking them to Java step definitions.            |
| **cucumber-testng**              | Integrates **Cucumber** with **TestNG**, allowing feature files to be executed through TestNG test runners, enabling structured reporting and parallel runs.   |

---

### 🧠 **Summary — Categorized View**

#### ⚙️ **Build & Configuration**

* `maven-compiler-plugin` → Compiles Java code
* `spring-boot-maven-plugin` → Runs and packages Spring Boot apps
* `maven-surefire-plugin` → Executes automated tests

#### 💾 **Database & Backend**

* `mysql-connector-j` → MySQL database connectivity
* `jasypt-spring-boot-starter` → Secure config encryption

#### 🌐 **Web & API**

* `spring-boot-starter-web` → REST API + Spring MVC
* `javax.servlet-api` → Low-level servlet and HTTP handling
* `spring-boot-starter-tomcat` → External server deployment

#### 🧪 **Testing Frameworks**

* `spring-boot-starter-test` → Full Spring Boot testing suite
* `junit` → Unit testing (legacy)
* `testng` → Advanced testing framework
* `mockito-core` → Mocking dependencies
* `rest-assured` → API testing
* `selenium-java` → Browser automation testing
* `webdrivermanager` → Auto driver management
* `cucumber-java` → BDD test writing in Gherkin
* `cucumber-testng` → Run Cucumber tests via TestNG

 
