package com.mirkoabozzi.product_service.dto;

import java.util.UUID;

public record ProductRespDTO(
        UUID id,
        String name,
        String description,
        int quantityAvailable
) {
}
