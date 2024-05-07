package com.itwillbs.member;

import javax.inject.Inject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service("MemberService")
public class MemberServiceImpl implements MemberService {
	
	
	private static final Logger logger = LoggerFactory.getLogger(MemberServiceImpl.class);
	
	@Inject
	private MemberDAO mdao;
	
	@Override
	public void memberJoin(MemberVO vo) {
		mdao.insertMember(vo);
		logger.debug(" S : 회원가입 완료 ");
	}

	@Override
	public MemberVO memberLogin(MemberVO vo) {
		MemberVO resultVO = mdao.loginMember(vo);
		return resultVO;
	}

}
