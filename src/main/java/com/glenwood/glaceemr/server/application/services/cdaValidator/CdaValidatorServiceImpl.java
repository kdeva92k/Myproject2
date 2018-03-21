package com.glenwood.glaceemr.server.application.services.cdaValidator;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.openhealthtools.mdht.uml.cda.consol.ConsolPackage;
import org.openhealthtools.mdht.uml.cda.mu2consol.Mu2consolPackage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.FileWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaUpdate;
import javax.persistence.criteria.Root;

import org.eclipse.emf.common.util.Diagnostic;
import org.eclipse.mdht.uml.cda.util.CDAUtil;
import org.eclipse.mdht.uml.cda.util.ValidationResult;

import com.glenwood.glaceemr.server.application.models.CdaInbox;
import com.glenwood.glaceemr.server.application.models.CdaInbox_;
import com.glenwood.glaceemr.server.application.repositories.CdaInboxRepository;
import com.glenwood.glaceemr.server.utils.HUtil;

/**
 * Service Implementation file for CdaValidatorController
 * @author Nithya
 *
 */
@Service
@Transactional
public class CdaValidatorServiceImpl implements CdaValidatorService {

	@Autowired
	CdaInboxRepository cdaInboxRepository;
	
	@Autowired
	EntityManager em;
	
	/**
	 * To Validate the give file and Based on isData flag update the status of the file in db and give the schema error message .
	 * @param filaPath   	Full Path of CDA file to be validate
	 * @param fileId    	File id to be validate 
	 * @param isData     	Flag will decide whether heve to update the status or have to give the schema error message
	 * @return    Validation Result / Exception message 
	 */
	@Override
	public JSONObject doValidation(String filaPath,String fileId,String isData) throws IOException, JSONException {
		Mu2consolPackage.eINSTANCE.unload();
		ConsolPackage.eINSTANCE.eClass();
		JSONObject errorObject=new JSONObject();
		ValidationResult result = new ValidationResult();
		File negativeFile = new File(filaPath);
		try{
			CDAUtil.load(new FileInputStream(negativeFile),  result);
			errorObject.put("ErrorMessage", "");
		}catch(Exception e){
				errorObject.put("ErrorMessage", "Error: "+e.getMessage());
				e.printStackTrace();
		}
		
		JSONArray ErrorMessage= new JSONArray();
		for (Diagnostic diagnostic : result.getErrorDiagnostics()) {
    		ErrorMessage.put(diagnostic.getMessage());
    	}
		if(isData.equalsIgnoreCase("f")){
		if(result.getErrorDiagnostics().size()>0){
			CriteriaBuilder criteriaBuilder=em.getCriteriaBuilder();
			CriteriaUpdate<CdaInbox> updateQry=criteriaBuilder.createCriteriaUpdate(CdaInbox.class);
			Root<CdaInbox> root = updateQry.from(CdaInbox.class);
			updateQry.set("error", true);
			updateQry.where(criteriaBuilder.equal(root.get(CdaInbox_.id),Integer.parseInt(fileId)));
			int updateResult=em.createQuery(updateQry).executeUpdate();
			if(updateResult!=-1){
			String	responseMsg="successfully added";
			errorObject.put("updateResult", responseMsg);
			}
		}else{
			CriteriaBuilder criteriaBuilder=em.getCriteriaBuilder();
			CriteriaUpdate<CdaInbox> updateQry=criteriaBuilder.createCriteriaUpdate(CdaInbox.class);
			Root<CdaInbox> root = updateQry.from(CdaInbox.class);
			updateQry.set("error", false);
			updateQry.where(criteriaBuilder.equal(root.get(CdaInbox_.id),Integer.parseInt(fileId)));
			int updateResult=em.createQuery(updateQry).executeUpdate();
			if(updateResult!=-1){
			String	responseMsg="successfully added";
			errorObject.put("updateResult", responseMsg);
			}
			
		}
		}else{
				errorObject.put("data", ErrorMessage);
		}
		return errorObject;
	

   
	}

}
