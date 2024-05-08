package com.itwillbs.pay;

import java.sql.Timestamp;

public class PayInfoVO {
	private String PAY_ID;
	private String USER_ID;
	private String PAY_TYPE;
	private String PAY_STATE;
	private int PAY_AMOUNT;
	private String CHARGE_TYPE;
	private String PAY_CAUSE;
	private String BANK;
	private String ACCOUNT;
	private String ACCOUNT_HOLDER;
	private Timestamp REGDATE;
	private Timestamp COMPLIDATE;
	
	public String getPAY_ID() {
		return PAY_ID;
	}
	
	public String getUSER_ID() {
		return USER_ID;
	}
	
	public String getPAY_TYPE() {
		return PAY_TYPE;
	}
	
	public String getPAY_STATE() {
		return PAY_STATE;
	}
	
	public int getPAY_AMOUNT() {
		return PAY_AMOUNT;
	}
	
	public String getCHARGE_TYPE() {
		return CHARGE_TYPE;
	}
	
	public String getPAY_CAUSE() {
		return PAY_CAUSE;
	}
	
	public String getBANK() {
		return BANK;
	}
	
	public String getACCOUNT() {
		return ACCOUNT;
	}
	
	public String getACCOUNT_HOLDER() {
		return ACCOUNT_HOLDER;
	}
	
	public Timestamp getREGDATE() {
		return REGDATE;
	}
	
	public Timestamp getCOMPLIDATE() {
		return COMPLIDATE;
	}
	
	public void setPAY_ID(String pAY_ID) {
		PAY_ID = pAY_ID;
	}
	
	public void setUSER_ID(String uSER_ID) {
		USER_ID = uSER_ID;
	}
	
	public void setPAY_TYPE(String pAY_TYPE) {
		PAY_TYPE = pAY_TYPE;
	}
	
	public void setPAY_STATE(String pAY_STATE) {
		PAY_STATE = pAY_STATE;
	}
	
	public void setPAY_AMOUNT(int pAY_AMOUNT) {
		PAY_AMOUNT = pAY_AMOUNT;
	}
	
	public void setCHARGE_TYPE(String cHARGE_TYPE) {
		CHARGE_TYPE = cHARGE_TYPE;
	}
	
	public void setPAY_CAUSE(String pAY_CAUSE) {
		PAY_CAUSE = pAY_CAUSE;
	}
	
	public void setBANK(String bANK) {
		BANK = bANK;
	}
	
	public void setACCOUNT(String aCCOUNT) {
		ACCOUNT = aCCOUNT;
	}
	
	public void setACCOUNT_HOLDER(String aCCOUNT_HOLDER) {
		ACCOUNT_HOLDER = aCCOUNT_HOLDER;
	}
	
	public void setREGDATE(Timestamp rEGDATE) {
		REGDATE = rEGDATE;
	}
	
	public void setCOMPLIDATE(Timestamp cOMPLIDATE) {
		COMPLIDATE = cOMPLIDATE;
	}
	
	@Override
	public String toString() {
		return "PayInfoVO [PAY_ID=" + PAY_ID + ", USER_ID=" + USER_ID + ", PAY_TYPE=" + PAY_TYPE + ", PAY_STATE="
				+ PAY_STATE + ", PAY_AMOUNT=" + PAY_AMOUNT + ", CHARGE_TYPE=" + CHARGE_TYPE + ", PAY_CAUSE=" + PAY_CAUSE
				+ ", BANK=" + BANK + ", ACCOUNT=" + ACCOUNT + ", ACCOUNT_HOLDER=" + ACCOUNT_HOLDER + ", REGDATE="
				+ REGDATE + ", COMPLIDATE=" + COMPLIDATE + "]";
	}
	
}
