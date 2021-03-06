package com.cafe24.mall.controller.api;

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
import com.cafe24.mall.dto.OrderDTO;
import com.cafe24.mall.service.AdminService;
import com.cafe24.mall.vo.BigCategoryVo;
import com.cafe24.mall.vo.GoodsVo;
import com.cafe24.mall.vo.MaindisplayCategoryVo;
import com.cafe24.mall.vo.MemberVo;
import com.cafe24.mall.vo.SmallCategoryVo;

import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;


@RestController("AdminAPIController")
@RequestMapping("/api/admin")
/**
 * 관리자 카테고리 관리, 주문 관리, 회원 관리, 진열 관리 담당 컨트롤러 클래스
 * @author 김석현
 *
 */
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

		return result == 1 ? ResponseEntity.status(HttpStatus.CREATED).body(JSONResult.success("관리자 상품등록 성공", result))
				: ResponseEntity.status(HttpStatus.OK).body(JSONResult.fail("관리자 상품등록 실패"));
	}

	/**
	 * 관리자 상품리스트조회 메소드
	 * 
	 * @return 응답
	 */
	@ApiOperation(value = "관리자 상품리스트조회")
	@ApiImplicitParams({
		@ApiImplicitParam(name = "startCol", value = "시작 인덱스", required = true, dataType = "Long", defaultValue = "") })
	@GetMapping("/goodslist/{startCol}")
	public ResponseEntity<JSONResult> goodsList(@PathVariable(value="startCol") Long startCol) {
		List<GoodsVo> list = adminService.getGoodsList(startCol);
		return ResponseEntity.status(HttpStatus.OK).body(JSONResult.success("관리자 상품조회 성공", list));
	}
	
	/**
	 * 관리자 상품상세조회 메소드
	 * 
	 * @return 응답
	 */
	@ApiOperation(value = "관리자 상품상세조회")
	@ApiImplicitParams({
		@ApiImplicitParam(name = "goodsno", value = "상품 번호", required = true, dataType = "Long", defaultValue = "") })
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
	@DeleteMapping("/goods/{goodsNo}")
	public ResponseEntity<JSONResult> removeGoodsInfo(@PathVariable("goodsNo") Long goodsNo) {

		int result = adminService.removeGoodsInfo(goodsNo);
		return result == 1 ? ResponseEntity.status(HttpStatus.OK).body(JSONResult.success("관리자 상품삭제 성공", result))
				: ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(JSONResult.fail("관리자 상품삭제 실패"));
	}

	@ApiOperation(value = "관리자 1차 카테고리 목록조회")
	
	@GetMapping("/category/biglist")
	public ResponseEntity<JSONResult> getBigCategorylist() {
		
		List<BigCategoryVo> bigcategoryList = adminService.getBigCategoryList();
		
		return ResponseEntity.status(HttpStatus.OK).body(JSONResult.success("관리자 카테고리 목록 조회 성공", bigcategoryList));
		
	}
	@ApiOperation(value = "관리자 1차 카테고리에 따른 2차 카테고리 목록조회")
	
	@GetMapping("/category/smalllist/{bigCategoryno}")
	public ResponseEntity<JSONResult> getSmallCategorylist(@PathVariable("bigCategoryno") Long bigCategoryno) {
		
		List<SmallCategoryVo> smallcategoryList = adminService.getSmallCategoryList(bigCategoryno);
		
		return ResponseEntity.status(HttpStatus.OK).body(JSONResult.success("관리자 카테고리 목록 조회 성공", smallcategoryList));
		
	}
	@ApiOperation(value = "관리자 카테고리 목록조회")

	@GetMapping("/category/list")
	public ResponseEntity<JSONResult> getCategorylist() {

		List<BigCategoryVo> categoryList = adminService.getCategoryList();

		return ResponseEntity.status(HttpStatus.OK).body(JSONResult.success("관리자 카테고리 목록 조회 성공", categoryList));

	}
	
	@ApiOperation(value = "관리자 새로운 카테고리 등록 (1,2차 카테고리)")
	@ApiImplicitParams({
			@ApiImplicitParam(name = "vo", value = "관리자가 등록할 카테고리 vo", required = true, dataType = "BigCategoryVo", defaultValue = "") })
	@PostMapping("/category")
	public ResponseEntity<JSONResult> addCategory(@RequestBody @Valid BigCategoryVo vo, BindingResult bindresult) {

		if (bindresult.hasErrors()) {
			List<FieldError> list = bindresult.getFieldErrors();
			String errMsg = "";
			for (FieldError err : list) {
				errMsg += err.getField() + "/";
			}
			errMsg += "오류발생";
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(JSONResult.fail(errMsg));
		}

		int result = adminService.addCatergory(vo);
		return result == 1 ? ResponseEntity.status(HttpStatus.CREATED).body(JSONResult.success("관리자 카테고리 등록 성공", result))
				: ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(JSONResult.fail("관리자 카테고리등록 실패"));
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
			@ApiImplicitParam(name = "no", value = "관리자가 삭제할 1차카테고리 no(번호)", required = true, dataType = "Long", defaultValue = "") })
	@DeleteMapping("/bigcategory/{no}")
	public JSONResult removeBigCategory(@PathVariable("no") Long no) {
		int result = adminService.removeBigCatergory(no);
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
			@ApiImplicitParam(name = "no", value = "관리자가 삭제할 2차카테고리 no(번호)", required = true, dataType = "Long", defaultValue = "") })
	@DeleteMapping("/smallcategory/{no}")
	public JSONResult removeSmallCategory(@PathVariable("no") Long no) {
		int result = adminService.removeSmallCatergory(no);
		return result == 1 ? JSONResult.success("관리자 2차 카테고리 삭제 성공", result) : JSONResult.fail("관리자 2차 카테고리 삭제 실패");
	}
	
	
	@ApiOperation(value = "관리자 회원정보 조회")
	@GetMapping("/member")
	public ResponseEntity<JSONResult> getMemberList(
			@RequestParam String id, 
			@RequestParam String orderdateStart,
			@RequestParam String orderdateEnd,
			@RequestParam(defaultValue = "1") Integer startCol) {

			List<MemberVo> memberList = adminService.getMemberList(id,orderdateStart, orderdateEnd,startCol);

		return ResponseEntity.status(HttpStatus.OK).body(JSONResult.success("관리자 회원정보 조회 성공", memberList));

	}

	@ApiOperation(value = "관리자 회원 정보 삭제")
	@ApiImplicitParams({
		@ApiImplicitParam(name = "memberNo", value = "삭제할 회원번호", required = true, dataType = "Long", defaultValue = "") })
	@DeleteMapping("/member/{memberNo}")
	public JSONResult removeMemberInfo(@PathVariable("memberNo") Long memberNo) {
		int result = adminService.removeMemberInfo(memberNo);
		return result == 1 ? JSONResult.success("관리자 회원 정보 삭제 성공", result) : JSONResult.fail("관리자 회원 정보 삭제 실패");
	}
	
	@ApiOperation(value = "관리자 주문 내역조회")
	@ApiImplicitParams({
		@ApiImplicitParam(name = "orderdateStart", value = "주문 시작날짜", required = true, dataType = "String", defaultValue = ""),
		@ApiImplicitParam(name = "orderdateEnd", value = "주문 마지막날짜", required = true, dataType = "String", defaultValue = ""),		
		@ApiImplicitParam(name = "startCol", value = "시작 인덱스", required = true, dataType = "Integer", defaultValue = ""),		
	})
	@GetMapping("/orderlist")
	public JSONResult adminOrderList(
			@RequestParam(defaultValue = "") String orderdateStart,
			@RequestParam(defaultValue = "") String orderdateEnd,
			@RequestParam(defaultValue = "1") Integer startCol
			) {
		List<OrderDTO> result = adminService.getAdminOrderList(orderdateStart,orderdateEnd,startCol);
		return result != null ? JSONResult.success("관리자 주문 내역조회 성공", result) 
				: JSONResult.fail("관리자 주문 내역조회 실패");
	}
	
	@ApiOperation(value = "관리자 기존 상품상세옵션 isable flag 상태 변경")
	@ApiImplicitParams({
		@ApiImplicitParam(name = "goodsNo", value = "옵션값을 초기화할  상품번호", required = true, dataType = "Long", defaultValue = "") })
	@PutMapping("/goods/option")
	public JSONResult modifyOptionDisable(
				Long goodsNo
			) {
		int result = adminService.modifyOptionDisable(goodsNo);
		return result >= 1 ? JSONResult.success("관리자 기존 상품상세옵션 isable flag 상태 변경 성공", result) 
				: JSONResult.fail("관리자 기존 상품상세옵션 isable flag 상태 변경 실패");
	}
	
	
	@ApiOperation(value = "관리자 진열 카테고리(기본정보) 등록")
	@ApiImplicitParams({
		@ApiImplicitParam(name = "mainDisplayName", value = "추가할 진열 카테고리명", required = true, dataType = "String", defaultValue = "") })
	@PostMapping("/displaycategory")
	public JSONResult addMaindisplayCategory(
			String mainDisplayName
			) {
		int result = adminService.addMaindisplayCategory(mainDisplayName);
		return result >= 1 ? JSONResult.success("관리자 진열 카테고리(기본정보) 등록 성공", result) 
				: JSONResult.fail("관리자 진열 카테고리(기본정보) 등록 실패");
	}
	
	
	@ApiOperation(value = "관리자 진열 카테고리(기본정보) 수정")
	@ApiImplicitParams({
		@ApiImplicitParam(name = "maindisplayNo", value = "수정할 진열 카테고리번호", required = true, dataType = "Long", defaultValue = ""),
		@ApiImplicitParam(name = "mainDisplayName", value = "수정한 진열 카테고리명", required = true, dataType = "String", defaultValue = "") })
	@PutMapping("/displaycategory")
	public JSONResult modifyMaindisplayCategory(
			Long maindisplayNo,
			String mainDisplayName
			) {
		int result = adminService.modifyMaindisplayCategory(maindisplayNo,mainDisplayName);
		return result >= 1 ? JSONResult.success("관리자 진열 카테고리(기본정보) 수정 성공", result) 
				: JSONResult.fail("관리자 진열 카테고리(기본정보) 수정 실패");
	}
	
	@ApiOperation(value = "관리자 진열 카테고리(기본정보) 조회")
	@GetMapping("/displaycategory")
	public JSONResult maindisplayCategoryList() {
		List<MaindisplayCategoryVo> result = adminService.getMaindisplayCategoryList();
		return JSONResult.success("관리자 진열 카테고리(기본정보) 조회 성공", result);
	}

	@ApiOperation(value = "관리자 진열 카테고리(기본정보) 삭제")
	@ApiImplicitParams({
		@ApiImplicitParam(name = "NO", value = "삭제할 진열 카테고리번호", required = true, dataType = "Long", defaultValue = "") })
	@DeleteMapping("/displaycategory")
	public JSONResult deleteMaindisplayCategory(Long no) {
		int result = adminService.DeleteMaindisplayCategory(no);
		return result == 1 ? JSONResult.success("관리자 진열 카테고리(기본정보) 삭제 성공", result) 
				: JSONResult.fail("관리자 진열 카테고리(기본정보) 삭제 실패");
	}
	
	@ApiOperation(value = "관리자 상품 메인진열  등록")
	@ApiImplicitParams({
		@ApiImplicitParam(name = "goodsNo", value = "메인 진열 카테고리에 추가할 상품번호", required = true, dataType = "Long", defaultValue = "") ,
		@ApiImplicitParam(name = "maindisplayCategoryNo", value = "메인 진열 카테고리번호", required = true, dataType = "Long", defaultValue = "") 		
	})
	@PostMapping("/maindisplay")
	public JSONResult addMaindisplay(
			Long goodsNo,
			Long maindisplayCategoryNo
			) {
		int result = adminService.addMaindisplay(goodsNo,maindisplayCategoryNo);
		return result >= 1 ? JSONResult.success("관리자 상품 메인진열 등록 성공", result) 
				: JSONResult.fail("관리자 상품 메인진열 등록 실패");
	}
	
	@ApiOperation(value = "관리자 상품 메인진열  삭제")
	@ApiImplicitParams({
		@ApiImplicitParam(name = "goodsNo", value = "메인 진열 카테고리에 삭제할 상품번호", required = true, dataType = "Long", defaultValue = "") ,
		@ApiImplicitParam(name = "maindisplayCategoryNo", value = "메인 진열 카테고리번호", required = true, dataType = "Long", defaultValue = "") 		
	})
	@DeleteMapping("/maindisplay")
	public JSONResult removeMaindisplay(
			Long goodsNo,
			Long maindisplayCategoryNo
			) {
		int result = adminService.removeMaindisplay(goodsNo,maindisplayCategoryNo);
		return result >= 1 ? JSONResult.success("관리자 상품 메인진열 삭제 성공", result) 
				: JSONResult.fail("관리자 상품 메인진열 삭제 실패");
	}
	
	@ApiOperation(value = "관리자 상품 갯수 조회")
	@GetMapping("/goods/totalcount")
	public JSONResult getGoodsTotalCount() {
		Integer result = adminService.getGoodsTotalCount();
		return result != null ? JSONResult.success("관리자 상품 갯수 조회 성공", result) 
				: JSONResult.fail("관리자 상품 갯수 조회 실패");
	}
	
	@ApiOperation(value = "관리자 회원수 조회")
	@GetMapping("/member/totalcount")
	public JSONResult getMemberTotalCount() {
		Integer result = adminService.getMemberTotalCount();
		return result != null ? JSONResult.success("관리자 회원 수 조회 성공", result) 
				: JSONResult.fail("관리자 회원 수 조회 실패");
	}

	@ApiOperation(value = "관리자 주문수 조회")
	@GetMapping("/order/totalcount")
	public JSONResult getOrderTotalCount() {
		Integer result = adminService.getOrderTotalCount();
		return result != null ? JSONResult.success("관리자 주문 수 조회 성공", result) 
				: JSONResult.fail("관리자 주문 수 조회 실패");
	}
	
	
	
	
	
	

}
