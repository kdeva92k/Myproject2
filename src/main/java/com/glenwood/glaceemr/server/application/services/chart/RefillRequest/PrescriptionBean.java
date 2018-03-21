package com.glenwood.glaceemr.server.application.services.chart.RefillRequest;

import java.sql.Timestamp;



public class PrescriptionBean {



	private Boolean ischronic;
	private Timestamp modifieddate;
	private String sch2="";
	private Timestamp expdate;
	private Boolean rxsubstitution;
	private Integer modifiedby;
	private String rxndc="";
	private String addi="";
	private java.sql.Date dateoforder;
	private Integer iseprescription;
	private Integer encounterid;
	private Timestamp orderdate;
	private String rxnotes="";
	private java.sql.Date startdate;
	private String duration="";
	private String rxintake="";
	private String units="";
	private java.sql.Date stopdate;
	private Integer orderby;
	private String lotno="";
	private String provider_name="";
	private Long patientid;
	private String rxassociateddx="";


	public String getRxstrength() {
		return rxstrength;
	}



	public String getRxform() {
		return rxform;
	}



	public void setRxform(String rxform) {
		this.rxform = rxform;
	}



	public String getRxroute() {
		return rxroute;
	}



	public void setRxroute(String rxroute) {
		this.rxroute = rxroute;
	}



	public String getRxfreq() {
		return rxfreq;
	}



	public void setRxfreq(String rxfreq) {
		this.rxfreq = rxfreq;
	}



	public String getNoofdays() {
		return noofdays;
	}



	public void setNoofdays(String noofdays) {
		this.noofdays = noofdays;
	}



	public String getRxquantity() {
		return rxquantity;
	}



	public void setRxquantity(String rxquantity) {
		this.rxquantity = rxquantity;
	}



	public String getRxquantityunits() {
		return rxquantityunits;
	}



	public void setRxquantityunits(String rxquantityunits) {
		this.rxquantityunits = rxquantityunits;
	}



	public String getNoofrefills() {
		return noofrefills;
	}



	public void setNoofrefills(String noofrefills) {
		this.noofrefills = noofrefills;
	}



	public PrescriptionBean(String rxname, String rxstrength, String rxform,
			String rxroute, String rxfreq, String noofdays, String rxquantity,
			String rxquantityunits, String noofrefills,Integer rxstatusid,
			Integer rootid, Integer providerid, Boolean toprint, Boolean ischronic, Timestamp modifieddate, String sch2, Timestamp expdate, Boolean rxsubstitution, Integer modifiedby, 
			String rxndc, String addi, java.sql.Date dateoforder, 
			Integer iseprescription, Integer encounterid, Timestamp orderdate, String rxnotes, 
			java.sql.Date startdate, String duration, String rxintake, String units,
			java.sql.Date stopdate, Integer orderby, String lotno, String provider_name, 
			Long patientid, String rxassociateddx) {
		this.rxname = rxname;
		this.rxstrength = rxstrength;
		this.rxform = rxform;
		this.rxroute = rxroute;
		this.rxfreq = rxfreq;
		this.noofdays = noofdays;
		this.rxquantity = rxquantity;
		this.rxquantityunits = rxquantityunits;
		this.noofrefills = noofrefills;
		this.rxstatusid=rxstatusid;
		this.rootid=rootid;
		this.providerid=providerid;
		this.toprint=toprint;
		this.ischronic=ischronic;
		this.modifieddate=modifieddate;
		this.sch2=sch2;
		this.expdate=expdate;
		this.rxsubstitution=rxsubstitution;
		this.modifiedby=modifiedby;
		this.rxndc=rxndc;
		this.addi=addi;
		this.dateoforder=dateoforder;
		this.iseprescription=iseprescription;
		this.encounterid=encounterid;
		this.orderdate=orderdate;
		this.rxnotes=rxnotes;
		this.startdate=startdate;
		this.duration=duration;
		this.rxintake=rxintake;
		this.units=units;
		this.stopdate=stopdate;
		this.orderby=orderby;
		this.lotno=lotno;
		this.provider_name=provider_name;
		this.patientid=patientid;
		this.rxassociateddx=rxassociateddx;
		
	}
/*
	public PrescriptionBean(Boolean ischronic, Timestamp modifieddate,
			String sch2, Timestamp expdate, Boolean rxsubstitution,
			Integer modifiedby, String rxndc, String addi, String rxname,
			String rxstrength, String rxform, String rxroute, String rxfreq,
			String noofdays, String rxquantity, String rxquantityunits,
			String noofrefills, Integer rxstatusid, Integer rootid,
			Integer providerid, Boolean toprint) {
		super();
		this.ischronic = ischronic;
		this.modifieddate = modifieddate;
		this.sch2 = sch2;
		this.expdate = expdate;
		this.rxsubstitution = rxsubstitution;
		this.modifiedby = modifiedby;
		this.rxndc = rxndc;
		this.addi = addi;
		this.rxname = rxname;
		this.rxstrength = rxstrength;
		this.rxform = rxform;
		this.rxroute = rxroute;
		this.rxfreq = rxfreq;
		this.noofdays = noofdays;
		this.rxquantity = rxquantity;
		this.rxquantityunits = rxquantityunits;
		this.noofrefills = noofrefills;
		this.rxstatusid = rxstatusid;
		this.rootid = rootid;
		this.providerid = providerid;
		this.toprint = toprint;
	}
*/


	public String getAddi() {
		return addi;
	}



	public void setAddi(String addi) {
		this.addi = addi;
	}



	public java.sql.Date getDateoforder() {
		return dateoforder;
	}



	public void setDateoforder(java.sql.Date dateoforder) {
		this.dateoforder = dateoforder;
	}



	public Integer getIseprescription() {
		return iseprescription;
	}



	public void setIseprescription(Integer iseprescription) {
		this.iseprescription = iseprescription;
	}



	public Integer getEncounterid() {
		return encounterid;
	}



	public void setEncounterid(Integer encounterid) {
		this.encounterid = encounterid;
	}



	public Timestamp getOrderdate() {
		return orderdate;
	}



	public void setOrderdate(Timestamp orderdate) {
		this.orderdate = orderdate;
	}



	public String getRxnotes() {
		return rxnotes;
	}



	public void setRxnotes(String rxnotes) {
		this.rxnotes = rxnotes;
	}



	public java.sql.Date getStartdate() {
		return startdate;
	}



	public void setStartdate(java.sql.Date startdate) {
		this.startdate = startdate;
	}



	public String getDuration() {
		return duration;
	}



	public void setDuration(String duration) {
		this.duration = duration;
	}



	public String getRxintake() {
		if(rxintake==null){
			return "";
		}
		else
			return rxintake;
	}



	public void setRxintake(String rxintake) {
		this.rxintake = rxintake;
	}



	public String getUnits() {
		return units;
	}



	public void setUnits(String units) {
		this.units = units;
	}



	public java.sql.Date getStopdate() {
		return stopdate;
	}



	public void setStopdate(java.sql.Date stopdate) {
		this.stopdate = stopdate;
	}



	public Integer getOrderby() {
		return orderby;
	}



	public void setOrderby(Integer orderby) {
		this.orderby = orderby;
	}



	public String getLotno() {
		return lotno;
	}



	public void setLotno(String lotno) {
		this.lotno = lotno;
	}



	public String getProvider_name() {
		return provider_name;
	}



	public void setProvider_name(String provider_name) {
		this.provider_name = provider_name;
	}



	public Long getPatientid() {
		return patientid;
	}



	public void setPatientid(Long patientid) {
		this.patientid = patientid;
	}



	public String getRxassociateddx() {
		return rxassociateddx;
	}



	public void setRxassociateddx(String rxassociateddx) {
		this.rxassociateddx = rxassociateddx;
	}



	public Boolean getIschronic() {
		return ischronic;
	}



	public void setIschronic(Boolean ischronic) {
		this.ischronic = ischronic;
	}



	public Timestamp getModifieddate() {
		return modifieddate;
	}



	public void setModifieddate(Timestamp modifieddate) {
		this.modifieddate = modifieddate;
	}



	public String getSch2() {
		return sch2;
	}



	public void setSch2(String sch2) {
		this.sch2 = sch2;
	}



	public Timestamp getExpdate() {
		return expdate;
	}



	public void setExpdate(Timestamp expdate) {
		this.expdate = expdate;
	}



	public Boolean getRxsubstitution() {
		return rxsubstitution;
	}



	public void setRxsubstitution(Boolean rxsubstitution) {
		this.rxsubstitution = rxsubstitution;
	}



	public Integer getModifiedby() {
		return modifiedby;
	}



	public void setModifiedby(Integer modifiedby) {
		this.modifiedby = modifiedby;
	}



	public String getRxndc() {
		return rxndc;
	}



	public void setRxndc(String rxndc) {
		this.rxndc = rxndc;
	}



	public Integer getProviderid() {
		return providerid;
	}



	public void setProviderid(Integer providerid) {
		this.providerid = providerid;
	}



	public Boolean getToprint() {
		return toprint;
	}



	public void setToprint(Boolean toprint) {
		this.toprint = toprint;
	}



	public Integer getRootid() {
		return rootid;
	}



	public void setRootid(Integer rootid) {
		this.rootid = rootid;
	}



	public Integer getRxstatusid() {
		return rxstatusid;
	}



	public void setRxstatusid(Integer rxstatusid) {
		this.rxstatusid = rxstatusid;
	}



	public void setRxstrength(String rxstrength) {
		this.rxstrength = rxstrength;
	}

	private String rxname="";
	private String rxstrength="";
	private String rxform="";
	private String rxroute="";
	private String rxfreq="";
	private String noofdays="";
	private String rxquantity="";
	private String rxquantityunits="";
	private String noofrefills="";
	private Integer rxstatusid;
	private Integer rootid;
	private Integer providerid;
	private Boolean toprint;
	

	public String getRxname() {
		return rxname;
	}

	public void setRxname(String rxname) {
		this.rxname = rxname;
	}
}


