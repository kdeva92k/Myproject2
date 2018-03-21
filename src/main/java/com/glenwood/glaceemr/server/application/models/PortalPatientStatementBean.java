package com.glenwood.glaceemr.server.application.models;

public class PortalPatientStatementBean {

	int billId;
	
	int batchNo;
	
	String statementType;
	
	String statementPath;
	
	String htmStatementContent;

	public int getBillId() {
		return billId;
	}

	public void setBillId(int billId) {
		this.billId = billId;
	}

	public int getBatchNo() {
		return batchNo;
	}

	public void setBatchNo(int batchNo) {
		this.batchNo = batchNo;
	}

	public String getStatementType() {
		return statementType;
	}

	public void setStatementType(String statementType) {
		this.statementType = statementType;
	}

	public String getStatementPath() {
		return statementPath;
	}

	public void setStatementPath(String statementPath) {
		this.statementPath = statementPath;
	}

	public String getHtmStatementContent() {
		return htmStatementContent;
	}

	public void setHtmStatementContent(String htmStatementContent) {
		this.htmStatementContent = htmStatementContent;
	}
	
}
