# Microservices Architecture

Let us start with a question.

> **Why would anyone take one working Spring Boot application and break it into 10 different applications?**

A beginner may think:

> "Why create more problems? We already have one application that works."

That is a very good question.

The answer is:

> **Microservices are not introduced because multiple applications are fashionable. They are introduced when business, organizational, scalability, deployment, or resilience requirements make a monolith difficult to evolve.**


# 1️⃣ Start With What You Already Know — Spring Boot Monolith

Suppose we are building an **Insurance Management System**.

Initially, we create one Spring Boot application:

```text
                  Insurance Application
                         │
       ┌─────────────────┼─────────────────┐
       │                 │                 │
       ▼                 ▼                 ▼
   Customers          Policies          Claims
       │                 │                 │
       ▼                 ▼                 ▼
   Customer           Policy            Claim
   Controller         Controller        Controller
       │                 │                 │
       ▼                 ▼                 ▼
    Service           Service           Service
       │                 │                 │
       └─────────────────┼─────────────────┘
                         ▼
                      MySQL
```

This is a **modular monolith** if we keep the business areas properly separated.

And there is absolutely nothing wrong with this architecture.

In fact:

> 🌸 **A well-designed monolith is often a better starting point than prematurely creating microservices.**



# 2️⃣ Then the Business Grows

Imagine our insurance company grows. We now have:

```text
10 million customers
5 million policies
millions of premium payments
millions of claims
thousands of employees
multiple teams
```

Different teams are working on different areas.

```text
Customer Team
Policy Team
Payment Team
Claims Team
Notification Team
Reporting Team
```

Now developers start asking:

> "Why should a small change in the Claims module require us to deploy the entire application?"

And another team asks:

> "Why should the Payment team wait for the Policy team to release?"

Now we have a reason to consider microservices.

 

# 3️⃣ What Is Microservices Architecture?

The monolith:

```text
             One Application
                  │
       ┌──────────┼──────────┐
       ▼          ▼          ▼
   Customer     Policy      Claim
```

becomes:

```text
          Microservices Architecture

       ┌───────────────────────┐
       │   Customer Service    │
       │     Spring Boot       │
       └───────────┬───────────┘
                   │
       ┌───────────┼────────────┐
       ▼           ▼            ▼
┌────────────┐ ┌───────────┐ ┌────────────┐
│  Policy    │ │  Payment  │ │   Claim    │
│  Service   │ │  Service  │ │  Service   │
│ SpringBoot │ │SpringBoot │ │ SpringBoot │
└─────┬──────┘ └─────┬─────┘ └─────┬──────┘
      │              │             │
      ▼              ▼             ▼
 Policy DB       Payment DB     Claim DB
```

Each service is:

* independently deployable
* independently scalable
* responsible for a business capability
* usually independently developed
* independently managed at runtime

 

# 4️⃣ The Most Important Word: Business Capability

Don't divide the application simply like this:

```text
CustomerController Service
PolicyController Service
ClaimController Service
```

Instead, ask:

> **What business capability does this service own?**

For insurance:

```text
Customer Management
Policy Management
Premium Management
Claims Management
Payment Management
Notification Management
```

These are potential service boundaries.

This is where **Domain-Driven Design** thinking becomes useful.

 

# 5️⃣ Microservices Are About Boundaries

Suppose we create:

```text
Customer Service
```

Its responsibility might be:

```text
Customer
Customer Profile
Customer Contact Information
Customer Registration
```

Policy Service:

```text
Policy
Policy Purchase
Policy Renewal
Policy Status
Policy Coverage
```

Claims Service:

```text
Claim
Claim Submission
Claim Assessment
Claim Approval
Claim Settlement
```

Payment Service:

```text
Payment
Transaction
Payment Status
Refund
```

The key principle:

> 🌸 **One service should have a clear business responsibility.**

 

# 6️⃣ Each Service Becomes a Spring Boot Application

Now the Spring Boot developer sees something familiar.

### Customer Service

```text
customer-service/
    ├── controller/
    ├── service/
    ├── repository/
    ├── model/
    └── application.yml
```

### Policy Service

```text
policy-service/
    ├── controller/
    ├── service/
    ├── repository/
    ├── model/
    └── application.yml
```

### Claim Service

```text
claim-service/
    ├── controller/
    ├── service/
    ├── repository/
    ├── model/
    └── application.yml
```

Notice:

> **Microservices don't replace Spring Boot.**

Spring Boot becomes the technology used to build individual services.

 

# 7️⃣ How Do Microservices Communicate?

This is where things become interesting.

In a monolith:

```java
policyService.getPolicy(policyId);
```

That's a simple Java method call.

In microservices:

```text
Claim Service
      │
      │ HTTP
      ▼
Policy Service
```

Now we need:

```text
HTTP
JSON
Network
Timeout
Retry
Authentication
Error handling
```

For example:

```text
GET /api/policies/101
```

The Claim Service might call the Policy Service.

  

# 8️⃣ Synchronous Communication

Suppose a claim is submitted. Claims Service needs policy information immediately.

```text
Client
   ↓
Claim Service
   ↓
HTTP Request
   ↓
Policy Service
   ↓
Policy DB
   ↓
Response
   ↓
Claim Service
```

This is **synchronous communication**. The caller waits for the response.

Good when:

> "I need the answer before I can continue."

 

# 9️⃣ But Don't Make Everything Synchronous

Imagine:

```text
Policy Purchased
```

Do we really need to wait for:

```text
Email Service
SMS Service
Analytics Service
Document Service
```

before telling the customer:

> "Policy purchased successfully"?

Probably not. Instead:

```text
Policy Service
      │
      │ PolicyPurchased event
      ▼
   Message Broker
      │
      ├──────────→ Notification Service
      │
      ├──────────→ Document Service
      │
      └──────────→ Analytics Service
```

This is **event-driven architecture**. The Policy Service publishes what happened. Other services react.

 

# 🔟 REST vs Messaging

As a Spring Boot developer, think:

```text
REST
 ↓
"I need information."

Event
 ↓
"Something happened."
```

Example:

```text
GET /policies/101
```

means:

> "Give me Policy 101."

Whereas:

```text
PolicyPurchased
```

means:

> "A policy has been purchased."

That distinction is extremely useful when designing service communication.
 

# 1️⃣1️⃣ Database Ownership

This is one of the most important microservices concepts. A beginner might create:

```text
Customer Service ─┐
Policy Service ───┼──→ One MySQL Database
Claim Service ────┤
Payment Service ──┘
```

Technically possible. But now every service can potentially access every table. You have created:

> **Database coupling.**

A stronger microservice design is:

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

Payment Service
      ↓
Payment DB
```

The service owns its data. Other services communicate through APIs/events rather than directly querying another service's tables.

 
# 1️⃣2️⃣ Now We Have a Consistency Problem

Here comes the distributed-system reality.

Suppose:

```text
Policy Service
      ↓
Policy DB
```

successfully creates a policy.

Then:

```text
Payment Service
```

fails. What happens? We can't simply use one normal database transaction across independent service databases. This introduces concepts such as:

```text
Eventual Consistency
Saga Pattern
Compensating Transactions
Transactional Outbox
```

This is why: **Microservices are primarily a distributed-systems problem, not merely a project-structuring technique.**

 

# 1️⃣3️⃣ What If a Service Goes Down?

Suppose:

```text
Claim Service
      ↓
Policy Service
```

But Policy Service is down. A naïve implementation waits forever. A production implementation thinks about:

```text
Timeout
Retry
Circuit Breaker
Fallback
Bulkhead
```

For example:

```text
Claim Service
      │
      ▼
Policy Service
      │
      X
   FAILURE
      │
      ▼
Circuit Breaker
      │
      ▼
Fallback / controlled failure
```

This prevents one unhealthy service from bringing down everything else.

 

# 1️⃣4️⃣ Scaling

Suppose Claims Service receives enormous traffic. With a monolith:

```text
Entire Application
       ↓
Scale everything
```

With microservices:

```text
Customer Service    → 2 instances
Policy Service      → 3 instances
Claim Service       → 20 instances
Payment Service     → 5 instances
```

We scale the service that needs capacity.This is one of the major benefits of microservices.

 

# 1️⃣5️⃣ Load Balancing

Suppose Claims Service has:

```text
Claim Service #1
Claim Service #2
Claim Service #3
```

A load balancer distributes requests:

```text
                 Load Balancer
                /      |       \
               ▼       ▼        ▼
            Claim #1 Claim #2 Claim #3
```

This gives us horizontal scalability and improves availability.

 

# 1️⃣6️⃣ Service Discovery

Now imagine Policy Service has several instances. How does Claim Service know where they are? Instead of hardcoding:

```text
http://192.168.10.25:8080
```

modern cloud-native environments commonly use service discovery and platform networking. Conceptually:

```text
             Service Discovery
                    │
       ┌────────────┼────────────┐
       ▼            ▼            ▼
Customer Service Policy Service Claim Service
```

In a Kubernetes environment, for example, services can be addressed through Kubernetes service discovery rather than hardcoded machine IP addresses.

 

# 1️⃣7️⃣ API Gateway

Now imagine clients need:

```text
Customer API
Policy API
Claim API
Payment API
```

Do we want React/mobile applications to know every internal service?

Often, no.

We introduce an API Gateway:

```text
                 React / Mobile
                       │
                       ▼
                  API Gateway
                /      |       \
               ▼       ▼        ▼
          Customer   Policy    Claims
           Service   Service   Service
```

The gateway can provide centralized entry-point concerns such as:

* routing
* authentication
* rate limiting
* request aggregation

 

# 1️⃣8️⃣ Authentication in Microservices

Suppose the user logs in.

```text
User
 ↓
Authentication Service
 ↓
JWT
```

Then:

```text
React
  ↓ JWT
API Gateway
  ↓
Policy Service
```

Services can validate the token and enforce authorization according to the architecture.

Now security is distributed too.

So microservices developers need to understand:

```text
JWT
OAuth 2.0
OpenID Connect
API Gateway
Service-to-service security
```

 

# 1️⃣9️⃣ Distributed Logging

Here comes a real production problem. Customer says:

> "My policy purchase failed."

Your request travelled through:

```text
API Gateway
      ↓
Policy Service
      ↓
Payment Service
      ↓
Notification Service
```

Which application log should you check? All of them. Therefore we need **correlation IDs / trace IDs**.

```text
Trace ID: ABC123
```

The same trace context follows the request through services. Then you can reconstruct:

```text
ABC123
 │
 ├── Gateway
 │
 ├── Policy Service
 │
 ├── Payment Service
 │
 └── Notification Service
```

This is distributed tracing.

 

# 2️⃣0️⃣ Observability

A production microservice architecture needs three major observability pillars:

```text
             Observability
                  │
        ┌─────────┼─────────┐
        ▼         ▼         ▼
       Logs     Metrics    Traces
```

### Logs

> What happened?

### Metrics

> How much? How often? How fast?

### Traces

> Where did this particular request travel?

Spring Boot developers should become comfortable with production observability rather than treating logging as `System.out.println()`.

 

# 2️⃣1️⃣ Caching

Suppose Policy Service receives:

```text
GET /policies/101
```

10,000 times.

Instead of querying the database every time:

```text
Policy Service
      ↓
    Redis
      ↓
 Cache HIT
```

For local in-memory caching, Caffeine can be useful. For shared/distributed caching, Redis is a common choice. And now our previous concept returns:

> **TTL — Time To Live.**

```text
Cache entry
    ↓
TTL
    ↓
Expires
    ↓
Fresh database lookup
```
 

# 2️⃣2️⃣ Deployment Changes Completely

In a monolith:

```text
insurance.jar
      ↓
one deployment
```

Microservices:

```text
customer-service.jar
policy-service.jar
claim-service.jar
payment-service.jar
notification-service.jar
```

Each can potentially have its own:

```text
Build
Test
Deploy
Scale
Rollback
```

This leads naturally to:

```text
Docker
CI/CD
Kubernetes
Cloud
Monitoring
```

Microservices and DevOps practices therefore tend to grow together.

 

# 2️⃣3️⃣ A Realistic Spring Boot Microservices Architecture

Now let's bring everything together.

```text
                         Users
                           │
                    React / Mobile
                           │
                           ▼
                     API Gateway
                           │
        ┌──────────────────┼──────────────────┐
        ▼                  ▼                  ▼
 Customer Service     Policy Service      Claim Service
 Spring Boot          Spring Boot         Spring Boot
        │                  │                  │
        ▼                  ▼                  ▼
 Customer DB           Policy DB           Claim DB
        │                  │                  │
        └──────────────────┼──────────────────┘
                           │
                     Message Broker
                       Kafka/RabbitMQ
                           │
              ┌────────────┼────────────┐
              ▼            ▼            ▼
         Payment       Notification   Analytics
         Service        Service        Service
```

Supporting infrastructure:

```text
        ┌─────────────────────────────────┐
        │         Infrastructure          │
        │                                 │
        │ Redis                           │
        │ Service Discovery               │
        │ API Gateway                     │
        │ Message Broker                  │
        │ Observability                   │
        │ Docker / Kubernetes             │
        └─────────────────────────────────┘
```

  

# 🌸 Mentor's Golden Rule

Don't teach students:  **"Microservices means creating many Spring Boot projects."**

Teach them: **"Microservices means identifying independent business capabilities and allowing them to evolve, deploy, scale, and fail independently."**

That's a much deeper understanding.
 

# 🎯 Spring Boot Developer's Microservices Checklist

Before calling yourself comfortable with microservices, you should understand:

```text
☑ REST APIs
☑ Spring Boot
☑ Spring Security
☑ JWT
☑ Database per service
☑ Service-to-service communication
☑ API Gateway
☑ Service discovery
☑ Load balancing
☑ Redis / distributed caching
☑ Kafka / RabbitMQ
☑ Event-driven architecture
☑ Timeout
☑ Retry
☑ Circuit Breaker
☑ Idempotency
☑ Eventual Consistency
☑ Saga Pattern
☑ Distributed Transactions
☑ Logging
☑ Metrics
☑ Distributed Tracing
☑ Docker
☑ Kubernetes
☑ CI/CD
```

## 🌱 Final Transflower Thought

A student who knows Spring Boot can say: **"I can build a REST API."**

A developer who understands microservices can say: **"I can build several independently deployable business services."**

But an engineer who understands distributed systems can answer:

> **"What happens when the network fails, the database goes down, messages are duplicated, a service becomes slow, data becomes temporarily inconsistent, traffic increases 100×, and I still need the system to remain reliable?"**

That is the real journey:

```text
Java
  ↓
Spring
  ↓
Spring Boot
  ↓
REST API
  ↓
Microservices
  ↓
Distributed Systems
  ↓
Cloud-Native Engineering
```

**Microservices is not the destination. It is a doorway into distributed-system engineering.**