package com.glenwood.glaceemr.server.application.models;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.glenwood.glaceemr.server.application.services.chart.MIPS.ConfigurationDetails;

@Entity
@Table(name = "ia_measures")
@JsonIgnoreProperties(ignoreUnknown = true)
public class IAMeasures implements Serializable {
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator="ia_measures_ia_measures_id_seq")
	@SequenceGenerator(name = "ia_measures_ia_measures_id_seq", sequenceName="ia_measures_ia_measures_id_seq", allocationSize=1)
	@Column(name="ia_measures_id")
	private Integer IaMeasuresId;

	@Column(name="ia_measures_status ")
	private boolean IaMeasuresStatus;

	@Column(name="ia_measures_measure_id")
	private String IaMeasuresMeasureId;

	@Column(name="ia_measures_lastmodifiedon")
	private Timestamp IaMeasuresLastModified;

	@Column(name="ia_measures_reportingyear")
	private int IaMeasuresReportingYear;

	@Column(name="ia_measures_points")
	private Integer IaMeasuresPoints;
	
	@Column(name="ia_measures_provider_id")
	private Integer IaMeasuresProviderId;

	public Integer getIaMeasuresId() {
		return IaMeasuresId;
	}

	public void setIaMeasuresId(Integer iaMeasuresId) {
		IaMeasuresId = iaMeasuresId;
	}

	public boolean isIaMeasuresStatus() {
		return IaMeasuresStatus;
	}

	public void setIaMeasuresStatus(boolean iaMeasuresStatus) {
		IaMeasuresStatus = iaMeasuresStatus;
	}

	public String getIaMeasuresMeasureId() {
		return IaMeasuresMeasureId;
	}

	public void setIaMeasuresMeasureId(String iaMeasuresMeasureId) {
		IaMeasuresMeasureId = iaMeasuresMeasureId;
	}

	public Timestamp getIaMeasuresLastModified() {
		return IaMeasuresLastModified;
	}

	public void setIaMeasuresLastModified(Timestamp iaMeasuresLastModified) {
		IaMeasuresLastModified = iaMeasuresLastModified;
	}

	public int getIaMeasuresReportingYear() {
		return IaMeasuresReportingYear;
	}

	public void setIaMeasuresReportingYear(Integer iaMeasuresReportingYear) {
		IaMeasuresReportingYear = iaMeasuresReportingYear;
	}

	public Integer getIaMeasuresPoints() {
		return IaMeasuresPoints;
	}

	public void setIaMeasuresPoints(Integer iaMeasuresPoints) {
		IaMeasuresPoints = iaMeasuresPoints;
	}
	
	public Integer getIaMeasuresProviderId() {
		return IaMeasuresProviderId;
	}

	public void setIaMeasuresProviderId(Integer iaMeasuresProviderId) {
		IaMeasuresProviderId = iaMeasuresProviderId;
	}


/*
	@OneToMany(mappedBy="iaMeasures")
	@JsonManagedReference
	private List<IAMeasures> iaMeasures;*/
	
	@OneToMany(mappedBy="iaMeasures")
	private List<QualityMeasuresProviderMapping> qtyprovidermapping;
	

}
