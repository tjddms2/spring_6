package com.se.board.memo;

import java.util.List;

import javax.inject.Inject;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import com.se.util.Paper;

@Repository
public class MemoDAO {
	@Inject
	private SqlSession sqlsession;
	private static final String NAMESPACE="memoMapper.";
	
	public List<MemoDTO> list(Paper pager)throws Exception{
		return sqlsession.selectList(NAMESPACE+"list", pager);
	}
	
	public MemoDTO select(int num)throws Exception{
		return sqlsession.selectOne(NAMESPACE+"select", num);
	}
}
