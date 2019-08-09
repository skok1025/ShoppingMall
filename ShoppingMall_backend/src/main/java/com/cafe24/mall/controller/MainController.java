package com.cafe24.mall.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class MainController {
	
	@ResponseBody
	@GetMapping("/join")
	public String join() {
		return "서비스 준비중...."; 
	}
	
	
	
	
	
	

}
