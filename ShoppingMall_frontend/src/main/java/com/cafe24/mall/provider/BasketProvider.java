package com.cafe24.mall.provider;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.web.client.RestTemplate;

import com.cafe24.mall.dto.BasketDTO;
import com.cafe24.mall.dto.BasketProcessDTO;
import com.cafe24.mall.dto.JSONResult;


@Repository
public class BasketProvider {

	@Autowired
	private RestTemplate restTemplate;

	public int addBasket(Long memberNo, Long goodsDetailNo, Integer cnt) {
		
		BasketProcessDTO dto = new BasketProcessDTO();
		dto.setMemberNo(memberNo);
		dto.setGoodsDetailNo(goodsDetailNo);
		dto.setCnt(cnt);
	
		JSONResultInteger jsonresult = 
				restTemplate.postForObject("http://localhost:8099/ShoppingMall_backend/api/basket/member/add", dto, JSONResultInteger.class);
		
		return jsonresult.getData();
	}
	
	public List<BasketDTO> selectBasketList(Long memberNo) {
		
		JSONResultBasketList jsonresult = 
				restTemplate.getForObject("http://localhost:8099/ShoppingMall_backend/api/basket/member/list?memberNo="+memberNo, JSONResultBasketList.class);
		
		return jsonresult.getData();
	}
	
	
	private static class JSONResultInteger extends JSONResult<Integer>{	}
	private static class JSONResultBasketList extends JSONResult<List<BasketDTO>>{	}

	
	
}
