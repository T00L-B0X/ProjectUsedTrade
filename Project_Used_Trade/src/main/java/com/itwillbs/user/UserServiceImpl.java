package com.itwillbs.user;

import java.util.List;

import javax.inject.Inject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.itwillbs.user.AuthVO;
import com.itwillbs.user.UserVO;
import com.itwillbs.user.UserDAO;

@Service
public class UserServiceImpl implements UserService{

	@Inject
	private UserDAO bDao;
	
	private static final Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);
	
	@Override
	public void boardJoin(UserVO vo) throws Exception {
		logger.debug("boardJoin(BoardVO vo) 호출");
		logger.debug("vo==="+vo);
		
		bDao.joinBoard(vo);
	}
	
	@Override
	public void boardAuthJoin(AuthVO avo) throws Exception {
		logger.debug("boardAuthJoin(AuthVO avo) 호출");
		
		bDao.authJoin(avo);
		
	}



	@Override
	public UserVO boardLogin(UserVO vo) throws Exception {
		logger.debug("boardLogin(BoardVO vo) 호출");
		
		return bDao.loginBoard(vo);
	}

	@Override
	public int boardIdCheck(String userid) throws Exception {
		logger.debug("boardIdCheck(String id) 호출");
		logger.debug("Ser id==>"+userid);
		int cnt = bDao.checkIdBoard(userid);
		return cnt;
		
	}

	@Override
	public List<UserVO> boardIdFind(UserVO vo) throws Exception {
		logger.debug("boardIdFind(BoardVO vo) 호출");
		
		return bDao.findIdBoard(vo);
		
	}

	@Override
	public UserVO boardPwFind(UserVO vo) throws Exception {
		logger.debug("boardPwFind(BoardVO vo) 호출");
		
		return bDao.findPwBoard(vo);
	}
	
	

	@Override
	public void updatePw(UserVO vo) throws Exception {
		logger.debug("updatePw(BoardVO vo) 호출");
		
		bDao.pwUpdate(vo);
		
	}

	@Override
	public UserVO read(String userid) throws Exception {
		// TODO Auto-generated method stub
		return bDao.read(userid);
		
	}
	
	
	
	
	
	
	
	
	
	
}
