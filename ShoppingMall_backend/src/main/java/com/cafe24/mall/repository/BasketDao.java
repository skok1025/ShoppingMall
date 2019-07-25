package com.cafe24.mall.repository;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.cafe24.mall.dto.BasketDTO;

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
		return sqlsession.selectList("basket.getBasketList", basketCode);
	}
}
