package com.mirkoabozzi.user_service.entities;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.mirkoabozzi.user_service.enums.Role;
import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.UUID;

@Document
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@JsonIgnoreProperties({"password"})
public class User {
    @Id
    private UUID id;
    private String name;
    private String surname;
    private String email;
    private String password;
    private Role role;
}
