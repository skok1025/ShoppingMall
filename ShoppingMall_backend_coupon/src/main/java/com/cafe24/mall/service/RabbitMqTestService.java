package com.cafe24.mall.service;

import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.Queue;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class RabbitMqTestService {

	/*
	 queue 가 없다면 문제가 되기 떄문에 queuesToDeclare 으로 없을 경우 선언해줌. 
	 */
	@RabbitListener(queuesToDeclare = @Queue(name="${spring.rabbitmq.test-queue-name}", durable = "true"))
//	@RabbitListener(queues = "${spring.rabbitmq.test-queue-name}")
    public void receiveMessage(final Message message) {
    	System.out.println(new String(message.getBody()));
    }
}
