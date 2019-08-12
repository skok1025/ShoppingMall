package com.cafe24.mall.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cafe24.mall.dto.BasketItemDTO;
import com.cafe24.mall.provider.OrderProvider;

@Service
public class OrderService {
	@Autowired
	private OrderProvider orderProvider;

	public List<BasketItemDTO> getBasketItemList(List<BasketItemDTO> basketItemList) {

		for(BasketItemDTO dto: basketItemList) {
			Long goodsDetailNo = dto.getGoodsDetailNo();
			
			BasketItemDTO basketItem = orderProvider.selectBasketItemDTO(goodsDetailNo);
			
			dto.setGoodsName(basketItem.getGoodsName());
			dto.setOptionName(basketItem.getOptionName());
			dto.setThumbnail(basketItem.getThumbnail());
			dto.setPrice(basketItem.getPrice()*dto.getCnt());
		}
		
		System.out.println("in service setting basketItemlist: "+basketItemList);
		
		return basketItemList;
	}
}
