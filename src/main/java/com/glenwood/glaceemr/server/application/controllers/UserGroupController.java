package com.glenwood.glaceemr.server.application.controllers;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.glenwood.glaceemr.server.application.models.UserGroup;
import com.glenwood.glaceemr.server.application.services.alertinbox.UserGroupService;
import com.glenwood.glaceemr.server.application.services.audittrail.AuditTrailSaveService;
import com.glenwood.glaceemr.server.application.services.audittrail.AuditTrailEnumConstants.LogActionType;
import com.glenwood.glaceemr.server.application.services.audittrail.AuditTrailEnumConstants.LogModuleType;
import com.glenwood.glaceemr.server.application.services.audittrail.AuditTrailEnumConstants.LogType;
import com.glenwood.glaceemr.server.application.services.audittrail.AuditTrailEnumConstants.LogUserType;
import com.glenwood.glaceemr.server.application.services.audittrail.AuditTrailEnumConstants.Log_Outcome;
import com.glenwood.glaceemr.server.utils.EMRResponseBean;
import com.glenwood.glaceemr.server.utils.SessionMap;

/**
 * Controller for UserGroup module. It has one main entities, <b> UserGroup </b>.
 * @author Jeyanthkumar S
 */
@RestController
@Transactional 
@RequestMapping(value="/user/UserGroup.Action")
public class UserGroupController{

	@Autowired
	UserGroupService userGroupService;
	@Autowired
	AuditTrailSaveService auditTrailSaveService;
	
	@Autowired
	SessionMap sessionMap;
	
	@Autowired
	HttpServletRequest request;

	/**
	 * 
	 * @return list of all user group
	 * Example url: UserGroup.Action/getusergroup
	 */
	
	@RequestMapping(value = "/getusergroup", method = RequestMethod.GET)
	@ResponseBody
	public EMRResponseBean userGroupList()
	{
		List<UserGroup> grpDetails;
		grpDetails=userGroupService.getUserGroup();
		EMRResponseBean emrResponseBean = new EMRResponseBean();
		emrResponseBean.setData(grpDetails);
		
		auditTrailSaveService.LogEvent(LogType.GLACE_LOG, LogModuleType.ALERTS, LogActionType.VIEW, -1, Log_Outcome.SUCCESS, "Getting user group names.", sessionMap.getUserID(), request.getRemoteAddr(), -1, "", LogUserType.USER_LOGIN, "", "");
		return emrResponseBean;
	}


	/**
	 * 
	 * @param groupid :required UserGroup groupId
	 * @return list the group details from UserGroup based upon the Group Id
	 * Example url: UserGroup.Action/getusergroupbyid?groupid=1
	 */
	@RequestMapping(value = "/getusergroupbyid", method = RequestMethod.GET)
	@ResponseBody
	public EMRResponseBean userGroupListById(@RequestParam(value="groupid", required=true) String groupid)
	{
		List<UserGroup> grpDetails;
		grpDetails=userGroupService.getUserGroupByGroupId(Integer.parseInt(groupid));
		EMRResponseBean emrResponseBean = new EMRResponseBean();
		emrResponseBean.setData(grpDetails);
		
		auditTrailSaveService.LogEvent(LogType.GLACE_LOG, LogModuleType.ALERTS, LogActionType.VIEW, -1, Log_Outcome.SUCCESS, "Getting user group names by id.", sessionMap.getUserID(), request.getRemoteAddr(), -1, "groupid="+groupid, LogUserType.USER_LOGIN, "", "");
		return emrResponseBean;
	}


	/**
	 * 
	 * @param groupId :required UserGroup groupId 
	 * @return list the deleted group details from UserGroup based upon the Group Id
	 * Example url: UserGroup.Action/delete?groupid=1
	 */
	@RequestMapping(value = "/delete", method = RequestMethod.GET)
	@ResponseBody
	public EMRResponseBean deleteUserGroupByGroupId(
			 @RequestParam(value="groupid", required=true) String groupId )
	{
		List<UserGroup> grpDetails=userGroupService.deleteUserGroupByGroupId(Integer.parseInt(groupId));
		EMRResponseBean emrResponseBean = new EMRResponseBean();
		emrResponseBean.setData(grpDetails);
		
		auditTrailSaveService.LogEvent(LogType.GLACE_LOG, LogModuleType.ALERTS, LogActionType.VIEW, -1, Log_Outcome.SUCCESS, "Delete user group names.", sessionMap.getUserID(), request.getRemoteAddr(), -1, "groupid="+groupId, LogUserType.USER_LOGIN, "", "");
		return emrResponseBean;
	}


	/**
	 * 
	 * @param groupId :required UserGroup groupId
	 * @param groupName :required UserGroup groupName
	 * @param userId :required UserGroup userId
	 * @param userName :required UserGroup userName
	 * @return list the updated group details from UserGroup
	 * Example url: UserGroup.Action/update?groupid=1&groupname=test doctor&userid=1,2,3&username=doctor~~demodoctor~~testdoctor
	 */
	@RequestMapping(value = "/update", method = RequestMethod.GET)
	@ResponseBody
	public EMRResponseBean updateUserGroupByGroupId(
			@RequestParam(value="groupid", required=true) String groupId,
			 @RequestParam(value="groupname", required=true) String groupName,
			 @RequestParam(value="userid", required=false) String userId,
			 @RequestParam(value="username", required=false) String userName )
			{
		List<Integer> userIdList=new ArrayList<Integer>();
		String[] userIdArray=userId.split(",");

		for (String s : userIdArray) {
			userIdList.add(Integer.parseInt(s));
		}
		List<String> userNameList=new ArrayList<String>();
		String[] userNameArray=userName.split("~~");
		for (String s : userNameArray) {
			userNameList.add(s);
		}
		List<UserGroup> grpDetails=userGroupService.updateUserGroupByGroupId(Integer.parseInt(groupId),groupName,userIdList,userNameList);
		EMRResponseBean emrResponseBean = new EMRResponseBean();
		emrResponseBean.setData(grpDetails);
		
		auditTrailSaveService.LogEvent(LogType.GLACE_LOG, LogModuleType.ALERTS, LogActionType.VIEW, -1, Log_Outcome.SUCCESS, "Update user group names.", sessionMap.getUserID(), request.getRemoteAddr(), -1, "groupid="+groupId+"&userId="+userId, LogUserType.USER_LOGIN, "", "");
		return emrResponseBean;
			}


	/**
	 * 
	 * @param groupName :required UserGroup groupName
	 * @param userId :required UserGroup userId
	 * @param userName :required UserGroup userName
	 * @return list the created group details from UserGroup
	 * Example url: UserGroup.Action/create?groupname=test doctor&userid=1,2,3&username=doctor~~demodoctor~~demodoctor2
	 */
	@RequestMapping(value = "/create", method = RequestMethod.GET)
	@ResponseBody
	public EMRResponseBean insertUserGroup(
			@RequestParam(value="groupname", required=false) String groupName,
			 @RequestParam(value="userid", required=false) String userId,
			 @RequestParam(value="username", required=false) String userName )
			{
		List<Integer> userIdList=new ArrayList<Integer>();
		String[] userIdArray=userId.split(",");

		for (String s : userIdArray) {
			userIdList.add(Integer.parseInt(s));
		}
		List<String> userNameList=new ArrayList<String>();
		String[] userNameArray=userName.split("~~");
		for (String s : userNameArray) {
			userNameList.add(s);
		}
		List<UserGroup> grpDetails=userGroupService.insertUserGroup(groupName,userIdList,userNameList);
		EMRResponseBean emrResponseBean = new EMRResponseBean();
		emrResponseBean.setData(grpDetails);
		
		auditTrailSaveService.LogEvent(LogType.GLACE_LOG, LogModuleType.ALERTS, LogActionType.VIEW, -1, Log_Outcome.SUCCESS, "Create user group names.", sessionMap.getUserID(), request.getRemoteAddr(), -1, "groupName="+groupName+"&userId="+userId+"&userName="+userName, LogUserType.USER_LOGIN, "", "");
		return emrResponseBean;
			}


}
