package com.arjinmc.biometriclock.fingerprint;

import android.content.Context;
import android.content.pm.PackageManager;

/**
 * Abstract class for FingerPrint Wrapper
 * Created by Eminem Lo on 30/9/2020.
 * email: arjinmc@hotmail.com
 */
class AbstractFingerPrintWrapper implements IFingerPrintWrapper {

    @Override
    public boolean isSupported(Context context) {
        if (context == null) {
            return false;
        }
        PackageManager packageManager = context.getPackageManager();
        return packageManager.hasSystemFeature(PackageManager.FEATURE_FINGERPRINT);
    }

    @Override
    public boolean hasEnrolled(Context context) {
        return false;
    }

}
