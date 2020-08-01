package com.cafe24.mall.repository;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.cafe24.mall.vo.CouponInfoVo;
import com.cafe24.mall.vo.CouponVo;

@Repository
public class CouponDao {

	@Autowired
	private SqlSession sqlsession;

	public int couponInfoInsert(CouponInfoVo vo) {
		return sqlsession.insert("coupon.infoInsert", vo);
	}

	public int couponInsert(CouponVo vo) {
		return sqlsession.insert("coupon.insert", vo);
	}
}
