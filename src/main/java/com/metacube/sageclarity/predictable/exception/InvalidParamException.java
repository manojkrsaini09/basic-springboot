package com.metacube.sageclarity.predictable.exception;

public class InvalidParamException extends ApplicationLevelException {

    public InvalidParamException(String message, Exception e) {
        super(message, e);
    }
    public InvalidParamException(String message) {
        super(message);
    }
}
