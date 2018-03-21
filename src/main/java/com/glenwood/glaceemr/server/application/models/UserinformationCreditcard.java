package com.glenwood.glaceemr.server.application.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "userinformation_creditcard")
public class UserinformationCreditcard {

	@Column(name="userinformation_details")
	private String userinformationDetails;

	@Column(name="userinformation_connection")
	private String userinformationConnection;
	
	@Id
	@Column(name="pos")
	private String pos;

	public String getUserinformationDetails() {
		return userinformationDetails;
	}

	public void setUserinformationDetails(String userinformationDetails) {
		this.userinformationDetails = userinformationDetails;
	}

	public String getUserinformationConnection() {
		return userinformationConnection;
	}

	public void setUserinformationConnection(String userinformationConnection) {
		this.userinformationConnection = userinformationConnection;
	}

	public String getPos() {
		return pos;
	}

	public void setPos(String pos) {
		this.pos = pos;
	}
	
}