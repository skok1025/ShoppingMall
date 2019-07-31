package com.cafe24.mall.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class TestController {

	@GetMapping("/template")
	public String template() {
		return "template";
	}
	
}
