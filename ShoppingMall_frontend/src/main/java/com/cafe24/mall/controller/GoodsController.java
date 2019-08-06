package com.cafe24.mall.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.cafe24.mall.service.GoodsService;
import com.cafe24.mall.service.MainService;
import com.cafe24.mall.vo.BigCategoryVo;
import com.cafe24.mall.vo.GoodsVo;

@Controller
@RequestMapping("/goods")
public class GoodsController {

	@Autowired
	private GoodsService goodsService;


	@Autowired
	private MainService mainService;
	
	@GetMapping("/category/{smallcategoryNo}")
	public String categoryPage(@PathVariable("smallcategoryNo") Long smallcategoryNo,Model model) {
		
		List<GoodsVo> list = goodsService.getGoodsList(smallcategoryNo);
		List<BigCategoryVo> categoryList = mainService.getCategoryList(); 
		
		GoodsVo categoryNames = goodsService.getCategoryNames(smallcategoryNo);
		
		
		System.out.println("해당 카테고리 상품 리스트 : "+list);
		System.out.println("+++++++++++++++");
		System.out.println("카테고리 리스트: "+categoryList);
		System.out.println("+++++++++++++++");
		System.out.println("1차 카테고리 : "+categoryNames.getBigcategoryName()+" 2차 카테고리: "+categoryNames.getSmallcategoryName());
		
		
		model.addAttribute("goodslist", list);
		model.addAttribute("categoryList",categoryList);
		model.addAttribute("categoryNames",categoryNames);
		
		return "goods/categorygoods";
	}

	@GetMapping("/search")
	public String categoryPage(@RequestParam String keyword,Model model) {
		
		List<GoodsVo> list = goodsService.getGoodsList(keyword);
		List<BigCategoryVo> categoryList = mainService.getCategoryList(); 
		
		
		System.out.println("해당 카테고리 상품 리스트 : "+list);
		System.out.println("+++++++++++++++");
		System.out.println("카테고리 리스트: "+categoryList);
		System.out.println("+++++++++++++++");
		System.out.println("검색어: "+keyword);
		
		
		model.addAttribute("goodslist", list);
		model.addAttribute("categoryList",categoryList);
		model.addAttribute("keyword",keyword);
		
		return "goods/searchgoods";
	}
	
	
}
