package com.itwillbs.auction;

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
		return sqlSession.selectOne(NAMESPACE+".selectAuctionInfo", goods_id);
	}
	@Override
	public int insertRecord(AuctionRecordVO arvo) {
		return sqlSession.insert(NAMESPACE+".insertRecord", arvo);
	}
	
	
	
	
	
}
