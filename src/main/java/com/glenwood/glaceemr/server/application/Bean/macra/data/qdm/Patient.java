package com.glenwood.glaceemr.server.application.Bean.macra.data.qdm;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

import com.glenwood.glaceemr.server.application.Bean.pqrs.Claim;



public class Patient
{
	private int patientId;
	private String firstName = new String();
	private String lastName = new String();
	private Date dob = new Date();
	private String gender = new String();
	private String race = new String();
	private String ethnicity = new String();
	private List<Encounter> encounterList = new ArrayList<Encounter>();
	private List<Diagnosis> dxList = new ArrayList<Diagnosis>();
	private List<Assessment> riskAssessmentList = new ArrayList<Assessment>();
	private List<Procedure> procedureList = new ArrayList<Procedure>();
	private List<Intervention> interventionList = new ArrayList<Intervention>();
	private List<MedicationOrder> medicationOrderList = new ArrayList<MedicationOrder>();
	private List<PhysicalExam> physicalExamList = new ArrayList<PhysicalExam>();
	private List<DiagnosticStudy> diagnosticStudyList = new ArrayList<DiagnosticStudy>();
	private List<LabTest> labTestList = new ArrayList<LabTest>();
	private List<FunctionalStatus> functionalStatus=new ArrayList<FunctionalStatus>();
	private List<Communication> communications = new ArrayList<Communication>();
	private List<Immunization> immunizationList = new ArrayList<Immunization>();
	private List<QDM> tobaccoStatusList = new ArrayList<QDM>();
	private List<ActiveMedication> activeMedicationsList = new ArrayList<ActiveMedication>();
	private List<ProcedureIntolerance> procedureIntoleranceList = new ArrayList<ProcedureIntolerance>();
	private List<ImmunizationIntolerance> immunizationIntoleranceList = new ArrayList<ImmunizationIntolerance>();
	private List<Claim> claims = new ArrayList<Claim>();
	
	public int getPatientId()
	{
		return patientId;
	}
	public void setPatientId(int patientId)
	{
		this.patientId = patientId;
	}
	public String getFirstName()
	{
		return firstName;
	}
	public List<Procedure> getProcedureList()
	{
		return procedureList;
	}
	public void setProcedureList(List<Procedure> procedureList)
	{
		this.procedureList = procedureList;
	}
	public List<Intervention> getInterventionList()
	{
		return interventionList;
	}
	public void setInterventionList(List<Intervention> interventionList)
	{
		this.interventionList = interventionList;
	}
	public List<MedicationOrder> getMedicationOrderList()
	{
		return medicationOrderList;
	}
	public void setMedicationOrders(List<MedicationOrder> medicationOrderList)
	{
		this.medicationOrderList = medicationOrderList;
	}
	public void setFirstName(String firstName)
	{
		this.firstName = firstName;
	}
	public String getLastName()
	{
		return lastName;
	}
	public void setLastName(String lastName)
	{
		this.lastName = lastName;
	}
	public Date getDob()
	{
		return dob;
	}
	public void setDob(Date dob)
	{
		this.dob = dob;
	}
	public String getGender()
	{
		return gender;
	}
	public void setGender(String gender)
	{
		this.gender = gender;
	}
	public String getRace()
	{
		return race;
	}
	public void setRace(String race)
	{
		this.race = race;
	}
	public String getEthnicity()
	{
		return ethnicity;
	}
	public void setEthnicity(String ethnicity)
	{
		this.ethnicity = ethnicity;
	}
	public List<Encounter> getEncounterList()
	{
		return encounterList;
	}
	public void setEncounterList(List<Encounter> encounterList)
	{
		this.encounterList = encounterList;
	}
	public List<Diagnosis> getDxList()
	{
		return dxList;
	}
	public void setDxList(List<Diagnosis> dxList)
	{
		this.dxList = dxList;
	}
	public List<Assessment> getRiskAssessmentList()
	{
		return riskAssessmentList;
	}
	public void setRiskAssessmentList(List<Assessment> riskAssessmentList)
	{
		this.riskAssessmentList = riskAssessmentList;
	}
	public List<PhysicalExam> getPhysicalExamList()
	{
		return physicalExamList;
	}
	public void setPhysicalExamList(List<PhysicalExam> physicalExamList)
	{
		this.physicalExamList = physicalExamList;
	}
	public List<DiagnosticStudy> getDiagnosticStudyList()
	{
		return diagnosticStudyList;
	}
	public void setDiagnosticStudyList(List<DiagnosticStudy> diagnosticStudyList)
	{
		this.diagnosticStudyList = diagnosticStudyList;
	}
	
	public List<FunctionalStatus> getFunctionalStatus() {
		return functionalStatus;
	}
	public void setFunctionalStatus(List<FunctionalStatus> functionalStatus) {
		this.functionalStatus = functionalStatus;
	}
	public List<LabTest> getLabTestList()
	{
		return labTestList;
	}
	public void setLabTestList(List<LabTest> labTestList)
	{
		this.labTestList = labTestList;
	}
	public List<Communication> getCommunications()
	{
		return communications;
	}
	public void setCommunications(List<Communication> communications)
	{
		this.communications = communications;
	}	
	public List<Immunization> getImmunizationList()
	{
		return immunizationList;
	}
	public void setImmunizationList(List<Immunization> immunizationList)
	{
		this.immunizationList = immunizationList;
	}
	public List<QDM> getTobaccoStatusList()
	{
		return tobaccoStatusList;
	}
	public void setTobaccoStatusList(List<QDM> tobaccoStatusList)
	{
		this.tobaccoStatusList = tobaccoStatusList;
	}
	public List<ActiveMedication> getActiveMedicationsList()
	{
		return activeMedicationsList;
	}
	public void setActiveMedicationsList(List<ActiveMedication> activeMedicationsList)
	{
		this.activeMedicationsList = activeMedicationsList;
	}
	public List<ProcedureIntolerance> getProcedureIntoleranceList()
	{
		return procedureIntoleranceList;
	}
	public void setProcedureIntoleranceList(List<ProcedureIntolerance> procedureIntoleranceList)
	{
		this.procedureIntoleranceList = procedureIntoleranceList;
	}
	public List<ImmunizationIntolerance> getImmunizationIntoleranceList()
	{
		return immunizationIntoleranceList;
	}
	public void setImmunizationIntoleranceList(List<ImmunizationIntolerance> immunizationIntoleranceList)
	{
		this.immunizationIntoleranceList = immunizationIntoleranceList;
	}
	public List<Claim> getClaims() {
		return claims;
	}
	public void setClaims(List<Claim> claims) {
		this.claims = claims;
	}
	
	public void sort() throws Exception
	{
		Collections.sort(this.getEncounterList());
		Collections.sort(this.getDxList());
		Collections.sort(this.getRiskAssessmentList());
		Collections.sort(this.getProcedureList());
		Collections.sort(this.getInterventionList());
		Collections.sort(this.getMedicationOrderList());
		Collections.sort(this.getPhysicalExamList());
		Collections.sort(this.getDiagnosticStudyList());
		Collections.sort(this.getLabTestList());
		Collections.sort(this.getCommunications());
		Collections.sort(this.getImmunizationList());
		Collections.sort(this.getTobaccoStatusList());
		Collections.sort(this.getActiveMedicationsList());
		Collections.sort(this.getProcedureIntoleranceList());
		Collections.sort(this.getImmunizationIntoleranceList());
		Collections.sort(this.getClaims());
	}
	
}
