package com.glenwood.glaceemr.server.application.services.chart.problemlist;

import java.net.URLDecoder;
import java.sql.Date;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceContext;
import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.glenwood.glaceemr.server.application.models.ProblemList;
import com.glenwood.glaceemr.server.application.repositories.ProblemListRepository;
import com.glenwood.glaceemr.server.application.services.audittrail.AuditTrailService;
import com.glenwood.glaceemr.server.application.specifications.ProblemListSpecification;
import com.glenwood.glaceemr.server.utils.SessionMap;
import com.google.common.base.Optional;
import com.google.common.base.Strings;

/**
 * Contains the implementation for the set of operations corresponds to problem list
 * @author Mamatha
 *
 */
@Service
public class ProblemListServiceImpl implements ProblemListService{


	@Autowired
	ProblemListRepository problemListRepository;
		
	@Autowired
	EntityManagerFactory emf ;
	
	@PersistenceContext
	EntityManager em;
	
	@Autowired
	AuditTrailService auditTrailService;

	@Autowired
	SessionMap sessionMap;

	@Autowired
	HttpServletRequest request;
	
	private Logger logger = Logger.getLogger(ProblemListServiceImpl.class);
	
	
	/**
	 * Method to fetch active problems
	 */
	@Override
	public List<ProblemList> getActiveProblems(Integer patientId) {
		patientId = Integer.parseInt(Optional.fromNullable(Strings.emptyToNull("" + patientId)).or("-1"));
		List<ProblemList> problemlist=problemListRepository.findAll(ProblemListSpecification.getproblemlist(patientId));
	    return problemlist;
	}


	/**
	 * Method to fetch inactive and resolved  problems
	 */
	@Override
	public List<ProblemList> getInactiveProblems(Integer patientId)
			throws Exception {
		patientId = Integer.parseInt(Optional.fromNullable(Strings.emptyToNull("" + patientId)).or("-1"));
		List<ProblemList> problemlist=problemListRepository.findAll(ProblemListSpecification.getInactiveResolved(patientId));
	    return problemlist;
	}


	/**
	 * Method to retrieve edit page data
	 */
	@Override
	public List<ProblemList> getEditData(Integer patientId, Integer problemId) {
		
		patientId = Integer.parseInt(Optional.fromNullable(Strings.emptyToNull("" + patientId)).or("-1"));
		problemId = Integer.parseInt(Optional.fromNullable(Strings.emptyToNull("" + problemId)).or("-1"));
		List<ProblemList> problemlist=problemListRepository.findAll(ProblemListSpecification.getDataToEdit(patientId,problemId));
	    return problemlist;
	}

    /**
     * Method to update a problem information
     */
	@Override
	public void editDataSave(Integer patientId, Integer userId, String saveData) throws Exception{
		 
		   JSONObject dxData = new JSONObject(saveData);
		   JSONObject dxEditData = (JSONObject) dxData.get("editData");
		   
		   patientId = Integer.parseInt(Optional.fromNullable(Strings.emptyToNull("" + patientId)).or("-1"));
		   userId = Integer.parseInt(Optional.fromNullable(Strings.emptyToNull("" + userId)).or("-1"));
			
		   int problemId =   Integer.parseInt(Optional.fromNullable(Strings.emptyToNull(dxEditData.get("problemId").toString())).or("-1"));
		   String onsetDate =  Optional.fromNullable(Strings.emptyToNull(dxEditData.get("onsetDate").toString())).or(" ");
		   String comments =  Optional.fromNullable(Strings.emptyToNull(dxEditData.get("comments").toString())).or(" ");
		   try{
			   comments = URLDecoder.decode(comments, "UTF-8");
		   }catch(Exception e){
			   
		   }
		   
		   String isActive =  Optional.fromNullable(Strings.emptyToNull(dxEditData.get("isActive").toString())).or(" ");
		   String resDate =  Optional.fromNullable(Strings.emptyToNull(dxEditData.get("resDate").toString())).or(" ");
		   String inacDate =  Optional.fromNullable(Strings.emptyToNull(dxEditData.get("inacDate").toString())).or(" ");
		   String codeSystem =  Optional.fromNullable(Strings.emptyToNull(dxEditData.get("codeSystem").toString())).or(" ");
	       int isResolved = Integer.parseInt(Optional.fromNullable(Strings.emptyToNull(dxEditData.get("isResolved").toString())).or("0"));
	       int chronicity = Integer.parseInt(Optional.fromNullable(Strings.emptyToNull(dxEditData.get("chronicity").toString())).or("-1"));
	       
	       
	       List<ProblemList> dxs = problemListRepository.findAll(ProblemListSpecification.getDataToEdit(patientId,problemId));
			if( dxs.size() > 0 ) {
				for (ProblemList dx : dxs) {
					
					if(!onsetDate.trim().equals("")){
						if(onsetDate.contains("/")){
							DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd"); 
							Date date = (Date) formatter.parse(onsetDate);
							
							DateFormat formatter2 = new SimpleDateFormat("yyyy-MM-dd");
							Date date2 = (Date) formatter2.parse(onsetDate);
							
							dx.setProblemListOnsetDate(date2);
						}else if(onsetDate.contains("-")){
							DateFormat formatter = new SimpleDateFormat("MM/dd/yyyy"); 
							Date date = (Date) formatter.parse(onsetDate);
							DateFormat formatter2 = new SimpleDateFormat("yyyy-MM-dd");
							Date date2 = (Date) formatter2.parse(onsetDate);
							dx.setProblemListOnsetDate(date2);
							
						}
					
					}
									
					
				    dx.setProblemListComments(comments);
				    boolean active = false;
				    
				    if(isActive.equalsIgnoreCase("f")||isActive.equalsIgnoreCase("false")){
				    	dx.setProblemListIsactive(false);
				    	dx.setProblemListInactivatedBy(userId);
				    	dx.setProblemListResolvedBy(null);
				    	dx.setProblemListResolvedDate(null);
				    	//Date date1 = (Date) formatter.parse(inacDate);
				    	//dx.setProblemListInactiveDate(date1);
				    }else{
				    	dx.setProblemListIsactive(true);
				    }
				    
				    boolean isres=false;
					if(isResolved==1){
						  isres=true;
					}
					if(isres){
						dx.setProblemListResolvedBy(userId);
						/*Date date2 = (Date) formatter.parse(resDate);
						dx.setProblemListResolvedDate(date2);*/
					}
					dx.setProblemListIsresolved(isres);

					problemListRepository.saveAndFlush(dx);
				}
			}
	
	}

    /**
     * Method to delete a problem from problem list
     */
	@Override
	public void deleteDataSave(Integer patientId, String deleteData) throws JSONException {

		JSONObject problemIdObj=new JSONObject(deleteData);
		JSONArray problemIdArray=(JSONArray) problemIdObj.get("ProblemIdArr");

		String problemIdStr=problemIdArray.toString().replace("[\"", "").replace("\"]", "");

		for(String currentProblemId:problemIdStr.split(",")){

			int currentPid=Integer.parseInt(currentProblemId);
			ProblemList plist = problemListRepository.findOne(currentPid);
			plist.setProblemListIsactive(false);
			problemListRepository.saveAndFlush(plist);

		}

	}

	 /**
     * Method to delete a problem from problem list
	 * @return 
     */
	@Override
	public List<ProblemList> deleteDataSaveFetch(Integer patientId, String deleteData) throws JSONException {

		JSONObject problemIdObj=new JSONObject(deleteData);
		JSONArray problemIdArray=(JSONArray) problemIdObj.get("ProblemIdArr");

		String problemIdStr=problemIdArray.toString().replace("[\"", "").replace("\"]", "");

		for(String currentProblemId:problemIdStr.split(",")){

			int currentPid=Integer.parseInt(currentProblemId);
			ProblemList plist = problemListRepository.findOne(currentPid);
			plist.setProblemListIsactive(false);
			problemListRepository.saveAndFlush(plist);

		}

		return getActiveProblems(patientId);
	}

	
}
