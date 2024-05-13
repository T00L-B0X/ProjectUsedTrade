package com.itwillbs.article;

import java.util.List;

public interface ArticleDAO {

	public int selectAno() throws Exception;

	public int insertArticle(ArticleVO avo) throws Exception;

	public ArticleVO selectArticle(Integer anumber) throws Exception;

	public int updateArticle(ArticleVO avo) throws Exception;

	public int countLike(LikecntVO lvo) throws Exception;

	public int insertLike(LikecntVO lvo) throws Exception;

	public int deleteLike(LikecntVO lvo) throws Exception;

	public List<ArticleVO> selectArticleList() throws Exception;

	public List<ArticleVO> selectNotiList5() throws Exception;

	public ArticleVO selectAds() throws Exception;

	public int deleteArticle(ArticleVO avo) throws Exception;

}
