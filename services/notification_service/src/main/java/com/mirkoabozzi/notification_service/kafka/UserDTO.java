package com.mirkoabozzi.notification_service.kafka;

import java.util.UUID;

public record UserDTO(
        UUID id,
        String name,
        String surname,
        String email,
        String role
) {
}
