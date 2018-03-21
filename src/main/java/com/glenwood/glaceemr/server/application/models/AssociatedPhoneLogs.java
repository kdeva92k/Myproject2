package com.glenwood.glaceemr.server.application.models;



import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "associated_phone_logs")
public class AssociatedPhoneLogs {
	@Id
	@Column(name="associated_phone_logs_entity_id")
	private Integer associatedPhoneLogsEntityId;

	public Integer getAssociatedPhoneLogsEntityId() {
		return associatedPhoneLogsEntityId;
	}

	public void setAssociatedPhoneLogsEntityId(Integer associatedPhoneLogsEntityId) {
		this.associatedPhoneLogsEntityId = associatedPhoneLogsEntityId;
	}

	public Integer getAssociatedPhoneLogsEntityType() {
		return associatedPhoneLogsEntityType;
	}

	public void setAssociatedPhoneLogsEntityType(
			Integer associatedPhoneLogsEntityType) {
		this.associatedPhoneLogsEntityType = associatedPhoneLogsEntityType;
	}

	public Integer getAssociatedPhoneLogsLogId() {
		return associatedPhoneLogsLogId;
	}

	public void setAssociatedPhoneLogsLogId(Integer associatedPhoneLogsLogId) {
		this.associatedPhoneLogsLogId = associatedPhoneLogsLogId;
	}

	public Boolean getAssociatedPhoneLogsIsactive() {
		return associatedPhoneLogsIsactive;
	}

	public void setAssociatedPhoneLogsIsactive(Boolean associatedPhoneLogsIsactive) {
		this.associatedPhoneLogsIsactive = associatedPhoneLogsIsactive;
	}

	@Column(name="associated_phone_logs_entity_type")
	private Integer associatedPhoneLogsEntityType;

	@Column(name="associated_phone_logs_log_id")
	private Integer associatedPhoneLogsLogId;

	@Column(name="associated_phone_logs_isactive")
	private Boolean associatedPhoneLogsIsactive;
}