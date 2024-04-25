package com.itwillbs.chatting;

import java.util.List;

import com.itwillbs.alarm.AlarmVO;
import com.itwillbs.chatting.ChatMemberVO;


public interface ChatGroupService {
	
	public List<ChatGroupVO> getChatGroupList();
	
	public int insertChat(ChatGroupVO chat);
	
	public List<AlarmVO> getAlarmInfo(String userid);
	
	public void deleteAlarm(int alarm_no);
	
	public int joinChatGroup(ChatMemberVO member);
	
	

}
