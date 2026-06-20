# 🧑‍🏫 **Turning Your Linked List into a Doubly Linked List**

## 🎭 Scene: Ravi (Mentor) & Sahil (Student)

Sahil walks into the lab, looking slightly confused.

He says:

> **“Sir, singly linked list is nice… but sometimes I want to move backward also.
> Like undo/redo operations, browser history, LRU cache…
> But my current linked list only goes forward.”**

Ravi laughs gently and says:

**“Sahil, you just discovered why humans invented
👉 *Doubly Linked Lists*.”**


# 🎒 Mentor: “Imagine You Are Walking in a Forest Trail…”

Picture yourself walking on a narrow trail:

* There is only one path forward
* No way to go back
* If you go ahead too far, you must walk around the entire forest

Sahil nods.

That is your **Singly Linked List** — a one-way street.

Now imagine the trail has signs:

```
<-- previous    current    next -->
```

You can walk forward and backward freely.

This is **Doubly Linked List**.


# 🧠 Why We Need a Doubly Linked List?

Mentor lists real-world use cases:

### ✔ Browser back/forward

### ✔ Music playlist skip backward

### ✔ Undo/Redo feature

### ✔ Cache algorithms

### ✔ Trees and Graphs often use them

Sahil:
“Sir, this feels more natural… like life. Always able to go forward and backward.”

Ravi:
“And that's why programmers love it.”

# 🔧 Step 1 — Doubly Node (Hero of the Story)

Ravi writes:

```java
package com.dsa.doublylist;

import java.io.Serializable;

public class Node implements Serializable {
    int data;
    Node next;
    Node prev;

    public Node(int data) {
        this.data = data;
        this.next = null;
        this.prev = null;
    }
}
```

Mentor:
“Earlier we only had `next`. Now we add `prev` — like giving your node two hands,
one to point forward, one to point backward.”

# 🔧 Step 2 — Building the Doubly Linked List

Ravi walks to the board slowly and explains each operation like a story.

# 🧱 **Doubly Linked List Implementation**

```java
package com.dsa.doublylist;

import java.io.Serializable;

public class DoublyLinkedList implements Serializable {

    Node head;
    Node tail;

    public DoublyLinkedList() {
        head = null;
        tail = null;
    }

    // Insert at end
    public void insert(int data) {

        Node newNode = new Node(data);

        // First element
        if (head == null) {
            head = tail = newNode;
            return;
        }

        tail.next = newNode;   // link current tail forward
        newNode.prev = tail;   // link new node backward
        tail = newNode;        // move tail to new node
    }

    // Delete a specific node (by value)
    public void delete(int data) {

        Node current = head;

        while (current != null) {

            if (current.data == data) {

                // Case 1: Delete Head
                if (current == head) {
                    head = head.next;
                    if (head != null) head.prev = null;
                    else tail = null;  // list is empty
                    return;
                }

                // Case 2: Delete Tail
                if (current == tail) {
                    tail = tail.prev;
                    tail.next = null;
                    return;
                }

                // Case 3: Delete Middle Node
                current.prev.next = current.next;
                current.next.prev = current.prev;
                return;
            }

            current = current.next;
        }
    }

    // Display Forward
    public void displayForward() {
        System.out.println("Forward Traversal:");
        Node current = head;
        while (current != null) {
            System.out.println(current.data);
            current = current.next;
        }
        System.out.println("-------------------------");
    }

    // Display Backward
    public void displayBackward() {
        System.out.println("Backward Traversal:");
        Node current = tail;
        while (current != null) {
            System.out.println(current.data);
            current = current.prev;
        }
        System.out.println("-------------------------");
    }
}
```

# 🎨 Mentor Visual: Linked List Evolution

### Singly Linked List

```
10 → 20 → 30 → null
```

### Doubly Linked List

```
null ← 10 ⇆ 20 ⇆ 30 → null
```

Sahil smiles:

> “Sir, it’s like each node can now talk to its neighbors.”

Ravi:
“Exactly! They are social now.”

# 🎥 Story: What Happens During Insert?

When you insert `40`:

1. `tail.next = newNode;`
2. `newNode.prev = tail;`
3. `tail = newNode;`

Ravi says:

“That’s like saying:
**40, meet your elder brother 30.
30, meet your younger brother 40.**”

# 🎥 Story: What Happens During Delete?

Deleting the middle node (say 20):

```
10 ⇆ 20 ⇆ 30
```

Changes to:

```
10 ⇆ 30
```

Ravi:
“20 gently leaves the family,
10 directly shakes hands with 30.”

# 🧪 Testing the Doubly List

```java
public class TestDLL {
    public static void main(String[] args) {

        DoublyLinkedList dll = new DoublyLinkedList();

        dll.insert(10);
        dll.insert(20);
        dll.insert(30);
        dll.insert(40);

        dll.displayForward();
        dll.displayBackward();

        dll.delete(30);

        dll.displayForward();
        dll.displayBackward();
    }
}
```

# 📜 Output

```
Forward Traversal:
10
20
30
40
-------------------------
Backward Traversal:
40
30
20
10
-------------------------
Forward Traversal:
10
20
40
-------------------------
Backward Traversal:
40
20
10
-------------------------
```

# 🌟 Mentor’s Final Words

“Your singly linked list was like a one-way road.

But a **doubly linked list** is like a modern expressway —
you can move forward, backward, delete efficiently, insert smartly,
and build more powerful data structures like:

✔ Deque
✔ LRU Cache
✔ Browser History
✔ Editor Undo/Redo System

You’ve just unlocked the next level of data structure mastery.”
