package com.sam.spring.graphql.service;

import com.sam.spring.graphql.entity.Product;
import org.springframework.graphql.data.method.annotation.Argument;

import java.util.List;

public interface ProductService {
    List<Product> getProducts();

    List<Product> findByCategory(String category);

    Product updateStock(Integer productId, Integer stock);

    Product createProduct(String name, String category, Float price, Integer stock);
}