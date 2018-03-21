package com.glenwood.glaceemr.server.application.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name = "primarykey_generator")
@JsonIgnoreProperties(ignoreUnknown = true)
public class PrimarykeyGenerator {
	@Id
	@Column(name="primarykey_generator_tableid")
	private Integer primarykey_generator_tableid;
	
	@Column(name="primarykey_generator_tablename")
	private String primarykey_generator_tablename;
	
	@Column(name="primarykey_generator_rowcount")
	private Integer primarykey_generator_rowcount;
	
	@Column(name="primarykey_generator_minval")
	private Integer primarykey_generator_minval;
	
	@Column(name="primarykey_generator_maxval")
	private Integer primarykey_generator_maxval;
	
	@Column(name="primarykey_generator_maxval2")
	private Integer primarykey_generator_maxval2;
	
	@Column(name="primarykey_generator_isactive",columnDefinition="integer default 0")
	private Integer primarykey_generator_isactive;

	public Integer getprimarykey_generator_tableid() {
		return primarykey_generator_tableid;
	}

	public void setprimarykey_generator_tableid(Integer primarykey_generator_tableid) {
		this.primarykey_generator_tableid = primarykey_generator_tableid;
	}

	public String getprimarykey_generator_tablename() {
		return primarykey_generator_tablename;
	}

	public void setprimarykey_generator_tablename(String primarykey_generator_tablename) {
		this.primarykey_generator_tablename = primarykey_generator_tablename;
	}

	public Integer getprimarykey_generator_rowcount() {
		return primarykey_generator_rowcount;
	}

	public void setprimarykey_generator_rowcount(Integer primarykey_generator_rowcount) {
		this.primarykey_generator_rowcount = primarykey_generator_rowcount;
	}

	public Integer getprimarykey_generator_minval() {
		return primarykey_generator_minval;
	}

	public void setprimarykey_generator_minval(Integer primarykey_generator_minval) {
		this.primarykey_generator_minval = primarykey_generator_minval;
	}

	public Integer getprimarykey_generator_maxval() {
		return primarykey_generator_maxval;
	}

	public void setprimarykey_generator_maxval(Integer primarykey_generator_maxval) {
		this.primarykey_generator_maxval = primarykey_generator_maxval;
	}

	public Integer getprimarykey_generator_maxval2() {
		return primarykey_generator_maxval2;
	}

	public void setprimarykey_generator_maxval2(Integer primarykey_generator_maxval2) {
		this.primarykey_generator_maxval2 = primarykey_generator_maxval2;
	}

	public Integer getprimarykey_generator_isactive() {
		return primarykey_generator_isactive;
	}

	public void setprimarykey_generator_isactive(Integer primarykey_generator_isactive) {
		this.primarykey_generator_isactive = primarykey_generator_isactive;
	}
}
