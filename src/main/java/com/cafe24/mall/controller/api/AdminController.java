package com.cafe24.mall.controller.api;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cafe24.mall.dto.JSONResult;
import com.cafe24.mall.service.AdminService;
import com.cafe24.mall.vo.BigCategoryVo;
import com.cafe24.mall.vo.GoodsImagesVo;
import com.cafe24.mall.vo.GoodsVo;
import com.cafe24.mall.vo.SmallCategoryVo;

import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;

@RestController("AdminAPIController")
@RequestMapping("/api/admin")
public class AdminController {

	@Autowired
	private AdminService adminService;
	
	/**
	 * 관리자 상품등록 메소드
	 * @param vo 관리자 상품등록할 vo 객체
	 * @return 응답
	 */
	@ApiOperation(value = "관리자 상품등록")
	@ApiImplicitParams({
			@ApiImplicitParam(name = "goodsVo", value = "관리자 상품등록 goodsVo", required = true, dataType = "GoodsVo", defaultValue = "") 
	})
	@PostMapping("/goods")
	public JSONResult addGoods(@RequestBody GoodsVo goodsvo) {
		
		int result = adminService.addGoods(goodsvo);	
		return result==1 ? JSONResult.success("관리자 상품등록 성공", goodsvo) : JSONResult.fail("관리자 상품등록 실패");
	}
	
	/**
	 * 관리자 상품조회 메소드
	 * @return 응답
	 */
	@ApiOperation(value = "관리자 상품조회")
	@GetMapping("/goodslist")
	public JSONResult goodsList() {
		return JSONResult.success("관리자 상품조회 성공", null);
	}
	
	
	
	@ApiOperation(value = "관리자 상품수정")
	@ApiImplicitParams({
			@ApiImplicitParam(name = "goodsVo", value = "관리자 상품수정 goodsVo", required = true, dataType = "GoodsVo", defaultValue = "") 
	})
	@PutMapping("/goods")
	public JSONResult modifyGoodsInfo(@RequestBody GoodsVo goodsvo) {
		
		int result = adminService.modifyGoodsInfo(goodsvo);
		return result==1 ? JSONResult.success("관리자 상품수정 성공", result) : JSONResult.fail("관리자 상품수정 실패");
	}
	
	
	/**
	 * 관리자 상품삭제 메소드
	 * @param goodsNo 관리자가 삭제할 상품번호
	 * @return 응답
	 */
	@ApiOperation(value = "관리자 상품삭제")
	@ApiImplicitParams({
			@ApiImplicitParam(name = "goodsNo", value = "관리자가 삭제할 상품번호", required = true, dataType = "Long", defaultValue = "") 
	})
	@DeleteMapping("/goods")
	public JSONResult removeGoodsInfo(@RequestBody Long goodsNo) {
		
		int result = adminService.removeGoodsInfo(goodsNo);
		return result==1 ? JSONResult.success("관리자 상품삭제 성공", result) : JSONResult.fail("관리자 상품삭제 실패");
	}
	
	@ApiOperation(value = "관리자 1차 카테고리 등록")
	@ApiImplicitParams({
			@ApiImplicitParam(name = "vo", value = "관리자가 등록할 1차카테고리 vo", required = true, dataType = "Long", defaultValue = "") 
	})
	@PostMapping("/bigcategory")
	public JSONResult addBigCategory(@RequestBody BigCategoryVo vo) {
		int result = adminService.addBigCatergory(vo); 
		return result==1 ? JSONResult.success("관리자 1차 카테고리 등록 성공", vo) : JSONResult.fail("관리자 1차 카테고리등록 실패");
	}
	
	
	@ApiOperation(value = "관리자 1차 카테고리 수정")
	@ApiImplicitParams({
		@ApiImplicitParam(name = "vo", value = "관리자가 수정할 1차카테고리 vo", required = true, dataType = "Long", defaultValue = "") 
	})
	@PutMapping("/bigcategory")
	public JSONResult modifyBigCategory(@RequestBody BigCategoryVo vo) {
		int result = adminService.modifyBigCatergory(vo); 
		return result==1 ? JSONResult.success("관리자 1차 카테고리 수정 성공", vo) : JSONResult.fail("관리자 1차 카테고리수정 실패");
	}
	

	@ApiOperation(value = "관리자 1차 카테고리 삭제")
	@ApiImplicitParams({
		@ApiImplicitParam(name = "vo", value = "관리자가 삭제할 1차카테고리 vo(번호)", required = true, dataType = "Long", defaultValue = "") 
	})
	@DeleteMapping("/bigcategory")
	public JSONResult removeBigCategory(@RequestBody BigCategoryVo vo) {
		int result = adminService.removeBigCatergory(vo); 
		return result==1 ? JSONResult.success("관리자 1차 카테고리 삭제 성공", result) : JSONResult.fail("관리자 1차 카테고리 삭제 실패");
	}
	
	
	
}
