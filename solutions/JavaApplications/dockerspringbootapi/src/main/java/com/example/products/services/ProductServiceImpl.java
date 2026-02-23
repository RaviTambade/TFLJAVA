package com.example.products.services;

import com.example.products.entities.Product;
import com.example.products.repositories.ProductRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Slf4j
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;

    @Override
    public List<Product> getAllProducts() {
        log.info("Fetching all products");
        return productRepository.findAll();
    }

    @Override
    public Product getProductById(String id) {
        log.info("Fetching product with id: {}", id);
        return productRepository.findById(id)
                .orElseThrow(() -> new ProductNotFoundException("Product not found with id: " + id));
    }

    @Override
    public List<Product> getProductsByCategory(String category) {
        log.info("Fetching products in category: {}", category);
        return productRepository.findByCategory(category);
    }

    @Override
    public List<Product> getActiveProducts() {
        log.info("Fetching active products");
        return productRepository.findByActiveTrue();
    }

    @Override
    public Product createProduct(Product product) {
        LocalDateTime now = LocalDateTime.now();
        product.setId(UUID.randomUUID().toString());
        product.setActive(true);
        product.setCreatedAt(now);
        product.setUpdatedAt(now);
        log.info("Creating product: {}", product.getName());
        return productRepository.save(product);
    }

    @Override
    public Product updateProduct(String id, Product updated) {
        Product existing = getProductById(id);
        existing.setName(updated.getName());
        existing.setDescription(updated.getDescription());
        existing.setPrice(updated.getPrice());
        existing.setCategory(updated.getCategory());
        existing.setStockQuantity(updated.getStockQuantity());
        existing.setActive(updated.isActive());
        existing.setUpdatedAt(LocalDateTime.now());
        log.info("Updating product with id: {}", id);
        return productRepository.save(existing);
    }

    @Override
    public void deleteProduct(String id) {
        if (!productRepository.existsById(id)) {
            throw new ProductNotFoundException("Product not found with id: " + id);
        }
        log.info("Deleting product with id: {}", id);
        productRepository.deleteById(id);
    }

    @Override
    public long getProductCount() {
        return productRepository.count();
    }
}
