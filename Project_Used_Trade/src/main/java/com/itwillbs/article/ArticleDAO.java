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

	public List<ArticleVO> selectNotiList() throws Exception;

	public List<ArticleVO> selectNotiList5() throws Exception;

	public ArticleVO selectAds() throws Exception;

	public int deleteArticle(ArticleVO avo) throws Exception;

	public int selectCno() throws Exception;

	public void insertComment(CommentVO cvo) throws Exception;
	
	public void updateComment(CommentVO cvo) throws Exception;
	
	public void deleteComment(CommentVO cvo) throws Exception;
	
	public List<CommentVO> selectComment(Integer anumber) throws Exception;

}
