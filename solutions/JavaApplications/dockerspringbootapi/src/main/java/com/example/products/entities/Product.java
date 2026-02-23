package com.example.products.entities;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.*;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Product {

    @JsonProperty("id")
    private String id;

    @NotBlank(message = "Name is required")
    @Size(min = 2, max = 100, message = "Name must be between 2 and 100 characters")
    @JsonProperty("name")
    private String name;

    @JsonProperty("description")
    private String description;

    @NotNull(message = "Price is required")
    @DecimalMin(value = "0.0", inclusive = false, message = "Price must be greater than 0")
    @JsonProperty("price")
    private BigDecimal price;

    @NotBlank(message = "Category is required")
    @JsonProperty("category")
    private String category;

    @Min(value = 0, message = "Stock quantity cannot be negative")
    @JsonProperty("stockQuantity")
    private int stockQuantity;

    @JsonProperty("active")
    private boolean active;

    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss")
    @JsonProperty("createdAt")
    private LocalDateTime createdAt;

    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss")
    @JsonProperty("updatedAt")
    private LocalDateTime updatedAt;

    // Factory method for new products
    public static Product create(String name, String description, BigDecimal price,
            String category, int stockQuantity) {
        LocalDateTime now = LocalDateTime.now();
        return Product.builder()
                .id(UUID.randomUUID().toString())
                .name(name)
                .description(description)
                .price(price)
                .category(category)
                .stockQuantity(stockQuantity)
                .active(true)
                .createdAt(now)
                .updatedAt(now)
                .build();
    }
}
