package com.itwillbs.goods;

import java.sql.Timestamp;
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

	@Override
	public int getNewGoodsId() {
		logger.debug("getNewGoodsId()");
		return GDao.selectNewGoodsId();		
	}

	@Override
	public int insertImageInfo(GoodsImgVO ivo) {
		logger.debug("insertImageInfo(GoodsImgVO ivo)");
		logger.debug("ivo : "+ivo.getGoods_id() + ivo.getGoods_img());
		return GDao.insertImageInfo(ivo);
		
	}

	@Override
	public int insertGoodsInfo(GoodsVO gvo) {
		logger.debug("insertGoodsInfo(GoodsVO gvo)");
		return GDao.insertGoodsInfo(gvo);
	}

	@Override
	public List<String> getImgList(int goods_id) {
		logger.debug("getImgList(int goods_id)");
		return GDao.selectImgList(goods_id);
	}

	@Override
	public Timestamp getRegdate(int newGoodsId) {
		logger.debug("getRegdate(int newGoodsId)");
		return GDao.selectRegdate(newGoodsId);
	}

	@Override
	public int modifyCurrentPrice(GoodsVO gvo) {
		
		return GDao.updateCurrentPrice(gvo);
	}

	@Override
	public int getNowPrice(int goods_id) {
		logger.debug(" getNowPrice(int goods_id) ");
		logger.debug("goods_id : " + goods_id);
		return GDao.selectNowPrice(goods_id);
	}

	
}
