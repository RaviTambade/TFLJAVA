package Membership;

import java.util.Scanner;

import Day2.Employee;
import Day2.SalesManager;

public class Main {
    public static void main(String[] args) {
        System.out.println("TFL Store ");
        System.out.println("******************************");

        Scanner scanner = new Scanner(System.in);

        // Getting user input for email and password
        System.out.println("Enter your Email ID:");
        String email = scanner.nextLine();

        System.out.println("Enter your password:");
        String password = scanner.nextLine();

        // Validate user credentials
        if (AuthManager.validate(email, password)) {
            System.out.println("Welcome to Transflower Store");

            // Create an Employee of type SalesManager and perform operations
            Employee emp = new SalesManager();
            emp.doWork();
            System.out.println(emp);

            // Calculate and display salary
            float salary = emp.computePay();
            System.out.println("Salary = " + salary);
        } else {
            System.out.println("Invalid User");
        }

        System.out.println("Thank you for visiting Transflower");
        scanner.close();
    }
}