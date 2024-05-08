package com.itwillbs.board;

import java.util.List;

import javax.inject.Inject;

import org.apache.ibatis.session.SqlSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

@Repository("BoardDAO")
public class BoardDAOImpl implements BoardDAO {
	
	@Inject
	private SqlSession sqlSession;
	
	private static final Logger logger = LoggerFactory.getLogger(BoardDAOImpl.class);
	
	private static final String NAMESPACE = "com.itwillbs.mapper.BoardMapper";

	@Override
	public void boardCreate(BoardVO vo) throws Exception {
		logger.info(" boardCreate(BoardVO vo) -> mapper 호출 ");
		sqlSession.insert(NAMESPACE+".createBoard", vo);
		logger.info(" mapper 실행 -> 서비스 이동");
	}

	@Override
	public List<BoardVO> boardListSelect() throws Exception {
		logger.info(" boardlistSelect() 호출 ");
		
		return sqlSession.selectList(NAMESPACE + ".selectBoardList");
	}

	@Override
	public BoardVO boardSelect(Integer bno) throws Exception {
		logger.info(" boardSelect(Integer bno) 호출 ");
		
		return sqlSession.selectOne(NAMESPACE + ".selectBoard", bno);
	}

}
