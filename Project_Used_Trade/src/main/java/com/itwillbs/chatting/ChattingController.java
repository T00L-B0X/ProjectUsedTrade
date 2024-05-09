package com.itwillbs.chatting;

import java.security.Principal;
import java.util.List;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.itwillbs.alarm.AlarmVO;
import com.itwillbs.user.MemberVO;
import com.itwillbs.user.UserService;
@Controller
public class ChattingController {

	@Inject
	private ChatGroupService chatService;
	
	@Inject
	private UserService userService;

	@Inject
	private ChatService service;

	private static final Logger logger = LoggerFactory.getLogger(ChattingController.class);

	@RequestMapping(value = "chatting", method = RequestMethod.GET)
	public String chatForm(Model model, HttpSession session, Principal principal) throws Exception {
		List<ChatGroupVO> list = chatService.getChatGroupList();
		
		// model.addAttribute에 session에 있는 사용자 정보 저장
		String userid = principal.getName();
		MemberVO memberVO = userService.read(userid);
		model.addAttribute("user", memberVO);
		

		model.addAttribute("chatList", list);

		logger.debug("chatList : " + list);
		return "/chatting/chatting";
	}

	// 채팅방 생성
	@RequestMapping(value = "chatting", method = RequestMethod.POST)
	public String insertChat(ChatGroupVO chat, MemberVO vo, HttpSession session) {
		String goPage = "";
		// vo.setUserid((String) session.getAttribute("userid"));
		chat.setUserid((String) session.getAttribute("userid"));
		int result = chatService.insertChat(chat);
		if (result > 0) {
			goPage = "redirect:/chatting";
		} else {
			goPage = "";
		}
		return goPage;
	}

	// 해당 유저의 안읽은 알람 정보 가져오기
	@ResponseBody
	@PostMapping("/chatting/getAlarmInfo")
	public List<AlarmVO> getAlarmInfo(HttpSession session) {
		MemberVO memberVO = (MemberVO) session.getAttribute("user");

		List<AlarmVO> data = chatService.getAlarmInfo(memberVO.getUserid());
		logger.debug(" 알람 데이터 : " + data);

		return data;
	}

	// 읽은 알람 삭제하기
	@ResponseBody
	@PostMapping("/chatting/deleteAlarm")
	public ResponseEntity<String> deleteAlarm(int alarm_no) {
		chatService.deleteAlarm(alarm_no);
		return new ResponseEntity<String>("SUCESS", HttpStatus.OK);
	}

	// 채팅 가입하기
	@ResponseBody
	@PostMapping("/chatting/joinChat")
	public ResponseEntity<String> joinChatGroup(@RequestBody ChatMemberVO member) {
		int result = chatService.joinChatGroup(member);

		if (result > 0) {
			return new ResponseEntity<String>("SUCCESS", HttpStatus.OK);
		} else {
			return new ResponseEntity<String>("FAILED", HttpStatus.BAD_REQUEST);
		}
	}

	
	// 게시글에서 채팅방 생성 이 후 채팅 멤버에 게시글 작성자 넣기
	@ResponseBody
	@RequestMapping(value = "read/connectChat", method = RequestMethod.POST)
	public ResponseEntity<String> connectChat(ChatMemberVO member, HttpServletRequest request) {
		int chat_no = 0;
		
		// 클라이언트로부터 전달된 bno 값을 가져옴
		String bnoString = request.getParameter("bno");
		Integer bno = null;
		if (bnoString != null) {
			bno = Integer.parseInt(bnoString);
		}
		
		// bno를 사용하여 작성자 정보 등을 가져오거나 필요한 처리를 수행한다.
		member.setChat_no(chatService.getChatNo(chat_no));
		member.setUserid(chatService.getMemberFromTable(bno));
		member.setAuth_role("게시글 작성자");
		member.setUser_name(chatService.getUserNameFromTable(bno));
		logger.debug("DB에 저장되는 게시글 작성자 정보 : " + member);

		// 채팅 멤버에 추가하는 작업을 수행한다.
		int result = chatService.joinChatGroup(member);
		if (result > 0) {
			return new ResponseEntity<String>("SUCCESS", HttpStatus.OK);
		} else {
			return new ResponseEntity<String>("FAILED", HttpStatus.BAD_REQUEST);
		}		
	}



	// 게시글에서 채팅하기 눌렀을 때 채팅방 생성
	@ResponseBody
	@RequestMapping(value = "read/joinChat", method = RequestMethod.POST)
	public String joinChat(@RequestBody ChatGroupVO chat, MemberVO vo, HttpSession session) {
		String goPage = "";
		chat.setUserid((String) session.getAttribute("userid"));
		int result = chatService.insertChat(chat);
		if (result > 0) {
			goPage = "redirect:/read";
			logger.debug(" 생성 완료! ");
		} else {
			goPage = "";
			logger.debug(" 생성 실패! ");
		}
		return goPage;
	}

}
