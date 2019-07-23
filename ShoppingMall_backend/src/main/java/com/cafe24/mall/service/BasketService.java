package com.cafe24.mall.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cafe24.mall.repository.BasketDao;
import com.cafe24.mall.vo.BasketVo;

@Service
public class BasketService {

	@Autowired
	private BasketDao basketDao;

	/**
	 * 상품을 장바구니에 등록하는 메소드
	 * @param basketCode 장바구니 코드
	 * @param goodsDetailNo 상품상세번호
	 * @return 성공유무
	 */
	public int addBasket(String basketCode, Long goodsDetailNo) {
		return 1;
	}

	/**
	 * 장바구니 정보를 수정하는 메소드
	 * @param basketvo
	 * @return
	 */
	public int modifyBasketInfo(BasketVo basketvo) {
		return 1;
	}

	public int removeBasketGoods(Long basketNo) {
		return 1;
	}
	
}
