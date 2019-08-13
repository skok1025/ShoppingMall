package com.cafe24.mall.provider;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.web.client.RestTemplate;

import com.cafe24.mall.dto.BasketItemDTO;
import com.cafe24.mall.dto.JSONResult;
import com.cafe24.mall.dto.OrderGoodsDTO;
import com.cafe24.mall.vo.OrderVo;

@Repository
public class OrderProvider {

	@Autowired
	RestTemplate restTemplate;

	public BasketItemDTO selectBasketItemDTO(Long goodsDetailNo) {
		
		JSONResultBasketItemDTO jsonresult = 
				restTemplate.getForObject("http://localhost:8099/ShoppingMall_backend/api/basket/getItem/"+goodsDetailNo, JSONResultBasketItemDTO.class);	
		
		return jsonresult.getData();
	}

	public int insertOrder(OrderVo vo) {
		JSONResultInteger jsonresult = 
				restTemplate.postForObject("http://localhost:8099/ShoppingMall_backend/api/order/", vo, JSONResultInteger.class);
		
		return jsonresult.getData();
	}

	public List<OrderGoodsDTO> selectOrderList(Long memberNo) {
		JSONResultOrderList jsonresult = 
				restTemplate.postForObject("http://localhost:8099/ShoppingMall_backend/api/order/membergoodslist",memberNo, JSONResultOrderList.class);
		
		
		return jsonresult.getData();
	}
	private static class JSONResultBasketItemDTO extends JSONResult<BasketItemDTO>{}
	private static class JSONResultInteger extends JSONResult<Integer>{}
	private static class JSONResultOrderList extends JSONResult<List<OrderGoodsDTO>>{}
	



}

