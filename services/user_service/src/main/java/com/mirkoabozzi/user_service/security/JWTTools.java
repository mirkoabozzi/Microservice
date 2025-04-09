package com.mirkoabozzi.user_service.security;

import com.mirkoabozzi.user_service.entities.User;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class JWTTools {

    @Value("${jwt.secret}")
    private String secret;
    @Value(("${jwt.expiration}"))
    private Long expiration;

    public String generateToken(User user) {
        return Jwts.builder()
                .issuedAt(new Date(System.currentTimeMillis()))
                .expiration(new Date(System.currentTimeMillis() + expiration))
                .subject(String.valueOf(user.getId()))
                .claim("role", user.getRole())
                .signWith(Keys.hmacShaKeyFor(secret.getBytes()))
                .compact();
    }
}
