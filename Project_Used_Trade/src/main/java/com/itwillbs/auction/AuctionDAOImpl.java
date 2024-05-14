package com.itwillbs.auction;

import java.util.List;

import javax.inject.Inject;

import org.apache.ibatis.session.SqlSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

@Repository
public class AuctionDAOImpl implements AuctionDAO {
	
	private static final Logger logger = LoggerFactory.getLogger(AuctionDAOImpl.class);
	
	private static final String NAMESPACE = "com.itwillbs.mapper.AuctionMapper";

	@Inject
	private SqlSession sqlSession;
	@Override
	public void insertAuctionInfo(AuctionVO avo) {
		sqlSession.insert(NAMESPACE+".insertAuctionInfo", avo);
		
	}
	@Override
	public AuctionVO selectAuctionInfo(int goods_id) {
		logger.debug(" selectAuctionInfo(int goods_id) ");
		logger.debug("글번호"+goods_id);
		return sqlSession.selectOne(NAMESPACE+".selectAuctionInfo", goods_id);
	}
	@Override
	public List<AuctionVO> sellInfo(String userid) {
		
		return sqlSession.selectList(NAMESPACE+".sellinfo", userid);
	}
	@Override
	public int insertRecord(AuctionRecordVO arvo) {
		return sqlSession.insert(NAMESPACE+".insertRecord", arvo);
	}
	@Override
	public int selectBidCount(int goods_id) {
		logger.debug(" getBidCount(int goods_id) ");
		return sqlSession.selectOne(NAMESPACE+".selectBidCount", goods_id);
	}
	@Override
	public List<AuctionRecordVO> selectRecordList(int goods_id) {
		logger.debug(" List<AuctionRecordVO> selectRecordList(int goods_id) ");
		return sqlSession.selectList(NAMESPACE+".selectAuctionRecord", goods_id);
	}
	@Override
	public int selectInstantPrice(int goods_id) {
		logger.debug(" selectInstantPrice(int goods_id) ");
		return sqlSession.selectOne(NAMESPACE+".selectInstantPrice", goods_id);
	}
	@Override
	public void updateAuctionStatus(int goods_id) {
		sqlSession.update(NAMESPACE+".updateAuctionStatus", goods_id);		
	}
	@Override
	public int updateAuStatusEnd(int goods_id) {
		return sqlSession.update(NAMESPACE+".updateAuStatusEnd", goods_id);
	}
	@Override
	public AuctionRecordVO selectBuyInfo(int goods_id) {
		
		return sqlSession.selectOne(NAMESPACE+".selectBuyInfo",goods_id);
	}
	
	
	
	
	
}
