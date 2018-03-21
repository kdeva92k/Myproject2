package com.glenwood.glaceemr.server.application.services.users;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.glenwood.glaceemr.server.application.models.Users;
import com.glenwood.glaceemr.server.application.repositories.UsersRepository;




@Service
public class UsersServiceImpl implements UsersService {

	
	@Resource
	private UsersRepository usersRepository;
	

	@SuppressWarnings("unused")
	@Override
	public int findByUserName(String searchValue) {
		int id =0;
		Users user =usersRepository.findByUserNameIgnoreCase(searchValue);
		System.out.println("findby user name->"+user.toString());
		if (user == null)
			{
			return id;
			}
		else
		{
			id = user.getUserId();
		}
		
		return id;
	}

	
	  @Override
	   public Users getUserDetails(String username) {
	       Users user = usersRepository.findByUserNameIgnoreCase(username);
	       return user;
	   }
	  
		public Users getUserObject(String userName,String password)
		{
			Users user=usersRepository.findByUserNameIgnoreCase(userName);
			return user;
		}
}
