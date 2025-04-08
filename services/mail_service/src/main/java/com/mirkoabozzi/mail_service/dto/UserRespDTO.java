package com.mirkoabozzi.mail_service.dto;

import com.mirkoabozzi.mail_service.enums.Role;
import lombok.Getter;

@Getter
public class UserRespDTO {
    private java.util.UUID id;
    private String name;
    private String surname;
    private String email;
    private Role userRole;
}
