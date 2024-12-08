package com.vcart.ecommerce.utils;

import com.vcart.ecommerce.entity.Product;
import com.vcart.ecommerce.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.Arrays;

@Component
public class ProductSeeder implements CommandLineRunner {

    @Autowired
    private ProductRepository productRepository;

    @Override
    public void run(String... args) throws Exception {
        if (productRepository.count() == 0) {
            Product product1 = new Product();
            product1.setName("Classic White T-Shirt");
            product1.setDescription("Premium cotton t-shirt perfect for customization");
            product1.setPrice(BigDecimal.valueOf(29.99));
            product1.setCategory("T-shirts");
            product1.setCustomizable(true);
            product1.setImageUrl("https://png.pngtree.com/png-vector/20230902/ourmid/pngtree-white-t-shirt-mockup-realistic-t-shirt-png-image_9906363.png");

            Product product2 = new Product();
            product2.setName("Cozy Gray Sweatshirt");
            product2.setDescription("Warm and comfortable sweatshirt for cold days");
            product2.setPrice(BigDecimal.valueOf(49.99));
            product2.setCategory("Sweatshirts");
            product2.setCustomizable(false);
            product2.setImageUrl("https://media.cisco.dowlis.com/catalog/product/cache/206de7029d60b0f7443d1e0c72907454/s/l/slide_18_.jpg");

            Product product3 = new Product();
            product3.setName("Classic Sweatpants");
            product3.setDescription("Comfortable sweatpants for everyday wear");
            product3.setPrice(BigDecimal.valueOf(39.99));
            product3.setCategory("Sweatpants");
            product3.setCustomizable(false);
            product3.setImageUrl("https://eterne.com/cdn/shop/files/29K_SSCREWNECKSHIRTIVY_0036.jpg?v=1732655878&width=3000");

            productRepository.saveAll(Arrays.asList(product1, product2, product3));

            System.out.println("Sample products added to the database!");
        }
    }
}
