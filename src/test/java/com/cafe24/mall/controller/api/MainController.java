package com.cafe24.mall.controller.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cafe24.mall.service.MainService;
import com.cafe24.mysite.dto.JSONResult;

import io.swagger.annotations.ApiOperation;

@RestController("mainApiController")
@RequestMapping("/api/main")
public class MainController {
	@Autowired
	private MainService mainService;
	
	@ApiOperation(value = "회원가입 페이지로 이동")
	@GetMapping("/join")
	public JSONResult join() {
		return JSONResult.success(null);
	}
	

}
