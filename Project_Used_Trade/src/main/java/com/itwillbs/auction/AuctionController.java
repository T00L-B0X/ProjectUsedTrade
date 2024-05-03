package com.itwillbs.auction;

import javax.inject.Inject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
						Model model) {
		logger.debug(" bidGet() 호출 ");
		AuctionVO avo = aService.getAuctionInfo(goods_id);
		model.addAttribute("avo", avo);
		logger.debug("avo : " + avo);
		
		GoodsVO gvo = gService.getGoodsInfo(goods_id);
		model.addAttribute("gvo", gvo);
		logger.debug("gvo : " + gvo);
	}
}