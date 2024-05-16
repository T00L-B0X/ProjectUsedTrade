package com.itwillbs.chatting;

public class ChatMemberVO {
	private int chat_no;
	private String userid;
	private String usernm;
	private String auth_role;
	private int msg_count;
	
	@Override
	public String toString() {
		return "ChatMemberVO [chat_no=" + chat_no + ", userid=" + userid + ", usernm=" + usernm + ", auth_role="
				+ auth_role + ", msg_count=" + msg_count + "]";
	}

	public int getChat_no() {
		return chat_no;
	}

	public void setChat_no(int chat_no) {
		this.chat_no = chat_no;
	}

	public String getUserid() {
		return userid;
	}

	public void setUserid(String userid) {
		this.userid = userid;
	}

	public String getUsernm() {
		return usernm;
	}

	public void setUsernm(String usernm) {
		this.usernm = usernm;
	}

	public String getAuth_role() {
		return auth_role;
	}

	public void setAuth_role(String auth_role) {
		this.auth_role = auth_role;
	}

	public int getMsg_count() {
		return msg_count;
	}

	public void setMsg_count(int msg_count) {
		this.msg_count = msg_count;
	}
	
	
	
	
	
}
