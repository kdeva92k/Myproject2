package com.glenwood.glaceemr.server.application.services.audittrail;

public interface AuditTrailEnumConstants {
	/* Log Module */
	public enum LogModuleType {

		SEARCH(1, "Search"), SECURITY(2, "Security"), DOCUMENT(3, "Document"), REPORT(4, "Report"), SYSTEM(5, "System"), PATIENT_REGISTRATION(6, "PatientRegistration"), EMPLOYEE_REGISTRATION(7, "EmployeeRegistration"), CONFIGURATION(8,
				"Configuration"), FINANCE_STATEMENT(9, "Finance/Statement"), FINANCE_CHARGE(10, "Finance/Charge"), FINANCE_AR(11, "Finance/AR"), FINANCE_PAYMENT(12, "Finance/Payment"), HMR(13, "HMR"), FINANCE_CLAIM(14, "Finance/Claim"), FINANCE_PAYER(
				15, "Finance/Payer"), IMMUNIZATIONREGISTRY(16, "ImmunizationRegistry"), PATIENTPORTAL(17, "PatientPortal"), FAX(18, "Fax"), GLACEMESSAGING_TEXT(19, "GlaceMessaging/Text"), GLACEMESSAGING_VOICE(20, "GlaceMessaging/Voice"), GLACEMESSAGING(
				21, "GlaceMessaging"), GLACEMESSAGING_EMAIL(22, "GlaceMessaging/Email"), QUALITY_REPORTING(23, "QualityReporting"), CDS(24, "CDS"), LABINTERFACE(25, "LabInterface"), FINANCE_CLEARINGHOUSE(25, "Finance/Clearinghouse"), CHART(26,
				"Chart"), PRESCRIPTIONS(27, "Prescriptions"), IMMUNIZATION(28, "Immunization"), FINANCE_ELIGIBILITY(29, "Finance/Eligibility"), OPENPROBLEM(30, "OpenProblem"), DASHBOARD(31, "Dashboard"), CONSENTFORM(32, "ConsentForm"), SCHEDULER(33,
				"Scheduler"), INVESTIGATION(34, "Investigation"), REFERRAL(35, "Referral"), FLOWSHEET(36, "Flowsheet"), INVENTORY(37, "Inventory"), ENCOUNTER(38, "Encounter"), MESSAGE(39, "Message"), DME(40, "DME"), DIRECT(41, "Direct"), GLACEMESSAGING_LETTER(
				42, "GlaceMessaging/Letter"), ASSESSMENT(43, "Assessment"), AMENDMENT(44, "Amendment"), ALLERGY(45, "Allergy"), PATIENT_EDUCATION(46, "PatientEducation"), TEMPLATE(47, "Template"), LoginAndLogOut(48, "Login/LogOut"), ALERTS(49, "Alerts"),SUPERBILL(50,"SuperBill"), ROOMSTATUS(51,"RoomStatus"),CoumadinFlowSheet(52,"CoumadinFlowSheet"),
				THERAPHYSESSION(53,"TheraphySession"), SKINTESTINGFORMS(54,"SkinTestingForms"), PHYSICALEXAMINATION(55,"PhysicalExamination"),
				ROS(56,"ReviewOfSystems"),VITALS(57,"Vitals"),HISTORY(58,"History"),ADMISSION(59,"Admission"),SHORTCUTS(60,"QuickShortcuts"),PRINTINGANDREPORTING(61,"PrintingAndReporting"),PASTENCOUNTERS(62,"PastEncounters"),FOCALSHORTCUTS(63,"FocalShortcuts"),LegacyRequest(64,"LegacyRequest"),MUBatchPerformance(65,"MUBatchPerformance"),MU(66,"MU"),PQRS(66,"PQRS"),CDAReconcile(67,"CDAReconcile");
		// semicolon needed when fields / methods follow

		public final int Value;
		private final String desc;

		LogModuleType(int value, String desc) {
			this.Value = value;
			this.desc = desc;
		}

		public int getValue() {
			return this.Value;
		}

		public String getDesc() {
			return this.desc;
		}

		public static String nameOf(int value) {
			String entKey = "N/A";
			for (LogModuleType status : LogModuleType.values()) {
				if (status.getValue() == value)
					entKey = status.desc;
			}
			return entKey;
		}

	}

	/* Action Type */
	public enum LogActionType {
		LOGIN(1, "Login"), LOGOUT(2, "Logout"), CREATE(3, "Create"), UPDATE(4, "Update"), VIEW(5, "View"), DELETE(6, "Delete"), START(7, "Start"), STOP(8, "Stop"), CLOSE(9, "Close"), FINISH(10, "Finish"), CREATEORUPDATE(11, "Create or Update"), SEND(
				12, "Send"), RECEIVE(13, "Receive"), CANCEL(14, "Cancel"), BULKCANCEL(15, "BulkCancel"), DOWNLOAD(16, "Download"), EXPORT(17, "Export"), PRINT(18, "Print"), SEARCH(19, "Search"), BULKDELETE(20, "Bulkdelete"), ATTACH(21, "Attach"), MOVE(
				22, "Move"), FORWARD(23, "Forward"), GENERATE(24, "Generate"), READ(25, "Read"), UNREAD(26, "Unread"), RESEND(27, "Resend"), REOPEN(28, "Reopen"), SIGNUP(29, "Signup");
		// semicolon needed when fields / methods follow

		public final int Value;
		private final String desc;

		LogActionType(int value, String desc) {
			this.Value = value;
			this.desc = desc;
		}

		public int getValue() {
			return this.Value;
		}

		public String getDesc() {
			return this.desc;
		}

		public static String nameOf(int value) {
			String entKey = "N/A";
			for (LogActionType status : LogActionType.values()) {
				if (status.getValue() == value)
					entKey = status.desc;
			}
			return entKey;
		}

	}

	/* Log Outcome */
	public enum Log_Outcome {
		SUCCESS("S", "Success"), FAILURE("F", "Failure"), EXCEPTION("E", "Exception"), INTERMEDIATE_UNKNOWN("I", "Intermediate or unknown"), SECURITY_VIOLATION("X", "Security Violation")

		;
		// semicolon needed when fields / methods follow
		private final String Value;
		private final String desc;

		Log_Outcome(String Value, String desc) {
			this.Value = Value;
			this.desc = desc;
		}

		public String getValue() {
			return this.Value;
		}

		public String getDesc() {
			return this.desc;
		}

		public static String nameOf(String value) {
			String entKey = "N/A";
			for (Log_Outcome status : Log_Outcome.values()) {
				if (status.getValue().equalsIgnoreCase(value))
					entKey = status.desc;
			}
			return entKey;
		}

	}

	/* Login User */
	public enum LogUserType {
		USER_LOGIN(1, "User Login"), PATIENT_LOGIN(2, "Patient Login"),GLACE_BATCH(3, "Glace Batch");
		// semicolon needed when fields / methods follow
		public final int Value;
		private final String desc;

		LogUserType(int value, String desc) {
			this.Value = value;
			this.desc = desc;
		}

		public int getValue() {
			return this.Value;
		}

		public String getDesc() {
			return desc;
		}

		public static String nameOf(int value) {
			String entKey = "N/A";
			for (LogUserType status : LogUserType.values()) {
				if (status.getValue() == value)
					entKey = status.desc;
			}
			return entKey;
		}
	}

	/* Log Type */
	public enum LogType {
		GLACE_LOG(1, "Glace log"), SECURITY_LOG(2, "Security log"), AUDIT_LOG(3, "Audit log");
		// semicolon needed when fields / methods follow
		public final int Value;
		private final String desc;

		LogType(int value, String desc) {
			this.Value = value;
			this.desc = desc;
		}

		public int getValue() {
			return this.Value;
		}

		public String getDesc() {
			return this.desc;
		}

		public static String nameOf(int value) {
			String entKey = "N/A";
			for (LogType status : LogType.values()) {
				if (status.getValue() == value)
					entKey = status.desc;
			}
			return entKey;
		}
	}

	/* Relavant_Id Type */
	public enum Log_Relavant_Id {
		/* Employee Registration */
		LOGINID("LoginID"), GROUPID("GroupID"),
		/* CNM */
		ENCOUNTER_ID("EncounterID"), PATIENT_ID("PatientID"), TEMPLATE_ID("TemplateID"),

		/* Lab results */
		RESULT_ID("ResultID"), DOCUMENT_ID("DocumentID"), ACCOUNTID("AccountID"), LOGINNAME("LoginName");

		// semicolon needed when fields / methods follow
		public final String Value;

		Log_Relavant_Id(String value) {
			this.Value = value;

		}

		public String getValue() {
			return this.Value;
		}

	}

}
