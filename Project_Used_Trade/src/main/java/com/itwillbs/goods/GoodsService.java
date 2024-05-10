package com.itwillbs.goods;

import java.sql.Timestamp;
import java.util.List;

public interface GoodsService {

	public List<GoodsVO> getGoodsList();

	public GoodsVO getGoodsInfo(int goods_id);

	public List<GoodsVO> getSearchList(GoodsVO gvo);

	public List<GoodsVO> getCateList(GoodsVO gvo);

	public int getNewGoodsId();

	public int insertImageInfo(GoodsImgVO ivo);

	public int insertGoodsInfo(GoodsVO gvo);

	public List<String> getImgList(int goods_id);

	public Timestamp getRegdate(int newGoodsId);

	public int modifyCurrentPrice(GoodsVO gvo);

	public int getNowPrice(int goods_id);


}
