package com.example.products.repositories;

import com.example.products.entities.Product;
import java.util.List;
import java.util.Optional;

public interface ProductRepository {

    List<Product> findAll();

    Optional<Product> findById(String id);

    List<Product> findByCategory(String category);

    List<Product> findByActiveTrue();

    Product save(Product product);

    void deleteById(String id);

    boolean existsById(String id);

    long count();
}
