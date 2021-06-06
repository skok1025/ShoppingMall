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
	public ResponseEntity<JSONResult> couponInfoAdd(CouponInfoVo vo) {
		/*
		vo.setName("coupon name");
		vo.setSale_type("P");
		vo.setSale_value("10");
		*/
		String info_no = couponService.couponInfoAdd(vo);
		return info_no != null ? ResponseEntity.status(HttpStatus.CREATED).body(JSONResult.success("쿠폰정보 등록 성공", info_no))
				: ResponseEntity.status(HttpStatus.OK).body(JSONResult.success("쿠폰정보 등록 실패",info_no));
	}

	@ApiOperation(value = "쿠폰 발급")
	@ApiImplicitParams({
		@ApiImplicitParam(name = "info_no", value = "쿠폰정보번호", required = true, dataType = "String"),
		@ApiImplicitParam(name = "name", value = "쿠폰명", required = true, dataType = "String"),
		@ApiImplicitParam(name = "sale_type", value = "할인형태 (P: 퍼센트 할인, W:정액할인)", required = true, dataType = "String"),
		@ApiImplicitParam(name = "sale_value", value = "할인값", required = true, dataType = "String"),
		@ApiImplicitParam(name = "ins_timestamp", value = "생성일자", required = false, dataType = "String", defaultValue = "now()"),
	})
	@GetMapping("/issue")
	public ResponseEntity<JSONResult> couponAdd(CouponVo vo) {
		System.out.println("Sending message...");
        
		List<String> memberNoList = new ArrayList<String>();
		memberNoList.add("2"); memberNoList.add("3");
		
		vo.setInfo_no("1");
		vo.setName("coupon name");
		vo.setSale_type("P");
		vo.setSale_value("10");
		vo.setIs_used("F");
		vo.setMemberNoList(memberNoList);
		
		
		//List<String> memberNoList = vo.getMemberNoList();
		
		for (String memberNo : memberNoList) {
			vo.setMember_no(memberNo);
			
			String send_message = vo.toString();
			rabbitTemplate.convertAndSend(
					libCouponConst.TOPIC_EXCHANGE, 
					libCouponConst.ROUTING_KEY, 
					send_message
			);
		}
		
		return ResponseEntity.status(HttpStatus.CREATED).body(JSONResult.success("쿠폰 RabbitMq 전송", "1"));
	}
	
	// TODO 하위 쿠폰관련 api 작성 필요
	@ApiOperation(value = "쿠폰 정보 리스트 조회")
	@GetMapping("/info/list")
	public ResponseEntity<JSONResult> couponInfoListRead() {
		List<CouponInfoVo> couponInfoList = null;
		
		return couponInfoList != null ? ResponseEntity.status(HttpStatus.OK).body(JSONResult.success("쿠폰정보 리스트 조회 성공", couponInfoList))
				: ResponseEntity.status(HttpStatus.BAD_REQUEST).body(JSONResult.success("쿠폰정보 리스트 조회 실패",couponInfoList));
	}
	
	@ApiOperation(value = "쿠폰 정보 조회")
	@GetMapping("/info/{info_no}")
	public ResponseEntity<JSONResult> couponInfo(@PathVariable(value = "info_no") String info_no) {
		CouponInfoVo couponInfo = null;
		
		return couponInfo != null ? ResponseEntity.status(HttpStatus.OK).body(JSONResult.success("쿠폰정보 조회 성공", couponInfo))
				: ResponseEntity.status(HttpStatus.BAD_REQUEST).body(JSONResult.success("쿠폰정보 조회 실패",couponInfo));
	}
	
	@ApiOperation(value = "쿠폰 정보 수정")
	@PutMapping("/info/{info_no}")
	public ResponseEntity<JSONResult> couponInfoUpdate(@PathVariable(value = "info_no") String info_no) {
		Integer result = null;	
		
		return result != null ? ResponseEntity.status(HttpStatus.OK).body(JSONResult.success("쿠폰정보 수정 성공", result))
				: ResponseEntity.status(HttpStatus.BAD_REQUEST).body(JSONResult.success("쿠폰정보 수정 실패",result));
	}
	
	@ApiOperation(value = "쿠폰 정보 삭제")
	@DeleteMapping("/info/{info_no}")
	public ResponseEntity<JSONResult> couponInfoDelete(@PathVariable(value = "info_no") String info_no) {
		// is_delete 컬럼 update
		Integer result = null;	
		
		return result != null ? ResponseEntity.status(HttpStatus.OK).body(JSONResult.success("쿠폰정보 삭제 성공", result))
				: ResponseEntity.status(HttpStatus.BAD_REQUEST).body(JSONResult.success("쿠폰정보 삭제 실패",result));
	}
	
	@ApiOperation(value = "쿠폰 발급정보 삭제")
	@DeleteMapping("/{coupon_no}")
	public ResponseEntity<JSONResult> couponDelete(@PathVariable(value = "coupon_no") String coupon_no) {
		// is_delete 컬럼 update
		Integer result = null;	
		
		return result != null ? ResponseEntity.status(HttpStatus.OK).body(JSONResult.success("쿠폰발급정보 삭제 성공", result))
				: ResponseEntity.status(HttpStatus.BAD_REQUEST).body(JSONResult.success("쿠폰발급정보 삭제 실패",result));
	}
	
	@ApiOperation(value = "쿠폰 발급정보 삭제 (해당 쿠폰의 모든 발급정보 삭제)")
	@DeleteMapping("/alldelete/{info_no}")
	public ResponseEntity<JSONResult> couponAllDelete(@PathVariable(value = "info_no") String info_no) {
		// is_delete 컬럼 update
		Integer result = null;	
		
		return result != null ? ResponseEntity.status(HttpStatus.OK).body(JSONResult.success("쿠폰발급정보 전체삭제 성공", result))
				: ResponseEntity.status(HttpStatus.BAD_REQUEST).body(JSONResult.success("쿠폰발급정보 전체삭제 실패",result));
	}
	
}
