package com.mirkoabozzi.api_gateway.controllers;

import com.mirkoabozzi.api_gateway.entities.User;
import com.mirkoabozzi.api_gateway.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @GetMapping("/{id}")
    public User getUser(@PathVariable UUID id) {
        return this.userService.findById(id);
    }
}
