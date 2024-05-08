package com.itwillbs.user;

import java.util.List;

import com.itwillbs.user.AuthVO;
import com.itwillbs.user.UserVO;

public interface UserService {
	
	public void boardJoin(UserVO vo) throws Exception;
	
	public void boardAuthJoin(AuthVO avo) throws Exception;

	public UserVO boardLogin(UserVO vo) throws Exception;
	
	public int boardIdCheck(String userid) throws Exception;
	
	public List<UserVO> boardIdFind(UserVO vo) throws Exception;
	
	public UserVO boardPwFind(UserVO vo) throws Exception;
	
	public void updatePw(UserVO vo) throws Exception;
	
	public UserVO read(String userid) throws Exception;
	
	
	
}
