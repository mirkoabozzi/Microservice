package com.mirkoabozzi.order_service.services;

import com.mirkoabozzi.order_service.client.ProductClient;
import com.mirkoabozzi.order_service.client.UserClient;
import com.mirkoabozzi.order_service.dto.OrderConfirmation;
import com.mirkoabozzi.order_service.dto.OrderDTO;
import com.mirkoabozzi.order_service.dto.UserDTO;
import com.mirkoabozzi.order_service.entities.Order;
import com.mirkoabozzi.order_service.exceptions.NotFoundExceptions;
import com.mirkoabozzi.order_service.kafka.OrderProducer;
import com.mirkoabozzi.order_service.repositories.OrderRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class OrderService {

    private final OrderRepository orderRepository;
    private final ProductClient productClient;
    private final UserClient userClient;

    private final OrderProducer orderProducer;

    public Order saveOrder(OrderDTO body) {

        UserDTO userFound = userClient.getUser(body.userId());

        if (userFound == null)
            throw new NotFoundExceptions("User with id " + body.userId() + " not found");

        Order newOrder = new Order(body.productId(), body.quantity());

        if (this.productClient.checkAvailability(body.productId(), body.quantity())) {
            this.productClient.decreaseProductQuantity(body.productId(), body.quantity());
            this.orderRepository.save(newOrder);
        } else {
            throw new NotFoundExceptions("Product unavailable");
        }

        this.orderProducer.sendOrderConfirmation(new OrderConfirmation(newOrder.getId(), userFound));

        return newOrder;
    }

    public List<Order> findAllOrder() {
        return this.orderRepository.findAll();
    }
}
