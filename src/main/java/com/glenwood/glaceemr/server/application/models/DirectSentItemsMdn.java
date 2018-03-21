package com.glenwood.glaceemr.server.application.models;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "direct_sent_items_mdn")
public class DirectSentItemsMdn {
	@Id
	@Column(name="direct_sent_items_mdn_id")
	private Integer id;
	
	@Column(name="direct_sent_items_mdn_message_id")
	private String mdnMessageId;
	
	@Column(name="direct_sent_items_mdn_received_on")
	private Timestamp mdnReceivedOn;
	
	@Column(name="direct_sent_items_mdn_from_address")
	private String mdnFromAddress;
	
	@Column(name="direct_sent_items_mdn_to_address")
	private String mdnToAddress;
	
	@Column(name="direct_sent_items_mdn_status")
	private String mdnStatus;
	
	@Column(name="direct_sent_items_mdn_comments")
	private String mdnComments;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getMdnMessageId() {
		return mdnMessageId;
	}

	public void setMdnMessageId(String mdnMessageId) {
		this.mdnMessageId = mdnMessageId;
	}

	public Timestamp getMdnReceivedOn() {
		return mdnReceivedOn;
	}

	public void setMdnReceivedOn(Timestamp mdnReceivedOn) {
		this.mdnReceivedOn = mdnReceivedOn;
	}

	public String getMdnFromAddress() {
		return mdnFromAddress;
	}

	public void setMdnFromAddress(String mdnFromAddress) {
		this.mdnFromAddress = mdnFromAddress;
	}

	public String getMdnToAddress() {
		return mdnToAddress;
	}

	public void setMdnToAddress(String mdnToAddress) {
		this.mdnToAddress = mdnToAddress;
	}

	public String getMdnStatus() {
		return mdnStatus;
	}

	public void setMdnStatus(String mdnStatus) {
		this.mdnStatus = mdnStatus;
	}

	public String getMdnComments() {
		return mdnComments;
	}

	public void setMdnComments(String mdnComments) {
		this.mdnComments = mdnComments;
	}

}
