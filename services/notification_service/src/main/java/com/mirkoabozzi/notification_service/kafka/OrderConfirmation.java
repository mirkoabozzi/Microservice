package com.mirkoabozzi.notification_service.kafka;

import java.util.UUID;

public record OrderConfirmation(
        UUID orderId
) {
}
