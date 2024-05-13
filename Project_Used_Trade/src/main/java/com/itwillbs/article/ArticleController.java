package com.itwillbs.article;

import java.security.Principal;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.itwillbs.chatting.ChatGroupService;
import com.itwillbs.chatting.ChatGroupVO;
import com.itwillbs.chatting.ChatMemberVO;
import com.itwillbs.user.MemberService;
import com.itwillbs.user.MemberVO;

@Controller
@RequestMapping("/article")
public class ArticleController {

	private static final Logger logger = LoggerFactory.getLogger(ArticleController.class);

	@Inject
	private MemberService mService;

	@Inject
	private ArticleService aService;

	@Inject
	private ChatGroupService chatService;

	// http://localhost:8088/article/articles
	@GetMapping("/articles")
	public String addArticle(Principal principal, Model model) throws Exception {
		logger.debug("ArticleController - addArticle - GET 호출");

		String path = "/user/login";

		if (principal != null) {
			String userid = principal.getName();

			MemberVO mvo = mService.read(userid);
			model.addAttribute("memberVO", mvo);

			path = "/article/articles";
		}

		return path;
	}

	@PostMapping("/articles")
	public ResponseEntity<Integer> addArticle(@RequestBody ArticleVO avo) throws Exception {
		logger.debug("ArticleController - addArticle - POST 호출");

		ResponseEntity<Integer> ano = ResponseEntity.badRequest().build();

		avo.setCategry("동네 소식");
		int result = aService.addArticle(avo);

		if (result == 1) {
			ano = ResponseEntity.ok().body(avo.getAnumber());
		}

		return ano;
	}

	@GetMapping("/articles/{anumber}")
	public String getArticle(@PathVariable("anumber") int anumber, Principal principal, Model model) throws Exception {
		logger.debug("ArticleController - getArticle - GET 호출");

		String path = "article/absence";

		ArticleVO avo = aService.getArticle(anumber);
		MemberVO mvo = null;
		if (principal != null) {
			String userid = principal.getName();

			mvo = mService.read(userid);
			model.addAttribute("memberVO", mvo);
		}

		if (avo != null && avo.getDeleted().equals("0")) {
			LikecntVO lvo = new LikecntVO();
			lvo.setUserid(mvo.getUserid());
			lvo.setAnumber(avo.getAnumber());
			logger.debug("lvo : " + lvo);
			logger.debug("lvo : " + lvo);

			model.addAttribute("like", aService.checkLike(lvo));
			model.addAttribute("articleVO", avo);

			path = "/article/read";
		}

		return path;
	}

	@PostMapping("/like")
	@ResponseBody
	public void like(@RequestBody LikecntVO lvo) throws Exception {
		logger.debug("ArticleController - like - POST 호출");
		logger.debug("lvo: " + lvo);

		aService.like(lvo);
	}

	@DeleteMapping("/dislike")
	@ResponseBody
	public void dislike(@RequestBody LikecntVO lvo) throws Exception {
		logger.debug("ArticleController - dislike - DELETE 호출");

		aService.dislike(lvo);
	}

	@PreAuthorize("hasRole('ROLE_ADMIN') or #principal.getName() == #avo.getUserid()")
	@GetMapping("/modify/{anumber}")
	public String modifyArticle(@PathVariable("anumber") int anumber, Principal principal, Model model)
			throws Exception {
		logger.debug("ArticleController - modifyArticle - GET 호출");

		String path = "/user/accessError";

		if (principal != null) {
			String userid = principal.getName();
			
			MemberVO mvo = mService.read(userid);
			model.addAttribute("memberVO", mvo);

			ArticleVO avo = aService.getArticle(anumber);

			if (avo != null && avo.getDeleted().equals("0")) {
				model.addAttribute("articleVO", avo);

				path = "/article/modify";
			}
		}

		return path;

	}

	@PutMapping("/modify/{anumber}")
	public ResponseEntity<Integer> modifyArticle(@RequestBody ArticleVO avo) throws Exception {
		logger.debug("ArticleController - modifyArticle - PUT 호출");

		ResponseEntity<Integer> ano = ResponseEntity.badRequest().build();

		int result = aService.modifyArticle(avo);

		if (result == 1) {
			ano = ResponseEntity.ok().body(avo.getAnumber());
		}

		return ano;
	}

	@PatchMapping("/delete/{anumber}")
	public ResponseEntity<Integer> deleteArticle(@RequestBody ArticleVO avo) throws Exception {
		logger.debug("ArticleController - deleteArticle - PATCH 호출");

		ResponseEntity<Integer> ano = ResponseEntity.badRequest().build();

		int result = aService.deleteArticle(avo);

		if (result == 1) {
			ano = ResponseEntity.ok().body(avo.getAnumber());
		}

		return ano;
	}

	// http://localhost:8088/article/list
	@GetMapping("/list")
	public void getArticleList(Principal principal, Model model) throws Exception {
		logger.debug("ArticleController - getArticleList - GET 호출");

		if (principal != null) {
			String userid = principal.getName();

			MemberVO mvo = mService.read(userid);
			model.addAttribute("memberVO", mvo);
		}

		model.addAttribute("NotiList5", aService.getNotiList5());
		model.addAttribute("ArticleList", aService.getArticleList());
		model.addAttribute("ad1", aService.getAds());
		model.addAttribute("ad2", aService.getAds());
		model.addAttribute("ad3", aService.getAds());
	}

	@ResponseBody
	@RequestMapping(value = "/connectChat/{anumber}", method = RequestMethod.POST)
	public ResponseEntity<String> connectChat(@PathVariable("anumber") int anumber, ChatMemberVO member,
			HttpServletRequest request) {
		int chat_no = 0;

		logger.debug("anumber: " + anumber);

		// goods_id를 사용하여 작성자 정보 등을 가져오거나 필요한 처리를 수행한다.
		member.setChat_no(chatService.getChatNo(chat_no));
		logger.debug("dsfafasd@@@@@@@@@@@@@: " + chatService.getMemberFromArticle(anumber));
		member.setUserid(chatService.getMemberFromArticle(anumber));
		logger.debug("asdsadasdsa: " + member.getUserid());
		member.setAuth_role("게시글 작성자");
		logger.debug("@@@@@@@@@@@@@@@@@@@@@: " + chatService.getUserNameFromArticle(anumber));
		member.setUsernm(chatService.getUserNameFromArticle(anumber));
		logger.debug("asdfasfsadf: " + member.getUsernm());
		logger.debug("DB에 저장되는 게시글 작성자 정보 : " + member);

		// 채팅 멤버에 추가하는 작업을 수행한다.
		int result = chatService.joinChatGroup(member);
		if (result > 0) {
			return new ResponseEntity<String>("SUCCESS", HttpStatus.OK);
		} else {
			return new ResponseEntity<String>("FAILED", HttpStatus.BAD_REQUEST);
		}
	}

	// 게시글에서 채팅하기 눌렀을 때 채팅방 생성
	@ResponseBody
	@RequestMapping(value = "/joinChat", method = RequestMethod.POST)
	public String joinChat(@RequestBody ChatGroupVO chat, MemberVO vo, HttpSession session, Principal principal) {
		String goPage = "";
		chat.setUserid(principal.getName());
		int result = chatService.insertChat(chat);
		if (result > 0) {
			goPage = "redirect:/read";
			logger.debug(" 생성 완료! ");
		} else {
			goPage = "";
			logger.debug(" 생성 실패! ");
		}
		return goPage;
	}

	@PreAuthorize("hasAnyRole('ROLE_ADMIN')")
	@GetMapping("notices")
	public String addNotices(Principal principal, Model model) throws Exception {
		logger.debug("ArticleController - addNotices - GET 호출");

		String path = "/user/login";

		if (principal != null) {
			String userid = principal.getName();

			MemberVO mvo = mService.read(userid);
			model.addAttribute("memberVO", mvo);

			path = "/article/articles";
		}

		return path;
	}

	@PreAuthorize("hasAnyRole('ROLE_ADMIN')")
	@PostMapping("notices")
	public ResponseEntity<Integer> addNotices(@RequestBody ArticleVO avo) throws Exception {
		logger.debug("ArticleController - addNotices - POST 호출");

		ResponseEntity<Integer> ano = ResponseEntity.badRequest().build();

		int result = aService.addArticle(avo);

		if (result == 1) {
			ano = ResponseEntity.ok().body(avo.getAnumber());
		}

		return ano;
	}
	
	// http://localhost:8088/article/notilist
		@GetMapping("/notilist")
		public void getNotiList(Principal principal, Model model) throws Exception {
			logger.debug("ArticleController - getArticleList - GET 호출");

			if (principal != null) {
				String userid = principal.getName();

				MemberVO mvo = mService.read(userid);
				model.addAttribute("memberVO", mvo);
			}

			model.addAttribute("NotiList", aService.getNotiList());
		}

}
