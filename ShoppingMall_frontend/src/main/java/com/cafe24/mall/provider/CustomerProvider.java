package com.cafe24.mall.provider;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.web.client.RestTemplate;

import com.cafe24.mall.dto.JSONResult;
import com.cafe24.mall.vo.MemberVo;

@Repository
public class CustomerProvider {

	@Autowired
	private RestTemplate restTemplate;
	
	public Integer checkid(String id) {

		//RestTemplate restTemplate = new RestTemplate();
		JSONResultInteger jsonresult = restTemplate.getForObject("http://localhost:8099/ShoppingMall_backend/api/customer/checkId?id="+id, JSONResultInteger.class);		
		
		return jsonresult.getData();
	}

	public Integer insertMember(MemberVo memberVo) {
	
		JSONResultInteger jsonresult = 
			restTemplate.postForObject("http://localhost:8099/ShoppingMall_backend/api/customer/account",memberVo ,JSONResultInteger.class);		
		
		return jsonresult.getData();
	
	}
	
	public MemberVo getAuth(String username) {
		
		MemberVo memberVo = new MemberVo();
		memberVo.setId(username);
		
		JSONResultMemberVo jsonresult = 
				restTemplate.postForObject("http://localhost:8099/ShoppingMall_backend/api/customer/auth",memberVo ,JSONResultMemberVo.class);		
		
		
		return jsonresult.getData();
	}

	private static class JSONResultInteger extends JSONResult<Integer>{}
	private static class JSONResultMemberVo extends JSONResult<MemberVo>{}
	

	
	
}
