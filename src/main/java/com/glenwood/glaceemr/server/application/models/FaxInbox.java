package com.glenwood.glaceemr.server.application.models;

import java.io.Serializable;
import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.SequenceGenerator;

import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.glenwood.glaceemr.server.utils.JsonTimestampSerializer;

@Entity
@Table(name = "fax_inbox")
@JsonIgnoreProperties(ignoreUnknown = true)
public class FaxInbox implements Serializable {
	/**
	 * 
	 */
	private static long serialVersionUID = 1L;
	@Id
	@SequenceGenerator(name = "sequence", sequenceName = "fax_inbox_fax_inbox_id_seq")
	@GeneratedValue(strategy = GenerationType.AUTO, generator = "sequence")
	@Column(name="fax_inbox_id")
	private Integer fax_inbox_id;

	@Column(name="fax_inbox_sendername")
	private String fax_inbox_sendername;

	@Column(name="fax_inbox_recipientname")
	private String fax_inbox_recipientname;

	@Column(name="fax_inbox_recipientnumber")
	private String fax_inbox_recipientnumber;

	@JsonSerialize(using = JsonTimestampSerializer.class)
	@Column(name="fax_inbox_receiveddate")
	private Timestamp fax_inbox_receiveddate;

	@Column(name="fax_inbox_tsid")
	private String fax_inbox_tsid;

	@Column(name="fax_inbox_csid")
	private String fax_inbox_csid;

	@Column(name="fax_inbox_routinginfo")
	private String fax_inbox_routinginfo;

	@Column(name="fax_inbox_filenames")
	private String fax_inbox_filenames;

	@Column(name="fax_inbox_folderid")
	private Integer fax_inbox_folderid;

	@Column(name="fax_inbox_subject")
	private String fax_inbox_subject;

	@Column(name="fax_inbox_type")
	private Integer fax_inbox_type;

	@Column(name="fax_inbox_statusid")
	private Integer fax_inbox_statusid;

	@Column(name="fax_inbox_forwardeduserid")
	private Integer fax_inbox_forwardeduserid;

	@Column(name="fax_inbox_modifieduserid")
	private Integer fax_inbox_modifieduserid;

	@JsonSerialize(using = JsonTimestampSerializer.class)
	@Column(name="fax_inbox_modifiedtime")
	private Timestamp fax_inbox_modifiedtime;

	@Column(name="fax_inbox_location")
	private Integer fax_inbox017Faxbox;

	@Column(name="fax_inbox_isenabled")
	private Boolean fax_inbox018Isenabled;

	@Column(name="fax_inbox_callerid")
	private String fax_inbox019Callerid;

	@Column(name="fax_inbox_notes")
	private String fax_inbox020Faxnotes;

	@Column(name="fax_inbox_metrofaxid")
	private String fax_inbox021MetrofaxId;

	@ManyToOne(fetch=FetchType.LAZY)
	@JsonManagedReference
	@JoinColumn(name = "fax_inbox_folderid", referencedColumnName = "fax_folder_id", insertable = false, updatable = false)
	public FaxFolder faxFolder;

	@ManyToOne(fetch=FetchType.LAZY)
	@JsonManagedReference
	@JoinColumn(name = "fax_inbox_type", referencedColumnName = "fax_type_id", insertable = false, updatable = false)
	private FaxType faxType;

	@ManyToOne(fetch=FetchType.LAZY)
	@JsonManagedReference
	@JoinColumn(name = "fax_inbox_statusid", referencedColumnName = "statusid", insertable = false, updatable = false)
	private InFaxStatus faxStatus;

	@NotFound(action=NotFoundAction.IGNORE)
	@ManyToOne(fetch=FetchType.LAZY)
	@JsonManagedReference
	@JoinColumn(name = "fax_inbox_forwardeduserid", referencedColumnName = "emp_profile_empid", insertable = false, updatable = false)
	private EmployeeProfile  emplopyeeProfile;

	@NotFound(action=NotFoundAction.IGNORE)
	@ManyToOne(fetch=FetchType.LAZY)
	@JsonManagedReference
	@JoinColumn(name = "fax_inbox_modifieduserid", referencedColumnName = "emp_profile_empid", insertable = false, updatable = false)
	private EmployeeProfile  emplopyeeProfile1;

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public static void setSerialversionuid(long serialversionuid) {
		serialVersionUID = serialversionuid;
	}

	public FaxFolder getFaxFolder() {
		return faxFolder;
	}

	public void setFaxFolder(FaxFolder faxFolder) {
		this.faxFolder = faxFolder;
	}

	public FaxType getFaxType() {
		return faxType;
	}

	public void setFaxType(FaxType faxType) {
		this.faxType = faxType;
	}

	public InFaxStatus getFaxStatus() {
		return faxStatus;
	}

	public void setFaxStatus(InFaxStatus faxStatus) {
		this.faxStatus = faxStatus;
	}

	public EmployeeProfile getEmplopyeeProfile() {
		return emplopyeeProfile;
	}

	public void setEmplopyeeProfile(EmployeeProfile emplopyeeProfile) {
		this.emplopyeeProfile = emplopyeeProfile;
	}

	public EmployeeProfile getEmplopyeeProfile1() {
		return emplopyeeProfile1;
	}

	public void setEmplopyeeProfile1(EmployeeProfile emplopyeeProfile1) {
		this.emplopyeeProfile1 = emplopyeeProfile1;
	}

	public Integer getfax_inbox_id() {
		return fax_inbox_id;
	}

	public void setfax_inbox_id(Integer fax_inbox_id) {
		this.fax_inbox_id = fax_inbox_id;
	}

	public String getfax_inbox_sendername() {
		return fax_inbox_sendername;
	}

	public void setfax_inbox_sendername(String fax_inbox_sendername) {
		this.fax_inbox_sendername = fax_inbox_sendername;
	}

	public String getfax_inbox_recipientname() {
		return fax_inbox_recipientname;
	}

	public void setfax_inbox_recipientname(String fax_inbox_recipientname) {
		this.fax_inbox_recipientname = fax_inbox_recipientname;
	}

	public String getfax_inbox_recipientnumber() {
		return fax_inbox_recipientnumber;
	}

	public void setfax_inbox_recipientnumber(String fax_inbox_recipientnumber) {
		this.fax_inbox_recipientnumber = fax_inbox_recipientnumber;
	}

	public Timestamp getfax_inbox_receiveddate() {
		return fax_inbox_receiveddate;
	}

	public void setfax_inbox_receiveddate(Timestamp fax_inbox_receiveddate) {
		this.fax_inbox_receiveddate = fax_inbox_receiveddate;
	}

	public String getfax_inbox_tsid() {
		return fax_inbox_tsid;
	}

	public void setfax_inbox_tsid(String fax_inbox_tsid) {
		this.fax_inbox_tsid = fax_inbox_tsid;
	}

	public String getfax_inbox_csid() {
		return fax_inbox_csid;
	}

	public void setfax_inbox_csid(String fax_inbox_csid) {
		this.fax_inbox_csid = fax_inbox_csid;
	}

	public String getfax_inbox_routinginfo() {
		return fax_inbox_routinginfo;
	}

	public void setfax_inbox_routinginfo(String fax_inbox_routinginfo) {
		this.fax_inbox_routinginfo = fax_inbox_routinginfo;
	}

	public String getfax_inbox_filenames() {
		return fax_inbox_filenames;
	}

	public void setfax_inbox_filenames(String fax_inbox_filenames) {
		this.fax_inbox_filenames = fax_inbox_filenames;
	}

	public Integer getfax_inbox_folderid() {
		return fax_inbox_folderid;
	}

	public void setfax_inbox_folderid(Integer fax_inbox_folderid) {
		this.fax_inbox_folderid = fax_inbox_folderid;
	}

	public String getfax_inbox_subject() {
		return fax_inbox_subject;
	}

	public void setfax_inbox_subject(String fax_inbox_subject) {
		this.fax_inbox_subject = fax_inbox_subject;
	}

	public Integer getfax_inbox_type() {
		return fax_inbox_type;
	}

	public void setfax_inbox_type(Integer fax_inbox_type) {
		this.fax_inbox_type = fax_inbox_type;
	}

	public Integer getfax_inbox_statusid() {
		return fax_inbox_statusid;
	}

	public void setfax_inbox_statusid(Integer fax_inbox_statusid) {
		this.fax_inbox_statusid = fax_inbox_statusid;
	}

	public Integer getfax_inbox_forwardeduserid() {
		return fax_inbox_forwardeduserid;
	}

	public void setfax_inbox_forwardeduserid(Integer fax_inbox_forwardeduserid) {
		this.fax_inbox_forwardeduserid = fax_inbox_forwardeduserid;
	}

	public Integer getfax_inbox_modifieduserid() {
		return fax_inbox_modifieduserid;
	}

	public void setfax_inbox_modifieduserid(Integer fax_inbox_modifieduserid) {
		this.fax_inbox_modifieduserid = fax_inbox_modifieduserid;
	}

	public Timestamp getfax_inbox_modifiedtime() {
		return fax_inbox_modifiedtime;
	}

	public void setfax_inbox_modifiedtime(Timestamp fax_inbox_modifiedtime) {
		this.fax_inbox_modifiedtime = fax_inbox_modifiedtime;
	}

	public Integer getfax_inbox017Faxbox() {
		return fax_inbox017Faxbox;
	}

	public void setfax_inbox017Faxbox(Integer fax_inbox017Faxbox) {
		this.fax_inbox017Faxbox = fax_inbox017Faxbox;
	}

	public Boolean getfax_inbox018Isenabled() {
		return fax_inbox018Isenabled;
	}

	public void setfax_inbox018Isenabled(Boolean fax_inbox018Isenabled) {
		this.fax_inbox018Isenabled = fax_inbox018Isenabled;
	}

	public String getfax_inbox019Callerid() {
		return fax_inbox019Callerid;
	}

	public void setfax_inbox019Callerid(String fax_inbox019Callerid) {
		this.fax_inbox019Callerid = fax_inbox019Callerid;
	}

	public String getfax_inbox020Faxnotes() {
		return fax_inbox020Faxnotes;
	}

	public void setfax_inbox020Faxnotes(String fax_inbox020Faxnotes) {
		this.fax_inbox020Faxnotes = fax_inbox020Faxnotes;
	}

	public String getfax_inbox021MetrofaxId() {
		return fax_inbox021MetrofaxId;
	}

	public void setfax_inbox021MetrofaxId(String fax_inbox021MetrofaxId) {
		this.fax_inbox021MetrofaxId = fax_inbox021MetrofaxId;
	}

}