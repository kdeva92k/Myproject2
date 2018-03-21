package com.glenwood.glaceemr.server.application.services.chart.assessment;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceContext;
import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specifications;
import org.springframework.stereotype.Service;

import com.glenwood.glaceemr.server.application.models.PatientAssessments;
import com.glenwood.glaceemr.server.application.models.ProblemList;
import com.glenwood.glaceemr.server.application.repositories.AssessmentRepository;
import com.glenwood.glaceemr.server.application.repositories.EncounterRepository;
import com.glenwood.glaceemr.server.application.repositories.ProblemListRepository;
import com.glenwood.glaceemr.server.application.specifications.AssessmentSpecification;
import com.glenwood.glaceemr.server.application.specifications.ProblemListSpecification;
import com.glenwood.glaceemr.server.utils.SessionMap;
import com.google.common.base.Optional;
import com.google.common.base.Strings;

@Service
public class AssessmentSummaryServiceImpl implements AssessmentSummaryService{

	@Autowired
	AssessmentRepository assessmentRepository;
		
	@Autowired
	EncounterRepository encounterRepository;
	
	@Autowired
	ProblemListRepository problemListRepository;

	@Autowired
	EntityManagerFactory emf ;
	
	@PersistenceContext
	EntityManager em;

	@Autowired
	SessionMap sessionMap;

	@Autowired
	HttpServletRequest request;
	
	private Logger logger = Logger.getLogger(AssessmentSummaryServiceImpl.class);
	
	/**
	 * Method to fetch current visit data
	 */
	@Override
	public List<PatientAssessments> getCurrentDiagnosis(Integer patientId,Integer encounterId) throws Exception {
		
		logger.debug("Fetching Current Problems");
		logger.error("in getCurrentDiagnosis");
		encounterId = Integer.parseInt(Optional.fromNullable(Strings.emptyToNull("" + encounterId)).or("-1"));
		patientId = Integer.parseInt(Optional.fromNullable(Strings.emptyToNull("" + patientId)).or("-1"));
		List<PatientAssessments> dxs = assessmentRepository.findAll(Specifications.where(AssessmentSpecification.DxByPatientId(patientId)).and(AssessmentSpecification.DxByEncounterId(encounterId)).and(AssessmentSpecification.getOrder()));		
		return dxs;
		
	}
 
	
    /**
     * Method to save current visit diagnosis data
     */
	@Override
	public Boolean saveDiagnosis(JSONObject assessListobj) throws Exception {
		
		
		int patientId = Integer.parseInt(Optional.fromNullable(Strings.emptyToNull("" + assessListobj.getString("patientId"))).or("-1"));
		int encounterId = Integer.parseInt(Optional.fromNullable(Strings.emptyToNull("" + assessListobj.getString("encounterId"))).or("-1"));
		int userId = Integer.parseInt(Optional.fromNullable(Strings.emptyToNull("" + assessListobj.getString("userId"))).or("-1"));
		JSONArray assessmentDetailsArray=(JSONArray) assessListobj.get("assessList");
		
		for(int i=0;i<assessmentDetailsArray.length();i++)
		{			
		String assessmentDetailsStr=assessmentDetailsArray.getString(i);
		JSONObject assessmentDetailsObj=new JSONObject(assessmentDetailsStr);			
		String dxCode = Optional.fromNullable(Strings.emptyToNull("" + assessmentDetailsObj.getString("dxCode"))).or(" ");
		String dxDesc = Optional.fromNullable(Strings.emptyToNull("" + assessmentDetailsObj.getString("dxDesc"))).or(" ");
		String dxStatus = Optional.fromNullable(Strings.emptyToNull("" + assessmentDetailsObj.getString("DxStatus"))).or("-2");
		String assessmentComment = Optional.fromNullable(Strings.emptyToNull("" + assessmentDetailsObj.getString("assessmentComment"))).or(" ");
		List<PatientAssessments> dupDxCode = assessmentRepository.findAll(Specifications.where(AssessmentSpecification.DxByPatientId(patientId)).and(AssessmentSpecification.DxByEncounterId(encounterId)).and(AssessmentSpecification.DxByCode(dxCode)));
		if(dupDxCode.size()<1 &&(!dxCode.trim().equalsIgnoreCase(""))){	
			}
		}
				
		return null;
	}



	/**
	 * Method to fetch data for edit page
	 */
	@Override
	public List<PatientAssessments> getEditData(Integer patientId, Integer encounterId,String dxCode,
			Integer problemId) {
		patientId = Integer.parseInt(Optional.fromNullable(Strings.emptyToNull("" + patientId)).or("-1"));
		encounterId = Integer.parseInt(Optional.fromNullable(Strings.emptyToNull("" + encounterId)).or("-1"));
		dxCode = Optional.fromNullable(Strings.emptyToNull("" + encounterId)).or(" ");
		problemId = Integer.parseInt(Optional.fromNullable(Strings.emptyToNull("" + problemId)).or("-1"));
		List<PatientAssessments> dxData = assessmentRepository.findAll(Specifications.where(AssessmentSpecification.getDataToEdit(patientId,encounterId,dxCode,problemId)));
		return dxData;
	}
	

	/**
	 * Method to send current encounter assessments to problem list 
	 */
	@Override
	public String moveToProblemList(Integer patientId,Integer encounterId,Integer userId) throws Exception {
		
		List<PatientAssessments> currentdiagnosis=getCurrentDiagnosis(patientId, encounterId);
		
		for(int i=0;i<currentdiagnosis.size();i++){
			
			PatientAssessments assessmentDetailsObj=currentdiagnosis.get(i);
			String dxCode = assessmentDetailsObj.getpatient_assessments_dxcode();
			String dxdesc = assessmentDetailsObj.getpatient_assessments_dxdescription();
			String ass_cmt = assessmentDetailsObj.getpatient_assessments_assessmentcomment();			
			String codeSystem = assessmentDetailsObj.getpatient_assessmentsCodingSystemid();
			
			if(dxCode!= null)
				dxCode= dxCode.trim();
			long count= problemListRepository.count(ProblemListSpecification.getByDxcode(dxCode, patientId));
			
			if(count==0){
			ProblemList problem = new ProblemList();
			problem.setProblemListPatientId(patientId);
			problem.setProblemListId(-1);
			problem.setProblemListDxCode(dxCode);
			problem.setProblemListDxDescp(dxdesc);
			problem.setProblemListComments(ass_cmt);
			problem.setProblemListChronicity(-1);
			problem.setProblemListCreatedby(userId);
			problem.setProblemListCreatedon(new Timestamp(new Date().getTime()));
			problem.setProblemListLastModOn(new Timestamp(new Date().getTime()));
			problem.setProblemListIsresolved(false);
			problem.setProblemListIsactive(true);
			problem.setProblemListOnsetDate(null);
			problem.setProblemListCodingSystemid(codeSystem);
			problem.setH063015(-1);
			problemListRepository.save(problem);
			}
			
		}
				
		return "success";
	}

}


