package com.arjinmc.madao.fingerprint;

import android.content.Context;
import android.content.pm.PackageManager;
import android.hardware.biometrics.BiometricManager;
import android.hardware.biometrics.BiometricPrompt;
import android.hardware.fingerprint.FingerprintManager;
import android.os.Build;

/**
 * Created by Eminem Lo on 2020-01-21.
 * email: arjinmc@hotmail.com
 */
public class FingerPrintWrapper implements IFingerPrintWrapper {

    @Override
    public boolean isSupported(Context context) {
        if (context == null) {
            return false;
        }
        PackageManager packageManager = context.getPackageManager();
        return packageManager.hasSystemFeature(PackageManager.FEATURE_FINGERPRINT);
    }

    @Override
    public boolean isEnable(Context context) {

        if (context == null) {
            return false;
        }

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
            BiometricManager biometricManager = (BiometricManager) context.getSystemService(Context.BIOMETRIC_SERVICE);
            return BiometricManager.BIOMETRIC_SUCCESS == biometricManager.canAuthenticate();
        } else if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.P) {
//            BiometricPrompt biometricPrompt = new BiometricPrompt.Builder(context).build();
//            biometricPrompt.
        } else if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            FingerprintManager fingerprintManager = (FingerprintManager) context.getSystemService(Context.FINGERPRINT_SERVICE);
            return fingerprintManager.hasEnrolledFingerprints();
        }
        return false;
    }
}
