package com.itwillbs.goods;

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
		return sqlSession.selectList(NAMESPACE+".selectGoodsList");
	}

	@Override
	public GoodsVO selectGoodsInfo(int goods_id) {
		logger.debug(" selectGoodsInfo() 실행 ");
		return sqlSession.selectOne(NAMESPACE+".selectGoodsInfo", goods_id);
	}

	@Override
	public List<GoodsVO> selectSearchList(GoodsVO gvo) {
		logger.debug(" selectSearchList(GoodsVO gvo) 실행 ");
		return sqlSession.selectList(NAMESPACE+".selectSearchList", gvo);
	}

	@Override
	public List<GoodsVO> selectCateList(GoodsVO gvo) {
		logger.debug(" selectCateList(GoodsVO gvo) 실행 ");
		return sqlSession.selectList(NAMESPACE+".selectCateList", gvo);
	}
	
	
	
}