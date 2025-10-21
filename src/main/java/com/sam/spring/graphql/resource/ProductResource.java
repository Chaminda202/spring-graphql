package com.sam.spring.graphql.resource;

import com.sam.spring.graphql.entity.Product;
import com.sam.spring.graphql.model.ProductInput;
import com.sam.spring.graphql.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class ProductResource {
    private final ProductService productService;

    @QueryMapping
    public List<Product> getProducts() {
        return productService.getProducts();
    }

    @QueryMapping
    public List<Product> findProductByCategory(@Argument String category) {
        return productService.findByCategory(category);
    }

    @MutationMapping
    public Product updateStock(@Argument Integer id, @Argument Integer stock) {
        return productService.updateStock(id, stock);
    }

    @MutationMapping
    public Product createProduct(@Argument String name, @Argument String category, @Argument Float price, @Argument Integer stock) {
        return productService.createProduct(name, category, price, stock);
    }

    @MutationMapping
    public Product createPrdct(@Argument ProductInput productInput) {
        return productService.createPrdct(productInput);
    }

    @QueryMapping
    public Page<Product> findProduct(@Argument Integer page, @Argument Integer size) {
        return productService.findProduct(page, size);
    }
}
