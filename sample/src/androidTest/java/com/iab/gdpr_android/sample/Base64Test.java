package com.iab.gdpr_android.sample;

import android.content.Context;
import android.support.test.InstrumentationRegistry;
import android.support.test.runner.AndroidJUnit4;

import com.iab.gdpr_android.consent.VendorConsent;
import com.iab.gdpr_android.consent.VendorConsentDecoder;
import com.iab.gdpr_android.consent.VendorConsentEncoder;

import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.Arrays;
import java.util.Date;
import java.util.HashSet;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.*;

/**
 * Test the base64 encoding/decoding with the real implementation on Android
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
@RunWith(AndroidJUnit4.class)
public class Base64Test {
    @Test
    public void testDecodeAndReencode() {
        // Given: known vendor consent string
        final String consentString = "BOOlLqOOOlLqTABABAENAk-AAAAXx7_______9______9uz_Gv_r_f__3nW8_39P3g_7_O3_7m_-zzV48_lrQV1yPAUCgA";

        // When: vendor consent is constructed
        final VendorConsent vendorConsent = VendorConsentDecoder.fromBase64String(consentString);

        // Then: values match expectation
        assertEquals(VendorConsentEncoder.toBase64String(vendorConsent), consentString);
    }

    @Test
    public void testRealString1() {
        // Given: known vendor consent string
        final String consentString = "BOOlLqOOOlLqTABABAENAk-AAAAXx7_______9______9uz_Gv_r_f__3nW8_39P3g_7_O3_7m_-zzV48_lrQV1yPAUCgA";

        // When: vendor consent is constructed
        final VendorConsent vendorConsent = VendorConsentDecoder.fromBase64String(consentString);

        // Then: values match expectation
        assertEquals(380, vendorConsent.getMaxVendorId());
        assertTrue(vendorConsent.isVendorAllowed(380));
        assertFalse(vendorConsent.isVendorAllowed(379));
    }

    @Test
    public void testRealString2() {
        // Given: known vendor consent string
        final String consentString = "BN5lERiOMYEdiAOAWeFRAAYAAaAAptQ";

        // When: vendor consent is constructed
        final VendorConsent vendorConsent = VendorConsentDecoder.fromBase64String(consentString);

        // Then: values match expectation
        assertThat(vendorConsent.getCmpId(), is(14));
        assertThat(vendorConsent.getCmpVersion(), is(22));
        assertThat(vendorConsent.getConsentLanguage(), is("FR"));
        assertThat(vendorConsent.getConsentRecordCreated(), is(new Date(14924661858L * 100)));
        assertThat(vendorConsent.getConsentRecordLastUpdated(), is(new Date(15240021858L * 100)));
        assertThat(vendorConsent.getAllowedPurposeIds().size(), is(5));
        assertThat(vendorConsent.getAllowedPurposesBits(), is(6291482));

        assertTrue(vendorConsent.isPurposeAllowed(2));
        assertFalse(vendorConsent.isPurposeAllowed(1));
        assertTrue(vendorConsent.isPurposeAllowed(21));
        assertTrue(vendorConsent.isVendorAllowed(1));
        assertTrue(vendorConsent.isVendorAllowed(5));
        assertTrue(vendorConsent.isVendorAllowed(7));
        assertTrue(vendorConsent.isVendorAllowed(9));
        assertFalse(vendorConsent.isVendorAllowed(0));
        assertFalse(vendorConsent.isVendorAllowed(10));
    }

    @Test
    public void testRealString3() {
        // Given: known vendor consent string
        final String consentString = "BN5lERiOMYEdiAKAWXEND1HoSBE6CAFAApAMgBkIDIgM0AgOJxAnQA";

        // When: vendor consent is constructed
        final VendorConsent vendorConsent = VendorConsentDecoder.fromBase64String(consentString);

        // Then: values match expectation
        assertThat(vendorConsent.getCmpId(), is(10));
        assertThat(vendorConsent.getCmpVersion(), is(22));
        assertThat(vendorConsent.getConsentLanguage(), is("EN"));
        assertThat(vendorConsent.getConsentRecordCreated(), is(new Date(14924661858L * 100)));
        assertThat(vendorConsent.getConsentRecordLastUpdated(), is(new Date(15240021858L * 100)));
        assertThat(vendorConsent.getAllowedPurposeIds().size(), is(8));
        assertThat(vendorConsent.getAllowedPurposesBits(), is(2000001));

        assertTrue(vendorConsent.isPurposeAllowed(4));
        assertFalse(vendorConsent.isPurposeAllowed(1));
        assertTrue(vendorConsent.isPurposeAllowed(24));
        assertFalse(vendorConsent.isPurposeAllowed(25));
        assertFalse(vendorConsent.isPurposeAllowed(0));
        assertFalse(vendorConsent.isVendorAllowed(1));
        assertFalse(vendorConsent.isVendorAllowed(3));
        assertTrue(vendorConsent.isVendorAllowed(225));
        assertTrue(vendorConsent.isVendorAllowed(5000));
        assertTrue(vendorConsent.isVendorAllowed(515));
        assertFalse(vendorConsent.isVendorAllowed(0));
        assertFalse(vendorConsent.isVendorAllowed(411));
        assertFalse(vendorConsent.isVendorAllowed(3244));
    }

    @Test
    public void testRealString4() {
        // Given: known vendor consent string
        final String consentString = "BOOMzbgOOQww_AtABAFRAb-AAAsvOA3gACAAkABgArgBaAF0AMAA1gBuAH8AQQBSgCoAL8AYQBigDIAM0AaABpgDYAOYAdgA8AB6gD4AQoAiABFQCMAI6ASABIgCTAEqAJeATIBQQCiAKSAU4BVQCtAK-AWYBaQC2ALcAXMAvAC-gGAAYcAxQDGAGQAMsAZsA0ADTAGqANcAbMA4ADjAHKAOiAdQB1gDtgHgAeMA9AD2AHzAP4BAACBAEEAIbAREBEgCKQEXARhZeYA";

        // When: vendor consent is constructed
        final VendorConsent vendorConsent = VendorConsentDecoder.fromBase64String(consentString);

        // Then: values match expectation
        assertThat(vendorConsent.getCmpId(), is(45));
        assertThat(vendorConsent.getCmpVersion(), is(1));
        assertThat(vendorConsent.getConsentLanguage(), is("FR"));
        assertThat(vendorConsent.getConsentRecordCreated(), is(new Date(15270622944L * 100)));
        assertThat(vendorConsent.getConsentRecordLastUpdated(), is(new Date(15271660607L * 100)));
        assertThat(vendorConsent.getAllowedPurposeIds().size(), is(5));

        assertTrue(vendorConsent.isPurposeAllowed(1));
        assertTrue(vendorConsent.isPurposeAllowed(2));
        assertTrue(vendorConsent.isPurposeAllowed(3));
        assertTrue(vendorConsent.isPurposeAllowed(4));
        assertTrue(vendorConsent.isPurposeAllowed(5));
        assertFalse(vendorConsent.isPurposeAllowed(6));
        assertFalse(vendorConsent.isPurposeAllowed(25));
        assertFalse(vendorConsent.isPurposeAllowed(0));
        assertTrue(vendorConsent.isVendorAllowed(1));
        assertFalse(vendorConsent.isVendorAllowed(5));
        assertTrue(vendorConsent.isVendorAllowed(45));
        assertFalse(vendorConsent.isVendorAllowed(47));
        assertFalse(vendorConsent.isVendorAllowed(146));
        assertTrue(vendorConsent.isVendorAllowed(147));
    }

    @Test
    public void testRealString5() {
        // Given: known vendor consent string
        final String consentString = "BONZt-1ONZt-1AHABBENAO-AAAAHCAEAASABmADYAOAAeA";

        // When: vendor vendorConsent is constructed
        final VendorConsent vendorConsent = VendorConsentDecoder.fromBase64String(consentString);

        // Then: values match expectation
        assertTrue(vendorConsent.isPurposeAllowed(1));
        assertTrue(vendorConsent.isPurposeAllowed(3));
        assertTrue(vendorConsent.isVendorAllowed(28));
        assertFalse(vendorConsent.isVendorAllowed(1));
        assertFalse(vendorConsent.isVendorAllowed(3));
        assertTrue(vendorConsent.isVendorAllowed(27));
    }

    @Test
    public void testRealString6() {
        // Given: known vendor consent string
        final String consentString = "BOOj_adOOj_adABABADEAb-AAAA-iATAAUAA2ADAAMgAgABIAC0AGQANAAcAA-ACKAEwAKIAaABFACQAHIAP0B9A";

        // When: vendor vendorConsent is constructed
        final VendorConsent vendorConsent = VendorConsentDecoder.fromBase64String(consentString);

        // Then: values match expectation
        assertThat(vendorConsent.getVersion(),is(1));
        assertThat(vendorConsent.getConsentRecordCreated(),is(new Date(1527670134100L)));
        assertThat(vendorConsent.getConsentRecordLastUpdated(),is(new Date(1527670134100L)));
        assertThat(vendorConsent.getCmpId(),is(1));
        assertThat(vendorConsent.getCmpVersion(),is(1));
        assertThat(vendorConsent.getConsentScreen(),is(0));
        assertThat(vendorConsent.getConsentLanguage(),is("DE"));
        assertThat(vendorConsent.getAllowedPurposeIds(),is(new HashSet<>(Arrays.asList(1,2,3,4,5))));
        assertThat(vendorConsent.getMaxVendorId(),is(1000));
        assertTrue(vendorConsent.isVendorAllowed(10));
        assertTrue(vendorConsent.isVendorAllowed(13));
        assertTrue(vendorConsent.isVendorAllowed(24));
        assertTrue(vendorConsent.isVendorAllowed(25));
        assertTrue(vendorConsent.isVendorAllowed(32));
        assertTrue(vendorConsent.isVendorAllowed(36));
        assertTrue(vendorConsent.isVendorAllowed(45));
        assertTrue(vendorConsent.isVendorAllowed(50));
        assertTrue(vendorConsent.isVendorAllowed(52));
        assertTrue(vendorConsent.isVendorAllowed(56));
        assertTrue(vendorConsent.isVendorAllowed(62));
        assertTrue(vendorConsent.isVendorAllowed(69));
        assertTrue(vendorConsent.isVendorAllowed(76));
        assertTrue(vendorConsent.isVendorAllowed(81));
        assertTrue(vendorConsent.isVendorAllowed(104));
        assertTrue(vendorConsent.isVendorAllowed(138));
        assertTrue(vendorConsent.isVendorAllowed(144));
        assertTrue(vendorConsent.isVendorAllowed(228));
        assertTrue(vendorConsent.isVendorAllowed(253));
        assertTrue(vendorConsent.isVendorAllowed(1000));
    }
}
