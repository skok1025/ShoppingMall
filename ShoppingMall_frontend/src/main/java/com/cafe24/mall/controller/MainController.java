package com.cafe24.mall.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.cafe24.mall.service.MainService;
import com.cafe24.mall.vo.BigCategoryVo;
import com.cafe24.mall.vo.GoodsVo;


@Controller
public class MainController {

	@Autowired
	private MainService mainService;

	@GetMapping("/")
	public String mainPage(Model model) {
		int startNo = 0;
		List<GoodsVo> mainDisplayList = mainService.getMainDisplayList();
		List<GoodsVo> list = mainService.getGoodsList(startNo);
		
		List<BigCategoryVo> categoryList = mainService.getCategoryList(); 
		
		System.out.println("상품 리스트 : "+list);
		System.out.println("+++++++++++++++");
		System.out.println("메인 진열 리스트: "+mainDisplayList);
		System.out.println("+++++++++++++++");
		System.out.println("카테고리 리스트: "+categoryList);
		
		
		model.addAttribute("goodslist", list);
		model.addAttribute("mainDisplayList",mainDisplayList);
		model.addAttribute("categoryList",categoryList);
		
		
		return "index/index";
	}
	
}
