package com.glenwood.glaceemr.server.application.Bean;

public class LabParametersBean {

	private Integer labParametersId;
	private String labParametersName;
	private String labParametersDisplayname;
	private String labParametersUnits;
	private String labParametersNormalrange;
	private String labParametersType;
	private Boolean labParametersIsactive;
	private Boolean labParametersIsflowsheetneeded;
	private String labParametersFlowsheeturl;
	
	public LabParametersBean(){
		
	}
	public LabParametersBean(Boolean labParametersIsflowsheetneeded,String labParametersFlowsheeturl){	
		this.labParametersIsflowsheetneeded=labParametersIsflowsheetneeded;
		this.labParametersFlowsheeturl=labParametersFlowsheeturl;	
	}
	
	public Integer getLabParametersId() {
		return labParametersId;
	}
	public void setLabParametersId(Integer labParametersId) {
		this.labParametersId = labParametersId;
	}
	public String getLabParametersName() {
		return labParametersName;
	}
	public void setLabParametersName(String labParametersName) {
		this.labParametersName = labParametersName;
	}
	public String getLabParametersDisplayname() {
		return labParametersDisplayname;
	}
	public void setLabParametersDisplayname(String labParametersDisplayname) {
		this.labParametersDisplayname = labParametersDisplayname;
	}
	public String getLabParametersUnits() {
		return labParametersUnits;
	}
	public void setLabParametersUnits(String labParametersUnits) {
		this.labParametersUnits = labParametersUnits;
	}
	public String getLabParametersNormalrange() {
		return labParametersNormalrange;
	}
	public void setLabParametersNormalrange(String labParametersNormalrange) {
		this.labParametersNormalrange = labParametersNormalrange;
	}
	public String getLabParametersType() {
		return labParametersType;
	}
	public void setLabParametersType(String labParametersType) {
		this.labParametersType = labParametersType;
	}
	public Boolean getLabParametersIsactive() {
		return labParametersIsactive;
	}
	public void setLabParametersIsactive(Boolean labParametersIsactive) {
		this.labParametersIsactive = labParametersIsactive;
	}
	public Boolean getLabParametersIsflowsheetneeded() {
		return labParametersIsflowsheetneeded;
	}
	public void setLabParametersIsflowsheetneeded(
			Boolean labParametersIsflowsheetneeded) {
		this.labParametersIsflowsheetneeded = labParametersIsflowsheetneeded;
	}
	public String getLabParametersFlowsheeturl() {
		return labParametersFlowsheeturl;
	}
	public void setLabParametersFlowsheeturl(String labParametersFlowsheeturl) {
		this.labParametersFlowsheeturl = labParametersFlowsheeturl;
	}

}
