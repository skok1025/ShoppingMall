package com.cafe24.mall.controller.api;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.AMQP.BasicProperties;

@Controller
@RequestMapping("/api/rabbitmq-test")
public class RabbitMqTestController {

	@Value("${spring.rabbitmq.host}")
	private String rabbitMqHost;
	
	@Value("${spring.rabbitmq.port}")
	private Integer rabbitMqPort;
	
	@Value("${spring.rabbitmq.username}")
	private String rabbitMqUserName;
	
	@Value("${spring.rabbitmq.password}")
	private String rabbitMqPassword;
	
	@Value("${spring.rabbitmq.test-queue-name}")
	private String QUEUE_NAME;
	
	private static String MESSAGE_CONTENT_TYPE = "application/json";
	private static String MESSAGE_ENCODING = "UTF-8";
	
	@ResponseBody
	@GetMapping("/send")
	public String channelSend() {
		ConnectionFactory factory = new ConnectionFactory();
		
		/*rabbitMq 설정값 세팅*/
		factory.setHost(rabbitMqHost);
		factory.setPort(rabbitMqPort);
		factory.setUsername(rabbitMqUserName);
		factory.setPassword(rabbitMqPassword);
		
		try (Connection conn = factory.newConnection(); Channel channel = conn.createChannel()) {
			
			// 큐를 생성 (declare)
			channel.queueDeclare(QUEUE_NAME, true, false, false, null);
			String message = "Hello World";
			
			BasicProperties props = new BasicProperties.Builder()
					.contentType(MESSAGE_CONTENT_TYPE)
					.contentEncoding(MESSAGE_ENCODING)
					.messageId(UUID.randomUUID().toString())
					.deliveryMode(2) // 1- 비영속성, 2- 영속성
					.build();
			
			/* routing key 에 큐 이름을 사용*/
			channel.basicPublish("", QUEUE_NAME, props, message.getBytes());
		} catch (Exception e) {
			// TODO: handle exception
		}
		return null;
	}

}
