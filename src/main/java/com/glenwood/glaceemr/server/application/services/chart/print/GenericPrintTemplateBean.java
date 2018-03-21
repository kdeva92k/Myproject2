package com.glenwood.glaceemr.server.application.services.chart.print;

import java.util.List;

import com.glenwood.glaceemr.server.application.models.LeafLibrary;

public class GenericPrintTemplateBean {

	List<LeafLibrary> templateList;
	
	List<LeafLibrary> styleTemplateList;
	
	public List<LeafLibrary> getTemplateList() {
		return templateList;
	}
	public void setTemplateList(List<LeafLibrary> templateList) {
		this.templateList = templateList;
	}
	public List<LeafLibrary> getStyleTemplateList() {
		return styleTemplateList;
	}
	public void setStyleTemplateList(List<LeafLibrary> styleTemplateList) {
		this.styleTemplateList = styleTemplateList;
	}	
	
}
