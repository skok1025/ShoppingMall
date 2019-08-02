package com.cafe24.mall.repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.cafe24.mall.dto.ChangeApplyDTO;
import com.cafe24.mall.dto.OrderDTO;
import com.cafe24.mall.dto.OrderGoodsDTO;
import com.cafe24.mall.vo.CancelApplyVo;
import com.cafe24.mall.vo.ChangeApplyVo;
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

	public int insertChangeApply(ChangeApplyVo vo) {
		return sqlsession.insert("order.insertChangeApply", vo);
	}

	public long getCurrentInsertGoodsDetailNo() {
		return sqlsession.selectOne("order.getCurrentInsertGoodsDetailNo");
	}

	public List<ChangeApplyDTO> selectChangeApplyList(String orderCode) {
		return sqlsession.selectList("order.selectChangeApplyList", orderCode);
	}

	public String getGenerateOrderCode() {
		return sqlsession.selectOne("order.getGenerateOrderCode");
	}

	public int deleteChangeApply(Long changeApplyNo) {
		return sqlsession.delete("order.deleteChangeApply", changeApplyNo);
	}

	public Long getCurrentInsertChangeApplyNo() {
		return sqlsession.selectOne("order.getCurrentInsertChangeApplyNo");
	}

	public int getSeillingPrice(Long currentInsertGoodsDetailNo) {
		return sqlsession.selectOne("order.getSeillingPrice", currentInsertGoodsDetailNo);
	}
	public List<OrderDTO> selectOrderList(Long memberNo) {
		return sqlsession.selectList("order.selectOrderList",memberNo);
	}

	public void deleteBasketGoodsByBasketCodeAndGoodsDetailNo(String basketCode, Long goodsDetailNo) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("basketCode", basketCode);
		map.put("goodsDetailNo", goodsDetailNo);
		sqlsession.delete("order.deleteBasketGoodsByBasketCodeAndGoodsDetailNo", map);
	}

	public void updateInventoryCnt(Long goodsDetailNo, int cnt) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("goodsDetailNo", goodsDetailNo);
		map.put("cnt", cnt);
		sqlsession.update("order.updateInventoryCnt", map);
	}

}