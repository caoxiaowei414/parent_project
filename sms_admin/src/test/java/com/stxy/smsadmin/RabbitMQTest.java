package com.stxy.smsadmin;

import com.stxy.smsadmin.rabbitmq.directRabbit.DirectSender;
import com.stxy.smsadmin.rabbitmq.fanoutRabbit.FanoutSender;
import com.stxy.smsadmin.rabbitmq.topicRabbit.TopicSender;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class RabbitMQTest {
  @Autowired
  private DirectSender directSender;
  @Autowired
  private FanoutSender fanoutSender ;
  @Autowired
  private TopicSender topicSender ;

  @Test
  public void directSend() throws Exception {
    directSender.send();
  }

  @Test
  public void fanoutSender() throws Exception {
    fanoutSender.send();
  }

  @Test
    public void topicSender() throws Exception {
    topicSender.send1();
  }



}