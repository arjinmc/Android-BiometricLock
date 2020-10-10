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
     * @param errorMsg
     */
    void onError(String errorMsg);

    /**
     * onFailed
     */
    void onFailed();

    /**
     * onSuccess
     */
    void onSuccess();
}
