package com.itwillbs.article;

import java.util.List;

public interface ArticleService {

	public int addArticle(ArticleVO avo) throws Exception;

	public ArticleVO getArticle(Integer anumber) throws Exception;

	public int modifyArticle(ArticleVO avo) throws Exception;

	public int checkLike(LikecntVO lvo) throws Exception;

	public int like(LikecntVO lvo) throws Exception;

	public int dislike(LikecntVO lvo) throws Exception;

	public List<ArticleVO> getArticleList() throws Exception;

	public List<ArticleVO> getNotiList5() throws Exception;

	public ArticleVO getAds() throws Exception;

}
