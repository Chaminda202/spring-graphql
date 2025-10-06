package com.sam.spring.graphql.service.impl;

import com.sam.spring.graphql.entity.Product;
import com.sam.spring.graphql.repository.ProductRepository;
import com.sam.spring.graphql.service.ProductService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class ProductServiceImpl implements ProductService {
    private ProductRepository productRepository;

    @Override
    public List<Product> getProducts() {
        return productRepository.findAll();
    }

    @Override
    public List<Product> findByCategory(String category) {
        return productRepository.findByCategory(category);
    }

    @Override
    public Product updateStock(Integer productId, Integer stock) {
        Product existingPrd = productRepository.findById(productId)
                .orElseThrow(() -> new RuntimeException("Product not found with id " + productId));
        existingPrd.setStock(stock);
        return existingPrd;
    }

    @Override
    public Product createProduct(String name, String category, Float price, Integer stock) {
        Product product = Product.builder()
                .name(name)
                .category(category)
                .price(price)
                .stock(stock)
                .build();
        return productRepository.save(product);
    }
}
