package com.cafe24.mall.controller.api;

import java.util.Arrays;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
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
	 * 
	 * @param vo 관리자 상품등록할 vo 객체
	 * @return 응답
	 */
	@ApiOperation(value = "관리자 상품등록")
	@ApiImplicitParams({
			@ApiImplicitParam(name = "goodsVo", value = "관리자 상품등록 goodsVo", required = true, dataType = "GoodsVo", defaultValue = "") })
	@PostMapping("/goods")
	public ResponseEntity<JSONResult> addGoods(@RequestBody @Valid GoodsVo goodsvo,BindingResult bindresult) {
		// 유효성 검사 실패시
		if (bindresult.hasErrors()) {
			List<FieldError> list = bindresult.getFieldErrors();
			String errMsg = "";
			for (FieldError err : list) {
				errMsg += err.getField() +"-"+err.getDefaultMessage()+"/";
			}
			errMsg += "오류발생";
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(JSONResult.fail(errMsg));
		}

		int result = adminService.addGoods(goodsvo);
		// int result = 1;

		return result == 1 ? ResponseEntity.status(HttpStatus.CREATED).body(JSONResult.success("관리자 상품등록 성공", goodsvo))
				: ResponseEntity.status(HttpStatus.OK).body(JSONResult.fail("관리자 상품등록 실패"));
	}

	/**
	 * 관리자 상품조회 메소드
	 * 
	 * @return 응답
	 */
	@ApiOperation(value = "관리자 상품조회")
	@GetMapping("/goodslist")
	public ResponseEntity<JSONResult> goodsList() {
	
		return ResponseEntity.status(HttpStatus.OK).body(JSONResult.success("관리자 상품조회 성공", null));
	}
	

	@ApiOperation(value = "관리자 상품수정")
	@ApiImplicitParams({
			@ApiImplicitParam(name = "goodsVo", value = "관리자 상품수정 goodsVo", required = true, dataType = "GoodsVo", defaultValue = "") })
	@PutMapping("/goods")
	public ResponseEntity<JSONResult> modifyGoodsInfo(@RequestBody GoodsVo goodsvo) {

		int result = adminService.modifyGoodsInfo(goodsvo);
		return result == 1 ? ResponseEntity.status(HttpStatus.OK).body(JSONResult.success("관리자 상품수정 성공", result))
				: ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(JSONResult.fail("관리자 상품수정 실패"));
	}

	/**
	 * 관리자 상품삭제 메소드
	 * 
	 * @param goodsNo 관리자가 삭제할 상품번호
	 * @return 응답
	 */
	@ApiOperation(value = "관리자 상품삭제")
	@ApiImplicitParams({
			@ApiImplicitParam(name = "goodsNo", value = "관리자가 삭제할 상품번호", required = true, dataType = "Long", defaultValue = "") })
	@DeleteMapping("/goods")
	public ResponseEntity<JSONResult> removeGoodsInfo(@RequestBody Long goodsNo) {

		int result = adminService.removeGoodsInfo(goodsNo);
		return result == 1 ? ResponseEntity.status(HttpStatus.OK).body(JSONResult.success("관리자 상품삭제 성공", result))
				: ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(JSONResult.fail("관리자 상품삭제 실패"));
	}

	@ApiOperation(value = "관리자 카테고리 목록조회")

	@GetMapping("/category/list")
	public ResponseEntity<JSONResult> getCategorylist() {

		List<BigCategoryVo> categoryList = adminService.getCategoryList();

		return ResponseEntity.status(HttpStatus.OK).body(JSONResult.success("관리자 카테고리 목록 조회 성공", categoryList));

	}

	@ApiOperation(value = "관리자 1차 카테고리 등록")
	@ApiImplicitParams({
			@ApiImplicitParam(name = "vo", value = "관리자가 등록할 1차카테고리 vo", required = true, dataType = "BigCategoryVo", defaultValue = "") })
	@PostMapping("/bigcategory")
	public ResponseEntity<JSONResult> addBigCategory(@RequestBody @Valid BigCategoryVo vo, BindingResult bindresult) {

		if (bindresult.hasErrors()) {
			List<FieldError> list = bindresult.getFieldErrors();
			String errMsg = "";
			for (FieldError err : list) {
				errMsg += err.getField() + "/";
			}
			errMsg += "오류발생";
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(JSONResult.fail(errMsg));
		}

		int result = adminService.addBigCatergory(vo);
		return result == 1 ? ResponseEntity.status(HttpStatus.CREATED).body(JSONResult.success("관리자 1차 카테고리 등록 성공", vo))
				: ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(JSONResult.fail("관리자 1차 카테고리등록 실패"));
	}

	@ApiOperation(value = "관리자 1차 카테고리 수정")
	@ApiImplicitParams({
			@ApiImplicitParam(name = "vo", value = "관리자가 수정할 1차카테고리 vo", required = true, dataType = "Long", defaultValue = "") })
	@PutMapping("/bigcategory")
	public ResponseEntity<JSONResult> modifyBigCategory(@RequestBody @Valid BigCategoryVo vo,
			BindingResult bindresult) {
		if (bindresult.hasErrors()) {
			List<FieldError> list = bindresult.getFieldErrors();
			String errMsg = "";
			for (FieldError err : list) {
				errMsg += err.getField() + "/";
			}
			errMsg += "오류발생";
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(JSONResult.fail(errMsg));
		}
		int result = adminService.modifyBigCatergory(vo);
		return result == 1 ? ResponseEntity.status(HttpStatus.OK).body(JSONResult.success("관리자 1차 카테고리 수정 성공", vo))
				: ResponseEntity.status(HttpStatus.OK).body(JSONResult.fail("관리자 1차 카테고리수정 실패"));
	}

	@ApiOperation(value = "관리자 1차 카테고리 삭제")
	@ApiImplicitParams({
			@ApiImplicitParam(name = "vo", value = "관리자가 삭제할 1차카테고리 vo(번호)", required = true, dataType = "Long", defaultValue = "") })
	@DeleteMapping("/bigcategory")
	public JSONResult removeBigCategory(@RequestBody BigCategoryVo vo) {
		int result = adminService.removeBigCatergory(vo);
		return result == 1 ? JSONResult.success("관리자 1차 카테고리 삭제 성공", result) : JSONResult.fail("관리자 1차 카테고리 삭제 실패");
	}

	@ApiOperation(value = "관리자 2차 카테고리 등록")
	@ApiImplicitParams({
			@ApiImplicitParam(name = "vo", value = "관리자가 등록할 2차카테고리 vo", required = true, dataType = "SmallCategoryVo", defaultValue = "") })
	@PostMapping("/smallcategory")
	public ResponseEntity<JSONResult> addSmallCategory(@RequestBody @Valid SmallCategoryVo vo,
			BindingResult bindresult) {
		if (bindresult.hasErrors()) {
			List<FieldError> list = bindresult.getFieldErrors();
			String errMsg = "";
			for (FieldError err : list) {
				errMsg += err.getField() + "/";
			}
			errMsg += "오류발생";
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(JSONResult.fail(errMsg));
		}

		int result = adminService.addSmallCatergory(vo);
		return result == 1 ? ResponseEntity.status(HttpStatus.CREATED).body(JSONResult.success("관리자 2차 카테고리 등록 성공", vo))
				: ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(JSONResult.fail("관리자 2차 카테고리등록 실패"));
	}

	@ApiOperation(value = "관리자 2차 카테고리 수정")
	@ApiImplicitParams({
			@ApiImplicitParam(name = "vo", value = "관리자가 수정할 2차카테고리 vo", required = true, dataType = "SmallCategoryVo", defaultValue = "") })
	@PutMapping("/smallcategory")
	public ResponseEntity<JSONResult> modifySmallCategory(@RequestBody @Valid SmallCategoryVo vo,
			BindingResult bindresult) {
		if (bindresult.hasErrors()) {
			List<FieldError> list = bindresult.getFieldErrors();
			String errMsg = "";
			for (FieldError err : list) {
				errMsg += err.getDefaultMessage() + "/";
			}
			errMsg += "오류발생";
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(JSONResult.fail(errMsg));
		}
		int result = adminService.modifySmallCatergory(vo);
		return result == 1 ? ResponseEntity.status(HttpStatus.OK).body(JSONResult.success("관리자 2차 카테고리 수정 성공", vo))
				: ResponseEntity.status(HttpStatus.OK).body(JSONResult.fail("관리자 2차 카테고리수정 실패"));
	}

	@ApiOperation(value = "관리자 2차 카테고리 삭제")
	@ApiImplicitParams({
			@ApiImplicitParam(name = "vo", value = "관리자가 삭제할 2차카테고리 vo(번호)", required = true, dataType = "Long", defaultValue = "") })
	@DeleteMapping("/smallcategory")
	public JSONResult removeSmallCategory(@RequestBody SmallCategoryVo vo) {
		int result = adminService.removeSmallCatergory(vo);
		return result == 1 ? JSONResult.success("c", vo) : JSONResult.fail("관리자 2차 카테고리 삭제 실패");
	}

}
