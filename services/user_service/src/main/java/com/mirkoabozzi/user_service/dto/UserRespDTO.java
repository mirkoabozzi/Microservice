package com.mirkoabozzi.user_service.dto;

import com.mirkoabozzi.user_service.enums.Role;

import java.util.UUID;

public record UserRespDTO(
        UUID id,
        String name,
        String surname,
        String email,
        Role userRole
) {
}
