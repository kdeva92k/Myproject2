package com.glenwood.glaceemr.server.application.services.employee;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.glenwood.glaceemr.server.application.models.EmployeeProfile;
import com.glenwood.glaceemr.server.application.models.UserGroup;
import com.glenwood.glaceemr.server.application.repositories.EmployeeProfileRepository;
import com.glenwood.glaceemr.server.application.repositories.UserGroupRepository;
import com.glenwood.glaceemr.server.application.specifications.EmployeeSpecification;

@Service
@Transactional
public class EmployeeServiceImpl implements EmployeeService {

	@Autowired
	EntityManagerFactory emf ;

	@PersistenceContext
	EntityManager em;

	@Autowired
	EmployeeProfileRepository empProfileRepository;
	
	@Autowired
	UserGroupRepository userGroupRepository;
	
	
	/**
	 * @param groupId 
	 * @param sort Example: asc
	 * @return  list of employee details based on groupid and sorting type
	 */
	@Override
	public List<EmployeeProfile> getEmployeeDetails(String groupId, String sort) {
		List<EmployeeProfile> employeeList=new ArrayList<EmployeeProfile>();
		List<EmployeeProfile> employees=null;
		
		List<Integer> groupidList=new ArrayList<Integer>();
		
		List<UserGroup> userGroup=userGroupRepository.findAll();
		for (int i = 0; i < userGroup.size(); i++) {
			if(!groupidList.contains(userGroup.get(i).getGroupid())){
				
			groupidList.add(userGroup.get(i).getGroupid());
				
			EmployeeProfile emp = new EmployeeProfile();
			emp.setEmpProfileFullname(userGroup.get(i).getGroupname());
			emp.setEmpProfileEmpid(userGroup.get(i).getGroupid());
			emp.setEmpProfileGroupid(-100);
			
			employeeList.add(emp);
			}
		}
		if(groupId.equalsIgnoreCase("all")){
			employees=empProfileRepository.findAll(EmployeeSpecification.getUsersList(sort));
		}
		else{
			employees=empProfileRepository.findAll(EmployeeSpecification.getUsersList(groupId,sort));
		}
		
		for (int i = 0; i < employees.size(); i++) {
			employeeList.add(employees.get(i));
		}
		
		
		return employeeList;
	}
}