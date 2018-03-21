/**
 * 
 */
package com.glenwood.glaceemr.server.utils;

import java.io.Serializable;

import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Component;

@Component
@Scope(value = "session", proxyMode = ScopedProxyMode.INTERFACES)
public class SessionMap implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	int userID;
	String sessionId;
	String dbName;
	String userName;
	Long portalUserID;
	String portalUser;
	String portalSessionID;
	String portalDBName;
	String practiceName;
	String practiceAccountId;
	String practiceSharedFolderPath;
	String practiceAddress;
	String practiceDoctorMessage;
	String practiceCity;
	String practiceWebAddress;
	String practiceFax;
	String practiceState;
	String practiceZipcode;
	String practiceEmail;
	String practicePhone;
	String portalLoginUrl;
	String portalLoginContext;
	String glaceApacheUrl;
	String glaceTomcatUrl;
	String glaceSpringContext;
	String glaceTomcatContext;
	Integer isPortalUserActive;
	Integer isOldPortalUser;
	Integer portalPassworResetCount;
	

	public int getUserID() {
		return userID;
	}

	public void setUserID(int userID) {
		this.userID = userID;
	}

	public String getDbName() {
		return dbName;
	}

	public void setDbName(String dbName) {
		this.dbName = dbName;
	}

	public String getSessionId() {
		return sessionId;
	}

	public void setSessionId(String sessionId) {
		this.sessionId = sessionId;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public Long getPortalUserID() {
		return portalUserID;
	}

	public void setPortalUserID(Long portalUserID) {
		this.portalUserID = portalUserID;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public String getPortalUser() {
		return portalUser;
	}

	public void setPortalUser(String portalUser) {
		this.portalUser = portalUser;
	}

	public String getPortalSessionID() {
		return portalSessionID;
	}

	public void setPortalSessionID(String portalSessionID) {
		this.portalSessionID = portalSessionID;
	}

	public String getPortalDBName() {
		return portalDBName;
	}

	public void setPortalDBName(String portalDBName) {
		this.portalDBName = portalDBName;
	}

	public String getPracticeName() {
		return practiceName;
	}

	public void setPracticeName(String practiceName) {
		this.practiceName = practiceName;
	}

	public String getPracticeAccountId() {
		return practiceAccountId;
	}

	public void setPracticeAccountId(String practiceAccountId) {
		this.practiceAccountId = practiceAccountId;
	}

	public String getPracticeSharedFolderPath() {
		return practiceSharedFolderPath;
	}

	public void setPracticeSharedFolderPath(String practiceSharedFolderPath) {
		this.practiceSharedFolderPath = practiceSharedFolderPath;
	}

	public String getPracticeAddress() {
		return practiceAddress;
	}

	public void setPracticeAddress(String practiceAddress) {
		this.practiceAddress = practiceAddress;
	}

	public String getPracticeDoctorMessage() {
		return practiceDoctorMessage;
	}

	public void setPracticeDoctorMessage(String practiceDoctorMessage) {
		this.practiceDoctorMessage = practiceDoctorMessage;
	}

	public String getPracticeCity() {
		return practiceCity;
	}

	public void setPracticeCity(String practiceCity) {
		this.practiceCity = practiceCity;
	}

	public String getPracticeWebAddress() {
		return practiceWebAddress;
	}

	public void setPracticeWebAddress(String practiceWebAddress) {
		this.practiceWebAddress = practiceWebAddress;
	}

	public String getPracticeFax() {
		return practiceFax;
	}

	public void setPracticeFax(String practiceFax) {
		this.practiceFax = practiceFax;
	}

	public String getPracticeState() {
		return practiceState;
	}

	public void setPracticeState(String practiceState) {
		this.practiceState = practiceState;
	}

	public String getPracticeZipcode() {
		return practiceZipcode;
	}

	public void setPracticeZipcode(String practiceZipcode) {
		this.practiceZipcode = practiceZipcode;
	}

	public String getPracticeEmail() {
		return practiceEmail;
	}

	public void setPracticeEmail(String practiceEmail) {
		this.practiceEmail = practiceEmail;
	}

	public String getPracticePhone() {
		return practicePhone;
	}

	public void setPracticePhone(String practicePhone) {
		this.practicePhone = practicePhone;
	}

	public String getPortalLoginUrl() {
		return portalLoginUrl;
	}

	public void setPortalLoginUrl(String portalLoginUrl) {
		this.portalLoginUrl = portalLoginUrl;
	}

	public String getPortalLoginContext() {
		return portalLoginContext;
	}

	public void setPortalLoginContext(String portalLoginContext) {
		this.portalLoginContext = portalLoginContext;
	}

	public String getGlaceApacheUrl() {
		return glaceApacheUrl;
	}

	public void setGlaceApacheUrl(String glaceApacheUrl) {
		this.glaceApacheUrl = glaceApacheUrl;
	}

	public String getGlaceTomcatUrl() {
		return glaceTomcatUrl;
	}

	public void setGlaceTomcatUrl(String glaceTomcatUrl) {
		this.glaceTomcatUrl = glaceTomcatUrl;
	}

	public String getGlaceSpringContext() {
		return glaceSpringContext;
	}

	public void setGlaceSpringContext(String glaceSpringContext) {
		this.glaceSpringContext = glaceSpringContext;
	}

	public String getGlaceTomcatContext() {
		return glaceTomcatContext;
	}

	public void setGlaceTomcatContext(String glaceTomcatContext) {
		this.glaceTomcatContext = glaceTomcatContext;
	}

	public Integer getIsPortalUserActive() {
		return isPortalUserActive;
	}

	public void setIsPortalUserActive(Integer isPortalUserActive) {
		this.isPortalUserActive = isPortalUserActive;
	}

	public Integer getIsOldPortalUser() {
		return isOldPortalUser;
	}

	public void setIsOldPortalUser(Integer isOldPortalUser) {
		this.isOldPortalUser = isOldPortalUser;
	}

	public Integer getPortalPassworResetCount() {
		return portalPassworResetCount;
	}

	public void setPortalPassworResetCount(Integer portalPassworResetCount) {
		this.portalPassworResetCount = portalPassworResetCount;
	}
	
}
