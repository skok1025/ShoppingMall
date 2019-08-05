package com.cafe24.mall.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.cafe24.mall.dto.JSONResult;
import com.cafe24.mall.provider.MainProvider;
import com.cafe24.mall.vo.BigCategoryVo;
import com.cafe24.mall.vo.GoodsVo;


@Service
public class MainService {

	@Autowired
	private MainProvider mainProvider;
	
	public List<GoodsVo> getGoodsList(int startNo) {				
		
		//JSONResult<List<GoodsVo>> result2 = restTemplate.getForObject("http://localhost:8099/ShoppingMall_backend/api/goods/list/"+startNo, JSONResult<List<GoodsVo>>.class);
		List<GoodsVo> result = mainProvider.selectGoodsList(startNo);
//		String result = restTemplate.getForObject("http://localhost:8099/ShoppingMall_backend/api/goods/list/"+startNo, String.class);
		
		return result;
	}

	public List<GoodsVo> getMainDisplayList() {
		Long maindisplayNo=1L;
		
		List<GoodsVo> result = mainProvider.selectMainDisplayList(maindisplayNo);
		return result;
	}

	public List<BigCategoryVo> getCategoryList() {
		
		return mainProvider.selectCategoryList();
	}


}
