package com.itwillbs.payment;

public interface PaymentDAO {
	
	// 결제 정보 입력 동작
	public void paymentInsert(PaymentVO pmvo);
	
	// 결제 금액 차감 동작
	public void payUpdateDeduct(PaymentVO pmvo);
	
	// 결제 취소 동작 (구현중)
	public void paymentUpdateCancel(PaymentVO pmvo);

}
