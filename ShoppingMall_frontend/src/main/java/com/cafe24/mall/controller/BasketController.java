package com.cafe24.mall.controller;

import java.util.List;
import java.util.Random;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cafe24.mall.dto.BasketItemDTO;
import com.cafe24.mall.security.SecurityUser;
import com.cafe24.mall.service.BasketService;
import com.cafe24.mall.vo.BasketVo;

@Controller
@RequestMapping("/basket")
public class BasketController {

	@Autowired
	private BasketService basketService;
	
	private String cookiePath = "/";

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

	//@ResponseBody
	@PostMapping("/nonuser/add")
	public String nonuserbasket(
			HttpServletRequest req,
			HttpServletResponse resp,
			@ModelAttribute("basketVo") BasketVo basketVo,
			Model model
			) {
		Random rand = new Random();
		String randomString = "u_"+(rand.nextInt(100000)+10000);
		// 추가적으로 랜덤하게 생성된 장바구니 코드의 경우, 기존 디비에 존재한다면 다시 만들어야 한다.
		
		boolean isBasketCodeExist = false;
		String basketCode = null;
		for(Cookie cookie:req.getCookies()) {
			System.out.println("존재하는 쿠키 이름: "+cookie.getName());
			if(cookie.getName().equals("temp_id")) {
				isBasketCodeExist = true;
				basketCode = cookie.getValue();
				//break;
			}
		}
		
		// Cookie 에 temp_id 가 없는 경우,
		if(!isBasketCodeExist) {
			Cookie codeCookie = new Cookie("temp_id", randomString);
			codeCookie.setMaxAge(60*60*24*5);
			codeCookie.setPath(cookiePath);
			resp.addCookie(codeCookie);
			basketCode = codeCookie.getValue();
		}	
		
		System.out.println("비회원 장바구니 코드: "+basketCode);
		System.out.println("장바구니에 담을 vo: "+basketVo);
		
		System.out.println("----------------------------");
		System.out.println(basketVo.getGoodsBasketList());
		
		// 장바구니 담기
		int result = basketService.addBasketManyGoods(basketCode, basketVo.getGoodsBasketList());
		
		if(result == 1) {
			System.out.println("비회원 장바구니 추가 성공");
			
			//model.addAttribute("basketaddsuccess","yes");
			//return "redirect:/goods/view/"+basketVo.getGoodsNo()+"?nonuseraddsuccess=yes";
			return "redirect:/basket/nonuser/view";
		}
		
		return "index/index";
	}
	
	//@ResponseBody
	@GetMapping("/nonuser/view")
	public String nonmemberBasketView(
			HttpServletRequest req,
			HttpServletResponse resp,
			Model model) {
		String basketCode = null;
		
		for(Cookie cookie:req.getCookies()) {
			if(cookie.getName().equals("temp_id")) {
				//System.out.println("유효범위: "+cookie.getPath());
				basketCode = cookie.getValue();
			}
		}
		
		
		for(Cookie cookie: req.getCookies()) {
			System.out.println("쿠키 이름: "+cookie.getName());
			System.out.println("\t\t"+" 쿠키 value: "+cookie.getValue());
		}
		
		System.out.println("비회원 장바구니 코드: "+basketCode);
		List<BasketItemDTO> basketList = null;
		if(basketCode != null) {
			basketList = basketService.getBasketList(basketCode);
		}
		
		Integer totalPrice = basketService.getTotalPrice(basketCode);
		
		System.out.println("장바구니: "+basketList);
		model.addAttribute("basketList", basketList);
		model.addAttribute("totalPrice", totalPrice);
		model.addAttribute("basketCode",basketCode);
		//model.addAttribute("memberNo",memberNo);
		return "basket/view";
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
	@GetMapping("/nonuser/delete/{goodsDetailNo}/{basketCode}")
	public String deleteMemberBasket(
			@PathVariable("goodsDetailNo") Long goodsDetailNo,
			@PathVariable("basketCode") String basketCode
			) {
		
			basketService.deleteBasket(goodsDetailNo,basketCode);
		return "redirect:/basket/nonuser/view";
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
	
	@GetMapping("/nonmember/allremove")
	public String nonmemberAllRemove(HttpServletRequest req) {
		String basketCode = "";
		
		for(Cookie cookie:req.getCookies()) {
			if(cookie.getName().equals("temp_id")) {
				//System.out.println("유효범위: "+cookie.getPath());
				basketCode = cookie.getValue();
			}
		}
		
		System.out.println("삭제 진행할 장바구니 코드: "+basketCode);
		
		basketService.allremove(basketCode);	
		
		
		return "redirect:/basket/nonuser/view";
	}
	
}
