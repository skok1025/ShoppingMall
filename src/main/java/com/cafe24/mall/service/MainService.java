package com.cafe24.mall.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cafe24.mall.repository.MainDao;

@Service
public class MainService {
	
	@Autowired
	private MainDao maindao;

}
