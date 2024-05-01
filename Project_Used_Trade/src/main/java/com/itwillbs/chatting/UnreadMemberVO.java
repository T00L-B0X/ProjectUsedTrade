package com.itwillbs.chatting;

public class UnreadMemberVO {

	private int message_id;
	private String userid;
	private int readCheck;
	private int chat_no;
	
	
	
	@Override
	public String toString() {
		return "UnreadMemberVO [message_id=" + message_id + ", userid=" + userid + ", readCheck=" + readCheck
				+ ", chat_no=" + chat_no + "]";
	}
	
	public int getMessage_id() {
		return message_id;
	}
	public void setMessage_id(int message_id) {
		this.message_id = message_id;
	}
	public String getUserid() {
		return userid;
	}
	public void setUserid(String userid) {
		this.userid = userid;
	}
	public int getReadCheck() {
		return readCheck;
	}
	public void setReadCheck(int readCheck) {
		this.readCheck = readCheck;
	}
	public int getChat_no() {
		return chat_no;
	}
	public void setChat_no(int chat_no) {
		this.chat_no = chat_no;
	}

	
}
