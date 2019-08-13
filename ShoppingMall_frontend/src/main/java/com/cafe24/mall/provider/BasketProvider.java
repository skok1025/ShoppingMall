package com.cafe24.mall.provider;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.web.client.RestTemplate;

import com.cafe24.mall.dto.BasketDTO;
import com.cafe24.mall.dto.BasketItemDTO;
import com.cafe24.mall.dto.BasketProcessDTO;
import com.cafe24.mall.dto.JSONResult;
import com.cafe24.mall.vo.BasketVo;


@Repository
public class BasketProvider {

	@Autowired
	private RestTemplate restTemplate;

	public int addBasket(Long memberNo, Long goodsDetailNo, Integer cnt) {
		
		BasketProcessDTO dto = new BasketProcessDTO();
		dto.setMemberNo(memberNo);
		dto.setGoodsDetailNo(goodsDetailNo);
		dto.setCnt(cnt);
	
		JSONResultInteger jsonresult = 
				restTemplate.postForObject("http://localhost:8099/ShoppingMall_backend/api/basket/member/add", dto, JSONResultInteger.class);
		
		return jsonresult.getData();
	}
	

	public int addBasket(String basketCode, BasketDTO dto) {
		JSONResultInteger jsonresult = 
				restTemplate.postForObject("http://localhost:8099/ShoppingMall_backend/api/basket/nonmember/add?basketCode="+basketCode, dto, JSONResultInteger.class);
		
		
		return jsonresult.getData();
	}
	
	
	public List<BasketItemDTO> selectBasketList(Long memberNo) {
		
		JSONResultBasketList jsonresult = 
				restTemplate.getForObject("http://localhost:8099/ShoppingMall_backend/api/basket/member/list?memberNo="+memberNo, JSONResultBasketList.class);
		
		return jsonresult.getData();
	}
	
	public List<BasketItemDTO> selectBasketList(String basketCode) {
		JSONResultBasketList jsonresult = 
				restTemplate.getForObject("http://localhost:8099/ShoppingMall_backend/api/basket/nonmember/list?basketCode="+basketCode, JSONResultBasketList.class);
		
		return jsonresult.getData();
	}
	
	public Integer selectTotalPrice(Long memberNo) {
		JSONResultInteger jsonresult = 
				restTemplate.getForObject("http://localhost:8099/ShoppingMall_backend/api/basket/member/totalprice?memberNo="+memberNo, JSONResultInteger.class);
		
		return jsonresult.getData();
	}
	
	public int deleteBasket(Long goodsDetailNo, String basketCode) {
		
		restTemplate.delete("http://localhost:8099/ShoppingMall_backend/api/basket/remove/"+goodsDetailNo+"/"+basketCode);
		
		return 1;
	}
	public int updateBasket(Long goodsDetailNo, Long memberNo, Long no, int cnt) {
		
		BasketVo vo = new BasketVo();
		vo.setGoodsDetailNo(goodsDetailNo);
		vo.setBasketCode(memberNo+"");
		vo.setNo(no);
		vo.setCnt(cnt);
		
		restTemplate.put("http://localhost:8099/ShoppingMall_backend/api/basket/modify", vo);
		
		return 1;
	}
	
	public void deleteAllBasket(Long memberNo) {
		restTemplate.delete("http://localhost:8099/ShoppingMall_backend/api/basket/member/allremove?memberNo="+memberNo);
	}
	private static class JSONResultInteger extends JSONResult<Integer>{	}
	private static class JSONResultBasketList extends JSONResult<List<BasketItemDTO>>{	}
	
	

	
	
}
