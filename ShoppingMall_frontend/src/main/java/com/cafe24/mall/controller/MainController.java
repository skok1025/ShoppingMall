package com.cafe24.mall.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;

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
	private SMSCafe24Service smsService;
	
	@ResponseBody
	@GetMapping("/api/list/{lastNo}")
	public List<GoodsVo> getList(@PathVariable("lastNo") int lastNo){
		return mainService.getGoodsList(lastNo);
	}
	
	@GetMapping("/")
	public String mainPage(Integer startNo,Model model) {
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
	
	
	/*Cafe24 SMS Hosing Service Test*/
		@GetMapping("/cafe24/api/checktel")
		public String checkTel(HttpServletRequest req) {
			String message  = "영운씨 안녕하세요";
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
