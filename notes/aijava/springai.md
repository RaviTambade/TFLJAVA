
# Spring AI

> **“You already know how to build enterprise applications with Spring Boot. Spring AI teaches you how to add intelligence to those applications.”**

A traditional Spring Boot application looks like:

```text
User
  ↓
Controller
  ↓
Service
  ↓
Repository
  ↓
Database
```

For example, in an insurance application:

```text
Customer
   ↓
PolicyController
   ↓
PolicyService
   ↓
PolicyRepository
   ↓
MySQL
```

You already understand this architecture. Now imagine the customer asks:  **“Explain my insurance policy in simple language.”** The database can retrieve the policy. But who converts that policy information into a natural-language explanation? **An AI model.** So our architecture becomes:

```text
Customer
   ↓
Spring Boot Application
   ↓
Spring AI
   ↓
LLM
   ↓
AI Response
```

That is the basic idea behind Spring AI.

 
## What exactly is Spring AI?

Spring AI is a Spring-based framework for integrating **AI models and AI capabilities into Java applications**. Instead of writing low-level code for every AI provider, Spring AI gives Java developers familiar abstractions. Think of it like this:

```text
Spring Boot
     ↓
Enterprise Application Development

Spring AI
     ↓
AI Application Development
```

You already use abstractions such as:

```java
JdbcTemplate
RestTemplate
WebClient
JpaRepository
```

Spring AI introduces AI-oriented abstractions such as:

```text
ChatClient
ChatModel
EmbeddingModel
VectorStore
Document
```

The important mindset is:  **Spring AI is not an AI model.** It is the **integration layer between your Spring application and AI capabilities**.

## 🧠 Where does the LLM fit?

Suppose your application needs to communicate with an LLM. Conceptually:

```text
Java Application
       ↓
Spring AI
       ↓
LLM Provider
       ↓
Language Model
```

The model could be provided by different vendors or run locally. That means your application architecture becomes less tightly coupled to a particular model provider.

## A Spring Boot developer's first Spring AI application

Imagine you already know Spring Boot. You might have:

```java
@RestController
public class GreetingController {

    @GetMapping("/hello")
    public String hello() {
        return "Hello";
    }
}
```

Now the requirement changes:

> `/ask?question=Explain insurance premium`

We want the answer to come from an LLM. With Spring AI, the application can conceptually become:

```text
HTTP Request
     ↓
Controller
     ↓
ChatClient
     ↓
LLM
     ↓
AI Response
```

A simplified example looks like:

```java
@RestController
public class AIController {

    private final ChatClient chatClient;

    public AIController(ChatClient.Builder builder) {
        this.chatClient = builder.build();
    }

    @GetMapping("/ask")
    public String ask(@RequestParam String question) {

        return chatClient
                .prompt(question)
                .call()
                .content();
    }
}
```

Now stop here. Don't memorize the code. Understand the architecture. You already know:

```text
Controller → Service → Repository
```

Now you are learning another abstraction:

```text
Application → ChatClient → AI Model
```


## Spring AI's important building blocks

This is where I would slow down in a Transflower classroom.

### 1. ChatClient

Think of `ChatClient` as a convenient interface for communicating with a chat-oriented AI model.

```text
Your Java application
        ↓
    ChatClient
        ↓
    Chat Model
        ↓
       LLM
```

It can handle things such as prompts, responses and more advanced interactions.


### 2. ChatModel

The `ChatModel` represents the underlying chat-model capability.

Think:

```text
ChatClient
    ↓
ChatModel
    ↓
Specific AI provider/model
```

This abstraction helps separate your application from the implementation details of a particular model provider.


## 3. Prompt

A prompt is simply the input instruction/context we provide to the model. For example:

```text
Explain this insurance policy to a customer
using simple English.
```

But professional AI applications go beyond one-line prompts. We can have:

```text
System Instructions
        +
User Question
        +
Business Context
        +
Retrieved Documents
        ↓
      Prompt
        ↓
       LLM
```

This is where prompt engineering becomes part of application engineering.
 

## 4. EmbeddingModel

Now we move from **conversation** to **search**. Suppose your insurance company has:

```text
policy.pdf
claim-guidelines.pdf
premium-rules.pdf
customer-handbook.pdf
```

A keyword search may not understand that:  "How much money will I receive after hospitalization?"

is related to:  "Hospitalization reimbursement limit."

This is where embeddings become useful. An embedding converts content into a numerical vector representation. Conceptually:

```text
Text
 ↓
Embedding Model
 ↓
Vector
```

For example:

```text
"hospitalization reimbursement"
              ↓
        [0.21, -0.13, 0.87, ...]
```

The actual vector is much larger; this is just a conceptual representation.

 

## 5. VectorStore

Now students who know databases should pay attention. Traditional database:

```text
SQL Database
     ↓
Exact / structured data
```

Vector database:

```text
Vector Store
     ↓
Semantic representations
```

Spring AI provides a `VectorStore` abstraction for working with vector-based storage. The architecture becomes:

```text
Documents
   ↓
Chunking
   ↓
EmbeddingModel
   ↓
Vectors
   ↓
VectorStore
```

Then when the user asks a question:

```text
Question
   ↓
Embedding
   ↓
Similarity Search
   ↓
Relevant Documents
```

This leads us directly to one of the most important enterprise AI patterns:

## RAG

**Retrieval-Augmented Generation**

 

## Spring AI + RAG

Let's return to our insurance application. Suppose the user asks:  **“Does my policy cover hospitalization for 10 days?”**  The LLM should not simply guess. We first retrieve relevant information from company knowledge.

```text
User Question
      ↓
Embedding
      ↓
VectorStore
      ↓
Relevant Policy Documents
      ↓
Context
      ↓
Prompt
      ↓
LLM
      ↓
Answer
```

That is RAG. And Spring AI gives Java developers abstractions that help build this kind of pipeline.
 

## Now think like a Spring Boot architect

Your traditional application:

```text
                 ┌───────────────┐
                 │   React UI    │
                 └───────┬───────┘
                         ↓
                 ┌───────────────┐
                 │ Spring Boot   │
                 │    API        │
                 └───────┬───────┘
                         ↓
              ┌──────────┴──────────┐
              ↓                     ↓
        Business Logic           MySQL
```

Add AI:

```text
                 ┌───────────────┐
                 │   React UI    │
                 └───────┬───────┘
                         ↓
                 ┌───────────────┐
                 │ Spring Boot   │
                 │    API        │
                 └───────┬───────┘
                         ↓
          ┌──────────────┼───────────────┐
          ↓              ↓               ↓
     Business        Spring AI       Database
      Logic              ↓
                    ┌───────┐
                    │  LLM  │
                    └───────┘
```

And eventually:

```text
                     Spring Boot
                          │
          ┌───────────────┼────────────────┐
          ↓               ↓                ↓
      Business          Spring AI       Database
       Logic               │
                           ↓
                  ┌────────────────┐
                  │ ChatClient      │
                  └───────┬────────┘
                          ↓
                    Chat Model
                          │
              ┌───────────┴───────────┐
              ↓                       ↓
             LLM                 VectorStore
                                      ↑
                                      │
                              EmbeddingModel
```

Now you are no longer just building a CRUD application. You are building an **AI-enabled enterprise application**.
 

## Spring AI learning roadmap for Transflower students

Since you already know Java and Spring Boot, I would teach Spring AI in this order:

```text
Java Fundamentals
       ↓
Spring Framework
       ↓
Spring Boot
       ↓
REST APIs
       ↓
Dependency Injection
       ↓
Database / JPA
       ↓
       ⭐
Spring AI
       ↓
ChatClient
       ↓
Prompts
       ↓
Chat Models
       ↓
Structured Output
       ↓
Embeddings
       ↓
VectorStore
       ↓
Semantic Search
       ↓
RAG
       ↓
Tool Calling
       ↓
AI Agents
       ↓
MCP
       ↓
Enterprise AI
```

Notice something important. **We don't throw away the old learning.** We build AI **on top of** our engineering foundation.
 

## The Transflower Mentor Message

I would end the classroom session with this story. One student may say:  “Sir, earlier I was a Spring Boot developer. Now should I become an AI developer?” I would say:  **“No. Become a better software engineer who understands AI.”** You already know:

```text
Java
Spring
Spring Boot
REST
SQL
JPA
Microservices
Docker
Cloud
```

Now add:

```text
LLMs
Prompts
Embeddings
Vector Search
RAG
AI Agents
Tool Calling
MCP
```

Then something interesting happens. You don't abandon your existing engineering skills. **Your existing skills become more valuable.** Because the future enterprise developer may not simply build:

> `GET /policies`

They may build: **“Ask the insurance system anything about your policy.”**

And behind that simple sentence could be:

```text
React
   ↓
Spring Boot
   ↓
Spring AI
   ↓
ChatClient
   ↓
RAG
   ↓
VectorStore
   ↓
Business APIs
   ↓
MySQL
   ↓
LLM
```

That is the transition I want Transflower students to understand:

> **Yesterday we taught computers how to execute instructions.
> Today we are teaching applications how to work with intelligence.**

And **Spring AI gives Java/Spring developers a bridge into that world.**