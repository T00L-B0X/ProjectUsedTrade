package com.itwillbs.user;

import java.sql.Timestamp;
import java.util.List;

public class MemberVO {
	private String userid;
	private String userpw;
	private String uemail;
	private String usernm;
	private String ubirth;
	private String gender;
	private String uphone;
	private Timestamp regdate;
	private Timestamp edtdat;
	private String uintro;
	
	public String getUintro() {
		return uintro;
	}
	public void setUintro(String uintro) {
		this.uintro = uintro;
	}
	private List<AuthVO> authList;
	private List<UGradeVO> gradeList;
	public String getUserid() {
		return userid;
	}
	public void setUserid(String userid) {
		this.userid = userid;
	}
	public String getUserpw() {
		return userpw;
	}
	public void setUserpw(String userpw) {
		this.userpw = userpw;
	}
	public String getUemail() {
		return uemail;
	}
	public void setUemail(String uemail) {
		this.uemail = uemail;
	}
	public String getUsernm() {
		return usernm;
	}
	public void setUsernm(String usernm) {
		this.usernm = usernm;
	}
	public String getUbirth() {
		return ubirth;
	}
	public void setUbirth(String ubirth) {
		this.ubirth = ubirth;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	public String getUphone() {
		return uphone;
	}
	public void setUphone(String uphone) {
		this.uphone = uphone;
	}
	public Timestamp getRegdate() {
		return regdate;
	}
	public void setRegdate(Timestamp regdate) {
		this.regdate = regdate;
	}
	public Timestamp getEdtdat() {
		return edtdat;
	}
	public void setEdtdat(Timestamp edtdat) {
		this.edtdat = edtdat;
	}
	public List<AuthVO> getAuthList() {
		return authList;
	}
	public void setAuthList(List<AuthVO> authList) {
		this.authList = authList;
	}
	public List<UGradeVO> getGradeList() {
		return gradeList;
	}
	public void setGradeList(List<UGradeVO> gradeList) {
		this.gradeList = gradeList;
	}
	@Override
	public String toString() {
		return "MemberVO [userid=" + userid + ", userpw=" + userpw + ", uemail=" + uemail + ", usernm=" + usernm
				+ ", ubirth=" + ubirth + ", gender=" + gender + ", uphone=" + uphone + ", regdate=" + regdate
				+ ", edtdat=" + edtdat + ", uintro=" + uintro + ", authList=" + authList + ", gradeList=" + gradeList
				+ "]";
	}
	
}
