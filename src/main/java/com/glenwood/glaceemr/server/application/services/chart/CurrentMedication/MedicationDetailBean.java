package com.glenwood.glaceemr.server.application.services.chart.CurrentMedication;

import java.util.Date;

public class MedicationDetailBean {

	 Integer prescid;
	 Integer EncounterId;
	 Integer statusid ;
	 Boolean  ischronic  ;
	 Integer orderBy ;
	 String  OrderDate;
	 Integer   ModifiedBy ;
	 String   modifieddate ;
	 String  Number ;
	 String  qty ;
	 String  drugname ;
	 String  dosagename;
	 String  unitname;
	 String refills ;
	 String  route ;
	 String duration ;
	 String lotno;
	 String expdate;
	 String days;
	 String Sch1 ;
	 String Sch2 ;
	 String form ;
	 String  comments;
	 Integer  rootid;
	 Boolean   allowsubstitution;
	 Date  startdate;
	 String   external_source_info;
	 Boolean overriddenflag ;
	 Boolean uncodedmed;
	 String  interal_source;
	 String qtyunits   ;
	 Boolean presctype;
	 String   rxnormcode ;
	 Boolean   leaf_patient_iscomplete;
	 Date leaf_patient_completed_on;
	
	public MedicationDetailBean(Integer prescid,Integer EncounterId,Integer statusid,Boolean ischronic,Integer orderBy,String OrderDate,Integer ModifiedBy,String modifieddate,String Number,String qty,String drugname,String dosagename,String unitname,String refills,String route,String duration,String lotno,String expdate,String days,String Sch1,String Sch2,String form,String comments,Integer rootid,Boolean allowsubstitution,Date startdate,String external_source_info,Boolean overriddenflag,Boolean uncodedmed,String interal_source,String qtyunits,Boolean presctype,String rxnormcode,Boolean leaf_patient_iscomplete,Date leaf_patient_completed_on){
		super();
		this.prescid=prescid;
		this.EncounterId=EncounterId;
		this.statusid=statusid;
		this.ischronic=ischronic;
		this.orderBy=orderBy;
		this.OrderDate=OrderDate;
		this.ModifiedBy=ModifiedBy;
		this.modifieddate=modifieddate;
		this.Number=Number;
		this.qty=qty;
		this.drugname=drugname;
		this.dosagename=dosagename;
		this.unitname=unitname;
		this.refills=refills;
		this.route=route;
		this.duration=duration;
		this.lotno=lotno;
		this.expdate=expdate;
		this.days=days;
		this.Sch1=Sch1;
		this.Sch2=Sch2;
		this.form=form;
		this.comments=comments;
		this.rootid=rootid;
		this.allowsubstitution=allowsubstitution;
		this.startdate=startdate;
		this.external_source_info=external_source_info;
		this.overriddenflag=overriddenflag;
		this.uncodedmed=uncodedmed;
		this.interal_source=interal_source;
		this.qtyunits=qtyunits;
		this.presctype=presctype;
		this.rxnormcode=rxnormcode;
		this.leaf_patient_iscomplete=leaf_patient_iscomplete;
		this.leaf_patient_completed_on=leaf_patient_completed_on;
	}
	
	public Integer getPrescId() {
		return prescid;
	}
	public void setPrescId(Integer prescid) {
		this.prescid = prescid;
	}
	public Integer getEncounterId() {
		return EncounterId;
	}
	public void setEncounterId(Integer encounterId) {
		EncounterId = encounterId;
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
	public Integer getOrderBy() {
		return orderBy;
	}
	public void setOrderBy(Integer orderBy) {
		this.orderBy = orderBy;
	}
	public String getOrderDate() {
		return OrderDate;
	}
	public void setOrderDate(String orderDate) {
		OrderDate = orderDate;
	}
	public Integer getModifiedBy() {
		return ModifiedBy;
	}
	public void setModifiedBy(Integer modifiedBy) {
		ModifiedBy = modifiedBy;
	}
	public String getModifiedDate() {
		return modifieddate;
	}
	public void setModifiedDate(String modifiedDate) {
		modifieddate = modifiedDate;
	}
	public String getNumber() {
		return Number;
	}
	public void setNumber(String number) {
		Number = number;
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
		return Sch1;
	}
	public void setSch1(String sch1) {
		Sch1 = sch1;
	}
	public String getSch2() {
		return Sch2;
	}
	public void setSch2(String sch2) {
		Sch2 = sch2;
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
	public String getExternal_source_info() {
		return external_source_info;
	}
	public void setExternal_source_info(String external_source_info) {
		this.external_source_info = external_source_info;
	}
	public Boolean getOverriddenflag() {
		return overriddenflag;
	}
	public void setOverriddenflag(Boolean overriddenflag) {
		this.overriddenflag = overriddenflag;
	}
	public Boolean getUncodedmed() {
		return uncodedmed;
	}
	public void setUncodedmed(Boolean uncodedmed) {
		this.uncodedmed = uncodedmed;
	}
	public String getInteral_source() {
		return interal_source;
	}
	public void setInteral_source(String interal_source) {
		this.interal_source = interal_source;
	}
	public String getQtyunits() {
		return qtyunits;
	}
	public void setQtyunits(String qtyunits) {
		this.qtyunits = qtyunits;
	}
	public Boolean getPresctype() {
		return presctype;
	}
	public void setPresctype(Boolean presctype) {
		this.presctype = presctype;
	}
	public String getRxnormcode() {
		return rxnormcode;
	}
	public void setRxnormcode(String rxnormcode) {
		this.rxnormcode = rxnormcode;
	}
	public Boolean getLeaf_patient_iscomplete() {
		return leaf_patient_iscomplete;
	}
	public void setLeaf_patient_iscomplete(Boolean leaf_patient_iscomplete) {
		this.leaf_patient_iscomplete = leaf_patient_iscomplete;
	}
	public Date getLeaf_patient_completed_on() {
		return leaf_patient_completed_on;
	}
	public void setLeaf_patient_completed_on(Date leaf_patient_completed_on) {
		this.leaf_patient_completed_on = leaf_patient_completed_on;
	}
}
