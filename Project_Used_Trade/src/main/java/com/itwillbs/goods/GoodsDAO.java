package com.itwillbs.goods;

import java.sql.Timestamp;
import java.util.List;

public interface GoodsDAO {

	public List<GoodsVO> selectGoodsList();

	public GoodsVO selectGoodsInfo(int goods_id);

	public List<GoodsVO> selectSearchList(GoodsVO gvo);

	public List<GoodsVO> selectCateList(GoodsVO gvo);

	public int selectNewGoodsId();

	public int insertImageInfo(GoodsImgVO ivo);

	public int insertGoodsInfo(GoodsVO gvo);

	public List<String> selectImgList(int goods_id);

	public Timestamp selectRegdate(int newGoodsId);

	public int updateCurrentPrice(GoodsVO gvo);

	public int selectNowPrice(int goods_id);

	public List<FakeVO> fakeVO();
}
