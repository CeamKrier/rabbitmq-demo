package com.example.rabbitmq.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * ProviderController
 */
@RestController
public class ProviderController {

  @Autowired
  ProviderMQ provider;

  @RequestMapping("/send")
  public String sendMessage(@RequestParam("msg") String msg) {
    provider.sendMessage(msg);
    return "Sent to the rabbitmq";
  }

}