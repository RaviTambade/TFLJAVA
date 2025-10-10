public class Employee {

    private String name;
    private int workingDays;
    private double dailyWages;
    private double basicSalary;
    private double tax;

    // Optional: track number of employees
    private static int employeeCount = 0;

    // Constructors
    public Employee(String name, int workingDays, double dailyWages, double basicSalary, double tax) {
        this.name = name;
        this.workingDays = workingDays;
        this.dailyWages = dailyWages;
        this.basicSalary = basicSalary;
        this.tax = tax;
        employeeCount++;
    }

    public Employee(String name, double basicSalary) {
        this.name = name;
        this.basicSalary = basicSalary;
        this.workingDays = 0;
        this.dailyWages = 0;
        this.tax = 0;
        employeeCount++;
    }

    // Getters and Setters
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public int getWorkingDays() { return workingDays; }
    public void setWorkingDays(int workingDays) { this.workingDays = workingDays; }

    public double getDailyWages() { return dailyWages; }
    public void setDailyWages(double dailyWages) { this.dailyWages = dailyWages; }

    public double getBasicSalary() { return basicSalary; }
    public void setBasicSalary(double basicSalary) { this.basicSalary = basicSalary; }

    public double getTax() { return tax; }
    public void setTax(double tax) { this.tax = tax; }

    // Compute pay
    public double computePay() {
        return (workingDays * dailyWages) + basicSalary - tax;
    }

    // Compute pay with bonus
    public double computePay(double bonus) {
        return computePay() + bonus;
    }

    // Calculate tax as percentage
    public void calculateTax(double taxPercentage) {
        this.tax = computePay() * (taxPercentage / 100);
    }

    // Display Employee Info
    public void displayInfo() {
        System.out.println("Name: " + name);
        System.out.println("Working Days: " + workingDays);
        System.out.println("Daily Wages: " + dailyWages);
        System.out.println("Basic Salary: " + basicSalary);
        System.out.println("Tax: " + tax);
        System.out.println("Net Salary: " + computePay());
    }

    // Static method to get employee count
    public static int getEmployeeCount() {
        return employeeCount;
    }
}
