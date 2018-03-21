package com.glenwood.glaceemr.server.application.controllers;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.glenwood.glaceemr.server.application.models.EmployeeProfile;
import com.glenwood.glaceemr.server.application.models.PosTable;
import com.glenwood.glaceemr.server.application.models.Room;
import com.glenwood.glaceemr.server.application.services.audittrail.AuditTrailEnumConstants.LogActionType;
import com.glenwood.glaceemr.server.application.services.audittrail.AuditTrailEnumConstants.LogModuleType;
import com.glenwood.glaceemr.server.application.services.audittrail.AuditTrailEnumConstants.LogType;
import com.glenwood.glaceemr.server.application.services.audittrail.AuditTrailEnumConstants.LogUserType;
import com.glenwood.glaceemr.server.application.services.audittrail.AuditTrailEnumConstants.Log_Outcome;
import com.glenwood.glaceemr.server.application.services.audittrail.AuditTrailSaveService;
import com.glenwood.glaceemr.server.application.services.roomStatus.ActivitiesData;
import com.glenwood.glaceemr.server.application.services.roomStatus.OrderedData;
import com.glenwood.glaceemr.server.application.services.roomStatus.PosRooms;
import com.glenwood.glaceemr.server.application.services.roomStatus.RoomInfoBean;
import com.glenwood.glaceemr.server.application.services.roomStatus.RoomStatusService;
import com.glenwood.glaceemr.server.utils.EMRResponseBean;

@RestController
@RequestMapping(value="/user/RoomStatus")
public class RoomStatusController {
	
	@Autowired
	RoomStatusService roomStatusService;
	
	@Autowired
	AuditTrailSaveService auditTrailSaveService;
	
	@Autowired
	HttpServletRequest request;
	
	/**
     * to get locations
     * @return locations
     * @throws Exception
     */
	@RequestMapping(value="/getLocations",method=RequestMethod.GET)
	@ResponseBody 
	public EMRResponseBean getLocations(@RequestParam(value="pos",required=false)Integer pos) throws Exception{
		List<PosTable> loc = roomStatusService.getPos(pos);
		EMRResponseBean locations = new EMRResponseBean();
		locations.setData(loc);
		auditTrailSaveService.LogEvent(LogType.GLACE_LOG, LogModuleType.ROOMSTATUS, LogActionType.VIEW,-1, Log_Outcome.SUCCESS,"success in getting locations list",-1,request.getRemoteAddr(), -1,"", LogUserType.USER_LOGIN,"","");
		return locations;
	}
	
	/**
	 * to get all the information about the room
	 * @param patientId
	 * @param encounterId
	 * @return responseBean
	 * @throws Exception
	 */
	@RequestMapping(value="/getRoomInformation",method=RequestMethod.GET)
	@ResponseBody 
	public EMRResponseBean getPosDetails(@RequestParam(value="pos",required=false)Integer pos) throws Exception{
	    List<OrderedData> ordered=null;
		List<ActivitiesData> activities=null; 
		List<PosTable> locations = roomStatusService.getPos(pos);
		List<EmployeeProfile> providers = roomStatusService.getproviders();
		List<PosRooms> roomStatus = roomStatusService.getRoomStatus();
		List<String> patientIdList=new ArrayList<String>();
		for(int i=0;i<roomStatus.size();i++){
				patientIdList.add(roomStatus.get(i).getPatId().toString());
				activities = roomStatusService.getActivities(patientIdList);
				ordered = roomStatusService.getOrdered(patientIdList);
			}
		List<Room> roomDetail = roomStatusService.getRoomName();
		RoomInfoBean roomInfo = new RoomInfoBean(locations,providers,roomStatus,ordered,roomDetail,activities);
		EMRResponseBean roomInformation = new EMRResponseBean();
		roomInformation.setData(roomInfo);
		auditTrailSaveService.LogEvent(LogType.GLACE_LOG, LogModuleType.ROOMSTATUS, LogActionType.VIEW,-1, Log_Outcome.SUCCESS,"success in getting room information",-1,request.getRemoteAddr(),-1,"", LogUserType.USER_LOGIN,"","");
		return roomInformation;
	}
	
	/**
	 * to get list of todays patient data
	 * @param pos
	 * @return patientsData
	 * @throws Exception
	 */
	@RequestMapping(value="/getTodaysPatientsdata",method=RequestMethod.GET)
	@ResponseBody 
	public EMRResponseBean getTodaysPatientsData(@RequestParam(value="pos",required=false)Integer pos) throws Exception{
		List<PosRooms> patientsData = roomStatusService.getTodaysPatientsData(pos);
		EMRResponseBean patientData = new EMRResponseBean();
		patientData.setData(patientsData);
		auditTrailSaveService.LogEvent(LogType.GLACE_LOG,LogModuleType.ROOMSTATUS, LogActionType.VIEW,-1, Log_Outcome.SUCCESS,"Success in getting list of todays patients data based on pos",-1,request.getRemoteAddr(),-1,pos.toString(), LogUserType.USER_LOGIN,"","");
		return patientData;
	}
	
	/**
	 * to update room numbers
	 * @param pos
	 * @param addPatientId
	 * @param roomtoAdd
	 * @return roomNos
	 * @throws Exception
	 */
	@RequestMapping(value="/updateRoomNo",method=RequestMethod.GET)
	@ResponseBody 
	public EMRResponseBean updateRoomNo(@RequestParam(value="pos",required=false)Integer pos,@RequestParam(value="addPatientId",required=false) String addPatientId,@RequestParam(value="roomtoAdd",required=false)Short roomtoAdd) throws Exception{
		JSONObject encounterList = roomStatusService.updateRoomNo(pos,addPatientId,roomtoAdd);
		EMRResponseBean roomNos = new EMRResponseBean();
		roomNos.setData(encounterList.toString());
		auditTrailSaveService.LogEvent(LogType.GLACE_LOG, LogModuleType.ROOMSTATUS, LogActionType.VIEW,-1, Log_Outcome.SUCCESS,"success in updating the room numbers",-1,request.getRemoteAddr(),-1,"", LogUserType.USER_LOGIN,"","");
		return roomNos;
    }
	
	/**
	 * to transfer patients
	 * @param oldRoom
	 * @param newRoom
	 * @param pos
	 * @return List<Encounter>
	 * @throws Exception
	 */
	@RequestMapping(value="/transferPatients",method=RequestMethod.GET)
	@ResponseBody 
	public EMRResponseBean updateTransferRooms(@RequestParam(value="toswap",required=false)Short toswap,@RequestParam(value="withroom",required=false)Short withroom,@RequestParam(value="pos",required=false)Integer pos) throws Exception{
		JSONObject transferPat=roomStatusService.updateTransferRooms(toswap,withroom,pos);
		EMRResponseBean transferPatient = new EMRResponseBean();
		transferPatient.setData(transferPat.toString());
		auditTrailSaveService.LogEvent(LogType.GLACE_LOG, LogModuleType.ROOMSTATUS, LogActionType.UPDATE,-1, Log_Outcome.SUCCESS,"successfully transferred the patients", -1,request.getRemoteAddr(), -1, "toswap="+toswap+"|withroom="+withroom+"|pos="+pos, LogUserType.USER_LOGIN,"","");
		return transferPatient;
    }
	
	/**
	 * to swap patients within rooms
	 * @param oldRoom
	 * @param newRoom
	 * @param pos
	 * @throws Exception
	 */
	@RequestMapping(value="/swapPatients",method=RequestMethod.GET)
	@ResponseBody 
	public EMRResponseBean updateSwappatients(@RequestParam(value="toswap",required=false)Short toswap,@RequestParam(value="withroom",required=false)Short withroom,@RequestParam(value="pos",required=false)Integer pos) throws Exception{
		JSONObject swapPatients = roomStatusService.updateSwapPatients(toswap,withroom,pos);
		EMRResponseBean swapPatient = new EMRResponseBean();
		swapPatient.setData(swapPatients.toString());
		auditTrailSaveService.LogEvent(LogType.GLACE_LOG, LogModuleType.ROOMSTATUS, LogActionType.UPDATE,-1, Log_Outcome.SUCCESS,"successfully swapped the patients within rooms",-1, request.getRemoteAddr(),-1,"withroom"+withroom+"|pos"+pos+"|toswap"+toswap, LogUserType.USER_LOGIN,"","");
		return swapPatient;
    }
              
 }