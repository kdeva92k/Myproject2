package com.glenwood.glaceemr.server.application.models;

import java.sql.Timestamp;

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
import javax.persistence.Transient;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.glenwood.glaceemr.server.utils.JsonTimestampSerializer;

@Entity
@JsonIgnoreProperties(ignoreUnknown = true)
@Table(name = "alert_event")
public class AlertEvent {
	
	@Id
	@SequenceGenerator(name = "sequence", sequenceName = "alert_sequence")
	@GeneratedValue(strategy = GenerationType.AUTO, generator = "sequence")
	@Column(name="alert_event_id")
	private Integer alertEventId;

	@Column(name="alert_event_from")
	private Integer alertEventFrom;

	@Column(name="alert_event_to")
	private Integer alertEventTo;

	@Column(name="alert_event_status")
	private Integer alertEventStatus;

	@Column(name="alert_event_category_id")
	private Integer alertEventCategoryId;

	@Column(name="alert_event_ref_id")
	private Integer alertEventRefId;

	@Column(name="alert_event_patient_id")
	private Integer alertEventPatientId;

	@Column(name="alert_event_encounter_id")
	private Integer alertEventEncounterId;

	@JsonSerialize(using = JsonTimestampSerializer.class)
	@Column(name="alert_event_created_date")
	private Timestamp alertEventCreatedDate;

	@JsonSerialize(using = JsonTimestampSerializer.class)
	@Column(name="alert_event_closed_date")
	private Timestamp alertEventClosedDate;
	
	@Column(name="alert_event_message")
	private String alertEventMessage;

	@Column(name="alert_event_unknown")
	private Boolean alertEventUnknown;

	@Column(name="alert_event_read")
	private Boolean alertEventRead;

	@Column(name="alert_event_highlight")
	private Boolean alertEventHighlight;

	@Column(name="alert_event_readby")
	private Integer alertEventReadby;

	@Column(name="alert_event_modifiedby")
	private Integer alertEventModifiedby;

	@Column(name="alert_event_patient_name")
	private String alertEventPatientName;

	@JsonSerialize(using = JsonTimestampSerializer.class)
	@Column(name="alert_event_read_date")
	private Timestamp alertEventReadDate;

	@JsonSerialize(using = JsonTimestampSerializer.class)
	@Column(name="alert_event_modified_date")
	private Timestamp alertEventModifiedDate;

	@Column(name="alert_event_chart_id")
	private Integer alertEventChartId;

	@Column(name="alert_event_room_id")
	private Integer alertEventRoomId;

	@Column(name="alert_event_status1")
	private Integer alertEventStatus1;

	@Column(name="alert_event_parentalertid")
	private Integer alertEventParentalertid;

	@Column(name="alert_event_isgroupalert")
	private Boolean alertEventIsgroupalert;

	@Column(name="alert_event_frompage")
	private String alertEventFrompage;
	
	@Transient
	@JsonSerialize
	@JsonDeserialize
	private String alertEventCreatedDateTime;
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JsonManagedReference
	@JoinColumn(name="alert_event_category_id",referencedColumnName="alert_category_id",insertable=false,updatable=false)
	AlertCategory alertCategoryTable;
	
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JsonManagedReference
	@JoinColumn(name="alert_event_room_id",referencedColumnName="room_details_id",insertable=false,updatable=false)
	Room roomTable;
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JsonManagedReference
	@JoinColumn(name="alert_event_chart_id",referencedColumnName="chart_id",insertable=false,updatable=false)
	Chart chartTable;
	
	

	@ManyToOne(fetch=FetchType.LAZY)
	@JsonManagedReference
	@JoinColumn(name="alert_event_from",referencedColumnName="emp_profile_empid",insertable=false,updatable=false)
	EmployeeProfile empProfileTableFrom;
	

	@ManyToOne(fetch=FetchType.LAZY)
	@JsonManagedReference
	@JoinColumn(name="alert_event_to",referencedColumnName="emp_profile_empid",insertable=false,updatable=false)
	EmployeeProfile empProfileTableTo;
	

	@ManyToOne(fetch=FetchType.LAZY)
	@JsonManagedReference
	@JoinColumn(name="alert_event_readby",referencedColumnName="emp_profile_empid",insertable=false,updatable=false)
	EmployeeProfile empProfileTableReadBy;
	

	@ManyToOne(fetch=FetchType.LAZY)
	@JsonManagedReference
	@JoinColumn(name="alert_event_modifiedby",referencedColumnName="emp_profile_empid",insertable=false,updatable=false)
	EmployeeProfile empProfileTableModifiedBy;
	
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JsonManagedReference
	@JoinColumn(name="alert_event_encounter_id",referencedColumnName="encounter_id",insertable=false,updatable=false)
	Encounter encounterTableId;
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JsonManagedReference
	@JoinColumn(name="alert_event_ref_id", referencedColumnName="portal_message_alertid", insertable=false, updatable=false)
	private PortalMessage portalMessage;

	@ManyToOne(fetch=FetchType.LAZY)
	@JsonManagedReference
	@JoinColumn(name="alert_event_patient_id", referencedColumnName="patient_registration_id", insertable=false, updatable=false)
	private PatientRegistration patientRegistration;
	
	/*@ManyToOne(fetch=FetchType.LAZY)
	@JsonManagedReference
	@JoinColumnsOrFormulas({ @JoinColumnOrFormula(formula = @JoinFormula(value = "alert_event_from::text", referencedColumnName = "pharm_details_ncpdpid")) })
	PharmDetails pharmDetails;*/

	public Integer getAlertEventId() {
		return alertEventId;
	}


	public void setAlertEventId(Integer alertEventId) {
		this.alertEventId = alertEventId;
	}


	public Integer getAlertEventFrom() {
		return alertEventFrom;
	}


	public void setAlertEventFrom(Integer alertEventFrom) {
		this.alertEventFrom = alertEventFrom;
	}


	public Integer getAlertEventTo() {
		return alertEventTo;
	}


	public void setAlertEventTo(Integer alertEventTo) {
		this.alertEventTo = alertEventTo;
	}


	public Integer getAlertEventStatus() {
		return alertEventStatus;
	}


	public void setAlertEventStatus(Integer alertEventStatus) {
		this.alertEventStatus = alertEventStatus;
	}


	public Integer getAlertEventCategoryId() {
		return alertEventCategoryId;
	}


	public void setAlertEventCategoryId(Integer alertEventCategoryId) {
		this.alertEventCategoryId = alertEventCategoryId;
	}


	public Integer getAlertEventRefId() {
		return alertEventRefId;
	}


	public void setAlertEventRefId(Integer alertEventRefId) {
		this.alertEventRefId = alertEventRefId;
	}


	public Integer getAlertEventPatientId() {
		return alertEventPatientId;
	}


	public void setAlertEventPatientId(Integer alertEventPatientId) {
		this.alertEventPatientId = alertEventPatientId;
	}


	public Integer getAlertEventEncounterId() {
		return alertEventEncounterId;
	}


	public void setAlertEventEncounterId(Integer alertEventEncounterId) {
		this.alertEventEncounterId = alertEventEncounterId;
	}


	public Timestamp getAlertEventCreatedDate() {
		return alertEventCreatedDate;
	}


	public void setAlertEventCreatedDate(Timestamp alertEventCreatedDate) {
		Timestamp toTimeStamp = Timestamp.valueOf(alertEventCreatedDate+"");   //+" 00:00:00"
		this.alertEventCreatedDate = toTimeStamp;
	}


	public Timestamp getAlertEventClosedDate() {
		return alertEventClosedDate;
	}


	public void setAlertEventClosedDate(Timestamp alertEventClosedDate) {
		Timestamp toTimeStamp = Timestamp.valueOf(alertEventClosedDate+"");   //+" 00:00:00"
		this.alertEventClosedDate = toTimeStamp;
	}


	public String getAlertEventMessage() {
		return alertEventMessage;
	}


	public void setAlertEventMessage(String alertEventMessage) {
		this.alertEventMessage = alertEventMessage;
	}


	public Boolean getAlertEventUnknown() {
		return alertEventUnknown;
	}


	public void setAlertEventUnknown(Boolean alertEventUnknown) {
		this.alertEventUnknown = alertEventUnknown;
	}


	public Boolean getAlertEventRead() {
		return alertEventRead;
	}


	public void setAlertEventRead(Boolean alertEventRead) {
		this.alertEventRead = alertEventRead;
	}


	public Boolean getAlertEventHighlight() {
		return alertEventHighlight;
	}


	public void setAlertEventHighlight(Boolean alertEventHighlight) {
		this.alertEventHighlight = alertEventHighlight;
	}


	public Integer getAlertEventReadby() {
		return alertEventReadby;
	}


	public void setAlertEventReadby(Integer alertEventReadby) {
		this.alertEventReadby = alertEventReadby;
	}


	public Integer getAlertEventModifiedby() {
		return alertEventModifiedby;
	}


	public void setAlertEventModifiedby(Integer alertEventModifiedby) {
		this.alertEventModifiedby = alertEventModifiedby;
	}


	public String getAlertEventPatientName() {
		return alertEventPatientName;
	}


	public void setAlertEventPatientName(String alertEventPatientName) {
		this.alertEventPatientName = alertEventPatientName;
	}


	public Timestamp getAlertEventReadDate() {
		return alertEventReadDate;
	}


	public void setAlertEventReadDate(Timestamp alertEventReadDate) {
		Timestamp toTimeStamp = Timestamp.valueOf(alertEventReadDate+"");   //+" 00:00:00"
		this.alertEventReadDate = toTimeStamp;
	}


	public Timestamp getAlertEventModifiedDate() {
		return alertEventModifiedDate;
	}


	public void setAlertEventModifiedDate(Timestamp alertEventModifiedDate) {
		Timestamp toTimeStamp = Timestamp.valueOf(alertEventModifiedDate+"");   //+" 00:00:00"
		this.alertEventModifiedDate = toTimeStamp;
	}


	public Integer getAlertEventChartId() {
		return alertEventChartId;
	}


	public void setAlertEventChartId(Integer alertEventChartId) {
		this.alertEventChartId = alertEventChartId;
	}


	public Integer getAlertEventRoomId() {
		return alertEventRoomId;
	}


	public void setAlertEventRoomId(Integer alertEventRoomId) {
		this.alertEventRoomId = alertEventRoomId;
	}


	public Integer getAlertEventStatus1() {
		return alertEventStatus1;
	}


	public void setAlertEventStatus1(Integer alertEventStatus1) {
		this.alertEventStatus1 = alertEventStatus1;
	}


	public Integer getAlertEventParentalertid() {
		return alertEventParentalertid;
	}


	public void setAlertEventParentalertid(Integer alertEventParentalertid) {
		this.alertEventParentalertid = alertEventParentalertid;
	}


	public Boolean getAlertEventIsgroupalert() {
		return alertEventIsgroupalert;
	}


	public void setAlertEventIsgroupalert(Boolean alertEventIsgroupalert) {
		this.alertEventIsgroupalert = alertEventIsgroupalert;
	}
	
	public void removeAlertEventReadDate() {
		this.alertEventReadDate = null;
	}


	public String getAlertEventFrompage() {
		return alertEventFrompage;
	}


	public void setAlertEventFrompage(String alertEventFrompage) {
		this.alertEventFrompage = alertEventFrompage;
	}


	public AlertCategory getAlertCategoryTable() {
		return alertCategoryTable;
	}


	public void setAlertCategoryTable(AlertCategory alertCategoryTable) {
		this.alertCategoryTable = alertCategoryTable;
	}


	public Room getRoomTable() {
		return roomTable;
	}


	public void setRoomTable(Room roomTable) {
		this.roomTable = roomTable;
	}


	public Chart getChartTable() {
		return chartTable;
	}


	public void setChartTable(Chart chartTable) {
		this.chartTable = chartTable;
	}


	public EmployeeProfile getEmpProfileTableFrom() {
		return empProfileTableFrom;
	}


	public void setEmpProfileTableFrom(EmployeeProfile empProfileTableFrom) {
		this.empProfileTableFrom = empProfileTableFrom;
	}


	public EmployeeProfile getEmpProfileTableTo() {
		return empProfileTableTo;
	}


	public void setEmpProfileTableTo(EmployeeProfile empProfileTableTo) {
		this.empProfileTableTo = empProfileTableTo;
	}


	public EmployeeProfile getEmpProfileTableReadBy() {
		return empProfileTableReadBy;
	}


	public void setEmpProfileTableReadBy(EmployeeProfile empProfileTableReadBy) {
		this.empProfileTableReadBy = empProfileTableReadBy;
	}


	public EmployeeProfile getEmpProfileTableModifiedBy() {
		return empProfileTableModifiedBy;
	}


	public void setEmpProfileTableModifiedBy(EmployeeProfile empProfileTableModifiedBy) {
		this.empProfileTableModifiedBy = empProfileTableModifiedBy;
	}


	public Encounter getEncounterTableId() {
		return encounterTableId;
	}


	public void setEncounterTableId(Encounter encounterTableId) {
		this.encounterTableId = encounterTableId;
	}


	public PortalMessage getPortalMessage() {
		return portalMessage;
	}


	public void setPortalMessage(PortalMessage portalMessage) {
		this.portalMessage = portalMessage;
	}


	public String getAlertEventCreatedDateTime() {
		return alertEventCreatedDateTime;
	}


	public void setAlertEventCreatedDateTime(String alertEventCreatedDateTime) {
		this.alertEventCreatedDateTime = alertEventCreatedDateTime;
	}
	
	
}