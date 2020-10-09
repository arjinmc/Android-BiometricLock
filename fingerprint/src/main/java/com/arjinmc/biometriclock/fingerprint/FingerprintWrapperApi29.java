package com.arjinmc.biometriclock.fingerprint;

import android.content.Context;
import android.hardware.biometrics.BiometricManager;
import android.os.Build;

import androidx.annotation.RequiresApi;

/**
 * FingerprintWrapper for api above 29
 * Created by Eminem Lo on 30/9/2020.
 * email: arjinmc@hotmail.com
 */
@RequiresApi(api = Build.VERSION_CODES.Q)
class FingerprintWrapperApi29 extends AbstractFingerprintWrapper {

    public FingerprintWrapperApi29(Context context) {
        super(context);
    }

    @Override
    public boolean hasEnrolled() {
        BiometricManager biometricManager = (BiometricManager) getContext().getSystemService(Context.BIOMETRIC_SERVICE);
        return BiometricManager.BIOMETRIC_SUCCESS == biometricManager.canAuthenticate();
    }
}
