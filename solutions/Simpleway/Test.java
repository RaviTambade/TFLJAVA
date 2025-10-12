public class Test{
    public static void main(String [] args){

        SalesEmployee salesperson1=new SalesEmployee(450000,15000);
        double salary =salesperson1.computePay();

        System.out.println("Sales Employee salary="+ salary);

    }
}