package com.stxy.smsadmin.rabbitmq.directRabbit;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component(value = "t4Receiver")
public class Receiver {
 
  @RabbitListener(queues = "#{autoDeleteQueue0.name}")
  public void receiver0(String str) {
    System.out.println("receiver0++++++++++:" + str);
  }
 
  @RabbitListener(queues = "#{autoDeleteQueue1.name}")
  public void receiver1(String str) {
    System.out.println("receiver1++++++++++:" + str);
  }
}