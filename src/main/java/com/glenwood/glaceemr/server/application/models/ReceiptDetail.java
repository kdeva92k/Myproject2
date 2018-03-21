package com.glenwood.glaceemr.server.application.models;

import java.sql.Date;
import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.glenwood.glaceemr.server.utils.JsonTimestampSerializer;

@Entity
@Table(name = "receipt_detail")
@JsonIgnoreProperties(ignoreUnknown = true)
public class ReceiptDetail {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "receipt_detail_receipt_detail_id_seq")
	@SequenceGenerator(name = "receipt_detail_receipt_detail_id_seq", sequenceName = "receipt_detail_receipt_detail_id_seq", allocationSize = 1)
	@Column(name="receipt_detail_id")
	private Long receiptDetailId;

	@Column(name="receipt_detail_reference_no")
	private String receiptDetailReferenceNo;

	@Column(name="receipt_detail_deposit_date")
	private Date receiptDetailDepositDate;

	@Column(name="receipt_detail_payer_id")
	private Long receiptDetailPayerId;

	@Column(name="receipt_detail_receipt_amt")
	private Double receiptDetailReceiptAmt;

	@Column(name="receipt_detail_posted_amt")
	private Double receiptDetailPostedAmt;

	@Column(name="receipt_detail_isadded_in_service")
	private Boolean receiptDetailIsaddedInService;

	@Column(name="receipt_detail_last_modified_by")
	private String receiptDetailLastModifiedBy;

	@JsonSerialize(using = JsonTimestampSerializer.class)
	@Column(name="receipt_detail_last_modified_date")
	private Timestamp receiptDetailLastModifiedDate;

	@Column(name="receipt_detail_reference")
	private String receiptDetailReference;

	@Column(name="h555555")
	private Integer h555555;

	@Column(name="receipt_detail_check_date")
	private Date receiptDetailCheckDate;

	@Column(name="receipt_detail_description")
	private String receiptDetailDescription;

	@Column(name="receipt_detail_payer_type")
	private Short receiptDetailPayerType;

	@Column(name="receipt_detail_payment_method")
	private Short receiptDetailPaymentMethod;

	@Column(name="receipt_detail_is_copay")
	private Boolean receiptDetailIsCopay;

	@Column(name="receipt_detail_localuse")
	private String receiptDetailLocaluse;

	@Column(name="receipt_detail_is_active")
	private Boolean receiptDetailIsActive;

	@JsonSerialize(using = JsonTimestampSerializer.class)
	@Column(name="receipt_detail_created_date")
	private Timestamp receiptDetailCreatedDate;

	@Column(name="receipt_detail_created_by")
	private String receiptDetailCreatedBy;

	@Column(name="receipt_detail_payment_code")
	private Integer receiptDetailPaymentCode;

	@Column(name="receipt_detail_adjustment_code")
	private Integer receiptDetailAdjustmentCode;

	@Column(name="receipt_detail_withhold_code")
	private Integer receiptDetailWithholdCode;

	@Column(name="receipt_detail_deductible_code")
	private Integer receiptDetailDeductibleCode;

	@Column(name="receipt_detail_refund_code")
	private Integer receiptDetailRefundCode;

	@Column(name="receipt_detail_denial_code")
	private Integer receiptDetailDenialCode;

	@Column(name="receipt_detail_custom_field1")
	private String receiptDetailCustomField1;

	@Column(name="receipt_detail_custom_field2")
	private Date receiptDetailCustomField2;

	@Column(name="receipt_detail_custom_field3")
	private Double receiptDetailCustomField3;

	@Column(name="receipt_detail_payee_type")
	private Integer receiptDetailPayeeType;

	@Column(name="receipt_detail_payee_id")
	private Integer receiptDetailPayeeId;

	@Column(name="receipt_detail_total_receipt_amt")
	private Double receiptDetailTotalReceiptAmt;

	@Column(name="receipt_detail_payment_reason")
	private Double receiptDetailPaymentReason;

	@Column(name="receipt_detail_given_for_dos")
	private Date receiptDetailGivenForDos;

	@Column(name="receipt_detail_refund_amount")
	private Double receiptDetailRefundAmount;

	@Column(name="receipt_detail_auth_no")
	private String receiptDetailAuthNo;

	@Column(name="receipt_detail_writeoff_amount")
	private Double receiptDetailWriteoffAmount;

	@Column(name="receipt_detail_writeoff_reason")
	private String receiptDetailWriteoffReason;

	@Column(name="receipt_detail_reason_type")
	private Short receiptDetailReasonType;

	@Column(name="receipt_detail_howpaid")
	private String receiptDetailHowpaid;

	@Column(name="receipt_detail_payment_place")
	private String receiptDetailPaymentPlace;
	
	@Column(name="receipt_detail_creditmethod")
	private String receiptDetailCreditmethod;

	public Long getReceiptDetailId() {
		return receiptDetailId;
	}

	public void setReceiptDetailId(Long receiptDetailId) {
		this.receiptDetailId = receiptDetailId;
	}

	public String getReceiptDetailReferenceNo() {
		return receiptDetailReferenceNo;
	}

	public void setReceiptDetailReferenceNo(String receiptDetailReferenceNo) {
		this.receiptDetailReferenceNo = receiptDetailReferenceNo;
	}

	public Date getReceiptDetailDepositDate() {
		return receiptDetailDepositDate;
	}

	public void setReceiptDetailDepositDate(Date receiptDetailDepositDate) {
		this.receiptDetailDepositDate = receiptDetailDepositDate;
	}

	public Long getReceiptDetailPayerId() {
		return receiptDetailPayerId;
	}

	public void setReceiptDetailPayerId(Long receiptDetailPayerId) {
		this.receiptDetailPayerId = receiptDetailPayerId;
	}

	public Double getReceiptDetailReceiptAmt() {
		return receiptDetailReceiptAmt;
	}

	public void setReceiptDetailReceiptAmt(Double receiptDetailReceiptAmt) {
		this.receiptDetailReceiptAmt = receiptDetailReceiptAmt;
	}

	public Double getReceiptDetailPostedAmt() {
		return receiptDetailPostedAmt;
	}

	public void setReceiptDetailPostedAmt(Double receiptDetailPostedAmt) {
		this.receiptDetailPostedAmt = receiptDetailPostedAmt;
	}

	public Boolean getReceiptDetailIsaddedInService() {
		return receiptDetailIsaddedInService;
	}

	public void setReceiptDetailIsaddedInService(
			Boolean receiptDetailIsaddedInService) {
		this.receiptDetailIsaddedInService = receiptDetailIsaddedInService;
	}

	public String getReceiptDetailLastModifiedBy() {
		return receiptDetailLastModifiedBy;
	}

	public void setReceiptDetailLastModifiedBy(String receiptDetailLastModifiedBy) {
		this.receiptDetailLastModifiedBy = receiptDetailLastModifiedBy;
	}

	public Timestamp getReceiptDetailLastModifiedDate() {
		return receiptDetailLastModifiedDate;
	}

	public void setReceiptDetailLastModifiedDate(
			Timestamp receiptDetailLastModifiedDate) {
		this.receiptDetailLastModifiedDate = receiptDetailLastModifiedDate;
	}

	public String getReceiptDetailReference() {
		return receiptDetailReference;
	}

	public void setReceiptDetailReference(String receiptDetailReference) {
		this.receiptDetailReference = receiptDetailReference;
	}

	public Integer getH555555() {
		return h555555;
	}

	public void setH555555(Integer h555555) {
		this.h555555 = h555555;
	}

	public Date getReceiptDetailCheckDate() {
		return receiptDetailCheckDate;
	}

	public void setReceiptDetailCheckDate(Date receiptDetailCheckDate) {
		this.receiptDetailCheckDate = receiptDetailCheckDate;
	}

	public String getReceiptDetailDescription() {
		return receiptDetailDescription;
	}

	public void setReceiptDetailDescription(String receiptDetailDescription) {
		this.receiptDetailDescription = receiptDetailDescription;
	}

	public Short getReceiptDetailPayerType() {
		return receiptDetailPayerType;
	}

	public void setReceiptDetailPayerType(Short receiptDetailPayerType) {
		this.receiptDetailPayerType = receiptDetailPayerType;
	}

	public Short getReceiptDetailPaymentMethod() {
		return receiptDetailPaymentMethod;
	}

	public void setReceiptDetailPaymentMethod(Short receiptDetailPaymentMethod) {
		this.receiptDetailPaymentMethod = receiptDetailPaymentMethod;
	}

	public Boolean getReceiptDetailIsCopay() {
		return receiptDetailIsCopay;
	}

	public void setReceiptDetailIsCopay(Boolean receiptDetailIsCopay) {
		this.receiptDetailIsCopay = receiptDetailIsCopay;
	}

	public String getReceiptDetailLocaluse() {
		return receiptDetailLocaluse;
	}

	public void setReceiptDetailLocaluse(String receiptDetailLocaluse) {
		this.receiptDetailLocaluse = receiptDetailLocaluse;
	}

	public Boolean getReceiptDetailIsActive() {
		return receiptDetailIsActive;
	}

	public void setReceiptDetailIsActive(Boolean receiptDetailIsActive) {
		this.receiptDetailIsActive = receiptDetailIsActive;
	}

	public Timestamp getReceiptDetailCreatedDate() {
		return receiptDetailCreatedDate;
	}

	public void setReceiptDetailCreatedDate(Timestamp receiptDetailCreatedDate) {
		this.receiptDetailCreatedDate = receiptDetailCreatedDate;
	}

	public String getReceiptDetailCreatedBy() {
		return receiptDetailCreatedBy;
	}

	public void setReceiptDetailCreatedBy(String receiptDetailCreatedBy) {
		this.receiptDetailCreatedBy = receiptDetailCreatedBy;
	}

	public Integer getReceiptDetailPaymentCode() {
		return receiptDetailPaymentCode;
	}

	public void setReceiptDetailPaymentCode(Integer receiptDetailPaymentCode) {
		this.receiptDetailPaymentCode = receiptDetailPaymentCode;
	}

	public Integer getReceiptDetailAdjustmentCode() {
		return receiptDetailAdjustmentCode;
	}

	public void setReceiptDetailAdjustmentCode(Integer receiptDetailAdjustmentCode) {
		this.receiptDetailAdjustmentCode = receiptDetailAdjustmentCode;
	}

	public Integer getReceiptDetailWithholdCode() {
		return receiptDetailWithholdCode;
	}

	public void setReceiptDetailWithholdCode(Integer receiptDetailWithholdCode) {
		this.receiptDetailWithholdCode = receiptDetailWithholdCode;
	}

	public Integer getReceiptDetailDeductibleCode() {
		return receiptDetailDeductibleCode;
	}

	public void setReceiptDetailDeductibleCode(Integer receiptDetailDeductibleCode) {
		this.receiptDetailDeductibleCode = receiptDetailDeductibleCode;
	}

	public Integer getReceiptDetailRefundCode() {
		return receiptDetailRefundCode;
	}

	public void setReceiptDetailRefundCode(Integer receiptDetailRefundCode) {
		this.receiptDetailRefundCode = receiptDetailRefundCode;
	}

	public Integer getReceiptDetailDenialCode() {
		return receiptDetailDenialCode;
	}

	public void setReceiptDetailDenialCode(Integer receiptDetailDenialCode) {
		this.receiptDetailDenialCode = receiptDetailDenialCode;
	}

	public String getReceiptDetailCustomField1() {
		return receiptDetailCustomField1;
	}

	public void setReceiptDetailCustomField1(String receiptDetailCustomField1) {
		this.receiptDetailCustomField1 = receiptDetailCustomField1;
	}

	public Date getReceiptDetailCustomField2() {
		return receiptDetailCustomField2;
	}

	public void setReceiptDetailCustomField2(Date receiptDetailCustomField2) {
		this.receiptDetailCustomField2 = receiptDetailCustomField2;
	}

	public Double getReceiptDetailCustomField3() {
		return receiptDetailCustomField3;
	}

	public void setReceiptDetailCustomField3(Double receiptDetailCustomField3) {
		this.receiptDetailCustomField3 = receiptDetailCustomField3;
	}

	public Integer getReceiptDetailPayeeType() {
		return receiptDetailPayeeType;
	}

	public void setReceiptDetailPayeeType(Integer receiptDetailPayeeType) {
		this.receiptDetailPayeeType = receiptDetailPayeeType;
	}

	public Integer getReceiptDetailPayeeId() {
		return receiptDetailPayeeId;
	}

	public void setReceiptDetailPayeeId(Integer receiptDetailPayeeId) {
		this.receiptDetailPayeeId = receiptDetailPayeeId;
	}

	public Double getReceiptDetailTotalReceiptAmt() {
		return receiptDetailTotalReceiptAmt;
	}

	public void setReceiptDetailTotalReceiptAmt(Double receiptDetailTotalReceiptAmt) {
		this.receiptDetailTotalReceiptAmt = receiptDetailTotalReceiptAmt;
	}

	public Double getReceiptDetailPaymentReason() {
		return receiptDetailPaymentReason;
	}

	public void setReceiptDetailPaymentReason(Double receiptDetailPaymentReason) {
		this.receiptDetailPaymentReason = receiptDetailPaymentReason;
	}

	public Date getReceiptDetailGivenForDos() {
		return receiptDetailGivenForDos;
	}

	public void setReceiptDetailGivenForDos(Date receiptDetailGivenForDos) {
		this.receiptDetailGivenForDos = receiptDetailGivenForDos;
	}

	public Double getReceiptDetailRefundAmount() {
		return receiptDetailRefundAmount;
	}

	public void setReceiptDetailRefundAmount(Double receiptDetailRefundAmount) {
		this.receiptDetailRefundAmount = receiptDetailRefundAmount;
	}

	public String getReceiptDetailAuthNo() {
		return receiptDetailAuthNo;
	}

	public void setReceiptDetailAuthNo(String receiptDetailAuthNo) {
		this.receiptDetailAuthNo = receiptDetailAuthNo;
	}

	public Double getReceiptDetailWriteoffAmount() {
		return receiptDetailWriteoffAmount;
	}

	public void setReceiptDetailWriteoffAmount(Double receiptDetailWriteoffAmount) {
		this.receiptDetailWriteoffAmount = receiptDetailWriteoffAmount;
	}

	public String getReceiptDetailWriteoffReason() {
		return receiptDetailWriteoffReason;
	}

	public void setReceiptDetailWriteoffReason(String receiptDetailWriteoffReason) {
		this.receiptDetailWriteoffReason = receiptDetailWriteoffReason;
	}

	public Short getReceiptDetailReasonType() {
		return receiptDetailReasonType;
	}

	public void setReceiptDetailReasonType(Short receiptDetailReasonType) {
		this.receiptDetailReasonType = receiptDetailReasonType;
	}

	public String getReceiptDetailHowpaid() {
		return receiptDetailHowpaid;
	}

	public void setReceiptDetailHowpaid(String receiptDetailHowpaid) {
		this.receiptDetailHowpaid = receiptDetailHowpaid;
	}

	public String getReceiptDetailPaymentPlace() {
		return receiptDetailPaymentPlace;
	}

	public void setReceiptDetailPaymentPlace(String receiptDetailPaymentPlace) {
		this.receiptDetailPaymentPlace = receiptDetailPaymentPlace;
	}

	public String getReceiptDetailCreditmethod() {
		return receiptDetailCreditmethod;
	}

	public void setReceiptDetailCreditmethod(String receiptDetailCreditmethod) {
		this.receiptDetailCreditmethod = receiptDetailCreditmethod;
	}
	
	
}