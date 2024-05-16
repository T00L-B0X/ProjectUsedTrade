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
	public List<ArticleVO> selectArticleList(Criteria cri) throws Exception {
		logger.debug("selectArticleList() 호출");

		return sqlSession.selectList(NAMESPACE + ".selectArticleList", cri);
	}

	@Override
	public List<ArticleVO> selectNotiList(Criteria cri) throws Exception {
		logger.debug("selectNotiList(Criteria cri) 호출");

		return sqlSession.selectList(NAMESPACE + ".selectNotiList", cri);
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

	@Override
	public int selectCno() throws Exception {
		logger.debug("selectCno() 호출");

		return sqlSession.selectOne(NAMESPACE + ".selectCno");
	}

	@Override
	public void insertComment(CommentVO cvo) throws Exception {
		logger.debug("insertComment(CommentVO cvo) 호출");

		sqlSession.insert(NAMESPACE + ".insertComment", cvo);
	}

	@Override
	public void updateComment(CommentVO cvo) throws Exception {
		logger.debug("updateComment(CommentVO cvo) 호출");

		sqlSession.update(NAMESPACE + ".updateComment", cvo);

	}

	@Override
	public void deleteComment(CommentVO cvo) throws Exception {
		logger.debug("deleteComment(CommentVO cvo) 호출");

		sqlSession.update(NAMESPACE + ".deleteComment", cvo);

	}

	@Override
	public List<CommentVO> selectComment(Integer anumber) throws Exception {
		logger.debug("selectComment(Integer anumber) 호출");

		return sqlSession.selectList(NAMESPACE + ".selectComment", anumber);
	}

	@Override
	public int selectArticleCount(Criteria cri) throws Exception {
		logger.debug("selectArticleCount(Criteria cri) 호출");

		return sqlSession.selectOne(NAMESPACE + ".selectArticleCount", cri);
	}

	@Override
	public int selectNotiCount(Criteria cri) throws Exception {
		logger.debug("selectNotiCount(Criteria cri) 호출");

		return sqlSession.selectOne(NAMESPACE + ".selectNotiCount", cri);
	}

	@Override
	public int countComment(Integer anumber) throws Exception {
		logger.debug("countComment(Integer anumber)");

		return sqlSession.selectOne(NAMESPACE + ".countComment", anumber);
	}

	@Override
	public int countLikeArticle(int anumber) throws Exception {
		logger.debug("countLikeArticle(int anumber) 호출");
		return sqlSession.selectOne(NAMESPACE + ".countLikeArticle", anumber);
	}

	@Override
	public void plusLike(LikecntVO lvo) throws Exception {
		logger.debug("plusLike(LikecntVO lvo) 호출");

		sqlSession.update(NAMESPACE + ".plusLike", lvo);
	}

	@Override
	public void minusLike(LikecntVO lvo) throws Exception {
		logger.debug("minusLike(LikecntVO lvo) 호출");

		sqlSession.update(NAMESPACE + ".minusLike", lvo);
	}

	@Override
	public void plusView(Integer anumber) throws Exception {
		// TODO Auto-generated method stub
		sqlSession.update(NAMESPACE + ".plusView", anumber);
	}

}
