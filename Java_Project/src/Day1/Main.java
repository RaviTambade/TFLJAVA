package Day1;

import java.util.ArrayList;
import java.util.List;
import Day1.Account;
import Day1.Complex;
 
public class Main {
    public static void main(String[] args) {
        // Anonymous class in Java
        Person p = new Person("Rutuja", "Tambade");
        System.out.println(p.getFirstName() + " " + p.getLastName());

        // Demonstrating variables and output
        int count = 12;
        String company = "Transflower";
        count++;
        System.out.println("Count = " + count);
        System.out.println(company);
        System.out.println("Hello, World!");

        // Creating Account objects and performing operations
        Account acc123 = new Account(60000);
        acc123.deposit(15000);
        float currentBalance123 = acc123.getBalance();

        Account acc124 = new Account(20000);
        acc124.deposit(8000);
        float currentBalance124 = acc124.getBalance();

        // Collection of Accounts
        List<Account> accounts = new ArrayList<>();
        accounts.add(acc123);
        accounts.add(acc124);

        for (Account account : accounts) {
            float result = account.getBalance();
            System.out.println("Current Balance = " + result);
        }

        // Complex numbers and addition
        Complex c1 = new Complex(34, 56);
        Complex c2 = new Complex(11, 78);
        Complex c3 = c1.add(c2);
        System.out.println("Result of Complex Addition: (" + c3.getReal() + ", " + c3.getImag() + ")");
    }
}

// Supporting Person class for the anonymous object example
class Person {
    private String firstName;
    private String lastName;

    public Person(String firstName, String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }
}