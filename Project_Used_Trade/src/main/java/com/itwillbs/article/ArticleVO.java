package com.itwillbs.article;

import java.sql.Timestamp;

import org.apache.ibatis.type.Alias;

@Alias("ArticleVO")
public class ArticleVO {

	private int anumber;
	private String categry;
	private String locatns;
	private String artitle;
	private String userid;
	private String content;
	private Timestamp regdate;
	private String ewriter;
	private Timestamp edtdate;
	private int viewcnt;
	private int likecnt;
	private String deleted;

	public int getAnumber() {
		return anumber;
	}

	public void setAnumber(int anumber) {
		this.anumber = anumber;
	}

	public String getCategry() {
		return categry;
	}

	public void setCategry(String categry) {
		this.categry = categry;
	}

	public String getLocatns() {
		return locatns;
	}

	public void setLocatns(String locatns) {
		this.locatns = locatns;
	}

	public String getArtitle() {
		return artitle;
	}

	public void setArtitle(String artitle) {
		this.artitle = artitle;
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

	public int getViewcnt() {
		return viewcnt;
	}

	public void setViewcnt(int viewcnt) {
		this.viewcnt = viewcnt;
	}

	public int getLikecnt() {
		return likecnt;
	}

	public void setLikecnt(int likecnt) {
		this.likecnt = likecnt;
	}

	public String getDeleted() {
		return deleted;
	}

	public void setDeleted(String deleted) {
		this.deleted = deleted;
	}

	@Override
	public String toString() {
		return "ArticleVO [anumber=" + anumber + ", categry=" + categry + ", locatns=" + locatns + ", artitle="
				+ artitle + ", userid=" + userid + ", content=" + content + ", regdate=" + regdate + ", ewriter="
				+ ewriter + ", edtdate=" + edtdate + ", viewcnt=" + viewcnt + ", likecnt=" + likecnt + ", deleted="
				+ deleted + "]";
	}

}
