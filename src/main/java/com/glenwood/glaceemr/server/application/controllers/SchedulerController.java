package com.glenwood.glaceemr.server.application.controllers;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.glenwood.glaceemr.server.application.models.Appointment;
import com.glenwood.glaceemr.server.application.models.SchedulerAppointmentBean;
import com.glenwood.glaceemr.server.application.models.SchedulerResource;
import com.glenwood.glaceemr.server.application.models.SchedulerResourceCategory;
import com.glenwood.glaceemr.server.application.services.audittrail.AuditTrailSaveService;
import com.glenwood.glaceemr.server.application.services.audittrail.AuditTrailEnumConstants.LogActionType;
import com.glenwood.glaceemr.server.application.services.audittrail.AuditTrailEnumConstants.LogModuleType;
import com.glenwood.glaceemr.server.application.services.audittrail.AuditTrailEnumConstants.LogType;
import com.glenwood.glaceemr.server.application.services.audittrail.AuditTrailEnumConstants.LogUserType;
import com.glenwood.glaceemr.server.application.services.audittrail.AuditTrailEnumConstants.Log_Outcome;
import com.glenwood.glaceemr.server.application.services.scheduler.SchedulerService;
import com.glenwood.glaceemr.server.utils.EMRResponseBean;
import com.glenwood.glaceemr.server.utils.SessionMap;
/**
 * 
 * @author Manikandan
 *
 */
@RestController
@Transactional
@RequestMapping(value="/user/Scheduler")
public class SchedulerController {

	@Autowired
	SchedulerService schedulerService;

	@Autowired
	AuditTrailSaveService auditTrailSaveService;

	@Autowired
	SessionMap sessionMap;

	@Autowired
	HttpServletRequest request;

	/**
	 * To get the resources list.
	 * @return
	 */
	@RequestMapping(value="/getresources",method=RequestMethod.GET)
	@ResponseBody
	public EMRResponseBean getResources(){

		List<SchedulerResource> schedulerResources=schedulerService.getResources();
		EMRResponseBean emrResponseBean=new EMRResponseBean();
		emrResponseBean.setData(schedulerResources);
		auditTrailSaveService.LogEvent(LogType.GLACE_LOG, LogModuleType.SCHEDULER, LogActionType.VIEW, -1, Log_Outcome.SUCCESS, "Getting Scheduler resources.", sessionMap.getUserID(), request.getRemoteAddr(), -1, "", LogUserType.USER_LOGIN, "", "");

		return emrResponseBean;
	}

	/**
	 * To get the resources category list.
	 * @return
	 */
	@RequestMapping(value="/getresourcecategory",method=RequestMethod.GET)
	@ResponseBody
	public EMRResponseBean getResourceCategories(){

		List<SchedulerResourceCategory> schedulerResourceCategories=schedulerService.getResourceCategories();
		EMRResponseBean emrResponseBean=new EMRResponseBean();
		emrResponseBean.setData(schedulerResourceCategories);
		auditTrailSaveService.LogEvent(LogType.GLACE_LOG, LogModuleType.SCHEDULER, LogActionType.VIEW, -1, Log_Outcome.SUCCESS, "Getting Scheduler resource categories.", sessionMap.getUserID(), request.getRemoteAddr(), -1, "", LogUserType.USER_LOGIN, "", "");

		return emrResponseBean;
	}

	/**
	 * To get appointment based on date.
	 * @return
	 * @param apptDate
	 * @param resourceId
	 */
	@RequestMapping(value="/getappointments",method=RequestMethod.GET)
	@ResponseBody
	public EMRResponseBean getAppointments(@RequestParam(value="apptdate",required=true,defaultValue="t") String apptDate,
            @RequestParam(value="resourceid",required=true) Integer[] resourceIds){
        SimpleDateFormat sdf=new SimpleDateFormat("MM/dd/yyyy");
        Date date=null;
        try{
            if(!apptDate.equals("-1")){
                date=new Date(apptDate);
                sdf.format(date);
            }
            else{    // If apptDate=-1 ===> Current date
                date=new Date();
                sdf.format(date);
            }
        }
        catch(Exception e){
            System.out.println(" Exception while parsing date :: "+apptDate);
            e.printStackTrace();
        }
        
        List<SchedulerAppointmentBean> schedulerAppointments=schedulerService.getAppointments(date,resourceIds);

        EMRResponseBean emrResponseBean=new EMRResponseBean();
        emrResponseBean.setData(schedulerAppointments);

        auditTrailSaveService.LogEvent(LogType.GLACE_LOG, LogModuleType.SCHEDULER, LogActionType.VIEW, -1, Log_Outcome.SUCCESS, "Getting Scheduler appointments based on resource.", sessionMap.getUserID(), request.getRemoteAddr(), -1, "resourceId="+resourceIds.toString(), LogUserType.USER_LOGIN, "", "");
        return emrResponseBean;
    } 

	/**
	 * To get default resource
	 * @return
	 * @param userId
	 */
	@RequestMapping(value="/getdefaultresource",method=RequestMethod.GET)
	@ResponseBody
	public EMRResponseBean getAppointments(@RequestParam(value="userid",required=true,defaultValue="-1") String userId){

		String resource = schedulerService.getDefaultResource(userId);
		EMRResponseBean emrResponseBean=new EMRResponseBean();
		emrResponseBean.setData(resource);

		auditTrailSaveService.LogEvent(LogType.GLACE_LOG, LogModuleType.SCHEDULER, LogActionType.VIEW, -1, Log_Outcome.SUCCESS, "Getting Scheduler default resource.", sessionMap.getUserID(), request.getRemoteAddr(), -1, "userId="+userId, LogUserType.USER_LOGIN, "", "");

		return emrResponseBean;
	}

	/**
     * To get templates
     * @param userId
     * @param date
     * @return
     */
    @RequestMapping(value="/gettemplates",method=RequestMethod.GET)
    @ResponseBody
    public EMRResponseBean getTemplates(@RequestParam(value="resourceId",required=true) Integer[] userIds, @RequestParam(value="date", required=true) String dateString){

        Date date=null;
        EMRResponseBean emrResponseBean=new EMRResponseBean();
        Map<Integer,Object> resultObject = new HashMap<Integer,Object>();
        try{
            SimpleDateFormat sdf=new SimpleDateFormat("MM/dd/yyyy");
            if(!dateString.equals("-1")){
                date=new Date(dateString);
                sdf.format(date);
            }
            else{    // If dateString=-1 ===> Current date
                date=new Date();
                sdf.format(date);
            }
        }
        catch(Exception e){
            System.out.println(" Exception while parsing date :: "+dateString);
            e.printStackTrace();
        }
        for(Integer userId: userIds){
            Object o = schedulerService.getTemplates(userId, date);
            resultObject.put(userId,o);
        }
        emrResponseBean.setData(resultObject);

        auditTrailSaveService.LogEvent(LogType.GLACE_LOG, LogModuleType.SCHEDULER, LogActionType.VIEW, -1, Log_Outcome.SUCCESS, "Getting templates", sessionMap.getUserID(), request.getRemoteAddr(), -1, "resourceId="+userIds, LogUserType.USER_LOGIN, "", "");

        return emrResponseBean;
    } 	
    
    /**
     * To create appointment
     * Sample body content {"schApptDate":"2015-07-22","schApptStarttime":"2015-07-22 14:00:00","schApptEndtime":"2015-07-22 14:20:00","schApptInterval":10,"schApptPatientId":2272,"schApptPatientname":"Tests","schApptLocation":1,"schApptResource":3,"schApptStatus":6,"schApptType":56,"schApptLastmodifiedTime":"2015-07-22 14:00:00","schApptLastmodifiedUserId":"14","schApptRoomId":"2","schApptStatusgrpId":2}
     * @param appointment
     * @return
     */
    @RequestMapping(value="/createappointment", method=RequestMethod.POST)
    @ResponseBody
    public EMRResponseBean createAppointment(@RequestBody(required=true) Appointment appointment){    

        EMRResponseBean emrResponseBean=new EMRResponseBean();
        Appointment createdAppointment=schedulerService.createAppointment(appointment);
        emrResponseBean.setData(createdAppointment);
    	return emrResponseBean;
    } 	
    
    /**
     * To get locations
     * @return
     */
    @RequestMapping(value="/getlocations",method=RequestMethod.GET)
    @ResponseBody
    public EMRResponseBean getLocation(){
    	
        EMRResponseBean emrResponseBean=new EMRResponseBean();
        List<Object> schResource = schedulerService.getApptBookLocationList();
        emrResponseBean.setData(schResource);
        auditTrailSaveService.LogEvent(LogType.GLACE_LOG, LogModuleType.SCHEDULER, LogActionType.VIEW, -1, Log_Outcome.SUCCESS, "Getting location list", sessionMap.getUserID(), request.getRemoteAddr(), -1, "", LogUserType.USER_LOGIN, "", "");
        return emrResponseBean;
    }
    
    /**
     * To get appointment types
     * @return
     */
    @RequestMapping(value="/getappttypes",method=RequestMethod.GET)
    @ResponseBody
    public EMRResponseBean getApptTypes(){
    	
        EMRResponseBean emrResponseBean=new EMRResponseBean();
        List<Object> apptTypes = schedulerService.getApptTypes();
        emrResponseBean.setData(apptTypes);
        auditTrailSaveService.LogEvent(LogType.GLACE_LOG, LogModuleType.SCHEDULER, LogActionType.VIEW, -1, Log_Outcome.SUCCESS, "Getting location list", sessionMap.getUserID(), request.getRemoteAddr(), -1, "", LogUserType.USER_LOGIN, "", "");
        return emrResponseBean;
    }
}
