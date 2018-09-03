package com.iab.gdpr_android.consent;

import com.iab.gdpr_android.Bits;
import com.iab.gdpr_android.consent.implementation.v1.ByteBufferBackedVendorConsent;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

import android.util.Base64;

import static com.iab.gdpr_android.ConsentStringConstants.VERSION_BIT_OFFSET;
import static com.iab.gdpr_android.ConsentStringConstants.VERSION_BIT_SIZE;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.anyString;
import static org.powermock.api.mockito.PowerMockito.when;

@RunWith(PowerMockRunner.class)
@PrepareForTest( { Base64.class })
public class VendorConsentDecoderTest {
    @Before
    public void before() {
        // Mock Base64.decode
        PowerMockito.mockStatic(Base64.class);
        when(Base64.encodeToString(any(), anyInt())).thenAnswer(invocation -> java.util.Base64.getUrlEncoder().withoutPadding().encodeToString((byte[]) invocation.getArguments()[0]));
        when(Base64.decode(anyString(), anyInt())).thenAnswer(invocation -> java.util.Base64.getUrlDecoder().decode((String) invocation.getArguments()[0]));
    }

    @Test(expected = IllegalArgumentException.class)
    public void testNullConsentString() {
        // Given: null consent string
        String consentString = null;

        // When: decoder is called
        final VendorConsent vendorConsent = VendorConsentDecoder.fromBase64String(consentString);

        // Then IllegalArgumentException exception is thrown
    }

    @Test(expected = IllegalArgumentException.class)
    public void testNullConsentBytes() {
        // Given: null consent string
        byte[] consentBytes = null;

        // When: decoder is called
        final VendorConsent vendorConsent = VendorConsentDecoder.fromByteArray(consentBytes);

        // Then IllegalArgumentException exception is thrown
    }

    @Test(expected = IllegalArgumentException.class)
    public void testEmptyConsentString() {
        // Given: empty consent string
        String consentString = "";

        // When: decoder is called
        final VendorConsent vendorConsent = VendorConsentDecoder.fromBase64String(consentString);

        // Then IllegalArgumentException exception is thrown

    }

    @Test(expected = IllegalArgumentException.class)
    public void testEmptyConsentBytes() {
        // Given: empty consent string
        byte[] consentBytes = new byte[0];

        // When: decoder is called
        final VendorConsent vendorConsent = VendorConsentDecoder.fromByteArray(consentBytes);

        // Then IllegalArgumentException exception is thrown

    }

    @Test(expected = IllegalStateException.class)
    public void testUnknownVersion() {
        // Given: unknown version number in consent string
        final Bits bits = new Bits(new byte[100]);
        bits.setInt(VERSION_BIT_OFFSET, VERSION_BIT_SIZE, 10);
        String consentString = java.util.Base64.getUrlEncoder().withoutPadding().encodeToString(bits.toByteArray());

        // When: decoder is called
        final VendorConsent vendorConsent = VendorConsentDecoder.fromBase64String(consentString);

        // Then IllegalStateException exception is thrown
    }

    @Test
    public void testVersion1() {
        // Given: version 1 consent string
        final String consentString = "BOOlLqOOOlLqTABABAENAk-AAAAXx7_______9______9uz_Gv_r_f__3nW8_39P3g_7_O3_7m_-zzV48_lrQV1yPAUCgA";

        // When: decoder is called
        final VendorConsent vendorConsent = VendorConsentDecoder.fromBase64String(consentString);

        // Then: v1 ByteBufferVendorConsent is returned
        assertThat(vendorConsent.getClass(),is(ByteBufferBackedVendorConsent.class));

    }

}