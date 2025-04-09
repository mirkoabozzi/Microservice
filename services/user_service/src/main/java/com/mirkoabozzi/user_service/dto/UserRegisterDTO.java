package com.mirkoabozzi.user_service.dto;

public record UserRegisterDTO(
        String name,
        String surname,
        String email,
        String password
) {
}
