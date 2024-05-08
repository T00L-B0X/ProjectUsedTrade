package com.itwillbs.auction;

public interface AuctionDAO {

	public void insertAuctionInfo(AuctionVO avo);

	public AuctionVO selectAuctionInfo(int goods_id);

	public int insertRecord(AuctionRecordVO arvo);

}
