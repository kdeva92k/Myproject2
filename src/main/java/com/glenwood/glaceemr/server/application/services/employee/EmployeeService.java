package com.glenwood.glaceemr.server.application.services.employee;

import java.util.List;

import com.glenwood.glaceemr.server.application.models.EmployeeProfile;

public interface EmployeeService {

	/**
	 * @param groupid 
	 * @param sort Example: asc
	 * @return  list of employee details based on group id and sorting type
	 */
	List<EmployeeProfile> getEmployeeDetails(String groupId, String sort);
}
