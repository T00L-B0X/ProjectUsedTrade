package com.itwillbs.article;

import javax.inject.Inject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service("ArticleService")
public class ArticleServiceImpl {

	private static final Logger logger = LoggerFactory.getLogger(ArticleServiceImpl.class);

	@Inject
	private ArticleDAO artDAO;

}
