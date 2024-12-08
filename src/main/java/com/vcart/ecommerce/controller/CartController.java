package com.vcart.ecommerce.controller;

import com.vcart.ecommerce.entity.CartItem;
import com.vcart.ecommerce.service.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;

@RestController
@RequestMapping("/cart")
public class CartController {
    @Autowired
    private CartService cartService;

    @GetMapping("/{userId}")
    public List<CartItem> getCart(@PathVariable String userId) {
        return cartService.getCartByUserId(userId);
    }

    @PostMapping("/add")
    public CartItem addToCart(@RequestBody CartItem cartItem) {
        return cartService.addToCart(cartItem.getUserId(), cartItem.getProductId(), cartItem.getQuantity());
    }

    @DeleteMapping("/remove")
    public void removeFromCart(@RequestParam String userId, @RequestParam String productId) {
        cartService.removeFromCart(userId, productId);
    }

    @GetMapping("/total/{userId}")
    public BigDecimal getTotal(@PathVariable String userId) {
        return cartService.calculateTotal(userId);
    }
}
