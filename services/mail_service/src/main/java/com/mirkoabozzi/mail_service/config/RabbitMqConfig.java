package com.mirkoabozzi.mail_service.config;

import lombok.RequiredArgsConstructor;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@Configuration
@RequiredArgsConstructor
public class RabbitMqConfig {

    private final RabbitMqProperties rabbitMqProperties;

    @Bean
    public DirectExchange mailExchange() {
        return new DirectExchange(rabbitMqProperties.getMailExchange());
    }

    @Bean
    public Queue newUserQueue() {
        return new Queue(rabbitMqProperties.getNewUserQueue(), true);
    }


    @Bean
    public Binding binding(Queue newUserQueue, DirectExchange mailExchange) {
        return BindingBuilder.bind(newUserQueue).to(mailExchange).with(rabbitMqProperties.getNewUserRoutingKey());
    }

    @Bean
    public MessageConverter jsonMessageConverter() {
        return new Jackson2JsonMessageConverter();
    }
}