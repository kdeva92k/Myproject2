package com.glenwood.glaceemr.server.application.models;

import java.io.Serializable;
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

@JsonSerialize(include = JsonSerialize.Inclusion.NON_NULL)
@Entity
@Table(name = "emp_profile")
public class EmployeeProfile implements Serializable {
	
	public EmployeeProfile(){
		
	}
	
	public EmployeeProfile(Integer empProfileEmpid, Integer empProfileLoginid, String empProfileFname, String empProfileLname, String empProfileMi, String empProfileCredentials, String empProfileAddress, String empProfileCity, String empProfileState, String empProfilePhoneno, String empProfileMailid) {
		this.empProfileEmpid= empProfileEmpid;
		this.empProfileLoginid= empProfileLoginid; 
		this.empProfileFname= empProfileFname;
		this.empProfileLname= empProfileLname;
		this.empProfileMi= empProfileMi; 
		this.empProfileCredentials= empProfileCredentials;
		this.empProfileAddress= empProfileAddress;
		this.empProfileCity= empProfileCity;
		this.empProfileState= empProfileState;				
		this.empProfilePhoneno= empProfilePhoneno;
		this.empProfileMailid= empProfileMailid;
	}
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "emp_profile_emp_profile_empid_seq")
	@SequenceGenerator(name = "emp_profile_emp_profile_empid_seq", sequenceName = "emp_profile_emp_profile_empid_seq", allocationSize = 1)
	@Column(name="emp_profile_empid")
	private Integer empProfileEmpid;

	@Column(name="emp_profile_loginid")
	private Integer empProfileLoginid;

	@Column(name="emp_profile_doctorid")
	private String empProfileDoctorid;

	@Column(name="emp_profile_lname")
	private String empProfileLname;

	@Column(name="emp_profile_mi")
	private String empProfileMi;

	@Column(name="emp_profile_fname")
	private String empProfileFname;

	@Column(name="emp_profile_fullname")
	private String empProfileFullname;

	@Column(name="emp_profile_credentials")
	private String empProfileCredentials;

	@Column(name="emp_profile_ssn")
	private String empProfileSsn;

	@Column(name="emp_profile_address")
	private String empProfileAddress;

	@Column(name="emp_profile_city")
	private String empProfileCity;

	@Column(name="emp_profile_state")
	private String empProfileState;

	@Column(name="emp_profile_zip")
	private String empProfileZip;

	@Column(name="emp_profile_phoneno")
	private String empProfilePhoneno;

	@Column(name="emp_profile_mailid")
	private String empProfileMailid;

	@Column(name="emp_profile_sign_tiff")
	private String empProfileSignTiff;

	@Column(name="emp_profile_superioruserid")
	private Integer empProfileSuperioruserid;

	@Column(name="emp_profile_notes")
	private String empProfileNotes;
	
	@Column(name="emp_profile_groupid")
	private Integer empProfileGroupid;

	@Column(name="emp_profile_penwidth")
	private Integer empProfilePenwidth;

	@Column(name="emp_profile_pencolor")
	private Integer empProfilePencolor;

	@Column(name="emp_profile_is_active")
	private Boolean empProfileIsActive;

	@Column(name="emp_profile_status")
	private Integer empProfileStatus;

	@Column(name="h555555")
	private Integer h555555;

	@Column(name="emp_profile_isprevencounter")
	private Boolean empProfileIsprevencounter;

	@Column(name="emp_profile_isrevmedication")
	private Boolean empProfileIsrevmedication;

	@Column(name="emp_profile_privatekey")
	private String empProfilePrivatekey;

	@Column(name="emp_profile_publickey")
	private String empProfilePublickey;

	@Column(name="emp_profile_prescriptionheaderid")
	private Integer empProfilePrescriptionheaderid;

	@Column(name="emp_profile_showservicecharges")
	private Integer empProfileShowservicecharges;

	@Column(name="emp_profile_ssunknown")
	private String empProfileSsunknown;

	@Column(name="emp_profile_visittype")
	private Integer empProfileVisittype;

	@Column(name="emp_profile_needinvoice")
	private Integer empProfileNeedinvoice;

	@JsonSerialize(using = JsonTimestampSerializer.class)
	@Column(name="emp_profile_lastpatchdisabledate")
	private Timestamp empProfileLastpatchdisabledate;

	@Column(name="emp_profile_isemergencyaccess")
	private Boolean empProfileIsemergencyaccess;

	@Column(name="emp_profile_rsa_secure_key")
	private String empProfileRsaSecureKey;

	@Column(name="emp_profile_speciality")
	private Integer empProfileSpeciality;

	@Column(name="emp_profile_direct_mailid")
	private String empProfileDirectMailid;

	@JsonSerialize(using = JsonTimestampSerializer.class)
	@Column(name="emp_profile_modified_date")
	private Timestamp empProfileModifiedDate;

	@Column(name="emp_profile_modified_by")
	private String empProfileModifiedBy;
	
	@Column(name="emp_profile_ctp_number")
	private String empProfileCtpNumber;
	
	@OneToMany(mappedBy="encounter_service_doctor")
	@JsonManagedReference
	List<Encounter> encounterServiceDr;
	
	@OneToMany(mappedBy="empProfile")
	@JsonBackReference
	List<Admission> admission;
	
	@OneToMany(mappedBy="staff_pin_number_details_profileid")
	@JsonManagedReference
	List<StaffPinNumberDetails> staff_pin_number_details;
	
	public Integer getEmpProfileEmpid() {
		return empProfileEmpid;
	}

	public List<Admission> getAdmission() {
		return admission;
	}

	public void setAdmission(List<Admission> admission) {
		this.admission = admission;
	}

	public void setEmpProfileEmpid(Integer empProfileEmpid) {
		this.empProfileEmpid = empProfileEmpid;
	}

	public Integer getEmpProfileLoginid() {
		return empProfileLoginid;
	}

	public void setEmpProfileLoginid(Integer empProfileLoginid) {
		this.empProfileLoginid = empProfileLoginid;
	}

	public String getEmpProfileDoctorid() {
		return empProfileDoctorid;
	}

	public void setEmpProfileDoctorid(String empProfileDoctorid) {
		this.empProfileDoctorid = empProfileDoctorid;
	}

	public String getEmpProfileLname() {
		return empProfileLname;
	}

	public void setEmpProfileLname(String empProfileLname) {
		this.empProfileLname = empProfileLname;
	}

	public String getEmpProfileMi() {
		return empProfileMi;
	}

	public void setEmpProfileMi(String empProfileMi) {
		this.empProfileMi = empProfileMi;
	}

	public String getEmpProfileFname() {
		return empProfileFname;
	}

	public void setEmpProfileFname(String empProfileFname) {
		this.empProfileFname = empProfileFname;
	}

	public String getEmpProfileFullname() {
		return empProfileFullname;
	}

	public void setEmpProfileFullname(String empProfileFullname) {
		this.empProfileFullname = empProfileFullname;
	}

	public String getEmpProfileCredentials() {
		return empProfileCredentials;
	}

	public void setEmpProfileCredentials(String empProfileCredentials) {
		this.empProfileCredentials = empProfileCredentials;
	}

	public String getEmpProfileSsn() {
		return empProfileSsn;
	}

	public void setEmpProfileSsn(String empProfileSsn) {
		this.empProfileSsn = empProfileSsn;
	}

	public String getEmpProfileAddress() {
		return empProfileAddress;
	}

	public void setEmpProfileAddress(String empProfileAddress) {
		this.empProfileAddress = empProfileAddress;
	}

	public String getEmpProfileCity() {
		return empProfileCity;
	}

	public void setEmpProfileCity(String empProfileCity) {
		this.empProfileCity = empProfileCity;
	}

	public String getEmpProfileState() {
		return empProfileState;
	}

	public void setEmpProfileState(String empProfileState) {
		this.empProfileState = empProfileState;
	}

	public String getEmpProfileZip() {
		return empProfileZip;
	}

	public void setEmpProfileZip(String empProfileZip) {
		this.empProfileZip = empProfileZip;
	}

	public String getEmpProfilePhoneno() {
		return empProfilePhoneno;
	}

	public void setEmpProfilePhoneno(String empProfilePhoneno) {
		this.empProfilePhoneno = empProfilePhoneno;
	}

	public String getEmpProfileMailid() {
		return empProfileMailid;
	}

	public void setEmpProfileMailid(String empProfileMailid) {
		this.empProfileMailid = empProfileMailid;
	}

	public String getEmpProfileSignTiff() {
		return empProfileSignTiff;
	}

	public void setEmpProfileSignTiff(String empProfileSignTiff) {
		this.empProfileSignTiff = empProfileSignTiff;
	}

	public Integer getEmpProfileSuperioruserid() {
		return empProfileSuperioruserid;
	}

	public void setEmpProfileSuperioruserid(Integer empProfileSuperioruserid) {
		this.empProfileSuperioruserid = empProfileSuperioruserid;
	}

	public String getEmpProfileNotes() {
		return empProfileNotes;
	}

	public void setEmpProfileNotes(String empProfileNotes) {
		this.empProfileNotes = empProfileNotes;
	}

	public Integer getEmpProfileGroupid() {
		return empProfileGroupid;
	}

	public void setEmpProfileGroupid(Integer empProfileGroupid) {
		this.empProfileGroupid = empProfileGroupid;
	}

	public Integer getEmpProfilePenwidth() {
		return empProfilePenwidth;
	}

	public void setEmpProfilePenwidth(Integer empProfilePenwidth) {
		this.empProfilePenwidth = empProfilePenwidth;
	}

	public Integer getEmpProfilePencolor() {
		return empProfilePencolor;
	}

	public void setEmpProfilePencolor(Integer empProfilePencolor) {
		this.empProfilePencolor = empProfilePencolor;
	}

	public Boolean getEmpProfileIsActive() {
		return empProfileIsActive;
	}

	public void setEmpProfileIsActive(Boolean empProfileIsActive) {
		this.empProfileIsActive = empProfileIsActive;
	}

	public Integer getEmpProfileStatus() {
		return empProfileStatus;
	}

	public void setEmpProfileStatus(Integer empProfileStatus) {
		this.empProfileStatus = empProfileStatus;
	}

	public Integer getH555555() {
		return h555555;
	}

	public void setH555555(Integer h555555) {
		this.h555555 = h555555;
	}

	public Boolean getEmpProfileIsprevencounter() {
		return empProfileIsprevencounter;
	}

	public void setEmpProfileIsprevencounter(Boolean empProfileIsprevencounter) {
		this.empProfileIsprevencounter = empProfileIsprevencounter;
	}

	public Boolean getEmpProfileIsrevmedication() {
		return empProfileIsrevmedication;
	}

	public void setEmpProfileIsrevmedication(Boolean empProfileIsrevmedication) {
		this.empProfileIsrevmedication = empProfileIsrevmedication;
	}

	public String getEmpProfilePrivatekey() {
		return empProfilePrivatekey;
	}

	public void setEmpProfilePrivatekey(String empProfilePrivatekey) {
		this.empProfilePrivatekey = empProfilePrivatekey;
	}

	public String getEmpProfilePublickey() {
		return empProfilePublickey;
	}

	public void setEmpProfilePublickey(String empProfilePublickey) {
		this.empProfilePublickey = empProfilePublickey;
	}

	public Integer getEmpProfilePrescriptionheaderid() {
		return empProfilePrescriptionheaderid;
	}

	public void setEmpProfilePrescriptionheaderid(
			Integer empProfilePrescriptionheaderid) {
		this.empProfilePrescriptionheaderid = empProfilePrescriptionheaderid;
	}

	public Integer getEmpProfileShowservicecharges() {
		return empProfileShowservicecharges;
	}

	public void setEmpProfileShowservicecharges(Integer empProfileShowservicecharges) {
		this.empProfileShowservicecharges = empProfileShowservicecharges;
	}

	public String getEmpProfileSsunknown() {
		return empProfileSsunknown;
	}

	public void setEmpProfileSsunknown(String empProfileSsunknown) {
		this.empProfileSsunknown = empProfileSsunknown;
	}

	public Integer getEmpProfileVisittype() {
		return empProfileVisittype;
	}

	public void setEmpProfileVisittype(Integer empProfileVisittype) {
		this.empProfileVisittype = empProfileVisittype;
	}

	public Integer getEmpProfileNeedinvoice() {
		return empProfileNeedinvoice;
	}

	public void setEmpProfileNeedinvoice(Integer empProfileNeedinvoice) {
		this.empProfileNeedinvoice = empProfileNeedinvoice;
	}

	public Timestamp getEmpProfileLastpatchdisabledate() {
		return empProfileLastpatchdisabledate;
	}

	public void setEmpProfileLastpatchdisabledate(
			Timestamp empProfileLastpatchdisabledate) {
		this.empProfileLastpatchdisabledate = empProfileLastpatchdisabledate;
	}

	public Boolean getEmpProfileIsemergencyaccess() {
		return empProfileIsemergencyaccess;
	}

	public void setEmpProfileIsemergencyaccess(Boolean empProfileIsemergencyaccess) {
		this.empProfileIsemergencyaccess = empProfileIsemergencyaccess;
	}

	public String getEmpProfileRsaSecureKey() {
		return empProfileRsaSecureKey;
	}

	public void setEmpProfileRsaSecureKey(String empProfileRsaSecureKey) {
		this.empProfileRsaSecureKey = empProfileRsaSecureKey;
	}

	public Integer getEmpProfileSpeciality() {
		return empProfileSpeciality;
	}

	public void setEmpProfileSpeciality(Integer empProfileSpeciality) {
		this.empProfileSpeciality = empProfileSpeciality;
	}

	public String getEmpProfileDirectMailid() {
		return empProfileDirectMailid;
	}

	public void setEmpProfileDirectMailid(String empProfileDirectMailid) {
		this.empProfileDirectMailid = empProfileDirectMailid;
	}

	public Timestamp getEmpProfileModifiedDate() {
		return empProfileModifiedDate;
	}

	public void setEmpProfileModifiedDate(Timestamp empProfileModifiedDate) {
		this.empProfileModifiedDate = empProfileModifiedDate;
	}

	public String getEmpProfileModifiedBy() {
		return empProfileModifiedBy;
	}

	public void setEmpProfileModifiedBy(String empProfileModifiedBy) {
		this.empProfileModifiedBy = empProfileModifiedBy;
	}
	
	public List<Encounter> getEncounterServiceDr() {
		return encounterServiceDr;
	}

	public void setEncounterServiceDr(List<Encounter> encounterServiceDr) {
		this.encounterServiceDr = encounterServiceDr;
	}
	public String getEmpProfileCtpNumber() {
		return empProfileCtpNumber;
	}

	public void setEmpProfileCtpNumber(String empProfileCtpNumber) {
		this.empProfileCtpNumber = empProfileCtpNumber;
	}

	@ManyToOne(fetch=FetchType.LAZY, cascade=CascadeType.ALL)
	@JsonManagedReference
	@NotFound(action=NotFoundAction.IGNORE)
	@JoinColumn(name="emp_profile_speciality", referencedColumnName="specialisation_referring_id" , insertable=false, updatable=false)
	SpecialisationReferring specialityTable;

	@OneToMany(cascade=CascadeType.ALL,mappedBy="employeeProfile")
	@LazyCollection(LazyCollectionOption.FALSE)
	@JsonManagedReference
	List<PrescriberDetails> prescriberDetails;
	
	@OneToMany(mappedBy="empProfile")
	@JsonManagedReference
	private List<AlertMonitor> alertMonitor;
	
	public List<AlertMonitor> getAlertMonitor() {
		return alertMonitor;
	}

	public void setAlertMonitor(List<AlertMonitor> alertMonitor) {
		this.alertMonitor = alertMonitor;
	}

	public List<PrescriberDetails> getPrescriberDetails() {
		return prescriberDetails;
	}

	public void setPrescriberDetails(List<PrescriberDetails> prescriberDetails) {
		this.prescriberDetails = prescriberDetails;
	}

	public SpecialisationReferring getSpecialityTable() {
		return specialityTable;
	}

	public void setSpecialityTable(SpecialisationReferring specialityTable) {
		this.specialityTable = specialityTable;
	}
	
	@OneToMany(mappedBy="empProfileTableFullName")
	private List<AttestationStatus> reportingProvider;
	
	@OneToMany(mappedBy="employeetableByCreatedName")
	private List<WarfarinLog> warfarinlogcreatedby;
	
	@OneToMany(mappedBy="employeetableModifiesName")
	private List<WarfarinLog> warfarinlogmodifiedby;
	
	@OneToMany(mappedBy="employeetableReviewedName")
	private List<WarfarinLog> warfarinlogreviewedby;
	
	@OneToMany(mappedBy="employeetableByEnteredName")
	private List<WarfarinLog> warfarinlogenteredby;
	
	@OneToMany(mappedBy ="employeeProfileTable")
    private List<MacraProviderConfiguration> macraProviderConfiguration;
	
	@OneToMany(mappedBy="empProfileTable")
	@JsonManagedReference
	private List<MacraMeasuresRate> macraMeasuresRate;
	
	@OneToMany(mappedBy="empProfile")
	private List<CarePlanLog> carePlanLog;
	
	@OneToMany(mappedBy="empProfileConcernCreatedBy")
	private List<CarePlanConcern> carePlanConcernCreatedBy;
	
	@OneToMany(mappedBy="empProfileConcernModifiedBy")
	private List<CarePlanConcern> carePlanConcernModifiedBy;
	
	@OneToMany(mappedBy="empProfileGoalCreatedBy")
	private List<CarePlanGoal> carePlanGoalCreatedBy;
	
	@OneToMany(mappedBy="empProfileGoalModifiedBy")
	private List<CarePlanGoal> carePlanGoalModifiedBy;
	
	
	@OneToMany(mappedBy="empProfileRecommInterCreatedBy")
	private List<CarePlanRecommendedIntervention> carePlanRecommInterCreatedBy;
	
	@OneToMany(mappedBy="empProfileRecommInterModifiedBy")
	private List<CarePlanRecommendedIntervention> carePlanRecommInterModifiedBy;
	
}