package com.cafe24.mall.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.cafe24.mall.service.MainService;
import com.cafe24.mall.vo.GoodsVo;
import com.sun.tools.javac.util.List;

@Controller
public class MainController {

	@Autowired
	private MainService mainService;

	@GetMapping("/")
	public String mainPage(Model model) {
		int startNo = 0;
		//List<GoodsVo> list = mainService.getGoodsList(startNo);
		String list = mainService.getGoodsList(startNo);
		
		System.out.println(list);
		
		//model.addAttribute("goodslist", list);
		return "index/index";
	}
	
}
