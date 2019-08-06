package com.cafe24.mall.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cafe24.mall.provider.CustomerProvider;
import com.cafe24.mall.vo.MemberVo;

@Service
public class CustomerService {

	@Autowired
	private CustomerProvider customerProvider;

	public Integer checkid(String id) {
		return customerProvider.checkid(id);
	}

	public Integer joinMember(MemberVo memberVo) {
		return customerProvider.insertMember(memberVo);
	}
	
}
