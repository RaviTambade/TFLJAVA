

# 🚀 Java Spring Boot Application + Docker – Complete Flow

You are using:

* Java
* Maven (`pom.xml`)
* Spring Boot
* MySQL (TCP connection)
* Dockerfile
* Docker Desktop



# 1️⃣ Project Structure (Spring Boot)

Typical project:

```
sample-docker/
 ├── pom.xml
 ├── src/
 │   └── main/
 │        ├── java/
 │        │     └── com/example/demo/
 │        │           ├── DemoApplication.java
 │        │           └── controller/
 │        │                └── HelloController.java
 │        └── resources/
 │              └── application.properties
 └── Dockerfile
```


# 2️⃣ Build JAR File (Maven)

Inside project folder:

```bash
mvn clean package
```

After build:

```
target/demo-0.0.1-SNAPSHOT.jar
```

This JAR is what Docker will run.



# 3️⃣ Sample Dockerfile (Spring Boot)

```dockerfile
FROM openjdk:17-jdk-slim

WORKDIR /app

COPY target/demo-0.0.1-SNAPSHOT.jar app.jar

EXPOSE 8080

ENTRYPOINT ["java", "-jar", "app.jar"]
```

# 4️⃣ Build Docker Image

Inside project folder (where Dockerfile exists):

```bash
docker build -t sample-docker .
```

Check images:

```bash
docker images
```

# 5️⃣ Run Container

If app runs on port 8080:

```bash
docker run -p 9090:8080 sample-docker
```

Meaning:

```
Host Port : Container Port
9090      : 8080
```

Access in browser:

```
http://localhost:9090/hello
```

If controller:

```java
@GetMapping("/hello")
public String hello() {
    return "Hello from Container";
}
```

# 6️⃣ Check Running Containers

```bash
docker ps
```

Shows:

* Container ID
* Image Name
* Ports
* Status

# 7️⃣ Stop / Kill Container

Graceful stop:

```bash
docker stop <container-id>
```

Force kill:

```bash
docker kill <container-id>
```

# 8️⃣ Remove Container

```bash
docker rm <container-id>
```

# 9️⃣ Remove Image

```bash
docker rmi sample-docker
```

If error:

```
image is being used by container
```

Force remove:

```bash
docker rmi -f sample-docker
```

# 🔟 Full Docker Lifecycle (Very Important for Students)

```
Source Code
   ↓
mvn clean package
   ↓
JAR File
   ↓
docker build
   ↓
Docker Image
   ↓
docker run
   ↓
Container Instance
```



### 🟢 Image

Blueprint (like class in Java)

### 🟢 Container

Running object (instance of image)

### 🟢 Port Mapping

```
Host → Container
9090 → 8080
```

### 🟢 docker ps

Shows running containers

### 🟢 docker images

Shows available images

 

# ⚠️ Common Mistakes Students Make

1. Wrong port mapping
2. JAR name mismatch in Dockerfile
3. Not running `mvn clean package`
4. MySQL not running
5. application.properties wrong DB config
6. Container already using port

 
# 🧠 Teaching Tip (Mentor Mode)

 

* What happens if I delete container?
* What happens if I delete image?
* What happens if I change code?
* Do I need to rebuild image?
* Is container state persistent?

This builds DevOps thinking.

 

# 🏗 Architecture View (Explain Like This)

```
Browser → localhost:9090
        ↓
Docker Engine
        ↓
Container (Spring Boot App)
        ↓
MySQL (TCP Connection)
```

 

# 🔥 Advanced Level Question for Undergraduate

* Why do we expose 8080?
* Why use WORKDIR?
* Why copy JAR not source code?
* Difference between docker stop and docker kill?
* What is ENTRYPOINT?

  

# 🧹 When You Remove Everything

If you run:

```bash
docker rm -f <container-id>
docker rmi -f <image-id>
```

Then:

```bash
docker ps
docker images
```

You will see:

* ❌ No containers
* ❌ No images

Docker Desktop is completely clean.

That means:

- 👉 Docker Engine is running
- 👉 But no images stored
- 👉 No containers running

 

# 🧠 Very Important Concept (Explain Like This)

## 🟢 Image = EXE file

## 🟢 Container = Running Process

Exactly like:

```text
program.exe → double click → process created
```

Similarly:

```text
Docker Image → docker run → Container created
```

You can create:

* 1 image
* 10 containers from same image

That is used for:

* Load balancing
* Handling 3000+ requests
* Scaling microservices

 

# 🐳 Docker vs Docker Hub

## 🟦 Docker

* Installed on your machine
* Background service (daemon)
* Builds images
* Runs containers

Think:

> Docker = Runtime Engine

  

## 🟦 Docker Hub

Website:

👉 [https://hub.docker.com](https://hub.docker.com)

It is:

* Cloud repository
* Stores public container images
* Central marketplace

Like:

| Tool   | Central Repo  |
| ------ | ------------- |
| Git    | GitHub        |
| Maven  | Maven Central |
| Docker | Docker Hub    |

  

# 📥 Pulling Images

Example:

```bash
docker pull hello-world
```

Downloads from Docker Hub.

Check:

```bash
docker images
```

  
## Run It

```bash
docker run hello-world
```

It prints:

```
Hello from Docker!
```

That means:

- ✅ Docker Engine working
- ✅ Internet connection working
- ✅ Image downloaded successfully


# 🧱 Pulling Real Server Images

## 🟢 MySQL

```bash
docker pull mysql
```

## 🟢 NGINX

```bash
docker pull nginx
```

## 🟢 Tomcat

```bash
docker pull tomcat
```

No need to install software manually anymore.

Companies now:

```
Linux + Docker
Pull images
Run containers
Done
```

  

# 🔍 Checking Docker Version

```bash
docker -v
```

Example output:

```
Docker version 28.x.x
```

  

# 🧠 Checking Docker Service (Windows)

In Linux:

```bash
systemctl status docker
```

But in Windows:

### Method 1: Services

1. Press `Windows + R`
2. Type:

```
services.msc
```

3. Find:

```
Docker Desktop Service
```

Check:

* Status = Running
* Startup Type = Automatic / Manual

If stopped → Right click → Start

  

### Method 2: Task Manager

Open:

```
Ctrl + Shift + Esc
```

Look for:

```
Docker Desktop
Docker Engine
```

### Method 3: Command Line Test

If this works:

```bash
docker ps
```

Then Docker is running.

If error:

```
Cannot connect to Docker daemon
```

Then Docker service is not running.


# 📌 Important Docker Commands (Revision)

| Command       | Meaning            |
| ------------- | ------------------ |
| docker images | List images        |
| docker ps     | Running containers |
| docker ps -a  | All containers     |
| docker run    | Create container   |
| docker stop   | Stop container     |
| docker kill   | Force stop         |
| docker rm     | Remove container   |
| docker rmi    | Remove image       |
| docker pull   | Download image     |
| docker build  | Create image       |



# 🔥 Advanced Understanding 


1. If I delete image, will container run?
   ❌ No

2. If I delete container, is image deleted?
   ❌ No

3. Can multiple containers use same image?
   ✅ Yes

4. Where are images stored?
   👉 Inside Docker Engine storage



# 🏗 Scaling Concept (Very Important)

If 3000 users hit your Spring Boot app:

Instead of:

```
1 container
```

You create:

```
5 containers from same image
```

Then use:

* NGINX
* Load balancer
* Kubernetes

This is modern architecture.


# 🧠 Final Mental Model 

```
Docker Hub → Image → Container → Running App
```

Image = Blueprint
Container = Running machine



# 🖥 Windows Services Concept (Very Important Foundation)

When you opened:

```text
services.msc
```

You saw:

* ✅ Docker Desktop Service
* ✅ MySQL80

These are:

> Background services (daemon processes)

## 🔹 Docker Desktop Service

If stopped → Docker commands will not work
If running → `docker ps`, `docker images` work

So:

```text
Docker Engine must run in background
```


## 🔹 MySQL80 Service

This is:

```text
MySQL Database Server running as Windows Service
```

Important concept:

| What You See    | What It Actually Is        |
| --------------- | -------------------------- |
| MySQL Workbench | GUI Application            |
| MySQL80         | Background Database Server |

Workbench = client
MySQL80 = actual DB engine


# 🎯 Now Moving to Real Project

You said:

> Create Dockerized Spring Boot Application with Products Controller using JSON Repository and CRUD operations.

Perfect 👌

Let’s structure a clean architecture.


# 🏗 Layered Architecture 

```text
Controller  →  Service  →  Repository
```

Or:

```text
Client (Postman)
        ↓
ProductController
        ↓
ProductService
        ↓
ProductRepository
        ↓
Database (or JSON storage)
```


# 📁 Project Structure

```text
product-api/
 ├── pom.xml
 ├── Dockerfile
 └── src/main/java/com/example/product/
      ├── ProductApplication.java
      ├── controller/
      │     └── ProductController.java
      ├── service/
      │     ├── ProductService.java
      │     └── ProductServiceImpl.java
      ├── repository/
      │     └── ProductRepository.java
      ├── model/
      │     └── Product.java
      └── exception/
            └── ProductNotFoundException.java
```

# 🧱 pom.xml Dependencies

```xml
<dependencies>

    <dependency>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-web</artifactId>
    </dependency>

    <dependency>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-data-jpa</artifactId>
    </dependency>

    <dependency>
        <groupId>com.h2database</groupId>
        <artifactId>h2</artifactId>
        <scope>runtime</scope>
    </dependency>

    <dependency>
        <groupId>org.projectlombok</groupId>
        <artifactId>lombok</artifactId>
        <optional>true</optional>
    </dependency>

</dependencies>
```

# 📦 Product Entity

```java
@Entity
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private double price;
}
```


# 📂 Repository

```java
@Repository
public interface ProductRepository 
        extends JpaRepository<Product, Long> {
}
```


# ⚙ Service Layer

### Interface

```java
public interface ProductService {
    List<Product> getAllProducts();
    Product getProductById(Long id);
    Product saveProduct(Product product);
    void deleteProduct(Long id);
}
```

### Implementation

```java
@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductRepository repository;

    public List<Product> getAllProducts() {
        return repository.findAll();
    }

    public Product getProductById(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new ProductNotFoundException("Not found"));
    }

    public Product saveProduct(Product product) {
        return repository.save(product);
    }

    public void deleteProduct(Long id) {
        repository.deleteById(id);
    }
}
```

# 🎮 Controller

```java
@RestController
@RequestMapping("/products")
public class ProductController {

    @Autowired
    private ProductService service;

    @GetMapping
    public List<Product> getAll() {
        return service.getAllProducts();
    }

    @PostMapping
    public Product create(@RequestBody Product product) {
        return service.saveProduct(product);
    }
}
```


# 🐳 Dockerfile

```dockerfile
FROM openjdk:17-jdk-slim

WORKDIR /app

COPY target/product-api-0.0.1-SNAPSHOT.jar app.jar

EXPOSE 8080

ENTRYPOINT ["java","-jar","app.jar"]
```


# 🔨 Build & Run

### 1️⃣ Build JAR

```bash
mvn clean package
```

### 2️⃣ Build Image

```bash
docker build -t product-api .
```

### 3️⃣ Run Container

```bash
docker run -p 9090:8080 product-api
```

Test:

```text
http://localhost:9090/products
```



# 🧠 Important Teaching Concept


* services.msc → Windows Service
* Docker Desktop → background daemon
* MySQL80 → background DB

Now connect that concept:

```text
Spring Boot App inside Docker
            ↓
Runs as Container
            ↓
Container = isolated process
            ↓
Docker Engine manages it
```


# 🎯 Critical Teaching Moment

Ask students:

1. Is Spring Boot running as Windows Service?
   ❌ No

2. Is Docker running as Windows Service?
   ✅ Yes

3. Can MySQL run as Windows Service?
   ✅ Yes

4. Can MySQL run as Docker container instead?
   ✅ Yes (Modern approach)



# 🔥 Next Level (Very Powerful Demo)

Instead of Windows MySQL:

```bash
docker run -d -p 3307:3306 --name mysql-container -e MYSQL_ROOT_PASSWORD=root mysql
```

Now DB also containerized.

This is modern DevOps.



# 🧠 Final Architecture View

```text
Postman
   ↓
Spring Boot Container
   ↓
MySQL Container
   ↓
Docker Engine
   ↓
Windows OS
```



# 🧠 1️⃣ What You Are Actually Building

Not JPA.
Not MySQL.

You are building:

```text
File-based Repository using JSON
```

So your flow becomes:

```text
Controller
   ↓
Service (Interface)
   ↓
ServiceImpl
   ↓
JSON Repository
   ↓
products.json file
```


# 🧱 2️⃣ Proper Maven Folder Structure (Very Important)

It MUST be like this:

```text
product-app/
 ├── pom.xml
 ├── Dockerfile
 └── src/
      └── main/
           ├── java/
           │    └── com/example/product/
           │         ├── ProductApplication.java
           │         ├── controller/
           │         ├── service/
           │         ├── repository/
           │         ├── model/
           │         └── exception/
           └── resources/
                ├── application.properties
                └── data/
                     └── products.json
```

⚠ IMPORTANT:

`products.json` must be inside:

```
src/main/resources/data/products.json
```

NOT randomly in workspace root.


# 🧩 3️⃣ ObjectMapper-Based JSON Repository

Now the important part you were discussing:

* loadFromFile()
* saveToFile()
* insert
* update
* delete
* findAll
* findById

Here is the clean architecture:


## 📦 ProductRepository (Custom JSON Repository)

```java
@Repository
public class ProductRepository {

    private final String FILE_PATH = "src/main/resources/data/products.json";

    private final ObjectMapper objectMapper = new ObjectMapper();

    public List<Product> findAll() throws IOException {
        return loadFromFile();
    }

    public Product save(Product product) throws IOException {
        List<Product> products = loadFromFile();
        products.add(product);
        saveToFile(products);
        return product;
    }

    private List<Product> loadFromFile() throws IOException {
        File file = new File(FILE_PATH);
        if (!file.exists()) {
            return new ArrayList<>();
        }
        return objectMapper.readValue(file,
                new TypeReference<List<Product>>() {});
    }

    private void saveToFile(List<Product> products) throws IOException {
        objectMapper.writeValue(new File(FILE_PATH), products);
    }
}
```


# 🧠 4️⃣ Why Service Interface?

You asked:

> Why ProductService.java interface?

Because architecture rule:

```text
Controller should depend on abstraction
NOT implementation
```

So:

```java
public interface ProductService {
    List<Product> getAll();
    Product save(Product product);
}
```

Then:

```java
@Service
public class ProductServiceImpl implements ProductService {
}
```

This allows:

* Future DB migration
* Replace JSON with MySQL
* Unit testing with mocks
* Clean architecture

This is professional design.

# ⚠ 5️⃣ Common Errors You Faced

From your narration I can see:

### ❌ Package mismatch

Package name not matching folder structure.

Must match exactly:

```java
package com.example.product.service;
```

And folder must be:

```
com/example/product/service/
```



### ❌ JSON file path problem

If you hardcode:

```java
"src/main/resources/data/products.json"
```

It works in IDE but ❌ fails in Docker.

Because inside Docker:

```text
There is no src folder.
```

Better approach:

```java
InputStream inputStream =
    getClass().getClassLoader()
        .getResourceAsStream("data/products.json");
```

That works inside JAR.



# 🐳 6️⃣ Very Important Docker Insight

When you run:

```bash
mvn clean package
```

It creates:

```
target/product-app.jar
```

That JAR contains:

```
resources folder
including products.json
```

Then Dockerfile:

```dockerfile
FROM openjdk:17-jdk-slim

WORKDIR /app

COPY target/product-app.jar app.jar

EXPOSE 8080

ENTRYPOINT ["java","-jar","app.jar"]
```

Inside container:

```
No src folder
Only app.jar
```

So file access must be via classpath.

# 🔥 7️⃣ Why JSON File Not Updating in Docker?

Because:

JAR is read-only inside container.

If you want persistence:

You must mount volume:

```bash
docker run -p 9090:8080 \
-v C:/data:/app/data \
product-app
```

Now file persists outside container.

This is advanced concept 👌


# 🧠 8️⃣ Architect Clarity Moment

You said:

> I want to become architect and have clarity.

So remember this:

### Storage Options

| Level        | Storage Type     |
| ------------ | ---------------- |
| Beginner     | JSON file        |
| Intermediate | H2 DB            |
| Professional | MySQL/PostgreSQL |
| Cloud        | RDS / Cloud SQL  |
| Enterprise   | Distributed DB   |


# 🚀 9️⃣ If You Want Perfect Setup

Instead of JSON:

Use H2 in-memory DB.

Add in `application.properties`:

```
spring.datasource.url=jdbc:h2:mem:testdb
spring.h2.console.enabled=true
```

Much cleaner.

# 🎯 10️⃣ What Actually Happened Today?

You experienced:

* Package errors
* Maven structure correction
* Dependency issues
* Lombok problems
* JSON path problems
* Docker build issues
* Service layer clarity
* Repository abstraction clarity

This is real software engineering.


# 💡 Final Advice

When AI modifies code:

1. Always check package names
2. Always check pom.xml dependencies
3. Always verify folder structure
4. Always test before Docker build
5. Never blindly accept all changes

You said:

> Keep all changes

That is dangerous in real projects 😄


# 🔥 If You Want Next Level

We can now:

* Convert JSON repo → JPA repo
* Add MySQL container
* Use docker-compose
* Add Swagger
* Add logging
* Add proper exception handling
* Move to cloud-ready structure


# 🟢 1️⃣ What is Optional<Product> ?

You saw:

```java
Optional<Product> findById(Long id);
```

And you asked: What is Optional?

In Java 8+, `Optional<T>` is a container object that may or may not contain a value.

Instead of returning:

```java
Product product = null;
```

We return:

```java
Optional<Product>
```

Why?

Because it prevents:

❌ NullPointerException

Example:

```java
Optional<Product> product = repository.findById(id);

if(product.isPresent()) {
    return product.get();
}
```

Or better:

```java
return repository.findById(id)
        .orElseThrow(() -> new RuntimeException("Product not found"));
```

So Optional is:

```text
A safe wrapper for nullable values
```

It is NOT question mark operator.
It is NOT like JavaScript.

It is a Java container class.



# 🟢 2️⃣ Why JSON File Not Visible in Docker?

Very important.

When you run:

```bash
docker run -d -p 8000:8080 products-image
```

Your container has:

```
/app/app.jar
```

It does NOT have:

```
src/main/resources/
```

Because JAR contains resources internally.

So if you use:

```java
new File("src/main/resources/data/products.json")
```

It will fail inside Docker.

Correct way:

```java
InputStream inputStream = 
    getClass().getClassLoader()
        .getResourceAsStream("data/products.json");
```

That reads from inside JAR.


# 🟢 3️⃣ Why Data Disappears After Container Restart?

Because:

Container filesystem is temporary.

When container stops:

❌ Data lost

Solution:

Use Docker Volume.

Example:

```bash
docker run -d -p 8000:8080 \
-v C:/data:/app/data \
products-image
```

Now:

* Left side → your machine folder
* Right side → container folder

Data persists.

# 🟢 4️⃣ Now Moving to JDBC + MySQL

You said:

> JDBC connection operator apply MySQL connection

Very good. Now architecture changes.

Instead of:

```text
JSON file
```

We use:

```text
MySQL database
```

Now you have 2 options:


## Option A: MySQL Installed on Windows

In application.properties:

```properties
spring.datasource.url=jdbc:mysql://localhost:3306/productsdb
spring.datasource.username=root
spring.datasource.password=root
```

Works if MySQL80 service is running.


## Option B (Professional): MySQL in Docker

Run MySQL container:

```bash
docker run -d \
--name mysql-container \
-e MYSQL_ROOT_PASSWORD=root \
-e MYSQL_DATABASE=productsdb \
-p 3307:3306 \
mysql
```

Now connection string changes:

```properties
spring.datasource.url=jdbc:mysql://mysql-container:3306/productsdb
```

BUT this works only if both containers are in same Docker network.

# 🟢 5️⃣ Best Professional Way: docker-compose

Instead of manually running two containers.

Create:

```yaml
version: '3.8'

services:

  mysql:
    image: mysql
    container_name: mysql-container
    environment:
      MYSQL_ROOT_PASSWORD: root
      MYSQL_DATABASE: productsdb
    ports:
      - "3307:3306"

  app:
    build: .
    container_name: products-app
    ports:
      - "8000:8080"
    depends_on:
      - mysql
```

Then run:

```bash
docker-compose up
```

Now:

```text
Spring Boot Container
        ↓
MySQL Container
        ↓
Docker Network
```

No localhost confusion.


# 🟢 6️⃣ Important Concept: localhost Inside Docker

Very important architect clarity.

If Spring Boot runs inside Docker:

```text
localhost means → container itself
```

It does NOT mean your Windows machine.

So:

❌ jdbc:mysql://localhost:3306

Inside container → wrong.

You must use:

```
container-name
```

Or:

```
host.docker.internal
```

If connecting to Windows MySQL.


# 🟢 7️⃣ Your Real Confusion Today

You mixed:

* JSON file persistence
* Docker filesystem
* MySQL service
* JDBC connection
* Optional usage
* Package structure
* Lombok dependency

That’s normal during architecture transition.


# 🧠 8️⃣ Clean Mental Model

## If Using JSON

```text
Controller → Service → JSON Repository → File
```

## If Using MySQL

```text
Controller → Service → JPA Repository → MySQL
```

## If Using Docker

```text
Docker Engine
    ↓
Container (Spring Boot)
    ↓
Container (MySQL)
```

# 🟢 9️⃣ Lombok Dependency (Just for clarity)

In pom.xml:

```xml
<dependency>
    <groupId>org.projectlombok</groupId>
    <artifactId>lombok</artifactId>
    <optional>true</optional>
</dependency>
```

And enable annotation processing in IDE.


# 🔥 10️⃣ Architect Advice for You
 

Don’t jump between:

JSON → JDBC → Docker → MySQL → Optional → Lombok

One layer at a time.

I suggest:

Step 1: Make Spring Boot + MySQL working WITHOUT Docker
Step 2: Then Dockerize app
Step 3: Then Dockerize MySQL
Step 4: Then docker-compose

That is professional path.