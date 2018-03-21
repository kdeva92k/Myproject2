package com.glenwood.glaceemr.server.application.models;

import java.sql.Timestamp;
import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.SequenceGenerator;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.persistence.*;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.glenwood.glaceemr.server.utils.JsonTimestampSerializer;

@Entity
@Table(name = "patient_portal_user")
public class PortalUser implements UserDetails {

	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator="patient_portal_user_patient_portal_user_portal_id_seq")
	@SequenceGenerator(name ="patient_portal_user_patient_portal_user_portal_id_seq", sequenceName="patient_portal_user_patient_portal_user_portal_id_seq", allocationSize=1)
	@Column(name="patient_portal_user_portal_id")
	private Integer patient_portal_user_portal_id;

	
	
	@Column(name="patient_portal_user_patient_id")
	private Long id;

	@Column(name="patient_portal_user_created_on")
	private Date patient_portal_user_created_on;

	@Column(name="patient_portal_user_name")
	private String username;

	@Column(name="patient_portal_user_password_hash")
	private String password;

	@Column(name="patient_portal_user_account_state")
	private Integer isActive;

	@Column(name="patient_portal_user_security_question")
	private String patient_portal_user_security_question;

	@Column(name="patient_portal_user_security_answer")
	private String patient_portal_user_security_answer;

	@Column(name="patient_portal_user_portal_account_verified")
	private Integer isOldUser;

	@Column(name="patient_portal_user_password")
	private String originalPassword;

	@JsonSerialize(using = JsonTimestampSerializer.class)
	@Column(name="patient_portal_user_access_time")
	private Timestamp patient_portal_user_access_time;

	@Column(name="patient_portal_user_wrong_entry_count")
	private Integer wrongEntryCount;

	@JsonSerialize(using = JsonTimestampSerializer.class)
	@Column(name="access_time")
	private Timestamp accessTime;

	@Column(name="security_question1")
	private String securityQuestion1;

	@Column(name="security_question2")
	private String securityQuestion2;

	@Column(name="security_question3")
	private String securityQuestion3;

	@Column(name="security_answer1")
	private String securityAnswer1;

	@Column(name="security_answer2")
	private String securityAnswer2;

	@Column(name="security_answer3")
	private String securityAnswer3;
	
	@Column(name="password_reset")
	private Integer passwordReset;
	
	@Column(name="from_portal")
	private boolean fromPortal;
	
	@Column(name="from_portal_isactive")
	private boolean fromPortalIsactive;
	
	@Column(name="patient_portal_user_token")
	private String patient_portal_userToken;

	public Integer getpatient_portal_user_portal_id() {
		return patient_portal_user_portal_id;
	}

	public void setpatient_portal_user_portal_id(Integer patient_portal_user_portal_id) {
		this.patient_portal_user_portal_id = patient_portal_user_portal_id;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
	
	public Date getpatient_portal_user_created_on() {
		return patient_portal_user_created_on;
	}

	public void setpatient_portal_user_created_on(Date patient_portal_user_created_on) {
		this.patient_portal_user_created_on = patient_portal_user_created_on;
	}
	@JsonIgnore
	public String getUsername() {
		return username;
	}
	@JsonProperty
	public void setUsername(String username) {
		this.username = username;
	}
	@JsonIgnore
	public String getPassword() {
		return password;
	}
	@JsonProperty
	public void setPassword(String password) {
		this.password = password;
	}

	public String getOriginalPassword() {
		return originalPassword;
	}

	public void setOriginalPassword(String originalPassword) {
		this.originalPassword = originalPassword;
	}

	public Integer getIsActive() {
		return isActive;
	}

	public void setIsActive(Integer isActive) {
		this.isActive = isActive;
	}
	
	public String getpatient_portal_user_security_question() {
		return patient_portal_user_security_question;
	}

	public void setpatient_portal_user_security_question(String patient_portal_user_security_question) {
		this.patient_portal_user_security_question = patient_portal_user_security_question;
	}

	public String getpatient_portal_user_security_answer() {
		return patient_portal_user_security_answer;
	}

	public void setpatient_portal_user_security_answer(String patient_portal_user_security_answer) {
		this.patient_portal_user_security_answer = patient_portal_user_security_answer;
	}

	public Integer getIsOldUser() {
		return isOldUser;
	}

	public void setIsOldUser(Integer isOldUser) {
		this.isOldUser = isOldUser;
	}

	public Timestamp getpatient_portal_user_access_time() {
		return patient_portal_user_access_time;
	}

	public void setpatient_portal_user_access_time(Timestamp patient_portal_user_access_time) {
		this.patient_portal_user_access_time = patient_portal_user_access_time;
	}

	public Integer getWrongEntryCount() {
		return wrongEntryCount;
	}

	public void setWrongEntryCount(Integer wrongEntryCount) {
		this.wrongEntryCount = wrongEntryCount;
	}

	public Timestamp getAccessTime() {
		return accessTime;
	}

	public void setAccessTime(Timestamp accessTime) {
		this.accessTime = accessTime;
	}

	public String getSecurityQuestion1() {
		return securityQuestion1;
	}

	public void setSecurityQuestion1(String securityQuestion1) {
		this.securityQuestion1 = securityQuestion1;
	}

	public String getSecurityQuestion2() {
		return securityQuestion2;
	}

	public void setSecurityQuestion2(String securityQuestion2) {
		this.securityQuestion2 = securityQuestion2;
	}

	public String getSecurityQuestion3() {
		return securityQuestion3;
	}

	public void setSecurityQuestion3(String securityQuestion3) {
		this.securityQuestion3 = securityQuestion3;
	}

	public String getSecurityAnswer1() {
		return securityAnswer1;
	}

	public void setSecurityAnswer1(String securityAnswer1) {
		this.securityAnswer1 = securityAnswer1;
	}

	public String getSecurityAnswer2() {
		return securityAnswer2;
	}

	public void setSecurityAnswer2(String securityAnswer2) {
		this.securityAnswer2 = securityAnswer2;
	}

	public String getSecurityAnswer3() {
		return securityAnswer3;
	}

	public void setSecurityAnswer3(String securityAnswer3) {
		this.securityAnswer3 = securityAnswer3;
	}
	
	public Integer getPasswordReset() {
		return passwordReset;
	}

	public void setPasswordReset(Integer passwordReset) {
		this.passwordReset = passwordReset;
	}

	public boolean isFromPortal() {
		return fromPortal;
	}

	public void setFromPortal(boolean fromPortal) {
		this.fromPortal = fromPortal;
	}

	public boolean isFromPortalIsactive() {
		return fromPortalIsactive;
	}

	public void setFromPortalIsactive(boolean fromPortalIsactive) {
		this.fromPortalIsactive = fromPortalIsactive;
	}

	public String getpatient_portal_userToken() {
		return patient_portal_userToken;
	}

	public void setpatient_portal_userToken(String patient_portal_userToken) {
		this.patient_portal_userToken = patient_portal_userToken;
	}

	public PortalUser()
	{

	}

	public PortalUser(String username) {
		this.username = username;
	}
	public PortalUser(String username,Date expires,boolean accountEnabled,boolean accountExpired,
			boolean credentialsExpired,boolean accountLocked) {
		this.username = username;
		this.expires = expires.getTime();
		this.accountEnabled=accountEnabled;
		this.accountExpired=accountExpired;
		this.credentialsExpired=credentialsExpired;
		this.accountLocked=accountLocked;
	}
	
	@Transient
	private long expires;
	
	@Transient
	private boolean accountExpired=false;
	
	@Transient
	private boolean accountLocked=false;
	
	@Transient
	private boolean credentialsExpired=false;
	
	@Transient
	private boolean accountEnabled=false;
	
	@Transient
	private String newPassword;	
	
	@JsonIgnore
	public String getNewPassword() {
		return newPassword;
	}
	@JsonProperty
	public void setNewPassword(String newPassword) {
		this.newPassword = newPassword;
	}
	
	
	@Override
	@JsonIgnore
	public boolean isAccountNonExpired() {
		return !accountExpired;
	}
	@Override
	@JsonIgnore
	public boolean isAccountNonLocked() {
		return !accountLocked;
	}
	@Override
	@JsonIgnore
	public boolean isCredentialsNonExpired() {
		return !credentialsExpired;
	}
	@Override
	@JsonIgnore
	public boolean isEnabled() {
		return !accountEnabled;
	}
	
	
	public long getExpires() {
		return expires;
	}
	public void setExpires(long expires) {
		this.expires = expires;
	}
	@Override
	public String toString() {
		return getClass().getSimpleName() + ": " + getUsername();
	}
	
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {

		return getAuthorities('A');
		
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