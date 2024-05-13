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
	
	
}
