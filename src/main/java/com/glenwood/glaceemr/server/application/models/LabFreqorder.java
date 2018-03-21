package com.glenwood.glaceemr.server.application.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name = "lab_freqorder")
@JsonIgnoreProperties(ignoreUnknown = true)
public class LabFreqorder {

	@Column(name="lab_freqorder_id")
	private Integer labFreqorderId;

	@Id
	@Column(name="lab_freqorder_testid")
	private Integer labFreqorderTestid;

	@Column(name="lab_freqorder_userid")
	private Integer labFreqorderUserid;

	public Integer getLabFreqorderId() {
		return labFreqorderId;
	}

	public void setLabFreqorderId(Integer labFreqorderId) {
		this.labFreqorderId = labFreqorderId;
	}

	public Integer getLabFreqorderTestid() {
		return labFreqorderTestid;
	}

	public void setLabFreqorderTestid(Integer labFreqorderTestid) {
		this.labFreqorderTestid = labFreqorderTestid;
	}

	public Integer getLabFreqorderUserid() {
		return labFreqorderUserid;
	}

	public void setLabFreqorderUserid(Integer labFreqorderUserid) {
		this.labFreqorderUserid = labFreqorderUserid;
	}	
}