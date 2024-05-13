package com.itwillbs.payment;

import java.security.Principal;
import java.util.Map;

import javax.inject.Inject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.itwillbs.pay.PayInfoVO;
import com.itwillbs.pay.PayService;
import com.itwillbs.pay.PayVO;

@Controller
@RequestMapping(value = "/payment/*")
public class PaymentController {
	
	@Inject
	private PayService pService;
	
	@Inject
	private PaymentService pmService;
	
	private static final Logger logger = LoggerFactory.getLogger(PaymentController.class);
	
	// 결제 페이지 GET
	// http://localhost:8088/payment/purchase
	@RequestMapping(value = "/purchase", method = RequestMethod.GET)
	public void purchaseGET(Principal principal, Model model) throws Exception {
		logger.debug("purchaseGET() 실행");
		logger.debug("/purchase.jsp 뷰 연결");
		
		String id = principal.getName();
		logger.debug(" id : "+id);
		
		PayVO pResultVO = pService.memberPay(id);
		
		// 선택한 물건 정보를 가저옴(생각중)
		
		model.addAttribute("pResultVO", pResultVO);
	}
	
	// 결제 페이지 POST
	@ResponseBody
	@RequestMapping(value = "/purchase", method = RequestMethod.POST)
	public String purchasePOST(@RequestBody Map<String,String> result, Principal principal) throws Exception {
		logger.debug("purchasePOST() 실행");
		logger.debug(" result : " + result);
		
		try {
			// 페이 정보 저장(생각중)

			PaymentVO pmvo = new PaymentVO();
			
			pmvo.setPAYMENT_ID(Integer.parseInt(result.get("PAYMENT_ID")));
			pmvo.setUSERID(result.get("USERID"));
			pmvo.setPAYMENT_AMOUNT(Integer.parseInt(result.get("PAYMENT_AMOUNT")));
			pmvo.setPAYMENT_STATE(result.get("PAYMENT_STATE"));
			pmvo.setDELIVERY_TYPE(result.get("DELIVERY_TYPE"));
			
			logger.debug(" PaymentVO : " + pmvo);

			// 결제 정보 저장 (결제 정보 검증 포함)
			pmService.insertPayment(pmvo);
			
			// 페이 업데이트 (결제 금액만큼 차감)
	        pmService.updateDeductPay(pmvo);
			
		} catch (Exception e) {
			logger.debug(" DAO의 결제 검증 동작 실패! ");
			e.printStackTrace();
		}
		
		logger.debug(" DAO의 결제 검증 동작 성공! ");
		
		// 삽입 완료 후 결제완료 페이지로 이동
		return "purchaseSuccess";
	}
	
	// 결제취소 GET
	
	// 결제취소 POST
}
