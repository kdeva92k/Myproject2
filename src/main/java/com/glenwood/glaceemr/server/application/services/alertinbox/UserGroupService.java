package com.glenwood.glaceemr.server.application.services.alertinbox;

import java.util.List;

import com.glenwood.glaceemr.server.application.models.UserGroup;

/**
 * Service Interface for AlertUserGroup
 * @author Jeyanthkumar S
 *
 */
public interface UserGroupService {
	
	/**
	 * @return list of all user group
	 */
	List<UserGroup> getUserGroup();
	
	/**
	 * @param groupId
	 * @return list of user group by group id
	 */
	List<UserGroup> getUserGroupByGroupId(Integer groupId);
	
	/**
	 * @param groupId
	 * @return list of user group deleted by group id
	 */
	List<UserGroup> deleteUserGroupByGroupId(Integer groupId);
	
	/**
	 * @param groupId
	 * @param groupName
	 * @param userId
	 * @param username
	 * @return list of user group updated by group id
	 */
	List<UserGroup> updateUserGroupByGroupId(Integer groupId,String groupName,List<Integer> userId,List<String> username);
	
	/**
	 * @param groupName
	 * @param userId
	 * @param username
	 * @return list of user group inserted in user group table
	 */
	List<UserGroup> insertUserGroup(String groupName,List<Integer> userId,List<String> username);
}
