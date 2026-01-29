## *“How a Java Hospital System Learns to Respond Calmly”*

> “You are writing Java code for a hospital.
> Requests come as strings: `admit`, `bill`, `dosage`, `discharge`.”

I ask the class:

> “Will you write 50 `if-else` statements?”

Everyone laughs.
That’s our entry point.

## 🏥 Act 1: The Disease – If–Else Syndrome

I write on the board:

```java
if(command.equals("admit")) { ... }
else if(command.equals("bill")) { ... }
else if(command.equals("dosage")) { ... }
```

Then I ask:

> “Is this **object-oriented**, or just *procedural code wearing Java clothes*?”

That usually lands 😄

## 🧠 Act 2: Mental Shift – Java Is About **Behavior**

I say:

> “In Java, behavior is not written.
> Behavior is **assigned**.”

So instead of:

* *deciding* every time

We start:

* *registering* behavior once

## 🧭 Act 3: Hospital Characters (Java Version)

### 👨‍⚕️ HospitalService – The Specialist

> “Doctors don’t sit in reception.
> They sit in departments.”

This class:

* admits patients
* calculates bills
* decides dosage

It contains **business rules**, not routing.

### 🧾 Command Registry – The Reception Desk

> “Reception never treats patients.
> It only forwards requests.”

In Java, this becomes:

```
Map<String, Command>
```

A simple idea:

* Key → command name
* Value → executable behavior

### 📜 Command Interface – The Contract

Now I pause and ask:

> “How does reception talk to *any* doctor?”

Answer: **Interface**

```java
interface Command {
    int execute(int a, int b);
}
```

> “Reception doesn’t care *who* you are.
> Only that you can `execute`.”

That’s **polymorphism without inheritance chains**.


## 🪄 Act 4: Binding Doctors (Java Style)

I explain:

> “In C++ we used `std::bind`.
> In Java, we use **method references** or **lambdas**.”

So the system says:

* `"admit"` → hospitalService::admitPatient
* `"bill"` → hospitalService::calculateBill

No coupling.
No hard coding.

## ✨ Act 5: Lambdas – Instant Medical Decisions

Then I smile and say:

> “Some hospital actions don’t need departments.”

Example:

* Discharge

Just a rule.

> “That’s why Java gave us **lambdas**.”

Small behavior, inline, readable.

## 🧱 Act 6: Putting It Together (Conceptual Java Code)

```java
Map<String, Command> commandMap = new HashMap<>();

commandMap.put("admit", hospitalService::admitPatient);
commandMap.put("bill", hospitalService::calculateBill);
commandMap.put("dosage", hospitalService::medicineDosage);

commandMap.put("discharge", (id, unused) -> {
    System.out.println("Patient " + id + " discharged");
    return 1;
});
```

I emphasize:

> “No `if-else`.
> No switch-case.
> Just **responsibility mapping**.”

## 🏗️ Act 7: Architecture Revealed

I draw:

```
User
 ↓
CommandRouter
 ↓
HospitalService
 ↓
Business Rule
```

Then I say:

> “This is exactly how:
>
> * Spring Controllers work
> * REST APIs route requests
> * Microservices stay calm under pressure”

## 🌍 Act 8: Java → Industry Mapping

| Java Concept     | Industry Meaning     |
| ---------------- | -------------------- |
| Interface        | Contract             |
| Lambda           | Inline business rule |
| Method Reference | Dependency Injection |
| Map              | Command Registry     |
| HospitalService  | Service Layer        |

I let that sink in.

## 🔑 Act 9: Mentor’s Rule (Java Edition)

> “Good Java code doesn’t grow `if-else`.
> It grows **maps of responsibility**.”

And then:

> “Once you understand this,
> Spring Boot becomes **syntax**, not mystery.”

## 🌱 Homework (Transflower Way)

- 1️⃣ Add `transferPatient` without touching old code
- 2️⃣ Replace `String` commands with `enum`
- 3️⃣ Add `help` command listing registered actions
- 4️⃣ Draw this as a **Spring Boot controller**

## 🎯 Mentor Closing Line

> “Frameworks come and go.
> Thinking stays.”


### 🏥 Healthcare Command Handler – Complete Java Application

Here’s a **complete, runnable Java console application** that implements the **Transflower Mentor Command Handler story** for the **Healthcare domain**.
### 🎯 What this app demonstrates

* Command Pattern (without over-engineering)
* Lambdas + Method References
* Loose coupling
* Industry-grade thinking in plain Java


## 1️⃣ Command Contract (Reception Desk Rule)

```java
@FunctionalInterface
interface Command {
    int execute(int a, int b);
}
```

👉 Reception doesn’t care *who* handles the request
👉 Only cares that it can **execute**

## 2️⃣ HospitalService (Doctors & Departments)

```java
class HospitalService {

    public int admitPatient(int patientId, int wardNo) {
        System.out.println("Patient " + patientId +
                " admitted to ward " + wardNo);
        return 1;
    }

    public int calculateBill(int days, int dailyCharge) {
        int amount = days * dailyCharge;
        System.out.println("Total bill amount: " + amount);
        return amount;
    }

    public int medicineDosage(int age, int baseDose) {
        int dosage = (age < 12) ? baseDose / 2 : baseDose;
        System.out.println("Prescribed dosage: " + dosage);
        return dosage;
    }
}
```

👉 **Business logic only**
👉 No routing, no input handling

## 3️⃣ CommandRouter (Reception Desk)

```java
import java.util.HashMap;
import java.util.Map;

class CommandRouter {

    private final Map<String, Command> commandMap = new HashMap<>();

    public void register(String commandName, Command command) {
        commandMap.put(commandName, command);
    }

    public boolean contains(String commandName) {
        return commandMap.containsKey(commandName);
    }

    public int execute(String commandName, int a, int b) {
        return commandMap.get(commandName).execute(a, b);
    }

    public void showHelp() {
        System.out.println("Available commands:");
        commandMap.keySet().forEach(cmd -> System.out.println(" - " + cmd));
    }
}
```

👉 This is your **mini API Gateway**
👉 Add new commands without touching old logic


## 4️⃣ Main Application (Hospital System)

```java
import java.util.Scanner;

public class HospitalApplication {

    public static void main(String[] args) {

        HospitalService hospitalService = new HospitalService();
        CommandRouter router = new CommandRouter();

        // Register commands (Binding happens here)
        router.register("admit", hospitalService::admitPatient);
        router.register("bill", hospitalService::calculateBill);
        router.register("dosage", hospitalService::medicineDosage);

        // Lambda-based command
        router.register("discharge", (patientId, unused) -> {
            System.out.println("Patient " + patientId + " discharged successfully.");
            return 1;
        });

        // Help command
        router.register("help", (a, b) -> {
            router.showHelp();
            return 0;
        });

        Scanner scanner = new Scanner(System.in);
        String command;

        System.out.println("🏥 Welcome to Hospital Management System");

        while (true) {
            System.out.print("\nEnter command (or 'exit'): ");
            command = scanner.next();

            if (command.equalsIgnoreCase("exit")) {
                System.out.println("System shutting down...");
                break;
            }

            if (!router.contains(command)) {
                System.out.println("❌ Invalid command. Type 'help'");
                continue;
            }

            int a = 0, b = 0;

            if (!command.equals("help")) {
                System.out.print("Enter first value: ");
                a = scanner.nextInt();
                System.out.print("Enter second value: ");
                b = scanner.nextInt();
            }

            router.execute(command, a, b);
        }

        scanner.close();
    }
}
```

## 🧠 What Students Should Realize (Mentor Emphasis)

### Without frameworks, we achieved:

* ✅ Command Pattern
* ✅ Runtime polymorphism
* ✅ Open–Closed Principle
* ✅ Separation of concerns
* ✅ Spring-like routing logic


## 🏗️ Mental Architecture Diagram

```
User Input
   ↓
CommandRouter (Map<String, Command>)
   ↓
HospitalService (Business Logic)
   ↓
Result
```

## 🌍 Industry Mapping (Reality Check)

| This Java App    | Real Project                  |
| ---------------- | ----------------------------- |
| CommandRouter    | REST Controller / API Gateway |
| Command          | Functional Interface          |
| Method Reference | Dependency Injection          |
| Lambda           | Inline business rule          |
| HospitalService  | Service Layer                 |


### 🎯 Mentor Closing Line

> “If you can build this in plain Java,
> frameworks become **tools**, not **crutches**.”


Perfect 👌
Now we **elevate the same Transflower story into Spring Boot**, without losing the *thinking* you carefully built.

This is exactly how I’d tell students:

> “What you wrote in core Java…
> Spring Boot just **hosts it on HTTP**.”




## *From Command Router → Spring Boot Controller*



## 🧠 Mentor Framing (Before Code)

> “In console apps, commands came from `Scanner`.
> In real systems, commands come from **HTTP requests**.”

Nothing else changes.

* Reception desk → **Controller**
* Command Map → **Request Mapping**
* HospitalService → **@Service**
* execute() → **Endpoint invocation**


# 🏥 Spring Boot Healthcare Command Controller

---

## 1️⃣ HospitalService (Same Doctor, New Building)

```java
package com.transflower.hospital.service;

import org.springframework.stereotype.Service;

@Service
public class HospitalService {

    public int admitPatient(int patientId, int wardNo) {
        System.out.println("Patient " + patientId +
                " admitted to ward " + wardNo);
        return 1;
    }

    public int calculateBill(int days, int dailyCharge) {
        return days * dailyCharge;
    }

    public int medicineDosage(int age, int baseDose) {
        return (age < 12) ? baseDose / 2 : baseDose;
    }

    public int dischargePatient(int patientId) {
        System.out.println("Patient " + patientId + " discharged");
        return 1;
    }
}
```

- 👉 Business logic unchanged
- 👉 Doctors don’t learn HTTP


## 2️⃣ CommandController (Reception Desk Goes Online)

```java
package com.transflower.hospital.controller;

import com.transflower.hospital.service.HospitalService;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;
import java.util.function.BiFunction;

@RestController
@RequestMapping("/hospital")
public class CommandController {

    private final Map<String, BiFunction<Integer, Integer, Integer>> commandMap
            = new HashMap<>();

    public CommandController(HospitalService hospitalService) {

        // Register commands (same idea as console app)
        commandMap.put("admit", hospitalService::admitPatient);
        commandMap.put("bill", hospitalService::calculateBill);
        commandMap.put("dosage", hospitalService::medicineDosage);

        // Lambda command
        commandMap.put("discharge", (patientId, unused) ->
                hospitalService.dischargePatient(patientId));
    }

    @GetMapping("/execute")
    public int executeCommand(@RequestParam String command,
                              @RequestParam int a,
                              @RequestParam int b) {

        if (!commandMap.containsKey(command)) {
            throw new IllegalArgumentException("Invalid hospital command");
        }

        return commandMap.get(command).apply(a, b);
    }

    @GetMapping("/help")
    public Map<String, String> help() {
        Map<String, String> help = new HashMap<>();
        commandMap.keySet()
                  .forEach(cmd -> help.put(cmd, "Supported hospital operation"));
        return help;
    }
}
```


## 🧭 How Requests Flow (Mentor Diagram)

```
Browser / Postman
        ↓
/hospital/execute
        ↓
CommandController
        ↓
Command Map Lookup
        ↓
HospitalService
```

## 🌐 Example API Calls (Reality Check)

### ✅ Admit Patient

```
GET /hospital/execute?command=admit&a=101&b=5
```

### 💰 Calculate Bill

```
GET /hospital/execute?command=bill&a=3&b=2000
```

### 💊 Dosage

```
GET /hospital/execute?command=dosage&a=10&b=500
```

### 🚪 Discharge

```
GET /hospital/execute?command=discharge&a=101&b=0
```

### 📋 Help

```
GET /hospital/help
```


## 🧠 Mentor Insight (Very Important)

> “Spring Boot did NOT introduce:
>
> * Polymorphism
> * Command Pattern
> * Routing logic”

You already built that in **plain Java**.

Spring Boot only gave:

* HTTP
* Dependency Injection
* Lifecycle management


## 🏗️ Industry Mapping (For Students)

| Console App          | Spring Boot             |
| -------------------- | ----------------------- |
| Scanner              | HTTP Request            |
| CommandRouter        | Controller              |
| Map<String, Command> | Map<String, BiFunction> |
| execute()            | Endpoint                |
| System.out           | HTTP Response           |



## 🌱 Transflower Homework (Spring Edition)

1️⃣ Replace `String command` with `enum CommandType`
2️⃣ Convert `/execute` to **POST + JSON body**
3️⃣ Add validation & exception handling
4️⃣ Split CommandRegistry into separate class
5️⃣ Convert this into **Microservice Gateway**



> “If you understand this controller,
> you understand **Spring Boot routing at its core**.”


