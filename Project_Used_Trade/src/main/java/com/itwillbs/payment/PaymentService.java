package com.itwillbs.payment;

public interface PaymentService {
	
	// 결제 정보 조회
	
	// 결제 정보 입력
	public void insertPayment(PaymentVO pmvo, int goodsPrice) throws Exception;
	
	// 결제 금액 차감
	public void updateDeductPay(PaymentVO pmvo) throws Exception;
	
	// 결제 취소 (구현중)
	public void cancelPayment(PaymentVO pmvo) throws Exception;
	
}
