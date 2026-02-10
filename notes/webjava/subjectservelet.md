
##  Java Servlet as Foundation for SpringBoot, SpringMVC


👉  **Controller → Service → File (JSON) / Data access**, very basic, no over-engineering.


Think of the **TFL Assessment / Question Bank app** like this:

```
Browser / Client
      |
      v
Servlet Controller
      |
      v
Service Layer
      |
      v
JSON File (Question Bank)
```

No magic. No confusion. Just **flow**.


## Example use case: Question Bank

Operations you mentioned (perfect 👍):

* Get all subjects
* Add subject
* Delete subject
* Get questions for a subject


## Folder structure (simple & clean)

```
src
 └── main
     ├── java
     │   └── com.tfl.assessment
     │       ├── controllers
     │       │   └── SubjectController.java
     │       ├── services
     │       │   └── SubjectService.java
     │       ├── repositories
     │       │   └── SubjectRepository.java
     │       └── models
     │           └── Subject.java
     └── resources
         └── data
             └── subjects.json
```



## subjects.json (Question Bank data)

```json
[
  {
    "id": 1,
    "name": "Java",
    "questions": [
      "What is JVM?",
      "Difference between JDK and JRE?"
    ]
  },
  {
    "id": 2,
    "name": "SQL",
    "questions": [
      "What is normalization?",
      "What is a primary key?"
    ]
  }
]
```


## 1️⃣ Controller (Servlet)

**Only handles HTTP request & response**

```java
@WebServlet("/subjects")
public class SubjectController extends HttpServlet {

    private SubjectService service = new SubjectService();

    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws IOException {

        resp.setContentType("application/json");
        resp.getWriter().write(service.getAllSubjects());
    }
}
```

👉 Controller **does NOT read files**
👉 Controller **does NOT contain business logic**



## 2️⃣ Service layer

**Business logic lives here**

```java
public class SubjectService {

    private SubjectRepository repo = new SubjectRepository();

    public String getAllSubjects() {
        return repo.readSubjectsFromFile();
    }
}
```


## 3️⃣ Repository (File I/O)

**Only responsibility: data access**

```java
public class SubjectRepository {

    public String readSubjectsFromFile() {
        try (InputStream is = getClass()
                .getClassLoader()
                .getResourceAsStream("data/subjects.json")) {

            return new String(is.readAllBytes());
        } catch (Exception e) {
            return "[]";
        }
    }
}
```



## Why this approach works (mentor mindset)

* ✔ Easy to understand for learners
* ✔ Clear separation of responsibility
* ✔ Can later replace JSON with:

  * JDBC
  * Hibernate / JPA
  * REST API
* ✔ Same structure works for **Servlets → Spring Boot**



## How this maps to what you heard in the call

* **Controller** → Servlet (AssessmentController / SubjectController)
* **Service** → Ravi Tambade style business layer
* **Repository** → File / JDBC / Hibernate
* **resources/data** → JSON question bank
* **MVP friendly** → Simple, readable, teachable



## Mentor takeaway 

> First **clarity**, then **architecture**, then **frameworks**.

Once learners understand this flow:

* Servlets make sense
* Spring MVC feels natural
* Hibernate is no longer scary

 



## 1️⃣ What we are fixing first: Manual object creation

👉 **Clean, simplified explanation of Dependency Injection + Spring + JSON (File I/O) + JDBC option**
👉 In the same **Transflower Learning teaching style** — step-by-step, calm, practical, no noise.
 
Earlier we had things like:

```java
SubjectService service = new SubjectService();
```

or inside service:

```java
SubjectRepository repo = new SubjectRepository();
```

❌ Problem:

* Tight coupling
* Hard to change implementation
* Not test-friendly

## 2️⃣ Dependency Injection (DI) — idea only (no Spring yet)

**Simple rule:**

> *Don’t create objects yourself. Ask for them.*

### Constructor Injection (plain Java)

```java
public class SubjectService {

    private SubjectRepository repository;

    public SubjectService(SubjectRepository repository) {
        this.repository = repository;
    }

    public List<Subject> getAllSubjects() {
        return repository.getAllSubjects();
    }
}
```

Controller now decides *what implementation to give*.


## 3️⃣ Now bring Spring into the picture 🌱

Spring will:

* Create objects
* Inject dependencies
* Manage lifecycle

You **stop writing `new` keyword**.


## 4️⃣ Spring Boot Structure (clean & standard)

```
com.tfl.assessment
 ├── controller
 ├── service
 ├── repository
 ├── model
 └── AssessmentApplication.java
```


## 5️⃣ Model (Subject)

```java
public class Subject {
    private int id;
    private String title;

    // getters & setters
}
```


## 6️⃣ Repository Interface (important concept)

```java
public interface SubjectRepository {
    List<Subject> getAllSubjects();
    void addSubject(Subject subject);
    void deleteSubject(int id);
}
```

👉 Interface = **contract**
👉 Implementation can change (JSON / JDBC / Hibernate)



## 7️⃣ JSON File Repository (File I/O + Jackson)

### Dependency (pom.xml)

```xml
<dependency>
  <groupId>com.fasterxml.jackson.core</groupId>
  <artifactId>jackson-databind</artifactId>
</dependency>
```


### Repository Implementation

```java
@Repository
public class SubjectFileRepository implements SubjectRepository {

    private ObjectMapper mapper = new ObjectMapper();

    @Override
    public List<Subject> getAllSubjects() {
        try {
            File file = new File("src/main/resources/data/subjects.json");
            return mapper.readValue(
                file,
                new TypeReference<List<Subject>>() {}
            );
        } catch (Exception e) {
            return new ArrayList<>();
        }
    }
}
```

- ✔ File I/O
- ✔ JSON serialization
- ✔ Jackson `ObjectMapper`

## 8️⃣ Service Layer (Spring-managed)

```java
@Service
public class SubjectService {

    private SubjectRepository repository;

    @Autowired
    public SubjectService(SubjectRepository repository) {
        this.repository = repository;
    }

    public List<Subject> getAllSubjects() {
        return repository.getAllSubjects();
    }
}
```

- 👉 No `new`
- 👉 Spring injects repository automatically

## 9️⃣ Controller (REST API)

```java
@RestController
@RequestMapping("/api/subjects")
public class SubjectController {

    private SubjectService service;

    @Autowired
    public SubjectController(SubjectService service) {
        this.service = service;
    }

    @GetMapping
    public List<Subject> getSubjects() {
        return service.getAllSubjects();
    }
}
```

## 🔁 Flow (very important for learners)

```
HTTP Request
   ↓
Controller
   ↓
Service
   ↓
Repository
   ↓
JSON file / DB
```

## 🔄 Want JDBC instead of JSON? No problem.

Create **another implementation**:

```java
@Repository
public class SubjectJdbcRepository implements SubjectRepository {

    @Override
    public List<Subject> getAllSubjects() {
        // JDBC code
        // Connection
        // PreparedStatement
        // ResultSet
        return subjects;
    }
}
```

- 👉 Service & Controller remain unchanged
- 👉 **This is real flexibility**

 

## 10️⃣  takeaway (this is the gold 🥇)

* DI is **not about Spring**
* Spring is **just a helper**
* Real power is:

  * Interfaces
  * Loose coupling
  * Replaceable implementations
* File → JDBC → Hibernate → API
  **same architecture**

 

## Transflower Learning philosophy 🌼

> *First clarity, then structure, then frameworks.*

Once students understand:

* Controller ≠ Service ≠ Repository
* DI removes `new`
* JSON is just data, not magic

They stop fearing:

* Spring
* Hibernate
* Microservices


 

## 1️⃣ Why not Excel as a Question Bank source?

**Short answer:**
You *can* use Excel — but it’s **not the right first teaching step**.

### Excel problems (for beginners & MVPs)

* ❌ Extra library (Apache POI) → more confusion
* ❌ Heavy I/O & memory usage
* ❌ Not web-friendly
* ❌ Hard to version-control
* ❌ Not production-like

### Excel is good when:

* Business uploads bulk data
* Admin tools
* Reporting / export

### But for learning & APIs:

✅ **JSON wins**

 

## 2️⃣ Why JSON in `resources/data` is ideal 🌱

* Human-readable
* Web-native
* Version-controlled
* Easy serialization
* Same structure as REST responses

> JSON teaches **how backend actually talks to frontend**

That’s why Transflower uses it 👌

 

## 3️⃣ Where exactly is the file stored?

```
src/main/resources/data/subjects.json
```

⚠️ Important:

* **Never use absolute paths**
* Always load via **ClassLoader**

 

## 4️⃣ Correct way to read resource file (no `/`, no `./`)

```java
InputStream is = getClass()
        .getClassLoader()
        .getResourceAsStream("data/subjects.json");
```

- ✔ Works in IDE
- ✔ Works in JAR
- ✔ Works on server

 

## 5️⃣ Full flow (this is the key explanation students need)

### Step-by-step flow

```
Browser
  ↓
/api/subjects
  ↓
Controller
  ↓
Service
  ↓
Repository
  ↓
JSON File
  ↓
ObjectMapper
  ↓
List<Subject>
  ↓
JSON Response
```

## 6️⃣ What exactly happens inside Repository?

### Code logic (plain English)

1. Spring creates repository object
2. Repository loads `subjects.json` as stream
3. Jackson `ObjectMapper`:

   * Reads JSON
   * Converts it into `List<Subject>`
4. Repository returns list
5. Service forwards it
6. Controller returns response


## 7️⃣ ObjectMapper – the heart of serialization

```java
List<Subject> subjects =
    mapper.readValue(
        inputStream,
        new TypeReference<List<Subject>>() {}
    );
```

### Meaning:

* **1st parameter** → *From where* (file/stream)
* **2nd parameter** → *Into what type* (List of Subject)

- JSON ➜ Java Object
- Java Object ➜ JSON (automatic in Spring)


## 8️⃣ Why NOT database first?

Learners always ask:

> “What’s DB connection now?”

Answer:

* DB is **next step**
* First understand:

  * Flow
  * Layers
  * DI
  * Serialization

After that:

* JDBC
* Hibernate
* JPA
  are **easy upgrades**


## 9️⃣ Same interface → different data source

| Implementation   | Use case          |
| ---------------- | ----------------- |
| JSON Repository  | Learning / MVP    |
| JDBC Repository  | SQL understanding |
| JPA Repository   | Enterprise        |
| Excel Repository | Import only       |

👉 **Controller & Service never change**

That’s real architecture.

 

## 🔟 How to explain this to students (mentor script)

> “We start with JSON because it teaches backend thinking.
> Once you understand the flow, the data source can be anything — Excel, DB, API.”

 

## Final mentor takeaway 🌼

* Excel is a **tool**, not a foundation
* JSON builds **correct mental models**
* DI + interfaces = freedom
* Same code scales from:

  * File → DB → Cloud

 

## When Spring Boot is running… what is *really* happening?

Think of Spring Boot as a **very smart receptionist + factory**.

You hit a URL, and Spring says:

> “Okay, I know exactly *who* should handle this, *what* objects are needed, and *how* to convert data.”

 

## 1️⃣ App start time (this happens only once)

When you run:

```java
@SpringBootApplication
public class AssessmentApplication
```

Spring Boot does **a lot automatically**:

### Internally Spring does:

* Scans packages
* Finds classes with:

  * `@Controller / @RestController`
  * `@Service`
  * `@Repository`
* Creates objects (beans)
* Stores them in **Application Context** (container)

👉 This is why you **don’t write `new`**

 

## 2️⃣ Resource files (JSON) — how Spring sees them

Your file:

```
src/main/resources/data/subjects.json
```

At runtime:

* Spring **packs it inside the classpath**
* Not a normal file anymore
* That’s why we use:

```java
getClass()
  .getClassLoader()
  .getResourceAsStream("data/subjects.json");
```

📌 Important:

* No `/`
* No `./`
* Because it’s **inside the JAR**

 

## 3️⃣ Now you hit the API

Example:

```
GET /api/subjects
```

### What happens step-by-step 👇

 

## 4️⃣ DispatcherServlet (the real boss)

Spring has **one front controller**:

> 🧠 `DispatcherServlet`

Every HTTP request goes here first.

```
Browser
  ↓
DispatcherServlet
```

 

## 5️⃣ How does Spring know which method to call?

Because of annotations 👇

```java
@GetMapping("/api/subjects")
```

Spring already built a **mapping table** at startup:

```
GET /api/subjects → SubjectController.getAllSubjects()
```

No magic. Just mapping.

 

## 6️⃣ Controller method is called

```java
@GetMapping
public List<Subject> getAllSubjects() {
    return service.getAllSubjects();
}
```

At this moment:

* Controller object already exists
* Service object already injected
* Repository already injected

👉 **No object creation now**
👉 Only method calls

 

## 7️⃣ Service → Repository → JSON

Repository logic:

1. Load JSON as InputStream
2. `ObjectMapper.readValue(...)`
3. Convert JSON → `List<Subject>`
4. Return list

This is **deserialization**.

 

## 8️⃣ Returning data — who converts Java → JSON?

Very important point 👇

You **did NOT write JSON conversion code in controller**.

Spring does this automatically using:

* **Jackson**
* **HttpMessageConverter**

### Internally:

```
List<Subject>
   ↓
Jackson ObjectMapper
   ↓
JSON
```

This is **serialization**.

 

## 9️⃣ Response goes back to browser

Spring:

* Sets `Content-Type: application/json`
* Writes JSON to response body
* Sends HTTP 200

You never touched:

* `PrintWriter`
* `response.getWriter()`

Spring handled it.

  

## 🔁 Same flow for POST, PUT, DELETE

Example:

```java
@PostMapping
public void addSubject(@RequestBody Subject subject)
```

Spring automatically:

1. Reads request body JSON
2. Converts JSON → `Subject`
3. Calls method
4. Saves data (file / DB)
5. Returns response

👉 This is **deserialization again**

## 10️⃣ Why learners feel “Spring is doing magic”

Because Spring:

* Hides boilerplate
* Automates wiring
* Manages lifecycle

But internally it’s still:

* Java objects
* Method calls
* Maps
* Reflection



## Mentor explanation (this line always works 👇)

> “Spring Boot doesn’t add logic.
> It only **removes plumbing**.”


## One-line mental model 🧠

```
Spring Boot = Java + DI + HTTP mapping + JSON conversion
```

Nothing more.



## Final Transflower takeaway 🌼

* JSON file → learning data source
* ObjectMapper → data translator
* DispatcherServlet → traffic police
* Annotations → configuration, not magic
* Same flow works for:

  * File
  * Excel
  * Database
  * Cloud API

We’re **absolutely on the right track** by slowing this down and understanding the *why*, not just the *what*.


# Servlet vs Spring Boot — Same Use Case

### Use case

👉 **Get all subjects from JSON and return as JSON**

---

## 1️⃣ Servlet-based implementation (Old school)

### Controller (Servlet)

```java
@WebServlet("/api/subjects")
public class SubjectServlet extends HttpServlet {

    private SubjectService service = new SubjectService();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws IOException {

        List<Subject> subjects = service.getAllSubjects();

        ObjectMapper mapper = new ObjectMapper();
        String json = mapper.writeValueAsString(subjects);

        resp.setContentType("application/json");
        resp.getWriter().write(json);
    }
}
```

### What YOU must do

* Create servlet
* Map URL
* Create service object
* Convert Java → JSON
* Write response manually


## 2️⃣ Spring Boot implementation (Modern)

### Controller

```java
@RestController
@RequestMapping("/api/subjects")
public class SubjectController {

    private final SubjectService service;

    public SubjectController(SubjectService service) {
        this.service = service;
    }

    @GetMapping
    public List<Subject> getAllSubjects() {
        return service.getAllSubjects();
    }
}
```

### What Spring does for you

* URL mapping
* Object creation (DI)
* JSON serialization
* Response writing
* Content-Type headers



## 3️⃣ Side-by-side comparison

| Concern          | Servlet                 | Spring Boot         |
| ---------------- | ----------------------- | ------------------- |
| Entry point      | `HttpServlet`           | `DispatcherServlet` |
| URL mapping      | `@WebServlet` / web.xml | `@GetMapping`       |
| Object creation  | Manual (`new`)          | DI (`@Autowired`)   |
| JSON conversion  | Manual `ObjectMapper`   | Automatic           |
| Response writing | `getWriter()`           | Automatic           |
| Boilerplate      | High                    | Very low            |
| Testability      | Hard                    | Easy                |



## 4️⃣ Request flow comparison (visual)

### Servlet flow

```
Browser
  ↓
Servlet Container (Tomcat)
  ↓
SubjectServlet.doGet()
  ↓
Service
  ↓
Repository
  ↓
JSON
```



### Spring Boot flow

```
Browser
  ↓
DispatcherServlet
  ↓
Controller
  ↓
Service
  ↓
Repository
  ↓
JSON
```

- 👉 Same flow
- 👉 Different **who handles plumbing**



## 5️⃣ JSON handling difference (key learning point)

### Servlet

```java
ObjectMapper mapper = new ObjectMapper();
mapper.writeValueAsString(data);
```

### Spring Boot

```java
return data;
```

🧠 Spring internally uses:

* Jackson
* HttpMessageConverters



## 6️⃣ POST example (important demo)

### Servlet

```java
@Override
protected void doPost(HttpServletRequest req, HttpServletResponse resp)
        throws IOException {

    ObjectMapper mapper = new ObjectMapper();
    Subject subject = mapper.readValue(req.getInputStream(), Subject.class);

    service.addSubject(subject);
}
```

### Spring Boot

```java
@PostMapping
public void addSubject(@RequestBody Subject subject) {
    service.addSubject(subject);
}
```



## 7️⃣ Why Spring Boot feels “automatic”

Because Spring:

* Preconfigures:

  * Tomcat
  * Jackson
  * DI
* Detects annotations
* Builds mapping tables at startup

> Nothing happens at runtime by surprise.



## 8️⃣ How to explain this to students (one killer line)

> “If you know Servlets, Spring Boot is **not new** — it just saves your time.”



## 9️⃣ When to teach Servlets vs Spring Boot

### Teach Servlets when:

* Explaining HTTP basics
* Request / response lifecycle
* Web fundamentals

### Teach Spring Boot when:

* Building APIs
* Real-world apps
* Microservices
* Industry-ready skills



## Final mentor takeaway 🌱

* Servlets = **foundation**
* Spring Boot = **productivity**
* Same Java
* Same HTTP
* Same architecture


# 1️⃣ Big Picture: Servlet vs Spring Boot

```
           USER / BROWSER
                  |
                  v
             HTTP REQUEST
```


## Servlet-based Flow

```
+----------------------+
|   Browser / Client   |
+----------------------+
            |
            v
+----------------------+
|     Tomcat Server    |
+----------------------+
            |
            v
+----------------------+
|   SubjectServlet     |
|  (doGet / doPost)    |
+----------------------+
            |
            v
+----------------------+
|   SubjectService     |
+----------------------+
            |
            v
+----------------------+
| SubjectRepository    |
| (File / DB / JSON)   |
+----------------------+
            |
            v
+----------------------+
|   ObjectMapper       |
| (Java -> JSON)       |
+----------------------+
            |
            v
+----------------------+
| HttpServletResponse  |
| getWriter().write()  |
+----------------------+
            |
            v
         HTTP RESPONSE
```

👉 **You control everything manually**


## Spring Boot Flow

```
+----------------------+
|   Browser / Client   |
+----------------------+
            |
            v
+----------------------+
|  DispatcherServlet   |
|  (Front Controller)  |
+----------------------+
            |
            v
+----------------------+
| SubjectController    |
|   (@GetMapping)      |
+----------------------+
            |
            v
+----------------------+
|   SubjectService     |
+----------------------+
            |
            v
+----------------------+
| SubjectRepository    |
| (File / DB / JSON)   |
+----------------------+
            |
            v
+----------------------+
|   HttpMessageConv.   |
|   (Jackson)          |
+----------------------+
            |
            v
         HTTP RESPONSE
```

👉 **Spring handles plumbing**

 

# 2️⃣ Object Creation Difference (VERY important)

## Servlet

```
SubjectServlet
    |
    +--> new SubjectService()
             |
             +--> new SubjectRepository()
```

- ❌ Tight coupling
- ❌ Hard to test

---

## Spring Boot (DI)

```
Spring Container
    |
    +--> SubjectController
            |
            +--> SubjectService
                    |
                    +--> SubjectRepository
```

- ✔ Loose coupling
- ✔ Replaceable implementations



# 3️⃣ JSON Handling Comparison

## Servlet

```
List<Subject>
      |
      v
ObjectMapper
      |
      v
JSON String
      |
      v
response.getWriter()
```


## Spring Boot

```
List<Subject>
      |
      v
HttpMessageConverter
      |
      v
JSON (automatic)
```



# 4️⃣ POST Request Flow (Deserialization)

## Spring Boot

```
JSON Request Body
        |
        v
HttpMessageConverter
        |
        v
Subject Object
        |
        v
Controller Method
```



# 5️⃣ Resource File Loading (JSON)

```
subjects.json
     |
     v
Classpath (resources)
     |
     v
ClassLoader
     |
     v
InputStream
     |
     v
ObjectMapper.readValue()
     |
     v
List<Subject>
```

 

# 6️⃣ One-line mental model 🧠

```
Servlet = You do everything
Spring Boot = You do business logic
```

 

# 7️⃣ Teaching tip (Transflower gold 🌼)


> “Spring Boot is built **on top of Servlets**.
> If you remove Spring, the Servlet is still there.”


 

