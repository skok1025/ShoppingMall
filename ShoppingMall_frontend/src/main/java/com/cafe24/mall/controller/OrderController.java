package com.cafe24.mall.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cafe24.mall.dto.BasketItemDTO;
import com.cafe24.mall.service.OrderService;

@Controller
@RequestMapping("/order")
public class OrderController {
	@Autowired
	private OrderService orderService;
	
	@ResponseBody
	@PostMapping({"/"})
	public String orderPage(
			@ModelAttribute BasketItemDTO basketItemDTO,
			Model model) {
		//System.out.println(basketItemDTO.getList().isEmpty());
		
		List<BasketItemDTO> basketItemList = basketItemDTO.getList();
		
		basketItemList = orderService.getBasketItemList(basketItemList);
		
		
		//model.addAttribute("basketItemList",basketItemList);
		
		return "test";
	}
}
