package com.sam.spring.graphql.resource;

import com.sam.spring.graphql.entity.Product;
import com.sam.spring.graphql.service.ProductService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.graphql.GraphQlTest;
import org.springframework.graphql.test.tester.GraphQlTester;
import org.springframework.test.context.bean.override.mockito.MockitoBean;

import java.util.List;

import static org.mockito.ArgumentMatchers.*;
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
                .operationName("getProducts")
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
                .operationName("findProductByCategory")
                .execute()
                .path("findProductByCategory")
                .entityList(Product.class)
                .hasSize(2);
    }

    @Test
    void findProductByCategory_shouldReturnEmptyList_whenNoMatch() {
        when(productService.findByCategory(anyString())).thenReturn(List.of());

        graphQlTester.documentName("product-query")
                .operationName("findProductByCategory")
                .execute()
                .path("findProductByCategory")
                .entityList(Product.class)
                .hasSize(0);
    }

    @Test
    void createProduct_shouldReturnNewProduct() {
        Product product = new Product(1, "Laptop", "Electronic", 2.45f, 10);

        when(productService.createProduct(anyString(), anyString(), anyFloat(), anyInt()))
                .thenReturn(product);

        graphQlTester.documentName("product-query")
                .operationName("createProduct")
                .execute()
                .path("createProduct")
                .entity(Product.class)
                .path("createProduct.id").entity(Integer.class).isEqualTo(1)
                .path("createProduct.name").entity(String.class).isEqualTo("Laptop")
                .path("createProduct.category").entity(String.class).isEqualTo("Electronic")
                .path("createProduct.price").entity(Float.class).isEqualTo(2.45f)
                .path("createProduct.stock").entity(Integer.class).isEqualTo(10);
    }

    @Test
    void updateStock_shouldReturnUpdateProduct() {
        Product product = new Product(1, "Laptop", "Electronic", 2.45f, 25);

        when(productService.updateStock(anyInt(), anyInt()))
                .thenReturn(product);

        graphQlTester.documentName("product-query")
                .operationName("updateStock")
                .execute()
                .path("updateStock")
                .entity(Product.class)
                .path("updateStock.id").entity(Integer.class).isEqualTo(1)
                .path("updateStock.stock").entity(Integer.class).isEqualTo(25);
    }
}
