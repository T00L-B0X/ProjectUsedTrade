package com.itwillbs.chatting;

import java.util.List;
import java.util.Map;

import com.itwillbs.alarm.AlarmVO;
import com.itwillbs.user.MemberVO;

public interface ChatGroupDAO {
	
	public List<ChatGroupVO> getChatGroupList();
	
	public void insertAlarm(Map<String, Object> map);
	
	public int insertChat(ChatGroupVO chat);
	
	public List<AlarmVO> getAlarmInfo(String userid);
	
	public List<MemberVO> getUserList(String userid);
	
	public void addChatMember(ChatGroupVO chat);
	
	public void deleteAlarm(int alarm_no);
	
	public int joinChatGroup(ChatMemberVO member);
	
	public int getChatNo(int chat_no);
	
	public String getMemberFromTable(int bno);
	
	public String getUserNameFromTable(int bno);
	

}
