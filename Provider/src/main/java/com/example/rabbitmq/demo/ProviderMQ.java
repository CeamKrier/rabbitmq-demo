package com.example.rabbitmq.demo;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

/**
 * Provider
 */

@Component
public class ProviderMQ {

  @Autowired
  private RabbitTemplate rTemplate;

  @Value("${rabbitmq.exchange}")
  private String exchange;
  
  @Value("${rabbitmq.queue}")
  private String queue;

  @Value("${rabbitmq.routingkey}}")
  private String routingKey;

  public void sendMessage(String message) {
    rTemplate.convertAndSend(exchange, routingKey, message);
    System.out.println("Provider sent this message: " + message);
  }

  @Bean
  Queue queue() {
    return new Queue(queue, false);
  }

  @Bean
  TopicExchange exchange() {
    return new TopicExchange(exchange);
  }

  @Bean
  Binding queueExchangeBinding(Queue queue, TopicExchange exchange) {
    return BindingBuilder.bind(queue).to(exchange).with(routingKey);
  }
  
}