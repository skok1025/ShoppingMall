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
import org.springframework.web.bind.annotation.RestController;

import com.cafe24.mall.dto.JSONResult;
import com.cafe24.mall.service.OrderService;
import com.cafe24.mall.vo.GoodsVo;
import com.cafe24.mall.vo.OrderVo;

import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;

@RestController("OrderAPIController")
public class OrderController {

	@Autowired
	private OrderService orderSevice;
	
	@ApiOperation(value = "주문")
	@ApiImplicitParams({
			@ApiImplicitParam(name = "ordervo", value = "고객이 주문한 orderVo", required = true, dataType = "GoodsVo", defaultValue = "") })
	@PostMapping("/orders")
	public ResponseEntity<JSONResult> addGoods(@RequestBody @Valid OrderVo ordervo,BindingResult bindresult) {
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
}
