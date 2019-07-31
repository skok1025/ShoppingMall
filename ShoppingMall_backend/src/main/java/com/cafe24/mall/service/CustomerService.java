package com.cafe24.mall.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cafe24.mall.repository.AdminDao;
import com.cafe24.mall.repository.CustomerDao;
import com.cafe24.mall.vo.MemberVo;

@Service
public class CustomerService {

	@Autowired
	private CustomerDao customerDao;
	
	@Autowired
	private AdminDao adminDao;
	
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

	@Transactional
	public MemberVo getAuthUser(MemberVo membervo) {

		MemberVo vo =  customerDao.getAuthUser(membervo);
		// 로그인 시, membervo.basketCode 를 이용하여
		// tblCustomerBasketCode 의 해당컬럼 member_no 를 업데이트
		// -> 비회원으로 장바구니에 상품을 담은 후, 로그인을 했을 때, 
		//    상품은 그대로 유지한다.
		// 여기서 발생하는 문제점은 비회원인 상태에서 장바구니 등록을 하고 로그인을 하면 로그인한 회원의 장바구니에 존재하지만
		// 같은 브라우저에 쿠키유효기간이 지나지 않아 장바구니코드가 쿠키에 살아있는 상태에서 다른 아이디로 로그인을 한다고 하면
		// 그 전 회원장바구니에는 존재하지 않는다.
		// 방법: 회원들에게 비회원 상태에서 장바구니를 등록하면 유실될 가능성을 알린다.
		if(membervo.getBasketCode() != null) {
			System.out.println(membervo.getBasketCode());
			membervo.setNo(vo.getNo());
			System.out.println(membervo.getNo());
			// 회원번호, 장바구니코드 넘김
			// 쿠키에 있던 장바구니코드를 회원번호와 매칭시키는 작업
			customerDao.updateCustomerBasketCodeMemberNo(membervo); 
		}
		
		
		
		return vo;
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
		
		Long memberNo = memberVo.getNo();
		
		adminDao.deleteMemberOrderInfo(memberNo);
		adminDao.updateOrderMemberNull(memberNo);
		adminDao.updateCustomerBasketCodeMemberNull(memberNo);
		adminDao.updateMemberTermsMemberNull(memberNo);
		
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
