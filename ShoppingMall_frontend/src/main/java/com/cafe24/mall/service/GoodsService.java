package com.cafe24.mall.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cafe24.mall.provider.GoodsProvider;
import com.cafe24.mall.util.PagingFrontUtil;
import com.cafe24.mall.vo.GoodsVo;

@Service
public class GoodsService {
	
	@Autowired
	private GoodsProvider goodsProvider;
	
	private int listSize = 5;
	private int pageSize = 8;
	
	public List<GoodsVo> getGoodsList(Long smallcategoryNo,Integer currentPage) {
		
		Integer totalcount = goodsProvider.selectTotalGoodsCount(smallcategoryNo);
		Integer startCol = PagingFrontUtil.getStartRecordNum(currentPage, totalcount, pageSize);
		
		System.out.println("카테고리의 상품 총 갯수: "+totalcount);
		System.out.println("시작 인덱스: "+startCol);
		
		return goodsProvider.selectGoodsList(smallcategoryNo,startCol);
	}

	public GoodsVo getCategoryNames(Long smallcategoryNo) {
		return goodsProvider.selectCategoryNames(smallcategoryNo);
	}

	public List<GoodsVo> getGoodsList(String keyword) {
		return goodsProvider.selectGoodsList(keyword);
	}

	public Map<String, Integer> getGoodsPaging(Long smallcategoryNo, Integer currentPage) {
		Integer totalcount = goodsProvider.selectTotalGoodsCount(smallcategoryNo);
		Map<String, Integer> result = PagingFrontUtil.getPagingVariable(currentPage, totalcount, pageSize, listSize);
		
		System.out.println("마지막 페이지: "+result.get("endPage"));
		System.out.println("nowEnd: "+result.get("nowEnd"));
		return result;
	}
}
