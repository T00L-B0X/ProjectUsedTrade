package com.itwillbs.auction;

import java.io.IOException;

import javax.inject.Inject;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.itwillbs.goods.GoodsService;
import com.itwillbs.goods.GoodsVO;

@Controller
public class AuctionController {
	private static final Logger logger = LoggerFactory.getLogger(AuctionController.class);
	
	@Inject
	AuctionService aService;
	@Inject
	GoodsService gService;
	@RequestMapping(value="/auction/bid", method=RequestMethod.GET)
	public void bidGet(@RequestParam("goods_id") int goods_id,
						Model model) throws Exception{
		logger.debug(" bidGet() 호출 ");
		AuctionVO avo = aService.getAuctionInfo(goods_id);
		model.addAttribute("avo", avo);
		logger.debug("avo : " + avo);
		
		GoodsVO gvo = gService.getGoodsInfo(goods_id);
		model.addAttribute("gvo", gvo);
		logger.debug("gvo : " + gvo);
	}
	
	@RequestMapping(value="/auction/bid", method=RequestMethod.POST)
	public void bidPost(@RequestParam("goods_id") int goods_id,
						@RequestParam("current_price") int current_price,
						HttpServletResponse response
						/* 입찰자(로그인한 사용자 id) 가져오기 */) throws Exception{
		logger.debug(" bidPost() 호출 ");
		
		// goods테이블 현재 경매가 update
		GoodsVO gvo = new GoodsVO();
		gvo.setGoods_id(goods_id);
		gvo.setCurrent_price(current_price);
		
		// 실제 db와 입찰하려는 가격을 비교
		int NowPrice = gService.getNowPrice(goods_id);
		int instantPrice = aService.getInstantPrice(goods_id);
		logger.debug("DB의 현재입찰가"+NowPrice);
		// current_price (입력 입찰가)가 NowPrice(db의 현재입찰가)+1000보다 크거나 즉시 입찰가와 같다면 상품 현재입찰가 수정
		if(NowPrice+1000 <= current_price || current_price == instantPrice) {
			int updateResult = gService.modifyCurrentPrice(gvo);
			if(updateResult == 1) {
				logger.debug(" goods테이블 현재 경매가 update 성공 ");				
				// auction record 테이블 insert
				AuctionRecordVO arvo = new AuctionRecordVO();
				Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
				String userid = authentication.getName();
				arvo.setAr_userid(userid);
				arvo.setBid_price(current_price);
				arvo.setGoods_id(goods_id);

				int insertResult = aService.insertRecord(arvo);
				
				if(insertResult == 1) {
					logger.debug(" 경매 기록 추가 성공 ");
				}else {
					logger.debug(" 경매 기록 추가 실패 ");
				}
			}
//			else {
//				logger.debug(" goods테이블 현재 경매가 update 실패 ");
//			}
//		}else {
//			logger.debug("db의 입찰가+1000보다 입력 입찰가가 작다");
//		}
		response.sendRedirect("/auction/bid?goods_id=" + goods_id);
		}
	}
}