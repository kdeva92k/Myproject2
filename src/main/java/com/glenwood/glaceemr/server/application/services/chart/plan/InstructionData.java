package com.glenwood.glaceemr.server.application.services.chart.plan;

import java.util.List;

public class InstructionData {
	
	List<InstructionHighLevelDetailsData> planinstructions= null;
	
	List<InstructionHighLevelDetailsData> anticipatoryguidance=null;

	public List<InstructionHighLevelDetailsData> getPlaninstructions() {
		return planinstructions;
	}

	public void setPlaninstructions(
			List<InstructionHighLevelDetailsData> planinstructions) {
		this.planinstructions = planinstructions;
	}

	public List<InstructionHighLevelDetailsData> getAnticipatoryguidance() {
		return anticipatoryguidance;
	}

	public void setAnticipatoryguidance(
			List<InstructionHighLevelDetailsData> anticipatoryguidance) {
		this.anticipatoryguidance = anticipatoryguidance;
	}
	
	
}
