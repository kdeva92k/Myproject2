package com.glenwood.glaceemr.server.application.Bean;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.GrantedAuthorityImpl;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Component;

import com.glenwood.glaceemr.server.application.models.Users;
import com.glenwood.glaceemr.server.application.repositories.UsersRepository;
import com.glenwood.glaceemr.server.application.services.users.UsersService;
@Component
public class CustomAuthenticationProvider implements AuthenticationProvider {

	@Autowired
	UsersRepository usersRepository;
	
	@Autowired
	UsersService usersService;

private UserDetailsService userDetailsService; //what am i supposed to do with this?

@Override
public Authentication authenticate(Authentication authentication) throws AuthenticationException {
	
	UsernamePasswordAuthenticationToken auth = (UsernamePasswordAuthenticationToken) authentication;
    String username = String.valueOf(auth.getPrincipal());
    String password = String.valueOf(auth.getCredentials());

//    System.out.println(">>>username"+username);
//    System.out.println(">>>password"+password);
  
    GrantedAuthority[] authorities = new GrantedAuthorityImpl[1];
    authorities[0] = new GrantedAuthorityImpl("ROLE_USER");
   
	Users user =usersService.getUserObject(username,password);
	
	if (user == null) {
        throw new BadCredentialsException("Username not found.");
    }

    if (!password.equals(user.getPassword())) {
        throw new BadCredentialsException("Wrong password.");
    }

    UserAuthentication customAuthentication = new UserAuthentication(user);



return new UsernamePasswordAuthenticationToken(user,password,user.getAuthorities()); 

}

@Override
public boolean supports(Class aClass) {
    return true;  //To indicate that this authenticationprovider can handle the auth request. since there's currently only one way of logging in, always return true
}

public UserDetailsService getUserDetailsService() {
    return userDetailsService;
}

public void setUserDetailsService(UserDetailsService userDetailsService) {
    this.userDetailsService = userDetailsService;
}
}