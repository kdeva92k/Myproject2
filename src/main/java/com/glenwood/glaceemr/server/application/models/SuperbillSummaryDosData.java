package com.glenwood.glaceemr.server.application.models;


public class SuperbillSummaryDosData {
  
	private Long serviceId;
	private Long patientId;
	private String submitStatus;
	private String cptCode;
	private Integer cptId;
	private String comments;
	private String mod1;
	private String dosfrom;
	private String dosto;
	private String dop;
	private String mod2;
	private Integer sdoctorid;
	private String sdoctor;
	private String bdoctor;
	private Integer bdoctorid;
	private Long authid;
	private String authno;
	private Integer referralid;
	private String referral;
	private String  pos;
	private Integer posid;
	private Integer units;
	private Double charges;
	private Double copay;
    private String dx1;
    private String dx2;
    private String dx3;
    private String dx4;
    private Long priinsid;
    
	public SuperbillSummaryDosData(Long serviceId,Long patientId,String submitStatus,String cptCode,Integer cptId,String comments,String mod1,
			String dosfrom,String dosto,String dop,String mod2,Integer sdoctorid,String sdoctor,String bdoctor,Integer bdoctorid,Long authid,
			String authno,Integer referralid,String referral,String  pos,Integer posid,Integer units,Double charges,
			Double copay,String dx1,String dx2,String dx3,String dx4,Long priinsid){
		this.serviceId=serviceId;
		this.patientId=patientId;
		this.submitStatus=submitStatus;
		this.cptCode=cptCode;
		this.cptId=cptId;
		this.comments=comments;
		this.mod1=mod1;
		this.dosfrom=dosfrom;
		this.dosto=dosto;
		this.dop=dop;
		this.mod2=mod2;
		this.sdoctorid=sdoctorid;
		this.sdoctor=sdoctor;
		this.bdoctor=bdoctor;
		this.bdoctorid=bdoctorid;
		this.authid=authid;
		this.authno=authno;
		this.referralid=referralid;
		this.referral=referral;
		this.pos=pos;
		this.posid=posid;
		this.units=units;
		this.charges=charges;
		this.copay=copay;
		this.dx1=dx1;
		this.dx2=dx2;
		this.dx3=dx3;
		this.dx4=dx4;
		this.priinsid=priinsid;
	}

	public String getComments() {
		return comments;
	}

	public String getMod1() {
		return mod1;
	}

	public String getDosfrom() {
		return dosfrom;
	}

	public String getDosto() {
		return dosto;
	}

	public String getDop() {
		return dop;
	}

	public String getMod2() {
		return mod2;
	}

	public String getSdoctorid() {
		return sdoctorid.toString();
	}

	public String getSdoctor() {
		return sdoctor;
	}

	public String getBdoctor() {
		return bdoctor;
	}

	public String getBdoctorid() {
		return bdoctorid.toString();
	}

	public String getAuthid() {
		return authid.toString();
	}

	public String getAuthno() {
		return authno;
	}

	public String getReferralid() {
		return referralid.toString();
	}

	public String getReferral() {
		return referral;
	}

	public String getPos() {
		return pos;
	}

	public String getPosid() {
		return posid.toString();
	}

	public String getUnits() {
		return units.toString();
	}

	public String getCharges() {
		return charges.toString();
	}

	public String getCopay() {
		return copay.toString();
	}

	public String getDx1() {
		return dx1;
	}

	public String getDx2() {
		return dx2;
	}

	public String getDx3() {
		return dx3;
	}

	public String getDx4() {
		return dx4;
	}

	public String getPriinsid() {
		return priinsid.toString();
	}

	public void setComments(String comments) {
		this.comments = comments;
	}

	public void setMod1(String mod1) {
		this.mod1 = mod1;
	}

	public void setDosfrom(String dosfrom) {
		this.dosfrom = dosfrom;
	}

	public void setDosto(String dosto) {
		this.dosto = dosto;
	}

	public void setDop(String dop) {
		this.dop = dop;
	}

	public void setMod2(String mod2) {
		this.mod2 = mod2;
	}

	public void setSdoctorid(Integer sdoctorid) {
		this.sdoctorid = sdoctorid;
	}

	public void setSdoctor(String sdoctor) {
		this.sdoctor = sdoctor;
	}

	public void setBdoctor(String bdoctor) {
		this.bdoctor = bdoctor;
	}

	public void setBdoctorid(Integer bdoctorid) {
		this.bdoctorid = bdoctorid;
	}

	public void setAuthid(Long authid) {
		this.authid = authid;
	}

	public void setAuthno(String authno) {
		this.authno = authno;
	}

	public void setReferralid(Integer referralid) {
		this.referralid = referralid;
	}

	public void setReferral(String referral) {
		this.referral = referral;
	}

	public void setPos(String pos) {
		this.pos = pos;
	}

	public void setPosid(Integer posid) {
		this.posid = posid;
	}

	public void setUnits(Integer units) {
		this.units = units;
	}

	public void setCharges(Double charges) {
		this.charges = charges;
	}

	public void setCopay(Double copay) {
		this.copay = copay;
	}

	public void setDx1(String dx1) {
		this.dx1 = dx1;
	}

	public void setDx2(String dx2) {
		this.dx2 = dx2;
	}

	public void setDx3(String dx3) {
		this.dx3 = dx3;
	}

	public void setDx4(String dx4) {
		this.dx4 = dx4;
	}

	public void setPriinsid(Long priinsid) {
		this.priinsid = priinsid;
	}

	public String getServiceId() {
		return serviceId.toString();
	}

	public String getPatientId() {
		return patientId.toString();
	}

	public String getSubmitStatus() {
		return submitStatus;
	}

	public String getCptCode() {
		return cptCode;
	}

	public String getCptId() {
		return cptId.toString();
	}

	public void setServiceId(Long serviceId) {
		this.serviceId = serviceId;
	}

	public void setPatientId(Long patientId) {
		this.patientId = patientId;
	}

	public void setSubmitStatus(String submitStatus) {
		this.submitStatus = submitStatus;
	}

	public void setCptCode(String cptCode) {
		this.cptCode = cptCode;
	}

	public void setCptId(Integer cptId) {
		this.cptId = cptId;
	}
	
	
	
		
}
	
	

