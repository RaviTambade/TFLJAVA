import java.io.*;
import java.lang.*;

//User defined Data Type

public class Hello{
    public static void main(String [] args){
      
        //two types of data types:
        // 1.Value Types (Primitive Data Types)
              //data types: int, float, double, boolean, char,enum, etc.

              //values of these variables pushed on stack
        // 2.Reference Types
             //data Types: class, interface  (User defined data Types)

        int count=45;
        boolean status=true;
        if(status ==true){
            System.out.println("status=" + status);
        }
        else{
            System.out.println("status=" + status);
        }

        //We are creating object of class Hello
        //We are creating instance of class Hello

        Hello instance=new Hello();

        System.out.println("Hello World");
    }
}