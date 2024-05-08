package com.itwillbs.auction;

import java.sql.Timestamp;

public class AuctionRecordVO {
	private int ar_no;
	private int goods_id;
	private Timestamp bid_time;
	private int bid_price;
	private String ar_userid;
	private int status;
	private Timestamp regdate;
	
	public int getAr_no() {
		return ar_no;
	}
	public void setAr_no(int ar_no) {
		this.ar_no = ar_no;
	}
	public int getGoods_id() {
		return goods_id;
	}
	public void setGoods_id(int goods_id) {
		this.goods_id = goods_id;
	}
	public Timestamp getBid_time() {
		return bid_time;
	}
	public void setBid_time(Timestamp bid_time) {
		this.bid_time = bid_time;
	}
	public int getBid_price() {
		return bid_price;
	}
	public void setBid_price(int bid_price) {
		this.bid_price = bid_price;
	}
	public String getAr_userid() {
		return ar_userid;
	}
	public void setAr_userid(String ar_userid) {
		this.ar_userid = ar_userid;
	}
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	public Timestamp getRegdate() {
		return regdate;
	}
	public void setRegdate(Timestamp regdate) {
		this.regdate = regdate;
	}
	@Override
	public String toString() {
		return "AuctionRecordVO [ar_no=" + ar_no + ", goods_id=" + goods_id + ", bid_time=" + bid_time + ", bid_price="
				+ bid_price + ", ar_userid=" + ar_userid + ", status=" + status + ", regdate=" + regdate + "]";
	}
	
	
	
}

