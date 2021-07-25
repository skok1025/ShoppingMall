package com.cafe24.mall.controller.api;

import java.util.ArrayList;
import java.util.List;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
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
import org.springframework.web.bind.annotation.RestController;

import com.cafe24.mall.dto.JSONResult;
import com.cafe24.mall.lib.libCouponConst;
import com.cafe24.mall.service.CouponService;
import com.cafe24.mall.vo.CouponInfoVo;
import com.cafe24.mall.vo.CouponVo;

import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;

@RestController("CouponApiController")
@RequestMapping("/api/coupon")
public class CouponController {
	@Autowired
	private CouponService couponService;
	
	private final RabbitTemplate rabbitTemplate;
	
	public CouponController(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }
	
	@ApiOperation(value = "쿠폰 정보 등록")
	@ApiImplicitParams({
		@ApiImplicitParam(name = "info_no", value = "쿠폰정보번호", required = false, dataType = "String", defaultValue = "auto increment"),
		@ApiImplicitParam(name = "name", value = "쿠폰명", required = true, dataType = "String", defaultValue = ""),
		@ApiImplicitParam(name = "sale_type", value = "할인형태 (P: 퍼센트 할인, W:정액할인)", required = true, dataType = "String", defaultValue = ""),
		@ApiImplicitParam(name = "sale_value", value = "할인값", required = true, dataType = "String", defaultValue = ""),
		@ApiImplicitParam(name = "ins_timestamp", value = "생성일자", required = false, dataType = "String", defaultValue = "now()"),
	})
	@PostMapping("/info")
	public ResponseEntity<JSONResult> couponInfoAdd(@RequestBody CouponInfoVo vo) {
		/*
		vo.setName("coupon name");
		vo.setSale_type("P");
		vo.setSale_value("10");
		*/
		Integer result = couponService.couponInfoAdd(vo);
		System.out.println(result);
		return result != 0 ? ResponseEntity.status(HttpStatus.CREATED).body(JSONResult.success("쿠폰정보 등록 성공", result))
				: ResponseEntity.status(HttpStatus.OK).body(JSONResult.fail("쿠폰정보 등록 실패"));
	}

	@ApiOperation(value = "쿠폰 발급")
	@ApiImplicitParams({
		@ApiImplicitParam(name = "info_no", value = "쿠폰정보번호", required = true, dataType = "String"),
		@ApiImplicitParam(name = "name", value = "쿠폰명", required = true, dataType = "String"),
		@ApiImplicitParam(name = "sale_type", value = "할인형태 (P: 퍼센트 할인, W:정액할인)", required = true, dataType = "String"),
		@ApiImplicitParam(name = "sale_value", value = "할인값", required = true, dataType = "String"),
		@ApiImplicitParam(name = "ins_timestamp", value = "생성일자", required = false, dataType = "String", defaultValue = "now()"),
	})
	@PostMapping("/issue")
	public ResponseEntity<JSONResult> couponAdd(@RequestBody CouponVo vo) {
		System.out.println("Sending message...");
        
		// 초기 버전 발급은 전체 멤버를 대상으로 발급한다.
		List<String> memberNoList = couponService.getAllMemberNoList();
		
//		vo.setInfo_no("1");
//		vo.setName("coupon name");
//		vo.setSale_type("P");
//		vo.setSale_value("10");
//		vo.setIs_used("F");
		vo.setMemberNoList(memberNoList);
		
		//List<String> memberNoList = vo.getMemberNoList();
		
		for (String memberNo : memberNoList) {
			vo.setMember_no(memberNo);
			
			String send_message = vo.toString();
			rabbitTemplate.convertAndSend(
					libCouponConst.TOPIC_EXCHANGE, 
					libCouponConst.CREATE_ROUTING_KEY, 
					send_message
			);
		}
		
		return ResponseEntity.status(HttpStatus.CREATED).body(JSONResult.success("쿠폰 RabbitMq 전송", "1"));
	}
	
	// TODO 하위 쿠폰관련 api 작성 필요
	@ApiOperation(value = "쿠폰 정보 리스트 조회")
	@GetMapping("/info/list")
	public ResponseEntity<JSONResult> couponInfoListRead() {
		List<CouponInfoVo> couponInfoList = couponService.getCouponInfoList();
		
		return couponInfoList != null ? ResponseEntity.status(HttpStatus.OK).body(JSONResult.success("쿠폰정보 리스트 조회 성공", couponInfoList))
				: ResponseEntity.status(HttpStatus.BAD_REQUEST).body(JSONResult.success("쿠폰정보 리스트 조회 실패",couponInfoList));
	}
	
	@ApiOperation(value = "쿠폰 정보 조회")
	@ApiImplicitParams(
			@ApiImplicitParam(name = "info_no", value = "조회할 쿠폰 정보 번호", required = true, dataType = "String")
	)
	@GetMapping("/info/{info_no}")
	public ResponseEntity<JSONResult> couponInfo(@PathVariable(value = "info_no") String info_no) {
		CouponInfoVo couponInfo = couponService.getCouponInfo(info_no);
		
		return couponInfo != null ? ResponseEntity.status(HttpStatus.OK).body(JSONResult.success("쿠폰정보 조회 성공", couponInfo))
				: ResponseEntity.status(HttpStatus.BAD_REQUEST).body(JSONResult.success("쿠폰정보 조회 실패",couponInfo));
	}
	
	@ApiOperation(value = "쿠폰 정보 수정")
	@ApiImplicitParams({
		@ApiImplicitParam(name = "info_no", value = "수정할 쿠폰 정보 번호", required = true, dataType = "String", example = "1"),
		@ApiImplicitParam(name = "name", value = "수정될 쿠폰명", required = false, dataType = "String", example = "20퍼센트 할인 쿠폰"),
		@ApiImplicitParam(name = "sale_type", value = "수정될 쿠폰타입 (P: 퍼센트 할인, W: 정액할인)", required = false, dataType = "String", example = "P"),
		@ApiImplicitParam(name = "sale_value", value = "수정될 쿠폰값", required = false, dataType = "String", example = "20")
	})
	@PutMapping("/info/{info_no}")
	public ResponseEntity<JSONResult> couponInfoUpdate(@PathVariable(value = "info_no") String info_no, CouponInfoVo couponInfoVo) {
		System.out.println(couponInfoVo);
		Integer result = couponService.updateCouponInfo(info_no, couponInfoVo);	
		
		return result != null ? ResponseEntity.status(HttpStatus.OK).body(JSONResult.success("쿠폰정보 수정 성공", result))
				: ResponseEntity.status(HttpStatus.BAD_REQUEST).body(JSONResult.success("쿠폰정보 수정 실패",result));
	}
	
	@ApiOperation(value = "쿠폰 정보 삭제")
	@ApiImplicitParams({
		@ApiImplicitParam(name = "info_no", value = "삭제할 쿠폰 정보 번호", required = true, dataType = "String")
	})
	@DeleteMapping("/info/{info_no}")
	public ResponseEntity<JSONResult> couponInfoDelete(@PathVariable(value = "info_no") String info_no) {
		// is_delete 컬럼 update
		Integer result = couponService.deleteCouponInfo(info_no);	
		
		return result != null ? ResponseEntity.status(HttpStatus.OK).body(JSONResult.success("쿠폰정보 삭제 성공", result))
				: ResponseEntity.status(HttpStatus.BAD_REQUEST).body(JSONResult.success("쿠폰정보 삭제 실패",result));
	}
	
	@ApiOperation(value = "쿠폰 발급정보 삭제")
	@ApiImplicitParams({
		@ApiImplicitParam(name = "coupon_no", value = "삭제할 쿠폰 번호", required = true, dataType = "String")
	})
	@DeleteMapping("/{coupon_no}")
	public ResponseEntity<JSONResult> couponDelete(@PathVariable(value = "coupon_no") String coupon_no) {
		// is_delete 컬럼 update
		Integer result = couponService.deleteCoupon(coupon_no);	
		
		return result != null ? ResponseEntity.status(HttpStatus.OK).body(JSONResult.success("쿠폰발급정보 삭제 성공", result))
				: ResponseEntity.status(HttpStatus.BAD_REQUEST).body(JSONResult.success("쿠폰발급정보 삭제 실패",result));
	}
	
	@ApiOperation(value = "쿠폰 발급정보 삭제 (해당 쿠폰의 모든 발급정보 삭제)")
	@ApiImplicitParams({
		@ApiImplicitParam(name = "info_no", value = "발급정보를 삭제할 쿠폰 정보 번호", required = true, dataType = "String")
	})
	@DeleteMapping("/alldelete/{info_no}")
	public ResponseEntity<JSONResult> couponAllDelete(@PathVariable(value = "info_no") String info_no) {
		// is_delete 컬럼 update
		Integer result = couponService.deleteCouponByInfoNo(info_no);	
		
		return result != null ? ResponseEntity.status(HttpStatus.OK).body(JSONResult.success("쿠폰발급정보 전체삭제 성공", result))
				: ResponseEntity.status(HttpStatus.BAD_REQUEST).body(JSONResult.success("쿠폰발급정보 전체삭제 실패",result));
	}
	
}
