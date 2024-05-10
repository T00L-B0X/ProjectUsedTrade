package com.itwillbs.pay;

import com.itwillbs.user.MemberVO;

public interface PayDAO {
	
	// 로그인 처리 동작
	public MemberVO loginMember(MemberVO vo);
	
	// 페이 조회
	public PayVO getMemberPay(String uid);
	
	// 페이 정보 삽입 동작 (충전)
	public void payInfoInsert(PayInfoVO pivo) throws Exception;
	
	// 페이 충전 동작
	public void payUpdate(PayInfoVO pivo) throws Exception;
	
	// 페이 정보 삽입 동작 (환불)
	public void payInfoRefundInsert(PayInfoVO pivo) throws Exception;
	
	// 페이 환불 동작
	public void payRefundUpdate(PayInfoVO pivo) throws Exception;
}
