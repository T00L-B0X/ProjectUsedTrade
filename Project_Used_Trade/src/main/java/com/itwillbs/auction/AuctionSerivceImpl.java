package com.itwillbs.auction;

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
	
	
	
}
