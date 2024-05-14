package com.itwillbs.auction;

import java.util.List;

import javax.inject.Inject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class AuctionSerivceImpl implements AuctionService {
	
	private static final Logger logger = LoggerFactory.getLogger(AuctionSerivceImpl.class);
	
	@Inject
	private AuctionDAO ADao;

	@Override
	public void insertAuctionInfo(AuctionVO avo) {
		logger.debug("insertAuctionInfo(AuctionVO avo)");
		
		ADao.insertAuctionInfo(avo);
	}

	@Override
	public AuctionVO getAuctionInfo(int goods_id) {
		
		return ADao.selectAuctionInfo(goods_id);
	}
	
	@Override
	public List<AuctionVO> sellInfo(String userid) {
		
		return ADao.sellInfo(userid);
	}

	@Override
	public int insertRecord(AuctionRecordVO arvo) {
		
		return ADao.insertRecord(arvo);
	}

	@Override
	public int getBidCount(int goods_id) {
		logger.debug(" getBidCount(int goods_id) ");
		
		return ADao.selectBidCount(goods_id);
	}

	@Override
	public List<AuctionRecordVO> getRecordList(int goods_id) {
		logger.debug(" List<AuctionRecordVO> getRecordList(int goods_id) ");
		return ADao.selectRecordList(goods_id);
	}

	@Override
	public int getInstantPrice(int goods_id) {
		
		return ADao.selectInstantPrice(goods_id);
	}

	@Override
	public void updateAuctionStatus(int goods_id) {
		
		ADao.updateAuctionStatus(goods_id);
	}

	@Override
	public int updateAuStatusEnd(int goods_id) {
		
		return ADao.updateAuStatusEnd(goods_id);		
	}
	

	
	
	
}
