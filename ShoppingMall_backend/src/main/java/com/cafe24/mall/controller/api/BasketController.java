package com.cafe24.mall.controller.api;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.cafe24.mall.dto.BasketDTO;
import com.cafe24.mall.dto.JSONResult;
import com.cafe24.mall.service.BasketService;
import com.cafe24.mall.vo.BasketVo;
import com.cafe24.mall.vo.MemberVo;

import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;

@RestController("BasketAPIController")
@RequestMapping("/api/basket")
/**
 * 장바구니 관련 컨트롤러 클래스
 * @author 김석현
 *
 */
public class BasketController {

	@Autowired
	private BasketService basketService;
	
	@ApiOperation(value = "장바구니추가 (회원)")
	@ApiImplicitParams({
		
		@ApiImplicitParam(name = "memberNo", value = "회원번호", required = true, dataType = "Long", defaultValue = ""), 
		@ApiImplicitParam(name = "goodsDetailNo", value = "장바구니에 등록할 상품상세번호", required = true, dataType = "Long", defaultValue = ""), 
		@ApiImplicitParam(name = "cnt", value = "장바구니에 등록할 상품갯수", required = true, dataType = "Integer", defaultValue = "1") 
		})
	@PostMapping("/member/add")
	public ResponseEntity<JSONResult> addBasket(@RequestParam Long memberNo ,@RequestParam Long goodsDetailNo, @RequestParam Integer cnt) {
		int result = basketService.addMemberBasket(memberNo,goodsDetailNo,cnt);
		return result == 1 ? ResponseEntity.status(HttpStatus.CREATED).body(JSONResult.success("회원 장바구니 상품등록 완료",result)) 
				: ResponseEntity.status(HttpStatus.OK).body(JSONResult.fail("회원 장바구니 상품등록 실패"));
	}

	@ApiOperation(value = "장바구니추가 (비회원)")
	@ApiImplicitParams({
		
		@ApiImplicitParam(name = "basketCode", value = "장바구니코드", required = true, dataType = "String", defaultValue = ""), 
		@ApiImplicitParam(name = "goodsDetailNo", value = "장바구니에 등록할 상품상세번호", required = true, dataType = "Long", defaultValue = ""), 
		@ApiImplicitParam(name = "cnt", value = "장바구니에 등록할 상품갯수", required = true, dataType = "Integer", defaultValue = "1") 
	})
	@PostMapping("/nonmember/add")
	public ResponseEntity<JSONResult> addBasket(@RequestParam String basketCode ,@RequestParam Long goodsDetailNo, @RequestParam Integer cnt) {
		int result = basketService.addNonMemberBasket(basketCode,goodsDetailNo,cnt);
		return result == 1 ? ResponseEntity.status(HttpStatus.CREATED).body(JSONResult.success("비회원 장바구니 상품등록 완료",result)) 
				: ResponseEntity.status(HttpStatus.OK).body(JSONResult.fail("비회원 장바구니 상품등록 실패"));
	}

	@ApiOperation(value = "장바구니 조회 (회원)")
	@ApiImplicitParams({
		@ApiImplicitParam(name = "memberNo", value = "회원번호", required = true, dataType = "Long", defaultValue = "") 
	})
	@GetMapping("/member/list")
	public JSONResult Basketlist(@RequestParam Long memberNo) {
		List<BasketDTO> list = basketService.getBasketList(memberNo);
		return JSONResult.success("장바구니 조회 (회원) 완료", list);
	}
	@ApiOperation(value = "장바구니 조회 (비회원)")
	@ApiImplicitParams({
		@ApiImplicitParam(name = "basketCode", value = "장바구니 코드", required = true, dataType = "String", defaultValue = "") 
	})
	@GetMapping("/nonmember/list")
	public JSONResult Basketlist(@RequestParam String basketCode) {
		List<BasketDTO> list = basketService.getBasketList(basketCode);
		return JSONResult.success("장바구니 조회 완료", list);
	}
	
	@ApiOperation(value = "장바구니수량 수정")
	@ApiImplicitParams({
		@ApiImplicitParam(name = "basketvo", value = "수정된 장바구니 정보 (tblBasket.no, cnt)", required = true, dataType = "String", defaultValue = "") 
	})
	@PutMapping("/modify")
	public JSONResult modifyBasketInfo(@RequestBody BasketVo basketvo) {
		int result = basketService.modifyBasketInfo(basketvo);
		return result == 1 ? JSONResult.success("장바구니수량 수정 완료",result) : JSONResult.fail("장바구니수량 수정 실패");	
	}
	
	@ApiOperation(value = "장바구니특정상품 삭제")	
	@DeleteMapping("/remove")
	public JSONResult removeBasketGoods(@RequestParam Long basketNo) {
		int result = basketService.removeBasketGoods(basketNo);
		return result == 1 ? JSONResult.success("장바구니 상품 삭제 완료",result) : JSONResult.fail("장바구니 상품 삭제 실패");		
	}

	@ApiOperation(value = "장바구니상품전체삭제(회원)")	
	@DeleteMapping("/member/allremove")
	public JSONResult allremoveMemberBasketGoods(@RequestParam Long memberNo) {
		int result = basketService.allremoveBasketGoods(memberNo);
		return result >= 1 ? JSONResult.success("장바구니상품전체삭제 완료",result) 
				: JSONResult.fail("장바구니상품전체 삭제 실패 (삭제할 장바구니가 없음)");		
	}

	@ApiOperation(value = "장바구니상품전체삭제(비회원)")	
	@DeleteMapping("/nonmember/allremove")
	public JSONResult allremoveNonMemberBasketGoods(@RequestParam String basketCode) {
		int result = basketService.allremoveBasketGoods(basketCode);
		return result >= 1 ? JSONResult.success("장바구니상품전체삭제 완료",result) 
				: JSONResult.fail("장바구니상품전체 삭제 실패 (삭제할 장바구니가 없음)");		
	}
	
	
	
	
}
