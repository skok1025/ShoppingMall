package com.cafe24.mall.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import com.cafe24.mall.repository.GoodsDao;
import com.cafe24.mall.vo.GoodsDetailVo;
import com.cafe24.mall.vo.GoodsImagesVo;
import com.cafe24.mall.vo.GoodsVo;
import com.cafe24.mall.vo.MaindisplayCategoryVo;

@Service
public class GoodsService {

	@Autowired
	private GoodsDao goodsDao;


	@Cacheable("getGoodsList")
	public List<GoodsVo> getGoodsList(Integer startNo) {
		return goodsDao.selectGoodsList(startNo);
	}
	
	public List<GoodsVo> getGoodsList(String kw) {
		return goodsDao.selectGoodsList(kw);
	}

	public GoodsVo getView(Long goodsNo) {
		return goodsDao.selectGoods(goodsNo);
	}

	public List<GoodsImagesVo> getSubImageList(Long goodsNo) {
		return goodsDao.selectSubImageList(goodsNo);
	}

	public GoodsImagesVo getMainImage(Long goodsNo) {
		return goodsDao.selectMainImage(goodsNo);
	}

	public List<GoodsDetailVo> getGoodsDetailList(Long goodsNo) {
		return goodsDao.selectGoodsDetailList(goodsNo);
	}

	public List<GoodsVo> getGoodsList(Long smallcategoryNo,Integer startCol) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("smallcategoryNo", smallcategoryNo);
		map.put("startCol", startCol);
		
		return goodsDao.selectGoodsList(map);
		
	}

	public List<GoodsVo> getMainDisplayList(Long maindisplayNo) {
		return goodsDao.selectMainDisplayList(maindisplayNo);
	}

	public GoodsVo getCategoryName(Long smallCategoryNo) {
		return goodsDao.selectCategoryName(smallCategoryNo);
	}

	public MaindisplayCategoryVo getMainDisplayCategoryVo() {
		return goodsDao.selectMainDisplayCategoryVo();
	}

	public Integer getTotalCount(Long smallcategoryNo) {
		return goodsDao.selectTotalCount(smallcategoryNo);
	}

}
