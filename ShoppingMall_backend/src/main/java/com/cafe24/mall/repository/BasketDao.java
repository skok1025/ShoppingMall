package com.cafe24.mall.repository;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.cafe24.mall.dto.BasketDTO;
import com.cafe24.mall.dto.BasketItemDTO;
import com.cafe24.mall.vo.BasketVo;

@Repository
public class BasketDao {

	@Autowired
	private SqlSession sqlsession;

	public int insertMemberBasket(Map<String, Object> map) {
		return sqlsession.insert("basket.insertMemberBasket", map);
	}

	public int getCodeCnt(String basketCode) {
		return sqlsession.selectOne("basket.getCodeCnt", basketCode);
	}

	public void insertNonMemberCustomerBasketCode(String basketCode) {
		sqlsession.insert("basket.insertNonMemberCustomerBasketCode", basketCode);
	}

	public int insertNonMemberBasket(Map<String, Object> map) {
		return sqlsession.insert("basket.insertNonMemberBasket", map);
	}

	public List<BasketDTO> getBasketList(String basketCode) {
		return sqlsession.selectList("basket.getNonMemberBasketList", basketCode);
	}

	public List<BasketDTO> getBasketList(Long memberNo) {
		return sqlsession.selectList("basket.getMemberBasketList", memberNo);
	}

	public int updateBasketInfo(BasketVo basketvo) {
		return sqlsession.update("basket.updateBasketInfo", basketvo);
	}

	public int deleteBasketGoods(Map<String, Object> map) {
		return sqlsession.delete("basket.deleteBasketGoods", map);
	}

	public int allDeleteBasketGoods(Long memberNo) {
		return sqlsession.delete("basket.allDeleteMemberBasketGoods", memberNo);
	}

	public int allDeleteBasketGoods(String basketCode) {
		return sqlsession.delete("basket.allDeleteNonMemberBasketGoods", basketCode);
	}

	public Long getCurrentInsertBasketNo() {
		return sqlsession.selectOne("basket.getCurrentInsertBasketNo");
	}

	public Integer selectBasketTotal(Long memberNo) {
		return sqlsession.selectOne("basket.selectMemberBasketTotal", memberNo);
	}

	public Integer selectBasketTotal(String basketCode) {
		return sqlsession.selectOne("basket.selectNonMemberBasketTotal", basketCode);
	}

	public void updateBasketCntZero(BasketVo basketvo) {
		sqlsession.update("basket.updateBasketCntZero",basketvo);
	}

	public void deleteCntZero() {
		sqlsession.delete("basket.deleteBasketCntZero");
	}

	public BasketItemDTO selectItem(Long goodsDetailNo) {
		return sqlsession.selectOne("basket.selectItem", goodsDetailNo);
	}

	public int updateToLoginBasket(Map<String, Object> map) {
		return sqlsession.update("basket.updateToLoginBasket", map);
	}

}
