package com.glenwood.glaceemr.server.application.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import com.glenwood.glaceemr.server.application.models.Users;


public interface UsersRepository extends JpaRepository<Users, Integer> {
	
	
	Users findByUserNameIgnoreCase(String searchValue);
	
	
	Users findByTxtUsername(String username);
}
