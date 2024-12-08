package com.vcart.ecommerce.entity;

import jakarta.validation.constraints.NotNull;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.math.BigDecimal;

@Data
@Document(collection = "cartItems")
public class CartItem {
    @Id
    private String id;

    @NotNull
    private String userId;

    @NotNull
    private String productId;

    private String productName;
    private BigDecimal price;
    private int quantity;

}
