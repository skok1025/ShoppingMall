package com.cafe24.mall.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cafe24.mall.security.SecurityUser;
import com.cafe24.mall.service.BasketService;
import com.cafe24.mall.service.MainService;
import com.cafe24.mall.util.SMSCafe24Service;
import com.cafe24.mall.vo.BigCategoryVo;
import com.cafe24.mall.vo.GoodsVo;
import com.cafe24.mall.vo.MaindisplayCategoryVo;


@Controller
public class MainController {

	@Autowired
	private MainService mainService;
	
	@Autowired
	private BasketService basketService;

	@Autowired
	private SMSCafe24Service smsService;
	
	
	@ResponseBody
	@GetMapping("/api/list/{lastNo}")
	public List<GoodsVo> getList(@PathVariable("lastNo") int lastNo){
		return mainService.getGoodsList(lastNo);
	}
	
	@GetMapping({"","/","/index"})
	public String mainPage(Integer startNo,Model model,HttpSession session) {
		//int startNo = 0;
		//if(startNo ==null) 
			startNo = 0;
		List<GoodsVo> mainDisplayList = mainService.getMainDisplayList();
		//List<GoodsVo> list = mainService.getGoodsList(startNo);
		List<BigCategoryVo> categoryList = mainService.getCategoryList(); 
		
		MaindisplayCategoryVo maindisplayCategoryVo = mainService.getMaindisplayCategoryVo();
		
		
		//System.out.println("상품 리스트 : "+list);
		System.out.println("+++++++++++++++");
		System.out.println("메인 진열 리스트: "+mainDisplayList);
		System.out.println("+++++++++++++++");
		System.out.println("카테고리 리스트: "+categoryList);		
		System.out.println("+++++++++++++++");
		System.out.println("메인 진열 카테고리 정보: "+maindisplayCategoryVo);
		
		
		//model.addAttribute("goodslist", list);
		
		
		model.addAttribute("mainDisplayList",mainDisplayList);
		model.addAttribute("categoryList",categoryList);
		model.addAttribute("maindisplayCategoryVo",maindisplayCategoryVo);
		
		
		return "index/index";
	}
	
	@GetMapping("/login")
	public String login() {
		return "index/login";
	}
	
	@GetMapping("/basket/update")
	public String authChangeMemberBasket(HttpServletRequest request,@AuthenticationPrincipal SecurityUser user) {
		Long memberNo = user.getNo();
    	String basketCode = user.getBasketCode();
    	
    	if(basketCode == null || "".equals(basketCode)) {
    		return "redirect:/";
    	}
    	
    	basketService.updateToLoginBasket(memberNo, basketCode);
    	
    	// 로그인 장바구니 업데이트 후 메인 페이지로 redirect
    	return "redirect:/";
	}
	
	
	/*Cafe24 SMS Hosing Service Test*/
		@GetMapping("/cafe24/api/checktel")
		public String checkTel(HttpServletRequest req) {
			String message  = "TEST 입니다";
			String sellertel = "01068669202";
			
			req.setAttribute("sellertel", sellertel);
			req.setAttribute("message", message);
			
			return "api/smsOrder";
		}
		
		@GetMapping("/cafe24/api/checktel2")
		public String checkTel2() {
			String message  = "skok1025(김석현님), SK Mall 에 가입하신 것 환영합니다";
			String receiverTel = "01068669202";
			try {
				smsService.cafe24SMSService( 
						message, 
						receiverTel, 
						null, 
						null, 
						null, 
						null, 
						null,
						null,
						null,
						null,
						null
				);		
			} catch (Exception e) {
				e.printStackTrace();
			}
			return "template";
		}
		
	
	
	
	
}
