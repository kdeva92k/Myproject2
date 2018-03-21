package com.glenwood.glaceemr.server.application.services.chart.coumadinflowsheet;

import java.sql.Date;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Hashtable;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specifications;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import com.glenwood.glaceemr.server.application.models.AssociatedPhoneLogs;
import com.glenwood.glaceemr.server.application.models.LabEntriesParameter;
import com.glenwood.glaceemr.server.application.models.LabParameterCode;
import com.glenwood.glaceemr.server.application.models.PatientEpisode;
import com.glenwood.glaceemr.server.application.models.PatientEpisodeAttributeDetails;
import com.glenwood.glaceemr.server.application.models.ReminderEvent;
import com.glenwood.glaceemr.server.application.models.WarfarinIndication;
import com.glenwood.glaceemr.server.application.models.WarfarinLog;
import com.glenwood.glaceemr.server.application.repositories.AssociatedPhoneLogRepositories;
import com.glenwood.glaceemr.server.application.repositories.LabEntriesParameterRepository;
import com.glenwood.glaceemr.server.application.repositories.LabParameterCodeRepository;
import com.glenwood.glaceemr.server.application.repositories.LabParametersRepository;
import com.glenwood.glaceemr.server.application.repositories.PatientEpisodeAttributeDetailsRepository;
import com.glenwood.glaceemr.server.application.repositories.PatientEpisodeRepository;
import com.glenwood.glaceemr.server.application.repositories.ReminderEventRepository;
import com.glenwood.glaceemr.server.application.repositories.WarfarinIndicationRepository;
import com.glenwood.glaceemr.server.application.repositories.WarfarinLogRepository;
import com.glenwood.glaceemr.server.application.specifications.CoumadinFlowSheetSpecification;
import com.glenwood.glaceemr.server.utils.HUtil;

/**
 * @author Ranjitha
 *
 */

@Service
@Transactional
public class CoumadinFlowSheetServiceImpl implements CoumadinFlowSheetService {

	@Autowired
	PatientEpisodeRepository patientEpisodeRepository;
	@Autowired
	PatientEpisodeAttributeDetailsRepository PatientEpisodeAttributeDetailsRepository;
	@Autowired
	WarfarinIndicationRepository WarfarinIndicationRepository;
	@Autowired
	WarfarinLogRepository WarfarinLogRepository;
	@Autowired
	AssociatedPhoneLogRepositories AssociatedPhoneLogRepositories;
	@Autowired
	LabParameterCodeRepository LabParameterCodeRepository;
	@Autowired
	LabEntriesParameterRepository LabEntriesParameterRepository;
	@Autowired
	ReminderEventRepository ReminderEventRepository;
	@Autowired
	LabParametersRepository LabParametersRepository;

	@PersistenceContext
	EntityManager em;

	/**
	 * Method to retrieve episode data
	 */
	@Override
	public List<PatientEpisode> getEpisodes(Integer patientId,
			Integer episodeId, Integer type) {
		List<PatientEpisode> episodedata = patientEpisodeRepository
				.findAll(Specifications.where(
						CoumadinFlowSheetSpecification.getEpisodes(patientId,
								episodeId, type)).and(
						CoumadinFlowSheetSpecification.getOrder()));

		return episodedata;
	}

	/**
	 * Method to save episode data without end date
	 */
	@Override
	public void saveEpisodes(Integer patientId, Integer userId,
			Integer episodeId, java.util.Date date1, Integer episodeStatus,
			java.sql.Timestamp currentTimestamp, String isEpisodeDirty)
			throws Exception {

		java.sql.Date sqlDate = new java.sql.Date(date1.getTime());

		if (!(episodeId != -1)) {
			PatientEpisode newpatientepisode = new PatientEpisode();
			newpatientepisode.setPatientEpisodeName("Coumadin Therapy");
			newpatientepisode.setPatientEpisodeType(3);
			newpatientepisode.setPatientEpisodePatientid(patientId);
			newpatientepisode.setPatientEpisodeCreatedBy(userId);
			newpatientepisode.setPatientEpisodeIsactive(true);
			newpatientepisode.setPatientEpisodeEndDate(null);
			newpatientepisode.setPatientEpisodeCreatedDate(currentTimestamp);
			newpatientepisode.setPatientEpisodeStartDate(sqlDate);
			patientEpisodeRepository.saveAndFlush(newpatientepisode);
		} else {
			if (isEpisodeDirty.equals("1")) {
				PatientEpisode newpatientepisode = patientEpisodeRepository
						.findOne(episodeId);
				if (newpatientepisode != null) {
					newpatientepisode.setPatientEpisodeName("Coumadin Therapy");
					newpatientepisode.setPatientEpisodeType(3);
					newpatientepisode.setPatientEpisodeStartDate(sqlDate);
					newpatientepisode.setPatientEpisodeModifiedBy(userId);
					newpatientepisode
							.setPatientEpisodeModifiedDate(currentTimestamp);
					newpatientepisode.setPatientEpisodePatientid(patientId);
					newpatientepisode.setPatientEpisodeStatus(episodeStatus);

					patientEpisodeRepository.saveAndFlush(newpatientepisode);
				}
			}
		}

	}

	/**
	 * Method to save episode data with end date
	 */
	@Override
	public void saveEpisodeswithEndDate(Integer patientId, Integer userId,
			Integer episodeId, java.util.Date episodeEndDate,
			java.util.Date date1, Integer episodeStatus,
			java.sql.Timestamp currentTimestamp, String isEpisodeDirty) {
		java.sql.Date sqlDate = new java.sql.Date(date1.getTime());
		java.sql.Date sqlDate2 = new java.sql.Date(episodeEndDate.getTime());

		if (!(episodeId != -1)) {
			PatientEpisode newpatientepisode = new PatientEpisode();
			newpatientepisode.setPatientEpisodeName("Coumadin Therapy");
			newpatientepisode.setPatientEpisodeType(3);
			newpatientepisode.setPatientEpisodePatientid(patientId);
			newpatientepisode.setPatientEpisodeCreatedBy(userId);
			newpatientepisode.setPatientEpisodeIsactive(true);
			newpatientepisode.setPatientEpisodeStartDate(sqlDate);
			newpatientepisode.setPatientEpisodeEndDate(sqlDate2);
			newpatientepisode.setPatientEpisodeCreatedDate(currentTimestamp);
			newpatientepisode.setPatientEpisodeStatus(episodeStatus);
			patientEpisodeRepository.saveAndFlush(newpatientepisode);

		} else {
			if (isEpisodeDirty.equals("1")) {
				PatientEpisode newpatientepisode = patientEpisodeRepository
						.findOne(episodeId);
				if (newpatientepisode != null) {
					newpatientepisode.setPatientEpisodeName("Coumadin Therapy");
					newpatientepisode.setPatientEpisodeType(3);
					newpatientepisode.setPatientEpisodeStartDate(sqlDate);
					newpatientepisode.setPatientEpisodeModifiedBy(userId);
					newpatientepisode
							.setPatientEpisodeModifiedDate(currentTimestamp);
					newpatientepisode.setPatientEpisodePatientid(patientId);
					newpatientepisode.setPatientEpisodeStatus(episodeStatus);
					newpatientepisode.setPatientEpisodeEndDate(sqlDate2);
					patientEpisodeRepository.saveAndFlush(newpatientepisode);
				}
			}
		}
	}

	/**
	 * Method to update INR Goal
	 */
	@Override
	public void updateINRGoal(Integer patientId, Integer episodeId,
			String INRGoalRangeStart, String INRGoalRangeEnd) throws Exception {
		List<PatientEpisodeAttributeDetails> gwid = PatientEpisodeAttributeDetailsRepository
				.findAll(Specifications.where(CoumadinFlowSheetSpecification
						.getGWID(episodeId)));
		Boolean INR_Goal_Range_Start_Availablity = false;
		Boolean INR_Goal_Range_End_Availablity = false;
		int a = 0, b = 0;
		for (int i = 0; i < 2; i++) {
			if (gwid != null) {
				String gwid1 = gwid.get(i)
						.getPatientEpisodeAttributeDetailsGwid();

				if (gwid1 != null) {
					if (gwid1.equals("INRGOALSTART")) {
						INR_Goal_Range_Start_Availablity = true;
						a = gwid.get(i).getPatientEpisodeAttributeDetailsId();
					} else if (gwid1.equals("INRGOALEND")) {
						INR_Goal_Range_End_Availablity = true;
						b = gwid.get(i).getPatientEpisodeAttributeDetailsId();
					}
				}
			}

		}

		if (INRGoalRangeStart != "") {
			if (INR_Goal_Range_Start_Availablity) {
				PatientEpisodeAttributeDetails newpatientepisodeattribute = PatientEpisodeAttributeDetailsRepository
						.findOne(a);
				newpatientepisodeattribute
						.setPatientEpisodeAttributeDetailsValue(INRGoalRangeStart);
				PatientEpisodeAttributeDetailsRepository
						.saveAndFlush(newpatientepisodeattribute);
			} else {
				PatientEpisodeAttributeDetails newpatientepisodeattribute = new PatientEpisodeAttributeDetails();
				newpatientepisodeattribute
						.setPatientEpisodeAttributeDetailsGwid("INRGoalRangeStart");
				newpatientepisodeattribute
						.setPatientEpisodeAttributeDetailsEpisodeid(episodeId);
				newpatientepisodeattribute
						.setPatientEpisodeAttributeDetailsValue(INRGoalRangeStart);
				PatientEpisodeAttributeDetailsRepository
						.saveAndFlush(newpatientepisodeattribute);
			}
		}
		if (INRGoalRangeEnd != "") {
			if (INR_Goal_Range_End_Availablity) {
				PatientEpisodeAttributeDetails newpatientepisodeattribute = PatientEpisodeAttributeDetailsRepository
						.findOne(b);
				newpatientepisodeattribute
						.setPatientEpisodeAttributeDetailsValue(INRGoalRangeEnd);
				PatientEpisodeAttributeDetailsRepository
						.saveAndFlush(newpatientepisodeattribute);

			} else {
				PatientEpisodeAttributeDetails newpatientepisodeattribute = new PatientEpisodeAttributeDetails();
				newpatientepisodeattribute
						.setPatientEpisodeAttributeDetailsGwid("INRGoalRangeEnd");
				newpatientepisodeattribute
						.setPatientEpisodeAttributeDetailsEpisodeid(episodeId);
				newpatientepisodeattribute
						.setPatientEpisodeAttributeDetailsValue(INRGoalRangeEnd);
				PatientEpisodeAttributeDetailsRepository
						.saveAndFlush(newpatientepisodeattribute);
			}

		}
	}

	/**
	 * Method to save indication
	 */
	@Override
	public void saveIndication(org.w3c.dom.Document document,
			Integer episodeId, Integer userId,
			java.sql.Timestamp currentTimestamp, Integer patientId)
			throws Exception {
		NodeList indicationList = ((org.w3c.dom.Document) document)
				.getElementsByTagName("indications");
		if (indicationList.getLength() > 0) {
			NodeList indications = indicationList.item(0).getChildNodes();
			for (int i = 0; i < indications.getLength(); i++) {
				Node indicationNode = indications.item(i);
				if (indicationNode.getNodeName().equalsIgnoreCase("indication")) {
					String deleteFlag = (indicationNode.getAttributes()
							.getNamedItem("deleteflag") != null ? indicationNode
							.getAttributes().getNamedItem("deleteflag")
							.getNodeValue()
							: "-1");
					NodeList indicationDetails = indicationNode.getChildNodes();
					String code = new String();
					String description = new String();
					for (int indicationDetailCnt = 0; indicationDetailCnt < indicationDetails
							.getLength(); indicationDetailCnt++) {
						if (indicationDetails.item(indicationDetailCnt)
								.getNodeName().equals("code")) {
							if (indicationDetails.item(indicationDetailCnt)
									.getFirstChild() != null) {
								code = indicationDetails
										.item(indicationDetailCnt)
										.getFirstChild().getNodeValue();
							}
						} else if (indicationDetails.item(indicationDetailCnt)
								.getNodeName().equals("description")) {
							if (indicationDetails.item(indicationDetailCnt)
									.getFirstChild() != null) {
								description = indicationDetails
										.item(indicationDetailCnt)
										.getFirstChild().getNodeValue();
							}
						}
					}
					int codingSystem = Integer
							.parseInt((indicationNode.getAttributes()
									.getNamedItem("codingsystem") != null ? indicationNode
									.getAttributes()
									.getNamedItem("codingsystem")
									.getNodeValue()
									: "-1"));
					int indicationId = Integer
							.parseInt((indicationNode.getAttributes()
									.getNamedItem("id") != null ? indicationNode
									.getAttributes().getNamedItem("id")
									.getNodeValue()
									: "-1"));
					WarfarinIndication newWarfarinIndication = WarfarinIndicationRepository
							.findOne(Specifications
									.where(CoumadinFlowSheetSpecification
											.getObj(indicationId, episodeId)));
					if (deleteFlag == "" || deleteFlag == "-1") {
						if (indicationId == -1) {
							WarfarinIndication newindication = new WarfarinIndication();
							newindication.setWarfarinIndicationId(episodeId);
							newindication.setWarfarinIndicationCode(code);
							newindication
									.setWarfarinIndicationCodeDescription(description);
							newindication
									.setWarfarinIndicationCodeSystem(codingSystem);
							newindication.setWarfarinIndicationStatus(1);
							newindication
									.setWarfarinIndicationCreatedBy(userId);
							newindication
									.setWarfarinIndicationCreatedOn(currentTimestamp);
							WarfarinIndicationRepository
									.saveAndFlush(newindication);
						}

						else {
							WarfarinIndication newindication = WarfarinIndicationRepository
									.findOne(newWarfarinIndication
											.getWarfarinIndicationId());
							if (newindication != null) {
								newindication.setWarfarinIndicationCode(code);
								newindication
										.setWarfarinIndicationCodeDescription(description);
								newindication
										.setWarfarinIndicationCodeSystem(codingSystem);
								newindication
										.setWarfarinIndicationCreatedBy(userId);
								newindication
										.setWarfarinIndicationCreatedOn(currentTimestamp);
								WarfarinIndicationRepository
										.saveAndFlush(newindication);
							}
						}
					} else if (deleteFlag == "1") {
						WarfarinIndication newindication = WarfarinIndicationRepository
								.findOne(newWarfarinIndication
										.getWarfarinIndicationId());
						if (newindication != null) {
							newindication.setWarfarinIndicationStatus(3);
							WarfarinIndicationRepository
									.saveAndFlush(newindication);
						}
					}

				}
			}
		}
	}

	/**
	 * Method to save log informations
	 */
	@Override
	public void saveLog(org.w3c.dom.Document document, Integer chartId,
			Integer patientId, Integer userId, Integer episodeId,
			Timestamp currentTimestamp) throws Exception {

		NodeList logList = document.getElementsByTagName("coumadin_log");
		if (logList.getLength() > 0) {
			NodeList logs = logList.item(0).getChildNodes();
			for (int i = 0; i < logs.getLength(); i++) {
				Node logNode = logs.item(i);
				if (logNode.getNodeName().equalsIgnoreCase("log")) {
					int logId = Integer.parseInt((logNode.getAttributes()
							.getNamedItem("id") != null ? logNode
							.getAttributes().getNamedItem("id").getNodeValue()
							: "-1"));
					int isNewPlan = Integer.parseInt((logNode.getAttributes()
							.getNamedItem("isNewPlan") != null ? logNode
							.getAttributes().getNamedItem("isNewPlan")
							.getNodeValue() : "0"));
					String deleteFlag = (logNode.getAttributes().getNamedItem(
							"deleteflag") != null ? logNode.getAttributes()
							.getNamedItem("deleteflag").getNodeValue() : "-1");
					if (deleteFlag.equals("1")) {
						WarfarinLog newwarfarinlog = WarfarinLogRepository
								.findOne(logId);
						if (newwarfarinlog != null) {
							newwarfarinlog.setWarfarinLogStatus(3);
							newwarfarinlog.setWarfarinLogModifiedBy(userId);
							newwarfarinlog
									.setWarfarinLogModifiedOn(currentTimestamp);
							continue;
						}
					}
					NodeList logEntry = logNode.getChildNodes();
					Hashtable<String, String> logDetail = new Hashtable<String, String>();
					SimpleDateFormat originalFormat = new SimpleDateFormat(
							"MM/dd/yyyy");

					if (logId == -1) {
						int phoneMessageEncounterId = 0;
						WarfarinLog newWarfarinLog = new WarfarinLog();
						newWarfarinLog.setWarfarinLogPatientId(patientId);
						newWarfarinLog.setWarfarinLogEpisodeId(episodeId);
						newWarfarinLog.setWarfarinLogCreatedBy(userId);
						newWarfarinLog
								.setWarfarinLogCreatedOn(currentTimestamp);
						java.sql.Timestamp timestampPerformedDate = null;
						for (int detailIndex = 0; detailIndex < logEntry
								.getLength(); detailIndex++) {
							String elementName = logEntry.item(detailIndex)
									.getNodeName();
							String elementValue = new String();
							if (logEntry.item(detailIndex).getFirstChild() != null)
								elementValue = logEntry.item(detailIndex)
										.getFirstChild().getNodeValue();

							if (elementName.equalsIgnoreCase("entry_date")) {
								java.util.Date date1 = originalFormat
										.parse(elementValue);
								java.sql.Date sqlDate = new java.sql.Date(
										date1.getTime());

								newWarfarinLog.setWarfarinLogEnteredOn(sqlDate);
								newWarfarinLog.setWarfarinLogEnteredBy(userId);
							} else if (elementName
									.equalsIgnoreCase("performed_date")) {
								java.util.Date date1 = originalFormat
										.parse(elementValue);
								java.sql.Date sqlDate = new java.sql.Date(
										date1.getTime());
								timestampPerformedDate = new java.sql.Timestamp(
										date1.getTime());
								newWarfarinLog
										.setWarfarinLogPerformedDate(sqlDate);
							} else if (elementName.equalsIgnoreCase("INR")) {

								List<LabParameterCode> labparamcode = LabParameterCodeRepository
										.findAll(CoumadinFlowSheetSpecification
												.getLabParams());
								List<LabEntriesParameter> labentriesparam = LabEntriesParameterRepository
										.findAll(CoumadinFlowSheetSpecification
												.getPTINR2(chartId,
														timestampPerformedDate));
								List<LabEntriesParam_LabParamCodeBean> labdetails = new ArrayList<LabEntriesParam_LabParamCodeBean>();
								Integer Id = 0;
								if (labparamcode != null
										& labentriesparam != null) {
									for (int ii = 0; ii < labparamcode.size(); ii++) {
										for (int j = 0; j < labentriesparam
												.size(); j++) {
											if (labparamcode
													.get(ii)
													.getLabParameterCodeParamid()
													.equals(labentriesparam
															.get(j)
															.getLabEntriesParameterMapid())) {
												String LabEntriesParamValue = labentriesparam
														.get(j)
														.getLabEntriesParameterValue();

												Integer LabParameterCodeParamid = labparamcode
														.get(ii)
														.getLabParameterCodeParamid();

												String LabParameterCodeValue = labparamcode
														.get(ii)
														.getLabParameterCodeValue();

												Integer LabEntriesParamId = labentriesparam
														.get(j)
														.getLabEntriesParameterId();

												LabEntriesParam_LabParamCodeBean newbean = new LabEntriesParam_LabParamCodeBean(
														LabEntriesParamValue,
														LabParameterCodeParamid,
														LabParameterCodeValue,
														LabEntriesParamId);
												labdetails.add(newbean);
											}
										}
									}
								}
								Integer mapid = 0;
								for (int k = 0; k < labdetails.size(); k++) {
									mapid = labdetails.get(k)
											.getLabParameterCodeParamid();
									if (labdetails.get(k)
											.getLabParameterCodeValue()
											.equals("6301-6")) {
										if (!(labdetails.get(k)
												.getLabEntriesParamId()
												.equals(""))) {
											Id = labdetails.get(k)
													.getLabEntriesParamId();
										}
									}
								}
								if (Id != 0) {
									LabEntriesParameter newlabEntriesParam = LabEntriesParameterRepository
											.findOne(Specifications
													.where(CoumadinFlowSheetSpecification
															.getLabEntriesParam(Id)));
									if (newlabEntriesParam != null) {
										newlabEntriesParam
												.setLabEntriesParameterValue(elementValue);
									}
								} else {
									LabEntriesParameter newlabEntriesParam = new LabEntriesParameter();
									newlabEntriesParam
											.setLabEntriesParameterTestdetailid(-1);
									newlabEntriesParam
											.setLabEntriesParameterMapid(mapid);
									newlabEntriesParam
											.setLabEntriesParameterValue(elementValue);
									newlabEntriesParam
											.setLabEntriesParameterChartid(chartId);
									newlabEntriesParam
											.setLabEntriesParameterDate(timestampPerformedDate);
									LabEntriesParameterRepository
											.saveAndFlush(newlabEntriesParam);
								}
							} else if (elementName.equalsIgnoreCase("PT")) {
								List<LabParameterCode> labparamcode = LabParameterCodeRepository
										.findAll(CoumadinFlowSheetSpecification
												.getLabParams());
								List<LabEntriesParameter> labentriesparam = LabEntriesParameterRepository
										.findAll(CoumadinFlowSheetSpecification
												.getPTINR2(chartId,
														timestampPerformedDate));
								List<LabEntriesParam_LabParamCodeBean> labdetails = new ArrayList<LabEntriesParam_LabParamCodeBean>();
								Integer Id = 0;
								if (labparamcode != null
										& labentriesparam != null) {
									for (int ii = 0; ii < labparamcode.size(); ii++) {
										for (int j = 0; j < labentriesparam
												.size(); j++) {
											if (labparamcode
													.get(ii)
													.getLabParameterCodeParamid()
													.equals(labentriesparam
															.get(j)
															.getLabEntriesParameterMapid())) {
												String LabEntriesParamValue = labentriesparam
														.get(j)
														.getLabEntriesParameterValue();

												Integer LabParameterCodeParamid = labparamcode
														.get(ii)
														.getLabParameterCodeParamid();

												String LabParameterCodeValue = labparamcode
														.get(ii)
														.getLabParameterCodeValue();

												Integer LabEntriesParamId = labentriesparam
														.get(j)
														.getLabEntriesParameterId();

												LabEntriesParam_LabParamCodeBean newbean = new LabEntriesParam_LabParamCodeBean(
														LabEntriesParamValue,
														LabParameterCodeParamid,
														LabParameterCodeValue,
														LabEntriesParamId);
												labdetails.add(newbean);
											}
										}
									}
								}
								Integer mapid = 0;
								for (int k = 0; k < labdetails.size(); k++) {
									mapid = labdetails.get(k)
											.getLabParameterCodeParamid();
									if (labdetails.get(k)
											.getLabParameterCodeValue()
											.equals("5902-2")) {
										if (!(labdetails.get(k)
												.getLabEntriesParamId()
												.equals(""))) {
											Id = labdetails.get(k)
													.getLabEntriesParamId();
										}
									}
								}
								if (Id != 0) {
									LabEntriesParameter newlabEntriesParam = LabEntriesParameterRepository
											.findOne(Specifications
													.where(CoumadinFlowSheetSpecification
															.getLabEntriesParam(Id)));
									if (newlabEntriesParam != null)
										newlabEntriesParam
												.setLabEntriesParameterValue(elementValue);

								} else {
									LabEntriesParameter newlabEntriesParam = new LabEntriesParameter();
									newlabEntriesParam
											.setLabEntriesParameterTestdetailid(-1);
									newlabEntriesParam
											.setLabEntriesParameterMapid(mapid);
									newlabEntriesParam
											.setLabEntriesParameterValue(elementValue);
									newlabEntriesParam
											.setLabEntriesParameterChartid(chartId);
									newlabEntriesParam
											.setLabEntriesParameterDate(timestampPerformedDate);
									LabEntriesParameterRepository
											.saveAndFlush(newlabEntriesParam);
								}

							}

							else if (elementName
									.equalsIgnoreCase("total_weekly_dose")) {
								newWarfarinLog
										.setWarfarinLogTotalWeeklyDose(elementValue);

							} else if (elementName
									.equalsIgnoreCase("sunday_dose")) {
								newWarfarinLog
										.setWarfarinLogSundayDose(elementValue);

							} else if (elementName
									.equalsIgnoreCase("monday_dose")) {
								newWarfarinLog
										.setWarfarinLogMondayDose(elementValue);

							} else if (elementName
									.equalsIgnoreCase("tuesday_dose")) {
								newWarfarinLog
										.setWarfarinLogTuesdayDose(elementValue);

							} else if (elementName
									.equalsIgnoreCase("wednesday_dose")) {
								newWarfarinLog
										.setWarfarinLogWednesdayDose(elementValue);

							} else if (elementName
									.equalsIgnoreCase("thursday_dose")) {
								newWarfarinLog
										.setWarfarinLogThursdayDose(elementValue);

							} else if (elementName
									.equalsIgnoreCase("friday_dose")) {
								newWarfarinLog
										.setWarfarinLogFridayDose(elementValue);

							} else if (elementName
									.equalsIgnoreCase("saturday_dose")) {
								newWarfarinLog
										.setWarfarinLogSaturdayDose(elementValue);

							} else if (elementName
									.equalsIgnoreCase("INR_return_visit_date")) {
								if (isNewPlan == 1) {
									java.util.Date date1 = originalFormat
											.parse(elementValue);
									java.util.Date sqlDate = new java.sql.Date(
											date1.getTime());
									createCoumadinReminders(patientId,
											episodeId, userId, sqlDate);
								} else {
									newWarfarinLog
											.setWarfarinLogReturnVisitDate(elementValue);
								}

							} else if (elementName
									.equalsIgnoreCase("return_visit_instructions")) {

								newWarfarinLog
										.setWarfarinLogReturnVisitInst(elementValue);

							} else if (elementName
									.equalsIgnoreCase("INR_comments")) {
								newWarfarinLog
										.setWarfarinLogComments(elementValue);

							} else if (elementName
									.equalsIgnoreCase("patient_informed_status")) {
								int elementvalue = Integer
										.parseInt(elementValue);

								newWarfarinLog
										.setWarfarinLogPatientInformed(elementvalue);

							} else if (elementName
									.equalsIgnoreCase("reviewed_status")) {
								if (!elementValue.trim().equals("")) {

									int elementvalue = Integer
											.parseInt(elementValue);
									newWarfarinLog
											.setWarfarinLogStatus(elementvalue);
								} else
									newWarfarinLog.setWarfarinLogStatus(0);
								if (elementValue.trim().equals(2)) {

									newWarfarinLog
											.setWarfarinLogReviewedBy(userId);
									newWarfarinLog
											.setWarfarinLogReviewedOn(currentTimestamp);
								}
							} else if (elementName
									.equalsIgnoreCase("phone_message_encounter_id")) {
								if (!elementValue.trim().equals(""))
									phoneMessageEncounterId = Integer
											.parseInt(elementValue.trim());
							}

						}
						WarfarinLogRepository.saveAndFlush(newWarfarinLog);

						List<WarfarinLog> log = WarfarinLogRepository
								.findAll(CoumadinFlowSheetSpecification
										.getMaxId());
						int value = log.get(0).getWarfarinLogId();
						if (phoneMessageEncounterId != 0) {
							AssociatedPhoneLogs newassociatedphonelog = new AssociatedPhoneLogs();
							newassociatedphonelog
									.setAssociatedPhoneLogsEntityId(value);
							newassociatedphonelog
									.setAssociatedPhoneLogsEntityType(2);
							newassociatedphonelog
									.setAssociatedPhoneLogsLogId(phoneMessageEncounterId);
							newassociatedphonelog
									.setAssociatedPhoneLogsIsactive(true);
							AssociatedPhoneLogRepositories
									.saveAndFlush(newassociatedphonelog);
						}
					} else {
						int phoneMessageEncounterId = 0;
						WarfarinLog newwarfarinlog = WarfarinLogRepository
								.findOne(Specifications
										.where(CoumadinFlowSheetSpecification
												.getObject(logId, episodeId)));
						newwarfarinlog.setWarfarinLogPatientId(patientId);
						newwarfarinlog.setWarfarinLogEpisodeId(episodeId);
						newwarfarinlog.setWarfarinLogCreatedBy(userId);
						newwarfarinlog
								.setWarfarinLogCreatedOn(currentTimestamp);
						java.sql.Timestamp timestampPerformedDate = null;
						if (newwarfarinlog != null) {
							for (int detailIndex = 0; detailIndex < logEntry
									.getLength(); detailIndex++) {
								String elementName = logEntry.item(detailIndex)
										.getNodeName();
								String elementValue = new String();
								if (logEntry.item(detailIndex).getFirstChild() != null)
									elementValue = logEntry.item(detailIndex)
											.getFirstChild().getNodeValue();
								if (elementName
										.equalsIgnoreCase("performed_date")) {
									java.util.Date date1 = originalFormat
											.parse(elementValue);
									java.sql.Date sqlDate = new java.sql.Date(
											date1.getTime());
									newwarfarinlog
											.setWarfarinLogPerformedDate(sqlDate);
									timestampPerformedDate = new java.sql.Timestamp(
											date1.getTime());
								} else if (elementName.equalsIgnoreCase("INR")) {

									List<LabParameterCode> labparamcode = LabParameterCodeRepository
											.findAll(CoumadinFlowSheetSpecification
													.getLabParams());
									List<LabEntriesParameter> labentriesparam = LabEntriesParameterRepository
											.findAll(CoumadinFlowSheetSpecification
													.getPTINR2(chartId,
															timestampPerformedDate));
									List<LabEntriesParam_LabParamCodeBean> labdetails = new ArrayList<LabEntriesParam_LabParamCodeBean>();
									Integer Id = 0;
									if (labparamcode != null
											& labentriesparam != null) {
										for (int ii = 0; ii < labparamcode
												.size(); ii++) {
											for (int j = 0; j < labentriesparam
													.size(); j++) {
												if (labparamcode
														.get(ii)
														.getLabParameterCodeParamid()
														.equals(labentriesparam
																.get(j)
																.getLabEntriesParameterMapid())) {
													String LabEntriesParamValue = labentriesparam
															.get(j)
															.getLabEntriesParameterValue();

													Integer LabParameterCodeParamid = labparamcode
															.get(ii)
															.getLabParameterCodeParamid();

													String LabParameterCodeValue = labparamcode
															.get(ii)
															.getLabParameterCodeValue();

													Integer LabEntriesParamId = labentriesparam
															.get(j)
															.getLabEntriesParameterId();

													LabEntriesParam_LabParamCodeBean newbean = new LabEntriesParam_LabParamCodeBean(
															LabEntriesParamValue,
															LabParameterCodeParamid,
															LabParameterCodeValue,
															LabEntriesParamId);
													labdetails.add(newbean);
												}
											}
										}
									}
									Integer mapid = 0;
									for (int k = 0; k < labdetails.size(); k++) {
										mapid = labdetails.get(k)
												.getLabParameterCodeParamid();
										if (labdetails.get(k)
												.getLabParameterCodeValue()
												.equals("6301-6")) {
											if (!(labdetails.get(k)
													.getLabEntriesParamId()
													.equals(""))) {
												Id = labdetails.get(k)
														.getLabEntriesParamId();
											}
										}
									}
									if (Id != 0) {
										LabEntriesParameter newlabEntriesParam = LabEntriesParameterRepository
												.findOne(Specifications
														.where(CoumadinFlowSheetSpecification
																.getLabEntriesParam(Id)));
										if (newlabEntriesParam != null)
											newlabEntriesParam
													.setLabEntriesParameterValue(elementValue);
									} else {
										LabEntriesParameter newlabEntriesParam = new LabEntriesParameter();
										newlabEntriesParam
												.setLabEntriesParameterTestdetailid(-1);
										newlabEntriesParam
												.setLabEntriesParameterMapid(mapid);
										newlabEntriesParam
												.setLabEntriesParameterValue(elementValue);
										newlabEntriesParam
												.setLabEntriesParameterChartid(chartId);
										newlabEntriesParam
												.setLabEntriesParameterDate(timestampPerformedDate);
										LabEntriesParameterRepository
												.saveAndFlush(newlabEntriesParam);
									}

								} else if (elementName.equalsIgnoreCase("PT")) {
									logDetail.put("PT", elementValue);

									List<LabParameterCode> labparamcode = LabParameterCodeRepository
											.findAll(CoumadinFlowSheetSpecification
													.getLabParams());
									List<LabEntriesParameter> labentriesparam = LabEntriesParameterRepository
											.findAll(CoumadinFlowSheetSpecification
													.getPTINR2(chartId,
															timestampPerformedDate));
									List<LabEntriesParam_LabParamCodeBean> labdetails = new ArrayList<LabEntriesParam_LabParamCodeBean>();
									Integer Id = 0;
									if (labparamcode != null
											& labentriesparam != null) {
										for (int ii = 0; ii < labparamcode
												.size(); ii++) {
											for (int j = 0; j < labentriesparam
													.size(); j++) {
												if (labparamcode
														.get(ii)
														.getLabParameterCodeParamid()
														.equals(labentriesparam
																.get(j)
																.getLabEntriesParameterMapid())) {
													String LabEntriesParamValue = labentriesparam
															.get(j)
															.getLabEntriesParameterValue();

													Integer LabParameterCodeParamid = labparamcode
															.get(ii)
															.getLabParameterCodeParamid();

													String LabParameterCodeValue = labparamcode
															.get(ii)
															.getLabParameterCodeValue();

													Integer LabEntriesParamId = labentriesparam
															.get(j)
															.getLabEntriesParameterId();

													LabEntriesParam_LabParamCodeBean newbean = new LabEntriesParam_LabParamCodeBean(
															LabEntriesParamValue,
															LabParameterCodeParamid,
															LabParameterCodeValue,
															LabEntriesParamId);
													labdetails.add(newbean);
												}
											}
										}
									}
									Integer mapid = 0;
									for (int k = 0; k < labdetails.size(); k++) {
										mapid = labdetails.get(k)
												.getLabParameterCodeParamid();
										if (labdetails.get(k)
												.getLabParameterCodeValue()
												.equals("5902-2")) {
											if (!(labdetails.get(k)
													.getLabEntriesParamId()
													.equals(""))) {
												Id = labdetails.get(k)
														.getLabEntriesParamId();
											}
										}
									}
									if (Id != 0) {
										LabEntriesParameter newlabEntriesParam = LabEntriesParameterRepository
												.findOne(Specifications
														.where(CoumadinFlowSheetSpecification
																.getLabEntriesParam(Id)));
										if (newlabEntriesParam != null)
											newlabEntriesParam
													.setLabEntriesParameterValue(elementValue);

									} else {
										LabEntriesParameter newlabEntriesParam = new LabEntriesParameter();
										newlabEntriesParam
												.setLabEntriesParameterTestdetailid(-1);
										newlabEntriesParam
												.setLabEntriesParameterMapid(mapid);
										newlabEntriesParam
												.setLabEntriesParameterValue(elementValue);
										newlabEntriesParam
												.setLabEntriesParameterChartid(chartId);
										newlabEntriesParam
												.setLabEntriesParameterDate(timestampPerformedDate);
										LabEntriesParameterRepository
												.saveAndFlush(newlabEntriesParam);
									}

								} else if (elementName
										.equalsIgnoreCase("entry_date")) {
									java.util.Date date1 = originalFormat
											.parse(elementValue);
									java.sql.Date sqlDate = new java.sql.Date(
											date1.getTime());

									newwarfarinlog
											.setWarfarinLogEnteredOn(sqlDate);
									newwarfarinlog
											.setWarfarinLogEnteredBy(userId);
								} else if (elementName
										.equalsIgnoreCase("total_weekly_dose")) {
									newwarfarinlog
											.setWarfarinLogTotalWeeklyDose(elementValue);
								} else if (elementName
										.equalsIgnoreCase("sunday_dose")) {
									newwarfarinlog
											.setWarfarinLogSundayDose(elementValue);
								} else if (elementName
										.equalsIgnoreCase("monday_dose")) {
									newwarfarinlog
											.setWarfarinLogMondayDose(elementValue);
								} else if (elementName
										.equalsIgnoreCase("tuesday_dose")) {
									newwarfarinlog
											.setWarfarinLogTuesdayDose(elementValue);
								} else if (elementName
										.equalsIgnoreCase("wednesday_dose")) {
									newwarfarinlog
											.setWarfarinLogWednesdayDose(elementValue);
								} else if (elementName
										.equalsIgnoreCase("thursday_dose")) {
									newwarfarinlog
											.setWarfarinLogThursdayDose(elementValue);
								} else if (elementName
										.equalsIgnoreCase("friday_dose")) {
									newwarfarinlog
											.setWarfarinLogFridayDose(elementValue);
								} else if (elementName
										.equalsIgnoreCase("saturday_dose")) {
									newwarfarinlog
											.setWarfarinLogSaturdayDose(elementValue);
								} else if (elementName
										.equalsIgnoreCase("INR_return_visit_date")) {
									newwarfarinlog
											.setWarfarinLogReturnVisitDate(elementValue);
								} else if (elementName
										.equalsIgnoreCase("return_visit_instructions")) {
									newwarfarinlog
											.setWarfarinLogReturnVisitInst(elementValue);
								} else if (elementName
										.equalsIgnoreCase("INR_comments")) {
									newwarfarinlog
											.setWarfarinLogComments(elementValue);
								} else if (elementName
										.equalsIgnoreCase("patient_informed_status")) {
									int elementvalue = Integer
											.parseInt(elementValue);
									newwarfarinlog
											.setWarfarinLogPatientInformed(elementvalue);
								} else if (elementName
										.equalsIgnoreCase("reviewed_status")) {
									if (!elementValue.trim().equals("")) {
										int elementvalue = Integer
												.parseInt(elementValue);
										newwarfarinlog
												.setWarfarinLogStatus(elementvalue);
									} else
										newwarfarinlog.setWarfarinLogStatus(0);
									if (elementValue.trim().equals(2)) {
										newwarfarinlog
												.setWarfarinLogReviewedBy(userId);
										newwarfarinlog
												.setWarfarinLogReviewedOn(currentTimestamp);
									}
								} else if (elementName
										.equalsIgnoreCase("phone_message_encounter_id")) {
									phoneMessageEncounterId = Integer
											.parseInt(elementValue.trim());
									AssociatedPhoneLogs newassociatedphonelog = new AssociatedPhoneLogs();
									newassociatedphonelog
											.setAssociatedPhoneLogsEntityId(logId);
									newassociatedphonelog
											.setAssociatedPhoneLogsEntityType(2);
									newassociatedphonelog
											.setAssociatedPhoneLogsLogId(phoneMessageEncounterId);
									newassociatedphonelog
											.setAssociatedPhoneLogsIsactive(true);
									AssociatedPhoneLogRepositories
											.saveAndFlush(newassociatedphonelog);
								}

							}
						}
					}

				}

			}
		}
	}

	/**
	 * Method to get Log informations details
	 */
	@SuppressWarnings({ "unused", "null" })
	@Override
	public List<LogInfoBean> getLogInfo(Integer chartId, Integer episodeId)
			throws Exception {
		long count = WarfarinLogRepository.count(CoumadinFlowSheetSpecification
				.getCount());
		String enteredBy = "";
		String reviewedBy = "";
		String modifiedBy = "";
		String createdBy = "";
		Date warfarinLogPerformedDate = null;
		String warfarinLogTotalWeeklyDose = "";
		LogInfoBean newLogInfo = null;
		Integer warfarinLogStatus = null;
		String warfarinLogSundayDose = "";
		String warfarinLogMondayDose = "";
		String warfarinLogTuesdayDose = "";
		String warfarinLogWednesdayDose = "";
		String warfarinLogThursdayDose = "";
		String warfarinLogFridayDose = "";
		String warfarinLogSaturdayDose = "";
		String warfarinLogReturnVisitDate = "";
		Integer warfarinLogReturnVisitReminderId = null;
		String warfarinLogReturnVisitInst = "";
		Integer reminderEventStatus = 0;
		String warfarinLogComments = "";
		Integer warfarinLogPatientInformed = null;
		Timestamp warfarinLogReviewedOn = null;
		Timestamp warfarinLogCreatedOn = null;
		Date warfarinLogEnteredOn = null;
		Integer logId = null;
		long Count = 0;
		String PT = "";
		String INR = "";
		String stringDate = "1111-01-01 00:00:00.000 ";
		String dates = "01/01/1990";
		SimpleDateFormat format = new SimpleDateFormat("MM/dd/yyyy");
		SimpleDateFormat timestampFormat = new SimpleDateFormat(
				"yyyy-MM-dd HH:mm:ss.SSS");
		java.util.Date Date = format.parse(dates);
		java.sql.Date defaultDate = new java.sql.Date(Date.getTime());
		Timestamp defaultTimestamp = Timestamp.valueOf(stringDate);

		List<WarfarinLog> Loginfo = WarfarinLogRepository
				.findAll(CoumadinFlowSheetSpecification.getLogInfo(chartId,
						episodeId));
		if (Loginfo != null) {
			for (int i = 0; i < Loginfo.size(); i++) {

				if (Loginfo.get(i).getEmployeetable1() != null)

					enteredBy = Loginfo.get(i).getEmployeetable1()
							.getEmpProfileFullname();
				if (Loginfo.get(i).getEmployeetable2() != null)

					reviewedBy = Loginfo.get(i).getEmployeetable2()
							.getEmpProfileFullname();
				if (Loginfo.get(i).getEmployeetable3() != null)

					modifiedBy = Loginfo.get(i).getEmployeetable3()
							.getEmpProfileFullname();
				if (Loginfo.get(i).getEmployeetable4() != null)

					createdBy = Loginfo.get(i).getEmployeetable4()
							.getEmpProfileFullname();

			}
		}
		List<LabParameterCode> labparamcodes = LabParameterCodeRepository
				.findAll(CoumadinFlowSheetSpecification.getLabParams());

		List<LabParameterCode> labparamcode = LabParameterCodeRepository
				.findAll(CoumadinFlowSheetSpecification.getLabParam());

		List<LabEntriesParameter> labentriescode = LabEntriesParameterRepository
				.findAll(CoumadinFlowSheetSpecification.getLabEntries(chartId));
		List<LabDetailsBean> INRlabdetails = new ArrayList<LabDetailsBean>();
		String LabEntriesParamValue = null;
		Date LabEntriesParamDate = null;
		Integer LabParameterCodeParamid = null;
		String LabParameterCodeValue = null;
		LabDetailsBean newbean = null;
		if (labparamcodes != null & labentriescode != null) {
			for (int i = 0; i < labparamcodes.size(); i++) {
				for (int j = 0; j < labentriescode.size(); j++) {
					if (labparamcodes
							.get(i)
							.getLabParameterCodeParamid()
							.equals(labentriescode.get(j)
									.getLabEntriesParameterMapid())) {

						LabEntriesParamValue = labentriescode.get(j)
								.getLabEntriesParameterValue();
						Timestamp timeStamp = labentriescode.get(j)
								.getLabEntriesParameterDate();
						java.sql.Date date = new java.sql.Date(
								timeStamp.getTime());
						LabEntriesParamDate = date;
						LabParameterCodeParamid = labparamcodes.get(i)
								.getLabParameterCodeParamid();
						LabParameterCodeValue = labparamcodes.get(i)
								.getLabParameterCodeValue();
						newbean = new LabDetailsBean(LabEntriesParamValue,
								LabEntriesParamDate, LabParameterCodeParamid,
								LabParameterCodeValue);
					}

				}
				INRlabdetails.add(newbean);
			}
		}

		LabDetailsBean newBean1 = null;
		List<LabDetailsBean> PTlabdetail = new ArrayList<LabDetailsBean>();
		if (labparamcode != null & labentriescode != null) {
			for (int i = 0; i < labparamcode.size(); i++) {
				for (int j = 0; j < labentriescode.size(); j++) {
					if (labparamcode
							.get(i)
							.getLabParameterCodeParamid()
							.equals(labentriescode.get(j)
									.getLabEntriesParameterMapid())) {
						LabEntriesParamValue = labentriescode.get(j)
								.getLabEntriesParameterValue();

						Timestamp timeStamp = labentriescode.get(j)
								.getLabEntriesParameterDate();
						java.sql.Date date = new java.sql.Date(
								timeStamp.getTime());
						LabEntriesParamDate = date;
						LabParameterCodeParamid = labparamcode.get(i)
								.getLabParameterCodeParamid();
						LabParameterCodeValue = labparamcode.get(i)
								.getLabParameterCodeValue();
						newBean1 = new LabDetailsBean(LabEntriesParamValue,
								LabEntriesParamDate, LabParameterCodeParamid,
								LabParameterCodeValue);

					}

				}
				PTlabdetail.add(newBean1);
			}
		}

		List<ReminderEvent> reminders = new ArrayList<ReminderEvent>();
		for (int i = 0; i < Loginfo.size(); i++) {
			if (Loginfo.get(i).getWarfarinLogReturnVisitReminderId() != null) {
				ReminderEvent events = ReminderEventRepository
						.findOne(CoumadinFlowSheetSpecification
								.getReminder(Loginfo.get(i)
										.getWarfarinLogReturnVisitReminderId()));
				if (events != null) {
					System.out.println("events:::::::::::::;;;"
							+ events.getReminderEventId());
					reminders.add(events);
				}
			}
		}

		List<LogInfoBean> labDetailsWithReminder = new ArrayList<LogInfoBean>();
		if (reminders.size() != 0) {
			for (int i = 0; i < Loginfo.size(); i++) {
				for (int j = 0; j < reminders.size(); j++) {
					logId = Loginfo.get(i).getWarfarinLogId();
					Count = count;
					warfarinLogEnteredOn = (Date) HUtil.nz(Loginfo.get(i)
							.getWarfarinLogEnteredOn(), defaultDate);

					warfarinLogPerformedDate = (Date) HUtil.nz(Loginfo.get(i)
							.getWarfarinLogPerformedDate(), defaultDate);

					warfarinLogTotalWeeklyDose = HUtil.Nz(Loginfo.get(i)
							.getWarfarinLogTotalWeeklyDose(), "");

					warfarinLogSundayDose = HUtil.Nz(Loginfo.get(i)
							.getWarfarinLogSundayDose(), "");

					warfarinLogMondayDose = HUtil.Nz(Loginfo.get(i)
							.getWarfarinLogMondayDose(), "");

					warfarinLogTuesdayDose = HUtil.Nz(Loginfo.get(i)
							.getWarfarinLogTuesdayDose(), "");
					warfarinLogWednesdayDose = HUtil.Nz(Loginfo.get(i)
							.getWarfarinLogWednesdayDose(), "");
					warfarinLogThursdayDose = HUtil.Nz(Loginfo.get(i)
							.getWarfarinLogThursdayDose(), "");
					warfarinLogFridayDose = HUtil.Nz(Loginfo.get(i)
							.getWarfarinLogFridayDose(), "");
					warfarinLogSaturdayDose = HUtil.Nz(Loginfo.get(i)
							.getWarfarinLogSaturdayDose(), "");
					warfarinLogReturnVisitDate = HUtil.Nz(Loginfo.get(i)
							.getWarfarinLogReturnVisitDate(), "");
					warfarinLogReturnVisitReminderId = Integer
							.parseInt(HUtil
									.Nz(Loginfo
											.get(i)
											.getWarfarinLogReturnVisitReminderId(),
											"0"));
					if (reminders.get(j) != null) {
						if (reminders.get(j).getReminderEventStatus() != null) {
							reminderEventStatus = reminders.get(j)
									.getReminderEventStatus();
						}
					}
					warfarinLogReturnVisitInst = HUtil.Nz(Loginfo.get(i)
							.getWarfarinLogReturnVisitInst(), "");
					warfarinLogComments = HUtil.Nz(Loginfo.get(i)
							.getWarfarinLogComments(), "");
					warfarinLogPatientInformed = Integer.parseInt(HUtil
							.Nz(Loginfo.get(i).getWarfarinLogPatientInformed(),
									"0"));
					if (reminders.get(j) != null) {
						if (reminders.get(j).getReminderEventId() != null)
							warfarinLogStatus = Integer
									.parseInt(HUtil.Nz(Loginfo.get(i)
											.getWarfarinLogStatus(), "0"));
					} else
						warfarinLogStatus = null;
					warfarinLogReviewedOn = (Timestamp) HUtil.nz(Loginfo.get(i)
							.getWarfarinLogReviewedOn(), defaultTimestamp);

					warfarinLogCreatedOn = (Timestamp) HUtil.nz(Loginfo.get(i)
							.getWarfarinLogCreatedOn(), defaultTimestamp);

					// }
				}
				newLogInfo = new LogInfoBean(logId, Count,
						warfarinLogEnteredOn, enteredBy,
						warfarinLogPerformedDate, warfarinLogTotalWeeklyDose,
						warfarinLogSundayDose, warfarinLogMondayDose,
						warfarinLogTuesdayDose, warfarinLogWednesdayDose,
						warfarinLogThursdayDose, warfarinLogFridayDose,
						warfarinLogSaturdayDose, warfarinLogReturnVisitDate,
						warfarinLogReturnVisitReminderId, reminderEventStatus,
						warfarinLogReturnVisitInst, warfarinLogComments,
						warfarinLogPatientInformed, warfarinLogStatus,
						warfarinLogReviewedOn, reviewedBy,
						warfarinLogCreatedOn, modifiedBy, createdBy);
				labDetailsWithReminder.add(newLogInfo);
			}
		}
		LogInfoBean newLogInfo1 = null;
		List<LogInfoBean> labDetailsWithINR = new ArrayList<LogInfoBean>();
		if (reminders.size() != 0) {
			if (labDetailsWithReminder != null & INRlabdetails != null) {
				for (int i = 0; i < labDetailsWithReminder.size(); i++) {
					for (int j = 0; j < INRlabdetails.size(); j++) {

						logId = labDetailsWithReminder.get(i)
								.getWarfarinLogId();
						Count = count;
						warfarinLogEnteredOn = labDetailsWithReminder.get(i)
								.getWarfarinLogEnteredOn();

						warfarinLogPerformedDate = (Date) HUtil.nz(
								labDetailsWithReminder.get(i)
										.getWarfarinLogPerformedDate(),
								defaultDate);
						warfarinLogTotalWeeklyDose = HUtil.Nz(
								labDetailsWithReminder.get(i)
										.getWarfarinLogTotalWeeklyDose(), "");
						warfarinLogSundayDose = HUtil.Nz(labDetailsWithReminder
								.get(i).getWarfarinLogSundayDose(), "");
						warfarinLogMondayDose = HUtil.Nz(labDetailsWithReminder
								.get(i).getWarfarinLogMondayDose(), "");
						warfarinLogTuesdayDose = HUtil.Nz(
								labDetailsWithReminder.get(i)
										.getWarfarinLogTuesdayDose(), "");
						warfarinLogWednesdayDose = HUtil.Nz(
								labDetailsWithReminder.get(i)
										.getWarfarinLogWednesdayDose(), "");
						warfarinLogThursdayDose = HUtil.Nz(
								labDetailsWithReminder.get(i)
										.getWarfarinLogThursdayDose(), "");
						warfarinLogFridayDose = HUtil.Nz(labDetailsWithReminder
								.get(i).getWarfarinLogFridayDose(), "");
						warfarinLogSaturdayDose = HUtil.Nz(
								labDetailsWithReminder.get(i)
										.getWarfarinLogSaturdayDose(), "");
						warfarinLogReturnVisitDate = HUtil.Nz(
								labDetailsWithReminder.get(i)
										.getWarfarinLogReturnVisitDate(), "");
						warfarinLogReturnVisitReminderId = Integer
								.parseInt(HUtil.Nz(labDetailsWithReminder
										.get(i)
										.getWarfarinLogReturnVisitReminderId(),
										"-1"));
						reminderEventStatus = Integer.parseInt(HUtil.Nz(
								labDetailsWithReminder.get(i)
										.getReminderEventStatus(), "-1"));
						warfarinLogReturnVisitInst = HUtil.Nz(
								labDetailsWithReminder.get(i)
										.getWarfarinLogReturnVisitInst(), "");
						warfarinLogComments = HUtil.Nz(labDetailsWithReminder
								.get(i).getWarfarinLogComments(), "0");
						warfarinLogPatientInformed = Integer
								.parseInt(HUtil
										.Nz(labDetailsWithReminder
												.get(i)
												.getWarfarinLogPatientInformed(),
												"-1"));
						warfarinLogStatus = Integer.parseInt(HUtil.Nz(
								labDetailsWithReminder.get(i)
										.getWarfarinLogStatus(), "-1"));
						warfarinLogReviewedOn = (Timestamp) HUtil.nz(
								labDetailsWithReminder.get(i)
										.getWarfarinLogReviewedOn(),
								defaultTimestamp);

						warfarinLogCreatedOn = (Timestamp) HUtil.nz(
								labDetailsWithReminder.get(i)
										.getWarfarinLogCreatedOn(),
								defaultTimestamp);

						INR = HUtil.Nz(INRlabdetails.get(j)
								.getLabEntriesParamValue(), "0");
						newLogInfo1 = new LogInfoBean(logId, Count,
								warfarinLogEnteredOn, enteredBy,
								warfarinLogPerformedDate,
								warfarinLogTotalWeeklyDose,
								warfarinLogSundayDose, warfarinLogMondayDose,
								warfarinLogTuesdayDose,
								warfarinLogWednesdayDose,
								warfarinLogThursdayDose, warfarinLogFridayDose,
								warfarinLogSaturdayDose,
								warfarinLogReturnVisitDate,
								warfarinLogReturnVisitReminderId,
								reminderEventStatus,
								warfarinLogReturnVisitInst,
								warfarinLogComments,
								warfarinLogPatientInformed, warfarinLogStatus,
								warfarinLogReviewedOn, reviewedBy,
								warfarinLogCreatedOn, modifiedBy, createdBy,
								INR);

					}

					labDetailsWithINR.add(newLogInfo1);

				}
			}
		} else {
			if (Loginfo != null & INRlabdetails != null) {
				for (int i = 0; i < Loginfo.size(); i++) {
					for (int j = 0; j < INRlabdetails.size(); j++) {
						logId = Integer.parseInt(HUtil.Nz(Loginfo.get(i)
								.getWarfarinLogId(), "0"));
						Count = count;
						warfarinLogEnteredOn = (Date) HUtil.nz(Loginfo.get(i)
								.getWarfarinLogEnteredOn(), defaultDate);

						warfarinLogPerformedDate = (Date) HUtil.nz(
								Loginfo.get(i).getWarfarinLogPerformedDate(),
								defaultDate);
						warfarinLogTotalWeeklyDose = HUtil.Nz(Loginfo.get(i)
								.getWarfarinLogTotalWeeklyDose(), "");
						warfarinLogSundayDose = HUtil.Nz(Loginfo.get(i)
								.getWarfarinLogSundayDose(), "");
						warfarinLogMondayDose = HUtil.Nz(Loginfo.get(i)
								.getWarfarinLogMondayDose(), "");
						warfarinLogTuesdayDose = HUtil.Nz(Loginfo.get(i)
								.getWarfarinLogTuesdayDose(), "");
						warfarinLogWednesdayDose = HUtil.Nz(Loginfo.get(i)
								.getWarfarinLogWednesdayDose(), "");
						warfarinLogThursdayDose = HUtil.Nz(Loginfo.get(i)
								.getWarfarinLogThursdayDose(), "");
						warfarinLogFridayDose = HUtil.Nz(Loginfo.get(i)
								.getWarfarinLogFridayDose(), "");
						warfarinLogSaturdayDose = HUtil.Nz(Loginfo.get(i)
								.getWarfarinLogSaturdayDose(), "");
						warfarinLogReturnVisitDate = HUtil.Nz(Loginfo.get(i)
								.getWarfarinLogReturnVisitDate(), "");
						warfarinLogReturnVisitReminderId = Integer
								.parseInt(HUtil.Nz(Loginfo.get(i)
										.getWarfarinLogReturnVisitReminderId(),
										"-1"));
						reminderEventStatus = null;
						warfarinLogReturnVisitInst = HUtil.Nz(Loginfo.get(i)
								.getWarfarinLogReturnVisitInst(), "");
						warfarinLogComments = HUtil.Nz(Loginfo.get(i)
								.getWarfarinLogComments(), "");
						warfarinLogPatientInformed = Integer.parseInt(HUtil.Nz(
								Loginfo.get(i).getWarfarinLogPatientInformed(),
								"-1"));
						warfarinLogStatus = Integer.parseInt(HUtil.Nz(Loginfo
								.get(i).getWarfarinLogStatus(), "-1"));
						warfarinLogReviewedOn = (Timestamp) HUtil.nz(Loginfo
								.get(i).getWarfarinLogReviewedOn(),
								defaultTimestamp);

						warfarinLogCreatedOn = (Timestamp) HUtil.nz(Loginfo
								.get(i).getWarfarinLogCreatedOn(),
								defaultTimestamp);

						INR = HUtil.Nz(INRlabdetails.get(j)
								.getLabEntriesParamValue(), "0");
						newLogInfo1 = new LogInfoBean(logId, Count,
								warfarinLogEnteredOn, enteredBy,
								warfarinLogPerformedDate,
								warfarinLogTotalWeeklyDose,
								warfarinLogSundayDose, warfarinLogMondayDose,
								warfarinLogTuesdayDose,
								warfarinLogWednesdayDose,
								warfarinLogThursdayDose, warfarinLogFridayDose,
								warfarinLogSaturdayDose,
								warfarinLogReturnVisitDate,
								warfarinLogReturnVisitReminderId,
								reminderEventStatus,
								warfarinLogReturnVisitInst,
								warfarinLogComments,
								warfarinLogPatientInformed, warfarinLogStatus,
								warfarinLogReviewedOn, reviewedBy,
								warfarinLogCreatedOn, modifiedBy, createdBy,
								INR);

					}

					labDetailsWithINR.add(newLogInfo1);

				}
			}
		}

		LogInfoBean newLogInfo2 = null;
		List<LogInfoBean> labDetailsWithPT = new ArrayList<LogInfoBean>();
		for (int i = 0; i < labDetailsWithINR.size(); i++) {
			for (int j = 0; j < PTlabdetail.size(); j++) {
				// if(labDetailsWithINR.get(i).getWarfarinLogPerformedDate().equals(PTlabdetail.get(j).getLabEntriesParamDate())){

				logId = Integer.parseInt(HUtil.Nz(labDetailsWithINR.get(i)
						.getWarfarinLogId(), "-1"));
				Count = count;
				warfarinLogEnteredOn = (Date) HUtil.nz(labDetailsWithINR.get(i)
						.getWarfarinLogEnteredOn(), defaultDate);

				warfarinLogPerformedDate = (Date) HUtil.nz(labDetailsWithINR
						.get(i).getWarfarinLogPerformedDate(), defaultDate);
				warfarinLogTotalWeeklyDose = HUtil.Nz(labDetailsWithINR.get(i)
						.getWarfarinLogTotalWeeklyDose(), "");
				warfarinLogSundayDose = HUtil.Nz(labDetailsWithINR.get(i)
						.getWarfarinLogSundayDose(), "");
				warfarinLogMondayDose = HUtil.Nz(labDetailsWithINR.get(i)
						.getWarfarinLogMondayDose(), "");
				warfarinLogTuesdayDose = HUtil.Nz(labDetailsWithINR.get(i)
						.getWarfarinLogTuesdayDose(), "");
				warfarinLogWednesdayDose = HUtil.Nz(labDetailsWithINR.get(i)
						.getWarfarinLogWednesdayDose(), "");
				warfarinLogThursdayDose = HUtil.Nz(labDetailsWithINR.get(i)
						.getWarfarinLogThursdayDose(), "");
				warfarinLogFridayDose = HUtil.Nz(labDetailsWithINR.get(i)
						.getWarfarinLogFridayDose(), "");
				warfarinLogSaturdayDose = HUtil.Nz(labDetailsWithINR.get(i)
						.getWarfarinLogSaturdayDose(), "");
				warfarinLogReturnVisitDate = HUtil.Nz(labDetailsWithINR.get(i)
						.getWarfarinLogReturnVisitDate(), "");
				warfarinLogReturnVisitReminderId = Integer.parseInt(HUtil.Nz(
						labDetailsWithINR.get(i)
								.getWarfarinLogReturnVisitReminderId(), "-1"));
				reminderEventStatus = Integer.parseInt(HUtil
						.Nz(labDetailsWithINR.get(i).getReminderEventStatus(),
								"-1"));
				warfarinLogReturnVisitInst = HUtil.Nz(labDetailsWithINR.get(i)
						.getWarfarinLogReturnVisitInst(), "");
				warfarinLogComments = HUtil.Nz(labDetailsWithINR.get(i)
						.getWarfarinLogComments(), "");
				warfarinLogPatientInformed = Integer.parseInt(HUtil.Nz(
						labDetailsWithINR.get(i)
								.getWarfarinLogPatientInformed(), "-1"));
				warfarinLogStatus = Integer.parseInt(HUtil.Nz(labDetailsWithINR
						.get(i).getWarfarinLogStatus(), "-1"));
				warfarinLogReviewedOn = (Timestamp) HUtil.nz(labDetailsWithINR
						.get(i).getWarfarinLogReviewedOn(), defaultTimestamp);

				warfarinLogCreatedOn = (Timestamp) HUtil.nz(labDetailsWithINR
						.get(i).getWarfarinLogCreatedOn(), defaultTimestamp);

				INR = HUtil.Nz(labDetailsWithINR.get(i).getINR(), "0");
				if (PTlabdetail.get(j) != null)
					PT = HUtil.Nz(PTlabdetail.get(j).getLabEntriesParamValue(),
							"");

				// }
				newLogInfo2 = new LogInfoBean(logId, Count,
						warfarinLogEnteredOn, enteredBy,
						warfarinLogPerformedDate, warfarinLogTotalWeeklyDose,
						warfarinLogSundayDose, warfarinLogMondayDose,
						warfarinLogTuesdayDose, warfarinLogWednesdayDose,
						warfarinLogThursdayDose, warfarinLogFridayDose,
						warfarinLogSaturdayDose, warfarinLogReturnVisitDate,
						warfarinLogReturnVisitReminderId, reminderEventStatus,
						warfarinLogReturnVisitInst, warfarinLogComments,
						warfarinLogPatientInformed, warfarinLogStatus,
						warfarinLogReviewedOn, reviewedBy,
						warfarinLogCreatedOn, modifiedBy, createdBy, INR, PT);

			}

			labDetailsWithPT.add(newLogInfo2);

		}

		return labDetailsWithPT;
	}

	/**
	 * Method to get PTINR values
	 */
	@Override
	public List<LabDetailsBean> getPTINR(Integer chartId, java.util.Date date1)
			throws Exception {
		String stringDate = "1111-01-01 00:00:00.000 ";
		Timestamp defaultTimestamp = Timestamp.valueOf(stringDate);
		List<LabParameterCode> labparamcode = LabParameterCodeRepository
				.findAll(CoumadinFlowSheetSpecification.getPTINR1());
		List<LabEntriesParameter> labentriescode = LabEntriesParameterRepository
				.findAll(CoumadinFlowSheetSpecification.getPTINR2(chartId,
						date1));
		List<LabDetailsBean> labdetails = new ArrayList<LabDetailsBean>();
		LabDetailsBean newbean = null;
		if (labparamcode != null & labentriescode != null) {
			for (int i = 0; i < labparamcode.size(); i++) {
				for (int j = 0; j < labentriescode.size(); j++) {
					if (labparamcode
							.get(i)
							.getLabParameterCodeParamid()
							.equals(labentriescode.get(j)
									.getLabEntriesParameterMapid())) {
						String LabEntriesParamValue = HUtil.Nz(labentriescode
								.get(j).getLabEntriesParameterValue(), "");
						Timestamp timeStamp = (Timestamp) HUtil.nz(
								labentriescode.get(j)
										.getLabEntriesParameterDate(),
								defaultTimestamp);
						java.sql.Date date = new java.sql.Date(
								timeStamp.getTime());
						Date LabEntriesParamDate = date;
						Integer LabParameterCodeParamid = Integer
								.parseInt(HUtil.Nz(labparamcode.get(i)
										.getLabParameterCodeParamid(), "-1"));
						String LabParameterCodeValue = HUtil.Nz(labparamcode
								.get(i).getLabParameterCodeValue(), "");
						newbean = new LabDetailsBean(LabEntriesParamValue,
								LabEntriesParamDate, LabParameterCodeParamid,
								LabParameterCodeValue);

					}

				}
				labdetails.add(newbean);
			}
		}

		return labdetails;
	}

	/**
	 * Method to get RecentINR details
	 */
	@Override
	public List<RecentINRBean> getRecentINR(Integer episodeId, Integer chartId,
			Integer patientId) throws Exception {
		List<WarfarinLog> LastRow = WarfarinLogRepository
				.findAll(CoumadinFlowSheetSpecification.getRow(patientId,
						episodeId));
		String dates = "01/01/1900";
		SimpleDateFormat format = new SimpleDateFormat("MM/dd/yyyy");
		java.util.Date performedDate;
		if (LastRow != null)
			performedDate = LastRow.get(0).getWarfarinLogPerformedDate();
		else
			performedDate = format.parse(dates);
		List<LabParameterCode> labparamcode = LabParameterCodeRepository
				.findAll(CoumadinFlowSheetSpecification.getLabParams());
		List<LabEntriesParameter> labentriesparam = LabEntriesParameterRepository
				.findAll(CoumadinFlowSheetSpecification.getRecentINR2(chartId,
						performedDate));
		List<LabEntriesParam_LabParamCodeBean> labdetails = new ArrayList<LabEntriesParam_LabParamCodeBean>();
		if (labparamcode != null & labentriesparam != null) {
			for (int i = 0; i < labparamcode.size(); i++) {
				for (int j = 0; j < labentriesparam.size(); j++) {
					if (labparamcode
							.get(i)
							.getLabParameterCodeParamid()
							.equals(labentriesparam.get(j)
									.getLabEntriesParameterMapid())) {
						String LabEntriesParamValue = labentriesparam.get(j)
								.getLabEntriesParameterValue();

						Integer LabParameterCodeParamid = labparamcode.get(i)
								.getLabParameterCodeParamid();

						String LabParameterCodeValue = labparamcode.get(i)
								.getLabParameterCodeValue();

						Timestamp timeStamp = labentriesparam.get(j)
								.getLabEntriesParameterDate();
						java.sql.Date date = new java.sql.Date(
								timeStamp.getTime());
						Date LabEntriesParamDate = date;

						LabEntriesParam_LabParamCodeBean newbean = new LabEntriesParam_LabParamCodeBean(
								LabEntriesParamValue, LabEntriesParamDate,
								LabParameterCodeParamid, LabParameterCodeValue);
						labdetails.add(newbean);
					}
				}
			}
		}
		List<PatientEpisode> episodedetails = patientEpisodeRepository
				.findAll(CoumadinFlowSheetSpecification.getEpisodes(episodeId));
		List<RecentINRBean> recentINR = new ArrayList<RecentINRBean>();
		if (labdetails != null & episodedetails != null) {
			for (int i = 0; i < labdetails.size(); i++) {

				for (int j = 0; j < episodedetails.size(); j++) {
					if (((labdetails.get(i).getLabEntriesParamDate())
							.equals(episodedetails.get(j)
									.getPatientEpisodeStartDate()))
							|| ((labdetails.get(i).getLabEntriesParamDate())
									.after(episodedetails.get(j)
											.getPatientEpisodeStartDate()))) {
						String LabEntriesParamValue = labdetails.get(i)
								.getLabEntriesParamValue();
						Integer LabParameterCodeParamid = labdetails.get(i)
								.getLabParameterCodeParamid();

						String LabParameterCodeValue = labdetails.get(i)
								.getLabParameterCodeValue();

						Date LabEntriesParamDate = labdetails.get(i)
								.getLabEntriesParamDate();

						RecentINRBean newbean = new RecentINRBean(
								LabEntriesParamValue, LabEntriesParamDate,
								LabParameterCodeParamid, LabParameterCodeValue);
						recentINR.add(newbean);

					}
				}
			}
		}
		return recentINR;

	}

	/**
	 * Method to get indication details
	 */
	@Override
	public List<WarfarinIndication> getIndication(Integer episodeId)
			throws Exception {
		List<WarfarinIndication> indication = WarfarinIndicationRepository
				.findAll(Specifications.where(CoumadinFlowSheetSpecification
						.getIndication(episodeId)));

		return indication;
	}

	/**
	 * Method to check for configurations
	 */
	@Override
	public Boolean checkConfiguration() throws Exception {
		long PTcount = LabParametersRepository
				.count(CoumadinFlowSheetSpecification.getPTCount());
		long INRCount = LabParametersRepository
				.count(CoumadinFlowSheetSpecification.getINRCount());
		if (PTcount == 0 || INRCount == 0) {
			return false;
		}
		return true;

	}

	@Override
	public void createCoumadinReminders(Integer patientId, Integer episodeId,
			Integer userId, java.util.Date date) {
		Calendar calendar = Calendar.getInstance();
		java.util.Date now = calendar.getTime();
		java.sql.Timestamp timestamp = new java.sql.Timestamp(now.getTime());
		java.sql.Timestamp currentTimestamp = new java.sql.Timestamp(
				date.getTime());

		List<WarfarinLog> warfarinlog = WarfarinLogRepository
				.findAll(CoumadinFlowSheetSpecification
						.getWarfarinlog(patientId));
		Integer warfarin_log_return_visit_reminder_id = 0;
		for (int i = 0; i < warfarinlog.size(); i++) {
			if (warfarinlog.get(i).getWarfarinLogReturnVisitReminderId() != null
					&& warfarinlog.get(i).getWarfarinLogReturnVisitReminderId() != -1) {
				warfarin_log_return_visit_reminder_id = warfarinlog.get(i)
						.getWarfarinLogReturnVisitReminderId();
				ReminderEvent events = ReminderEventRepository
						.findOne(Specifications.where(CoumadinFlowSheetSpecification
								.getReminder(warfarin_log_return_visit_reminder_id)));
				events.setReminderEventStatus(1);
				ReminderEventRepository.saveAndFlush(events);

			}
		}
		ReminderEvent eventObj = new ReminderEvent();
		eventObj.setReminderEventSubject("Coumadin log alert");
		eventObj.setReminderEventDescription("Next INR Draw Due");
		eventObj.setReminderEventStatus(0);

		eventObj.setReminderEventDuedate(currentTimestamp);
		eventObj.setReminderEventCreatedby(userId);
		eventObj.setReminderEventCreateddate(timestamp);
		eventObj.setReminderEventModifiedby(userId);
		eventObj.setReminderEventModifieddate(timestamp);
		eventObj.setReminderEventPatientid(patientId);
		eventObj.setReminderEventType(0);
		ReminderEventRepository.saveAndFlush(eventObj);

	}

}
