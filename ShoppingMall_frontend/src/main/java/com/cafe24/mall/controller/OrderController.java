package com.cafe24.mall.controller;

import java.io.UnsupportedEncodingException;
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
import com.cafe24.mall.dto.OrderGoodsDTO;
import com.cafe24.mall.security.SecurityUser;
import com.cafe24.mall.service.BasketService;
import com.cafe24.mall.service.CouponService;
import com.cafe24.mall.service.OrderService;
import com.cafe24.mall.vo.CouponVo;
import com.cafe24.mall.vo.OrderVo;

@Controller
@RequestMapping("/order")
public class OrderController {
	@Autowired
	private OrderService orderService;
	
	@Autowired
	private BasketService basketService;
	
	@Autowired
	private CouponService couponService;
	
	@GetMapping("/coupon-view")
	public String orderCouponPage(@AuthenticationPrincipal SecurityUser user, Model model) {
		Long memberNo = user.getNo();
		List<CouponVo> memberCouponList = couponService.getMemberCouponList(memberNo);
		System.out.println(memberCouponList);
		model.addAttribute("memberCouponList", memberCouponList);
		
		return "order/coupon";
	}
	
	@PostMapping("/view")
	public String orderPage(
			@ModelAttribute BasketItemDTO basketItemDTO,
			Model model,
			@AuthenticationPrincipal SecurityUser user) {
		//System.out.println(basketItemDTO.getList().isEmpty());
		Long memberNo = user.getNo();
		List<BasketItemDTO> basketItemList = basketItemDTO.getList();
	
		String userName = user.getName();
		
		System.out.println("로그인 중인 회원명: "+userName);
		
//		try {
//			userName = new String(userName.getBytes("MS949"), "utf-8");
//		} catch (UnsupportedEncodingException e) {
//			e.printStackTrace();
//		}
		
		basketItemList = orderService.getBasketItemList(basketItemList);
		Integer totalPrice = basketService.getTotalPrice(memberNo);
		
		
		model.addAttribute("basketItemList",basketItemList);
		model.addAttribute("totalPrice", totalPrice);
		model.addAttribute("userName", userName);
		model.addAttribute("userTel", user.getTel());
		
		return "order/view";
	}
	
	//@ResponseBody
	@PostMapping("/proceed")
	public String orderProceed(
			OrderVo vo,
			@AuthenticationPrincipal SecurityUser user,
			Model model) {
		vo.setMemberNo(user.getNo());
		int result = orderService.addOrder(vo);
		
		if(result == 1) {
			// 장바구니 비우기
			basketService.allremove(user.getNo());
			System.out.println(vo);
			if (!vo.getApplyCouponNo().equals("null") && !vo.getApplyCouponNo().isEmpty()) {
				couponService.setUsedCoupon(vo.getApplyCouponNo());
			}
			
			return "redirect:/order/list?ordersuccess=yes";
		}
		
		model.addAttribute("orderfail", "yes");
		return "order/view";
	}
	
	//@ResponseBody
	@GetMapping("/list")
	public String orderList(
			@AuthenticationPrincipal SecurityUser user,
			Model model
			) {
		
		List<OrderGoodsDTO> list = orderService.getOrderList(user.getNo());
		System.out.println(list);
		model.addAttribute("list", list);
		return "order/list";
	}
}
