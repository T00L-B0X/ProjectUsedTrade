package com.itwillbs.user;

import java.sql.Timestamp;
import java.util.List;

import lombok.Data;

@Data
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
	
}
