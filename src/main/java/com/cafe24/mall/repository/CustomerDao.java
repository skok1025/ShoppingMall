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
}
