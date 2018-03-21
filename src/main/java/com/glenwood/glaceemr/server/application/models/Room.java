package com.glenwood.glaceemr.server.application.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name="room_details")
@JsonIgnoreProperties(ignoreUnknown = true)
public class Room {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = " room_details_room_details_id_seq")
	@SequenceGenerator(name = " room_details_room_details_id_seq", sequenceName = " room_details_room_details_id_seq", allocationSize = 1)
	@Column(name="room_details_id")
	@NotNull
	private Integer roomId;
	
	@Column(name="room_details_name")
	private String roomName;
	
	@Column(name="room_details_isactive")
	@NotNull
	private Boolean roomIsActive;
	

	/*@OneToMany(cascade=CascadeType.ALL,mappedBy="roomTable")
	@JsonManagedReference
	List<AlertEvent> alertTable;*/

	public Integer getRoomId() {
		return roomId;
	}

	public void setRoomId(Integer roomId) {
		this.roomId = roomId;
	}

	public String getRoomName() {
		return roomName;
	}

	public void setRoomName(String roomName) {
		this.roomName = roomName;
	}

	public Boolean getRoomIsActive() {
		return roomIsActive;
	}

	public void setRoomIsActive(Boolean roomIsActive) {
		this.roomIsActive = roomIsActive;
	}
}
