package com.vcart.ecommerce.entity;

import jakarta.validation.constraints.NotNull;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.math.BigDecimal;

@Document(collection = "products")
@Data
public class Product {
    @Id
    private String id;

    @NotNull
    private String name;

    @NotNull
    private String description;

    @NotNull
    private BigDecimal price;

    @NotNull
    private boolean isCustomizable;

    @NotNull
    private String category;

    @NotNull
    private String imageUrl;

}
