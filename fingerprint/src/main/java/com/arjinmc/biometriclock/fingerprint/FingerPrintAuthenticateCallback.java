package com.arjinmc.biometriclock.fingerprint;

/**
 * Authenticate callback for finger print
 * Created by Eminem Lo on 30/9/2020.
 * email: arjinmc@hotmail.com
 */
public interface FingerPrintAuthenticateCallback {

    /**
     * onError
     *
     * @param errorCode
     * @param errorMsg
     */
    void onError(int errorCode,String errorMsg);

    /**
     * onSuccess
     */
    void onSuccess();
}
