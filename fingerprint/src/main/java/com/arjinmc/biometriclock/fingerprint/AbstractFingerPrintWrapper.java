package com.arjinmc.biometriclock.fingerprint;

import android.content.Context;
import android.content.pm.PackageManager;

/**
 * Abstract class for FingerPrint Wrapper
 * Created by Eminem Lo on 30/9/2020.
 * email: arjinmc@hotmail.com
 */
class AbstractFingerPrintWrapper implements IFingerPrintWrapper {

    protected Context mContext;

    public AbstractFingerPrintWrapper(Context context) {
        mContext = context;
    }

    public Context getContext() {
        return mContext;
    }

    @Override
    public boolean isSupported() {
        if (mContext == null) {
            return false;
        }
        PackageManager packageManager = mContext.getPackageManager();
        return packageManager.hasSystemFeature(PackageManager.FEATURE_FINGERPRINT);
    }

    @Override
    public boolean hasEnrolled() {
        return false;
    }

    @Override
    public void authenticate(FingerPrintAuthenticateCallback fingerPrintAuthenticateCallback) {

    }

}
