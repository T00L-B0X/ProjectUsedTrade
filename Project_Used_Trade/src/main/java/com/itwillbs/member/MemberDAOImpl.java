package com.itwillbs.member;

import java.util.HashMap;
import java.util.Map;

import javax.inject.Inject;

import org.apache.ibatis.session.SqlSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

@Repository("MemberDAO")
public class MemberDAOImpl implements MemberDAO {
	
	@Inject
	private SqlSession sqlSession;
	
	private static final Logger logger = LoggerFactory.getLogger(MemberDAOImpl.class);
	
	// mapper의 위치정보 (namespace)
	private static final String NAMESPACE = "com.itwillbs.mapper.MemberMapper";

	@Override
	public void insertMember(MemberVO vo) {
		logger.info(" insertMember(MemberVO vo) 실행 ");
		logger.info(" sqlSession 사용 -> mapper 호출 ");
		
		sqlSession.insert(NAMESPACE + ".insertMember", vo);
		
	}

	@Override
	public MemberVO loginMember(MemberVO vo) {
		logger.debug(" loginMember(MeberVO vo) 실행 ");
		
		MemberVO resultVO = sqlSession.selectOne(NAMESPACE + ".loginMember", vo);
		
		return resultVO;
	}

	@Override
	public MemberVO loginMember(String userid, String user_pw) {
		logger.debug(" loginMember(String userid, String userpw) 실행 ");
		
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("userid", userid);
		paramMap.put("userpw", user_pw);
		
		sqlSession.selectOne(NAMESPACE + ".loginMember", paramMap);
		return null;
	}
	

	
	
	
	
	
	
	
	
	
	
	
}
