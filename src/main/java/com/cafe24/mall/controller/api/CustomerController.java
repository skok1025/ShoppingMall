package com.cafe24.mall.controller.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.cafe24.mall.dto.JSONResult;
import com.cafe24.mall.service.CustomerService;
import com.cafe24.mall.vo.MemberVo;

@RestController("CustomerAPIController")
@RequestMapping("/api/customer")
public class CustomerController {
	
	@Autowired
	private CustomerService customerService;
	
	@ResponseBody
	@GetMapping("/checkId")
	public String CheckId(String id) {
		return "0"; // 존재하지않는 아이디
	}
	
	@PostMapping("/join")
	public JSONResult join(@ModelAttribute MemberVo memberVo) {
		int result = customerService.memberJoin(memberVo);
		
		return JSONResult.success(result);
	}
}
