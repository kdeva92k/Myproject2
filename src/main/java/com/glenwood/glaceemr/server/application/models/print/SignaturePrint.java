package com.glenwood.glaceemr.server.application.models.print;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name = "signature_print")
@JsonIgnoreProperties(ignoreUnknown = true)
public class SignaturePrint {
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	@Column(name="signature_print_id")
	private Integer signaturePrintId;
	
	@Column(name="signature_print_name")
	private String signaturePrintName;
	
	@Column(name="signature_print_signdetails")
	private Boolean isSigndetailsReq;
	
	@Column(name="signature_print_disclaimer")
	private String signaturePrintDisclaimer;
	
	@Column(name="signature_print_disclaimer_style")
	private String signaturePrintDisclaimerStyle;
	
	@Column(name="signature_print_signfooter")
	private String signaturePrintSignfooter;
	
	@Column(name="signature_print_signfooter_style")
	private String signaturePrintSignfooterStyle;
	
	@Column(name="signature_print_cosignfooter")
	private String signaturePrintCosignfooter;
	
	@Column(name="signature_print_cosignfooter_style")
	private String signaturePrintCosignfooterStyle;
		
	@Column(name="signature_print_encounter")
	private Boolean isEncounterDateReq;
	
	@Column(name="signature_print_sign_timestamp")
	private Boolean isSignTimestampReq;
	
	@Column(name="signature_print_cosign_timestamp")
	private Boolean isCosignTimestampReq;
	
	@Column(name="signature_print_evaluation")
	private Boolean isEvaluationTimeReq;

	@Column(name="signature_print_regards")
	private String signaturePrintRegards;
	
	@Column(name="signature_print_regards_style")
	private String signaturePrintRegardsStyle;
	
	public Integer getSignaturePrintId() {
		return signaturePrintId;
	}

	public void setSignaturePrintId(Integer signaturePrintId) {
		this.signaturePrintId = signaturePrintId;
	}

	public String getSignaturePrintName() {
		return signaturePrintName;
	}

	public void setSignaturePrintName(String signaturePrintName) {
		this.signaturePrintName = signaturePrintName;
	}

	public Boolean getIsSigndetailsReq() {
		return isSigndetailsReq;
	}

	public void setIsSigndetailsReq(Boolean isSigndetailsReq) {
		this.isSigndetailsReq = isSigndetailsReq;
	}

	public String getSignaturePrintDisclaimer() {
		return signaturePrintDisclaimer;
	}

	public void setSignaturePrintDisclaimer(String signaturePrintDisclaimer) {
		this.signaturePrintDisclaimer = signaturePrintDisclaimer;
	}

	public String getSignaturePrintDisclaimerStyle() {
		return signaturePrintDisclaimerStyle;
	}

	public void setSignaturePrintDisclaimerStyle(String signaturePrintDisclaimerStyle) {
		this.signaturePrintDisclaimerStyle = signaturePrintDisclaimerStyle;
	}

	public String getSignaturePrintSignfooter() {
		return signaturePrintSignfooter;
	}

	public void setSignaturePrintSignfooter(String signaturePrintSignfooter) {
		this.signaturePrintSignfooter = signaturePrintSignfooter;
	}

	public String getSignaturePrintSignfooterStyle() {
		return signaturePrintSignfooterStyle;
	}

	public void setSignaturePrintSignfooterStyle(String signaturePrintSignfooterStyle) {
		this.signaturePrintSignfooterStyle = signaturePrintSignfooterStyle;
	}

	public String getSignaturePrintCosignfooter() {
		return signaturePrintCosignfooter;
	}

	public void setSignaturePrintCosignfooter(String signaturePrintCosignfooter) {
		this.signaturePrintCosignfooter = signaturePrintCosignfooter;
	}

	public String getSignaturePrintCosignfooterStyle() {
		return signaturePrintCosignfooterStyle;
	}

	public void setSignaturePrintCosignfooterStyle(String signaturePrintCosignfooterStyle) {
		this.signaturePrintCosignfooterStyle = signaturePrintCosignfooterStyle;
	}

	public Boolean getIsEncounterDateReq() {
		return isEncounterDateReq;
	}

	public void setIsEncounterDateReq(Boolean isEncounterDateReq) {
		this.isEncounterDateReq = isEncounterDateReq;
	}

	public Boolean getIsSignTimestampReq() {
		return isSignTimestampReq;
	}

	public void setIsSignTimestampReq(Boolean isSignTimestampReq) {
		this.isSignTimestampReq = isSignTimestampReq;
	}

	public Boolean getIsCosignTimestampReq() {
		return isCosignTimestampReq;
	}

	public void setIsCosignTimestampReq(Boolean isCosignTimestampReq) {
		this.isCosignTimestampReq = isCosignTimestampReq;
	}

	public Boolean getIsEvaluationTimeReq() {
		return isEvaluationTimeReq;
	}

	public void setIsEvaluationTimeReq(Boolean isEvaluationTimeReq) {
		this.isEvaluationTimeReq = isEvaluationTimeReq;
	}

	public String getSignaturePrintRegards() {
		return signaturePrintRegards;
	}

	public void setSignaturePrintRegards(String signaturePrintRegards) {
		this.signaturePrintRegards = signaturePrintRegards;
	}

	public String getSignaturePrintRegardsStyle() {
		return signaturePrintRegardsStyle;
	}

	public void setSignaturePrintRegardsStyle(String signaturePrintRegardsStyle) {
		this.signaturePrintRegardsStyle = signaturePrintRegardsStyle;
	}

}
