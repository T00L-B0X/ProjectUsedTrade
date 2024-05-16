package com.itwillbs.user;

import java.util.List;

import com.itwillbs.user.AuthVO;
import com.itwillbs.user.MemberVO;

public interface MemberService {
	
	public void boardJoin(MemberVO vo) throws Exception;
	
	public void boardAuthJoin(AuthVO avo) throws Exception;

	public MemberVO boardLogin(MemberVO vo) throws Exception;
	
	public int boardIdCheck(String userid) throws Exception;
	
	public List<MemberVO> boardIdFind(MemberVO vo) throws Exception;
	
	public MemberVO boardPwFind(MemberVO vo) throws Exception;
	
	public void updatePw(MemberVO vo) throws Exception;
	
	public MemberVO read(String userid) throws Exception;
	
	public MemberVO updateUser(MemberVO vo) throws Exception;
	
	
	
}
