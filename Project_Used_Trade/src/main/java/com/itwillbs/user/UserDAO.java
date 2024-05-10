package com.itwillbs.user;

import java.util.List;

import com.itwillbs.user.AuthVO;
import com.itwillbs.user.UserVO;

public interface UserDAO {

	public void joinBoard(UserVO vo) throws Exception;
	
	public void authJoin(AuthVO avo) throws Exception;
	
	public UserVO loginBoard(UserVO vo) throws Exception;
	
	public int checkIdBoard(String userid) throws Exception;
	
	public List<UserVO> findIdBoard(UserVO vo) throws Exception;
	
	public UserVO findPwBoard(UserVO vo) throws Exception;
	
	public void pwUpdate(UserVO vo) throws Exception;
	
	public UserVO read(String userid) throws Exception;
	
	public UserVO updateUser(UserVO vo) throws Exception;
	
}
