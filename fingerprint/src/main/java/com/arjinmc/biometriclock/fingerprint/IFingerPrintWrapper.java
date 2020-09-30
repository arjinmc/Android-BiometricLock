package com.arjinmc.biometriclock.fingerprint;

import android.content.Context;

/**
 * Interface for common methods of FingerPrintWrapper
 * Created by Eminem Lo on 2020-01-21.
 * email: arjinmc@hotmail.com
 */
interface IFingerPrintWrapper {

    /**
     * check the device is support Finger print
     *
     * @param context
     * @return
     */
    boolean isSupported(Context context);

    /**
     * check if user has enrolled at least one finger print
     *
     * @param context
     * @return
     */
    boolean hasEnrolled(Context context);

}
