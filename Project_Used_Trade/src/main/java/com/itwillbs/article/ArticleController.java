package com.itwillbs.article;

import java.security.Principal;

import javax.inject.Inject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.itwillbs.user.MemberVO;
import com.itwillbs.user.UserService;

@Controller
@RequestMapping(value = "/article/*")
public class ArticleController {

	private static final Logger logger = LoggerFactory.getLogger(ArticleController.class);

	@Inject
	private UserService uService;

	// http://localhost:8088/article/topic
	@RequestMapping(value = "/topic", method = RequestMethod.GET)
	public String ArticleTopicGET(Principal principal, Model model) throws Exception {
		logger.debug("ArticleController - ArticleTopicGET() 호출");

		String path = "/user/login";

		if (principal != null) {
			path = "/article/topic";

			String userid = principal.getName();
			
			MemberVO vo = uService.read(userid);
			model.addAttribute("user", vo);
		}

		return path;
	}

}
