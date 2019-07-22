package com.cafe24.mall.controller.api;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cafe24.mall.dto.JSONResult;
import com.cafe24.mall.dto.OrderGoodsDTO;
import com.cafe24.mall.service.OrderService;
import com.cafe24.mall.vo.CancelApplyVo;
import com.cafe24.mall.vo.GoodsVo;
import com.cafe24.mall.vo.OrderGoodsVo;
import com.cafe24.mall.vo.OrderVo;

import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;

@RestController("OrderAPIController")
@RequestMapping("/api/order")
public class OrderController {

	@Autowired
	private OrderService orderService;
	
	@ApiOperation(value = "주문")
	@ApiImplicitParams({
			@ApiImplicitParam(name = "ordervo", value = "고객이 주문한 orderVo", required = true, dataType = "OrderVo", defaultValue = "") })
	@PostMapping("/")
	public ResponseEntity<JSONResult> addOrder(@RequestBody @Valid OrderVo ordervo,BindingResult bindresult) {
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

		int result = orderService.addOrder(ordervo);
		// int result = 1;

		return result == 1 ? ResponseEntity.status(HttpStatus.CREATED).body(JSONResult.success("고객 주문등록 성공", ordervo))
				: ResponseEntity.status(HttpStatus.OK).body(JSONResult.fail("고객 주문등록 실패"));
	}
	
	@ApiOperation(value = "주문코드에 따른상품상세조회")
	@ApiImplicitParams({
		@ApiImplicitParam(name = "orderCode", value = "조회할 주문코드", required = true, dataType = "String", defaultValue = "") })
	@PostMapping("/list")
	public ResponseEntity<JSONResult> viewOrderGoodsList(@RequestBody String orderCode) {
		
		List<OrderGoodsDTO> orderList = orderService.getOrderGoodsList(orderCode);
		// int result = 1;
		
		return ResponseEntity.status(HttpStatus.OK).body(JSONResult.success("주문상세조회  성공", orderList));
	}
	
	@ApiOperation(value = "주문코드에 따른 주문자정보 및 배송지정보 조회 API")
	@ApiImplicitParams({
		@ApiImplicitParam(name = "orderCode", value = "조회할 주문코드", required = true, dataType = "String", defaultValue = "") })
	@PostMapping("/info")
	public ResponseEntity<JSONResult> viewOrderInfo(@RequestBody String orderCode) {
		
		OrderVo vo = orderService.getOrderInfo(orderCode);
		// int result = 1;
		
		return ResponseEntity.status(HttpStatus.OK).body(JSONResult.success("주문코드에 따른 주문자정보 및 배송지정보 조회  성공", vo));
	}
	
	@ApiOperation(value = "주문취소 API")
	@ApiImplicitParams({
		@ApiImplicitParam(name = "CancelApplyVo", value = "주문취소할 냬용 vo", required = true, dataType = "CancelApplyVo", defaultValue = "") })
	@PostMapping("/cancel")
	public ResponseEntity<JSONResult> addCancelApply(@RequestBody CancelApplyVo vo) {
		
		int result = orderService.addCancelApply(vo);
	
		return result == 1 ? ResponseEntity.status(HttpStatus.CREATED).body(JSONResult.success("주문취소신청 성공", result))
				: ResponseEntity.status(HttpStatus.OK).body(JSONResult.fail("주문취소신청 실패"));
	
	}
	
	
	
}
