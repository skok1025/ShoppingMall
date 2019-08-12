package com.cafe24.mall.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cafe24.mall.dto.BasketDTO;
import com.cafe24.mall.provider.BasketProvider;

@Service
public class BasketService {

	@Autowired
	private BasketProvider basketProvider;

	public int addBasketManyGoods(Long memberNo, List<BasketDTO> goodsBasketList) {

		int result = 1;
		
		for(BasketDTO dto : goodsBasketList) {
			result *= basketProvider.addBasket(memberNo,dto.getGoodsDetailNo(),dto.getCnt());
		}
		
		return result;
	}

	public List<BasketDTO> getBasketList(Long memberNo) {
		return basketProvider.selectBasketList(memberNo);
	}
}
