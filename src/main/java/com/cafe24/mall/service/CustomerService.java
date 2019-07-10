package com.cafe24.mall.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cafe24.mall.repository.CustomerDao;
import com.cafe24.mall.vo.MemberVo;

@Service
public class CustomerService {

	@Autowired
	private CustomerDao customerDao;

	public int memberJoin(MemberVo memberVo) {
		
		return 0;
	}
}
