package com.mirkoabozzi.mail_service.services;

import com.mirkoabozzi.mail_service.dto.UserRespDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class NewUserMessageConsumer {
    @RabbitListener(queues = "new_user_queue")
    public void getNewUserMessage(UserRespDTO userRespDTO) {
        log.info("Message from user queue");
        log.info("Id [{}]", String.valueOf(userRespDTO.getId()));
        log.info("Name [{}]", userRespDTO.getName());
        log.info("Surname [{}]", userRespDTO.getSurname());
        log.info("Email [{}]", userRespDTO.getEmail());
        log.info("Role [{}]", String.valueOf(userRespDTO.getUserRole()));

        //TODO send welcome email

    }
}
