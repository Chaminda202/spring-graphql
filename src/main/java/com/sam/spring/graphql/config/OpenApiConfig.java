package com.sam.spring.graphql.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenApiConfig {

    @Bean
    public OpenAPI openAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("Spring Boot with Graphql")
                        .version("1.0")
                        .description("Demo API with Spring Boot, Graphql, MariaDB, Flyway"));
    }
}