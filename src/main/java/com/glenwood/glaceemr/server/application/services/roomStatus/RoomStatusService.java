package com.glenwood.glaceemr.server.application.services.roomStatus;

import java.util.List;

import org.json.JSONObject;

import com.glenwood.glaceemr.server.application.models.EmployeeProfile;
import com.glenwood.glaceemr.server.application.models.PosTable;
import com.glenwood.glaceemr.server.application.models.Room;

public interface RoomStatusService {
   
	/**
	 * to get pos
	 * @param pos 
	 * @param pos 
	 * @return list of pos
	 */
	List<PosTable> getPos(Integer pos);
	
	/**
	 * to get providers
	 * @return list of providers
	 */
	List<EmployeeProfile> getproviders();
	
	/**
	 * to get patientsData
	 * @param pos
	 * @return list of patientsData
	 */
	List<PosRooms> getTodaysPatientsData(Integer pos);
	
	/**
	 * to get roomName
	 * @return list of rooms
	 */
	List<Room> getRoomName();
	
	/**
	 * to updateRoomNo
	 * @param pos
	 * @param addPatientId
	 * @param roomtoAdd
	 * @return list of encounter details
	 */
	JSONObject updateRoomNo(Integer pos, String addPatientId, Short roomtoAdd);
	
	/**
	 * to get RoomStatus
	 * @return list of room status
	 */
	List<PosRooms> getRoomStatus();
	
	/**
	 * to transfer patients 
	 * @param toSwap
	 * @param withroom
	 * @param pos
	 * @return
	 */
	JSONObject updateTransferRooms(Short toswap, Short withroom,Integer pos);
	
	/**
	 * to swap patients between rooms
	 * @param toSwap
	 * @param withroom
	 * @param pos
	 * @return list of encounter details after swapping patients
	 */
	JSONObject updateSwapPatients(Short toswap,Short withroom,Integer pos);
	
	/**
	 * to get ordered information
	 * @param patientId
	 * @return patientId
	 */
	List<OrderedData> getOrdered(List<String> patientIdList);
	
	/**
	 * to get activities information
	 * @param patientId
	 * @param encounterId
	 * @return activities
	 */
	List<ActivitiesData> getActivities(List<String> patientIdList);
	

}