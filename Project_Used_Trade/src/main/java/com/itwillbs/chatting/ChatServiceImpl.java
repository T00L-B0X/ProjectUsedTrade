package com.itwillbs.chatting;

import java.util.List;

import org.springframework.stereotype.Service;

@Service("ChatService")
public class ChatServiceImpl implements ChatService {

	@Override
	public List<ChatMessageVO> messageList(int roomId) {
		
		return null;
	}

	@Override
	public int insertMessage(ChatMessageVO chatMessage) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public List<ChatGroupVO> getChatGroupListById(String userid) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<ChatMemberVO> getChatMember(int chat_no) {
		// TODO Auto-generated method stub
		return null;
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
	public List<Integer> getUnreadCntByUser(ChatMessageVO chatMessage) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void readMessageInRoom(ChatMessageVO chatMessage) {
		// TODO Auto-generated method stub
		
	}

}
