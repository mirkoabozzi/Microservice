package com.mirkoabozzi.order_service.client;

import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.service.annotation.GetExchange;

import java.util.UUID;

public interface ProductClient {

    @GetExchange("/api/products/check")
    boolean checkAvailability(@RequestParam UUID productId, @RequestParam int quantity);
}
