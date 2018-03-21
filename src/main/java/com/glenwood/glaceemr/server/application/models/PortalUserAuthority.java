package com.glenwood.glaceemr.server.application.models;


import javax.persistence.*;
import javax.validation.constraints.NotNull;
import org.springframework.security.core.GrantedAuthority;
import com.fasterxml.jackson.annotation.JsonIgnore;
@Entity
@IdClass(PortalUserAuthority.class)
public class PortalUserAuthority implements GrantedAuthority {

	private static final long serialVersionUID = 1L;

	@NotNull
	@ManyToOne(fetch = FetchType.LAZY)
	@JsonIgnore
	@Id
	private PortalUser user;
	@NotNull
	@Id
	private String authority;
	public PortalUser getUser() {
		return user;
	}
	public void setUser(PortalUser user) {
		this.user = user;
	}
	@Override
	public String getAuthority() {
		return authority;
	}
	public void setAuthority(String authority) {
		this.authority = authority;
	}
	@Override
	public boolean equals(Object obj) {
		if (!(obj instanceof PortalUserAuthority))
			return false;
		PortalUserAuthority ua = (PortalUserAuthority) obj;
		return ua.getAuthority() == this.getAuthority() || ua.getAuthority().equals(this.getAuthority());
	}
	@Override
	public int hashCode() {
		return getAuthority() == null ? 0 : getAuthority().hashCode();
	}
	@Override
	public String toString() {
		return getClass().getSimpleName() + ": " + getAuthority();
	}
}
