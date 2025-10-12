//class relationship is a 
//Inheritance 

public class SalesEmployee extends Employee{
    private double salesTarget;
    private double incentives;

    public SalesEmployee(double target, double incentives){
        super("Rutuja", 35, 1000, 10000, 20000  );
        this.salesTarget=target;
        this.incentives=incentives;
    }

    public double computePay(){
        return super.computePay()+ incentives;
    }
}


//Single Responsbility Principle