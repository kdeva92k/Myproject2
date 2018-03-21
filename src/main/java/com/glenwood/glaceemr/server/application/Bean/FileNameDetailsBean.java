package com.glenwood.glaceemr.server.application.Bean;

public class FileNameDetailsBean {
	public Integer filenameId;

	public Integer filenameScanid;

	public String filenameName;

	public Integer filedetailsId;

	public Integer filedetailsFlag;

	public String filedetailsDescription;

	public FileNameDetailsBean(Integer filenameId,Integer filenameScanid,String filenameName,
					Integer filedetailsId,Integer filedetailsFlag,String filedetailsDescription){

		super();
		if(filenameId!=null){
			this.filenameId=filenameId;
		}

		if(filenameScanid!=null){
			this.filenameScanid=filenameScanid;
		}
		if(filenameName!= null){
			this.filenameName=filenameName;
		}
		if(filedetailsId!= null){
			this.filedetailsId=filedetailsId;
		}
		if(filedetailsFlag!= null){
			this.filedetailsFlag=filedetailsFlag;
		}
		if(filedetailsDescription!= null){
			this.filedetailsDescription=filedetailsDescription;
		}
	}
	
	public Integer getFilenameId() {
		return filenameId;
	}

	public void setFilenameId(Integer filenameId) {
		this.filenameId = filenameId;
	}

	public Integer getFilenameScanid() {
		return filenameScanid;
	}

	public void setFilenameScanid(Integer filenameScanid) {
		this.filenameScanid = filenameScanid;
	}

	public String getFilenameName() {
		return filenameName;
	}

	public void setFilenameName(String filenameName) {
		this.filenameName = filenameName;
	}

	public Integer getFiledetailsId() {
		return filedetailsId;
	}

	public void setFiledetailsId(Integer filedetailsId) {
		this.filedetailsId = filedetailsId;
	}

	public Integer getFiledetailsFlag() {
		return filedetailsFlag;
	}

	public void setFiledetailsFlag(Integer filedetailsFlag) {
		this.filedetailsFlag = filedetailsFlag;
	}

	public String getFiledetailsDescription() {
		return filedetailsDescription;
	}

	public void setFiledetailsDescription(String filedetailsDescription) {
		this.filedetailsDescription = filedetailsDescription;
	}
	
	
}
