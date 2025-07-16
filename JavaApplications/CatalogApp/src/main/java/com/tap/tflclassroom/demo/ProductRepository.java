package com.tap.tflclassroom.demo;
import java.util.*;

//Contract , Specification, Agreement, Policy

//Interface Inheritance
public class  ProductRepository implements IProductRepository {

    @Override
    public boolean  insert(){
        boolean status=false;
        return status;
    }

    @Override  
    public boolean  update(){
        boolean status=false;
        return status;
    }


    @Override
    public boolean  remove(){
          boolean status=false;
        return status;
    }

    @Override
    public List<Product> getAll(){
        ArrayList<Product> products=new ArrayList<Product>();

        Product p1=new Product();
        p1.id=34;
        p1.title="laptop";
        p1.description="Smart laptop";
        p1.quantity=340;
        products.add(p1);

        Product p2=new Product();
        p2.id=45;
        p2.title="IBM ThinkPad";
        p2.description="Smart IBM  Notebook";
        p2.quantity=654;
        products.add(p2);

        return products;
    }

    @Override
    public Product getById(int id){

        Product theProduct=new Product();
        return theProduct;

    }
}
