package com.itwillbs.chatting;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

@Repository("ChatDAO")
public class ChatDAOImpl implements ChatDAO {

	@Override
	public List<ChatMessageVO> messageList(int roomId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int insertMessage(ChatMessageVO chatMessage) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public List<ChatGroupVO> getChatgroupListById(String userid) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<ChatMemberVO> getChatMember(int chat_no) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void updateMessageCountExceptMe(ChatMessageVO chatMessage) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void readChatMessage(ChatMessageVO chatMessage) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public int getChatCnt(String userid) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int getMemberCount(int chat_no) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void insertUnreadMember(Map<String, Object> map) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<Integer> getUnreadCntByUser(ChatMessageVO chatMessage) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void readMessageInRoom(ChatMessageVO chatMessage) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void deleteUnreadMsg(ChatMessageVO chatMessage) {
		// TODO Auto-generated method stub
		
	}

}
