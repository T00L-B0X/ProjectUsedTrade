package com.itwillbs.chatting;

import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.stereotype.Repository;

import com.itwillbs.alarm.AlarmVO;
import com.itwillbs.member.MemberVO;
import com.itwillbs.chatting.ChatMemberVO;

@Repository("ChatGroupDAO")
public class ChatGroupDAOImpl implements ChatGroupDAO{
	
	@Inject
	private SqlSessionTemplate sqlSession;
	
	private static final String NAMESPACE = "com.itwillbs.mapper.ChattingMapper";

	// 채팅방 조회
	@Override
	public List<ChatGroupVO> getChatGroupList() {
		return sqlSession.selectList(NAMESPACE + ".getChatGroupList");
	}

	// 알람 등록
	@Override
	public void insertAlarm(Map<String, Object> map) {
		sqlSession.insert(NAMESPACE + ".insertAlarm", map);
	}

	@Override
	public int insertChat(ChatGroupVO chat) {
		return sqlSession.insert(NAMESPACE + ".insertChat", chat);
	}

	// 안읽은 알람정보 가져오기
	@Override
	public List<AlarmVO> getAlarmInfo(String userid) {
		return sqlSession.selectList(NAMESPACE + ".getAlarmInfo", userid);
	}

	@Override
	public List<MemberVO> getUserList(String userid) {
		return sqlSession.selectList(NAMESPACE + ".getUserList", userid);
	}

	@Override
	public void addChatMember(ChatGroupVO chat) {
		sqlSession.insert(NAMESPACE + ".addChatMember", chat);
	}

	@Override
	public void deleteAlarm(int alarm_no) {
		sqlSession.delete(NAMESPACE + ".deleteAlarm", alarm_no);
	}

	@Override
	public int joinChatGroup(ChatMemberVO member) {
		return sqlSession.insert(NAMESPACE + ".joinChatGroup", member);
	}

}
