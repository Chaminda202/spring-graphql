package com.sam.spring.graphql.service;

import com.sam.spring.graphql.entity.Product;
import com.sam.spring.graphql.model.ProductInput;
import org.springframework.data.domain.Page;

import java.util.List;

public interface ProductService {
    List<Product> getProducts();

    List<Product> findByCategory(String category);

    Product updateStock(Integer productId, Integer stock);

    Product createProduct(String name, String category, Float price, Integer stock);

    Product createPrdct(ProductInput productInput);

    Page<Product> findProduct(Integer page, Integer size);
}