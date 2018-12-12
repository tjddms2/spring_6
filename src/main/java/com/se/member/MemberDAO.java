package com.se.member;

import javax.inject.Inject;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

@Repository
public class MemberDAO {

	@Inject
	private SqlSession sqlsession;
	private static final String NAMESPACE="memberMapper.";	
	
	public int join(MemberDTO memberDTO)throws Exception {
		return sqlsession.insert(NAMESPACE+"join", memberDTO);
	}
	
	public MemberDTO idCheck(String id) throws Exception{//이아디로 판단하는 기준으로만하기
		return sqlsession.selectOne(NAMESPACE+"idCheck", id);
	}

	public MemberDTO login(MemberDTO memberDTO)throws Exception {
		return sqlsession.selectOne(NAMESPACE+"login", memberDTO);
	}
	
	public int update(MemberDTO memberDTO) throws Exception {
		return sqlsession.update(NAMESPACE+"update", memberDTO);
	}
	public int delete(String id) throws Exception {
		return sqlsession.delete(NAMESPACE+"delete", id);
	}
}
