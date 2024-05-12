package com.itwillbs.article;

public interface ArticleService {

	public int addArticle(ArticleVO avo) throws Exception;

	public ArticleVO getArticle(Integer anumber) throws Exception;

	public int modifyArticle(ArticleVO avo) throws Exception;

	public int checkLike(LikecntVO lvo) throws Exception;

	public int like(LikecntVO lvo) throws Exception;

	public int dislike(LikecntVO lvo) throws Exception;

}
