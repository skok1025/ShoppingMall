package com.cafe24.mall.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cafe24.mall.dto.BasketItemDTO;
import com.cafe24.mall.security.SecurityUser;
import com.cafe24.mall.service.BasketService;
import com.cafe24.mall.service.OrderService;

@Controller
@RequestMapping("/order")
public class OrderController {
	@Autowired
	private OrderService orderService;
	
	@Autowired
	private BasketService basketService;
	
	@PostMapping("/view")
	public String orderPage(
			@ModelAttribute BasketItemDTO basketItemDTO,
			Model model,
			@AuthenticationPrincipal SecurityUser user) {
		//System.out.println(basketItemDTO.getList().isEmpty());
		Long memberNo = user.getNo();
		List<BasketItemDTO> basketItemList = basketItemDTO.getList();
		
		basketItemList = orderService.getBasketItemList(basketItemList);
		Integer totalPrice = basketService.getTotalPrice(memberNo);
		
		
		model.addAttribute("basketItemList",basketItemList);
		model.addAttribute("totalPrice", totalPrice);
		
		return "order/view";
	}
}
