package com.itwillbs.user;

import java.util.List;

import javax.inject.Inject;

import org.apache.ibatis.session.SqlSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import com.itwillbs.user.AuthVO;
import com.itwillbs.user.UserVO;

@Repository
public class UserDAOImpl implements UserDAO{

	private static final Logger logger = LoggerFactory.getLogger(UserDAOImpl.class);
	
	@Inject
	private SqlSession sqlSession;
	
	private static final String NAMESPACE="com.itwillbs.mapper.UserMapper";

	@Override
	public void joinBoard(UserVO vo) throws Exception {
		logger.debug("joinBoard(BoardVO vo) 호출");
		
		sqlSession.insert(NAMESPACE+".join", vo);
		
	}
	
	@Override
	public void authJoin(AuthVO avo) throws Exception {
		logger.debug("authJoin(AuthVO vo) 호출");
		
		sqlSession.insert(NAMESPACE+".authJoin", avo);
		
	}

	@Override
	public UserVO loginBoard(UserVO vo) throws Exception {
		logger.debug("loginBoard(BoardVO vo) 호출");
		
		return sqlSession.selectOne(NAMESPACE+".login", vo);
	}

	@Override
	public int checkIdBoard(String userid) throws Exception {
	    logger.debug("checkIdBoard(String id) 호출");
	    logger.debug("DAO id==>"+userid);

	    int cnt =  sqlSession.selectOne(NAMESPACE + ".checkId", userid);
	    return cnt;
	}

	@Override
	public List<UserVO> findIdBoard(UserVO vo) throws Exception {
		logger.debug("findIdBoard(BoardVO vo)호출");
		
		return sqlSession.selectList(NAMESPACE+".findId", vo);
		
	}

	@Override
	public UserVO findPwBoard(UserVO vo) throws Exception {
		logger.debug("findPwBoard(BoardVO vo) 호출");
		
		return sqlSession.selectOne(NAMESPACE+".findPw", vo);
	}
	
	

	@Override
	public void pwUpdate(UserVO vo) throws Exception {
		logger.debug("pwUpdate(BoardVO vo) 호출");
		
		sqlSession.update(NAMESPACE+".newPw", vo);
		
	}

	@Override
	public UserVO read(String userid) throws Exception {
		
		return sqlSession.selectOne(NAMESPACE+".list", userid);
	}
	
	
	
	
	
	
}
