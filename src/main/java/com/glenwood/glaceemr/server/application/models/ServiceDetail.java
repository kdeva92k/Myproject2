package com.glenwood.glaceemr.server.application.models;

import java.sql.Timestamp;
import java.math.BigInteger;
import java.sql.Date;
import java.util.List;

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

import org.hibernate.annotations.JoinColumnOrFormula;
import org.hibernate.annotations.JoinColumnsOrFormulas;
import org.hibernate.annotations.JoinFormula;
import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.glenwood.glaceemr.server.utils.JsonTimestampSerializer;

@Entity
@Table(name = "service_detail")
@JsonIgnoreProperties(ignoreUnknown = true)
public class ServiceDetail {
	@Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "service_detail_service_detail_id_seq")
    @SequenceGenerator(name = "service_detail_service_detail_id_seq", sequenceName = "service_detail_service_detail_id_seq", allocationSize = 1)
	@Column(name="service_detail_id")
	private BigInteger serviceDetailId;

	@Column(name="service_detail_patientid")
	private BigInteger serviceDetailPatientid;

	@Column(name="service_detail_submit_status")
	private String serviceDetailSubmitStatus;

	@Column(name="service_detail_dos")
	private Date serviceDetailDos;

	@Column(name="service_detail_dos_from")
	private Date serviceDetailDosFrom;

	@Column(name="service_detail_dop")
	private Date serviceDetailDop;

	@Column(name="service_detail_cptid")
	private Integer serviceDetailCptid;

	@Column(name="service_detail_modifier1")
	private String serviceDetailModifier1;

	@Column(name="service_detail_comments")
	private String serviceDetailComments;

	@Column(name="service_detail_modifier2")
	private String serviceDetailModifier2;

	@Column(name="service_detail_sdoctorid")
	private Integer serviceDetailSdoctorid;

	@Column(name="service_detail_bdoctorid")
	private Integer serviceDetailBdoctorid;

	@Column(name="service_detail_rdoctorid")
	private Integer serviceDetailRdoctorid;

	@Column(name="service_detail_authid")
	private Long serviceDetailAuthid;

	@Column(name="service_detail_referralid")
	private Long serviceDetailReferralid;

	@Column(name="service_detail_posid")
	private Integer serviceDetailPosid;

	@Column(name="service_detail_unit")
	private Integer serviceDetailUnit;

	@Column(name="service_detail_charges")
	private Double serviceDetailCharges;

	@Column(name="service_detail_copay")
	private Double serviceDetailCopay;

	@Column(name="service_detail_credits")
	private Double serviceDetailCredits;

	@Column(name="service_detail_expected_payments")
	private Double serviceDetailExpectedPayments;

	@Column(name="service_detail_expected_date")
	private Date serviceDetailExpectedDate;

	@Column(name="service_detail_reference")
	private String serviceDetailReference;

	@Column(name="service_detail_bookmark")
	private String serviceDetailBookmark;

	@Column(name="service_detail_source")
	private String serviceDetailSource;

	@Column(name="service_detail_server")
	private String serviceDetailServer;

	@Column(name="service_detail_dx1")
	private String serviceDetailDx1;

	@Column(name="service_detail_dx2")
	private String serviceDetailDx2;

	@Column(name="service_detail_dx3")
	private String serviceDetailDx3;

	@Column(name="service_detail_dx4")
	private String serviceDetailDx4;

	@Column(name="service_detail_dx5")
	private String serviceDetailDx5;

	@Column(name="service_detail_dx6")
	private String serviceDetailDx6;

	@Column(name="service_detail_dx7")
	private String serviceDetailDx7;

	@Column(name="service_detail_dx8")
	private String serviceDetailDx8;

	@Column(name="service_detail_placedby")
	private String serviceDetailPlacedby;

	@Column(name="service_detail_placeddate")
	private Date serviceDetailPlaceddate;

	@Column(name="service_detail_modifiedby")
	private String serviceDetailModifiedby;

	@JsonSerialize(using = JsonTimestampSerializer.class)
	@Column(name="service_detail_modifieddate")
	private Timestamp serviceDetailModifieddate;

	@Column(name="service_detail_primaryins")
	private Integer serviceDetailPrimaryins;

	@Column(name="service_detail_secondaryins")
	private Long serviceDetailSecondaryins;

	@Column(name="service_detail_other_ins")
	private Long serviceDetailOtherIns;

	@Column(name="service_detail_guarantor_id")
	private Long serviceDetailGuarantorId;

	@Column(name="service_detail_primary_claimid")
	private Long serviceDetailPrimaryClaimid;

	@Column(name="service_detail_secondary_claimid")
	private Long serviceDetailSecondaryClaimid;

	@Column(name="service_detail_billing_reason")
	private Integer serviceDetailBillingReason;

	@Column(name="service_detail_billing_status")
	private String serviceDetailBillingStatus;

	@Column(name="service_detail_stepid")
	private Long serviceDetailStepid;

	@Column(name="service_detail_rlu_claim")
	private String serviceDetailRluClaim;

	@Column(name="h555555")
	private Integer h555555;

	@Column(name="service_detail_unknown1")
	private Integer serviceDetailUnknown1;

	@Column(name="service_detail_modifier3")
	private String serviceDetailModifier3;

	@Column(name="service_detail_modifier4")
	private String serviceDetailModifier4;

	@Column(name="service_detail_type_of_service")
	private Integer serviceDetailTypeOfService;

	@Column(name="service_detail_ar_status")
	private String serviceDetailArStatus;

	@Column(name="service_detail_unknown2")
	private Double serviceDetailUnknown2;

	@Column(name="service_detail_unknown3")
	private String serviceDetailUnknown3;

	@Column(name="service_detail_unknown4")
	private Date serviceDetailUnknown4;

	@Column(name="service_detail_unknown5")
	private Double serviceDetailUnknown5;

	@Column(name="version")
	private Long version;

	@Column(name="ruleversion")
	private Long ruleversion;

	@Column(name="valid")
	private Boolean valid;

	@Column(name="servicetype")
	private Integer servicetype;

	@Column(name="prev_est_pat_pay")
	private Double prevEstPatPay;

	@Column(name="est_pat_pay")
	private Double estPatPay;

	@Column(name="est_pat_bal")
	private Double estPatBal;

	@Column(name="isblocked")
	private Boolean isblocked;

	@Column(name="service_detail_scan")
	private Boolean serviceDetailScan;

	@Column(name="service_detail_payment_group")
	private Integer serviceDetailPaymentGroup;

	@Column(name="service_detail_validation_status")
	private Integer serviceDetailValidationStatus;

	@Column(name="service_detail_dualins")
	private Boolean serviceDetailDualins;

	@Column(name="service_detail_second_primary")
	private Long serviceDetailSecondPrimary;

	@Column(name="service_detail_dualins_status")
	private Integer serviceDetailDualinsStatus;

	@Column(name="service_detail_release_stage")
	private Integer serviceDetailReleaseStage;

	@Column(name="service_detail_dontbill",nullable = false, columnDefinition = "integer default 1")
	private Integer serviceDetailDontbill;

	@Column(name="service_detail_anes_starttime")
	private String serviceDetailAnesStarttime;

	@Column(name="service_detail_anes_endtime")
	private String serviceDetailAnesEndtime;

	@Column(name="service_detail_anes_timeline")
	private String serviceDetailAnesTimeline;

	@Column(name="service_detail_hemoglobincnt")
	private String serviceDetailHemoglobincnt;

	@Column(name="service_detail_dls")
	private String serviceDetailDls;

	@Column(name="service_detail_dfs")
	private String serviceDetailDfs;

	@Column(name="service_detail_ndc")
	private String serviceDetailNdc;

	@Column(name="service_detail_drugdosagedetail")
	private String serviceDetailDrugdosagedetail;

	@Column(name="service_detail_msptype")
	private String serviceDetailMsptype;

	@Column(name="service_detail_hosp_admdate")
	private String serviceDetailHospAdmdate;

	@Column(name="service_detail_hosp_disdate")
	private String serviceDetailHospDisdate;

	@Column(name="service_detail_hosp_lmpdate")
	private String serviceDetailHospLmpdate;

	@Column(name="service_detail_injury_date")
	private String serviceDetailInjuryDate;

	@Column(name="service_detail_medrec_number")
	private String serviceDetailMedrecNumber;

	@Column(name="service_detail_dx9")
	private String serviceDetailDx9;

	@Column(name="service_detail_dx10")
	private String serviceDetailDx10;

	@Column(name="service_detail_dx11")
	private String serviceDetailDx11;

	@Column(name="service_detail_dx12")
	private String serviceDetailDx12;

	@Column(name="service_detail_dx13")
	private String serviceDetailDx13;

	@Column(name="service_detail_dx14")
	private String serviceDetailDx14;

	@Column(name="service_detail_dx15")
	private String serviceDetailDx15;

	@Column(name="service_detail_dx16")
	private String serviceDetailDx16;

	@Column(name="service_detail_dx17")
	private String serviceDetailDx17;

	@Column(name="service_detail_dx18")
	private String serviceDetailDx18;

	@Column(name="service_detail_dx19")
	private String serviceDetailDx19;

	@Column(name="service_detail_dx20")
	private String serviceDetailDx20;

	@Column(name="service_detail_interm_flag")
	private Integer serviceDetailIntermFlag;

	@Column(name="service_detail_dxsystem")
	private String serviceDetailDxsystem;
	
	@Column(name="service_detail_dx1desc")
	private String serviceDetailDx1desc;
	
	@Column(name="service_detail_dx2desc")
	private String serviceDetailDx2desc;

	@Column(name="service_detail_dx3desc")
	private String serviceDetailDx3desc;

	@Column(name="service_detail_dx4desc")
	private String serviceDetailDx4desc;
	
	@Column(name="service_detail_dx5desc")
	private String serviceDetailDx5desc;
	
	@Column(name="service_detail_dx6desc")
	private String serviceDetailDx6desc;
	
	@Column(name="service_detail_dx7desc")
	private String serviceDetailDx7desc;
	
	@Column(name="service_detail_dx8desc")
	private String serviceDetailDx8desc;
	
	@Column(name="service_detail_dx9desc")
	private String serviceDetailDx9desc;
	
	@Column(name="service_detail_dx10desc")
	private String serviceDetailDx10desc;
	
	@Column(name="service_detail_dx11desc")
	private String serviceDetailDx11desc;
	
	@Column(name="service_detail_dx12desc")
	private String serviceDetailDx12desc;
	
	@Column(name="service_detail_dx13desc")
	private String serviceDetailDx13desc;
	
	@Column(name="service_detail_dx14desc")
	private String serviceDetailDx14desc;
	
	@Column(name="service_detail_dx15desc")
	private String serviceDetailDx15desc;
	
	@Column(name="service_detail_dx16desc")
	private String serviceDetailDx16desc;

	@Column(name="service_detail_dx17desc")
	private String serviceDetailDx17desc;
	
	@Column(name="service_detail_dx18desc")
	private String serviceDetailDx18desc;
	
	@Column(name="service_detail_dx19desc")
	private String serviceDetailDx19desc;

	@Column(name="service_detail_dx20desc")
	private String serviceDetailDx20desc;
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JsonManagedReference
	@NotFound(action=NotFoundAction.IGNORE)
	@JoinColumn(name="service_detail_cptid", referencedColumnName="cpt_id" , insertable=false, updatable=false)
	private Cpt cpt;
	
	@OneToOne(fetch=FetchType.LAZY)
	@JsonManagedReference
	@JoinColumn(name="service_detail_id", referencedColumnName="associate_service_detail_service_id" , insertable=false, updatable=false)
	private AssociateServiceDetails associateDetails;
	
    @ManyToOne(fetch=FetchType.LAZY)
	@JsonManagedReference
	@JoinColumn(name="service_detail_referralid", referencedColumnName="authorization_referral_id"  , insertable=false, updatable=false)
	private AuthorizationReferral authorizationReferral;


    @ManyToOne(fetch=FetchType.LAZY)
  	@JsonManagedReference
  	@JoinColumn(name="service_detail_posid", referencedColumnName="pos_table_relation_id"  , insertable=false, updatable=false)
  	private PosTable posTable;

    @ManyToOne(fetch=FetchType.LAZY)
  	@JsonManagedReference
  	@JoinColumn(name="service_detail_sdoctorid", referencedColumnName="emp_profile_empid"  , insertable=false, updatable=false)
  	private EmployeeProfile sdoctors;
    
    @ManyToOne(fetch=FetchType.LAZY)
  	@JsonManagedReference
  	@JoinColumn(name="service_detail_bdoctorid", referencedColumnName="emp_profile_empid"  , insertable=false, updatable=false)
  	private EmployeeProfile bdoctors;
    
    @ManyToOne(fetch=FetchType.LAZY)
  	@JsonManagedReference
  	@JoinColumn(name="service_detail_primaryins", referencedColumnName="patient_ins_detail_id" , insertable=false, updatable=false)
  	private PatientInsDetail patientInsDetail;
    
    @ManyToOne(fetch=FetchType.LAZY)
  	@JsonManagedReference
  	@JoinColumn(name="service_detail_secondaryins", referencedColumnName="patient_ins_detail_id" , insertable=false, updatable=false)
  	private PatientInsDetail secInsDetail;
    
	@ManyToOne(fetch=FetchType.LAZY)
	@JsonManagedReference
	@JoinColumn(name="service_detail_dx1",referencedColumnName="icdm_icdcode",insertable=false,updatable=false)
    private Icdm icdm1;
	
    @ManyToOne(fetch=FetchType.LAZY)
    @JsonManagedReference
	@JoinColumn(name="service_detail_dx2",referencedColumnName="icdm_icdcode",insertable=false,updatable=false)
    private Icdm icdm2;
	
    @ManyToOne(fetch=FetchType.LAZY)
	@JsonManagedReference
	@JoinColumn(name="service_detail_dx3",referencedColumnName="icdm_icdcode",insertable=false,updatable=false)
    private Icdm icdm3;
	
    @ManyToOne(fetch=FetchType.LAZY)
	@JsonManagedReference
	@JoinColumn(name="service_detail_dx4",referencedColumnName="icdm_icdcode",insertable=false,updatable=false)
    private Icdm icdm4;
	
    @ManyToOne(fetch=FetchType.LAZY)
	@JsonManagedReference
	@JoinColumn(name="service_detail_dx5",referencedColumnName="icdm_icdcode",insertable=false,updatable=false)
    private Icdm icdm5;
    
    @ManyToOne(fetch=FetchType.LAZY)
	@JsonManagedReference
	@JoinColumn(name="service_detail_dx6",referencedColumnName="icdm_icdcode",insertable=false,updatable=false)
    private Icdm icdm6;
	
    @ManyToOne(fetch=FetchType.LAZY)
	@JsonManagedReference
	@JoinColumn(name="service_detail_dx7",referencedColumnName="icdm_icdcode",insertable=false,updatable=false)
    private Icdm icdm7;
	
    @ManyToOne(fetch=FetchType.LAZY)
	@JsonManagedReference
	@JoinColumn(name="service_detail_dx8",referencedColumnName="icdm_icdcode",insertable=false,updatable=false)
    private Icdm icdm8;
    
    @ManyToOne(fetch=FetchType.LAZY)
    @JsonManagedReference
    @JoinColumn(name="service_detail_submit_status",referencedColumnName="submit_status_code",insertable=false,updatable=false)
    private SubmitStatus submitStatus;
    
    @ManyToOne(fetch=FetchType.LAZY)
	@JsonManagedReference
	@JoinColumn(name="service_detail_patientid", referencedColumnName="chart_patientid" , insertable=false, updatable=false)
	private Chart chartTable;
    
    
    
    public Chart getChartTable() {
		return chartTable;
	}


	public void setChartTable(Chart chartTable) {
		this.chartTable = chartTable;
	}

	public SubmitStatus getSubmitStatus() {
		return submitStatus;
	}


	public void setSubmitStatus(SubmitStatus submitStatus) {
		this.submitStatus = submitStatus;
	}


	public PatientInsDetail getPatientInsDetail() {
		return patientInsDetail;
	}


	public void setPatientInsDetail(PatientInsDetail patientInsDetail) {
		this.patientInsDetail = patientInsDetail;
	}
	 
    public PatientInsDetail getSecInsDetail() {
		return secInsDetail;
	}


	public void setSecInsDetail(PatientInsDetail secInsDetail) {
		this.secInsDetail = secInsDetail;
	}
	
	public EmployeeProfile getBdoctors() {
		return bdoctors;
	}


	public void setBdoctors(EmployeeProfile bdoctors) {
		this.bdoctors = bdoctors;
	}


	public EmployeeProfile getSdoctors() {
		return sdoctors;
	}


	public void setSdoctors(EmployeeProfile sdoctors) {
		this.sdoctors = sdoctors;
	}

	public PosTable getPosTable() {
		return posTable;
	}


	public void setPosTable(PosTable posTable) {
		this.posTable = posTable;
	}
    
	public BigInteger getServiceDetailId() {
		return serviceDetailId;
	}

	public void setServiceDetailId(BigInteger serviceDetailId) {
		this.serviceDetailId = serviceDetailId;
	}

	public BigInteger getServiceDetailPatientid() {
		return serviceDetailPatientid;
	}

	public void setServiceDetailPatientid(BigInteger serviceDetailPatientid) {
		this.serviceDetailPatientid = serviceDetailPatientid;
	}

	public String getServiceDetailSubmitStatus() {
		return serviceDetailSubmitStatus;
	}

	public void setServiceDetailSubmitStatus(String serviceDetailSubmitStatus) {
		this.serviceDetailSubmitStatus = serviceDetailSubmitStatus;
	}

	public Date getServiceDetailDos() {
		return serviceDetailDos;
	}

	public void setServiceDetailDos(Date serviceDetailDos) {
		this.serviceDetailDos = serviceDetailDos;
	}

	public Date getServiceDetailDosFrom() {
		return serviceDetailDosFrom;
	}

	public void setServiceDetailDosFrom(Date serviceDetailDosFrom) {
		this.serviceDetailDosFrom = serviceDetailDosFrom;
	}

	public Date getServiceDetailDop() {
		return serviceDetailDop;
	}

	public void setServiceDetailDop(Date serviceDetailDop) {
		this.serviceDetailDop = serviceDetailDop;
	}

	public Integer getServiceDetailCptid() {
		return serviceDetailCptid;
	}

	public void setServiceDetailCptid(Integer serviceDetailCptid) {
		this.serviceDetailCptid = serviceDetailCptid;
	}

	public String getServiceDetailModifier1() {
		return serviceDetailModifier1;
	}

	public void setServiceDetailModifier1(String serviceDetailModifier1) {
		this.serviceDetailModifier1 = serviceDetailModifier1;
	}

	public String getServiceDetailComments() {
		return serviceDetailComments;
	}

	public void setServiceDetailComments(String serviceDetailComments) {
		this.serviceDetailComments = serviceDetailComments;
	}

	public String getServiceDetailModifier2() {
		return serviceDetailModifier2;
	}

	public void setServiceDetailModifier2(String serviceDetailModifier2) {
		this.serviceDetailModifier2 = serviceDetailModifier2;
	}

	public Integer getServiceDetailSdoctorid() {
		return serviceDetailSdoctorid;
	}

	public void setServiceDetailSdoctorid(Integer serviceDetailSdoctorid) {
		this.serviceDetailSdoctorid = serviceDetailSdoctorid;
	}

	public Integer getServiceDetailBdoctorid() {
		return serviceDetailBdoctorid;
	}

	public void setServiceDetailBdoctorid(Integer serviceDetailBdoctorid) {
		this.serviceDetailBdoctorid = serviceDetailBdoctorid;
	}

	public Integer getServiceDetailRdoctorid() {
		return serviceDetailRdoctorid;
	}

	public void setServiceDetailRdoctorid(Integer serviceDetailRdoctorid) {
		this.serviceDetailRdoctorid = serviceDetailRdoctorid;
	}

	public Long getServiceDetailAuthid() {
		return serviceDetailAuthid;
	}

	public void setServiceDetailAuthid(Long serviceDetailAuthid) {
		this.serviceDetailAuthid = serviceDetailAuthid;
	}

	public Long getServiceDetailReferralid() {
		return serviceDetailReferralid;
	}

	public void setServiceDetailReferralid(Long serviceDetailReferralid) {
		this.serviceDetailReferralid = serviceDetailReferralid;
	}

	public Integer getServiceDetailPosid() {
		return serviceDetailPosid;
	}

	public void setServiceDetailPosid(Integer serviceDetailPosid) {
		this.serviceDetailPosid = serviceDetailPosid;
	}

	public Integer getServiceDetailUnit() {
		return serviceDetailUnit;
	}

	public void setServiceDetailUnit(Integer serviceDetailUnit) {
		this.serviceDetailUnit = serviceDetailUnit;
	}

	public Double getServiceDetailCharges() {
		return serviceDetailCharges;
	}

	public void setServiceDetailCharges(Double serviceDetailCharges) {
		this.serviceDetailCharges = serviceDetailCharges;
	}

	public Double getServiceDetailCopay() {
		return serviceDetailCopay;
	}

	public void setServiceDetailCopay(Double serviceDetailCopay) {
		this.serviceDetailCopay = serviceDetailCopay;
	}

	public Double getServiceDetailCredits() {
		return serviceDetailCredits;
	}

	public void setServiceDetailCredits(Double serviceDetailCredits) {
		this.serviceDetailCredits = serviceDetailCredits;
	}

	public Double getServiceDetailExpectedPayments() {
		return serviceDetailExpectedPayments;
	}

	public void setServiceDetailExpectedPayments(
			Double serviceDetailExpectedPayments) {
		this.serviceDetailExpectedPayments = serviceDetailExpectedPayments;
	}

	public Date getServiceDetailExpectedDate() {
		return serviceDetailExpectedDate;
	}

	public void setServiceDetailExpectedDate(Date serviceDetailExpectedDate) {
		this.serviceDetailExpectedDate = serviceDetailExpectedDate;
	}

	public String getServiceDetailReference() {
		return serviceDetailReference;
	}

	public void setServiceDetailReference(String serviceDetailReference) {
		this.serviceDetailReference = serviceDetailReference;
	}

	public String getServiceDetailBookmark() {
		return serviceDetailBookmark;
	}

	public void setServiceDetailBookmark(String serviceDetailBookmark) {
		this.serviceDetailBookmark = serviceDetailBookmark;
	}

	public String getServiceDetailSource() {
		return serviceDetailSource;
	}

	public void setServiceDetailSource(String serviceDetailSource) {
		this.serviceDetailSource = serviceDetailSource;
	}

	public String getServiceDetailServer() {
		return serviceDetailServer;
	}

	public void setServiceDetailServer(String serviceDetailServer) {
		this.serviceDetailServer = serviceDetailServer;
	}

	public String getServiceDetailDx1() {
		return serviceDetailDx1;
	}

	public void setServiceDetailDx1(String serviceDetailDx1) {
		this.serviceDetailDx1 = serviceDetailDx1;
	}

	public String getServiceDetailDx2() {
		return serviceDetailDx2;
	}

	public void setServiceDetailDx2(String serviceDetailDx2) {
		this.serviceDetailDx2 = serviceDetailDx2;
	}

	public String getServiceDetailDx3() {
		return serviceDetailDx3;
	}

	public void setServiceDetailDx3(String serviceDetailDx3) {
		this.serviceDetailDx3 = serviceDetailDx3;
	}

	public String getServiceDetailDx4() {
		return serviceDetailDx4;
	}

	public void setServiceDetailDx4(String serviceDetailDx4) {
		this.serviceDetailDx4 = serviceDetailDx4;
	}

	public String getServiceDetailDx5() {
		return serviceDetailDx5;
	}

	public void setServiceDetailDx5(String serviceDetailDx5) {
		this.serviceDetailDx5 = serviceDetailDx5;
	}

	public String getServiceDetailDx6() {
		return serviceDetailDx6;
	}

	public void setServiceDetailDx6(String serviceDetailDx6) {
		this.serviceDetailDx6 = serviceDetailDx6;
	}

	public String getServiceDetailDx7() {
		return serviceDetailDx7;
	}

	public void setServiceDetailDx7(String serviceDetailDx7) {
		this.serviceDetailDx7 = serviceDetailDx7;
	}

	public String getServiceDetailDx8() {
		return serviceDetailDx8;
	}

	public void setServiceDetailDx8(String serviceDetailDx8) {
		this.serviceDetailDx8 = serviceDetailDx8;
	}

	public String getServiceDetailPlacedby() {
		return serviceDetailPlacedby;
	}

	public void setServiceDetailPlacedby(String serviceDetailPlacedby) {
		this.serviceDetailPlacedby = serviceDetailPlacedby;
	}

	public Date getServiceDetailPlaceddate() {
		return serviceDetailPlaceddate;
	}

	public void setServiceDetailPlaceddate(Date serviceDetailPlaceddate) {
		this.serviceDetailPlaceddate = serviceDetailPlaceddate;
	}

	public String getServiceDetailModifiedby() {
		return serviceDetailModifiedby;
	}

	public void setServiceDetailModifiedby(String serviceDetailModifiedby) {
		this.serviceDetailModifiedby = serviceDetailModifiedby;
	}

	public Timestamp getServiceDetailModifieddate() {
		return serviceDetailModifieddate;
	}

	public void setServiceDetailModifieddate(Timestamp serviceDetailModifieddate) {
		this.serviceDetailModifieddate = serviceDetailModifieddate;
	}

	public Integer getServiceDetailPrimaryins() {
		return serviceDetailPrimaryins;
	}

	public void setServiceDetailPrimaryins(Integer serviceDetailPrimaryins) {
		this.serviceDetailPrimaryins = serviceDetailPrimaryins;
	}

	public Long getServiceDetailSecondaryins() {
		return serviceDetailSecondaryins;
	}

	public void setServiceDetailSecondaryins(Long serviceDetailSecondaryins) {
		this.serviceDetailSecondaryins = serviceDetailSecondaryins;
	}

	public Long getServiceDetailOtherIns() {
		return serviceDetailOtherIns;
	}

	public void setServiceDetailOtherIns(Long serviceDetailOtherIns) {
		this.serviceDetailOtherIns = serviceDetailOtherIns;
	}

	public Long getServiceDetailGuarantorId() {
		return serviceDetailGuarantorId;
	}

	public void setServiceDetailGuarantorId(Long serviceDetailGuarantorId) {
		this.serviceDetailGuarantorId = serviceDetailGuarantorId;
	}

	public Long getServiceDetailPrimaryClaimid() {
		return serviceDetailPrimaryClaimid;
	}

	public void setServiceDetailPrimaryClaimid(Long serviceDetailPrimaryClaimid) {
		this.serviceDetailPrimaryClaimid = serviceDetailPrimaryClaimid;
	}

	public Long getServiceDetailSecondaryClaimid() {
		return serviceDetailSecondaryClaimid;
	}

	public void setServiceDetailSecondaryClaimid(Long serviceDetailSecondaryClaimid) {
		this.serviceDetailSecondaryClaimid = serviceDetailSecondaryClaimid;
	}

	public Integer getServiceDetailBillingReason() {
		return serviceDetailBillingReason;
	}

	public void setServiceDetailBillingReason(Integer serviceDetailBillingReason) {
		this.serviceDetailBillingReason = serviceDetailBillingReason;
	}

	public String getServiceDetailBillingStatus() {
		return serviceDetailBillingStatus;
	}

	public void setServiceDetailBillingStatus(String serviceDetailBillingStatus) {
		this.serviceDetailBillingStatus = serviceDetailBillingStatus;
	}

	public Long getServiceDetailStepid() {
		return serviceDetailStepid;
	}

	public void setServiceDetailStepid(Long serviceDetailStepid) {
		this.serviceDetailStepid = serviceDetailStepid;
	}

	public String getServiceDetailRluClaim() {
		return serviceDetailRluClaim;
	}

	public void setServiceDetailRluClaim(String serviceDetailRluClaim) {
		this.serviceDetailRluClaim = serviceDetailRluClaim;
	}

	public Integer getH555555() {
		return h555555;
	}

	public void setH555555(Integer h555555) {
		this.h555555 = h555555;
	}

	public Integer getServiceDetailUnknown1() {
		return serviceDetailUnknown1;
	}

	public void setServiceDetailUnknown1(Integer serviceDetailUnknown1) {
		this.serviceDetailUnknown1 = serviceDetailUnknown1;
	}

	public String getServiceDetailModifier3() {
		return serviceDetailModifier3;
	}

	public void setServiceDetailModifier3(String serviceDetailModifier3) {
		this.serviceDetailModifier3 = serviceDetailModifier3;
	}

	public String getServiceDetailModifier4() {
		return serviceDetailModifier4;
	}

	public void setServiceDetailModifier4(String serviceDetailModifier4) {
		this.serviceDetailModifier4 = serviceDetailModifier4;
	}

	public Integer getServiceDetailTypeOfService() {
		return serviceDetailTypeOfService;
	}

	public void setServiceDetailTypeOfService(Integer serviceDetailTypeOfService) {
		this.serviceDetailTypeOfService = serviceDetailTypeOfService;
	}

	public String getServiceDetailArStatus() {
		return serviceDetailArStatus;
	}

	public void setServiceDetailArStatus(String serviceDetailArStatus) {
		this.serviceDetailArStatus = serviceDetailArStatus;
	}

	public Double getServiceDetailUnknown2() {
		return serviceDetailUnknown2;
	}

	public void setServiceDetailUnknown2(Double serviceDetailUnknown2) {
		this.serviceDetailUnknown2 = serviceDetailUnknown2;
	}

	public String getServiceDetailUnknown3() {
		return serviceDetailUnknown3;
	}

	public void setServiceDetailUnknown3(String serviceDetailUnknown3) {
		this.serviceDetailUnknown3 = serviceDetailUnknown3;
	}

	public Date getServiceDetailUnknown4() {
		return serviceDetailUnknown4;
	}

	public void setServiceDetailUnknown4(Date serviceDetailUnknown4) {
		this.serviceDetailUnknown4 = serviceDetailUnknown4;
	}

	public Double getServiceDetailUnknown5() {
		return serviceDetailUnknown5;
	}

	public void setServiceDetailUnknown5(Double serviceDetailUnknown5) {
		this.serviceDetailUnknown5 = serviceDetailUnknown5;
	}

	public Long getVersion() {
		return version;
	}

	public void setVersion(Long version) {
		this.version = version;
	}

	public Long getRuleversion() {
		return ruleversion;
	}

	public void setRuleversion(Long ruleversion) {
		this.ruleversion = ruleversion;
	}

	public Boolean getValid() {
		return valid;
	}

	public void setValid(Boolean valid) {
		this.valid = valid;
	}

	public Integer getServicetype() {
		return servicetype;
	}

	public void setServicetype(Integer servicetype) {
		this.servicetype = servicetype;
	}

	public Double getPrevEstPatPay() {
		return prevEstPatPay;
	}

	public void setPrevEstPatPay(Double prevEstPatPay) {
		this.prevEstPatPay = prevEstPatPay;
	}

	public Double getEstPatPay() {
		return estPatPay;
	}

	public void setEstPatPay(Double estPatPay) {
		this.estPatPay = estPatPay;
	}

	public Double getEstPatBal() {
		return estPatBal;
	}

	public void setEstPatBal(Double estPatBal) {
		this.estPatBal = estPatBal;
	}

	public Boolean getIsblocked() {
		return isblocked;
	}

	public void setIsblocked(Boolean isblocked) {
		this.isblocked = isblocked;
	}

	public Boolean getServiceDetailScan() {
		return serviceDetailScan;
	}

	public void setServiceDetailScan(Boolean serviceDetailScan) {
		this.serviceDetailScan = serviceDetailScan;
	}

	public Integer getServiceDetailPaymentGroup() {
		return serviceDetailPaymentGroup;
	}

	public void setServiceDetailPaymentGroup(Integer serviceDetailPaymentGroup) {
		this.serviceDetailPaymentGroup = serviceDetailPaymentGroup;
	}

	public Integer getServiceDetailValidationStatus() {
		return serviceDetailValidationStatus;
	}

	public void setServiceDetailValidationStatus(
			Integer serviceDetailValidationStatus) {
		this.serviceDetailValidationStatus = serviceDetailValidationStatus;
	}

	public Boolean getServiceDetailDualins() {
		return serviceDetailDualins;
	}

	public void setServiceDetailDualins(Boolean serviceDetailDualins) {
		this.serviceDetailDualins = serviceDetailDualins;
	}

	public Long getServiceDetailSecondPrimary() {
		return serviceDetailSecondPrimary;
	}

	public void setServiceDetailSecondPrimary(Long serviceDetailSecondPrimary) {
		this.serviceDetailSecondPrimary = serviceDetailSecondPrimary;
	}

	public Integer getServiceDetailDualinsStatus() {
		return serviceDetailDualinsStatus;
	}

	public void setServiceDetailDualinsStatus(Integer serviceDetailDualinsStatus) {
		this.serviceDetailDualinsStatus = serviceDetailDualinsStatus;
	}

	public Integer getServiceDetailReleaseStage() {
		return serviceDetailReleaseStage;
	}

	public void setServiceDetailReleaseStage(Integer serviceDetailReleaseStage) {
		this.serviceDetailReleaseStage = serviceDetailReleaseStage;
	}

	public Integer getServiceDetailDontbill() {
		return serviceDetailDontbill;
	}

	public void setServiceDetailDontbill(Integer serviceDetailDontbill) {
		this.serviceDetailDontbill = serviceDetailDontbill;
	}

	public String getServiceDetailAnesStarttime() {
		return serviceDetailAnesStarttime;
	}

	public void setServiceDetailAnesStarttime(String serviceDetailAnesStarttime) {
		this.serviceDetailAnesStarttime = serviceDetailAnesStarttime;
	}

	public String getServiceDetailAnesEndtime() {
		return serviceDetailAnesEndtime;
	}

	public void setServiceDetailAnesEndtime(String serviceDetailAnesEndtime) {
		this.serviceDetailAnesEndtime = serviceDetailAnesEndtime;
	}

	public String getServiceDetailAnesTimeline() {
		return serviceDetailAnesTimeline;
	}

	public void setServiceDetailAnesTimeline(String serviceDetailAnesTimeline) {
		this.serviceDetailAnesTimeline = serviceDetailAnesTimeline;
	}

	public String getServiceDetailHemoglobincnt() {
		return serviceDetailHemoglobincnt;
	}

	public void setServiceDetailHemoglobincnt(String serviceDetailHemoglobincnt) {
		this.serviceDetailHemoglobincnt = serviceDetailHemoglobincnt;
	}

	public String getServiceDetailDls() {
		return serviceDetailDls;
	}

	public void setServiceDetailDls(String serviceDetailDls) {
		this.serviceDetailDls = serviceDetailDls;
	}

	public String getServiceDetailDfs() {
		return serviceDetailDfs;
	}

	public void setServiceDetailDfs(String serviceDetailDfs) {
		this.serviceDetailDfs = serviceDetailDfs;
	}

	public String getServiceDetailNdc() {
		return serviceDetailNdc;
	}

	public void setServiceDetailNdc(String serviceDetailNdc) {
		this.serviceDetailNdc = serviceDetailNdc;
	}

	public String getServiceDetailDrugdosagedetail() {
		return serviceDetailDrugdosagedetail;
	}

	public void setServiceDetailDrugdosagedetail(
			String serviceDetailDrugdosagedetail) {
		this.serviceDetailDrugdosagedetail = serviceDetailDrugdosagedetail;
	}

	public String getServiceDetailMsptype() {
		return serviceDetailMsptype;
	}

	public void setServiceDetailMsptype(String serviceDetailMsptype) {
		this.serviceDetailMsptype = serviceDetailMsptype;
	}

	public String getServiceDetailHospAdmdate() {
		return serviceDetailHospAdmdate;
	}

	public void setServiceDetailHospAdmdate(String serviceDetailHospAdmdate) {
		this.serviceDetailHospAdmdate = serviceDetailHospAdmdate;
	}

	public String getServiceDetailHospDisdate() {
		return serviceDetailHospDisdate;
	}

	public void setServiceDetailHospDisdate(String serviceDetailHospDisdate) {
		this.serviceDetailHospDisdate = serviceDetailHospDisdate;
	}

	public String getServiceDetailHospLmpdate() {
		return serviceDetailHospLmpdate;
	}

	public void setServiceDetailHospLmpdate(String serviceDetailHospLmpdate) {
		this.serviceDetailHospLmpdate = serviceDetailHospLmpdate;
	}

	public String getServiceDetailInjuryDate() {
		return serviceDetailInjuryDate;
	}

	public void setServiceDetailInjuryDate(String serviceDetailInjuryDate) {
		this.serviceDetailInjuryDate = serviceDetailInjuryDate;
	}

	public String getServiceDetailMedrecNumber() {
		return serviceDetailMedrecNumber;
	}

	public void setServiceDetailMedrecNumber(String serviceDetailMedrecNumber) {
		this.serviceDetailMedrecNumber = serviceDetailMedrecNumber;
	}

	public String getServiceDetailDx9() {
		return serviceDetailDx9;
	}

	public void setServiceDetailDx9(String serviceDetailDx9) {
		this.serviceDetailDx9 = serviceDetailDx9;
	}

	public String getServiceDetailDx10() {
		return serviceDetailDx10;
	}

	public void setServiceDetailDx10(String serviceDetailDx10) {
		this.serviceDetailDx10 = serviceDetailDx10;
	}

	public String getServiceDetailDx11() {
		return serviceDetailDx11;
	}

	public void setServiceDetailDx11(String serviceDetailDx11) {
		this.serviceDetailDx11 = serviceDetailDx11;
	}

	public String getServiceDetailDx12() {
		return serviceDetailDx12;
	}

	public void setServiceDetailDx12(String serviceDetailDx12) {
		this.serviceDetailDx12 = serviceDetailDx12;
	}

	public String getServiceDetailDx13() {
		return serviceDetailDx13;
	}

	public void setServiceDetailDx13(String serviceDetailDx13) {
		this.serviceDetailDx13 = serviceDetailDx13;
	}

	public String getServiceDetailDx14() {
		return serviceDetailDx14;
	}

	public void setServiceDetailDx14(String serviceDetailDx14) {
		this.serviceDetailDx14 = serviceDetailDx14;
	}

	public String getServiceDetailDx15() {
		return serviceDetailDx15;
	}

	public void setServiceDetailDx15(String serviceDetailDx15) {
		this.serviceDetailDx15 = serviceDetailDx15;
	}

	public String getServiceDetailDx16() {
		return serviceDetailDx16;
	}

	public void setServiceDetailDx16(String serviceDetailDx16) {
		this.serviceDetailDx16 = serviceDetailDx16;
	}

	public String getServiceDetailDx17() {
		return serviceDetailDx17;
	}

	public void setServiceDetailDx17(String serviceDetailDx17) {
		this.serviceDetailDx17 = serviceDetailDx17;
	}

	public String getServiceDetailDx18() {
		return serviceDetailDx18;
	}

	public void setServiceDetailDx18(String serviceDetailDx18) {
		this.serviceDetailDx18 = serviceDetailDx18;
	}

	public String getServiceDetailDx19() {
		return serviceDetailDx19;
	}

	public void setServiceDetailDx19(String serviceDetailDx19) {
		this.serviceDetailDx19 = serviceDetailDx19;
	}

	public String getServiceDetailDx20() {
		return serviceDetailDx20;
	}

	public void setServiceDetailDx20(String serviceDetailDx20) {
		this.serviceDetailDx20 = serviceDetailDx20;
	}

	public Integer getServiceDetailIntermFlag() {
		return serviceDetailIntermFlag;
	}

	public void setServiceDetailIntermFlag(Integer serviceDetailIntermFlag) {
		this.serviceDetailIntermFlag = serviceDetailIntermFlag;
	}

	public String getServiceDetailDxsystem() {
		return serviceDetailDxsystem;
	}

	public void setServiceDetailDxsystem(String serviceDetailDxsystem) {
		this.serviceDetailDxsystem = serviceDetailDxsystem;
	}
	
	public Cpt getCpt() {
		return cpt;
	}


	public void setCpt(Cpt cpt) {
		this.cpt = cpt;
	}
	
	public AssociateServiceDetails getAssociateDetails() {
		return associateDetails;
	}


	public void setAssociateDetails(AssociateServiceDetails associateDetails) {
		this.associateDetails = associateDetails;
	}


	public AuthorizationReferral getAuthorizationReferral() {
		return authorizationReferral;
	}


	public void setAuthorizationReferral(AuthorizationReferral authorizationReferral) {
		this.authorizationReferral = authorizationReferral;
	}


	public Icdm getIcdm1() {
		return icdm1;
	}


	public void setIcdm1(Icdm icdm1) {
		this.icdm1 = icdm1;
	}


	public Icdm getIcdm2() {
		return icdm2;
	}


	public void setIcdm2(Icdm icdm2) {
		this.icdm2 = icdm2;
	}


	public Icdm getIcdm3() {
		return icdm3;
	}


	public void setIcdm3(Icdm icdm3) {
		this.icdm3 = icdm3;
	}


	public Icdm getIcdm4() {
		return icdm4;
	}


	public void setIcdm4(Icdm icdm4) {
		this.icdm4 = icdm4;
	}


	public Icdm getIcdm5() {
		return icdm5;
	}


	public void setIcdm5(Icdm icdm5) {
		this.icdm5 = icdm5;
	}


	public Icdm getIcdm6() {
		return icdm6;
	}


	public void setIcdm6(Icdm icdm6) {
		this.icdm6 = icdm6;
	}


	public Icdm getIcdm7() {
		return icdm7;
	}


	public void setIcdm7(Icdm icdm7) {
		this.icdm7 = icdm7;
	}


	public Icdm getIcdm8() {
		return icdm8;
	}


	public void setIcdm8(Icdm icdm8) {
		this.icdm8 = icdm8;
	}


	public String getServiceDetailDx1desc() {
		return serviceDetailDx1desc;
	}


	public void setServiceDetailDx1desc(String serviceDetailDx1desc) {
		this.serviceDetailDx1desc = serviceDetailDx1desc;
	}


	public String getServiceDetailDx2desc() {
		return serviceDetailDx2desc;
	}


	public void setServiceDetailDx2desc(String serviceDetailDx2desc) {
		this.serviceDetailDx2desc = serviceDetailDx2desc;
	}


	public String getServiceDetailDx3desc() {
		return serviceDetailDx3desc;
	}


	public void setServiceDetailDx3desc(String serviceDetailDx3desc) {
		this.serviceDetailDx3desc = serviceDetailDx3desc;
	}


	public String getServiceDetailDx4desc() {
		return serviceDetailDx4desc;
	}


	public void setServiceDetailDx4desc(String serviceDetailDx4desc) {
		this.serviceDetailDx4desc = serviceDetailDx4desc;
	}


	public String getServiceDetailDx5desc() {
		return serviceDetailDx5desc;
	}


	public void setServiceDetailDx5desc(String serviceDetailDx5desc) {
		this.serviceDetailDx5desc = serviceDetailDx5desc;
	}


	public String getServiceDetailDx6desc() {
		return serviceDetailDx6desc;
	}


	public void setServiceDetailDx6desc(String serviceDetailDx6desc) {
		this.serviceDetailDx6desc = serviceDetailDx6desc;
	}


	public String getServiceDetailDx7desc() {
		return serviceDetailDx7desc;
	}


	public void setServiceDetailDx7desc(String serviceDetailDx7desc) {
		this.serviceDetailDx7desc = serviceDetailDx7desc;
	}


	public String getServiceDetailDx8desc() {
		return serviceDetailDx8desc;
	}


	public void setServiceDetailDx8desc(String serviceDetailDx8desc) {
		this.serviceDetailDx8desc = serviceDetailDx8desc;
	}


	public String getServiceDetailDx9desc() {
		return serviceDetailDx9desc;
	}


	public void setServiceDetailDx9desc(String serviceDetailDx9desc) {
		this.serviceDetailDx9desc = serviceDetailDx9desc;
	}


	public String getServiceDetailDx10desc() {
		return serviceDetailDx10desc;
	}


	public void setServiceDetailDx10desc(String serviceDetailDx10desc) {
		this.serviceDetailDx10desc = serviceDetailDx10desc;
	}


	public String getServiceDetailDx11desc() {
		return serviceDetailDx11desc;
	}


	public void setServiceDetailDx11desc(String serviceDetailDx11desc) {
		this.serviceDetailDx11desc = serviceDetailDx11desc;
	}


	public String getServiceDetailDx12desc() {
		return serviceDetailDx12desc;
	}


	public void setServiceDetailDx12desc(String serviceDetailDx12desc) {
		this.serviceDetailDx12desc = serviceDetailDx12desc;
	}


	public String getServiceDetailDx13desc() {
		return serviceDetailDx13desc;
	}


	public void setServiceDetailDx13desc(String serviceDetailDx13desc) {
		this.serviceDetailDx13desc = serviceDetailDx13desc;
	}


	public String getServiceDetailDx14desc() {
		return serviceDetailDx14desc;
	}


	public void setServiceDetailDx14desc(String serviceDetailDx14desc) {
		this.serviceDetailDx14desc = serviceDetailDx14desc;
	}


	public String getServiceDetailDx15desc() {
		return serviceDetailDx15desc;
	}


	public void setServiceDetailDx15desc(String serviceDetailDx15desc) {
		this.serviceDetailDx15desc = serviceDetailDx15desc;
	}


	public String getServiceDetailDx16desc() {
		return serviceDetailDx16desc;
	}


	public void setServiceDetailDx16desc(String serviceDetailDx16desc) {
		this.serviceDetailDx16desc = serviceDetailDx16desc;
	}


	public String getServiceDetailDx17desc() {
		return serviceDetailDx17desc;
	}


	public void setServiceDetailDx17desc(String serviceDetailDx17desc) {
		this.serviceDetailDx17desc = serviceDetailDx17desc;
	}


	public String getServiceDetailDx18desc() {
		return serviceDetailDx18desc;
	}


	public void setServiceDetailDx18desc(String serviceDetailDx18desc) {
		 this.serviceDetailDx18desc = serviceDetailDx18desc;
	}


	public String getServiceDetailDx19desc() {
		return serviceDetailDx19desc;
	}


	public void setServiceDetailDx19desc(String serviceDetailDx19desc) {
		this.serviceDetailDx19desc = serviceDetailDx19desc;
	}


	public String getServiceDetailDx20desc() {
		return serviceDetailDx20desc;
	}


	public void setServiceDetailDx20desc(String serviceDetailDx20desc) {
		this.serviceDetailDx20desc = serviceDetailDx20desc;
	}
	
	@ManyToOne(fetch=FetchType.LAZY)
   	@JsonManagedReference
   	@JoinColumn(name="service_detail_posid", referencedColumnName="place_of_service_placeid"  , insertable=false, updatable=false)
   	private PlaceOfService placeOfService ;
    
	
    public PlaceOfService getPlaceOfService() {
		return placeOfService;
	}
    
	public void setPlaceOfService(PlaceOfService placeOfService) {
		this.placeOfService = placeOfService;
	}
}