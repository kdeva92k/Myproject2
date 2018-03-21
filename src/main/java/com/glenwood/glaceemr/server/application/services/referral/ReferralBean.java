package com.glenwood.glaceemr.server.application.services.referral;

import java.util.List;

import com.glenwood.glaceemr.server.application.models.EmployeeProfile;
import com.glenwood.glaceemr.server.application.models.PatientAssessments;
import com.glenwood.glaceemr.server.application.models.LeafPatient;
import com.glenwood.glaceemr.server.application.models.ProblemList;
import com.glenwood.glaceemr.server.application.models.Referral;

public class ReferralBean {
	
	List<EmployeeProfile> empList;
	
	List<Referral> referralList;
	
	List<LeafPatient> leafList;
	
	List<LeafPatient> savedLeafList;
	
	List<PatientAssessments> dxList;
	
	List<ProblemList> problemList;
	
	List<ReferralDiagnosisBean> diagnosisList;

	public ReferralBean(List<EmployeeProfile> empList, List<Referral> referralList,
					    List<LeafPatient> leafList,List<LeafPatient> savedLeafList,
					    List<PatientAssessments> dxList,List<ProblemList> problemList,
					    List<ReferralDiagnosisBean> diagnosisList) {
		
		this.empList = empList;
		this.referralList = referralList;
		this.leafList = leafList;
		this.savedLeafList = savedLeafList;
		this.dxList = dxList;
		this.problemList = problemList;
		this.diagnosisList = diagnosisList;
		
	}
	
	public List<EmployeeProfile> getEmpList() {
		return empList;
	}

	public void setEmpList(List<EmployeeProfile> empList) {
		this.empList = empList;
	}

	public List<Referral> getReferralList() {
		return referralList;
	}

	public void setReferralList(List<Referral> referralList) {
		this.referralList = referralList;
	}

	public List<LeafPatient> getLeafList() {
		return leafList;
	}

	public void setLeafList(List<LeafPatient> leafList) {
		this.leafList = leafList;
	}

	public List<LeafPatient> getSavedLeafList() {
		return savedLeafList;
	}

	public void setSavedLeafList(List<LeafPatient> savedLeafList) {
		this.savedLeafList = savedLeafList;
	}

	public List<PatientAssessments> getDxList() {
		return dxList;
	}

	public void setDxList(List<PatientAssessments> dxList) {
		this.dxList = dxList;
	}

	public List<ProblemList> getProblemList() {
		return problemList;
	}

	public void setProblemList(List<ProblemList> problemList) {
		this.problemList = problemList;
	}

	public List<ReferralDiagnosisBean> getDiagnosisList() {
		return diagnosisList;
	}

	public void setDiagnosisList(List<ReferralDiagnosisBean> diagnosisList) {
		this.diagnosisList = diagnosisList;
	}
	
	
}
