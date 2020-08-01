package com.cafe24.mall.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cafe24.mall.repository.CouponDao;
import com.cafe24.mall.vo.CouponInfoVo;
import com.cafe24.mall.vo.CouponVo;

@Service
public class CouponService {

	@Autowired
	private CouponDao couponDao;
	
	public String couponInfoAdd(CouponInfoVo vo) {
		int result = couponDao.couponInfoInsert(vo);
		return vo.getInfo_no();
	}

	public int couponAdd(CouponVo vo) {
		return couponDao.couponInsert(vo);
	}

}
