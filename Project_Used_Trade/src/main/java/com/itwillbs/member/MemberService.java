package com.itwillbs.member;

public interface MemberService {
	
	// 회원가입
	public void memberJoin(MemberVO vo);
	
	// 로그인 처리
	public MemberVO memberLogin(MemberVO vo);

}
