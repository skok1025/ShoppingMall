package com.cafe24.mall.controller;

import java.util.List;
import java.util.Map;

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
import com.cafe24.mall.vo.GoodsDetailVo;
import com.cafe24.mall.vo.GoodsImagesVo;
import com.cafe24.mall.vo.GoodsVo;

@Controller
@RequestMapping("/goods")
public class GoodsController {

	@Autowired
	private GoodsService goodsService;


	@Autowired
	private MainService mainService;
	
	@GetMapping("/category/{smallcategoryNo}")
	public String categoryPage(
			@PathVariable("smallcategoryNo") Long smallcategoryNo,
			@RequestParam(defaultValue = "1") Integer currentPage,
			Model model) {
		
		List<GoodsVo> list = goodsService.getGoodsList(smallcategoryNo,currentPage);
		Map<String, Integer> paging = goodsService.getGoodsPaging(smallcategoryNo, currentPage);
		
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
		model.addAttribute("smallcategoryNo",smallcategoryNo);
		model.addAttribute("paging",paging);
		
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
	
	@GetMapping("/view/{goodsNo}")
	public String view(
			@PathVariable("goodsNo") Long goodsNo,
			Model model) {
		
		GoodsVo goodsVo = mainService.getGoodsInfo(goodsNo);
		// 메인 이미지
		GoodsImagesVo mainImageVo = mainService.getMainImageVo(goodsNo);
		
		// 서브 이미지들
		List<GoodsImagesVo> subImageList = mainService.getSubImageList(goodsNo);
		
		// 해당 상품 상세옵션 리스트
		List<GoodsDetailVo> goodsDetailList = mainService.getGoodsDetailList(goodsNo);
		
		// 사이드 카테고리 리스트
		List<BigCategoryVo> categoryList = mainService.getCategoryList(); 
		
		
		System.out.println("메인 이미지: "+mainImageVo);
		System.out.println("서브 이미지 리스트: "+subImageList);
		
		System.out.println("상품 옵션 리스트: "+goodsDetailList);
		
		System.out.println("상품 정보: "+goodsVo);
		model.addAttribute("vo",goodsVo);
		model.addAttribute("categoryList",categoryList);
		model.addAttribute("mainImage",mainImageVo.getImage());
		model.addAttribute("subImageList",subImageList);
		model.addAttribute("goodsDetailList",goodsDetailList);
		
		
		return "goods/view";
	}
	
	
}
