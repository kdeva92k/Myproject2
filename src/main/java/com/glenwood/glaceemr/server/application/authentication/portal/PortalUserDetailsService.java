package com.glenwood.glaceemr.server.application.authentication.portal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AccountStatusUserDetailsChecker;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.glenwood.glaceemr.server.application.models.PortalUser;
import com.glenwood.glaceemr.server.application.repositories.PortalUserRepository;
import com.glenwood.glaceemr.server.application.services.portalLogin.PortalLoginService;
import com.glenwood.glaceemr.server.utils.SessionMap;

@Service
public class PortalUserDetailsService implements UserDetailsService{


	@Autowired
	PortalUserRepository portalUserRepository;
	
	@Autowired
	PortalLoginService portalLoginService;
	
	@Autowired
	SessionMap sessionMap;
	
	private final AccountStatusUserDetailsChecker detailsChecker = new AccountStatusUserDetailsChecker();
	
	@Override
	public final PortalUser loadUserByUsername(String username) throws UsernameNotFoundException {
	System.out.println("In EMR PortalUserDetailsService::::::::"+username);
	final PortalUser user = portalLoginService.findByUserNameIgnoreCase(username);
	if (user == null) {
	throw new UsernameNotFoundException("User Not Found");
	}
	detailsChecker.check(user);
	sessionMap.setPortalUserID(user.getId());
	if(user.getIsOldUser()!=null)
		sessionMap.setIsOldPortalUser(user.getIsOldUser());
	else
		sessionMap.setIsOldPortalUser(0);
	
	if(user.getIsActive()!=null)
		sessionMap.setIsPortalUserActive(user.getIsActive());
	else
		sessionMap.setIsOldPortalUser(0);
	
	if(user.getPasswordReset()!=null)
		sessionMap.setPortalPassworResetCount(user.getPasswordReset());
	else
		sessionMap.setPortalPassworResetCount(0);
		
	return user;
	}
}
