# Installing Apache Tomcat Web Server

> “Students, now that we understand that Tomcat provides the runtime environment for Java web applications, the next question is simple: **How do we install Tomcat?**”

There are three common approaches:

```text
                    Apache Tomcat
                         │
          ┌──────────────┼──────────────┐
          │              │              │
          ▼              ▼              ▼
       Windows         Linux         Docker
       Manual          Server        Container
       Setup           Setup          Setup
```

The important thing is not just installing Tomcat.
**Learn how to verify that Tomcat is actually running.**


# 1️⃣ First — Install Java

Tomcat requires a compatible Java runtime.

Check whether Java is already installed:

```bash
java -version
```

Also check the compiler:

```bash
javac -version
```

If both commands work, you have a JDK installed. For development, I recommend installing a current **LTS JDK** such as Java 17 or Java 21, while checking the Tomcat version's compatibility requirements.

- 

# 2️⃣ Install Tomcat on Windows

## Step 1 — Download Tomcat

Download Apache Tomcat from the official Apache Tomcat website.

[Apache Tomcat official website](https://tomcat.apache.org/?utm_source=chatgpt.com)

Choose the Tomcat version appropriate for your application. For a traditional Servlet/JSP application, pay attention to the **Jakarta Servlet** version supported by the Tomcat release.

  

## Step 2 — Extract Tomcat

Suppose you download a ZIP archive. Extract it somewhere such as:

```text
C:\apache-tomcat\
```

You will see a structure similar to:

```text
C:\apache-tomcat\
│
├── bin\
├── conf\
├── lib\
├── logs\
├── temp\
├── webapps\
├── work\
└── README.md
```

### Mentor:

> “Students, don't randomly delete these folders. Each one has a job.”

For example:

| Folder    | Purpose                     |
| --------- | --------------------------- |
| `bin`     | Startup/shutdown scripts    |
| `conf`    | Tomcat configuration        |
| `lib`     | Tomcat libraries            |
| `logs`    | Log files                   |
| `webapps` | Deployable web applications |
| `work`    | Temporary/generated files   |



# Step 3 — Configure `JAVA_HOME`

Tomcat needs to know where Java is installed. On Windows, configure:

```text
JAVA_HOME
```

For example:

```text
JAVA_HOME=C:\Program Files\Java\jdk-21
```

Then verify:

```cmd
echo %JAVA_HOME%
```

You should see your JDK directory.

# Step 4 — Start Tomcat

Open Command Prompt. Move to Tomcat's `bin` directory:

```cmd
cd C:\apache-tomcat\bin
```

Start Tomcat:

```cmd
startup.bat
```

A Tomcat console window should appear.


# Step 5 — Open Tomcat in Browser

Open:

```text
http://localhost:8080
```

If Tomcat is running correctly, you should see the Tomcat welcome page.

```text
Browser
   │
   │ http://localhost:8080
   ▼
┌─────────────────┐
│     TOMCAT      │
│   Port : 8080   │
└─────────────────┘
```

🎉 **Your Tomcat server is running!**

# Step 6 — Stop Tomcat

Go back to the `bin` directory:

```cmd
cd C:\apache-tomcat\bin
```

Run:

```cmd
shutdown.bat
```


# 🐧 3️⃣ Install Tomcat on Linux

Now let's move from your laptop to a Linux server.

> 👨‍🏫 **Mentor:**
> “This is where your Java knowledge starts moving toward real deployment.”

## Step 1 — Check Java

```bash
java -version
```

If Java isn't installed, install an appropriate JDK using your distribution's package manager. For Ubuntu/Debian, for example:

```bash
sudo apt update
sudo apt install openjdk-21-jdk
```

Then:

```bash
java -version
```

# Step 2 — Download Tomcat

Download the desired Tomcat release from: [Apache Tomcat downloads](https://tomcat.apache.org/download-10.cgi?utm_source=chatgpt.com)

For example, after downloading the archive:

```bash
tar -xzf apache-tomcat-*.tar.gz
```

Move it to a suitable location:

```bash
sudo mv apache-tomcat-* /opt/tomcat
```

Now:

```text
/opt/tomcat/
│
├── bin/
├── conf/
├── lib/
├── logs/
├── webapps/
└── work/
```

# Step 3 — Start Tomcat

```bash
cd /opt/tomcat/bin
```

Give the scripts execute permission if necessary:

```bash
chmod +x *.sh
```

Start Tomcat:

```bash
./startup.sh
```

Check whether Tomcat is listening:

```bash
ss -ltnp | grep 8080
```

Then access:

```text
http://localhost:8080
```

For a remote Linux server:

```text
http://SERVER-IP:8080
```

Make sure your firewall/security group permits the required port.


# 4️⃣ Run Tomcat Using Docker

Now we enter the modern deployment world.

>  **Mentor:**
> “Instead of installing Java and Tomcat manually on every machine, what if I package the runtime environment?”

That's where **Docker** becomes useful.

```text
Developer
    │
    ▼
Docker Image
    │
    ▼
Tomcat + Java
    │
    ▼
Container
    │
    ▼
Java Web Application
```



# Step 1 — Install Docker

Install Docker Desktop on Windows/macOS or Docker Engine on Linux. Then verify:

```bash
docker --version
```

# Step 2 — Pull a Tomcat Image

You can obtain official Tomcat images from Docker Hub. [Tomcat images on Docker Hub](https://hub.docker.com/_/tomcat?utm_source=chatgpt.com)

For example:

```bash
docker pull tomcat:10-jdk21
```

Use a tag appropriate to the Tomcat/JDK combination you actually need.

# Step 3 — Run Tomcat

```bash
docker run -d \
  --name my-tomcat \
  -p 8080:8080 \
  tomcat:10-jdk21
```

Let's understand this command.

```text
docker run
     │
     ├── -d
     │     Run in background
     │
     ├── --name my-tomcat
     │     Container name
     │
     ├── -p 8080:8080
     │     Host port → Container port
     │
     └── tomcat:10-jdk21
           Tomcat image
```

# Step 4 — Check Container

```bash
docker ps
```

You should see something similar to:

```text
CONTAINER ID   IMAGE             PORTS
abc123         tomcat:10-jdk21   0.0.0.0:8080->8080/tcp
```

Now open:

```text
http://localhost:8080
```


# 📦 Step 5 — Deploy Your WAR into Docker Tomcat

Suppose your Maven project produces:

```text
target/student-web-app.war
```

You can copy it into Tomcat's deployment directory:

```bash
docker cp target/student-web-app.war my-tomcat:/usr/local/tomcat/webapps/
```

Tomcat will detect the WAR and deploy the application. You can then access something like:

```text
http://localhost:8080/student-web-app/
```

# Complete Learning Picture

Students, now connect everything we've learned:

```text
                    JAVA WEB DEVELOPMENT
                           │
                           ▼
                    Java Application
                           │
                           ▼
                     Servlet / JSP
                           │
                           ▼
                    WAR Application
                           │
              ┌────────────┼────────────┐
              │            │            │
              ▼            ▼            ▼
          Windows        Linux        Docker
              │            │            │
              └────────────┼────────────┘
                           │
                           ▼
                         TOMCAT
                           │
                           ▼
                    Servlet Container
                           │
                           ▼
                     HTTP Request
                           │
                           ▼
                        Servlet
                           │
                           ▼
                         JDBC
                           │
                           ▼
                       Database
```

# 🌻 Transflower Mentor's Checklist

Before saying **“Tomcat is installed”**, verify these five things:

```text
☑ Java installed
        ↓
☑ JAVA_HOME configured
        ↓
☑ Tomcat installed/image available
        ↓
☑ Tomcat started
        ↓
☑ http://localhost:8080 works
```

Then take the next step:

```text
Core Java
    ↓
JDBC
    ↓
Servlet
    ↓
JSP
    ↓
Tomcat
    ↓
Spring MVC
    ↓
Spring Boot
    ↓
REST API
    ↓
Docker
    ↓
Cloud Deployment 🚀
```

> **“Don't learn Tomcat just as an installation exercise. Understand why it exists. Your Java program learned to communicate with the database through JDBC. Now Tomcat teaches that program how to communicate with the web.”**