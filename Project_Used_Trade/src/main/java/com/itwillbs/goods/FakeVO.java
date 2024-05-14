package com.itwillbs.goods;

public class FakeVO {
	private String goods_title;
	private int current_price;

	public String getGoods_title() {
		return goods_title;
	}

	public void setGoods_title(String goods_title) {
		this.goods_title = goods_title;
	}

	public int getCurrent_price() {
		return current_price;
	}

	public void setCurrent_price(int current_price) {
		this.current_price = current_price;
	}

	@Override
	public String toString() {
		return "FakeVO [goods_title=" + goods_title + ", current_price=" + current_price + "]";
	};

}
