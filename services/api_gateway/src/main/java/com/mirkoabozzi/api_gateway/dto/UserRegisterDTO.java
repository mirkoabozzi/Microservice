package com.mirkoabozzi.api_gateway.dto;

public record UserRegisterDTO(
        String name,
        String surname,
        String email,
        String password
) {
}
