package com.glenwood.glaceemr.server.application.specifications;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.JoinType;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;

import com.glenwood.glaceemr.server.application.models.AppReferenceValues;
import com.glenwood.glaceemr.server.application.models.AppReferenceValues_;
import com.glenwood.glaceemr.server.application.models.PortalApptRequest;
import com.glenwood.glaceemr.server.application.models.PortalApptRequest_;
import com.glenwood.glaceemr.server.application.models.PosTable;
import com.glenwood.glaceemr.server.application.models.PosTable_;
import com.glenwood.glaceemr.server.application.models.SchedulerAppointment_;
import com.glenwood.glaceemr.server.application.models.SchedulerAppointment;
import com.glenwood.glaceemr.server.application.models.SchedulerDateTemplate;
import com.glenwood.glaceemr.server.application.models.SchedulerDateTemplate_;
import com.glenwood.glaceemr.server.application.models.SchedulerLock;
import com.glenwood.glaceemr.server.application.models.SchedulerLock_;
import com.glenwood.glaceemr.server.application.models.SchedulerResourceCategory_;
import com.glenwood.glaceemr.server.application.models.SchedulerResourceCategory;
import com.glenwood.glaceemr.server.application.models.SchedulerResource_;
import com.glenwood.glaceemr.server.application.models.SchedulerResource;
import com.glenwood.glaceemr.server.application.models.SchedulerTemplate;
import com.glenwood.glaceemr.server.application.models.SchedulerTemplateDetail;
import com.glenwood.glaceemr.server.application.models.SchedulerTemplateDetail_;
import com.glenwood.glaceemr.server.application.models.SchedulerTemplateOwnerMapping;
import com.glenwood.glaceemr.server.application.models.SchedulerTemplateOwnerMapping_;
import com.glenwood.glaceemr.server.application.models.SchedulerTemplateTimeMapping;
import com.glenwood.glaceemr.server.application.models.SchedulerTemplateTimeMapping_;
import com.glenwood.glaceemr.server.application.models.SchedulerTemplate_;

public class PortalAppointmentsSpecification {

	/**
	 * @param patientId	: used to get appointments list of a patient of that particular id
	 * @param appointmentsType : type of the appointment (Future,Past,Present)
	 * @return BooleanExpression is a  predicate  
	 */	
	public static Specification<SchedulerAppointment> getPatientFutureAppointmentsByDate(final int patientId)
	{
		return new Specification<SchedulerAppointment>() {

			@Override
			public Predicate toPredicate(Root<SchedulerAppointment> root,
					CriteriaQuery<?> cq, CriteriaBuilder cb) {	

				DateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");
				Date today = new Date();
				System.out.println(dateFormat.format(today));
				try {
					today = dateFormat.parse(dateFormat.format(today));
				} catch (ParseException e) {

					e.printStackTrace();
				}
				Predicate apptTypePredicate;
				apptTypePredicate=cb.greaterThanOrEqualTo(root.get(SchedulerAppointment_.schApptDate), today);
				
				if(Long.class!=cq.getResultType()){
					root.fetch(SchedulerAppointment_.schResProvider, JoinType.LEFT);
					root.fetch(SchedulerAppointment_.schResLoc, JoinType.LEFT);
				}
				
				Predicate requestedAppts=cq.where(cb.equal(root.get(SchedulerAppointment_.schApptPatientId), patientId),apptTypePredicate).getRestriction();
				return requestedAppts;
			}

		};
	}

	/**
	 * @param patientId	: used to get appointments list of a patient of that particular id
	 * @param appointmentsType : type of the appointment (Future,Past,Present)
	 * @return BooleanExpression is a  predicate  
	 */	
	public static Specification<SchedulerAppointment> getPatientPastAppointmentsByDate(final int patientId)
	{
		return new Specification<SchedulerAppointment>() {

			@Override
			public Predicate toPredicate(Root<SchedulerAppointment> root,
					CriteriaQuery<?> cq, CriteriaBuilder cb) {	

				DateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");
				Date today = new Date();
				System.out.println(dateFormat.format(today));
				try {
					today = dateFormat.parse(dateFormat.format(today));
				} catch (ParseException e) {

					e.printStackTrace();
				}
				Predicate apptTypePredicate;
				apptTypePredicate=cb.lessThan(root.get(SchedulerAppointment_.schApptDate), today);
				
				if(Long.class!=cq.getResultType()){
					root.fetch(SchedulerAppointment_.schResProvider, JoinType.LEFT);
					root.fetch(SchedulerAppointment_.schResLoc, JoinType.LEFT);
				}
				
				Predicate requestedAppts=cq.where(cb.equal(root.get(SchedulerAppointment_.schApptPatientId), patientId),apptTypePredicate).getRestriction();
				return requestedAppts;
			}

		};
	}


	/**
	 * @param patientId	: used to get appointments list of a patient of that particular id
	 * @param appointmentsType : type of the appointment (Future,Past,Present)
	 * @return BooleanExpression is a  predicate  
	 */	
	public static Specification<SchedulerAppointment> getPatientTodaysAppointmentsByDate(final int patientId)
	{
		return new Specification<SchedulerAppointment>() {

			@Override
			public Predicate toPredicate(Root<SchedulerAppointment> root,
					CriteriaQuery<?> cq, CriteriaBuilder cb) {				

				DateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");
				Date today = new Date();
				System.out.println(dateFormat.format(today));
				try {
					today = dateFormat.parse(dateFormat.format(today));
				} catch (ParseException e) {

					e.printStackTrace();
				}
				Predicate apptTypePredicate;
				apptTypePredicate=cb.equal(root.get(SchedulerAppointment_.schApptDate), today);
				
				if(Long.class!=cq.getResultType()){
					root.fetch(SchedulerAppointment_.schResProvider, JoinType.LEFT);
					root.fetch(SchedulerAppointment_.schResLoc, JoinType.LEFT);
				}
				
				Predicate requestedAppts=cq.where(cb.equal(root.get(SchedulerAppointment_.schApptPatientId), patientId),apptTypePredicate).getRestriction();
				return requestedAppts;
			}

		};
	}

	/**
	 * @param patientId	: used to get appointments list of a patient of that particular id
	 * @param appointmentsType : type of the appointment (Future,Past,Present)
	 * @return BooleanExpression is a  predicate  
	 */
	public static Specification<PortalApptRequest> getPatientAppointmentRequests(final int patientId)
	{
		return new Specification<PortalApptRequest>() {

			@Override
			public Predicate toPredicate(Root<PortalApptRequest> root,
					CriteriaQuery<?> cq, CriteriaBuilder cb) {		
				
				DateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");
				Date today = new Date();
				System.out.println(dateFormat.format(today));
				try {
					today = dateFormat.parse(dateFormat.format(today));
				} catch (ParseException e) {

					e.printStackTrace();
				}
				
				if(Long.class!=cq.getResultType()){
					
					root.fetch(PortalApptRequest_.portalApptRequestResourceEntity, JoinType.LEFT);
					root.fetch(PortalApptRequest_.portalApptRequestDetailsList, JoinType.LEFT);
				}
				
				//Join<PortalApptRequest, PortalApptRequestDetail> requestDetailsJoin=root.join(PortalApptRequest_.portalApptRequestDetailsList, JoinType.INNER);

				Predicate patientApptRequests=cq.where(cb.equal(root.get(PortalApptRequest_.portalApptRequestPatientId),patientId)/*, cb.greaterThanOrEqualTo(requestDetailsJoin.get(PortalApptRequestDetail_.portalApptRequestDetailRequestDate), today)*/).getRestriction();
				return patientApptRequests;
			}

		};
	}

	/**
	 * @param patientId	: used to get appointments list of a patient of that particular id
	 * @param appointmentsType : all appointment (Future,Past,Present)
	 * @return BooleanExpression is a  predicate  
	 */	
	public static Specification<SchedulerAppointment> getPatientTotalAppointments(final int patientId)
	{
		return new Specification<SchedulerAppointment>() {

			@Override
			public Predicate toPredicate(Root<SchedulerAppointment> root,
					CriteriaQuery<?> cq, CriteriaBuilder cb) {				

				DateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");
				Date today = new Date();
				System.out.println(dateFormat.format(today));
				try {
					today = dateFormat.parse(dateFormat.format(today));
				} catch (ParseException e) {

					e.printStackTrace();
				}

				Predicate requestedAppts=cq.where(cb.equal(root.get(SchedulerAppointment_.schApptPatientId), patientId)).getRestriction();
				return requestedAppts;
			}

		};
	}


	/**
	 * @return Appointment Status List  
	 */	
	public static Specification<AppReferenceValues> getSchApptStatusList()
	{
		return new Specification<AppReferenceValues>() {

			@Override
			public Predicate toPredicate(Root<AppReferenceValues> root, CriteriaQuery<?> cq, CriteriaBuilder cb) {

				Predicate schApptStatusList=cq.where(cb.equal(root.get(AppReferenceValues_.App_Reference_Values_tableId), 1)).getRestriction();

				return schApptStatusList;
			}

		};
	}

	/**
	 * @return Appointment Type List  
	 */	
	public static Specification<AppReferenceValues> getSchApptTypeList()
	{
		return new Specification<AppReferenceValues>() {

			@Override
			public Predicate toPredicate(Root<AppReferenceValues> root, CriteriaQuery<?> cq, CriteriaBuilder cb) {

				Predicate schApptStatusList=cq.where(cb.equal(root.get(AppReferenceValues_.App_Reference_Values_tableId), 2)).getRestriction();

				return schApptStatusList;
			}

		};
	}

	/**
	 * @return Appointment Reason List  
	 */	
	public static Specification<AppReferenceValues> getSchApptReasonList()
	{
		return new Specification<AppReferenceValues>() {

			@Override
			public Predicate toPredicate(Root<AppReferenceValues> root, CriteriaQuery<?> cq, CriteriaBuilder cb) {

				Predicate schApptStatusList=cq.where(cb.equal(root.get(AppReferenceValues_.App_Reference_Values_tableId), 402)).getRestriction();

				return schApptStatusList;
			}

		};
	}


	/**
	 * @return Appointment Booking Locations  
	 */	
	public static Specification<SchedulerResource> getAppointmentBookingLocations()
	{
		return new Specification<SchedulerResource>() {

			@Override
			public Predicate toPredicate(Root<SchedulerResource> root, CriteriaQuery<?> cq, CriteriaBuilder cb) {
				System.out.println("In getAppointmentBookingLocations");

				Join<SchedulerResource, PosTable> posResourceJoin=root.join(SchedulerResource_.ApptBookLocation,JoinType.LEFT);
				root.fetch(SchedulerResource_.ApptBookLocation,JoinType.LEFT);

				Predicate locationList=cq.where(cb.equal(root.get(SchedulerResource_.schResourceIsdoctor), 0),
						cb.not(root.get(SchedulerResource_.schResourceIsdoctor).in(20)),
						cb.equal(posResourceJoin.get(PosTable_.posTableIsActive), true),
						cb.equal(root.get(SchedulerResource_.schResourceLocalserver), true)).orderBy(cb.desc(root.get(SchedulerResource_.schResourceOrder))).getRestriction();

				return locationList;
			}

		};
	}

	/**
	 * @return Appointment Booking Locations  
	 */	
	public static Specification<SchedulerResource> getAppointmentBookingDoctors(final int posId)
	{
		return new Specification<SchedulerResource>() {

			@Override
			public Predicate toPredicate(Root<SchedulerResource> root, CriteriaQuery<?> cq, CriteriaBuilder cb) {
				System.out.println("In getAppointmentBookingDoctors");


				Predicate locationList;
				if(posId==-1)
				    locationList=cq.where(cb.not(root.get(SchedulerResource_.schResourceIsdoctor).in(20,0)),
						cb.equal(root.get(SchedulerResource_.schResourceLocalserver), true)).orderBy(cb.desc(root.get(SchedulerResource_.schResourceOrder))).getRestriction();
				else
					locationList=cq.where(cb.not(root.get(SchedulerResource_.schResourceIsdoctor).in(20,0)),
							cb.equal(root.get(SchedulerResource_.schResourceLocalserver), true)).orderBy(cb.desc(root.get(SchedulerResource_.schResourceOrder))).getRestriction();

				return locationList;
			}

		};
	}


	/**
	 * @return Scheduler Resource Category  
	 */	
	public static Specification<SchedulerResourceCategory> getSchResourceCategoriesList()
	{
		return new Specification<SchedulerResourceCategory>() {

			@Override
			public Predicate toPredicate(Root<SchedulerResourceCategory> root, CriteriaQuery<?> cq, CriteriaBuilder cb) {

				Predicate schRersourceCategoryList=cq.where(cb.equal(root.get(SchedulerResourceCategory_.schResourceCategoryIsactive), true)).orderBy(cb.asc(root.get(SchedulerResourceCategory_.schResourceCategoryResourceName))).getRestriction();

				return schRersourceCategoryList;
			}

		};
	}


	/**
	 * @return Scheduler Template Ids  
	 * @throws ParseException 
	 */	
	public static Specification<SchedulerTemplateDetail> getFreeSlotSchTemplateDetailList(final int providerId, Date apptDate,final List<Integer> templateIdList) 
	{

		return new Specification<SchedulerTemplateDetail>() {

			@Override
			public Predicate toPredicate(Root<SchedulerTemplateDetail> root, CriteriaQuery<?> cq, CriteriaBuilder cb) {

				Join<SchedulerTemplateDetail, SchedulerResource> apptLoactionJoin=root.join(SchedulerTemplateDetail_.schApptLocation,JoinType.LEFT);
				apptLoactionJoin.on(cb.equal(apptLoactionJoin.get(SchedulerResource_.schResourceIsdoctor), 0));

				Join<SchedulerTemplateDetail, SchedulerResource> apptDoctorJoin=root.join(SchedulerTemplateDetail_.schApptDoctor,JoinType.LEFT);
				Predicate apptDoctorJoinPredicate1=cb.equal(apptDoctorJoin.get(SchedulerResource_.schResourceId), providerId);

				Predicate apptDoctorJoinPredicate2=apptDoctorJoin.get(SchedulerResource_.schResourceId).in(1,2);
				apptDoctorJoin.on(apptDoctorJoinPredicate1,apptDoctorJoinPredicate2);

				root.fetch(SchedulerTemplateDetail_.schApptLocation,JoinType.LEFT);
				root.fetch(SchedulerTemplateDetail_.schApptDoctor,JoinType.LEFT);



				//Predicate schTemplateDetailPredicate=cq.where(cb.equal(root.get(SchedulerTemplateDetail_.schTemplateDetailIslocked), false),root.get(SchedulerTemplateDetail_.schTemplateDetailId).in(templateIdList)).getRestriction();
				Predicate schTemplateDetailPredicate=cq.where(root.get(SchedulerTemplateDetail_.schTemplateDetailId).in(templateIdList)).getRestriction();

				return schTemplateDetailPredicate;
			}

		};
	}


	/**
	 * @return Scheduler Template Ids  
	 * @throws ParseException 
	 */	
	public static Specification<SchedulerTemplate> getSchTemplateId(final int ownerId, final Date apptDate) 
	{		
		return new Specification<SchedulerTemplate>() {

			@Override
			public Predicate toPredicate(Root<SchedulerTemplate> root, CriteriaQuery<?> cq, CriteriaBuilder cb) {

				Calendar	templateDate	=Calendar.getInstance();
				templateDate.setTime(apptDate);
				int dayOfWeekInMonth 		= templateDate.get( Calendar.DAY_OF_WEEK_IN_MONTH );
				int dayOfWeek 				= templateDate.get( Calendar.DAY_OF_WEEK );

				Join<SchedulerTemplate, SchedulerTemplateOwnerMapping> templateOwnerJoin=root.join(SchedulerTemplate_.schedulerTemplateOwnerMapping, JoinType.INNER);
				root.fetch(SchedulerTemplate_.schedulerTemplateOwnerMapping,JoinType.INNER);

				Predicate schTemplatePredicate=cq.where(cb.equal(root.get(SchedulerTemplate_.schTemplateSchedule), (String.valueOf(dayOfWeekInMonth)+String.valueOf(dayOfWeek))),
						cb.equal(templateOwnerJoin.get(SchedulerTemplateOwnerMapping_.schTemplateOwnerMappingResource), ownerId),
						cb.equal(templateOwnerJoin.get(SchedulerTemplateOwnerMapping_.schTemplateOwnerMappingIsActive), true)).getRestriction();

				return schTemplatePredicate;
			}

		};
	}

	/**
	 * @return Scheduler Template Ids  
	 * @throws ParseException 
	 */	
	public static Specification<SchedulerTemplateTimeMapping> getSchTemplateTimeMappingId(final int templateId) 
	{		
		return new Specification<SchedulerTemplateTimeMapping>() {

			@Override
			public Predicate toPredicate(Root<SchedulerTemplateTimeMapping> root, CriteriaQuery<?> cq, CriteriaBuilder cb) {

				Predicate schTemplateTimingPredicate=cq.where(cb.equal(root.get(SchedulerTemplateTimeMapping_.schTemplateTimeMappingTemplateId), templateId)).getRestriction();

				return schTemplateTimingPredicate;
			}

		};
	}




	/**
	 * @return Scheduler Template Ids  
	 * @throws ParseException 
	 */	
	public static Specification<SchedulerDateTemplate> getSchDateTemplateIds(final int ownerId, final Date apptDate) 
	{		
		return new Specification<SchedulerDateTemplate>() {

			@Override
			public Predicate toPredicate(Root<SchedulerDateTemplate> root, CriteriaQuery<?> cq, CriteriaBuilder cb) {
				Predicate schRersourceCategoryList=cq.where(cb.equal(root.get(SchedulerDateTemplate_.schDateTemplateResourceId), ownerId),
						cb.equal(root.get(SchedulerDateTemplate_.schDateTemplateDate), apptDate)).getRestriction();

				return schRersourceCategoryList;
			}

		};
	}


	/**
	 * @return Scheduler Template Ids  
	 * @throws ParseException 
	 */	
	public static Specification<SchedulerAppointment> getBookedSlots(final int resourceId, final Date apptDate) 
	{		
		return new Specification<SchedulerAppointment>() {

			@Override
			public Predicate toPredicate(Root<SchedulerAppointment> root, CriteriaQuery<?> cq, CriteriaBuilder cb) {
				Predicate bookedSlotsList=cq.where(cb.equal(root.get(SchedulerAppointment_.schApptResource), resourceId),
						cb.equal(root.get(SchedulerAppointment_.schApptDate), apptDate)).orderBy(cb.asc(root.get(SchedulerAppointment_.schApptStarttime))).getRestriction();

				return bookedSlotsList;
			}

		};
	}

	/**
	 * @return Scheduler Template Ids  
	 * @throws ParseException 
	 */	
	public static Specification<SchedulerLock> getLockedSlots(final int resourceId, final Date apptDate) 
	{		
		return new Specification<SchedulerLock>() {

			@Override
			public Predicate toPredicate(Root<SchedulerLock> root, CriteriaQuery<?> cq, CriteriaBuilder cb) {

				Predicate lockedSlotsList=cq.where(cb.equal(root.get(SchedulerLock_.schLockStatusId), 4),
						cb.equal(root.get(SchedulerLock_.schLockIslocked), true),
						cb.equal(root.get(SchedulerLock_.schLockResourceId), resourceId),
						cb.equal(root.get(SchedulerLock_.schLockDate), apptDate)).orderBy(cb.asc(root.get(SchedulerLock_.schLockStarttime))).getRestriction();

				return lockedSlotsList;
			}

		};
	}

	/**
	 * @return Scheduler Template Ids  
	 * @throws ParseException 
	 */	
	public static Specification<SchedulerAppointment> getApptDetailsByApptIdAndPatientId(final int apptId, final int patientId) 
	{		
		return new Specification<SchedulerAppointment>() {

			@Override
			public Predicate toPredicate(Root<SchedulerAppointment> root, CriteriaQuery<?> cq, CriteriaBuilder cb) {

				Predicate schApptPredicate=cq.where(cb.equal(root.get(SchedulerAppointment_.schApptId), apptId),
						cb.equal(root.get(SchedulerAppointment_.schApptPatientId), patientId)).getRestriction();

				return schApptPredicate;
			}

		};
	}

	public static Pageable createPortalApptListRequestByDescDate(int pageIndex, int offset) {

		return new PageRequest(pageIndex, offset, Sort.Direction.DESC,"schApptStarttime");
	}

	public static Pageable createPortalRequestApptListRequestByDescDate(int pageIndex, int offset) {

		return new PageRequest(pageIndex, offset, Sort.Direction.DESC,"portalApptRequestId");
	}



}
