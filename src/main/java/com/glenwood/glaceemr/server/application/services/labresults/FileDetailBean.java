package com.glenwood.glaceemr.server.application.services.labresults;

import java.util.ArrayList;

@SuppressWarnings("rawtypes")
public class FileDetailBean {
		
	private int id;
	private int categoryId;
	private int flag;
	private String description;
	private String comments;
	private String createdOn;
	private int createdBy;
	private String modifiedOn;
	private int modifiedBy;
	private int encounterId;
	private int patientId;
	private int chartId;
	private ArrayList fileNames;
	private boolean active;
	private boolean sign;
	private long templateId;
	private String userdate;
	private int entityId;
    private int typeId;
    private boolean buffer3=false;
    private int faxreferenceid = -1;
    private int fileScanType;
		
    public int getFaxreferenceid() {
		return faxreferenceid;
	}

	public void setFaxreferenceid(int faxreferenceid) {
		this.faxreferenceid = faxreferenceid;
	}

	private FileDetailBean(){
    }
		
    public static FileDetailBean createInstance(int id,int categoryId,int flag,String description,String comments,String createdOn,int createdBy,String modifiedOn,int modifiedBy,int encounterId,int patientId,int chartId,ArrayList fileNames,boolean active,String userdate,int entityId,int typeId){
			
    	FileDetailBean detailBean = new FileDetailBean();
		detailBean.setActive(active);
		detailBean.setCategoryId(categoryId);
		detailBean.setChartId(chartId);
		detailBean.setComments(comments);
		detailBean.setCreatedBy(createdBy);
		detailBean.setCreatedOn(createdOn);
		detailBean.setDescription(description);
		detailBean.setEncounterId(encounterId);
		detailBean.setFileNames(fileNames);
		detailBean.setFlag(flag);
		detailBean.setId(id);
		detailBean.setModifiedBy(modifiedBy);
		detailBean.setModifiedOn(modifiedOn);
		detailBean.setPatientId(patientId);
		detailBean.setUserdate(userdate);
		detailBean.setEntityId(entityId);
		detailBean.setTypeId(typeId);
		return detailBean;		
    }
    public static FileDetailBean createInstanceForScan(int id,int categoryId,int flag,String description,String comments,String createdOn,int createdBy,String modifiedOn,int modifiedBy,int encounterId,int patientId,int chartId,ArrayList fileNames,boolean active,String userdate,int entityId,int typeId,int fileScanType){
		
    	FileDetailBean detailBean = new FileDetailBean();
		detailBean.setActive(active);
		detailBean.setCategoryId(categoryId);
		detailBean.setChartId(chartId);
		detailBean.setComments(comments);
		detailBean.setCreatedBy(createdBy);
		detailBean.setCreatedOn(createdOn);
		detailBean.setDescription(description);
		detailBean.setEncounterId(encounterId);
		detailBean.setFileNames(fileNames);
		detailBean.setFlag(flag);
		detailBean.setId(id);
		detailBean.setModifiedBy(modifiedBy);
		detailBean.setModifiedOn(modifiedOn);
		detailBean.setPatientId(patientId);
		detailBean.setUserdate(userdate);
		detailBean.setEntityId(entityId);
		detailBean.setTypeId(typeId);
		detailBean.setFileScantype(fileScanType);
		return detailBean;		
    }
    
 public static FileDetailBean createInstanceForConsent(int id,int categoryId,int flag,String description,String comments,String createdOn,int createdBy,String modifiedOn,int modifiedBy,int encounterId,int patientId,int chartId,ArrayList fileNames,boolean active,String userdate,int entityId,int typeId,boolean isSigned,long templateId ){
		
	 FileDetailBean detailBean = new FileDetailBean();
		detailBean.setActive(active);
		detailBean.setCategoryId(categoryId);
		detailBean.setChartId(chartId);
		detailBean.setComments(comments);
		detailBean.setCreatedBy(createdBy);
		detailBean.setCreatedOn(createdOn);
		detailBean.setDescription(description);
		detailBean.setEncounterId(encounterId);
		detailBean.setFileNames(fileNames);
		detailBean.setFlag(flag);
		detailBean.setId(id);
		detailBean.setModifiedBy(modifiedBy);
		detailBean.setModifiedOn(modifiedOn);
		detailBean.setPatientId(patientId);
		detailBean.setUserdate(userdate);
		detailBean.setEntityId(entityId);
		detailBean.setTypeId(typeId);
		detailBean.setSigned(isSigned);
		detailBean.setTemplateId(templateId);
		return detailBean;		
    }
    
    public boolean isActive() {
		return active;
	}
	
	private void setActive(boolean active) {
		this.active = active;
	}
	
	public boolean isSigned() {
		return sign;
	}
	
	private void setSigned(boolean sign) {
		this.sign = sign;
	}
	
	public long getTemplateId() {
		return templateId;
	}
	
	private void setTemplateId(long templateId) {
		this.templateId = templateId;
	}
	
	public int getCategoryId() {
		return categoryId;
	}
	
	private void setCategoryId(int categoryId) {
		this.categoryId = categoryId;
	}
	
	public int getChartId() {
		return chartId;
	}
	
	private void setChartId(int chartId) {
		this.chartId = chartId;
	}
	
	public String getComments() {
		return comments;
	}
	
	private void setComments(String comments) {
		if(comments.equals("-1"))
			this.comments = "--";
		else	
		    this.comments = comments;
	}
	
	public int getCreatedBy() {
		return createdBy;
	}
	
	private void setCreatedBy(int createdBy) {
		this.createdBy = createdBy;
	}
	
	public String getCreatedOn() {
		return createdOn;
	}
	
	private void setCreatedOn(String createdOn) {
		this.createdOn = createdOn;
	}
	
	public String getDescription() {
		return description;
	}
	
	private void setDescription(String description) {
		if(description.equals("-1"))
			this.description = "--";
		else
		    this.description = description;
	}
	
	public int getEncounterId() {
		return encounterId;
	}
	
	private void setEncounterId(int encounterId) {
		this.encounterId = encounterId;
	}
	
	public ArrayList getFileNames() {
		return fileNames;
	}
	
	private void setFileNames(ArrayList fileNames) {
		this.fileNames = fileNames;
	}
	
	public int getFlag() {
		return flag;
	}
	
	private void setFlag(int flag) {
		this.flag = flag;
	}
	
	public int getId() {
		return id;
	}
	
	private void setId(int id) {
		this.id = id;
	}
	
	public int getModifiedBy() {
		return modifiedBy;
	}
	
	private void setModifiedBy(int modifiedBy) {
		this.modifiedBy = modifiedBy;
	}
	
	public String getModifiedOn() {
		return modifiedOn;
	}
	
	private void setModifiedOn(String modifiedOn) {
		this.modifiedOn = modifiedOn;
	}
	
	public int getPatientId() {
		return patientId;
	}
	
	private void setPatientId(int patientId) {
		this.patientId = patientId;
	}
	
	private void setUserdate(String userdate) {
		this.userdate = userdate;
	}
	
	public String getUserdate() {
		return userdate;
	}
	
	private void setEntityId(int entityId) {
		this.entityId = entityId;
	}

	public int getEntityId() {
		return entityId;
	}

	public int getTypeId() {
		return typeId;
	}

	private void setTypeId(int typeId) {
		this.typeId = typeId;
	}

	public boolean isBuffer3() {
		return buffer3;
	}

	public void setBuffer3(boolean buffer3) {
		this.buffer3 = buffer3;
	}	
	private void setFileScantype(int fileScanType)
	{
		this.fileScanType = fileScanType;
	}
	public int getFileScantype() {
		return fileScanType;
	}
}