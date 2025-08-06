public class Customer {
    //Access Specifiers : public, private, protected
    //Visibility of a variable
    //data members , intance variable
    //state 
    private String password;
    public String name;
    private String email;
    protected String membership;

    //behaviour  using Functions 
    //member functions
    //Initializer  Default Constructor

    Customer(){
        System.out.println("Default Constructor is called");
        this.name="Satej";
        this.email="satej.kale@gmail.com";
        this.membership="platinum";
        this.password="123";
    }

    //Shared functions/ static functions/ class functions
    public static void main(String[] args){
        System.out.println("Main function is started Exeucting");

        Customer cst1=new Customer();
        System.out.println( "Name " + cst1.name);

        Customer cst2=new Customer();
        cst2.name="Yash";
        System.out.println( "Name " + cst2.name);
    }
    
}
