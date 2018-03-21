package com.glenwood.glaceemr.server.application.Bean;

import java.util.Date;

public class patientInsDetailBean {

	private Integer patientInsDetailInsaddressid;
	private Short patientInsDetailInsno;
	private Integer patientInsDetailInstype;
	private String patientInsDetailPatientinsuranceid;
	private String patientInsDetailSubscribername;
	private Date patientInsDetailSubscriberDob;
	private Short patientInsDetailRelationtosubs;
	private Date patientInsDetailValidfrom;
	private Date  patientInsDetailValidto;
	private Date patientInsDetailIssueddate;
	private Date patientInsDetailLastModifiedDate;
	private Boolean patientInsDetailIsactive;
	private String patientInsDetailGroupno;
	private String patientInsDetailGroupname;
	private String patientInsDetailInsphone;
	private String insCompAddrAddress;
	private String insCompAddrCity;
	private String insCompAddrZip;
	private Short patientInsDetailPlantype;
	private Double patientInsDetailDeductible;
	private Boolean patientInsDetailCapitated;
	private Boolean patientInsDetailSlsPresent;
	private Boolean patientInsDetailDmePresent;
	private Double patientInsDetailOopMet;
	private Double patientInsDetailAmountMet;
	private Boolean patientInsDetailReferralReq;
	private Double patientInsDetailOutOfPocket;
	private String patientInsDetailDmeCoins;
	private String patientInsDetailSleepstudyCoins;
	private String patientInsDetailCoins;
	private String patientInsDetailAuthReq;
	private Boolean patientInsDetailDeductablemet;
	private Boolean patientInsDetailIsdeductable;
	private Double patientInsDetailCopay;
	private Double patientInsDetailOtherCopay;
	private Double patientInsDetailProcedureCopay;
	private Integer patientInsDetailLifetimemax;
	private Boolean patientInsDetailIsvfceligible;
	private Integer patientInsDetailFinClass;
	private Integer patientInsDetailMaxVisit;
	private String patientInsDetailProgramType;
	private Date patientInsDetailProgramStartdate;
	private Date patientInsDetailProgramEnddate;
	private String insCompanyName;


	public patientInsDetailBean(Integer patientInsDetailInsaddressid,Short patientInsDetailInsno,Integer patientInsDetailInstype,  String patientInsDetailPatientinsuranceid,String patientInsDetailSubscribername, Date patientInsDetailSubscriberDob,Short patientInsDetailRelationtosubs,Date patientInsDetailValidfrom,Date  patientInsDetailValidto,Date patientInsDetailIssueddate,Date patientInsDetailLastModifiedDate,Boolean patientInsDetailIsactive,String patientInsDetailGroupno,String patientInsDetailGroupname,String patientInsDetailInsphone,String insCompAddrAddress,String insCompAddrCity,String insCompAddrZip,Short patientInsDetailPlantype,Double patientInsDetailDeductible,Boolean patientInsDetailCapitated,Boolean patientInsDetailSlsPresent,Boolean patientInsDetailDmePresent,Double patientInsDetailOopMet,Double patientInsDetailAmountMet,Boolean patientInsDetailReferralReq,Double patientInsDetailOutOfPocket,String patientInsDetailDmeCoins,String patientInsDetailSleepstudyCoins,String patientInsDetailCoins,String patientInsDetailAuthReq,Boolean patientInsDetailDeductablemet,Boolean patientInsDetailIsdeductable,Double patientInsDetailCopay,Double patientInsDetailOtherCopay,Double patientInsDetailProcedureCopay,Integer patientInsDetailLifetimemax,Boolean patientInsDetailIsvfceligible,Integer patientInsDetailFinClass,Integer patientInsDetailMaxVisit,String patientInsDetailProgramType,Date patientInsDetailProgramStartdate,Date patientInsDetailProgramEnddate,String insCompanyName){
		super();

		this.patientInsDetailInsaddressid=patientInsDetailInsaddressid;
		this.patientInsDetailInsno=patientInsDetailInsno;
		this.patientInsDetailInstype=patientInsDetailInstype;
		this.patientInsDetailPatientinsuranceid=patientInsDetailPatientinsuranceid;
		this.patientInsDetailSubscribername=patientInsDetailSubscribername;
		this.patientInsDetailSubscriberDob=patientInsDetailSubscriberDob;
		this.patientInsDetailRelationtosubs=patientInsDetailRelationtosubs;
		this.patientInsDetailValidfrom=patientInsDetailValidfrom;
		this. patientInsDetailValidto=patientInsDetailValidto;
		this.patientInsDetailIssueddate=patientInsDetailIssueddate;
		this.patientInsDetailLastModifiedDate=patientInsDetailLastModifiedDate;
		this.patientInsDetailIsactive=patientInsDetailIsactive;
		this.patientInsDetailGroupno=patientInsDetailGroupno;
		this.patientInsDetailGroupname=patientInsDetailGroupname;
		this.patientInsDetailInsphone=patientInsDetailInsphone;
		this.insCompAddrAddress=insCompAddrAddress;
		this.insCompAddrCity=insCompAddrCity;
		this.insCompAddrZip=insCompAddrZip;
		this.patientInsDetailPlantype=patientInsDetailPlantype;
		this.patientInsDetailDeductible=patientInsDetailDeductible;
		this.patientInsDetailCapitated=patientInsDetailCapitated;
		this.patientInsDetailSlsPresent=patientInsDetailSlsPresent;
		this.patientInsDetailDmePresent=patientInsDetailDmePresent;
		this.patientInsDetailOopMet=patientInsDetailOopMet;
		this.patientInsDetailAmountMet=patientInsDetailAmountMet;
		this.patientInsDetailReferralReq=patientInsDetailReferralReq;
		this.patientInsDetailOutOfPocket=patientInsDetailOutOfPocket;
		this.patientInsDetailDmeCoins=patientInsDetailDmeCoins;
		this.patientInsDetailSleepstudyCoins=patientInsDetailSleepstudyCoins;
		this.patientInsDetailCoins=patientInsDetailCoins;
		this.patientInsDetailAuthReq=patientInsDetailAuthReq;
		this.patientInsDetailDeductablemet=patientInsDetailDeductablemet;
		this.patientInsDetailIsdeductable=patientInsDetailIsdeductable;
		this.patientInsDetailCopay=patientInsDetailCopay;
		this.patientInsDetailOtherCopay=patientInsDetailOtherCopay;
		this.patientInsDetailProcedureCopay=patientInsDetailProcedureCopay;
		this.patientInsDetailLifetimemax=patientInsDetailLifetimemax;
		this.patientInsDetailIsvfceligible=patientInsDetailIsvfceligible;
		this.patientInsDetailFinClass=patientInsDetailFinClass;
		this.patientInsDetailMaxVisit=patientInsDetailMaxVisit;
		this.patientInsDetailProgramType=patientInsDetailProgramType;
		this.patientInsDetailProgramStartdate=patientInsDetailProgramStartdate;
		this.patientInsDetailProgramEnddate=patientInsDetailProgramEnddate;
		this.insCompanyName=insCompanyName;

	}
	public Integer getPatientInsDetailInsaddressid() {
		return patientInsDetailInsaddressid;
	}
	public void setPatientInsDetailInsaddressid(Integer patientInsDetailInsaddressid) {
		this.patientInsDetailInsaddressid = patientInsDetailInsaddressid;
	}
	public Short getPatientInsDetailInsno() {
		return patientInsDetailInsno;
	}
	public void setPatientInsDetailInsno(Short patientInsDetailInsno) {
		this.patientInsDetailInsno = patientInsDetailInsno;
	}
	public Integer getPatientInsDetailInstype() {
		return patientInsDetailInstype;
	}
	public void setPatientInsDetailInstype(Integer patientInsDetailInstype) {
		this.patientInsDetailInstype = patientInsDetailInstype;
	}
	public String getPatientInsDetailPatientinsuranceid() {
		return patientInsDetailPatientinsuranceid;
	}
	public void setPatientInsDetailPatientinsuranceid(
			String patientInsDetailPatientinsuranceid) {
		this.patientInsDetailPatientinsuranceid = patientInsDetailPatientinsuranceid;
	}
	public String getPatientInsDetailSubscribername() {
		return patientInsDetailSubscribername;
	}
	public void setPatientInsDetailSubscribername(
			String patientInsDetailSubscribername) {
		this.patientInsDetailSubscribername = patientInsDetailSubscribername;
	}
	public Date getPatientInsDetailSubscriberDob() {
		return patientInsDetailSubscriberDob;
	}
	public void setPatientInsDetailSubscriberDob(Date patientInsDetailSubscriberDob) {
		this.patientInsDetailSubscriberDob = patientInsDetailSubscriberDob;
	}
	public Short getPatientInsDetailRelationtosubs() {
		return patientInsDetailRelationtosubs;
	}
	public void setPatientInsDetailRelationtosubs(
			Short patientInsDetailRelationtosubs) {
		this.patientInsDetailRelationtosubs = patientInsDetailRelationtosubs;
	}
	public Date getPatientInsDetailValidfrom() {
		return patientInsDetailValidfrom;
	}
	public void setPatientInsDetailValidfrom(Date patientInsDetailValidfrom) {
		this.patientInsDetailValidfrom = patientInsDetailValidfrom;
	}
	public Date getPatientInsDetailValidto() {
		return patientInsDetailValidto;
	}
	public void setPatientInsDetailValidto(Date patientInsDetailValidto) {
		this.patientInsDetailValidto = patientInsDetailValidto;
	}
	public Date getPatientInsDetailIssueddate() {
		return patientInsDetailIssueddate;
	}
	public void setPatientInsDetailIssueddate(Date patientInsDetailIssueddate) {
		this.patientInsDetailIssueddate = patientInsDetailIssueddate;
	}
	public Date getPatientInsDetailLastModifiedDate() {
		return patientInsDetailLastModifiedDate;
	}
	public void setPatientInsDetailLastModifiedDate(
			Date patientInsDetailLastModifiedDate) {
		this.patientInsDetailLastModifiedDate = patientInsDetailLastModifiedDate;
	}
	public Boolean getPatientInsDetailIsactive() {
		return patientInsDetailIsactive;
	}
	public void setPatientInsDetailIsactive(Boolean patientInsDetailIsactive) {
		this.patientInsDetailIsactive = patientInsDetailIsactive;
	}
	public String getPatientInsDetailGroupno() {
		return patientInsDetailGroupno;
	}
	public void setPatientInsDetailGroupno(String patientInsDetailGroupno) {
		this.patientInsDetailGroupno = patientInsDetailGroupno;
	}
	public String getPatientInsDetailGroupname() {
		return patientInsDetailGroupname;
	}
	public void setPatientInsDetailGroupname(String patientInsDetailGroupname) {
		this.patientInsDetailGroupname = patientInsDetailGroupname;
	}
	public String getPatientInsDetailInsphone() {
		return patientInsDetailInsphone;
	}
	public void setPatientInsDetailInsphone(String patientInsDetailInsphone) {
		this.patientInsDetailInsphone = patientInsDetailInsphone;
	}
	public String getInsCompAddrAddress() {
		return insCompAddrAddress;
	}
	public void setInsCompAddrAddress(String insCompAddrAddress) {
		this.insCompAddrAddress = insCompAddrAddress;
	}
	public String getInsCompAddrCity() {
		return insCompAddrCity;
	}
	public void setInsCompAddrCity(String insCompAddrCity) {
		this.insCompAddrCity = insCompAddrCity;
	}
	public String getInsCompAddrZip() {
		return insCompAddrZip;
	}
	public void setInsCompAddrZip(String insCompAddrZip) {
		this.insCompAddrZip = insCompAddrZip;
	}
	public Short getPatientInsDetailPlantype() {
		return patientInsDetailPlantype;
	}
	public void setPatientInsDetailPlantype(Short patientInsDetailPlantype) {
		this.patientInsDetailPlantype = patientInsDetailPlantype;
	}
	public Double getPatientInsDetailDeductible() {
		return patientInsDetailDeductible;
	}
	public void setPatientInsDetailDeductible(Double patientInsDetailDeductible) {
		this.patientInsDetailDeductible = patientInsDetailDeductible;
	}
	public Boolean getPatientInsDetailCapitated() {
		return patientInsDetailCapitated;
	}
	public void setPatientInsDetailCapitated(Boolean patientInsDetailCapitated) {
		this.patientInsDetailCapitated = patientInsDetailCapitated;
	}
	public Boolean getPatientInsDetailSlsPresent() {
		return patientInsDetailSlsPresent;
	}
	public void setPatientInsDetailSlsPresent(Boolean patientInsDetailSlsPresent) {
		this.patientInsDetailSlsPresent = patientInsDetailSlsPresent;
	}
	public Boolean getPatientInsDetailDmePresent() {
		return patientInsDetailDmePresent;
	}
	public void setPatientInsDetailDmePresent(Boolean patientInsDetailDmePresent) {
		this.patientInsDetailDmePresent = patientInsDetailDmePresent;
	}
	public Double getPatientInsDetailOopMet() {
		return patientInsDetailOopMet;
	}
	public void setPatientInsDetailOopMet(Double patientInsDetailOopMet) {
		this.patientInsDetailOopMet = patientInsDetailOopMet;
	}
	public Double getPatientInsDetailAmountMet() {
		return patientInsDetailAmountMet;
	}
	public void setPatientInsDetailAmountMet(Double patientInsDetailAmountMet) {
		this.patientInsDetailAmountMet = patientInsDetailAmountMet;
	}
	public Boolean getPatientInsDetailReferralReq() {
		return patientInsDetailReferralReq;
	}
	public void setPatientInsDetailReferralReq(Boolean patientInsDetailReferralReq) {
		this.patientInsDetailReferralReq = patientInsDetailReferralReq;
	}
	public Double getPatientInsDetailOutOfPocket() {
		return patientInsDetailOutOfPocket;
	}
	public void setPatientInsDetailOutOfPocket(Double patientInsDetailOutOfPocket) {
		this.patientInsDetailOutOfPocket = patientInsDetailOutOfPocket;
	}
	public String getPatientInsDetailDmeCoins() {
		return patientInsDetailDmeCoins;
	}
	public void setPatientInsDetailDmeCoins(String patientInsDetailDmeCoins) {
		this.patientInsDetailDmeCoins = patientInsDetailDmeCoins;
	}
	public String getPatientInsDetailSleepstudyCoins() {
		return patientInsDetailSleepstudyCoins;
	}
	public void setPatientInsDetailSleepstudyCoins(
			String patientInsDetailSleepstudyCoins) {
		this.patientInsDetailSleepstudyCoins = patientInsDetailSleepstudyCoins;
	}
	public String getPatientInsDetailCoins() {
		return patientInsDetailCoins;
	}
	public void setPatientInsDetailCoins(String patientInsDetailCoins) {
		this.patientInsDetailCoins = patientInsDetailCoins;
	}
	public String getPatientInsDetailAuthReq() {
		return patientInsDetailAuthReq;
	}
	public void setPatientInsDetailAuthReq(String patientInsDetailAuthReq) {
		this.patientInsDetailAuthReq = patientInsDetailAuthReq;
	}
	public Boolean getPatientInsDetailDeductablemet() {
		return patientInsDetailDeductablemet;
	}
	public void setPatientInsDetailDeductablemet(
			Boolean patientInsDetailDeductablemet) {
		this.patientInsDetailDeductablemet = patientInsDetailDeductablemet;
	}
	public Boolean getPatientInsDetailIsdeductable() {
		return patientInsDetailIsdeductable;
	}
	public void setPatientInsDetailIsdeductable(Boolean patientInsDetailIsdeductable) {
		this.patientInsDetailIsdeductable = patientInsDetailIsdeductable;
	}
	public Double getPatientInsDetailCopay() {
		return patientInsDetailCopay;
	}
	public void setPatientInsDetailCopay(Double patientInsDetailCopay) {
		this.patientInsDetailCopay = patientInsDetailCopay;
	}
	public Double getPatientInsDetailOtherCopay() {
		return patientInsDetailOtherCopay;
	}
	public void setPatientInsDetailOtherCopay(Double patientInsDetailOtherCopay) {
		this.patientInsDetailOtherCopay = patientInsDetailOtherCopay;
	}
	public Double getPatientInsDetailProcedureCopay() {
		return patientInsDetailProcedureCopay;
	}
	public void setPatientInsDetailProcedureCopay(
			Double patientInsDetailProcedureCopay) {
		this.patientInsDetailProcedureCopay = patientInsDetailProcedureCopay;
	}
	public Integer getPatientInsDetailLifetimemax() {
		return patientInsDetailLifetimemax;
	}
	public void setPatientInsDetailLifetimemax(Integer patientInsDetailLifetimemax) {
		this.patientInsDetailLifetimemax = patientInsDetailLifetimemax;
	}
	public Boolean getPatientInsDetailIsvfceligible() {
		return patientInsDetailIsvfceligible;
	}
	public void setPatientInsDetailIsvfceligible(
			Boolean patientInsDetailIsvfceligible) {
		this.patientInsDetailIsvfceligible = patientInsDetailIsvfceligible;
	}
	public Integer getPatientInsDetailFinClass() {
		return patientInsDetailFinClass;
	}
	public void setPatientInsDetailFinClass(Integer patientInsDetailFinClass) {
		this.patientInsDetailFinClass = patientInsDetailFinClass;
	}
	public Integer getPatientInsDetailMaxVisit() {
		return patientInsDetailMaxVisit;
	}
	public void setPatientInsDetailMaxVisit(Integer patientInsDetailMaxVisit) {
		this.patientInsDetailMaxVisit = patientInsDetailMaxVisit;
	}
	public String getPatientInsDetailProgramType() {
		return patientInsDetailProgramType;
	}
	public void setPatientInsDetailProgramType(String patientInsDetailProgramType) {
		this.patientInsDetailProgramType = patientInsDetailProgramType;
	}
	public Date getPatientInsDetailProgramStartdate() {
		return patientInsDetailProgramStartdate;
	}
	public void setPatientInsDetailProgramStartdate(
			Date patientInsDetailProgramStartdate) {
		this.patientInsDetailProgramStartdate = patientInsDetailProgramStartdate;
	}
	public Date getPatientInsDetailProgramEnddate() {
		return patientInsDetailProgramEnddate;
	}
	public void setPatientInsDetailProgramEnddate(
			Date patientInsDetailProgramEnddate) {
		this.patientInsDetailProgramEnddate = patientInsDetailProgramEnddate;
	}
	public String getInsCompanyName() {
		return insCompanyName;
	}
	public void setInsCompanyName(String insCompanyName) {
		this.insCompanyName = insCompanyName;
	}



}
