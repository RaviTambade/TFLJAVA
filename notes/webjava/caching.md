

# TTL — Time To Live (Caching)
 Let’s take this one step further in **Transflower Learning** and understand **TTL (Time To Live)** not merely as a caching term,but as an important **distributed-system design concept**.
Imagine you have built an **Insurance Management System**. Thousands of customers are looking at:

```text
GET /api/policies/101
```

Suppose Policy #101 rarely changes. Without caching:

```text
User 1 ──→ API ──→ Database
User 2 ──→ API ──→ Database
User 3 ──→ API ──→ Database
...
User 10,000 ──→ API ──→ Database
```

The database is repeatedly answering the **same question**. That is wasteful.

---

## 1️⃣ Introduce the Cache

We put a cache between the application and database:

```text
              ┌──────────────┐
Request ─────→│ Application  │
              └──────┬───────┘
                     │
                     ▼
                 ┌───────┐
                 │ Cache │
                 └───┬───┘
                     │
                Cache Miss
                     │
                     ▼
                ┌──────────┐
                │ Database │
                └──────────┘
```

Now consider the first request.

### First request

```text
GET /api/policies/101
        ↓
      Cache
        ↓
     MISS ❌
        ↓
    Database
        ↓
 Policy #101
        ↓
      Cache
        ↓
     Response
```

We store:

```text
Key   : policy:101
Value : Policy object
TTL   : 10 minutes
```

---

## 2️⃣ Second Request

Another user asks for the same policy.

```text
GET /api/policies/101
        ↓
      Cache
        ↓
      HIT ✅
        ↓
     Response
```

The database isn't contacted. This is why caching can dramatically reduce database load and latency.

---

# ⏳ 3️⃣ Now Comes TTL

Here is the important question:  **How long should Policy #101 remain in the cache?** Suppose we configure:

```text
TTL = 10 minutes
```

That means: **This cached entry is valid for 10 minutes from its cache insertion/reset time, depending on the cache's expiration policy.**  Think of TTL like an **expiry date attached to cached data**.

```text
10:00 AM
   ↓
Cache entry created
   ↓
10:01
10:02
10:03
...
10:09
   ↓
10:10
   ↓
TTL expired ⏰
```

After expiration:

```text
Cache
  ↓
Expired ❌
  ↓
Database
  ↓
Fresh data
  ↓
Cache again
```

# 🌱 4️⃣ Why Do We Need TTL?

Because **data changes**. Imagine:

```text
Database:

Policy 101
Premium = ₹20,000
```

Cache contains:

```text
Policy 101
Premium = ₹20,000
```

Someone updates the database:

```text
Premium = ₹22,000
```

But the cache still contains:

```text
₹20,000
```

Now users may receive old information. This is called: 

# ⚠️ Stale Data

And this leads us to one of the biggest caching questions:  **"When should cached data be considered invalid?"**
 -

# 5️⃣ Cache Invalidation

There are several strategies.

### Strategy 1 — TTL

Let the cache expire naturally.

```text
Cache
  ↓
TTL expires
  ↓
Entry removed
  ↓
Next request
  ↓
Database
```

Simple.

But users may temporarily receive stale data.

 

### Strategy 2 — Explicit Eviction

When data changes:

```text
UPDATE Policy
      ↓
Database updated
      ↓
Remove policy:101
      ↓
Cache MISS next time
      ↓
Database
      ↓
Fresh data
      ↓
Cache
```

This is often called **cache eviction**.

  

### Strategy 3 — Update Cache

Another approach is:

```text
UPDATE Database
      +
UPDATE Cache
```

Now both contain the latest value. But this introduces another problem:  **What happens if updating the database succeeds but updating the cache fails?** Now distributed consistency becomes important.


# 🧠 Mentor Question

Suppose:

```text
TTL = 60 seconds
```

At 10:00:

```text
Cache → Product price = ₹100
```

At 10:00:10:

```text
Database → Product price = ₹120
```

At 10:00:20:

```text
User requests product
```

What does the user get?

If the cache hasn't been invalidated:

```text
₹100
```

even though the database contains:

```text
₹120
```

That is the trade-off.  **Caching improves performance, but introduces a consistency problem.** This is one of the most important lessons for backend developers.

 

# 6️⃣ TTL Is Not Just a Performance Setting

A beginner may think: "I'll simply set TTL to 1 hour."  But a senior developer asks:  **"How frequently does this data change, and how stale can the business tolerate it being?"**

For example:

| Data                          |    Possible TTL |
| ----------------------------- | --------------: |
| Product catalog               |         Minutes |
| Country/state master data     |           Hours |
| Exchange rates                | Seconds/minutes |
| User session                  |         Minutes |
| Frequently changing inventory |      Very short |
| Static configuration          |           Hours |
| Reference/master data         |      Hours/days |

These aren't universal values. 

**TTL should be determined by business requirements, freshness requirements, traffic patterns, and the cost of recomputing/fetching the data.**

 

# ☕ 7️⃣ Spring Cache

Now let's connect this concept to Java. Spring provides an abstraction called:

```text
Spring Cache
```

Conceptually:

```java
@Cacheable("policies")
public Policy getPolicy(int id) {
    return repository.findById(id);
}
```

The first call:

```text
getPolicy(101)
     ↓
Cache MISS
     ↓
Repository
     ↓
Database
```

Later:

```text
getPolicy(101)
     ↓
Cache HIT
     ↓
Return cached Policy
```

The business method doesn't need to manually implement:

```text
if cache exists
else database
store cache
```

The caching abstraction handles the caching behavior.

# ⚡ 8️⃣ Caffeine

For a single application instance, **Caffeine** is a popular high-performance in-process Java cache.

Think:

```text
Spring Boot Application
        │
        ▼
   Caffeine Cache
        │
        ▼
    Database
```

The cache lives inside the application's memory.

For example:

```text
Application Instance
      │
      ├── Cache
      ├── Controllers
      ├── Services
      └── Repositories
```

This can be extremely fast because there's no network round trip to an external cache. But there is an architectural limitation.


# 🌍 9️⃣ What Happens When We Have Multiple Servers?

Imagine:

```text
              Load Balancer
             /      |      \
            /       |       \
        Server A  Server B  Server C
           │         │         │
        Cache A   Cache B   Cache C
```

Suppose Server A caches:

```text
Product 101 = ₹100
```

Server B might not have it.

Server C might have:

```text
Product 101 = ₹90
```

Now we have multiple independent caches.

This is where a distributed cache becomes useful.


# 🔴 Redis

With Redis:

```text
             Load Balancer
            /      |      \
           /       |       \
       Server A Server B Server C
           \       |       /
            \      |      /
              Redis
                │
             Database
```

All application instances can access the same cache.

So:

```text
Server A
   ↓
Redis
```

and:

```text
Server B
   ↓
Redis
```

can see the same cached data.

This makes Redis particularly useful in **distributed and horizontally scaled applications**.

# 🔥 10️⃣ The Big Picture

Now our architecture becomes:

```text
                    Users
                      │
                      ▼
                Load Balancer
                      │
          ┌───────────┼───────────┐
          ▼           ▼           ▼
      Spring Boot Spring Boot Spring Boot
       Server A     Server B     Server C
          │           │           │
          └───────────┼───────────┘
                      │
                      ▼
                    Redis
                  Cache
                      │
                Cache MISS
                      │
                      ▼
                  Database
```

And the request lifecycle becomes:

```text
Request
   ↓
Redis
   ↓
 ┌───────────────┐
 │ Cache HIT ?   │
 └───────┬───────┘
      Yes│     │No
         │     │
         ▼     ▼
      Return  Database
                │
                ▼
              Redis
                │
                ▼
             Response
```


# 🌸 Transflower Mentor's Golden Rule

When teaching caching, don't remember only:  **"Cache makes applications faster."** Remember the complete equation:

```text
Caching = Performance + Scalability  - Freshness  + Complexity
```

Caching reduces expensive operations.

But it introduces:

* cache invalidation
* stale data
* memory management
* eviction policies
* TTL decisions
* distributed consistency
* cache stampede considerations

And that is why **caching is an architectural decision, not simply a configuration setting.**

## 🎯 Interview Question

If an interviewer asks: **"What is TTL in caching?"**

A strong answer is:

> **TTL, or Time To Live, specifies how long a cached entry remains valid before it expires. When the TTL expires, the entry is considered expired/removed according to the cache implementation, and a subsequent request may need to retrieve fresh data from the underlying data source. TTL helps balance performance against data freshness.**

And if they ask: **"Spring Cache vs Caffeine vs Redis?"**

Think:

```text
Spring Cache
     ↓
Caching abstraction

Caffeine
     ↓
Fast local/in-memory cache

Redis
     ↓
Distributed/shared cache
```

That is the mental model I would want a Transflower student to carry into an interview.