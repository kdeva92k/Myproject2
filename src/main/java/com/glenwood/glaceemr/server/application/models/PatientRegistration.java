package com.glenwood.glaceemr.server.application.models;


import java.io.Serializable;
import java.sql.Date;
import java.sql.Timestamp;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;
import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.glenwood.glaceemr.server.utils.JsonTimestampSerializer;

@SuppressWarnings("serial")
@Entity
@Table(name = "patient_registration")
public class PatientRegistration implements Serializable {

	public PatientRegistration(String patientRegistrationLastName, String patientRegistrationMidInitial, String patientRegistrationFirstName, String patientRegistrationAccountno, String patientRegistrationDob, Integer patientRegistrationSex, String patientRegistrationAddress1, String patientRegistrationAddress2, String patientRegistrationCity, String patientRegistrationState, String patientRegistrationZip, String patientRegistrationPhoneNo, String patientRegistrationWorkNo, Integer patientRegistrationPosId, Integer patientRegistrationReferingPhysician, Integer patientRegistrationPrincipalDoctor, String patientRegistrationCellno, String patientRegistrationEthnicity, String patientRegistrationRace, String patientRegistrationPreferredLan){
		super();
		this.patientRegistrationLastName=  patientRegistrationLastName;
		this.patientRegistrationMidInitial= patientRegistrationMidInitial; 
		this.patientRegistrationFirstName= patientRegistrationFirstName; 
		this.patientRegistrationAccountno= patientRegistrationAccountno; 
		this.patientRegistrationSpoketo= patientRegistrationDob; 
		this.patientRegistrationSex= patientRegistrationSex; 
		this.patientRegistrationAddress1= patientRegistrationAddress1; 
		this.patientRegistrationAddress2= patientRegistrationAddress2;
		this.patientRegistrationCity= patientRegistrationCity ;
		this.patientRegistrationState= patientRegistrationState; 
		this.patientRegistrationZip= patientRegistrationZip; 
		this.patientRegistrationPhoneNo= patientRegistrationPhoneNo; 
		this.patientRegistrationWorkNo= patientRegistrationWorkNo; 
		this.patientRegistrationPosId= patientRegistrationPosId; 
		this.patientRegistrationReferingPhysician= patientRegistrationReferingPhysician; 
		this.patientRegistrationPrincipalDoctor= patientRegistrationPrincipalDoctor; 										
		this.patientRegistrationCellno= patientRegistrationCellno; 
		this.patientRegistrationEthnicity= patientRegistrationEthnicity; 
		this.patientRegistrationRace= patientRegistrationRace; 
		this.patientRegistrationPreferredLan= patientRegistrationPreferredLan;
	}
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "patient_registration_patient_registration_id_seq")
	@SequenceGenerator(name = "patient_registration_patient_registration_id_seq", sequenceName = "patient_registration_patient_registration_id_seq", allocationSize = 1)
	@Column(name="patient_registration_id")
	private Integer patientRegistrationId;

	@Column(name="patient_registration_last_name")
	private String patientRegistrationLastName;

	@Column(name="patient_registration_mid_initial")
	private String patientRegistrationMidInitial;

	@Column(name="patient_registration_first_name")
	private String patientRegistrationFirstName;

	@Column(name="patient_registration_accountno")
	private String patientRegistrationAccountno;

	@Column(name="patient_registration_ssn")
	private String patientRegistrationSsn;

	@Column(name="patient_registration_dob")
	private Date patientRegistrationDob;

	@Column(name="patient_registration_sex")
	private Integer patientRegistrationSex;

	@Column(name="patient_registration_maritalstatus")
	private Integer patientRegistrationMaritalstatus;

	@Column(name="patient_registration_address1")
	private String patientRegistrationAddress1;

	@Column(name="patient_registration_address2")
	private String patientRegistrationAddress2;

	@Column(name="patient_registration_city")
	private String patientRegistrationCity;

	@Column(name="patient_registration_state")
	private String patientRegistrationState;

	@Column(name="patient_registration_zip")
	private String patientRegistrationZip;

	@Column(name="patient_registration_phone_no")
	private String patientRegistrationPhoneNo;

	@Column(name="patient_registration_work_no")
	private String patientRegistrationWorkNo;

	@Column(name="patient_registration_active")
	private Boolean patientRegistrationActive;

	@Column(name="patient_registration_dxdate")
	private Date patientRegistrationDxdate;

	@Column(name="patient_registration_cur_dx_date")
	private Date patientRegistrationCurDxDate;

	@Column(name="patient_registration_how_intro")
	private Integer patientRegistrationHowIntro;

	@Column(name="patient_registration_pos_id")
	private Integer patientRegistrationPosId;

	@Column(name="patient_registration_father_name")
	private String patientRegistrationFatherName;

	@Column(name="patient_registration_mother_name")
	private String patientRegistrationMotherName;

	@Column(name="patient_registration_tif_reference")
	private String patientRegistrationTifReference;

	@Column(name="patient_registration_tmpstatus")
	private String patientRegistrationTmpstatus;

	@Column(name="patient_registration_billingstatus")
	private String patientRegistrationBillingstatus;

	@Column(name="patient_registration_billinggroup")
	private Integer patientRegistrationBillinggroup;

	@Column(name="patient_registration_basediagnosis")
	private String patientRegistrationBasediagnosis;

	@Column(name="patient_registration_billingreasonid")
	private Integer patientRegistrationBillingreasonid;

	@Column(name="patient_registration_totaldue")
	private Double patientRegistrationTotaldue;

	@Column(name="patient_registration_refering_physician")
	private Integer patientRegistrationReferingPhysician;

	@Column(name="patient_registration_principal_doctor")
	private Integer patientRegistrationPrincipalDoctor;

	@Column(name="patient_registration_therapist")
	private Integer patientRegistrationTherapist;

	@Column(name="patient_registration_unalloccash")
	private Double patientRegistrationUnalloccash;

	@Column(name="patient_registration_mdcopay")
	private Double patientRegistrationMdcopay;

	@Column(name="patient_registration_therapistcopay")
	private Double patientRegistrationTherapistcopay;

	@Column(name="patient_registration_copay")
	private Double patientRegistrationCopay;

	@Column(name="patient_registration_family_phy")
	private String patientRegistrationFamilyPhy;

	@Column(name="patient_registration_notes")
	private String patientRegistrationNotes;

	@Column(name="patient_registration_accttype")
	private Integer patientRegistrationAccttype;

	@Column(name="patient_registration_employer")
	private String patientRegistrationEmployer;

	@Column(name="patient_registration_sublux_region")
	private String patientRegistrationSubluxRegion;

	@Column(name="patient_registration_datefirstseen")
	private Date patientRegistrationDatefirstseen;

	@Column(name="patient_registration_recalldate")
	private Date patientRegistrationRecalldate;

	@Column(name="patient_registration_xray_date")
	private Date patientRegistrationXrayDate;

	@Column(name="patient_registration_hosp_date_from")
	private Date patientRegistrationHospDateFrom;

	@Column(name="patient_registration_hosp_date_to")
	private Date patientRegistrationHospDateTo;

	@Column(name="patient_registration_recall_letter")
	private Boolean patientRegistrationRecallLetter;

	@Column(name="patient_registration_recallphone")
	private Boolean patientRegistrationRecallphone;

	@Column(name="patient_registration_leavemsg")
	private Boolean patientRegistrationLeavemsg;

	@Column(name="patient_registration_tenweeks")
	private Boolean patientRegistrationTenweeks;

	@Column(name="patient_registration_assigned")
	private Boolean patientRegistrationAssigned;

	@Column(name="patient_registration_lop")
	private Boolean patientRegistrationLop;

	@Column(name="patient_registration_patient_alert")
	private String patientRegistrationPatientAlert;

	@Column(name="patient_registration_guardian")
	private String patientRegistrationGuardian;

	@Column(name="patient_registration_reln")
	private String patientRegistrationReln;

	@Column(name="patient_registration_curr_auth_no")
	private String patientRegistrationCurrAuthNo;

	@Column(name="patient_registration_forlocaluse")
	private String patientRegistrationForlocaluse;

	@Column(name="patient_registration_lastcpt")
	private String patientRegistrationLastcpt;

	@Column(name="patient_registration_econtactperson")
	private String patientRegistrationEcontactperson;

	@Column(name="patient_registration_erelationship")
	private Integer patientRegistrationErelationship;

	@Column(name="patient_registration_eaddress")
	private String patientRegistrationEaddress;

	@Column(name="patient_registration_ecity")
	private String patientRegistrationEcity;

	@Column(name="patient_registration_estate")
	private String patientRegistrationEstate;

	@Column(name="patient_registration_ezip")
	private String patientRegistrationEzip;

	@Column(name="patient_registration_ephoneno")
	private String patientRegistrationEphoneno;

	@Column(name="patient_registration_auth_valid_upto")
	private Date patientRegistrationAuthValidUpto;

	@Column(name="patient_registration_total_auth_visits")
	private Integer patientRegistrationTotalAuthVisits;

	@Column(name="patient_registration_allergies")
	private String patientRegistrationAllergies;

	@Column(name="patient_registration_siblings_ages")
	private String patientRegistrationSiblingsAges;

	@Column(name="patient_registration_mail_id")
	private String patientRegistrationMailId;

	@Column(name="patient_registration_cellno")
	private String patientRegistrationCellno;

	@Column(name="patient_registration_driverlicenseno")
	private String patientRegistrationDriverlicenseno;

	@Column(name="patient_registration_faxno")
	private String patientRegistrationFaxno;

	@Column(name="patient_registration_insexcess")
	private Double patientRegistrationInsexcess;

	@Column(name="patient_registration_notesoftheday")
	private String patientRegistrationNotesoftheday;

	@Column(name="patient_registration_reason_pt_bill")
	private Integer patientRegistrationReasonPtBill;

	@Column(name="patient_registration_cur_visit_no")
	private Integer patientRegistrationCurVisitNo;

	@Column(name="patient_registration_referredby")
	private Long patientRegistrationReferredby;

	@Column(name="patient_registration_customfield1")
	private String patientRegistrationCustomfield1;

	@Column(name="patient_registration_extrafld_xml")
	private String patientRegistrationExtrafldXml;

	@Column(name="patient_registration_neverschedule")
	private Boolean patientRegistrationNeverschedule;

	@Column(name="patient_registration_chart_status")
	private Integer patientRegistrationChartStatus;

	@Column(name="patient_registration_app_type")
	private Integer patientRegistrationAppType;

	@Column(name="patient_registration_chartno")
	private String patientRegistrationChartno;

	@Column(name="patient_registration_specialdx")
	private String patientRegistrationSpecialdx;

	@Column(name="patient_registration_login_id")
	private Integer patientRegistrationLoginId;

	@Column(name="patient_registration_isapproved")
	private Boolean patientRegistrationIsapproved;

	@Column(name="patient_registration_approved_by")
	private Integer patientRegistrationApprovedBy;

	@Column(name="patient_registration_last_modified_by")
	private Integer patientRegistrationLastModifiedBy;

	@JsonSerialize(using = JsonTimestampSerializer.class)
	@Column(name="patient_registration_last_modified_date")
	private Timestamp patientRegistrationLastModifiedDate;

	@Column(name="patient_registration_log")
	private String patientRegistrationLog;

	@Column(name="patient_registration_temp1")
	private String patientRegistrationTemp1;

	@Column(name="patient_registration_oldservicetotaldue")
	private Double patientRegistrationOldservicetotaldue;

	@Column(name="patient_registration_financially_responisible")
	private String patientRegistrationFinanciallyResponisible;

	@Column(name="patient_registration_datelastbilled")
	private Date patientRegistrationDatelastbilled;

	@Column(name="h555555")
	private Integer h555555;

	@Column(name="patient_registration_unknown1")
	private String patientRegistrationUnknown1;

	@Column(name="patient_registration_unknown2")
	private Boolean patientRegistrationUnknown2;

	@Column(name="patient_registration_unknown3")
	private Boolean patientRegistrationUnknown3;

	@Column(name="patient_registration_costplan")
	private Integer patientRegistrationCostplan;

	@Column(name="patient_registration_unknown4")
	private String patientRegistrationUnknown4;

	@Column(name="patient_registration_unknown7")
	private Integer patientRegistrationUnknown7;

	@Column(name="patient_registration_guarantorid")
	private Integer patientRegistrationGuarantorid;

	@Column(name="patient_registration_guarantorrel")
	private Integer patientRegistrationGuarantorrel;

	@Column(name="patient_registration_status")
	private Integer patientRegistrationStatus;

	@Column(name="version")
	private Integer version;

	@Column(name="ruleversion")
	private Integer ruleversion;

	@Column(name="valid")
	private Boolean valid;

	@Column(name="patient_registration_placed_on")
	private Date patientRegistrationPlacedOn;

	@Column(name="patient_registration_placed_by")
	private String patientRegistrationPlacedBy;

	@Column(name="patient_registration_unknown8")
	private Double patientRegistrationUnknown8;

	@Column(name="patient_registration_plan")
	private Integer patientRegistrationPlan;

	@Column(name="patient_registration_pharmacy_id")
	private Integer patientRegistrationPharmacyId;

	@Column(name="special_patient_type")
	private Integer specialPatientType;

	@Column(name="isadmin")
	private Boolean isadmin;

	@Column(name="patient_registration_unknown5")
	private Integer patientRegistrationUnknown5;

	@Column(name="patient_registration_unknown6")
	private Integer patientRegistrationUnknown6;

	@Column(name="patient_registration_photoid")
	private Boolean patientRegistrationPhotoid;

	@Column(name="patient_registration_new_patient_alert")
	private String patientRegistrationNewPatientAlert;

	@Column(name="patient_registration_race")
	private String patientRegistrationRace;

	@Column(name="patient_registration_ethnicity")
	private String patientRegistrationEthnicity;

	@Column(name="patient_registration_preferred_lan")
	private String patientRegistrationPreferredLan;

	@Column(name="discard_replication")
	private Boolean discardReplication;

	@Column(name="patient_registration_bill_status")
	private Integer patientRegistrationBillStatus;

	@JsonSerialize(using = JsonTimestampSerializer.class)
	@Column(name="patient_registration_dobtime")
	private Timestamp patientRegistrationDobtime;

	@Column(name="patient_registration_procedure_copay")
	private Double patientRegistrationProcedureCopay;

	@Column(name="patient_registration_other_copay")
	private Double patientRegistrationOtherCopay;

	@Column(name="patient_registration_auth_req")
	private String patientRegistrationAuthReq;

	@Column(name="patient_registration_recall_reason")
	private String patientRegistrationRecallReason;

	@Column(name="patient_registration_coins")
	private Double patientRegistrationCoins;

	@Column(name="patient_registration_reminder_preference")
	private Integer patientRegistrationReminderPreference;

	@Column(name="patient_registration_suffix")
	private String patientRegistrationSuffix;

	@Column(name="patient_registration_guardian_photoid")
	private Boolean patientRegistrationGuardianPhotoid;

	@Column(name="patient_registration_dontbill")
	private Integer patientRegistrationDontbill;

	@Column(name="patient_registration_exempt_in_report")
	private Boolean patientRegistrationExemptInReport;

	@Column(name="patient_registration_isdeductable")
	private Boolean patientRegistrationIsdeductable;

	@Column(name="patient_registration_deductablemet")
	private Boolean patientRegistrationDeductablemet;

	@Column(name="patient_registration_benefits")
	private String patientRegistrationBenefits;

	@Column(name="patient_registration_spoketo")
	private String patientRegistrationSpoketo;

	@JsonSerialize(using = JsonTimestampSerializer.class)
	@Column(name="patient_registration_spokedate")
	private Timestamp patientRegistrationSpokedate;

	@Column(name="patient_registration_isauthreq_omhs")
	private Boolean patientRegistrationIsauthreqOmhs;

	@Column(name="patient_registration_auth_phno")
	private String patientRegistrationAuthPhno;

	@Column(name="patient_registration_comments")
	private String patientRegistrationComments;

	@Column(name="patient_registration_claim_address")
	private String patientRegistrationClaimAddress;

	@Column(name="patient_registration_addresstype")
	private Integer patientRegistrationAddresstype;

	@Column(name="patient_registration_ispatientportal")
	private Boolean patientRegistrationIspatientportal;

	@Column(name="patient_registration_isreminder")
	private Boolean patientRegistrationIsreminder;

	@Column(name="patient_registration_reminderon")
	private Date patientRegistrationReminderon;

	@Column(name="patient_registration_deathon")
	private Date patientRegistrationDeathon;

	@Column(name="patient_registration_deathcause")
	private String patientRegistrationDeathcause;

	@Column(name="patient_registration_preferredcontact")
	private Integer patientRegistrationPreferredcontact;

	@Column(name="patient_registration_other_no")
	private String patientRegistrationOtherNo;

	@Column(name="patient_registration_ptreferredby")
	private String patientRegistrationPtreferredby;

	@Column(name="patient_registration_nickname")
	private String patientRegistrationNickname;

	@Column(name="patient_registration_other_physicianids")
	private String patientRegistrationOtherPhysicianids;

	@Column(name="patient_registration_isportalccessblocked")
	private Boolean patientRegistrationIsportalccessblocked;

	@Column(name="patient_registration_eligibility_deductible_comments")
	private String patientRegistrationEligibilityDeductibleComments;

	@Column(name="patient_registration_ccn")
	private String patientRegistrationCcn;

	@Column(name="patient_registration_erx_history_consent_type")
	private String patientRegistrationErxHistoryConsentType;

	@Column(name="patient_registration_vfcstatus")
	private String patientRegistrationVfcstatus;

	@Column(name="patient_registration_patient_declined")
	private Boolean patientRegistrationPatientDeclined;

	@Column(name="patient_registration_address_start_date")
	private String patientRegistrationAddressStartDate;

	@Column(name="patient_registration_address_end_date")
	private String patientRegistrationAddressEndDate;

	@Column(name="patient_registration_maiden_name")
	private String patientRegistrationMaidenName;

	@Column(name="patient_registration_consentform")
	private Boolean patientRegistrationConsentform;

	@Column(name="patient_registration_istxtmsg")
	private Boolean patientRegistrationIstxtmsg;

	@Column(name="patient_registration_secemail")
	private String patientRegistrationSecemail;

	@JsonSerialize(using = JsonTimestampSerializer.class)
	@Column(name="patient_registration_dodtime")
	private Timestamp patientRegistrationDodtime;

	@Column(name="patient_registration_credits")
	private Double patientRegistrationCredits;

	@Column(name="patient_registration_acotype")
	private Integer patientRegistrationAcotype;

	@Column(name="patient_registration_emr")
	private Boolean patientRegistrationEmr;

	@Column(name="patient_registration_highriskpatient")
	private Boolean patientRegistrationHighriskpatient;

	@Column(name="patient_registration_case_manager")
	private Integer patientRegistrationCaseManager;

	@Column(name="patient_registration_cspno")
	private String patientRegistrationCspno;

	@Column(name="patient_registration_ptreferredname")
	private String patientRegistrationPtreferredname;

	@Column(name="patient_registration_telemed")
	private Boolean patientRegistrationTelemed;

	@Column(name="patient_registration_outcome")
	private Integer patientRegistrationOutcome;

	@Column(name="patient_registration_program")
	private String patientRegistrationProgram;

	@Column(name="patient_registration_patientpracticerel")
	private Integer patientRegistrationPatientpracticerel;

	@Column(name="patient_registration_studycode")
	private Integer patientRegistrationStudycode;
	
	@Column(name="patient_registration_state_name")
	private String patientRegistrationStateName;
	
	@Column(name="patient_registration_granular_race_code")
	private String patientRegistrationGranularRaceCode;
	
	@Column(name="patient_registration_granular_ethnicity_code")
	private String patientRegistrationGranularEthnicityCode;
	
	public String getPatientRegistrationGranularRaceCode() {
		return patientRegistrationGranularRaceCode;
	}

	public void setPatientRegistrationGranularRaceCode(String patientRegistrationGranularRaceCode) {
		this.patientRegistrationGranularRaceCode = patientRegistrationGranularRaceCode;
	}

	public String getPatientRegistrationGranularEthnicityCode() {
		return patientRegistrationGranularEthnicityCode;
	}

	public void setPatientRegistrationGranularEthnicityCode(String patientRegistrationGranularEthnicityCode) {
		this.patientRegistrationGranularEthnicityCode = patientRegistrationGranularEthnicityCode;
	}

	public String getPatientRegistrationStateName() {
		return patientRegistrationStateName;
	}

	public void setPatientRegistrationStateName(String patientRegistrationStateName) {
		this.patientRegistrationStateName = patientRegistrationStateName;
	}

	@ManyToOne(fetch=FetchType.LAZY)
	@JsonManagedReference
	@JoinColumn(name="patient_registration_refering_physician",referencedColumnName="referring_doctor_uniqueid",insertable=false,updatable=false)
	@NotFound(action=NotFoundAction.IGNORE)	// To ignore entries -1, -2 in patient_registration table which doesn't exist in H076001
	private ReferringDoctor referringPhyTable;

	@OneToMany(mappedBy="patientRegistrationTable")
	@JsonManagedReference
	private List<PatientInsDetail> patientInsuranceTable;
	
	@OneToMany(mappedBy="patientRegistration")
	@JsonBackReference
	List<Admission> admission;
	
	@OneToMany(mappedBy="patientregistration")
	@JsonBackReference
	List<PatientSignature> patientSignature;
	

	@OneToMany(mappedBy="patientRegistration")
	private List<Hl7ResultInbox> hl7ResultInbox;
	
	@ManyToOne(fetch=FetchType.LAZY)
	@NotFound(action=NotFoundAction.IGNORE)
	@JsonManagedReference
	@JoinColumn(name="patient_registration_guarantorid", referencedColumnName="guarantor_key", insertable=false, updatable=false)
	private Guarantor guaranatorDetails;
	
	
	@OneToMany(cascade=CascadeType.ALL,mappedBy="patientRegistration",fetch=FetchType.LAZY)
	@JsonBackReference
	List<AlertEvent> alertEvent;	
	
	public ReferringDoctor getReferringPhyTable() {
		return referringPhyTable;
	}

	public List<AlertEvent> getAlertEvent() {
		return alertEvent;
	}

	public void setAlertEvent(List<AlertEvent> alertEvent) {
		this.alertEvent = alertEvent;
	}

	public void setReferringPhyTable(ReferringDoctor referringPhyTable) {
		this.referringPhyTable = referringPhyTable;
	}			
	
	public List<Admission> getAdmission() {
		return admission;
	}

	public void setAdmission(List<Admission> admission) {
		this.admission = admission;
	}
	
	public List<PatientInsDetail> getPatientInsuranceTable() {
		return patientInsuranceTable;
	}

	public void setPatientInsuranceTable(
			List<PatientInsDetail> patientInsuranceTable) {
		this.patientInsuranceTable = patientInsuranceTable;
	}

	public Integer getPatientRegistrationId() {
		return patientRegistrationId;
	}

	public void setPatientRegistrationId(Integer patientRegistrationId) {
		this.patientRegistrationId = patientRegistrationId;
	}

	public String getPatientRegistrationLastName() {
		return patientRegistrationLastName;
	}

	public void setPatientRegistrationLastName(String patientRegistrationLastName) {
		this.patientRegistrationLastName = patientRegistrationLastName;
	}

	public String getPatientRegistrationFirstName() {
		return patientRegistrationFirstName;
	}

	public void setPatientRegistrationFirstName(String patientRegistrationFirstName) {
		this.patientRegistrationFirstName = patientRegistrationFirstName;
	}

	public String getPatientRegistrationAccountno() {
		return patientRegistrationAccountno;
	}

	public void setPatientRegistrationAccountno(String patientRegistrationAccountno) {
		this.patientRegistrationAccountno = patientRegistrationAccountno;
	}

	public String getPatientRegistrationSsn() {
		return patientRegistrationSsn;
	}

	public void setPatientRegistrationSsn(String patientRegistrationSsn) {
		this.patientRegistrationSsn = patientRegistrationSsn;
	}

	public Date getPatientRegistrationDob() {
		return patientRegistrationDob;
	}

	public void setPatientRegistrationDob(Date patientRegistrationDob) {
		this.patientRegistrationDob = patientRegistrationDob;
	}

	public Integer getPatientRegistrationSex() {
		return patientRegistrationSex;
	}

	public void setPatientRegistrationSex(Integer patientRegistrationSex) {
		this.patientRegistrationSex = patientRegistrationSex;
	}

	public Integer getPatientRegistrationMaritalstatus() {
		return patientRegistrationMaritalstatus;
	}

	public void setPatientRegistrationMaritalstatus(
			Integer patientRegistrationMaritalstatus) {
		this.patientRegistrationMaritalstatus = patientRegistrationMaritalstatus;
	}

	public String getPatientRegistrationAddress1() {
		return patientRegistrationAddress1;
	}

	public void setPatientRegistrationAddress1(String patientRegistrationAddress1) {
		this.patientRegistrationAddress1 = patientRegistrationAddress1;
	}

	public String getPatientRegistrationAddress2() {
		return patientRegistrationAddress2;
	}

	public void setPatientRegistrationAddress2(String patientRegistrationAddress2) {
		this.patientRegistrationAddress2 = patientRegistrationAddress2;
	}

	public String getPatientRegistrationCity() {
		return patientRegistrationCity;
	}

	public void setPatientRegistrationCity(String patientRegistrationCity) {
		this.patientRegistrationCity = patientRegistrationCity;
	}

	public String getPatientRegistrationState() {
		return patientRegistrationState;
	}

	public void setPatientRegistrationState(String patientRegistrationState) {
		this.patientRegistrationState = patientRegistrationState;
	}

	public String getPatientRegistrationZip() {
		return patientRegistrationZip;
	}

	public void setPatientRegistrationZip(String patientRegistrationZip) {
		this.patientRegistrationZip = patientRegistrationZip;
	}

	public String getPatientRegistrationWorkNo() {
		return patientRegistrationWorkNo;
	}

	public void setPatientRegistrationWorkNo(String patientRegistrationWorkNo) {
		this.patientRegistrationWorkNo = patientRegistrationWorkNo;
	}

	public Boolean getPatientRegistrationActive() {
		return patientRegistrationActive;
	}

	public void setPatientRegistrationActive(Boolean patientRegistrationActive) {
		this.patientRegistrationActive = patientRegistrationActive;
	}

	public Date getPatientRegistrationDxdate() {
		return patientRegistrationDxdate;
	}

	public void setPatientRegistrationDxdate(Date patientRegistrationDxdate) {
		this.patientRegistrationDxdate = patientRegistrationDxdate;
	}

	public Date getPatientRegistrationCurDxDate() {
		return patientRegistrationCurDxDate;
	}

	public void setPatientRegistrationCurDxDate(Date patientRegistrationCurDxDate) {
		this.patientRegistrationCurDxDate = patientRegistrationCurDxDate;
	}

	public Integer getPatientRegistrationHowIntro() {
		return patientRegistrationHowIntro;
	}

	public void setPatientRegistrationHowIntro(Integer patientRegistrationHowIntro) {
		this.patientRegistrationHowIntro = patientRegistrationHowIntro;
	}

	public Integer getPatientRegistrationPosId() {
		return patientRegistrationPosId;
	}

	public void setPatientRegistrationPosId(Integer patientRegistrationPosId) {
		this.patientRegistrationPosId = patientRegistrationPosId;
	}

	public String getPatientRegistrationFatherName() {
		return patientRegistrationFatherName;
	}

	public void setPatientRegistrationFatherName(
			String patientRegistrationFatherName) {
		this.patientRegistrationFatherName = patientRegistrationFatherName;
	}

	public String getPatientRegistrationMotherName() {
		return patientRegistrationMotherName;
	}

	public void setPatientRegistrationMotherName(
			String patientRegistrationMotherName) {
		this.patientRegistrationMotherName = patientRegistrationMotherName;
	}

	public String getPatientRegistrationTifReference() {
		return patientRegistrationTifReference;
	}

	public void setPatientRegistrationTifReference(
			String patientRegistrationTifReference) {
		this.patientRegistrationTifReference = patientRegistrationTifReference;
	}

	public String getPatientRegistrationTmpstatus() {
		return patientRegistrationTmpstatus;
	}

	public void setPatientRegistrationTmpstatus(String patientRegistrationTmpstatus) {
		this.patientRegistrationTmpstatus = patientRegistrationTmpstatus;
	}

	public String getPatientRegistrationBillingstatus() {
		return patientRegistrationBillingstatus;
	}

	public void setPatientRegistrationBillingstatus(
			String patientRegistrationBillingstatus) {
		this.patientRegistrationBillingstatus = patientRegistrationBillingstatus;
	}

	public Integer getPatientRegistrationBillinggroup() {
		return patientRegistrationBillinggroup;
	}

	public void setPatientRegistrationBillinggroup(
			Integer patientRegistrationBillinggroup) {
		this.patientRegistrationBillinggroup = patientRegistrationBillinggroup;
	}

	public String getPatientRegistrationBasediagnosis() {
		return patientRegistrationBasediagnosis;
	}

	public void setPatientRegistrationBasediagnosis(
			String patientRegistrationBasediagnosis) {
		this.patientRegistrationBasediagnosis = patientRegistrationBasediagnosis;
	}

	public Integer getPatientRegistrationBillingreasonid() {
		return patientRegistrationBillingreasonid;
	}

	public void setPatientRegistrationBillingreasonid(
			Integer patientRegistrationBillingreasonid) {
		this.patientRegistrationBillingreasonid = patientRegistrationBillingreasonid;
	}

	public Double getPatientRegistrationTotaldue() {
		return patientRegistrationTotaldue;
	}

	public void setPatientRegistrationTotaldue(Double patientRegistrationTotaldue) {
		this.patientRegistrationTotaldue = patientRegistrationTotaldue;
	}

	public Integer getPatientRegistrationReferingPhysician() {
		return patientRegistrationReferingPhysician;
	}

	public void setPatientRegistrationReferingPhysician(
			Integer patientRegistrationReferingPhysician) {
		this.patientRegistrationReferingPhysician = patientRegistrationReferingPhysician;
	}

	public Integer getPatientRegistrationPrincipalDoctor() {
		return patientRegistrationPrincipalDoctor;
	}

	public void setPatientRegistrationPrincipalDoctor(
			Integer patientRegistrationPrincipalDoctor) {
		this.patientRegistrationPrincipalDoctor = patientRegistrationPrincipalDoctor;
	}

	public Integer getPatientRegistrationTherapist() {
		return patientRegistrationTherapist;
	}

	public void setPatientRegistrationTherapist(Integer patientRegistrationTherapist) {
		this.patientRegistrationTherapist = patientRegistrationTherapist;
	}

	public Double getPatientRegistrationUnalloccash() {
		return patientRegistrationUnalloccash;
	}

	public void setPatientRegistrationUnalloccash(
			Double patientRegistrationUnalloccash) {
		this.patientRegistrationUnalloccash = patientRegistrationUnalloccash;
	}

	public Double getPatientRegistrationMdcopay() {
		return patientRegistrationMdcopay;
	}

	public void setPatientRegistrationMdcopay(Double patientRegistrationMdcopay) {
		this.patientRegistrationMdcopay = patientRegistrationMdcopay;
	}

	public Double getPatientRegistrationTherapistcopay() {
		return patientRegistrationTherapistcopay;
	}

	public void setPatientRegistrationTherapistcopay(
			Double patientRegistrationTherapistcopay) {
		this.patientRegistrationTherapistcopay = patientRegistrationTherapistcopay;
	}

	public Double getPatientRegistrationCopay() {
		return patientRegistrationCopay;
	}

	public void setPatientRegistrationCopay(Double patientRegistrationCopay) {
		this.patientRegistrationCopay = patientRegistrationCopay;
	}

	public String getPatientRegistrationFamilyPhy() {
		return patientRegistrationFamilyPhy;
	}

	public void setPatientRegistrationFamilyPhy(String patientRegistrationFamilyPhy) {
		this.patientRegistrationFamilyPhy = patientRegistrationFamilyPhy;
	}

	public String getPatientRegistrationNotes() {
		return patientRegistrationNotes;
	}

	public void setPatientRegistrationNotes(String patientRegistrationNotes) {
		this.patientRegistrationNotes = patientRegistrationNotes;
	}

	public Integer getPatientRegistrationAccttype() {
		return patientRegistrationAccttype;
	}

	public void setPatientRegistrationAccttype(Integer patientRegistrationAccttype) {
		this.patientRegistrationAccttype = patientRegistrationAccttype;
	}

	public String getPatientRegistrationEmployer() {
		return patientRegistrationEmployer;
	}

	public void setPatientRegistrationEmployer(String patientRegistrationEmployer) {
		this.patientRegistrationEmployer = patientRegistrationEmployer;
	}

	public String getPatientRegistrationSubluxRegion() {
		return patientRegistrationSubluxRegion;
	}

	public void setPatientRegistrationSubluxRegion(
			String patientRegistrationSubluxRegion) {
		this.patientRegistrationSubluxRegion = patientRegistrationSubluxRegion;
	}

	public Date getPatientRegistrationDatefirstseen() {
		return patientRegistrationDatefirstseen;
	}

	public void setPatientRegistrationDatefirstseen(
			Date patientRegistrationDatefirstseen) {
		this.patientRegistrationDatefirstseen = patientRegistrationDatefirstseen;
	}

	public Date getPatientRegistrationRecalldate() {
		return patientRegistrationRecalldate;
	}

	public void setPatientRegistrationRecalldate(Date patientRegistrationRecalldate) {
		this.patientRegistrationRecalldate = patientRegistrationRecalldate;
	}

	public Date getPatientRegistrationXrayDate() {
		return patientRegistrationXrayDate;
	}

	public void setPatientRegistrationXrayDate(Date patientRegistrationXrayDate) {
		this.patientRegistrationXrayDate = patientRegistrationXrayDate;
	}

	public Date getPatientRegistrationHospDateFrom() {
		return patientRegistrationHospDateFrom;
	}

	public void setPatientRegistrationHospDateFrom(
			Date patientRegistrationHospDateFrom) {
		this.patientRegistrationHospDateFrom = patientRegistrationHospDateFrom;
	}

	public Date getPatientRegistrationHospDateTo() {
		return patientRegistrationHospDateTo;
	}

	public void setPatientRegistrationHospDateTo(Date patientRegistrationHospDateTo) {
		this.patientRegistrationHospDateTo = patientRegistrationHospDateTo;
	}

	public Boolean getPatientRegistrationRecallLetter() {
		return patientRegistrationRecallLetter;
	}

	public void setPatientRegistrationRecallLetter(
			Boolean patientRegistrationRecallLetter) {
		this.patientRegistrationRecallLetter = patientRegistrationRecallLetter;
	}

	public Boolean getPatientRegistrationRecallphone() {
		return patientRegistrationRecallphone;
	}

	public void setPatientRegistrationRecallphone(
			Boolean patientRegistrationRecallphone) {
		this.patientRegistrationRecallphone = patientRegistrationRecallphone;
	}

	public Boolean getPatientRegistrationLeavemsg() {
		return patientRegistrationLeavemsg;
	}

	public void setPatientRegistrationLeavemsg(Boolean patientRegistrationLeavemsg) {
		this.patientRegistrationLeavemsg = patientRegistrationLeavemsg;
	}

	public Boolean getPatientRegistrationTenweeks() {
		return patientRegistrationTenweeks;
	}

	public void setPatientRegistrationTenweeks(Boolean patientRegistrationTenweeks) {
		this.patientRegistrationTenweeks = patientRegistrationTenweeks;
	}

	public Boolean getPatientRegistrationAssigned() {
		return patientRegistrationAssigned;
	}

	public void setPatientRegistrationAssigned(Boolean patientRegistrationAssigned) {
		this.patientRegistrationAssigned = patientRegistrationAssigned;
	}

	public Boolean getPatientRegistrationLop() {
		return patientRegistrationLop;
	}

	public void setPatientRegistrationLop(Boolean patientRegistrationLop) {
		this.patientRegistrationLop = patientRegistrationLop;
	}

	public String getPatientRegistrationPatientAlert() {
		return patientRegistrationPatientAlert;
	}

	public void setPatientRegistrationPatientAlert(
			String patientRegistrationPatientAlert) {
		this.patientRegistrationPatientAlert = patientRegistrationPatientAlert;
	}

	public String getPatientRegistrationGuardian() {
		return patientRegistrationGuardian;
	}

	public void setPatientRegistrationGuardian(String patientRegistrationGuardian) {
		this.patientRegistrationGuardian = patientRegistrationGuardian;
	}

	public String getPatientRegistrationReln() {
		return patientRegistrationReln;
	}

	public void setPatientRegistrationReln(String patientRegistrationReln) {
		this.patientRegistrationReln = patientRegistrationReln;
	}

	public String getPatientRegistrationCurrAuthNo() {
		return patientRegistrationCurrAuthNo;
	}

	public void setPatientRegistrationCurrAuthNo(
			String patientRegistrationCurrAuthNo) {
		this.patientRegistrationCurrAuthNo = patientRegistrationCurrAuthNo;
	}

	public String getPatientRegistrationForlocaluse() {
		return patientRegistrationForlocaluse;
	}

	public void setPatientRegistrationForlocaluse(
			String patientRegistrationForlocaluse) {
		this.patientRegistrationForlocaluse = patientRegistrationForlocaluse;
	}

	public String getPatientRegistrationLastcpt() {
		return patientRegistrationLastcpt;
	}

	public void setPatientRegistrationLastcpt(String patientRegistrationLastcpt) {
		this.patientRegistrationLastcpt = patientRegistrationLastcpt;
	}

	public String getPatientRegistrationEcontactperson() {
		return patientRegistrationEcontactperson;
	}

	public void setPatientRegistrationEcontactperson(
			String patientRegistrationEcontactperson) {
		this.patientRegistrationEcontactperson = patientRegistrationEcontactperson;
	}

	public Integer getPatientRegistrationErelationship() {
		return patientRegistrationErelationship;
	}

	public void setPatientRegistrationErelationship(
			Integer patientRegistrationErelationship) {
		this.patientRegistrationErelationship = patientRegistrationErelationship;
	}

	public String getPatientRegistrationEaddress() {
		return patientRegistrationEaddress;
	}

	public void setPatientRegistrationEaddress(String patientRegistrationEaddress) {
		this.patientRegistrationEaddress = patientRegistrationEaddress;
	}

	public String getPatientRegistrationEcity() {
		return patientRegistrationEcity;
	}

	public void setPatientRegistrationEcity(String patientRegistrationEcity) {
		this.patientRegistrationEcity = patientRegistrationEcity;
	}

	public String getPatientRegistrationEstate() {
		return patientRegistrationEstate;
	}

	public void setPatientRegistrationEstate(String patientRegistrationEstate) {
		this.patientRegistrationEstate = patientRegistrationEstate;
	}

	public String getPatientRegistrationEzip() {
		return patientRegistrationEzip;
	}

	public void setPatientRegistrationEzip(String patientRegistrationEzip) {
		this.patientRegistrationEzip = patientRegistrationEzip;
	}

	public String getPatientRegistrationEphoneno() {
		return patientRegistrationEphoneno;
	}

	public void setPatientRegistrationEphoneno(String patientRegistrationEphoneno) {
		this.patientRegistrationEphoneno = patientRegistrationEphoneno;
	}

	public Date getPatientRegistrationAuthValidUpto() {
		return patientRegistrationAuthValidUpto;
	}

	public void setPatientRegistrationAuthValidUpto(
			Date patientRegistrationAuthValidUpto) {
		this.patientRegistrationAuthValidUpto = patientRegistrationAuthValidUpto;
	}

	public Integer getPatientRegistrationTotalAuthVisits() {
		return patientRegistrationTotalAuthVisits;
	}

	public void setPatientRegistrationTotalAuthVisits(
			Integer patientRegistrationTotalAuthVisits) {
		this.patientRegistrationTotalAuthVisits = patientRegistrationTotalAuthVisits;
	}

	public String getPatientRegistrationAllergies() {
		return patientRegistrationAllergies;
	}

	public void setPatientRegistrationAllergies(String patientRegistrationAllergies) {
		this.patientRegistrationAllergies = patientRegistrationAllergies;
	}

	public String getPatientRegistrationSiblingsAges() {
		return patientRegistrationSiblingsAges;
	}

	public void setPatientRegistrationSiblingsAges(
			String patientRegistrationSiblingsAges) {
		this.patientRegistrationSiblingsAges = patientRegistrationSiblingsAges;
	}

	public String getPatientRegistrationMailId() {
		return patientRegistrationMailId;
	}

	public void setPatientRegistrationMailId(String patientRegistrationMailId) {
		this.patientRegistrationMailId = patientRegistrationMailId;
	}

	public String getPatientRegistrationCellno() {
		return patientRegistrationCellno;
	}

	public void setPatientRegistrationCellno(String patientRegistrationCellno) {
		this.patientRegistrationCellno = patientRegistrationCellno;
	}

	public String getPatientRegistrationDriverlicenseno() {
		return patientRegistrationDriverlicenseno;
	}

	public void setPatientRegistrationDriverlicenseno(
			String patientRegistrationDriverlicenseno) {
		this.patientRegistrationDriverlicenseno = patientRegistrationDriverlicenseno;
	}

	public String getPatientRegistrationFaxno() {
		return patientRegistrationFaxno;
	}

	public void setPatientRegistrationFaxno(String patientRegistrationFaxno) {
		this.patientRegistrationFaxno = patientRegistrationFaxno;
	}

	public Double getPatientRegistrationInsexcess() {
		return patientRegistrationInsexcess;
	}

	public void setPatientRegistrationInsexcess(Double patientRegistrationInsexcess) {
		this.patientRegistrationInsexcess = patientRegistrationInsexcess;
	}

	public String getPatientRegistrationNotesoftheday() {
		return patientRegistrationNotesoftheday;
	}

	public void setPatientRegistrationNotesoftheday(
			String patientRegistrationNotesoftheday) {
		this.patientRegistrationNotesoftheday = patientRegistrationNotesoftheday;
	}

	public Integer getPatientRegistrationReasonPtBill() {
		return patientRegistrationReasonPtBill;
	}

	public void setPatientRegistrationReasonPtBill(
			Integer patientRegistrationReasonPtBill) {
		this.patientRegistrationReasonPtBill = patientRegistrationReasonPtBill;
	}

	public Integer getPatientRegistrationCurVisitNo() {
		return patientRegistrationCurVisitNo;
	}

	public void setPatientRegistrationCurVisitNo(
			Integer patientRegistrationCurVisitNo) {
		this.patientRegistrationCurVisitNo = patientRegistrationCurVisitNo;
	}

	public Long getPatientRegistrationReferredby() {
		return patientRegistrationReferredby;
	}

	public void setPatientRegistrationReferredby(
			Long patientRegistrationReferredby) {
		this.patientRegistrationReferredby = patientRegistrationReferredby;
	}

	public String getPatientRegistrationCustomfield1() {
		return patientRegistrationCustomfield1;
	}

	public void setPatientRegistrationCustomfield1(
			String patientRegistrationCustomfield1) {
		this.patientRegistrationCustomfield1 = patientRegistrationCustomfield1;
	}

	public String getPatientRegistrationExtrafldXml() {
		return patientRegistrationExtrafldXml;
	}

	public void setPatientRegistrationExtrafldXml(
			String patientRegistrationExtrafldXml) {
		this.patientRegistrationExtrafldXml = patientRegistrationExtrafldXml;
	}

	public Boolean getPatientRegistrationNeverschedule() {
		return patientRegistrationNeverschedule;
	}

	public void setPatientRegistrationNeverschedule(
			Boolean patientRegistrationNeverschedule) {
		this.patientRegistrationNeverschedule = patientRegistrationNeverschedule;
	}

	public Integer getPatientRegistrationChartStatus() {
		return patientRegistrationChartStatus;
	}

	public void setPatientRegistrationChartStatus(
			Integer patientRegistrationChartStatus) {
		this.patientRegistrationChartStatus = patientRegistrationChartStatus;
	}

	public Integer getPatientRegistrationAppType() {
		return patientRegistrationAppType;
	}

	public void setPatientRegistrationAppType(Integer patientRegistrationAppType) {
		this.patientRegistrationAppType = patientRegistrationAppType;
	}

	public String getPatientRegistrationChartno() {
		return patientRegistrationChartno;
	}

	public void setPatientRegistrationChartno(String patientRegistrationChartno) {
		this.patientRegistrationChartno = patientRegistrationChartno;
	}

	public String getPatientRegistrationSpecialdx() {
		return patientRegistrationSpecialdx;
	}

	public void setPatientRegistrationSpecialdx(String patientRegistrationSpecialdx) {
		this.patientRegistrationSpecialdx = patientRegistrationSpecialdx;
	}

	public Integer getPatientRegistrationLoginId() {
		return patientRegistrationLoginId;
	}

	public void setPatientRegistrationLoginId(Integer patientRegistrationLoginId) {
		this.patientRegistrationLoginId = patientRegistrationLoginId;
	}

	public Boolean getPatientRegistrationIsapproved() {
		return patientRegistrationIsapproved;
	}

	public void setPatientRegistrationIsapproved(
			Boolean patientRegistrationIsapproved) {
		this.patientRegistrationIsapproved = patientRegistrationIsapproved;
	}

	public Integer getPatientRegistrationApprovedBy() {
		return patientRegistrationApprovedBy;
	}

	public void setPatientRegistrationApprovedBy(
			Integer patientRegistrationApprovedBy) {
		this.patientRegistrationApprovedBy = patientRegistrationApprovedBy;
	}

	public Integer getPatientRegistrationLastModifiedBy() {
		return patientRegistrationLastModifiedBy;
	}

	public void setPatientRegistrationLastModifiedBy(
			Integer patientRegistrationLastModifiedBy) {
		this.patientRegistrationLastModifiedBy = patientRegistrationLastModifiedBy;
	}

	public Timestamp getPatientRegistrationLastModifiedDate() {
		return patientRegistrationLastModifiedDate;
	}

	public void setPatientRegistrationLastModifiedDate(
			Timestamp patientRegistrationLastModifiedDate) {
		this.patientRegistrationLastModifiedDate = patientRegistrationLastModifiedDate;
	}

	public String getPatientRegistrationLog() {
		return patientRegistrationLog;
	}

	public void setPatientRegistrationLog(String patientRegistrationLog) {
		this.patientRegistrationLog = patientRegistrationLog;
	}

	public String getPatientRegistrationTemp1() {
		return patientRegistrationTemp1;
	}

	public void setPatientRegistrationTemp1(String patientRegistrationTemp1) {
		this.patientRegistrationTemp1 = patientRegistrationTemp1;
	}

	public Double getPatientRegistrationOldservicetotaldue() {
		return patientRegistrationOldservicetotaldue;
	}

	public void setPatientRegistrationOldservicetotaldue(
			Double patientRegistrationOldservicetotaldue) {
		this.patientRegistrationOldservicetotaldue = patientRegistrationOldservicetotaldue;
	}

	public String getPatientRegistrationFinanciallyResponisible() {
		return patientRegistrationFinanciallyResponisible;
	}

	public void setPatientRegistrationFinanciallyResponisible(
			String patientRegistrationFinanciallyResponisible) {
		this.patientRegistrationFinanciallyResponisible = patientRegistrationFinanciallyResponisible;
	}

	public Date getPatientRegistrationDatelastbilled() {
		return patientRegistrationDatelastbilled;
	}

	public void setPatientRegistrationDatelastbilled(
			Date patientRegistrationDatelastbilled) {
		this.patientRegistrationDatelastbilled = patientRegistrationDatelastbilled;
	}

	public Integer getH555555() {
		return h555555;
	}

	public void setH555555(Integer h555555) {
		this.h555555 = h555555;
	}

	public String getPatientRegistrationUnknown1() {
		return patientRegistrationUnknown1;
	}

	public void setPatientRegistrationUnknown1(String patientRegistrationUnknown1) {
		this.patientRegistrationUnknown1 = patientRegistrationUnknown1;
	}

	public Boolean getPatientRegistrationUnknown2() {
		return patientRegistrationUnknown2;
	}

	public void setPatientRegistrationUnknown2(Boolean patientRegistrationUnknown2) {
		this.patientRegistrationUnknown2 = patientRegistrationUnknown2;
	}

	public Boolean getPatientRegistrationUnknown3() {
		return patientRegistrationUnknown3;
	}

	public void setPatientRegistrationUnknown3(Boolean patientRegistrationUnknown3) {
		this.patientRegistrationUnknown3 = patientRegistrationUnknown3;
	}

	public Integer getPatientRegistrationCostplan() {
		return patientRegistrationCostplan;
	}

	public void setPatientRegistrationCostplan(Integer patientRegistrationCostplan) {
		this.patientRegistrationCostplan = patientRegistrationCostplan;
	}

	public String getPatientRegistrationUnknown4() {
		return patientRegistrationUnknown4;
	}

	public void setPatientRegistrationUnknown4(String patientRegistrationUnknown4) {
		this.patientRegistrationUnknown4 = patientRegistrationUnknown4;
	}

	public Integer getPatientRegistrationUnknown7() {
		return patientRegistrationUnknown7;
	}

	public void setPatientRegistrationUnknown7(Integer patientRegistrationUnknown7) {
		this.patientRegistrationUnknown7 = patientRegistrationUnknown7;
	}

	public Integer getPatientRegistrationGuarantorid() {
		return patientRegistrationGuarantorid;
	}

	public void setPatientRegistrationGuarantorid(
			Integer patientRegistrationGuarantorid) {
		this.patientRegistrationGuarantorid = patientRegistrationGuarantorid;
	}

	public Integer getPatientRegistrationGuarantorrel() {
		return patientRegistrationGuarantorrel;
	}

	public void setPatientRegistrationGuarantorrel(
			Integer patientRegistrationGuarantorrel) {
		this.patientRegistrationGuarantorrel = patientRegistrationGuarantorrel;
	}

	public Integer getPatientRegistrationStatus() {
		return patientRegistrationStatus;
	}

	public void setPatientRegistrationStatus(Integer patientRegistrationStatus) {
		this.patientRegistrationStatus = patientRegistrationStatus;
	}

	public Integer getVersion() {
		return version;
	}

	public void setVersion(Integer version) {
		this.version = version;
	}

	public Integer getRuleversion() {
		return ruleversion;
	}

	public void setRuleversion(Integer ruleversion) {
		this.ruleversion = ruleversion;
	}

	public Boolean getValid() {
		return valid;
	}

	public void setValid(Boolean valid) {
		this.valid = valid;
	}

	public Date getPatientRegistrationPlacedOn() {
		return patientRegistrationPlacedOn;
	}

	public void setPatientRegistrationPlacedOn(Date patientRegistrationPlacedOn) {
		this.patientRegistrationPlacedOn = patientRegistrationPlacedOn;
	}

	public String getPatientRegistrationPlacedBy() {
		return patientRegistrationPlacedBy;
	}

	public void setPatientRegistrationPlacedBy(String patientRegistrationPlacedBy) {
		this.patientRegistrationPlacedBy = patientRegistrationPlacedBy;
	}

	public Double getPatientRegistrationUnknown8() {
		return patientRegistrationUnknown8;
	}

	public void setPatientRegistrationUnknown8(Double patientRegistrationUnknown8) {
		this.patientRegistrationUnknown8 = patientRegistrationUnknown8;
	}

	public Integer getPatientRegistrationPlan() {
		return patientRegistrationPlan;
	}

	public void setPatientRegistrationPlan(Integer patientRegistrationPlan) {
		this.patientRegistrationPlan = patientRegistrationPlan;
	}

	public Integer getPatientRegistrationPharmacyId() {
		return patientRegistrationPharmacyId;
	}

	public void setPatientRegistrationPharmacyId(
			Integer patientRegistrationPharmacyId) {
		this.patientRegistrationPharmacyId = patientRegistrationPharmacyId;
	}

	public Integer getSpecialPatientType() {
		return specialPatientType;
	}

	public void setSpecialPatientType(Integer specialPatientType) {
		this.specialPatientType = specialPatientType;
	}

	public Boolean getIsadmin() {
		return isadmin;
	}

	public void setIsadmin(Boolean isadmin) {
		this.isadmin = isadmin;
	}

	public Integer getPatientRegistrationUnknown5() {
		return patientRegistrationUnknown5;
	}

	public void setPatientRegistrationUnknown5(Integer patientRegistrationUnknown5) {
		this.patientRegistrationUnknown5 = patientRegistrationUnknown5;
	}

	public Integer getPatientRegistrationUnknown6() {
		return patientRegistrationUnknown6;
	}

	public void setPatientRegistrationUnknown6(Integer patientRegistrationUnknown6) {
		this.patientRegistrationUnknown6 = patientRegistrationUnknown6;
	}

	public Boolean getPatientRegistrationPhotoid() {
		return patientRegistrationPhotoid;
	}

	public void setPatientRegistrationPhotoid(Boolean patientRegistrationPhotoid) {
		this.patientRegistrationPhotoid = patientRegistrationPhotoid;
	}

	public String getPatientRegistrationNewPatientAlert() {
		return patientRegistrationNewPatientAlert;
	}

	public void setPatientRegistrationNewPatientAlert(
			String patientRegistrationNewPatientAlert) {
		this.patientRegistrationNewPatientAlert = patientRegistrationNewPatientAlert;
	}

	public String getPatientRegistrationRace() {
		return patientRegistrationRace;
	}

	public void setPatientRegistrationRace(String patientRegistrationRace) {
		this.patientRegistrationRace = patientRegistrationRace;
	}

	public String getPatientRegistrationEthnicity() {
		return patientRegistrationEthnicity;
	}

	public void setPatientRegistrationEthnicity(String patientRegistrationEthnicity) {
		this.patientRegistrationEthnicity = patientRegistrationEthnicity;
	}

	public String getPatientRegistrationPreferredLan() {
		return patientRegistrationPreferredLan;
	}

	public void setPatientRegistrationPreferredLan(
			String patientRegistrationPreferredLan) {
		this.patientRegistrationPreferredLan = patientRegistrationPreferredLan;
	}

	public Boolean getDiscardReplication() {
		return discardReplication;
	}

	public void setDiscardReplication(Boolean discardReplication) {
		this.discardReplication = discardReplication;
	}

	public Integer getPatientRegistrationBillStatus() {
		return patientRegistrationBillStatus;
	}

	public void setPatientRegistrationBillStatus(
			Integer patientRegistrationBillStatus) {
		this.patientRegistrationBillStatus = patientRegistrationBillStatus;
	}

	public Double getPatientRegistrationProcedureCopay() {
		return patientRegistrationProcedureCopay;
	}

	public void setPatientRegistrationProcedureCopay(
			Double patientRegistrationProcedureCopay) {
		this.patientRegistrationProcedureCopay = patientRegistrationProcedureCopay;
	}

	public Double getPatientRegistrationOtherCopay() {
		return patientRegistrationOtherCopay;
	}

	public void setPatientRegistrationOtherCopay(
			Double patientRegistrationOtherCopay) {
		this.patientRegistrationOtherCopay = patientRegistrationOtherCopay;
	}

	public String getPatientRegistrationAuthReq() {
		return patientRegistrationAuthReq;
	}

	public void setPatientRegistrationAuthReq(String patientRegistrationAuthReq) {
		this.patientRegistrationAuthReq = patientRegistrationAuthReq;
	}

	public String getPatientRegistrationRecallReason() {
		return patientRegistrationRecallReason;
	}

	public void setPatientRegistrationRecallReason(
			String patientRegistrationRecallReason) {
		this.patientRegistrationRecallReason = patientRegistrationRecallReason;
	}

	public Double getPatientRegistrationCoins() {
		return patientRegistrationCoins;
	}

	public void setPatientRegistrationCoins(Double patientRegistrationCoins) {
		this.patientRegistrationCoins = patientRegistrationCoins;
	}

	public Integer getPatientRegistrationReminderPreference() {
		return patientRegistrationReminderPreference;
	}

	public void setPatientRegistrationReminderPreference(
			Integer patientRegistrationReminderPreference) {
		this.patientRegistrationReminderPreference = patientRegistrationReminderPreference;
	}

	public String getPatientRegistrationSuffix() {
		return patientRegistrationSuffix;
	}

	public void setPatientRegistrationSuffix(String patientRegistrationSuffix) {
		this.patientRegistrationSuffix = patientRegistrationSuffix;
	}

	public Boolean getPatientRegistrationGuardianPhotoid() {
		return patientRegistrationGuardianPhotoid;
	}

	public void setPatientRegistrationGuardianPhotoid(
			Boolean patientRegistrationGuardianPhotoid) {
		this.patientRegistrationGuardianPhotoid = patientRegistrationGuardianPhotoid;
	}

	public Integer getPatientRegistrationDontbill() {
		return patientRegistrationDontbill;
	}

	public void setPatientRegistrationDontbill(Integer patientRegistrationDontbill) {
		this.patientRegistrationDontbill = patientRegistrationDontbill;
	}

	public Boolean getPatientRegistrationExemptInReport() {
		return patientRegistrationExemptInReport;
	}

	public void setPatientRegistrationExemptInReport(
			Boolean patientRegistrationExemptInReport) {
		this.patientRegistrationExemptInReport = patientRegistrationExemptInReport;
	}

	public Boolean getPatientRegistrationIsdeductable() {
		return patientRegistrationIsdeductable;
	}

	public void setPatientRegistrationIsdeductable(
			Boolean patientRegistrationIsdeductable) {
		this.patientRegistrationIsdeductable = patientRegistrationIsdeductable;
	}

	public Boolean getPatientRegistrationDeductablemet() {
		return patientRegistrationDeductablemet;
	}

	public void setPatientRegistrationDeductablemet(
			Boolean patientRegistrationDeductablemet) {
		this.patientRegistrationDeductablemet = patientRegistrationDeductablemet;
	}

	public String getPatientRegistrationBenefits() {
		return patientRegistrationBenefits;
	}

	public void setPatientRegistrationBenefits(String patientRegistrationBenefits) {
		this.patientRegistrationBenefits = patientRegistrationBenefits;
	}

	public String getPatientRegistrationSpoketo() {
		return patientRegistrationSpoketo;
	}

	public void setPatientRegistrationSpoketo(String patientRegistrationSpoketo) {
		this.patientRegistrationSpoketo = patientRegistrationSpoketo;
	}

	public Timestamp getPatientRegistrationSpokedate() {
		return patientRegistrationSpokedate;
	}

	public void setPatientRegistrationSpokedate(
			Timestamp patientRegistrationSpokedate) {
		this.patientRegistrationSpokedate = patientRegistrationSpokedate;
	}

	public Boolean getPatientRegistrationIsauthreqOmhs() {
		return patientRegistrationIsauthreqOmhs;
	}

	public void setPatientRegistrationIsauthreqOmhs(
			Boolean patientRegistrationIsauthreqOmhs) {
		this.patientRegistrationIsauthreqOmhs = patientRegistrationIsauthreqOmhs;
	}

	public String getPatientRegistrationAuthPhno() {
		return patientRegistrationAuthPhno;
	}

	public void setPatientRegistrationAuthPhno(String patientRegistrationAuthPhno) {
		this.patientRegistrationAuthPhno = patientRegistrationAuthPhno;
	}

	public String getPatientRegistrationComments() {
		return patientRegistrationComments;
	}

	public void setPatientRegistrationComments(String patientRegistrationComments) {
		this.patientRegistrationComments = patientRegistrationComments;
	}

	public String getPatientRegistrationClaimAddress() {
		return patientRegistrationClaimAddress;
	}

	public void setPatientRegistrationClaimAddress(
			String patientRegistrationClaimAddress) {
		this.patientRegistrationClaimAddress = patientRegistrationClaimAddress;
	}

	public Integer getPatientRegistrationAddresstype() {
		return patientRegistrationAddresstype;
	}

	public void setPatientRegistrationAddresstype(
			Integer patientRegistrationAddresstype) {
		this.patientRegistrationAddresstype = patientRegistrationAddresstype;
	}

	public Boolean getPatientRegistrationIspatientportal() {
		return patientRegistrationIspatientportal;
	}

	public void setPatientRegistrationIspatientportal(
			Boolean patientRegistrationIspatientportal) {
		this.patientRegistrationIspatientportal = patientRegistrationIspatientportal;
	}

	public Boolean getPatientRegistrationIsreminder() {
		return patientRegistrationIsreminder;
	}

	public void setPatientRegistrationIsreminder(
			Boolean patientRegistrationIsreminder) {
		this.patientRegistrationIsreminder = patientRegistrationIsreminder;
	}

	public Date getPatientRegistrationReminderon() {
		return patientRegistrationReminderon;
	}

	public void setPatientRegistrationReminderon(Date patientRegistrationReminderon) {
		this.patientRegistrationReminderon = patientRegistrationReminderon;
	}

	public Date getPatientRegistrationDeathon() {
		return patientRegistrationDeathon;
	}

	public void setPatientRegistrationDeathon(Date patientRegistrationDeathon) {
		this.patientRegistrationDeathon = patientRegistrationDeathon;
	}

	public String getPatientRegistrationDeathcause() {
		return patientRegistrationDeathcause;
	}

	public void setPatientRegistrationDeathcause(
			String patientRegistrationDeathcause) {
		this.patientRegistrationDeathcause = patientRegistrationDeathcause;
	}

	public Integer getPatientRegistrationPreferredcontact() {
		return patientRegistrationPreferredcontact;
	}

	public void setPatientRegistrationPreferredcontact(
			Integer patientRegistrationPreferredcontact) {
		this.patientRegistrationPreferredcontact = patientRegistrationPreferredcontact;
	}

	public String getPatientRegistrationOtherNo() {
		return patientRegistrationOtherNo;
	}

	public void setPatientRegistrationOtherNo(String patientRegistrationOtherNo) {
		this.patientRegistrationOtherNo = patientRegistrationOtherNo;
	}

	public String getPatientRegistrationPtreferredby() {
		return patientRegistrationPtreferredby;
	}

	public void setPatientRegistrationPtreferredby(
			String patientRegistrationPtreferredby) {
		this.patientRegistrationPtreferredby = patientRegistrationPtreferredby;
	}

	public String getPatientRegistrationNickname() {
		return patientRegistrationNickname;
	}

	public void setPatientRegistrationNickname(String patientRegistrationNickname) {
		this.patientRegistrationNickname = patientRegistrationNickname;
	}

	public String getPatientRegistrationOtherPhysicianids() {
		return patientRegistrationOtherPhysicianids;
	}

	public void setPatientRegistrationOtherPhysicianids(
			String patientRegistrationOtherPhysicianids) {
		this.patientRegistrationOtherPhysicianids = patientRegistrationOtherPhysicianids;
	}

	public Boolean getPatientRegistrationIsportalccessblocked() {
		return patientRegistrationIsportalccessblocked;
	}

	public void setPatientRegistrationIsportalccessblocked(
			Boolean patientRegistrationIsportalccessblocked) {
		this.patientRegistrationIsportalccessblocked = patientRegistrationIsportalccessblocked;
	}

	public String getPatientRegistrationEligibilityDeductibleComments() {
		return patientRegistrationEligibilityDeductibleComments;
	}

	public void setPatientRegistrationEligibilityDeductibleComments(
			String patientRegistrationEligibilityDeductibleComments) {
		this.patientRegistrationEligibilityDeductibleComments = patientRegistrationEligibilityDeductibleComments;
	}

	public String getPatientRegistrationCcn() {
		return patientRegistrationCcn;
	}

	public void setPatientRegistrationCcn(String patientRegistrationCcn) {
		this.patientRegistrationCcn = patientRegistrationCcn;
	}

	public String getPatientRegistrationErxHistoryConsentType() {
		return patientRegistrationErxHistoryConsentType;
	}

	public void setPatientRegistrationErxHistoryConsentType(
			String patientRegistrationErxHistoryConsentType) {
		this.patientRegistrationErxHistoryConsentType = patientRegistrationErxHistoryConsentType;
	}

	public String getPatientRegistrationVfcstatus() {
		return patientRegistrationVfcstatus;
	}

	public void setPatientRegistrationVfcstatus(String patientRegistrationVfcstatus) {
		this.patientRegistrationVfcstatus = patientRegistrationVfcstatus;
	}

	public Boolean getPatientRegistrationPatientDeclined() {
		return patientRegistrationPatientDeclined;
	}

	public void setPatientRegistrationPatientDeclined(
			Boolean patientRegistrationPatientDeclined) {
		this.patientRegistrationPatientDeclined = patientRegistrationPatientDeclined;
	}

	public String getPatientRegistrationAddressStartDate() {
		return patientRegistrationAddressStartDate;
	}

	public void setPatientRegistrationAddressStartDate(
			String patientRegistrationAddressStartDate) {
		this.patientRegistrationAddressStartDate = patientRegistrationAddressStartDate;
	}

	public String getPatientRegistrationAddressEndDate() {
		return patientRegistrationAddressEndDate;
	}

	public void setPatientRegistrationAddressEndDate(
			String patientRegistrationAddressEndDate) {
		this.patientRegistrationAddressEndDate = patientRegistrationAddressEndDate;
	}

	public String getPatientRegistrationMaidenName() {
		return patientRegistrationMaidenName;
	}

	public void setPatientRegistrationMaidenName(
			String patientRegistrationMaidenName) {
		this.patientRegistrationMaidenName = patientRegistrationMaidenName;
	}

	public Boolean getPatientRegistrationConsentform() {
		return patientRegistrationConsentform;
	}

	public void setPatientRegistrationConsentform(
			Boolean patientRegistrationConsentform) {
		this.patientRegistrationConsentform = patientRegistrationConsentform;
	}

	public Boolean getPatientRegistrationIstxtmsg() {
		return patientRegistrationIstxtmsg;
	}

	public void setPatientRegistrationIstxtmsg(Boolean patientRegistrationIstxtmsg) {
		this.patientRegistrationIstxtmsg = patientRegistrationIstxtmsg;
	}

	public String getPatientRegistrationSecemail() {
		return patientRegistrationSecemail;
	}

	public void setPatientRegistrationSecemail(String patientRegistrationSecemail) {
		this.patientRegistrationSecemail = patientRegistrationSecemail;
	}

	public Timestamp getPatientRegistrationDodtime() {
		return patientRegistrationDodtime;
	}

	public void setPatientRegistrationDodtime(Timestamp patientRegistrationDodtime) {
		this.patientRegistrationDodtime = patientRegistrationDodtime;
	}

	public Double getPatientRegistrationCredits() {
		return patientRegistrationCredits;
	}

	public void setPatientRegistrationCredits(Double patientRegistrationCredits) {
		this.patientRegistrationCredits = patientRegistrationCredits;
	}

	public Integer getPatientRegistrationAcotype() {
		return patientRegistrationAcotype;
	}

	public void setPatientRegistrationAcotype(Integer patientRegistrationAcotype) {
		this.patientRegistrationAcotype = patientRegistrationAcotype;
	}

	public Boolean getPatientRegistrationEmr() {
		return patientRegistrationEmr;
	}

	public void setPatientRegistrationEmr(Boolean patientRegistrationEmr) {
		this.patientRegistrationEmr = patientRegistrationEmr;
	}

	public Boolean getPatientRegistrationHighriskpatient() {
		return patientRegistrationHighriskpatient;
	}

	public void setPatientRegistrationHighriskpatient(
			Boolean patientRegistrationHighriskpatient) {
		this.patientRegistrationHighriskpatient = patientRegistrationHighriskpatient;
	}

	public Integer getPatientRegistrationCaseManager() {
		return patientRegistrationCaseManager;
	}

	public void setPatientRegistrationCaseManager(
			Integer patientRegistrationCaseManager) {
		this.patientRegistrationCaseManager = patientRegistrationCaseManager;
	}

	public String getPatientRegistrationCspno() {
		return patientRegistrationCspno;
	}

	public void setPatientRegistrationCspno(String patientRegistrationCspno) {
		this.patientRegistrationCspno = patientRegistrationCspno;
	}

	public String getPatientRegistrationPtreferredname() {
		return patientRegistrationPtreferredname;
	}

	public void setPatientRegistrationPtreferredname(
			String patientRegistrationPtreferredname) {
		this.patientRegistrationPtreferredname = patientRegistrationPtreferredname;
	}

	public Boolean getPatientRegistrationTelemed() {
		return patientRegistrationTelemed;
	}

	public void setPatientRegistrationTelemed(Boolean patientRegistrationTelemed) {
		this.patientRegistrationTelemed = patientRegistrationTelemed;
	}

	public Integer getPatientRegistrationOutcome() {
		return patientRegistrationOutcome;
	}

	public void setPatientRegistrationOutcome(Integer patientRegistrationOutcome) {
		this.patientRegistrationOutcome = patientRegistrationOutcome;
	}

	public String getPatientRegistrationProgram() {
		return patientRegistrationProgram;
	}

	public void setPatientRegistrationProgram(String patientRegistrationProgram) {
		this.patientRegistrationProgram = patientRegistrationProgram;
	}

	public Integer getPatientRegistrationPatientpracticerel() {
		return patientRegistrationPatientpracticerel;
	}

	public void setPatientRegistrationPatientpracticerel(
			Integer patientRegistrationPatientpracticerel) {
		this.patientRegistrationPatientpracticerel = patientRegistrationPatientpracticerel;
	}

	public Integer getPatientRegistrationStudycode() {
		return patientRegistrationStudycode;
	}

	public void setPatientRegistrationStudycode(Integer patientRegistrationStudycode) {
		this.patientRegistrationStudycode = patientRegistrationStudycode;
	}

	@ManyToOne(fetch=FetchType.LAZY)
	@JsonBackReference
	@JoinColumn(name="patient_registration_accttype", referencedColumnName="account_type_id" , insertable=false, updatable=false)
	private AccountType ptAccType;
	
	@OneToMany(cascade=CascadeType.ALL,mappedBy="patientRegistration")
	@LazyCollection(LazyCollectionOption.FALSE)
	@JsonManagedReference
	List<AuthorizationMaster> authMaster;
	
	public List<AuthorizationMaster> getAuthMaster() {
		return authMaster;
	}

	public void setAuthMaster(List<AuthorizationMaster> authMaster) {
		this.authMaster = authMaster;
	}

	public String getPatientRegistrationMidInitial() {
		return patientRegistrationMidInitial;
	}

	public void setPatientRegistrationMidInitial(String patientRegistrationMidInitial) {
		this.patientRegistrationMidInitial = patientRegistrationMidInitial;
	}

	public AccountType getPtAccType() {
		return ptAccType;
	}

	public void setPtAccType(AccountType ptAccType) {
		this.ptAccType = ptAccType;
	}

	public String getPatientRegistrationPhoneNo() {
		return patientRegistrationPhoneNo;
	}

	public void setPatientRegistrationPhoneNo(String patientRegistrationPhoneNo) {
		this.patientRegistrationPhoneNo = patientRegistrationPhoneNo;
	}
	
	public Timestamp getPatientRegistrationDobtime() {
		return patientRegistrationDobtime;
	}

	public void setPatientRegistrationDobtime(Timestamp patientRegistrationDobtime) {
		this.patientRegistrationDobtime = patientRegistrationDobtime;
	}

	public Chart getChartTable() {
		return chartTable;
	}

	public void setChartTable(Chart chartTable) {
		this.chartTable = chartTable;
	}

	@ManyToOne(fetch=FetchType.LAZY)
	@JsonManagedReference
	@NotFound(action=NotFoundAction.IGNORE)
	@JoinColumn(name="patient_registration_principal_doctor", referencedColumnName="emp_profile_empid" , insertable=false, updatable=false)
	private EmployeeProfile empProfile;

	public EmployeeProfile getEmpProfile() {
		return empProfile;
	}

	public void setEmpProfile(EmployeeProfile empProfile) {
		this.empProfile = empProfile;
	}
	
	@OneToOne(cascade=CascadeType.ALL,fetch=FetchType.LAZY)
	@JsonBackReference
	@JoinColumn(name="patient_registration_id", referencedColumnName="chart_patientid" , insertable=false, updatable=false)
	private Chart chartTable;
	
	@OneToMany(cascade=CascadeType.ALL,mappedBy="patientRegistrationTable")
	@JsonManagedReference
	List<Chart> alertTable;
	
	@ManyToOne(cascade=CascadeType.ALL,fetch=FetchType.LAZY)
	@JoinColumn(name="patient_registration_id",referencedColumnName="chart_patientid",insertable=false,updatable=false)
	@JsonManagedReference
	private Chart chartIds;
	
	public List<Chart> getAlertTable() {
		return alertTable;
	}

	public void setAlertTable(List<Chart> alertTable) {
		this.alertTable = alertTable;
	}
	
	public List<Hl7ResultInbox> getHl7ResultInbox() {
		return hl7ResultInbox;
	}

	public void setHl7ResultInbox(List<Hl7ResultInbox> hl7ResultInbox) {
		this.hl7ResultInbox = hl7ResultInbox;
	}
	@ManyToOne(cascade=CascadeType.ALL,fetch=FetchType.LAZY)
	@JoinColumn(name="patient_registration_id",referencedColumnName="patient_id",insertable=false,updatable=false)
	@JsonManagedReference
	NoMatchFoundPatient nomatchfoundpatient;
	
	@ManyToOne(cascade=CascadeType.ALL,fetch=FetchType.LAZY)
	@JoinColumn(name="patient_registration_pos_id",referencedColumnName="pos_table_relation_id",insertable=false,updatable=false)
	@JsonManagedReference
	PosTable posTable;
	

	
	public PosTable getPosTable() {
		return posTable;
	}

	public void setPosTable(PosTable posTable) {
		this.posTable = posTable;
	}

//	public Reference getReference() {
//		return reference;
//	}
//
//	public void setReference(Reference reference) {
//		this.reference = reference;
//	}

	public NoMatchFoundPatient getNomatchfoundpatient() {
		return nomatchfoundpatient;
	}

	public void setNomatchfoundpatient(NoMatchFoundPatient nomatchfoundpatient) {
		this.nomatchfoundpatient = nomatchfoundpatient;
	}

	public Guarantor getGuaranatorDetails() {
		return guaranatorDetails;
	}

	public void setGuaranatorDetails(Guarantor guaranatorDetails) {
		this.guaranatorDetails = guaranatorDetails;
	}

	public PatientRegistration(){
		
	}
	
	@OneToMany(mappedBy="patientRegistration")
	@JsonManagedReference
	List<QualityMeasuresPatientEntries> qualityMeasuresPatientEntries;

	@OneToMany(mappedBy="patientRegistration1")
	@JsonManagedReference
	List<ProblemList> problemList;
	
}
