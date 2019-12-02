package com.example.rabbitmq.demo;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Consumer
 * 
 * Contains the logic of how to process the results retrieved from the message queue.
 */

@Component
public class Consumer {

  @Autowired
  ConsumerMQ cmq;

  /**
   * Consumes every message on the 'messageList' whenever this node is available to do it
   * @param msg basic string message
   */
  @RabbitListener(queues = "messageList")
  public void messageListener(String msg) {
    System.out.println("Received message: " + msg);

    setTimeout(() -> cmq.sendMessage("Highly computational intensive task result: " + Math.random() * 100), 3000);
  }

  public static void setTimeout(Runnable runnable, int delay){
    new Thread(() -> {
        try {
            Thread.sleep(delay);
            runnable.run();
        }
        catch (Exception e){
            System.err.println(e);
        }
    }).start();
  }
  
}