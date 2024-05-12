package com.itwillbs.article;

public interface ArticleDAO {

	public int selectAno() throws Exception;

	public int insertArticle(ArticleVO avo) throws Exception;

	public ArticleVO selectArticle(Integer anumber) throws Exception;

	public int updateArticle(ArticleVO avo) throws Exception;

	public int countLike(LikecntVO lvo) throws Exception;

	public int insertLike(LikecntVO lvo) throws Exception;

	public int deleteLike(LikecntVO lvo) throws Exception;

}
