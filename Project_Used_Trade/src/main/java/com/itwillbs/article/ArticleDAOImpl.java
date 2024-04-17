package com.itwillbs.article;

import javax.inject.Inject;

import org.apache.ibatis.session.SqlSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

@Repository("ArticleDAO")
public class ArticleDAOImpl implements ArticleDAO {

	private static final Logger logger = LoggerFactory.getLogger(ArticleDAOImpl.class);

	private static final String NAMESPACE = "com.itwillbs.mapper.ArticleMapper";

	@Inject
	private SqlSession sqlSession;

}
