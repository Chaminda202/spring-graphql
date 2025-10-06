package com.sam.spring.graphql.resource;

import com.sam.spring.graphql.entity.Product;
import com.sam.spring.graphql.service.ProductService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.graphql.GraphQlTest;
import org.springframework.graphql.test.tester.GraphQlTester;
import org.springframework.test.context.bean.override.mockito.MockitoBean;

import java.util.List;

import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

@GraphQlTest(ProductResource.class)
public class ProductResourceTest {
    @Autowired
    private GraphQlTester graphQlTester;

    @MockitoBean
    private ProductService productService;

    @Test
    void getProductsTest() {
        List<Product> mockProducts = List.of(new Product(1, "Laptop", "Electronic", 2.45f, 10));
        when(productService.getProducts()).thenReturn(mockProducts);
        graphQlTester.documentName("product-query")
                .execute()
                .path("getProducts")
                .entityList(Product.class)
                .hasSize(1);
    }

    @Test
    void findProductByCategory_shouldReturnProducts_whenMatch() {
        List<Product> mockProducts = List.of(
                new Product(1, "Laptop", "Electronic", 2.45f, 10),
                new Product(1, "Phone", "Electronic", 2.45f, 10));

        when(productService.findByCategory(anyString())).thenReturn(mockProducts);
        graphQlTester.documentName("product-query")
                .execute()
                .path("findProductByCategory")
                .entityList(Product.class)
                .hasSize(2);
    }

    @Test
    void findProductByCategory_shouldReturnEmptyList_whenNoMatch() {
        when(productService.findByCategory(anyString())).thenReturn(List.of());

        graphQlTester.documentName("product-query")
                .execute()
                .path("findProductByCategory")
                .entityList(Product.class)
                .hasSize(0);
    }
}
