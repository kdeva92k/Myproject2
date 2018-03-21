package com.glenwood.glaceemr.server.application.services.chart.skintest;

import java.util.List;


public class SkinTestOrderSaveJSON {
	Integer loginId;
	Integer orderId;
	Boolean isOrderEntry;
	String patientId;
	String encounterId;
	Integer skinTestShortcutId;
	Integer status;
	String startDate;
	Integer technician;
	Boolean prickFlag;
	Boolean intradermalFlag;
	List<String> concentrationList;
	String dxCode1;
	String dxDesc1;
	String dxCode2;
	String dxDesc2;
	String dxCode3;
	String dxDesc3;
	String dxCode4;
	String dxDesc4;
	String dxCode5;
	String dxDesc5;
	String dxCode6;
	String dxDesc6;
	String dxCode7;
	String dxDesc7;
	String dxCode8;
	String dxDesc8;
	String dxCode9;
	String dxDesc9;
	String dxCode10;
	String dxDesc10;
	String dxCode11;
	String dxDesc11;
	String dxCode12;
	String dxDesc12;
	String dxCodeSystem;
	Integer units;
	Integer testDetailId;
	Integer serviceDoctor;
	Integer pos;
	String orderNotes;
	String defaultReadValue;
	/*String diluentWhealValue;
	String diluentFlareValue;
	String diluentGrade;
	String diluentErythema;
	String diluentPseudopodia;
	String histamineWhealValue;
	String histamineFlareValue;
	String histamineGrade;
	String histamineErythema;
	String histaminePseudopodia;
	List<SkinTestResultJSON> results;*/
	
	public Integer getLoginId() {
		return loginId;
	}
	public void setLoginId(Integer loginId) {
		this.loginId = loginId;
	}
	public Integer getOrderId() {
		return orderId;
	}
	public void setOrderId(Integer orderId) {
		this.orderId = orderId;
	}
	public Boolean getIsOrderEntry() {
		return isOrderEntry;
	}
	public void setIsOrderEntry(Boolean isOrderEntry) {
		this.isOrderEntry = isOrderEntry;
	}
	public String getPatientId() {
		return patientId;
	}
	public void setPatientId(String patientId) {
		this.patientId = patientId;
	}
	public String getEncounterId() {
		return encounterId;
	}
	public void setEncounterId(String encounterId) {
		this.encounterId = encounterId;
	}
	public Integer getSkinTestShortcutId() {
		return skinTestShortcutId;
	}
	public void setSkinTestShortcutId(Integer skinTestShortcutId) {
		this.skinTestShortcutId = skinTestShortcutId;
	}
	public Integer getStatus() {
		return status;
	}
	public void setStatus(Integer status) {
		this.status = status;
	}
	public String getStartDate() {
		return startDate;
	}
	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}
	public Integer getTechnician() {
		return technician;
	}
	public void setTechnician(Integer technician) {
		this.technician = technician;
	}
	public Boolean getPrickFlag() {
		return prickFlag;
	}
	public void setPrickFlag(Boolean prickFlag) {
		this.prickFlag = prickFlag;
	}
	public Boolean getIntradermalFlag() {
		return intradermalFlag;
	}
	public void setIntradermalFlag(Boolean intradermalFlag) {
		this.intradermalFlag = intradermalFlag;
	}
	public String getDxCode1() {
		return dxCode1;
	}
	public void setDxCode1(String dxCode1) {
		this.dxCode1 = dxCode1;
	}
	public String getDxDesc1() {
		return dxDesc1;
	}
	public void setDxDesc1(String dxDesc1) {
		this.dxDesc1 = dxDesc1;
	}
	public String getDxCode2() {
		return dxCode2;
	}
	public void setDxCode2(String dxCode2) {
		this.dxCode2 = dxCode2;
	}
	public String getDxDesc2() {
		return dxDesc2;
	}
	public void setDxDesc2(String dxDesc2) {
		this.dxDesc2 = dxDesc2;
	}
	public String getDxCode3() {
		return dxCode3;
	}
	public void setDxCode3(String dxCode3) {
		this.dxCode3 = dxCode3;
	}
	public String getDxDesc3() {
		return dxDesc3;
	}
	public void setDxDesc3(String dxDesc3) {
		this.dxDesc3 = dxDesc3;
	}
	public String getDxCode4() {
		return dxCode4;
	}
	public void setDxCode4(String dxCode4) {
		this.dxCode4 = dxCode4;
	}
	public String getDxDesc4() {
		return dxDesc4;
	}
	public void setDxDesc4(String dxDesc4) {
		this.dxDesc4 = dxDesc4;
	}
	public Integer getTestDetailId() {
		return testDetailId;
	}
	public void setTestDetailId(Integer testDetailId) {
		this.testDetailId = testDetailId;
	}
	/*public String getDiluentWhealValue() {
		return diluentWhealValue;
	}
	public void setDiluentWhealValue(String diluentWhealValue) {
		this.diluentWhealValue = diluentWhealValue;
	}
	public String getDiluentFlareValue() {
		return diluentFlareValue;
	}
	public void setDiluentFlareValue(String diluentFlareValue) {
		this.diluentFlareValue = diluentFlareValue;
	}
	public String getDiluentGrade() {
		return diluentGrade;
	}
	public void setDiluentGrade(String diluentGrade) {
		this.diluentGrade = diluentGrade;
	}
	public String getDiluentErythema() {
		return diluentErythema;
	}
	public void setDiluentErythema(String diluentErythema) {
		this.diluentErythema = diluentErythema;
	}
	public String getDiluentPseudopodia() {
		return diluentPseudopodia;
	}
	public void setDiluentPseudopodia(String diluentPseudopodia) {
		this.diluentPseudopodia = diluentPseudopodia;
	}
	public String getHistamineWhealValue() {
		return histamineWhealValue;
	}
	public void setHistamineWhealValue(String histamineWhealValue) {
		this.histamineWhealValue = histamineWhealValue;
	}
	public String getHistamineFlareValue() {
		return histamineFlareValue;
	}
	public void setHistamineFlareValue(String histamineFlareValue) {
		this.histamineFlareValue = histamineFlareValue;
	}
	public String getHistamineGrade() {
		return histamineGrade;
	}
	public void setHistamineGrade(String histamineGrade) {
		this.histamineGrade = histamineGrade;
	}
	public String getHistamineErythema() {
		return histamineErythema;
	}
	public void setHistamineErythema(String histamineErythema) {
		this.histamineErythema = histamineErythema;
	}
	public String getHistaminePseudopodia() {
		return histaminePseudopodia;
	}
	public void setHistaminePseudopodia(String histaminePseudopodia) {
		this.histaminePseudopodia = histaminePseudopodia;
	}
	public List<SkinTestResultJSON> getResults() {
		return results;
	}
	public void setResults(List<SkinTestResultJSON> results) {
		this.results = results;
	}*/
	public Integer getServiceDoctor() {
		return serviceDoctor;
	}
	public void setServiceDoctor(Integer serviceDoctor) {
		this.serviceDoctor = serviceDoctor;
	}
	public Integer getPos() {
		return pos;
	}
	public void setPos(Integer pos) {
		this.pos = pos;
	}
	public List<String> getConcentrationList() {
		return concentrationList;
	}
	public void setConcentrationList(List<String> concentrationList) {
		this.concentrationList = concentrationList;
	}
	public String getDxCode5() {
		return dxCode5;
	}
	public void setDxCode5(String dxCode5) {
		this.dxCode5 = dxCode5;
	}
	public String getDxDesc5() {
		return dxDesc5;
	}
	public void setDxDesc5(String dxDesc5) {
		this.dxDesc5 = dxDesc5;
	}
	public String getDxCode6() {
		return dxCode6;
	}
	public void setDxCode6(String dxCode6) {
		this.dxCode6 = dxCode6;
	}
	public String getDxDesc6() {
		return dxDesc6;
	}
	public void setDxDesc6(String dxDesc6) {
		this.dxDesc6 = dxDesc6;
	}
	public String getDxCode7() {
		return dxCode7;
	}
	public void setDxCode7(String dxCode7) {
		this.dxCode7 = dxCode7;
	}
	public String getDxDesc7() {
		return dxDesc7;
	}
	public void setDxDesc7(String dxDesc7) {
		this.dxDesc7 = dxDesc7;
	}
	public String getDxCode8() {
		return dxCode8;
	}
	public void setDxCode8(String dxCode8) {
		this.dxCode8 = dxCode8;
	}
	public String getDxDesc8() {
		return dxDesc8;
	}
	public void setDxDesc8(String dxDesc8) {
		this.dxDesc8 = dxDesc8;
	}
	public String getDxCode9() {
		return dxCode9;
	}
	public void setDxCode9(String dxCode9) {
		this.dxCode9 = dxCode9;
	}
	public String getDxDesc9() {
		return dxDesc9;
	}
	public void setDxDesc9(String dxDesc9) {
		this.dxDesc9 = dxDesc9;
	}
	public String getDxCode10() {
		return dxCode10;
	}
	public void setDxCode10(String dxCode10) {
		this.dxCode10 = dxCode10;
	}
	public String getDxDesc10() {
		return dxDesc10;
	}
	public void setDxDesc10(String dxDesc10) {
		this.dxDesc10 = dxDesc10;
	}
	public String getDxCode11() {
		return dxCode11;
	}
	public void setDxCode11(String dxCode11) {
		this.dxCode11 = dxCode11;
	}
	public String getDxDesc11() {
		return dxDesc11;
	}
	public void setDxDesc11(String dxDesc11) {
		this.dxDesc11 = dxDesc11;
	}
	public String getDxCode12() {
		return dxCode12;
	}
	public void setDxCode12(String dxCode12) {
		this.dxCode12 = dxCode12;
	}
	public String getDxDesc12() {
		return dxDesc12;
	}
	public void setDxDesc12(String dxDesc12) {
		this.dxDesc12 = dxDesc12;
	}
	public String getDxCodeSystem() {
		return dxCodeSystem;
	}
	public void setDxCodeSystem(String dxCodeSystem) {
		this.dxCodeSystem = dxCodeSystem;
	}
	public Integer getUnits() {
		return units;
	}
	public void setUnits(Integer units) {
		this.units = units;
	}
	public String getOrderNotes() {
		return orderNotes;
	}
	public void setOrderNotes(String orderNotes) {
		this.orderNotes = orderNotes;
	}
	public String getDefaultReadValue() {
		return defaultReadValue;
	}
	public void setDefaultReadValue(String defaultReadValue) {
		this.defaultReadValue = defaultReadValue;
	}

}