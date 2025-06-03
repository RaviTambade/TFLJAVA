//There is a relationship between two classes
// 1. has a relationship------- Containment, Association
//Every Item has a Product

public class Item {

    public int units;
    public  Product product;

    public Item(){
        this.units=0;
        this.product=new Product();
    }
    public Item(Product p, int quantity){
        this.units=quantity;
        this.product=p;
    }
   
    public float getTotal(){
       float amount=this.product.unitPrice * this.units ;
       return amount;
    }

    public static void main(String[] args){

        Product theProduct1=new Product(12,"Colgate toothpaste", "best tooth paste", 50, 34,5600);
        Product theProduct2=new Product(23,"Pepsodent toothbrush", "Universal tooth brush", 15, 5000,15600);

        Item item1=new Item(theProduct1, 5);
        Item item2= new Item(theProduct2,7);

        System.out.println( item1.product.title);
        System.out.println( item2.product.title);
        float totalShoppingAmount=item1.getTotal() + item2.getTotal();
        System.out.println( "total shopping=" +totalShoppingAmount);

        

        

    }
}
