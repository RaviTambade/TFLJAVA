package com.example.products.repositories;

import com.example.products.entities.Product;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import jakarta.annotation.PostConstruct;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;


@Repository
@Slf4j
public class JsonProductRepository implements ProductRepository {

    private final ObjectMapper objectMapper;
    private final Map<String, Product> store = new ConcurrentHashMap<>();

    @Value("${app.data.file-path:data/products.json}")
    private String dataFilePath;

    public JsonProductRepository() {
        this.objectMapper = new ObjectMapper();
        this.objectMapper.registerModule(new JavaTimeModule());
        this.objectMapper.findAndRegisterModules();
    }

    @PostConstruct
    public void init() {
        loadFromFile();
    }

    @Override
    public List<Product> findAll() {
        return new ArrayList<>(store.values());
    }

    @Override
    public Optional<Product> findById(String id) {
        return Optional.ofNullable(store.get(id));
    }

    @Override
    public List<Product> findByCategory(String category) {
        return store.values().stream()
                .filter(p -> p.getCategory().equalsIgnoreCase(category))
                .collect(Collectors.toList());
    }

    @Override
    public List<Product> findByActiveTrue() {
        return store.values().stream()
                .filter(Product::isActive)
                .collect(Collectors.toList());
    }

    @Override
    public Product save(Product product) {
        store.put(product.getId(), product);
        persistToFile();
        return product;
    }

    @Override
    public void deleteById(String id) {
        store.remove(id);
        persistToFile();
    }

    @Override
    public boolean existsById(String id) {
        return store.containsKey(id);
    }

    @Override
    public long count() {
        return store.size();
    }

    // ── Persistence helpers ──────────────────────────────────────────────────

    private void loadFromFile() {
        File file = new File(dataFilePath);
        if (!file.exists()) {
            log.info("No data file found at {}. Starting with empty store.", dataFilePath);
            return;
        }
        try {
            List<Product> products = objectMapper.readValue(file, new TypeReference<List<Product>>() {});
            products.forEach(p -> store.put(p.getId(), p));
            log.info("Loaded {} products from {}", store.size(), dataFilePath);
        } catch (IOException e) {
            log.error("Failed to load products from file: {}", e.getMessage());
        }
    }

    private void persistToFile() {
        try {
            Path path = Paths.get(dataFilePath);
            Files.createDirectories(path.getParent());
            objectMapper.writerWithDefaultPrettyPrinter()
                    .writeValue(path.toFile(), new ArrayList<>(store.values()));
            log.debug("Persisted {} products to {}", store.size(), dataFilePath);
        } catch (IOException e) {
            log.error("Failed to persist products to file: {}", e.getMessage());
        }
    }
}
