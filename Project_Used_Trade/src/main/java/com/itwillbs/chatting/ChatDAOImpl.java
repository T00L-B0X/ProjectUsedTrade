package com.itwillbs.chatting;

import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import org.mybatis.spring.SqlSessionTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

@Repository("ChatDAO")
public class ChatDAOImpl implements ChatDAO {

	@Inject
	private SqlSessionTemplate sqlSession;

	private static final String NAMESPACE = "com.itwillbs.mapper.ChattingMapper";

	private static final Logger logger = LoggerFactory.getLogger(ChatDAOImpl.class);

	
	
	@Override
	public List<ChatMessageVO> messageList(int chat_no) {

		return sqlSession.selectList(NAMESPACE + ".messageList", chat_no);
	}

	@Override
	public int insertMessage(ChatMessageVO chatMessage) {

		return sqlSession.insert(NAMESPACE + ".insertMessage", chatMessage);
	}

	@Override
	public List<ChatGroupVO> getChatGroupListById(String userid) {
		return sqlSession.selectList(NAMESPACE + ".getChatGroupListById", userid);
	}

	@Override
	public List<ChatMemberVO> getChatMember(int chat_no) {
		return sqlSession.selectList(NAMESPACE + "getChatMember", chat_no);
	}

	@Override
	public void updateMessageCountExceptMe(ChatMessageVO chatMessage) {
		sqlSession.update(NAMESPACE + ".updateMessageCountExceptMe", chatMessage);
	}

	@Override
	public void readChatMessage(ChatMessageVO chatMessage) {
		sqlSession.update(NAMESPACE + "readChatMessage", chatMessage);
	}

	@Override
	public int getChatCnt(String userid) {
		return sqlSession.selectOne(NAMESPACE + "getChatCnt", userid);
	}

	@Override
	public int getMemberCount(int chat_no) {

		return sqlSession.selectOne(NAMESPACE + ".getMemberCount", chat_no);
	}

	@Override
	public void insertUnreadMember(Map<String, Object> map) {
		sqlSession.insert(NAMESPACE + ".insertUnreadMember", map);
	}

	@Override
	public List<Integer> getUnreadCntByUser(ChatMessageVO chatMessage) {

		return sqlSession.selectList(NAMESPACE + ".getUnreadCntByUser", chatMessage);
	}

	@Override
	public void readMessageInRoom(ChatMessageVO chatMessage) {
		sqlSession.update(NAMESPACE + ".readMessageInRoom", chatMessage);

	}

	@Override
	public void deleteUnreadMsg(ChatMessageVO chatMessage) {
		sqlSession.update(NAMESPACE + ".deleteUnreadMsg", chatMessage);
	}

}
