package com.itwillbs.user;

public class AuthVO {

	private String id;
	private String AUTHORITY;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getAUTHORITY() {
		return AUTHORITY;
	}
	public void setAUTHORITY(String aUTHORITY) {
		AUTHORITY = aUTHORITY;
	}
	@Override
	public String toString() {
		return "AuthVO [id=" + id + ", AUTHORITY=" + AUTHORITY + "]";
	}
	
	
}
