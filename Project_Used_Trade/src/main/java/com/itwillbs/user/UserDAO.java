package com.itwillbs.user;

import java.util.List;

import com.itwillbs.user.AuthVO;
import com.itwillbs.user.MemberVO;

public interface UserDAO {

	public void joinBoard(MemberVO vo) throws Exception;
	
	public void authJoin(AuthVO avo) throws Exception;
	
	public MemberVO loginBoard(MemberVO vo) throws Exception;
	
	public int checkIdBoard(String userid) throws Exception;
	
	public List<MemberVO> findIdBoard(MemberVO vo) throws Exception;
	
	public MemberVO findPwBoard(MemberVO vo) throws Exception;
	
	public void pwUpdate(MemberVO vo) throws Exception;
	
	public MemberVO read(String userid) throws Exception;
	
	public UserVO updateUser(UserVO vo) throws Exception;
	
}
