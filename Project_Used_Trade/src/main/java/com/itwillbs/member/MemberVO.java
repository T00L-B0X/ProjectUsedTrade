package com.itwillbs.member;

public class MemberVO {
	private String userid;
	private String user_pw;
	private String user_name;
	private String user_email;
	
	public String getUserid() {
		return userid;
	}
	public void setUserid(String userid) {
		this.userid = userid;
	}
	public String getUser_pw() {
		return user_pw;
	}
	public void setUser_pw(String user_pw) {
		this.user_pw = user_pw;
	}
	public String getUser_name() {
		return user_name;
	}
	public void setUser_name(String user_name) {
		this.user_name = user_name;
	}
	public String getUser_email() {
		return user_email;
	}
	public void setUser_email(String user_email) {
		this.user_email = user_email;
	}
	
	@Override
	public String toString() {
		return "MemberVO [user_id=" + userid + ", user_pw=" + user_pw + ", user_name=" + user_name + ", user_email="
				+ user_email + "]";
	}
	
	
	
	
	
	
	

}
