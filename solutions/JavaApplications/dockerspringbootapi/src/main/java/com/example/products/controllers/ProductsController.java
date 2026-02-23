package com.example.products.controllers;

import com.example.products.entities.Product;
import com.example.products.services.ProductService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;


@RestController
@RequestMapping("/api/products")
@RequiredArgsConstructor
@Slf4j
public class ProductsController {

    private final ProductService productService;

    // ── GET /api/products ─────────────────────────────────────────────────────
    @GetMapping
    public ResponseEntity<List<Product>> getAllProducts(
            @RequestParam(required = false) String category,
            @RequestParam(required = false) Boolean active) {

        List<Product> products;
        if (category != null) {
            products = productService.getProductsByCategory(category);
        } else if (Boolean.TRUE.equals(active)) {
            products = productService.getActiveProducts();
        } else {
            products = productService.getAllProducts();
        }
        return ResponseEntity.ok(products);
    }

    // ── GET /api/products/count ───────────────────────────────────────────────
    @GetMapping("/count")
    public ResponseEntity<Map<String, Long>> getProductCount() {
        return ResponseEntity.ok(Map.of("count", productService.getProductCount()));
    }

    // ── GET /api/products/{id} ────────────────────────────────────────────────
    @GetMapping("/{id}")
    public ResponseEntity<Product> getProductById(@PathVariable String id) {
        Product product = productService.getProductById(id);
        return ResponseEntity.ok(product);
    }

    // ── POST /api/products ────────────────────────────────────────────────────
    @PostMapping
    public ResponseEntity<Product> createProduct(@Valid @RequestBody Product product) {
        Product created = productService.createProduct(product);
        return ResponseEntity.status(HttpStatus.CREATED).body(created);
    }

    // ── PUT /api/products/{id} ────────────────────────────────────────────────
    @PutMapping("/{id}")
    public ResponseEntity<Product> updateProduct(
            @PathVariable String id,
            @Valid @RequestBody Product product) {
        Product updated = productService.updateProduct(id, product);
        return ResponseEntity.ok(updated);
    }

    // ── DELETE /api/products/{id} ─────────────────────────────────────────────
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProduct(@PathVariable String id) {
        productService.deleteProduct(id);
        return ResponseEntity.noContent().build();
    }
}
