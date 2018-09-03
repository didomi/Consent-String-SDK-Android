package com.iab.gdpr_android.sample;

import android.app.Application;
import android.util.Log;

import com.iab.gdpr_android.consent.VendorConsent;
import com.iab.gdpr_android.consent.VendorConsentDecoder;

public class SampleApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();

        VendorConsent consent = VendorConsentDecoder.fromBase64String("BOOlLqOOOlLqTABABAENAk-AAAAXx7_______9______9uz_Gv_r_f__3nW8_39P3g_7_O3_7m_-zzV48_lrQV1yPAUCgA");
        String consentString = consent.toString();
        Log.e("Sample", "VendorConsent");
    }
}
