
//In Java if no parent defined for a class,
//then parent for that class is Object class.

public class DatabaseManager {
    private String resource;

    //constructor overloading
    public DatabaseManager(){
        System.out.println("calling default constructor");
        System.out.println("Initializing  default resource ");
    }

    public DatabaseManager(String res)

    {   System.out.println("calling parameterized constructor");
        this.resource=res;
        System.out.println("Acquiring resource "+ this.resource);
        //Initializtion   
    }

    public void processQuery(){

    }
    public static void main(String[] args) {

        // Main function is called by Primary Thread
        
        String databaseconnection="mysql";
        DatabaseManager mysqlMgr=new DatabaseManager(databaseconnection);
        mysqlMgr.processQuery();
        mysqlMgr=null;

        System.gc();

        try{
            Thread.sleep(2000);
        }
        catch(InterruptedException e){
            e.printStackTrace();
        }
    }
    
    //gabage collection

    //method overriding
    // This method is called by Garbage Collector
    //Garbage collector is responsible for house keep role 
    //such as   finalization and automatic memory cleanup
    @Override 
    protected void finalize(){
        //perform cleanup
        //Garbage collector is worker thread.
        System.out.println("Garbage Collector is calling finalize");
        System.out.println("Releasing resources ");
    }
}
