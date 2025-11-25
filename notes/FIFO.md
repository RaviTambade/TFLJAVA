 

# 🧑‍🏫 **Turning Your Linked List into a Queue**

Imagine you are standing in a bank.

You see a **queue**:

* People join at the **end** → `enqueue`
* The person at the **front** is served first → `dequeue`

This is exactly how a Queue works:

### ✔ Insert at tail → enqueue

### ✔ Remove from head → dequeue

### ✔ Follow FIFO (First In, First Out)

Your `LinkedList` is almost ready…
But it tries to behave like a **Swiss army knife** (search, remove by data, etc.).

A Queue must be **simple and disciplined**.

So, we redesign it.

 

# 🧱 **Step 1 – Node Class**

```java
package com.dsa.linkedlist;

import java.io.Serializable;

public class Node implements Serializable {
    int data;
    Node next;

    public Node(int data) {
        this.data = data;
        this.next = null;
    }
}
```

 

# 🧱 **Step 2 – LinkedList Supporting Queue Operations**

We keep it simple:

* Maintain **head** → front of queue
* Maintain **tail** → end of queue
* Insert at tail = O(1)
* Remove from head = O(1)

 

```java
package com.dsa.linkedlist;

import java.io.Serializable;

public class LinkedList implements Serializable {

    private Node head;   // front
    private Node tail;   // rear

    public LinkedList() {
        head = null;
        tail = null;
    }

    // Queue Operation: Enqueue
    public void enqueue(int data) {
        Node newNode = new Node(data);

        if (head == null) {
            // First element
            head = tail = newNode;
        } else {
            tail.next = newNode;
            tail = newNode;        // move tail to last node
        }
    }

    // Queue Operation: Dequeue
    public int dequeue() {
        if (head == null) {
            throw new RuntimeException("Queue is empty");
        }

        int value = head.data;
        head = head.next;          // move head forward

        // If queue becomes empty
        if (head == null) {
            tail = null;
        }

        return value;
    }

    // Check if queue is empty
    public boolean isEmpty() {
        return head == null;
    }

    // Display queue elements
    public void display() {
        System.out.println("Queue Elements:");
        Node current = head;
        while (current != null) {
            System.out.println(current.data);
            current = current.next;
        }
        System.out.println("------------------------------");
    }
}
```

 

# 🎥 **How the Queue Works Internally**

### 🧍 Enqueue (insert at rear)

```
10 → 20 → 30 → null
^head           ^tail
```

Every new student stands *at the back of the line*.

### 🧍 Dequeue (remove from front)

```
10 is removed  
head moves to next → 20 → 30
```

The line moves forward.

### 📌 **This is real Queue behavior**

No searching
No removing by value
No complicated traversal

Just clean FIFO.

# 🧪 **Sample Test Code**

```java
public class TestQueue {
    public static void main(String[] args) {
        LinkedList queue = new LinkedList();

        queue.enqueue(10);
        queue.enqueue(20);
        queue.enqueue(30);

        queue.display();

        System.out.println("Dequeued: " + queue.dequeue());
        queue.display();

        queue.enqueue(40);
        queue.display();
    }
}
```

# 🎯 **Output**

```
Queue Elements:
10
20
30
------------------------------
Dequeued: 10
Queue Elements:
20
30
------------------------------
Queue Elements:
20
30
40
------------------------------
```
