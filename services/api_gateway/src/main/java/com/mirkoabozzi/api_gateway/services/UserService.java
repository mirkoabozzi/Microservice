package com.mirkoabozzi.api_gateway.services;

import com.mirkoabozzi.api_gateway.dto.UserRegisterDTO;
import com.mirkoabozzi.api_gateway.entities.User;
import com.mirkoabozzi.api_gateway.exceptions.BadRequestException;
import com.mirkoabozzi.api_gateway.exceptions.NotFoundException;
import com.mirkoabozzi.api_gateway.mapper.UserMapper;
import com.mirkoabozzi.api_gateway.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;

    public User findById(UUID id) {
        return this.userRepository.findById(id).orElseThrow(() -> new NotFoundException("User with id " + id + " not found on DB"));
    }

    public User findByEmail(String email) {
        return this.userRepository.findByEmail(email).orElseThrow(() -> new NotFoundException("Email " + email + " not found on DB"));
    }

    public User saveUser(UserRegisterDTO body) {
        if (this.userRepository.existsByEmail(body.email()))
            throw new BadRequestException("Email " + body.email() + " already on DB");
        
        User newUser = userMapper.createUser(body);

        return this.userRepository.save(newUser);
    }
}
