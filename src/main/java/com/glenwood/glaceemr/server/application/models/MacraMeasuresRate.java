package com.glenwood.glaceemr.server.application.models;

import java.sql.Timestamp;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.glenwood.glaceemr.server.utils.JsonTimestampSerializer;

@Entity
@Table(name = "macra_measures_rate")
@JsonIgnoreProperties(ignoreUnknown = true)
public class MacraMeasuresRate {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator="macra_measures_rate_macra_measures_rate_id_seq")
	@SequenceGenerator(name = "macra_measures_rate_macra_measures_rate_id_seq", sequenceName="macra_measures_rate_macra_measures_rate_id_seq", allocationSize=1)
	@Column(name="macra_measures_rate_id")
	private Integer macraMeasuresRateId;

	@Column(name="macra_measures_rate_reporting_year")
	private Integer macraMeasuresRateReportingYear;

	@Column(name="macra_measures_rate_period_start")
	private Date macraMeasuresRatePeriodStart;

	@Column(name="macra_measures_rate_period_end")
	private Date macraMeasuresRatePeriodEnd;

	@Column(name="macra_measures_rate_measure_id")
	private String macraMeasuresRateMeasureId;

	@Column(name="macra_measures_rate_measure_type")
	private Integer macraMeasuresRateMeasureType;

	@Column(name="macra_measures_rate_provider_id")
	private Integer macraMeasuresRateProviderId;

	@Column(name="macra_measures_rate_reporting")
	private Double macraMeasuresRateReporting;

	@Column(name="macra_measures_rate_performance")
	private Double macraMeasuresRatePerformance;

	@JsonSerialize(using = JsonTimestampSerializer.class)
	@Column(name="macra_measures_rate_updated_on")
	private Timestamp macraMeasuresRateUpdatedOn;

	@Column(name="macra_measures_rate_ipp")
	private Integer macraMeasuresRateIpp;

	@Column(name="macra_measures_rate_ipplist")
	private String macraMeasuresRateIpplist;

	@Column(name="macra_measures_rate_denominator")
	private Integer macraMeasuresRateDenominator;

	@Column(name="macra_measures_rate_denominatorlist")
	private String macraMeasuresRateDenominatorlist;

	@Column(name="macra_measures_rate_denominator_exclusion")
	private Integer macraMeasuresRateDenominatorExclusion;

	@Column(name="macra_measures_rate_denominator_exclusionlist")
	private String macraMeasuresRateDenominatorExclusionlist;

	@Column(name="macra_measures_rate_numerator")
	private Integer macraMeasuresRateNumerator;

	@Column(name="macra_measures_rate_numeratorlist")
	private String macraMeasuresRateNumeratorlist;

	@Column(name="macra_measures_rate_numerator_exclusion")
	private Integer macraMeasuresRateNumeratorExclusion;

	@Column(name="macra_measures_rate_numerator_exclusionlist")
	private String macraMeasuresRateNumeratorExclusionlist;

	@Column(name="macra_measures_rate_denominator_exception")
	private Integer macraMeasuresRateDenominatorException;

	@Column(name="macra_measures_rate_denominator_exceptionlist")
	private String macraMeasuresRateDenominatorExceptionlist;

	@Column(name="macra_measures_rate_criteria")
	private Integer macraMeasuresRateCriteria;

	@Column(name="macra_measures_rate_npi")
	private String macraMeasuresRateNpi;

	@Column(name="macra_measures_rate_tin")
	private String macraMeasuresRateTin;

	@Column(name="macra_measures_rate_cmsId")
	private String macraMeasuresRateCMSId;
	
	@Column(name="macra_measures_rate_points")
	private Double macraMeasuresRatePoints;
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JsonManagedReference
	@JoinColumn(name="macra_measures_rate_provider_id", referencedColumnName="emp_profile_empid" , insertable=false, updatable=false)
	private EmployeeProfile empProfileTable;

	public Integer getMacraMeasuresRateId() {
		return macraMeasuresRateId;
	}

	public void setMacraMeasuresRateId(Integer macraMeasuresRateId) {
		this.macraMeasuresRateId = macraMeasuresRateId;
	}

	public Integer getMacraMeasuresRateReportingYear() {
		return macraMeasuresRateReportingYear;
	}

	public void setMacraMeasuresRateReportingYear(
			Integer macraMeasuresRateReportingYear) {
		this.macraMeasuresRateReportingYear = macraMeasuresRateReportingYear;
	}

	public Date getMacraMeasuresRatePeriodStart() {
		return macraMeasuresRatePeriodStart;
	}

	public void setMacraMeasuresRatePeriodStart(Date macraMeasuresRatePeriodStart) {
		this.macraMeasuresRatePeriodStart = macraMeasuresRatePeriodStart;
	}

	public Date getMacraMeasuresRatePeriodEnd() {
		return macraMeasuresRatePeriodEnd;
	}

	public void setMacraMeasuresRatePeriodEnd(Date macraMeasuresRatePeriodEnd) {
		this.macraMeasuresRatePeriodEnd = macraMeasuresRatePeriodEnd;
	}

	public String getMacraMeasuresRateMeasureId() {
		return macraMeasuresRateMeasureId;
	}

	public void setMacraMeasuresRateMeasureId(String macraMeasuresRateMeasureId) {
		this.macraMeasuresRateMeasureId = macraMeasuresRateMeasureId;
	}

	
	public String getMacraMeasuresRateCMSId() {
		return macraMeasuresRateCMSId;
	}

	public void setMacraMeasuresRateCMSId(String macraMeasuresRateCMSId) {
		this.macraMeasuresRateCMSId = macraMeasuresRateCMSId;
	}

	public Integer getMacraMeasuresRateMeasureType() {
		return macraMeasuresRateMeasureType;
	}

	public void setMacraMeasuresRateMeasureType(Integer macraMeasuresRateMeasureType) {
		this.macraMeasuresRateMeasureType = macraMeasuresRateMeasureType;
	}

	public Integer getMacraMeasuresRateProviderId() {
		return macraMeasuresRateProviderId;
	}

	public void setMacraMeasuresRateProviderId(Integer macraMeasuresRateProviderId) {
		this.macraMeasuresRateProviderId = macraMeasuresRateProviderId;
	}

	public Double getMacraMeasuresRateReporting() {
		return macraMeasuresRateReporting;
	}

	public void setMacraMeasuresRateReporting(Double macraMeasuresRateReporting) {
		this.macraMeasuresRateReporting = macraMeasuresRateReporting;
	}

	public Double getMacraMeasuresRatePerformance() {
		return macraMeasuresRatePerformance;
	}

	public void setMacraMeasuresRatePerformance(Double macraMeasuresRatePerformance) {
		this.macraMeasuresRatePerformance = macraMeasuresRatePerformance;
	}

	public Timestamp getMacraMeasuresRateUpdatedOn() {
		return macraMeasuresRateUpdatedOn;
	}

	public void setMacraMeasuresRateUpdatedOn(Timestamp macraMeasuresRateUpdatedOn) {
		this.macraMeasuresRateUpdatedOn = macraMeasuresRateUpdatedOn;
	}

	public Integer getMacraMeasuresRateIpp() {
		return macraMeasuresRateIpp;
	}

	public void setMacraMeasuresRateIpp(Integer macraMeasuresRateIpp) {
		this.macraMeasuresRateIpp = macraMeasuresRateIpp;
	}

	public String getMacraMeasuresRateIpplist() {
		return macraMeasuresRateIpplist;
	}

	public void setMacraMeasuresRateIpplist(String macraMeasuresRateIpplist) {
		this.macraMeasuresRateIpplist = macraMeasuresRateIpplist;
	}

	public Integer getMacraMeasuresRateDenominator() {
		return macraMeasuresRateDenominator;
	}

	public void setMacraMeasuresRateDenominator(Integer macraMeasuresRateDenominator) {
		this.macraMeasuresRateDenominator = macraMeasuresRateDenominator;
	}

	public String getMacraMeasuresRateDenominatorlist() {
		return macraMeasuresRateDenominatorlist;
	}

	public void setMacraMeasuresRateDenominatorlist(
			String macraMeasuresRateDenominatorlist) {
		this.macraMeasuresRateDenominatorlist = macraMeasuresRateDenominatorlist;
	}

	public Integer getMacraMeasuresRateDenominatorExclusion() {
		return macraMeasuresRateDenominatorExclusion;
	}

	public void setMacraMeasuresRateDenominatorExclusion(
			Integer macraMeasuresRateDenominatorExclusion) {
		this.macraMeasuresRateDenominatorExclusion = macraMeasuresRateDenominatorExclusion;
	}

	public String getMacraMeasuresRateDenominatorExclusionlist() {
		return macraMeasuresRateDenominatorExclusionlist;
	}

	public void setMacraMeasuresRateDenominatorExclusionlist(
			String macraMeasuresRateDenominatorExclusionlist) {
		this.macraMeasuresRateDenominatorExclusionlist = macraMeasuresRateDenominatorExclusionlist;
	}

	public Integer getMacraMeasuresRateNumerator() {
		return macraMeasuresRateNumerator;
	}

	public void setMacraMeasuresRateNumerator(Integer macraMeasuresRateNumerator) {
		this.macraMeasuresRateNumerator = macraMeasuresRateNumerator;
	}

	public String getMacraMeasuresRateNumeratorlist() {
		return macraMeasuresRateNumeratorlist;
	}

	public void setMacraMeasuresRateNumeratorlist(
			String macraMeasuresRateNumeratorlist) {
		this.macraMeasuresRateNumeratorlist = macraMeasuresRateNumeratorlist;
	}

	public Integer getMacraMeasuresRateNumeratorExclusion() {
		return macraMeasuresRateNumeratorExclusion;
	}

	public void setMacraMeasuresRateNumeratorExclusion(
			Integer macraMeasuresRateNumeratorExclusion) {
		this.macraMeasuresRateNumeratorExclusion = macraMeasuresRateNumeratorExclusion;
	}

	public String getMacraMeasuresRateNumeratorExclusionlist() {
		return macraMeasuresRateNumeratorExclusionlist;
	}

	public void setMacraMeasuresRateNumeratorExclusionlist(
			String macraMeasuresRateNumeratorExclusionlist) {
		this.macraMeasuresRateNumeratorExclusionlist = macraMeasuresRateNumeratorExclusionlist;
	}

	public Integer getMacraMeasuresRateDenominatorException() {
		return macraMeasuresRateDenominatorException;
	}

	public void setMacraMeasuresRateDenominatorException(
			Integer macraMeasuresRateDenominatorException) {
		this.macraMeasuresRateDenominatorException = macraMeasuresRateDenominatorException;
	}

	public String getMacraMeasuresRateDenominatorExceptionlist() {
		return macraMeasuresRateDenominatorExceptionlist;
	}

	public void setMacraMeasuresRateDenominatorExceptionlist(
			String macraMeasuresRateDenominatorExceptionlist) {
		this.macraMeasuresRateDenominatorExceptionlist = macraMeasuresRateDenominatorExceptionlist;
	}

	public Integer getMacraMeasuresRateCriteria() {
		return macraMeasuresRateCriteria;
	}

	public void setMacraMeasuresRateCriteria(Integer macraMeasuresRateCriteria) {
		this.macraMeasuresRateCriteria = macraMeasuresRateCriteria;
	}

	public String getMacraMeasuresRateNpi() {
		return macraMeasuresRateNpi;
	}

	public void setMacraMeasuresRateNpi(String macraMeasuresRateNpi) {
		this.macraMeasuresRateNpi = macraMeasuresRateNpi;
	}

	public String getMacraMeasuresRateTin() {
		return macraMeasuresRateTin;
	}

	public void setMacraMeasuresRateTin(String macraMeasuresRateTin) {
		this.macraMeasuresRateTin = macraMeasuresRateTin;
	}

	public Double getMacraMeasuresRatePoints() {
		return macraMeasuresRatePoints;
	}

	public void setMacraMeasuresRatePoints(Double macraMeasuresRatePoints) {
		this.macraMeasuresRatePoints = macraMeasuresRatePoints;
	}
	
}