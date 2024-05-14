package com.itwillbs.payment;

import javax.inject.Inject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class PaymentServiceImpl implements PaymentService {
	
	@Inject
	private PaymentDAO pmdao;
	
	private static final Logger logger = LoggerFactory.getLogger(PaymentServiceImpl.class);

	@Override
	public void insertPayment(PaymentVO pmvo, int goodsPrice) throws Exception {
		logger.debug(" insertPayment(PaymentVO pmvo) 실행 ");
		
		if(pmvo.getPAYMENT_AMOUNT() != goodsPrice) {
			// 실제로 결제된 금액과 DB 서버의 물건 금액과 같은지 확인
			logger.debug(" ####### 결제된 금액과 물건의 금액이 다릅니다. ####### ");
			throw new Exception("결제된 금액과 물건의 금액이 다릅니다.");
		} else {
			 // 금액이 같으면 DB에 결제 정보를 삽입.
			pmdao.paymentInsert(pmvo);
		}
		
	}
	
	@Override
	public void updateDeductPay(PaymentVO pmvo) throws Exception {
		logger.debug(" updateDeductPay(PaymentVO pmvo) 실행 ");
		
		pmdao.payUpdateDeduct(pmvo);
	}

	@Override
	public void cancelPayment(PaymentVO pmvo) throws Exception {
		logger.debug(" cancelPayment(PaymentVO pmvo) 실행 ");
		
		pmdao.paymentUpdateCancel(pmvo);
	}

}
