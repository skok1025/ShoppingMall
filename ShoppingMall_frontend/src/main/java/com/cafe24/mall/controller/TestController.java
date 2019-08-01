package com.cafe24.mall.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/template")
public class TestController {

	@GetMapping("/basic")
	public String basic() {
		return "basic";
	}
	
	@GetMapping("/login")
	public String login() {
		return "customer/login";
	}
	
}
