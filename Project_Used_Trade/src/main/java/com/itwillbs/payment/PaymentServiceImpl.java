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
	public void insertPayment(PaymentVO pmvo) {

		
	}

	@Override
	public void cancelPayment(PaymentVO pmvo) {

		
	}

	@Override
	public void updateDeductPay(PaymentVO pmvo) {

		
	}


}
