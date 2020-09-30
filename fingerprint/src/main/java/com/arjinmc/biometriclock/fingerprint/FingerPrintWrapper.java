package com.arjinmc.biometriclock.fingerprint;

import android.content.Context;
import android.os.Build;

/**
 * Finger print wrapper
 * Created by Eminem Lo on 2020-01-21.
 * email: arjinmc@hotmail.com
 */
class FingerPrintWrapper extends AbstractFingerPrintWrapper {

    private static AbstractFingerPrintWrapper mFingerPrintWrapper;

    public FingerPrintWrapper(Context context) throws IllegalAccessException {
        super(context);
        throw new IllegalAccessException("Use getInstance(context) to get the instance");
    }

    public static AbstractFingerPrintWrapper getInstance(Context context) {
        if (mFingerPrintWrapper == null) {
            //get instance for different platform version
            if (isAboveApi29()) {
                mFingerPrintWrapper = new FingerPrintWrapperApi29(context);
            } else if (isAboveApi28()) {
                mFingerPrintWrapper = new FingerPrintWrapperApi28(context);
            } else if (isAboveApi23()) {
                mFingerPrintWrapper = new FingerPrintWrapperApi23(context);
            }
        }
        return mFingerPrintWrapper;
    }

    @Override
    public boolean hasEnrolled() {

        if (mFingerPrintWrapper == null) {
            return false;
        }
        return mFingerPrintWrapper.hasEnrolled();
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
