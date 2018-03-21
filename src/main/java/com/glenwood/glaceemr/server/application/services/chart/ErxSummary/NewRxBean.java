package com.glenwood.glaceemr.server.application.services.chart.ErxSummary;

public class NewRxBean {
	
	Integer prescriptionid;
	String frequency;
	Boolean uncoded;
	String prescriberordernumber;
	String ordereddate;
	String medicationname;
	String drugname;
	String dosage;
	String unit;
	String form;
	String schedule1 ;
	String notes;
	String duration;
	String NumberForEachIntake;
	String Route;
	String Quantity;
	String refills ;
	Boolean isPrinted;
	String refillsqualifier;
	Integer substitution ;
	String quantityqualifier;
	Integer uniquekey;
	String rxrefno;
	String RmsgId;
	String ssdrugformcode;
	String rxdxcode;
	String Drugcoveragestatuscode;
	
	public String getRefillsqualifier() {
		return refillsqualifier;
	}


	public void setRefillsqualifier(String refillsqualifier) {
		this.refillsqualifier = refillsqualifier;
	}


	public String getDrugcoveragestatuscode() {
		return Drugcoveragestatuscode;
	}


	public void setDrugcoveragestatuscode(String drugcoveragestatuscode) {
		Drugcoveragestatuscode = drugcoveragestatuscode;
	}


	public String getCodelistqualifier() {
		return codelistqualifier;
	}


	public void setCodelistqualifier(String codelistqualifier) {
		this.codelistqualifier = codelistqualifier;
	}


	public String getUnitsourcecode() {
		return unitsourcecode;
	}


	public void setUnitsourcecode(String unitsourcecode) {
		this.unitsourcecode = unitsourcecode;
	}


	public String getFormcode() {
		return formcode;
	}


	public void setFormcode(String formcode) {
		this.formcode = formcode;
	}


	public String getPotencityunitcode() {
		return potencityunitcode;
	}


	public void setPotencityunitcode(String potencityunitcode) {
		this.potencityunitcode = potencityunitcode;
	}


	public String getFormsourcecode() {
		return formsourcecode;
	}


	public void setFormsourcecode(String formsourcecode) {
		this.formsourcecode = formsourcecode;
	}


	String rxdxqualifier;
	String clinical_information_qualifier;
	String payerid;
	Integer requestpbmid;
	String sig;
	String coolastname;
	String coofirstname;
	String pat_cardholderid;
	String pat_pbmname;
	String pat_groupid;
	String pat_cardholdername;
	String pat_cardholderfname;
	String pat_cardholderlname;
	String pat_insuranceid_part1;
	String pat_insuranceid_part2;
	String pat_bin_id;
	String ssndc;
	String ssndc_qualifier;
	String quantity_units;
	String ordereddate_mmddyyyy;
	String codelistqualifier;
	String unitsourcecode;
	String effectivedate_MMDDYYYY;
	String effectivedate;
	Integer dea_schedule_value;
	String deaschedule;
	String formcode;
	String potencityunitcode;
	String formsourcecode;
	Integer provider_id  ;
	Boolean drug_overridden ;
	Integer statusid;
	boolean ismedsup;
	
	
	public boolean getIsmedsup() {
		return ismedsup;
	}


	public void setIsmedsup(boolean ismedsup) {
		this.ismedsup = ismedsup;
	}


	public Integer getPrescriptionid() {
		return prescriptionid;
	}


	public void setPrescriptionid(Integer prescriptionid) {
		this.prescriptionid = prescriptionid;
	}


	public String getFrequency() {
		return frequency;
	}


	public void setFrequency(String frequency) {
		this.frequency = frequency;
	}


	public Boolean getUncoded() {
		return uncoded;
	}


	public void setUncoded(Boolean uncoded) {
		this.uncoded = uncoded;
	}


	public String getPrescriberordernumber() {
		return prescriberordernumber;
	}


	public void setPrescriberordernumber(String prescriberordernumber) {
		this.prescriberordernumber = prescriberordernumber;
	}


	public String getOrdereddate() {
		return ordereddate;
	}


	public void setOrdereddate(String ordereddate) {
		this.ordereddate = ordereddate;
	}


	public String getMedicationname() {
		return medicationname;
	}


	public void setMedicationname(String medicationname) {
		this.medicationname = medicationname;
	}


	public String getDrugname() {
		return drugname;
	}


	public void setDrugname(String drugname) {
		this.drugname = drugname;
	}


	public String getDosage() {
		return dosage;
	}


	public void setDosage(String dosage) {
		this.dosage = dosage;
	}


	public String getUnit() {
		return unit;
	}


	public void setUnit(String unit) {
		this.unit = unit;
	}


	public String getForm() {
		return form;
	}


	public void setForm(String form) {
		this.form = form;
	}


	public String getSchedule1() {
		return schedule1;
	}


	public void setSchedule1(String schedule1) {
		this.schedule1 = schedule1;
	}


	public String getNotes() {
		return notes;
	}


	public void setNotes(String notes) {
		this.notes = notes;
	}


	public String getDuration() {
		return duration;
	}


	public void setDuration(String duration) {
		this.duration = duration;
	}


	public String getNumberForEachIntake() {
		return NumberForEachIntake;
	}


	public void setNumberForEachIntake(String numberForEachIntake) {
		NumberForEachIntake = numberForEachIntake;
	}


	public String getRoute() {
		return Route;
	}


	public void setRoute(String route) {
		Route = route;
	}


	public String getQuantity() {
		return Quantity;
	}


	public void setQuantity(String quantity) {
		Quantity = quantity;
	}


	public String getRefills() {
		return refills;
	}


	public void setRefills(String refills) {
		this.refills = refills;
	}


	public Boolean getIsPrinted() {
		return isPrinted;
	}


	public void setIsPrinted(Boolean isPrinted) {
		this.isPrinted = isPrinted;
	}


	public Integer getSubstitution() {
		return substitution;
	}


	public void setSubstitution(Integer substitution) {
		this.substitution = substitution;
	}


	public String getQuantityqualifier() {
		return quantityqualifier;
	}


	public void setQuantityqualifier(String quantityqualifier) {
		this.quantityqualifier = quantityqualifier;
	}


	public Integer getUniquekey() {
		return uniquekey;
	}


	public void setUniquekey(Integer uniquekey) {
		this.uniquekey = uniquekey;
	}


	public String getRxrefno() {
		return rxrefno;
	}


	public void setRxrefno(String rxrefno) {
		this.rxrefno = rxrefno;
	}


	public String getRmsgId() {
		return RmsgId;
	}


	public void setRmsgId(String rmsgId) {
		RmsgId = rmsgId;
	}


	public String getSsdrugformcode() {
		return ssdrugformcode;
	}


	public void setSsdrugformcode(String ssdrugformcode) {
		this.ssdrugformcode = ssdrugformcode;
	}


	public String getRxdxcode() {
		return rxdxcode;
	}


	public void setRxdxcode(String rxdxcode) {
		this.rxdxcode = rxdxcode;
	}


	public String getRxdxqualifier() {
		return rxdxqualifier;
	}


	public void setRxdxqualifier(String rxdxqualifier) {
		this.rxdxqualifier = rxdxqualifier;
	}


	public String getClinical_information_qualifier() {
		return clinical_information_qualifier;
	}


	public void setClinical_information_qualifier(
			String clinical_information_qualifier) {
		this.clinical_information_qualifier = clinical_information_qualifier;
	}


	public String getPayerid() {
		return payerid;
	}


	public void setPayerid(String payerid) {
		this.payerid = payerid;
	}


	public Integer getRequestpbmid() {
		return requestpbmid;
	}


	public void setRequestpbmid(Integer requestpbmid) {
		this.requestpbmid = requestpbmid;
	}


	public String getSig() {
		return sig;
	}


	public void setSig(String sig) {
		this.sig = sig;
	}


	public String getCoolastname() {
		return coolastname;
	}


	public void setCoolastname(String coolastname) {
		this.coolastname = coolastname;
	}


	public String getCoofirstname() {
		return coofirstname;
	}


	public void setCoofirstname(String coofirstname) {
		this.coofirstname = coofirstname;
	}


	public String getPat_cardholderid() {
		return pat_cardholderid;
	}


	public void setPat_cardholderid(String pat_cardholderid) {
		this.pat_cardholderid = pat_cardholderid;
	}


	public String getPat_pbmname() {
		return pat_pbmname;
	}


	public void setPat_pbmname(String pat_pbmname) {
		this.pat_pbmname = pat_pbmname;
	}


	public String getPat_groupid() {
		return pat_groupid;
	}


	public void setPat_groupid(String pat_groupid) {
		this.pat_groupid = pat_groupid;
	}


	public String getPat_cardholdername() {
		return pat_cardholdername;
	}


	public void setPat_cardholdername(String pat_cardholdername) {
		this.pat_cardholdername = pat_cardholdername;
	}


	public String getPat_cardholderfname() {
		return pat_cardholderfname;
	}


	public void setPat_cardholderfname(String pat_cardholderfname) {
		this.pat_cardholderfname = pat_cardholderfname;
	}


	public String getPat_cardholderlname() {
		return pat_cardholderlname;
	}


	public void setPat_cardholderlname(String pat_cardholderlname) {
		this.pat_cardholderlname = pat_cardholderlname;
	}


	public String getPat_insuranceid_part1() {
		return pat_insuranceid_part1;
	}


	public void setPat_insuranceid_part1(String pat_insuranceid_part1) {
		this.pat_insuranceid_part1 = pat_insuranceid_part1;
	}


	public String getPat_insuranceid_part2() {
		return pat_insuranceid_part2;
	}


	public void setPat_insuranceid_part2(String pat_insuranceid_part2) {
		this.pat_insuranceid_part2 = pat_insuranceid_part2;
	}


	public String getPat_bin_id() {
		return pat_bin_id;
	}


	public void setPat_bin_id(String pat_bin_id) {
		this.pat_bin_id = pat_bin_id;
	}


	public String getSsndc() {
		return ssndc;
	}


	public void setSsndc(String ssndc) {
		this.ssndc = ssndc;
	}


	public String getSsndc_qualifier() {
		return ssndc_qualifier;
	}


	public void setSsndc_qualifier(String ssndc_qualifier) {
		this.ssndc_qualifier = ssndc_qualifier;
	}


	public String getQuantity_units() {
		return quantity_units;
	}


	public void setQuantity_units(String quantity_units) {
		this.quantity_units = quantity_units;
	}


	public String getOrdereddate_mmddyyyy() {
		return ordereddate_mmddyyyy;
	}


	public void setOrdereddate_mmddyyyy(String ordereddate_mmddyyyy) {
		this.ordereddate_mmddyyyy = ordereddate_mmddyyyy;
	}


	public String getEffectivedate_MMDDYYYY() {
		return effectivedate_MMDDYYYY;
	}


	public void setEffectivedate_MMDDYYYY(String effectivedate_MMDDYYYY) {
		this.effectivedate_MMDDYYYY = effectivedate_MMDDYYYY;
	}


	public String getEffectivedate() {
		return effectivedate;
	}


	public void setEffectivedate(String effectivedate) {
		this.effectivedate = effectivedate;
	}


	public Integer getDea_schedule_value() {
		return dea_schedule_value;
	}


	public void setDea_schedule_value(Integer dea_schedule_value) {
		this.dea_schedule_value = dea_schedule_value;
	}


	public String getDeaschedule() {
		return deaschedule;
	}


	public void setDeaschedule(String deaschedule) {
		this.deaschedule = deaschedule;
	}


	public Integer getProvider_id() {
		return provider_id;
	}


	public void setProvider_id(Integer provider_id) {
		this.provider_id = provider_id;
	}


	public Boolean getDrug_overridden() {
		return drug_overridden;
	}


	public void setDrug_overridden(Boolean drug_overridden) {
		this.drug_overridden = drug_overridden;
	}


	public Integer getStatusid() {
		return statusid;
	}


	public void setStatusid(Integer statusid) {
		this.statusid = statusid;
	}


	public NewRxBean(Integer prescriptionid,String frequency,Boolean uncoded,String ordereddate,String medicationname, String drugname,String dosage,String unit,String form ,String notes,String duration,String NumberForEachIntake,String Route,String Quantity,String refills ,Boolean isPrinted,Integer substitution ,String quantityqualifier,Integer uniquekey,String rxrefno,String RmsgId,String ssdrugformcode,String rxdxcode,String rxdxqualifier,String clinical_information_qualifier,String payerid,Integer requestpbmid,String sig,String coolastname,String coofirstname,String pat_cardholderid,String pat_pbmname,String pat_groupid,String pat_cardholdername,String pat_cardholderfname,String pat_cardholderlname,String pat_insuranceid_part1,String pat_insuranceid_part2,String pat_bin_id,String ssndc,String quantity_units,String ordereddate_mmddyyyy,String effectivedate_MMDDYYYY,String effectivedate,Integer dea_schedule_value,String deaschedule,Integer provider_id  ,Boolean drug_overridden ,Integer statusid,boolean ismedsup){
		this.prescriptionid=prescriptionid;
		this.frequency=frequency;
		this.uncoded=uncoded;
		this.ordereddate=ordereddate;
		this.medicationname=medicationname;
		this.drugname=drugname;
		this.dosage=dosage;
		this.unit=unit;
		this.form=form;
		this.notes=notes;
		this.duration=duration;
		this.NumberForEachIntake=NumberForEachIntake;
		this.Route=Route;
		this.Quantity=Quantity;
		this.refills=refills;
		this.isPrinted=isPrinted;
		this.substitution=substitution;
		this.quantityqualifier=quantityqualifier;
		this.uniquekey=uniquekey;
		this.rxrefno=rxrefno;
		this.RmsgId=RmsgId;
		this.ssdrugformcode=ssdrugformcode;
		this.rxdxcode=rxdxcode;
		this.rxdxqualifier=rxdxqualifier;
		this.clinical_information_qualifier=clinical_information_qualifier;
		this.payerid=payerid;
		this.requestpbmid=requestpbmid;
		this.sig=sig;
		this.coolastname=coolastname;
		this.coofirstname=coofirstname;
		this.pat_cardholderid=pat_cardholderid;
		this.pat_pbmname=pat_pbmname;
		this.pat_groupid=pat_groupid;
		this.pat_cardholdername=pat_cardholdername;
		this.pat_cardholderfname=pat_cardholderfname;
		this.pat_cardholderlname=pat_cardholderlname;
		this.pat_insuranceid_part1=pat_insuranceid_part1;
		this.pat_insuranceid_part2=pat_insuranceid_part2;
		this.pat_bin_id=pat_bin_id;
		this.ssndc=ssndc;
		this.quantity_units=quantity_units;
		this.ordereddate_mmddyyyy=ordereddate_mmddyyyy;
		this.effectivedate_MMDDYYYY=effectivedate_MMDDYYYY;
		this.effectivedate=effectivedate;
		this.dea_schedule_value=dea_schedule_value;
		this.deaschedule=deaschedule;
		this.provider_id=provider_id;
		this.drug_overridden=drug_overridden;
		this.statusid=statusid;
		this.ismedsup=ismedsup;
	}

}
