package com.glenwood.glaceemr.server.application.services.chart.ErxSummary;

public class ErxPatientDataBean {

	 Integer patientid;
	 String patid;  
	 String socsecno;
	 String patlastname; 
	 String patmidinitial;
	 String patfirstname;
	 String DOB; 
	 Integer sex; 
	 String pataddress; 
	 String pataddress2;
	 String patplacelocationqualifier;  
	 String patcity;
	 String patstate;
	 String patzip;
	 String patmailid;
	 String patphonenumber;
	 String patcellno;
	 String patfaxno;
	 String patworkno; 
	 String phonequalifier;
	 String cellqualifier;
	 String faxqualifier;
	 String workqualifier;
	 String patprefix;
	 String patsuffix;
	 String customfields; 
	 String DOB_mmddyyyy;
	 Integer patrelation;
	
	public ErxPatientDataBean( Integer patientid,String patid,String socsecno,String patlastname,String patmidinitial,String patfirstname,String DOB,Integer sex,String pataddress,String pataddress2,String patplacelocationqualifier,String patcity,String patstate,String patzip,String patmailid,String patphonenumber,String patcellno,String patfaxno,String patworkno,String phonequalifier,String cellqualifier,String faxqualifier,String workqualifier,String patprefix,String patsuffix,String customfields,String DOB_mmddyyyy,Integer patrelation){
		
		this.patientid=patientid;
		this.patid=patid;
		this.socsecno=socsecno;
		this.patlastname=patlastname;
		this.patmidinitial=patmidinitial;
		this.patfirstname=patfirstname;
		this.DOB=DOB;
		this.sex=sex;
		this.pataddress=pataddress;
		this.pataddress2=pataddress2;
		this.patplacelocationqualifier=patplacelocationqualifier;
		this.patcity=patcity;
		this.patstate=patstate;
		this.patzip=patzip;
		this.patmailid=patmailid;
		this.patphonenumber=patphonenumber;
		this.patcellno=patcellno;
		this.patfaxno=patfaxno;
		this.patworkno=patworkno;
		this.phonequalifier=phonequalifier;
		this.cellqualifier=cellqualifier;
		this.faxqualifier=faxqualifier;
		this.workqualifier=workqualifier;
		this.patprefix=patprefix;
		this.patsuffix=patsuffix;
		this.customfields=customfields;
		this.DOB_mmddyyyy=DOB_mmddyyyy;
		this.patrelation=patrelation;
		
	}

	public Integer getPatientid() {
		return patientid;
	}

	public void setPatientid(Integer patientid) {
		this.patientid = patientid;
	}

	public String getPatid() {
		return patid;
	}

	public void setPatid(String patid) {
		this.patid = patid;
	}

	public String getSocsecno() {
		return socsecno;
	}

	public void setSocsecno(String socsecno) {
		this.socsecno = socsecno;
	}

	public String getPatlastname() {
		return patlastname;
	}

	public void setPatlastname(String patlastname) {
		this.patlastname = patlastname;
	}

	public String getPatmidinitial() {
		return patmidinitial;
	}

	public void setPatmidinitial(String patmidinitial) {
		this.patmidinitial = patmidinitial;
	}

	public String getPatfirstname() {
		return patfirstname;
	}

	public void setPatfirstname(String patfirstname) {
		this.patfirstname = patfirstname;
	}

	public String getDOB() {
		return DOB;
	}

	public void setDOB(String dOB) {
		DOB = dOB;
	}

	public Integer getSex() {
		return sex;
	}

	public void setSex(Integer sex) {
		this.sex = sex;
	}

	public String getPataddress() {
		return pataddress;
	}

	public void setPataddress(String pataddress) {
		this.pataddress = pataddress;
	}

	public String getPataddress2() {
		return pataddress2;
	}

	public void setPataddress2(String pataddress2) {
		this.pataddress2 = pataddress2;
	}

	public String getPatplacelocationqualifier() {
		return patplacelocationqualifier;
	}

	public void setPatplacelocationqualifier(String patplacelocationqualifier) {
		this.patplacelocationqualifier = patplacelocationqualifier;
	}

	public String getPatcity() {
		return patcity;
	}

	public void setPatcity(String patcity) {
		this.patcity = patcity;
	}

	public String getPatstate() {
		return patstate;
	}

	public void setPatstate(String patstate) {
		this.patstate = patstate;
	}

	public String getPatzip() {
		return patzip;
	}

	public void setPatzip(String patzip) {
		this.patzip = patzip;
	}

	public String getPatmailid() {
		return patmailid;
	}

	public void setPatmailid(String patmailid) {
		this.patmailid = patmailid;
	}

	public String getPatphonenumber() {
		return patphonenumber;
	}

	public void setPatphonenumber(String patphonenumber) {
		this.patphonenumber = patphonenumber;
	}

	public String getPatcellno() {
		return patcellno;
	}

	public void setPatcellno(String patcellno) {
		this.patcellno = patcellno;
	}

	public String getPatfaxno() {
		return patfaxno;
	}

	public void setPatfaxno(String patfaxno) {
		this.patfaxno = patfaxno;
	}

	public String getPatworkno() {
		return patworkno;
	}

	public void setPatworkno(String patworkno) {
		this.patworkno = patworkno;
	}

	public String getPhonequalifier() {
		return phonequalifier;
	}

	public void setPhonequalifier(String phonequalifier) {
		this.phonequalifier = phonequalifier;
	}

	public String getCellqualifier() {
		return cellqualifier;
	}

	public void setCellqualifier(String cellqualifier) {
		this.cellqualifier = cellqualifier;
	}

	public String getFaxqualifier() {
		return faxqualifier;
	}

	public void setFaxqualifier(String faxqualifier) {
		this.faxqualifier = faxqualifier;
	}

	public String getWorkqualifier() {
		return workqualifier;
	}

	public void setWorkqualifier(String workqualifier) {
		this.workqualifier = workqualifier;
	}

	public String getPatprefix() {
		return patprefix;
	}

	public void setPatprefix(String patprefix) {
		this.patprefix = patprefix;
	}

	public String getPatsuffix() {
		return patsuffix;
	}

	public void setPatsuffix(String patsuffix) {
		this.patsuffix = patsuffix;
	}

	public String getCustomfields() {
		return customfields;
	}

	public void setCustomfields(String customfields) {
		this.customfields = customfields;
	}

	public String getDOB_mmddyyyy() {
		return DOB_mmddyyyy;
	}

	public void setDOB_mmddyyyy(String dOB_mmddyyyy) {
		DOB_mmddyyyy = dOB_mmddyyyy;
	}

	public Integer getPatrelation() {
		return patrelation;
	}

	public void setPatrelation(Integer patrelation) {
		this.patrelation = patrelation;
	}
}
