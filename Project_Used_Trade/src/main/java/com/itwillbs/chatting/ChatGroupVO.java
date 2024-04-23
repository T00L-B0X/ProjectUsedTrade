package com.itwillbs.chatting;

public class ChatGroupVO {
	private int chatNo;
	private String chatTitle;
	private String userId;
	private String chatDate;
	private String userName;
	
	// DB에는 없음 채팅방장 설정을 위한 변수
	private String authRole;
	private int msgCount;
	
	
	
	
	@Override
	public String toString() {
		return "ChatGroupVO [chatNo=" + chatNo + ", chatTitle=" + chatTitle + ", userId=" + userId + ", chatDate="
				+ chatDate + ", userName=" + userName + ", authRole=" + authRole + ", msgCount=" + msgCount + "]";
	}
	
	
	public int getChatNo() {
		return chatNo;
	}
	public void setChatNo(int chatNo) {
		this.chatNo = chatNo;
	}
	public String getChatTitle() {
		return chatTitle;
	}
	public void setChatTitle(String chatTitle) {
		this.chatTitle = chatTitle;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getChatDate() {
		return chatDate;
	}
	public void setChatDate(String chatDate) {
		this.chatDate = chatDate;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getAuthRole() {
		return authRole;
	}
	public void setAuthRole(String authRole) {
		this.authRole = authRole;
	}
	public int getMsgCount() {
		return msgCount;
	}
	public void setMsgCount(int msgCount) {
		this.msgCount = msgCount;
	}

}
