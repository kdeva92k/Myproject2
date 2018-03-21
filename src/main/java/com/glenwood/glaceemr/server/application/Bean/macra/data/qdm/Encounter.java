package com.glenwood.glaceemr.server.application.Bean.macra.data.qdm;


public class Encounter extends QDM
{

	private String principalDiagnosisCode = new String();
	private String principalDiagnosisCodeSystemOID = new String();
	
	public String getPrincipalDiagnosisCode() {
		return principalDiagnosisCode;
	}
	public void setPrincipalDiagnosisCode(String principalDiagnosisCode) {
		this.principalDiagnosisCode = principalDiagnosisCode;
	}
	public String getPrincipalDiagnosisCodeSystemOID() {
		return principalDiagnosisCodeSystemOID;
	}
	public void setPrincipalDiagnosisCodeSystemOID(String principalDiagnosisCodeSystemOID) {
		this.principalDiagnosisCodeSystemOID = principalDiagnosisCodeSystemOID;
	}
	


}
