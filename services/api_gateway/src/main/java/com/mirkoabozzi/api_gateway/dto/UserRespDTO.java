package com.mirkoabozzi.api_gateway.dto;

import com.mirkoabozzi.api_gateway.enums.Role;

import java.util.UUID;

public record UserRespDTO(
        UUID id,
        String name,
        String surname,
        String email,
        Role userRole
) {
}
