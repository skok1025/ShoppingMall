package com.cafe24.mall.controller.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cafe24.mall.dto.JSONResult;
import com.cafe24.mall.service.GoodsService;

@RestController("GoodsAPIController")
@RequestMapping("/api/goods")
public class GoodsController {

	@Autowired
	private GoodsService goodsService;
	
	@GetMapping("/search")
	public JSONResult search(String kw,String kwkind) {
		return JSONResult.success("상품 리스트 검색 성공","키워드:"+kw+",키워드 카테고리:"+kwkind);
	}
	
	@GetMapping("/view")
	public JSONResult view(Long goodDetailNo) {
		return JSONResult.success("상품 상세 조회 성공", "상품상세번호:"+goodDetailNo);
	}
}
