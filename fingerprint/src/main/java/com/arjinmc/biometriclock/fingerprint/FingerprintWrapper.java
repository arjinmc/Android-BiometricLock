package com.arjinmc.biometriclock.fingerprint;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Build;

import androidx.annotation.RequiresApi;

import com.arjinmc.biometriclock.fingerprint.model.FingerprintEnrollStatus;

/**
 * Fingerprint wrapper
 * Created by Eminem Lo on 2020-01-21.
 * email: arjinmc@hotmail.com
 */
@RequiresApi(api = Build.VERSION_CODES.M)
class FingerprintWrapper extends AbstractFingerprintWrapper {

    private static AbstractFingerprintWrapper mFingerprintWrapper;

    public FingerprintWrapper(Context context) throws IllegalAccessException {
        super(context);
        throw new IllegalAccessException("Use getInstance(context) to get the instance");
    }

    public static AbstractFingerprintWrapper getInstance(Context context) {
        if (context == null) {
            return null;
        }
        if (mFingerprintWrapper == null) {
            try {

                //get instance for different platform version

                mFingerprintWrapper = getBetterFingerprintWrapper(context);
//            if (isAboveApi29()) {
//                mFingerprintWrapper = new FingerprintWrapperApi29(context);
//            } else if (isAboveApi28()) {
//                mFingerprintWrapper = new FingerprintWrapperApi28(context);
//            } else if (isAboveApi23()) {
//                mFingerprintWrapper = new FingerprintWrapperApi23(context);
//            }
            } catch (Exception e) {
                if (isAboveApi23()) {
//                mFingerprintWrapper = new FingerprintWrapperApi23(context);
                }
            }
        }
        return mFingerprintWrapper;
    }

    @Override
    public int hasEnrolled() {

        if (mFingerprintWrapper == null) {
            return FingerprintEnrollStatus.STATUS_UNKNOWN;
        }
        return mFingerprintWrapper.hasEnrolled();
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    private static AbstractFingerprintWrapper getBetterFingerprintWrapper(Context context) {

        AbstractFingerprintWrapper fingerprintWrapper;
        fingerprintWrapper = tryFingerWrapper(context, Build.VERSION_CODES.M);
        if (fingerprintWrapper == null && Build.VERSION.SDK_INT >= Build.VERSION_CODES.P) {
            fingerprintWrapper = tryFingerWrapper(context, Build.VERSION_CODES.P);
        }
        if (fingerprintWrapper == null && Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
            fingerprintWrapper = tryFingerWrapper(context, Build.VERSION_CODES.Q);
        }
        return fingerprintWrapper;
    }

    @SuppressLint("NewApi")
    private static AbstractFingerprintWrapper tryFingerWrapper(Context context, int targetApi) {
        AbstractFingerprintWrapper fingerprintWrapper;
        try {
            switch (targetApi) {
                case Build.VERSION_CODES.M:
                default:
                    fingerprintWrapper = new FingerprintWrapperApi23(context);
                    break;
                case Build.VERSION_CODES.P:
                    fingerprintWrapper = new FingerprintWrapperApi28(context);
                    break;
                case Build.VERSION_CODES.Q:
                    fingerprintWrapper = new FingerprintWrapperApi29(context);
                    break;
            }
            fingerprintWrapper.isSupported();
            return fingerprintWrapper;
        } catch (Exception e) {
            return null;
        }
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
