package com.mirkoabozzi.mail_service.config;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix = "rabbitmq")
@Getter
@Setter
public class RabbitMqProperties {
    private String mailExchange;
    private String newUserQueue;
    private String newUserRoutingKey;
}
