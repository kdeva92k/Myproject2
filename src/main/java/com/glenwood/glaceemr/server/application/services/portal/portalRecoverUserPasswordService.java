package com.glenwood.glaceemr.server.application.services.portal;

import java.io.IOException;

import org.json.JSONException;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.glenwood.glaceemr.server.application.models.PortalPasswordResetBean;
import com.glenwood.glaceemr.server.application.services.portal.portalSettings.PortalRegistrationResponse;
import com.glenwood.glaceemr.server.utils.EMRResponseBean;

public interface portalRecoverUserPasswordService {

	
	RecoverPortalPasswordBean authenticateUsernameAndGetSecurityQuestions(RecoverPortalPasswordBean recoverBean) throws JsonProcessingException;
	
	EMRResponseBean authenticateSecurityQuestions(RecoverPortalPasswordBean recoverBean) throws IOException, JSONException;

	PortalRegistrationResponse resetPatientPassword(PortalPasswordResetBean portalPasswordResetBean);

	PortalRegistrationResponse updatePasswordResetToken(Integer patientId);
}
