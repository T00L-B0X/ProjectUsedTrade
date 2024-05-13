package com.itwillbs.alarm;

public class AlarmVO {
	private int alarm_no;
	private String userId;
	private String alarm_type;
	private String alarm_cdate;
	private String alarm_prefix;
	private int chat_no;
	private int chat_type;
	
	
	



	
	
	@Override
	public String toString() {
		return "AlarmVO [alarm_no=" + alarm_no + ", userId=" + userId + ", alarm_type=" + alarm_type + ", alarm_cdate="
				+ alarm_cdate + ", alarm_prefix=" + alarm_prefix + ", chat_no=" + chat_no + ", chat_type=" + chat_type
				+ "]";
	}


	public int getChat_type() {
		return chat_type;
	}
	
	
	public void setChat_type(int chat_type) {
		this.chat_type = chat_type;
	}
	
	public int getAlarm_no() {
		return alarm_no;
	}
	public void setAlarm_no(int alarm_no) {
		this.alarm_no = alarm_no;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getAlarm_type() {
		return alarm_type;
	}
	public void setAlarm_type(String alarm_type) {
		this.alarm_type = alarm_type;
	}
	public String getAlarm_cdate() {
		return alarm_cdate;
	}
	public void setAlarm_cdate(String alarm_cdate) {
		this.alarm_cdate = alarm_cdate;
	}
	public String getAlarm_prefix() {
		return alarm_prefix;
	}
	public void setAlarm_prefix(String alarm_prefix) {
		this.alarm_prefix = alarm_prefix;
	}
	public int getChat_no() {
		return chat_no;
	}
	public void setChat_no(int chat_no) {
		this.chat_no = chat_no;
	}
	
	
	
}