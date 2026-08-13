
# 🌸 Transflower Mentor: Your Java Project Is a Software Home 🏡
Let us turn this into a **Transflower Learning session** where students understand not only *what each Maven folder is*, but **why the structure exists and how a professional Java developer thinks about project organization**.

Dear students, Today, I don't want to start with Java syntax. I don't want to start with:

```java
public class Customer
```

Instead, I want to ask you a simple question:

> **If you were building a house, would you put the kitchen, bedroom, bathroom, garage, and living room in one place without any organization?**

Of course not. You would create spaces with **specific responsibilities**.

```text
House
│
├── Living Room
├── Kitchen
├── Bedroom
├── Bathroom
└── Storage
```

Software is exactly the same.

> 🌱 **Good software is not just about writing good code. It is also about putting the right code in the right place.**


# 🏡 1. Your Java Project Is Your House

Imagine creating a Maven project:

```text
insurance-management/
│
├── src/
│
├── target/
│
└── pom.xml
```

At first, a student may look at this and say:

> "Why are there so many folders?"

As a mentor, I would say:

> **Don't look at folders as folders. Look at them as responsibilities.**

```text
src/main/java       → Application source code
src/main/resources  → Application resources
src/test/java       → Test source code
src/test/resources  → Test resources
target              → Generated build output
pom.xml             → Maven project instructions
```

Now the structure starts making sense.



# 🧱 2. `src/` — The Main Building

Think of:

```text
src/
```

as the **construction area of your software**.Inside it, Maven separates two worlds:

```text
src/
│
├── main/     → Actual application
│
└── test/     → Testing the application
```

This distinction is extremely important.  **Main is what you build. Test is how you verify what you built.**

 

# 🛋️ 3. `src/main/java` — Where the Application Lives

Now we enter:

```text
src/main/java/
```

This is where your Java source code lives. For example:

```text
src/main/java/
└── com/
    └── tfl/
        └── insurance/
            ├── Customer.java
            ├── Policy.java
            ├── Claim.java
            └── PaymentService.java
```

Think of this as the **main living area of your software house**. This is where developers spend most of their time. You may organize the code further:

```text
com.tfl.insurance
│
├── controller
├── service
├── repository
├── model
└── configuration
```

For example:

```text
controller
   ↓
Handles HTTP requests

service
   ↓
Business logic

repository
   ↓
Database interaction

model
   ↓
Domain/data representation
```

Now your project starts communicating its architecture just by looking at its folders.

 

# 🧠 Mentor Question

Suppose I find this:

```text
src/main/java/
```

and inside it I see:

```text
CustomerController.java
CustomerService.java
CustomerRepository.java
```

I can immediately understand:

> "Ah! This application probably follows a layered architecture."

That's the power of **organization**.

Your folder structure becomes a form of documentation.

 

# 🍳 4. `src/main/resources` — The Pantry

Now imagine the kitchen. You don't keep everything in the refrigerator. You organize:

```text
Rice
Spices
Vegetables
Utensils
```

Similarly, Java applications need things that are **not Java source code**. That's why we have:

```text
src/main/resources/
```

For example:

```text
src/main/resources/
│
├── application.properties
├── messages.properties
└── logback.xml
```

Depending on the application, you might also have:

```text
JSON files
XML files
SQL scripts
templates
static resources
configuration files
```

The important idea is:

> **Resources support the application but are not Java source files.**

 

# ☕ Spring Boot Connection

When you start working with Spring Boot, this folder becomes very important. You will frequently see:

```text
src/main/resources/
└── application.properties
```

or:

```text
application.yml
```

For example:

```properties
server.port=8080
```

or:

```properties
spring.datasource.url=jdbc:mysql://localhost:3306/insurance
```

So remember:

```text
src/main/java
      ↓
Java code

src/main/resources
      ↓
Configuration + supporting resources
```

---

# 🔬 5. `src/test/java` — Your Engineering Laboratory

Now we enter the laboratory.

```text
src/test/java/
```

This is where we test our application code.

For example:

```text
src/main/java/
└── CustomerService.java

src/test/java/
└── CustomerServiceTest.java
```

The production code says:

> "I can calculate premium."

The test says:

> "Prove it."

That's the mindset of professional software engineering.

 

# 🧪 Example

Suppose:

```java
public class Calculator {

    public int add(int a, int b) {
        return a + b;
    }
}
```

Then:

```text
src/test/java/
└── CalculatorTest.java
```

might contain tests using JUnit.

Conceptually:

```text
Calculator
     ↓
Test
     ↓
Expected result
     ↓
Actual result
     ↓
PASS / FAIL
```

This is why I tell students:

> 🌸 **Testing is not something you do after development. Testing is part of development.**
 

# 🧫 6. `src/test/resources` — Test Data Pantry

Sometimes your tests need files.

For example:

```text
src/test/resources/
│
├── customer.json
├── policy.json
└── test-data.sql
```

These are resources specifically required by tests. Think:

```text
main/resources
      ↓
Production pantry

test/resources
      ↓
Testing pantry
```

This separation is very useful. You don't want your test data accidentally becoming production configuration.

 

# 🏭 7. `target/` — The Factory

Now we reach:

```text
target/
```

This is an important folder because students often ask:

> "Where did this folder come from? I didn't create it."

Exactly!  **Maven created it.** Think of Maven as a factory.

```text
Source Code
     ↓
   Maven
     ↓
Compile
     ↓
Test
     ↓
Package
     ↓
Build Artifact
```

The factory floor is:

```text
target/
```

You might see:

```text
target/
│
├── classes/
├── test-classes/
├── generated-sources/
└── application.jar
```

 

# ⚙️ `target/classes`

This contains compiled application classes. For example:

```text
Customer.java
```

becomes:

```text
Customer.class
```

Conceptually:

```text
.java
  ↓
javac / Maven
  ↓
.class
```

 

# 🧪 `target/test-classes`

Your test source:

```text
CustomerServiceTest.java
```

gets compiled into something like:

```text
CustomerServiceTest.class
```

and placed under the test build output.

 

# 📦 The Final JAR

Maven can package your application:

```text
target/
└── insurance-management-1.0.jar
```

That JAR becomes a deployable artifact. For a Spring Boot application, you may eventually do:

```bash
java -jar insurance-management-1.0.jar
```

And your application starts.

 

# 🚨 Important: Don't Commit `target/`

Students sometimes do:

```bash
git add .
```

and accidentally commit:

```text
target/
```

Don't do that.

Why?

Because:

> **`target/` is generated output.**

Your source code should be committed. Generated build artifacts generally should not be committed to source control. So your `.gitignore` normally includes:

```text
target/
```


# 📜 8. `pom.xml` — The Maven Instruction Book

Now we come to the most important file in a Maven project:

```text
pom.xml
```

POM means:

> **Project Object Model**

Think of `pom.xml` as the **instruction manual given to Maven**.

You tell Maven:

```text
Who am I?
What libraries do I need?
How should I build?
What plugins should I use?
What Java version am I using?
```

For example:

```xml
<groupId>com.tfl</groupId>
<artifactId>insurance-management</artifactId>
<version>1.0.0</version>
```


# 🧩 Maven Coordinates

These three are particularly important:

```text
groupId
artifactId
version
```

Think of them like the identity of your software artifact.

```text
groupId
   ↓
Organization / namespace

artifactId
   ↓
Project name

version
   ↓
Release version
```

For example:

```text
com.tfl
insurance-management
1.0.0
```


# 📚 Dependencies

Suppose you need Spring Boot. You don't manually download every JAR. You declare dependencies in:

```text
pom.xml
```

For example, conceptually:

```xml
<dependency>
    ...
</dependency>
```

Maven resolves the dependency and its required transitive dependencies. This is where Maven becomes extremely powerful.

# 🏗️ The Complete Maven House

Now let's look at the entire house again:

```text
insurance-management/
│
├── src/
│   │
│   ├── main/
│   │   ├── java/
│   │   │   └── com/
│   │   │       └── tfl/
│   │   │           └── insurance/
│   │   │               ├── controller/
│   │   │               ├── service/
│   │   │               ├── repository/
│   │   │               └── model/
│   │   │
│   │   └── resources/
│   │       └── application.properties
│   │
│   └── test/
│       ├── java/
│       │   └── com/
│       │       └── tfl/
│       │           └── insurance/
│       │               └── CustomerServiceTest.java
│       │
│       └── resources/
│           └── test-data.json
│
├── target/
│   └── generated build output
│
└── pom.xml
```

Now don't see this as a collection of folders.

See it as:

```text
                    🏡 Software House
                          │
        ┌─────────────────┼──────────────────┐
        │                 │                  │
     MAIN CODE          TESTS             BUILD
        │                 │                  │
        ▼                 ▼                  ▼
   src/main           src/test            target
        │
   ┌────┴────┐
   ▼         ▼
 java    resources
   │
   ▼
Application
```


# 🌸 The Deeper Mentor Lesson

Now I want you to notice something. A project structure isn't primarily about the compiler. It is about **human beings**. Imagine joining a project developed by 50 engineers. You open it and immediately see:

```text
src/main/java
src/main/resources
src/test/java
src/test/resources
pom.xml
```

You already know where to look. That's the power of **convention**. Maven says:

> "Put production code here."

Developers follow the convention.
Spring Boot builds upon those conventions.
Testing frameworks understand those conventions.
IDE tools understand those conventions.
CI/CD pipelines understand those conventions.
Build tools understand those conventions.


# 🔄 Convention Creates Automation

This is a very important software engineering idea.Because everyone follows the same structure:

```text
Developer
   ↓
Maven
   ↓
Compile
   ↓
Test
   ↓
Package
   ↓
Deploy
```

Automation becomes easier.

You don't have to tell Maven:

> "Search this random directory for Java files."

Maven already knows the conventional source directories.

 

# 🌱 Mentor Rule: Convention Before Customization

A student often asks:

> "Can I rename `src/main/java`?"

Technically, build systems can be configured.

But my advice is:

> **Don't customize something simply because you can.**

First understand:

```text
Why does Maven expect this?
Why is test code separated?
Why are resources separated?
Why is target generated?
Why is pom.xml important?
```

Then customize only when there is a genuine reason.



# 🎯 Interview Questions From This Topic

### Q1. What is `src/main/java`?

> The standard Maven directory for production Java source code.

### Q2. What is `src/main/resources`?

> The standard location for production resources such as configuration files and other classpath resources.

### Q3. What is `src/test/java`?

> The standard location for test source code.

### Q4. What is `target`?

> Maven's generated build-output directory containing compiled classes, test classes, packaged artifacts, and other generated files.

### Q5. Should `target` be committed to Git?

> Generally no. It is generated output and should normally be ignored.

### Q6. What is `pom.xml`?

> The Maven Project Object Model file that describes project metadata, dependencies, build configuration, plugins, and related settings.


# 🌸 Final Transflower Thought

Dear students,

When you learn Java, don't just learn:

```java
class
method
object
interface
```

Learn to think like an engineer.

Ask:

> **Where should this code live?**

Ask:

> **Who owns this responsibility?**

Ask:

> **How will another developer find this code six months from now?**

That is why project structure matters.

Remember our house:

```text
🏡 Java Project
│
├── 🛋️ main/java       → Application code
├── 🍳 main/resources   → Application resources
├── 🔬 test/java        → Tests
├── 🧪 test/resources   → Test data/resources
├── 🏭 target           → Build output
└── 📜 pom.xml          → Maven instructions
```

And remember the bigger lesson:

> 🌱 **Clean code is not only about clean classes. Clean architecture starts with a clean home for those classes.**

> **Don't just write code. Organize it so that another developer can understand your thinking by walking through your project.**
