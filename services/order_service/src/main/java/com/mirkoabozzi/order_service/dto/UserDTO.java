package com.mirkoabozzi.order_service.dto;

import java.util.UUID;

public record UserDTO(
        UUID id,
        String name,
        String surname,
        String email,
        String role
) {
}
