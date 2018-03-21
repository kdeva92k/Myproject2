package com.glenwood.glaceemr.server.application.authentication.portal;

import java.util.Collection;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;

import com.glenwood.glaceemr.server.application.models.PortalUser;

public class PortalUserAuthentication implements Authentication {
	
	private static final long serialVersionUID = 1L;
	
	private final PortalUser user;
	private boolean authenticated = true;
	
	public PortalUserAuthentication(PortalUser user) {
	this.user = user;
	}
	@Override
	public String getName() {
	return user.getUsername();
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
	public PortalUser getDetails() {
	return user;
	}
	@Override
	public Object getPrincipal() {
	return user.getUsername();
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