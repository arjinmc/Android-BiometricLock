package com.arjinmc.biometriclock.fingerprint;

import android.app.Dialog;
import android.content.Context;

import androidx.annotation.StringRes;

/**
 * Fingerprint Config
 * Created by Eminem Lo on 9/10/2020.
 * email: arjinmc@hotmail.com
 */
public final class FingerprintConfig {

    private static FingerprintConfig mFingerprintConfig;
    private static Context mContext;
    /**
     * Authenticate dialog title
     */
    private String mTitle;
    /**
     * Authenticate dialog subtitle
     */
    private String mSubtitle;
    /**
     * Authenticate dialog description
     */
    private String mDescription;
    /**
     * Authenticate dialog tips remind user to touch sensor
     */
    private String mTouchSensorTips;
    /**
     * Authenticate dialog cancel button text
     */
    private String mCancelText;
    private Dialog mAuthenticateDialog;

    private FingerprintConfig() {
    }

    public static FingerprintConfig getInstance(Context context) {
        if (mFingerprintConfig == null) {
            mFingerprintConfig = new FingerprintConfig();
        }
        mContext = context;
        return mFingerprintConfig;
    }

    public void setTitle(String title) {
        mTitle = title;
    }

    public void setTitle(@StringRes int title) {
        mTitle = mContext.getString(title);
    }

    public String getTitle() {
        return mTitle;
    }

    public void setSubtitle(String subtitle) {
        mSubtitle = subtitle;
    }

    public void setSubtitle(@StringRes int subtitle) {
        mSubtitle = mContext.getString(subtitle);
    }

    public String getSubtitle() {
        return mSubtitle;
    }

    public void setDescription(String description) {
        mDescription = description;
    }

    public void setDescription(@StringRes int description) {
        mDescription = mContext.getString(description);
    }

    public String getDescription() {
        return mDescription;
    }

    public String getTouchSensorTips() {
        return mTouchSensorTips;
    }

    public void setTouchSensorTips(String touchSensorTips) {
        mTouchSensorTips = touchSensorTips;
    }

    public void setTouchSensorTips(@StringRes int touchSensorTips) {
        mTouchSensorTips = mContext.getString(touchSensorTips);
    }

    public void setCancelText(String cancelText) {
        mCancelText = cancelText;
    }

    public void setCancelText(@StringRes int cancelText) {
        mCancelText = mContext.getString(cancelText);
    }

    public String getCancelText() {
        return mCancelText;
    }

    public void setAuthenticateDialog(Dialog dialog) {
        mAuthenticateDialog = dialog;
    }

    public Dialog getAuthenticateDialog() {
        return mAuthenticateDialog;
    }
}
