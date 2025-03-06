package com.mirkoabozzi.order_service.dto;

import java.util.UUID;

public record OrderConfirmation(
        UUID orderId
) {
}
