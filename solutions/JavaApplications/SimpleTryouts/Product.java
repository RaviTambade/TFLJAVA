public class Product {
    //Encapsulation: Data Hiding
    public  int id;
    public  String title;
    public String description;
    public float unitPrice;
    public int likes;
    public int quantity;
    
    //When two constructors has same name, that means 
    //it is Constuctor overloading
    //default constructor  (You can call it as well method overloading)
    public Product(){
        // this keyword is used for refering to self instance
        // it is also called self refernce
        this.id=34;
        this.title="Gerbera";
        this.description="Wedding Flower";
        this.unitPrice=10;
        this.likes=56000;
        this.quantity=78000;
    }

    //parameterized constructor
    public Product(int id, String title, String description, float price, int quantity, int likes){

        //values are assigned to data members of object
        this.id=id;
        this.title=title;
        this.description=description;
        this.unitPrice=price;
        this.likes=likes;
        this.quantity=quantity;
    }

    //method overloading
    public void display(){
        System.out.println("display method without parameter is called....");
    }

    public void display(int number){   
        System.out.println("display method with parameter" + number+ " is called");
    }

    public static void main(String[] args) {
        Product p1=new Product();  //object is created using default constructor
        Product p2=new Product();  //object is created using default constructor

        //Object is getting created using parameterized constructor
        Product p3=new Product(54, "Rose","Valentine Flower",34, 5600,9000 );
        Product p4=new Product(23, "Lotus","Worship Flower",40, 1000,15000);
        //How many objects are created ?     4 

        //Print data of each object
        System.out.println(p1.title);
        System.out.println(p2.title);
        System.out.println(p3.title);
        System.out.println(p4.title);  
    }
}
