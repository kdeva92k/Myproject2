package com.glenwood.glaceemr.server.application.models;

import java.sql.Date;
import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.glenwood.glaceemr.server.utils.JsonTimestampSerializer;

@Entity
@JsonIgnoreProperties(ignoreUnknown = true)
@Table(name = "sch_lock")
public class SchedulerLock {

	@Id
	 @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sch_lock_sch_lock_relation_id_seq")
	@SequenceGenerator(name = "sch_lock_sch_lock_relation_id_seq", sequenceName = "sch_lock_sch_lock_relation_id_seq", allocationSize = 1)
	@Column(name="sch_lock_id")
	private Integer schLockId;

	@Column(name="sch_lock_date")
	private Date schLockDate;

	@Column(name="sch_lock_starttime")
	private Timestamp schLockStarttime;

	@Column(name="sch_lock_endtime")
	private Timestamp schLockEndtime;

	@Column(name="sch_lock_interval")
	private Integer schLockInterval;

	@Column(name="sch_lock_resource_id")
	private Integer schLockResourceId;

	@Column(name="sch_lock_status_id")
	private Integer schLockStatusId;

	@Column(name="sch_lock_type_id")
	private Integer schLockTypeId;

	@Column(name="sch_lock_location_id")
	private Integer schLockLocationId;

	@Column(name="sch_lock_safe4")
	private Integer schLockSafe4;

	@Column(name="sch_lock_islocked")
	private Boolean schLockIslocked;

	@Column(name="sch_lock_notes")
	private String schLockNotes;

	@JsonSerialize(using = JsonTimestampSerializer.class)
	@Column(name="sch_lock_lockeddate")
	private Timestamp schLockLockeddate;

	@Column(name="sch_lock_lockedbyuser")
	private String schLockLockedbyuser;

	@Column(name="sch_lock_safe1")
	private Integer schLockSafe1;

	@Column(name="sch_lock_safe2")
	private String schLockSafe2;

	@Column(name="h555555")
	private Integer h555555;
	
	@Column(name="sch_lock_samedayslot")
	private Integer schLockSameDaySlot;

	public Integer getSchLockId() {
		return schLockId;
	}

	public void setSchLockId(Integer schLockId) {
		this.schLockId = schLockId;
	}

	public Date getSchLockDate() {
		return schLockDate;
	}

	public void setSchLockDate(Date schLockDate) {
		this.schLockDate = schLockDate;
	}

	public Timestamp getSchLockStarttime() {
		return schLockStarttime;
	}

	public void setSchLockStarttime(Timestamp schLockStarttime) {
		this.schLockStarttime = schLockStarttime;
	}

	public Timestamp getSchLockEndtime() {
		return schLockEndtime;
	}

	public void setSchLockEndtime(Timestamp schLockEndtime) {
		this.schLockEndtime = schLockEndtime;
	}

	public Integer getSchLockInterval() {
		return schLockInterval;
	}

	public void setSchLockInterval(Integer schLockInterval) {
		this.schLockInterval = schLockInterval;
	}

	public Integer getSchLockResourceId() {
		return schLockResourceId;
	}

	public void setSchLockResourceId(Integer schLockResourceId) {
		this.schLockResourceId = schLockResourceId;
	}

	public Integer getSchLockStatusId() {
		return schLockStatusId;
	}

	public void setSchLockStatusId(Integer schLockStatusId) {
		this.schLockStatusId = schLockStatusId;
	}

	public Integer getSchLockTypeId() {
		return schLockTypeId;
	}

	public void setSchLockTypeId(Integer schLockTypeId) {
		this.schLockTypeId = schLockTypeId;
	}

	public Integer getSchLockLocationId() {
		return schLockLocationId;
	}

	public void setSchLockLocationId(Integer schLockLocationId) {
		this.schLockLocationId = schLockLocationId;
	}

	public Integer getSchLockSafe4() {
		return schLockSafe4;
	}

	public void setSchLockSafe4(Integer schLockSafe4) {
		this.schLockSafe4 = schLockSafe4;
	}

	public Boolean getSchLockIslocked() {
		return schLockIslocked;
	}

	public void setSchLockIslocked(Boolean schLockIslocked) {
		this.schLockIslocked = schLockIslocked;
	}

	public String getSchLockNotes() {
		return schLockNotes;
	}

	public void setSchLockNotes(String schLockNotes) {
		this.schLockNotes = schLockNotes;
	}

	public Timestamp getSchLockLockeddate() {
		return schLockLockeddate;
	}

	public void setSchLockLockeddate(Timestamp schLockLockeddate) {
		this.schLockLockeddate = schLockLockeddate;
	}

	public String getSchLockLockedbyuser() {
		return schLockLockedbyuser;
	}

	public void setSchLockLockedbyuser(String schLockLockedbyuser) {
		this.schLockLockedbyuser = schLockLockedbyuser;
	}

	public Integer getSchLockSafe1() {
		return schLockSafe1;
	}

	public void setSchLockSafe1(Integer schLockSafe1) {
		this.schLockSafe1 = schLockSafe1;
	}

	public String getSchLockSafe2() {
		return schLockSafe2;
	}

	public void setSchLockSafe2(String schLockSafe2) {
		this.schLockSafe2 = schLockSafe2;
	}

	public Integer getH555555() {
		return h555555;
	}

	public void setH555555(Integer h555555) {
		this.h555555 = h555555;
	}

	public Integer getSchLockSameDaySlot() {
		return schLockSameDaySlot;
	}

	public void setSchLockSameDaySlot(Integer schLockSameDaySlot) {
		this.schLockSameDaySlot = schLockSameDaySlot;
	}
	
}