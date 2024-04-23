package com.itwillbs.chatting;


public class ChatMessageVO {
	private int messageId;
	private int chatNo;
	private String messageContent;
	private String messageRegdate;
	private String userId;
	private String userName;
	private int unreadCount;
	
	// DB에 없는 필요한 변수
	private String type;
	
	
	@Override
	public String toString() {
		return "ChatMessageVO [messageId=" + messageId + ", chatNo=" + chatNo + ", messageContent=" + messageContent
				+ ", messageRegdate=" + messageRegdate + ", userId=" + userId + ", userName=" + userName
				+ ", unreadCount=" + unreadCount + ", type=" + type + "]";
	}

	public int getMessageId() {
		return messageId;
	}

	public void setMessageId(int messageId) {
		this.messageId = messageId;
	}

	public int getChatNo() {
		return chatNo;
	}

	public void setChatNo(int chatNo) {
		this.chatNo = chatNo;
	}

	public String getMessageContent() {
		return messageContent;
	}

	public void setMessageContent(String messageContent) {
		this.messageContent = messageContent;
	}

	public String getMessageRegdate() {
		return messageRegdate;
	}

	public void setMessageRegdate(String messageRegdate) {
		this.messageRegdate = messageRegdate;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public int getUnreadCount() {
		return unreadCount;
	}

	public void setUnreadCount(int unreadCount) {
		this.unreadCount = unreadCount;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}
	
	

}
