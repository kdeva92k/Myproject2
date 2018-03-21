package com.glenwood.glaceemr.server.application.services.chart.plan;

import java.util.List;

public class InstructionDetailsData {
	
	Integer elementinstructionid;
	String elementname;
	List<InstructionElementDetailsData> elementdetail;
	List<InstructionAssociateDetailsData> associate;
	
	public Integer getElementinstructionid() {
		return elementinstructionid;
	}
	public void setElementinstructionid(Integer elementinstructionid) {
		this.elementinstructionid = elementinstructionid;
	}
	public String getElementname() {
		return elementname;
	}
	public void setElementname(String elementname) {
		this.elementname = elementname;
	}
	public List<InstructionElementDetailsData> getElementdetail() {
		return elementdetail;
	}
	public void setElementdetail(List<InstructionElementDetailsData> elementdetail) {
		this.elementdetail = elementdetail;
	}
	public List<InstructionAssociateDetailsData> getAssociate() {
		return associate;
	}
	public void setAssociate(List<InstructionAssociateDetailsData> associate) {
		this.associate = associate;
	}

}