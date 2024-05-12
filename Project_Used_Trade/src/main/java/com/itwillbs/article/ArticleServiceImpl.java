package com.itwillbs.article;

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
		avo.setCategry("동네 소식");
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

		return artDAO.insertLike(lvo);
	}

	@Override
	public int dislike(LikecntVO lvo) throws Exception {
		logger.debug("dislike(LikecntVO lvo) 호출");

		return artDAO.deleteLike(lvo);
	}

}
