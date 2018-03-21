package com.glenwood.glaceemr.server.application.services.roomStatus;

public class ActivityBean {


		private Integer moduleid;
		private Integer entityid;
		private String time;
		private Integer userid;
		private String empProfileFullname;
		private String userName;
		private String entityValue;

		public ActivityBean(Integer moduleid,Integer entityid,String time,Integer userid){
			super();
			this.moduleid=moduleid;
			this.entityid=entityid;
			this.time=time;
			this.userid=userid;
		}

		public ActivityBean(Integer entityid,String entityValue) {
			super();
			this.entityid=entityid;
			this.entityValue=entityValue;
		}
		
		public ActivityBean(String userName,Integer userid){
			super();
			this.userid=userid;
			this.userName=userName;
		}
		
		public String getEntityValue() {
			return entityValue;
		}

		public void setEntityValue(String entityValue) {
			this.entityValue = entityValue;
		}

		public String getuserName() {
			return userName;
		}

		public void setUserName(String userName) {
			this.userName = userName;
		}

		public Integer getModuleid() {
			return moduleid;
		}

		public void setModuleid(Integer moduleid) {
			this.moduleid = moduleid;
		}

		public Integer getEntityid() {
			return entityid;
		}

		public void setEntityid(Integer entityid) {
			this.entityid = entityid;
		}

		public String getTime() {
			return time;
		}

		public void setTime(String time) {
			this.time = time;
		}

		public Integer getUserid() {
			return userid;
		}

		public void setUserid(Integer userid) {
			this.userid = userid;
		}

		public String getEmpProfileFullname() {
			return empProfileFullname;
		}

		public void setEmpProfileFullname(String empProfileFullname) {
			this.empProfileFullname = empProfileFullname;
		}
		
		

	}


