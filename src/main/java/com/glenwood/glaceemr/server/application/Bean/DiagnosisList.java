package com.glenwood.glaceemr.server.application.Bean;

import java.util.HashMap;
import java.util.List;

public class DiagnosisList {

	public HashMap<String, HashMap<String, List<Object>>> diagnosisList;
	
	public HashMap<String, HashMap<String, List<Object>>> getDiagnosisList() {
		return diagnosisList;
	}

	public void setDiagnosisList(HashMap<String, HashMap<String, List<Object>>> diagnosisList) {
		this.diagnosisList = diagnosisList;
	}

}
