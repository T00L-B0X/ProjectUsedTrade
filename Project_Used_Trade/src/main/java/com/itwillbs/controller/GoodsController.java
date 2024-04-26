package com.itwillbs.controller;

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
public class GoodsController {
	
	@Inject
	private GoodsService gService;
	
	private static final Logger logger = LoggerFactory.getLogger(GoodsController.class);
	// http://localhost:8088/goods/goodsMain
		@RequestMapping(value = "/goods/goodsMain", method = RequestMethod.GET)
		public void GoodsListGET() throws Exception {
			logger.debug(" GoodsListGET() 호출 ");

		}
		
		@RequestMapping(value = "/goods/read", method = RequestMethod.GET)
		public void GoodsInfoGET(@RequestParam("goods_id") int goods_id, Model model) throws Exception {
			logger.debug(" GoodsInfoGET() 호출 ");
			// 조회수 증가
			
			// 사진 전부 들고오기
			
			// 글정보 저장
			GoodsVO gvo = gService.getGoodsInfo(goods_id);
			model.addAttribute(gvo);
			logger.debug("gvo : " + gvo);
		}
}
