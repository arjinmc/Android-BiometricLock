package com.arjinmc.biometriclock.fingerprint.view;

import android.app.Dialog;
import android.content.Context;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

/**
 * Define your own AuthenticateDialog as extend AbstractFingerprintAuthenticateDialog
 * Created by Eminem Lo on 10/10/2020.
 * email: arjinmc@hotmail.com
 */
public class AbstractFingerprintAuthenticateDialog extends Dialog implements IFingerprintAuthenticateDialog {

    public AbstractFingerprintAuthenticateDialog(@NonNull Context context) {
        super(context);
    }

    public AbstractFingerprintAuthenticateDialog(@NonNull Context context, int themeResId) {
        super(context, themeResId);
    }

    protected AbstractFingerprintAuthenticateDialog(@NonNull Context context, boolean cancelable, @Nullable OnCancelListener cancelListener) {
        super(context, cancelable, cancelListener);
    }

    @Override
    public void onAuthenticateFailed() {

    }

    @Override
    public void onReset() {

    }
}
