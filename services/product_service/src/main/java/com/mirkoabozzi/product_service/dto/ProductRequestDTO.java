package com.mirkoabozzi.product_service.dto;

public record ProductRequestDTO(
        String name,
        String description,
        int quantityAvailable
) {
}
