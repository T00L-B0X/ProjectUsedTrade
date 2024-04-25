package com.itwillbs.chatting;

/**
 * @author ITWILL
 *
 */
public class ChatGroupVO {
	private int chat_no;
	private String chat_title;
	private String userid;
	private String chat_date;
	private String user_name;
	
	// DB에는 없음 채팅방장 설정을 위한 변수
	private String auth_role;
	private int msgCount;
	public int getChat_no() {
		return chat_no;
	}
	public void setChat_no(int chat_no) {
		this.chat_no = chat_no;
	}
	public String getChat_title() {
		return chat_title;
	}
	public void setChat_title(String chat_title) {
		this.chat_title = chat_title;
	}
	public String getUserid() {
		return userid;
	}
	public void setUserid(String userid) {
		this.userid = userid;
	}
	public String getChat_date() {
		return chat_date;
	}
	public void setChat_date(String chat_date) {
		this.chat_date = chat_date;
	}
	public String getUser_name() {
		return user_name;
	}
	public void setUser_name(String user_name) {
		this.user_name = user_name;
	}
	public String getAuth_role() {
		return auth_role;
	}
	public void setAuth_role(String auth_role) {
		this.auth_role = auth_role;
	}
	public int getMsgCount() {
		return msgCount;
	}
	public void setMsgCount(int msgCount) {
		this.msgCount = msgCount;
	}
	
	
	@Override
	public String toString() {
		return "ChatGroupVO [chat_no=" + chat_no + ", chat_title=" + chat_title + ", userid=" + userid + ", chat_date="
				+ chat_date + ", user_name=" + user_name + ", auth_role=" + auth_role + ", msgCount=" + msgCount + "]";
	}
	
	
	
	
	
	
}