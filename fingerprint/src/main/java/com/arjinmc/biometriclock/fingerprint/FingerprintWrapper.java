package com.arjinmc.biometriclock.fingerprint;

import android.content.Context;
import android.os.Build;

/**
 * Fingerprint wrapper
 * Created by Eminem Lo on 2020-01-21.
 * email: arjinmc@hotmail.com
 */
class FingerprintWrapper extends AbstractFingerprintWrapper {

    private static AbstractFingerprintWrapper mFingerprintWrapper;

    public FingerprintWrapper(Context context) throws IllegalAccessException {
        super(context);
        throw new IllegalAccessException("Use getInstance(context) to get the instance");
    }

    public static AbstractFingerprintWrapper getInstance(Context context) {
        if (mFingerprintWrapper == null) {
            //get instance for different platform version
//            if (isAboveApi29()) {
//                mFingerPrintWrapper = new FingerprintWrapperApi29(context);
//            } else if (isAboveApi28()) {
//                mFingerPrintWrapper = new FingerprintWrapperApi28(context);
//            } else if (isAboveApi23()) {
                mFingerprintWrapper = new FingerprintWrapperApi23(context);
//            }
        }
        return mFingerprintWrapper;
    }

    @Override
    public boolean hasEnrolled() {

        if (mFingerprintWrapper == null) {
            return false;
        }
        return mFingerprintWrapper.hasEnrolled();
    }

    private static boolean isAboveApi29() {
        return Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q;
    }

    private static boolean isAboveApi28() {
        return Build.VERSION.SDK_INT >= Build.VERSION_CODES.P;
    }

    private static boolean isAboveApi23() {
        return Build.VERSION.SDK_INT >= Build.VERSION_CODES.M;
    }
}
