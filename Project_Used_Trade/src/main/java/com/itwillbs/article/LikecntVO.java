package com.itwillbs.article;

import org.apache.ibatis.type.Alias;

@Alias("LikecntVO")
public class LikecntVO {

	private String userid;
	private int anumber;

	@Override
	public String toString() {
		return "LikecntVO [userid=" + userid + ", anumber=" + anumber + "]";
	}

	public String getUserid() {
		return userid;
	}

	public void setUserid(String userid) {
		this.userid = userid;
	}

	public int getAnumber() {
		return anumber;
	}

	public void setAnumber(int anumber) {
		this.anumber = anumber;
	}

}
