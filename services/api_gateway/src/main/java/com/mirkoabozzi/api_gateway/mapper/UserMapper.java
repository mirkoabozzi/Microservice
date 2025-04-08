package com.mirkoabozzi.api_gateway.mapper;

import com.mirkoabozzi.api_gateway.dto.UserRegisterDTO;
import com.mirkoabozzi.api_gateway.dto.UserRespDTO;
import com.mirkoabozzi.api_gateway.entities.User;
import com.mirkoabozzi.api_gateway.enums.Role;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class UserMapper {

    private final PasswordEncoder passwordEncoder;

    public User createUser(UserRegisterDTO body) {
        return User.builder()
                .id(UUID.randomUUID())
                .name(body.name())
                .surname(body.surname())
                .email(body.email())
                .password(passwordEncoder.encode(body.password()))
                .role(Role.USER)
                .build();
    }

    public UserRespDTO userToDTO(User user) {
        return new UserRespDTO(
                user.getId(),
                user.getName(),
                user.getSurname(),
                user.getEmail(),
                user.getRole()
        );
    }
}
