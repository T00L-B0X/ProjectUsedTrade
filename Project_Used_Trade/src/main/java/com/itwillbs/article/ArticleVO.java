package com.itwillbs.article;

import java.sql.Timestamp;

import org.apache.ibatis.type.Alias;

@Alias("ArticleVO")
public class ArticleVO {

	private int ANUMBER;
	private String CATEGRY;
	private String LOCATNS;
	private String ARTITLE;
	private String USERID;
	private String CONTENT;
	private Timestamp REGDATE;
	private String EWRITER;
	private Timestamp EDTDATE;
	private int VIEWCNT;
	private int LIKECNT;
	private String DELETED;

	public int getANUMBER() {
		return ANUMBER;
	}

	public void setANUMBER(int aNUMBER) {
		ANUMBER = aNUMBER;
	}

	public String getCATEGRY() {
		return CATEGRY;
	}

	public void setCATEGRY(String cATEGRY) {
		CATEGRY = cATEGRY;
	}

	public String getLOCATNS() {
		return LOCATNS;
	}

	public void setLOCATNS(String lOCATNS) {
		LOCATNS = lOCATNS;
	}

	public String getARTITLE() {
		return ARTITLE;
	}

	public void setARTITLE(String aRTITLE) {
		ARTITLE = aRTITLE;
	}

	public String getUSERID() {
		return USERID;
	}

	public void setUSERID(String uSERID) {
		USERID = uSERID;
	}

	public String getCONTENT() {
		return CONTENT;
	}

	public void setCONTENT(String cONTENT) {
		CONTENT = cONTENT;
	}

	public Timestamp getREGDATE() {
		return REGDATE;
	}

	public void setREGDATE(Timestamp rEGDATE) {
		REGDATE = rEGDATE;
	}

	public String getEWRITER() {
		return EWRITER;
	}

	public void setEWRITER(String eWRITER) {
		EWRITER = eWRITER;
	}

	public Timestamp getEDTDATE() {
		return EDTDATE;
	}

	public void setEDTDATE(Timestamp eDTDATE) {
		EDTDATE = eDTDATE;
	}

	public int getVIEWCNT() {
		return VIEWCNT;
	}

	public void setVIEWCNT(int vIEWCNT) {
		VIEWCNT = vIEWCNT;
	}

	public int getLIKECNT() {
		return LIKECNT;
	}

	public void setLIKECNT(int lIKECNT) {
		LIKECNT = lIKECNT;
	}

	public String getDELETED() {
		return DELETED;
	}

	public void setDELETED(String dELETED) {
		DELETED = dELETED;
	}

	@Override
	public String toString() {
		return "ArticleVO [ANUMBER=" + ANUMBER + ", CATEGRY=" + CATEGRY + ", LOCATNS=" + LOCATNS + ", ARTITLE="
				+ ARTITLE + ", USERID=" + USERID + ", CONTENT=" + CONTENT + ", REGDATE=" + REGDATE + ", EWRITER="
				+ EWRITER + ", EDTDATE=" + EDTDATE + ", VIEWCNT=" + VIEWCNT + ", LIKECNT=" + LIKECNT + ", DELETED="
				+ DELETED + "]";
	}

}
