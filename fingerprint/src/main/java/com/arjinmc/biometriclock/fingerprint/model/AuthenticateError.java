package com.arjinmc.biometriclock.fingerprint.model;

/**
 * Authenticate Error
 * Created by Eminem Lo on 30/9/2020.
 * email: arjinmc@hotmail.com
 */
public class AuthenticateError {

    public static final int ERROR_NO_ERROR = 0;
    public static final int ERROR_FAILED = 1;
    public static final int ERROR_NEED_HELP = 2;
    public static final int ERROR_AUTHENTICATE_ERROR = 3;

    public static final String formatErrorMessage(int code, CharSequence msg) {
        return code + ":" + msg.toString();
    }
}
