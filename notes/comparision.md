## "The Sorting of Students"

> “Come here, kids,” said Mentor Ravi, pulling out a dusty ledger from the shelf.
> “Let me tell you a story of how Java learned to compare apples to oranges — or should I say, Students by Name, Roll Number, or Marks!”

### 🏫 **📘 The Classroom Analogy**

Once upon a time in Java High School, the principal wanted to sort a list of students.

Now, each `Student` had:

* A **name**
* A **roll number**
* A **total marks**

But here's the twist:

🧩 Sometimes they wanted to sort students **by name**,
🧩 sometimes **by roll number**,
🧩 and sometimes **by marks**.

Now Java had a problem:
**How do we tell the sorting algorithm how to compare two students?**

### 🎯 Solution #1: `Comparable` — The Natural Order

Mentor Ravi explained:

> “Imagine every student knows how to compare themselves with another student. That’s `Comparable`.”

```java
class Student implements Comparable<Student> {
    String name;
    int roll;
    int marks;

    public Student(String name, int roll, int marks) {
        this.name = name;
        this.roll = roll;
        this.marks = marks;
    }

    @Override
    public int compareTo(Student other) {
        return this.roll - other.roll; // Natural order by roll number
    }
}
```

🧠 **Comparable** defines the *natural* ordering inside the class itself using `compareTo()`.

### ⚖️ Solution #2: `Comparator` — The External Judge

But one day, the principal said:

> “I don’t want the natural order. I want to compare by **marks** today. And **name** tomorrow!”

That’s where **Comparator** came in — an **external decision maker**.

```java
class CompareByMarks implements Comparator<Student> {
    public int compare(Student s1, Student s2) {
        return s2.marks - s1.marks; // Descending order of marks
    }
}

class CompareByName implements Comparator<Student> {
    public int compare(Student s1, Student s2) {
        return s1.name.compareTo(s2.name);
    }
}
```

Now the principal could **plug in different comparators** depending on the sorting need:

```java
Collections.sort(studentList, new CompareByMarks());
Collections.sort(studentList, new CompareByName());
```

💡 Java is happy. Flexibility achieved.

### 📊 At a Glance

| Feature     | `Comparable`                   | `Comparator`                         |
| ----------- | ------------------------------ | ------------------------------------ |
| Location    | Inside the class               | Outside the class                    |
| Interface   | `compareTo(T o)`               | `compare(T o1, T o2)`                |
| Use case    | One natural/default sort logic | Multiple, custom sort logics         |
| Example use | `Collections.sort(list)`       | `Collections.sort(list, comparator)` |


### 🛠️ Hands-On Code Summary

```java
import java.util.*;

class Student implements Comparable<Student> {
    String name;
    int roll;
    int marks;

    public Student(String name, int roll, int marks) {
        this.name = name;
        this.roll = roll;
        this.marks = marks;
    }

    public String toString() {
        return name + " (" + roll + ") - " + marks;
    }

    @Override
    public int compareTo(Student other) {
        return this.roll - other.roll;
    }
}

class CompareByMarks implements Comparator<Student> {
    public int compare(Student a, Student b) {
        return b.marks - a.marks; // Descending
    }
}

class CompareByName implements Comparator<Student> {
    public int compare(Student a, Student b) {
        return a.name.compareTo(b.name);
    }
}

public class Main {
    public static void main(String[] args) {
        List<Student> students = Arrays.asList(
            new Student("Riya", 3, 87),
            new Student("Aman", 1, 92),
            new Student("Kanchan", 2, 78)
        );

        System.out.println("Natural Order (by Roll):");
        Collections.sort(students);
        students.forEach(System.out::println);

        System.out.println("\nSorted by Marks:");
        Collections.sort(students, new CompareByMarks());
        students.forEach(System.out::println);

        System.out.println("\nSorted by Name:");
        Collections.sort(students, new CompareByName());
        students.forEach(System.out::println);
    }
}
```

### 👨‍🏫 Final Words from Mentor Ravi

> “Remember dear students —
> Use `Comparable` when objects know how to compare themselves.
> Use `Comparator` when you want **external control**, **flexibility**, or **multiple sorting rules**.”
