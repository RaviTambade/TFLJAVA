# 🧑‍🏫 **Circular Doubly Linked List**

## 🎭 Scene: Ravi (Mentor) & Sarthak (Student)

Sarthak comes to class confidently:

> **“Sir, I learned doubly linked list!
> But… you once mentioned something magical —
> *Circular* Doubly Linked List.
> What is that?”**

Ravi, with a smile that hides a challenge:

> **“Sarthak, imagine a family sitting around a round table…”**

# 🍽️ **Round Table Story**

Ravi draws a circle on the board.

He says:

“Everyone sits around the table.

* Each person can look at the person **to the right** → `next`
* And the person **to the left** → `prev`
* No one sits at the “end”
* Because **the table is circular**”

This is a **Circular Doubly Linked List**.

# 🧠 Why Circular Doubly Linked List?

Ravi explains situations where this structure shines:

### ✔ Music playlist repeat mode

### ✔ Round-robin scheduling in OS

### ✔ Multiplayer game turns

### ✔ Navigating items in a carousel

Sarthak imagines a merry-go-round:

> “Sir! No matter where I stand, I can reach anywhere by moving right or left.”

Ravi laughs:

**“Exactly beta! That’s the beauty.”**

# 🔩 Node Structure

```java
package com.dsa.circular;

import java.io.Serializable;

public class Node implements Serializable {
    int data;
    Node next;
    Node prev;

    public Node(int data) {
        this.data = data;
    }
}
```

# 🔧 Circular Doubly Linked List Implementation

Ravi now draws arrows in a circle and begins to code…

```java
package com.dsa.circular;

import java.io.Serializable;

public class CircularDoublyLinkedList implements Serializable {

    Node head;
    Node tail;

    public CircularDoublyLinkedList() {
        head = null;
        tail = null;
    }

    // Insert at end
    public void insert(int data) {

        Node newNode = new Node(data);

        // Empty list
        if (head == null) {
            head = tail = newNode;
            head.next = head;
            head.prev = head;
            return;
        }

        // Link new node
        tail.next = newNode;
        newNode.prev = tail;

        // Close the circle
        newNode.next = head;
        head.prev = newNode;

        // Move tail
        tail = newNode;
    }

    // Delete specific value
    public void delete(int data) {

        if (head == null) return;

        Node current = head;

        do {
            if (current.data == data) {

                // Only one node
                if (current == head && current == tail) {
                    head = tail = null;
                    return;
                }

                // Deleting head
                if (current == head) {
                    head = head.next;
                }

                // Deleting tail
                if (current == tail) {
                    tail = tail.prev;
                }

                // Connect neighbors
                current.prev.next = current.next;
                current.next.prev = current.prev;

                return;
            }

            current = current.next;

        } while (current != head);
    }

    // Display forward
    public void displayForward() {
        if (head == null) {
            System.out.println("List empty");
            return;
        }

        System.out.println("Forward (Circular):");
        Node current = head;

        do {
            System.out.println(current.data);
            current = current.next;
        } while (current != head);

        System.out.println("-------------------------");
    }

    // Display backward
    public void displayBackward() {
        if (tail == null) {
            System.out.println("List empty");
            return;
        }

        System.out.println("Backward (Circular):");
        Node current = tail;

        do {
            System.out.println(current.data);
            current = current.prev;
        } while (current != tail);

        System.out.println("-------------------------");
    }
}
```

# 🎨 Mentor Visual – How the Circle Looks

After inserting 10, 20, 30, 40:

```
     10 ⇆ 20 ⇆ 30 ⇆ 40
      ↑               ↓
      └────── circle ─┘
```

Both directions form a closed loop:

```
Forward: 10 → 20 → 30 → 40 → back to 10  
Backward: 10 ← 20 ← 30 ← 40 ← back to 10  
```

Sarthak jumps up:

> “Sir, this is like playing passing-the-parcel in a circle!”

Ravi:
**“Yes! The parcel never stops.”**

# 🎯 Test Code

```java
public class TestCircularDLL {

    public static void main(String[] args) {

        CircularDoublyLinkedList list = new CircularDoublyLinkedList();

        list.insert(10);
        list.insert(20);
        list.insert(30);
        list.insert(40);

        list.displayForward();
        list.displayBackward();

        list.delete(30);

        list.displayForward();
        list.displayBackward();
    }
}
```

# 🧾 Expected Output

```
Forward (Circular):
10
20
30
40
-------------------------
Backward (Circular):
40
30
20
10
-------------------------
Forward (Circular):
10
20
40
-------------------------
Backward (Circular):
40
20
10
-------------------------
```

# 🌟 Mentor’s Final Words

“A singly linked list was like a one-way street.
A doubly linked list became a two-way road.

But a **circular doubly linked list**…

…is like a **never-ending highway loop**.
Perfect for systems that require continuous rotation.”

Sarthak smiles:

> **“Sir, data structures are becoming my favorite movie series.”**

Ravi laughs:

> “Beta, next season awaits —
> just ask **‘Next version please.’**”
