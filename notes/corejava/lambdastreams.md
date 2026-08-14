# Introduction to Lambda Expressions and Streams

One morning in the Transflower classroom, I asked a student a very simple question: **“You have a list of 100 students. I want you to find all students who scored more than 70 marks. How will you solve it?”**

The student immediately opened his laptop and started writing a `for` loop.I smiled. “Yes. That will work.” 

Then I asked another question: **“But what are you really trying to tell the computer?”**

The student paused.I continued: “Are you really interested in writing a `for` loop?”  “Or are you interested in expressing a business requirement?” The requirement is simple: **Find students whose marks are greater than 70.** That small conversation opens the door to two powerful features introduced in modern Java:

**Lambda Expressions** and **Streams.**

## ☕ From Java Programming to Expressive Java Programming

In the early days of Java, we commonly wrote code like:

```text
for
 ↓
if
 ↓
process
```

We explicitly told the computer:

> Start from the first element.
> Move to the next element.
> Check the condition.
> Process the element.
> Continue until the collection ends.

This is perfectly valid programming.But as software systems became larger, developers started asking:

> **“Can we express what we want more directly?”**

Java evolved. Lambda expressions allowed us to represent **behavior as a value**. Streams allowed us to process collections through a **declarative pipeline**.And suddenly Java code could become much closer to the language of the business requirement.

# 🧠 A Small Change in Thinking

Suppose we have:

```text
Students
   ↓
Find students
   ↓
Marks > 70
```

The traditional programmer thinks:  “How do I iterate through the collection?” The modern Java programmer starts thinking: **“What transformation do I want to perform on this collection?”** That is an important shift.We move from: **How should I do it?** towards: **What do I want to achieve?**. This is one of the ideas behind functional-style programming.


# 🌱 Then Came Lambda

Imagine I ask: “Give me the logic for checking whether a student has scored more than 70.” Instead of creating a complete class and method just to represent that small piece of behavior, Java allows us to write the behavior directly:

```text
student -> student.getMarks() > 70
```

That small expression is a **Lambda Expression**.

I tell students:

> **“Don't think of Lambda as strange Java syntax. Think of it as a small piece of behavior that you can pass around.”**

For example:

```text
Input
  ↓
Student
  ↓
Check marks
  ↓
true / false
```

The Lambda expresses that behavior.


# 🌊 Then Came Streams

Now imagine we have:

```text
100 Students
```

and we want: Students with marks greater than 70. A Stream allows us to describe the processing as a pipeline:

```text
Students
   ↓
Stream
   ↓
Filter
   ↓
Students with marks > 70
```

And the pipeline can continue:

```text
Students
   ↓
filter()
   ↓
map()
   ↓
sorted()
   ↓
collect()
   ↓
Result
```

This is where the power of Lambda and Streams comes together.

 
# 🔗 Lambda + Stream

The Lambda tells the Stream **what behavior to apply**. For example:

```text
filter(student -> student.getMarks() > 70)
```

The Stream provides the **pipeline through which the data flows**. So I tell my students:

> **Lambda gives you the behavior.
> Stream gives you the pipeline.**

Together they give you a powerful way of expressing collection-processing logic.


# 🏢 Why Should a Java Developer Care?

At first, students may think:  “Sir, I can already do this with a `for` loop. Why should I learn Streams?” Good question. Because real enterprise applications don't contain only 5 integers. They contain:

```text
Customers
Orders
Products
Employees
Policies
Claims
Transactions
Payments
Invoices
```

Imagine an insurance application containing thousands of policies. You may need to:

* find active policies
* filter expired policies
* calculate premiums
* sort policies
* extract customer names
* calculate totals
* group policies by type
* find the highest premium
* calculate averages

Lambda and Streams give us a vocabulary for expressing these operations clearly.

 
# 🌻 But Don't Forget Your Fundamentals

I always tell Transflower students: **“Don't learn Streams before you understand Collections.”** First understand:

```text
Array
 ↓
List
 ↓
Set
 ↓
Map
 ↓
Collections
```

Then understand:

```text
Iteration
 ↓
for loop
 ↓
Enhanced for loop
```

Then:

```text
Interface
 ↓
Functional Interface
 ↓
Lambda
```

And finally:

```text
Collection
 ↓
Stream
 ↓
Pipeline
 ↓
Lambda
 ↓
Result
```

The new Java features should **build on your fundamentals**, not replace them.

 
# 🚀 Our Learning Journey

In this journey, we will gradually move from:

```text
Traditional Java
      ↓
Anonymous Classes
      ↓
Functional Interfaces
      ↓
Lambda Expressions
      ↓
Method References
      ↓
Stream API
      ↓
filter()
      ↓
map()
      ↓
sorted()
      ↓
collect()
      ↓
reduce()
      ↓
groupingBy()
      ↓
Real Enterprise Problems
```

We will not memorize these methods. We will **solve problems using them**.

 

# 🌱 The Transflower Way

I don't want a student to finish this topic saying:  “Sir, I know Lambda syntax.” I want the student to say:  **“I understand how to represent behavior.”** I don't want the student to say:  “I know `filter()`, `map()` and `reduce()`.” I want the student to say:  **“I can look at a collection-processing problem and design a clear processing pipeline.”**

That is the real learning. Because our goal at Transflower is not to produce developers who know more syntax. Our goal is to produce engineers who can **think, solve, build, debug, explain, and teach.**

So let's begin our journey:

> **From `for` loops to Lambda.**
> **From collections to Streams.**
> **From writing instructions to expressing intent.**

And most importantly:

> **Don't just learn the syntax. Learn the thinking behind it.**


## Let us get started...

One day, I asked a student: **“You have a list of students. Find all students who scored more than 70 marks.”** The student immediately started writing:

```java
for(int i = 0; i < students.size(); i++) {
    Student student = students.get(i);

    if(student.getMarks() > 70) {
        System.out.println(student);
    }
}
```

I looked at the code and said:  “Correct.” Then I asked: **“But can you express your intention more clearly?”** The student looked confused. I said:  “Your requirement is not really about a `for` loop.”
The requirement is: **Find students whose marks are greater than 70.** That is where **Lambda Expressions and Streams** begin.


## First Understand the Problem

Before Lambda and Stream, students normally write:

```text
Collection
   ↓
for loop
   ↓
if condition
   ↓
result
```

For example:

```java
List<Integer> numbers = Arrays.asList(10, 20, 35, 40, 55);

for (Integer number : numbers) {
    if (number > 30) {
        System.out.println(number);
    }
}
```

This is perfectly valid Java. But Java 8 introduced a more expressive programming style:

```text
Collection
    ↓
Stream
    ↓
Filter
    ↓
Transform
    ↓
Collect
```



## Part 1 — Lambda Expression

Start with a simple question. Suppose we have a method:

```java
void greet(String name) {
    System.out.println("Hello " + name);
}
```

We normally call:

```java
greet("Ravi");
```

But sometimes we want to **pass behavior as a parameter**. This is where students encounter a new idea:

> **A function can be represented as a value.**

For example:

```java
(name) -> System.out.println("Hello " + name)
```

This is a **Lambda Expression**. Think of it as:

```text
Lambda
   ↓
Input
   ↓
Processing
   ↓
Output
```

Example:

```java
(a, b) -> a + b
```

Meaning:

> Take `a` and `b`, and return `a + b`.

 

###  But Java Is Not Purely Functional

Students often ask:  “Sir, does Java now become a functional language?” No. Java is still primarily an object-oriented language. Lambda expressions allow Java to support **functional programming concepts**.

The important concept is:

> **Passing behavior to a method.**

 

### Functional Interface
 
Now we need one more concept. A Lambda Expression needs a target type. That target is often a **functional interface**. A functional interface contains exactly **one abstract method**.

Example:

```java
@FunctionalInterface
interface Calculator {
    int calculate(int a, int b);
}
```

Now we can write:

```java
Calculator addition = (a, b) -> a + b;
```

And:

```java
Calculator multiplication = (a, b) -> a * b;
```

The same interface can now represent different behaviors.

```text
Calculator
     ↑
     |
 ┌───┴──────────────┐
 ↓                  ↓
Addition       Multiplication
```

This is the first major lesson:  **Lambda allows us to pass behavior.**

 

### Java's Built-in Functional Interfaces

Students should become comfortable with these:

| Interface           | Purpose                       |
| ------------------- | ----------------------------- |
| `Predicate<T>`      | Returns `true/false`          |
| `Function<T,R>`     | Converts one value to another |
| `Consumer<T>`       | Consumes a value              |
| `Supplier<T>`       | Produces a value              |
| `UnaryOperator<T>`  | T → T                         |
| `BinaryOperator<T>` | T,T → T                       |

The most important four initially are:

```text
Predicate
Function
Consumer
Supplier
```

 

### Predicate

Suppose:

```java
Predicate<Integer> isEven =
        number -> number % 2 == 0;
```

Now:

```java
System.out.println(isEven.test(10));
```

Output:

```text
true
```

Think:

```text
Integer
   ↓
Predicate
   ↓
boolean
```

# Function

```java
Function<String, Integer> length =
        name -> name.length();
```

Now:

```java
int result = length.apply("Transflower");
```

Think:

```text
String
   ↓
Function
   ↓
Integer
```

 

# Consumer

```java
Consumer<String> printer =
        name -> System.out.println(name);
```

Think:

```text
String
   ↓
Consumer
   ↓
Side Effect
```
 

### Supplier

```java
Supplier<Double> randomNumber =
        () -> Math.random();
```

Think:

```text
Nothing
   ↓
Supplier
   ↓
Value
```

 

## Part 2 — What Is a Stream?

Now comes the interesting part. Ask students:  “Is a Stream a collection?” **No.** This is very important. A `List` stores data. A `Stream` processes data.

```text
List
 ↓
Stores objects

Stream
 ↓
Processes objects
```

For example:

```java
List<Integer> numbers =
        Arrays.asList(10, 20, 30, 40, 50);
```

Create a stream:

```java
numbers.stream();
```

Now we can process that data.

 

### Filter

Suppose:

```text
10
20
30
40
50
```

We want:

```text
numbers > 25
```

Using Stream:

```java
numbers.stream()
       .filter(n -> n > 25)
       .forEach(System.out::println);
```

Output:

```text
30
40
50
```

Now compare the thinking. Traditional:

```text
for
 ↓
if
 ↓
print
```

Stream:

```text
stream
 ↓
filter
 ↓
forEach
```

The code now describes **what we want**, rather than focusing heavily on the mechanics of iteration.

 

### Map

Now suppose:

```text
10
20
30
```

We want:

```text
20
40
60
```

We are transforming every value.

```java
numbers.stream()
       .map(n -> n * 2)
       .forEach(System.out::println);
```

Think:

```text
10 → 20
20 → 40
30 → 60
```

So:

> **`map()` transforms data.**

 

### Filter + Map

Now combine them.

```java
List<Integer> result =
        numbers.stream()
               .filter(n -> n > 20)
               .map(n -> n * 2)
               .toList();
```

Pipeline:

```text
Collection
    ↓
stream()
    ↓
filter()
    ↓
map()
    ↓
toList()
```

This is the heart of Stream programming.

 

### Let's Use Objects

Now we move from numbers to something meaningful. Suppose:

```java
class Student {

    private String name;
    private int marks;

    // constructor, getters
}
```

And:

```java
List<Student> students = ...
```

Requirement:

> Find students who scored more than 70.

We can write:

```java
students.stream()
        .filter(s -> s.getMarks() > 70)
        .forEach(System.out::println);
```

Now ask students:  **“What does this code say?”**

It almost reads like English:  From students, filter students whose marks are greater than 70, then print them. That is the power of Streams.

 

### Common Stream Operations

Students should learn these progressively:

#### Intermediate operations

```text
filter()
map()
sorted()
distinct()
limit()
skip()
```

These generally produce another Stream.

#### Terminal operations

```text
forEach()
collect()
toList()
count()
min()
max()
reduce()
anyMatch()
allMatch()
noneMatch()
findFirst()
findAny()
```

These finish the pipeline.

 

### Stream Pipeline

This diagram should be on the classroom board:

```text
             SOURCE
                ↓
        ┌───────────────┐
        │   List        │
        └───────┬───────┘
                ↓
             stream()
                ↓
        ┌───────────────┐
        │   filter()    │
        └───────┬───────┘
                ↓
        ┌───────────────┐
        │    map()      │
        └───────┬───────┘
                ↓
        ┌───────────────┐
        │   sorted()    │
        └───────┬───────┘
                ↓
        ┌───────────────┐
        │   collect()   │
        └───────┬───────┘
                ↓
              RESULT
```

This is called a **Stream Pipeline**.

 

##  A Very Important Concept — Lazy Evaluation
 
Ask:  “When does `filter()` actually execute?” Students often think:  “Immediately.” No. Most intermediate Stream operations are **lazy**.

For example:

```java
students.stream()
        .filter(s -> s.getMarks() > 70)
        .map(s -> s.getName());
```

Nothing necessarily happens until a terminal operation is invoked.

For example:

```java
.toList();
```

So:

```text
Source
 ↓
Intermediate operations
 ↓
Intermediate operations
 ↓
Terminal operation
 ↓
Execution
```

This is an important conceptual step.

 

##  Method References

Once students understand Lambda:

```java
name -> System.out.println(name)
```

introduce:

```java
System.out::println
```

This is a **method reference**.

Another example:

```java
students.stream()
        .map(Student::getName)
        .forEach(System.out::println);
```

Students should understand:

> **Method reference is not a different programming concept from Lambda. It is a concise way of referring to an existing method.**

 

## 🧮 Reduce

Now we ask:  “You have 1, 2, 3, 4, 5. How do you calculate the total?” We can use:

```java
int total =
        numbers.stream()
               .reduce(0, (a, b) -> a + b);
```

Conceptually:

```text
0 + 1
 ↓
1 + 2
 ↓
3 + 3
 ↓
6 + 4
 ↓
10 + 5
 ↓
15
```

This introduces an important functional programming concept: **Reduce many values into one value.**

 

## Real-World Example

Imagine an e-commerce application.

```java
List<Order> orders;
```

Requirement:

> Find all completed orders above ₹10,000.

```java
orders.stream()
      .filter(o -> o.getStatus().equals("COMPLETED"))
      .filter(o -> o.getAmount() > 10000)
      .forEach(System.out::println);
```

Now requirement changes:

> Calculate their total amount.

```java
double total =
        orders.stream()
              .filter(o -> o.getStatus().equals("COMPLETED"))
              .filter(o -> o.getAmount() > 10000)
              .mapToDouble(Order::getAmount)
              .sum();
```

Now students see why Streams matter in enterprise Java.

 

## The Transflower Learning Sequence

I would **not** teach Lambda and Streams in one giant lecture. Use this progression:

```text
1. Anonymous Inner Classes
          ↓
2. Functional Interfaces
          ↓
3. Lambda Expressions
          ↓
4. Predicate
          ↓
5. Function
          ↓
6. Consumer
          ↓
7. Supplier
          ↓
8. Method References
          ↓
9. Stream API
          ↓
10. filter()
          ↓
11. map()
          ↓
12. sorted()
          ↓
13. distinct()
          ↓
14. limit() / skip()
          ↓
15. collect() / toList()
          ↓
16. count()
          ↓
17. min() / max()
          ↓
18. reduce()
          ↓
19. groupingBy()
          ↓
20. Real-world problems
```

 

## Final Mentor Challenge

After teaching the concepts, don't give students another definition. Give them a problem:  **“Here is a collection of 100 employees.”** Ask them to solve:

1. Find employees earning more than ₹50,000.
2. Find employees from Pune.
3. Sort employees by salary.
4. Extract only employee names.
5. Find the highest salary.
6. Calculate average salary.
7. Group employees by department.
8. Count employees in each department.
9. Find the employee with the highest salary in each department.
10. Find departments whose average salary is greater than ₹75,000.

Now the student realizes:

> **Lambda gives me the ability to express behavior.** 
> **Stream gives me the ability to process collections through a pipeline.**

And together:

```text
Lambda
   +
Functional Interface
   +
Stream
   +
Pipeline
   ↓
Expressive Java Programming
```

The final message I would give the Transflower student is:

> **“Don't learn Streams because Java developers use Streams. Learn Streams because you want your code to express your business intention clearly.”**

And one more important rule:

> **Don't replace every `for` loop with a Stream just because you can.**

A good Java engineer knows **when a loop is clearer and when a Stream makes the intent clearer.**
