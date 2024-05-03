package com.itwillbs.auction;

import java.sql.Timestamp;

public class AuctionVO {
	private int au_no;
	private int goods_id;
	private String au_userid;
	private String au_title;
	private int instant_price;
	private Timestamp start_time;
	private Timestamp end_time;
	private int start_price;
	private int au_status;
	private Timestamp regdate; 
	private Timestamp updatedate;
	
	public int getAu_no() {
		return au_no;
	}
	public void setAu_no(int au_no) {
		this.au_no = au_no;
	}
	public int getGoods_id() {
		return goods_id;
	}
	public void setGoods_id(int goods_id) {
		this.goods_id = goods_id;
	}
	public String getAu_userid() {
		return au_userid;
	}
	public void setAu_userid(String au_userid) {
		this.au_userid = au_userid;
	}
	public String getAu_title() {
		return au_title;
	}
	public void setAu_title(String au_title) {
		this.au_title = au_title;
	}
	public int getInstant_price() {
		return instant_price;
	}
	public void setInstant_price(int instant_price) {
		this.instant_price = instant_price;
	}
	public Timestamp getStart_time() {
		return start_time;
	}
	public void setStart_time(Timestamp start_time) {
		this.start_time = start_time;
	}
	public Timestamp getEnd_time() {
		return end_time;
	}
	public void setEnd_time(Timestamp end_time) {
		this.end_time = end_time;
	}
	public int getStart_price() {
		return start_price;
	}
	public void setStart_price(int start_price) {
		this.start_price = start_price;
	}
	public int getAu_status() {
		return au_status;
	}
	public void setAu_status(int au_status) {
		this.au_status = au_status;
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
	@Override
	public String toString() {
		return "AuctionVO [au_no=" + au_no + ", goods_id=" + goods_id + ", au_userid=" + au_userid + ", au_title="
				+ au_title + ", instant_price=" + instant_price + ", start_time=" + start_time + ", end_time="
				+ end_time + ", start_price=" + start_price + ", au_status=" + au_status + ", regdate=" + regdate
				+ ", updatedate=" + updatedate + "]";
	}
	
	
	
}
