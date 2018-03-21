package com.glenwood.glaceemr.server.application.services.roomStatus;

import java.util.List;

import com.glenwood.glaceemr.server.application.models.EmployeeProfile;
import com.glenwood.glaceemr.server.application.models.PosTable;
import com.glenwood.glaceemr.server.application.models.Room;

public class RoomInfoBean {
	private List<PosTable> locations;
	private List<EmployeeProfile> providers;
	private List<PosRooms> roomStatus;
	private List<OrderedData> ordered;
	private List<Room> roomDetail;
	private List<ActivitiesData> activities;
	
	public RoomInfoBean(List<PosTable> locations, List<EmployeeProfile> providers,List<PosRooms> roomStatus, List<OrderedData> ordered,List<Room> roomDetail, List<ActivitiesData> activities) {
		super();
		this.locations=locations;
		this.providers=providers;
		this.roomStatus=roomStatus;
		this.ordered=ordered;
		this.roomDetail=roomDetail;
		this.activities=activities;
	}

	public List<PosTable> getLocations() {
		return locations;
	}

	public void setLocations(List<PosTable> locations) {
		this.locations = locations;
	}

	public List<EmployeeProfile> getProviders() {
		return providers;
	}

	public void setProviders(List<EmployeeProfile> providers) {
		this.providers = providers;
	}

	public List<PosRooms> getRoomStatus() {
		return roomStatus;
	}

	public void setRoomStatus(List<PosRooms> roomStatus) {
		this.roomStatus = roomStatus;
	}

	public List<OrderedData> getOrdered() {
		return ordered;
	}

	public void setOrdered(List<OrderedData> ordered) {
		this.ordered = ordered;
	}

	public List<Room> getRoomDetail() {
		return roomDetail;
	}

	public void setRoomDetail(List<Room> roomDetail) {
		this.roomDetail = roomDetail;
	}

	public List<ActivitiesData> getActivities() {
		return activities;
	}

	public void setActivities(List<ActivitiesData> activities) {
		this.activities = activities;
	}

}
