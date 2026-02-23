package com.example.products.services;

import com.example.products.entities.Product;

import java.util.List;

public interface ProductService {

    List<Product> getAllProducts();

    Product getProductById(String id);

    List<Product> getProductsByCategory(String category);

    List<Product> getActiveProducts();

    Product createProduct(Product product);

    Product updateProduct(String id, Product product);

    void deleteProduct(String id);

    long getProductCount();
}
