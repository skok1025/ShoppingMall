package com.cafe24.mall.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cafe24.mall.dto.ChangeApplyDTO;
import com.cafe24.mall.dto.EavDTO;
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
		
		String orderCode = orderDao.getGenerateOrderCode();
		
		// 주문코드 생성
		ordervo.setCode(orderCode);
		
		int result =  orderDao.insertOrder(ordervo);
		
		for(OrderGoodsVo ordergoodsvo:orderGoodsList) {
			ordergoodsvo.setOrderNo(ordervo.getNo()); // 방금 넣은 OrderNo
			ordergoodsvo.setSailingPrice(orderDao.getSeillingPrice(ordergoodsvo.getGoodsDetailNo()) * ordergoodsvo.getCnt());
			result *= orderDao.insertOrderGoods(ordergoodsvo);
			
			// 주문 후, 상품 재고 주문수량만큼 차감
			orderDao.updateInventoryCnt(ordergoodsvo.getGoodsDetailNo(),ordergoodsvo.getCnt());
			
			// 장바구니에 해당 상품을 삭제해야 함
			if(ordergoodsvo.getBasketCode() != null) {
				orderDao.deleteBasketGoodsByBasketCodeAndGoodsDetailNo(ordergoodsvo.getBasketCode(),ordergoodsvo.getGoodsDetailNo());
			}
		}
		
		addOrderValueInfo(ordervo, orderCode);
		
		return result;
	}
	
	private void addOrderValueInfo(OrderVo ordervo, String orderCode) {
		String orderPrice = ordervo.getOrderPrice();
		String orderCalcInfo = ordervo.getOrderCalcInfo();
		String applyCouponNo = ordervo.getApplyCouponNo();
		
		System.out.println("test");
		System.out.println(ordervo);
		
		if (!orderPrice.equals("null")) {
			EavDTO dto = new EavDTO();
			dto.setOrder_code(orderCode);
			dto.setConfig_name("order_price");
			dto.setConfig_value(orderPrice);
			
			orderDao.insertOrderValue(dto);
		}
		
		if (!orderCalcInfo.equals("null")) {
			EavDTO dto = new EavDTO();
			dto.setOrder_code(orderCode);
			dto.setConfig_name("order_calc_info");
			dto.setConfig_value(orderCalcInfo);
			
			orderDao.insertOrderValue(dto);
		}
		
		if (!applyCouponNo.equals("null")) {
			EavDTO dto = new EavDTO();
			dto.setOrder_code(orderCode);
			dto.setConfig_name("apply_coupon_no");
			dto.setConfig_value(applyCouponNo);
			
			orderDao.insertOrderValue(dto);
		}
	}

	public List<OrderGoodsDTO> getOrderGoodsList(String orderCode) {
		return orderDao.selectOrderGoodsList(orderCode);
	}

	public List<OrderGoodsDTO> getOrderGoodsList(Long memberNo) {
		return orderDao.selectOrderGoodsList(memberNo);
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

	public String getOrderDetailInfo(String orderCode, String info) {
		Map<String, String> searchMap = new HashMap<String, String>();
		searchMap.put("orderCode", orderCode);
		searchMap.put("config_name", info);
		
		return orderDao.selectOrderDetailInfo(searchMap);
	}


}
