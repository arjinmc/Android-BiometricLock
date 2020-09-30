package com.arjinmc.biometriclock.fingerprint;

import android.content.Context;
import android.hardware.fingerprint.FingerprintManager;
import android.os.Build;
import android.os.CancellationSignal;

import androidx.annotation.RequiresApi;

import com.arjinmc.biometriclock.fingerprint.model.AuthenticateError;

/**
 * FingerPrintWrapper for api above 23
 * Created by Eminem Lo on 30/9/2020.
 * email: arjinmc@hotmail.com
 */
@RequiresApi(api = Build.VERSION_CODES.M)
class FingerPrintWrapperApi23 extends AbstractFingerPrintWrapper {

    private FingerprintManager mFingerprintManager;
    private FingerprintManager.AuthenticationCallback mAuthenticationCallback;

    public FingerPrintWrapperApi23(Context context) {
        super(context);
        mFingerprintManager = getFingerprintManager();
    }

    @Override
    public boolean isSupported() {
        if (mFingerprintManager != null) {
            return mFingerprintManager.isHardwareDetected();
        }
        return false;
    }

    @Override
    public boolean hasEnrolled() {
        if (mFingerprintManager != null) {
            return mFingerprintManager.hasEnrolledFingerprints();
        } else {
            return false;
        }
    }

    @Override
    public void authenticate(final FingerPrintAuthenticateCallback fingerPrintAuthenticateCallback) {
        if (fingerPrintAuthenticateCallback == null) {
            return;
        }
        if (mAuthenticationCallback == null) {
            mAuthenticationCallback = new FingerprintManager.AuthenticationCallback() {
                @Override
                public void onAuthenticationError(int errorCode, CharSequence errString) {
                    super.onAuthenticationError(errorCode, errString);
                    fingerPrintAuthenticateCallback.onError(AuthenticateError.ERROR_AUTHENTICATE_ERROR
                            , AuthenticateError.formatErrorMessage(errorCode, errString.toString()));
                }

                @Override
                public void onAuthenticationHelp(int helpCode, CharSequence helpString) {
                    super.onAuthenticationHelp(helpCode, helpString);
                    fingerPrintAuthenticateCallback.onError(AuthenticateError.ERROR_NEED_HELP
                            , AuthenticateError.formatErrorMessage(helpCode, helpString.toString()));
                }

                @Override
                public void onAuthenticationSucceeded(FingerprintManager.AuthenticationResult result) {
                    super.onAuthenticationSucceeded(result);
                    fingerPrintAuthenticateCallback.onSuccess();
                }

                @Override
                public void onAuthenticationFailed() {
                    super.onAuthenticationFailed();
                    fingerPrintAuthenticateCallback.onError(AuthenticateError.ERROR_FAILED, "authentication failed");
                }
            };
        }
        mFingerprintManager.authenticate(null, new CancellationSignal(), 0, mAuthenticationCallback, null);
    }

    private FingerprintManager getFingerprintManager() {

        if (mContext == null) {
            return null;
        }

        if (mFingerprintManager == null) {
            mFingerprintManager = (FingerprintManager) getContext().getSystemService(Context.FINGERPRINT_SERVICE);
        }
        return mFingerprintManager;
    }
}
