package com.itwillbs.security;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

public class CustomLoginSuccessHandler implements AuthenticationSuccessHandler {

	private static final Logger logger = LoggerFactory.getLogger(CustomLoginSuccessHandler.class);
	
	@Override
	public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
			Authentication auth) throws IOException, ServletException {
		logger.debug("CustomLoginSuccessHandler_onAuthenticationSuccess() 호출");
		logger.debug("인증 성공(로그인 성공, 토근정보O) 후 처리 수행");
		
		List<String> roleNames = new ArrayList<String>();
		
		auth.getAuthorities().forEach(authority -> {
			roleNames.add(authority.getAuthority());
		});
		
		logger.debug("roleNames :"+roleNames);
		
		if(roleNames.contains("ROLE_ADMIN")) {
			logger.debug("관리자 권한 로그인");
			response.sendRedirect("/");
			return;
		}
		if(roleNames.contains("ROLE_USER")) {
			logger.debug("회원 권한 로그인");
			response.sendRedirect("/");
			return;
		}
			logger.debug("권한이 없는(비회원) 로그인");
			response.sendRedirect("/");
	
	}

}
