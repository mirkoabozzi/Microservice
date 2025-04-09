package com.mirkoabozzi.notification_service.kafka;

import com.mirkoabozzi.notification_service.entities.Notification;
import com.mirkoabozzi.notification_service.enums.NotificationType;
import com.mirkoabozzi.notification_service.repositories.NotificationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class NotificationConsumer {

    private final NotificationRepository notificationRepository;

    @KafkaListener(topics = "order-topic")
    public void orderNotification(OrderConfirmation orderConfirmation) {
        System.out.println("Consumer -> Message received from order service");
        System.out.println("Order number " + orderConfirmation.orderId());
        System.out.println("Client name " + orderConfirmation.user().name());
        System.out.println("Client surname " + orderConfirmation.user().surname());
        System.out.println("Client email " + orderConfirmation.user().email());

        Notification notification = Notification.builder()
                .notificationType(NotificationType.ORDER_CONFIRMATION)
                .notificationDate(LocalDateTime.now())
                .orderConfirmation(orderConfirmation)
                .build();

        // todo java mail sender

        this.notificationRepository.save(notification);
    }
}
