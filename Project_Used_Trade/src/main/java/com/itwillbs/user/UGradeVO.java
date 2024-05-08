package com.itwillbs.user;



public class UGradeVO {
	private String ugrade;
	private float feerage;
	public String getUgrade() {
		return ugrade;
	}
	public float getFeerage() {
		return feerage;
	}
	public void setUgrade(String ugrade) {
		this.ugrade = ugrade;
	}
	public void setFeerage(float feerage) {
		this.feerage = feerage;
	}
	@Override
	public String toString() {
		return "UGradeVO [ugrade=" + ugrade + ", feerage=" + feerage + "]";
	}
	
}
