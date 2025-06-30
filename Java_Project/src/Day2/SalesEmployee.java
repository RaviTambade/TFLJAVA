package Day2;

import java.util.Date;

public class SalesEmployee  extends Employee {
    private float target;
    private float salesDone;
    private float incentive;

    // Default constructor
    public SalesEmployee() {
        super();
        this.target = 0;
        this.salesDone = 0;
        this.incentive = 0;
    }

    // Parameterized constructor
    public SalesEmployee(int id, String firstName, String lastName, 
                         String email, String contactNumber, String location,
                         Date birthDate, float da, float hra, float basicSalary,
                         float target, float salesDone) {
        super(id, firstName, lastName, email, contactNumber, location, birthDate, da, hra, basicSalary);
        this.target = target;
        this.salesDone = salesDone;
        this.incentive = 0;
    }

    @Override
    public void doWork() {
        if (salesDone >= target) {
            this.incentive = 5000;
        } else {
            this.incentive = 0;
        }
    }

    @Override
    public float computePay() {
        return super.computePay() + incentive;
    }

    @Override
    public String toString() {
        return super.toString() + " Incentive=" + incentive + ", Target=" + target;
    }

    // Getters and Setters
    public float getTarget() { return target; }
    public void setTarget(float target) { this.target = target; }

    public float getSalesDone() { return salesDone; }
    public void setSalesDone(float salesDone) { this.salesDone = salesDone; }

    public float getIncentive() { return incentive; }
    public void setIncentive(float incentive) { this.incentive = incentive; }
}