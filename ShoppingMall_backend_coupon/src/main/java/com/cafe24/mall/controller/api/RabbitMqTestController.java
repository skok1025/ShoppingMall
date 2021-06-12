package com.cafe24.mall.controller.api;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

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
	
	@ResponseBody
	@GetMapping("/send")
	public String channelSend() {
		ConnectionFactory factory = new ConnectionFactory();
		
		/*rabbitMq 설정값 세팅*/
		factory.setHost(rabbitMqHost);
		factory.setPort(rabbitMqPort);
		factory.setUsername(rabbitMqUserName);
		factory.setPassword(rabbitMqPassword);
		
		try (Connection conn = factory.newConnection(); Channel channel = conn.createChannel()){
			channel.queueDeclare(QUEUE_NAME, true, false, false, null);
			String message = "Hello World";
			
			/* routing key 에 큐 이름을 사용*/
			channel.basicPublish("", QUEUE_NAME, null, message.getBytes());
		} catch (Exception e) {
			// TODO: handle exception
		}
		return null;
	}

}
