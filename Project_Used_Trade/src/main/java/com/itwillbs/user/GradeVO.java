package com.itwillbs.user;



public class GradeVO {
	private String grade;
	private float grade_commission;
	public String getGrade() {
		return grade;
	}
	public void setGrade(String grade) {
		this.grade = grade;
	}
	public float getGrade_commission() {
		return grade_commission;
	}
	public void setGrade_commission(float grade_commission) {
		this.grade_commission = grade_commission;
	}
	@Override
	public String toString() {
		return "GradeVO [grade=" + grade + ", grade_commission=" + grade_commission + "]";
	}
	
	
}
