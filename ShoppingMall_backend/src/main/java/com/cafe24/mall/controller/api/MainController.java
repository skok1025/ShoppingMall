package com.cafe24.mall.controller.api;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController("MainAPIController")

public class MainController {

	@ResponseBody
	@GetMapping("/index")
	public String index() {
		return "시작페이지";
	}
}
