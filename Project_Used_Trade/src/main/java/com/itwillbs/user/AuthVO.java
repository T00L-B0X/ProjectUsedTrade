package com.itwillbs.user;

public class AuthVO {

	private String userid;
	private String auth;
	public String getUserid() {
		return userid;
	}
	public String getAuth() {
		return auth;
	}
	public void setUserid(String userid) {
		this.userid = userid;
	}
	public void setAuth(String auth) {
		this.auth = auth;
	}
	@Override
	public String toString() {
		return "AuthVO [userid=" + userid + ", auth=" + auth + "]";
	}
	

	
}
