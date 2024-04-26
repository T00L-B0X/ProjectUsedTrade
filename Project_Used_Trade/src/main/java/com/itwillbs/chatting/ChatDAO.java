package com.itwillbs.chatting;

import java.util.List;
import java.util.Map;

public interface ChatDAO {
	
	public List<ChatMessageVO> messageList(int roomId);
	public int insertMessage(ChatMessageVO chatMessage);
	public List<ChatGroupVO> getChatGroupListById(String userid);
	public List<ChatMemberVO> getChatMember(int chat_no);
	public void updateMessageCountExceptMe(ChatMessageVO chatMessage);
	public void readChatMessage(ChatMessageVO chatMessage);
	public int getChatCnt(String userid);
	public int getMemberCount(int chat_no);
	public void insertUnreadMember(Map<String, Object> map);
	public List<Integer> getUnreadCntByUser(ChatMessageVO chatMessage);
	public void readMessageInRoom(ChatMessageVO chatMessage);
	public void deleteUnreadMsg(ChatMessageVO chatMessage);
	
}
