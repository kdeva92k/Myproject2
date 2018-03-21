package com.glenwood.glaceemr.server.application.services.audittrail;

import java.util.ArrayList;

import com.glenwood.glaceemr.server.application.services.audittrail.AuditTrailEnumConstants.LogActionType;
import com.glenwood.glaceemr.server.application.services.audittrail.AuditTrailEnumConstants.LogModuleType;
import com.glenwood.glaceemr.server.application.services.audittrail.AuditTrailEnumConstants.LogType;
import com.glenwood.glaceemr.server.application.services.audittrail.AuditTrailEnumConstants.LogUserType;
import com.glenwood.glaceemr.server.application.services.audittrail.AuditTrailEnumConstants.Log_Outcome;

public interface AuditTrailSaveService {

	public int logDataBackup(int logId, ArrayList<Object> data);

	public int logException(int logId, LogType LogType, LogModuleType LogModuleType, LogActionType LogActionType, int Parent_id, Log_Outcome Log_Outcome, String Description, int User_Id, LogUserType LogUserType, Exception e);

	public int LogEvent(int logId, LogType LogType, LogModuleType LogModuleType, LogActionType LogActionType, int Parent_id, Log_Outcome Log_Outcome, String Description, int User_Id, String ClientIP, int patientId, String relevantIds,
			LogUserType LogUserType, String PHI_Description, String Raw_Data);

	public int LogEvent(LogType LogType, LogModuleType LogModuleType, LogActionType LogActionType, int Parent_id, Log_Outcome Log_Outcome, String Description, int User_Id, String ClientIP, int patientId, String relevantIds, LogUserType LogUserType,
			String PHI_Description, String Raw_Data);
}
