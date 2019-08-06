package com.cafe24.mall.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cafe24.mall.provider.AdminProvider;
import com.cafe24.mall.vo.GoodsVo;
import com.cafe24.mall.vo.MemberVo;

@Service
public class AdminService {

	@Autowired
	private AdminProvider adminProvider;

	public List<MemberVo> getMemberList(String id, String orderDateStart, String orderDateEnd) {
		return adminProvider.selectMemberList(id,orderDateStart,orderDateEnd);
	}

	public int removerMember(Long userNo) {
		return adminProvider.deleteMember(userNo);
	}

	public List<GoodsVo> getGoodsList(Long pageNum) {
		return adminProvider.selectGoodsList(pageNum);
	}

	public int removerGoods(Long goodsNo) {
		return adminProvider.deleteGoods(goodsNo);
	}

	
}
