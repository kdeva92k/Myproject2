package com.glenwood.glaceemr.server.application.models;

import java.sql.Timestamp;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.SequenceGenerator;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.glenwood.glaceemr.server.utils.JsonTimestampSerializer;

@Entity
@Table(name = "payment_service")
public class PaymentService {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator="payment_service_payment_service_id_seq")
	@SequenceGenerator(name ="payment_service_payment_service_id_seq", sequenceName="payment_service_payment_service_id_seq", allocationSize=1)
	@Column(name="payment_service_id")
	private Integer paymentServiceId;

	@Column(name="payment_service_userid")
	private String paymentServiceUserid;

	@Column(name="payment_service_category")
	private String paymentServiceCategory;

	@Column(name="payment_service_vendor")
	private String paymentServiceVendor;

	@Column(name="payment_service_type")
	private String paymentServiceType;

	@Column(name="payment_service_reason")
	private String paymentServiceReason;

	@Column(name="payment_service_ticket")
	private Long paymentServiceTicket;

	@Column(name="payment_service_amount")
	private Double paymentServiceAmount;

	@Column(name="payment_service_referenceid")
	private String paymentServiceReferenceid;

	@Column(name="payment_service_status")
	private String paymentServiceStatus;

	@Column(name="payment_service_patientid")
	private Integer paymentServicePatientid;

	@Column(name="payment_service_encounterid")
	private Integer paymentServiceEncounterid;

	@Column(name="payment_service_exception")
	private String paymentServiceException;

	@Column(name="payment_service_errorcode")
	private Integer paymentServiceErrorcode;

	@Column(name="payment_service_transactionid")
	private Long paymentServiceTransactionid;

	@Column(name="payment_service_serverip")
	private String paymentServiceServerip;

	@Column(name="payment_service_authcode")
	private String paymentServiceAuthcode;

	@JsonSerialize(using = JsonTimestampSerializer.class)
	@Column(name="payment_service_time")
	private Timestamp paymentServiceTime;

	@Column(name="payment_service_cardinfo")
	private String paymentServiceCardinfo;

	@Column(name="payment_service_response")
	private String paymentServiceResponse;

	@Column(name="payment_service_canvoidorcredit")
	private Boolean paymentServiceCanvoidorcredit;

	@Column(name="payment_service_cancredit")
	private Boolean paymentServiceCancredit;

	@Column(name="payment_service_credit_type")
	private String paymentServiceCreditType;

	@Column(name="payment_service_routingnumber")
	private Integer paymentServiceRoutingnumber;

	@Column(name="payment_service_statusdescription")
	private String paymentServiceStatusdescription;

	@Column(name="payment_service_location_id")
	private String paymentServiceLocationId;

	@Column(name="payment_service_pos")
	private String paymentServicePos;
	
	@Column(name="payment_service_cardholdername")
	private String paymentServiceCardHolderName;

	public Integer getPaymentServiceId() {
		return paymentServiceId;
	}

	public void setPaymentServiceId(Integer paymentServiceId) {
		this.paymentServiceId = paymentServiceId;
	}

	public String getPaymentServiceUserid() {
		return paymentServiceUserid;
	}

	public void setPaymentServiceUserid(String paymentServiceUserid) {
		this.paymentServiceUserid = paymentServiceUserid;
	}

	public String getPaymentServiceCategory() {
		return paymentServiceCategory;
	}

	public void setPaymentServiceCategory(String paymentServiceCategory) {
		this.paymentServiceCategory = paymentServiceCategory;
	}

	public String getPaymentServiceVendor() {
		return paymentServiceVendor;
	}

	public void setPaymentServiceVendor(String paymentServiceVendor) {
		this.paymentServiceVendor = paymentServiceVendor;
	}

	public String getPaymentServiceType() {
		return paymentServiceType;
	}

	public void setPaymentServiceType(String paymentServiceType) {
		this.paymentServiceType = paymentServiceType;
	}

	public String getPaymentServiceReason() {
		return paymentServiceReason;
	}

	public void setPaymentServiceReason(String paymentServiceReason) {
		this.paymentServiceReason = paymentServiceReason;
	}

	public Long getPaymentServiceTicket() {
		return paymentServiceTicket;
	}

	public void setPaymentServiceTicket(Long paymentServiceTicket) {
		this.paymentServiceTicket = paymentServiceTicket;
	}

	public Double getPaymentServiceAmount() {
		return paymentServiceAmount;
	}

	public void setPaymentServiceAmount(Double paymentServiceAmount) {
		this.paymentServiceAmount = paymentServiceAmount;
	}

	public String getPaymentServiceReferenceid() {
		return paymentServiceReferenceid;
	}

	public void setPaymentServiceReferenceid(String paymentServiceReferenceid) {
		this.paymentServiceReferenceid = paymentServiceReferenceid;
	}

	public String getPaymentServiceStatus() {
		return paymentServiceStatus;
	}

	public void setPaymentServiceStatus(String paymentServiceStatus) {
		this.paymentServiceStatus = paymentServiceStatus;
	}

	public Integer getPaymentServicePatientid() {
		return paymentServicePatientid;
	}

	public void setPaymentServicePatientid(Integer paymentServicePatientid) {
		this.paymentServicePatientid = paymentServicePatientid;
	}

	public Integer getPaymentServiceEncounterid() {
		return paymentServiceEncounterid;
	}

	public void setPaymentServiceEncounterid(Integer paymentServiceEncounterid) {
		this.paymentServiceEncounterid = paymentServiceEncounterid;
	}

	public String getPaymentServiceException() {
		return paymentServiceException;
	}

	public void setPaymentServiceException(String paymentServiceException) {
		this.paymentServiceException = paymentServiceException;
	}

	public Integer getPaymentServiceErrorcode() {
		return paymentServiceErrorcode;
	}

	public void setPaymentServiceErrorcode(Integer paymentServiceErrorcode) {
		this.paymentServiceErrorcode = paymentServiceErrorcode;
	}

	public Long getPaymentServiceTransactionid() {
		return paymentServiceTransactionid;
	}

	public void setPaymentServiceTransactionid(Long paymentServiceTransactionid) {
		this.paymentServiceTransactionid = paymentServiceTransactionid;
	}

	public String getPaymentServiceServerip() {
		return paymentServiceServerip;
	}

	public void setPaymentServiceServerip(String paymentServiceServerip) {
		this.paymentServiceServerip = paymentServiceServerip;
	}

	public String getPaymentServiceAuthcode() {
		return paymentServiceAuthcode;
	}

	public void setPaymentServiceAuthcode(String paymentServiceAuthcode) {
		this.paymentServiceAuthcode = paymentServiceAuthcode;
	}

	public Timestamp getPaymentServiceTime() {
		return paymentServiceTime;
	}

	public void setPaymentServiceTime(Timestamp paymentServiceTime) {
		this.paymentServiceTime = paymentServiceTime;
	}

	public String getPaymentServiceCardinfo() {
		return paymentServiceCardinfo;
	}

	public void setPaymentServiceCardinfo(String paymentServiceCardinfo) {
		this.paymentServiceCardinfo = paymentServiceCardinfo;
	}

	public String getPaymentServiceResponse() {
		return paymentServiceResponse;
	}

	public void setPaymentServiceResponse(String paymentServiceResponse) {
		this.paymentServiceResponse = paymentServiceResponse;
	}

	public Boolean getPaymentServiceCanvoidorcredit() {
		return paymentServiceCanvoidorcredit;
	}

	public void setPaymentServiceCanvoidorcredit(
			Boolean paymentServiceCanvoidorcredit) {
		this.paymentServiceCanvoidorcredit = paymentServiceCanvoidorcredit;
	}

	public Boolean getPaymentServiceCancredit() {
		return paymentServiceCancredit;
	}

	public void setPaymentServiceCancredit(Boolean paymentServiceCancredit) {
		this.paymentServiceCancredit = paymentServiceCancredit;
	}

	public String getPaymentServiceCreditType() {
		return paymentServiceCreditType;
	}

	public void setPaymentServiceCreditType(String paymentServiceCreditType) {
		this.paymentServiceCreditType = paymentServiceCreditType;
	}

	public Integer getPaymentServiceRoutingnumber() {
		return paymentServiceRoutingnumber;
	}

	public void setPaymentServiceRoutingnumber(Integer paymentServiceRoutingnumber) {
		this.paymentServiceRoutingnumber = paymentServiceRoutingnumber;
	}

	public String getPaymentServiceStatusdescription() {
		return paymentServiceStatusdescription;
	}

	public void setPaymentServiceStatusdescription(
			String paymentServiceStatusdescription) {
		this.paymentServiceStatusdescription = paymentServiceStatusdescription;
	}

	public String getPaymentServiceLocationId() {
		return paymentServiceLocationId;
	}

	public void setPaymentServiceLocationId(String paymentServiceLocationId) {
		this.paymentServiceLocationId = paymentServiceLocationId;
	}

	public String getPaymentServicePos() {
		return paymentServicePos;
	}

	public void setPaymentServicePos(String paymentServicePos) {
		this.paymentServicePos = paymentServicePos;
	}

	public String getPaymentServiceCardHolderName() {
		return paymentServiceCardHolderName;
	}

	public void setPaymentServiceCardHolderName(String paymentServiceCardHolderName) {
		this.paymentServiceCardHolderName = paymentServiceCardHolderName;
	}
	
	
	
}