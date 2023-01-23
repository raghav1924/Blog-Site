package com.blog.authApp.rabbitmq;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.management.Query;

@Configuration
public class MessageConfiguration {

    private String exchageName="blog_exchange";
    private String queueName="blog_queue";

    @Bean
    public DirectExchange getExchange(){return new DirectExchange(exchageName);}
    @Bean
    public Queue getQueue(){return new Queue(queueName);}
    @Bean
    public Jackson2JsonMessageConverter jsonMessageConverter(){return new Jackson2JsonMessageConverter();}
    @Bean
    public RabbitTemplate getTemplate(final ConnectionFactory connectionFactory){
        RabbitTemplate rabbitTemplate=new RabbitTemplate(connectionFactory);
        rabbitTemplate.setMessageConverter(jsonMessageConverter());
        return rabbitTemplate;
    }
    @Bean
    public Binding getBinding(Queue queue,DirectExchange directExchange){
        return BindingBuilder.bind(queue).to(directExchange).with("blog_path");
    }
}
