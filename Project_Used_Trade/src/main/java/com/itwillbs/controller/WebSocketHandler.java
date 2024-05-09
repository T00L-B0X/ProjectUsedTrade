package com.itwillbs.controller;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import javax.inject.Inject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.itwillbs.chatting.ChatGroupVO;
import com.itwillbs.chatting.ChatMessageVO;
import com.itwillbs.chatting.ChatService;
import com.itwillbs.user.MemberVO;
/**
 * 
 * 실시간 채팅을 위한 webSocketHandler
 *
 */
@Controller
public class WebSocketHandler extends TextWebSocketHandler {

	@Inject
	ChatService chatService;

	private static final Logger logger = LoggerFactory.getLogger(WebSocketHandler.class);

	/*
	 * jackson라이브러리에서 제공하는 클래스로 java객체와 JSON 데이터 간의 변환을 처리하는 데 사용됨 JSON 데이터를 Java
	 * 객체로 변환하거나, 그 반대의 작업 수행 가능
	 * 
	 * 1. JSON 데이터를 Java 객체로 변환하기 MyObject myObject =
	 * objectMapper.readValue(jsonString, MyObject.class);
	 * 
	 * 2. Java 객체를 JSON 데이터로 변환하기 String jsonString =
	 * objectMapper.writeValueAsString(myObject);
	 */

	private final ObjectMapper objectMapper = new ObjectMapper();

	/*
	 * 채팅방 목록 Map<방 번호(key), ArrayList<session> >이 들어감. key : 방번호, value : 해당 채팅방에
	 * 참여하는 WebSocketSession 객체들의 리스트
	 * 
	 * ConcurrentHashMap : 스레드 안전(thread-safe)한 맵 구현체 - 여러 스레드에서 동시에 접근하더라도 안전하게
	 * 데이터를 수정할 수 있도록 동기화 되어 있다.
	 * 
	 * 여러 채팅방에 대한 정보를 저장하고 관리하는 역할 수행
	 */
	private Map<Integer, ArrayList<WebSocketSession>> roomList = new ConcurrentHashMap<Integer, ArrayList<WebSocketSession>>();

	// 해당 userid와 그에 따르는 session 관리 Map
	private Map<WebSocketSession, String> userSessionMap = new ConcurrentHashMap<WebSocketSession, String>();

	// 로그인한 전체 session 목록 담는 List
	private List<WebSocketSession> sessionList = new ArrayList<WebSocketSession>();
	private static int i;

	// 웹소켓으로 HttpSession에 있는 userid 가져오기
	private String getUserid(WebSocketSession session) {
		Map<String, Object> httpSession = session.getAttributes();
		MemberVO loginUser = (MemberVO) httpSession.get("user");
//		MemberVO loginUser = new MemberVO();
//		loginUser.setUserid((String)httpSession.get("user"));
		if (loginUser == null) {
			return session.getId(); // WebSocketSession 의 session id 반환
		} else {
			return loginUser.getUserid();
		}
	}

	// 로그인한 유저 아이디가 속해있는 채팅방 리스트 가져오는 메소드
	private List<ChatGroupVO> getRoomListById(String userid) {
		return chatService.getChatGroupListById(userid);
	}

	// websocket 연결 성공 시
	@Override
	public void afterConnectionEstablished(WebSocketSession session) throws Exception {
		i++;
		System.out.println(session.getId() + " 연결 성공 => 총 접속 인원 : " + i + "명");
		System.out.println();
		String userid = getUserid(session);
		userSessionMap.put(session, userid);

		sessionList.add(session);

		// 해당 유저가 속해있는 채팅방 가져오기
		List<ChatGroupVO> list = getRoomListById(userid);

		// 해당 유저가 속해있는 채팅방이 있는 경우에만 실행
		if (list != null) {
			for (ChatGroupVO chat : list) {
				ArrayList<WebSocketSession> userList = new ArrayList<>();
				roomList.put(chat.getChat_no(), userList);
			}
		}

		for (ChatGroupVO chat : list) {
			System.out.println(userid + "님이 속해 있는 방 : " + chat.getChat_no());
		}
		System.out.println();
		System.out.println("연결 후 roomList 상태 : " + roomList);
		for (int i = 14; i <= 17; i++) {
			if (roomList.get(i) != null) {
				for (int j = 0; j < roomList.get(i).size(); j++) {
					System.out.println(i + "변방에 들어있는 유저 session" + "[" + j + "] : " + roomList.get(i).get(j));
				}
			}
		}
		System.out.println("연결 후 userSessionMap 상태 : " + userSessionMap);
	}

	// websocket 연결 종료 시
	@Override
	public void afterConnectionClosed(WebSocketSession session, CloseStatus status) throws Exception {
		i--;
		System.out.println(session.getId() + "연결 종료 => 총 접속 인원 : " + i + "명");
		System.out.println();

		String userid = getUserid(session);
		// 해당 유저가 속해있는 채팅방 가져오기
		List<ChatGroupVO> list = getRoomListById(userid);

		// 연결 끊은 해당 유저 맵에 삭제
		userSessionMap.remove(session);

		// 해당 유저가 속해있는 방이 존재하는 경우만 실행
		if (list != null) {
			for (ChatGroupVO chat : list) {
				// roomList에 방에 해당하는 List에 누군가 있는경우
				if (roomList.get(chat.getChat_no()) != null) {
					// 해당 List에서 연결끊은 session 삭제
					roomList.get(chat.getChat_no()).remove(session);
				}
			}
		}

		for (int i = 0; i < sessionList.size(); i++) {
			if (sessionList.get(i).equals(session)) {
				sessionList.remove(sessionList.get(i));
			}
		}

	}

	// websocket 메세지 수신 및 송신
	@Override
	protected void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {
		// 메시지에서 페이로드(본문)를 가져오는 메소드로 전달받은 메시지를 문자열 형태의
		// 페이로드로 반환
		String msg = message.getPayload();

		// Json 객체 -> Java객체
		ChatMessageVO chatMessage = objectMapper.readValue(msg, ChatMessageVO.class);
		
		logger.debug(" @@@@@@@@@@@@@@@ : " + chatMessage );

		// 해당 채팅방 인원수 가져오기
		int memCnt = chatService.getMemberCount(chatMessage.getChat_no());
		chatMessage.setUnread_count(memCnt);

		int chat_no = chatMessage.getChat_no(); // 채팅방 번호
		String userid = getUserid(session); // userid

		// 해당 유저가 속해있는 채팅방 가져오기
		List<ChatGroupVO> list = getRoomListById(userid);

		// 방에 입장 시 해당 방에 세션 추가
		if (chatMessage.getType().equals("enter-room")) {

			// ============ 방에 세션 추가하기전에 기존에 들어가 있던 방에서 세션 삭제 ============

			// 해당 유저가 속해있는 방이 존재하는 경우만 실행
			if (list != null) {
				for (ChatGroupVO chat : list) {
					// roomList에 내가 속해있는 모든방에 나의 session 삭제
					roomList.get(chat.getChat_no()).remove(session);
				}
				roomList.get(chat_no).add(session); // 방에 들어온 유저 세션리스트에 세션 추가
			}
			// ========================================================================================

			System.out.println();
			System.out.println("방 접속 후 roomList 상태 : " + roomList);
			for (int i = 14; i <= 17; i++) {
				if (roomList.get(i) != null) {
					for (int j = 0; j < roomList.get(i).size(); j++) {
						System.out.println(i + "번방에 들어있는 유저(접속 후 상태) session[" + j + "] : " + roomList.get(i).get(j));
					}
				}
			}

			// 해당 RoomList에 들어온 사람이 2명이면 sessionCount = 2;
			for (WebSocketSession sess : roomList.get(chat_no)) {
				String uId = userSessionMap.get(sess);
				chatMessage.setUserid(uId);

				// 해당 방에 채팅 메시지 안읽은 개수 가져오기
				List<Integer> msgIdList = chatService.getUnreadCntByUser(chatMessage);
				for (Integer msgId : msgIdList) {
					chatMessage.setMessage_id(msgId);
					// 방 안에서 해당 message_id에 대한 메시지 읽음 카운트 -1 처리
					chatService.readMessageInRoom(chatMessage);
				}
			}

			// 채팅방에 들어오면 읽음 처리하기
			chatService.readChatMessage(chatMessage);

			// 현재 들어와 있는 모든 세션에게 reload 메시지 전송 (메시지 채팅방 목록 ajax 다시 뿌리기 위함)
			TextMessage tMsg = new TextMessage("reload");
			for (WebSocketSession sess : sessionList) {
				sess.sendMessage(tMsg);
			}
		}
		// 채팅 메세지 입력 시
		else if(roomList.get(chat_no) != null && chatMessage.getType().equals("msg")) {
			LocalDateTime currentDateTime = LocalDateTime.now();
			DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
			String formattedDateTime = currentDateTime.format(formatter);
			
			logger.debug(" @@@@@@@@@@@@@@@@@@@@@chatMessage : " + chatMessage);
			// DB에 메시지 등록
			chatService.insertMessage(chatMessage);
			
			// 현재 session 수
			int sessionCount = 0;
			
			for(WebSocketSession sess : roomList.get(chat_no)) {
				sessionCount ++;
			}
			
			// 메세지에 이름, 아이디, 내용을 담는다
			TextMessage textMessage = new TextMessage(chatMessage.getUser_name() + "," + chatMessage.getUserid() +
					"," + chatMessage.getMessage_content() + "," + formattedDateTime + "," + 
					(chatMessage.getUnread_count()-sessionCount)); 
			
			// 해당 채팅방에 속한 모든 세션에게 메시지 전송
			// 해당 RoomList에 들어온 사람이 2명이면 sessionCount = 2;
			for(WebSocketSession sess : roomList.get(chat_no)) {
				String uId = userSessionMap.get(sess);
				chatMessage.setUserid(uId);
				
				// 해당 방에 채팅 메시지 안읽은 개수 가져오기 
				List<Integer> msgIdList = chatService.getUnreadCntByUser(chatMessage);
				for (Integer msgId : msgIdList) {
					chatMessage.setMessage_id(msgId);
					// 방 안에서 해당 message_id에 대한 메시지 읽음 카운트 -1 처리
					chatService.readMessageInRoom(chatMessage);
				}
				
				// 해당 채팅방에 들어가 있는 상태에서 대화 오고갈때 바로 읽음 처리 되기 위함
				// 채팅방 들어가기 전 새로운 채팅메시지의 개수 읽음 처리
				chatService.readChatMessage(chatMessage);
				
				sess.sendMessage(textMessage);
			}
			
			System.out.println(chat_no + "방에 들어온 세션 수 " + sessionCount);
			
			// 메시지 전송 시 마다 현재 들어와 있는 모든 세션에게 reload 메시지 전송
			// 메시지 채팅방목록 ajax 다시 뿌리기
			TextMessage tMsg = new TextMessage("reload");
			for(WebSocketSession sess : sessionList) {
				sess.sendMessage(tMsg);
			}
			
		}
		
		// 닫기 버튼 누를 시
		else if(chatMessage.getType().equals("close-room")) {
			// roomList에 내가 속해있던 방에 나의 session 삭제
			roomList.get(chat_no).remove(session);
		}

	}

}
