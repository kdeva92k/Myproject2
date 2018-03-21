package com.glenwood.glaceemr.server.application.models;

import java.sql.Timestamp;

import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.SequenceGenerator;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.glenwood.glaceemr.server.utils.JsonTimestampSerializer;

@Entity
@Table(name = "prescriberspecificdrug")
public class Prescriberspecificdrug {

	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator="prescriberspecificdrug_psd_id_seq")
	@SequenceGenerator(name =" prescriberspecificdrug_psd_id_seq", sequenceName="prescriberspecificdrug_psd_id_seq", allocationSize=1)
	@Column(name="psd_id")
	private Integer psdId;

	@Column(name="groupid")
	private Integer groupid;

	@Id
	@Column(name="drugid")
	private Integer drugid;
	
	@Column(name="loginid")
	private Integer loginid;
}