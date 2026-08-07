##  Building a Company’s Employee System

Alright, imagine we’re working together at a software company, and we’ve been asked to model employee data for the HR system. We want to start simple with two types of employees:

1️⃣ A **general employee** — every employee has a name, an ID, and a base salary.

2️⃣ A **sales employee**, who is also an employee but earns extra commission on top of the base salary.


### **First: Base Class - Employee**

We start by creating an `Employee` class. It contains:

* `id`: unique identifier.
* `name`: employee name.
* `salary`: base salary.

This class represents *common properties* of any employee.

```java
public class Employee {
    protected int id;
    protected String name;
    protected double salary;

    public Employee(int id, String name, double salary) {
        this.id = id;
        this.name = name;
        this.salary = salary;
    }

    public void display() {
        System.out.println("ID: " + id + ", Name: " + name + ", Salary: " + salary);
    }
}
```

  

### **Inheritance: SalesEmployee extends Employee**

We know that a sales employee is *still* an employee, but with *extra properties* like commission. This is a classic case of **inheritance**: we extend the `Employee` class.

In `SalesEmployee`, we add:

* `commission`: extra earnings based on sales.

```java
public class SalesEmployee extends Employee {
    private double commission;

    public SalesEmployee(int id, String name, double salary, double commission) {
        super(id, name, salary); // reuse Employee's constructor
        this.commission = commission;
    }

    @Override
    public void display() {
        super.display();
        System.out.println("Commission: " + commission);
    }
}
```

### **Containment: Adding Address to Employee**

Now, let’s say every employee also has an address. But instead of putting address fields directly into `Employee`, we create a separate `Address` class — that’s **containment** (also called *composition*), where one class *contains* another.

```java
public class Address {
    private String city;
    private String state;
    private String zip;

    public Address(String city, String state, String zip) {
        this.city = city;
        this.state = state;
        this.zip = zip;
    }

    public void display() {
        System.out.println("Address: " + city + ", " + state + " - " + zip);
    }
}
```

Then in `Employee`, we add an `Address` reference:

```java
public class Employee {
    protected int id;
    protected String name;
    protected double salary;
    protected Address address; // Containment: HAS-A relationship

    public Employee(int id, String name, double salary, Address address) {
        this.id = id;
        this.name = name;
        this.salary = salary;
        this.address = address;
    }

    public void display() {
        System.out.println("ID: " + id + ", Name: " + name + ", Salary: " + salary);
        if (address != null) {
            address.display();
        }
    }
}
```

 

### **Putting It All Together**

Finally, let’s create an object and see inheritance and containment in action:

```java
public class Main {
    public static void main(String[] args) {
        Address addr = new Address("Pune", "Maharashtra", "411001");

        SalesEmployee salesEmp = new SalesEmployee(101, "Ravi Tambade", 50000, 8000);
        salesEmp.address = addr; // setting contained object

        salesEmp.display();
    }
}
```

 
### ** What Did We Learn?**

- ✅ **Inheritance**: `SalesEmployee extends Employee` → SalesEmployee *is-a* specialized Employee.
- ✅ **Containment**: `Employee has an Address` → Employee *has-a* Address → a perfect example of composition.
- ✅ **Advantages**:

* Reuse: You don’t rewrite name, ID, or salary in `SalesEmployee`.
* Maintainability: Address can evolve separately without affecting the Employee logic.
* Scalability: Tomorrow, you can add `ManagerEmployee` or `InternEmployee` classes, and all share common employee functionality.


### **Why Does This Matter?**

In real software projects, we combine **inheritance** for *IS-A* relationships (specialization) and **containment** for *HAS-A* relationships (composition). Together, they help us model complex real-world scenarios cleanly.