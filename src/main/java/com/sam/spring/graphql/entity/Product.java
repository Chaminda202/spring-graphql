package com.sam.spring.graphql.entity;

import com.sam.spring.graphql.enums.Rating;
import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String name;
    private String category;
    private Float price;
    private Integer stock;
    @Enumerated(EnumType.STRING)
    private Rating rating;

    private BigDecimal discount;

    private LocalDateTime listedDate;
}
