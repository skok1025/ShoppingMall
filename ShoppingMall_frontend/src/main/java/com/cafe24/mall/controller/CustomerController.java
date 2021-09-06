package com.cafe24.mall.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cafe24.mall.service.CustomerService;
import com.cafe24.mall.service.EmailService;
import com.cafe24.mall.vo.MemberVo;

@Controller
@RequestMapping("/customer")
public class CustomerController {

	@Autowired
	private CustomerService customerService;

	@GetMapping("/join")
	public String join(@ModelAttribute("memberVo") MemberVo memberVo) {
		return "index/join";
	}

	@PostMapping("/join")
	public String join(@ModelAttribute("memberVo") @Valid MemberVo memberVo, BindingResult bindingResult, Model model) {

		if (bindingResult.hasErrors()) {
			model.addAttribute(bindingResult.getModel());
			return "index/join";
		}

		Integer result = customerService.joinMember(memberVo);
		
		// 가입 축하 sms 메세지 전송
		customerService.sendJoinSms(memberVo);

		if (result == 1) {
			return "redirect:/login?joinsuccess=yes";
		}

		// 실패한 경우
		model.addAttribute("joinfail", "yes");
		return "index/join";

	}

	@ResponseBody
	@GetMapping("/checkid")
	public Integer checkid(@RequestParam String id) {
		return customerService.checkid(id);
	}

	@ResponseBody
	@PostMapping("/checkemail")
	public String checkEmail(String to) {
		Object authCode = customerService.sendEmailAuthCode(to);

		return authCode.equals(false) ? "error" : (String) authCode;
	}

}
