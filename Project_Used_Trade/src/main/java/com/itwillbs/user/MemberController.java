package com.itwillbs.user;

import java.security.Principal;
import java.util.Calendar;
import java.util.List;
import java.util.Random;

import javax.inject.Inject;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.EnableAsync;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.itwillbs.auction.AuctionService;
import com.itwillbs.auction.AuctionVO;
import com.itwillbs.pay.PayService;
import com.itwillbs.pay.PayVO;
import com.itwillbs.user.AuthVO;
import com.itwillbs.user.MemberVO;
import com.itwillbs.user.PasswordGenerator;
import com.itwillbs.user.MemberService;
import com.itwillbs.user.MailService;

@Controller
@EnableAsync
@RequestMapping(value = "/user/*")
public class MemberController {

	@Inject
	private MemberService bService;

	@Inject
	private PayService pService;
	
	@Inject
	private AuctionService aService;

	@Inject
	private MailService mailService;

	private static final Logger logger = LoggerFactory.getLogger(MemberController.class);

	// http://localhost:8088/user/login

	@Autowired
	private PasswordEncoder pwEncoder;

	@RequestMapping(value = "/join", method = RequestMethod.GET)
	public void join() throws Exception {
		logger.debug("join() 호출");

	}

	@RequestMapping(value = "/join", method = RequestMethod.POST)
	public String joinPOST(MemberVO vo, AuthVO avo, String userpw, String userid) throws Exception {
		logger.debug("joinPOST()");
		logger.debug("id==>" + userid);
		logger.debug("pw" + userpw);
		logger.debug("vo==>" + vo);

		vo.setUserpw(pwEncoder.encode(vo.getUserpw()));

		bService.boardJoin(vo);
		bService.boardAuthJoin(avo);

		// pvo.setUSERID(vo.getUserid());
		// logger.debug(pvo.toString());

		// Pay_ID 생성 (시간 정보를 섞어서 만듬)
		Calendar today = Calendar.getInstance();
		// int year = today.get(Calendar.YEAR); // 연도
		int month = today.get(Calendar.MONTH) + 1; // 월
		int day = today.get(Calendar.DAY_OF_MONTH); // 일
		int hours = today.get(Calendar.HOUR_OF_DAY); // 시
		int minutes = today.get(Calendar.MINUTE); // 분
		int seconds = today.get(Calendar.SECOND); // 초

		// 시간 요소를 적절한 자릿수로 변환하여 조합
		// long uniquePayId = year * 10000000000L + month * 100000000 + day * 1000000 +
		// hours * 10000 + minutes * 100 + seconds;
		int uniquePayId = month * 100000000 + day * 1000000 + hours * 10000 + minutes * 100 + seconds;

		String uniquePayIdStr = String.valueOf(uniquePayId);

		// 배열에 넣어 섞는 과정
		char[] charArray = uniquePayIdStr.toCharArray();

		Random random = new Random();
		for (int i = charArray.length - 1; i > 0; i--) {
			int j = random.nextInt(i + 1);
			char temp = charArray[i];
			charArray[i] = charArray[j];
			charArray[j] = temp;
		}

		int shuffledPayId = Integer.parseInt(new String(charArray));

		PayVO pvo = new PayVO();

		pvo.setUSERID(userid);
		pvo.setPAY_BALANCE(0);
		pvo.setPAY_ID(shuffledPayId);

		pService.insertMemberPay(pvo);

		return "redirect:/user/login";
	}

	@GetMapping("/login")
	public String loginForm(@RequestParam(value = "error", required = false) String error,
			@RequestParam(value = "exception", required = false) String exception, Model model) {

		model.addAttribute("error", error);
		model.addAttribute("exception", exception);
		return "/user/login";
	}

	/*
	 * @RequestMapping(value = "/login",method = RequestMethod.POST) public String
	 * loginPOST(String id,String pw,BoardVO vo,HttpSession session)throws Exception
	 * { logger.debug("loginPOST() 호출"); logger.debug("id:"+id);
	 * logger.debug("pw:"+pw);
	 * 
	 * BoardVO result = bService.boardLogin(vo);
	 * 
	 * if (result != null) { logger.debug("result=>"+result);
	 * session.setAttribute("result", result); return "redirect:/"; }else { return
	 * "login"; } }
	 */
	
	/*
	@RequestMapping(value = "/home", method = RequestMethod.GET)
	public String main(HttpSession session, Principal principal) throws Exception {
		logger.debug("main() 호출");

		String userid = principal.getName();

		MemberVO vo = bService.read(userid);
		session.setAttribute("user", vo);
		
		return "/main";
	}
	*/

	@RequestMapping(value = "/findId", method = RequestMethod.GET)
	public void findId() throws Exception {
		logger.debug("findId() 호출");
	}

	@RequestMapping(value = "/findId", method = RequestMethod.POST)
	public String findIdPOST(String usernm, String uemail, Model model, MemberVO vo) throws Exception {
		logger.debug("findIdPOST() 호출");
		logger.debug("name:" + usernm);
		logger.debug("email:" + uemail);

		List<MemberVO> list = bService.boardIdFind(vo);

		for (MemberVO result : list) {
			if (result.getUsernm().equals(usernm) && result.getUemail().equals(uemail)) {
				logger.debug("result=====>" + result);

				model.addAttribute("result", result);

				return "/user/showId";
			}

		}
		return "/user/showId";
	}

	@RequestMapping(value = "/showId", method = RequestMethod.GET)
	public void showId() {
		logger.debug("showId() 호출");
	}

	@RequestMapping(value = "/findPw", method = RequestMethod.GET)
	public void findPw() throws Exception {
		logger.debug("findPw() 호출");

	}

	@RequestMapping(value = "/findPw", method = RequestMethod.POST)

	public String findPwPOST(String userid, String uemail, Model model, MemberVO vo, RedirectAttributes attr)
			throws Exception {

		logger.debug("findPwPOST() 호출");
		logger.debug("id:" + userid);
		logger.debug("email:" + uemail);

		MemberVO result = bService.boardPwFind(vo);
		// model.addAttribute("result", result);

		// 사용자가 존재하는 경우에만 처리
		if (result != null) {
			// 임시 비밀번호 생성
			String temporaryPassword = PasswordGenerator.generateRandomPassword();
			// 암호화된 임시 비밀번호 생성
			String pw = pwEncoder.encode(temporaryPassword);
			// 사용자의 비밀번호를 암호화된 임시 비밀번호로 변경
			result.setUserpw(pw);
			// 변경된 비밀번호를 데이터베이스에 업데이트
			bService.updatePw(result);

			// 이메일로 임시 비밀번호 보내기
			StringBuffer sb = new StringBuffer();
			sb.append(" <html><head></head><body> ");
			sb.append(" <h1> 안녕하세요 *** 입니다. </h1> ");
			sb.append(" <p>임시 비밀번호: " + temporaryPassword + "</p> ");
			sb.append(" </body></html> ");

			mailService.sendMail(uemail, "임시 비밀번호 발급 안내", sb.toString());

			// 비밀번호 변경 후 메시지를 모델에 추가
			attr.addFlashAttribute("message", "임시 비밀번호가 이메일로 발송되었습니다. 새로운 비밀번호로 로그인해주세요.");

		} else {
			// 사용자가 존재하지 않는 경우에는 메시지를 모델에 추가
			attr.addFlashAttribute("message", "입력한 정보와 일치하는 사용자가 없습니다.");
		}
		return "redirect:/user/login";

	}

	@RequestMapping(value = "accessError", method = RequestMethod.GET)
	public void accessDenied(Authentication auth) throws Exception {
		logger.debug("accessDenied() 호출");
		logger.debug("접근 권한없는 접근이 발생");
		logger.debug("auth:" + auth);
	}

	/*
	 * @RequestMapping(value = "/user/logout", method = RequestMethod.GET) public
	 * void logout() throws Exception { logger.debug("logout() 호출"); }
	 */

	/*
	 * @PreAuthorize("hasAnyRole('ROLE_ADMIN','ROLE_MEMBER')")
	 * 
	 * @GetMapping(value = "/annoMember") public void doMember2() {
	 * 
	 * logger.debug("logined annotation member");
	 * 
	 * }
	 * 
	 * @Secured({"ROLE_ADMIN"})
	 * 
	 * @GetMapping(value = "/annoAdmin") public void doAdmin2() {
	 * logger.debug("admin annotation only"); }
	 */

	@PreAuthorize("hasAnyRole('ROLE_ADMIN','ROLE_USER')")
	@RequestMapping(value = "/mypage", method = RequestMethod.GET)
	public void mypage(Principal principal, Model model) throws Exception {
		logger.debug("mypage() 호출");
		
		String userid = principal.getName();
		
		PayVO pResultVO = pService.memberPay(userid);
		List<AuctionVO> vo = aService.sellInfo(userid);
		
		logger.debug("userid:"+userid);
		logger.debug("pResultVO:"+pResultVO);
		logger.debug("vo:"+vo);
		
		int total = 0;
		int sell = 0;

		for (AuctionVO auctionVO : vo) {
		    if (auctionVO.getAu_status() >= 0) {
		        total++;
		    }
		    if (auctionVO.getAu_status() == 0) {
		        sell++;
		    } 
		}

		model.addAttribute("total", total);
		model.addAttribute("sell", sell);
		
		
		model.addAttribute("pResultVO", pResultVO);
		model.addAttribute("vo", vo);
		
		
	}

	@PreAuthorize("hasAnyRole('ROLE_ADMIN','ROLE_USER')")
	@RequestMapping(value = "/modify", method = RequestMethod.GET)
	public void modify(Principal principal, Model model) throws Exception {
		logger.debug("modify() 호출");

		String userid = principal.getName();

		MemberVO vo = bService.read(userid);
		model.addAttribute("user", vo);

	}

	@PreAuthorize("hasAnyRole('ROLE_ADMIN','ROLE_USER')")
	@RequestMapping(value = "/modify", method = RequestMethod.POST)
	public String modifyPOST(MemberVO vo, Model model, String userid, String userpw) throws Exception {
		logger.debug("modifyPOST() 호출");
		logger.debug("userid:" + userid);
		logger.debug("userpw:" + userpw);

		vo.setUserpw(pwEncoder.encode(vo.getUserpw()));

		bService.updateUser(vo);

		return "redirect:/user/login";

	}

}
