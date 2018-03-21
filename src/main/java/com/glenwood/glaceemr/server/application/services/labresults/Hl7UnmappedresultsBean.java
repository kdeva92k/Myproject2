package com.glenwood.glaceemr.server.application.services.labresults;

import java.util.Date;

public class Hl7UnmappedresultsBean {
	private Date hl7UnmappedresultsOrdDate;
	private String hl7UnmappedresultsOrdbyLastname;
	private String hl7UnmappedresultsOrdbyFirstname;
	private Integer hl7UnmappedresultsTestDetailId;
	private Integer hl7UnmappedresultsId;
	private Date hl7UnmappedresultsPerformeddate;
	private Integer hl7UnmappedresultsOrdbyDocid;
	private String hl7UnmappedresultsLabname;
	private String hl7UnmappedresultsResult;
	private String hl7UnmappedresultsMapStatus;
	private String hl7UnmappedresultsResultStatus;
	private Date hl7UnmappedresultsSpecimenCollectedDate;
	private String hl7UnmappedresultsSrcOfSpecimen;
	private String hl7UnmappedresultsCondOfSpecimen;
	private Integer hl7UnmappedresultsTestStatus;
	private Integer hl7UnmappedresultsPrelimStatus;
	private Integer hl7UnmappedresultsFinalStatus;
	private String hl7UnmappedresultsTestnotes;
	private String hl7UnmappedresultsSpecimenRejectreason;
	private String hl7UnmappedresultsResultscopiesto;
	private String hl7UnmappedresultsRelevantClinicalInfo;
	private Integer hl7UnmappedresultsPrelimtestId;
	private Integer hl7UnmappedresultsLabcompDetailid;
	private Integer hl7UnmappedresultsIspdf;
	private String hl7UnmappedresultsExtTestcode;
	private String hl7UnmappedresultsFilename;
	private String hl7UnmappedresultsRequisitionId;
	private Boolean hl7UnmappedresultsIsfasting;
	private String hl7UnmappedresultsCollectionVolume;
	private Integer hl7UnmappedresultsFilewiseId;
	
	
	public Hl7UnmappedresultsBean(String hl7UnmappedresultsOrdbyFirstname,String hl7UnmappedresultsOrdbyLastname,
			Date hl7UnmappedresultsOrdDate,Integer hl7UnmappedresultsTestDetailId,Integer hl7UnmappedresultsId,
			Date hl7UnmappedresultsPerformeddate,Integer hl7UnmappedresultsOrdbyDocid, String hl7UnmappedresultsLabname,
			String hl7UnmappedresultsResult,String hl7UnmappedresultsMapStatus,
			String hl7UnmappedresultsResultStatus,Date hl7UnmappedresultsSpecimenCollectedDate,String hl7UnmappedresultsSrcOfSpecimen,
			String hl7UnmappedresultsCondOfSpecimen,Integer hl7UnmappedresultsTestStatus,Integer hl7UnmappedresultsPrelimStatus,
			Integer hl7UnmappedresultsFinalStatus,String hl7UnmappedresultsTestnotes,String hl7UnmappedresultsSpecimenRejectreason,
			String hl7UnmappedresultsResultscopiesto,String hl7UnmappedresultsRelevantClinicalInfo,Integer hl7UnmappedresultsPrelimtestId,
			Integer hl7UnmappedresultsLabcompDetailid,Integer hl7UnmappedresultsIspdf,String hl7UnmappedresultsExtTestcode,String hl7UnmappedresultsFilename,
			String hl7UnmappedresultsRequisitionId,Boolean hl7UnmappedresultsIsfasting,String hl7UnmappedresultsCollectionVolume,
			Integer hl7UnmappedresultsFilewiseId) {
		super();
		this.hl7UnmappedresultsOrdDate = hl7UnmappedresultsOrdDate;
		this.hl7UnmappedresultsOrdbyLastname = hl7UnmappedresultsOrdbyLastname;
		this.hl7UnmappedresultsOrdbyFirstname = hl7UnmappedresultsOrdbyFirstname;
		this.hl7UnmappedresultsTestDetailId=hl7UnmappedresultsTestDetailId;
		this.hl7UnmappedresultsId=hl7UnmappedresultsId;
		this.hl7UnmappedresultsOrdbyDocid=hl7UnmappedresultsOrdbyDocid;
		this.hl7UnmappedresultsLabname=hl7UnmappedresultsLabname;
		this.hl7UnmappedresultsResult=hl7UnmappedresultsResult;
		this.hl7UnmappedresultsMapStatus=hl7UnmappedresultsMapStatus;
		this.hl7UnmappedresultsResultStatus=hl7UnmappedresultsResultStatus;
		this.hl7UnmappedresultsSpecimenCollectedDate=hl7UnmappedresultsSpecimenCollectedDate;
		this.hl7UnmappedresultsSrcOfSpecimen=hl7UnmappedresultsSrcOfSpecimen;
		this.hl7UnmappedresultsCondOfSpecimen=hl7UnmappedresultsCondOfSpecimen;
		this.hl7UnmappedresultsTestStatus=hl7UnmappedresultsTestStatus;
		this.hl7UnmappedresultsPrelimStatus=hl7UnmappedresultsPrelimStatus;
		this.hl7UnmappedresultsFinalStatus=hl7UnmappedresultsFinalStatus;
		this.hl7UnmappedresultsTestnotes=hl7UnmappedresultsTestnotes;
		this.hl7UnmappedresultsSpecimenRejectreason=hl7UnmappedresultsSpecimenRejectreason;
		this.hl7UnmappedresultsResultscopiesto=hl7UnmappedresultsResultscopiesto;
		this.hl7UnmappedresultsRelevantClinicalInfo=hl7UnmappedresultsRelevantClinicalInfo;
		this.hl7UnmappedresultsPrelimtestId=hl7UnmappedresultsPrelimtestId;
		this.hl7UnmappedresultsLabcompDetailid=hl7UnmappedresultsLabcompDetailid;
		this.hl7UnmappedresultsIspdf=hl7UnmappedresultsIspdf;
		this.hl7UnmappedresultsExtTestcode=hl7UnmappedresultsExtTestcode;
		this.hl7UnmappedresultsFilename=hl7UnmappedresultsFilename;
		this.hl7UnmappedresultsRequisitionId=hl7UnmappedresultsRequisitionId;
		this.hl7UnmappedresultsIsfasting=hl7UnmappedresultsIsfasting;
		this.hl7UnmappedresultsCollectionVolume=hl7UnmappedresultsCollectionVolume;
		this.hl7UnmappedresultsFilewiseId=hl7UnmappedresultsFilewiseId;
	}
	public Hl7UnmappedresultsBean(String hl7UnmappedresultsOrdbyFirstname,String hl7UnmappedresultsOrdbyLastname,
			Date hl7UnmappedresultsOrdDate,Integer hl7UnmappedresultsTestDetailId){
		super();
		this.hl7UnmappedresultsOrdDate = hl7UnmappedresultsOrdDate;
		this.hl7UnmappedresultsOrdbyLastname = hl7UnmappedresultsOrdbyLastname;
		this.hl7UnmappedresultsOrdbyFirstname = hl7UnmappedresultsOrdbyFirstname;
		this.hl7UnmappedresultsTestDetailId=hl7UnmappedresultsTestDetailId;
	}
	public Date getHl7UnmappedresultsOrdDate() {
		return hl7UnmappedresultsOrdDate;
	}
	public void setHl7UnmappedresultsOrdDate(Date hl7UnmappedresultsOrdDate) {
		this.hl7UnmappedresultsOrdDate = hl7UnmappedresultsOrdDate;
	}
	public String getHl7UnmappedresultsOrdbyLastname() {
		return hl7UnmappedresultsOrdbyLastname;
	}
	public void setHl7UnmappedresultsOrdbyLastname(
			String hl7UnmappedresultsOrdbyLastname) {
		this.hl7UnmappedresultsOrdbyLastname = hl7UnmappedresultsOrdbyLastname;
	}
	public String getHl7UnmappedresultsOrdbyFirstname() {
		return hl7UnmappedresultsOrdbyFirstname;
	}
	public void setHl7UnmappedresultsOrdbyFirstname(
			String hl7UnmappedresultsOrdbyFirstname) {
		this.hl7UnmappedresultsOrdbyFirstname = hl7UnmappedresultsOrdbyFirstname;
	}
	public Integer getHl7UnmappedresultsTestDetailId() {
		return hl7UnmappedresultsTestDetailId;
	}
	public void setHl7UnmappedresultsTestDetailId(
			Integer hl7UnmappedresultsTestDetailId) {
		this.hl7UnmappedresultsTestDetailId = hl7UnmappedresultsTestDetailId;
	}
	public Integer getHl7UnmappedresultsId() {
		return hl7UnmappedresultsId;
	}
	public void setHl7UnmappedresultsId(Integer hl7UnmappedresultsId) {
		this.hl7UnmappedresultsId = hl7UnmappedresultsId;
	}
	public Date getHl7UnmappedresultsPerformeddate() {
		return hl7UnmappedresultsPerformeddate;
	}
	public void setHl7UnmappedresultsPerformeddate(
			Date hl7UnmappedresultsPerformeddate) {
		this.hl7UnmappedresultsPerformeddate = hl7UnmappedresultsPerformeddate;
	}	
	public Integer getHl7UnmappedresultsOrdbyDocid() {
		return hl7UnmappedresultsOrdbyDocid;
	}
	public void setHl7UnmappedresultsOrdbyDocid(Integer hl7UnmappedresultsOrdbyDocid) {
		this.hl7UnmappedresultsOrdbyDocid = hl7UnmappedresultsOrdbyDocid;
	}
	public String getHl7UnmappedresultsLabname() {
		return hl7UnmappedresultsLabname;
	}

	public void setHl7UnmappedresultsLabname(String hl7UnmappedresultsLabname) {
		this.hl7UnmappedresultsLabname = hl7UnmappedresultsLabname;
	}
	public String getHl7UnmappedresultsResult() {
		return hl7UnmappedresultsResult;
	}

	public void setHl7UnmappedresultsResult(String hl7UnmappedresultsResult) {
		this.hl7UnmappedresultsResult = hl7UnmappedresultsResult;
	}
	public String getHl7UnmappedresultsMapStatus() {
		return hl7UnmappedresultsMapStatus;
	}

	public void setHl7UnmappedresultsMapStatus(String hl7UnmappedresultsMapStatus) {
		this.hl7UnmappedresultsMapStatus = hl7UnmappedresultsMapStatus;
	}
	public String getHl7UnmappedresultsResultStatus() {
		return hl7UnmappedresultsResultStatus;
	}

	public void setHl7UnmappedresultsResultStatus(
			String hl7UnmappedresultsResultStatus) {
		this.hl7UnmappedresultsResultStatus = hl7UnmappedresultsResultStatus;
	}
	
	public Date getHl7UnmappedresultsSpecimenCollectedDate() {
		return hl7UnmappedresultsSpecimenCollectedDate;
	}

	public void setHl7UnmappedresultsSpecimenCollectedDate(
			Date hl7UnmappedresultsSpecimenCollectedDate) {
		this.hl7UnmappedresultsSpecimenCollectedDate = hl7UnmappedresultsSpecimenCollectedDate;
	}
	public String getHl7UnmappedresultsSrcOfSpecimen() {
		return hl7UnmappedresultsSrcOfSpecimen;
	}

	public void setHl7UnmappedresultsSrcOfSpecimen(
			String hl7UnmappedresultsSrcOfSpecimen) {
		this.hl7UnmappedresultsSrcOfSpecimen = hl7UnmappedresultsSrcOfSpecimen;
	}
	public String getHl7UnmappedresultsCondOfSpecimen() {
		return hl7UnmappedresultsCondOfSpecimen;
	}

	public void setHl7UnmappedresultsCondOfSpecimen(
			String hl7UnmappedresultsCondOfSpecimen) {
		this.hl7UnmappedresultsCondOfSpecimen = hl7UnmappedresultsCondOfSpecimen;
	}
	public Integer getHl7UnmappedresultsTestStatus() {
		return hl7UnmappedresultsTestStatus;
	}

	public void setHl7UnmappedresultsTestStatus(Integer hl7UnmappedresultsTestStatus) {
		this.hl7UnmappedresultsTestStatus = hl7UnmappedresultsTestStatus;
	}
	public Integer getHl7UnmappedresultsPrelimStatus() {
		return hl7UnmappedresultsPrelimStatus;
	}

	public void setHl7UnmappedresultsPrelimStatus(
			Integer hl7UnmappedresultsPrelimStatus) {
		this.hl7UnmappedresultsPrelimStatus = hl7UnmappedresultsPrelimStatus;
	}
	public Integer getHl7UnmappedresultsFinalStatus() {
		return hl7UnmappedresultsFinalStatus;
	}

	public void setHl7UnmappedresultsFinalStatus(
			Integer hl7UnmappedresultsFinalStatus) {
		this.hl7UnmappedresultsFinalStatus = hl7UnmappedresultsFinalStatus;
	}
   
	public String getHl7UnmappedresultsTestnotes() {
		return hl7UnmappedresultsTestnotes;
	}

	public void setHl7UnmappedresultsTestnotes(String hl7UnmappedresultsTestnotes) {
		this.hl7UnmappedresultsTestnotes = hl7UnmappedresultsTestnotes;
	}
	public String getHl7UnmappedresultsSpecimenRejectreason() {
		return hl7UnmappedresultsSpecimenRejectreason;
	}

	public void setHl7UnmappedresultsSpecimenRejectreason(
			String hl7UnmappedresultsSpecimenRejectreason) {
		this.hl7UnmappedresultsSpecimenRejectreason = hl7UnmappedresultsSpecimenRejectreason;
	}
	public String getHl7UnmappedresultsResultscopiesto() {
		return hl7UnmappedresultsResultscopiesto;
	}

	public void setHl7UnmappedresultsResultscopiesto(
			String hl7UnmappedresultsResultscopiesto) {
		this.hl7UnmappedresultsResultscopiesto = hl7UnmappedresultsResultscopiesto;
	}
	public String getHl7UnmappedresultsRelevantClinicalInfo() {
		return hl7UnmappedresultsRelevantClinicalInfo;
	}

	public void setHl7UnmappedresultsRelevantClinicalInfo(
			String hl7UnmappedresultsRelevantClinicalInfo) {
		this.hl7UnmappedresultsRelevantClinicalInfo = hl7UnmappedresultsRelevantClinicalInfo;
	}
	public Integer getHl7UnmappedresultsPrelimtestId() {
		return hl7UnmappedresultsPrelimtestId;
	}

	public void setHl7UnmappedresultsPrelimtestId(
			Integer hl7UnmappedresultsPrelimtestId) {
		this.hl7UnmappedresultsPrelimtestId = hl7UnmappedresultsPrelimtestId;
	}
	public Integer getHl7UnmappedresultsLabcompDetailid() {
		return hl7UnmappedresultsLabcompDetailid;
	}

	public void setHl7UnmappedresultsLabcompDetailid(
			Integer hl7UnmappedresultsLabcompDetailid) {
		this.hl7UnmappedresultsLabcompDetailid = hl7UnmappedresultsLabcompDetailid;
	}
	public Integer getHl7UnmappedresultsIspdf() {
		return hl7UnmappedresultsIspdf;
	}

	public void setHl7UnmappedresultsIspdf(Integer hl7UnmappedresultsIspdf) {
		this.hl7UnmappedresultsIspdf = hl7UnmappedresultsIspdf;
	}
	public String getHl7UnmappedresultsExtTestcode() {
		return hl7UnmappedresultsExtTestcode;
	}
	public void setHl7UnmappedresultsExtTestcode(
			String hl7UnmappedresultsExtTestcode) {
		this.hl7UnmappedresultsExtTestcode = hl7UnmappedresultsExtTestcode;
	}
	public String getHl7UnmappedresultsFilename() {
		return hl7UnmappedresultsFilename;
	}
	public void setHl7UnmappedresultsFilename(String hl7UnmappedresultsFilename) {
		this.hl7UnmappedresultsFilename = hl7UnmappedresultsFilename;
	}
	public String getHl7UnmappedresultsRequisitionId() {
		return hl7UnmappedresultsRequisitionId;
	}
	public void setHl7UnmappedresultsRequisitionId(
			String hl7UnmappedresultsRequisitionId) {
		this.hl7UnmappedresultsRequisitionId = hl7UnmappedresultsRequisitionId;
	}
	public Boolean getHl7UnmappedresultsIsfasting() {
		return hl7UnmappedresultsIsfasting;
	}
	public void setHl7UnmappedresultsIsfasting(Boolean hl7UnmappedresultsIsfasting) {
		this.hl7UnmappedresultsIsfasting = hl7UnmappedresultsIsfasting;
	}
	public String getHl7UnmappedresultsCollectionVolume() {
		return hl7UnmappedresultsCollectionVolume;
	}
	public void setHl7UnmappedresultsCollectionVolume(
			String hl7UnmappedresultsCollectionVolume) {
		this.hl7UnmappedresultsCollectionVolume = hl7UnmappedresultsCollectionVolume;
	}
	public Integer getHl7UnmappedresultsFilewiseId() {
		return hl7UnmappedresultsFilewiseId;
	}
	public void setHl7UnmappedresultsFilewiseId(Integer hl7UnmappedresultsFilewiseId) {
		this.hl7UnmappedresultsFilewiseId = hl7UnmappedresultsFilewiseId;
	}
}