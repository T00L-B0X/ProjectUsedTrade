package com.itwillbs.pay;

public class PayVO {
	private String USERID;
	private String PAYID;
	private int PAY_BALANCE;
	
	public String getUSERID() {
		return USERID;
	}
	public String getPAYID() {
		return PAYID;
	}
	public int getPAY_BALANCE() {
		return PAY_BALANCE;
	}
	public void setUSERID(String uSERID) {
		USERID = uSERID;
	}
	public void setPAYID(String pAYID) {
		PAYID = pAYID;
	}
	public void setPAY_BALANCE(int pAY_BALANCE) {
		PAY_BALANCE = pAY_BALANCE;
	}
	
	@Override
	public String toString() {
		return "PayVO [USERID=" + USERID + ", PAYID=" + PAYID + ", PAY_BALANCE=" + PAY_BALANCE + "]";
	}
	
}
