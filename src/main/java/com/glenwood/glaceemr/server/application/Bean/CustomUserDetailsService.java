package com.glenwood.glaceemr.server.application.Bean;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.glenwood.glaceemr.server.application.models.Users;
import com.glenwood.glaceemr.server.application.repositories.UsersRepository;
import com.glenwood.glaceemr.server.application.services.users.UsersService;
import com.glenwood.glaceemr.server.filters.DataBaseAccessFilter;

@Service
public class CustomUserDetailsService implements UserDetailsService{


	protected final Logger logger = LoggerFactory.getLogger(CustomUserDetailsService.class);
	@Autowired
	UsersRepository usersRepository;
	
	@Autowired
	UsersService usersService;
	
	public static String password="";
	@Override
	public UserDetails loadUserByUsername(String userName)
			throws UsernameNotFoundException {
//		System.out.println("\n>>>>>>>database retrived password>>>>"+DataBaseAccessFilter.password+"\n");
		System.out.println("\n\nbefore query>>>\n"+DataBaseAccessFilter.password);
		String testpassword=DataBaseAccessFilter.password;
		Users user =usersService.getUserObject(userName,testpassword);
		
		boolean enabled = true;
		boolean accountNonExpired = true;
		boolean credentialsNonExpired = true;
		boolean accountNonLocked = true;
		System.out.println(">>>>>>>>>>after login query");
		User loggedInUser = new User(user.getUserName(), user.getPassword(),
				enabled, accountNonExpired,
				credentialsNonExpired, accountNonLocked, getAuthorities('A'));
		
		logger.debug("here in custom details service ", loggedInUser);
		return loggedInUser;
	}

	
	
	/**
	 * Retrieves a collection of {@link GrantedAuthority} based on a numerical role
	 * @param role the numerical role
	 * @return a collection of {@link GrantedAuthority
	 */
	public Collection<? extends GrantedAuthority> getAuthorities(Character role) {
		List<GrantedAuthority> authList = getGrantedAuthorities(getRoles(role));
		return authList;
	}
	
	/**
	 * Converts a numerical role to an equivalent list of roles
	 * @param role the numerical role
	 * @return list of roles as as a list of {@link String}
	 */
	public List<String> getRoles(Character role) {
		List<String> roles = new ArrayList<String>();
		
		if (role == 'A') {
			roles.add("GLACE_ADMIN");

			
		} else if (role == 'G') {
			roles.add("GLACE_USER");
		}
		
		return roles;
	}
	
	/**
	 * Wraps {@link String} roles to {@link SimpleGrantedAuthority} objects
	 * @param roles {@link String} of roles
	 * @return list of granted authorities
	 */
	public static List<GrantedAuthority> getGrantedAuthorities(List<String> roles) {
		List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
		for (String role : roles) {
			authorities.add(new SimpleGrantedAuthority(role));
		}
		return authorities;
	}
}
