package com.itwillbs.article;

import java.security.Principal;

import javax.inject.Inject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

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

		if (principal != null) {
			String userid = principal.getName();

			MemberVO mvo = mService.read(userid);
			model.addAttribute("memberVO", mvo);
		}

		if (avo != null && avo.getDeleted().equals("0")) {
			LikecntVO lvo = new LikecntVO();
			lvo.setUserid(avo.getUserid());
			lvo.setAnumber(avo.getAnumber());

			model.addAttribute("like", aService.checkLike(lvo));
			model.addAttribute("articleVO", avo);

			path = "/article/read";
		}

		return path;
	}

	@PostMapping("/like")
	public void like(@RequestBody LikecntVO lvo) throws Exception {
		logger.debug("ArticleController - like - POST 호출");
		logger.debug("lvo: " + lvo);

		aService.like(lvo);
	}

	@DeleteMapping("/dislike")
	public void dislike(@RequestBody LikecntVO lvo) throws Exception {
		logger.debug("ArticleController - dislike - DELETE 호출");

		aService.dislike(lvo);
	}

	@GetMapping("/modify/{anumber}")
	public String modifyArticle(@PathVariable("anumber") int anumber, Principal principal, Model model)
			throws Exception {
		logger.debug("ArticleController - modifyArticle - GET 호출");

		String path = "/user/accessError";

		if (principal != null) {
			String userid = principal.getName();

			MemberVO mvo = mService.read(userid);

			ArticleVO avo = aService.getArticle(anumber);

			if (avo != null && avo.getDeleted().equals("0")) {
				if (mvo.getUserid().equals(avo.getUserid())) {
					model.addAttribute("articleVO", avo);

					path = "/article/modify";
				}
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

}
