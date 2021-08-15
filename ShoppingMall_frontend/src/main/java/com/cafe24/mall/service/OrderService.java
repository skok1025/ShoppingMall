package com.cafe24.mall.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cafe24.mall.dto.BasketItemDTO;
import com.cafe24.mall.dto.OrderGoodsDTO;
import com.cafe24.mall.provider.OrderProvider;
import com.cafe24.mall.vo.OrderGoodsVo;
import com.cafe24.mall.vo.OrderGoodsVo.status;
import com.cafe24.mall.vo.OrderVo;
import com.cafe24.mall.vo.OrderVo.orderStatus;
import com.cafe24.mall.vo.OrderVo.paymentStatus;

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

	public int addOrder(OrderVo vo) {
		vo.setPaymentStatus(paymentStatus.y);
		vo.setOrderStatus(orderStatus.배송대기);
		vo.setPaymentWay("카드");
		
		vo.setReceiverAddress(vo.getAddr1()+" "+vo.getAddr2());
		
		for(OrderGoodsVo orderGoodsVo :vo.getOrderGoodsList()) {
			orderGoodsVo.setStatus(status.배송대기);
		}
		
		
		System.out.println("in service orderVo: "+vo);
		return orderProvider.insertOrder(vo);
	}

	public List<OrderGoodsDTO> getOrderList(Long memberNo) {
		List<OrderGoodsDTO> orderList = orderProvider.selectOrderList(memberNo);
		
		for (OrderGoodsDTO orderGoodsDTO : orderList) {
			String orderCode = orderGoodsDTO.getOrderCode();
			
			String orderPrice = orderProvider.getOrderPrice(orderCode);
			String orderCalcInfo = orderProvider.getOrderCalcInfo(orderCode);
			
			orderGoodsDTO.setOrderPrice(orderPrice);
			orderGoodsDTO.setOrderCalcInfo(orderCalcInfo);
		}
		
		return orderList;
	}
}
