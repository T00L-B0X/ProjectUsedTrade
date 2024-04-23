package com.itwillbs.chatting;

public class UnreadMemberVO {

	private int messageId;
	private String userId;
	private int readCheck;
	private int chatNo;

	@Override
	public String toString() {
		return "UnreadMemberVO [messageId=" + messageId + ", userId=" + userId + ", readCheck=" + readCheck
				+ ", chatNo=" + chatNo + "]";
	}

	public int getMessageId() {
		return messageId;
	}

	public void setMessageId(int messageId) {
		this.messageId = messageId;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public int getReadCheck() {
		return readCheck;
	}

	public void setReadCheck(int readCheck) {
		this.readCheck = readCheck;
	}

	public int getChatNo() {
		return chatNo;
	}

	public void setChatNo(int chatNo) {
		this.chatNo = chatNo;
	}

}
