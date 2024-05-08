package com.itwillbs.auction;

import javax.inject.Inject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.itwillbs.goods.GoodsService;
import com.itwillbs.goods.GoodsVO;

@RestController
public class AuctionRestController {
	private static final Logger logger = LoggerFactory.getLogger(AuctionRestController.class);
	@Inject
	private GoodsService gService;
	
	@Inject
	private AuctionService aService;
	
	@RequestMapping(value="/auction/{goods_id}", method=RequestMethod.GET)
	public ResponseEntity<AuctionVO> getAuctionInfo(
			@PathVariable("goods_id") int goods_id) throws Exception{
		logger.debug(" getAuctionInfo() 호출 ");
		AuctionVO avo = aService.getAuctionInfo(goods_id);
		
		return new ResponseEntity<AuctionVO>(avo,HttpStatus.OK);
	}
	
	@RequestMapping(value="/goods/{goods_id}", method=RequestMethod.GET)
	public ResponseEntity<GoodsVO> getGoodsInfo(
			@PathVariable("goods_id") int goods_id) throws Exception{
		logger.debug(" getGoodsInfo() 호출 ");
		GoodsVO gvo = gService.getGoodsInfo(goods_id);
		
		return new ResponseEntity<GoodsVO>(gvo,HttpStatus.OK);
	}
	
}
