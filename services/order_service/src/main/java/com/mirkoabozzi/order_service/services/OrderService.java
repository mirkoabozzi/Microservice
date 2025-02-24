package com.mirkoabozzi.order_service.services;

import com.mirkoabozzi.order_service.client.ProductClient;
import com.mirkoabozzi.order_service.dto.OrderDTO;
import com.mirkoabozzi.order_service.entities.Order;
import com.mirkoabozzi.order_service.exceptions.NotFoundExceptions;
import com.mirkoabozzi.order_service.repositories.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class OrderService {

    private final OrderRepository orderRepository;
    private final ProductClient productClient;

    public Order saveOrder(OrderDTO body) {

        Order newOrder = new Order(body.productId(), body.quantity());
        
        if (this.productClient.checkAvailability(body.productId(), body.quantity())) {
            this.orderRepository.save(newOrder);
        } else {
            throw new NotFoundExceptions("Product unavailable");
        }
        return newOrder;
    }
}
