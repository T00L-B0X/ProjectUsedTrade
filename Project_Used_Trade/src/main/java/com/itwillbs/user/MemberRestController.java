package com.itwillbs.user;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.itwillbs.user.PasswordGenerator;
import com.itwillbs.user.MemberService;
import com.itwillbs.user.MailService;

@RestController
public class MemberRestController {

	private static final Logger logger = LoggerFactory.getLogger(MemberRestController.class);

	@Inject
	private MemberService bService;

	@Inject
	private MailService mailService;

	@Autowired
	private PasswordEncoder pwEncoder;

	@PostMapping("/ConfirmId")
	public ResponseEntity<Integer> confirmId(@RequestParam("userid") String userid) throws Exception {

		int cnt = bService.boardIdCheck(userid);
		return ResponseEntity.ok().body(cnt);
	}

	@PostMapping("/sendEmail")
	public ResponseEntity<String> sendEmail(@RequestParam("uemail") String uemail) throws Exception {

		// email이 null이거나 비어있는 경우 실패로 처리
		if (uemail == null || uemail.isEmpty()) {
			// 실패 응답 반환

			return ResponseEntity.badRequest().contentType(MediaType.valueOf("text/plain; charset=UTF-8"))
					.body("이메일 오류!!");
		}

		// 사용자가 존재하는 경우에만 처리
		// 이 부분은 email이 null이 아닌 경우에만 실행됩니다.
		// 실패 응답을 반환하지 않고 정상적인 응답을 반환하는 것이라면 이 부분에서 사용자 존재 여부 등을 확인하고 처리해야 합니다.

		// 임시 비밀번호 생성
		String pw = PasswordGenerator.generateRandomPassword();

		// 이메일로 임시 비밀번호 보내기
		StringBuffer sb = new StringBuffer();
		sb.append(" <html><head></head><body> ");
		sb.append(" <h1> 안녕하세요 test 입니다. </h1> ");
		sb.append(" <p>임시 비밀번호: " + pw + "</p> ");
		sb.append(" </body></html> ");

		mailService.sendMail(uemail, "임시 비밀번호 발급 안내", sb.toString());

		// 성공 응답 반환
		return ResponseEntity.ok().body(pw);
	}

	@PostMapping(value = "/checkPw")
	public ResponseEntity<Integer> checkPw(@RequestParam("nowpw") String nowpw, HttpServletRequest request)
			throws Exception {

		System.out.println("nowpw:" + nowpw);

		MemberVO vo = (MemberVO) request.getSession().getAttribute("user");

		System.out.println("vo:" + vo);
		String pw = vo.getUserpw();

		System.out.println("pw:" + pw);

		boolean pwchk = pwEncoder.matches(nowpw, pw);
		System.out.println("pwchk:" + pwchk);

		int result = pwchk ? 1 : 0;

		System.out.println("result:" + result);

		return ResponseEntity.ok().body(result);
	}

	@PostMapping(value = "/updatePw")
	public ResponseEntity<Integer> updatePw(@RequestParam("newpw") String newpw, HttpServletRequest request)
			throws Exception {

		System.out.println("newpw:" + newpw);

		MemberVO vo = (MemberVO) request.getSession().getAttribute("user");

		System.out.println("vo:" + vo);
		String pw = vo.getUserpw();

		System.out.println("pw:" + pw);

		boolean pwchk = pwEncoder.matches(newpw, pw);
		System.out.println("pwchk:" + pwchk);

		int result = pwchk ? 1 : 0;

		System.out.println("result:" + result);

		return ResponseEntity.ok().body(result);

	}

}
