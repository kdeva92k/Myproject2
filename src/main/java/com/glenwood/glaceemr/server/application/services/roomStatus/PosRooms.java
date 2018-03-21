package com.glenwood.glaceemr.server.application.services.roomStatus;

public class PosRooms {

		private Short roomId;
		private Integer posId;
		private String posName;
		private String serName;
		private String patName;
		private String refName;
		private Integer alertEventCategoryId;
		private String checkIn_date;
		private String qwt;
		private Integer patId;

		public  PosRooms(Short roomId,Integer posId,String posName,String serName,String patName,Integer patId,String refName,Integer alertEventCategoryId,String checkIn_date, String qwt) {
	        super();
			this.roomId = roomId;
			this.posId = posId;
			this.posName = posName;
			this.serName = serName;
			this.patId = patId;
			this.patName = patName;
			this.refName = refName;
			this.alertEventCategoryId = alertEventCategoryId;
			this.checkIn_date = checkIn_date;
			this.qwt = qwt;
		}
		public PosRooms(Integer patId,String patName){
			super();
			this.patId = patId;
			this.patName = patName;
		}
		
		public String getPatName() {
			return patName;
		}
		public void setPatName(String patName) {
			this.patName = patName;
		}
		public Integer getPatId() {
			return patId;
		}
		public void setPatId(Integer patId) {
			this.patId = patId;
		}
		public Short getRoomId() {
			return roomId;
		}

		public void setRoomId(Short roomId) {
			this.roomId = roomId;
		}

		public Integer getPosId() {
			return posId;
		}

		public void setPosId(Integer posId) {
			this.posId = posId;
		}

		public String getPosName() {
			return posName;
		}

		public void setPosName(String posName) {
			this.posName = posName;
		}

		public String getSerName() {
			return serName;
		}

		public void setSerName(String serName) {
			this.serName = serName;
		}

		public String getRefName() {
			return refName;
		}

		public void setRefName(String refName) {
			this.refName = refName;
		}

		public Integer getAlertEventCategoryId() {
			return alertEventCategoryId;
		}

		public void setAlertEventCategoryId(Integer alertEventCategoryId) {
			this.alertEventCategoryId = alertEventCategoryId;
		}

		public String getCheckIn_date() {
			return checkIn_date;
		}

		public void setCheckIn_date(String checkIn_date) {
			this.checkIn_date = checkIn_date;
		}

		public String getQwt() {
			return qwt;
		}

		public void setQwt(String qwt) {
			this.qwt = qwt;
		}
		
	}


