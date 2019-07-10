package com.cafe24.mall.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cafe24.mall.repository.BasketDao;

@Service
public class BasketService {

	@Autowired
	private BasketDao basketDao;
	
}
