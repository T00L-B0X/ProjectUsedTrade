package com.itwillbs.security;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;

public class CustomLoginFailureHandler implements AuthenticationFailureHandler {

	private static final Logger logger = LoggerFactory.getLogger(CustomLoginFailureHandler.class);

	@Override
	public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response, AuthenticationException exception)
			throws IOException, ServletException {
		logger.debug(" CustomLoginFailureHandler_onAuthenticationFailure() ?ò∏Ï∂? ");
		logger.debug(" ?ù∏Ï¶ùÏã§?å®(Î°úÍ∑∏?ù∏ ?ã§?å®) ?õÑ Ï≤òÎ¶¨ ?àò?ñâ ");

		response.sendRedirect("/loginBswill?error=1");
	}

}
