package com.itwillbs.pay;

import com.siot.IamportRestClient.response.IamportResponse;
import com.siot.IamportRestClient.response.Payment;

public interface PayService {
	
	// 페이 아이디 생성 (회원가입시 실행)
	public void insertMemberPay(PayVO pvo) throws Exception;
	
	// 페이 조회 (페이 충전 페이지)
	public PayVO memberPay(String uid) throws Exception;
	
	// 페이 정보 입력 (충전)
	public void insertPayInfo(PayInfoVO pivo) throws Exception;
	
	// 페이 충전
	public void updatePay(PayInfoVO pivo) throws Exception;
	
	// 페이 검증 후 충전
	public void verifyIamportService(IamportResponse<Payment> irsp, int amount, PayInfoVO pivo) throws Exception;
	
	// 페이 정보 입력 (환불)
	public void insertPayInfoRefund(PayInfoVO pivo) throws Exception;
	
	// 페이 환불
	public void updatePayRefund(PayInfoVO pivo) throws Exception;
}
