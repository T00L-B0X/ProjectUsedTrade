package com.itwillbs.goods;

import java.util.List;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/goods")
public class GoodsRestController {

	private static final Logger logger = LoggerFactory.getLogger(GoodsRestController.class);
	@Inject
	private GoodsService gService;

	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public ResponseEntity<List<GoodsVO>> goodsList() throws Exception {
		logger.debug(" goodsList() 호출 ");

		List<GoodsVO> goodsList = gService.getGoodsList();

		return new ResponseEntity<List<GoodsVO>>(goodsList, HttpStatus.OK);
	}

	@RequestMapping(value = "/list/search", method = RequestMethod.GET)
	public ResponseEntity<List<GoodsVO>> goodsSearchList(@RequestParam("keyword") String keyword,
			@RequestParam("category") String category) throws Exception {
		logger.debug(" goodsSearchList() 호출 ");
		GoodsVO gvo = new GoodsVO();
		gvo.setKeyword(keyword);
		gvo.setCategory(category);

		List<GoodsVO> goodsSearchList = gService.getSearchList(gvo);

		return new ResponseEntity<List<GoodsVO>>(goodsSearchList, HttpStatus.OK);
	}

	@RequestMapping(value = "/read/{goods_id}", method = RequestMethod.GET)
	public ResponseEntity<GoodsVO> goodsInfo(@PathVariable("goods_id") int goods_id) throws Exception {
		logger.debug(" goosInfo() 호출 ");
		GoodsVO goodsInfo = gService.getGoodsInfo(goods_id);
		logger.debug("goodsInfo : " + goodsInfo);

		return new ResponseEntity<GoodsVO>(goodsInfo, HttpStatus.OK);

	}

}