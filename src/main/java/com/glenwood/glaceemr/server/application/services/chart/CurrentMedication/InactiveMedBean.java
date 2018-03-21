package com.glenwood.glaceemr.server.application.services.chart.CurrentMedication;

import java.util.Date;

public class InactiveMedBean {

	 Integer prescid;
	 Integer Encounterid;
	 Integer statusid ;
	 Boolean ischronic; 
	 String orderBy ;
	 String orderdate;
	 String modifiedby;
	 String modifieddate;
	 String number ;
	 String qty ;
	 String drugname ; 
	 String dosagename; 
	 String unitname;
	 String units;
	 String refills ; 
	 String route ;
	 String duration ;
	 String lotno;
	 String expdate;
	 String days;
	 String sch1 ;
	 String sch2 ;
	 String form ;
	 String comments;
	 Integer rootid;
	 Boolean allowsubstitution ;
	 Date startdate; 
	 Date stopdate;
	 Boolean toprint;
	 Integer medfrom;
	 String provider_name;
	 String ndc_code;
	 String dxforrx;
	 String med_quit_reason;
	 String inactivated_by;
	 String inactivated_on;
	 String status;
	 String curroot;
	
	 public Integer getPrescid() {
		return prescid;
	}

	public void setPrescid(Integer prescid) {
		this.prescid = prescid;
	}

	public Integer getEncounterid() {
		return Encounterid;
	}

	public void setEncounterid(Integer encounterid) {
		Encounterid = encounterid;
	}

	public Integer getStatusid() {
		return statusid;
	}

	public void setStatusid(Integer statusid) {
		this.statusid = statusid;
	}

	public Boolean getIschronic() {
		return ischronic;
	}

	public void setIschronic(Boolean ischronic) {
		this.ischronic = ischronic;
	}

	public String getOrderBy() {
		return orderBy;
	}

	public void setOrderBy(String orderBy) {
		this.orderBy = orderBy;
	}

	public String getOrderdate() {
		return orderdate;
	}

	public void setOrderdate(String orderdate) {
		this.orderdate = orderdate;
	}

	public String getModifiedby() {
		return modifiedby;
	}

	public void setModifiedby(String modifiedby) {
		this.modifiedby = modifiedby;
	}

	public String getModifieddate() {
		return modifieddate;
	}

	public void setModifieddate(String modifieddate) {
		this.modifieddate = modifieddate;
	}

	public String getNumber() {
		return number;
	}

	public void setNumber(String number) {
		this.number = number;
	}

	public String getQty() {
		return qty;
	}

	public void setQty(String qty) {
		this.qty = qty;
	}

	public String getDrugname() {
		return drugname;
	}

	public void setDrugname(String drugname) {
		this.drugname = drugname;
	}

	public String getDosagename() {
		return dosagename;
	}

	public void setDosagename(String dosagename) {
		this.dosagename = dosagename;
	}

	public String getUnitname() {
		return unitname;
	}

	public void setUnitname(String unitname) {
		this.unitname = unitname;
	}

	public String getUnits() {
		return units;
	}

	public void setUnits(String units) {
		this.units = units;
	}

	public String getRefills() {
		return refills;
	}

	public void setRefills(String refills) {
		this.refills = refills;
	}

	public String getRoute() {
		return route;
	}

	public void setRoute(String route) {
		this.route = route;
	}

	public String getDuration() {
		return duration;
	}

	public void setDuration(String duration) {
		this.duration = duration;
	}

	public String getLotno() {
		return lotno;
	}

	public void setLotno(String lotno) {
		this.lotno = lotno;
	}

	public String getExpdate() {
		return expdate;
	}

	public void setExpdate(String expdate) {
		this.expdate = expdate;
	}

	public String getDays() {
		return days;
	}

	public void setDays(String days) {
		this.days = days;
	}

	public String getSch1() {
		return sch1;
	}

	public void setSch1(String sch1) {
		this.sch1 = sch1;
	}

	public String getSch2() {
		return sch2;
	}

	public void setSch2(String sch2) {
		this.sch2 = sch2;
	}

	public String getForm() {
		return form;
	}

	public void setForm(String form) {
		this.form = form;
	}

	public String getComments() {
		return comments;
	}

	public void setComments(String comments) {
		this.comments = comments;
	}

	public Integer getRootid() {
		return rootid;
	}

	public void setRootid(Integer rootid) {
		this.rootid = rootid;
	}

	public Boolean getAllowsubstitution() {
		return allowsubstitution;
	}

	public void setAllowsubstitution(Boolean allowsubstitution) {
		this.allowsubstitution = allowsubstitution;
	}

	public Date getStartdate() {
		return startdate;
	}

	public void setStartdate(Date startdate) {
		this.startdate = startdate;
	}

	public Date getStopdate() {
		return stopdate;
	}

	public void setStopdate(Date stopdate) {
		this.stopdate = stopdate;
	}

	public Boolean getToprint() {
		return toprint;
	}

	public void setToprint(Boolean toprint) {
		this.toprint = toprint;
	}

	public Integer getMedfrom() {
		return medfrom;
	}

	public void setMedfrom(Integer medfrom) {
		this.medfrom = medfrom;
	}

	public String getProvider_name() {
		return provider_name;
	}

	public void setProvider_name(String provider_name) {
		this.provider_name = provider_name;
	}

	public String getNdc_code() {
		return ndc_code;
	}

	public void setNdc_code(String ndc_code) {
		this.ndc_code = ndc_code;
	}

	public String getDxforrx() {
		return dxforrx;
	}

	public void setDxforrx(String dxforrx) {
		this.dxforrx = dxforrx;
	}

	public String getMed_quit_reason() {
		return med_quit_reason;
	}

	public void setMed_quit_reason(String med_quit_reason) {
		this.med_quit_reason = med_quit_reason;
	}

	public String getInactivated_by() {
		return inactivated_by;
	}

	public void setInactivated_by(String inactivated_by) {
		this.inactivated_by = inactivated_by;
	}

	public String getInactivated_on() {
		return inactivated_on;
	}

	public void setInactivated_on(String inactivated_on) {
		this.inactivated_on = inactivated_on;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getCurroot() {
		return curroot;
	}

	public void setCurroot(String curroot) {
		this.curroot = curroot;
	}

	public InactiveMedBean(Integer prescid,Integer Encounterid,Integer statusid ,Boolean ischronic,String orderBy ,String orderdate,String modifiedby ,String modifieddate ,String number ,String qty ,String drugname ,String dosagename,String unitname,String refills ,String route ,String duration ,String lotno,String expdate,String days,String sch1 ,String sch2 ,String form ,String comments,Integer rootid,Boolean allowsubstitution ,Date startdate,String ndc_code,String status){
		 
		 this.prescid=prescid;
		 this.Encounterid=Encounterid;
		 this.statusid =statusid;
		 this.ischronic=ischronic; 
		 this.orderBy =orderBy;
		 this.orderdate= orderdate;
		 this.modifiedby =modifiedby;
		 this.modifieddate = modifieddate;
		 this.number =number;
		 this.qty =qty;
		 this.drugname =drugname; 
		 this.dosagename=dosagename; 
		 this.unitname=unitname;
		 this.refills =refills; 
		 this.route =route;
		 this.duration =duration;
		 this.lotno=lotno;
		 this.expdate= expdate;
		 this.days=days;
		 this.sch1 =sch1;
		 this.sch2 =sch2;
		 this.form =form;
		 this.comments=comments;
		 this.rootid=rootid;
		 this.allowsubstitution =allowsubstitution;
		 this.startdate=startdate; 
		 this.ndc_code=ndc_code;
		 this.status=status;
		 
	 }

	 public InactiveMedBean(Integer prescid,Integer Encounterid,Integer statusid ,Boolean ischronic,String orderBy ,String orderdate,String modifiedby ,String modifieddate ,String number ,String qty ,String drugname ,String dosagename,String unitname,String units,String refills ,String route ,String duration ,String lotno,String expdate,String days,String sch1 ,String sch2 ,String form ,String comments,Integer rootid,Boolean allowsubstitution ,Date startdate,Date stopdate,Boolean toprint,String provider_name,String ndc_code,String dxforrx,String med_quit_reason,String inactivated_by,String inactivated_on,String status){
		 this.prescid=prescid;
		 this.Encounterid=Encounterid;
		 this.statusid =statusid;
		 this.ischronic=ischronic; 
		 this.orderBy =orderBy;
		 this.orderdate=orderdate;
		 this.modifiedby =modifiedby;
		 this.modifieddate =modifieddate;
		 this.number =number;
		 this.qty =qty;
		 this.drugname =drugname; 
		 this.dosagename=dosagename; 
		 this.unitname=unitname;
		 this.units=units;
		 this.refills =refills; 
		 this.route =route;
		 this.duration =duration;
		 this.lotno=lotno;
		 this.expdate= expdate;
		 this.days=days;
		 this.sch1 =sch1;
		 this.sch2 =sch2;
		 this.form =form;
		 this.comments=comments;
		 this.rootid=rootid;
		 this.allowsubstitution =allowsubstitution;
		 this.startdate=startdate; 
		 this.stopdate=stopdate;
		 this.toprint=toprint;
		 this.provider_name=provider_name;
		 this.ndc_code=ndc_code;
		 this.dxforrx=dxforrx;
		 this.med_quit_reason=med_quit_reason;
		 this.inactivated_by=inactivated_by;
		 this.inactivated_on=inactivated_on;
		 this.status=status;
	 }

}

