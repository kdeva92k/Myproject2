package com.glenwood.glaceemr.server.application.services.chart.Allergies;

import java.lang.reflect.Field;

public class CodeSystem{
	public final static String LOINC				=	"2.16.840.1.113883.6.1";
	public final static String ConfidentialityCode	=	"2.16.840.1.113883.5.25";
	public final static String SNOMED				=	"2.16.840.1.113883.6.96";
	public final static String NDC					=	"2.16.840.1.113883.6.69";
	public final static String RXNORM				=	"2.16.840.1.113883.6.88";
	public static final String ActCode 				= 	"2.16.840.1.113883.5.4";
	public static final String ConcernAct 			= 	"2.16.840.1.113883.5.6";
	public static final String ICD_9CM 				= 	"2.16.840.1.113883.6.104";
	public static final String ObservationInterpretation 	= 	"2.16.840.1.113883.5.83";
	public static final String ActPriority 			= 	"2.16.840.1.113883.5.7";
	public static final String RouteOfAdministration= 	"2.16.840.1.113883.5.112";
	public static final String AdministrativeGender = 	"2.16.840.1.113883.5.1";
	public static final String RoleClassRelationship = 	"2.16.840.1.113883.5.110";
	public static final String RoleCode 			= 	"2.16.840.1.113883.5.111";
	public static final String RxBinNo 				= 	"2.16.840.1.113883.19";
	public static final String interpretationCode 	= 	"2.16.840.1.113883.5.83";
	public static final String CPT_4 				= 	"2.16.840.1.113883.6.12";
	public static final String UNII					=	"2.16.840.1.113883.4.9";
	public static final String HCPCS			="2.16.840.1.113883.6.285";
	public static final String ADMINISTRATIVESEX="2.16.840.1.113883.12.1";							   
	public static final String CVX				="2.16.840.1.113883.6.59";
	public static final String SNOMEDCT			="2.16.840.1.113883.6.96";
	public static final String CPT				="2.16.840.1.113883.6.12";
	public static final String DISCHARGEDISPOSITION="2.16.840.1.113883.12.112";
	public static final String CDCREC			="2.16.840.1.113883.6.238";
	public static final String SOP				="2.16.840.1.113883.3.221.5";
	public static final String ICD9DIAGNOSIS	="2.16.840.1.113883.6.103";
	public static final String ICD9PROCEDURE	="2.16.840.1.113883.6.104";
	public static final String ICD10PCS			="2.16.840.1.113883.6.4";
	public static final String ICD10CM			="2.16.840.1.113883.6.90";
	public static final String HSLOC			="2.16.840.1.113883.6.259";
	public static final String Vaccines_administered_CVX="2.16.840.1.113883.12.292";
	public static final String NAACCRBehaviorCode = "2.16.840.1.113883.3.520.3.14";
	public static final String NAACCRdiagnosticconfirmation = "2.16.840.1.113883.3.520.3.3";
	public static final String NAACCRlateralitycode = "2.16.840.1.113883.3.520.3.1";
	public static final String TNMclinicalstagedescriptor = "2.16.840.1.113883.3.520.3.10";
	public static final String TNMEdition		= "2.16.840.1.113883.3.520.3.5";
	public static final String Tumorobservation				= "2.16.840.1.113883.3.520.3.6";
	public static final String Nodesobservation				= "2.16.840.1.113883.3.520.3.7";
	public static final String Metastasesobservation		= "2.16.840.1.113883.3.520.3.8";
	public static final String TNMClinicalStageGroup		= "2.16.840.1.113883.15.6";
	public static final String StageclinicalCancerobservation = "2.16.840.1.113883.3.520.3.4";
	public static final String ParticipationFunction 	= 	"2.16.840.1.113883.5.88";
	public static final String ObservationMethod		=	"2.16.840.1.113883.5.84";
	public static final String ObservationValue			=	"2.16.840.1.113883.5.1063";
	public static final String CoverageRoleClassRelationship = "2.16.840.1.113883.6.255.1336";
	public static final String nqfOID						 = "2.16.840.1.113883.3.560.1";
	public static final String authoringToolId				 = "2.16.840.1.113883.3.560.101.2";
	public static final String OccupationCodes               = "2.16.840.1.113883.6.240";
	public static final String IndustryCodes                 = "2.16.840.1.113883.6.240";
	public static final String NPICodes						 = "2.16.840.1.113883.4.6";
	public static final String NUCCCodes				     = "2.16.840.1.113883.6.101";
	public static final String GlenwoodSystemsOID			 = "2.16.840.1.113883.3.225";
	
	public static String getCodeSystemName(String code){
		if(code==null)
			return null;
		 Field[] fields = CodeSystem.class.getDeclaredFields();
		 for (int i = 0; i < fields.length; i++) {
			 try {
				if(fields[i].get(new String()).equals(code))
					 return fields[i].getName();
			} catch (IllegalArgumentException | IllegalAccessException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		 }
		return "";
	}
	public static String getSeverityName(String SeverityName){
		if(SeverityName.equals("1"))
			return  "Mild to moderate";
		if(SeverityName.equals("2"))
			return  "Moderate";
		if(SeverityName.equals("3"))
			return  "Severe";
		return "NA";
		
		
		
	}

}
