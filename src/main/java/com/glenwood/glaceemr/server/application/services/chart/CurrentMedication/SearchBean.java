package com.glenwood.glaceemr.server.application.services.chart.CurrentMedication;

public class SearchBean {
	
	 String rxName;
	 Integer cs_schedule;
	 String drugname; 
	 String ndccode; 
	 String dosagename ; 
	 String units ; 
	 String route ; 
	 String sch1;
	 String form ;
	 Integer Relation; 
	 Integer medid;
	 Integer qty; 
	 Integer refill;
	 String intake; 
	 String days;
	 String drugtype;
	 String drugstatus;
	 String qtyunits;
	 String external_source_info;
	 String category; 
	 Boolean presctype;
	 String imagename;  
	 String rxnormcode;
	 Long totrec;
	 
	 public SearchBean(String rxName, Integer cs_schedule, String drugname, String ndccode, String dosagename , String units , String route , String sch1, String form , Integer Relation, Integer medid, Integer qty, Integer refill, String intake, String days, String drugtype, String drugstatus, String qtyunits, String external_source_info, String category, Boolean presctype, String imagename, String rxnormcode){
		
		 this.rxName=rxName;
		 this.cs_schedule=cs_schedule;
		 this.drugname=drugname; 
		 this.ndccode=ndccode; 
		 this.dosagename =dosagename; 
		 this.units =units; 
		 this.route =route; 
		 this.sch1=sch1;
		 this.form =form;
		 this.Relation=Relation; 
		 this.medid=medid;
		 this.qty=qty; 
		 this.refill=refill;
		 this.intake=intake; 
		 this.days=days;
		 this.drugtype=drugtype;
		 this.drugstatus=drugstatus;
		 this.qtyunits=qtyunits;
		 this.external_source_info=external_source_info;
		 this.category=category; 
		 this.presctype=presctype;
		 this.imagename=imagename;  
		 this.rxnormcode=rxnormcode;
	 }

	public String getRxName() {
		return rxName;
	}

	public void setRxName(String rxName) {
		this.rxName = rxName;
	}

	public Integer getCs_schedule() {
		return cs_schedule;
	}

	public void setCs_schedule(Integer cs_schedule) {
		this.cs_schedule = cs_schedule;
	}

	public String getDrugname() {
		return drugname;
	}

	public void setDrugname(String drugname) {
		this.drugname = drugname;
	}

	public String getNdccode() {
		return ndccode;
	}

	public void setNdccode(String ndccode) {
		this.ndccode = ndccode;
	}

	public String getDosagename() {
		return dosagename;
	}

	public void setDosagename(String dosagename) {
		this.dosagename = dosagename;
	}

	public String getUnits() {
		return units;
	}

	public void setUnits(String units) {
		this.units = units;
	}

	public String getRoute() {
		return route;
	}

	public void setRoute(String route) {
		this.route = route;
	}

	public String getSch1() {
		return sch1;
	}

	public void setSch1(String sch1) {
		this.sch1 = sch1;
	}

	public String getForm() {
		return form;
	}

	public void setForm(String form) {
		this.form = form;
	}

	public Integer getRelation() {
		return Relation;
	}

	public void setRelation(Integer relation) {
		Relation = relation;
	}

	public Integer getMedid() {
		return medid;
	}

	public void setMedid(Integer medid) {
		this.medid = medid;
	}

	public Integer getQty() {
		return qty;
	}

	public void setQty(Integer qty) {
		this.qty = qty;
	}

	public Integer getRefill() {
		return refill;
	}

	public void setRefill(Integer refill) {
		this.refill = refill;
	}

	public String getIntake() {
		return intake;
	}

	public void setIntake(String intake) {
		this.intake = intake;
	}

	public String getDays() {
		return days;
	}

	public void setDays(String days) {
		this.days = days;
	}

	public String getDrugtype() {
		return drugtype;
	}

	public void setDrugtype(String drugtype) {
		this.drugtype = drugtype;
	}

	public String getDrugstatus() {
		return drugstatus;
	}

	public void setDrugstatus(String drugstatus) {
		this.drugstatus = drugstatus;
	}

	public String getQtyunits() {
		return qtyunits;
	}

	public void setQtyunits(String qtyunits) {
		this.qtyunits = qtyunits;
	}

	public String getExternal_source_info() {
		return external_source_info;
	}

	public void setExternal_source_info(String external_source_info) {
		this.external_source_info = external_source_info;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public Boolean getPresctype() {
		return presctype;
	}

	public void setPresctype(Boolean presctype) {
		this.presctype = presctype;
	}

	public String getImagename() {
		return imagename;
	}

	public void setImagename(String imagename) {
		this.imagename = imagename;
	}

	public String getRxnormcode() {
		return rxnormcode;
	}

	public void setRxnormcode(String rxnormcode) {
		this.rxnormcode = rxnormcode;
	}

	public Long getTotrec() {
		return totrec;
	}

	public void setTotrec(Long totrec) {
		this.totrec = totrec;
	}

}
