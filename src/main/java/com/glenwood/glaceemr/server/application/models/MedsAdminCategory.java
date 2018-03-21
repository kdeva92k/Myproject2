package com.glenwood.glaceemr.server.application.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "meds_admin_category")
public class MedsAdminCategory {

	@Id
	@Column(name="meds_admin_category_id")
	private Integer medsAdminCategoryId;

	@Column(name="meds_admin_category_name")
	private String medsAdminCategoryName;

	public Integer getMedsAdminCategoryId() {
		return medsAdminCategoryId;
	}

	public void setMedsAdminCategoryId(Integer medsAdminCategoryId) {
		this.medsAdminCategoryId = medsAdminCategoryId;
	}

	public String getMedsAdminCategoryName() {
		return medsAdminCategoryName;
	}

	public void setMedsAdminCategoryName(String medsAdminCategoryName) {
		this.medsAdminCategoryName = medsAdminCategoryName;
	}
	
	
	
}