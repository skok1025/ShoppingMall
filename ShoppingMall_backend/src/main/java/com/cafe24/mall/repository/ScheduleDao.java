package com.cafe24.mall.repository;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class ScheduleDao {


	@Autowired
	private SqlSession sqlSession;
	
	public int updateDormancy() {
		return sqlSession.update("member.updateDormancy");
	}

}
