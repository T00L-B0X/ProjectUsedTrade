package com.itwillbs.chatting;

import java.util.List;

import javax.inject.Inject;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.itwillbs.member.MemberVO;

@Controller
public class ChatController {

	private static final Logger logger = LoggerFactory.getLogger(ChatController.class);

	@Inject
	private ChatService chatService;

	// 채팅방 폼으로 이동
	@RequestMapping(value = "chathome", method = RequestMethod.GET)
	public String chatForm() {
		logger.debug(" chat.jsp 페이지로 이동 ");
		return "/chatting/chat";
	}

	// 채팅방 리스트 가져오기 ajax
	@ResponseBody
	@RequestMapping(value = "chathome/chatList.do", method = RequestMethod.POST)
	public ResponseEntity<List<ChatGroupVO>> getChatList(HttpSession session) {
		MemberVO user = (MemberVO) session.getAttribute("user");
		String userid = user.getUserid();

		logger.debug(" 채팅방 리스트 가져오기 (ajax) userid : " + userid);
		logger.debug(" /chatList.do 연결 ");

		// 해당 유저가 속해있는 채팅방 가져오기
		List<ChatGroupVO> list = chatService.getChatGroupListById(userid);
		logger.debug(" 속해있는 채팅방 리스트 : " + list);

		return new ResponseEntity<List<ChatGroupVO>>(list, HttpStatus.OK);
	}
	

	@ResponseBody // 객체를 응답 데이터로 보내기위한 어노테이션 (default 데이터 형식 json)
	@RequestMapping(value = "{chat_no}.do")
	public ResponseEntity<List<ChatMessageVO>> messageList(@PathVariable int chat_no, String userid) {
		// 해당 방의 메시지 리스트 가져오기
		List<ChatMessageVO> list = chatService.messageList(chat_no);
		logger.debug(" chat_no.do 연결 ");
		logger.debug(" 채팅방 내용 : " + list);
		// userid는 안 읽은 메시지 처리 위해서 받아온건데 1:1이 아니라 멀티 채팅방일때 생각 중
		return new ResponseEntity<List<ChatMessageVO>>(list, HttpStatus.OK);
	}

	@ResponseBody
	@RequestMapping(value = "chatmember.do")
	public ResponseEntity<List<ChatMemberVO>> getChatMember(int chat_no) {
		// 해당 방의 멤버 리스트 가져오기
		List<ChatMemberVO> list = chatService.getChatMember(chat_no);
		logger.debug(" chatmember.do 연결 ");
		return new ResponseEntity<List<ChatMemberVO>>(list, HttpStatus.OK);
	}

	@ResponseBody
	@RequestMapping(value = "getChatCnt.do")
	public ResponseEntity<Integer> getChatCnt(String userid) {
		// 해당 유저의 안읽은 메시지 개수 가져오기
		int chatCnt = chatService.getChatCnt(userid);
		logger.debug(" getChatCnt 연결 ");
		return new ResponseEntity<Integer>(chatCnt, HttpStatus.OK);
	}

}
