package com.arjinmc.biometriclock.fingerprint;

import android.content.Context;
import android.os.Build;

import com.arjinmc.biometriclock.fingerprint.model.FingerprintEnrollStatus;

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

        if (!isSystemVersionSupportFingerprint()) {
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
    public static int hasEnrolled(Context context) {
        if (context == null) {
            return FingerprintEnrollStatus.STATUS_UNKNOWN;
        }

        if (!isSystemVersionSupportFingerprint()) {
            return FingerprintEnrollStatus.STATUS_UNKNOWN;
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
        if (context == null || fingerPrintAuthenticateCallback == null) {
            return;
        }

        if (!isSystemVersionSupportFingerprint()) {
            return;
        }

        FingerprintWrapper.getInstance(context).authenticate(fingerPrintAuthenticateCallback);
    }

    public static void cancelAuthenticate(Context context) {
        if (context == null) {
            return;
        }

        if (!isSystemVersionSupportFingerprint()) {
            return;
        }

        FingerprintWrapper.getInstance(context).cancelAuthenticate();
    }

    private static boolean isSystemVersionSupportFingerprint() {
        return Build.VERSION.SDK_INT >= Build.VERSION_CODES.M;
    }

}
