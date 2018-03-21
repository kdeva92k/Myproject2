package com.glenwood.glaceemr.server.application.services.labresults;

public class FileBean {
	
	private int fileId;
	private int fileDetailId;
	private String fileName;
	private String fileExtension;
	private long fileSize;
	private boolean active;
	private String createdOn;
	private int createdBy;
	private String modifiedOn;
	private int modifiedBy;
	
	private FileBean(){
		
	}
	
	public static FileBean createInstance(int fileId,int fileDetailId,String fileName,String fileExtension,long fileSize,boolean active,String createdOn,int createdBy,String modifiedOn,int modifiedBy){
	
		FileBean bean = new FileBean();
		bean.setActive(active);
		bean.setFileDetailId(fileDetailId);
		bean.setFileExtension(fileExtension);
		bean.setFileId(fileId);
		bean.setFileName(fileName);
		bean.setFileSize(fileSize);
		bean.setCreatedBy(createdBy);
		bean.setCreatedOn(createdOn);
		bean.setModifiedBy(modifiedBy);
		bean.setModifiedOn(modifiedOn);
		return bean;		
	}
	
	
	public boolean isActive() {
		return active;
	}
	
	private void setActive(boolean active) {
		this.active = active;
	}
	
	public int getFileDetailId() {
		return fileDetailId;
	}
	
	public void setFileDetailId(int fileDetailId) {
		this.fileDetailId = fileDetailId;
	}
	
	public String getFileExtension() {
		return fileExtension;
	}
	
	private void setFileExtension(String fileExtension) {
		this.fileExtension = fileExtension;
	}
	
	public int getFileId() {
		return fileId;
	}
	
	private void setFileId(int fileId) {
		this.fileId = fileId;
	}
	
	public String getFileName() {
		return fileName;
	}
	
	private void setFileName(String fileName) {
		this.fileName = fileName;
	}
	
	public long getFileSize() {
		return fileSize;
	}
	
	private void setFileSize(long fileSize) {
		this.fileSize = fileSize;
	}
	
	public int getCreatedBy() {
		return createdBy;
	}
	
	public String getCreatedOn() {
		return createdOn;
	}
	
	public int getModifiedBy() {
		return modifiedBy;
	}
	
	public String getModifiedOn() {
		return modifiedOn;
	}
	
	private void setCreatedBy(int createdBy) {
		this.createdBy = createdBy;
	}
	
	private void setCreatedOn(String createdOn) {
		this.createdOn = createdOn;
	}
	
	private void setModifiedBy(int modifiedBy) {
		this.modifiedBy = modifiedBy;
	}
	
	private void setModifiedOn(String modifiedOn) {
		this.modifiedOn = modifiedOn;
	}	
}