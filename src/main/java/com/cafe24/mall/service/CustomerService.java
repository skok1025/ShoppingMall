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

	public MemberVo memberJoin(MemberVo memberVo) {
		//sampleUserDB.add(memberVo);
		return memberVo;
	}

	public MemberVo getAuthUser(MemberVo membervo) {
		for(MemberVo vo:sampleUserDB) {
			if(vo.getId().equals(membervo.getId()) && 
			   vo.getPassword().equals(membervo.getPassword())) {
				return vo;
			}
		}
		
		return null;
	}
	
	public int getIdCount(String id) {
		int count = 0;
		
		for(MemberVo vo:sampleUserDB) {
			if(vo.getId().equals(id)) {
				count++;
			}
		}
		
		return count;
	}

	public int removeAccount(MemberVo memberVo) {
		
		// 진짜 비밀번호 1234
		if("1234".equals(memberVo.getPassword())) {
			return 1;
		}
		return 0;
	}

	/**
	 * 회원정보수정 메소드
	 * @param memberVo 수정정보를 담은 vo 객체
	 * @return 수정성공 유무 (1-성공, 0-실패)
	 */
	public int modifyAccount(MemberVo memberVo) {
		return 1;
	}
	
	
}
