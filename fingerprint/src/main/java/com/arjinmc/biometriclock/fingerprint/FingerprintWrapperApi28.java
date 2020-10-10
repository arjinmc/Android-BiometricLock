package com.arjinmc.biometriclock.fingerprint;

import android.content.Context;
import android.content.DialogInterface;
import android.hardware.biometrics.BiometricPrompt;
import android.os.Build;
import android.os.CancellationSignal;

import androidx.annotation.RequiresApi;

import com.arjinmc.biometriclock.fingerprint.model.FingerprintEnrollStatus;

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

/**
 * FingerprintWrapper for api above 28
 * Created by Eminem Lo on 30/9/2020.
 * email: arjinmc@hotmail.com
 */
@RequiresApi(api = Build.VERSION_CODES.P)
class FingerprintWrapperApi28 extends AbstractFingerprintWrapper {

    private BiometricPrompt mBiometricPrompt;
    private BiometricPrompt.AuthenticationCallback mAuthenticationCallback;
    private CancellationSignal mCancellationSignal;
    private Executor mExecutor;

    public FingerprintWrapperApi28(Context context) {
        super(context);
    }

    @Override
    public int hasEnrolled() {
        if (getBiometricPrompt() != null) {
            int result;
            try {
                result = getBiometricPrompt().getAllowedAuthenticators();
            } catch (Exception e) {
                return FingerprintEnrollStatus.STATUS_UNKNOWN;
            }
            return result > 0 ? FingerprintEnrollStatus.STATUS_HAS_ENROLLED : FingerprintEnrollStatus.STATUS_NONE_ENROLLED;

        } else {
            return FingerprintEnrollStatus.STATUS_UNKNOWN;
        }
    }

    @Override
    public void authenticate(final FingerprintAuthenticateCallback fingerPrintAuthenticateCallback) {
        mCancellationSignal = new CancellationSignal();

        if (mAuthenticationCallback == null) {
            mAuthenticationCallback = new BiometricPrompt.AuthenticationCallback() {
                @Override
                public void onAuthenticationError(int errorCode, CharSequence errString) {
                    super.onAuthenticationError(errorCode, errString);
                    fingerPrintAuthenticateCallback.onError(errString.toString());
                }

                @Override
                public void onAuthenticationSucceeded(BiometricPrompt.AuthenticationResult result) {
                    super.onAuthenticationSucceeded(result);
                    fingerPrintAuthenticateCallback.onSuccess();
                }

                @Override
                public void onAuthenticationFailed() {
                    super.onAuthenticationFailed();
                    fingerPrintAuthenticateCallback.onFailed();
                }
            };
        }
        getBiometricPrompt().authenticate(mCancellationSignal, mExecutor, mAuthenticationCallback);

    }

    @Override
    public void cancelAuthenticate() {
        if (mCancellationSignal != null) {
            mCancellationSignal.isCanceled();
        }
        mAuthenticationCallback = null;
    }

    private BiometricPrompt getBiometricPrompt() {
        if (mBiometricPrompt == null) {
            mExecutor = Executors.newSingleThreadExecutor();
            mBiometricPrompt = new BiometricPrompt.Builder(getContext())
                    .setTitle("BiometricPrompt")
                    .setSubtitle("hello")
                    .setDescription("okbo")
                    .setNegativeButton("cancel", mExecutor, new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                        }
                    }).build();
        }
        return mBiometricPrompt;
    }
}
