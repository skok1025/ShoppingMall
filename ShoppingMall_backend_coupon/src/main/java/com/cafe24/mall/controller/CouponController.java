package com.cafe24.mall.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cafe24.mall.dto.JSONResult;
import com.cafe24.mall.service.CouponService;
import com.cafe24.mall.vo.CouponInfoVo;
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
	public String couponAdd(CouponVo vo) {
		System.out.println("Sending message...");
// test data
		List<String> userIdList = new ArrayList<String>();
		//userIdList.add("skok1025");
		//userIdList.add("skok10");
		for (int i = 0; i < 1000; i++) {
			userIdList.add("test" + i);
		}
		
		vo.setNo("1");
		vo.setName("coupon name");
		vo.setSale_type("P");
		vo.setSale_value("10");
		vo.setIs_used("F");
		vo.setInfo_no("1");
		vo.setUserIdList(userIdList);
// test data
		for (String userId : vo.getUserIdList()) {
			vo.setUser_id(userId);
			String send_message = vo.toString();
			rabbitTemplate.convertAndSend(topicExchange, "foo.bar.baz", send_message);	
		}
		
		return "send!!";
	}
}
