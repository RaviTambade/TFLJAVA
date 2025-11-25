# 🧑‍🏫 **Turning Your Linked List into a Stack**

Let’s begin the story…

## 🎭 **Scene: Rohan (Mentor) & Aarav (Student)**

Aarav walks into the classroom excited after learning queues yesterday.

He says:

> **“Sir! Queue was fun… but I heard there is something extra powerful called STACK!
> I want to build that too.”**

Rohan smiles like a magician opening his trick box.

# 🎒 **Mentor (smiling):**

“Aarav, imagine you have a school bag.

What do you put inside first?
Your books.

If you suddenly want the book at the bottom, can you pull it out directly?”

Aarav:
“No sir! I must remove all the items on top first.”

Rohan:
**“Exactly! That’s STACK.”**

# 📚 **STACK = LIFO (Last-In, First-Out)**

* The last book you put inside
* Is the first book that comes out

This is how Stack behaves:

### ✔ push → insert at top

### ✔ pop → remove from top

### ✔ peek → check the top element

### ✔ isEmpty → verify if empty

And guess what?
**Linked List is the perfect backbone to build this.**

# 🧱 **Key Idea: Insert & Remove from HEAD**

Why?

Because:

### 👉 Inserting at head = O(1)

### 👉 Removing from head = O(1)

And Stack needs FAST push/pop.

# 🔧 **Node Class (simple building block)**

```java
package com.dsa.stack;

import java.io.Serializable;

public class Node implements Serializable {
    int data;
    Node next;

    public Node(int data) {
        this.data = data;
    }
}
```

# 🧱 **LinkedList as Stack**

Now Rohan walks to the board and writes:

```java
package com.dsa.stack;

import java.io.Serializable;

public class LinkedListStack implements Serializable {

    private Node top;  // top of stack

    public LinkedListStack() {
        top = null;
    }

    // Stack Operation: PUSH (insert at top)
    public void push(int data) {
        Node newNode = new Node(data);

        newNode.next = top;   // link old top below
        top = newNode;        // move top to new node
    }

    // Stack Operation: POP (remove from top)
    public int pop() {
        if (top == null) {
            throw new RuntimeException("Stack is empty");
        }

        int value = top.data;
        top = top.next;       // move top down
        return value;
    }

    // Peek top element
    public int peek() {
        if (top == null) {
            throw new RuntimeException("Stack is empty");
        }
        return top.data;
    }

    // Check empty
    public boolean isEmpty() {
        return top == null;
    }

    // Display
    public void display() {
        System.out.println("Stack (top to bottom):");
        Node current = top;
        while (current != null) {
            System.out.println(current.data);
            current = current.next;
        }
        System.out.println("----------------------------");
    }
}
```

# 🎥 **Mentor Story – How Push Works**

Rohan draws:

```
top → 30
       ↓
      20
       ↓
      10
```

Aarav pushes 40:

```
push(40)
```

Mentor explains:

* Create new node 40
* Point it to old top
* Move `top` to 40

```
top → 40
       ↓
      30
       ↓
      20
       ↓
      10
```

# 🎥 **Mentor Story – How Pop Works**

```
pop() removes 40
top moves to 30
```

Aarav smiles:
**“This is exactly my school bag story!”**

Mentor laughs:
“That’s why I used your example.”

# 🧪 **Test the Stack**

```java
public class TestStack {
    public static void main(String[] args) {

        LinkedListStack stack = new LinkedListStack();

        stack.push(10);
        stack.push(20);
        stack.push(30);
        stack.push(40);

        stack.display();

        System.out.println("Popped: " + stack.pop());
        System.out.println("Top Element: " + stack.peek());

        stack.display();
    }
}
```

# 🧾 **Expected Output**

```
Stack (top to bottom):
40
30
20
10
----------------------------
Popped: 40
Top Element: 30
Stack (top to bottom):
30
20
10
----------------------------
```

# 🌟 **Mentor's Final Speech**

“A Queue teaches discipline…
But a Stack teaches responsibility.

You can only take out what you last kept.

And with LinkedList, your Stack becomes
lightweight, fast, flexible, and dynamic.”
