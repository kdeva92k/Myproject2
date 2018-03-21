package com.glenwood.glaceemr.server.application.services.audittrail;

import java.util.Vector;

import javax.servlet.http.HttpServletRequest;

import com.glenwood.glaceemr.server.utils.GlaceMonitoringParameters;


public interface AuditTrailService {
	public int LogEvent(int Log_Type,int Log_Component,int Event_Type,int parent_Event,int Event_Outcome,String Description,int User_Id,String SystemIP,String ClientIP,int patientId,int chartId,int encounterId,int LoginType,HttpServletRequest request);
	public int LogEvent(int Log_Type,int Log_Component,int Event_Type,int parent_Event,int Event_Outcome,String Description,int User_Id,String SystemIP,String ClientIP,int patientId,int chartId,int encounterId,int LoginType,HttpServletRequest request,String newDescription);
	public int LogEventExternal(int Log_Type,int Log_Component,int Event_Type,int parent_Event,int Event_Outcome,String Description,int User_Id,String SystemIP,String ClientIP,int patientId,int chartId,int encounterId,int LoginType,HttpServletRequest request,String newDescription);
	public int LogEvent(int Log_Type,int Log_Component,int Event_Type,int parent_Event,int Event_Outcome,String Description,int User_Id,String SystemIP,String ClientIP,int patientId,int chartId,int encounterId,int LoginType,String newDescription,Vector<Object> logmodules,String parent_event_session);
	public int LogEvent(int Log_Type,int Log_Component,int Event_Type,int parent_Event,int Event_Outcome,String Description,int User_Id,String SystemIP,String ClientIP,int patientId,int chartId,int encounterId,int LoginType,String newDescription,Vector<Object> logmodules,String parent_event_session,HttpServletRequest request);
	public int LogEvent(int Log_Type,int Log_Component,int Event_Type,int parent_Event,int Event_Outcome,String Description,int User_Id,String SystemIP,String ClientIP,int patientId,int chartId,int encounterId,int LoginType, int Event_Id, HttpServletRequest request);
	public int LogSession(int parent_Event,int Outcome,String description,String SystemIP,String ClientIP,HttpServletRequest request);
	
	public int LogEvent(int Log_Type,int Log_Component,int Event_Type,int parent_Event,int Event_Outcome,String Description,int User_Id,String ClientIP,int patientId,int chartId,int encounterId,int LoginType);
    public GlaceMonitoringParameters getServerMonitorResults();
	
}
