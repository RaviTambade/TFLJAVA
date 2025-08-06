

// Inheritance in Java allows a class to inherit fields and methods from another class.
//Super class (Parent class)
//Sub class


public class DBManager {

    //private data memvbers
    private   String DB_NAME = "transflower.db";
    private   int DB_VERSION = 1;


    //public member functions

    public void displayAllTopics(){
        // This method will display all topics from the database
        // Implementation will be added later
        System.out.println("Displaying all topics from the database...");
        System.out.println("Database Name: " + DB_NAME);
        System.out.println("Database Version: " + DB_VERSION);
    }

    public DBManager() {
        // Constructor to initialize the database connection
        // Implementation will be added later
        System.out.println("Initializing database connection...");
 


    }
   
    //Overridable
    @Override
    protected void finalize(){

        // This method is called when the object is garbage collected
        System.out.println("DBManager object is being garbage collected");
       
    }   

    //Shared methods for database operations can be added here
    public static void main(String[] args) {
        System.out.println("Welcome to the Transflower Database Manager");
        //automatic memory management : GC
        DBManager dbManager = new DBManager();
        dbManager.displayAllTopics();
    }
    // Additional methods for database operations can be added here
}
