# Distributed-System Design for Spring Boot Developers

Let us start with a simple question.  **What happens when one Spring Boot application is no longer enough?** In the beginning, life is simple:

```text
Browser
   ↓
Spring Boot Application
   ↓
MySQL
```

Maybe you have:

```text
Customer
Policy
Premium
Claim
```

Everything runs inside one application. This is perfectly fine. But then the business grows. You have:

* 10,000 users → 1 million users
* 1 server → 20 servers
* one database → multiple databases
* one application → multiple services
* synchronous calls → asynchronous messaging

Now you have entered the world of **distributed systems**.


## 1️⃣ First Understand: What Is a Distributed System?

A distributed system is not simply: "Many Spring Boot applications." It is a system where **multiple independent computing components cooperate over a network to provide one overall business capability**. Think about an insurance platform:

```text
                    Insurance Platform
                           │
        ┌──────────────────┼──────────────────┐
        ▼                  ▼                  ▼
   Customer Service   Policy Service    Claim Service
   Spring Boot        Spring Boot       Spring Boot
        │                  │                  │
     Database           Database           Database
```

These services are independent processes. They communicate through:

```text
HTTP / REST
      or
Messaging
      or
Events
```

And immediately, we inherit a new problem:

> **The network can fail.**

That single sentence changes how you design applications.


## 2️⃣ Monolith vs Distributed System

###### Monolithic application

```text
              Spring Boot
       ┌──────────────────────┐
       │ Customer             │
       │ Policy               │
       │ Premium              │
       │ Claim                │
       │ Authentication       │
       └──────────┬───────────┘
                  │
               Database
```

Simple. You can debug it relatively easily. You can deploy it relatively easily. Transactions are easier. Communication between modules is just a method call.

```java
policyService.getPolicy(id);
```

###### Distributed architecture

Now split it:

```text
 Customer Service
       │
       │ REST
       ▼
 Policy Service
       │
       │ Event
       ▼
 Premium Service
       │
       │ Event
       ▼
 Claim Service
```

Now:

```text
policyService.getPolicy(id);
```

may become:

```text
HTTP
 ↓
Network
 ↓
Another Spring Boot service
 ↓
Database
 ↓
HTTP Response
```

And this introduces **latency, timeouts, retries, partial failures, consistency problems, observability problems, and deployment complexity**. That is the real beginning of distributed-system thinking.


## 3️⃣ Mentor Rule ##1: Assume Failure

In a normal application, developers often think:

```text
Service A → Service B
```

> "B will respond."

A distributed-system developer thinks:

```text
Service A → Network → Service B
                    X
                 timeout
```

Anything can fail.

* Service B may be down.
* Network may be slow.
* Database may be unavailable.
* Request may be duplicated.
* Response may be lost.
* Service B may process the request but Service A may never receive the response.

So: **In distributed systems, failure is not an exception. Failure is part of the design.**


## 4️⃣ Spring Boot Developer's First Distributed-System Problem: Timeout

Suppose:

```text
Claim Service
      ↓
Policy Service
```

Claim Service waits for Policy Service. What if Policy Service doesn't respond? Without proper timeout handling:

```text
Request
   ↓
Claim Service
   ↓
Policy Service
   ↓
............
............
............
```

Threads/connections can remain occupied. Soon:

```text
100 requests waiting
500 requests waiting
1000 requests waiting
```

The entire system can become unhealthy. Therefore:

> **Every remote call should have an appropriate timeout.**

This is different from calling a local Java method.



## 5️⃣ Retry — But Be Careful!
 
Suppose the network fails. You might think: "Let's retry."

```text
Request
  ↓
Service B
  ↓
Timeout
  ↓
Retry
  ↓
Service B
```

Retry can help with **transient failures**. But blindly retrying is dangerous. Imagine:

```text
POST /payments
```

The payment service successfully processes ₹20,000. But the response gets lost. Your application thinks:

```text
Payment failed
```

So it retries.

Now:

```text
Payment ##1 → ₹20,000
Payment ##2 → ₹20,000
```

The customer has been charged twice. This teaches an important concept:

## 🔑 Idempotency

An operation is idempotent when repeating the same logical request does not incorrectly produce multiple effects.

For example:

```text
PUT /customers/101
```

setting the same customer state repeatedly can be designed to be idempotent.

But:

```text
POST /payments
```

may require an **idempotency key** to safely handle retries.


## 6️⃣ Circuit Breaker

Now imagine:

```text
Claim Service
      ↓
Policy Service
```

Policy Service is down.

Without protection:

```text
Request
  ↓
Policy Service
  ↓
Timeout
  ↓
Retry
  ↓
Timeout
  ↓
Retry
```

Thousands of requests keep hitting a failing service. This can make the situation worse. A **Circuit Breaker** says:

> "Policy Service has failed repeatedly. Stop calling it temporarily."

Conceptually:

```text
          CLOSED
             │
       failures increase
             ▼
           OPEN
             │
       stop remote calls
             │
        wait / probe
             ▼
        HALF-OPEN
             │
       ┌─────┴─────┐
       ▼           ▼
    success      failure
       │           │
       ▼           ▼
    CLOSED        OPEN
```

This protects the system from cascading failures. For Spring Boot applications, libraries such as **Resilience4j** are commonly used for patterns such as circuit breakers, retries, rate limiting, and bulkheads.

 

## 7️⃣ Synchronous vs Asynchronous Communication

This is another major decision.

###### Synchronous

```text
Claim Service
     │
     │ HTTP
     ▼
Policy Service
     │
     ▼
Response
```

Claim Service waits.

Good when:

> "I need the answer right now."


### Asynchronous

Instead:

```text
Claim Service
     │
     │ Event
     ▼
 Message Broker
     │
     ├──────────────→ Policy Service
     │
     ├──────────────→ Notification Service
     │
     └──────────────→ Analytics Service
```

The sender doesn't necessarily wait for every consumer.

Common technologies include:

* Kafka
* RabbitMQ
* AWS messaging services
* other event-streaming/message-broker platforms

Now your system becomes more loosely coupled.

 

## 8️⃣ Event-Driven Thinking

Suppose a customer purchases a policy. Instead of directly calling five services:

```text
Purchase Policy
     ↓
Payment
     ↓
Notification
     ↓
Document
     ↓
Analytics
```

the Policy Service can publish:

```text
PolicyPurchased
```

Then:

```text
                 Policy Service
                       │
                       │ PolicyPurchased
                       ▼
                  Message Broker
                 /      |       \
                /       |        \
               ▼        ▼         ▼
          Payment   Notification  Analytics
           Service     Service      Service
```

This gives us a powerful architectural principle: **Publish what happened; let interested services decide what to do about it.**

 

## 9️⃣ But Now We Have a New Problem: Data Consistency

In a monolith:

```text
Policy
Payment
Claim
```

might use one database transaction. In distributed architecture:

```text
Policy DB
Payment DB
Claim DB
```

Now you can't casually assume one ACID transaction covers everything.

Imagine:

```text
Policy Created ✅
Payment Failed ❌
Notification not sent ❌
```

What should happen?

This is where concepts such as:

* eventual consistency
* Saga pattern
* compensating transactions
* transactional outbox

become important.

 

## 🔟 Eventual Consistency

Suppose:

```text
Policy Service
     ↓
Policy DB
```

updates immediately.

Then it publishes:

```text
PolicyPurchased
```

Notification Service receives it a little later.

For a short period:

```text
Policy DB       → Updated
Notification DB → Not yet updated
```

The system becomes consistent **eventually**, rather than instantly everywhere. This is:

## **Eventual Consistency**

Students should understand: **Distributed systems often exchange immediate consistency for scalability, availability, and loose coupling.** But this should be a deliberate business decision—not something we accept blindly.

 

## 1️⃣1️⃣ Database Per Service

One common microservice principle is:

```text
Customer Service
       ↓
Customer DB

Policy Service
       ↓
Policy DB

Claim Service
       ↓
Claim DB
```

Instead of:

```text
All services
     ↓
One giant shared database
```

Why? Because otherwise services become coupled through the database. If Policy Service directly manipulates Claim tables:

```text
Policy Service
      ↓
Claim tables
```

you have created hidden coupling. 
Better:

```text
Policy Service
      ↓
Policy DB

Claim Service
      ↓
Claim DB
```

and communicate through APIs/events.


## 1️⃣2️⃣ But How Do Services Find Each Other?

Suppose we have:

```text
Claim Service
     ↓
Policy Service
```

Where is Policy Service running?

Maybe:

```text
localhost:8081
```

today.

Tomorrow:

```text
server-23:8081
```

Next month:

```text
3 instances
```

Now hardcoding URLs becomes problematic.

This leads to:

## Service Discovery

Conceptually:

```text
                 Service Registry
                /       |       \
               /        |        \
        Customer     Policy     Claim
        Service      Service    Service
```

A service can discover another service dynamically.

In cloud-native environments, platforms such as Kubernetes can also provide service discovery mechanisms.


## 1️⃣3️⃣ Load Balancing

Suppose Policy Service has three instances:

```text
             Policy Service
            /      |       \
           ▼       ▼        ▼
       Instance  Instance  Instance
          A          B         C
```

Incoming requests can be distributed:

```text
Request 1 → A
Request 2 → B
Request 3 → C
Request 4 → A
```

This gives us:

> **Horizontal scalability**

Instead of making one machine bigger:

```text
8 GB → 16 GB → 32 GB
```

we can add more instances:

```text
1 instance
     ↓
3 instances
     ↓
10 instances
```

## 1️⃣4️⃣ Distributed Caching

Remember our previous discussion about TTL. In a distributed application:

```text
        Load Balancer
       /      |      \
      ▼       ▼       ▼
   Spring   Spring   Spring
    Boot     Boot     Boot
      \       |       /
       \      |      /
          Redis
            │
         Database
```

Now Redis becomes a shared cache. This is different from local Caffeine cache:

```text
Server A → Caffeine A
Server B → Caffeine B
Server C → Caffeine C
```

Each server has its own memory. Redis provides a shared caching layer.


## 1️⃣5️⃣ Observability — "Where Did My Request Go?"

This is perhaps one of the biggest challenges. Imagine a user reports:  "My claim request is taking 8 seconds."

Your architecture:

```text
API Gateway
    ↓
Claim Service
    ↓
Policy Service
    ↓
Customer Service
    ↓
Database
```

Where did the 8 seconds go? You need:

###### Logs

```text
ClaimService: request received
PolicyService: policy lookup started
PolicyService: database query
ClaimService: policy lookup completed
```

###### Metrics

```text
Request count
Error rate
Latency
CPU
Memory
Database connections
```

###### Distributed Tracing

A request gets a correlation/trace identity:

```text
Trace ID: ABC123
```

Then you can follow:

```text
API Gateway
   ↓
Claim Service
   ↓
Policy Service
   ↓
Customer Service
   ↓
Database
```

This is why observability is not an optional luxury in distributed systems.

 

## 1️⃣6️⃣ API Gateway

When clients have many services:

```text
Mobile
Web
React
Angular
```

you don't necessarily want every client to know every internal service. Instead:

```text
             React
               │
             Mobile
               │
               ▼
          API Gateway
        /      |       \
       ▼       ▼        ▼
 Customer   Policy    Claims
 Service    Service   Service
```

The gateway can handle concerns such as:

* routing
* authentication
* rate limiting
* request aggregation
* sometimes transformation

The exact responsibilities depend on architecture.

 

## 🌸 The Distributed-System Mental Model

For a Spring Boot developer, I recommend remembering these **10 questions**. Whenever you design a distributed application, ask:

```text
1. How do services communicate?
        ↓
   REST / messaging

2. What happens when a service is down?
        ↓
   Timeout / fallback / circuit breaker

3. What happens when the network fails?
        ↓
   Retry / resilience

4. What happens if a request is duplicated?
        ↓
   Idempotency

5. How do services exchange events?
        ↓
   Kafka / RabbitMQ / etc.

6. Where does each service store its data?
        ↓
   Database ownership

7. How do services discover each other?
        ↓
   Service discovery / platform networking

8. How do we scale?
        ↓
   Load balancing + horizontal scaling

9. How do we cache?
        ↓
   Caffeine / Redis

10. How do we debug production?
        ↓
   Logs + Metrics + Traces
```

## 🧩 Spring Boot Developer → Distributed-System Developer

Don't think:

```text
Spring Boot
   =
@RestController
@Service
@Repository
```

That is only the beginning. A production Spring Boot developer eventually needs to understand:

```text
                    Spring Boot
                         │
        ┌────────────────┼────────────────┐
        ▼                ▼                ▼
       API            Database         Messaging
        │                │                │
        ▼                ▼                ▼
    Security          Caching          Events
        │                │                │
        └────────────────┼────────────────┘
                         ▼
                 Distributed System
                         │
        ┌────────────────┼────────────────┐
        ▼                ▼                ▼
    Resilience       Observability     Scalability
```

 

## 🌸 Final Transflower Mentor Message

A beginner asks: **"How do I create a Spring Boot REST API?"**
A good developer asks: **"How do I structure my Spring Boot application?"**
A senior developer asks:

> **"What happens when this application has 100 instances, 10 million users, multiple databases, network failures, duplicate requests, and services that can independently fail?"**

That is the transition:

```text
Java Developer
      ↓
Spring Developer
      ↓
Spring Boot Developer
      ↓
Microservices Developer
      ↓
Distributed Systems Engineer
```

And remember the most important principle:

> **A distributed system is not created by splitting one application into many Spring Boot projects.**
> **It is created by learning how to manage communication, failure, consistency, scalability, and observability across independent components.**

That is where **Spring Boot development meets real system architecture**.