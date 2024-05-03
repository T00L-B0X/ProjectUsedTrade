package com.itwillbs.auction;

public interface AuctionService {

	public void insertAuctionInfo(AuctionVO avo);

	public AuctionVO getAuctionInfo(int goods_id);

}
