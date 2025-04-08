package com.mirkoabozzi.mail_service.services;

import com.mirkoabozzi.mail_service.dto.UserRespDTO;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

@Service
public class NewUserMessageConsumer {
    @RabbitListener(queues = "new_user_queue")
    public void getNewUserMessage(UserRespDTO userRespDTO) {
        System.out.println("Message from user queue");
        System.out.println(userRespDTO.getId());
        System.out.println(userRespDTO.getName());
        System.out.println(userRespDTO.getSurname());
        System.out.println(userRespDTO.getEmail());
        System.out.println(userRespDTO.getUserRole());

        //TODO send welcome email
        
    }
}
