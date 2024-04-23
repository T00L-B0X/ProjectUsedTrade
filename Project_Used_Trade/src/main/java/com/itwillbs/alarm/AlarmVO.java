package com.itwillbs.alarm;

public class AlarmVO {
	private int alarmNo;
	private String userId;
	private String alarmType;
	private String alarmCdate;
	private String alarmPrefix;
	private int chatNo;
	
	
	
	@Override
	public String toString() {
		return "AlarmVO [alarmNo=" + alarmNo + ", userId=" + userId + ", alarmType=" + alarmType + ", alarmCdate="
				+ alarmCdate + ", alarmPrefix=" + alarmPrefix + ", chatNo=" + chatNo + "]";
	}
	
	public int getAlarmNo() {
		return alarmNo;
	}
	public void setAlarmNo(int alarmNo) {
		this.alarmNo = alarmNo;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getAlarmType() {
		return alarmType;
	}
	public void setAlarmType(String alarmType) {
		this.alarmType = alarmType;
	}
	public String getAlarmCdate() {
		return alarmCdate;
	}
	public void setAlarmCdate(String alarmCdate) {
		this.alarmCdate = alarmCdate;
	}
	public String getAlarmPrefix() {
		return alarmPrefix;
	}
	public void setAlarmPrefix(String alarmPrefix) {
		this.alarmPrefix = alarmPrefix;
	}
	public int getChatNo() {
		return chatNo;
	}
	public void setChatNo(int chatNo) {
		this.chatNo = chatNo;
	}
	
	
	

}
