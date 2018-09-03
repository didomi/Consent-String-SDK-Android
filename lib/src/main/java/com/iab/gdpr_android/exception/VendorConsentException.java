package com.iab.gdpr_android.exception;

public class VendorConsentException extends ConsentStringException {
    public VendorConsentException(String message, Throwable cause) {
        super(message, cause);
    }

    public VendorConsentException(String message) {
        super(message);
    }
}
