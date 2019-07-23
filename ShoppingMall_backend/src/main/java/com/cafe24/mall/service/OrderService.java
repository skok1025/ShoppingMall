package com.cafe24.mall.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cafe24.mall.dto.OrderGoodsDTO;
import com.cafe24.mall.repository.OrderDao;
import com.cafe24.mall.vo.CancelApplyVo;
import com.cafe24.mall.vo.OrderGoodsVo;
import com.cafe24.mall.vo.OrderVo;

@Service
public class OrderService {

	@Autowired
	private OrderDao orderDao;

	@Transactional
	public int addOrder(OrderVo ordervo) {
		List<OrderGoodsVo> orderGoodsList = ordervo.getOrderGoodsList();
		
		int result =  orderDao.insertOrder(ordervo);
		
		for(OrderGoodsVo ordergoodsvo:orderGoodsList) {
			ordergoodsvo.setOrderNo(ordervo.getNo()); // 방금 넣은 OrderNo
			result *= orderDao.insertOrderGoods(ordergoodsvo);
			
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

}
