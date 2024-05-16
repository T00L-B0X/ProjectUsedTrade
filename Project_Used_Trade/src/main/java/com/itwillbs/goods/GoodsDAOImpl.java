package com.itwillbs.goods;

import java.sql.Timestamp;
import java.util.List;

import javax.inject.Inject;

import org.apache.ibatis.session.SqlSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

@Repository
public class GoodsDAOImpl implements GoodsDAO {

	private static final Logger logger = LoggerFactory.getLogger(GoodsDAOImpl.class);

	private static final String NAMESPACE = "com.itwillbs.mapper.GoodsMapper";

	@Inject
	private SqlSession sqlSession;

	@Override
	public List<GoodsVO> selectGoodsList() {
		logger.debug("selectGoodsList() 실행 ");
		return sqlSession.selectList(NAMESPACE + ".selectGoodsList");
	}

	@Override
	public GoodsVO selectGoodsInfo(int goods_id) {
		logger.debug(" selectGoodsInfo() 실행 ");
		return sqlSession.selectOne(NAMESPACE + ".selectGoodsInfo", goods_id);
	}

	@Override
	public List<GoodsVO> selectSearchList(GoodsVO gvo) {
		logger.debug(" selectSearchList(GoodsVO gvo) 실행 ");
		return sqlSession.selectList(NAMESPACE + ".selectSearchList", gvo);
	}

	@Override
	public List<GoodsVO> selectCateList(GoodsVO gvo) {
		logger.debug(" selectCateList(GoodsVO gvo) 실행 ");
		return sqlSession.selectList(NAMESPACE + ".selectCateList", gvo);
	}

	@Override
	public int selectNewGoodsId() {
		logger.debug(" selectNewGoodsId() ");
		return sqlSession.selectOne(NAMESPACE + ".selectNewGoodsId");
	}

	@Override
	public int insertImageInfo(GoodsImgVO ivo) {
		logger.debug(" insertImageInfo(GoodsImgVO ivo) ");
		logger.debug("ivo : " + ivo.getGoods_id() + ivo.getGoods_img());
		return sqlSession.insert(NAMESPACE + ".insertImageInfo", ivo);
	}

	@Override
	public int insertGoodsInfo(GoodsVO gvo) {
		logger.debug("insertGoodsInfo(GoodsVO gvo)");
		return sqlSession.insert(NAMESPACE + ".insertGoodsInfo", gvo);
	}

	@Override
	public List<String> selectImgList(int goods_id) {
		logger.debug("selectImgList(int goods_id)");
		return sqlSession.selectList(NAMESPACE + ".selectImgList", goods_id);
	}

	@Override
	public Timestamp selectRegdate(int newGoodsId) {
		logger.debug("selectRegdate(int newGoodsId)");
		return sqlSession.selectOne(NAMESPACE + ".selectRegdate", newGoodsId);
	}

	@Override
	public int updateCurrentPrice(GoodsVO gvo) {

		return sqlSession.update(NAMESPACE + ".updateCurrentPrice", gvo);
	}

	@Override
	public int selectNowPrice(int goods_id) {
		logger.debug(" selectNowPrice(int goods_id) ");
		logger.debug("goods_id : " + goods_id);
		return sqlSession.selectOne(NAMESPACE + ".selectNowPrice", goods_id);
	}

	@Override
	public List<FakeVO> fakeVO() {

		return sqlSession.selectList(NAMESPACE + ".FakeVO");
	}

}