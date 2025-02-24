package com.mirkoabozzi.order_service.dto;

import java.util.UUID;

public record OrderDTO(
        UUID productId,
        int quantity
) {
}
