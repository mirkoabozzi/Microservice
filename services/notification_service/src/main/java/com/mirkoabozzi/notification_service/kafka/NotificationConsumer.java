package com.mirkoabozzi.notification_service.kafka;

import com.mirkoabozzi.notification_service.entities.Notification;
import com.mirkoabozzi.notification_service.enums.NotificationType;
import com.mirkoabozzi.notification_service.repositories.NotificationRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
@Slf4j
public class NotificationConsumer {

    private final NotificationRepository notificationRepository;

    @KafkaListener(topics = "order-topic")
    public void orderNotification(OrderConfirmation orderConfirmation) {
        log.info("Consumer -> Message received from order service");
        log.info("Order number [{}]", orderConfirmation.orderId());
        log.info("Client name [{}]", orderConfirmation.user().name());
        log.info("Client surname [{}]", orderConfirmation.user().surname());
        log.info("Client email [{}]", orderConfirmation.user().email());

        Notification notification = Notification.builder()
                .notificationType(NotificationType.ORDER_CONFIRMATION)
                .notificationDate(LocalDateTime.now())
                .orderConfirmation(orderConfirmation)
                .build();

        // todo java mail sender

        this.notificationRepository.save(notification);
    }
}
