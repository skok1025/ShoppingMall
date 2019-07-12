package com.cafe24.mall.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cafe24.mall.repository.AdminDao;
import com.cafe24.mall.vo.GoodsVo;

@Service
public class AdminService {

	@Autowired
	private AdminDao adminDao;

	public int addGoods(GoodsVo goodsvo) {
		return 1;
	}

	public int removeGoodsInfo(Long goodsNo) {
		return 1;
	}

	public int modifyGoodsInfo(GoodsVo goodsvo) {
		return 1;
	}
	
}
