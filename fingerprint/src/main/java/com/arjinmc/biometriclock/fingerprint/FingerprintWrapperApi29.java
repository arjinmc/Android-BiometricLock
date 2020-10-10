package com.arjinmc.biometriclock.fingerprint;

import android.content.Context;
import android.content.DialogInterface;
import android.hardware.biometrics.BiometricManager;
import android.hardware.biometrics.BiometricPrompt;
import android.os.Build;
import android.os.CancellationSignal;
import android.text.TextUtils;

import androidx.annotation.RequiresApi;

import com.arjinmc.biometriclock.fingerprint.model.FingerprintEnrollStatus;

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

/**
 * FingerprintWrapper for api above 29
 * Created by Eminem Lo on 30/9/2020.
 * email: arjinmc@hotmail.com
 */
@RequiresApi(api = Build.VERSION_CODES.Q)
class FingerprintWrapperApi29 extends AbstractFingerprintWrapper {

    private BiometricManager mBiometricManager;
    private BiometricPrompt mBiometricPrompt;
    private BiometricPrompt.AuthenticationCallback mAuthenticationCallback;
    private CancellationSignal mCancellationSignal;
    private Executor mExecutor;

    public FingerprintWrapperApi29(Context context) {
        super(context);
    }

    @Override
    public boolean isSupported() {
        int result = getBiometricManager().canAuthenticate();
        if (result == BiometricManager.BIOMETRIC_ERROR_HW_UNAVAILABLE || result == BiometricManager.BIOMETRIC_ERROR_NO_HARDWARE) {
            return false;
        }
        return true;
    }

    @Override
    public int hasEnrolled() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
            return BiometricManager.BIOMETRIC_SUCCESS == getBiometricManager().canAuthenticate(BiometricManager.Authenticators.DEVICE_CREDENTIAL)
                    ? FingerprintEnrollStatus.STATUS_HAS_ENROLLED : FingerprintEnrollStatus.STATUS_HAS_NO_ENROLLED;
        } else {
            return BiometricManager.BIOMETRIC_SUCCESS == getBiometricManager().canAuthenticate()
                    ? FingerprintEnrollStatus.STATUS_HAS_ENROLLED : FingerprintEnrollStatus.STATUS_HAS_NO_ENROLLED;
        }
    }

    @Override
    public void authenticate(final FingerprintAuthenticateCallback fingerPrintAuthenticateCallback) {

        if (fingerPrintAuthenticateCallback == null) {
            return;
        }

        if (hasEnrolled() == FingerprintEnrollStatus.STATUS_HAS_NO_ENROLLED) {
            return;
        }
        mCancellationSignal = new CancellationSignal();

        if (mAuthenticationCallback == null) {
            mAuthenticationCallback = new BiometricPrompt.AuthenticationCallback() {
                @Override
                public void onAuthenticationError(int errorCode, CharSequence errString) {
                    super.onAuthenticationError(errorCode, errString);
                    if (errorCode == BiometricPrompt.BIOMETRIC_ERROR_CANCELED) {
                        fingerPrintAuthenticateCallback.onCancel();
                    } else {
                        fingerPrintAuthenticateCallback.onError(errString.toString());
                    }
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

    private BiometricManager getBiometricManager() {
        if (mBiometricManager == null) {
            mBiometricManager = (BiometricManager) getContext().getSystemService(Context.BIOMETRIC_SERVICE);
        }
        return mBiometricManager;
    }

    private BiometricPrompt getBiometricPrompt() {
        if (mBiometricPrompt == null) {
            mExecutor = Executors.newSingleThreadExecutor();
            BiometricPrompt.Builder builder = new BiometricPrompt.Builder(getContext());
            FingerprintConfig fingerprintConfig = FingerprintConfig.getInstance(getContext());

            builder.setTitle(TextUtils.isEmpty(fingerprintConfig.getTitle())
                    ? getContext().getString(R.string.biometriclock_authenticate_fingerprint_dialog_title)
                    : fingerprintConfig.getTitle());
            if (!TextUtils.isEmpty(fingerprintConfig.getSubtitle())) {
                builder.setSubtitle(fingerprintConfig.getSubtitle());
            }
            if (!TextUtils.isEmpty(fingerprintConfig.getDescription())) {
                builder.setDescription(fingerprintConfig.getDescription());
            }
            builder.setNegativeButton(
                    TextUtils.isEmpty(fingerprintConfig.getCancelText())
                            ? getContext().getString(R.string.biometriclock_fingerprint_cancel)
                            : fingerprintConfig.getCancelText()
                    , mExecutor, new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                        }
                    });
            mBiometricPrompt = builder.build();
        }
        return mBiometricPrompt;
    }

}
