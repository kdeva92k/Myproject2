package com.glenwood.glaceemr.server.application.services.Documents;


public class ConsentFormsBean {
	private String patientDocCategoryName;
	private Integer filedetailsId;
	private String filedetailsDescription;
	private String filedetailsComments;
	private String filedetailsCreationdate;
	private Boolean filenameIsreviewed;
	private long count;
	private String filenameName;
	private String empProfileFullname;

	
	public ConsentFormsBean(String patientDocCategoryName,Integer filedetailsId,String filedetailsDescription,String filedetailsComments,String filedetailsCreationdate,Boolean filenameIsreviewed,long count,String filenameName,String empProfileFullname){
	
		this.patientDocCategoryName=patientDocCategoryName;
		this.filedetailsId=filedetailsId;
		this.filedetailsDescription=filedetailsDescription;
		this.filedetailsComments=filedetailsComments;
		this.filedetailsCreationdate=filedetailsCreationdate;
		this.filenameIsreviewed=filenameIsreviewed;
		this.count=count;
		this.filenameName=filenameName;
		this.empProfileFullname=empProfileFullname;
	}

	public String getPatientDocCategoryName() {
		return patientDocCategoryName;
	}

	public void setPatientDocCategoryName(String patientDocCategoryName) {
		this.patientDocCategoryName = patientDocCategoryName;
	}

	public Integer getFiledetailsId() {
		return filedetailsId;
	}

	public void setFiledetailsId(Integer filedetailsId) {
		this.filedetailsId = filedetailsId;
	}

	public String getFiledetailsDescription() {
		return filedetailsDescription;
	}

	public void setFiledetailsDescription(String filedetailsDescription) {
		this.filedetailsDescription = filedetailsDescription;
	}

	public String getFiledetailsComments() {
		return filedetailsComments;
	}

	public void setFiledetailsComments(String filedetailsComments) {
		this.filedetailsComments = filedetailsComments;
	}

	public String getFiledetailsCreationdate() {
		return filedetailsCreationdate;
	}

	public void setFiledetailsCreationdate(String filedetailsCreationdate) {
		this.filedetailsCreationdate = filedetailsCreationdate;
	}

	public Boolean getFilenameIsreviewed() {
		return filenameIsreviewed;
	}

	public void setFilenameIsreviewed(Boolean filenameIsreviewed) {
		this.filenameIsreviewed = filenameIsreviewed;
	}

	public long getCount() {
		return count;
	}

	public void setCount(long count) {
		this.count = count;
	}

	public String getEmpProfileFullname() {
		return empProfileFullname;
	}

	public void setEmpProfileFullname(String empProfileFullname) {
		this.empProfileFullname = empProfileFullname;
	}

	public String getFilenameName() {
		return filenameName;
	}

	public void setFilenameName(String filenameName) {
		this.filenameName = filenameName;
	}


}
