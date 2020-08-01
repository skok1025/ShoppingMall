package com.cafe24.mall.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.cafe24.mall.dto.JSONResult;
import com.cafe24.mall.service.CouponService;
import com.cafe24.mall.vo.CouponInfoVo;

@Controller
@RequestMapping("/coupon")
public class CouponInfoController {

	@Autowired
	private CouponService couponService;
	
	@GetMapping("/info/add")
	public ResponseEntity<JSONResult> couponInfoAdd(CouponInfoVo vo) {
		vo.setName("coupon name");
		vo.setSale_type("P");
		vo.setSale_value("10");
		
		String info_no = couponService.couponInfoAdd(vo);
		return info_no != null ? ResponseEntity.status(HttpStatus.CREATED).body(JSONResult.success("쿠폰정보 등록 성공", info_no))
				: ResponseEntity.status(HttpStatus.OK).body(JSONResult.success("쿠폰정보 등록 실패",info_no));
	}
}
