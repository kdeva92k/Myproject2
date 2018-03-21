package com.glenwood.glaceemr.server.application.services.portal;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Random;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.text.WordUtils;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.glenwood.glaceemr.server.application.models.PatientPortalUser;
import com.glenwood.glaceemr.server.application.models.InitialSettings;
import com.glenwood.glaceemr.server.application.models.PatientRegistration;
import com.glenwood.glaceemr.server.application.models.PortalPasswordResetBean;
import com.glenwood.glaceemr.server.application.repositories.PatientPortalUserRepository;
import com.glenwood.glaceemr.server.application.repositories.InitialSettingsRepository;
import com.glenwood.glaceemr.server.application.services.audittrail.AuditTrailEnumConstants;
import com.glenwood.glaceemr.server.application.services.audittrail.AuditTrailSaveService;
import com.glenwood.glaceemr.server.application.services.portal.portalSettings.PortalRegistrationResponse;
import com.glenwood.glaceemr.server.application.specifications.PortalLoginSpecification;
import com.glenwood.glaceemr.server.application.specifications.PortalRecoverUserPasswordSpecifiction;
import com.glenwood.glaceemr.server.application.specifications.PortalSettingsSpecification;
import com.glenwood.glaceemr.server.utils.EMRResponseBean;
import com.glenwood.glaceemr.server.utils.MD5;
import com.glenwood.glaceemr.server.utils.MultipartUtility;

@Service
public class portalRecoverUserPasswordServiceImpl implements portalRecoverUserPasswordService{

	@Autowired
	PatientPortalUserRepository patient_portal_userRepository;

	@Autowired
	ObjectMapper objectMapper;
	
	@Autowired
	AuditTrailSaveService auditTrailSaveService;
	
	@Autowired
	HttpServletRequest request;
	
	@Autowired
	InitialSettingsRepository initialSettingsRepository;

	@Override
	public RecoverPortalPasswordBean authenticateUsernameAndGetSecurityQuestions(
			RecoverPortalPasswordBean recoverBean) throws JsonProcessingException {

		List<PatientPortalUser> portalUsersList=patient_portal_userRepository.findAll(PortalRecoverUserPasswordSpecifiction.authenticatePortalUser(recoverBean.getUsername()));

		RecoverPortalPasswordBean securityQuestionsBean=new RecoverPortalPasswordBean();
		
		if(portalUsersList.size()>0){
		PatientPortalUser portalUser=portalUsersList.get(0);
		securityQuestionsBean.setUsername(portalUser.getpatient_portal_user_name());
		securityQuestionsBean.setDbname(recoverBean.getDbname());
		securityQuestionsBean.setSecurityQuestion1(portalUser.getSecurityQuestion1());
		securityQuestionsBean.setSecurityQuestion2(portalUser.getSecurityQuestion2());
		securityQuestionsBean.setSecurityQuestion3(portalUser.getSecurityQuestion3());
		}
		return securityQuestionsBean;
	}

	@Override
	public EMRResponseBean authenticateSecurityQuestions(
			RecoverPortalPasswordBean recoverBean) throws IOException, JSONException {

		PatientPortalUser portalUser=patient_portal_userRepository.findOne(PortalRecoverUserPasswordSpecifiction.authenticatePortalUser(recoverBean.getUsername()));
		InitialSettings practiceDetails=initialSettingsRepository.findOne(PortalSettingsSpecification.getPracticeDetails("Practice Name"));


		EMRResponseBean bean=new EMRResponseBean();

		bean.setCanUserAccess(true);
		bean.setIsAuthorizationPresent(true);
		bean.setLogin(false);

		boolean isDOBCorrect=false;
		//boolean isAccountIDCorrect=false;
		boolean isAnswer1Correct=false;
		boolean isAnswer2Correct=false;
		boolean isAnswer3Correct=false;

		DateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");
		Date DOB = new Date(portalUser.getChartpatient_portal_userTable().getPatientRegistrationTable().getPatientRegistrationDob().getTime());

		if(dateFormat.format(DOB).equalsIgnoreCase(recoverBean.getDob()))
			isDOBCorrect=true;
		/*if(portalUser.getChartpatient_portal_userTable().getPatientRegistrationTable().getPatientRegistrationAccountno().equalsIgnoreCase(recoverBean.getAccountID()))
			isAccountIDCorrect=true;*/
		if(portalUser.getSecurityAnswer1().equalsIgnoreCase(recoverBean.getSecurityAnswer1()))
			isAnswer1Correct=true;
		if(portalUser.getSecurityAnswer2().equalsIgnoreCase(recoverBean.getSecurityAnswer2()))
			isAnswer2Correct=true;
		if(portalUser.getSecurityAnswer3().equalsIgnoreCase(recoverBean.getSecurityAnswer3()))
			isAnswer3Correct=true;


		StringBuffer sb=new StringBuffer();

		if(!isDOBCorrect||!isAnswer1Correct||!isAnswer2Correct||!isAnswer3Correct)
		{
			if(!isDOBCorrect)
				sb.append("DOB, ");
			/*if(!isAccountIDCorrect)
				sb.append("Account ID, ");*/
			if(!isAnswer1Correct)
				sb.append("Answer1, ");
			if(!isAnswer2Correct)
				sb.append("Answer2, ");
			if(!isAnswer3Correct)
				sb.append("Answer3, ");
			
			bean.setSuccess(false);
			bean.setData(sb.toString().substring(0, sb.toString().length()-2)+" authentication failed.");
			return bean;
		}
		
		portalUser.setpatient_portal_userToken(generateRandomString());
		auditTrailSaveService.LogEvent(AuditTrailEnumConstants.LogType.GLACE_LOG,AuditTrailEnumConstants.LogModuleType.PATIENTPORTAL,
				AuditTrailEnumConstants.LogActionType.SIGNUP,1,AuditTrailEnumConstants.Log_Outcome.SUCCESS,"Patient with id "+portalUser.getpatient_portal_user_patient_id()+" has successfully updated the token.",-1,
				request.getRemoteAddr(),Integer.parseInt(String.valueOf(portalUser.getpatient_portal_user_patient_id())),"",
				AuditTrailEnumConstants.LogUserType.PATIENT_LOGIN,"Patient with id "+portalUser.getpatient_portal_user_patient_id()+" has requested the password reset.","");


		String URL ="https://mailer1.glaceemr.com/Mailer/sendMail";

		String charSet = "UTF-8";

		String boundary = "===" + System.currentTimeMillis() + "===";

		MultipartUtility multipartUtility=new MultipartUtility(URL, charSet, boundary);

		HttpURLConnection httpURLConnection;
		String mailpassword="demopwd0";

		int mailtype=1;

		String subject="Password Recovery";

		JSONArray toIds = new JSONArray();
		toIds.put(portalUser.getChartpatient_portal_userTable().getPatientRegistrationTable().getPatientRegistrationMailId());

		JSONArray ccids = new JSONArray();

		JSONArray bccids = new JSONArray();

		String accountId="Glenwood";


		String htmlbody="<html><head></head>"+
				"<body style='width:100%;color:#1e1e1e;font-size:16px;'>"+
				"<label style='width:100%;padding:10px 5px;'>Dear "+WordUtils.capitalizeFully(portalUser.getChartpatient_portal_userTable().getPatientRegistrationTable().getPatientRegistrationFirstName())+" "+WordUtils.capitalizeFully(portalUser.getChartpatient_portal_userTable().getPatientRegistrationTable().getPatientRegistrationLastName())+",</label>"+
				"<br><br>"+
				"<label>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Welcome to Patient Portal password reset service.</label>"+
				"<br><br>"+
				"<label>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<a style='color:#1773cf' href='https://patientportal.glaceemr.com/glaceportal_login/PasswordReset.jsp?practiceId="+recoverBean.getDbname()+"&patientId="+portalUser.getpatient_portal_user_patient_id()+"&resetToken="+portalUser.getpatient_portal_userToken()+"'>Click here to reset password.</a></label>"+
				"<br><br>"+
				"<label style='width:100%;padding:10px 5px;'>Thanks and Regards,</label>"+
				"<br>"+
				"<label style='width:100%;padding:10px 5px;'>"+WordUtils.capitalizeFully(practiceDetails.getInitialSettingsOptionValue())+".</label>"+
				"<label></label></body></html>";

		String plaintext="";


		JSONObject jsonInString = new JSONObject();

		jsonInString.put("mailtype", mailtype);

		jsonInString.put("sender","donotreply@glenwoodsystems.com");

		jsonInString.put("to",toIds);

		jsonInString.put("cc", ccids);

		jsonInString.put("bcc", bccids);

		jsonInString.put("subject",subject);

		jsonInString.put("plaintext",plaintext);

		jsonInString.put("htmlbody",htmlbody);

		jsonInString.put("accountId",accountId);

		jsonInString.put("mailpassword",mailpassword);

		multipartUtility.addFormField("mailerResp", jsonInString.toString());

		httpURLConnection = multipartUtility.execute();

		httpURLConnection.connect();

		System.out.println(httpURLConnection.getResponseCode());
		
		if(httpURLConnection.getResponseCode()==200){
			bean.setSuccess(true);
			bean.setData(portalUser.getChartpatient_portal_userTable().getPatientRegistrationTable().getPatientRegistrationMailId());
		}
		else{
			bean.setSuccess(false);
		bean.setData("Email sending failed. Please try after some time.");
		}

		return bean;
	}

	@Override
	public PortalRegistrationResponse resetPatientPassword(PortalPasswordResetBean portalPasswordResetBean) {
		
		PortalRegistrationResponse regResponse=new PortalRegistrationResponse();
		
		if(portalPasswordResetBean.getPatientId()!=-1){
			PatientPortalUser portalUser=patient_portal_userRepository.findOne(PortalLoginSpecification.patientById(String.valueOf(portalPasswordResetBean.getPatientId())));
			
			if(portalUser.getpatient_portal_userToken()==null||portalUser.getpatient_portal_userToken().equals("-1")){
				regResponse.setSuccess(false);
				regResponse.setMessage("Password reset link has been expired.");
			}else if(!portalUser.getpatient_portal_userToken().equals(portalPasswordResetBean.getToken())){
				regResponse.setSuccess(false);
				regResponse.setMessage("Password reset token mismatch.");
				auditTrailSaveService.LogEvent(AuditTrailEnumConstants.LogType.GLACE_LOG,AuditTrailEnumConstants.LogModuleType.PATIENTPORTAL,
						AuditTrailEnumConstants.LogActionType.SIGNUP,1,AuditTrailEnumConstants.Log_Outcome.SUCCESS,"Patient with id "+portalPasswordResetBean.getPatientId()+" has successfully reset the password.",-1,
						request.getRemoteAddr(),Integer.parseInt(String.valueOf(portalUser.getpatient_portal_user_patient_id())),"",
						AuditTrailEnumConstants.LogUserType.PATIENT_LOGIN,"Patient with id "+portalPasswordResetBean.getPatientId()+" has successfully reset the password.","");
			}else if(portalUser.getpatient_portal_userToken().equals(portalPasswordResetBean.getToken())){
				
				if(!portalPasswordResetBean.getPassword().equals(portalUser.getpatient_portal_user_password())){
				portalUser.setpatient_portal_userToken("-1");
				portalUser.setpatient_portal_user_password(portalPasswordResetBean.getPassword());
				MD5 md5 = new MD5();
				md5.setHashString(portalPasswordResetBean.getPassword());
				String encryptedPassword=md5.getHashString();
				portalUser.setpatient_portal_user_password_hash(encryptedPassword);
				patient_portal_userRepository.saveAndFlush(portalUser);
				regResponse.setSuccess(true);
				regResponse.setMessage("Your password has been reset successfully.");
				auditTrailSaveService.LogEvent(AuditTrailEnumConstants.LogType.GLACE_LOG,AuditTrailEnumConstants.LogModuleType.PATIENTPORTAL,
						AuditTrailEnumConstants.LogActionType.SIGNUP,1,AuditTrailEnumConstants.Log_Outcome.SUCCESS,"Patient with id "+portalPasswordResetBean.getPatientId()+" has successfully reset the password.",-1,
						request.getRemoteAddr(),Integer.parseInt(String.valueOf(portalUser.getpatient_portal_user_patient_id())),"",
						AuditTrailEnumConstants.LogUserType.PATIENT_LOGIN,"Patient with id "+portalPasswordResetBean.getPatientId()+" has successfully reset the password.","");
				}else{
					regResponse.setSuccess(false);
					regResponse.setMessage("Please enter new password different from old one.");
					auditTrailSaveService.LogEvent(AuditTrailEnumConstants.LogType.GLACE_LOG,AuditTrailEnumConstants.LogModuleType.PATIENTPORTAL,
							AuditTrailEnumConstants.LogActionType.SIGNUP,1,AuditTrailEnumConstants.Log_Outcome.SUCCESS,"Patient with id "+portalPasswordResetBean.getPatientId()+" has failed to reset the password.",-1,
							request.getRemoteAddr(),Integer.parseInt(String.valueOf(portalUser.getpatient_portal_user_patient_id())),"",
							AuditTrailEnumConstants.LogUserType.PATIENT_LOGIN,"Patient with id "+portalPasswordResetBean.getPatientId()+" has failed to reset the password.","");
				}
			}
				
		}else{
			regResponse.setSuccess(false);
			regResponse.setMessage("Password reset link is invalid or corrupted.");
		}
		
		return regResponse;
	
	}
	
	@Override
	public PortalRegistrationResponse updatePasswordResetToken(Integer patientId) {
		
		PortalRegistrationResponse regResponse=new PortalRegistrationResponse();
		
		if(patientId!=-1){
			PatientPortalUser portalUser=patient_portal_userRepository.findOne(PortalLoginSpecification.patientById(String.valueOf(patientId)));
			
			portalUser.setpatient_portal_userToken(generateRandomString());
			regResponse.setSuccess(true);
			regResponse.setMessage("Password reset token has been generated successfully.");
			auditTrailSaveService.LogEvent(AuditTrailEnumConstants.LogType.GLACE_LOG,AuditTrailEnumConstants.LogModuleType.PATIENTPORTAL,
					AuditTrailEnumConstants.LogActionType.SIGNUP,1,AuditTrailEnumConstants.Log_Outcome.SUCCESS,"Patient with id "+patientId+" has successfully updated the token.",-1,
					request.getRemoteAddr(),Integer.parseInt(String.valueOf(portalUser.getpatient_portal_user_patient_id())),"",
					AuditTrailEnumConstants.LogUserType.PATIENT_LOGIN,"Patient with id "+patientId+" has requested the password reset.","");
		}else{
			regResponse.setSuccess(false);
			regResponse.setMessage("No such user exists.");
		}
		
		return regResponse;
	
	}
	
	 	private static final String CHAR_LIST =  "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890";
	    private static final int RANDOM_STRING_LENGTH = 10;
	     
	    /**
	     * This method generates random string
	     * @return
	     */
	    public String generateRandomString(){
	         
	        StringBuffer randStr = new StringBuffer();
	        for(int i=0; i<RANDOM_STRING_LENGTH; i++){
	            int number = getRandomNumber();
	            char ch = CHAR_LIST.charAt(number);
	            randStr.append(ch);
	        }
	        return randStr.toString();
	    }
	     
	    /**
	     * This method generates random numbers
	     * @return int
	     */
	    private int getRandomNumber() {
	        int randomInt = 0;
	        Random randomGenerator = new Random();
	        randomInt = randomGenerator.nextInt(CHAR_LIST.length());
	        if (randomInt - 1 == -1) {
	            return randomInt;
	        } else {
	            return randomInt - 1;
	        }
	    }
}
