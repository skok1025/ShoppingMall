package com.cafe24.mall.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cafe24.mall.provider.CouponProvidor;
import com.cafe24.mall.vo.CouponVo;

@Service
public class CouponService {
	
	@Autowired
	private CouponProvidor couponProvidor;

	public List<CouponVo> getMemberCouponList(Long memberNo) {
		return couponProvidor.getMemberCouponList(memberNo);
	}

	public void setUsedCoupon(String applyCouponNo) {
		couponProvidor.setUsedCoupon(applyCouponNo);
	}

}
