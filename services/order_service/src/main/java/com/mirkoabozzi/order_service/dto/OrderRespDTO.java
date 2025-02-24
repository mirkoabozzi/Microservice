package com.mirkoabozzi.order_service.dto;

import java.util.UUID;

public record OrderRespDTO(
        UUID id,
        UUID productId,
        int quantity
) {
}