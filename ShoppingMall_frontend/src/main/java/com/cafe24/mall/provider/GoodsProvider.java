package com.cafe24.mall.provider;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.web.client.RestTemplate;

import com.cafe24.mall.dto.JSONResult;
import com.cafe24.mall.vo.GoodsVo;

@Repository
public class GoodsProvider {
	
	@Autowired
	private RestTemplate restTemplate;
	
	public List<GoodsVo> selectGoodsList(Long smallcategoryNo,Integer startCol) {
		//RestTemplate restTemplate = new RestTemplate();
		JSONResultGoodsList jsonresult = restTemplate.getForObject("http://localhost:8099/ShoppingMall_backend/api/goods/category?smallcategoryNo="+smallcategoryNo+"&startCol="+startCol, JSONResultGoodsList.class);		
		
		return jsonresult.getData();
	}
	
	public GoodsVo selectCategoryNames(Long smallcategoryNo) {
		//RestTemplate restTemplate = new RestTemplate();
		JSONResultGoodsVo jsonresult = restTemplate.getForObject("http://localhost:8099/ShoppingMall_backend/api/goods/category/getname/"+smallcategoryNo, JSONResultGoodsVo.class);		
		
		return jsonresult.getData();
	}
	
	public List<GoodsVo> selectGoodsList(String keyword) {
		
		JSONResultGoodsList jsonresult = restTemplate.getForObject("http://localhost:8099/ShoppingMall_backend/api/goods/search?kw="+keyword, JSONResultGoodsList.class);		
		
		return jsonresult.getData();
	}
	public Integer selectTotalGoodsCount(Long smallcategoryNo) {
		JSONResultInteger jsonresult = 
				restTemplate.getForObject("http://localhost:8099/ShoppingMall_backend/api/goods/totalcount/category/"+smallcategoryNo,  JSONResultInteger.class);
		
		return jsonresult.getData();

	}
	private static class JSONResultGoodsList extends JSONResult<List<GoodsVo>>{}
	private static class JSONResultGoodsVo extends JSONResult<GoodsVo>{}
	private static class JSONResultInteger extends JSONResult<Integer>{	}
}
