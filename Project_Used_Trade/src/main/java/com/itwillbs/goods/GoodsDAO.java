package com.itwillbs.goods;

import java.util.List;

public interface GoodsDAO {

	public List<GoodsVO> selectGoodsList();

	public GoodsVO selectGoodsInfo(int goods_id);

	public List<GoodsVO> selectSearchList(GoodsVO gvo);

	public List<GoodsVO> selectCateList(GoodsVO gvo);

}
