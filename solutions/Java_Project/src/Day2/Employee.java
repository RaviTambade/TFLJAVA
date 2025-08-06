package Day2;


import java.util.Date;

public abstract class Employee {
    private int id;
    private String firstName;
    private String lastName;
    private String email;
    private String contactNumber;
    private String location;
    private Date birthDate;
    private float da;
    private float hra;
    private float basicSalary;

    // Default constructor with constructor chaining
    public Employee() {
        this(12, "Ravi", "Tambade", "ravi.tambade@transflower.in", "9881735801",
             "Pune", new Date(75, 7, 18), 1000, 10000, 15000);
    }

    // Parameterized constructor
    public Employee(int id, String firstName, String lastName, 
                    String email, String contactNumber, String location,
                    Date birthDate, float da, float hra, float basicSalary) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.contactNumber = contactNumber;
        this.location = location;
        this.birthDate = birthDate;
        this.da = da;
        this.hra = hra;
        this.basicSalary = basicSalary;
    }

    // Abstract method
    //overriable method to be implemented by subclasses
    public abstract void doWork();

    // Virtual method for calculating pay
    public float computePay() {
        return (da * 25) + basicSalary + hra;
    }

    // toString override
    @Override
    public String toString() {
        return String.format("%d, %s, %s, %s, %s, %s", id, firstName, lastName, email, contactNumber, location);
    }

    // Getters and Setters
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public String getFirstName() { return firstName; }
    public void setFirstName(String firstName) { this.firstName = firstName; }

    public String getLastName() { return lastName; }
    public void setLastName(String lastName) { this.lastName = lastName; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public String getContactNumber() {
     return contactNumber; 
    }
    public void setContactNumber(String contactNumber) { this.contactNumber = contactNumber; }

    public String getLocation() { return location; }
    public void setLocation(String location) { this.location = location; }

    public Date getBirthDate() { return birthDate; }
    public void setBirthDate(Date birthDate) { this.birthDate = birthDate; }

    public float getDa() { return da; }
    public void setDa(float da) { this.da = da; }

    public float getHra() { return hra; }
    public void setHra(float hra) { this.hra = hra; }

    public float getBasicSalary() { return basicSalary; }
    public void setBasicSalary(float basicSalary) { this.basicSalary = basicSalary; }
}
