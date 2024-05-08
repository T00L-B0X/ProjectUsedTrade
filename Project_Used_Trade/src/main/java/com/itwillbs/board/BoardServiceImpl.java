package com.itwillbs.board;

import java.util.List;

import javax.inject.Inject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service("BoardService")
public class BoardServiceImpl implements BoardService {
	
	@Inject
	private BoardDAO bdao;
	
	private static final Logger logger = LoggerFactory.getLogger(BoardServiceImpl.class);

	@Override
	public void regist(BoardVO vo) throws Exception {
		logger.info(" regist(BoardVO vo) 실행 -> DAO 글쓰기 동작 호출 ");
		
		bdao.boardCreate(vo);
		
		logger.info(" 서비스 동작 완료 -> 컨트롤러 이동 ");
		
	}

	@Override
	public List<BoardVO> getList() throws Exception {
		logger.info(" getList() 실행 ");
		return bdao.boardListSelect();
	}

	@Override
	public BoardVO read(Integer bno) throws Exception {
		logger.info(" read(Integer bno) 실행 ");
		return bdao.boardSelect(bno);
	}

}
