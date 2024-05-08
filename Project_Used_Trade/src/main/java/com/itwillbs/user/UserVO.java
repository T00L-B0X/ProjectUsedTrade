package com.itwillbs.user;

import java.sql.Timestamp;
import java.util.List;



public class UserVO {
	
	private String userid;
	private String userpw;
	private String uemail;
	private String usernm;
	private String ubirth;
	private String gender;
	private String uphone;
	private Timestamp regdate;
	private Timestamp edtdat;
	
	private List<AuthVO> authList;
	private List<UGradeVO> gradeList;
	public String getUserid() {
		return userid;
	}
	public String getUserpw() {
		return userpw;
	}
	public String getUemail() {
		return uemail;
	}
	public String getUsernm() {
		return usernm;
	}
	public String getUbirth() {
		return ubirth;
	}
	public String getGender() {
		return gender;
	}
	public String getUphone() {
		return uphone;
	}
	public Timestamp getRegdate() {
		return regdate;
	}
	public Timestamp getEdtdat() {
		return edtdat;
	}
	public List<AuthVO> getAuthList() {
		return authList;
	}
	public List<UGradeVO> getGradeList() {
		return gradeList;
	}
	public void setUserid(String userid) {
		this.userid = userid;
	}
	public void setUserpw(String userpw) {
		this.userpw = userpw;
	}
	public void setUemail(String uemail) {
		this.uemail = uemail;
	}
	public void setUsernm(String usernm) {
		this.usernm = usernm;
	}
	public void setUbirth(String ubirth) {
		this.ubirth = ubirth;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	public void setUphone(String uphone) {
		this.uphone = uphone;
	}
	public void setRegdate(Timestamp regdate) {
		this.regdate = regdate;
	}
	public void setEdtdat(Timestamp edtdat) {
		this.edtdat = edtdat;
	}
	public void setAuthList(List<AuthVO> authList) {
		this.authList = authList;
	}
	public void setGradeList(List<UGradeVO> gradeList) {
		this.gradeList = gradeList;
	}
	@Override
	public String toString() {
		return "UserVO [userid=" + userid + ", userpw=" + userpw + ", uemail=" + uemail + ", usernm=" + usernm
				+ ", ubirth=" + ubirth + ", gender=" + gender + ", uphone=" + uphone + ", regdate=" + regdate
				+ ", edtdat=" + edtdat + ", authList=" + authList + ", gradeList=" + gradeList + "]";
	}
	
	
	
	
}
