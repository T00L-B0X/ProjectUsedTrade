package com.itwillbs.common;

import javax.inject.Inject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping(value="/common")
public class CommonController {
	
	private static final Logger logger = LoggerFactory.getLogger(CommonController.class);
	
	@Inject
	private PasswordEncoder pwEncoder;
	
	@RequestMapping(value="/customLogin", method=RequestMethod.GET)
	public void customLogin() {
		logger.debug( "customLogin() 호출 ");
	}
	
	//회원가입
	
}
