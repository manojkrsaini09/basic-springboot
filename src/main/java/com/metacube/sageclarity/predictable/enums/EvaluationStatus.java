package com.metacube.sageclarity.predictable.enums;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public enum EvaluationStatus {
	UPLOADED("Uploaded") ,
	PREDICTIONS_IN_PROGRESS("Predictions in  progress"),
	PREDICTIONS_MADE("Predictions Made"),
	ERROR("Error");

  private String value;

    public static final Map<String,EvaluationStatus> valueToEvaluationStatus = new HashMap<>() ;
    public static final List<String> evaluationStatusList = new ArrayList<String>();
    static {
    	for(EvaluationStatus status:EvaluationStatus.values()) {
    		valueToEvaluationStatus.put(status.value, status);
			evaluationStatusList.add(status.value);
    	}
    }

    EvaluationStatus(String val){
        this.value = val;
    }
    
    public static EvaluationStatus getEvaluationStatusFromStringValue(String value) {
    	return valueToEvaluationStatus.get(value);
    }

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}
}
