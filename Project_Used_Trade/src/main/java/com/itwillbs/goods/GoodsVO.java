package com.itwillbs.goods;

import java.sql.Timestamp;
import java.time.Duration;

public class GoodsVO {
	private int goods_id;
	private String user_id;
	private String category;
	private String goods_title;
	private String status;
	private String goods_info;
	private String goods_repimg;
	private int start_price;
	private int current_price;
	private int instant_price;
	private String area;
	private String transact_type;
	private int auction_time;
	private int viewcnt;
	private String updateid;
	private Timestamp regdate;
	private Timestamp updatedate;
	private String keyword;

	public int getGoods_id() {
		return goods_id;
	}

	public void setGoods_id(int goods_id) {
		this.goods_id = goods_id;
	}

	public String getUser_id() {
		return user_id;
	}

	public void setUser_id(String user_id) {
		this.user_id = user_id;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public String getGoods_title() {
		return goods_title;
	}

	public void setGoods_title(String goods_title) {
		this.goods_title = goods_title;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getGoods_info() {
		return goods_info;
	}

	public void setGoods_info(String goods_info) {
		this.goods_info = goods_info;
	}

	public String getGoods_repimg() {
		return goods_repimg;
	}

	public void setGoods_repimg(String goods_repimg) {
		this.goods_repimg = goods_repimg;
	}

	public int getStart_price() {
		return start_price;
	}

	public void setStart_price(int start_price) {
		this.start_price = start_price;
	}

	public int getCurrent_price() {
		return current_price;
	}

	public void setCurrent_price(int current_price) {
		this.current_price = current_price;
	}

	public int getInstant_price() {
		return instant_price;
	}

	public void setInstant_price(int instant_price) {
		this.instant_price = instant_price;
	}

	public String getArea() {
		return area;
	}

	public void setArea(String area) {
		this.area = area;
	}

	public String getTransact_type() {
		return transact_type;
	}

	public void setTransact_type(String transact_type) {
		this.transact_type = transact_type;
	}

	public int getAuction_time() {
		return auction_time;
	}

	public void setAuction_time(int auction_time) {
		this.auction_time = auction_time;
	}

	public int getViewcnt() {
		return viewcnt;
	}

	public void setViewcnt(int viewcnt) {
		this.viewcnt = viewcnt;
	}

	public String getUpdateid() {
		return updateid;
	}

	public void setUpdateid(String updateid) {
		this.updateid = updateid;
	}

	public Timestamp getRegdate() {
		return regdate;
	}

	public void setRegdate(Timestamp regdate) {
		this.regdate = regdate;
	}

	public Timestamp getUpdatedate() {
		return updatedate;
	}

	public void setUpdatedate(Timestamp updatedate) {
		this.updatedate = updatedate;
	}

	public String getKeyword() {
		return keyword;
	}

	public void setKeyword(String keyword) {
		this.keyword = keyword;
	}

	@Override
	public String toString() {
		return "GoodsVO [goods_id=" + goods_id + ", user_id=" + user_id + ", category=" + category + ", goods_title="
				+ goods_title + ", status=" + status + ", goods_info=" + goods_info + ", goods_repimg=" + goods_repimg
				+ ", start_price=" + start_price + ", current_price=" + current_price + ", instant_price="
				+ instant_price + ", area=" + area + ", transact_type=" + transact_type + ", auction_time="
				+ auction_time + ", viewcnt=" + viewcnt + ", updateid=" + updateid + ", regdate=" + regdate
				+ ", updatedate=" + updatedate + ", keyword=" + keyword + "]";
	}
	
	
}