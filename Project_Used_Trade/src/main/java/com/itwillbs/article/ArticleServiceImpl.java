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

		artDAO.plusView(anumber);

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
	public void like(LikecntVO lvo) throws Exception {
		logger.debug("like(LikecntVO lvo) 호출");

		artDAO.insertLike(lvo);
		artDAO.plusLike(lvo);
	}

	@Override
	public void dislike(LikecntVO lvo) throws Exception {
		logger.debug("dislike(LikecntVO lvo) 호출");

		artDAO.deleteLike(lvo);
		artDAO.minusLike(lvo);
	}

	@Override
	public List<ArticleVO> getArticleList(Criteria cri) throws Exception {
		logger.debug("getArticleList() 호출");

		return artDAO.selectArticleList(cri);
	}

	@Override
	public List<ArticleVO> getNotiList(Criteria cri) throws Exception {
		logger.debug("getNotiList(Criteria cri) 호출");

		return artDAO.selectNotiList(cri);
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

	@Override
	public int countArticle(Criteria cri) throws Exception {
		logger.debug("countArticle(Criteria cri) 호출");

		return artDAO.selectArticleCount(cri);
	}

	@Override
	public int countNoti(Criteria cri) throws Exception {
		logger.debug("countNoti(Criteria cri) 호출");

		return artDAO.selectNotiCount(cri);
	}

	@Override
	public int countComment(Integer anumber) throws Exception {
		logger.debug("countComment(Integer anumber)");
		return artDAO.countComment(anumber);
	}

}
