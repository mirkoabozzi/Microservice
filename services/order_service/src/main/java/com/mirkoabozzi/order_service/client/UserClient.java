package com.mirkoabozzi.order_service.client;

import com.mirkoabozzi.order_service.dto.UserDTO;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.service.annotation.GetExchange;

import java.util.UUID;

public interface UserClient {

    @GetExchange("/api/users/{id}")
    UserDTO getUser(@PathVariable UUID id);
}
