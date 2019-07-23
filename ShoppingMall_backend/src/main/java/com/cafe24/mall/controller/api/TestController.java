package com.cafe24.mall.controller.api;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller("testController")
@RequestMapping("/api/test")
public class TestController {

	@ResponseBody
	@GetMapping("/sample")
	public String sample() {
		return "서비스 준비중 입니다.";
	}
	
}
