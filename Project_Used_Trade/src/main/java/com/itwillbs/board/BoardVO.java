package com.itwillbs.board;

import com.itwillbs.member.MemberVO;

/**
 * @author ITWILL
 *
 */
public class BoardVO {
	
	private int bno;

	private String title;
	private String content;
	private String writer;  // userid
	private MemberVO vo;
	
	// DB에는 없는 변수
	private String user_name;
	
	
	public MemberVO getVo() {
		return vo;
	}
	public void setVo(MemberVO vo) {
		this.vo = vo;
	}
	public int getBno() {
		return bno;
	}
	public void setBno(int bno) {
		this.bno = bno;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getWriter() {
		return writer;
	}
	public void setWriter(String writer) {
		this.writer = writer;
	}
	
	public String getUser_name() {
		return user_name;
	}
	public void setUser_name(String user_name) {
		this.user_name = user_name;
	}
	
	@Override
	public String toString() {
		return "BoardVO [bno=" + bno + ", title=" + title + ", content=" + content + ", writer=" + writer + ", vo=" + vo
				+ ", user_name=" + user_name + "]";
	}

	
	
	
	

}
