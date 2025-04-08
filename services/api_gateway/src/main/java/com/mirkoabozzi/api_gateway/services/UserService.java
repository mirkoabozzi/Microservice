package com.mirkoabozzi.api_gateway.services;

import com.mirkoabozzi.api_gateway.dto.UserRegisterDTO;
import com.mirkoabozzi.api_gateway.dto.UserRespDTO;
import com.mirkoabozzi.api_gateway.entities.User;
import com.mirkoabozzi.api_gateway.exceptions.BadRequestException;
import com.mirkoabozzi.api_gateway.exceptions.NotFoundException;
import com.mirkoabozzi.api_gateway.mapper.UserMapper;
import com.mirkoabozzi.api_gateway.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;
    private final RabbitTemplate rabbitTemplate;

    @Value("${rabbitmq.exchange.name}")
    private String exchange;
    @Value("${rabbitmq.routing.key}")
    private String routingKey;

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

        rabbitTemplate.convertAndSend(exchange, routingKey, userRespDTO);
        
        return savedUser;
    }
}
