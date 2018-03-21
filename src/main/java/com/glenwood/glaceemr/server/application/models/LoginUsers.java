package com.glenwood.glaceemr.server.application.models;

import java.sql.Date;
import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.glenwood.glaceemr.server.utils.JsonTimestampSerializer;

@Entity
@Table(name = "login_users")
public class LoginUsers {

	@Id
	@Column(name="login_users_id")
	private Integer loginUsersId;
                                                           
	@Column(name="login_users_username")
	private String loginUsersUsername;

	@Column(name="login_users_password")
	private String loginUsersPassword;

	@Column(name="login_users_registered_date")
	private Date loginUsersRegisteredDate;

	@Column(name="login_users_isactive")
	private Boolean loginUsersIsactive;

	@Column(name="login_users_mail_id")
	private String loginUsersMailId;

	@Column(name="login_users_default_practice")
	private Integer loginUsersDefaultPractice;

	@Column(name="login_users_default_doctor")
	private Integer loginUsersDefaultDoctor;

	@Column(name="login_users_default_location")
	private String loginUsersDefaultLocation;

	@Column(name="login_users_default_app_type")
	private Integer loginUsersDefaultAppType;

	@Column(name="login_users_pos_allowed")
	private String loginUsersPosAllowed;

	@Column(name="login_users_default_page")
	private String loginUsersDefaultPage;

	@Column(name="login_users_department")
	private Integer loginUsersDepartment;

	@Column(name="login_users_reportedby")
	private String loginUsersReportedby;

	@Column(name="login_users_alerturl")
	private String loginUsersAlerturl;

	@Column(name="login_users_showreported_to_user")
	private String loginUsersShowreportedToUser;

	@Column(name="login_users_blocked_user")
	private Boolean loginUsersBlockedUser;

	@Column(name="login_users_unknown1")
	private Boolean loginUsersUnknown1;

	@JsonSerialize(using = JsonTimestampSerializer.class)
	@Column(name="login_users_unknown2")
	private Timestamp loginUsersUnknown2;

	@Column(name="login_users_chgpwdnxtlogin")
	private Boolean loginUsersChgpwdnxtlogin;

	@Column(name="login_users_cantchgpwd")
	private Boolean loginUsersCantchgpwd;

	@Column(name="login_users_pwdneverexp")
	private Boolean loginUsersPwdneverexp;

	@Column(name="login_users_application_scope")
	private Integer loginUsersApplicationScope;

	@Column(name="login_users_password_salt")
	private String loginUsersPasswordSalt;

	@Column(name="login_users_authorisation_status")
	private Integer loginUsersAuthorisationStatus;

	@Column(name="login_users_application_scope_info")
	private String loginUsersApplicationScopeInfo;

	@Column(name="login_users_isdoublebook")
	private Boolean loginUsersIsdoublebook;

	public Integer getLoginUsersId() {
		return loginUsersId;
	}

	public void setLoginUsersId(Integer loginUsersId) {
		this.loginUsersId = loginUsersId;
	}

	public String getLoginUsersUsername() {
		return loginUsersUsername;
	}

	public void setLoginUsersUsername(String loginUsersUsername) {
		this.loginUsersUsername = loginUsersUsername;
	}

	public String getLoginUsersPassword() {
		return loginUsersPassword;
	}

	public void setLoginUsersPassword(String loginUsersPassword) {
		this.loginUsersPassword = loginUsersPassword;
	}

	public Date getLoginUsersRegisteredDate() {
		return loginUsersRegisteredDate;
	}

	public void setLoginUsersRegisteredDate(Date loginUsersRegisteredDate) {
		this.loginUsersRegisteredDate = loginUsersRegisteredDate;
	}

	public Boolean getLoginUsersIsactive() {
		return loginUsersIsactive;
	}

	public void setLoginUsersIsactive(Boolean loginUsersIsactive) {
		this.loginUsersIsactive = loginUsersIsactive;
	}

	public String getLoginUsersMailId() {
		return loginUsersMailId;
	}

	public void setLoginUsersMailId(String loginUsersMailId) {
		this.loginUsersMailId = loginUsersMailId;
	}

	public Integer getLoginUsersDefaultPractice() {
		return loginUsersDefaultPractice;
	}

	public void setLoginUsersDefaultPractice(Integer loginUsersDefaultPractice) {
		this.loginUsersDefaultPractice = loginUsersDefaultPractice;
	}

	public Integer getLoginUsersDefaultDoctor() {
		return loginUsersDefaultDoctor;
	}

	public void setLoginUsersDefaultDoctor(Integer loginUsersDefaultDoctor) {
		this.loginUsersDefaultDoctor = loginUsersDefaultDoctor;
	}

	public String getLoginUsersDefaultLocation() {
		return loginUsersDefaultLocation;
	}

	public void setLoginUsersDefaultLocation(String loginUsersDefaultLocation) {
		this.loginUsersDefaultLocation = loginUsersDefaultLocation;
	}

	public Integer getLoginUsersDefaultAppType() {
		return loginUsersDefaultAppType;
	}

	public void setLoginUsersDefaultAppType(Integer loginUsersDefaultAppType) {
		this.loginUsersDefaultAppType = loginUsersDefaultAppType;
	}

	public String getLoginUsersPosAllowed() {
		return loginUsersPosAllowed;
	}

	public void setLoginUsersPosAllowed(String loginUsersPosAllowed) {
		this.loginUsersPosAllowed = loginUsersPosAllowed;
	}

	public String getLoginUsersDefaultPage() {
		return loginUsersDefaultPage;
	}

	public void setLoginUsersDefaultPage(String loginUsersDefaultPage) {
		this.loginUsersDefaultPage = loginUsersDefaultPage;
	}

	public Integer getLoginUsersDepartment() {
		return loginUsersDepartment;
	}

	public void setLoginUsersDepartment(Integer loginUsersDepartment) {
		this.loginUsersDepartment = loginUsersDepartment;
	}

	public String getLoginUsersReportedby() {
		return loginUsersReportedby;
	}

	public void setLoginUsersReportedby(String loginUsersReportedby) {
		this.loginUsersReportedby = loginUsersReportedby;
	}

	public String getLoginUsersAlerturl() {
		return loginUsersAlerturl;
	}

	public void setLoginUsersAlerturl(String loginUsersAlerturl) {
		this.loginUsersAlerturl = loginUsersAlerturl;
	}

	public String getLoginUsersShowreportedToUser() {
		return loginUsersShowreportedToUser;
	}

	public void setLoginUsersShowreportedToUser(String loginUsersShowreportedToUser) {
		this.loginUsersShowreportedToUser = loginUsersShowreportedToUser;
	}

	public Boolean getLoginUsersBlockedUser() {
		return loginUsersBlockedUser;
	}

	public void setLoginUsersBlockedUser(Boolean loginUsersBlockedUser) {
		this.loginUsersBlockedUser = loginUsersBlockedUser;
	}

	public Boolean getLoginUsersUnknown1() {
		return loginUsersUnknown1;
	}

	public void setLoginUsersUnknown1(Boolean loginUsersUnknown1) {
		this.loginUsersUnknown1 = loginUsersUnknown1;
	}

	public Timestamp getLoginUsersUnknown2() {
		return loginUsersUnknown2;
	}

	public void setLoginUsersUnknown2(Timestamp loginUsersUnknown2) {
		this.loginUsersUnknown2 = loginUsersUnknown2;
	}

	public Boolean getLoginUsersChgpwdnxtlogin() {
		return loginUsersChgpwdnxtlogin;
	}

	public void setLoginUsersChgpwdnxtlogin(Boolean loginUsersChgpwdnxtlogin) {
		this.loginUsersChgpwdnxtlogin = loginUsersChgpwdnxtlogin;
	}

	public Boolean getLoginUsersCantchgpwd() {
		return loginUsersCantchgpwd;
	}

	public void setLoginUsersCantchgpwd(Boolean loginUsersCantchgpwd) {
		this.loginUsersCantchgpwd = loginUsersCantchgpwd;
	}

	public Boolean getLoginUsersPwdneverexp() {
		return loginUsersPwdneverexp;
	}

	public void setLoginUsersPwdneverexp(Boolean loginUsersPwdneverexp) {
		this.loginUsersPwdneverexp = loginUsersPwdneverexp;
	}

	public Integer getLoginUsersApplicationScope() {
		return loginUsersApplicationScope;
	}

	public void setLoginUsersApplicationScope(Integer loginUsersApplicationScope) {
		this.loginUsersApplicationScope = loginUsersApplicationScope;
	}

	public String getLoginUsersPasswordSalt() {
		return loginUsersPasswordSalt;
	}

	public void setLoginUsersPasswordSalt(String loginUsersPasswordSalt) {
		this.loginUsersPasswordSalt = loginUsersPasswordSalt;
	}

	public Integer getLoginUsersAuthorisationStatus() {
		return loginUsersAuthorisationStatus;
	}

	public void setLoginUsersAuthorisationStatus(
			Integer loginUsersAuthorisationStatus) {
		this.loginUsersAuthorisationStatus = loginUsersAuthorisationStatus;
	}

	public String getLoginUsersApplicationScopeInfo() {
		return loginUsersApplicationScopeInfo;
	}

	public void setLoginUsersApplicationScopeInfo(
			String loginUsersApplicationScopeInfo) {
		this.loginUsersApplicationScopeInfo = loginUsersApplicationScopeInfo;
	}

	public Boolean getLoginUsersIsdoublebook() {
		return loginUsersIsdoublebook;
	}

	public void setLoginUsersIsdoublebook(Boolean loginUsersIsdoublebook) {
		this.loginUsersIsdoublebook = loginUsersIsdoublebook;
	}
}