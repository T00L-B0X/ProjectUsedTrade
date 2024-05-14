package com.itwillbs.auction;

import java.util.List;

public interface AuctionService {

	public void insertAuctionInfo(AuctionVO avo);

	public AuctionVO getAuctionInfo(int goods_id);

	public int insertRecord(AuctionRecordVO arvo);

	public int getBidCount(int goods_id);

	public List<AuctionRecordVO> getRecordList(int goods_id);

	public int getInstantPrice(int goods_id);

	public void updateAuctionStatus(int goods_id);

	public int updateAuStatusEnd(int goods_id);

	public AuctionRecordVO getBuyInfo(int goods_id);
}
