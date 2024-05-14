package com.itwillbs.payment;

import javax.inject.Inject;

import org.apache.ibatis.session.SqlSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

@Repository
public class PaymentDAOImpl implements PaymentDAO {
	
	@Inject
	private SqlSession sqlSession;
	
	private static final Logger logger = LoggerFactory.getLogger(PaymentDAOImpl.class);
	
	private static final String NAMESPACE = "com.itwillbs.mapper.PaymentMapper";

	@Override
	public void paymentInsert(PaymentVO pmvo) {
		logger.debug(" paymentInsert(PaymentVO pmvo) 호출 -> mapper 호출");
		
		sqlSession.insert(NAMESPACE + ".insertPayment", pmvo);
	}

	@Override
	public void payUpdateDeduct(PaymentVO pmvo) {
		logger.debug(" getMemberPay(String uid) 호출 -> mapper 호출");
		
		sqlSession.update(NAMESPACE + ".updatePayDeduct", pmvo);
	}

	@Override
	public void paymentUpdateCancel(PaymentVO pmvo) {
		logger.debug(" paymentUpdateCancel(PaymentVO pmvo) 호출 -> mapper 호출");
		
		sqlSession.insert(NAMESPACE + ".insertPayInfoCancel", pmvo);
	}
	
	
}
