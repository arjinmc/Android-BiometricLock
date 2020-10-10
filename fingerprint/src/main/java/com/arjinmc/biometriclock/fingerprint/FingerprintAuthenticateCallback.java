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
     * onHasNoEnrolled
     */
    void onHasNoEnrolled();

    /**
     * onCancel
     */
    void onCancel();

    /**
     * onSuccess
     */
    void onSuccess();
}
