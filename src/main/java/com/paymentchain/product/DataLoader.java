package com.paymentchain.product;

import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import com.paymentchain.product.service.ProductService;
import com.paymentchain.product.entities.Product;

import java.util.Arrays;
import java.util.List;

@Component
public class DataLoader {
    @Autowired
    private ProductService productService;

    @PostConstruct
    public void loadData() {
        Product product1 = Product.builder()
            .id(1L)
            .code("01")
            .name("Tarjeta de credito")
            .build();
        Product product2 = Product.builder()
            .id(2L)
            .code("02")
            .name("Cuenta de ahorro")
            .build();

        Product product3 = Product.builder()
            .id(3L)
            .code("03")
            .name("Cuenta corriente")
            .build();

        Product product4 = Product.builder()
            .id(4L)
            .code("04")
            .name("Leasing inmobiliario")
            .build();

        List<Product> products = Arrays.asList(product1, product2, product3, product4);

        products.forEach(product -> {
            productService.save(product);
        });
    }
}
