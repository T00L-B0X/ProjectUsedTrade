package com.itwillbs.article;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping(value = "/article/*")
public class ArticleController {

	private static final Logger logger = LoggerFactory.getLogger(ArticleController.class);

	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public void ArticleListGET() throws Exception {
		logger.debug("ArticleController - ArticleListGET() »£√‚");
	}

}
