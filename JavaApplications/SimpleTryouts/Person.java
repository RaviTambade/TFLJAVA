public class Person {

    //Data Members
    public String firstName;
    public String middleName;
    public String lastName;
    public int age;

    public static void main(String[] args){
        //How many instances of person class are created ?  : 3
        Person p1=new Person();
        //Assigning values to data members of p1
        p1.firstName="Yash";
        p1.middleName="Shrinivas";
        p1.lastName="Gawade";
        p1.age=19;

        Person p2=new Person();
        //Assigning values to data members of p2 
        p2.firstName="Sandeep";
        p2.middleName="Dhnyaneshwar";
        p2.lastName="Kale";
        p2.age=33;

        Person p3=new Person();
           //Assigning values to data members of p3
        p3.firstName="Ravi";
        p3.middleName="Vasamt";
        p3.lastName="Tambade";
        p3.age=48;

        //Creating collection of Person 

        Person []  people=new Person[3];
        people[0]=p1;
        people[1]=p2;
        people[2]=p3;

        //Print each data member of each person object from people collection
        //for loop
        for(Person person:people){
            System.out.println(person.firstName);
            System.out.println(person.middleName);
            System.out.println(person.lastName);
            System.out.println("\n");
        }
    }
}
