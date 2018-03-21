package com.glenwood.glaceemr.server.application.services.chart.ErxSummary;

import java.util.Date;

public class PrescribedMedBean {
	
	
	Integer prescId;
	Integer EncounterId;
	Integer StatusId;
	String StartDate;
	Date stopdate;
	Boolean ischronic ;
	Boolean toprint;
	Boolean tofax;
	Integer orderBy;
	Date OrderDate;
	Integer ModifiedBy;
	String ModifiedDate;
	String Number;
	String qty;
	String drugname;
	String dosagename;
	String unitname;
	String refills;
	String route;
	String duration;
	String lotno;
	String expdate;
	String days;
	String Sch1;
	String Sch2;
	String form;
	String comments;
	Integer rootid;
	Boolean allowsubstitution;
	String rxdxcode;
	String dxqualifiercode;
	Integer iseprescription;
	Integer isprescriptionsent;
	Integer uniquekey;
	String createdby;
	Integer messageid;
	String subjectstr;
	String subjectstr_desc;
	String subjectval;
	String ssdrugformcode;
	String medInstruction;
	String addi;
	String qtyunits;
	Integer eliid;
	String formstatus;
	String copaydetail;
	String covstatus;
	String ndccode;
	String external_source_info;
	Boolean overriddenflag;
	String monograph;
	String leaflet;
	Boolean isPrinted;
	Integer isFaxSent;
	String med_quit_reason ;
	Boolean uncodedmed;
	String interal_source;
	String Modified_Date;
	String previous_dose;
	String rxnormcode;
	Boolean presctype;
	String trans_mode;
	String errorCodes; 
	String actualSentTime;
	String sentDoctor;
	String senttime;
	String pharmacyname;
	String failurereason;
	Double datediff;
	
	
	
public Integer getPrescId() {
		return prescId;
	}



	public void setPrescId(Integer prescId) {
		this.prescId = prescId;
	}



	public Integer getEncounterId() {
		return EncounterId;
	}



	public void setEncounterId(Integer encounterId) {
		EncounterId = encounterId;
	}



	public Integer getStatusId() {
		return StatusId;
	}



	public void setStatusId(Integer statusId) {
		StatusId = statusId;
	}



	public String getStartDate() {
		return StartDate;
	}



	public void setStartDate(String startDate) {
		StartDate = startDate;
	}



	public Date getStopdate() {
		return stopdate;
	}



	public void setStopdate(Date stopdate) {
		this.stopdate = stopdate;
	}



	public Boolean getIschronic() {
		return ischronic;
	}



	public void setIschronic(Boolean ischronic) {
		this.ischronic = ischronic;
	}



	public Boolean getToprint() {
		return toprint;
	}



	public void setToprint(Boolean toprint) {
		this.toprint = toprint;
	}



	public Boolean getTofax() {
		return tofax;
	}



	public void setTofax(Boolean tofax) {
		this.tofax = tofax;
	}



	public Integer getOrderBy() {
		return orderBy;
	}



	public void setOrderBy(Integer orderBy) {
		this.orderBy = orderBy;
	}



	public Date getOrderDate() {
		return OrderDate;
	}



	public void setOrderDate(Date orderDate) {
		OrderDate = orderDate;
	}



	public Integer getModifiedBy() {
		return ModifiedBy;
	}



	public void setModifiedBy(Integer modifiedBy) {
		ModifiedBy = modifiedBy;
	}



	public String getModifiedDate() {
		return ModifiedDate;
	}



	public void setModifiedDate(String modifiedDate) {
		ModifiedDate = modifiedDate;
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



	public String getRxdxcode() {
		return rxdxcode;
	}



	public void setRxdxcode(String rxdxcode) {
		this.rxdxcode = rxdxcode;
	}



	public String getDxqualifiercode() {
		return dxqualifiercode;
	}



	public void setDxqualifiercode(String dxqualifiercode) {
		this.dxqualifiercode = dxqualifiercode;
	}



	public Integer getIseprescription() {
		return iseprescription;
	}



	public void setIseprescription(Integer iseprescription) {
		this.iseprescription = iseprescription;
	}



	public Integer getIsprescriptionsent() {
		return isprescriptionsent;
	}



	public void setIsprescriptionsent(Integer isprescriptionsent) {
		this.isprescriptionsent = isprescriptionsent;
	}



	public Integer getUniquekey() {
		return uniquekey;
	}



	public void setUniquekey(Integer uniquekey) {
		this.uniquekey = uniquekey;
	}



	public String getCreatedby() {
		return createdby;
	}



	public void setCreatedby(String createdby) {
		this.createdby = createdby;
	}



	public Integer getMessageid() {
		return messageid;
	}



	public void setMessageid(Integer messageid) {
		this.messageid = messageid;
	}



	public String getSubjectstr() {
		return subjectstr;
	}



	public void setSubjectstr(String subjectstr) {
		this.subjectstr = subjectstr;
	}



	public String getSubjectstr_desc() {
		return subjectstr_desc;
	}



	public void setSubjectstr_desc(String subjectstr_desc) {
		this.subjectstr_desc = subjectstr_desc;
	}



	public String getSubjectval() {
		return subjectval;
	}



	public void setSubjectval(String subjectval) {
		this.subjectval = subjectval;
	}



	public String getSsdrugformcode() {
		return ssdrugformcode;
	}



	public void setSsdrugformcode(String ssdrugformcode) {
		this.ssdrugformcode = ssdrugformcode;
	}



	public String getMedInstruction() {
		return medInstruction;
	}



	public void setMedInstruction(String medInstruction) {
		this.medInstruction = medInstruction;
	}



	public String getAddi() {
		return addi;
	}



	public void setAddi(String addi) {
		this.addi = addi;
	}



	public String getQtyunits() {
		return qtyunits;
	}



	public void setQtyunits(String qtyunits) {
		this.qtyunits = qtyunits;
	}


	public Integer getEliid() {
		return eliid;
	}



	public void setEliid(Integer eliid) {
		this.eliid = eliid;
	}



	public String getFormstatus() {
		return formstatus;
	}



	public void setFormstatus(String formstatus) {
		this.formstatus = formstatus;
	}



	public String getCopaydetail() {
		return copaydetail;
	}



	public void setCopaydetail(String copaydetail) {
		this.copaydetail = copaydetail;
	}



	public String getCovstatus() {
		return covstatus;
	}



	public void setCovstatus(String covstatus) {
		this.covstatus = covstatus;
	}



	public String getNdccode() {
		return ndccode;
	}



	public void setNdccode(String ndccode) {
		this.ndccode = ndccode;
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



	public String getMonograph() {
		return monograph;
	}



	public void setMonograph(String monograph) {
		this.monograph = monograph;
	}



	public String getLeaflet() {
		return leaflet;
	}



	public void setLeaflet(String leaflet) {
		this.leaflet = leaflet;
	}



	public Boolean getIsPrinted() {
		return isPrinted;
	}



	public void setIsPrinted(Boolean isPrinted) {
		this.isPrinted = isPrinted;
	}



	public Integer getIsFaxSent() {
		return isFaxSent;
	}



	public void setIsFaxSent(Integer isFaxSent) {
		this.isFaxSent = isFaxSent;
	}



	public String getMed_quit_reason() {
		return med_quit_reason;
	}



	public void setMed_quit_reason(String med_quit_reason) {
		this.med_quit_reason = med_quit_reason;
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



	public String getModified_Date() {
		return Modified_Date;
	}



	public void setModified_Date(String modified_Date) {
		Modified_Date = modified_Date;
	}



	public String getPrevious_dose() {
		return previous_dose;
	}



	public void setPrevious_dose(String previous_dose) {
		this.previous_dose = previous_dose;
	}



	public String getRxnormcode() {
		return rxnormcode;
	}



	public void setRxnormcode(String rxnormcode) {
		this.rxnormcode = rxnormcode;
	}



	public Boolean getPresctype() {
		return presctype;
	}



	public void setPresctype(Boolean presctype) {
		this.presctype = presctype;
	}



	public String getTrans_mode() {
		return trans_mode;
	}



	public void setTrans_mode(String trans_mode) {
		this.trans_mode = trans_mode;
	}



	public String getErrorCodes() {
		return errorCodes;
	}



	public void setErrorCodes(String errorCodes) {
		this.errorCodes = errorCodes;
	}



	public String getActualSentTime() {
		return actualSentTime;
	}



	public void setActualSentTime(String actualSentTime) {
		this.actualSentTime = actualSentTime;
	}



	public String getSentDoctor() {
		return sentDoctor;
	}



	public void setSentDoctor(String sentDoctor) {
		this.sentDoctor = sentDoctor;
	}



	public String getSenttime() {
		return senttime;
	}



	public void setSenttime(String senttime) {
		this.senttime = senttime;
	}



	public String getPharmacyname() {
		return pharmacyname;
	}



	public void setPharmacyname(String pharmacyname) {
		this.pharmacyname = pharmacyname;
	}



	public String getFailurereason() {
		return failurereason;
	}



	public void setFailurereason(String failurereason) {
		this.failurereason = failurereason;
	}



	public Double getDatediff() {
		return datediff;
	}



	public void setDatediff(Double datediff) {
		this.datediff = datediff;
	}



public PrescribedMedBean(Integer prescId,Integer EncounterId,Integer StatusId,String StartDate,Date stopdate,Boolean ischronic ,Boolean toprint,Boolean tofax,Integer orderBy,Date OrderDate,Integer ModifiedBy,String ModifiedDate,String Number,String qty,String drugname,String dosagename,String unitname,String refills,String route,String duration,String lotno,String expdate,String days,String Sch1,String Sch2,String form,String comments,Integer rootid,Boolean allowsubstitution,String rxdxcode,String dxqualifiercode,Integer iseprescription,Integer isprescriptionsent,Integer uniquekey,String createdby,Integer messageid,String subjectstr,String subjectstr_desc,String subjectval,String ssdrugformcode,String medInstruction,String addi,String qtyunits,Integer eliid,String formstatus,String copaydetail,String covstatus,String ndccode,String external_source_info,Boolean overriddenflag,String monograph,String leaflet,Boolean isPrinted,Integer isFaxSent,String med_quit_reason ,Boolean uncodedmed,String interal_source,String previous_dose,String rxnormcode,Boolean presctype  ,String errorCodes,String actualSentTime,String sentDoctor,String senttime,String pharmacyname,String failurereason,Double datediff){
	
	this.prescId=prescId;
	this.EncounterId=EncounterId;
	this.StatusId=StatusId;
	this.StartDate=StartDate;
	this.stopdate=stopdate;
	this.ischronic=ischronic;
	this.toprint=toprint;
	this.tofax=tofax;
	this.orderBy=orderBy;
	this.OrderDate=OrderDate;
	this.ModifiedBy=ModifiedBy;
	this.ModifiedDate=ModifiedDate;
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
	this.rxdxcode=rxdxcode;
	this.dxqualifiercode=dxqualifiercode;
	this.iseprescription=iseprescription;
	this.isprescriptionsent=isprescriptionsent;
	this.uniquekey=uniquekey;
	this.createdby=createdby;
	this.messageid=messageid;
	this.subjectstr=subjectstr;
	this.subjectstr_desc=subjectstr_desc;
	this.subjectval=subjectval;
	this.ssdrugformcode=ssdrugformcode;
	this.medInstruction=medInstruction;
	this.addi=addi;
	this.qtyunits=qtyunits;
	this.eliid=eliid;
	this.formstatus=formstatus;
	this.copaydetail=copaydetail;
	this.covstatus=covstatus;
	this.ndccode=ndccode;
	this.external_source_info=external_source_info;
	this.overriddenflag=overriddenflag;
	this.monograph=monograph;
	this.leaflet=leaflet;
	this.isPrinted=isPrinted;
	this.isFaxSent=isFaxSent;
	this.med_quit_reason=med_quit_reason;
	this.uncodedmed=uncodedmed;
	this.interal_source=interal_source;
	this.previous_dose=previous_dose;
	this.rxnormcode=rxnormcode;
	this.presctype=presctype;
	this.errorCodes=errorCodes; 
	this.actualSentTime=actualSentTime;
	this.sentDoctor=sentDoctor;
	this.senttime=senttime;
	this.pharmacyname=pharmacyname;
	this.failurereason=failurereason;
	this.datediff=datediff;
	
	
}
	

}
