package com.itwillbs.board;

import java.util.List;

import javax.inject.Inject;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.mysql.cj.Session;

@Controller
public class BoardController {
	@Inject
	private BoardService bService;
	
	private static final Logger logger = LoggerFactory.getLogger(BoardController.class);
	
	// 글쓰기 GET
	// http://localhost:8088/register
	@RequestMapping(value = "/register" , method = RequestMethod.GET)
	public void registerGET() throws Exception {
		logger.info(" /register -> registerGET() 호출 ");
		logger.info(" /register.jsp 연결 ");
	}
	
	// 글쓰기 POST
	@RequestMapping(value = "/register" , method = RequestMethod.POST)
	public String registerPOST(BoardVO vo, HttpSession session) throws Exception {
		logger.info(" registerPOST(BoardVO vo) 호출 ");
		
		
		logger.info(" 전달정보 : " + vo);
		vo.setWriter((String)session.getAttribute("userid"));
		bService.regist(vo);
		
		logger.info(" 글쓰기 완료! -> 리스트 페이지 이동 ");
		
		return "redirect:/list";
	}
	
	
	// 리스트GETddd
	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public void listGET(Model model, HttpSession session) throws Exception {
		logger.info(" listGET()실행");
		logger.info(" list.jsp 연결");
		
		List<BoardVO> boardList = bService.getList();
		logger.info("list.size : " + boardList.size());
		
		model.addAttribute("boardList", boardList);
	}
	
	
	// 본문읽기 GET
	// http://localhost:8088/read?bno=000
	@RequestMapping(value = "/read", method = RequestMethod.GET)
	public void readGET(@RequestParam("bno") int bno, Model model, HttpSession session) throws Exception {
		logger.info(" /read -> readGET() 호출 ");
		logger.info(" bno : " + bno);
		
		BoardVO vo = bService.read(bno);
		model.addAttribute("vo", vo);
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
