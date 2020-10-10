package com.arjinmc.biometriclock.fingerprint;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Build;
import android.util.Log;

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

            //get instance for different platform version
            mFingerprintWrapper = getBetterFingerprintWrapper(context);
            //for test
//                mFingerprintWrapper = new FingerprintWrapperApi29(context);
//                mFingerprintWrapper = new FingerprintWrapperApi28(context);
//                mFingerprintWrapper = new FingerprintWrapperApi23(context);
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
}
