package com.itwillbs.goods;

import java.util.List;

public interface GoodsService {

	public List<GoodsVO> getGoodsList();

	public GoodsVO getGoodsInfo(int goods_id);

	public List<GoodsVO> getSearchList(GoodsVO gvo);

	public List<GoodsVO> getCateList(GoodsVO gvo);

}
