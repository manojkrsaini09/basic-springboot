package com.metacube.sageclarity.predictable.vo;

import java.io.Serializable;
import java.security.Principal;

public class UserLoginVO implements Serializable {
    private Boolean isAuthenticated = false;
    private String userName;
    private String errorMessage;
    public UserLoginVO(Principal principle){
        if(principle != null){
            this.isAuthenticated = true;
        }else{
            this.isAuthenticated = false;
            this.errorMessage = "Invalid Credentials";
        }
    }

    public Boolean getAuthenticated() {
        return isAuthenticated;
    }

    public void setAuthenticated(Boolean authenticated) {
        isAuthenticated = authenticated;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }
}
