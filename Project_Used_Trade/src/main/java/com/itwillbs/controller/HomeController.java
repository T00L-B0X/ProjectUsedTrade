package com.itwillbs.controller;

import java.security.Principal;
import java.text.DateFormat;
import java.util.Date;
import java.util.Locale;

import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.itwillbs.user.MemberService;
import com.itwillbs.user.MemberVO;

/**
 * Handles requests for the application home page.
 */
@Controller
@EnableAsync
public class HomeController {

	private static final Logger logger = LoggerFactory.getLogger(HomeController.class);
	
	private MemberService mService;
		
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String main(MemberVO vo, HttpSession session, Principal principal) throws Exception {
		logger.debug("main() 호출");
		String path = "/main";

		return path;
	}

}