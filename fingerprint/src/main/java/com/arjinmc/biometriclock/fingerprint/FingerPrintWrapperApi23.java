package com.arjinmc.biometriclock.fingerprint;

import android.content.Context;
import android.hardware.fingerprint.FingerprintManager;
import android.os.Build;

import androidx.annotation.RequiresApi;

/**
 * FingerPrintWrapper for api above 23
 * Created by Eminem Lo on 30/9/2020.
 * email: arjinmc@hotmail.com
 */
@RequiresApi(api = Build.VERSION_CODES.M)
class FingerPrintWrapperApi23 extends AbstractFingerPrintWrapper {

    @Override
    public boolean hasEnrolled(Context context) {
        FingerprintManager fingerprintManager = (FingerprintManager) context.getSystemService(Context.FINGERPRINT_SERVICE);
        return fingerprintManager.hasEnrolledFingerprints();
    }
}
