package com.cafe24.mall.controller.api;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.cafe24.mall.dto.JSONResult;
import com.cafe24.mall.service.GoodsService;
import com.cafe24.mall.vo.GoodsDetailVo;
import com.cafe24.mall.vo.GoodsImagesVo;
import com.cafe24.mall.vo.GoodsVo;

import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;

@RestController("GoodsAPIController")
@RequestMapping("/api/goods")
public class GoodsController {

	@Autowired
	private GoodsService goodsService;
	
	/**
	 * 상품카테고리를 통한 상품 리스트 탐색
	 * @param smallcategoryNo 2차 카테고리 번호
	 * @return 응답
	 */
	@ApiOperation(value = "상품카테고리 상품리스트 탐색")
	@ApiImplicitParams({
		@ApiImplicitParam(name = "smallcategoryNo", value = "검색할 키워드", required = true, dataType = "String", defaultValue = ""),
	})
	@GetMapping("/category")
	public JSONResult search(@RequestParam Long smallcategoryNo) {
		List<GoodsVo> list = goodsService.getGoodsList(smallcategoryNo);
		return JSONResult.success("상품카테고리 상품리스트 탐색 성공",list);
	}
	/**
	 * 상품 검색
	 * @param kw 검색할 키워드
	 * @param kwkind 검색할 키워드 종류
	 * @return 응답
	 */
	@ApiOperation(value = "상품 검색")
	@ApiImplicitParams({
		@ApiImplicitParam(name = "kw", value = "검색할 키워드", required = true, dataType = "String", defaultValue = ""),
	})
	@GetMapping("/search")
	public JSONResult search(@RequestParam String kw) {
		List<GoodsVo> list = goodsService.getGoodsList(kw);
		return JSONResult.success("상품 리스트 검색 성공",list);
	}
	
	@ApiOperation(value = "상품상세조회")
	@ApiImplicitParams({
			@ApiImplicitParam(name = "goodsNo", value = "상품 번호", required = true, dataType = "Long", defaultValue = "") 
	})
	@GetMapping("/view/{goodsNo}")
	public JSONResult view(@PathVariable(value="goodsNo") Long goodsNo) {
		
		GoodsVo vo = goodsService.getView(goodsNo);
		
		return JSONResult.success("상품 상세 조회 성공", vo);
	}
	
	@ApiOperation(value = "상품 메인이미지 조회")
	@ApiImplicitParams({
		@ApiImplicitParam(name = "goodsNo", value = "상품 번호", required = true, dataType = "Long", defaultValue = "") 
	})
	@GetMapping("/mainimage/{goodsNo}")
	public JSONResult getMainImage(@PathVariable(value="goodsNo") Long goodsNo) {
		
		GoodsImagesVo vo = goodsService.getMainImage(goodsNo);
		
		return JSONResult.success("상품 메인이미지 조회 성공", vo);
	}
	@ApiOperation(value = "상품서브이미지리스트조회")
	@ApiImplicitParams({
			@ApiImplicitParam(name = "goodsNo", value = "상품 번호", required = true, dataType = "Long", defaultValue = "") 
	})
	@GetMapping("/subimagelist/{goodsNo}")
	public JSONResult getSubimagelist(@PathVariable(value="goodsNo") Long goodsNo) {
		
		List<GoodsImagesVo> list = goodsService.getSubImageList(goodsNo);
		
		return JSONResult.success("상품 서브이미지 조회 성공", list);
	}
	
	@ApiOperation(value = "상품상세옵션리스트 조회")
	@ApiImplicitParams({
			@ApiImplicitParam(name = "goodsNo", value = "상품 번호", required = true, dataType = "Long", defaultValue = "") 
	})
	@GetMapping("/goodsdetail/{goodsNo}")
	public JSONResult getGoodsDetailList(@PathVariable(value="goodsNo") Long goodsNo) {
		
		List<GoodsDetailVo> list = goodsService.getGoodsDetailList(goodsNo);
		
		return JSONResult.success("상품상세옵션리스트조회 성공", list);
	}
	
	@ApiOperation(value = "상품 메인진열 조회")
	@ApiImplicitParams({
		@ApiImplicitParam(name = "maindisplayNo", value = "메인진열 카테고리 번호", required = true, dataType = "Long", defaultValue = "") 
})
	@GetMapping("/maindisplay")
	public JSONResult getMainDisplayList(Long maindisplayNo) {
		
		List<GoodsVo> list = goodsService.getMainDisplayList(maindisplayNo);
		
		return JSONResult.success("상품 메인진열 조회 성공", list);
	}
	
	
}
