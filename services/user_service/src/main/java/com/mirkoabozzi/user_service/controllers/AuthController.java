package com.mirkoabozzi.user_service.controllers;

import com.mirkoabozzi.user_service.dto.UserLoginDTO;
import com.mirkoabozzi.user_service.dto.UserLoginRespDTO;
import com.mirkoabozzi.user_service.dto.UserRegisterDTO;
import com.mirkoabozzi.user_service.dto.UserRespDTO;
import com.mirkoabozzi.user_service.entities.User;
import com.mirkoabozzi.user_service.services.AuthService;
import com.mirkoabozzi.user_service.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;
    private final UserService userService;

    @PostMapping("/register")
    @ResponseStatus(HttpStatus.CREATED)
    public UserRespDTO saveUser(@RequestBody UserRegisterDTO body) {
        User user = this.userService.saveUser(body);
        return new UserRespDTO(user.getId(), user.getName(), user.getSurname(), user.getEmail(), user.getRole());
    }

    @PostMapping("/login")
    public UserLoginRespDTO login(@RequestBody UserLoginDTO body) {
        return new UserLoginRespDTO(this.authService.checkCredentialsAndGenerateToken(body));
    }
}
