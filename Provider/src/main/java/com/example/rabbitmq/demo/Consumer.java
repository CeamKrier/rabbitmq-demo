package com.example.rabbitmq.demo;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Consumer
 */

@Component
public class Consumer {

  @Autowired
  ProviderMQ pmq;

  @RabbitListener(queues = "responseList")
  public void messageListener(String msg) {
    System.out.println("Received message: " + msg);

  }
  
}