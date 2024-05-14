package com.itwillbs.payment;

import java.security.Principal;
import java.util.Map;

import javax.inject.Inject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.itwillbs.goods.GoodsService;
import com.itwillbs.goods.GoodsVO;
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
	
	@Inject
	private GoodsService gService;
	
	private static final Logger logger = LoggerFactory.getLogger(PaymentController.class);
	
	// 결제 페이지 GET
	// http://localhost:8088/payment/purchase
	// http://localhost:8088/payment/purchase/{goods_id}
	// http://localhost:8088/payment/purchase/1
	// http://localhost:8088/payment/purchase?goods_id=1
	@RequestMapping(value = "/purchase", method = RequestMethod.GET)
	public void purchaseGET(@RequestParam("goods_id") int goods_id, Principal principal, Model model) throws Exception {
		logger.debug("purchaseGET() 실행");
		logger.debug("/purchase.jsp 뷰 연결");
		
		GoodsVO goodsInfo = gService.getGoodsInfo(goods_id);
		logger.debug("goodsInfo : " + goodsInfo);
		
		String id = principal.getName();
		logger.debug(" id : "+id);
		
		PayVO pResultVO = pService.memberPay(id);
		
		// 물건 정보와 페이 정보를 모델에 추가
		model.addAttribute("goodsVO", goodsInfo);
		model.addAttribute("pResultVO", pResultVO);
		
	}
	
	// 결제 페이지 POST
	@ResponseBody
	@RequestMapping(value = "/purchase", method = RequestMethod.POST)
	public String purchasePOST(@RequestBody Map<String,String> result, Principal principal) throws Exception {
		logger.debug("purchasePOST() 실행");
		logger.debug(" result : " + result);
		
		try {

			// 물건의 가격 정보를 가져와서 결제 금액과 비교(결제 검증)
			GoodsVO goodsInfo = gService.getGoodsInfo(Integer.parseInt(result.get("GOODS_ID")));
			
			// 물건의 가격
			int goodsPrice = goodsInfo.getInstant_price();
			
			PaymentVO pmvo = new PaymentVO();
			
			pmvo.setPAYMENT_ID(Integer.parseInt(result.get("PAYMENT_ID")));
			pmvo.setUSERID(result.get("USERID"));
			pmvo.setPAYMENT_AMOUNT(Integer.parseInt(result.get("PAYMENT_AMOUNT")));
			pmvo.setPAYMENT_STATE(result.get("PAYMENT_STATE"));
			pmvo.setDELIVERY_TYPE(result.get("DELIVERY_TYPE"));
			pmvo.setGOODS_ID(Integer.parseInt(result.get("GOODS_ID")));
			
			logger.debug(" PaymentVO : " + pmvo);

			// 결제 정보 저장 (결제 정보 검증 포함)
			pmService.insertPayment(pmvo, goodsPrice);
			
			// 페이 업데이트 (결제 금액만큼 차감)
	        pmService.updateDeductPay(pmvo);
			
		} catch (Exception e) {
			logger.debug(" DAO의 결제 검증 동작 실패! ");
			e.printStackTrace();
		}
		
		logger.debug(" DAO의 결제 검증 동작 성공! ");
		
		// 완료 후 결제완료 페이지로 이동
		return "purchaseSuccess";
	}
	
	// 결제완료 페이지 GET
	@RequestMapping(value = "/purchaseSuccess", method = RequestMethod.GET)
	public void purchaseSuccessGET(Principal principal, Model model) throws Exception {
		logger.debug("purchaseSuccessGET() 실행");
		logger.debug("/purchaseSuccessGET.jsp 뷰 연결");
		
		String id = principal.getName();
		logger.debug(" id : "+id);
		
		PayVO pResultVO = pService.memberPay(id);
		
		model.addAttribute("pResultVO", pResultVO);
		
	}
	
	// 결제취소 GET
	
	// 결제취소 POST
}
