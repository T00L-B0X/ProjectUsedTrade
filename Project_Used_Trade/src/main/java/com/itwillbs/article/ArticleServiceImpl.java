package com.itwillbs.article;

import java.util.List;

import javax.inject.Inject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service("ArticleService")
public class ArticleServiceImpl implements ArticleService {

	private static final Logger logger = LoggerFactory.getLogger(ArticleServiceImpl.class);

	@Inject
	private ArticleDAO artDAO;

	@Override
	public int addArticle(ArticleVO avo) throws Exception {
		logger.debug("addTopic(ArticleVO vo) 호출");

		avo.setAnumber(artDAO.selectAno());
		avo.setEwriter(avo.getUserid());

		return artDAO.insertArticle(avo);
	}

	@Override
	public ArticleVO getArticle(Integer anumber) throws Exception {
		logger.debug("getTopic(int bno) 호출");

		return artDAO.selectArticle(anumber);
	}

	@Override
	public int modifyArticle(ArticleVO avo) throws Exception {
		logger.debug("modifyArticle(ArticleVO avo) 호출");

		return artDAO.updateArticle(avo);
	}

	@Override
	public int checkLike(LikecntVO lvo) throws Exception {
		logger.debug("checkLike(LikecntVO lvo) 호출");

		return artDAO.countLike(lvo);
	}

	@Override
	public int like(LikecntVO lvo) throws Exception {
		logger.debug("like(LikecntVO lvo) 호출");
		// logger.debug("@@@@@@@"+artDAO.insertLike(lvo));

		return artDAO.insertLike(lvo);
	}

	@Override
	public int dislike(LikecntVO lvo) throws Exception {
		logger.debug("dislike(LikecntVO lvo) 호출");

		return artDAO.deleteLike(lvo);
	}

	@Override
	public List<ArticleVO> getArticleList() throws Exception {
		logger.debug("getArticleList() 호출");

		return artDAO.selectArticleList();
	}

	@Override
	public List<ArticleVO> getNotiList() throws Exception {
		logger.debug("getNotiList() 호출");

		return artDAO.selectNotiList();
	}

	@Override
	public List<ArticleVO> getNotiList5() throws Exception {
		logger.debug("getNotiList5() 호출");

		return artDAO.selectNotiList5();
	}

	@Override
	public ArticleVO getAds() throws Exception {
		logger.debug("getAds() 호출");
		return artDAO.selectAds();
	}

	@Override
	public int deleteArticle(ArticleVO avo) throws Exception {
		logger.debug("deleteArticle(ArticleVO avo) 호출");

		return artDAO.deleteArticle(avo);
	}

	@Override
	public void addComment(CommentVO cvo) throws Exception {
		logger.debug("addComment(CommentVO cvo) 호출");

		cvo.setCnumber(artDAO.selectCno());
		cvo.setEwriter(cvo.getUserid());		
		
		artDAO.insertComment(cvo);
	}

	@Override
	public void modifyComment(CommentVO cvo) throws Exception {
		logger.debug("modifyComment(CommentVO cvo) 호출");

		artDAO.updateComment(cvo);

	}

	@Override
	public void deleteComment(CommentVO cvo) throws Exception {
		logger.debug("deleteComment(CommentVO cvo) 호출");

		artDAO.deleteComment(cvo);

	}

	@Override
	public List<CommentVO> getComment(Integer anumber) throws Exception {
		logger.debug("getComment(Integer anumber) 호출");
		
		return artDAO.selectComment(anumber);
	}

}
