package com.stxy.smsadmin.rabbitmq.directRabbit;

import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class DirectSender {
  @Autowired
  private DirectExchange directExchange;
  @Autowired
  private RabbitTemplate rabbitTemplate;
  private String[] keys = {"orange", "black", "green"};
 
  public void send() {
    String message = "哈哈哈";
    for (int i = 0; i < 5; i++) {
      System.out.println("send++++++++++:".concat(message));
      rabbitTemplate.convertAndSend(directExchange.getName(), keys[2], message);
    }
  }
}