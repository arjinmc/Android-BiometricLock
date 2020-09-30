package com.arjinmc.biometriclock.fingerprint;

import android.content.Context;
import android.os.Build;

/**
 * Finger print wrapper
 * Created by Eminem Lo on 2020-01-21.
 * email: arjinmc@hotmail.com
 */
public class FingerPrintWrapper extends AbstractFingerPrintWrapper {

    private static AbstractFingerPrintWrapper mFingerPrintWrapper;

    public static AbstractFingerPrintWrapper getInstance() {
        if (mFingerPrintWrapper == null) {
            //get instance for different platform version
            if (isAboveApi29()) {
                mFingerPrintWrapper = new FingerPrintWrapperApi23();
            } else if (isAboveApi28()) {
                mFingerPrintWrapper = new FingerPrintWrapperApi23();
            } else if (isAboveApi23()) {
                mFingerPrintWrapper = new FingerPrintWrapperApi23();
            }
        }
        return mFingerPrintWrapper;
    }

    @Override
    public boolean hasEnrolled(Context context) {

        if (context == null) {
            return false;
        }

        if (mFingerPrintWrapper == null) {
            return false;
        }
        return mFingerPrintWrapper.hasEnrolled(context);
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
