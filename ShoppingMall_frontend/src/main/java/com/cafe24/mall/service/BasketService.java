package com.cafe24.mall.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cafe24.mall.dto.BasketDTO;
import com.cafe24.mall.dto.BasketItemDTO;
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

	public List<BasketItemDTO> getBasketList(Long memberNo) {
		return basketProvider.selectBasketList(memberNo);
	}

	public Integer getTotalPrice(Long memberNo) {
		return basketProvider.selectTotalPrice(memberNo);
	}

	public int deleteBasket(Long goodsDetailNo, String basketCode) {
		return basketProvider.deleteBasket(goodsDetailNo,basketCode);
	}

	public int editBasket(Long goodsDetailNo, Long memberNo, Long no,int cnt) {
		return basketProvider.updateBasket(goodsDetailNo,memberNo,no,cnt);	
	}

	public void allremove(Long memberNo) {
		basketProvider.deleteAllBasket(memberNo);
	}
}
