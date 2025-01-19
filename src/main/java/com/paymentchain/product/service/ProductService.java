package com.paymentchain.product.service;

import com.paymentchain.product.entities.Product;
import com.paymentchain.product.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductService {
    @Autowired
    ProductRepository productRepository;

    public List<Product> findAll(){
        return productRepository.findAll();
    }

    public ResponseEntity<Product> findById(Long id) {
        return productRepository.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    public ResponseEntity<Product> update(Long id, Product dto) {
        Optional<Product> existingProduct = productRepository.findById(id);

        if (existingProduct.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        Product savedProduct = existingProduct.get();
        savedProduct.setName(dto.getName());
        savedProduct.setCode(dto.getCode());

        productRepository.save(savedProduct);

        return ResponseEntity.ok(savedProduct);
    }

    public ResponseEntity<Product> save(Product dto) {
        Product product = productRepository.save(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(product);
    }

    public ResponseEntity<Void> delete(Long id) {
        if (!productRepository.findById(id).isPresent()) {
            return ResponseEntity.notFound().build();
        }

        productRepository.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
