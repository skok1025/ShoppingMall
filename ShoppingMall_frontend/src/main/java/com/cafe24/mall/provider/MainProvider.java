package com.cafe24.mall.provider;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.web.client.RestTemplate;

@Repository
public class MainProvider {

//	@Autowired
//	private RestTemplate restTemplate;
	
	public String selectGoodsList(int startNo) {
		RestTemplate restTemplate = new RestTemplate();
		return restTemplate.getForObject("http://localhost:8099/ShoppingMall_backend/api/goods/list/"+startNo, String.class);
	}

	
}
