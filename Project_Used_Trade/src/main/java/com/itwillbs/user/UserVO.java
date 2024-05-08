package com.itwillbs.user;

import java.sql.Timestamp;
import java.util.List;



public class UserVO {
	
	private String id;
	private String pw;
	private String email;
	private String name;
	private String birthday;
	private String gender;
	private String phoneNumber;
	private Timestamp regdate;
	private Timestamp updatedate;
	
	private List<AuthVO> authList;
	private List<GradeVO> gradeList;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getPw() {
		return pw;
	}
	public void setPw(String pw) {
		this.pw = pw;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getBirthday() {
		return birthday;
	}
	public void setBirthday(String birthday) {
		this.birthday = birthday;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	public String getPhoneNumber() {
		return phoneNumber;
	}
	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}
	public Timestamp getRegdate() {
		return regdate;
	}
	public void setRegdate(Timestamp regdate) {
		this.regdate = regdate;
	}
	public Timestamp getUpdatedate() {
		return updatedate;
	}
	public void setUpdatedate(Timestamp updatedate) {
		this.updatedate = updatedate;
	}
	public List<AuthVO> getAuthList() {
		return authList;
	}
	public void setAuthList(List<AuthVO> authList) {
		this.authList = authList;
	}
	public List<GradeVO> getGradeList() {
		return gradeList;
	}
	public void setGradeList(List<GradeVO> gradeList) {
		this.gradeList = gradeList;
	}
	@Override
	public String toString() {
		return "UserVO [id=" + id + ", pw=" + pw + ", email=" + email + ", name=" + name + ", birthday=" + birthday
				+ ", gender=" + gender + ", phoneNumber=" + phoneNumber + ", regdate=" + regdate + ", updatedate="
				+ updatedate + ", authList=" + authList + ", gradeList=" + gradeList + "]";
	}
	
	
	
}
