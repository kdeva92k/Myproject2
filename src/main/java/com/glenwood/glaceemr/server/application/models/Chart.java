package com.glenwood.glaceemr.server.application.models;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;


import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.glenwood.glaceemr.server.utils.JsonTimestampSerializer;

@Entity
@Table(name="chart")
public class Chart implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	 @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "chart_chart_id_seq")
	@SequenceGenerator(name = "chart_chart_id_seq", sequenceName = "chart_chart_id_seq", allocationSize = 1)
	@Column(name="chart_id")
	private Integer chartId;

	@Column(name="chart_unknown1")
	private String chartUnknown1;

	@NotNull
	@Column(name="chart_patientid")
	private Integer chartPatientid;

	@Column(name="chart_createdby")
	private Integer chartCreatedby;

	@JsonSerialize(using = JsonTimestampSerializer.class)
	@Column(name="chart_createddate")
	private Timestamp chartCreateddate;

	@Column(name="chart_unknown2")
	private Integer chartUnknown2;

	@Column(name="chart_statusid")
	private Integer chartStatusid;

	@Column(name="chart_forwarded_from")
	private Integer chartForwardedFrom;

	@Column(name="chart_forwarded_to")
	private Integer chartForwardedTo;

	@JsonSerialize(using = JsonTimestampSerializer.class)
	@Column(name="chart_forwarded_date")
	private Timestamp chartForwardedDate;

	@Column(name="chart_forwarded_comment")
	private String chartForwardedComment;

	@Column(name="chart_alreadyseen")
	private Boolean chartAlreadyseen;

	@Column(name="chart_remainder")
	private String chartRemainder;

	@Column(name="chart_allergies")
	private String chartAllergies;

	@Column(name="chart_pharmacy")
	private String chartPharmacy;

	@Column(name="chart_access_status")
	private Boolean chartAccessStatus;

	@Column(name="chart_reportexempt_flag")
	private Integer chartReportexemptFlag;

	@Column(name="chart_accessid")
	private String chartAccessid;

	@Column(name="chart_iscomplete")
	private Boolean chartIscomplete;

	@Column(name="chart_alertid")
	private Integer chartAlertid;

	@Column(name="nomedication")
	private Boolean nomedication;

	@Column(name="chart_vaccinenotes")
	private String chartVaccinenotes;

	@Column(name="chart_telemedurl")
	private String chartTelemedurl;

	@Column(name="chart_remainder_by")
	private Integer chartRemainderBy;

	@OneToMany(mappedBy="chartTable")
	@JsonManagedReference
	private List<ServiceDetail> serviceDetail;

	@JsonSerialize(using = JsonTimestampSerializer.class)
	@Column(name="chart_remainder_date")
	private Timestamp chartRemainderDate;

	@Column(name="chart_immnotes")
	private String chartImmnotes;

	@OneToOne(cascade=CascadeType.ALL, fetch=FetchType.LAZY)
	@JsonManagedReference
	@NotFound(action=NotFoundAction.IGNORE)
	@JoinColumn(name="chart_patientid", referencedColumnName="patient_registration_id", insertable=false, updatable=false)
	private PatientRegistration patientRegistrationTable;

	@OneToMany(mappedBy="chartTable")
	@JsonManagedReference
	private List<DirectEmailLog> directEmailLogTable;
	
	@OneToMany(mappedBy="chartTable")
	private List<Encounter> encounterTable;
	
	@OneToMany(cascade=CascadeType.ALL,mappedBy="chart1")
	@JsonManagedReference
	List<LabEntriesParameter> labEntriesParameter;
	
	@OneToMany(mappedBy="chart")
    @JsonManagedReference
    List<ChartcenterEncounter> chartCenterEncounter;
	
	@OneToMany(mappedBy="chartIds")
	@JsonManagedReference
	List<PatientRegistration> patientRegistration;
    

	public List<DirectEmailLog> getDirectEmailLogTable() {
		return directEmailLogTable;
	}

	public void setDirectEmailLogTable(List<DirectEmailLog> directEmailLogTable) {
		this.directEmailLogTable = directEmailLogTable;
	}

	public Integer getChartId() {
		return chartId;
	}

	public void setChartId(Integer chartId) {
		this.chartId = chartId;
	}

	public String getChartUnknown1() {
		return chartUnknown1;
	}

	public void setChartUnknown1(String chartUnknown1) {
		this.chartUnknown1 = chartUnknown1;
	}

	public Integer getChartPatientid() {
		return chartPatientid;
	}

	public void setChartPatientid(Integer chartPatientid) {
		this.chartPatientid = chartPatientid;
	}

	public Integer getChartCreatedby() {
		return chartCreatedby;
	}

	public void setChartCreatedby(Integer chartCreatedby) {
		this.chartCreatedby = chartCreatedby;
	}

	public Timestamp getChartCreateddate() {
		return chartCreateddate;
	}

	public void setChartCreateddate(Timestamp chartCreateddate) {
		this.chartCreateddate = chartCreateddate;
	}

	public Integer getChartUnknown2() {
		return chartUnknown2;
	}

	public void setChartUnknown2(Integer chartUnknown2) {
		this.chartUnknown2 = chartUnknown2;
	}

	public Integer getChartStatusid() {
		return chartStatusid;
	}

	public void setChartStatusid(Integer chartStatusid) {
		this.chartStatusid = chartStatusid;
	}

	public Integer getChartForwardedFrom() {
		return chartForwardedFrom;
	}

	public void setChartForwardedFrom(Integer chartForwardedFrom) {
		this.chartForwardedFrom = chartForwardedFrom;
	}

	public Integer getChartForwardedTo() {
		return chartForwardedTo;
	}

	public void setChartForwardedTo(Integer chartForwardedTo) {
		this.chartForwardedTo = chartForwardedTo;
	}

	public Timestamp getChartForwardedDate() {
		return chartForwardedDate;
	}

	public void setChartForwardedDate(Timestamp chartForwardedDate) {
		this.chartForwardedDate = chartForwardedDate;
	}

	public String getChartForwardedComment() {
		return chartForwardedComment;
	}

	public void setChartForwardedComment(String chartForwardedComment) {
		this.chartForwardedComment = chartForwardedComment;
	}

	public Boolean getChartAlreadyseen() {
		return chartAlreadyseen;
	}

	public void setChartAlreadyseen(Boolean chartAlreadyseen) {
		this.chartAlreadyseen = chartAlreadyseen;
	}

	public String getChartRemainder() {
		return chartRemainder;
	}

	public void setChartRemainder(String chartRemainder) {
		this.chartRemainder = chartRemainder;
	}

	public String getChartAllergies() {
		return chartAllergies;
	}

	public void setChartAllergies(String chartAllergies) {
		this.chartAllergies = chartAllergies;
	}

	public String getChartPharmacy() {
		return chartPharmacy;
	}

	public void setChartPharmacy(String chartPharmacy) {
		this.chartPharmacy = chartPharmacy;
	}

	public Boolean getChartAccessStatus() {
		return chartAccessStatus;
	}

	public void setChartAccessStatus(Boolean chartAccessStatus) {
		this.chartAccessStatus = chartAccessStatus;
	}

	public Integer getChartReportexemptFlag() {
		return chartReportexemptFlag;
	}

	public void setChartReportexemptFlag(Integer chartReportexemptFlag) {
		this.chartReportexemptFlag = chartReportexemptFlag;
	}

	public String getChartAccessid() {
		return chartAccessid;
	}

	public void setChartAccessid(String chartAccessid) {
		this.chartAccessid = chartAccessid;
	}

	public Boolean getChartIscomplete() {
		return chartIscomplete;
	}

	public void setChartIscomplete(Boolean chartIscomplete) {
		this.chartIscomplete = chartIscomplete;
	}

	public Integer getChartAlertid() {
		return chartAlertid;
	}

	public void setChartAlertid(Integer chartAlertid) {
		this.chartAlertid = chartAlertid;
	}

	public Boolean getNomedication() {
		return nomedication;
	}

	public void setNomedication(Boolean nomedication) {
		this.nomedication = nomedication;
	}

	public String getChartVaccinenotes() {
		return chartVaccinenotes;
	}

	public void setChartVaccinenotes(String chartVaccinenotes) {
		this.chartVaccinenotes = chartVaccinenotes;
	}

	public String getChartTelemedurl() {
		return chartTelemedurl;
	}

	public void setChartTelemedurl(String chartTelemedurl) {
		this.chartTelemedurl = chartTelemedurl;
	}

	public Integer getChartRemainderBy() {
		return chartRemainderBy;
	}

	public void setChartRemainderBy(Integer chartRemainderBy) {
		this.chartRemainderBy = chartRemainderBy;
	}

	public Timestamp getChartRemainderDate() {
		return chartRemainderDate;
	}

	public void setChartRemainderDate(Timestamp chartRemainderDate) {
		this.chartRemainderDate = chartRemainderDate;
	}
	public String getChartImmnotes() {
		return chartImmnotes;
	}

	public void setChartImmnotes(String chartImmnotes) {
		this.chartImmnotes = chartImmnotes;
	}

	public PatientRegistration getPatientRegistrationTable() {
		return patientRegistrationTable;
	}

	public void setPatientRegistrationTable(
			PatientRegistration patientRegistrationTable) {
		this.patientRegistrationTable = patientRegistrationTable;
	}
}