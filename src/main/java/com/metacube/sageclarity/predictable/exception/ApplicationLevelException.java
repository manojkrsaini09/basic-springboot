package com.metacube.sageclarity.predictable.exception;

public class ApplicationLevelException extends Exception {
    public ApplicationLevelException(String message) {
        super(message);
    }
    public ApplicationLevelException(String message, Throwable cause) {
        super(message, cause);
    }
}
