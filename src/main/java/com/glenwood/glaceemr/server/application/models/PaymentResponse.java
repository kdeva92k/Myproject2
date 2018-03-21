package com.glenwood.glaceemr.server.application.models;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component
@Scope("prototype")
public class PaymentResponse {

	ReceiptDetail receipt;
	
	boolean paymentSuccess;
	
	String message;
	
	boolean emailSent;

	public ReceiptDetail getReceipt() {
		return receipt;
	}

	public void setReceipt(ReceiptDetail receipt) {
		this.receipt = receipt;
	}

	public boolean isPaymentSuccess() {
		return paymentSuccess;
	}

	public void setPaymentSuccess(boolean paymentSuccess) {
		this.paymentSuccess = paymentSuccess;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public boolean isEmailSent() {
		return emailSent;
	}

	public void setEmailSent(boolean emailSent) {
		this.emailSent = emailSent;
	}
	
}
