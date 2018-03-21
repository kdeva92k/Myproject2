package com.glenwood.glaceemr.server.application.services.chart.flowsheet;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

public class FS_LabBean implements Comparable<FS_LabBean> {

	private String islab;
	private String labTestId;
	private String labName;
	private String orderedOn;
	private String performedOn;
	private String status;
	private String resultNotes;
	private String gender;
	private String due;
	private String dueCriteria;
	private String testDetailId;
	private String completedDetaildId;
	private String completedIdStatus;
	private String performdatesort;
	private Integer groupId;

	public FS_LabBean() {
		super();
	}

	public FS_LabBean(Date performedOn, int labEntriesTestdetailId,
			int labEntriesTestId, String labEntriesTestDesc,
			Date labEntriesOrdOn, Date labEntriesPerfOn,
			String labEntriesResultNotes, int labEntriesTestStatus) {
		this.labTestId = labEntriesTestId + "";
		this.labName = labEntriesTestDesc;
		// this.orderedOn = new
		// SimpleDateFormat("MM/dd/yyyy").format(labEntriesOrdOn);
		if (performedOn != null) {
			this.performedOn = new SimpleDateFormat("MM/dd/yyyy")
					.format(performedOn);
		} else {
			this.performedOn = "";
		}
		this.testDetailId = labEntriesTestdetailId + "";
		this.status = labEntriesTestStatus + "";
		this.resultNotes = labEntriesResultNotes.replaceAll("\r", "");
		if (labEntriesOrdOn != null) {
			this.orderedOn = new SimpleDateFormat("MM/dd/yyyy")
					.format(labEntriesOrdOn);
			this.performdatesort = new SimpleDateFormat("MM/dd/yyyy")
					.format(labEntriesOrdOn);
		} else {
			this.orderedOn = "";
			this.performdatesort = "";
		}

	}

	public FS_LabBean(Date performedOn, int labEntriesTestId,
			String labEntriesResultNotes, String labEntriesTestDesc) {
		this.labTestId = labEntriesTestId + "";
		this.labName = labEntriesTestDesc;
		if (performedOn != null) {
			this.orderedOn = new SimpleDateFormat("MM/dd-yyyy")
					.format(performedOn);
			this.performedOn = new SimpleDateFormat("MM/dd/yyyy")
					.format(performedOn);
		} else {
			this.orderedOn = "";
			this.performedOn = "";
		}
		this.status = "3";
		this.resultNotes = labEntriesResultNotes.replaceAll("\r", "");;

	}

	public FS_LabBean(Date performedOn, String labEntriesResultNotes,
			String labEntriesTestId) {
		this.labTestId = labEntriesTestId;
		this.labName = "";
		if (performedOn != null) {
			this.orderedOn = new SimpleDateFormat("MM/dd/yyyy")
					.format(performedOn);
			this.performedOn = new SimpleDateFormat("MM/dd/yyyy")
					.format(performedOn);
		} else {
			this.orderedOn = "";
			this.performedOn = "";
		}
		this.status = "8";
		this.resultNotes = labEntriesResultNotes.replaceAll("\r", "");;

	}

	public String gettestDetailId() {
		return testDetailId;
	}

	public String getCompletedDetaildId() {
		return completedDetaildId;
	}

	public String getCompletedIdStatus() {
		return completedIdStatus;
	}

	public void settestDetailId(ArrayList<Integer> testDetailIdsTemp) {
		try {
			StringBuilder testDetailIdstr = new StringBuilder();
			for (int counter = 0; counter < testDetailIdsTemp.size(); counter++) {
				if (counter == (testDetailIdsTemp.size() - 1))
					testDetailIdstr.append(testDetailIdsTemp.get(counter)
							.toString());
				else
					testDetailIdstr.append(testDetailIdsTemp.get(counter)
							.toString() + ",");
			}
			this.testDetailId = testDetailIdstr.toString();
		} catch (Exception e) {
			this.testDetailId = "";
			e.printStackTrace();
		}
	}

	public void setCompletedDetaildId(List<String> testIds) {
		try {
			StringBuilder completedDetailIdstr = new StringBuilder();
			List<Integer> CompStatus = new ArrayList<Integer>();
			for (int counter = 0; counter < testIds.size(); counter++) {
				if (counter == (testIds.size() - 1)) {
					completedDetailIdstr.append(testIds.get(counter).toString()
							.split("@#@")[0]);
					CompStatus.add(Integer.parseInt(testIds.get(counter).toString().split("@#@")[1]));
				} else {
					
					completedDetailIdstr.append(testIds.get(counter).toString().split("@#@")[0]
							+ "_");
					CompStatus.add(Integer.parseInt(testIds.get(counter).toString().split("@#@")[1]));
				}
			}
			this.completedDetaildId = completedDetailIdstr.toString();
			
			if (CompStatus.size() > 0){
				String[] testIdArr = completedDetailIdstr.toString().split("_");
				List<Integer> testIdList = new ArrayList<Integer>();
				
				for(int b=0;b<testIdArr.length;b++){
					int testid = Integer.parseInt(testIdArr[b]);
					testIdList.add(testid);
				}
				int maxTestId = Collections.max(testIdList);
				
				for (int counter = 0; counter < testIds.size(); counter++) {
						int cmpTestId = Integer.parseInt(testIds.get(counter).toString().split("@#@")[0]);
						if(cmpTestId==maxTestId){
							this.completedIdStatus=testIds.get(counter).toString().split("@#@")[1];
						}
				}
				
				//this.completedIdStatus = Collections.max(CompStatus) + "";
			}
				
			else
				this.completedIdStatus = "";
		} catch (Exception e) {
			this.completedDetaildId = "";
			this.completedIdStatus = "";
			e.printStackTrace();
		}
	}

	public String getLabName() {
		return labName;
	}

	public void setLabName(String labName) {
		this.labName = labName;
	}

	public String getOrderedOn() {
		return orderedOn;
	}

	public void setOrderedOn(String orderedOn) {
		this.orderedOn = orderedOn;
	}

	public String getPerformedOn() {
		return performedOn;
	}

	public void setPerformedOn(String performedOn) {
		this.performedOn = performedOn;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getResultNotes() {
		return resultNotes;
	}

	public void setResultNotes(String resultNotes) {
		this.resultNotes = resultNotes;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getDue() {
		return due;
	}

	public void setDue(String due) {
		this.due = due;
	}

	public String getDueCriteria() {
		return dueCriteria;
	}

	public void setDueCriteria(String dueCriteria) {
		this.dueCriteria = dueCriteria;
	}

	public String getLabTestId() {
		return labTestId;
	}

	public void setLabTestId(String labTestId) {
		this.labTestId = labTestId;
	}

	public String getPerformdatesort() {
		return performdatesort;
	}

	public void setPerformdatesort(String performdatesort) {
		this.performdatesort = performdatesort;
	}

	public void setTestDetailId(String testDetailId) {
		this.testDetailId = testDetailId;
	}

	public void setCompletedDetaildId(String completedDetaildId) {
		this.completedDetaildId = completedDetaildId;
	}

	public void setCompletedIdStatus(String completedIdStatus) {
		this.completedIdStatus = completedIdStatus;
	}

	public Integer getGroupId() {
		return groupId;
	}

	public void setGroupId(Integer groupId) {
		this.groupId = groupId;
	}

	public String getIslab() {
		return islab;
	}

	public void setIslab(String islab) {
		this.islab = islab;
	}

	@Override
	public int compareTo(FS_LabBean o) {

		if (o != null && o instanceof FS_LabBean) {
			SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy");
			try {

				if (!"".equalsIgnoreCase(o.getPerformedOn())&& !"".equalsIgnoreCase(performedOn)) {
					Date date1 = sdf.parse(performedOn);
					Date date2 = sdf.parse(o.getPerformedOn());
					return date2.compareTo(date1);
				}
			} catch (ParseException e) {
				// e.printStackTrace();
			}
		}
		return -1;

	}
}