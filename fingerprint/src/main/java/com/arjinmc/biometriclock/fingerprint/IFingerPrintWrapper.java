package com.arjinmc.biometriclock.fingerprint;

/**
 * Interface for common methods of FingerPrintWrapper
 * Created by Eminem Lo on 2020-01-21.
 * email: arjinmc@hotmail.com
 */
interface IFingerPrintWrapper {

    /**
     * check the device is support Finger print
     *
     * @return
     */
    boolean isSupported();

    /**
     * check if user has enrolled at least one finger print
     *
     * @return
     */
    boolean hasEnrolled();

    /**
     * authenticate finger print
     *
     * @param fingerPrintAuthenticateCallback
     */
    void authenticate(FingerPrintAuthenticateCallback fingerPrintAuthenticateCallback);

}
