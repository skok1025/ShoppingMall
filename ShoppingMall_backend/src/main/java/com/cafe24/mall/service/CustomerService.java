package com.cafe24.mall.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cafe24.mall.repository.CustomerDao;
import com.cafe24.mall.vo.MemberVo;

@Service
public class CustomerService {

	@Autowired
	private CustomerDao customerDao;
	
	public static List<MemberVo> sampleUserDB =Arrays.asList(
				new MemberVo("skok1025", "1234"),
				new MemberVo("ewsinger", "1234"),
				new MemberVo("what", "1234")
			);
	
	

	public int memberJoin(MemberVo memberVo) {
		// 회원가입이 일어날때 tblCustomerBasketCode 에 
		// 회원마다 basketCode 할당 (code == member_no)
		
		int result =  customerDao.insertMember(memberVo);
		result *= customerDao.insertJoinBaseCustomerBasketCode(memberVo.getNo());
		
		return result;
	}

	public MemberVo getAuthUser(MemberVo membervo) {
//		for(MemberVo vo:sampleUserDB) {
//			if(vo.getId().equals(membervo.getId()) && 
//			   vo.getPassword().equals(membervo.getPassword())) {
//				return vo;
//			}
//		}
		
		return customerDao.getAuthUser(membervo);
	}
	
	public int getIdCount(String id) {
		int count = customerDao.getIdCount(id);
		
//		for(MemberVo vo:sampleUserDB) {
//			if(vo.getId().equals(id)) {
//				count++;
//			}
//		}
		
		
		
		return count;
	}

	public int removeAccount(MemberVo memberVo) {
		
		return customerDao.deleteAccount(memberVo);
	}

	/**
	 * 회원정보수정 메소드
	 * @param memberVo 수정정보를 담은 vo 객체
	 * @return 수정성공 유무 (1-성공, 0-실패)
	 */
	public int modifyAccount(MemberVo memberVo) {
		return customerDao.updateAccount(memberVo);
	}

	public int modifyAccountPw(MemberVo memberVo) {
		if(!memberVo.getNewPw().equals(memberVo.getConfirmPw())) {
			return 0;
		}
		return customerDao.updateAccountPw(memberVo);
	}


	
	
}
