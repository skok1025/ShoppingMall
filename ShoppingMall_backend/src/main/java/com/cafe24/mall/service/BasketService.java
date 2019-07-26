package com.cafe24.mall.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cafe24.mall.dto.BasketDTO;
import com.cafe24.mall.repository.BasketDao;
import com.cafe24.mall.vo.BasketVo;

@Service
public class BasketService {

	@Autowired
	private BasketDao basketDao;


	/**
	 * 회원이 상품을 장바구니에 등록하는 메소드
	 * @param memberNo 회원번호==장바구니 코드
	 * @param goodsDetailNo 상품상세번호
	 * @param cnt 수량
	 * @return 성공유무
	 */
	@Transactional
	public int addMemberBasket(Long memberNo, Long goodsDetailNo, Integer cnt) {
		
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("memberNo", memberNo);
		map.put("goodsDetailNo", goodsDetailNo);
		map.put("cnt", cnt);
		
		// tblBasket insert
		int result = basketDao.insertMemberBasket(map);
		
		return result;
	}

	/**
	 * 비회원이 상품을 장바구니에 등록하는 메소드
	 * @param basketCode 장바구니 코드
	 * @param goodsDetailNo 상품상세번호
	 * @param cnt 수량
	 * @return
	 */
	@Transactional
	public int addNonMemberBasket(String basketCode, Long goodsDetailNo, Integer cnt) {
		
		if(basketDao.getCodeCnt(basketCode) == 0) {
			// tblCustomerBasketCode 에 code 등록
			basketDao.insertNonMemberCustomerBasketCode(basketCode);
		}
		
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("basketCode", basketCode);
		map.put("goodsDetailNo", goodsDetailNo);
		map.put("cnt", cnt);
		
		return basketDao.insertNonMemberBasket(map);
	}
	
	
	/**
	 * 장바구니 수량정보를 수정하는 메소드
	 * @param basketvo
	 * @return
	 */
	public int modifyBasketInfo(BasketVo basketvo) {
		return basketDao.updateBasketInfo(basketvo);
	}

	public int removeBasketGoods(Long basketNo) {
		return basketDao.deleteBasketGoods(basketNo);
	}

	public List<BasketDTO> getBasketList(String basketCode) {
		return basketDao.getBasketList(basketCode);
	}

	public List<BasketDTO> getBasketList(Long memberNo) {
		return basketDao.getBasketList(memberNo);
	}

	public int allremoveBasketGoods(Long memberNo) {
		return basketDao.allDeleteBasketGoods(memberNo);
	}

	public int allremoveBasketGoods(String basketCode) {
		return basketDao.allDeleteBasketGoods(basketCode);
	}

	
	
}
