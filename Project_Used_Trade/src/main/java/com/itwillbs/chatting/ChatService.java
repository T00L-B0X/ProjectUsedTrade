package com.itwillbs.chatting;

import java.util.List;

public interface ChatService {
	
	public List<ChatMessageVO> messageList(int roomId);
	
	public int insertMessage(ChatMessageVO chatMessage);
	
	public List<ChatGroupVO> getChatGroupListById(String userid);
	
	public List<ChatMemberVO> getChatMember(int chat_no);
	
	public void readChatMessage(ChatMessageVO chatMessage);
	
	public int getChatCnt(String userid);
	
	public int getMemberCount(int chat_no);
	
	public List<Integer> getUnreadCntByUser(ChatMessageVO chatMessage);
	
	public void readMessageInRoom(ChatMessageVO chatMessage);

}
