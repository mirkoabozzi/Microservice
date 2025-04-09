package com.mirkoabozzi.user_service.services;

import com.mirkoabozzi.user_service.config.RabbitMqProperties;
import com.mirkoabozzi.user_service.dto.UserRegisterDTO;
import com.mirkoabozzi.user_service.dto.UserRespDTO;
import com.mirkoabozzi.user_service.entities.User;
import com.mirkoabozzi.user_service.exceptions.BadRequestException;
import com.mirkoabozzi.user_service.exceptions.NotFoundException;
import com.mirkoabozzi.user_service.mapper.UserMapper;
import com.mirkoabozzi.user_service.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;
    private final RabbitTemplate rabbitTemplate;
    private final RabbitMqProperties rabbitMqProperties;

    public User findById(UUID id) {
        return this.userRepository.findById(id).orElseThrow(() -> new NotFoundException("User with id " + id + " not found on DB"));
    }

    public User findByEmail(String email) {
        return this.userRepository.findByEmail(email).orElseThrow(() -> new NotFoundException("Email " + email + " not found on DB"));
    }

    public User saveUser(UserRegisterDTO body) {
        if (this.userRepository.existsByEmail(body.email()))
            throw new BadRequestException("Email " + body.email() + " already on DB");

        User newUser = this.userMapper.createUser(body);
        User savedUser = this.userRepository.save(newUser);
        UserRespDTO userRespDTO = this.userMapper.userToDTO(savedUser);

        rabbitTemplate.convertAndSend(rabbitMqProperties.getMailExchange(), rabbitMqProperties.getNewUserRoutingKey(), userRespDTO);

        return savedUser;
    }

    public List<User> getAllUsers() {
        return this.userRepository.findAll();
    }
}
