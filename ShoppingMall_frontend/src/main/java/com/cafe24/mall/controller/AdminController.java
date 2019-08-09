package com.cafe24.mall.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.cafe24.mall.service.AdminService;
import com.cafe24.mall.vo.BigCategoryVo;
import com.cafe24.mall.vo.GoodsVo;
import com.cafe24.mall.vo.MemberVo;
import com.cafe24.mall.vo.SmallCategoryVo;

@Controller
@RequestMapping("/admin")
public class AdminController {
	
	@Autowired
	private AdminService adminService;
	
	
	@GetMapping({"","/","/index"})
	public String index(			
			@RequestParam(value = "id", defaultValue = "") String id,
			@RequestParam(value = "orderdateEnd", defaultValue = "") String orderdateEnd,
			@RequestParam(value = "orderdateStart", defaultValue = "") String orderdateStart,
			@RequestParam(defaultValue = "1") Integer currentPage,
			Model model) {
		
		if(currentPage==null || currentPage < 1) {
			currentPage = 1;
		}
		
		List<MemberVo> memberList = adminService.getMemberList(id,orderdateStart,orderdateEnd,currentPage);
		Map<String, Integer> paging = adminService.getMemberPaging(currentPage);
		
		
		System.out.println("회원 정보 리스트: "+memberList);
		model.addAttribute("memberList",memberList);
		model.addAttribute("paging",paging);
		
		return "admin/index";
	}
	
	@GetMapping({"/goods"})
	public String goodsPage(			
			Model model,
			@RequestParam(defaultValue = "1") Integer currentPage) {
		
		System.out.println("----------currentPage:"+currentPage);
		
		List<GoodsVo> goodsList = adminService.getGoodsList(currentPage);
		System.out.println("등록된 상품 리스트: "+goodsList);
		
		Map<String, Integer> paging = adminService.getGoodsPaging(currentPage);
		
		model.addAttribute("goodsList", goodsList);
		model.addAttribute("paging",paging);
		
		return "admin/goods";
	}
	
	
	
	@GetMapping("/memberdelete/{userNo}")
	public String memberdelete(@PathVariable("userNo") Long userNo, Model model){
		int result = adminService.removerMember(userNo);
		
		if(result == 1) {
			return "redirect:/admin";
		}
		
		// 실패한 경우
		model.addAttribute("deletefail", "yes");
		return "admin/index";
	}
	
	@GetMapping("/goodsdelete/{goodsNo}")
	public String goodsdelete(@PathVariable("goodsNo") Long goodsNo, Model model){
		int result = adminService.removerGoods(goodsNo);
		
		if(result == 1) {
			return "redirect:/admin/goods";
		}
		
		// 실패한 경우
		model.addAttribute("deletefail", "yes");
		return "admin/index";
	}
	
	@GetMapping("/goods/add")
	public String addPage(Model model) {
		
		List<BigCategoryVo> blist = adminService.getBigCategoryList();
		
		System.out.println("1차 카테고리 리스트: "+blist);
		model.addAttribute("blist",blist);
		return "admin/goodsadd";
	}
	
	@PostMapping("/goods/add")
	public String addGoods(
			@ModelAttribute GoodsVo goodsVo,
			@RequestParam("mainattach") MultipartFile mainattach,
			@RequestParam("subattach1") MultipartFile subattach1,			
			@RequestParam("subattach2") MultipartFile subattach2,
			@RequestParam("subattach3") MultipartFile subattach3,
			Model model) {
		//System.out.println(mainattach);
		System.out.println("등록하고자 하는 상품: "+goodsVo);
		System.out.println("등록하고자 하는 상품의 옵션값들: "+goodsVo.getOptionListTxt());
		
		Integer result = adminService.addGoods(goodsVo,mainattach,subattach1,subattach2,subattach3);
		
		if(result == 1) {
			return "redirect:/admin/goods";
		}
		
		// 실패한 경우
		model.addAttribute("addfail", "yes");
		return "admin/index";
	}
	
	

	@ResponseBody
	@GetMapping("/goods/getsmallcategory")
	public List<SmallCategoryVo> addSmallCategory(@RequestParam Long bigCategoryNo) {
		
		List<SmallCategoryVo> slist = adminService.getSmallCategoryList(bigCategoryNo);
		
		System.out.println(bigCategoryNo+"의 2차 카테고리 리스트: "+slist);
		return slist;
	}
	

}
