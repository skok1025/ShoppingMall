package com.cafe24.mall.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cafe24.mall.service.MainService;

import io.swagger.annotations.ApiOperation;

@Controller
public class MainController {
	
	@Autowired
	private MainService mainService;
	
	@ResponseBody
	@ApiOperation(value = "회원가입 페이지로 이동")
	@GetMapping("/join")
	public String join() {
		return "회원가입- 약관동의 페이지";
	}
	
	
	
	
	
	

}
