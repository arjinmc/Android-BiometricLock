package com.arjinmc.biometriclock.fingerprint;

import android.content.Context;
import android.hardware.biometrics.BiometricPrompt;
import android.os.Build;

import androidx.annotation.RequiresApi;

/**
 * FingerPrintWrapper for api above 28
 * Created by Eminem Lo on 30/9/2020.
 * email: arjinmc@hotmail.com
 */
@RequiresApi(api = Build.VERSION_CODES.P)
class FingerPrintWrapperApi28 extends AbstractFingerPrintWrapper {

    @Override
    public boolean hasEnrolled(Context context) {
        BiometricPrompt biometricPrompt = new BiometricPrompt.Builder(context).build();
        return biometricPrompt.getAllowedAuthenticators() > 0;
    }
}
