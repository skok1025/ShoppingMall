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
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.cafe24.mall.dto.ChangeApplyDTO;
import com.cafe24.mall.dto.JSONResult;
import com.cafe24.mall.dto.OrderDTO;
import com.cafe24.mall.dto.OrderGoodsDTO;
import com.cafe24.mall.service.OrderService;
import com.cafe24.mall.vo.CancelApplyVo;
import com.cafe24.mall.vo.ChangeApplyVo;
import com.cafe24.mall.vo.OrderVo;

import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;

@RestController("OrderAPIController")
@RequestMapping("/api/order")
/**
 * 주문 관련 컨트롤러 클래스
 * @author 김석현
 *
 */
public class OrderController {

	@Autowired
	private OrderService orderService;
	
	
	
	
	@ApiOperation(value = "주문")
	@ApiImplicitParams({
			@ApiImplicitParam(name = "ordervo", value = "고객이 주문한 orderVo", required = true, dataType = "OrderVo", defaultValue = "") })
	@PostMapping("/")
	public ResponseEntity<JSONResult> addOrder(@RequestBody /* @Valid */ OrderVo ordervo,BindingResult bindresult) {
		
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
		
		// 주문금액 검증
		System.out.println("주문금액 검증 필요!!");
        System.out.println(ordervo);
		
		int result = orderService.addOrder(ordervo);

		return result == 1 ? ResponseEntity.status(HttpStatus.CREATED).body(JSONResult.success("고객 주문등록 성공", result))
				: ResponseEntity.status(HttpStatus.OK).body(JSONResult.fail("고객 주문등록 실패"));
	}
	
	/**
	 * 주문내역조회
	 * @param memberNo 주문내역을 확인할 회원번호
	 * @return 응답
	 */
	@ApiOperation(value = "회원 주문내역조회")
	@ApiImplicitParams({
		@ApiImplicitParam(name = "memberNo", value = "주문내역을 확인할 회원번호", required = true, dataType = "Long", defaultValue = "") })
	@PostMapping("/list")
	//@ExceptionHandler(DataIntegrityViolationException.class)
	public ResponseEntity<JSONResult> getOrderList(@RequestBody Long memberNo) {
		
		
		List<OrderDTO> result = orderService.getOrderList(memberNo);
		// int result = 1;
		
		return ResponseEntity.status(HttpStatus.OK).body(JSONResult.success("고객 주문내역조회 성공", result));
				
	}
	
	
	
	@ApiOperation(value = "주문코드에 따른상품상세조회")
	@ApiImplicitParams({
		@ApiImplicitParam(name = "orderCode", value = "조회할 주문코드", required = true, dataType = "String", defaultValue = "") })
	@PostMapping("/goodslist")
	public ResponseEntity<JSONResult> viewOrderGoodsList(@RequestBody String orderCode) {
		
		List<OrderGoodsDTO> orderList = orderService.getOrderGoodsList(orderCode);
		// int result = 1;
		
		return ResponseEntity.status(HttpStatus.OK).body(JSONResult.success("주문코드에 따른 상품상세조회 성공", orderList));
	}
	@ApiOperation(value = "주문 회원번호에 따른상품상세조회")
	@PostMapping("/membergoodslist")
	public ResponseEntity<JSONResult> viewOrderGoodsList(@RequestBody Long memberNo) {
		
		List<OrderGoodsDTO> orderList = orderService.getOrderGoodsList(memberNo);
		// int result = 1;
		
		return ResponseEntity.status(HttpStatus.OK).body(JSONResult.success("주문 회원번호에 따른 상품상세조회 성공", orderList));
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
	
	@ApiOperation(value = "주문 교환신청 API")
	@ApiImplicitParams({
		@ApiImplicitParam(name = "ChangeApplyVo", value = "주문취소할 냬용 vo", required = true, dataType = "CancelApplyVo", defaultValue = "") })
	@PostMapping("/change")
	public ResponseEntity<JSONResult> addChangeApply(@RequestBody ChangeApplyVo vo) {
		
		int result = orderService.addChangeApply(vo);
		
		return result == 1 ? ResponseEntity.status(HttpStatus.CREATED).body(JSONResult.success("주문 교환신청 성공", result))
				: ResponseEntity.status(HttpStatus.OK).body(JSONResult.fail("주문 교환신청 실패"));
		
	}
	
	@ApiOperation(value = "주문 교환신청내역 조회 API")
	@ApiImplicitParams({
		@ApiImplicitParam(name = "orderCode", value = "주문 교환신청내역을 조회할 주문코드", required = true, dataType = "CancelApplyVo", defaultValue = "") })
	@GetMapping("/change/{orderCode}")
	public ResponseEntity<JSONResult> ChangeApplyList(@PathVariable(value = "orderCode") String orderCode) {
		
		List<ChangeApplyDTO> result = orderService.getChangeApplyList(orderCode);
		
		return ResponseEntity.status(HttpStatus.OK).body(JSONResult.success("주문 교환신청내역 조회 성공", result));
		
	}
	
	@ApiOperation(value = "주문 교환신청 취소 API")
	@ApiImplicitParams({
		@ApiImplicitParam(name = "changeApplyNo", value = "주문 교환신청을 취소할 주문 교환신청번호", required = true, dataType = "CancelApplyVo", defaultValue = "") })
	@DeleteMapping("/change")
	public ResponseEntity<JSONResult> CancelChangeApply(@RequestBody Long changeApplyNo) {
		
		int result = orderService.cancelChangeApply(changeApplyNo);
		
		return result == 1 ? ResponseEntity.status(HttpStatus.OK).body(JSONResult.success("주문 교환신청취소 성공", result))
				: ResponseEntity.status(HttpStatus.OK).body(JSONResult.fail("쭈문 꾜환씬청 씰퍠"));
		
	}
	
	@ApiOperation(value = "주문 상세정보 조회 API")
	@ApiImplicitParams({
		@ApiImplicitParam(name = "orderCode", value = "주문상세조회 할 주문코드", required = true, dataType = "String", defaultValue = ""),
		@ApiImplicitParam(name = "info", value = "조회할 내용 (order_price|order_calc_info)", required = true, dataType = "String", defaultValue = "")})
	@GetMapping("/detail/{orderCode}")
	public ResponseEntity<JSONResult> getOrderDetailInfo(
			@PathVariable(value = "orderCode") String orderCode,
			@RequestParam String info) {
		String result = orderService.getOrderDetailInfo(orderCode, info);
		
		return ResponseEntity.status(HttpStatus.OK).body(JSONResult.success("주문 상세정보 조회 성공", result));
	}
	
	
	
}
