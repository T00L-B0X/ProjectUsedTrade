package com.itwillbs.payment;

import java.sql.Timestamp;

import org.apache.ibatis.type.Alias;

@Alias("PaymentVO")
public class PaymentVO {
	private int PAYMENT_ID;
	private Timestamp PAYMENT_DATE;
	private int PAYMENT_AMOUNT;
	private String PAYMENT_STATE;
	private String DELIVERY_TYPE;
	private String USERID;
	private int GOODS_ID;
	private Timestamp REGDATE;
	private Timestamp UPDATEDATE;
	
	public int getPAYMENT_ID() {
		return PAYMENT_ID;
	}
	public Timestamp getPAYMENT_DATE() {
		return PAYMENT_DATE;
	}
	public int getPAYMENT_AMOUNT() {
		return PAYMENT_AMOUNT;
	}
	public String getPAYMENT_STATE() {
		return PAYMENT_STATE;
	}
	public String getDELIVERY_TYPE() {
		return DELIVERY_TYPE;
	}
	public String getUSERID() {
		return USERID;
	}
	public int getGOODS_ID() {
		return GOODS_ID;
	}
	public Timestamp getREGDATE() {
		return REGDATE;
	}
	public Timestamp getUPDATEDATE() {
		return UPDATEDATE;
	}
	
	public void setPAYMENT_ID(int pAYMENT_ID) {
		PAYMENT_ID = pAYMENT_ID;
	}
	public void setPAYMENT_DATE(Timestamp pAYMENT_DATE) {
		PAYMENT_DATE = pAYMENT_DATE;
	}
	public void setPAYMENT_AMOUNT(int pAYMENT_AMOUNT) {
		PAYMENT_AMOUNT = pAYMENT_AMOUNT;
	}
	public void setPAYMENT_STATE(String pAYMENT_STATE) {
		PAYMENT_STATE = pAYMENT_STATE;
	}
	public void setDELIVERY_TYPE(String dELIVERY_TYPE) {
		DELIVERY_TYPE = dELIVERY_TYPE;
	}
	public void setUSERID(String uSERID) {
		USERID = uSERID;
	}
	public void setGOODS_ID(int gOODS_ID) {
		GOODS_ID = gOODS_ID;
	}
	public void setREGDATE(Timestamp rEGDATE) {
		REGDATE = rEGDATE;
	}
	public void setUPDATEDATE(Timestamp uPDATEDATE) {
		UPDATEDATE = uPDATEDATE;
	}
	
	@Override
	public String toString() {
		return "PaymentVO [PAYMENT_ID=" + PAYMENT_ID + ", PAYMENT_DATE=" + PAYMENT_DATE + ", PAYMENT_AMOUNT="
				+ PAYMENT_AMOUNT + ", PAYMENT_STATE=" + PAYMENT_STATE + ", DELIVERY_TYPE=" + DELIVERY_TYPE + ", USERID="
				+ USERID + ", GOODS_ID=" + GOODS_ID + ", REGDATE=" + REGDATE + ", UPDATEDATE=" + UPDATEDATE + "]";
	}
	
}
