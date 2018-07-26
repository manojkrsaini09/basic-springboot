package com.metacube.sageclarity.predictable.enums;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public enum ObjectType {
	OBJECT1("Object1") ,
	OBJECT2("Object2");

  private String value;

    public static final Map<String,ObjectType> valueToObjectType = new HashMap<>() ;
    public static final List<String> objectTypeList = new ArrayList<String>();
    static {
    	for(ObjectType type:ObjectType.values()) {
    		valueToObjectType.put(type.value, type);
			objectTypeList.add(type.value);
    	}
    }

    ObjectType(String val){
        this.value = val;
    }
    
    public static ObjectType getObjectTypeFromStringValue(String value) {
    	return valueToObjectType.get(value);
    }

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}
}
