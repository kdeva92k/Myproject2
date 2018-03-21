package com.glenwood.glaceemr.server.application.Bean;

import java.util.Collection;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;

import com.glenwood.glaceemr.server.application.models.Users;

public class UserAuthentication implements Authentication {

	private final Users user;
	private boolean authenticated = true;

	public UserAuthentication(Users user) {
		this.user = user;
	}

	@Override
	public String getName() {
		return user.getUserName();
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return user.getAuthorities();
	}

	@Override
	public Object getCredentials() {
		return user.getPassword();
	}

	@Override
	public Users getDetails() {
		return user;
	}

	@Override
	public Object getPrincipal() {
		return user.getUserName();
	}

	@Override
	public boolean isAuthenticated() {
		return authenticated;
	}

	@Override
	public void setAuthenticated(boolean authenticated) {
		this.authenticated = authenticated;
	}
}