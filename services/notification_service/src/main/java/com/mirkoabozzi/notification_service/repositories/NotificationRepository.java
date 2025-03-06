package com.mirkoabozzi.notification_service.repositories;

import com.mirkoabozzi.notification_service.entities.Notification;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface NotificationRepository extends MongoRepository<Notification, String> {
}
