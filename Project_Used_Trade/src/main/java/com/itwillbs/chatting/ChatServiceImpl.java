package com.itwillbs.chatting;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service("ChatService")
public class ChatServiceImpl implements ChatService {

	@Inject
	private ChatDAO chatDao;

	private static final Logger logger = LoggerFactory.getLogger(ChatServiceImpl.class);

	@Override
	public List<ChatMessageVO> messageList(int chat_no) {

		return chatDao.messageList(chat_no);
	}

	@Override
	public int insertMessage(ChatMessageVO chatMessage) {
		// 메시지 삽입
		int result = chatDao.insertMessage(chatMessage);

		// 나를 제외한 해당 방 사람들에게 안읽은 메시지 카운트 삽입
		chatDao.updateMessageCountExceptMe(chatMessage);

		int chat_no = chatMessage.getChat_no();
		List<ChatMemberVO> memberList = chatDao.getChatMember(chat_no);

		Map<String, Object> map = new HashMap<String, Object>();
		for (ChatMemberVO member : memberList) {
			map.put("userid", member.getUserid());
			map.put("message_id", chatMessage.getMessage_id());
			map.put("chat_no", chatMessage.getChat_no());
			// 메시지 등록시마다 해당 방에 속한 유저들 대상으로 안읽은 유저
			// 테이블에 추가
			chatDao.insertUnreadMember(map);
		}
		return result;
	}

	@Override
	public List<ChatGroupVO> getChatGroupListById(String userid) {
		return chatDao.getChatGroupListById(userid);
	}

	@Override
	public List<ChatMemberVO> getChatMember(int chat_no) {

		return chatDao.getChatMember(chat_no);
	}

	@Override
	public void readChatMessage(ChatMessageVO chatMessage) {
		chatDao.readChatMessage(chatMessage);
	}

	@Override
	public int getChatCnt(String userid) {
		return chatDao.getChatCnt(userid);
	}

	@Override
	public int getMemberCount(int chat_no) {
		return chatDao.getMemberCount(chat_no);
	}

	@Override
	public List<Integer> getUnreadCntByUser(ChatMessageVO chatMessage) {
		return chatDao.getUnreadCntByUser(chatMessage);
	}

	@Override
	public void readMessageInRoom(ChatMessageVO chatMessage) {
		// 안읽은 멤버 테이블에 해당 방에 대한 유저에 관한 행 삭제
		chatDao.deleteUnreadMsg(chatMessage);
		chatDao.readMessageInRoom(chatMessage);
	}

}
