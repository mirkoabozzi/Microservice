package com.mirkoabozzi.user_service.services;

import com.mirkoabozzi.user_service.dto.UserLoginDTO;
import com.mirkoabozzi.user_service.entities.User;
import com.mirkoabozzi.user_service.exceptions.UnauthorizedException;
import com.mirkoabozzi.user_service.security.JWTTools;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final UserService userService;
    private final PasswordEncoder passwordEncoder;
    private final JWTTools jwtTools;

    public String checkCredentialsAndGenerateToken(UserLoginDTO body) {

        User userFound = this.userService.findByEmail(body.email());
        if (!this.passwordEncoder.matches(body.password(), userFound.getPassword()))
            throw new UnauthorizedException("Incorrect password");
        else return this.jwtTools.generateToken(userFound);
    }
}
