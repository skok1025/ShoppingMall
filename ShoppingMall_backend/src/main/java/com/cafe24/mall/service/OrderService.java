package com.cafe24.mall.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cafe24.mall.dto.ChangeApplyDTO;
import com.cafe24.mall.dto.OrderDTO;
import com.cafe24.mall.dto.OrderGoodsDTO;
import com.cafe24.mall.repository.OrderDao;
import com.cafe24.mall.vo.CancelApplyVo;
import com.cafe24.mall.vo.ChangeApplyVo;
import com.cafe24.mall.vo.OrderGoodsVo;
import com.cafe24.mall.vo.OrderVo;

@Service
public class OrderService {

	@Autowired
	private OrderDao orderDao;

	@Transactional
	public int addOrder(OrderVo ordervo) {
		List<OrderGoodsVo> orderGoodsList = ordervo.getOrderGoodsList();
		
		// 주문코드 생성
		ordervo.setCode(orderDao.getGenerateOrderCode());
		
		int result =  orderDao.insertOrder(ordervo);
		
		for(OrderGoodsVo ordergoodsvo:orderGoodsList) {
			ordergoodsvo.setOrderNo(ordervo.getNo()); // 방금 넣은 OrderNo
			ordergoodsvo.setSailingPrice(orderDao.getSeillingPrice(ordergoodsvo.getGoodsDetailNo()) * ordergoodsvo.getCnt());
			result *= orderDao.insertOrderGoods(ordergoodsvo);
			
			// 장바구니에 해당 상품을 삭제해야 함
			if(ordergoodsvo.getBasketCode() != null) {
				orderDao.deleteBasketGoodsByBasketCodeAndGoodsDetailNo(ordergoodsvo.getBasketCode(),ordergoodsvo.getGoodsDetailNo());
			}
		}
		
		
		return result;
	}

	public List<OrderGoodsDTO> getOrderGoodsList(String orderCode) {
		return orderDao.selectOrderGoodsList(orderCode);
	}

	public OrderVo getOrderInfo(String orderCode) {
		return orderDao.selectOrderInfo(orderCode);
	}

	public int addCancelApply(CancelApplyVo vo) {
		return orderDao.insertCancelApply(vo);
	}

	public int addChangeApply(ChangeApplyVo vo) {
		return orderDao.insertChangeApply(vo);
	}

	public List<ChangeApplyDTO> getChangeApplyList(String orderCode) {
		return orderDao.selectChangeApplyList(orderCode);
	}

	public int cancelChangeApply(Long changeApplyNo) {
		return orderDao.deleteChangeApply(changeApplyNo);
	}

	public List<OrderDTO> getOrderList(Long memberNo) {
		return orderDao.selectOrderList(memberNo);
	}

}
