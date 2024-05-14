package com.itwillbs.article;

import java.sql.Timestamp;

public class CommentVO {

	private int cnumber; // 댓글번호
	private int anumber; // 원글번호
	private int pnumber; // 상위댓글번호
	private int commliv; // 댓글 깊이
	private String userid;
	private String content;
	private Timestamp regdate;
	private String ewriter;
	private Timestamp edtdate;
	private String deleted;

	public int getCnumber() {
		return cnumber;
	}

	public void setCnumber(int cnumber) {
		this.cnumber = cnumber;
	}

	public int getAnumber() {
		return anumber;
	}

	public void setAnumber(int anumber) {
		this.anumber = anumber;
	}

	public int getPnumber() {
		return pnumber;
	}

	public void setPnumber(int pnumber) {
		this.pnumber = pnumber;
	}

	public int getCommliv() {
		return commliv;
	}

	public void setCommliv(int commliv) {
		this.commliv = commliv;
	}

	public String getUserid() {
		return userid;
	}

	public void setUserid(String userid) {
		this.userid = userid;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public Timestamp getRegdate() {
		return regdate;
	}

	public void setRegdate(Timestamp regdate) {
		this.regdate = regdate;
	}

	public String getEwriter() {
		return ewriter;
	}

	public void setEwriter(String ewriter) {
		this.ewriter = ewriter;
	}

	public Timestamp getEdtdate() {
		return edtdate;
	}

	public void setEdtdate(Timestamp edtdate) {
		this.edtdate = edtdate;
	}

	public String getDeleted() {
		return deleted;
	}

	public void setDeleted(String deleted) {
		this.deleted = deleted;
	}

	@Override
	public String toString() {
		return "CommentVO [cnumber=" + cnumber + ", anumber=" + anumber + ", pnumber=" + pnumber + ", commliv="
				+ commliv + ", userid=" + userid + ", content=" + content + ", regdate=" + regdate + ", ewriter="
				+ ewriter + ", edtdate=" + edtdate + ", deleted=" + deleted + "]";
	}

}