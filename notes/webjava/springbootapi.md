# Spring Boot API Strategy

"Sanika, Sahil, Nirjala, Sarthak – listen carefully. You are not just writing lines of Java code. You are actually building a **mini factory**.

Imagine this:

* **Your Postman request** is like a *customer order slip*. The farmer (Postman) comes to the factory gate (Tomcat Server) with a request:
  *‘Give me the result of Candidate with ID = 101.’*

* At the **factory gate** stands the **Controller**. Controller is like the *reception desk*. It takes the customer order slip and says:
  *‘Okay, you want candidate result. Let me check our system.’*
  That is why we put annotations like `@RestController`, `@GetMapping`, `@RequestParam`. These tell the Controller: *“If someone knocks at this gate with this kind of order, you must respond.”*

* But the **Controller itself does not do calculations**. Receptionist never goes to the warehouse. It simply **delegates**.
  The receptionist calls the **Service Department** – that is your `ResultService`.
  Here comes **Dependency Injection**: we don’t create Service manually; Spring Boot auto-creates it and wires it inside Controller using `@Autowired`.

* The **Service** is like the *production department*. It knows the **business rules**:
  *“If candidate ID is valid → fetch the score, if not found → return empty.”*
  But Service still doesn’t touch the database. Service just *plans what needs to be done*.

* Now Service says: *“I need raw materials.”*
  That’s where the **Repository** comes in – `ResultRepository`. This is the *warehouse manager*. Repository directly talks to **MySQL database**.
  It knows how to call stored procedures, how to run SQL queries. That’s why you see **callable statements, setInt, executeQuery** in your code.

* Database is the *actual storage godown*. It has all the results in tables, rows, triggers, and procedures.

So flow is:

👉 **Postman Request (Customer Slip)**
 - ➡️ **Controller (Reception Desk)**
 - ➡️ **Service (Production Department – applies business logic)**
- ➡️ **Repository (Warehouse Manager – fetches from DB)**
- ➡️ **Database (Godown – stores the data)**
- ⬅️ **Repository returns stock**
- ⬅️ **Service processes stock**
- ⬅️ **Controller prepares response**
- ⬅️ **Postman gets the parcel (JSON Response)**

This whole factory runs smoothly only because of **Spring Boot pipeline**.

* `@RestController`, `@Service`, `@Repository` = signboards for each department.
* `@Autowired` = automatic delivery boy who connects one department to another.
* **Tomcat** = factory gate where customers submit requests.
* **Postman** = customer who tests whether your factory delivers correct output.

So tomorrow, when you open Postman and hit `http://localhost:8080/candidate/score?id=101`,
don’t just think *‘oh my method is called’*.
Think: *‘My whole mini factory just got activated – Controller → Service → Repository → Database → back to JSON response.’*

That’s how professional Spring Boot APIs are written – structured, layered, and loosely coupled.