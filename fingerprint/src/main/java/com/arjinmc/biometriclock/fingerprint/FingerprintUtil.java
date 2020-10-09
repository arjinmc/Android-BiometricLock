package com.arjinmc.biometriclock.fingerprint;

import android.content.Context;

/**
 * Fingerprint util
 * Created by Eminem Lo on 2020-01-21.
 * email: arjinmc@hotmail.com
 */
public final class FingerprintUtil {

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
        return FingerprintWrapper.getInstance(context).isSupported();
    }

    /**
     * check if user has enrolled at least one fingerprint
     *
     * @param context
     * @return
     */
    public static boolean hasEnrolled(Context context) {
        if (context == null) {
            return false;
        }
        return FingerprintWrapper.getInstance(context).hasEnrolled();
    }

    /**
     * call authenticate to use fingerprint
     *
     * @param context
     * @param fingerPrintAuthenticateCallback
     */
    public static void authenticate(Context context, FingerprintAuthenticateCallback fingerPrintAuthenticateCallback) {
        if (context==null || fingerPrintAuthenticateCallback == null) {
            return;
        }
        FingerprintWrapper.getInstance(context).authenticate(fingerPrintAuthenticateCallback);
    }

    public static void cancelAuthenticate(Context context){
        if(context==null){
            return;
        }
        FingerprintWrapper.getInstance(context).cancelAuthenticate();
    }

}
