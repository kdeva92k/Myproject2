package com.glenwood.glaceemr.server.application.models;

import java.sql.Date;
import java.sql.Timestamp;

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


import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.glenwood.glaceemr.server.utils.JsonTimestampSerializer;

@Entity
@Table(name = "non_service_detail")
@JsonIgnoreProperties(ignoreUnknown = true)
public class NonServiceDetails {

	@Id
	 @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "non_service_detail_non_service_detail_id_seq")
	@SequenceGenerator(name = "non_service_detail_non_service_detail_id_seq", sequenceName = "non_service_detail_non_service_detail_id_seq", allocationSize = 1)
	@Column(name="non_service_detail_id")
	private Long nonServiceDetailId;

	@Column(name="non_service_detail_patient_id")
	private Long nonServiceDetailPatientId;

	@Column(name="non_service_detail_service_id")
	private Long nonServiceDetailServiceId;

	@Column(name="non_service_detail_date_of_posting")
	private Date nonServiceDetailDateOfPosting;

	@Column(name="non_service_detail_payment_cpt_id")
	private Integer nonServiceDetailPaymentCptId;

	@Column(name="non_service_detail_comments")
	private String nonServiceDetailComments;

	@Column(name="non_service_detail_amount")
	private Double nonServiceDetailAmount;

	@Column(name="non_service_detail_credits")
	private Double nonServiceDetailCredits;

	@Column(name="non_service_detail_payment_method")
	private Integer nonServiceDetailPaymentMethod;

	@Column(name="non_service_detail_deductible")
	private Double nonServiceDetailDeductible;

	@Column(name="non_service_detail_coins")
	private Double nonServiceDetailCoins;

	@Column(name="non_service_detail_date_paid")
	private Date nonServiceDetailDatePaid;

	@Column(name="non_service_detail_risk_withheld")
	private Double nonServiceDetailRiskWithheld;

	@Column(name="non_service_detail_payer_id")
	private Long nonServiceDetailPayerId;

	@Column(name="non_service_detail_reference")
	private String nonServiceDetailReference;

	@Column(name="non_service_detail_bookmark")
	private String nonServiceDetailBookmark;

	@Column(name="non_service_detail_source")
	private String nonServiceDetailSource;

	@Column(name="non_service_detail_server")
	private String nonServiceDetailServer;

	@Column(name="non_service_detail_placed_by")
	private String nonServiceDetailPlacedBy;

	@Column(name="non_service_detail_last_modified_by")
	private String nonServiceDetailLastModifiedBy;

	@JsonSerialize(using = JsonTimestampSerializer.class)
	@Column(name="non_service_detail_last_modified_date")
	private Timestamp nonServiceDetailLastModifiedDate;

	@Column(name="non_service_detail_check_id")
	private Long nonServiceDetailCheckId;

	@Column(name="h555555")
	private Integer h555555;

	@Column(name="non_service_detail_eob_custom_field1")
	private Long nonServiceDetailEobCustomField1;

	@Column(name="non_service_detail_entry_type")
	private Short nonServiceDetailEntryType;

	@Column(name="non_service_detail_reverse_id")
	private Long nonServiceDetailReverseId;

	@Column(name="non_service_detail_reference_no")
	private String nonServiceDetailReferenceNo;

	@Column(name="non_service_detail_receipt_date")
	private Date nonServiceDetailReceiptDate;

	@Column(name="non_service_detail_paid_by")
	private Integer nonServiceDetailPaidBy;

	@JsonSerialize(using = JsonTimestampSerializer.class)
	@Column(name="non_service_detail_placed_date")
	private Timestamp nonServiceDetailPlacedDate;

	@Column(name="non_service_detail_transferred_by")
	private String nonServiceDetailTransferredBy;

	@Column(name="non_service_detail_transferred_date")
	private Date nonServiceDetailTransferredDate;

	@Column(name="non_service_detail_transferred_amt")
	private Double nonServiceDetailTransferredAmt;

	@Column(name="non_service_detail_transferred_from")
	private Long nonServiceDetailTransferredFrom;

	@Column(name="non_service_detail_transferred_to")
	private Long nonServiceDetailTransferredTo;

	@Column(name="non_service_detail_copay")
	private Double nonServiceDetailCopay;

	@Column(name="non_service_detail_eob_custom_field2")
	private Integer nonServiceDetailEobCustomField2;

	@JsonSerialize(using = JsonTimestampSerializer.class)
	@Column(name="non_service_detail_eob_custom_field3")
	private Timestamp nonServiceDetailEobCustomField3;

	@Column(name="non_service_detail_is_valid")
	private Boolean nonServiceDetailIsValid;

	@Column(name="non_service_detail_refund_status")
	private Short nonServiceDetailRefundStatus;

	@Column(name="non_service_detail_denialvalidator_ruleid")
	private Integer nonServiceDetailDenialvalidatorRuleid;
	
	@Column(name="non_service_detail_remark_code")
	private String nonServiceDetailRemarkCode;

	public String getNonServiceDetailRemarkCode() {
		return nonServiceDetailRemarkCode;
	}


	public void setNonServiceDetailRemarkCode(String nonServiceDetailRemarkCode) {
		this.nonServiceDetailRemarkCode = nonServiceDetailRemarkCode;
	}


	@ManyToOne(cascade=CascadeType.ALL ,fetch=FetchType.EAGER)
	@JoinColumn(name="non_service_detail_check_id", referencedColumnName="receipt_detail_id", insertable=false, updatable=false)
	@JsonManagedReference
    ReceiptDetail receiptDetails;
	

	@ManyToOne(cascade=CascadeType.ALL ,fetch=FetchType.EAGER)
	@JoinColumn(name="non_service_detail_payment_method", referencedColumnName="names_mapping_plan_id", insertable=false, updatable=false)
	@JsonManagedReference
    NamesMapping namesMapping;

	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="non_service_detail_payment_cpt_id", referencedColumnName="cpt_id", insertable=false, updatable=false)
	@JsonManagedReference
    Cpt cpt;
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="non_service_detail_service_id", referencedColumnName="service_detail_id", insertable=false, updatable=false)
	@JsonBackReference
    ServiceDetail serviceDetail;

	public Cpt getCpt() {
		return cpt;
	}


	public void setCpt(Cpt cpt) {
		this.cpt = cpt;
	}

	public Long getNonServiceDetailId() {
		return nonServiceDetailId;
	}


	public void setNonServiceDetailId(Long nonServiceDetailId) {
		this.nonServiceDetailId = nonServiceDetailId;
	}


	public Long getNonServiceDetailPatientId() {
		return nonServiceDetailPatientId;
	}


	public void setNonServiceDetailPatientId(Long nonServiceDetailPatientId) {
		this.nonServiceDetailPatientId = nonServiceDetailPatientId;
	}


	public Long getNonServiceDetailServiceId() {
		return nonServiceDetailServiceId;
	}


	public void setNonServiceDetailServiceId(Long nonServiceDetailServiceId) {
		this.nonServiceDetailServiceId = nonServiceDetailServiceId;
	}


	public Date getNonServiceDetailDateOfPosting() {
		return nonServiceDetailDateOfPosting;
	}


	public void setNonServiceDetailDateOfPosting(Date nonServiceDetailDateOfPosting) {
		this.nonServiceDetailDateOfPosting = nonServiceDetailDateOfPosting;
	}


	public Integer getNonServiceDetailPaymentCptId() {
		return nonServiceDetailPaymentCptId;
	}


	public void setNonServiceDetailPaymentCptId(Integer nonServiceDetailPaymentCptId) {
		this.nonServiceDetailPaymentCptId = nonServiceDetailPaymentCptId;
	}


	public String getNonServiceDetailComments() {
		return nonServiceDetailComments;
	}


	public void setNonServiceDetailComments(String nonServiceDetailComments) {
		this.nonServiceDetailComments = nonServiceDetailComments;
	}


	public Double getNonServiceDetailAmount() {
		return nonServiceDetailAmount;
	}


	public void setNonServiceDetailAmount(Double nonServiceDetailAmount) {
		this.nonServiceDetailAmount = nonServiceDetailAmount;
	}


	public Double getNonServiceDetailCredits() {
		return nonServiceDetailCredits;
	}


	public void setNonServiceDetailCredits(Double nonServiceDetailCredits) {
		this.nonServiceDetailCredits = nonServiceDetailCredits;
	}


	public Integer getNonServiceDetailPaymentMethod() {
		return nonServiceDetailPaymentMethod;
	}


	public void setNonServiceDetailPaymentMethod(
			Integer nonServiceDetailPaymentMethod) {
		this.nonServiceDetailPaymentMethod = nonServiceDetailPaymentMethod;
	}


	public Double getNonServiceDetailDeductible() {
		return nonServiceDetailDeductible;
	}


	public void setNonServiceDetailDeductible(Double nonServiceDetailDeductible) {
		this.nonServiceDetailDeductible = nonServiceDetailDeductible;
	}


	public Double getNonServiceDetailCoins() {
		return nonServiceDetailCoins;
	}


	public void setNonServiceDetailCoins(Double nonServiceDetailCoins) {
		this.nonServiceDetailCoins = nonServiceDetailCoins;
	}


	public Date getNonServiceDetailDatePaid() {
		return nonServiceDetailDatePaid;
	}


	public void setNonServiceDetailDatePaid(Date nonServiceDetailDatePaid) {
		this.nonServiceDetailDatePaid = nonServiceDetailDatePaid;
	}


	public Double getNonServiceDetailRiskWithheld() {
		return nonServiceDetailRiskWithheld;
	}


	public void setNonServiceDetailRiskWithheld(Double nonServiceDetailRiskWithheld) {
		this.nonServiceDetailRiskWithheld = nonServiceDetailRiskWithheld;
	}


	public Long getNonServiceDetailPayerId() {
		return nonServiceDetailPayerId;
	}


	public void setNonServiceDetailPayerId(Long nonServiceDetailPayerId) {
		this.nonServiceDetailPayerId = nonServiceDetailPayerId;
	}


	public String getNonServiceDetailReference() {
		return nonServiceDetailReference;
	}


	public void setNonServiceDetailReference(String nonServiceDetailReference) {
		this.nonServiceDetailReference = nonServiceDetailReference;
	}


	public String getNonServiceDetailBookmark() {
		return nonServiceDetailBookmark;
	}


	public void setNonServiceDetailBookmark(String nonServiceDetailBookmark) {
		this.nonServiceDetailBookmark = nonServiceDetailBookmark;
	}


	public String getNonServiceDetailSource() {
		return nonServiceDetailSource;
	}


	public void setNonServiceDetailSource(String nonServiceDetailSource) {
		this.nonServiceDetailSource = nonServiceDetailSource;
	}


	public String getNonServiceDetailServer() {
		return nonServiceDetailServer;
	}


	public void setNonServiceDetailServer(String nonServiceDetailServer) {
		this.nonServiceDetailServer = nonServiceDetailServer;
	}


	public String getNonServiceDetailPlacedBy() {
		return nonServiceDetailPlacedBy;
	}


	public void setNonServiceDetailPlacedBy(String nonServiceDetailPlacedBy) {
		this.nonServiceDetailPlacedBy = nonServiceDetailPlacedBy;
	}


	public String getNonServiceDetailLastModifiedBy() {
		return nonServiceDetailLastModifiedBy;
	}


	public void setNonServiceDetailLastModifiedBy(
			String nonServiceDetailLastModifiedBy) {
		this.nonServiceDetailLastModifiedBy = nonServiceDetailLastModifiedBy;
	}


	public Timestamp getNonServiceDetailLastModifiedDate() {
		return nonServiceDetailLastModifiedDate;
	}


	public void setNonServiceDetailLastModifiedDate(
			Timestamp nonServiceDetailLastModifiedDate) {
		this.nonServiceDetailLastModifiedDate = nonServiceDetailLastModifiedDate;
	}


	public Long getNonServiceDetailCheckId() {
		return nonServiceDetailCheckId;
	}


	public void setNonServiceDetailCheckId(Long nonServiceDetailCheckId) {
		this.nonServiceDetailCheckId = nonServiceDetailCheckId;
	}


	public Integer getH555555() {
		return h555555;
	}


	public void setH555555(Integer h555555) {
		this.h555555 = h555555;
	}


	public Long getNonServiceDetailEobCustomField1() {
		return nonServiceDetailEobCustomField1;
	}


	public void setNonServiceDetailEobCustomField1(
			Long nonServiceDetailEobCustomField1) {
		this.nonServiceDetailEobCustomField1 = nonServiceDetailEobCustomField1;
	}


	public Short getNonServiceDetailEntryType() {
		return nonServiceDetailEntryType;
	}


	public void setNonServiceDetailEntryType(Short nonServiceDetailEntryType) {
		this.nonServiceDetailEntryType = nonServiceDetailEntryType;
	}


	public Long getNonServiceDetailReverseId() {
		return nonServiceDetailReverseId;
	}


	public void setNonServiceDetailReverseId(Long nonServiceDetailReverseId) {
		this.nonServiceDetailReverseId = nonServiceDetailReverseId;
	}


	public String getNonServiceDetailReferenceNo() {
		return nonServiceDetailReferenceNo;
	}


	public void setNonServiceDetailReferenceNo(String nonServiceDetailReferenceNo) {
		this.nonServiceDetailReferenceNo = nonServiceDetailReferenceNo;
	}


	public Date getNonServiceDetailReceiptDate() {
		return nonServiceDetailReceiptDate;
	}


	public void setNonServiceDetailReceiptDate(Date nonServiceDetailReceiptDate) {
		this.nonServiceDetailReceiptDate = nonServiceDetailReceiptDate;
	}


	public Integer getNonServiceDetailPaidBy() {
		return nonServiceDetailPaidBy;
	}


	public void setNonServiceDetailPaidBy(Integer nonServiceDetailPaidBy) {
		this.nonServiceDetailPaidBy = nonServiceDetailPaidBy;
	}


	public Timestamp getNonServiceDetailPlacedDate() {
		return nonServiceDetailPlacedDate;
	}


	public void setNonServiceDetailPlacedDate(Timestamp nonServiceDetailPlacedDate) {
		this.nonServiceDetailPlacedDate = nonServiceDetailPlacedDate;
	}


	public String getNonServiceDetailTransferredBy() {
		return nonServiceDetailTransferredBy;
	}


	public void setNonServiceDetailTransferredBy(
			String nonServiceDetailTransferredBy) {
		this.nonServiceDetailTransferredBy = nonServiceDetailTransferredBy;
	}


	public Date getNonServiceDetailTransferredDate() {
		return nonServiceDetailTransferredDate;
	}


	public void setNonServiceDetailTransferredDate(
			Date nonServiceDetailTransferredDate) {
		this.nonServiceDetailTransferredDate = nonServiceDetailTransferredDate;
	}


	public Double getNonServiceDetailTransferredAmt() {
		return nonServiceDetailTransferredAmt;
	}


	public void setNonServiceDetailTransferredAmt(
			Double nonServiceDetailTransferredAmt) {
		this.nonServiceDetailTransferredAmt = nonServiceDetailTransferredAmt;
	}


	public Long getNonServiceDetailTransferredFrom() {
		return nonServiceDetailTransferredFrom;
	}


	public void setNonServiceDetailTransferredFrom(
			Long nonServiceDetailTransferredFrom) {
		this.nonServiceDetailTransferredFrom = nonServiceDetailTransferredFrom;
	}


	public Long getNonServiceDetailTransferredTo() {
		return nonServiceDetailTransferredTo;
	}


	public void setNonServiceDetailTransferredTo(Long nonServiceDetailTransferredTo) {
		this.nonServiceDetailTransferredTo = nonServiceDetailTransferredTo;
	}


	public Double getNonServiceDetailCopay() {
		return nonServiceDetailCopay;
	}


	public void setNonServiceDetailCopay(Double nonServiceDetailCopay) {
		this.nonServiceDetailCopay = nonServiceDetailCopay;
	}


	public Integer getNonServiceDetailEobCustomField2() {
		return nonServiceDetailEobCustomField2;
	}


	public void setNonServiceDetailEobCustomField2(
			Integer nonServiceDetailEobCustomField2) {
		this.nonServiceDetailEobCustomField2 = nonServiceDetailEobCustomField2;
	}


	public Timestamp getNonServiceDetailEobCustomField3() {
		return nonServiceDetailEobCustomField3;
	}


	public void setNonServiceDetailEobCustomField3(
			Timestamp nonServiceDetailEobCustomField3) {
		this.nonServiceDetailEobCustomField3 = nonServiceDetailEobCustomField3;
	}


	public Boolean getNonServiceDetailIsValid() {
		return nonServiceDetailIsValid;
	}


	public void setNonServiceDetailIsValid(Boolean nonServiceDetailIsValid) {
		this.nonServiceDetailIsValid = nonServiceDetailIsValid;
	}


	public Short getNonServiceDetailRefundStatus() {
		return nonServiceDetailRefundStatus;
	}


	public void setNonServiceDetailRefundStatus(Short nonServiceDetailRefundStatus) {
		this.nonServiceDetailRefundStatus = nonServiceDetailRefundStatus;
	}


	public Integer getNonServiceDetailDenialvalidatorRuleid() {
		return nonServiceDetailDenialvalidatorRuleid;
	}


	public void setNonServiceDetailDenialvalidatorRuleid(
			Integer nonServiceDetailDenialvalidatorRuleid) {
		this.nonServiceDetailDenialvalidatorRuleid = nonServiceDetailDenialvalidatorRuleid;
	}

	public ReceiptDetail getReceiptDetails() {
		return receiptDetails;
	}


	public void setReceiptDetails(ReceiptDetail receiptDetails) {
		this.receiptDetails = receiptDetails;
	}


	public NamesMapping getNamesMapping() {
		return namesMapping;
	}


	public void setNamesMapping(NamesMapping namesMapping) {
		this.namesMapping = namesMapping;
	}	
}