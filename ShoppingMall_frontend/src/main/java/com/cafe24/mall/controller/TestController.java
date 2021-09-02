package com.cafe24.mall.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.InputStream;
import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.cafe24.mall.datasource.DataSource;


@Controller
@RequestMapping("/template")
public class TestController {

	@GetMapping("/basic")
	public String basic() {
		return "template";
	}

	@GetMapping("/login")
	public String login() {
		return "customer/login";
	}

}
