package com.transflower.productcatalog.controllers;
import java.util.List;
import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.transflower.productcatalog.entities.Product;
import com.transflower.productcatalog.services.ProductService;

@RestController
public class ProductsController {

    private final ProductService productService;

    //@Autowired
    public ProductsController(ProductService service) {
        this.productService = service;
    }
    
    @GetMapping("/products")  
    private List<Product> getAllProducts()   
    {  
        List<Product> flowers=this.productService.getAll();
        return flowers;
    }  
     
    @GetMapping("/products/{id}")  
    private Product getProduct(@PathVariable("id") int id)   
    {  
       return productService.getById(id);  
    }  
   
    @DeleteMapping("/products/{id}")  
    private void deleteProduct(@PathVariable("id") int id)   
    {  
        productService.delete(id);  
    }  
    //creating post mapping that post the book detail in the database  
    @PostMapping("/products")  
    private int saveProduct(@RequestBody Product flower)   
    {  
        //productService.saveOrUpdate(flower);  
        //return flower.geId();  
        return 1;
    }  
    //creating put mapping that updates the book detail   
    @PutMapping("/products")  
    private Product updateProduct(@RequestBody Product flower)   
    {  
        productService.saveOrUpdate(flower);  
        return flower;  
    }  
}