package com.glenwood.glaceemr.server.application.services.portal.portalForms;

import java.sql.ResultSet;
import java.util.Date;

import com.glenwood.glaceemr.server.application.models.LeafXmlVersion;
import com.glenwood.glaceemr.server.application.models.PatientRegistration;
import com.glenwood.glaceemr.server.application.models.TabLibrary;
import com.glenwood.glaceemr.server.utils.DateUtil;
import com.glenwood.glaceemr.server.utils.HUtil;

public class TabDetailBean{
	int tabId;
	String tabName;
	String description;
	String actionURL;
	String summaryURL;
	boolean isActive;
	String xmlURL;
	String xslURL;
	int isXML;
	int xmlVersion;
	int xmlGroup;
	String printingURL;
	String versionName;
	String condition;
	int tabType;
	int codifiedLeafType;

	private TabDetailBean(){
	}

	public static TabDetailBean getNewInstance(int tabId, CompletePatientDetailsBean patientDetials, TabLibrary tabLibrary, LeafXmlVersion leafXmlVersion)throws Exception{
		TabDetailBean tabBean = new TabDetailBean();
		String patientDOB=patientDetials.getPatientRegistrationDob();

		tabBean.setTabName(tabLibrary.getTabLibraryDisplayname());
		tabBean.setDescription(tabLibrary.getTabLibraryDescription());
		tabBean.setActionURL(tabLibrary.getTabLibraryActionurl());
		tabBean.setSummaryURL(tabLibrary.getTabLibrarySummaryurl());
		tabBean.setActive(tabLibrary.getTabLibraryIsactive());
		tabBean.setIsXML(tabLibrary.getTabLibraryIsxml());
		tabBean.setXmlGroup(tabLibrary.getTabLibraryXmlGroup());
		tabBean.setCodifiedLeafType(tabLibrary.getTabLibraryIscodified());

		if(tabBean.getIsXML()==0)
			return tabBean;
		
		tabBean.setXmlURL(leafXmlVersion.getLeafXmlVersionXmlurl());
		tabBean.setXslURL(leafXmlVersion.getLeafXmlVersionXslurl());
		tabBean.setPrintingURL(leafXmlVersion.getLeafXmlVersionCondition());
		tabBean.setVersionName(leafXmlVersion.getLeafXmlVersionVersion());
		tabBean.setCondition(leafXmlVersion.getLeafXmlVersionPrintxslurl());
		tabBean.setTabType(leafXmlVersion.getLeafXmlVersionTabType());

		return tabBean;
	}

	public String getAgeinYear(String dob){
		int ageinyear = 0,ageinmonth=0,ageinday=0;
		String age="";

		if(HUtil.Nz(dob,"").equals(""))return age;
		ageinyear = (int)(DateUtil.dateDiff( DateUtil.DATE , DateUtil.getDate(dob) , new java.util.Date() )/365);
		return ""+ ageinyear;
	}

	public String getAgeinMonth(String dob){
		int ageinyear = 0,ageinmonth=0,ageinday=0;
		String age="";
		if(HUtil.Nz(dob,"").equals(""))return age;
		ageinyear = (int)(DateUtil.dateDiff( DateUtil.DATE , DateUtil.getDate(dob) , new java.util.Date() )/365);
		ageinmonth = (int)((DateUtil.dateDiff( DateUtil.DATE , DateUtil.getDate(dob) , new java.util.Date() )%365)/30);
		ageinyear = 12 * ageinyear;
		ageinmonth = ageinyear + ageinmonth; 
		return ""+ ageinmonth;
	}


	public String getActionURL() {
		return actionURL;
	}

	public void setActionURL(String actionURL) {
		this.actionURL = actionURL;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public boolean isActive() {
		return isActive;
	}

	public void setActive(boolean isActive) {
		this.isActive = isActive;
	}

	public int getIsXML() {
		return isXML;
	}

	public void setIsXML(int isXML) {
		this.isXML = isXML;
	}

	public String getPrintingURL() {
		return printingURL;
	}

	public void setPrintingURL(String printingURL) {
		this.printingURL = printingURL;
	}

	public String getSummaryURL() {
		return summaryURL;
	}

	public void setSummaryURL(String summaryURL) {
		this.summaryURL = summaryURL;
	}

	public int getTabId() {
		return tabId;
	}

	public void setTabId(int tabId) {
		this.tabId = tabId;
	}

	public String getTabName() {
		return tabName;
	}

	public void setTabName(String tabName) {
		this.tabName = tabName;
	}

	public int getXmlGroup() {
		return xmlGroup;
	}

	public void setXmlGroup(int xmlGroup) {
		this.xmlGroup = xmlGroup;
	}

	public String getXmlURL() {
		return xmlURL;
	}

	public void setXmlURL(String xmlURL) {
		this.xmlURL = xmlURL;
	}

	public int getXmlVersion() {
		return xmlVersion;
	}

	public void setXmlVersion(int xmlVersion) {
		this.xmlVersion = xmlVersion;
	}

	public String getXslURL() {
		return xslURL;
	}

	public void setXslURL(String xslURL) {
		this.xslURL = xslURL;
	}

	public String getCondition() {
		return condition;
	}

	public void setCondition(String condition) {
		this.condition = condition;
	}

	public String getVersionName() {
		return versionName;
	}

	public void setVersionName(String versionName) {
		this.versionName = versionName;
	}

	public int getTabType() {
		return tabType;
	}

	public void setTabType(int tabType) {
		this.tabType = tabType;
	}
	public void setCodifiedLeafType(int codifiedType){
		codifiedLeafType=codifiedType;
	}
	public int getCodifiedLeafType(){
		return codifiedLeafType;
	}

}