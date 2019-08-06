package com.cafe24.mall.provider;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.web.client.RestTemplate;

import com.cafe24.mall.dto.JSONResult;
import com.cafe24.mall.vo.MemberVo;

@Repository
public class AdminProvider {

	@Autowired
	private RestTemplate restTemplate;

	public List<MemberVo> selectMemberList(String id, String orderDateStart, String orderDateEnd) {
		
		if(id== null)
			id = "";
		if(orderDateStart== null)
			orderDateStart = "";
		if(orderDateEnd== null)
			orderDateEnd = "";
		
		JSONResultMemberList jsonresult = 
				restTemplate.getForObject("http://localhost:8099/ShoppingMall_backend/api/admin/member?id="+id+
						"&orderdateStart="+ orderDateStart
						+"&orderdateEnd="+ orderDateEnd, JSONResultMemberList.class);		
		
		return jsonresult.getData();
	}
	
	public int deleteMember(Long userNo) {
		
		
		restTemplate.delete("http://localhost:8099/ShoppingMall_backend/api/admin/member/"+userNo);
		
		return 1;
	}
	
	private static class JSONResultMemberList extends JSONResult<List<MemberVo>>{
		
	}
	
	private static class JSONResultInteger extends JSONResult<Integer>{
		
	}


}
