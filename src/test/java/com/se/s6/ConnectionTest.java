package com.se.s6;

import static org.junit.Assert.*;

import javax.inject.Inject;

import org.apache.ibatis.session.SqlSession;
import org.junit.Test;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

public class ConnectionTest extends AbstractTestCase {

	@Inject		//DB연결까지 끝냈다.
	/*private DriverManagerDataSource ds;*/
	
	private SqlSession sqlSession;
	
	@Test
	public void test()throws Exception {
		assertNotNull(sqlSession);
	}
	

}
