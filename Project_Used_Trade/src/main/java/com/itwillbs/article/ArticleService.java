package com.itwillbs.article;

import java.util.List;

public interface ArticleService {

	public int addArticle(ArticleVO avo) throws Exception;

	public ArticleVO getArticle(Integer anumber) throws Exception;

	public int modifyArticle(ArticleVO avo) throws Exception;

	public int checkLike(LikecntVO lvo) throws Exception;

	public void like(LikecntVO lvo) throws Exception;

	public void dislike(LikecntVO lvo) throws Exception;

	public List<ArticleVO> getArticleList(Criteria cri) throws Exception;

	public List<ArticleVO> getNotiList(Criteria cri) throws Exception;

	public List<ArticleVO> getNotiList5() throws Exception;

	public ArticleVO getAds() throws Exception;

	public int deleteArticle(ArticleVO avo) throws Exception;

	public void addComment(CommentVO cvo) throws Exception;

	public void modifyComment(CommentVO cvo) throws Exception;

	public void deleteComment(CommentVO cvo) throws Exception;

	public List<CommentVO> getComment(Integer anumber) throws Exception;

	public int countArticle(Criteria cri) throws Exception;

	public int countNoti(Criteria cri) throws Exception;

	public int countComment(Integer anumber) throws Exception;

}
