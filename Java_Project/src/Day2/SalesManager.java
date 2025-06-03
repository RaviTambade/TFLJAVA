package Day2;

import java.util.Date;

public class SalesManager extends SalesEmployee {
    private float bonus;

    // Default constructor
    public SalesManager() {
        super();
        this.bonus = 0;
    }

    // Parameterized constructor
    public SalesManager(int id, String firstName, String lastName, 
                        String email, String contactNumber, String location,
                        Date birthDate, float da, float hra, float basicSalary,
                        float target, float salesDone, float bonus) {
        super(id, firstName, lastName, email, contactNumber, location, birthDate, da, hra, basicSalary, target, salesDone);
        this.bonus = bonus;
    }

    @Override
    public float computePay() {
        return super.computePay() + bonus;
    }

    @Override
    public void doWork() {
        super.doWork();
    }

    @Override
    public String toString() {
        return super.toString() + " Bonus=" + bonus;
    }

    // Getters and Setters
    public float getBonus() { return bonus; }
    public void setBonus(float bonus) { this.bonus = bonus; }
}