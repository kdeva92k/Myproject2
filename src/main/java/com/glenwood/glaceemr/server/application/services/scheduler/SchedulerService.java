package com.glenwood.glaceemr.server.application.services.scheduler;

import java.util.Date;
import java.util.List;

import com.glenwood.glaceemr.server.application.models.Appointment;
import com.glenwood.glaceemr.server.application.models.SchedulerAppointmentBean;
import com.glenwood.glaceemr.server.application.models.SchedulerResource;
import com.glenwood.glaceemr.server.application.models.SchedulerResourceCategory;

public interface SchedulerService {

	List<SchedulerResource> getResources();

	List<SchedulerAppointmentBean> getAppointments(Date apptDate, Integer[] resourceId);

	List<SchedulerResourceCategory> getResourceCategories();

	String getDefaultResource(String userId);

	Object getTemplates(int userId, Date date);

	Appointment createAppointment(Appointment appointmentDataBean);

	List<Object> getApptBookLocationList();

	List<Object> getApptTypes();

}
