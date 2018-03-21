package com.glenwood.glaceemr.server.application.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "coding_systems")
public class CodingSystems {

	@Id
	@Column(name="coding_system_oid")
	private String codingSystemOid;

	@Column(name="coding_system_name")
	private String codingSystemName;

	@Column(name="coding_system_description")
	private String codingSystemDescription;

	public String getCodingSystemOid() {
		return codingSystemOid;
	}

	public void setCodingSystemOid(String codingSystemOid) {
		this.codingSystemOid = codingSystemOid;
	}

	public String getCodingSystemName() {
		return codingSystemName;
	}

	public void setCodingSystemName(String codingSystemName) {
		this.codingSystemName = codingSystemName;
	}

	public String getCodingSystemDescription() {
		return codingSystemDescription;
	}

	public void setCodingSystemDescription(String codingSystemDescription) {
		this.codingSystemDescription = codingSystemDescription;
	}
	
	
}