package com.glenwood.glaceemr.server.application.services.chart.Allergies;

public class AllergiesTypeBean {

	private Integer allergtypeId;

	private String allergtypeName;

	private Boolean allergtypeIsactive;

	private Integer allergtypeGender;

	public Integer getAllergtypeId() {
		return allergtypeId;
	}

	public void setAllergtypeId(Integer allergtypeId) {
		this.allergtypeId = allergtypeId;
	}

	public String getAllergtypeName() {
		return allergtypeName;
	}

	public void setAllergtypeName(String allergtypeName) {
		this.allergtypeName = allergtypeName;
	}

	public Boolean getAllergtypeIsactive() {
		return allergtypeIsactive;
	}

	public void setAllergtypeIsactive(Boolean allergtypeIsactive) {
		this.allergtypeIsactive = allergtypeIsactive;
	}

	public Integer getAllergtypeGender() {
		return allergtypeGender;
	}

	public void setAllergtypeGender(Integer allergtypeGender) {
		this.allergtypeGender = allergtypeGender;
	}
	
}