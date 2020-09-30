package com.arjinmc.biometriclock.fingerprint;

import android.content.Context;

/**
 * Finger print util
 * Created by Eminem Lo on 2020-01-21.
 * email: arjinmc@hotmail.com
 */
public final class FingerPrintUtil {

    /**
     * check if device support finger print
     *
     * @param context
     * @return
     */
    public static boolean isSupport(Context context) {
        if (context == null) {
            return false;
        }
        return FingerPrintWrapper.getInstance(context).isSupported();
    }

    /**
     * check if user has enrolled at least one finger print
     *
     * @param context
     * @return
     */
    public static boolean hasEnrolled(Context context) {
        if (context == null) {
            return false;
        }
        return FingerPrintWrapper.getInstance(context).hasEnrolled();
    }

    /**
     * call authenticate to use finger print
     *
     * @param context
     * @param fingerPrintAuthenticateCallback
     */
    public static void authenticate(Context context, FingerPrintAuthenticateCallback fingerPrintAuthenticateCallback) {
        if (fingerPrintAuthenticateCallback == null) {
            return;
        }
        FingerPrintWrapper.getInstance(context).authenticate(fingerPrintAuthenticateCallback);
    }
}
