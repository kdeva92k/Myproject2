package com.glenwood.glaceemr.server.application.models;

import java.sql.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;


import org.hibernate.annotations.JoinColumnOrFormula;
import org.hibernate.annotations.JoinColumnsOrFormulas;
import org.hibernate.annotations.JoinFormula;
import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.glenwood.glaceemr.server.utils.JsonTimestampSerializer;

@Entity
@Table(name = "generated_bills_history_details")
public class GeneratedBillsHistoryDetails {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "generated_bills_history_details_billid_seq")
	@SequenceGenerator(name = "generated_bills_history_details_billid_seq", sequenceName = "generated_bills_history_details_billid_seq", allocationSize = 1)
	@Column(name="generated_bills_history_details_billid")
	private Integer generated_bills_history_details_billid;

	@Column(name="generated_bills_history_details_billrefno")
	private String generated_bills_history_details_billrefno;

	@Column(name="generated_bills_history_details_current_genetateddate")
	private Date generatedDate;

	@Column(name="generated_bills_history_details_patientid")
	private Long generated_bills_history_details_patientid;

	@Column(name="generated_bills_history_details_cptcode")
	private String generated_bills_history_details_cptcode;

	@Column(name="generated_bills_history_details_user")
	private String generated_bills_history_details_user;

	@Column(name="generated_bills_history_details_totalbalance")
	private Double generated_bills_history_details_totalbalance;

	@Column(name="h555555")
	private Integer h555555;

	@Column(name="generated_bills_history_details_serviceid")
	private Long generated_bills_history_details_serviceid;

	@Column(name="generated_bills_history_details_billingreason")
	private String generated_bills_history_details_billingreason;

	@Column(name="generated_bills_history_details_lastbilleddate")
	private Date generated_bills_history_details_lastbilleddate;

	@Column(name="generated_bills_history_details_unknown1")
	private Date generated_bills_history_details_unknown1;

	@Column(name="generated_bills_history_details_unknown2")
	private String generated_bills_history_details_unknown2;

	@Column(name="ens_batch_no")
	private Integer ensBatchNo;

	@Column(name="mailed_status")
	private Integer mailedStatus;
	
	@Column(name="file_path")
	private String filePath;
	
	@NotFound(action=NotFoundAction.IGNORE)
	@ManyToOne(cascade=CascadeType.ALL, fetch=FetchType.LAZY)
	@JoinColumnsOrFormulas({ @JoinColumnOrFormula(formula = @JoinFormula(value = "generated_bills_history_details_cptcode::integer", referencedColumnName = "bill_status_scpt_id")) })
	@JsonManagedReference
	private BillStatus bill_status;
	
	@NotFound(action=NotFoundAction.IGNORE)
	@ManyToOne(cascade=CascadeType.ALL, fetch=FetchType.LAZY)
	@JoinColumn(name="ens_batch_no", referencedColumnName="batchno", updatable=false, insertable=false)
	@JsonManagedReference
	private EnsBillsDetails ensBillsDetails;
	
	@NotFound(action=NotFoundAction.IGNORE)
	@ManyToOne(cascade=CascadeType.ALL, fetch=FetchType.LAZY)
	@JoinColumn(name="generated_bills_history_details_patientid", referencedColumnName="patient_registration_id", updatable=false, insertable=false)
	@JsonManagedReference
	private PatientRegistration patientRegistrationDetails;


	public Integer getgenerated_bills_history_details_billid() {
		return generated_bills_history_details_billid;
	}

	public void setgenerated_bills_history_details_billid(Integer generated_bills_history_details_billid) {
		this.generated_bills_history_details_billid = generated_bills_history_details_billid;
	}

	public String getgenerated_bills_history_details_billrefno() {
		return generated_bills_history_details_billrefno;
	}

	public void setgenerated_bills_history_details_billrefno(String generated_bills_history_details_billrefno) {
		this.generated_bills_history_details_billrefno = generated_bills_history_details_billrefno;
	}

	
	public Date getGenerated_bills_history_details_current_genetateddate() {
		return generatedDate;
	}

	public void setGenerated_bills_history_details_current_genetateddate(
			Date generated_bills_history_details_current_genetateddate) {
		this.generatedDate = generated_bills_history_details_current_genetateddate;
	}

	public Date getgenerated_bills_history_details_current_genetateddate() {
		return generatedDate;
	}

	public void setgenerated_bills_history_details_current_genetateddate(Date generated_bills_history_details_current_genetateddate) {
		this.generatedDate = generated_bills_history_details_current_genetateddate;
	}

	public Long getgenerated_bills_history_details_patientid() {
		return generated_bills_history_details_patientid;
	}

	public void setgenerated_bills_history_details_patientid(Long generated_bills_history_details_patientid) {
		this.generated_bills_history_details_patientid = generated_bills_history_details_patientid;
	}

	public String getgenerated_bills_history_details_cptcode() {
		return generated_bills_history_details_cptcode;
	}

	public void setgenerated_bills_history_details_cptcode(String generated_bills_history_details_cptcode) {
		this.generated_bills_history_details_cptcode = generated_bills_history_details_cptcode;
	}

	public String getgenerated_bills_history_details_user() {
		return generated_bills_history_details_user;
	}

	public void setgenerated_bills_history_details_user(String generated_bills_history_details_user) {
		this.generated_bills_history_details_user = generated_bills_history_details_user;
	}

	public Double getgenerated_bills_history_details_totalbalance() {
		return generated_bills_history_details_totalbalance;
	}

	public void setgenerated_bills_history_details_totalbalance(Double generated_bills_history_details_totalbalance) {
		this.generated_bills_history_details_totalbalance = generated_bills_history_details_totalbalance;
	}

	public Integer getH555555() {
		return h555555;
	}

	public void setH555555(Integer h555555) {
		this.h555555 = h555555;
	}

	public Long getgenerated_bills_history_details_serviceid() {
		return generated_bills_history_details_serviceid;
	}

	public void setgenerated_bills_history_details_serviceid(Long generated_bills_history_details_serviceid) {
		this.generated_bills_history_details_serviceid = generated_bills_history_details_serviceid;
	}

	public String getgenerated_bills_history_details_billingreason() {
		return generated_bills_history_details_billingreason;
	}

	public void setgenerated_bills_history_details_billingreason(String generated_bills_history_details_billingreason) {
		this.generated_bills_history_details_billingreason = generated_bills_history_details_billingreason;
	}

	public Date getgenerated_bills_history_details_lastbilleddate() {
		return generated_bills_history_details_lastbilleddate;
	}

	public void setgenerated_bills_history_details_lastbilleddate(Date generated_bills_history_details_lastbilleddate) {
		this.generated_bills_history_details_lastbilleddate = generated_bills_history_details_lastbilleddate;
	}

	public Date getgenerated_bills_history_details_unknown1() {
		return generated_bills_history_details_unknown1;
	}

	public void setgenerated_bills_history_details_unknown1(Date generated_bills_history_details_unknown1) {
		this.generated_bills_history_details_unknown1 = generated_bills_history_details_unknown1;
	}

	public String getgenerated_bills_history_details_unknown2() {
		return generated_bills_history_details_unknown2;
	}

	public void setgenerated_bills_history_details_unknown2(String generated_bills_history_details_unknown2) {
		this.generated_bills_history_details_unknown2 = generated_bills_history_details_unknown2;
	}

	public Integer getEnsBatchNo() {
		return ensBatchNo;
	}

	public void setEnsBatchNo(Integer ensBatchNo) {
		this.ensBatchNo = ensBatchNo;
	}

	public Integer getMailedStatus() {
		return mailedStatus;
	}

	public void setMailedStatus(Integer mailedStatus) {
		this.mailedStatus = mailedStatus;
	}

	public String getFilePath() {
		return filePath;
	}

	public void setFilePath(String filePath) {
		this.filePath = filePath;
	}

	public BillStatus getbill_status() {
		return bill_status;
	}

	public void setbill_status(BillStatus bill_status) {
		this.bill_status = bill_status;
	}

	public EnsBillsDetails getEnsBillsDetails() {
		return ensBillsDetails;
	}

	public void setEnsBillsDetails(EnsBillsDetails ensBillsDetails) {
		this.ensBillsDetails = ensBillsDetails;
	}

	public PatientRegistration getPatientRegistrationDetails() {
		return patientRegistrationDetails;
	}

	public void setPatientRegistrationDetails(
			PatientRegistration patientRegistrationDetails) {
		this.patientRegistrationDetails = patientRegistrationDetails;
	}
	
}