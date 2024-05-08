package com.itwillbs.controller;

import javax.inject.Inject;

import org.apache.ibatis.session.SqlSession;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(
		{
		"file:src/main/webapp/WEB-INF/spring/root-context.xml",
		"file:src/main/webapp/WEB-INF/spring/security-context.xml"
		}
		)
public class DBConnectTest {
	
	@Inject
	private PasswordEncoder pwEncoder;

	private static final Logger logger = LoggerFactory.getLogger(DBConnectTest.class);
	
	@Inject
	private SqlSession sqlSession;

	//@Test
	public void testConnectDB() throws Exception {
		logger.debug("testConnectDB() 호출");

		logger.debug("sqlSession: " + sqlSession);
	}
	
	@Test
	public void testEncode() {
		System.out.println(pwEncoder.encode("asdqwe123"));
	}

}
