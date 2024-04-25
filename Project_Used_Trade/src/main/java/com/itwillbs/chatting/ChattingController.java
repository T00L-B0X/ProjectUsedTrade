package com.itwillbs.chatting;

import java.util.List;

import javax.inject.Inject;
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
import com.itwillbs.member.MemberVO;


@Controller
public class ChattingController {
	
	@Inject
	private ChatGroupService chatService;
	
	private static final Logger logger = LoggerFactory.getLogger(ChattingController.class);
	
	@RequestMapping(value = "chatting", method = RequestMethod.GET)
	public String chatForm(Model model, HttpSession session) {
		List<ChatGroupVO> list = chatService.getChatGroupList();
		
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
		if(result > 0) {
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
		MemberVO memberVO = (MemberVO)session.getAttribute("user");
		
		List<AlarmVO> data = chatService.getAlarmInfo(memberVO.getUserid());
		logger.debug(" 알람 데이터 : " + data);
		
		return data;
	}
	
	
	// 읽은 알람 삭제하기
	@ResponseBody
	@PostMapping("/chatting/deleteAlarm")
	public ResponseEntity<String> deleteAlarm(int alarmNo) {
		chatService.deleteAlarm(alarmNo);
		return new ResponseEntity<String>("SUCESS", HttpStatus.OK);
	}
	
	
	// 채팅 가입하기
	@ResponseBody
	@PostMapping("/joinChat")
	public ResponseEntity<String> joinChatGroup(@RequestBody ChatMemberVO member) {
		int result = chatService.joinChatGroup(member);
		
		if(result > 0) {
			return new ResponseEntity<String>("SUCCESS", HttpStatus.OK);
		} else {
			return new ResponseEntity<String>("FAILED", HttpStatus.BAD_REQUEST);
		}
	}
	
	
	
	

}
