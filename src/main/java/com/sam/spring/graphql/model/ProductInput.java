package com.sam.spring.graphql.model;

import com.sam.spring.graphql.enums.Rating;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public record ProductInput(String name, String category, Float price, Integer stock, Rating rating, BigDecimal discount, LocalDateTime listedDate) {
}
