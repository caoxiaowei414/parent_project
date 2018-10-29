package com.stxy.smsadmin.rabbitmq.topicRabbit;

import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class TopicSender implements RabbitTemplate.ReturnCallback {

	@Autowired
	private RabbitTemplate rabbitTemplate;

	public void send() {
		String context = "hi, i am message all";
		System.out.println("Sender : " + context);
		this.rabbitTemplate.convertAndSend("topicExchange", "topic.ssk", context);
	}

	public void send1() {
		String context = "hi, i am message 1";
		System.out.println("Sender : " + context);
		this.rabbitTemplate.setReturnCallback(this);
		this.rabbitTemplate.setConfirmCallback((correlationData, ack, cause) -> {
			if (!ack) {
				System.out.println("HelloSender消息发送失败" + cause + correlationData.toString());
			} else {
				System.out.println("HelloSender 消息发送成功 ");
			}
		});


		this.rabbitTemplate.convertAndSend("topicExchange", "topic.message", context);
	}

	public void send2() {
		String context = "hi, i am messages 2";
		System.out.println("Sender : " + context);
		this.rabbitTemplate.convertAndSend("topicExchange", "topic.messages", context);
	}

	@Override
	public void returnedMessage(Message message, int replyCode, String replyText, String exchange, String routingKey) {
		System.out.println("sender return success" + message.toString()+"==="+replyCode+"==="+replyText+"==="+exchange+"==="+routingKey);
	}
}