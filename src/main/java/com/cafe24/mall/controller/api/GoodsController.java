package com.cafe24.mall.controller.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.cafe24.mall.dto.JSONResult;
import com.cafe24.mall.service.GoodsService;

import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;

@RestController("GoodsAPIController")
@RequestMapping("/api/goods")
public class GoodsController {

	@Autowired
	private GoodsService goodsService;
	
	/**
	 * 상품 검색
	 * @param kw 검색할 키워드
	 * @param kwkind 검색할 키워드 종류
	 * @return 응답
	 */
	@ApiOperation(value = "상품 검색")
	@ApiImplicitParams({
		@ApiImplicitParam(name = "kw", value = "검색할 키워드", required = true, dataType = "String", defaultValue = ""),
		@ApiImplicitParam(name = "kwkind", value = "검색할 키워드의 종류", required = true, dataType = "String", defaultValue = "") 
	})
	@GetMapping("/search")
	public JSONResult search(@RequestParam String kw,@RequestParam String kwkind) {
		return JSONResult.success("상품 리스트 검색 성공","키워드:"+kw+",키워드 카테고리:"+kwkind);
	}
	
	@ApiOperation(value = "상품 상세정보 조회")
	@ApiImplicitParams({
			@ApiImplicitParam(name = "goodDetailNo", value = "상품 상세번호", required = true, dataType = "Long", defaultValue = "") 
	})
	@GetMapping("/view")
	public JSONResult view(@RequestParam Long goodDetailNo) {
		return JSONResult.success("상품 상세 조회 성공", "상품상세번호:"+goodDetailNo);
	}
}
