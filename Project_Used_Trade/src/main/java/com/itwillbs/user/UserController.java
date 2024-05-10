package com.itwillbs.user;

import java.security.Principal;
import java.util.List;

import javax.inject.Inject;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.itwillbs.user.AuthVO;
import com.itwillbs.user.UserVO;
import com.itwillbs.user.PasswordGenerator;
import com.itwillbs.user.UserDAO;
import com.itwillbs.user.UserService;
import com.itwillbs.user.MailService;

@Controller
@EnableAsync
@RequestMapping(value = "/user/*")
public class UserController {

	@Inject
	private UserService bService;
	
	@Inject
	private MailService mailService;

	private static final Logger logger = LoggerFactory.getLogger(UserController.class);

	// http://localhost:8088/user/login
	
	@Autowired
	private PasswordEncoder pwEncoder;

	@RequestMapping(value = "/join", method = RequestMethod.GET)
	public void join() throws Exception {
		logger.debug("join() 호출");

	}

	@RequestMapping(value = "/join", method = RequestMethod.POST)
	public String joinPOST(UserVO vo,AuthVO avo, String userpw,String userid) throws Exception{
		logger.debug("joinPOST()");
		logger.debug("id==>"+userid);
		logger.debug("pw"+userpw);
		logger.debug("vo==>"+vo);
		
		vo.setUserpw(pwEncoder.encode(vo.getUserpw()));

		
		bService.boardJoin(vo);
		bService.boardAuthJoin(avo);
		
		

		return "redirect:/user/login";
	}

	@GetMapping("/login")
	public String loginForm(@RequestParam(value = "error", required = false) String error, 
				@RequestParam(value = "exception", required = false) String exception,
				Model model) {
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
	
	@RequestMapping(value = "/home",method = RequestMethod.GET)
	public String main(HttpSession session, Principal principal) throws Exception {
		logger.debug("main() 호출");
		
	
		String userid = principal.getName();
		UserVO vo = bService.read(userid); 
        session.setAttribute("user", vo);
		
          
        return "/user/home";

		
	}
	 
	@RequestMapping(value = "/findId", method = RequestMethod.GET)
	public void findId() throws Exception {
		logger.debug("findId() 호출");
	}

	@RequestMapping(value = "/findId", method = RequestMethod.POST)
	public String findIdPOST(String usernm, String uemail, Model model, UserVO vo) throws Exception {
		logger.debug("findIdPOST() 호출");
		logger.debug("name:" + usernm);
		logger.debug("email:" + uemail);

		List<UserVO> list = bService.boardIdFind(vo);

		for (UserVO result : list) {
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
	public String findPwPOST(String userid, String uemail, Model model, UserVO vo,RedirectAttributes attr) throws Exception {
		logger.debug("findPwPOST() 호출");
		logger.debug("id:" + userid);
		logger.debug("email:" + uemail);

		
		UserVO result = bService.boardPwFind(vo);		  
		//model.addAttribute("result", result);
		 
		
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
		logger.debug("auth:"+auth);
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
	@RequestMapping(value = "/mypage",method = RequestMethod.GET)
	public void mypage() throws Exception {
		logger.debug("mypage() 호출");
		
	}
	
	@PreAuthorize("hasAnyRole('ROLE_ADMIN','ROLE_USER')")
	@RequestMapping(value = "/modify",method = RequestMethod.GET)
	public void modify(Principal principal,Model model) throws Exception{
		logger.debug("modify() 호출");
		
		String userid = principal.getName();
		UserVO vo = bService.read(userid); 
        model.addAttribute("user", vo);

	}
	
	@PreAuthorize("hasAnyRole('ROLE_ADMIN','ROLE_USER')")
	@RequestMapping(value = "/modify",method = RequestMethod.POST)
	public String modifyPOST(UserVO vo,Model model,String userid,String userpw) throws Exception{
		logger.debug("modifyPOST() 호출");
		logger.debug("userid:"+userid);
		logger.debug("userpw:"+userpw);
		
		vo.setUserpw(pwEncoder.encode(vo.getUserpw()));
		
		bService.updateUser(vo);
		
		return "redirect:/user/login";
		
	}
	
}
