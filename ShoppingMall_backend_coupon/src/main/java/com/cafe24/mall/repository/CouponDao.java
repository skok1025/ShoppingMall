package com.cafe24.mall.repository;

import java.util.List;
import java.util.Map;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

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

	public List<CouponInfoVo> getCouponInfoList() {
		return sqlsession.selectList("coupon.getInfoList");
	}

	public CouponInfoVo getCouponInfo(String info_no) {
		return sqlsession.selectOne("coupon.getInfo", info_no);
	}

	public Integer updateCouponInfo(CouponInfoVo couponInfoVo) {
		return sqlsession.update("coupon.updateCouponInfo", couponInfoVo);
	}

	public Integer deleteCouponInfo(String info_no) {
		return sqlsession.update("coupon.deleteCouponInfo", info_no);
	}

	public Integer deleteCoupon(Map<String, String> searchMap) {
		return sqlsession.update("coupon.deleteCoupon", searchMap);
	}

	public Integer deleteCouponByInfoNo(String info_no) {
		return sqlsession.update("coupon.deleteCouponByInfoNo", info_no);
	}

	public List<String> getAllMemberNoList() {
		return sqlsession.selectList("coupon.getAllMemberNoList");
	}

	public List<CouponVo> getMemberCouponList(String memberNo) {
		return sqlsession.selectList("coupon.getMemberCouponList", memberNo);
	}
}
