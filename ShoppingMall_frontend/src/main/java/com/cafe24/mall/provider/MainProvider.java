package com.cafe24.mall.provider;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.web.client.RestTemplate;

import com.cafe24.mall.dto.JSONResult;
import com.cafe24.mall.vo.BigCategoryVo;
import com.cafe24.mall.vo.GoodsDetailVo;
import com.cafe24.mall.vo.GoodsImagesVo;
import com.cafe24.mall.vo.GoodsVo;

@Repository
public class MainProvider {

	@Autowired
	private RestTemplate restTemplate;
	
	public List<GoodsVo> selectGoodsList(int startNo) {
		//RestTemplate restTemplate = new RestTemplate();
		JSONResultGoodsList jsonresult = restTemplate.getForObject("http://localhost:8099/ShoppingMall_backend/api/goods/list/"+startNo, JSONResultGoodsList.class);
		return jsonresult.getData();
	}

	
	public List<GoodsVo> selectMainDisplayList(Long maindisplayNo) {		
		//RestTemplate restTemplate = new RestTemplate();
		JSONResultGoodsList jsonresult = restTemplate.getForObject("http://localhost:8099/ShoppingMall_backend/api/goods/maindisplay/"+maindisplayNo, JSONResultGoodsList.class);
		return jsonresult.getData();
	}

	public List<BigCategoryVo> selectCategoryList() {
		//RestTemplate restTemplate = new RestTemplate();
		JSONResultCategoryList jsonresult = restTemplate.getForObject("http://localhost:8099/ShoppingMall_backend/api/goods/category/list", JSONResultCategoryList.class);
		
		return jsonresult.getData();
	}
	public GoodsVo selectGoodsInfo(Long goodsNo) {
		JSONResultGoodsVo jsonresult = restTemplate.getForObject("http://localhost:8099/ShoppingMall_backend/api/goods/view/"+goodsNo, JSONResultGoodsVo.class);
		
		return jsonresult.getData();
	}
	public GoodsImagesVo selectMainImageVo(Long goodsNo) {
		JSONResultGoodsImageVo jsonresult = restTemplate.getForObject("http://localhost:8099/ShoppingMall_backend/api/goods/mainimage/"+goodsNo, JSONResultGoodsImageVo.class);
		
		return jsonresult.getData();
	}
	
	public List<GoodsImagesVo> selectSubImageList(Long goodsNo) {
		JSONResultGoodsImageList jsonresult = restTemplate.getForObject("http://localhost:8099/ShoppingMall_backend/api/goods/subimagelist/"+goodsNo, JSONResultGoodsImageList.class);
		
		return jsonresult.getData();
	}

	public List<GoodsDetailVo> selectGoodsDetailList(Long goodsNo) {
		JSONResultGoodsDetailList jsonresult = restTemplate.getForObject("http://localhost:8099/ShoppingMall_backend/api/goods/goodsdetail/"+goodsNo, JSONResultGoodsDetailList.class);
		
		return jsonresult.getData();
	}
	
	private static class JSONResultGoodsList extends JSONResult<List<GoodsVo>>{}
	private static class JSONResultCategoryList extends JSONResult<List<BigCategoryVo>>{}
	private static class JSONResultGoodsVo extends JSONResult<GoodsVo>{}
	private static class JSONResultGoodsImageVo extends JSONResult<GoodsImagesVo>{}
	private static class JSONResultGoodsImageList extends JSONResult<List<GoodsImagesVo>>{}
	private static class JSONResultGoodsDetailList extends JSONResult<List<GoodsDetailVo>>{}
	
	

	
	
}
