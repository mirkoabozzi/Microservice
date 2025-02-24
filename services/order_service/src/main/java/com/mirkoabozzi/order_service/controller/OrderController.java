package com.mirkoabozzi.order_service.controller;

import com.mirkoabozzi.order_service.dto.OrderDTO;
import com.mirkoabozzi.order_service.dto.OrderRespDTO;
import com.mirkoabozzi.order_service.entities.Order;
import com.mirkoabozzi.order_service.services.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/orders")
@RequiredArgsConstructor
public class OrderController {

    private final OrderService orderService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public OrderRespDTO saveOrder(@RequestBody OrderDTO body) {
        Order order = this.orderService.saveOrder(body);
        return new OrderRespDTO(order.getId(), order.getProductId(), order.getQuantity());
    }
}
