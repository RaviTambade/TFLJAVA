package Day4;

public class Employee {
    public void calculateSalary() {
        System.out.println("Employee: CalculateSalary");
    }

    public void doWork() {
        // Base implementation
    }
}

class SalesEmployee extends Employee {
    @Override
    public void calculateSalary() {
        System.out.println("Sales Employee: CalculateSalary");
    }

    @Override
    public void doWork() {
        super.doWork();
    }
}

class SalesManager extends SalesEmployee {
    @Override
    public void calculateSalary() {
        super.calculateSalary();
    }
}