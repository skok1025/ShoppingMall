package com.cafe24.mall.provider;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.web.client.RestTemplate;

import com.cafe24.mall.dto.BasketItemDTO;
import com.cafe24.mall.dto.JSONResult;
import com.cafe24.mall.vo.CouponVo;

@Repository
public class CouponProvidor {

	@Autowired
	private RestTemplate restTemplate;
	
	public List<CouponVo> getMemberCouponList(Long memberNo) {
		JSONResultMemberCouponList jsonResult = 
				restTemplate.getForObject("http://localhost:8082/mall_coupon/api/coupon/list/" + memberNo, JSONResultMemberCouponList.class);
		return jsonResult.getData();
	}
	

	public void setUsedCoupon(String applyCouponNo) {
		restTemplate.delete("http://localhost:8082/mall_coupon/api/coupon/" + applyCouponNo + "?isUsed=T");
	}
	
	private static class JSONResultMemberCouponList extends JSONResult<List<CouponVo>>{	}
}
