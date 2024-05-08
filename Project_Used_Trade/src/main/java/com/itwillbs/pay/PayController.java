package com.itwillbs.pay;

import java.io.IOException;
import java.util.Map;

import javax.inject.Inject;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.itwillbs.member.MemberVO;
import com.siot.IamportRestClient.IamportClient;
import com.siot.IamportRestClient.exception.IamportResponseException;
import com.siot.IamportRestClient.response.IamportResponse;
import com.siot.IamportRestClient.response.Payment;

@Controller
@RequestMapping(value = "/pay/*")
public class PayController {
	
	@Inject
	private PayService pService;

	private static final Logger logger = LoggerFactory.getLogger(PayController.class);
	
    // 토큰 발급을 위해 포트원(아임포트)에서 제공해주는 REST API 사용.(maven에 의존성 추가)
    private final IamportClient iamportClientApi;

    // 생성자로 REST API KEY와 SECRET KEY를 입력해서 토큰 생성.
    public PayController() {
        this.iamportClientApi = new IamportClient("7430406235078784",
                "ouSBd4UnZHjQ76SIT5mu9DNunvBcvDKIzaElSYIKgkP5OxB4MywPfdhmLvLvyoVAgtY8TiiljQkHR3KF");
    }
    
    /**
     * impUid로 결제내역 조회
     * @param impUid
     * @return
     * @throws IamportResponseException
     * @throws IOException
     */
    public IamportResponse<Payment> paymentLookup(String impUid) throws IamportResponseException, IOException {
        return iamportClientApi.paymentByImpUid(impUid);
    }
    
    // http://localhost:8088/pay/login
	// 로그인 GET
	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public void loginGET() throws Exception {
		logger.debug("loginGET() 실행");
		logger.debug("/login.jsp 뷰 연결");
		
	}
	
	// 로그인 - POST
	@RequestMapping(value = "/login",method = RequestMethod.POST)
	public String memberLoginPOST(MemberVO vo, HttpSession session) {
		logger.debug(" login.jsp ->LoginPOST() 호출 ");
		
		logger.debug(" 로그인 정보 vo : " + vo);
		
		MemberVO resultVO = pService.memberLogin(vo);
		
		String addr ="";
		if(resultVO == null) {
			logger.debug(" 로그인 실패! -> 다시 로그인 ");
			addr = "/member/login";
		} else {
			logger.debug(" 로그인 성공!! -> 페이 충전 페이지 이동 ");
			
			session.setAttribute("id", resultVO.getUserid());

			addr = "/pay/payCharge";
		}		
		
		return "redirect:"+addr;
	}
	
	// http://localhost:8088/pay/payCharge
	// 충전페이지 GET
	@RequestMapping(value = "/payCharge", method = RequestMethod.GET)
	public void PayChargeGET(HttpSession session, Model model) throws Exception {
		logger.debug("payChargeGET() 실행");
		logger.debug("/payCharge.jsp 뷰 연결");
		
		String id = (String) session.getAttribute("id");
		logger.debug(" id : "+id);
		
		PayVO pResultVO = pService.memberPay(id);
		model.addAttribute("pResultVO", pResultVO);
	}
	
	// 충전페이지 POST (결제 정보 검증 후 충전)
	@ResponseBody
	@RequestMapping(value = "/vertifyIamport", method = RequestMethod.POST)
	public String PayChargePOST(@RequestBody Map<String,String> result, Model model) throws Exception {
		logger.debug("payChargePOST() 실행");
		logger.debug(" result : " + result);
		
    	// 실제 결제금액 조회를 위한 포트원 서버쪽에서 id
        String impUid = result.get("imp_uid");
        
        // 실제로 유저가 결제한 금액
        int amount = Integer.parseInt(result.get("PAY_AMOUNT"));
		
		try {
	        // 포트원 서버쪽에 결제된 정보 조회
	        // paymentByImpUid 는 포트원에서 제공해주는 API인 결제내역 조회(/payments/{imp_uid})의 역할을 함.
	        IamportResponse<Payment> irsp = paymentLookup(impUid);
	        
			PayInfoVO pivo = new PayInfoVO();
			
			//pivo.setPAY_ID(Integer.parseInt(result.get("PAY_ID")));
			pivo.setPAY_ID(result.get("PAY_ID"));
			pivo.setUSER_ID(result.get("USER_ID"));
			pivo.setPAY_TYPE(result.get("PAY_TYPE"));
			pivo.setPAY_STATE(result.get("PAY_STATE"));
			pivo.setPAY_AMOUNT(Integer.parseInt(result.get("PAY_AMOUNT")));
			pivo.setCHARGE_TYPE(result.get("CHARGE_TYPE"));
			//pivo.setCHARGE_TYPE(Integer.parseInt(result.get("CHARGE_TYPE")));
			
			logger.debug(" PayInfoVO : " + pivo);
			
	        // 서비스 -> DAO의 페이충전 검증 동작 실행
	        pService.verifyIamportService(irsp, amount, pivo);
	        
	        // 조건 추가 필요(페이 충전 검증이 실패하면 메서드 실행 x)
	        pService.updatePay(pivo);
	        
		} catch (Exception e) {
			logger.debug(" DAO의 페이충전 검증 동작 실패! ");
			e.printStackTrace();
		}
		
        logger.debug(" DAO의 페이충전 검증 동작 성공! ");
        logger.debug(" DAO의 페이충전 완료! ");
        
		// 삽입 완료 후 충전완료 페이지로 이동
		return "payChargeSuccess";
		
	}
	
	// 충전완료 GET
	// http://localhost:8088/pay/payChargeSuccess
	@RequestMapping(value = "/payChargeSuccess", method = RequestMethod.GET)
	public void payChargeSuccessGET(HttpSession session, Model model) throws Exception {
		logger.debug("payChargeSuccessGET() 실행");
		logger.debug("/payChargeSuccess.jsp 뷰 연결");
		
		String id = (String) session.getAttribute("id");
		logger.debug(" id : "+id);
		
		PayVO pResultVO = pService.memberPay(id);
		model.addAttribute("pResultVO", pResultVO);
	}
	
	// 충전취소 GET
	
	// 충전취소 POST
	
	// 환불 GET
	// http://localhost:8088/pay/payRefund
	@RequestMapping(value = "/payRefund", method = RequestMethod.GET)
	public void payRefundGET(HttpSession session, Model model) throws Exception {
		logger.debug("payRefundGET() 실행");
		logger.debug("/payRefund.jsp 뷰 연결");
		
		String id = (String) session.getAttribute("id");
		logger.debug(" id : "+id);
		
		PayVO pResultVO = pService.memberPay(id);
		model.addAttribute("pResultVO", pResultVO);
		
	}
	
	// 환불 POST
	@ResponseBody
	@RequestMapping(value = "/payRefund", method = RequestMethod.POST)
	public String payRefundPOST(@RequestBody Map<String,String> result,
			PayVO pvo, HttpSession session, Model model) throws Exception {
		
		logger.debug("payRefundPOST() 실행");
		
		String id = (String) session.getAttribute("id");
		
		PayVO pResultVO = pService.memberPay(id);
		
		logger.debug(" PayVO : " + pResultVO);
		
		try {     
			PayInfoVO pivo = new PayInfoVO();
			
			pivo.setPAY_ID(pResultVO.getPAYID());
			pivo.setUSER_ID(id);
			pivo.setPAY_TYPE(result.get("PAY_TYPE"));
			pivo.setPAY_STATE(result.get("PAY_STATE"));
			pivo.setPAY_AMOUNT(Integer.parseInt(result.get("PAY_AMOUNT")));
			pivo.setBANK(result.get("BANK"));
			pivo.setACCOUNT(result.get("ACCOUNT"));
			pivo.setACCOUNT_HOLDER(result.get("ACCOUNT_HOLDER"));
			
			logger.debug(" PayInfoVO : " + pivo);
			
			pService.insertPayInfoRefund(pivo, session);
			
			pService.updatePayRefund(pivo);
	        
		} catch (Exception e) {
			logger.debug(" DAO의 페이환불 신청 동작 실패! ");
			e.printStackTrace();
		}
		
		logger.debug(" DAO의 페이환불 신청 동작 성공! ");
		
		return "payRefundSuccess";
	}

}
