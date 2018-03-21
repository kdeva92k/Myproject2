package com.glenwood.glaceemr.server.application.models;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.glenwood.glaceemr.server.utils.JsonTimestampSerializer;

@Entity
@Table(name = "fax_outbox")
@JsonIgnoreProperties(ignoreUnknown = true)
public class FaxOutbox implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator="fax_outbox_fax_outbox_id_seq")
	@SequenceGenerator(name ="fax_outbox_fax_outbox_id_seq", sequenceName="fax_outbox_fax_outbox_id_seq", allocationSize=1)
	@Column(name="fax_outbox_id")
	private Integer fax_outbox_id;

	@Column(name="fax_outbox_subject")
	private String fax_outbox_subject;

	@Column(name="fax_outbox_htmlfile")
	private String fax_outbox_htmlfile;

	@Column(name="fax_outbox_folderid")
	private Integer fax_outbox_folderid;

	@Column(name="fax_outbox_type")
	private Integer fax_outbox_type;

	@JsonSerialize(using = JsonTimestampSerializer.class)
	@Column(name="fax_outbox_scheduledtime")
	private Timestamp fax_outbox_scheduledtime;

	@JsonSerialize(using = JsonTimestampSerializer.class)
	@Column(name="fax_outbox_dispatchtime")
	private Timestamp fax_outbox_dispatchtime;

	@Column(name="fax_outbox_statusid")
	private Integer fax_outbox_statusid;

	@Column(name="fax_outbox_recipientnumber")
	private String fax_outbox_recipientnumber;

	@Column(name="fax_outbox_recipientname")
	private String fax_outbox_recipientname;

	@Column(name="fax_outbox_attachmentpath")
	private String fax_outbox_attachmentpath;

	@Column(name="fax_outbox_forwardedto")
	private Integer fax_outbox_forwardedto;

	@Column(name="fax_outbox_createdby")
	private Integer fax_outbox_createdby;

	@JsonSerialize(using = JsonTimestampSerializer.class)
	@Column(name="fax_outbox_createddate")
	private Timestamp fax_outbox_createddate;

	@Column(name="fax_outbox_billingcode")
	private String fax_outbox_billingcode;

	@Column(name="fax_outbox_iscoverpage")
	private Boolean fax_outbox_iscoverpage;

	@Column(name="fax_outbox_coverpagetemplate")
	private String fax_outbox_coverpagetemplate;

	@Column(name="fax_outbox_comments")
	private String fax_outbox_comments;

	@Column(name="fax_outbox_modifiedby")
	private int fax_outbox_modifiedby;

	@JsonSerialize(using = JsonTimestampSerializer.class)
	@Column(name="fax_outbox_modifieddate")
	private Timestamp fax_outbox_modifieddate;

	@Column(name="fax_outbox_jobid")
	private Integer fax_outbox_jobid;

	@Column(name="fax_outbox_sendername")
	private String fax_outbox_sendername;

	@Column(name="fax_outbox_location")
	private Integer fax_outbox023Faxbox;

	@Column(name="fax_outbox_patientid")
	private Integer fax_outbox024AssociatedPatientid;

	@Column(name="fax_outbox_attachedfiles")
	private String fax_outboxAttachment;

	@Column(name="fax_outbox_noofretries")
	private Integer fax_outboxNoofretries;

	@Column(name="fax_outbox_rulename")
	private String fax_outboxRulename;

	@Column(name="fax_outbox_displaynumber")
	private String fax_outboxDisplaynumber;

	@Column(name="fax_outbox_isdialrule")
	private Boolean fax_outboxIsDialRuleDisabled;

	@Column(name="fax_outbox_pngfiles")
	private String fax_outboxFaxpngfiles;

	@Column(name="fax_outbox_trackingid")
	private String fax_outboxTrackingNo;

	@Column(name="fax_outbox_retriesnote")
	private String fax_outboxRetriesNote;

	@NotFound(action=NotFoundAction.IGNORE)
	@ManyToOne(fetch=FetchType.LAZY)
	@JsonManagedReference
	@JoinColumn(name = "fax_outbox_id", referencedColumnName = "emp_profile_empid", insertable = false, updatable = false)
	private EmployeeProfile empProfile;
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JsonManagedReference
	@JoinColumn(name = "fax_outbox_folderid", referencedColumnName = "fax_folder_id", insertable = false, updatable = false)
	private FaxFolder faxFolder;
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JsonManagedReference
	@JoinColumn(name = "fax_outbox_statusid", referencedColumnName = "statusid", insertable = false, updatable = false)
	private FaxStatus faxStatus;
	
	@NotFound(action=NotFoundAction.IGNORE)
	@ManyToOne(fetch=FetchType.LAZY)
	@JsonManagedReference
	@JoinColumn(name = "fax_outbox_forwardedto", referencedColumnName = "emp_profile_empid", insertable = false, updatable = false)
	private EmployeeProfile chart_users_1;
	
	@NotFound(action=NotFoundAction.IGNORE)
	@ManyToOne(fetch=FetchType.LAZY)
	@JsonManagedReference
	@JoinColumn(name = "fax_outbox_createdby", referencedColumnName = "emp_profile_empid", insertable = false, updatable = false)
	private EmployeeProfile chart_users_2;

	@NotFound(action=NotFoundAction.IGNORE)
	@ManyToOne(fetch=FetchType.LAZY)
	@JsonManagedReference
	@JoinColumn(name = "fax_outbox_modifiedby", referencedColumnName = "emp_profile_empid", insertable = false, updatable = false)
	private EmployeeProfile chart_users_3;
	
	@OneToMany(mappedBy = "fax_outbox" , fetch=FetchType.LAZY)
	List<ConsultFaxTracking> consultFaxTrackings;
	
	public List<ConsultFaxTracking> getConsultFaxTrackings() {
		return consultFaxTrackings;
	}

	public void setConsultFaxTrackings(List<ConsultFaxTracking> consultFaxTrackings) {
		this.consultFaxTrackings = consultFaxTrackings;
	}
	
	public Integer getfax_outbox_id() {
		return fax_outbox_id;
	}

	public void setfax_outbox_id(Integer fax_outbox_id) {
		this.fax_outbox_id = fax_outbox_id;
	}

	public String getfax_outbox_subject() {
		return fax_outbox_subject;
	}

	public void setfax_outbox_subject(String fax_outbox_subject) {
		this.fax_outbox_subject = fax_outbox_subject;
	}

	public String getfax_outbox_htmlfile() {
		return fax_outbox_htmlfile;
	}

	public void setfax_outbox_htmlfile(String fax_outbox_htmlfile) {
		this.fax_outbox_htmlfile = fax_outbox_htmlfile;
	}

	public Integer getfax_outbox_folderid() {
		return fax_outbox_folderid;
	}

	public void setfax_outbox_folderid(Integer fax_outbox_folderid) {
		this.fax_outbox_folderid = fax_outbox_folderid;
	}

	public Integer getfax_outbox_type() {
		return fax_outbox_type;
	}

	public void setfax_outbox_type(Integer fax_outbox_type) {
		this.fax_outbox_type = fax_outbox_type;
	}

	public Timestamp getfax_outbox_scheduledtime() {
		return fax_outbox_scheduledtime;
	}

	public void setfax_outbox_scheduledtime(Timestamp fax_outbox_scheduledtime) {
		this.fax_outbox_scheduledtime = fax_outbox_scheduledtime;
	}

	public Timestamp getfax_outbox_dispatchtime() {
		return fax_outbox_dispatchtime;
	}

	public void setfax_outbox_dispatchtime(Timestamp fax_outbox_dispatchtime) {
		this.fax_outbox_dispatchtime = fax_outbox_dispatchtime;
	}

	public Integer getfax_outbox_statusid() {
		return fax_outbox_statusid;
	}

	public void setfax_outbox_statusid(Integer fax_outbox_statusid) {
		this.fax_outbox_statusid = fax_outbox_statusid;
	}

	public String getfax_outbox_recipientnumber() {
		return fax_outbox_recipientnumber;
	}

	public void setfax_outbox_recipientnumber(String fax_outbox_recipientnumber) {
		this.fax_outbox_recipientnumber = fax_outbox_recipientnumber;
	}

	public String getfax_outbox_recipientname() {
		return fax_outbox_recipientname;
	}

	public void setfax_outbox_recipientname(String fax_outbox_recipientname) {
		this.fax_outbox_recipientname = fax_outbox_recipientname;
	}

	public String getfax_outbox_attachmentpath() {
		return fax_outbox_attachmentpath;
	}

	public void setfax_outbox_attachmentpath(String fax_outbox_attachmentpath) {
		this.fax_outbox_attachmentpath = fax_outbox_attachmentpath;
	}

	public Integer getfax_outbox_forwardedto() {
		return fax_outbox_forwardedto;
	}

	public void setfax_outbox_forwardedto(Integer fax_outbox_forwardedto) {
		this.fax_outbox_forwardedto = fax_outbox_forwardedto;
	}

	public Integer getfax_outbox_createdby() {
		return fax_outbox_createdby;
	}

	public void setfax_outbox_createdby(Integer fax_outbox_createdby) {
		this.fax_outbox_createdby = fax_outbox_createdby;
	}

	public Timestamp getfax_outbox_createddate() {
		return fax_outbox_createddate;
	}

	public void setfax_outbox_createddate(Timestamp fax_outbox_createddate) {
		this.fax_outbox_createddate = fax_outbox_createddate;
	}

	public String getfax_outbox_billingcode() {
		return fax_outbox_billingcode;
	}

	public void setfax_outbox_billingcode(String fax_outbox_billingcode) {
		this.fax_outbox_billingcode = fax_outbox_billingcode;
	}

	public Boolean getfax_outbox_iscoverpage() {
		return fax_outbox_iscoverpage;
	}

	public void setfax_outbox_iscoverpage(Boolean fax_outbox_iscoverpage) {
		this.fax_outbox_iscoverpage = fax_outbox_iscoverpage;
	}

	public String getfax_outbox_coverpagetemplate() {
		return fax_outbox_coverpagetemplate;
	}

	public void setfax_outbox_coverpagetemplate(String fax_outbox_coverpagetemplate) {
		this.fax_outbox_coverpagetemplate = fax_outbox_coverpagetemplate;
	}

	public String getfax_outbox_comments() {
		return fax_outbox_comments;
	}

	public void setfax_outbox_comments(String fax_outbox_comments) {
		this.fax_outbox_comments = fax_outbox_comments;
	}

	public int getfax_outbox_modifiedby() {
		return fax_outbox_modifiedby;
	}

	public void setfax_outbox_modifiedby(int fax_outbox_modifiedby) {
		this.fax_outbox_modifiedby = fax_outbox_modifiedby;
	}

	public Timestamp getfax_outbox_modifieddate() {
		return fax_outbox_modifieddate;
	}

	public void setfax_outbox_modifieddate(Timestamp fax_outbox_modifieddate) {
		this.fax_outbox_modifieddate = fax_outbox_modifieddate;
	}

	public Integer getfax_outbox_jobid() {
		return fax_outbox_jobid;
	}

	public void setfax_outbox_jobid(Integer fax_outbox_jobid) {
		this.fax_outbox_jobid = fax_outbox_jobid;
	}

	public String getfax_outbox_sendername() {
		return fax_outbox_sendername;
	}

	public void setfax_outbox_sendername(String fax_outbox_sendername) {
		this.fax_outbox_sendername = fax_outbox_sendername;
	}

	public Integer getfax_outbox023Faxbox() {
		return fax_outbox023Faxbox;
	}

	public void setfax_outbox023Faxbox(Integer fax_outbox023Faxbox) {
		this.fax_outbox023Faxbox = fax_outbox023Faxbox;
	}

	public Integer getfax_outbox024AssociatedPatientid() {
		return fax_outbox024AssociatedPatientid;
	}

	public void setfax_outbox024AssociatedPatientid(Integer fax_outbox024AssociatedPatientid) {
		this.fax_outbox024AssociatedPatientid = fax_outbox024AssociatedPatientid;
	}

	public String getfax_outboxAttachment() {
		return fax_outboxAttachment;
	}

	public void setfax_outboxAttachment(String fax_outboxAttachment) {
		this.fax_outboxAttachment = fax_outboxAttachment;
	}

	public Integer getfax_outboxNoofretries() {
		return fax_outboxNoofretries;
	}

	public void setfax_outboxNoofretries(Integer fax_outboxNoofretries) {
		this.fax_outboxNoofretries = fax_outboxNoofretries;
	}

	public String getfax_outboxRulename() {
		return fax_outboxRulename;
	}

	public void setfax_outboxRulename(String fax_outboxRulename) {
		this.fax_outboxRulename = fax_outboxRulename;
	}

	public String getfax_outboxDisplaynumber() {
		return fax_outboxDisplaynumber;
	}

	public void setfax_outboxDisplaynumber(String fax_outboxDisplaynumber) {
		this.fax_outboxDisplaynumber = fax_outboxDisplaynumber;
	}

	public Boolean getfax_outboxIsDialRuleDisabled() {
		return fax_outboxIsDialRuleDisabled;
	}

	public void setfax_outboxIsDialRuleDisabled(Boolean fax_outboxIsDialRuleDisabled) {
		this.fax_outboxIsDialRuleDisabled = fax_outboxIsDialRuleDisabled;
	}

	public String getfax_outboxFaxpngfiles() {
		return fax_outboxFaxpngfiles;
	}

	public void setfax_outboxFaxpngfiles(String fax_outboxFaxpngfiles) {
		this.fax_outboxFaxpngfiles = fax_outboxFaxpngfiles;
	}

	public String getfax_outboxTrackingNo() {
		return fax_outboxTrackingNo;
	}

	public void setfax_outboxTrackingNo(String fax_outboxTrackingNo) {
		this.fax_outboxTrackingNo = fax_outboxTrackingNo;
	}

	public String getfax_outboxRetriesNote() {
		return fax_outboxRetriesNote;
	}

	public void setfax_outboxRetriesNote(String fax_outboxRetriesNote) {
		this.fax_outboxRetriesNote = fax_outboxRetriesNote;
	}

	public FaxFolder getFaxFolder() {
		return faxFolder;
	}

	public void setFaxFolder(FaxFolder faxFolder) {
		this.faxFolder = faxFolder;
	}

	public EmployeeProfile getEmpProfile() {
		return empProfile;
	}

	public void setEmpProfile(EmployeeProfile empProfile) {
		this.empProfile = empProfile;
	}

	public EmployeeProfile getChart_users_1() {
		return chart_users_1;
	}

	public void setChart_users_1(EmployeeProfile chart_users_1) {
		this.chart_users_1 = chart_users_1;
	}

	public EmployeeProfile getChart_users_2() {
		return chart_users_2;
	}

	public void setChart_users_2(EmployeeProfile chart_users_2) {
		this.chart_users_2 = chart_users_2;
	}

	public EmployeeProfile getChart_users_3() {
		return chart_users_3;
	}

	public void setChart_users_3(EmployeeProfile chart_users_3) {
		this.chart_users_3 = chart_users_3;
	}

	public FaxStatus getFaxStatus() {
		return faxStatus;
	}

	public void setFaxStatus(FaxStatus faxStatus) {
		this.faxStatus = faxStatus;
	}
}