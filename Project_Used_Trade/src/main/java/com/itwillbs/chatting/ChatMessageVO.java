package com.itwillbs.chatting;


/**
 * @author ITWILL
 *
 */
public class ChatMessageVO {
	private int message_id;
	private int chat_no;
	private String message_content;
	private String message_regdate;
	private String userid;
	private String user_name;
	private int unread_count;
	
	// DB에 없는 필요한 변수
	private String type;

	
	
	@Override
	public String toString() {
		return "ChatMessageVO [message_id=" + message_id + ", chat_no=" + chat_no + ", message_content="
				+ message_content + ", message_regdate=" + message_regdate + ", userid=" + userid + ", user_name="
				+ user_name + ", unread_count=" + unread_count + ", type=" + type + "]";
	}

	public int getMessage_id() {
		return message_id;
	}

	public void setMessage_id(int message_id) {
		this.message_id = message_id;
	}

	public int getChat_no() {
		return chat_no;
	}

	public void setChat_no(int chat_no) {
		this.chat_no = chat_no;
	}

	public String getMessage_content() {
		return message_content;
	}

	public void setMessage_content(String message_content) {
		this.message_content = message_content;
	}

	public String getMessage_regdate() {
		return message_regdate;
	}

	public void setMessage_regdate(String message_regdate) {
		this.message_regdate = message_regdate;
	}

	public String getUserid() {
		return userid;
	}

	public void setUserid(String userid) {
		this.userid = userid;
	}

	public String getUser_name() {
		return user_name;
	}

	public void setUser_name(String user_name) {
		this.user_name = user_name;
	}

	public int getUnread_count() {
		return unread_count;
	}

	public void setUnread_count(int unread_count) {
		this.unread_count = unread_count;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}
	
	
}