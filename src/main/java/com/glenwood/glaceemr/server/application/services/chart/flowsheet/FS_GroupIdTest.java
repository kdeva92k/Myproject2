package com.glenwood.glaceemr.server.application.services.chart.flowsheet;

public class FS_GroupIdTest {
	
	private Integer groupId;
	private Integer testId;
	
	public FS_GroupIdTest(){
		super();
	}	
	public FS_GroupIdTest(int grupId, int testId ){
		this.groupId = grupId;
		this.testId = testId;
	}
	
	public Integer getGroupId() {
		return groupId;
	}
	public void setGroupId(Integer groupId) {
		this.groupId = groupId;
	}
	public Integer getTestId() {
		return testId;
	}
	public void setTestId(Integer testId) {
		this.testId = testId;
	}
	
	@Override
	public boolean equals(Object obj) {
		if(obj instanceof FS_GroupIdTest) {
			return ((FS_GroupIdTest)obj).getTestId() == getTestId();
		}
		return super.equals(obj);
	}

}