package com.glenwood.glaceemr.server.application.authentication.portal;

import com.glenwood.glaceemr.server.application.models.PortalUser;
import com.glenwood.glaceemr.server.application.models.PortalUserAuthority;

public enum PortalUserRole {
	
	USER, ADMIN;
	
	public PortalUserAuthority asAuthorityFor(final PortalUser user) {
	final PortalUserAuthority authority = new PortalUserAuthority();
	authority.setAuthority("ROLE_" + toString());
	authority.setUser(user);
	return authority;
	}
	public static PortalUserRole valueOf(final PortalUserAuthority authority) {
	switch (authority.getAuthority()) {
	case "ROLE_USER":
	return USER;
	case "ROLE_ADMIN":
	return ADMIN;
	}
	throw new IllegalArgumentException("No role defined for authority: " + authority.getAuthority());
	}
	
}
