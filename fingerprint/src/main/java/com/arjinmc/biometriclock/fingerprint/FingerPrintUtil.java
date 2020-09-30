package com.arjinmc.biometriclock.fingerprint;

import android.content.Context;

/**
 * Finger print util
 * Created by Eminem Lo on 2020-01-21.
 * email: arjinmc@hotmail.com
 */
public final class FingerPrintUtil {

    public static boolean isSupport(Context context) {
        if (context == null) {
            return false;
        }
        return FingerPrintWrapper.getInstance().hasEnrolled(context);
    }
}
