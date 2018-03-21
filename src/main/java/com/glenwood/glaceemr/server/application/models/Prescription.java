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
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.hibernate.annotations.JoinColumnOrFormula;
import org.hibernate.annotations.JoinColumnsOrFormulas;
import org.hibernate.annotations.JoinFormula;
import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.glenwood.glaceemr.server.utils.JsonDateSerializer;
import com.glenwood.glaceemr.server.utils.JsonTimestampSerializer;

@Entity
@Table(name = "doc_presc")
public class Prescription implements Serializable{
	

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "doc_presc_doc_presc_id_seq")
	@SequenceGenerator(name = "doc_presc_doc_presc_id_seq", sequenceName = "doc_presc_doc_presc_id_seq", allocationSize = 1)
	@Column(name="doc_presc_id")
	private Integer docPrescId;

	@Column(name="doc_presc_encounter_id")
	private Integer docPrescEncounterId;

	@Column(name="doc_presc_status")
	private Integer docPrescStatus;

	@JsonSerialize(using = JsonDateSerializer.class)
	@Column(name="doc_presc_start_date")
	private Date docPrescStartDate;
	
	@NotFound(action=NotFoundAction.IGNORE)
	@ManyToOne(cascade=CascadeType.ALL,fetch=FetchType.LAZY)
	@JoinColumn(name="doc_presc_provider_id",referencedColumnName="emp_profile_empid",insertable=false, updatable=false)
	@JsonManagedReference
	EmployeeProfile empprofile;
	
	
	@ManyToOne(cascade=CascadeType.ALL,fetch=FetchType.LAZY)
	@JoinColumn(name="doc_presc_schedule1",referencedColumnName="drug_schedule_name",insertable=false, updatable=false)
	@JsonManagedReference
	DrugSchedule drugSchedule;
	
	@ManyToOne(cascade=CascadeType.ALL,fetch=FetchType.LAZY)
	@JoinColumn(name="doc_presc_encounter_id",referencedColumnName="encounter_id",insertable=false,updatable=false)
	@NotFound(action=NotFoundAction.IGNORE)
	@JsonManagedReference
	Encounter encounter;
	
	@OneToMany(mappedBy="prescription", fetch=FetchType.LAZY)
	@JsonManagedReference
	List<MedsAdminPlan> medsAdminPlan;
	
	 @ManyToOne(cascade=CascadeType.ALL,fetch=FetchType.LAZY)
     @JoinColumn(name="doc_presc_ndc_code",referencedColumnName="pkg_product_id",insertable=false, updatable=false)
     @JsonManagedReference
     NdcPkgProduct ndcPkgProduct;
	
	public Encounter getEncounter() {
		return encounter;
	}

	public void setEncounter(Encounter encounter) {
		this.encounter = encounter;
	}

	public MedStatus getMedstatus() {
		return medstatus;
	}

	public void setMedstatus(MedStatus medstatus) {
		this.medstatus = medstatus;
	}

	public List<MedsAdminPlan> getMedsAdminPlan() {
		return medsAdminPlan;
	}

	public void setMedsAdminPlan(List<MedsAdminPlan> medsAdminPlan) {
		this.medsAdminPlan = medsAdminPlan;
	}

	public String getRxquantity() {
		return rxquantity;
	}

	public void setRxquantity(String rxquantity) {
		this.rxquantity = rxquantity;
	}

	public String getRxname() {
		return rxname;
	}

	public void setRxname(String rxname) {
		this.rxname = rxname;
	}

	public String getRxstrength() {
		return rxstrength;
	}

	public void setRxstrength(String rxstrength) {
		this.rxstrength = rxstrength;
	}

	public String getNoofrefills() {
		return noofrefills;
	}

	public void setNoofrefills(String noofrefills) {
		this.noofrefills = noofrefills;
	}

	public String getRxfreq() {
		return rxfreq;
	}

	public void setRxfreq(String rxfreq) {
		this.rxfreq = rxfreq;
	}

	public String getRxform() {
		return rxform;
	}

	public void setRxform(String rxform) {
		this.rxform = rxform;
	}

	public Integer getRxroute() {
		return rxroute;
	}

	public void setRxroute(Integer rxroute) {
		this.rxroute = rxroute;
	}

	public String getNoofdays() {
		return noofdays;
	}

	public void setNoofdays(String noofdays) {
		this.noofdays = noofdays;
	}

	public String getRxquantityunits() {
		return rxquantityunits;
	}

	public void setRxquantityunits(String rxquantityunits) {
		this.rxquantityunits = rxquantityunits;
	}

	@ManyToOne(cascade=CascadeType.ALL,fetch=FetchType.LAZY)
	@JoinColumn(name="doc_presc_status",referencedColumnName="med_status_id",insertable=false, updatable=false)
	@JsonManagedReference
	MedStatus medstatus;

/*	@ManyToOne(cascade=CascadeType.ALL,fetch=FetchType.LAZY)
	@JoinColumn(name="doc_presc_status",referencedColumnName="med_status_id",insertable=false, updatable=false)
	@JsonManagedReference
	MedStatus medstatus;
	
	@ManyToOne(cascade=CascadeType.ALL,fetch=FetchType.LAZY)
	@JoinColumn(name="doc_presc_ndc_code",referencedColumnName="ndc_code",insertable=false, updatable=false)
	@JsonManagedReference
	NdcDrugBrandMap ndcdrugbrandmap;
	
	
	@ManyToOne(cascade=CascadeType.ALL,fetch=FetchType.LAZY)
	@JoinColumn(name="doc_presc_ndc_code",referencedColumnName="pkg_product_id",insertable=false, updatable=false)
	@JsonManagedReference
	NdcPkgProduct ndcPkgProduct;*/
	
	
	@ManyToOne(cascade=CascadeType.ALL,fetch=FetchType.LAZY)
	@JoinColumn(name="doc_presc_ndc_code",referencedColumnName="ndc_code",insertable=false, updatable=false)
	@JsonManagedReference
	NdcDrugBrandMap ndcdrugbrandmap;
	
	
	
	public NdcDrugBrandMap getNdcdrugbrandmap() {
		return ndcdrugbrandmap;
	}

	public void setNdcdrugbrandmap(NdcDrugBrandMap ndcdrugbrandmap) {
		this.ndcdrugbrandmap = ndcdrugbrandmap;
	}

	public EmployeeProfile getEmpprofile() {
		return empprofile;
	}

	public void setEmpprofile(EmployeeProfile empprofile) {
		this.empprofile = empprofile;
	}

	public Integer getDocPrescId() {
		return docPrescId;
	}

	public void setDocPrescId(Integer docPrescId) {
		this.docPrescId = docPrescId;
	}

	public Integer getDocPrescEncounterId() {
		return docPrescEncounterId;
	}

	public void setDocPrescEncounterId(Integer docPrescEncounterId) {
		this.docPrescEncounterId = docPrescEncounterId;
	}

	public Integer getDocPrescStatus() {
		return docPrescStatus;
	}

	public void setDocPrescStatus(Integer docPrescStatus) {
		this.docPrescStatus = docPrescStatus;
	}

	public Date getDocPrescStartDate() {
		return docPrescStartDate;
	}

	public void setDocPrescStartDate(Date docPrescStartDate) {
		this.docPrescStartDate = docPrescStartDate;
	}

	/*public Date getDocPrescStopDate() {
		return docPrescStopDate;
	}

	public void setDocPrescStopDate(Date docPrescStopDate) {
		this.docPrescStopDate = docPrescStopDate;
	}
*/
	public Boolean getDocPrescIsChronic() {
		return docPrescIsChronic;
	}

	public void setDocPrescIsChronic(Boolean docPrescIsChronic) {
		this.docPrescIsChronic = docPrescIsChronic;
	}

	public Boolean getDocPrescToPrintFax() {
		return docPrescToPrintFax;
	}

	public void setDocPrescToPrintFax(Boolean docPrescToPrintFax) {
		this.docPrescToPrintFax = docPrescToPrintFax;
	}

	public Integer getDocPrescOrderedBy() {
		return docPrescOrderedBy;
	}

	public void setDocPrescOrderedBy(Integer docPrescOrderedBy) {
		this.docPrescOrderedBy = docPrescOrderedBy;
	}

	public Timestamp getDocPrescOrderedDate() {
		return docPrescOrderedDate;
	}

	public void setDocPrescOrderedDate(Timestamp docPrescOrderedDate) {
		this.docPrescOrderedDate = docPrescOrderedDate;
	}

	public Integer getDocPrescLastModifiedBy() {
		return docPrescLastModifiedBy;
	}

	public void setDocPrescLastModifiedBy(Integer docPrescLastModifiedBy) {
		this.docPrescLastModifiedBy = docPrescLastModifiedBy;
	}

	public Timestamp getDocPrescLastModifiedDate() {
		return docPrescLastModifiedDate;
	}

	public void setDocPrescLastModifiedDate(Timestamp docPrescLastModifiedDate) {
		this.docPrescLastModifiedDate = docPrescLastModifiedDate;
	}

	public String getDocPrescIntake() {
		return docPrescIntake;
	}

	public void setDocPrescIntake(String docPrescIntake) {
		this.docPrescIntake = docPrescIntake;
	}

	public String getDocPrescQuantity() {
		return rxquantity;
	}

	public void setDocPrescQuantity(String docPrescQuantity) {
		this.rxquantity = docPrescQuantity;
	}

	public String getDocPrescRxName() {
		return rxname;
	}

	public void setDocPrescRxName(String rxname) {
		this.rxname = rxname;
	}

	public String getDocPrescDosageWithUnit() {
		return rxstrength;
	}

	public void setDocPrescDosageWithUnit(String docPrescDosageWithUnit) {
		this.rxstrength = docPrescDosageWithUnit;
	}

	public String getDocPrescUnit() {
		return docPrescUnit;
	}

	public void setDocPrescUnit(String docPrescUnit) {
		this.docPrescUnit = docPrescUnit;
	}

	public String getDocPrescNoOfRefills() {
		return noofrefills;
	}

	public void setDocPrescNoOfRefills(String docPrescNoOfRefills) {
		this.noofrefills = docPrescNoOfRefills;
	}

	public String getDocPrescRoute() {
		return docPrescRoute;
	}

	public void setDocPrescRoute(String docPrescRoute) {
		this.docPrescRoute = docPrescRoute;
	}

	public String getDocPrescDuration() {
		return docPrescDuration;
	}

	public void setDocPrescDuration(String docPrescDuration) {
		this.docPrescDuration = docPrescDuration;
	}

	public String getDocPrescSchedule1() {
		return rxfreq;
	}

	public void setDocPrescSchedule1(String docPrescSchedule1) {
		this.rxfreq = docPrescSchedule1;
	}

	public String getDocPrescSchedule2() {
		return docPrescSchedule2;
	}

	public void setDocPrescSchedule2(String docPrescSchedule2) {
		this.docPrescSchedule2 = docPrescSchedule2;
	}

	public String getDocPrescForm() {
		return rxform;
	}

	public void setDocPrescForm(String docPrescForm) {
		this.rxform = docPrescForm;
	}

	public String getDocPrescComments() {
		return docPrescComments;
	}

	public void setDocPrescComments(String docPrescComments) {
		this.docPrescComments = docPrescComments;
	}

	public Long getDocPrescPatientId() {
		return docPrescPatientId;
	}

	public void setDocPrescPatientId(Long docPrescPatientId) {
		this.docPrescPatientId = docPrescPatientId;
	}

	public Integer getDocPrescRouteId() {
		return rxroute;
	}

	public void setDocPrescRouteId(Integer docPrescRouteId) {
		this.rxroute = docPrescRouteId;
	}

	public Boolean getDocPrescIsActive() {
		return docPrescIsActive;
	}

	public void setDocPrescIsActive(Boolean docPrescIsActive) {
		this.docPrescIsActive = docPrescIsActive;
	}

	public Boolean getDocPrescAllowSubstitution() {
		return docPrescAllowSubstitution;
	}

	public void setDocPrescAllowSubstitution(Boolean docPrescAllowSubstitution) {
		this.docPrescAllowSubstitution = docPrescAllowSubstitution;
	}

	public Integer getDocPrescIsEPrescSent() {
		return docPrescIsEPrescSent;
	}

	public void setDocPrescIsEPrescSent(Integer docPrescIsEPrescSent) {
		this.docPrescIsEPrescSent = docPrescIsEPrescSent;
	}

	public String getDocPrescSsInboxId() {
		return docPrescSsInboxId;
	}

	public void setDocPrescSsInboxId(String docPrescSsInboxId) {
		this.docPrescSsInboxId = docPrescSsInboxId;
	}

	public String getDocPrescDxForRx() {
		return docPrescDxForRx;
	}

	public void setDocPrescDxForRx(String docPrescDxForRx) {
		this.docPrescDxForRx = docPrescDxForRx;
	}

	public String getDocPrescLotNumber() {
		return docPrescLotNumber;
	}

	public void setDocPrescLotNumber(String docPrescLotNumber) {
		this.docPrescLotNumber = docPrescLotNumber;
	}

	/*public Timestamp getDocPrescExpireDate() {
		return docPrescExpireDate;
	}

	public void setDocPrescExpireDate(Timestamp docPrescExpireDate) {
		this.docPrescExpireDate = docPrescExpireDate;
	}*/

	public String getDocPrescDays() {
		return noofdays;
	}

	public void setDocPrescDays(String docPrescDays) {
		this.noofdays = docPrescDays;
	}

	public Integer getDocPrescIsEPresc() {
		return docPrescIsEPresc;
	}

	public void setDocPrescIsEPresc(Integer docPrescIsEPresc) {
		this.docPrescIsEPresc = docPrescIsEPresc;
	}

	public Integer getDocPrescUniqueId() {
		return docPrescUniqueId;
	}

	public void setDocPrescUniqueId(Integer docPrescUniqueId) {
		this.docPrescUniqueId = docPrescUniqueId;
	}

	public String getDocPrescBrandName() {
		return docPrescBrandName;
	}

	public void setDocPrescBrandName(String docPrescBrandName) {
		this.docPrescBrandName = docPrescBrandName;
	}

	public String getDocPrescInstruction() {
		return docPrescInstruction;
	}

	public void setDocPrescInstruction(String docPrescInstruction) {
		this.docPrescInstruction = docPrescInstruction;
	}

	public Integer getDocPrescSetid() {
		return docPrescSetid;
	}

	public void setDocPrescSetid(Integer docPrescSetid) {
		this.docPrescSetid = docPrescSetid;
	}

	public String getDocPrescAddsig() {
		return docPrescAddsig;
	}

	public void setDocPrescAddsig(String docPrescAddsig) {
		this.docPrescAddsig = docPrescAddsig;
	}

	public String getDocPrescQuantityUnits() {
		return rxquantityunits;
	}

	public void setDocPrescQuantityUnits(String docPrescQuantityUnits) {
		this.rxquantityunits = docPrescQuantityUnits;
	}

	public Integer getDocPrescProviderId() {
		return docPrescProviderId;
	}

	public void setDocPrescProviderId(Integer docPrescProviderId) {
		this.docPrescProviderId = docPrescProviderId;
	}

	public String getDocPrescReminderStatus() {
		return docPrescReminderStatus;
	}

	public void setDocPrescReminderStatus(String docPrescReminderStatus) {
		this.docPrescReminderStatus = docPrescReminderStatus;
	}

	public Boolean getDocPrescToFax() {
		return docPrescToFax;
	}

	public void setDocPrescToFax(Boolean docPrescToFax) {
		this.docPrescToFax = docPrescToFax;
	}

	public String getDocPrescNdcCode() {
		return docPrescNdcCode;
	}

	public void setDocPrescNdcCode(String docPrescNdcCode) {
		this.docPrescNdcCode = docPrescNdcCode;
	}

	public String getDocPrescExternalSourceInfo() {
		return docPrescExternalSourceInfo;
	}

	public void setDocPrescExternalSourceInfo(String docPrescExternalSourceInfo) {
		this.docPrescExternalSourceInfo = docPrescExternalSourceInfo;
	}

	public Integer getDocPrescMapEligibilityDescId() {
		return docPrescMapEligibilityDescId;
	}

	public void setDocPrescMapEligibilityDescId(Integer docPrescMapEligibilityDescId) {
		this.docPrescMapEligibilityDescId = docPrescMapEligibilityDescId;
	}

	public String getDocPrescFormularyStatus() {
		return docPrescFormularyStatus;
	}

	public void setDocPrescFormularyStatus(String docPrescFormularyStatus) {
		this.docPrescFormularyStatus = docPrescFormularyStatus;
	}

	public String getDocPrescCopayDetail() {
		return docPrescCopayDetail;
	}

	public void setDocPrescCopayDetail(String docPrescCopayDetail) {
		this.docPrescCopayDetail = docPrescCopayDetail;
	}

	public String getDocPrescCoverageStatus() {
		return docPrescCoverageStatus;
	}

	public void setDocPrescCoverageStatus(String docPrescCoverageStatus) {
		this.docPrescCoverageStatus = docPrescCoverageStatus;
	}

	public Integer getDocPrescFaxSentId() {
		return docPrescFaxSentId;
	}

	public void setDocPrescFaxSentId(Integer docPrescFaxSentId) {
		this.docPrescFaxSentId = docPrescFaxSentId;
	}

	public Boolean getDocPrescIsPrinted() {
		return docPrescIsPrinted;
	}

	public void setDocPrescIsPrinted(Boolean docPrescIsPrinted) {
		this.docPrescIsPrinted = docPrescIsPrinted;
	}

	public String getDocPrescMedInternalRootSource() {
		return docPrescMedInternalRootSource;
	}

	public void setDocPrescMedInternalRootSource(
			String docPrescMedInternalRootSource) {
		this.docPrescMedInternalRootSource = docPrescMedInternalRootSource;
	}

	public Boolean getDocPrescIsOverridden() {
		return docPrescIsOverridden;
	}

	public void setDocPrescIsOverridden(Boolean docPrescIsOverridden) {
		this.docPrescIsOverridden = docPrescIsOverridden;
	}

	public String getDocPrescMonograph() {
		return docPrescMonograph;
	}

	public void setDocPrescMonograph(String docPrescMonograph) {
		this.docPrescMonograph = docPrescMonograph;
	}

	public String getDocPrescLeaflet() {
		return docPrescLeaflet;
	}

	public void setDocPrescLeaflet(String docPrescLeaflet) {
		this.docPrescLeaflet = docPrescLeaflet;
	}

	public String getDocPrescMedQuitReason() {
		return docPrescMedQuitReason;
	}

	public void setDocPrescMedQuitReason(String docPrescMedQuitReason) {
		this.docPrescMedQuitReason = docPrescMedQuitReason;
	}

	public Timestamp getDocPrescInactivatedOn() {
		return docPrescInactivatedOn;
	}

	public void setDocPrescInactivatedOn(Timestamp docPrescInactivatedOn) {
		this.docPrescInactivatedOn = docPrescInactivatedOn;
	}

	public Integer getDocPrescInactivatedBy() {
		return docPrescInactivatedBy;
	}

	public void setDocPrescInactivatedBy(Integer docPrescInactivatedBy) {
		this.docPrescInactivatedBy = docPrescInactivatedBy;
	}

	public Integer getDocPrescLeafId() {
		return docPrescLeafId;
	}

	public void setDocPrescLeafId(Integer docPrescLeafId) {
		this.docPrescLeafId = docPrescLeafId;
	}

	public Boolean getDocPrescIsUncoded() {
		return docPrescIsUncoded;
	}

	public void setDocPrescIsUncoded(Boolean docPrescIsUncoded) {
		this.docPrescIsUncoded = docPrescIsUncoded;
	}

	public String getDocPrescHistoryUnitsAddEdit() {
		return docPrescHistoryUnitsAddEdit;
	}

	public void setDocPrescHistoryUnitsAddEdit(String docPrescHistoryUnitsAddEdit) {
		this.docPrescHistoryUnitsAddEdit = docPrescHistoryUnitsAddEdit;
	}

	public String getDocPrescUnitsAddEdit() {
		return docPrescUnitsAddEdit;
	}

	public void setDocPrescUnitsAddEdit(String docPrescUnitsAddEdit) {
		this.docPrescUnitsAddEdit = docPrescUnitsAddEdit;
	}

	public Boolean getDocPrescIsMedSup() {
		return docPrescIsMedSup;
	}

	public void setDocPrescIsMedSup(Boolean docPrescIsMedSup) {
		this.docPrescIsMedSup = docPrescIsMedSup;
	}

	public Integer getDocPrescIsCancelrxSent() {
		return docPrescIsCancelrxSent;
	}

	public void setDocPrescIsCancelrxSent(Integer docPrescIsCancelrxSent) {
		this.docPrescIsCancelrxSent = docPrescIsCancelrxSent;
	}

	public String getDocPrescDxQualifierCode() {
		return docPrescDxQualifierCode;
	}

	public void setDocPrescDxQualifierCode(String docPrescDxQualifierCode) {
		this.docPrescDxQualifierCode = docPrescDxQualifierCode;
	}

	public String getDocPrescRxnormCode() {
		return docPrescRxnormCode;
	}

	public void setDocPrescRxnormCode(String docPrescRxnormCode) {
		this.docPrescRxnormCode = docPrescRxnormCode;
	}

	public String getDocPrescBaseDose() {
		return docPrescBaseDose;
	}

	public void setDocPrescBaseDose(String docPrescBaseDose) {
		this.docPrescBaseDose = docPrescBaseDose;
	}

	public String getDocPrescBaseDoseUnits() {
		return docPrescBaseDoseUnits;
	}

	public void setDocPrescBaseDoseUnits(String docPrescBaseDoseUnits) {
		this.docPrescBaseDoseUnits = docPrescBaseDoseUnits;
	}

	@Column(name="doc_presc_stop_date")
	private Date docPrescStopDate;

	@Column(name="doc_presc_is_chronic")
	private Boolean docPrescIsChronic;

	@Column(name="doc_presc_to_print_fax")
	private Boolean docPrescToPrintFax;

	@Column(name="doc_presc_ordered_by")
	private Integer docPrescOrderedBy;

	@JsonSerialize(using = JsonTimestampSerializer.class)
	@Column(name="doc_presc_ordered_date")
	private Timestamp docPrescOrderedDate;

	@Column(name="doc_presc_last_modified_by")
	private Integer docPrescLastModifiedBy;

	@JsonSerialize(using = JsonTimestampSerializer.class)
	@Column(name="doc_presc_last_modified_date")
	private Timestamp docPrescLastModifiedDate;

	@Column(name="doc_presc_intake")
	private String docPrescIntake;

	@Column(name="doc_presc_quantity")
	private String rxquantity;

	@Column(name="doc_presc_rx_name")
	private String rxname;

	@Column(name="doc_presc_dosage_with_unit")
	private String rxstrength;

	@Column(name="doc_presc_unit")
	private String docPrescUnit;

	@Column(name="doc_presc_no_of_refills")
	private String noofrefills;

	@Column(name="doc_presc_route")
	private String docPrescRoute;

	@Column(name="doc_presc_duration")
	private String docPrescDuration;

	@Column(name="doc_presc_schedule1")
	private String rxfreq;

	@Column(name="doc_presc_schedule2")
	private String docPrescSchedule2;

	@Column(name="doc_presc_form")
	private String rxform;

	@Column(name="doc_presc_comments")
	private String docPrescComments;

	@Column(name="doc_presc_patient_id")
	private Long docPrescPatientId;

	@Column(name="doc_presc_route_id")
	private Integer rxroute;

	@Column(name="doc_presc_is_active")
	private Boolean docPrescIsActive;

	@Column(name="doc_presc_allow_substitution")
	private Boolean docPrescAllowSubstitution;

	@Column(name="doc_presc_is_e_presc_sent")
	private Integer docPrescIsEPrescSent;

	@Column(name="doc_presc_ss_inbox_id")
	private String docPrescSsInboxId;

	@Column(name="doc_presc_dx_for_rx")
	private String docPrescDxForRx;

	@Column(name="doc_presc_lot_number")
	private String docPrescLotNumber;

	@JsonSerialize(using = JsonTimestampSerializer.class)
	@Column(name="doc_presc_expire_date")
	private Timestamp docPrescExpireDate;

	@Column(name="doc_presc_days")
	private String noofdays;

	@Column(name="doc_presc_is_e_presc")
	private Integer docPrescIsEPresc;

	@Column(name="doc_presc_unique_id")
	private Integer docPrescUniqueId;

	@Column(name="doc_presc_brand_name")
	private String docPrescBrandName;

	@Column(name="doc_presc_instruction")
	private String docPrescInstruction;

	@Column(name="doc_presc_setid")
	private Integer docPrescSetid;

	@Column(name="doc_presc_addsig")
	private String docPrescAddsig;

	@Column(name="doc_presc_quantity_units")
	private String rxquantityunits;

	@Column(name="doc_presc_provider_id")
	private Integer docPrescProviderId;

	@Column(name="doc_presc_reminder_status")
	private String docPrescReminderStatus;

	@Column(name="doc_presc_to_fax")
	private Boolean docPrescToFax;

	@Column(name="doc_presc_ndc_code")
	private String docPrescNdcCode;

	@Column(name="doc_presc_external_source_info")
	private String docPrescExternalSourceInfo;

	@Column(name="doc_presc_map_eligibility_desc_id")
	private Integer docPrescMapEligibilityDescId;

	@Column(name="doc_presc_formulary_status")
	private String docPrescFormularyStatus;

	@Column(name="doc_presc_copay_detail")
	private String docPrescCopayDetail;

	@Column(name="doc_presc_coverage_status")
	private String docPrescCoverageStatus;

	@Column(name="doc_presc_fax_sent_id")
	private Integer docPrescFaxSentId;

	@Column(name="doc_presc_is_printed")
	private Boolean docPrescIsPrinted;

	@Column(name="doc_presc_med_internal_root_source")
	private String docPrescMedInternalRootSource;

	@Column(name="doc_presc_is_overridden")
	private Boolean docPrescIsOverridden;

	@Column(name="doc_presc_monograph")
	private String docPrescMonograph;

	@Column(name="doc_presc_leaflet")
	private String docPrescLeaflet;

	@Column(name="doc_presc_med_quit_reason")
	private String docPrescMedQuitReason;

	@JsonSerialize(using = JsonTimestampSerializer.class)
	@Column(name="doc_presc_inactivated_on")
	private Timestamp docPrescInactivatedOn;

	@Column(name="doc_presc_inactivated_by")
	private Integer docPrescInactivatedBy;

	@Column(name="doc_presc_leaf_id")
	private Integer docPrescLeafId;

	@Column(name="doc_presc_is_uncoded")
	private Boolean docPrescIsUncoded;

	@Column(name="doc_presc_history_units_add_edit")
	private String docPrescHistoryUnitsAddEdit;

	@Column(name="doc_presc_units_add_edit")
	private String docPrescUnitsAddEdit;

	@Column(name="doc_presc_is_med_sup")
	private Boolean docPrescIsMedSup;

	@Column(name="doc_presc_is_cancelrx_sent")
	private Integer docPrescIsCancelrxSent;

	@Column(name="doc_presc_dx_qualifier_code")
	private String docPrescDxQualifierCode;

	@Column(name="doc_presc_rxnorm_code")
	private String docPrescRxnormCode;

	@Column(name="doc_presc_rxnorm_cd")
	private String docPrescRxnormCD;
	
	@Column(name="doc_presc_base_dose")
	private String docPrescBaseDose;

	@Column(name="doc_presc_base_dose_units")
	private String docPrescBaseDoseUnits;

	@Column(name="doc_presc_print_time")
	private String docPrescPrintTime;

	public String getDocPrescPrintTime() {
		return docPrescPrintTime;
	}

	public void setDocPrescPrintTime(String docPrescPrintTime) {
		this.docPrescPrintTime = docPrescPrintTime;
	}
	
	@NotFound(action=NotFoundAction.IGNORE)
	@ManyToOne(cascade=CascadeType.ALL,fetch=FetchType.LAZY)
	@JoinColumn(name="doc_presc_last_modified_by",referencedColumnName="emp_profile_empid",insertable=false, updatable=false)
	@JsonManagedReference
	EmployeeProfile empProfileModify;
	
	@OneToMany(mappedBy="prescription", fetch=FetchType.LAZY)
	@JsonManagedReference
	List<SSOutbox> ssOutbox;

	@NotFound(action=NotFoundAction.IGNORE)
	@ManyToOne(cascade=CascadeType.ALL,fetch=FetchType.LAZY)
	@JoinColumn(name="doc_presc_inactivated_by",referencedColumnName="emp_profile_empid",insertable=false, updatable=false)
	@JsonManagedReference
	EmployeeProfile empProfileInActive;
	
	@ManyToOne(cascade=CascadeType.ALL,fetch=FetchType.LAZY)
	@JoinColumn(name="doc_presc_form",referencedColumnName="drug_form_name",insertable=false, updatable=false)
	@JsonManagedReference
	DrugForm drugForm;
	
	@ManyToOne(cascade=CascadeType.ALL,fetch=FetchType.LAZY)
	//@JoinColumn(name="doc_presc_ss_inbox_id",referencedColumnName="ss_inbox_id",insertable=false, updatable=false)
	@JoinColumnsOrFormulas({ @JoinColumnOrFormula(formula= @JoinFormula(value="doc_presc_ss_inbox_id::integer" , referencedColumnName="ss_inbox_id"))})
	@JsonManagedReference
	SSInbox ssInbox;
	
	@ManyToOne(cascade=CascadeType.ALL,fetch=FetchType.LAZY)
	@JoinColumn(name="doc_presc_quantity_units",referencedColumnName="description",insertable=false, updatable=false)
	@JsonManagedReference
	PrescriptionUnits prescriptionUnits;
	
	@ManyToOne(cascade=CascadeType.ALL,fetch=FetchType.LAZY)
	@JoinColumn(name="doc_presc_map_eligibility_desc_id",referencedColumnName="ss_pat_eligibility_desc_id",insertable=false, updatable=false)
	@JsonManagedReference
	SsPatEligibilityDesc ssPatEligibilityDesc;
	
	@NotFound(action=NotFoundAction.IGNORE)
	@ManyToOne(cascade=CascadeType.ALL,fetch=FetchType.LAZY)
	@JoinColumn(name="doc_presc_ordered_by",referencedColumnName="emp_profile_empid",insertable=false, updatable=false)
	@JsonManagedReference
	EmployeeProfile empProfileOrderBy;
	
	@ManyToOne(cascade=CascadeType.ALL,fetch=FetchType.LAZY)
	@JoinColumn(name="doc_presc_leaf_id",referencedColumnName="leaf_patient_id",insertable=false, updatable=false)
	@JsonManagedReference
	LeafPatient leafPatient;
	
	@ManyToOne(cascade=CascadeType.ALL,fetch=FetchType.LAZY)
	@JoinColumn(name="doc_presc_route_id",referencedColumnName="modified_dose_parent_root_id",insertable=false, updatable=false)
	@JsonManagedReference
	ModifiedDose modifiedDose;
	
	
	public ModifiedDose getModifiedDose() {
		return modifiedDose;
	}

	public void setModifiedDose(ModifiedDose modifiedDose) {
		this.modifiedDose = modifiedDose;
	}

	
	public String getDocPrescRxnormCD() {
		return docPrescRxnormCD;
	}

	public void setDocPrescRxnormCD(String docPrescRxnormCD) {
		this.docPrescRxnormCD = docPrescRxnormCD;
	}

	public LeafPatient getLeafPatient() {
		return leafPatient;
	}

	public void setLeafPatient(LeafPatient leafPatient) {
		this.leafPatient = leafPatient;
	}

	public EmployeeProfile getEmpProfileOrderBy() {
		return empProfileOrderBy;
	}

	public void setEmpProfileOrderBy(EmployeeProfile empProfileOrderBy) {
		this.empProfileOrderBy = empProfileOrderBy;
	}

	public SSInbox getSsInbox() {
		return ssInbox;
	}

	public SsPatEligibilityDesc getSsPatEligibilityDesc() {
		return ssPatEligibilityDesc;
	}

	public void setSsPatEligibilityDesc(SsPatEligibilityDesc ssPatEligibilityDesc) {
		this.ssPatEligibilityDesc = ssPatEligibilityDesc;
	}

	public void setSsInbox(SSInbox ssInbox) {
		this.ssInbox = ssInbox;
	}

	public PrescriptionUnits getPrescriptionUnits() {
		return prescriptionUnits;
	}

	public void setPrescriptionUnits(PrescriptionUnits prescriptionUnits) {
		this.prescriptionUnits = prescriptionUnits;
	}

	public EmployeeProfile getEmpProfileInActive() {
		return empProfileInActive;
	}

	public void setEmpProfileInActive(EmployeeProfile empProfileInActive) {
		this.empProfileInActive = empProfileInActive;
	}

	public EmployeeProfile getEmpProfileModify() {
		return empProfileModify;
	}

	public void setEmpProfileModify(EmployeeProfile empProfileModify) {
		this.empProfileModify = empProfileModify;
	}

	public List<SSOutbox> getSsOutbox() {
		return ssOutbox;
	}

	public void setSsOutbox(List<SSOutbox> ssOutbox) {
		this.ssOutbox = ssOutbox;
	}
}