package com.iab.gdpr_android.exception;

public class ConsentStringException extends RuntimeException {
    public ConsentStringException(String message, Throwable cause) {
        super(message, cause);
    }

    public ConsentStringException(String message) {
        super(message);
    }
}
