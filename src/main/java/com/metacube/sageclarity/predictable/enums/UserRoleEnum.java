package com.metacube.sageclarity.predictable.enums;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public enum UserRoleEnum {
	SUPER_ADMIN("Super Admin") ,
	USER("User") ,
	ADMIN("Admin");

  private String value;

    public static final Map<String,UserRoleEnum> valueToUserRole = new HashMap<>() ;
    public static final List<String> userRoleList = new ArrayList<String>();
    static {
    	for(UserRoleEnum type:UserRoleEnum.values()) {
    		valueToUserRole.put(type.value, type);
			userRoleList.add(type.value);
    	}
    }

    UserRoleEnum(String val){
        this.value = val;
    }
    
    public static UserRoleEnum getUserRoleFromStringValue(String value) {
    	return valueToUserRole.get(value);
    }

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}
}
