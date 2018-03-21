package com.glenwood.glaceemr.server.application.models;

import java.util.Collection;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import org.springframework.security.core.GrantedAuthority;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;

@Entity
@Table(name = "login_users")
@JsonSerialize(include= JsonSerialize.Inclusion.NON_NULL)
public class Users {
	
	@Id
	 @Column(name="login_users_id")
	private Integer userId;

    
    @Column(name="login_users_username")
    private String userName;
    
    @Column(name="login_users_password")
    private String password;

	@Column(name="login_users_username",insertable=false, updatable=false)
    private String txtUsername;
    
    
	@Column(name="login_users_isactive")
	private boolean isActive;


	public Integer getUserId() {
		return userId;
	}


	public void setUserId(Integer userId) {
		this.userId = userId;
	}


	public String getUserName() {
		return userName;
	}


	public void setUserName(String userName) {
		this.userName = userName;
	}


	public String getPassword() {
		return password;
	}


	public void setPassword(String password) {
		this.password = password;
	}


	public String getTxtUsername() {
		return txtUsername;
	}


	public void setTxtUsername(String txtUsername) {
		this.txtUsername = txtUsername;
	}


	public boolean isActive() {
		return isActive;
	}


	public void setActive(boolean isActive) {
		this.isActive = isActive;
	}


	@Override
	public String toString() {
		return "Users [userId=" + userId + ", userName=" + userName
				+ ", password=" + password + ", txtUsername=" + txtUsername
				+ ", isActive=" + isActive + "]";
	}


	public Collection<? extends GrantedAuthority> getAuthorities() {
		// TODO Auto-generated method stub
		return null;
	}
 
	

    
}
