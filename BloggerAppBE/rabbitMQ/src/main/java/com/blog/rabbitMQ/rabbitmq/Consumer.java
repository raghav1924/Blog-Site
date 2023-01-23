package com.blog.rabbitMQ.rabbitmq;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
public class Consumer {

    @RabbitListener(queues = "blog_queue")
    public void getTheDto(EmailDTO emailDTO){

        System.out.println(emailDTO.getReceiver());
        System.out.println(emailDTO.getMessageBody());
        System.out.println(emailDTO.getSubject());
    }
}
