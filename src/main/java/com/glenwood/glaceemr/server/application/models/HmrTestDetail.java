package com.glenwood.glaceemr.server.application.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name = "hmr_test_detail")
@JsonIgnoreProperties(ignoreUnknown = true)
public class HmrTestDetail {
	
	@Column(name="hmr_test_detail_id", nullable=false)
	@SequenceGenerator(name="hmr_test_detail_hmr_test_detail_id_seq", sequenceName="hmr_test_detail_hmr_test_detail_id_seq", allocationSize=1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE ,generator="hmr_test_detail_hmr_test_detail_id_seq")
	private Integer hmrTestDetailId;

	@Id
	@Column(name="hmr_test_detail_test_id")
	private Integer hmrTestDetailTestId;
	
	@Column(name="hmr_test_detail_from")
	private Integer hmrTestDetailFrom;
	
	@Column(name="hmr_test_detail_to")
	private Integer hmrTestDetailTo;
	
	@Column(name="hmr_test_detail_duration_type")
	private Integer hmrTestDetailDurationType;
	
	@Column(name="hmr_test_detail_is_preferred_age")
	private Integer hmrTestDetailIsPreferredAge;
	
	@Column(name="hmr_test_detail_sort_by")
	private Integer hmrTestDetailSortBy;
	
	@Column(name="hmr_test_detail_is_active")
	private Integer hmrTestDetailIsActive;
	
	@Column(name="hmr_test_detail_schedule")
	private Integer hmrTestDetailSchedule;
	
	@Column(name="hmr_test_detail_schedule_type")
	private Integer hmrTestDetailScheduleType;
	
	@Column(name="hmr_test_detail_description", length=100)
	private String hmrTestDetailDescription;
	
	public Integer getHmrTestDetailId() {
		return hmrTestDetailId;
	}

	public void setHmrTestDetailId(Integer hmrTestDetailId) {
		this.hmrTestDetailId = hmrTestDetailId;
	}

	public Integer getHmrTestDetailTestId() {
		return hmrTestDetailTestId;
	}

	public void setHmrTestDetailTestId(Integer hmrTestDetailTestId) {
		this.hmrTestDetailTestId = hmrTestDetailTestId;
	}

	public Integer getHmrTestDetailFrom() {
		return hmrTestDetailFrom;
	}

	public void setHmrTestDetailFrom(Integer hmrTestDetailFrom) {
		this.hmrTestDetailFrom = hmrTestDetailFrom;
	}

	public Integer getHmrTestDetailTo() {
		return hmrTestDetailTo;
	}

	public void setHmrTestDetailTo(Integer hmrTestDetailTo) {
		this.hmrTestDetailTo = hmrTestDetailTo;
	}

	public Integer getHmrTestDetailDurationType() {
		return hmrTestDetailDurationType;
	}

	public void setHmrTestDetailDurationType(Integer hmrTestDetailDurationType) {
		this.hmrTestDetailDurationType = hmrTestDetailDurationType;
	}

	public Integer getHmrTestDetailIsPreferredAge() {
		return hmrTestDetailIsPreferredAge;
	}

	public void setHmrTestDetailIsPreferredAge(Integer hmrTestDetailIsPreferredAge) {
		this.hmrTestDetailIsPreferredAge = hmrTestDetailIsPreferredAge;
	}

	public Integer getHmrTestDetailSortBy() {
		return hmrTestDetailSortBy;
	}

	public void setHmrTestDetailSortBy(Integer hmrTestDetailSortBy) {
		this.hmrTestDetailSortBy = hmrTestDetailSortBy;
	}

	public Integer getHmrTestDetailIsActive() {
		return hmrTestDetailIsActive;
	}

	public void setHmrTestDetailIsActive(Integer hmrTestDetailIsActive) {
		this.hmrTestDetailIsActive = hmrTestDetailIsActive;
	}

	public Integer getHmrTestDetailSchedule() {
		return hmrTestDetailSchedule;
	}

	public void setHmrTestDetailSchedule(Integer hmrTestDetailSchedule) {
		this.hmrTestDetailSchedule = hmrTestDetailSchedule;
	}

	public Integer getHmrTestDetailScheduleType() {
		return hmrTestDetailScheduleType;
	}

	public void setHmrTestDetailScheduleType(Integer hmrTestDetailScheduleType) {
		this.hmrTestDetailScheduleType = hmrTestDetailScheduleType;
	}

	public String getHmrTestDetailDescription() {
		return hmrTestDetailDescription;
	}

	public void setHmrTestDetailDescription(String hmrTestDetailDescription) {
		this.hmrTestDetailDescription = hmrTestDetailDescription;
	}
}
