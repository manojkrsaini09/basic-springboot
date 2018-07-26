package com.metacube.sageclarity.predictable.enums;


public enum ExceptionType {
    GENERAL_ERROR(1001, "Server error"),
    NO_DATA_FOUND(1002, "Unable to find data for request parameters"),
    INVALID_METHOD_PARAM(1003, "Invalid Method parameter");

    private final int code;
    private final String message;
    private ExceptionType(int code, String message) {
        this.code = code;
        this.message = message;
    }

    public int getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }
}

