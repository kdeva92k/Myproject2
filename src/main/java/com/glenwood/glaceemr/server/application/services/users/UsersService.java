package com.glenwood.glaceemr.server.application.services.users;

import com.glenwood.glaceemr.server.application.models.Users;


public interface UsersService {
	
	public int findByUserName(String searchValue);
	
	 public Users getUserDetails(String username) ;
	 
	 public Users getUserObject(String userName,String password);
	
}
