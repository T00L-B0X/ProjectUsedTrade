package com.itwillbs.pay;

import javax.inject.Inject;

import org.apache.ibatis.session.SqlSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import com.itwillbs.member.MemberVO;

@Repository
public class PayDAOImpl implements PayDAO {

	@Inject
	private SqlSession sqlSession;
	
	private static final Logger logger = LoggerFactory.getLogger(PayDAOImpl.class);

	private static final String NAMESPACE = "com.itwillbs.mapper.PayMapper";
	
	@Override
	public MemberVO loginMember(MemberVO vo) {
		logger.debug(" loginMember(MemberVO vo) 호출 -> mapper 호출");
		
		MemberVO resultVO = sqlSession.selectOne(NAMESPACE + ".loginMember", vo);
		
		return resultVO;
	}
	
	@Override
	public PayVO getMemberPay(String uid) {
		logger.debug(" getMemberPay(String uid) 호출 -> mapper 호출");
		
		PayVO pResultVO = sqlSession.selectOne(NAMESPACE + ".getMemberPay", uid);
		
		return pResultVO;
	}

	@Override
	public void payInfoInsert(PayInfoVO pivo) throws Exception {
		logger.debug("payInfoInsert(PayInfoVO pivo) -> mapper 호출");
		
		sqlSession.insert(NAMESPACE + ".insertPayInfo", pivo);
	}

	@Override
	public void payUpdate(PayInfoVO pivo) throws Exception {
		logger.debug("payUpdate(PayInfoVO pivo) -> mapper 호출");
		
		sqlSession.update(NAMESPACE + ".updatePay", pivo);
	}

	@Override
	public void payInfoRefundInsert(PayInfoVO pivo) throws Exception {
		logger.debug("payInfoRefundInsert(PayInfoVO pivo) -> mapper 호출");
		
		sqlSession.insert(NAMESPACE + ".insertPayInfoRefund", pivo);
		
	}

	@Override
	public void payRefundUpdate(PayInfoVO pivo) throws Exception {
		logger.debug("payUpdate(PayInfoVO pivo) -> mapper 호출");
		
		sqlSession.update(NAMESPACE + ".updatePayRefund", pivo);
		
	}
	
	
	
	
}
