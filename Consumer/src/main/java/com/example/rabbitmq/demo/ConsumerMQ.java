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
 * 
 * Containes the frame of the messaging queue implementation. This file holds initialization
 *  of the exchange, queue and corresponding routing key/s.
 */

@Component
public class ConsumerMQ {

  @Autowired
  private RabbitTemplate rTemplate;

  // conventionally takes the variables from the 'application.properties' file
  // just the basic name definitions 
  @Value("${rabbitmq.exchange}")
  private String exchange;
  
  @Value("${rabbitmq.queue}")
  private String queue;

  @Value("${rabbitmq.routingkey}}")
  private String routingKey;

  // method below would take the message from the controller and convert the java object into the rabbitMQ message with the function 'convertAndSend'
  public void sendMessage(String message) {
    rTemplate.convertAndSend(exchange, routingKey, message);
    System.out.println("Provider sent this message: " + message);
  }

  /**
   * Necessary skeleton definitions of the rabbitMQ system: exchange, queue and their correlation(binding)
   */

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