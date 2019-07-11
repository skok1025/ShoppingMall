package com.cafe24.mall.controller.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cafe24.mall.dto.JSONResult;
import com.cafe24.mall.service.AdminService;

@RestController("AdminAPIController")
@RequestMapping("/api/admin")
public class AdminController {

	@Autowired
	private AdminService adminService;
	
	@GetMapping("/goodslist")
	public JSONResult goodsList() {
		return JSONResult.success("관리자 상품조회 성공", null);
	}
	
	
}
