package com.itwillbs.member;

import java.text.DateFormat;
import java.util.Date;
import java.util.Locale;

import javax.inject.Inject;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.itwillbs.member.MemberService;
import com.itwillbs.member.MemberVO;

/**
 * Handles requests for the application home page.
 */
@Controller
public class MemberController {
	
	@Inject
	private MemberService mService;
	
	private static final Logger logger = LoggerFactory.getLogger(MemberController.class);
	
	
	
	// 임시 회원가입 화면 (기능 완성시 삭제)
	// http://localhost:8088/memberJoin
	@RequestMapping(value = "/memberJoin", method = RequestMethod.GET)
	public void memberJoinGET() {
		logger.debug(" memberJoinGET() 실행 - 회원정보 입력 ");
	}
	
	
	
	// 임시 회원가입처리 (기능 완성시 삭제)
	// 회원가입(처리) POST
	@RequestMapping(value = "memberJoin" , method = RequestMethod.POST)
	public String memberJoinPOST(MemberVO vo) {
		logger.info(" mebmerJoinPost() 실행 - 회원가입 처리");
		logger.info(" 전달 정보 : " + vo);
		
		mService.memberJoin(vo);
		logger.info(" 회원가입 성공 " + vo.getUserid());
		
		
		return "redirect:/login";
	}
	
	
	// 임시 회원가입처리 (기능 완성시 삭제)
	// 로그인 GET
	// http://localhost:8088/login
	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public void memberLoginGET() {
		logger.info(" /login.jsp 페이지 연결 ");
	}
	
	
	// 임시 회원가입처리 (기능 완성시 삭제)
	// 로그인 POST
	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public String memberLoginPOST(MemberVO vo, HttpSession session) {
		logger.info(" login.jsp -> memberLoginPOST() 호출 ");
		logger.info(" 로그인 정보 : " + vo);
		logger.info(" 로그인 정보 : " + vo.getUser_name());
		
		MemberVO memberVO = mService.memberLogin(vo);
		
		String addr = "";
		if(memberVO == null) {
			logger.debug(" 로그인 실패! -> 다시 로그인 ");
			addr = "/login";
		}else {
			logger.debug(" 로그인 성공!! -> 메인페이지 ");
			
			// 로그인 성공한 사용자의 아이디 정보를 세션에 저장
			session.setAttribute("id", memberVO.getUserid());
			session.setAttribute("vo", memberVO.toString());
			session.setAttribute("loginMember", memberVO);
			
			//return "redirect:/member/main";
			addr = "/";
		}		
		
		return "redirect:"+addr;
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
