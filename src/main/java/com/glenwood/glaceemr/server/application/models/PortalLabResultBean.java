package com.glenwood.glaceemr.server.application.models;

import java.util.List;

public class PortalLabResultBean {

	List<PortalLabParametersBean> labParametersList;
	
	FileDetails labAttachments;

	public List<PortalLabParametersBean> getLabParametersList() {
		return labParametersList;
	}

	public void setLabParametersList(List<PortalLabParametersBean> labParametersList) {
		this.labParametersList = labParametersList;
	}

	public FileDetails getLabAttachments() {
		return labAttachments;
	}

	public void setLabAttachments(FileDetails labAttachments) {
		this.labAttachments = labAttachments;
	}
	
}
