package com.cafe24.mall.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MainController {


	@GetMapping("/")
	public String mainPage(Model model) {
		return "index/index";
	}
	
}
