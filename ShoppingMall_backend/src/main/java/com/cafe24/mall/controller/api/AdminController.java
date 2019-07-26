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
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.cafe24.mall.dto.JSONResult;
import com.cafe24.mall.service.AdminService;
import com.cafe24.mall.vo.BigCategoryVo;
import com.cafe24.mall.vo.GoodsImagesVo;
import com.cafe24.mall.vo.GoodsVo;
import com.cafe24.mall.vo.MemberVo;
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
	 * 관리자 상품리스트조회 메소드
	 * 
	 * @return 응답
	 */
	@ApiOperation(value = "관리자 상품리스트조회")
	@ApiImplicitParams({
		@ApiImplicitParam(name = "pageNum", value = "상품목록 페이지 번호", required = true, dataType = "long", defaultValue = "") })
	@GetMapping("/goodslist/{pageNum}")
	public ResponseEntity<JSONResult> goodsList(@PathVariable(value="pageNum") Long pageNum) {
		List<GoodsVo> list = adminService.getGoodsList(pageNum);
		return ResponseEntity.status(HttpStatus.OK).body(JSONResult.success("관리자 상품조회 성공", list));
	}
	
	/**
	 * 관리자 상품상세조회 메소드
	 * 
	 * @return 응답
	 */
	@ApiOperation(value = "관리자 상품상세조회")
	@ApiImplicitParams({
		@ApiImplicitParam(name = "goodsno", value = "상품 번호", required = true, dataType = "long", defaultValue = "") })
	@GetMapping("/goods/{goodsno}")
	public ResponseEntity<JSONResult> goodsInfo(@PathVariable(value="goodsno") Long goodsno) {
		
		GoodsVo goods = adminService.getGoods(goodsno);
		
		return ResponseEntity.status(HttpStatus.OK).body(JSONResult.success("관리자 상품상세조회 성공", goods));
	}
	

	@ApiOperation(value = "관리자 상품수정")
	@ApiImplicitParams({
			@ApiImplicitParam(name = "goodsVo", value = "관리자 상품수정 goodsVo", required = true, dataType = "GoodsVo", defaultValue = "") })
	@PutMapping("/goods")
	public ResponseEntity<JSONResult> modifyGoodsInfo(@RequestBody @Valid GoodsVo goodsvo,BindingResult bindresult) {

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
		return result == 1 ? JSONResult.success("관리자 2차 카테고리 삭제 성공", result) : JSONResult.fail("관리자 2차 카테고리 삭제 실패");
	}
	
	
	@ApiOperation(value = "관리자 회원정보 조회")

	@GetMapping("/member")
	public ResponseEntity<JSONResult> getMemberList(
			@RequestParam String id, 
			@RequestParam String orderdateStart,
			@RequestParam String orderdateEnd) {

			List<MemberVo> memberList = adminService.getMemberList(id,orderdateStart, orderdateEnd);

		return ResponseEntity.status(HttpStatus.OK).body(JSONResult.success("관리자 회원정보 조회 성공", memberList));

	}

	@ApiOperation(value = "관리자 회원 정보 삭제")
	@ApiImplicitParams({
		@ApiImplicitParam(name = "memberNo", value = "삭제할 회원번호", required = true, dataType = "Long", defaultValue = "") })
	@DeleteMapping("/member")
	public JSONResult removeMemberInfo(@RequestBody Long memberNo) {
		int result = adminService.removeMemberInfo(memberNo);
		return result == 1 ? JSONResult.success("관리자 회원 정보 삭제 성공", result) : JSONResult.fail("관리자 회원 정보 삭제 실패");
	}

}
