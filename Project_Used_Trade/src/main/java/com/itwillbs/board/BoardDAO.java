package com.itwillbs.board;

import java.util.List;

public interface BoardDAO {
	
	// 글쓰기
	public void boardCreate(BoardVO vo) throws Exception;
	
	// 글 목록 조회
	public List<BoardVO> boardListSelect() throws Exception;
	
	// 글 내용 조회
	public BoardVO boardSelect(Integer bno) throws Exception;

}
