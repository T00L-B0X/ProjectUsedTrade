package com.itwillbs.user;

import java.util.List;

import javax.inject.Inject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.itwillbs.user.AuthVO;
import com.itwillbs.user.MemberVO;
import com.itwillbs.user.UserDAO;

@Service
public class UserServiceImpl implements UserService{

	@Inject
	private UserDAO bDao;
	
	private static final Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);
	
	@Override
	public void boardJoin(MemberVO vo) throws Exception {
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
	public MemberVO boardLogin(MemberVO vo) throws Exception {
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
	public List<MemberVO> boardIdFind(MemberVO vo) throws Exception {
		logger.debug("boardIdFind(BoardVO vo) 호출");
		
		return bDao.findIdBoard(vo);
		
	}

	@Override
	public MemberVO boardPwFind(MemberVO vo) throws Exception {
		logger.debug("boardPwFind(BoardVO vo) 호출");
		
		return bDao.findPwBoard(vo);
	}
	
	

	@Override
	public void updatePw(MemberVO vo) throws Exception {
		logger.debug("updatePw(BoardVO vo) 호출");
		
		bDao.pwUpdate(vo);
		
	}

	@Override
	public UserVO read(String userid) throws Exception {
		logger.debug("read(String userid) 호출");

		return bDao.read(userid);
		
	}

	@Override
	public UserVO updateUser(UserVO vo) throws Exception {
		logger.debug("updatePw(String userid)호출");
		
		return bDao.updateUser(vo);
	}
	
	
	
	
	
	
	
	
	
	
}
