package Day2;

import java.util.Date;

public class main {
    public static void main(String[] args) {
        
        // Create an instance of SalesEmployee
        SalesEmployee salesEmployee = new SalesEmployee(
            1, "Rutuja", "Tambade", "rututambade@gmail.com", "1234567890",
            "pune", new Date(90, 4, 15), 1000, 5000, 20000,
            50000, 55000
        );
        
        salesEmployee.doWork();
        System.out.println("SalesEmployee Pay: " + salesEmployee.computePay());
        System.out.println(salesEmployee);

        // Create an instance of SalesManager
        SalesManager salesManager = new SalesManager(
            2, "Neha", "Bhor", "neha.bhor@example.com", "0987654321",
            "Manchar", new Date(85, 10, 20), 1200, 6000, 25000,
            60000, 65000, 7000
        );
        
        salesManager.doWork();
        System.out.println("SalesManager Pay: " + salesManager.computePay());
        System.out.println(salesManager);
    }
}