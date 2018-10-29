package com.stxy.smsadmin.rabbitmq.topicRabbit;

import com.rabbitmq.client.Channel;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
@RabbitListener(queues = "topic.messages")
public class TopicReceiver2 {

    @RabbitHandler
    public void process(String message, Channel channel, Message messa) throws  RuntimeException {
        if (message != null ){
            throw  new RuntimeException();
        }

        try {
            channel.basicAck(messa.getMessageProperties().getDeliveryTag(),false);
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("Topic Receiver2  : " + message);
    }

}