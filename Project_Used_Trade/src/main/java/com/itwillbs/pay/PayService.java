package com.itwillbs.pay;

import javax.servlet.http.HttpSession;

import com.itwillbs.user.MemberVO;
import com.siot.IamportRestClient.response.IamportResponse;
import com.siot.IamportRestClient.response.Payment;

public interface PayService {
	
	// 회원 로그인 
	public MemberVO memberLogin(MemberVO vo);
	
	// 페이 조회 (페이 충전 페이지)
	public PayVO memberPay(String uid) throws Exception;
	
	// 페이 정보 입력 (충전)
	public void insertPayInfo(PayInfoVO pivo, HttpSession session) throws Exception;
	
	// 페이 충전
	public void updatePay(PayInfoVO pivo) throws Exception;
	
	// 페이 검증 후 충전
	public void verifyIamportService(IamportResponse<Payment> irsp, int amount, PayInfoVO pivo) throws Exception;
	
	// 결제 정보 조회
	
	// 페이 정보 입력 (환불)
	public void insertPayInfoRefund(PayInfoVO pivo, HttpSession session) throws Exception;
	
	// 페이 환불
	public void updatePayRefund(PayInfoVO pivo) throws Exception;
}
