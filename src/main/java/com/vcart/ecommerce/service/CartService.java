package com.vcart.ecommerce.service;

import com.vcart.ecommerce.entity.CartItem;
import com.vcart.ecommerce.entity.Product;
import com.vcart.ecommerce.repository.CartItemRepository;
import com.vcart.ecommerce.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
public class CartService {

    @Autowired
    private CartItemRepository cartItemRepository;

    @Autowired
    private ProductService productService;

    @Autowired
    private UserRepository userRepository;

    // Get cart by user ID
    public List<CartItem> getCartByUserId(String userId) {
        // Validate User ID
        boolean userExists = userRepository.findById(userId).isPresent();
        if (!userExists) {
            throw new IllegalArgumentException("User with ID " + userId + " does not exist.");
        }

        List<CartItem> cartItems = cartItemRepository.findByUserId(userId);

        if (cartItems.isEmpty()) {
            throw new IllegalArgumentException("Cart is empty for user with ID " + userId);
        }

        return cartItems;
    }

    // Add to cart
    public CartItem addToCart(String userId, String productId, int quantity) {
        // Validate Product ID
        Product product = productService.getProductById(productId);
        if (product == null) {
            throw new IllegalArgumentException("Product with ID " + productId + " does not exist.");
        }

        // Validate User ID
        boolean userExists = userRepository.findById(userId).isPresent();
        if (!userExists) {
            throw new IllegalArgumentException("User with ID " + userId + " does not exist.");
        }

        // Create Cart Item
        CartItem cartItem = new CartItem();
        cartItem.setUserId(userId);
        cartItem.setProductId(productId);
        cartItem.setProductName(product.getName());
        cartItem.setPrice(product.getPrice());
        cartItem.setQuantity(quantity);

        // Save to repository
        return cartItemRepository.save(cartItem);
    }

    // Remove from cart
    public void removeFromCart(String userId, String productId) {
        // Validate User ID
        boolean userExists = userRepository.findById(userId).isPresent();
        if (!userExists) {
            throw new IllegalArgumentException("User with ID " + userId + " does not exist.");
        }

        // Validate Product ID exists in user's cart
        boolean productInCart = cartItemRepository.findByUserId(userId).stream()
                .anyMatch(item -> item.getProductId().equals(productId));
        if (!productInCart) {
            throw new IllegalArgumentException("Product with ID " + productId + " is not in the cart of user " + userId);
        }

        // Remove the product from the cart
        cartItemRepository.deleteByUserIdAndProductId(userId, productId);
    }

    // Calculate total cart value
    public BigDecimal calculateTotal(String userId) {
        // Validate User ID
        boolean userExists = userRepository.findById(userId).isPresent();
        if (!userExists) {
            throw new IllegalArgumentException("User with ID " + userId + " does not exist.");
        }

        // Get cart items
        List<CartItem> cartItems = cartItemRepository.findByUserId(userId);
        if (cartItems.isEmpty()) {
            throw new IllegalArgumentException("Cart is empty for user with ID " + userId);
        }

        // Calculate total
        return cartItems.stream()
                .map(item -> item.getPrice().multiply(BigDecimal.valueOf(item.getQuantity())))
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }
}
