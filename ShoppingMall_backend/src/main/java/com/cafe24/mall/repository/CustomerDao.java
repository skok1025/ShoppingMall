package com.cafe24.mall.repository;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.cafe24.mall.vo.MemberVo;

@Repository
public class CustomerDao {

	@Autowired
	private SqlSession sqlsession;

	public int getIdCount(String id) {
		return sqlsession.selectOne("member.getIdCount",id);
	}

	public int insertMember(MemberVo memberVo) {
		return sqlsession.insert("member.insertMember",memberVo);
	}

	public void deleteAllMemberData() {
		sqlsession.delete("member.deleteAllMemberData");
	}

	public int updateAccount(MemberVo memberVo) {
		return sqlsession.update("member.updateAccount",memberVo);
	}

	public MemberVo getAuthUser(MemberVo membervo) {
		return sqlsession.selectOne("member.getAuthUser",membervo);
	}

	public int updateAccountPw(MemberVo memberVo) {
		return sqlsession.update("member.updateAccountPw", memberVo);
	}

	public int deleteAccount(MemberVo memberVo) {
		return sqlsession.delete("member.deleteAccount", memberVo);
	}

	public Long getCurrentInsertNo() {
		return sqlsession.selectOne("member.getCurrentInsertNo");
	}

	/**
	 * 회원가입 진행 시, 회원에게 할당된 장바구니 코드를 등록하는 메소드
	 * 회원번호 (member_no) 와 장바구니 코드(code) 같다.
	 * @param no 회원번호
	 * @return 성공유무
	 */
	public int insertJoinBaseCustomerBasketCode(Long no) {
		return sqlsession.insert("basket.insertJoinBaseCustomerBasketCode", no);
	}

	public void updateCustomerBasketCodeMemberNo(MemberVo vo) {
		sqlsession.update("basket.updateCustomerBasketCodeMemberNo", vo);
	}
}
