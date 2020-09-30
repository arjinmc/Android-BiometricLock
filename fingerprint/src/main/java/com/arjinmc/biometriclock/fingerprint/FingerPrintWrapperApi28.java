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

    public FingerPrintWrapperApi28(Context context) {
        super(context);
    }

    @Override
    public boolean hasEnrolled() {
        BiometricPrompt biometricPrompt = new BiometricPrompt.Builder(getContext()).build();
        return biometricPrompt.getAllowedAuthenticators() > 0;
    }
}
