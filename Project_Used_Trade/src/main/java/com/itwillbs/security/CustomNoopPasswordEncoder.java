/*package com.itwillbs.security;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.crypto.password.PasswordEncoder;

public class CustomNoopPasswordEncoder implements PasswordEncoder{
	
	private static final Logger logger = LoggerFactory.getLogger(CustomNoopPasswordEncoder.class);
	
	@Override
	public String encode(CharSequence rawPassword) {
		
		logger.debug("before encode :"+rawPassword);
		
		return rawPassword.toString();
	}

	@Override
	public boolean matches(CharSequence rawPassword, String encodedPassword) {
		
		logger.debug("matches : "+rawPassword + ":"+encodedPassword );
		
		return rawPassword.toString().equals(encodedPassword);
	}

	
}
*/