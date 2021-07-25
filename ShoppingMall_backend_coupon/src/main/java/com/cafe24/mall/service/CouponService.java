package com.cafe24.mall.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cafe24.mall.repository.CouponDao;
import com.cafe24.mall.vo.CouponInfoVo;
import com.cafe24.mall.vo.CouponVo;

@Service
public class CouponService {

	@Autowired
	private CouponDao couponDao;
	
	public Integer couponInfoAdd(CouponInfoVo vo) {
		int result = couponDao.couponInfoInsert(vo);
		return result;
	}

	public int couponAdd(CouponVo vo) {
		return couponDao.couponInsert(vo);
	}

	/**
	 * 쿠폰 정보 리스트 얻어오기
	 * @return 쿠폰 정보 전체 리스트
	 */
	public List<CouponInfoVo> getCouponInfoList() {
		return couponDao.getCouponInfoList();
	}

	/**
	 * 쿠폰 정보 얻어오기
	 * @param info_no 쿠폰 정보 번호
	 * @return 쿠폰 정보
	 */
	public CouponInfoVo getCouponInfo(String info_no) {
		return couponDao.getCouponInfo(info_no);
	}

	public Integer updateCouponInfo(String info_no, CouponInfoVo couponInfoVo) {
		System.out.println(info_no);
		couponInfoVo.setInfo_no(info_no);
		return couponDao.updateCouponInfo(couponInfoVo);
	}

	public Integer deleteCouponInfo(String info_no) {
		return couponDao.deleteCouponInfo(info_no);
	}

	public Integer deleteCoupon(String coupon_no) {
		return couponDao.deleteCoupon(coupon_no);
	}

	public Integer deleteCouponByInfoNo(String info_no) {
		return couponDao.deleteCouponByInfoNo(info_no);
	}

	public List<String> getAllMemberNoList() {
		return couponDao.getAllMemberNoList();
	}

}
