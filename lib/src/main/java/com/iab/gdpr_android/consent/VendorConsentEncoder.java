package com.iab.gdpr_android.consent;

import android.util.Base64;

/**
 * Encode {@link VendorConsent} to Base64 string
 */
public class VendorConsentEncoder {
    /**
     * Encode vendor consent to Base64 string
     * @param vendorConsent vendor consent
     * @return Base64 encoded string
     */
    public static String toBase64String(VendorConsent vendorConsent) {
        return Base64.encodeToString(vendorConsent.toByteArray(), Base64.URL_SAFE | Base64.NO_PADDING | Base64.NO_WRAP);
    }

}
