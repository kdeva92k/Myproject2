package com.glenwood.glaceemr.server.application.services.alertinbox;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.glenwood.glaceemr.server.application.models.UserGroup;
import com.glenwood.glaceemr.server.application.repositories.UserGroupRepository;
import com.glenwood.glaceemr.server.application.specifications.UserGroupSpecification;

/**
 * Service Implementation file for AlertUserGroup
 * @author Jeyanthkumar S
 *
 */
@Service
@Transactional
public class UserGroupServiceImpl implements UserGroupService{

	@Autowired
	UserGroupRepository alertUserGroupRepository;

	@Autowired
	EntityManagerFactory emf ;

	@PersistenceContext
	EntityManager em;


	/**
	 * @return list of all user group
	 */
	@Override
	public List<UserGroup> getUserGroup() {
		List<UserGroup> listOfUserGroups = null;
		listOfUserGroups=alertUserGroupRepository.findAll();
		return listOfUserGroups;
	}
	
	
	/**
	 * @param groupId
	 * @return list of user group by group id
	 */
	@Override
	public List<UserGroup> getUserGroupByGroupId(Integer groupId) {
		List<UserGroup> listOfUserGroups = null;
		listOfUserGroups=alertUserGroupRepository.findAll(UserGroupSpecification.groupDetailsByGroupId(groupId));
		return listOfUserGroups;
	}


	/**
	 * @param groupId
	 * @return list of user group deleted by group id
	 */
	@Override
	public List<UserGroup> deleteUserGroupByGroupId(Integer groupId) {
		List<UserGroup> listOfUserGroups = null;
		listOfUserGroups=alertUserGroupRepository.findAll(UserGroupSpecification.groupDetailsByGroupId(groupId));
		alertUserGroupRepository.deleteInBatch(alertUserGroupRepository.findAll(UserGroupSpecification.groupDetailsByGroupId(groupId)));
		return listOfUserGroups;
	}


	/**
	 * @param groupId
	 * @param groupName
	 * @param userId
	 * @param userName
	 * @return list of user group updated by group id
	 */
	@Override
	public List<UserGroup> updateUserGroupByGroupId(Integer groupId,String groupName,List<Integer> userId,List<String> userName) {
		List<UserGroup> listOfUserGroups = null;
		alertUserGroupRepository.deleteInBatch(alertUserGroupRepository.findAll(UserGroupSpecification.groupDetailsByGroupId(groupId)));
		for(int i=0;i<userId.size();i++)
		{
			UserGroup aug=new UserGroup();
			aug.setGroupid(groupId);
			aug.setGroupname(groupName);
			aug.setUserid(userId.get(i));
			aug.setUsername(userName.get(i));
			alertUserGroupRepository.save(aug);
		}
		listOfUserGroups=alertUserGroupRepository.findAll(UserGroupSpecification.groupDetailsByGroupId(groupId));
		return listOfUserGroups;
	}


	/**
	 * @param groupName
	 * @param userId
	 * @param userName
	 * @return list of user group inserted in user group table
	 */
	@Override
	public List<UserGroup> insertUserGroup(String groupName,List<Integer> userId,List<String> userName) {
		List<UserGroup> listOfUserGroups = null;
		int groupId;
		List<UserGroup> id=alertUserGroupRepository.findAll(new PageRequest(0, 1,Direction.DESC,"groupId")).getContent();
		if(id.size()==1)
		{
			groupId=id.get(0).getGroupid()+1;
		}
		else
		{
			groupId=10000;
		}
		for(int i=0;i<userId.size();i++)
		{
			UserGroup aug=new UserGroup();
			aug.setGroupid(groupId);
			aug.setGroupname(groupName);
			aug.setUserid(userId.get(i));
			aug.setUsername(userName.get(i));
			alertUserGroupRepository.save(aug);
		}
		listOfUserGroups=alertUserGroupRepository.findAll(UserGroupSpecification.groupDetailsByGroupId(groupId));
		return listOfUserGroups;
	}
	
	
}
