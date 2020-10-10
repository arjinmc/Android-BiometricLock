package com.arjinmc.biometriclock.fingerprint.model;

/**
 * Fingerprint Enroll status
 * Created by Eminem Lo on 9/10/2020.
 * email: arjinmc@hotmail.com
 */
public final class FingerprintEnrollStatus {

    /**
     * unknown whether has enrolled
     */
    public static final int STATUS_UNKNOWN = -1;
    /**
     * has not enrolled
     */
    public static final int STATUS_HAS_NO_ENROLLED = 0;
    /**
     * has enrolled
     */
    public static final int STATUS_HAS_ENROLLED = 1;

    public static final String getStatusName(int status) {
        switch (status) {
            case STATUS_HAS_NO_ENROLLED:
                return "HAS NOT ENROLLED";
            case STATUS_HAS_ENROLLED:
                return "HAS ENROLLED";
            default:
                return "UNKNOWN";
        }
    }
}
