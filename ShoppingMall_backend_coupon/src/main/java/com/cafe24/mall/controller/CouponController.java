package com.cafe24.mall.controller;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cafe24.mall.vo.CouponVo;

@Controller
@RequestMapping("/coupon")
public class CouponController {
	private final RabbitTemplate rabbitTemplate;
	private static final String topicExchange = "coupon-create-exchange";
	
	public CouponController(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }
	
	@ResponseBody
	@GetMapping("/add")
	public String send(CouponVo vo) {
		System.out.println("Sending message...");
		vo.setNo("1");
		vo.setName("coupon name");
		vo.setType("P");
		vo.setSalerate("10");
		String send_message = vo.toString();
        rabbitTemplate.convertAndSend(topicExchange, "foo.bar.baz", send_message);
		return "send!!";
	}
}
