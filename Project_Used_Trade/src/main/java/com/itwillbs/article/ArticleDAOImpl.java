package com.itwillbs.article;

import java.util.List;

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

	@Override
	public int selectAno() throws Exception {
		logger.debug("selectAno()");

		return sqlSession.selectOne(NAMESPACE + ".selectAno");
	}

	@Override
	public int insertArticle(ArticleVO avo) throws Exception {
		logger.debug("insertTopic(ArticleVO avo)");
		logger.debug("avo:" + avo);

		return sqlSession.insert(NAMESPACE + ".insertArticle", avo);
	}

	@Override
	public ArticleVO selectArticle(Integer anumber) throws Exception {
		logger.debug("selectArticle(Integer bno) 호출");

		return sqlSession.selectOne(NAMESPACE + ".selectArticle", anumber);
	}

	@Override
	public int updateArticle(ArticleVO avo) throws Exception {
		logger.debug("updateArticle(ArticleVO avo) 호출");

		return sqlSession.update(NAMESPACE + ".updateArticle", avo);
	}

	@Override
	public int countLike(LikecntVO lvo) throws Exception {
		logger.debug("countLike(LikecntVO lvo) 호출");

		return sqlSession.selectOne(NAMESPACE + ".checkLike", lvo);
	}

	@Override
	public int insertLike(LikecntVO lvo) throws Exception {
		logger.debug("insertLike(LikecntVO lvo) 호출");
		logger.debug("lvo: " + lvo);

		return sqlSession.insert(NAMESPACE + ".insertLike", lvo);
	}

	@Override
	public int deleteLike(LikecntVO lvo) throws Exception {
		logger.debug("deleteLike(LikecntVO lvo) 호출");

		return sqlSession.delete(NAMESPACE + ".deleteLike", lvo);
	}

	@Override
	public List<ArticleVO> selectArticleList() throws Exception {
		logger.debug("selectArticleList() 호출");

		return sqlSession.selectList(NAMESPACE + ".selectArticleList");
	}

	@Override
	public List<ArticleVO> selectNotiList5() throws Exception {
		logger.debug("selectNotiList5() 호출");

		return sqlSession.selectList(NAMESPACE + ".selectNotiList5");
	}

	@Override
	public ArticleVO selectAds() throws Exception {
		logger.debug("selectAds() 호출");

		return sqlSession.selectOne(NAMESPACE + ".selectAds");
	}

	@Override
	public int deleteArticle(ArticleVO avo) throws Exception {
		logger.debug("deleteArticle(ArticleVO avo) 호출");

		return sqlSession.delete(NAMESPACE + ".deleteArticle", avo);
	}

}
