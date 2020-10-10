package com.arjinmc.biometriclock.fingerprint;

import android.app.Dialog;
import android.content.Context;
import android.hardware.fingerprint.FingerprintManager;
import android.os.Build;
import android.os.CancellationSignal;

import androidx.annotation.RequiresApi;

import com.arjinmc.biometriclock.fingerprint.model.FingerprintEnrollStatus;
import com.arjinmc.biometriclock.fingerprint.view.FingerprintAuthenticateDialog;

/**
 * FingerprintWrapper for api above 23
 * Created by Eminem Lo on 30/9/2020.
 * email: arjinmc@hotmail.com
 */
@RequiresApi(api = Build.VERSION_CODES.M)
class FingerprintWrapperApi23 extends AbstractFingerprintWrapper {

    private FingerprintManager mFingerprintManager;
    private FingerprintManager.AuthenticationCallback mAuthenticationCallback;
    private CancellationSignal mCancellationSignal;
    private Dialog mAuthenticateDialog;

    public FingerprintWrapperApi23(Context context) {
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
    public int hasEnrolled() {
        if (mFingerprintManager != null) {
            return mFingerprintManager.hasEnrolledFingerprints() ?
                    FingerprintEnrollStatus.STATUS_HAS_ENROLLED : FingerprintEnrollStatus.STATUS_HAS_NO_ENROLLED;
        } else {
            return FingerprintEnrollStatus.STATUS_UNKNOWN;
        }
    }

    @Override
    public void authenticate(final FingerprintAuthenticateCallback fingerPrintAuthenticateCallback) {
        if (fingerPrintAuthenticateCallback == null) {
            return;
        }

        if (hasEnrolled() == FingerprintEnrollStatus.STATUS_HAS_NO_ENROLLED) {
            fingerPrintAuthenticateCallback.onHasNoEnrolled();
            return;
        }
        if (mAuthenticationCallback == null) {
            mAuthenticationCallback = new FingerprintManager.AuthenticationCallback() {
                @Override
                public void onAuthenticationError(int errorCode, CharSequence errString) {
                    super.onAuthenticationError(errorCode, errString);
                    if (mAuthenticateDialog != null) {
                        mAuthenticateDialog.dismiss();
                    }
                    if (errorCode == FingerprintManager.FINGERPRINT_ERROR_CANCELED) {
                        fingerPrintAuthenticateCallback.onCancel();
                    } else {
                        fingerPrintAuthenticateCallback.onError(errString.toString());
                    }
                }

                @Override
                public void onAuthenticationSucceeded(FingerprintManager.AuthenticationResult result) {
                    super.onAuthenticationSucceeded(result);
                    if (mAuthenticateDialog != null) {
                        mAuthenticateDialog.dismiss();
                    }
                    fingerPrintAuthenticateCallback.onSuccess();
                }

                @Override
                public void onAuthenticationFailed() {
                    super.onAuthenticationFailed();
                    fingerPrintAuthenticateCallback.onFailed();
                }
            };
        }
        mCancellationSignal = new CancellationSignal();
        mFingerprintManager.authenticate(null, mCancellationSignal, 0, mAuthenticationCallback, null);
        if (mAuthenticateDialog == null) {
            mAuthenticateDialog = FingerprintConfig.getInstance(getContext()).getAuthenticateDialog();
            if (mAuthenticateDialog == null) {
                mAuthenticateDialog = new FingerprintAuthenticateDialog(getContext());
            }
        }
        if (!mAuthenticateDialog.isShowing()) {
            mAuthenticateDialog.show();
        }
    }

    @Override
    public void cancelAuthenticate() {
        if (mFingerprintManager == null) {
            return;
        }
        mCancellationSignal.cancel();
        mAuthenticationCallback = null;
        if (mAuthenticateDialog != null) {
            mAuthenticateDialog.dismiss();
        }
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
