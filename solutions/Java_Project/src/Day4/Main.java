package Day4;


public class Main {
    public static void main(String[] args) {
        // Books
        Books books = new Books();
        //System.out.println("Book at index 0: " + books.getTitle());

        // Employee, SalesEmployee, and SalesManager
        Employee employee = new Employee();
        SalesEmployee salesEmployee = new SalesEmployee();
        SalesManager salesManager = new SalesManager();

        employee.calculateSalary();
        salesEmployee.calculateSalary();
        salesManager.calculateSalary();

        System.out.println("------------------------------");
        // Singleton Pattern in Person
        Person person = Person.getInstance();
        person.display("Math", "Science", "History");


        System.out.println("------------------------------");
        int[] nums = {1, 2};
        person.swap(nums);
        System.out.println("Swapped numbers: " + nums[0] + ", " + nums[1]);

        System.out.println("------------------------------");
        
        double[] results = new double[2];
        person.calculate(5, results);
        System.out.println("Area: " + results[0] + ", Circumference: " + results[1]);

        // Product with AutoCloseable
        try (Product product = new Product()) {
            product.setTitle("Laptop");
            product.setUnitPrice(1500.00);
            System.out.println("Product: " + product.getTitle() + ", Price: " + product.getUnitPrice());
        } catch (Exception e) {
            e.printStackTrace();
        }


        System.out.println("------------------------------");
        // Stack Clone
        Stack stack = new Stack(5);
        Stack clonedStack = stack.clone();
        System.out.println("Stack cloned successfully.");


        System.out.println("------------------------------");
        // Student
        Student student = new Student();
        student.setPrn(12345);
        student.setFullName("Rutuja Tambade");
        student.setEmail("rututambade@gmail.com");
        student.setLocation("Pune");
        System.out.println("Student: " + student.getFullName() + ", Location: " + student.getLocation());
    }
}
