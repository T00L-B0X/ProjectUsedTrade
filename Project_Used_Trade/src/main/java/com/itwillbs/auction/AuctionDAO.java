package com.itwillbs.auction;

import java.util.List;

public interface AuctionDAO {

	public void insertAuctionInfo(AuctionVO avo);

	public AuctionVO selectAuctionInfo(int goods_id);
	
	public List<AuctionVO> sellInfo(String userid);

	public int insertRecord(AuctionRecordVO arvo);

	public int selectBidCount(int goods_id);

	public List<AuctionRecordVO> selectRecordList(int goods_id);

	public int selectInstantPrice(int goods_id);

	public void updateAuctionStatus(int goods_id);

	public int updateAuStatusEnd(int goods_id);

}
