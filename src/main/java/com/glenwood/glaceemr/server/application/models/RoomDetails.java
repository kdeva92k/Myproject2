package com.glenwood.glaceemr.server.application.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name = "room_details")
@JsonIgnoreProperties(ignoreUnknown = true)
public class RoomDetails {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = " room_details_room_details_id_seq")
	@SequenceGenerator(name = " room_details_room_details_id_seq", sequenceName = " room_details_room_details_id_seq", allocationSize = 1)
	@Column(name="room_details_id")
	private Integer room_details_id;

	@Column(name="room_details_name")
	private String room_details_name;

	@Column(name="room_details_isactive")
	private Boolean room_details_isactive;

	public Integer getroom_details_id() {
		return room_details_id;
	}

	public void setroom_details_id(Integer room_details_id) {
		this.room_details_id = room_details_id;
	}

	public String getroom_details_name() {
		return room_details_name;
	}

	public void setroom_details_name(String room_details_name) {
		this.room_details_name = room_details_name;
	}

	public Boolean getroom_details_isactive() {
		return room_details_isactive;
	}

	public void setroom_details_isactive(Boolean room_details_isactive) {
		this.room_details_isactive = room_details_isactive;
	}
	
	
}