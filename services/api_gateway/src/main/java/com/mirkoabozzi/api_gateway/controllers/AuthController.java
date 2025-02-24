package com.mirkoabozzi.api_gateway.controllers;

import com.mirkoabozzi.api_gateway.dto.UserLoginDTO;
import com.mirkoabozzi.api_gateway.dto.UserLoginRespDTO;
import com.mirkoabozzi.api_gateway.dto.UserRegisterDTO;
import com.mirkoabozzi.api_gateway.dto.UserRespDTO;
import com.mirkoabozzi.api_gateway.entities.User;
import com.mirkoabozzi.api_gateway.services.AuthService;
import com.mirkoabozzi.api_gateway.services.UserService;
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
