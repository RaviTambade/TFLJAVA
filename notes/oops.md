# 🌟 ** A Complete OOP Story in the Banking World **

*(A cinematic, mentor-guided journey where Java’s Object-Oriented concepts come alive inside a real bank.)*

# 🏦 **Chapter 1: Welcome to Sunrise Bank**

Rohan walked into Sunrise Bank, a place buzzing with customers, clerks, managers… and Ravi, his mentor.

“Rohan,” Ravi said, “today your won’t just *learn* Object-Oriented Programming.
you will *live* it.”

He looked around, confused.

Ravi smiled and whispered:

> “This entire bank is an Object-Oriented system.
> Every person, every account, every transaction… an object.”

And the journey began.


# 🧱 **Chapter 2: The Blueprint Rooms (Classes)**

Ravi guided Rohan into a restricted section called **Blueprint Rooms**.

“These rooms,” Ravi explained, “hold the *classes*—the designs that create real objects.”

There were rooms labeled:

* `Customer`
* `Account`
* `SavingsAccount`
* `CurrentAccount`
* `Transaction`
* `Loan`
* `Bank`

Each room had detailed blueprints:
**fields**, **methods**, **rules**, **behaviors**.

Rohan realized:

> “Classes are like architectural plans of the bank’s entities.”

Ravi nodded proudly.

# 👤 **Chapter 3: The Birth of a Customer (Objects)**

Ravi took him to the Customer Desk.

A clerk typed:

```java
Customer c1 = new Customer("Ravi", "9876543210");
```

A small holographic person appeared—**Ravi**, a real customer object.

Rohan stared.

“Every time you use `new`,” Ravi explained,
"You create a new object with its own identity and memory.”

```java
Customer c2 = new Customer("Anita", "8765432109");
```

Another customer appeared.

“Same blueprint,” Ravi said, “different objects.”

# 🏧 **Chapter 4: Encapsulation – Protecting Bank Secrets**

Now Ravi entered the **Vault Area**.

“Inside these vaults,” Ravi said,
“we store customers’ account balances, passwords, and sensitive data.”

To protect them:

```java
class Account {
    private double balance;
    
    public double getBalance() { return balance; }
    public void deposit(double amount) { balance += amount; }
    public void withdraw(double amount) {
        if(balance >= amount) balance -= amount;
    }
}
```

Rohan understood:

> “Encapsulation is security.
> Customers cannot directly manipulate the balance.
> They must go through bank-approved methods.”

Exactly.

# 🧬 **Chapter 5: Inheritance – Banking Family Tree**

Ravi now entered the **Account Hierarchy Hall**.

On the wall:

```
         Account
        /       \
SavingsAccount  CurrentAccount
```

Ravi explained:

“Every **SavingsAccount** *is an* Account.
Every **CurrentAccount** *is also an* Account.”

```java
class SavingsAccount extends Account { }
class CurrentAccount extends Account { }
```

Rohan smiled:

> “So they inherit balance, deposit, withdraw…
> But can also add extra features?”

“Yes! That’s the beauty of inheritance.”

# 🦸 **Chapter 6: Polymorphism – One Form, Many Behaviors**

Ravi summoned two accounts:

* SavingsAccount sa
* CurrentAccount ca

Then Ravi stored them in a common Account pointer:

```java
Account acct;

acct = new SavingsAccount();
acct.withdraw(2000);  // Savings rule applies

acct = new CurrentAccount();
acct.withdraw(2000);  // Current rule applies
```

Rohan gasped:

> “Same method call, different behavior?”

Ravi smiled:

“That, my boy, is **polymorphism**.”

SavingsAccount might impose limits.
CurrentAccount might allow overdrafts.

Same withdrawal request → different responses.

# 🎨 **Chapter 7: Abstraction – ATM Interface**

Ravi walked to an ATM machine.

“Rohan, see how simple the ATM interface is?”

* Insert card
* Enter PIN
* Choose withdrawal
* Enter amount

But behind the scenes:

* Network calls
* Fraud checks
* Balance validations
* Transaction logs
* Notifications

All are hidden.

Ravi declared:

```java
interface ATMOperations {
    void withdraw();
    void checkBalance();
}
```

“Abstraction gives only necessary details,” Ravi said.
“Just like the ATM.”

# 📜 **Chapter 8: Transactions – The Lifeline of the Bank**

Ravi opened the **Transaction Ledger Room**.

Every activity created a new object:

```java
Transaction t = new Transaction(accountId, "WITHDRAW", 500);
```

Each transaction had:

* timestamp
* account involved
* amount
* type
* status

“These objects ensure transparency,” Ravi said.

Rohan nodded.

# 🏦 **Chapter 9: The Bank Object – The Big Manager**

Finally, Ravi showed Rohan the **Bank class**.

“This object manages everything.”

```java
class Bank {
    List<Customer> customers;
    List<Account> accounts;
    List<Transaction> transactions;

    void openAccount(Customer c, String type) { }
    void processTransaction(Transaction t) { }
}
```

Rohan realized:

> “So the Bank object orchestrates all other objects.”

Exactly.

# ✨ **Chapter 10: Rohan’s Realization – OOP is Real Life**

By the end of the tour, Rohan said:

> “Sir…
> Java’s OOP is not artificial.
> It is a reflection of real life.
>
> Customers, accounts, transactions, inheritance, encapsulation…
> Everything in the bank behaves exactly like objects in Java.”

Ravi placed a hand on his shoulder:

“Remember, Rohan…
Good programmers don’t write code first.
They imagine a real world…
And then let Java bring it alive.”

## Learning Java OOP through a Product Store

### 🛍️ Scene: The Product Store

Once upon a time in the land of JavaLand, Young learners opened a digital **Product Store**. But before they could sell anything, they had to model the idea of a **Product** in code.

Their Mentor appeared and said:

> "To build anything big in software, first you must master the art of **Object-Oriented Thinking**. Let’s begin with a `Product`."
### 🧱 Step 1: Create a Class – The Blueprint

```java
public class Product {
    // Attributes (state)
    int id;
    String name;
    double price;

    // Constructor
    public Product(int id, String name, double price) {
        this.id = id;
        this.name = name;
        this.price = price;
    }

    // Method (behavior)
    public void displayDetails() {
        System.out.println("Product ID: " + id);
        System.out.println("Name: " + name);
        System.out.println("Price: ₹" + price);
    }
}
```

🧙‍♂️ Mentor explains:

> "A class is like a **blueprint**. It defines what a product is — its **attributes** (id, name, price) and **behaviors** (like displaying details). But this class alone is not enough — it’s not a real product yet!"

### 🧩 Step 2: Create Objects – Real Products

Now, the students write a `main` method:

```java
public class Main {
    public static void main(String[] args) {
        Product p1 = new Product(101, "Pen", 10.5);
        Product p2 = new Product(102, "Notebook", 55.0);

        p1.displayDetails();
        p2.displayDetails();
    }
}
```

Mentor smiles and says:

> "An **object** is a real-world instance of a class. Here, `p1` and `p2` are real products, living in memory. Each has its own state."


### 🎯 Let’s Talk Object-Oriented Concepts

#### ✅ 1. **Encapsulation**

We should protect data using `private` and access it using **getters/setters**:

```java
public class Product {
    private int id;
    private String name;
    private double price;

    // Constructor
    public Product(int id, String name, double price) {
        this.id = id;
        this.name = name;
        this.price = price;
    }

    // Getters
    public int getId() { return id; }
    public String getName() { return name; }
    public double getPrice() { return price; }

    // Setters
    public void setPrice(double price) { this.price = price; }

    public void displayDetails() {
        System.out.println(id + " | " + name + " | ₹" + price);
    }
}
```

🧙 Mentor says:

> "Always protect Your data. Don’t let outsiders directly change Your variables."


#### ✅ 2. **Abstraction**

You hide complexity from the outside world.

```java
public class Product {
    // internal details like calculations or tax logic can be hidden
    public double getDiscountedPrice(double discountPercent) {
        return price - (price * discountPercent / 100);
    }
}
```

> "Give others simple methods to work with. Hide the messy logic inside."


#### ✅ 3. **Inheritance** (for later learning)

Let’s say we also have a `DigitalProduct`:

```java
public class DigitalProduct extends Product {
    private double fileSize;

    public DigitalProduct(int id, String name, double price, double fileSize) {
        super(id, name, price);
        this.fileSize = fileSize;
    }

    public void showFileSize() {
        System.out.println("File Size: " + fileSize + "MB");
    }
}
```

🧙 Mentor:

> "Child classes inherit from parent. `DigitalProduct` is a **specialized type** of `Product`."

#### ✅ 4. **Polymorphism** (one day You’ll love this)

```java
Product product = new DigitalProduct(201, "E-book", 200.0, 2.5);
product.displayDetails(); // Calls base version or overridden one

// At runtime, behavior adapts based on actual object
```

### 💎 Final Wisdom

The mentor puts it all together:

> “Everything around you is an object: a pen, a student, a book, even this app. Java lets you build real-world solutions by mimicking real-world objects. Once you master `class` and `object`, Your world of software will never be the same.”

### 📘 Practice Task for the Student

* Create a `Customer` class.
* Add fields: id, name, email.
* Add a method to show customer details.
* Create 2 customer objects and call the method.
 

## 🛠️ Project: **Mini Product Management Console App**

> Objective: Learn how to create, list, and search products using **Java + OOP + Menu-based Console UI**.


## 🎨 Step 1: Define the `Product` Class

This is Your **blueprint** for any product in the system.

```java
public class Product {
    private int id;
    private String name;
    private double price;

    public Product(int id, String name, double price) {
        this.id = id;
        this.name = name;
        this.price = price;
    }

    public int getId() { return id; }
    public String getName() { return name; }
    public double getPrice() { return price; }

    public void setPrice(double price) { this.price = price; }

    public void displayDetails() {
        System.out.println("ID: " + id + ", Name: " + name + ", Price: ₹" + price);
    }
}
```

## 🗃️ Step 2: Create a `ProductRepository`

This handles **data management**—adding, listing, and searching.

```java
import java.util.ArrayList;
import java.util.List;

public class ProductRepository {
    private List<Product> products = new ArrayList<>();

    public void addProduct(Product p) {
        products.add(p);
    }

    public List<Product> getAllProducts() {
        return products;
    }

    public Product findById(int id) {
        for (Product p : products) {
            if (p.getId() == id) {
                return p;
            }
        }
        return null;
    }
}
```


## 🎯 Step 3: The `ProductService` Layer

Adds a business logic layer (e.g., validate inputs, apply discounts, etc.)

```java
public class ProductService {
    private ProductRepository repo = new ProductRepository();

    public void createProduct(int id, String name, double price) {
        Product p = new Product(id, name, price);
        repo.addProduct(p);
    }

    public void listAllProducts() {
        for (Product p : repo.getAllProducts()) {
            p.displayDetails();
        }
    }

    public void searchProduct(int id) {
        Product p = repo.findById(id);
        if (p != null) {
            p.displayDetails();
        } else {
            System.out.println("Product not found!");
        }
    }
}
```

 

## 🖥️ Step 4: Main Menu – Console-Based UI

```java
import java.util.Scanner;

public class ProductApp {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        ProductService service = new ProductService();

        while (true) {
            System.out.println("\n=== Product Management ===");
            System.out.println("1. Add Product");
            System.out.println("2. List All Products");
            System.out.println("3. Search Product by ID");
            System.out.println("4. Exit");
            System.out.print("Enter choice: ");

            int choice = sc.nextInt();
            sc.nextLine(); // consume leftover newline

            switch (choice) {
                case 1:
                    System.out.print("Enter ID: ");
                    int id = sc.nextInt();
                    sc.nextLine();
                    System.out.print("Enter Name: ");
                    String name = sc.nextLine();
                    System.out.print("Enter Price: ");
                    double price = sc.nextDouble();
                    service.createProduct(id, name, price);
                    System.out.println("✅ Product added!");
                    break;
                case 2:
                    service.listAllProducts();
                    break;
                case 3:
                    System.out.print("Enter ID to search: ");
                    int searchId = sc.nextInt();
                    service.searchProduct(searchId);
                    break;
                case 4:
                    System.out.println("Goodbye!");
                    return;
                default:
                    System.out.println("❌ Invalid choice. Try again.");
            }
        }
    }
}
```

 

## ✅ What You Just Learned

| Concept                    | How We Applied It                             |
| -------------------------- | --------------------------------------------- |
| **Class & Object**         | `Product` and its instances                   |
| **Encapsulation**          | Private fields + public getters/setters       |
| **Abstraction**            | Users don’t see internal data storage logic   |
| **Separation of Concerns** | `Repository`, `Service`, and `UI` roles split |
| **Basic Java Input**       | `Scanner` used for user interaction           |
