package com.glenwood.glaceemr.server.application.services.audittrail;

import java.io.File;
import java.io.IOException;
import java.lang.management.ManagementFactory;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Vector;

import javax.persistence.Query;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Service;

import com.glenwood.glaceemr.server.utils.GlaceMonitoringParameters;

@Service
public class AuditTrailServiceImpl implements AuditTrailService{


	@PersistenceContext
	EntityManager em ;
	long timeDiff=0;

	GlaceMonitoringParameters monitoringParams;
	@Override
	public int LogEvent(int Log_Type, int Log_Component, int Event_Type,
			int parent_Event, int Event_Outcome, String Description,
			int User_Id, String SystemIP, String ClientIP, int patientId,
			int chartId, int encounterId, int LoginType,
			HttpServletRequest request) {
 
		return 0;
	}

	@Override
	public int LogEvent(int Log_Type, int Log_Component, int Event_Type,
			int parent_Event, int Event_Outcome, String Description,
			int User_Id, String SystemIP, String ClientIP, int patientId,
			int chartId, int encounterId, int LoginType,
			HttpServletRequest request, String newDescription) {


		return 0;
	}

	@Override
	public int LogEventExternal(int Log_Type, int Log_Component,
			int Event_Type, int parent_Event, int Event_Outcome,
			String Description, int User_Id, String SystemIP, String ClientIP,
			int patientId, int chartId, int encounterId, int LoginType,
			HttpServletRequest request, String newDescription) {
		return 0;
	}

	@Override
	public int LogEvent(int Log_Type, int Log_Component, int Event_Type,
			int parent_Event, int Event_Outcome, String Description,
			int User_Id, String SystemIP, String ClientIP, int patientId,
			int chartId, int encounterId, int LoginType, String newDescription,
			Vector<Object> logmodules, String parent_event_session) {

		return 0;
	}

	@Override
	public int LogEvent(int Log_Type, int Log_Component, int Event_Type,
			int parent_Event, int Event_Outcome, String Description,
			int User_Id, String SystemIP, String ClientIP, int patientId,
			int chartId, int encounterId, int LoginType, String newDescription,
			Vector<Object> logmodules, String parent_event_session,
			HttpServletRequest request) {
		return 0;
	}

	@Override
	public int LogEvent(int Log_Type, int Log_Component, int Event_Type,
			int parent_Event, int Event_Outcome, String Description,
			int User_Id, String SystemIP, String ClientIP, int patientId,
			int chartId, int encounterId, int LoginType, int Event_Id,
			HttpServletRequest request) {
		return 0;
	}

	@Override
	public int LogSession(int parent_Event, int Outcome, String description,
			String SystemIP, String ClientIP, HttpServletRequest request) {
		return 0;
	}

	@Override
	public int LogEvent(int Log_Type, int Log_Component, int Event_Type,
			int parent_Event, int Event_Outcome, String Description,
			int User_Id, String ClientIP, int patientId, int chartId,
			int encounterId, int LoginType) {
		return 0;
	}


	@Override
	public GlaceMonitoringParameters getServerMonitorResults() {
		monitoringParams = new GlaceMonitoringParameters();

		getSharedFolderUsableSpace();
		dbFreeSpace();
		getServerFolderSpace();
		getJVMpace();
		getDBRuntime();
		return monitoringParams;
	}

	void getSharedFolderUsableSpace(){
		try{
			//         String sharePath = settingsRepository.findOne("SHARED_FOLDER_PATH").getSettingsValue();
			String sharePath = "/tmp";
			File sharedFolderPath = new File(sharePath);
			if (sharedFolderPath.exists()) {
				long usableSharedFolderSpace = (sharedFolderPath
						.getUsableSpace()) / 1024 / 1024 / 1024;
				monitoringParams.setSHFS(sharedFolderPath.getUsableSpace());
				File sampleTestFile = new File(sharePath
						+ "/MonitorGlace.txt");
				sampleTestFile.createNewFile();
				if (sampleTestFile.exists()) {
					monitoringParams.setShared(1); ;
					sampleTestFile.setWritable(true);
					sampleTestFile.delete();
				} else {
					monitoringParams.setShared(0);
				}

				if (usableSharedFolderSpace > 1) {
					monitoringParams.setSHSP(1);
				} else {
					monitoringParams.setSHSP(0) ;
				}
			}
		}catch(IOException ioex){
			monitoringParams.setShared(-1);
			monitoringParams.setSHSP(-1) ;
			monitoringParams.setSHFS(-1);
		}
	}

	void dbFreeSpace(){
		File dbPartitionPath = new File("/var/database/");
		if (dbPartitionPath.exists()) {
			long usableDBSpace = (dbPartitionPath.getUsableSpace()) / 1024 / 1024 / 1024;
			monitoringParams.setDBFS( dbPartitionPath.getUsableSpace());
			if (usableDBSpace > 1) {
				monitoringParams.setDBSP(1);
			} else {
				monitoringParams.setDBSP(0);
			}
		} else {
			monitoringParams.setDBSP(-1);
		}
	}

	void getServerFolderSpace(){
		/*
         Temp folder free space
		 */
		try {
			File tempFolder = new File("/tmp/");
			if (tempFolder.exists()) {
				monitoringParams.setTFS(tempFolder.getUsableSpace());
			}
		} catch (Exception e) {
			System.out
			.println("Exception in accessing temp folder space-->"
					+ e.getMessage());
		}
		try {
			File rootFolder = new File("/");
			if (rootFolder.exists()) {
				monitoringParams.setRFS(rootFolder.getUsableSpace());
			}
		} catch (Exception ex) {
			System.out
			.println("Exception in accessing root folder space-->"
					+ ex.getMessage());
		}

		/*
          Var folder free space
		 */
		try {
			File tempFolder = new File("/var/");
			if (tempFolder.exists()) {
				monitoringParams.setVARFS (tempFolder.getUsableSpace());
			}
		} catch (Exception e) {
			System.out
			.println("Exception in accessing var folder space-->"
					+ e.getMessage());
		}

		/*
          Usr folder free space
		 */
		try {
			File tempFolder = new File("/usr/");
			if (tempFolder.exists()) {
				monitoringParams.setUSRFS ( tempFolder.getUsableSpace());
			}
		} catch (Exception e) {
			System.out
			.println("Exception in accessing usr folder space-->"
					+ e.getMessage());
		}

	}
	@SuppressWarnings("restriction")
	void getJVMpace(){
		try {
			monitoringParams.setFJVM ( Runtime.getRuntime().maxMemory());
		} catch (Exception ex) {
			System.out.println("Exception in obtaining jvm space-->"
					+ ex.getMessage());
		}

		try {
			monitoringParams.setFMEM( ((com.sun.management.OperatingSystemMXBean) ManagementFactory
					.getOperatingSystemMXBean())
					.getFreePhysicalMemorySize());
		} catch (Exception ex) {
			System.out.println("Exception in free RAM space-->"
					+ ex.getMessage());
		}

	}
	void getDBRuntime(){
		int DB=0;

		float DBRT=(float) 0.0;
		try{
			DB=1;
		    DBRT=1	;	
			monitoringParams.setDB(DB);
			monitoringParams.setDBRT(DBRT);
			monitoringParams.setDBFS(monitoringParams.getRFS());
			/*
			
			String totalTime = "0.0";
			Calendar calendar = Calendar.getInstance();
			Date date1 = calendar.getTime();
			Query query = em.createNativeQuery("SELECT login_users_username FROM login_users WHERE login_users_username = 'demodoctor' limit 1");
			List result= query.getResultList();

			if(result!=null){
						DB=1;
						Calendar calendar1 = Calendar.getInstance();
						Date date2 = calendar1.getTime();
						timeDiff = (date2.getTime() - date1.getTime()) ;
 			}
			else{  
				DB=0;
			}
			
		
			DBRT =timeDiff;
			

		*/}catch(Exception e)
		{
			e.printStackTrace();
		}/*finally
		{
		
			try{
			monitoringParams.setDB(DB);
			monitoringParams.setDBRT(DBRT);
			monitoringParams.setDBFS(monitoringParams.getRFS());
			
			}catch(Exception e)
			{
				e.printStackTrace();
			}
			
		}*/
		
		
	}

}