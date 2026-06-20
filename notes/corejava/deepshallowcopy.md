
## The Photocopy Twins” – Understanding Shallow vs Deep Copy”**

> *"Think of your object as a person with a birth certificate. When you copy the person, are you copying **just the outer image** (like a photocopy), or are you also making a **new birth certificate**? That’s the story of shallow vs deep copy."* – says the mentor, walking into the classroom with two photo frames.

### 📖 The Setup: Meet Our Classes

#### `DateOfBirth.java`

```java
public class DateOfBirth {
    int day;
    int month;
    int year;

    public DateOfBirth(int day, int month, int year) {
        this.day = day;
        this.month = month;
        this.year = year;
    }

    public String toString() {
        return day + "/" + month + "/" + year;
    }
}
```

#### `Person.java`

```java
public class Person {
    String name;
    DateOfBirth dob;

    public Person(String name, DateOfBirth dob) {
        this.name = name;
        this.dob = dob;
    }

    public String toString() {
        return "Name: " + name + ", DOB: " + dob;
    }

    // Shallow Copy
    public Person shallowCopy() {
        return new Person(this.name, this.dob); // Shares same DateOfBirth reference
    }

    // Deep Copy
    public Person deepCopy() {
        DateOfBirth newDob = new DateOfBirth(dob.day, dob.month, dob.year);
        return new Person(this.name, newDob); // Creates a new DateOfBirth object
    }
}
```

### 🧪 Let’s Test the Twins in `Main.java`

```java
public class Main {
    public static void main(String[] args) {
        DateOfBirth originalDob = new DateOfBirth(1, 1, 2000);
        Person original = new Person("Ravi", originalDob);

        // Make copies
        Person shallowCopy = original.shallowCopy();
        Person deepCopy = original.deepCopy();

        System.out.println("Before modifying:");
        System.out.println("Original:     " + original);
        System.out.println("Shallow Copy: " + shallowCopy);
        System.out.println("Deep Copy:    " + deepCopy);

        // Modify original DOB
        original.dob.day = 15;

        System.out.println("\nAfter modifying original DOB:");
        System.out.println("Original:     " + original);
        System.out.println("Shallow Copy: " + shallowCopy);
        System.out.println("Deep Copy:    " + deepCopy);
    }
}
```

### 🎭 Mentor Explains with Analogy

> "Imagine **Ravi** has a twin made by **photocopy** (shallow copy) and another twin made by **re-creating him from scratch** (deep copy). Now, if you go and **change Ravi’s birth date** on the shared certificate, the photocopy twin’s birthday also changes. But the deep copy twin? He's unaffected—he has his **own** certificate."


### 🧠 Final Summary

| Copy Type    | What gets copied                     | Shared References? | Safe from Side Effects? |
| ------------ | ------------------------------------ | ------------------ | ----------------------- |
| Shallow Copy | Primitive fields + object references | ✅ Yes              | ❌ No                    |
| Deep Copy    | Everything including nested objects  | ❌ No               | ✅ Yes                   |

### 💡 Takeaway for Students

> “Always choose deep copy when you want true independence between copies. Shallow copy is faster, but comes with the risk of unintended side effects if mutable references are shared.”

 