//There is also relationship between person and Employee
//is a relationship  : Inheritance

public class Employee extends Person {
    private  Date joinDate;

    public Employee(Date theDate){
        this.theDate=theDate;
    }  
}
