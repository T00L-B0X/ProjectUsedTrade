package com.itwillbs.member;

public interface MemberDAO {
	
	// 회원가입 처리 동작
	public void insertMember(MemberVO vo);
	
	// 로그인 처리 동작
	public MemberVO loginMember(MemberVO vo);
	public MemberVO loginMember(String userid, String user_pw);

}
