package com.arjinmc.biometriclock.fingerprint;

/**
 * Authenticate callback for fingerprint
 * Created by Eminem Lo on 30/9/2020.
 * email: arjinmc@hotmail.com
 */
public interface FingerprintAuthenticateCallback {

    /**
     * onError
     *
     * @param errorCode
     * @param errorMsg
     */
    void onError(int errorCode,String errorMsg);

    /**
     * onFailed
     */
    void onFailed();

    /**
     * onSuccess
     */
    void onSuccess();
}
