package com.iab.gdpr_android.consent;

import com.iab.gdpr_android.Bits;
import com.iab.gdpr_android.consent.implementation.v1.ByteBufferBackedVendorConsent;

import android.util.Base64;

import static com.iab.gdpr_android.ConsentStringConstants.VERSION_BIT_OFFSET;
import static com.iab.gdpr_android.ConsentStringConstants.VERSION_BIT_SIZE;

/**
 * {@link VendorConsent} decoder from Base64 string. Right now only version 1 is know, but eventually
 * this can be extended to support new versions
 */
public class VendorConsentDecoder {
    public static VendorConsent fromBase64String(String consentString) {
        if (isNullOrEmpty(consentString))
            throw new IllegalArgumentException("Null or empty consent string passed as an argument");

        // TODO: Integration test on encoding/decoding (unit tests are mocked)
        return fromByteArray(Base64.decode(consentString, Base64.URL_SAFE | Base64.NO_PADDING));
    }

    public static VendorConsent fromByteArray(byte[] bytes) {
        if (bytes == null || bytes.length == 0)
            throw new IllegalArgumentException("Null or empty consent bytes passed as an argument");

        final Bits bits = new Bits(bytes);
        final int version = getVersion(bits);
        switch (version) {
            case 1:
                return new ByteBufferBackedVendorConsent(bits);
            default:
                throw new IllegalStateException("Unsupported version: " + version);
        }
    }

    /**
     * Get the version field from bitmap
     * @param bits bitmap
     * @return a version number
     */
    private static int getVersion(Bits bits) {
        return bits.getInt(VERSION_BIT_OFFSET, VERSION_BIT_SIZE);
    }

    /**
     * Utility method to check whether string is empty or null
     * @param string value to check
     * @return a boolean value of the check
     */
    private static boolean isNullOrEmpty(String string) {
        return string == null || string.isEmpty();
    }
}
