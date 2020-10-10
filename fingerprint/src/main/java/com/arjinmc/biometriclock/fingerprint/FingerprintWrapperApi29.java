package com.arjinmc.biometriclock.fingerprint;

import android.content.Context;
import android.hardware.biometrics.BiometricManager;
import android.os.Build;

import androidx.annotation.RequiresApi;

import com.arjinmc.biometriclock.fingerprint.model.FingerprintEnrollStatus;

/**
 * FingerprintWrapper for api above 29
 * Created by Eminem Lo on 30/9/2020.
 * email: arjinmc@hotmail.com
 */
@RequiresApi(api = Build.VERSION_CODES.Q)
class FingerprintWrapperApi29 extends AbstractFingerprintWrapper {

    private BiometricManager mBiometricManager;

    public FingerprintWrapperApi29(Context context) {
        super(context);
    }

    @Override
    public boolean isSupported() {
        int result = getBiometricManager().canAuthenticate();
        if (result == BiometricManager.BIOMETRIC_ERROR_HW_UNAVAILABLE || result == BiometricManager.BIOMETRIC_ERROR_NO_HARDWARE) {
            return false;
        }
        return true;
    }

    @Override
    public int hasEnrolled() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
            return BiometricManager.BIOMETRIC_SUCCESS == getBiometricManager().canAuthenticate(BiometricManager.Authenticators.DEVICE_CREDENTIAL)
                    ? FingerprintEnrollStatus.STATUS_HAS_ENROLLED : FingerprintEnrollStatus.STATUS_HAS_NO_ENROLLED;
        } else {
            return BiometricManager.BIOMETRIC_SUCCESS == getBiometricManager().canAuthenticate()
                    ? FingerprintEnrollStatus.STATUS_HAS_ENROLLED : FingerprintEnrollStatus.STATUS_HAS_NO_ENROLLED;
        }
    }

    @Override
    public void authenticate(FingerprintAuthenticateCallback fingerPrintAuthenticateCallback) {
    }

    @Override
    public void cancelAuthenticate() {

    }

    private BiometricManager getBiometricManager() {
        if (mBiometricManager == null) {
            mBiometricManager = (BiometricManager) getContext().getSystemService(Context.BIOMETRIC_SERVICE);
        }
        return mBiometricManager;
    }

}
