package com.glenwood.glaceemr.server.application.models;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;


@Entity
@Table(name = "user_group")
public class UserGroup{

	@Column(name="user_group_groupid")
	private int groupId;

	@Column(name="user_group_groupname")
	private String groupName;

	@Column(name="user_group_userid")
	private int userId;

	@Column(name="user_group_username")
	private String userName;

	@Id
	@SequenceGenerator(name = "sequence", sequenceName = "user_group_sequence")
	@GeneratedValue(strategy = GenerationType.AUTO, generator = "sequence")
	@Column(name="user_group_id")
	private int id;

	public int getGroupid() {
		return groupId;
	}

	public void setGroupid(int groupId) {
		this.groupId = groupId;
	}

	public String getGroupname() {
		return groupName;
	}

	public void setGroupname(String groupName) {
		this.groupName = groupName;
	}

	public int getUserid() {
		return userId;
	}

	public void setUserid(int userId) {
		this.userId = userId;
	}

	public String getUsername() {
		return userName;
	}

	public void setUsername(String userName) {
		this.userName = userName;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	
	


}
