package com.itwillbs.pay;

public class PayVO {
	private String USERID;
	private int PAY_ID;
	private int PAY_BALANCE;
	
	public String getUSERID() {
		return USERID;
	}
	public int getPAY_ID() {
		return PAY_ID;
	}
	public int getPAY_BALANCE() {
		return PAY_BALANCE;
	}
	public void setUSERID(String uSERID) {
		USERID = uSERID;
	}
	public void setPAY_ID(int pAY_ID) {
		PAY_ID = pAY_ID;
	}
	public void setPAY_BALANCE(int pAY_BALANCE) {
		PAY_BALANCE = pAY_BALANCE;
	}
	
	@Override
	public String toString() {
		return "PayVO [USERID=" + USERID + ", PAY_ID=" + PAY_ID + ", PAY_BALANCE=" + PAY_BALANCE + "]";
	}
	
}
