package com.itwillbs.board;

import com.itwillbs.member.MemberVO;

public class BoardVO {
	
	private int bno;
	private String title;
	private String content;
	private String writer;
	private MemberVO vo;
	
	
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
	
	@Override
	public String toString() {
		return "BoardVO [bno=" + bno + ", title=" + title + ", content=" + content + ", writer=" + writer + "]";
	}
	
	
	
	

}
