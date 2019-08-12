package com.cafe24.mall.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cafe24.mall.dto.BasketDTO;
import com.cafe24.mall.dto.BasketItemDTO;
import com.cafe24.mall.security.SecurityUser;
import com.cafe24.mall.service.BasketService;
import com.cafe24.mall.vo.BasketVo;
import com.cafe24.mall.vo.GoodsDetailVo;

@Controller
@RequestMapping("/basket")
public class BasketController {

	@Autowired
	private BasketService basketService;
	
	@PostMapping("/user/add")
	public String userbasket(
			@ModelAttribute("basketVo") BasketVo basketVo
			,@AuthenticationPrincipal SecurityUser user
	) {
		
		System.out.println("----------------------------");
		System.out.println(basketVo.getGoodsBasketList());
		System.out.println("등록하려 하는 멤버번호: "+user.getNo());
		
		int result = basketService.addBasketManyGoods(user.getNo(),basketVo.getGoodsBasketList());
		
		if(result == 1) {
			return "redirect:/goods/view/"+basketVo.getGoodsNo()+"?addsuccess=yes";
		}
		
		return result+"";
	}

	@ResponseBody
	@PostMapping("/nonuser/add")
	public String nonuserbasket(
			@ModelAttribute("basketVo") BasketVo basketVo
			) {
		
		System.out.println("----------------------------");
		System.out.println(basketVo.getGoodsBasketList());
		
		return "test";
	}
	
	@GetMapping("/view")
	public String memberBasketView(
			Model model,
			@AuthenticationPrincipal SecurityUser user) {
		
		Long memberNo = user.getNo();
		List<BasketItemDTO> basketList = basketService.getBasketList(memberNo);
		
		Integer totalPrice = basketService.getTotalPrice(memberNo);
		
		System.out.println("장바구니: "+basketList);
		model.addAttribute("basketList", basketList);
		model.addAttribute("totalPrice", totalPrice);
		model.addAttribute("memberNo",memberNo);
		return "basket/view";
	}
	
	@GetMapping("/delete/{goodsDetailNo}/{basketCode}")
	public String deleteMemberBasket(
			@PathVariable("goodsDetailNo") Long goodsDetailNo,
			@PathVariable("basketCode") String basketCode,
			@AuthenticationPrincipal SecurityUser user
			) {
		System.out.println("삭제 진행 회원번호: "+user.getNo());
		
		if(user.getNo().toString().equals(basketCode)) { // 로그인을 한 회원인지 여부 확인
			basketService.deleteBasket(goodsDetailNo,basketCode);
		}
		return "redirect:/basket/view";
	}
	
	@GetMapping("/edit/{goodsDetailNo}/{memberNo}/{no}/{cnt}")
	public String editMemberBasket(
			@PathVariable("goodsDetailNo") Long goodsDetailNo,
			@PathVariable("memberNo") Long memberNo,
			@PathVariable("no") Long no,
			@PathVariable("cnt") int cnt,
			@AuthenticationPrincipal SecurityUser user
			) {
		if(user.getNo().equals(memberNo)) {
			basketService.editBasket(goodsDetailNo,memberNo,no,cnt);
		}
		
		return "redirect:/basket/view";
	}
	
	@GetMapping("/allremove/{memberNo}")
	public String allRemove(
			@PathVariable("memberNo") Long memberNo,
			@AuthenticationPrincipal SecurityUser user) {
		
		System.out.println("삭제 진행 회원번호: "+user.getNo());
		System.out.println(memberNo);
		
		if(user.getNo().equals(memberNo)) {
			basketService.allremove(memberNo);
		}
		
		return "redirect:/basket/view";
	}
	
}
