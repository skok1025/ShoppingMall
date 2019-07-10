package com.cafe24.mall.controller.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import com.cafe24.mall.service.OrderService;

@RestController("OrderAPIController")
public class OrderController {

	@Autowired
	private OrderService orderSevice;
	
}
