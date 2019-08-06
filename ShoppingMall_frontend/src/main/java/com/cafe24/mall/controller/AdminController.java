package com.cafe24.mall.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.cafe24.mall.service.AdminService;
import com.cafe24.mall.vo.MemberVo;

@Controller
@RequestMapping("/admin")
public class AdminController {
	
	@Autowired
	private AdminService adminService;
	
	
	@GetMapping({"","/","/index"})
	public String index(			
			@RequestParam(value = "id", defaultValue = "") String id,
			@RequestParam(value = "orderdateEnd", defaultValue = "") String orderdateEnd,
			@RequestParam(value = "orderdateStart", defaultValue = "") String orderdateStart
			,Model model) {
		
		
		List<MemberVo> memberList = adminService.getMemberList(id,orderdateStart,orderdateEnd);
		
		System.out.println("회원 정보 리스트: "+memberList);
		model.addAttribute("memberList",memberList);
		
		return "admin/index";
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
	

}
