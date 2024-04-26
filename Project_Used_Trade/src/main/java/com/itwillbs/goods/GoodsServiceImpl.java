package com.itwillbs.goods;

import java.util.List;

import javax.inject.Inject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class GoodsServiceImpl implements GoodsService{

	private static final Logger logger = LoggerFactory.getLogger(GoodsServiceImpl.class);

	@Inject
	private GoodsDAO GDao;

	@Override
	public List<GoodsVO> getGoodsList() {
		logger.debug(" getGoodsList() 호출 ");
		return GDao.selectGoodsList();
	}

	@Override
	public GoodsVO getGoodsInfo(int goods_id) {
		logger.debug(" getGoodsInfo() 호출 ");
		return GDao.selectGoodsInfo(goods_id);
	}

	@Override
	public List<GoodsVO> getSearchList(GoodsVO gvo) {
		logger.debug(" getSearchList(GoodsVO gvo) ");
		return GDao.selectSearchList(gvo);
	}

	@Override
	public List<GoodsVO> getCateList(GoodsVO gvo) {
		logger.debug(" getCateList(GoodsVO gvo) ");
		return GDao.selectCateList(gvo);
	}
	
	
	
	

}
