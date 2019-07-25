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
public class BasketController {

	@Autowired
	private BasketService basketService;
	
	@ApiOperation(value = "장바구니에 상품등록 (회원)")
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

	@ApiOperation(value = "장바구니에 상품등록 (비회원)")
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

	@ApiOperation(value = "장바구니 조회")
	@ApiImplicitParams({
		@ApiImplicitParam(name = "basketCode", value = "장바구니 코드", required = true, dataType = "String", defaultValue = "") 
	})
	@GetMapping("/nonmember/list")
	public JSONResult Basketlist(@RequestParam String basketCode) {
		List<BasketDTO> list = basketService.getBasketList(basketCode);
		return JSONResult.success("장바구니 조회 완료", list);
	}
	
	@ApiOperation(value = "장바구니 수정")
	@ApiImplicitParams({
		@ApiImplicitParam(name = "basketvo", value = "수정된 장바구니 정보", required = true, dataType = "String", defaultValue = "") 
	})
	@PutMapping("/modify")
	public JSONResult modifyBasketInfo(@RequestBody BasketVo basketvo) {
		int result = basketService.modifyBasketInfo(basketvo);
		return result == 1 ? JSONResult.success("장바구니 수정 완료",result) : JSONResult.fail("장바구니 수정 실패");	
	}
	
	@DeleteMapping("/remove/{basketNo}")
	public JSONResult removeBasketGoods(@PathVariable("basketNo") Long basketNo) {
		int result = basketService.removeBasketGoods(basketNo);
		return result == 1 ? JSONResult.success("장바구니 상품 삭제 완료",result) : JSONResult.fail("장바구니 상품 삭제 실패");		
	}
	
	
}
