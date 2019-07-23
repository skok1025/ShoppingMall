package com.cafe24.mall.repository;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.cafe24.mall.dto.OrderGoodsDTO;
import com.cafe24.mall.vo.CancelApplyVo;
import com.cafe24.mall.vo.OrderGoodsVo;
import com.cafe24.mall.vo.OrderVo;

@Repository
public class OrderDao {
	@Autowired
	private SqlSession sqlsession;

	public int insertOrder(OrderVo ordervo) {

		return sqlsession.insert("order.insertOrder", ordervo);
	}

	public int insertOrderGoods(OrderGoodsVo ordergoodsvo) {
		return sqlsession.insert("order.insertOrderGoods",ordergoodsvo);
	}

	public List<OrderGoodsDTO> selectOrderGoodsList(String orderCode) {
		return sqlsession.selectList("order.selectOrderGoodsList", orderCode);
	}

	public OrderVo selectOrderInfo(String orderCode) {
		return sqlsession.selectOne("order.selectOrderInfo",orderCode);
	}

	public int insertCancelApply(CancelApplyVo vo) {
		return sqlsession.insert("order.insertCancelApply", vo);
	}


	public Long getCurrentInsertOrderNo() {
		return sqlsession.selectOne("order.getCurrentInsertOrderNo");
	}
}