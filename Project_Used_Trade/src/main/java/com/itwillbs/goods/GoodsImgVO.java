package com.itwillbs.goods;

public class GoodsImgVO {
	private int img_no;
	private int goods_id;
	private String goods_img;
	
	public int getImg_no() {
		return img_no;
	}
	public void setImg_no(int img_no) {
		this.img_no = img_no;
	}
	public int getGoods_id() {
		return goods_id;
	}
	public void setGoods_id(int goods_id) {
		this.goods_id = goods_id;
	}
	public String getGoods_img() {
		return goods_img;
	}
	public void setGoods_img(String goods_img) {
		this.goods_img = goods_img;
	}
	@Override
	public String toString() {
		return "GoodsImgVO [img_no=" + img_no + ", goods_id=" + goods_id + ", goods_img=" + goods_img + "]";
	}
	
}