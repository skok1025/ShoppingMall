package com.cafe24.mall.provider;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.web.client.RestTemplate;

import com.cafe24.mall.dto.BasketItemDTO;
import com.cafe24.mall.dto.JSONResult;

@Repository
public class OrderProvider {

	@Autowired
	RestTemplate restTemplate;

	public BasketItemDTO selectBasketItemDTO(Long goodsDetailNo) {
		
		JSONResultBasketItemDTO jsonresult = 
				restTemplate.getForObject("http://localhost:8099/ShoppingMall_backend/api/basket/getItem/"+goodsDetailNo, JSONResultBasketItemDTO.class);	
		
		return jsonresult.getData();
	}


	private static class JSONResultBasketItemDTO extends JSONResult<BasketItemDTO>{}


}

