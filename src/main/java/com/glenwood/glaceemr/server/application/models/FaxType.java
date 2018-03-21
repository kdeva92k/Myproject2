package com.glenwood.glaceemr.server.application.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "fax_type")
public class FaxType {
	@Id
	@Column(name="fax_type_id")
	private Integer faxTypeId;

	@Column(name="fax_type_name")
	private String faxTypeName;

	@Column(name="fax_type_description")
	private String faxTypeDescription;

	public Integer getFaxTypeId() {
		return faxTypeId;
	}

	public void setFaxTypeId(Integer faxTypeId) {
		this.faxTypeId = faxTypeId;
	}

	public String getFaxTypeName() {
		return faxTypeName;
	}

	public void setFaxTypeName(String faxTypeName) {
		this.faxTypeName = faxTypeName;
	}

	public String getFaxTypeDescription() {
		return faxTypeDescription;
	}

	public void setFaxTypeDescription(String faxTypeDescription) {
		this.faxTypeDescription = faxTypeDescription;
	}
}