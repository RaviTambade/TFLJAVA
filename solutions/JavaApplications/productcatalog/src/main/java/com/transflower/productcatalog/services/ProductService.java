package com.transflower.productcatalog.services;
import java.util.ArrayList;
import java.util.List;
import org.springframework.stereotype.Service;

import com.transflower.productcatalog.entities.Product;

@Service
public class ProductService {

    public List<Product> getAll(){
        List<Product> flowers=new ArrayList<>();
        // For demonstration purposes, adding some sample products
        flowers.add(new Product(1, "Gerbera","Wedding Flower",10));
        flowers.add(new Product(2, "Rose","Valentine Flower",19));
        flowers.add(new Product(4, "Jasmine","Smelling Flower",2));
        return flowers;
    }

    public Product getById(int id){
        return new Product(1, "Gerbera","Wedding Flower",10);
    }

    public void delete(int  id){
        
    }

    public void saveOrUpdate(Product flower){

    }
}