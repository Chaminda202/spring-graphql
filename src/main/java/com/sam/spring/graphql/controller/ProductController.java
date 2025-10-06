package com.sam.spring.graphql.controller;

import com.sam.spring.graphql.entity.Product;
import com.sam.spring.graphql.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = "/products")
@RequiredArgsConstructor
public class ProductController {
    private final ProductService productService;

    @GetMapping
    public List<Product> getProducts() {
        return productService.getProducts();
    }

    @GetMapping("/{category}")
    public List<Product> findProductByCategory(@PathVariable String category) {
        return productService.findByCategory(category);
    }
}
