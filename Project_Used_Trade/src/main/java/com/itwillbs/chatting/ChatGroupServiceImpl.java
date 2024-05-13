package com.itwillbs.chatting;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.itwillbs.alarm.AlarmVO;
import com.itwillbs.chatting.ChatMemberVO;
import com.itwillbs.user.MemberVO;

@Service("ChatGroupService")
public class ChatGroupServiceImpl implements ChatGroupService {
	
	private static final Logger logger = LoggerFactory.getLogger(ChatGroupServiceImpl.class);
	
	@Inject
	private ChatGroupDAO chatDao;
	
	@Override
	public List<ChatGroupVO> getChatGroupList() {
		return chatDao.getChatGroupList();
	}

	@Override
	public int insertChat(ChatGroupVO chat) {
		// 채팅그룹 생성
		int result = chatDao.insertChat(chat);
		
		chat.setAuth_role("방장");
		// 방장 채팅 멤버에 추가
		chatDao.addChatMember(chat);
		
		String userid = chat.getUserid();
		// 자신을 뺀 모든 유저의 List를 가져옴
		List<MemberVO> userList = chatDao.getUserList(userid);
		Map<String, Object> map = new HashMap<String, Object>();
		for (MemberVO user : userList) {
			map.put("sender_name", chat.getUsernm());
			map.put("userid", user.getUserid());
			map.put("chat_no", chat.getChat_no());
			map.put("chat_type", chat.getChat_type());
			
			
			// alarm 테이블에 자신을 뺀 모든 유저에 대한 알림 추가
			chatDao.insertAlarm(map);
			logger.debug(" 알람 map.put : " + map);
		}
		return result;
	}

	@Override
	public List<AlarmVO> getAlarmInfo(String userid) {
		return chatDao.getAlarmInfo(userid);
	}

	@Override
	public void deleteAlarm(int alarm_no) {
		chatDao.deleteAlarm(alarm_no);
	}

	@Override
	public int joinChatGroup(ChatMemberVO member) {
		return chatDao.joinChatGroup(member);
	}

	@Override
	public String getMemberFromTable(int goods_id) {
		return chatDao.getMemberFromTable(goods_id);
	}

	@Override
	public String getUserNameFromTable(int goods_id) {
		return chatDao.getUserNameFromTable(goods_id);
	}

	@Override
	public int getChatNo(int chat_no) {
		return chatDao.getChatNo(chat_no);
	}
	

}
