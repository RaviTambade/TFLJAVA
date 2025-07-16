package com.tap.tflclassroom.demo;
import java.util.*;

public class App 
{ 
    public static void main( String[] args ) throws Exception
    {
        
        //Polymorphism

        IProductRepository repo=new ProductRepository();
        List<Product> products=repo.getAll();

        for (Product product : products) { 
            System.out.println(product.title);
            System.out.println(product.description);
            System.out.println(product.quantity);
        }
    }
}



//mvn --version   
//mvn archetype:generate -DgroupId=com.tap.tflclassroom.demo -DartifactId=tflclassroom-demo -DarchetypeArtifactId=maven-archetype-quickstart -DinteractiveMode=false
//mvn clean install
//mvn exec:java -Dexec.mainClass="com.tap.tflclassroom.demo
