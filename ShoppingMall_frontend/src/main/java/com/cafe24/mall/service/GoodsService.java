package com.cafe24.mall.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cafe24.mall.provider.GoodsProvider;
import com.cafe24.mall.vo.GoodsVo;

@Service
public class GoodsService {
	
	@Autowired
	private GoodsProvider goodsProvider;
	
	public List<GoodsVo> getGoodsList(Long smallcategoryNo) {
		return goodsProvider.selectGoodsList(smallcategoryNo);
	}

	public GoodsVo getCategoryNames(Long smallcategoryNo) {
		return goodsProvider.selectCategoryNames(smallcategoryNo);
	}

	public List<GoodsVo> getGoodsList(String keyword) {
		return goodsProvider.selectGoodsList(keyword);
	}
}
