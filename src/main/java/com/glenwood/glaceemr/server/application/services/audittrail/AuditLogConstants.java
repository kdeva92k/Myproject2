package com.glenwood.glaceemr.server.application.services.audittrail;

public interface AuditLogConstants{

	/* Log Type */
	public static final int GLACE_LOG=1;
	public static final int SECURITY_LOG=2;
	public static final int AUDIT_LOG=3;
	
	/* Log Outcome*/
	public static final int FAILURE=0;
	public static final int SUCCESS=1;
	public static final int ERROR=2;	
	
	/* Event Type*/
	
	public static final int LOGIN=1;
	public static final int LOGOUT=2;
	public static final int CREATED=3;
	public static final int UPDATE=4;
	public static final int VIEWED=5;
	public static final int DELETED=6;	
	public static final int START=7;
	public static final int STOP=8;
	public static final int CLOSED=9;
	public static final int FINISHED=13;
	public static final int CREATEORUPDATE=10;
	public static final int SENT=11;
	public static final int RECEIVED=12;
	public static final int CANCELED=16;
	public static final int DOWNLOADED =17;
	public static final int EXPORT =18;
	public static final int PRINT =19;
	public static final int SEARCH =20;
	/* Login User*/
	public static final int USER_LOGIN=1;	
	public static final int PATIENT_LOGIN=2;
	
	/* Log Component*/
	public static final int LoginAndLogOut = 1;
	public static final int Chart = 2;
	public static final int Encounter = 3;
	public static final int Investigations = 4;
	public static final int AuditFunction = 5;
	
	public static final int Leaf = 7;
	public static final int Prescriptions = 8;
	public static final int ScansAndFiles = 9;
	public static final int PrintingAndReporting = 10;
	public static final int Fax = 11;
	public static final int Vaccination = 12;	
	
	public static final int EmployeeLogin = 14;
	public static final int Security_Admin=15;
	public static final int Hardware_Error=16;
	public static final int Assessment=17;	

	public static final int PatientRegistration=19;
	public static final int Service=20;
	public static final int Configurations_Pages=21;
	public static final int Bills=23;
	public static final int OpenProblem=24;
	public static final int Ar_Analysis=25;
	public static final int Denial_Processing=26;
	public static final int No_Response=27;
	public static final int DepositManagement=28;
	public static final int EOBPosting=29;	
	public static final int Reports=30;
	public static final int Purge=31;
	public static final int CLAIMS=32;
	public static final int Revert_Services=33;
	public static final int HL7_Lab_Results=34;
	public static final int HMR=35;
	public static final int Queue=36;
	public static final int EPrescription=37;
	public static final int AnsiEOB=38;
	public static final int InsuraceMerge=39;
	
	public static final int ClaimsErrorReport = 41;
	
	public static final int Pharmacy = 43;
	public static final int ActiveMedication = 44;
	public static final int CurrentMedication = 45;
	
	public static final int Search=46;
	
	public static final int Allergies=72;
	
	public static final int ConcurrentAccess=78;
	public static final int Scheduler=79;
	public static final int Orderset=80;
	public static final int Session=81;
	public static final int SchedulerReport=82;
	public static final int IngenixPayorid=83;
	public static final int AnsiClaimConfig=84;
	public static final int PasswordManagement=85;
	public static final int ENSStatement=86;
	public static final int ImportPatientDemographics=87;
	public static final int ERA=88;
	public static final int REFUND=89;
	
	public static final int InsuranceEligibility=95;
	
	public static final int DenialManagement=114;
	public static final int EmergencyAccess=115;
	
	public static final int ARAnalysis=123;
	public static final int Inventory=124;
	public static final int GlaceValidations=125;
	public static final int Flowsheet=126;
	public static final int CollectionCenter=127;
	public static final int Handouts=128;
	public static final int HIERegistry=129;
	public static final int PatientPortal=131;
	public static final int Referral=132;
	public static final int AlertsConfiguration=133;
	public static final int DirectMessage=134;

}