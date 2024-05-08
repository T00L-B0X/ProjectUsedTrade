package com.itwillbs.pay;

import javax.inject.Inject;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.itwillbs.member.MemberVO;
import com.siot.IamportRestClient.request.CancelData;
import com.siot.IamportRestClient.response.IamportResponse;
import com.siot.IamportRestClient.response.Payment;

@Service
public class PayServiceImpl implements PayService {

	@Inject
	private PayDAO pdao;
	
	private static final Logger logger = LoggerFactory.getLogger(PayServiceImpl.class);
	
	@Override
	public MemberVO memberLogin(MemberVO vo) {
		logger.debug(" loginMember(MemberVO vo) 실행 ");
		logger.debug(" DAO 로그인 처리 동작 호출 ");
		
		MemberVO resultVO = pdao.loginMember(vo);
		
		return resultVO;
	}
	
	
	@Override
	public PayVO memberPay(String uid) throws Exception {
		logger.debug(" memberPay(String uid) 실행 ");
		
		PayVO pResultVO = pdao.getMemberPay(uid);
		
		return pResultVO;
	}


	@Override
	public void insertPayInfo(PayInfoVO pivo, HttpSession session) throws Exception {
		logger.debug(" insertPayInfo(PayInfoVO pivo) 실행! ");
		
		pdao.payInfoInsert(pivo);
	}
	
    /**
     * 포트원(아임포트) 서버쪽 결제내역과 DB에 페이 충전 금액을 비교하는 서비스.
     * 다름 -> 예외 발생
     * 같음 -> 결제정보를 DB에 저장
     * @param irsp (포트원 쪽 결제 내역 조회 정보)
     * @param reservationId (내 DB에서 물건 가격을 알기위한 번호)
     */
	@Override
	@Transactional
	public void verifyIamportService(IamportResponse<Payment> irsp, int amount, PayInfoVO pivo) throws Exception {
        // 실제로 결제된 금액과 포트원 서버쪽 결제내역 금액과 같은지 확인
        // 이때 가격은 BigDecimal이란 데이터 타입으로 주로 금융쪽에서 정확한 값표현을 위해 씀.
        // int형으로 비교해주기 위해 형변환 필요.
    	
        if(irsp.getResponse().getAmount().intValue() != amount) {
        	// 포트원에서 서버쪽 결제내역과 다르면 예외 발생.
        	logger.debug(" ####### 결제된 금액과 포트원 서버 내역의 금액이 다릅니다. ####### ");
            throw new Exception("결제된 금액과 포트원 서버 내역의 금액이 다릅니다.");
        } else {
            // 포트원에서 서버쪽 결제내역과 같으면 DB에 결제 정보를 삽입.
    		logger.debug(" PayInfoVO : " + pivo);
    		
        	pdao.payInfoInsert(pivo);
        }
	}

	@Override
	public void updatePay(PayInfoVO pivo) throws Exception {
		logger.debug(" updatePay(PayInfoVO pivo) 실행! ");
		
		pdao.payUpdate(pivo);
	}


	@Override
	public void insertPayInfoRefund(PayInfoVO pivo, HttpSession session) throws Exception {
		logger.debug(" insertPayInfoRefund(PayInfoVO pivo) 실행! ");
		
		pdao.payInfoRefundInsert(pivo);
	}


	@Override
	public void updatePayRefund(PayInfoVO pivo) throws Exception {
		logger.debug(" updatePayRefund(PayInfoVO pivo) 실행! ");
		
		pdao.payRefundUpdate(pivo);
	}
	
	
	
}
