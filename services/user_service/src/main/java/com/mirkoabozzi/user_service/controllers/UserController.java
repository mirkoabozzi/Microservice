package com.mirkoabozzi.user_service.controllers;

import com.mirkoabozzi.user_service.dto.UserRespDTO;
import com.mirkoabozzi.user_service.entities.User;
import com.mirkoabozzi.user_service.mapper.UserMapper;
import com.mirkoabozzi.user_service.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;
    private final UserMapper userMapper;

    @GetMapping("/{id}")
    public UserRespDTO getUser(@PathVariable UUID id) {
        User useFound = this.userService.findById(id);
        return userMapper.userToDTO(useFound);
    }

    @GetMapping
    public List<UserRespDTO> getAllUsers() {
        List<User> userList = this.userService.getAllUsers();
        return userList.stream().map(userMapper::userToDTO).toList();
    }
}
