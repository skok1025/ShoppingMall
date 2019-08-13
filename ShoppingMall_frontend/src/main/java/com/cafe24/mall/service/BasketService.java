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

	// 회원 장바구니 담기
	public int addBasketManyGoods(Long memberNo, List<BasketDTO> goodsBasketList) {

		int result = 1;
		
		for(BasketDTO dto : goodsBasketList) {
			result *= basketProvider.addBasket(memberNo,dto.getGoodsDetailNo(),dto.getCnt());
		}
		
		return result;
	}
	// 비회원 장바구니 담기
	public int addBasketManyGoods(String basketCode, List<BasketDTO> goodsBasketList) {
		int result = 1;
		
		for(BasketDTO dto : goodsBasketList) {
			result *= basketProvider.addBasket(basketCode, dto);
		}
		
		return result;
	}

	public List<BasketItemDTO> getBasketList(Long memberNo) {
		return basketProvider.selectBasketList(memberNo);
	}

	public Integer getTotalPrice(Long memberNo) {
		return basketProvider.selectTotalPrice(memberNo);
	}

	public Integer getTotalPrice(String basketCode) {
		return basketProvider.selectTotalPrice(basketCode);
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

	public void allremove(String basketCode) {
		basketProvider.deleteAllBasket(basketCode);
	}
	public List<BasketItemDTO> getBasketList(String basketCode) {
		return basketProvider.selectBasketList(basketCode);
	}
	public void updateToLoginBasket(Long memberNo, String basketCode) {
		basketProvider.updateToLoginBasket(memberNo, basketCode);
	}

	
}
